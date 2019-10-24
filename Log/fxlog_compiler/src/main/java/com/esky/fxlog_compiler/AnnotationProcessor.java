package com.esky.fxlog_compiler;

import com.esky.fxlog_annotation.Converter;
import com.esky.fxlog_annotation.Printer;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import static javax.lang.model.element.Modifier.ABSTRACT;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

/**
 * @author AirLand
 * @time on 2019-09-27 15:00
 * @email lish_air@163.com
 * @jianshu https://www.jianshu.com/u/816932948905
 * @gitHub https://github.com/LiShiHui24740
 * @describe:
 */
public class AnnotationProcessor extends AbstractProcessor {

    private Filer filerUtils;
    private List<FieldSpec> printerFieldSpecs;
    private List<FieldSpec> converterFieldSpecs;
    private List<MethodSpec> methodSpecs;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filerUtils = processingEnvironment.getFiler();
        printerFieldSpecs = new ArrayList<>();
        converterFieldSpecs = new ArrayList<>();
        methodSpecs = new ArrayList<>();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new HashSet<>();
        annotations.add(Converter.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        createDefaultMethod();
        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(Printer.class)) {
            TypeElement typeElement = (TypeElement) annotatedElement;
            checkParamsValidClass(typeElement);
            ClassName className = ClassName.get(typeElement);
            String printerName = creatPrinter(className, typeElement.getAnnotation(Printer.class).name());
            creatMethod(printerName, null, printerName, 0);
        }
        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(Converter.class)) {
            TypeElement typeElement = (TypeElement) annotatedElement;
            checkParamsValidClass(typeElement);
            ClassName className = ClassName.get(typeElement);
            String converterName = creatConverter(className, typeElement.getAnnotation(Converter.class).name());
            creatMethod("logPrinter", converterName, converterName, 0);
            creatMethod("logPrinter", converterName, "Thread" + converterName, 1);
            creatMethod("logPrinter", converterName, "Stack" + converterName, 2);
        }
        createJavaFile();
        return true;
    }

    private String creatPrinter(ClassName className, String name) {
        String fieldName = captureNameLowerCase(name.endsWith("Printer") || name.endsWith("printer") ? name : name + "Printer");
        FieldSpec fieldSpec = FieldSpec.builder(className, fieldName)
            .addModifiers(PRIVATE, STATIC)
            .initializer("new $T()", className)
            .build();
        printerFieldSpecs.add(fieldSpec);
        return fieldName;
    }

    private String creatConverter(ClassName className, String name) {
        String fieldName = captureNameLowerCase((name.endsWith("Converter") || name.endsWith("converter") ? name : name + "Converter"));
        FieldSpec fieldSpec = FieldSpec.builder(className, fieldName)
            .addModifiers(PRIVATE, STATIC)
            .initializer("new $T()", className)
            .build();
        converterFieldSpecs.add(fieldSpec);
        return fieldName;
    }

    private void createDefaultMethod() {
        ClassName logPrinter = ClassName.get("com.esky.fxlog.print", "LogPrinter");
        creatPrinter(logPrinter, "logPrinter");
        ClassName noConverter = ClassName.get("com.esky.fxlog.converter", "NoConverter");
        ClassName jsonConverter = ClassName.get("com.esky.fxlog.converter", "JsonConverter");
        creatConverter(noConverter, "noConverter");
        creatConverter(jsonConverter, "jsonConverter");
        creatMethod("logPrinter", "noConverter", "", 0);
        creatMethod("logPrinter", "noConverter", "Thread", 1);
        creatMethod("logPrinter", "noConverter", "Stack", 2);
        creatMethod("logPrinter", "jsonConverter", "Json", 0);
        creatMethod("logPrinter", "jsonConverter", "ThreadJson", 1);
        creatMethod("logPrinter", "jsonConverter", "StackJson", 2);
    }

    private void creatMethod(String printer, String converter, String methodName, int showLevel) {
        methodName = captureNameUpperCase(methodName.replaceAll("Converter", "").replace("converter", ""));
        MethodSpec methodSpec1 = MethodSpec.methodBuilder("d" + methodName)
            .addModifiers(PUBLIC, STATIC)
            .addParameter(String.class, "tag")
            .addParameter(String.class, "message")
            .addStatement("logImp.printLogD($L,$L,$L, tag, message)", printer, converter, showLevel)
            .build();
        methodSpecs.add(methodSpec1);
        MethodSpec methodSpec2 = MethodSpec.methodBuilder("e" + methodName)
            .addModifiers(PUBLIC, STATIC)
            .addParameter(String.class, "tag")
            .addParameter(String.class, "message")
            .addStatement("logImp.printLogE($L,$L,$L, tag, message)", printer, converter, showLevel)
            .build();
        methodSpecs.add(methodSpec2);
        MethodSpec methodSpec3 = MethodSpec.methodBuilder("v" + methodName)
            .addModifiers(PUBLIC, STATIC)
            .addParameter(String.class, "tag")
            .addParameter(String.class, "message")
            .addStatement("logImp.printLogV($L,$L,$L, tag, message)", printer, converter, showLevel)
            .build();
        methodSpecs.add(methodSpec3);
        MethodSpec methodSpec4 = MethodSpec.methodBuilder("w" + methodName)
            .addModifiers(PUBLIC, STATIC)
            .addParameter(String.class, "tag")
            .addParameter(String.class, "message")
            .addStatement("logImp.printLogW($L,$L,$L, tag, message)", printer, converter, showLevel)
            .build();
        methodSpecs.add(methodSpec4);
        MethodSpec methodSpec5 = MethodSpec.methodBuilder("i" + methodName)
            .addModifiers(PUBLIC, STATIC)
            .addParameter(String.class, "tag")
            .addParameter(String.class, "message")
            .addStatement("logImp.printLogI($L,$L,$L, tag, message)", printer, converter, showLevel)
            .build();
        methodSpecs.add(methodSpec5);

    }

    private void createJavaFile() {
        ClassName className = ClassName.get("com.esky.fxlog.print", "LogImp");
        FieldSpec logImp = FieldSpec.builder(className, "logImp")
            .addModifiers(PRIVATE, STATIC)
            .initializer("new $T()", className)
            .build();
        TypeSpec.Builder builder = TypeSpec.classBuilder("FxLog");
        builder.addModifiers(PUBLIC, FINAL);
        builder.addField(logImp);
        for (FieldSpec fieldSpec1 : printerFieldSpecs) {
            builder.addField(fieldSpec1);
        }
        for (FieldSpec fieldSpec1 : converterFieldSpecs) {
            builder.addField(fieldSpec1);
        }
        ClassName logConfig = ClassName.get("com.esky.fxlog.config", "LogConfig");
        MethodSpec initMethod = MethodSpec.methodBuilder("init")
            .addModifiers(PUBLIC, STATIC)
            .addParameter(boolean.class, "isDebug")
            .addStatement("$T.getInstance().setDebug(isDebug)", logConfig)
            .build();
        builder.addMethod(initMethod);
        for (MethodSpec methodSpec : methodSpecs) {
            builder.addMethod(methodSpec);
        }
        TypeSpec typeSpec = builder.build();
        try {
            JavaFile.builder("com.esky.fxlog", typeSpec)
                .build().writeTo(filerUtils);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkParamsValidClass(TypeElement element) throws IllegalArgumentException {
        if (element.getKind() != ElementKind.CLASS) {
            throw new IllegalArgumentException("@Converter just be used class or interface");
        }
        if (!element.getModifiers().contains(PUBLIC)) {
            throw new IllegalArgumentException(String.format("The class %s is not public", Converter.class.getSimpleName()));
        }
        if (element.getModifiers().contains(ABSTRACT)) {
            throw new IllegalArgumentException(String.format("The class %s is abstract. You can't annotate abstract classes with @Converter", element.getSimpleName()));
        }
    }

    private String captureNameUpperCase(String name) {
        if (name == null || name.equals("")) return name;
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }

    private String captureNameLowerCase(String name) {
        if (name == null || name.equals("")) return name;
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return name;
    }
}
