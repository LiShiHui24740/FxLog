package com.airland.log;

import android.util.Log;

import com.esky.fxlog.converter.ILogConverter;
import com.esky.fxlog.print.IPrinter;
import com.esky.fxlog_annotation.Printer;

/**
 * @author AirLand
 * @time on 2019-10-24 13:43
 * @email lish_air@163.com
 * @jianshu https://www.jianshu.com/u/816932948905
 * @gitHub https://github.com/LiShiHui24740
 * @describe:
 */
@Printer(name = "CustomPrinter")
public class CustomPrinter implements IPrinter {
    @Override
    public void printLog(ILogConverter iLogConverter, int showLevel, int logLevel, String tag, String message) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" \n***********************************************")
            .append("\n*")
            .append("\t")
            .append(message)
            .append("\t\t")
            .append("\n***********************************************");
        Log.d(tag, stringBuilder.toString());
    }
}
