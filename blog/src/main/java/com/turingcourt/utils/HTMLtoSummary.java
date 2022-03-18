package com.turingcourt.utils;


/**
 * @author Cyanogen
 * @description html文本提取
 * @date 2022/3/18 16:37
 */
public class HTMLtoSummary {
    private static final int SIZE = 256;

    public static String buildArticleTabloid(String htmlArticleComment) {

        String regex = "\\s+";
        String str = htmlArticleComment.trim();
        //去掉所有空格
        String articleTabloid = str.replaceAll(regex, "");

        int beginIndex = articleTabloid.indexOf("<");
        int endIndex = articleTabloid.indexOf(">");
        StringBuilder myArticleTabloid = new StringBuilder();
        String nowStr;

        while (beginIndex != -1) {
            nowStr = articleTabloid.substring(0, beginIndex);
            if (nowStr.length() > SIZE) {
                nowStr = nowStr.substring(0, SIZE);
                myArticleTabloid.append(nowStr);
            } else {
                myArticleTabloid.append(nowStr);
            }

            articleTabloid = articleTabloid.substring(endIndex + 1);
            beginIndex = articleTabloid.indexOf("<");
            if (myArticleTabloid.length() < SIZE) {
                //过滤掉<pre>标签中的代码块
                if (articleTabloid.length() > 4) {
                    if (articleTabloid.charAt(beginIndex) == '<'
                            && articleTabloid.charAt(beginIndex + 1) == 'p'
                            && articleTabloid.charAt(beginIndex + 2) == 'r'
                            && articleTabloid.charAt(beginIndex + 3) == 'e') {
                        endIndex = articleTabloid.indexOf("</pre>");
                        endIndex = endIndex + 5;
                    } else {
                        endIndex = articleTabloid.indexOf(">");
                    }
                } else {
                    endIndex = articleTabloid.indexOf(">");
                }
            } else {
                break;
            }

        }

        if (myArticleTabloid.length() > SIZE) {
            myArticleTabloid = new StringBuilder(myArticleTabloid.substring(0, SIZE));
        }

        return myArticleTabloid.toString();
    }
}
