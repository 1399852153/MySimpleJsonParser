package com.xiongyx.my.simple.json.util;

import java.util.HashSet;
import java.util.Set;

public class MyJsonUnicodeUtils {

    private static final Set<String> specialChars = new HashSet<>();

    static {
        // 特殊转义字符
        specialChars.add("0008");
        specialChars.add("0009");
        specialChars.add("000A");
        specialChars.add("000C");
        specialChars.add("000D");
        specialChars.add("0022");
        specialChars.add("005C");

    }

    public static String unicodeToString(String input) {
        if (input == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i < input.length()) {
            if (input.startsWith("\\u", i)) {
                try {
                    // 解析4位十六进制Unicode码点
                    String hex = input.substring(i + 2, i + 6);

                    if(specialChars.contains(hex)) {
                        // 特殊控制字符，原封不动
                        builder.append("\\u").append(hex);
                    }else{
                        int codePoint = Integer.parseInt(hex, 16);
                        builder.append((char) codePoint);
                    }

                    i += 6;
                } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                    // 遇到无效格式保持原样
                    builder.append(input.charAt(i));
                    i++;
                }
            } else {
                builder.append(input.charAt(i));
                i++;
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String input = "垂直制表符\\u000B 换页符\\u000C";
        String expected = "A中文😀";

        System.out.println(unicodeToString(input));
    }
}
