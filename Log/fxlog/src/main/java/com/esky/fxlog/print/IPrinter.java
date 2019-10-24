package com.esky.fxlog.print;


import com.esky.fxlog.converter.ILogConverter;

/**
 * @author AirLand
 * @time on 2019-10-22 14:47
 * @email lish_air@163.com
 * @jianshu https://www.jianshu.com/u/816932948905
 * @gitHub https://github.com/LiShiHui24740
 * @describe:
 */
public interface IPrinter {

    char LEFT_TOP_CORNER = '┌';
    char LEFT_BOTTOM_CORNER = '└';
    char MIDDLE_CORNER = '├';
    char VERTICAL_LINE = '│';
    String SOLID_LINE_DIVIDER = "────────────────────────────────────────────────────────";
    String SPACE = "                                                  ";
    int LEVEL_VERBOSE = 0;
    int LEVEL_DEBUG = 1;
    int LEVEL_INFO = 2;
    int LEVEL_WARNING = 3;
    int LEVEL_ERROR = 4;

    int SHOW_LEVEL_1 = 0;
    int SHOW_LEVEL_2 = 1;
    int SHOW_LEVEL_3 = 2;

    void printLog(ILogConverter iLogConverter, int showLevel, int logLevel, String tag, String message);
}
