package com.lishuai.edittextdoublecreate;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    private final static Pattern URL = Pattern.compile("^(https?|ftp|http)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");

    /**
     * 判断是否为一个合法的url地址
     *
     * @param str
     * @return
     */
    public static boolean isUrl(String str) {
        if (str == null || str.trim().length() == 0)
            return false;
        return URL.matcher(str).matches();
    }

    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     */
    public static boolean isEmpty(String value) {
        if (value != null && !"".equalsIgnoreCase(value.trim())
                && !"null".equalsIgnoreCase(value.trim())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     */
    public static boolean isBland(CharSequence input) {
        if (input == null || "".equals(input) || "null".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 处理空字符串
     *
     * @param str
     * @return String
     */
    public static String doEmpty(String str) {
        return doEmpty(str, "");
    }

    /**
     * 处理空字符串
     *
     * @param str
     * @param defaultValue
     * @return String
     */
    public static String doEmpty(String str, String defaultValue) {
        if (str == null || str.equalsIgnoreCase("null")
                || str.trim().equals("") || str.trim().equals("－请选择－")) {
            str = defaultValue;
        } else if (str.startsWith("null")) {
            str = str.substring(4, str.length());
        }
        return str.trim();
    }

    /**
     * 请选择
     */
    final static String PLEASE_SELECT = "请选择...";

    public static boolean notEmpty(Object o) {
        return o != null && !"".equals(o.toString().trim())
                && !"null".equalsIgnoreCase(o.toString().trim())
                && !"undefined".equalsIgnoreCase(o.toString().trim())
                && !PLEASE_SELECT.equals(o.toString().trim());
    }

    public static boolean empty(Object o) {
        return o == null || "".equals(o.toString().trim())
                || "null".equalsIgnoreCase(o.toString().trim())
                || "undefined".equalsIgnoreCase(o.toString().trim())
                || PLEASE_SELECT.equals(o.toString().trim());
    }

    public static boolean num(Object o) {
        int n = 0;
        try {
            n = Integer.parseInt(o.toString().trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (n > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean decimal(Object o) {
        double n = 0;
        try {
            n = Double.parseDouble(o.toString().trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (n > 0.0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 给JID返回用户名
     *
     * @param Jid
     * @return
     */
    public static String getUserNameByJid(String Jid) {
        if (empty(Jid)) {
            return null;
        }
        if (!Jid.contains("@")) {
            return Jid;
        }
        return Jid.split("@")[0];
    }

    /**
     * 给用户名返回JID
     *
     * @param jidFor   域名//如ahic.com.cn
     * @param userName
     * @return
     */
    public static String getJidByName(String userName, String jidFor) {
        if (empty(jidFor) || empty(jidFor)) {
            return null;
        }
        return userName + "@" + jidFor;
    }

    /**
     * 给用户名返回JID,使用默认域名ahic.com.cn
     *
     * @param userName
     * @return
     */
    public static String getJidByName(String userName) {
        String jidFor = "swinner.com";
        return getJidByName(userName, jidFor);
    }

    /**
     * 根据给定的时间字符串，返回月 日 时 分 秒
     *
     * @param allDate like "yyyy-MM-dd hh:mm:ss SSS"
     * @return
     */
    public static String getMonthTomTime(String allDate) {
        return allDate.substring(5, 19);
    }

    /**
     * 根据给定的时间字符串，返回月 日 时 分 月到分钟
     *
     * @param allDate like "yyyy-MM-dd hh:mm:ss SSS"
     * @return
     */
    public static String getMonthTime(String allDate) {
        return allDate.substring(5, 16);
    }

    /**
     * 根据给定的时间字符串，返回年 月 日 时 分 到分钟
     *
     * @param allDate like "yyyy-MM-dd hh:mm:ss SSS"
     * @return
     */
    public static String getYearTime(String allDate) {
        return allDate.substring(0, 16);
    }

    /**
     * 根据给定的时间字符串，返回年 月 日
     *
     * @param allDate like "yyyy-MM-dd hh:mm:ss SSS"
     * @return
     */
    public static String getDayTime(String allDate) {
        return allDate.substring(0, 10);
    }

    private static Calendar mCalendar = Calendar.getInstance();

    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(mCalendar.getTime());
    }

    /**
     * 获取当前年份
     *
     * @return 年
     */
    public static int getCurrentYear() {
        return mCalendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     *
     * @return 月
     */
    public static int getCurrentMonth() {
        return mCalendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日
     *
     * @return 日
     */
    public static int getCurrentDay() {
        return mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前时间时
     *
     * @return 小时
     */
    public static int getCurentHour() {
        return mCalendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取当前时间分
     *
     * @return 分
     */
    public static int getCurrentMinute() {
        return mCalendar.get(Calendar.MINUTE);
    }

    /**
     * 将一个字符串转化为输入流
     */

    public static InputStream getStringStream(String sInputString) {

        if (sInputString != null && !sInputString.trim().equals("")) {

            try {

                ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(
                        sInputString.getBytes());

                return tInputStringStream;

            } catch (Exception ex) {

                ex.printStackTrace();

            }

        }

        return null;

    }

    /**
     * 将一个输入流转化为字符串
     */

    public static String getStreamString(InputStream tInputStream) {

        if (tInputStream != null) {

            try {

                BufferedReader tBufferedReader = new BufferedReader(
                        new InputStreamReader(tInputStream));

                StringBuffer tStringBuffer = new StringBuffer();

                String sTempOneLine = new String("");

                while ((sTempOneLine = tBufferedReader.readLine()) != null) {

                    tStringBuffer.append(sTempOneLine);

                }

                return tStringBuffer.toString();

            } catch (Exception ex) {

                ex.printStackTrace();

            }

        }

        return null;

    }

    /**
     * unicode转码
     *
     * @param theString
     * @return
     */

    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    /***
     * @param input : 银行卡号,例如"6225880137706868"
     * @return
     */
    public static String formBankCard(String input) {
        // String result = input.replaceAll("([\\d]{4})(?=\\d)", "$1 ");
        String temp = "";
        int len = input.length();
        switch (len) {
            case 16:
                temp = input.substring(0, 4).concat("********")
                        .concat(input.substring(12));
                break;
            case 17:
            case 18:
            case 19:
                temp = input.substring(0, 4).concat("************")
                        .concat(input.substring(16));
                break;
            default:
                String moddle = "";
                for (int i = 0, j = len - 8; i < j; i++) {
                    moddle += "*";
                }
                temp = input.substring(0, 4).concat(moddle)
                        .concat(input.substring(len - 4));
                break;
        }
        String result = temp.replaceAll("([\\d]|[*]){4}(?!$)", "$0 ");
        return result;
    }

    /***
     * @param input : 银行卡号,例如"6225880137706868" : 银行卡号,例如"***********06868"
     * @return
     */
    public static String formBankCardBefore(String input) {
        // String result = input.replaceAll("([\\d]{4})(?=\\d)", "$1 ");
        String temp = "";
        int len = input.length();
        switch (len) {
            case 16:
                temp = input.substring(0, 0).concat("************")
                        .concat(input.substring(12));
                break;
            case 17:
            case 18:
            case 19:
                temp = input.substring(0, 0).concat("****************")
                        .concat(input.substring(15));
                break;
            default:
                String moddle = "";
                for (int i = 0, j = len - 8; i < j; i++) {
                    moddle += "*";
                }
                temp = input.substring(0, 4).concat(moddle)
                        .concat(input.substring(len - 4));
                break;
        }
        String result = temp.replaceAll("([\\d]|[*]){4}(?!$)", "$0 ");
        return result;
    }

    /**
     * 判断是否是闰年
     *
     * @param year 年
     * @return
     */
    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    }

    /**
     * 获取时间串中的月日
     *
     * @param time 时间
     * @return
     */
    public static String getMonthDay(String time) {
        if (isBland(time)) {
            return "";
        }
        String temp = "";
        if (time.length() >= 10) {
            temp = time.substring(5, 10);
        } else if (time.length() >= 5) {
            temp = time.substring(5);
        } else
            temp = time;

        return temp;
    }

    public static final String format = "yyyy-MM-dd HH:mm:ss";

    public static int getMonth(String time) {
        if (isBland(time)) {
            return 0;
        }

        Date date;

        try {
            date = new SimpleDateFormat(format, Locale.getDefault()).parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

        if (date == null)
            return 0;

        int month = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        month = calendar.get(Calendar.MONTH) + 1;
        return month;
    }

    public static String getTime(String time) {
        if (isBland(time)) {
            return "";
        }

        String temp = "";

        if (time.length() >= 10) {
            temp = time.substring(0, 10);
        } else {
            temp = time;
        }

        return temp;
    }

    /**
     * 截取  版本更新信息
     *
     * @param intro
     * @return
     */
    public static String[] getIntro(String intro) {
        return intro.split("_");
    }


    // 根据Unicode编码完美的判断中文汉字和符号
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    // 完整的判断中文汉字和符号
    public static boolean isChinese(String strName) {
        if (isEmpty(strName))
            return false;

        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    public static String hiddenPhone(String phone) {
        if (isBland(phone)) {
            return "";
        }
        if (isMobile(phone)) {
            return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        } else
            return phone;
    }

    public static boolean isMobile(String mobiles) {
        if (isBland(mobiles))
            return false;

        Pattern p = Pattern.compile("^((13[0-9])|(14[5-9])|(15[^4,\\D])|(17[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
