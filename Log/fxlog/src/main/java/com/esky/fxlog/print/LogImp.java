package com.esky.fxlog.print;


import com.esky.fxlog.converter.ILogConverter;

/**
 * @author AirLand
 * @time on 2019-09-17 21:54
 * @email lish_air@163.com
 * @jianshu https://www.jianshu.com/u/816932948905
 * @gitHub https://github.com/LiShiHui24740
 * @describe:
 */
public class LogImp implements ILogPrint {

    @Override
    public void printLogV(IPrinter iPrinter, ILogConverter iLogConverter, int showLevel, String tag, String message) {
        iPrinter.printLog(iLogConverter, showLevel, IPrinter.LEVEL_VERBOSE, tag, message);
    }

    @Override
    public void printLogI(IPrinter iPrinter, ILogConverter iLogConverter, int showLevel, String tag, String message) {
        iPrinter.printLog(iLogConverter, showLevel, IPrinter.LEVEL_INFO, tag, message);
    }

    @Override
    public void printLogW(IPrinter iPrinter, ILogConverter iLogConverter, int showLevel, String tag, String message) {
        iPrinter.printLog(iLogConverter, showLevel, IPrinter.LEVEL_WARNING, tag, message);
    }

    @Override
    public void printLogD(IPrinter iPrinter, ILogConverter iLogConverter, int showLevel, String tag, String message) {
        iPrinter.printLog(iLogConverter, showLevel, IPrinter.LEVEL_DEBUG, tag, message);
    }

    @Override
    public void printLogE(IPrinter iPrinter, ILogConverter iLogConverter, int showLevel, String tag, String message) {
        iPrinter.printLog(iLogConverter, showLevel, IPrinter.LEVEL_ERROR, tag, message);
    }
}
