package com.esky.fxlog.util;



/**
 * @author AirLand
 * @time on 2019-09-26 15:58
 * @email lish_air@163.com
 * @jianshu https://www.jianshu.com/u/816932948905
 * @gitHub https://github.com/LiShiHui24740
 * @describe:
 */
public class TransformUtils {

    private static final String SPACE_TAB = "│\t";

    /**
     * @param strJson
     * @return 格式化后的json字符串
     */
    public static String stringToJSON(String strJson) {
        // 计数tab的个数
        int tabNum = 0;
        StringBuffer jsonFormat = new StringBuffer();
        int length = strJson.length();

        char last = 0;
        for (int i = 0; i < length; i++) {
            char c = strJson.charAt(i);
            if (c == '{') {
                tabNum++;
                jsonFormat.append(c + "\n" + SPACE_TAB);
                jsonFormat.append(getSpaceOrTab(tabNum));
            } else if (c == '}') {
                tabNum--;
                jsonFormat.append("\n" + SPACE_TAB);
                jsonFormat.append(getSpaceOrTab(tabNum));
                jsonFormat.append(c);
            } else if (c == ',') {
                jsonFormat.append(c + "\n" + SPACE_TAB);
                jsonFormat.append(getSpaceOrTab(tabNum));
            } else if (c == ':') {
                jsonFormat.append(c + " ");
            } else if (c == '[') {
                tabNum++;
                char next = strJson.charAt(i + 1);
                if (next == ']') {
                    jsonFormat.append(c);
                } else {
                    jsonFormat.append(c + "\n" + SPACE_TAB);
                    jsonFormat.append(getSpaceOrTab(tabNum));
                }
            } else if (c == ']') {
                tabNum--;
                if (last == '[') {
                    jsonFormat.append(c);
                } else {
                    jsonFormat.append("\n" + SPACE_TAB + getSpaceOrTab(tabNum) + c);
                }
            } else {
                jsonFormat.append(c);
            }
            last = c;
        }
        return jsonFormat.toString();
    }

    private static String getSpaceOrTab(int tabNum) {
        StringBuffer sbTab = new StringBuffer();
        for (int i = 0; i < tabNum; i++) {
            sbTab.append('\t');
        }
        return sbTab.toString();
    }

}
