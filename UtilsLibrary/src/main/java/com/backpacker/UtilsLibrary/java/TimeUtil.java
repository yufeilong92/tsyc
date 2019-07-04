package com.backpacker.UtilsLibrary.java;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: kotlin_androidone
 * @Package com.backpacker.UtilsLibrary
 * @Description: $todo
 * @author: L-BackPacker
 * @date: 2019/3/31 21:30
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class TimeUtil {
    /**
     * 获取当前时间戳
     *
     * @param date 当时时间
     * @return
     */
    public static String getStringTimeStamp(String date) {
        if (StringUtil.isEmpty(date)) {
            return "";
        }
        String commonDateStr = TimeUtil.getCommonDateStr(date);
        long nowTime = TimeUtil.intervalNow(commonDateStr);
        String result = "";
        long ms = nowTime / 1000;
        BigDecimal decimal = new BigDecimal(ms).setScale(0, BigDecimal.ROUND_HALF_UP);
        //秒数
        long longNow = decimal.longValue();
        long temp = 0;
        if (longNow < 60) {
            result = "刚刚";
        } else if ((temp = longNow / 60) < 60) {
            result = temp + "分前";
        } else if ((temp = temp / 60) < 24) {
            result = temp + "小时前";
        } else if ((temp = temp / 24) < 30) {
            result = temp + "天前";
        } else if ((temp = temp / 30) < 12) {
            result = temp + "月前";
        } else {
            temp = temp / 12;
            result = temp + "年前";
        }
        return result;
    }

    /**
     * 返回yyyy-MM-DD hh:mm:ss
     *
     * @param datestr 处理：2015-12-22 08:49:21.0
     * @return
     */
    public static String getCommonDateStr(String datestr) {
        if (StringUtil.isEmpty(datestr) || datestr.length() <= 19)
            return datestr;
        String tmpStr = datestr.substring(0, 19);
        Date date = strToDate(tmpStr, null);
        if (date == null)
            return tmpStr;
        return getChatTime(date.getTime());
    }

    public static String getChatTime(long time) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);


    }

    /**
     * 字符串转日期
     *
     * @param str 字符串
     * @param def 默认时间，如果转换失败则返回默认时间
     */
    public static Date strToDate(String str, Date def) {
        return strToDate(str, "yyyy-MM-dd HH:mm:ss", def);
    }

    /**
     * 字符串转日期
     *
     * @param str 字符串
     * @param def 默认时间，如果转换失败则返回默认时间
     */
    public static Date strToDate(String str, String formatstr, Date def) {
        if (StringUtil.isEmpty(str))
            return def;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formatstr);
            return sdf.parse(str);
        } catch (Exception e) {
            return def;
        }
    }


    /**
     * 计算当前时间-提供的时间间隔
     *
     * @param str
     * @return
     */
    public static long intervalNow(String str) {
        return intervalNow(strToDate(str, null));
    }

    /**
     * 计算当前时间-提供的时间间隔
     *
     * @param date
     * @return
     */
    public static long intervalNow(Date date) {
        if (date == null)
            return (new Date()).getTime();
        return (new Date()).getTime() - date.getTime();
    }
    //截取年月日
    public static String getYMDT(String str) {
        String time = "";
        if (!StringUtil.isEmpty(str)) {
            int x = str.indexOf(" ");
            if (x == -1)
                return str;
            time = str.substring(0, x);
        }
        return time;
    }
    /**
     * 字符传转换成long
     * @param time
     * @return
     */
    public static long getTimeWString(String time) {
        if (StringUtil.isEmpty(time)) {
            return 0;
        }
        Date date = strToDate(time, null);
        if (date == null)
            return 0;
        return date.getTime();
    }
    //毫秒换成00:00:00
    public static String getCountTimeByLong(long finishTime) {
        int totalTime = (int) (finishTime / 1000);//秒
        int hour = 0, minute = 0, second = 0;

        if (3600 <= totalTime) {
            hour = totalTime / 3600;
            totalTime = totalTime - 3600 * hour;
        }
        if (60 <= totalTime) {
            minute = totalTime / 60;
            totalTime = totalTime - 60 * minute;
        }
        if (0 <= totalTime) {
            second = totalTime;
        }
        StringBuilder sb = new StringBuilder();

        if (hour < 10) {
            sb.append("0").append(hour).append(":");
        } else {
            sb.append(hour).append(":");
        }
        if (minute < 10) {
            sb.append("0").append(minute).append(":");
        } else {
            sb.append(minute).append(":");
        }
        if (second < 10) {
            sb.append("0").append(second);
        } else {
            sb.append(second);
        }
        return sb.toString();

    }

    //秒换成00:00:00
    public static String getCountTimeByLongOne(int totalTime) {
//        int totalTime = (int) (finishTime / 1000);//秒
        int hour = 0, minute = 0, second = 0;

        if (3600 <= totalTime) {
            hour = totalTime / 3600;
            totalTime = totalTime - 3600 * hour;
        }
        if (60 <= totalTime) {
            minute = totalTime / 60;
            totalTime = totalTime - 60 * minute;
        }
        if (0 <= totalTime) {
            second = totalTime;
        }
        StringBuilder sb = new StringBuilder();

        if (hour < 10) {
            sb.append("0").append(hour).append(":");
        } else {
            sb.append(hour).append(":");
        }
        if (minute < 10) {
            sb.append("0").append(minute).append(":");
        } else {
            sb.append(minute).append(":");
        }
        if (second < 10) {
            sb.append("0").append(second);
        } else {
            sb.append(second);
        }
        return sb.toString();

    }
}
