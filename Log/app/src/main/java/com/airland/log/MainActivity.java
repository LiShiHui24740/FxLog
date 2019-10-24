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
        FxLog.dCustomPrinter("lishihui","自定义Printer");
        FxLog.d("lishihui", "kdqkdqkdqkdqd2");
        FxLog.dThread("lishihui", "打印线程信息");
        FxLog.dXml("lishihui", "自定义XmlConverter");
        FxLog.dJson("lishihui", "{\"formtArrs\":[],\"info\":{\"appId\":\"10141c160b424dd186d75e2aa72d43e2\",\"audioPushRtmpUrl\":\"rtmp://uc-publish-sec.midaochat.net/midao_live_sec/1084725_9_1001927\",\"pushRtmpUrl\":\"rtmp://uc-publish-sec.midaochat.net/midao_live_sec/1084727\",\"roomToken\":\"00610141c160b424dd186d75e2aa72d43e2IAC8LzccZZcy5rwgigsGxl\",\"roomid\":1084726,\"sid\":1084725},\"msg\":\"success\",\"result\":1000,\"suc\":true,\"useTemplate\":true}");
        FxLog.dStackJson("lishihui", "{\"formtArrs\":[],\"info\":{\"appId\":\"10141c160b424dd186d75e2aa72d43e2\",\"audioPushRtmpUrl\":\"rtmp://uc-publish-sec.midaochat.net/midao_live_sec/1084725_9_1001927\",\"pushRtmpUrl\":\"rtmp://uc-publish-sec.midaochat.net/midao_live_sec/1084727\",\"roomToken\":\"00610141c160b424dd186d75e2aa72d43e2IAC8LzccZZcy5rwgigsGxl\",\"roomid\":1084726,\"sid\":1084725},\"msg\":\"success\",\"result\":1000,\"suc\":true,\"useTemplate\":true}");
    }
}
