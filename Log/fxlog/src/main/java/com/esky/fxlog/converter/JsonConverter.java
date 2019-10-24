package com.esky.fxlog.converter;



import com.esky.fxlog.util.TransformUtils;

/**
 * @author AirLand
 * @time on 2019-09-26 17:17
 * @email lish_air@163.com
 * @jianshu https://www.jianshu.com/u/816932948905
 * @gitHub https://github.com/LiShiHui24740
 * @describe:
 */
public class JsonConverter implements ILogConverter {

    @Override
    public String convert(String log) {
        return TransformUtils.stringToJSON(log);
    }
}
