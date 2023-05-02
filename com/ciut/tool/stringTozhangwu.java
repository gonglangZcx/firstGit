package com.ciut.tool;

import com.ciut.domain.zhangwu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class stringTozhangwu {
    public static zhangwu change(String s) {
        zhangwu zhangWu = new zhangwu();
        // 使用正则表达式解析字符串
        Matcher matcher = Pattern.compile("\\[([^\\]]+)\\]").matcher(s);
        if (matcher.find()) {
            String[] data = matcher.group(1).split(", ");
            for (String item : data) {
                String[] temp = item.split("=");
                switch (temp[0]) {
                    case "zwid":
                        zhangWu.setZwid(Integer.parseInt(temp[1]));
                        break;
                    case "flname":
                        zhangWu.setFlname(temp[1]);
                        break;
                    case "money":
                        zhangWu.setMoney(Double.parseDouble(temp[1]));
                        break;
                    case "zhanghu":
                        zhangWu.setZhanghu(temp[1]);
                        break;
                    case "createtime":
                        zhangWu.setCreatetime(temp[1]);
                        break;
                    case "description":
                        zhangWu.setDescription(temp[1]);
                        break;
                    case "username":
                        zhangWu.setUsername(temp[1]);
                        break;
                }
            }
        }
        return zhangWu;
    }


}
