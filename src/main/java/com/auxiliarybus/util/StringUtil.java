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
     * �����ض����ַ��������ַ����е������ַ�ת��
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
     * �����ض����ַ�������һЩԤ����� HTML ʵ��ת��Ϊ�ַ�
     *
     * @param str
     * @return
     */
    public static String htmlspecialchars_decode(String str) {
        //��ת��
        str = str.replaceAll("&nbsp;", " ")
                .replaceAll("&amp;", "&")
                .replaceAll("&gt;", ">")
                .replaceAll("&lt;", "<")
                .replaceAll("&apos;", "'")
                .replaceAll("&quot;", "\"")
                .replaceAll("&lsquo;", "��")
                .replaceAll("&rsquo;", "��")
                .replaceAll("&sbquo;", "��")
                .replaceAll("&ldquo;", "��")
                .replaceAll("&rdquo;", "��")
                .replaceAll("&mdash;", "��")
                .replaceAll("&hellip;", "��")
                .replaceAll("&middot;", "��");
        //������ʽ�滻 ^&[A-Za-z]*;$
        //str = str.replaceAll("^\u2018?\u2019?\uff0c?\u201c?\u201d?(&[A-Za-z]*;)\u2018?\u2019?\uff0c?\u201c?\u201d?$", "");
        String result = str;
        String regEx = "(&[A-Za-z]*;)";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(str);
        while (mat.find()) {
            String scriptStr = mat.group(0);// ��ȡ�ű�
            result = result.replace(scriptStr, " ");// �滻
        }
        return result;
    }

    /**
     * �����ض����ַ���ת���ȥ&
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
     * ȥ��javascript�ű�
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
            String scriptStr = mat.group(0);// ��ȡ�ű�
            String hasScriptStr = StringUtil.htmlspecialchars(scriptStr);// ��ȡ���˺�Ľű�
            str = str.replace(scriptStr, hasScriptStr);// �滻
        }
        return str;
    }

    /**
     * У���Ƿ���javascript�ű�
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
     * ����ַ������ַ�������˫�ֽ��磺���֣�ȫ�Ƿ��ŵ��������ַ������ֽڵ�Ӣ����ĸ�����һ���ַ�
     *
     * @param str String
     * @return Int �ַ�����
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
     * ��ȡһ�����ȵ��ַ������ɸ���ʡ�Է�
     *
     * @param str   String
     * @param len   Int Ҫ��ȡ�ĳ���
     * @param elide String ʡ�Է�
     * @return String ��ȡ����ִ�
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
     * �޳�html����
     *
     * @param result
     * @return
     */
    public static String escapeHtml(String result) {
        return result.replaceAll("<[^>]*>", "").replaceAll("&nbsp;", " ");
    }

    public static String escapeSpecial(String str) {
        String temp = str;
        temp = temp.replace("<", ""); // ���� "< "
        temp = temp.replace(">", ""); // ���� "> "
        temp = temp.replaceAll("&", "").replaceAll(">", "").replaceAll("<", "").replaceAll("=", "").replaceAll("'", "")
                .replaceAll("\"", "").replaceAll("%", "").replaceAll("\\(", "").replaceAll("\\)", "");

        return temp;
    }

    /**
     * ���˳������ֺͶ��������ַ�
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
     * ��ȡ֪�����ȵ��ַ���
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
     * �����ַ���ȡһ�����ȵ���Ӣ�Ļ���ַ������ɸ���ʡ�Է�
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
            // ���GBK�Ãɂ��ֹ���ʾһ���h��
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
            // ���GBK�Ãɂ��ֹ���ʾһ���h��
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
     * �ַ���������ʾ
     *
     * @param str
     * @param cutNum ��������
     * @param cutStr �����ַ���
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

    //����html��http:��https:
    public static String cleanHtmlHttp(String str) {
        return str.replaceAll("(\"|')https?:(\\/\\/[0-9a-z]+\\.(19lou|citysbs|appcenter|qa-53)\\.com)", "$1$2");
    }

}
