package com.farm.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: 正则工具类
 * @author: xyc
 * @create: 2019-10-27 16:30
 */
public class PatternUtil {

    /**
     * 根据正则截取
     *
     * @param regex
     * @return
     */
    public static String text(String input, String regex) {
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(input);
        String result = "";
        if (matcher.find()) {
            result = matcher.group(1);
        }

        return result;
    }

    public static void main(String args[]) {
        String input = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?sort=symbol&asc=1&node=gn_%s&symbol=&_s_r_a=init";
        String regex = "node=gn_(.+?)&";
        System.out.println(text(input, regex));
    }
}
