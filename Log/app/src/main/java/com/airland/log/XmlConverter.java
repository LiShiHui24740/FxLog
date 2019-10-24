package com.airland.log;


import androidx.annotation.Nullable;

import com.esky.fxlog.converter.ILogConverter;
import com.esky.fxlog_annotation.Converter;

/**
 * @author AirLand
 * @time on 2019-10-21 14:54
 * @email lish_air@163.com
 * @jianshu https://www.jianshu.com/u/816932948905
 * @gitHub https://github.com/LiShiHui24740
 * @describe:
 */
@Converter(name = "Xml")
public class XmlConverter implements ILogConverter {
    @Override
    public String convert(@Nullable String log) {
        return "AirLand-"+log;
    }
}
