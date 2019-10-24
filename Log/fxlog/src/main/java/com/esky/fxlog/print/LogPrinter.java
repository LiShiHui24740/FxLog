package com.esky.fxlog.print;

import android.util.Log;

import com.esky.fxlog.config.LogConfig;
import com.esky.fxlog.converter.ILogConverter;


/**
 * @author AirLand
 * @time on 2019-09-17 21:59
 * @email lish_air@163.com
 * @jianshu https://www.jianshu.com/u/816932948905
 * @gitHub https://github.com/LiShiHui24740
 * @describe:
 */
public final class LogPrinter implements IPrinter {


    @Override
    public void printLog(ILogConverter iLogConverter, int showLevel, int logLevel, String tag, String message) {
        if (!LogConfig.getInstance().isDebug())
            return;
        StringBuilder resultstringBuilder = new StringBuilder();
        switch (showLevel) {
            case 0:
                message = iLogConverter == null ? message : iLogConverter.convert(message);
                message = resultstringBuilder.append(" \n")
                    .append(LEFT_TOP_CORNER).append(SOLID_LINE_DIVIDER).append(SOLID_LINE_DIVIDER).append("\n")
                    .append(VERTICAL_LINE).append("    ").append(message).append("\n")
                    .append(LEFT_BOTTOM_CORNER).append(SOLID_LINE_DIVIDER).append(SOLID_LINE_DIVIDER).append("\n").toString();
                break;
            case 1:
                message = iLogConverter == null ? message : iLogConverter.convert(message);
                message = resultstringBuilder.append(" \n")
                    .append(LEFT_TOP_CORNER).append(SOLID_LINE_DIVIDER).append(SOLID_LINE_DIVIDER).append("\n")
                    .append(VERTICAL_LINE).append("      tag  ").append(VERTICAL_LINE).append("\t").append(tag).append("\n")
                    .append(MIDDLE_CORNER).append(SOLID_LINE_DIVIDER).append(SOLID_LINE_DIVIDER).append("\n")
                    .append(VERTICAL_LINE).append(SPACE).append("  loginfo  ").append(SPACE).append("\n")
                    .append(MIDDLE_CORNER).append(SOLID_LINE_DIVIDER).append(SOLID_LINE_DIVIDER).append("\n")
                    .append(VERTICAL_LINE).append("\t").append(message).append("\n")
                    .append(MIDDLE_CORNER).append(SOLID_LINE_DIVIDER).append(SOLID_LINE_DIVIDER).append("\n")
                    .append(VERTICAL_LINE).append("   thread  ").append(VERTICAL_LINE).append("\t").append(Thread.currentThread()).append("\n")
                    .append(LEFT_BOTTOM_CORNER).append(SOLID_LINE_DIVIDER).append(SOLID_LINE_DIVIDER).append("\n").toString();
                break;
            case 2:
                StringBuilder stringBuilder = new StringBuilder();
                StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
                //i==4表示栈中第五个才是对应调用的地方，从0-3分别为：getStackTrace()内部的VMStack.getThreadStackTrace(this)->getStackTrace()->log()->logV()/logE()/logOther()...
                int count = (LogConfig.getInstance().getStackDeep() == -1 ? stackTraceElements.length : 5 + LogConfig.getInstance().getStackDeep());
                for (int i = 5; i < count; i++) {
                    if (i < stackTraceElements.length) {
                        StackTraceElement stackTraceElement = stackTraceElements[i];
                        String stackTraceStr = stackTraceElement.toString();
                        if (i != 5) {
                            stringBuilder.append(VERTICAL_LINE + "\t\t\t" + VERTICAL_LINE + "\t").append(stackTraceStr);
                            if (i != count - 1) {
                                stringBuilder.append("\n");
                            }
                        } else {
                            stringBuilder.append(stackTraceStr).append("\n");
                        }
                    }
                }
                String stackStr = stringBuilder.toString();
                message = iLogConverter == null ? message : iLogConverter.convert(message);
                message = resultstringBuilder.append(" \n")
                    .append(LEFT_TOP_CORNER).append(SOLID_LINE_DIVIDER).append(SOLID_LINE_DIVIDER).append("\n")
                    .append(VERTICAL_LINE).append("      tag  ").append(VERTICAL_LINE).append("\t").append(tag).append("\n")
                    .append(MIDDLE_CORNER).append(SOLID_LINE_DIVIDER).append(SOLID_LINE_DIVIDER).append("\n")
                    .append(VERTICAL_LINE).append(SPACE).append("  loginfo  ").append(SPACE).append("\n")
                    .append(MIDDLE_CORNER).append(SOLID_LINE_DIVIDER).append(SOLID_LINE_DIVIDER).append("\n")
                    .append(VERTICAL_LINE).append("\t").append(message).append("\n")
                    .append(MIDDLE_CORNER).append(SOLID_LINE_DIVIDER).append(SOLID_LINE_DIVIDER).append("\n")
                    .append(VERTICAL_LINE).append("   thread  ").append(VERTICAL_LINE).append("\t").append(Thread.currentThread()).append("\n")
                    .append(MIDDLE_CORNER).append(SOLID_LINE_DIVIDER).append(SOLID_LINE_DIVIDER).append("\n")
                    .append(VERTICAL_LINE).append("    stack  ").append(VERTICAL_LINE).append("\t").append(stackStr).append("\n")
                    .append(LEFT_BOTTOM_CORNER).append(SOLID_LINE_DIVIDER).append(SOLID_LINE_DIVIDER).append("\n").toString();
                break;
        }
        switch (logLevel) {
            case IPrinter.LEVEL_VERBOSE:
                Log.v(tag, message);
                break;
            case IPrinter.LEVEL_INFO:
                Log.i(tag, message);
                break;
            case IPrinter.LEVEL_WARNING:
                Log.w(tag, message);
                break;
            case IPrinter.LEVEL_DEBUG:
                Log.d(tag, message);
                break;
            case IPrinter.LEVEL_ERROR:
                Log.e(tag, message);
                break;
        }
    }
}
