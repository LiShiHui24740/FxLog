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
