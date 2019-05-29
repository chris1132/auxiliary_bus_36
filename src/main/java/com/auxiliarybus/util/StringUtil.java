package com.auxiliarybus.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangchaohui on 2019/1/3
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String str) {
        return !StringUtil.isEmpty(str);
    }

    public static String trim(String str) {
        if (str == null)
            return null;
        else
            return str.trim();
    }


    /**
     * 给定特定的字符串，把字符串中的特殊字符转义
     *
     * @param str
     * @return
     */
    public static String htmlspecialchars(String str) {
        if (str == null) {
            return null;
        }
        str = str.replaceAll("&", "&amp;").replaceAll(">", "&gt;").replaceAll("<", "&lt;").replaceAll("'", "&apos;").replaceAll("\"", "&quot;");
        return str;
    }

    /**
     * 给定特定的字符串，把一些预定义的 HTML 实体转换为字符
     *
     * @param str
     * @return
     */
    public static String htmlspecialchars_decode(String str) {
        //先转义
        str = str.replaceAll("&nbsp;", " ")
                .replaceAll("&amp;", "&")
                .replaceAll("&gt;", ">")
                .replaceAll("&lt;", "<")
                .replaceAll("&apos;", "'")
                .replaceAll("&quot;", "\"")
                .replaceAll("&lsquo;", "‘")
                .replaceAll("&rsquo;", "’")
                .replaceAll("&sbquo;", "，")
                .replaceAll("&ldquo;", "“")
                .replaceAll("&rdquo;", "”")
                .replaceAll("&mdash;", "—")
                .replaceAll("&hellip;", "…")
                .replaceAll("&middot;", "·");
        //正则表达式替换 ^&[A-Za-z]*;$
        //str = str.replaceAll("^\u2018?\u2019?\uff0c?\u201c?\u201d?(&[A-Za-z]*;)\u2018?\u2019?\uff0c?\u201c?\u201d?$", "");
        String result = str;
        String regEx = "(&[A-Za-z]*;)";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(str);
        while (mat.find()) {
            String scriptStr = mat.group(0);// 获取脚本
            result = result.replace(scriptStr, " ");// 替换
        }
        return result;
    }

    /**
     * 给定特定的字符串转义除去&
     *
     * @param str
     * @return
     */
    public static String htmlspecialcharsForShare(String str) {
        if (str == null) {
            return null;
        }
        str = str.replaceAll(">", "&gt;").replaceAll("<", "&lt;").replaceAll("'", "&apos;").replaceAll("\"", "&quot;");
        return str;
    }

    /**
     * 去除javascript脚本
     *
     * @param str
     * @return
     */
    public static String htmlspecialcharsScript(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        String regEx = "<[\\s]*?[Ss][Cc][Rr][Ii][Pp][Tt][^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?[Ss][Cc][Rr][Ii][Pp][Tt][\\s]*?>";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(str);
        if (mat.find()) {
            String scriptStr = mat.group(0);// 获取脚本
            String hasScriptStr = StringUtil.htmlspecialchars(scriptStr);// 获取过滤后的脚本
            str = str.replace(scriptStr, hasScriptStr);// 替换
        }
        return str;
    }

    /**
     * 校验是否是javascript脚本
     */
    public static boolean isScript(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        String regEx = "<[\\s]*?[Ss][Cc][Rr][Ii][Pp][Tt][^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?[Ss][Cc][Rr][Ii][Pp][Tt][\\s]*?>";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return true;
        }
        return false;
    }


    /**
     * 获得字符串的字符个数，双字节如：汉字，全角符号等算两个字符，单字节的英文字母标点算一个字符
     *
     * @param str String
     * @return Int 字符个数
     */

    public static int getCharLength(String str) {
        try {
            return str.getBytes("gbk").length;
        } catch (UnsupportedEncodingException e) {
            return 0;
        }
    }

    public static String getFileType(String fileName) {
        if (fileName.endsWith(".jpg")) {
            return "jpg";
        } else if (fileName.endsWith(".jpeg")) {
            return "jpeg";
        } else if (fileName.endsWith(".gif")) {
            return "gif";
        } else if (fileName.endsWith(".png")) {
            return "png";
        }
        return "";
    }

    /**
     * 截取一定长度的字符串，可附加省略符
     *
     * @param str   String
     * @param len   Int 要截取的长度
     * @param elide String 省略符
     * @return String 截取后的字串
     */
    public static String subString(String str, int len, String elide) {
        if (str.length() > len)
            return str.substring(0, len) + elide;
        else
            return str;
    }

    public static String subStr(String str, int toCount) {
        int reInt = 0;
        StringBuffer reStr = new StringBuffer();
        if (str == null) {
            return "";
        }
        char[] tempChar = str.toCharArray();
        for (int kk = 0; (kk < tempChar.length && toCount > reInt); kk++) {
            String s1 = String.valueOf(tempChar[kk]);
            byte[] b = s1.getBytes();
            reInt += b.length;
            if (toCount < reInt) {
                break;
            }
            reStr.append(tempChar[kk]);
        }

        return reStr.toString();
    }

    public static String subStr(String str, int toCount, String elide) {
        int reInt = 0;
        StringBuffer reStr = new StringBuffer();
        if (str == null) {
            return "";
        }
        char[] tempChar = str.toCharArray();
        for (int kk = 0; (kk < tempChar.length && toCount > reInt); kk++) {
            String s1 = String.valueOf(tempChar[kk]);
            byte[] b = s1.getBytes();
            reInt += b.length;
            if (toCount < reInt) {
                break;
            }
            reStr.append(tempChar[kk]);
            if (toCount == reInt) reStr.append(elide);
        }

        return reStr.toString();
    }

    /**
     * 剔除html代码
     *
     * @param result
     * @return
     */
    public static String escapeHtml(String result) {
        return result.replaceAll("<[^>]*>", "").replaceAll("&nbsp;", " ");
    }

    public static String escapeSpecial(String str) {
        String temp = str;
        temp = temp.replace("<", ""); // 过滤 "< "
        temp = temp.replace(">", ""); // 过滤 "> "
        temp = temp.replaceAll("&", "").replaceAll(">", "").replaceAll("<", "").replaceAll("=", "").replaceAll("'", "")
                .replaceAll("\"", "").replaceAll("%", "").replaceAll("\\(", "").replaceAll("\\)", "");

        return temp;
    }

    /**
     * 过滤除了数字和逗号所有字符
     *
     * @param content
     * @return
     */
    public static String filterSqlContentExt(String content) {
        if (content == null || content.length() == 0) return "";
        String flt = "[^(0-9)|,]";
        content = content.replaceAll(flt, "");
        return content;
    }

    /**
     * 截取知道长度的字符串
     *
     * @param str
     * @param num
     * @return
     */
    public static String getSubString(String str, int num) {
        String result = "";
        if (str != null && str.length() >= 2) {
            result = str.substring(2);
        } else if (str != null && str.length() > 0) {
            result = str;
        }
        return result;
    }

    /**
     * 根据字符截取一定长度的中英文混合字符串，可附加省略符
     *
     * @param str
     * @param j
     * @param elide
     * @return
     * @return:String
     */
    public static String subCharString(String str, int j, String elide) {
        if (isEmpty(str))
            return "";
        byte[] buf = null;
        String s = null;
        try {
            buf = str.getBytes("GBK");
            int strLen = buf.length;
            if (j >= strLen || j < 1) {
                return str;
            }
            int count = 0;
            int i = 0;
            for (i = j - 1; i >= 0; i--) {
                if (buf[i] < 0)
                    count++;
                else
                    break;
            }
            // 因為GBK用兩個字節表示一個漢字
            if (count % 2 == 0)
                s = new String(buf, 0, j, "GBK");
            else
                s = new String(buf, 0, j - 1, "GBK");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        return s + elide.trim();
    }

    public static String cutByteByGBK(String str, int j, String elide) {
        if (isEmpty(str))
            return "";
        byte[] buf = null;
        String s = null;
        try {
            buf = str.getBytes("GBK");
            int strLen = buf.length;
            if (j >= strLen || j < 1) {
                return str;
            }
            int count = 0;
            int i = 0;
            for (i = j - 1; i >= 0; i--) {
                if (buf[i] < 0)
                    count++;
                else
                    break;
            }
            // 因為GBK用兩個字節表示一個漢字
            if (count % 2 == 0)
                s = new String(buf, 0, j, "GBK");
            else
                s = new String(buf, 0, j - 1, "GBK");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        return s + elide.trim();
    }


    /**
     * 字符串隔开显示
     *
     * @param str
     * @param cutNum 隔开个数
     * @param cutStr 隔开字符串
     * @return String
     */
    public static String cutString(String str, int cutNum, String cutStr) {
        String newStr = "";
        int length = str.length();
        int count = 0;
        if (length % cutNum == 0) {
            count = length / cutNum;
        } else {
            count = length / cutNum + 1;
        }
        for (int i = 0; i < count; i++) {
            int j = i * cutNum;
            int cutL = j + cutNum;
            if (cutL > length)
                cutL = length;
            newStr += str.substring(j, cutL) + cutStr;
        }
        return newStr.substring(0, newStr.length() - 1);
    }

    public static String cleanHtmlspecialchars(String str) {
        return str.replaceAll("&amp;", "").replaceAll("&gt;", "").replaceAll("&lt;", "")
                .replaceAll("&apos;", "").replaceAll("&quot;", "").replaceAll("&rdquo;", "").replaceAll("&mdash;", "")
                .replaceAll("&hellip;", "").replaceAll("&ldquo;", "").replaceAll("&uml;", "").replaceAll("&middot;", "")
                .replaceAll("&rsquo;", "").replaceAll("&lsquo;", "").replaceAll("&nbsp;", "");
    }

    //过滤html中http:、https:
    public static String cleanHtmlHttp(String str) {
        return str.replaceAll("(\"|')https?:(\\/\\/[0-9a-z]+\\.(19lou|citysbs|appcenter|qa-53)\\.com)", "$1$2");
    }

}
