package com.airland.log;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.esky.fxlog.FxLog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FxLog.init(true);
        FxLog.d("AirLand", "简单打印日志");
        FxLog.wThread("AirLand", "打印线程信息");
        FxLog.iXml("AirLand", "自定义XmlConverter，这里只是一个例子没有实现xml格式化");
        FxLog.vJson("AirLand", "{\"formtArrs\":[],\"info\":{\"appId\":\"10141c160b424dd186d75e2aa72d43e2\",\"roomToken\":\"00610141c160b424dd186d75e2aa72d43e2IAC8LzccZZcy5rwgigsGxl\",\"roomid\":1084726,\"sid\":1084725},\"msg\":\"success\",\"result\":1000,\"suc\":true,\"useTemplate\":true}");
        FxLog.eStackJson("AirLand", "{\"formtArrs\":[],\"info\":{\"appId\":\"10141c160b424dd186d75e2aa72d43e2\",\"roomToken\":\"00610141c160b424dd186d75e2aa72d43e2IAC8LzccZZcy5rwgigsGxl\",\"roomid\":1084726,\"sid\":1084725},\"msg\":\"success\",\"result\":1000,\"suc\":true,\"useTemplate\":true}");
        FxLog.dCustomPrinter("AirLand","自定义Printer");
    }
}
