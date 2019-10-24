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
public interface ILogPrint {

    void printLogV(IPrinter iPrinter, ILogConverter iLogConverter, int showLevel, String tag, String message);

    void printLogI(IPrinter iPrinter, ILogConverter iLogConverter, int showLevel, String tag, String message);

    void printLogW(IPrinter iPrinter, ILogConverter iLogConverter, int showLevel, String tag, String message);

    void printLogD(IPrinter iPrinter, ILogConverter iLogConverter, int showLevel, String tag, String message);

    void printLogE(IPrinter iPrinter, ILogConverter iLogConverter, int showLevel, String tag, String message);

}
