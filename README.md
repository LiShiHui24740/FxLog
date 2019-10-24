# FxLog
#### 简介
##### FxLog是一个android控制台日志打印库，内部封装了表格式的日志展现形式，默认添加了Json数据格式化的转换器和表格式的打印器，用户可以通过Converter注解实现自己的格式化转换器，输出自己想要的日志格式。也可以通过Printer注解实现自己的打印器也就是非默认的表格式。
#### 效果
![github](https://github.com/LiShiHui24740/FxLog/blob/master/Log/img/log1.png)  
![github](https://github.com/LiShiHui24740/FxLog/blob/master/Log/img/log2.png) 
#### 集成
```
dependencies {
    implementation 'com.github.airland:fxlog:1.0.0'
    annotationProcessor 'com.github.airland:fxlog-compiler:1.0.0'
}
```
#### 使用
```
  #是否开启debug模式，设置为true才会打印日志
  FxLog.init(true);
  #日志分为d,w,e,i,v五个级别，其中方法名中Thread代表打印线程信息，Stack代表打印方法调用栈信息
  FxLog.d("AirLand", "简单打印日志");
  FxLog.wThread("AirLand", "打印线程信息");
  FxLog.vJson("AirLand", "{\"formtArrs\":[],\"info\":{\"appId\":\"10141c160b424dd186d75e2aa72d43e2\",\"roomToken\":\"00610141c160b424dd186d75e2aa72d43e2IAC8LzccZZcy5rwgigsGxl\",\"roomid\":1084726,\"sid\":1084725},\"msg\":\"success\",\"result\":1000,\"suc\":true,\"useTemplate\":true}");
  FxLog.eStackJson("AirLand", "{\"formtArrs\":[],\"info\":{\"appId\":\"10141c160b424dd186d75e2aa72d43e2\",\"roomToken\":\"00610141c160b424dd186d75e2aa72d43e2IAC8LzccZZcy5rwgigsGxl\",\"roomid\":1084726,\"sid\":1084725},\"msg\":\"success\",\"result\":1000,\"suc\":true,\"useTemplate\":true}");
 ```
#### 高级用法
1.自定义转换器
相对日志进行格式化处理，比如json，xml等格式的日志，库中目前默认封装的有json格式转换器，要实现自己的转换器如下：
```
#实现ILogConverter接口同时，添加@Converter注解，name是该转换器的名字，然后rebuild一下
#FxLog类中会自动生成对应的方法： FxLog.iXml("AirLand", "自定义XmlConverter，这里只是一个例子没有实现xml格式化");
@Converter(name = "Xml")
public class XmlConverter implements ILogConverter {
    @Override
    public String convert(@Nullable String log) {
        return "AirLand-"+log;
    }
}
```
