package com.esky.fxlog.config;

/**
 * @author AirLand
 * @time on 2019-10-22 15:08
 * @email lish_air@163.com
 * @jianshu https://www.jianshu.com/u/816932948905
 * @gitHub https://github.com/LiShiHui24740
 * @describe:
 */
public class LogConfig {
    private static volatile LogConfig logConfig;
    private boolean isDebug;
    private int stackDeep = 3;

    public static LogConfig getInstance() {
        if (logConfig == null) {
            synchronized (LogConfig.class) {
                if (logConfig == null) {
                    logConfig = new LogConfig();
                }
            }
        }
        return logConfig;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public int getStackDeep() {
        return stackDeep;
    }

    public void setStackDeep(int stackDeep) {
        this.stackDeep = stackDeep;
    }
}
