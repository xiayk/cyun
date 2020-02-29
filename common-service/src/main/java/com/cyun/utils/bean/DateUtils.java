//package com.cyun.utils.bean;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.Assert;
//import org.springframework.util.StringUtils;
//
//import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * <p>
// * Title:DateUtil
// * </p>
// * <p>
// * Description:(用一句话描述该文件做什么)
// * </p>
// * <p>
// * Copyright: Copyright (c) 2014
// * </p>
// * <p>
// * </p>
// *
// * @author yu
// */
//public final class DateUtils {
//    public final static int LASTDAY = 26;
//    public static final long MILLISECOND_PER_DAY = 86400000L;
//    public static final long MILLISECOND_PER_HOUR = 60 * 60 * 1000L;
//    public static final long MILLISECOND_PER_MINUTE = 60 * 1000L;
//    protected Logger logger = LoggerFactory.getLogger(getClass());
//    private static final String idstrDatePattern = "yyyyMMdd";
//    public static final SimpleDateFormat yyyymmddhhmmss = new SimpleDateFormat("yyyyMMddHHmmss");
//
//    /**
//     * 默认日期格式
//     */
//    private static final String[] parsePatterns = new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyy/MM/dd", "yyyy_MM_dd",
//            "yyyyMMdd", "yyyyMd", "yyyy.MM.dd", "yyyy-M", "yyyy-M-d"
//            // 这里可以增加更多的日期格式，用得多的放在前面
//    };
//
//    @SuppressWarnings("unused")
//    private static String timePattern = "HH:mm";
//
//    /**
//     * 得到 year 年后/前 的当天
//     *
//     * @param year       正数 后 负数 前
//     * @param newDateSec
//     * @return
//     */
//    public static Long getYearBeforAgo(int year, Long newDateSec) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(newDateSec);
//        int currenYear = calendar.get(Calendar.YEAR);
//        calendar.set(Calendar.YEAR, currenYear + year);
//        return calendar.getTimeInMillis();
//    }
//
//    /**
//     * 得到 month 月后/前 的当天
//     *
//     * @param month      正数 后 负数 前
//     * @param newDateSec
//     * @return
//     */
//    public static Long getMonthBeforAgo(int month, Long newDateSec) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(newDateSec);
//        int currenMonth = calendar.get(Calendar.MONTH);
//        calendar.set(Calendar.MONTH, currenMonth + month);
//        return calendar.getTimeInMillis();
//    }
//
//    /**
//     * 【得到毫秒数】(字符日期的毫秒数)
//     *
//     * @param date
//     * @return
//     */
//    public static Long getStrDataTimes(String date) {
//        if (StringUtils.isEmpty(date)) {
//            return null;
//        }
//        Date d = strToDate(date);
//
//        return d.getTime();
//    }
//
//    /**
//     * 【转换为yyyy/MM/dd格式的字符串日期】
//     *
//     * @param date
//     * @return
//     */
//
//    public static String getNowToStr(Date date) {
//        DateFormat df = new SimpleDateFormat(parsePatterns[1]);
//        return df.format(date);
//    }
//
//    public static String getNowToStrAll(Date date) {
//        DateFormat df = new SimpleDateFormat(parsePatterns[0]);
//        return df.format(date);
//    }
//
//    public static String getDateBeforetoString(String dateStr, int day) {
//        DateFormat df = new SimpleDateFormat(parsePatterns[1]);
//        Calendar now = Calendar.getInstance();
//        now.setTime(strToDate(dateStr, "yyyy-MM-dd"));
//        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
//        return df.format(now.getTime());
//    }
//
//    public static String getDateAftertoString(String dateStr, int day) {
//        DateFormat df = new SimpleDateFormat(parsePatterns[1]);
//        Calendar now = Calendar.getInstance();
//        now.setTime(strToDate(dateStr, "yyyy-MM-dd"));
//        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
//        return df.format(now.getTime());
//    }
//
//    public static String getNowToDay(Date date) {
//        DateFormat df = new SimpleDateFormat(parsePatterns[0]);
//        return df.format(date);
//    }
//
//    /**
//     * 【得到当前UNIX时间的秒数】
//     *
//     * @return
//     */
//    public static long getUnixTimeInSeconds() {
//        return Calendar.getInstance().getTimeInMillis() / 1000;
//    }
//
//    /**
//     * 【unix毫秒转Date】(这里用一句话描述这个方法的作用)
//     *
//     * @param unixTime
//     * @return
//     */
//    public static Date UnixTimeToDate(long unixTime) {
//        return new Date(unixTime);
//    }
//
//    /**
//     * 【unix毫秒转 时间秒数】(这里用一句话描述这个方法的作用)
//     *
//     * @param unixTime
//     * @return
//     */
//    public static long UnixTimeToTimeInSeconds(long unixTime) {
//        return getTimeInMillisecond(UnixTimeToDate(unixTime));
//    }
//
//    /**
//     * 【得到指定时间的毫秒数】
//     *
//     * @param d Date实例，为空则取当前时间
//     * @return
//     */
//    public static long getTimeInMillisecond(Date d) {
//        return d.getTime();
//    }
//
//    /**
//     * 【得到当前时间的毫秒数】(这里用一句话描述这个方法的作用)
//     *
//     * @return
//     */
//    public static long getTimeInSeconds() {
//        return System.currentTimeMillis();
//    }
//
//    /**
//     * 【】(d> d2 true)
//     *
//     * @param d
//     * @param d2
//     * @return
//     */
//    public static boolean sortDate(Date d, Date d2) {
//        if (d == null || d2 == null) {
//            return false;
//        }
//        Calendar c1 = Calendar.getInstance();
//        Calendar c2 = Calendar.getInstance();
//        c1.setTime(d);
//        c2.setTime(d2);
//        boolean  flag;
//        if(c1.equals(c2)){
//            flag = true;
//        }else {
//            flag =  c1.after(c2) ? true : false;
//        }
//
//
//        return flag;
//    }
//
//
//    /**
//     * 【判断两个日期是否同一天】
//     *
//     * @param d1
//     * @param d2
//     * @return
//     */
//    public static boolean date1ToDate2IsDAY(Date d1, Date d2) {
//        if (d1 == null || d2 == null) {
//            return false;
//        }
//        return dateToStr(d1).equals(dateToStr(d2));
//    }
//
//    public static String getDatePattern() {
//        String defaultDatePattern = "yyyy-MM-dd";
//        return defaultDatePattern;
//    }
//
//    public static String dateToStr(Date date) {
//        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());
//        return df.format(date);
//    }
//
//    /**
//     * 返回yyyy年MM月DD日
//     *
//     * @param date
//     * @return
//     */
//    public static String dateToYYMMDD(String date) {
//        Date d = strToDate(date);
//        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
//        return df.format(d);
//    }
//
//    /**
//     * 返回yyyy年MM月DD日
//     *
//     * @param date
//     * @return
//     */
//    public static String dateToYYMMDD(Date date) {
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
//        return df.format(date);
//    }
//
//    /**
//     * 返回yyyy-MM-DD
//     *
//     * @param date
//     * @return
//     */
//    public static String dateToYYMMDDs(Date date) {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        return df.format(date);
//    }
//
//    public static String dateToYYMMDDHHmmss(Date date) {
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//        return df.format(date);
//    }
//
//    public static String dateToYYMMDDHHmmss(String date) {
//        Date d = strToDate(date);
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//        return df.format(d);
//    }
//
//    public static String dateToYY_MM_DDHHmmss(String date) {
//        Date d = strToDate(date);
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return df.format(d);
//    }
//
//    public static String dateToYYYY_M_d(String date) {
//        Date d = strToDate(date);
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
//        return df.format(d);
//    }
//
//    public static String dateToYYYY_MM_dd(String date) {
//        Date d = strToDate(date);
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        return df.format(d);
//    }
//
//    /**
//     * 【返回yyyy年MM月】
//     *
//     * @param date
//     * @return
//     */
//
//    public static String dateToYYmms(String date) {
//        Date d = strToDate(date);
//        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月");
//        return df.format(d);
//    }
//
//    /**
//     * 当前日期
//     */
//    public static Date getDate() {
//        return new Date();
//    }
//
//    public static String date2Str(SimpleDateFormat date_sdf) {
//        Date date = getDate();
//
//        return date_sdf.format(date);
//    }
//
//    /**
//     * 【返回yyyy-MM】
//     *
//     * @param date
//     * @return
//     */
//
//    public static String dateToStrYYmm(Date date) {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
//        return df.format(date);
//    }
//
//    public static String dateToStrYYmm2(Date date) {
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
//        return df.format(date);
//    }
//
//    public static String dateToStrYYmmss(Date date) {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return df.format(date);
//    }
//
//    /**
//     * text:时间转换成指定格式的str字符<br>
//     *
//     * @param date
//     * @param datePattern
//     * @return
//     * @author 戴向军 2016年8月22日--> 下午4:38:19
//     */
//    public static String dateToStrPattern(Date date, String datePattern) {
//        SimpleDateFormat df = new SimpleDateFormat(datePattern);
//        return df.format(date);
//    }
//
//    public static String dateToStrid(Date date) {
//        SimpleDateFormat df = new SimpleDateFormat(idstrDatePattern);
//        return df.format(date);
//    }
//
//    public static String dateToStrNoYear(Date date) {
//        SimpleDateFormat df = new SimpleDateFormat("MM-dd");
//        return df.format(date);
//    }
//
//    public static String dateToStr(Date date, String datePattern) {
//        SimpleDateFormat df = new SimpleDateFormat(datePattern);
//        return df.format(date);
//    }
//
//    /**
//     * 【得到时间的秒数】(这里用一句话描述这个方法的作用)
//     *
//     * @param date
//     * @return
//     */
//    public static Long dateToMillisecond(Date date) {
//        return null != date ? dateToSecond(date) / 1000 : null;
//    }
//
//    /**
//     * 【得到时间的毫秒数】(这里用一句话描述这个方法的作用)
//     *
//     * @param date
//     * @return
//     */
//    public static Long dateToSecond(Date date) {
//        return null != date ? date.getTime() : null;
//    }
//
//    public static String getNow() {
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//        String nowstr = df.format(new Date());
//        return nowstr;
//    }
//
//    public static Date strToDateHHMM(String str) {
//
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            return df.parse(str);
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static Date strToDate(String str) {
//        Date date = null;
//        try {
//            date = org.apache.commons.lang3.time.DateUtils.parseDate(str, parsePatterns);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return date;
//    }
//
//    public static Date strToDate(String str, String datePattern) {
//        Date date = null;
//        try {
//            date = org.apache.commons.lang3.time.DateUtils.parseDate(str, new String[]{datePattern});
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return date;
//    }
//
//    /**
//     * 【用来转化把包含时分秒的时间字符串】
//     *
//     * @param str
//     * @return
//     */
//
//    public static Date strToTime(String str) {
//        Date date = null;
//        try {
//            date = org.apache.commons.lang3.time.DateUtils.parseDate(str, new String[]{"yyyy-MM-dd HH:mm:ss"});
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return date;
//    }
//
//    public static Date strToTimeddHHmm(String str) throws ParseException {
//        Date date = null;
//
//        date = org.apache.commons.lang3.time.DateUtils.parseDate(str, new String[]{"yyyy-MM-dd HH:mm"});
//
//        return date;
//    }
//
//    public static String getLastMonthStr() {
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.MONTH, -1);
//        Date d1 = cal.getTime();
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
//        String lastMonthStr = df.format(d1);
//        return lastMonthStr;
//    }
//
//    public static String getThisMonthStr() {
//        Calendar cal = Calendar.getInstance();
//        Date d1 = cal.getTime();
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
//        String lastMonthStr = df.format(d1);
//        return lastMonthStr;
//    }
//
//    public static String getTodayBhStr() {
//        Calendar cal = Calendar.getInstance();
//        Date d1 = cal.getTime();
//        SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
//        return df.format(d1);
//    }
//
//    public static String getTodayBhStr2() {
//        Calendar cal = Calendar.getInstance();
//        Date d1 = cal.getTime();
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
//        return df.format(d1);
//    }
//
//    @SuppressWarnings("static-access")
//    public static Integer getAgoBh(int dayNo) {
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DAY_OF_MONTH, -dayNo + 1);
//        Date d1 = cal.getTime();
//        SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
//        return Integer.valueOf(df.format(d1) + "0000");
//    }
//
//    public static Date getThisMonthEndDate() {
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        return cal.getTime();
//    }
//
//    /**
//     * 获取当前月份下一个月的最后一天
//     *
//     * @return
//     */
//    public static Date getNextThisMonthEndDate() {
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.MONTH, +1);
//        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        return cal.getTime();
//    }
//
//    /**
//     * 获取上一个月的最后一天
//     *
//     * @return
//     */
//    public static Date getLastMonthEndDate() {
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.MONTH, -1);
//        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        return cal.getTime();
//    }
//
//    /**
//     * 【获取当前上个月1号】
//     *
//     * @return
//     * @time:2015年11月11日 下午4:12:20
//     * @author 涂鑫
//     * @version
//     */
//    public static Date getLastMonthStartDate() {
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.MONTH, -1);
//        cal.set(Calendar.DAY_OF_MONTH, 1);
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        return cal.getTime();
//    }
//
//    /**
//     * 【获取指定月份上个月1号】
//     *
//     * @param date
//     * @return
//     * @time:2015年11月11日 下午4:12:46
//     * @author 涂鑫
//     * @version
//     */
//    public static Date getLastMonthStartDate(Date date) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        cal.add(Calendar.MONTH, -1);
//        cal.set(Calendar.DAY_OF_MONTH, 1);
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        return cal.getTime();
//    }
//
//    /**
//     * 获取当前月份下一个月的第一天
//     *
//     * @return
//     */
//    public static Date getNextLastMonthStartDate() {
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.MONTH, +1);
//        cal.set(Calendar.DAY_OF_MONTH, 1);
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        return cal.getTime();
//    }
//
//    /**
//     * 【获得指定日期所在月份的最后一天】
//     *
//     * @param date
//     * @return
//     */
//
////    public static String getLastDayOfMonth(Date date) {
////        int year = Integer.parseInt(DateUtil.dateToStr(date, "yyyy"));
////        int month = Integer.parseInt(DateUtil.dateToStr(date, "MM")) - 1;
////        Calendar cal = Calendar.getInstance();
////        cal.set(Calendar.YEAR, year);
////        cal.set(Calendar.MONTH, month);
////        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
////        cal.set(Calendar.HOUR_OF_DAY, 23);
////        cal.set(Calendar.SECOND, 59);
////        cal.set(Calendar.MINUTE, 59);
////        return dateToStrPattern(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
////    }
////
////    /**
////     * 【获得指定日期所在月份的第一天】
////     *
////     * @param date
////     * @return
////     */
////
////    public static String getFirstDayOfMonth(Date date) {
////        int year = Integer.parseInt(DateUtil.dateToStr(date, "yyyy"));
////        int month = Integer.parseInt(DateUtil.dateToStr(date, "MM")) - 1;
////        Calendar cal = Calendar.getInstance();
////        cal.set(Calendar.YEAR, year);
////        cal.set(Calendar.MONTH, month);
////        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
////        cal.set(Calendar.HOUR_OF_DAY, 0);
////        cal.set(Calendar.SECOND, 0);
////        cal.set(Calendar.MINUTE, 0);
////
////        return dateToStrPattern(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
////    }
//
//    /**
//     * 通过两个时间段，按默认30的比例划分
//     *
//     * @param startTime
//     * @param endTime
//     */
//
//    public static List dateProportionDivision30(String startTime, String endTime) {
//        List dateList = new ArrayList();
//        int proportion = ((daysBetween(dateToYYYY_M_d(startTime), dateToYYYY_M_d(endTime)) - 1) / 30) + 1;
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date startTimeDate = null; //初始化date
//            startTimeDate = sdf.parse(startTime); //Mon Jan 14 00:00:00 CST 2013
//            int interval = 0;
//            if (daysBetween(dateToYYYY_M_d(startTime), dateToYYYY_M_d(endTime)) <= 30) {
//                interval = daysBetween(dateToYYYY_M_d(startTime), dateToYYYY_M_d(endTime));
//            } else {
//                interval = ((daysBetween(dateToYYYY_M_d(startTime), dateToYYYY_M_d(endTime))) / 2) + 1;
//            }
//            for (int i = 0; i <= interval; i++) {
//                if (i == interval) {
//                    dateList.add(dateToStrPattern(org.apache.commons.lang3.time.DateUtils.parseDate(endTime, new String[]{"yyyy-MM-dd"}), "M-d"));
//                } else {
//                    dateList.add(dateToStrPattern(getDateAfter(startTimeDate, proportion * i), "M-d"));
//                }
//            }
//        } catch (ParseException e) {
////            e.printStackTrace();
//        }
//        return dateList;
//    }
//
//    public static void main(String[] args) {
////        System.out.println(getDistanceDays("2018-1-1 00:00:00", "2018-1-2 23:59:59"));
////        System.out.println(DateUtil.dateToYYMMDDs(new Date()));
////        System.out.println(DateUtil.nextDayToStr(new Date()));
////        System.out.println(DateUtil.getDistanceDays(DateUtil.dateToYYMMDDs(new Date()), DateUtil.nextDayToStr(new Date())));
//      System.out.println(strToDate("2018-05","yyyy-MM"));
//        System.out.println(DateUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"));
//
//
//    }
//
//    public static Date getThisMonthStartDate() {
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.DAY_OF_MONTH, 1);
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        return cal.getTime();
//    }
//
//    public static String getCNYearMonthStr(String yearMonth) {
//        int year = Integer.parseInt(yearMonth.substring(0, 4));
//        int month = Integer.parseInt(yearMonth.substring(4));
//        return year + "年" + month + "月";
//    }
//
//    public static String getFirstDayOfMonth(String yearMonth) {
//
//        int year = Integer.parseInt(yearMonth.substring(0, 4));
//        int month = Integer.parseInt(yearMonth.substring(4));
//        Calendar cal = Calendar.getInstance();
//        cal.set(year, month - 1, 1);
//        return dateToStr(getFirstDayOfMonthDate(yearMonth));
//    }
//
//    public static Date getFirstDayOfMonthDate(String yearMonth) {
//        int year = Integer.parseInt(yearMonth.substring(0, 4));
//        int month = Integer.parseInt(yearMonth.substring(4));
//        Calendar cal = Calendar.getInstance();
//        cal.set(year, month - 1, 1);
//        return cal.getTime();
//    }
//
//    public static String getLastDayOfMonth(String yearMonth) {
//        return dateToStr(getLastDayOfMonthDate(yearMonth));
//    }
//
//    public static Date getLastDayOfMonthDate(String yearMonth) {
//        int year = Integer.parseInt(yearMonth.substring(0, 4));
//        int month = Integer.parseInt(yearMonth.substring(4));
//        Calendar cal = Calendar.getInstance();
//        cal.set(year, month - 1, 1);
//        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
//        return cal.getTime();
//    }
//
//    public static Integer getMonthDays(String yearMonth) {
//        int year = Integer.parseInt(yearMonth.substring(0, 4));
//        int month = Integer.parseInt(yearMonth.substring(4));
//        Calendar cal = Calendar.getInstance();
//        cal.set(year, month - 1, 1);
//        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//    }
//
//    /**
//     * 计算天数
//     *
//     * @param beginDate yyyy-MM-dd
//     * @param endDate   yyyy-MM-dd
//     * @return
//     */
//    public static int daysBetween(String beginDate, String endDate) {
//        try {
//            Date date1 = org.apache.commons.lang3.time.DateUtils.parseDate(beginDate, new String[]{"yyyy-MM-dd"});
//            Date date2 = org.apache.commons.lang3.time.DateUtils.parseDate(endDate, new String[]{"yyyy-MM-dd"});
//            long days = Math.abs((date2.getTime() - date1.getTime()) / MILLISECOND_PER_DAY) + 1;
//            // log.debug("beginDate:"+beginDate+"\tendDate:"+endDate+"\tdays："+days);
////            return new Long(days).intValue();
////            return
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    /**
//     * 计算天数
//     *
//     * @param beginDate yyyy-MM-dd
//     * @param endDate   yyyy-MM-dd
//     * @return
//     */
//    public static int daysBetween(Long beginDate, Long endDate) {
//        try {
//
//            Date date1 = org.apache.commons.lang3.time.DateUtils.parseDate(formatDate(beginDate, "yyyy-MM-dd"), new String[]{"yyyy-MM-dd"});
//            Date date2 = org.apache.commons.lang3.time.DateUtils.parseDate(formatDate(endDate, "yyyy-MM-dd"), new String[]{"yyyy-MM-dd"});
//            long days = Math.abs((date2.getTime() - date1.getTime()) / MILLISECOND_PER_DAY);
//            // log.debug("beginDate:"+beginDate+"\tendDate:"+endDate+"\tdays："+days);
//            return new Long(days).intValue();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    /**
//     * 通用计算 天、小时、分钟的间隔 周期数 ，针对 yyyy-MM-dd HH:mm:ss 格式
//     *
//     * @param beginDate yyyy-MM-dd HH:mm:ss
//     * @param endDate   yyyy-MM-dd HH:mm:ss
//     * @param ：         天 2： 小时 3： 分钟 4：秒
//     * @return
//     */
//    public static long periodBetween(String beginDate, String endDate, int dealtimelimitunit) {
//        try {
//            Date date1 = org.apache.commons.lang3.time.DateUtils.parseDate(beginDate, new String[]{"yyyy-MM-dd HH:mm:ss"});
//            Date date2 = org.apache.commons.lang3.time.DateUtils.parseDate(endDate, new String[]{"yyyy-MM-dd HH:mm:ss"});
//            long limit = 0;
//            if (dealtimelimitunit == 1) {
//                limit = Math.abs((date2.getTime() - date1.getTime()) / MILLISECOND_PER_DAY);
//            } else if (dealtimelimitunit == 2) {
//                limit = Math.abs((date2.getTime() - date1.getTime()) / MILLISECOND_PER_HOUR);
//            } else if (dealtimelimitunit == 3) {
//                limit = Math.abs((date2.getTime() - date1.getTime()) / MILLISECOND_PER_MINUTE);
//            } else if (dealtimelimitunit == 4) {
//                limit = Math.abs((date2.getTime() - date1.getTime()));
//            }
//
//            return new Long(limit);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    /**
//     * 获取指定月份
//     *
//     * @param monthStr yyyyMM格式
//     * @param monthAdd
//     * @return
//     */
//    public static String getMonthStr(String monthStr, int monthAdd) {
//        if (monthAdd == 0) {
//            return monthStr;
//        }
//
//        int year = Integer.parseInt(monthStr.substring(0, 4));
//        int month = Integer.parseInt(monthStr.substring(4));
//        Calendar cal = Calendar.getInstance();
//        cal.set(year, month - 1, 1);
//        cal.add(Calendar.MONTH, monthAdd);
//        Date d1 = cal.getTime();
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
//        String newmonthStr = df.format(d1);
//        return newmonthStr;
//    }
//
//    public static String[] getMonthStr(String monthStr) {
//        int year = Integer.parseInt(monthStr.substring(0, 4));
//        int month = Integer.parseInt(monthStr.substring(4));
//        Calendar cal = Calendar.getInstance();
//        cal.set(year, month - 1, 1);
//        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//        String[] dates = new String[maxDay];
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        for (int i = 0; i < maxDay; i++) {
//
//            dates[i] = df.format(cal.getTime());
//            cal.add(Calendar.DAY_OF_MONTH, 1);
//
//        }
//
//        return dates;
//    }
//
//    public static int getMonthMaxDays(String monthStr) {
//        int year = Integer.parseInt(monthStr.substring(0, 4));
//        int month = Integer.parseInt(monthStr.substring(4));
//        Calendar cal = Calendar.getInstance();
//        cal.set(year, month - 1, 1);
//        return cal.getMaximum(Calendar.DAY_OF_MONTH);
//    }
//
//    /**
//     * 只接受开始日期小余等于结束日期 计算开始日期和结束日期
//     *
//     * @param beginDate
//     * @param start
//     * @param endDate
//     * @param end
//     * @return
//     */
//    @SuppressWarnings({"deprecation"})
//    public static double computeHolidayNums(Date beginDate, int start, Date endDate, int end) {
//        double no = 0;
//        if (start == 2) {
//            beginDate.setHours(12);
//        }
//        if (end == 2) {
//            endDate.setHours(12);
//        }
//        // 只接受开始日期小余等于结束日期
//        Assert.isTrue(beginDate.getTime() <= endDate.getTime());
//        double b = endDate.getTime() - beginDate.getTime();
//        no = 0.5 + b / MILLISECOND_PER_DAY;
//        return no;
//    }
//
//    public static Double computeStrHolidayNums(String beginDate, int start, String endDate, int end) {
//        try {
//            Date d1 = org.apache.commons.lang3.time.DateUtils.parseDate(beginDate, new String[]{"yyyy-MM-dd"});
//            Date d2 = org.apache.commons.lang3.time.DateUtils.parseDate(endDate, new String[]{"yyyy-MM-dd"});
//            return computeHolidayNums(d1, start, d2, end);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static List<Integer> getMonthList(int beginMonth, int endMonth) {
//        List<Integer> list = new ArrayList<Integer>();
//
//        while (beginMonth <= endMonth) {
//            list.add(beginMonth);
//            beginMonth = Integer.parseInt(getMonthStr("" + beginMonth, 1));
//        }
//        return list;
//    }
//
//    public static int[] monthDays = {31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
//
//    /**
//     * 取上周的今天
//     *
//     * @return
//     */
//    public static Date getLastWeekDate() {
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(System.currentTimeMillis() - (7 * 24 * 3600 * 1000));
//        return cal.getTime();
//    }
//
//    /**
//     * 取下周的今天
//     *
//     * @return
//     */
//    public static Date getNextWeekDate() {
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(System.currentTimeMillis() + (7 * 24 * 3600 * 1000));
//        return cal.getTime();
//    }
//
//    /**
//     * 日期加1天后再格式化
//     *
//     * @param date
//     * @return
//     */
//    public static String nextDayToStr(Date date) {
//        date = new Date(date.getTime() + 1 * 24 * 60 * 60 * 1000);
//        return dateToStr(date);
//    }
//
//    /**
//     * 日期加1天后再格式化
//     *
//     * @param date 基准日期
//     * @param days 正数 往后加的天数 负数 往前减的天数
//     * @return
//     */
//    public static String nextDayToStr(Date date, int days) {
//        date = new Date(date.getTime() + days * 24 * 60 * 60 * 1000);
//        return dateToStr(date);
//    }
//
//    /**
//     * 取给定时间的当月一号
//     *
//     * @param date
//     * @return
//     */
//    public static Date getMonthStartDate(Date date) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        cal.set(Calendar.DAY_OF_MONTH, 1);
//        try {
//            return org.apache.commons.lang3.time.DateUtils.parseDate(dateToStr(cal.getTime()), new String[]{"yyyy-MM-dd"});
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return new Date();
//        }
//    }
//
//    /**
//     * 取当前日期的前一天
//     *
//     * @param date
//     * @return
//     */
//    public static Date getYesterdayDate(Date date) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
//        try {
//            return org.apache.commons.lang3.time.DateUtils.parseDate(dateToStr(cal.getTime()), new String[]{"yyyy-MM-dd"});
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return new Date();
//        }
//    }
//
//    /**
//     * 取当天是否为当月的第一天
//     *
//     * @param date
//     * @return
//     */
//    public static boolean isFirstDate(Date date) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        if (cal.get(Calendar.DATE) == 1) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    /**
//     * 取当前的年龄
//     *
//     * @param birthDay
//     * @return
//     * @throws Exception
//     */
//    public static int getAge(Date birthDay) throws Exception {
//        Calendar cal = Calendar.getInstance();
//
//        if (cal.before(birthDay)) {
//            throw new IllegalArgumentException("出生时间大于当前时间!");
//        }
//
//        int yearNow = cal.get(Calendar.YEAR);
//        int monthNow = cal.get(Calendar.MONTH) + 1;// 注意此处，如果不加1的话计算结果是错误的
//        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
//        cal.setTime(birthDay);
//
//        int yearBirth = cal.get(Calendar.YEAR);
//        int monthBirth = cal.get(Calendar.MONTH);
//        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
//
//        int age = yearNow - yearBirth;
//
//        if (monthNow <= monthBirth) {
//            if (monthNow == monthBirth) {
//                if (dayOfMonthNow < dayOfMonthBirth) {
//                    age--;
//                }
//            } else {
//                age--;
//            }
//        }
//        return age;
//    }
//
//    public static Date getRootLastWeekDate() {
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(System.currentTimeMillis() - (3 * 24 * 3600 * 1000));
//        return cal.getTime();
//    }
//
//    public static Date getRootNextWeekDate() {
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(System.currentTimeMillis() + (3 * 24 * 3600 * 1000));
//        return cal.getTime();
//    }
//
//    /**
//     * 取上周四
//     *
//     * @return
//     */
//    public static Date getLastThursday() {
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.DAY_OF_WEEK, 5);
//        cal.setTimeInMillis(cal.getTimeInMillis() - (7 * 24 * 3600 * 1000));
//        return cal.getTime();
//    }
//
//    /**
//     * 取这周三
//     *
//     * @return
//     */
//    public static Date getThisWednesday() {
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.DAY_OF_WEEK, 4);
//        return cal.getTime();
//    }
//
//    /**
//     * 取规定日期之前的X天
//     *
//     * @param date
//     * @param i
//     * @return
//     */
//    public static String getRestrictDay(Date date, int i) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        cal.setTimeInMillis(System.currentTimeMillis() - (i * 24 * 3600 * 1000));
//        return dateToStr(cal.getTime());
//    }
//
//    /**
//     * 取规定日期最近的m天之内并且每个n 取一个日子
//     *
//     * @param date
//     * @param
//     * @return
//     */
//    public static List<String> getRestrictDayList(Date date, int m, int n) {
//        Calendar cal = Calendar.getInstance();
//
//        List<String> list = new ArrayList<>();
//
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//        String days = sdf1.format(date);
//        list.add(days);
//        int count = n;
//        for (int i = 2; n < m; i++) {
//            n = count * i;
//
//            cal.add(Calendar.DATE, -count);
//            String days_ago = sdf1.format(cal.getTime());
//            list.add(days_ago);
//        }
//        return list;
//    }
//
//    /**
//     * 取得指定日期所在周的第一天
//     *
//     * @param date 指定日期
//     * @return 所在周的第一天
//     * @author 赵汉江
//     */
//    public static Date getFirstDayOfWeek(Date date) {
//        Calendar c = new GregorianCalendar();
//        c.setFirstDayOfWeek(Calendar.MONDAY);
//        c.setTime(date);
//        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
//        return c.getTime();
//    }
//
//    /**
//     * 获取指定日期所在周的任意周几的日期
//     *
//     * @param date
//     * @param dayNumber
//     * @return
//     * @author 唐礼军
//     */
//    public static Date getFirstDayOfWeek(Date date, int dayNumber) {
//        Calendar c = new GregorianCalendar();
//        c.setFirstDayOfWeek(Calendar.MONDAY);
//        c.setTime(date);
//        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + dayNumber - 1);
//        return c.getTime();
//    }
//
//    /**
//     * 【取得指定日期所在周的指定星期值的日期 星期值 从1-7，1为星期一，7为星期日。】
//     *
//     * @param date
//     * @param weekvalue
//     * @return
//     */
//
//    public static Date getDateByDateOfWeek(Date date, int weekvalue) {
//        if (weekvalue > 7 || weekvalue < 1) {
//            return null;
//        }
//        Calendar c = new GregorianCalendar();
//        c.setFirstDayOfWeek(Calendar.MONDAY);
//        c.setTime(date);
//        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + (weekvalue - 1)); // Sunday
//        return c.getTime();
//    }
//
//    /**
//     * 【获得当前日期是星期几 星期一返回1 星期二返回2 星期三返回3 星期四返回4 星期五返回5 星期六返回6
//     * 星期日返回7】(这里用一句话描述这个方法的作用)
//     *
//     * @param date
//     * @return
//     */
//
//    public static int getDayForWeek(Date date) {
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        return getDayForWeek(c);
//    }
//
//    /**
//     * 【获得当前格林尼治时间在中国地区的当前星期中的[1~7]天数值】
//     *
//     * @param calendar
//     * @return
//     */
//
//    public static int getDayForWeek(Calendar calendar) {
//        if (null != calendar) {
//            Calendar preDay = Calendar.getInstance();
//            preDay.setTimeInMillis(calendar.getTimeInMillis());
//            preDay.add(Calendar.DAY_OF_WEEK, -1);
//            return preDay.get(Calendar.DAY_OF_WEEK);
//        } else {
//            return -1;
//        }
//    }
//
//    /**
//     * 获得当前日期是 汉字星期几 星期一返回"一" 星期二返回"二" 星期三返回"三" 星期四返回"四" 星期五返回"五" 星期六返回"六"
//     * 星期日返回"日"
//     *
//     * @param
//     * @return
//     * @throws Throwable
//     */
//    public static String getDayForWeekcn(Date date) {
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        int weekint = c.get(Calendar.DAY_OF_WEEK) - 1;
//        String weekcn = null;
//        switch (weekint) {
//            case 1:
//                weekcn = "一";
//                break;
//            case 2:
//                weekcn = "二";
//                break;
//            case 3:
//                weekcn = "三";
//                break;
//            case 4:
//                weekcn = "四";
//                break;
//            case 5:
//                weekcn = "五";
//                break;
//            case 6:
//                weekcn = "六";
//                break;
//            case 0:
//                weekcn = "日";
//                break;
//            default:
//                break;
//        }
//        return weekcn;
//    }
//
//    /**
//     * 根据星期的数值得到 星期的汉字值 星期一返回"一" 星期二返回"二" 星期三返回"三" 星期四返回"四" 星期五返回"五" 星期六返回"六"
//     * 星期日返回"日"
//     *
//     * @param
//     * @return
//     * @throws Throwable
//     */
//    public static String getWeekintForWeekcn(int weekint) {
//        String weekcn = null;
//        switch (weekint) {
//            case 1:
//                weekcn = "一";
//                break;
//            case 2:
//                weekcn = "二";
//                break;
//            case 3:
//                weekcn = "三";
//                break;
//            case 4:
//                weekcn = "四";
//                break;
//            case 5:
//                weekcn = "五";
//                break;
//            case 6:
//                weekcn = "六";
//                break;
//            case 7:
//                weekcn = "日";
//                break;
//            case 0:
//                weekcn = "日";
//                break;
//            default:
//                break;
//        }
//        return weekcn;
//    }
//
//    /**
//     * 根据星期的数值得到 星期的汉字值 星期一返回"一" 星期二返回"二" 星期三返回"三" 星期四返回"四" 星期五返回"五" 星期六返回"六"
//     * 星期日返回"日"
//     *
//     * @param
//     * @return
//     * @throws Throwable
//     */
//    public static String getWeekintForWeekcn2(int weekint) {
//        String weekcn = null;
//        switch (weekint) {
//            case 1:
//                weekcn = "一";
//                break;
//            case 2:
//                weekcn = "二";
//                break;
//            case 3:
//                weekcn = "三";
//                break;
//            case 4:
//                weekcn = "四";
//                break;
//            case 5:
//                weekcn = "五";
//                break;
//            case 6:
//                weekcn = "六";
//                break;
//            case 7:
//                weekcn = "七";
//                break;
//
//            case 8:
//                weekcn = "八";
//                break;
//            case 9:
//                weekcn = "九";
//                break;
//            case 0:
//                weekcn = "零";
//                break;
//
//            default:
//                break;
//        }
//        return weekcn;
//    }
//
//    /**
//     * 获得时间路径
//     *
//     * @return String
//     * @Title: getDatePath
//     * @Description: TODO(这里用一句话描述这个方法的作用)
//     * @author 赵汉江
//     * @date 2013-3-13 下午08:39:28
//     * @version V1.0
//     */
//    public static String getDatePath() {
//        return DateUtil.dateToStr(new Date(), "yyyy/MM/dd");
//    }
//
//    /**
//     * 【据 星期值 查询 开始日期 和 结束日期 时间段内 星期值相等的第一个日期】
//     *
//     * @param Startdate
//     * @param enddate
//     * @param weekvalue
//     * @return
//     */
//
//    public static Date getDateByStartdateEnddateWeekvalue(Date Startdate, Date enddate, int weekvalue) {
//        List<Date> list = getEveryDay(Startdate, enddate);
//        for (Date date : list) {
//            int day = getDayForWeek(date);
//            if (day == weekvalue) {
//                return date;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 【获取一段时间内每天的日期 包括开始日期和结束日期】
//     *
//     * @param startDate
//     * @param endDate
//     * @return
//     */
//
//    public static List<Date> getEveryDay(Date startDate, Date endDate) {
//        Calendar c = new GregorianCalendar();
//        List<Date> list = new ArrayList<Date>();
//        list.add(startDate);
//        while (startDate.before(endDate)) {
//            c.add(Calendar.DAY_OF_YEAR, 1);
//            startDate = c.getTime();
//            list.add(startDate);
//        }
//        return list;
//    }
//
//    /**
//     * 【获取一段时间类每天日期】
//     *
//     * @param
//     * @param
//     * @return
//     * @time:2015年12月17日 下午4:52:49
//     * @author 涂鑫
//     * @version
//     */
//    public static List<Date> findDates(Date startDate, Date endDate) {
//        List<Date> lDate = new ArrayList<Date>();
//        lDate.add(startDate);
//        Calendar calBegin = Calendar.getInstance();
//        // 使用给定的 Date 设置此 Calendar 的时间
//        calBegin.setTime(startDate);
//        Calendar calEnd = Calendar.getInstance();
//        // 使用给定的 Date 设置此 Calendar 的时间
//        calEnd.setTime(endDate);
//        // 测试此日期是否在指定日期之后
//        while (endDate.after(calBegin.getTime())) {
//            if (date1ToDate2IsDAY(endDate, calBegin.getTime())) {
//                break;
//            }
//            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
//            calBegin.add(Calendar.DAY_OF_MONTH, 1);
//            lDate.add(calBegin.getTime());
//        }
//        return lDate;
//    }
//
//
//    /**
//     * 【返回两个日期相差的月份】
//     *
//     * @param s_date
//     * @param e_date
//     * @return
//     */
//
//    public static int getEveryMonth(Date s_date, Date e_date) {
//        if (s_date == null || e_date == null) {
//            return -1;
//        }
//        int month = 0;
//        Calendar c = Calendar.getInstance();
//        c.setTime(s_date);
//
//        int s_y = c.get(Calendar.YEAR);
//        int s_m = c.get(Calendar.MONTH) + 1;
//
//        c.setTime(e_date);
//
//        int e_y = c.get(Calendar.YEAR);
//        int e_m = c.get(Calendar.MONTH) + 1;
//
//        if (s_y == e_y) {
//            month = e_m - s_m;
//        } else {
//            month = 12 * (e_y - s_y) + e_m - s_m;
//        }
//        return month;
//    }
//
//    /**
//     * 【得到指定日期的月份 1 - 12】(这里用一句话描述这个方法的作用)
//     *
//     * @param date
//     * @return
//     */
//
//    public static int getDateMoth(Date date) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//
//        return calendar.get(Calendar.MONTH) + 1;
//    }
//
//    /**
//     * 【得到指定日期的月份】(这里用一句话描述这个方法的作用)
//     *
//     * @param date
//     * @return
//     */
//
//    public static String getDateMonthStr(Date date) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        int month = calendar.get(Calendar.MONTH) + 1;
//        String weekcn = "一";
//        switch (month) {
//            case 1:
//                weekcn = "一";
//                break;
//            case 2:
//                weekcn = "二";
//                break;
//            case 3:
//                weekcn = "三";
//                break;
//            case 4:
//                weekcn = "四";
//                break;
//            case 5:
//                weekcn = "五";
//                break;
//            case 6:
//                weekcn = "六";
//                break;
//            case 7:
//                weekcn = "七";
//                break;
//            case 8:
//                weekcn = "八";
//                break;
//            case 9:
//                weekcn = "九";
//                break;
//            case 10:
//                weekcn = "十";
//                break;
//            case 11:
//                weekcn = "十一";
//                break;
//            case 12:
//                weekcn = "十二";
//                break;
//            default:
//                break;
//        }
//        return weekcn;
//    }
//
//    /**
//     * 【查找所在日期的上一个月的月份 yyyy-MM】
//     *
//     * @param d
//     * @return
//     */
//    public static String getUpMonthByDate(Date d) {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
//        return df.format(getSpaceMonthDate(d, -1));
//    }
//
//    /**
//     * 【获得 基数时间 间隔月份的时间】(这里用一句话描述这个方法的作用)
//     *
//     * @param d     时间基数
//     * @param space 间隔几个月，可以使正负数
//     * @return
//     */
//    public static Date getSpaceMonthDate(Date d, int space) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(d);
//        calendar.add(Calendar.MONTH, space);
//        return calendar.getTime();
//    }
//
//    /**
//     * 【得到日期的对应月份的最后一天】
//     *
//     * @param date
//     * @return
//     */
//
//    public static Date getLastMonthDay(Date date) {
//        Date dd = getMonthStartDate(date);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(dd);
//        calendar.add(Calendar.MONTH, 1); // 下个月的第一天
//        calendar.add(Calendar.DATE, -1); // 本月的最后一天
//        return calendar.getTime();
//    }
//
//    /**
//     * 【得到日期对应下月的第一天】
//     *
//     * @param date
//     * @return
//     */
//
//    public static Date getNextMonthFirst(Date date) {
//        Date dd = getMonthStartDate(date);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(dd);
//        calendar.add(Calendar.MONTH, 1); // 下个月的第一天
//        return calendar.getTime();
//    }
//
//    /**
//     * 【得到指定日期的一周的日期】
//     *
//     * @param date
//     * @return
//     */
//
//    public static List<String> getThisWeekDay(Date date) {
//        Calendar c = Calendar.getInstance();
//        List<String> list = new ArrayList<String>();
//        Date monday = getFirstDayOfWeek(date);
//        c.setTime(monday);
//        list.add(dateToStr(monday));
//        for (int i = 1; i < 7; i++) {
//            c.add(Calendar.DAY_OF_YEAR, 1);
//            Date week = c.getTime();
//            list.add(dateToStr(week));
//        }
//        return list;
//    }
//
//    /**
//     * 【获得时间段内的月份 以集合返回】
//     *
//     * @param begin
//     * @param end
//     * @return
//     */
//
//    @SuppressWarnings("deprecation")
//    public static List<String> getMonthListByBegindateEnddate(Date begin, Date end) {
//        @SuppressWarnings("unused")
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//        SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
//        List<String> monthList = new ArrayList<String>();
//        int months = (end.getYear() - begin.getYear()) * 12 + (end.getMonth() - begin.getMonth());
//        for (int i = 0; i <= months; i++) {
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(begin);
//            calendar.add(Calendar.MONTH, i);
//            monthList.add(monthFormat.format(calendar.getTime()));
//        }
//        return monthList;
//    }
//
//    /**
//     * 【返回固定小时，分钟的 时间】
//     *
//     * @param cal
//     * @param timerhour
//     * @param timerminute
//     * @return
//     */
//
//    public static String getTimerDate(Calendar cal, int timerhour, int timerminute) {
//
//        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
//        String strhour = "";
//        String strminute = "";
//        if (timerhour < 10) {
//            strhour = "0" + String.valueOf(timerhour);
//        } else {
//            strhour = String.valueOf(timerhour);
//        }
//
//        if (timerminute < 10) {
//            strminute = "0" + String.valueOf(timerminute);
//        } else {
//            strminute = String.valueOf(timerminute);
//        }
//
//        return df2.format(cal.getTime()) + " " + strhour + ":" + strminute + ":00";
//    }
//
//    public static String getCurrentTimeDate() {
//        Calendar calendar = Calendar.getInstance();
//        Date d1 = calendar.getTime();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return df.format(d1);
//    }
//
//    /***
//     * 获取指定日期推后几天的日期
//     *
//     * @param d
//     * @param day
//     * @return
//     * @author 戴向军
//     * @date 2016年5月25日->下午6:02:41
//     */
//    public static Date getDateAfter(Date d, int day) {
//        Calendar now = Calendar.getInstance();
//        now.setTime(d);
//        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
//        return now.getTime();
//    }
//
//    /**
//     * 获取指定日期推前几天的日期 text:<br>
//     *
//     * @param
//     * @param day
//     * @return
//     * @author 戴向军 2016年8月12日--> 下午5:30:14
//     */
//    public static Date getDateBefore(String dateStr, int day) {
//        Calendar now = Calendar.getInstance();
//        now.setTime(strToDate(dateStr, "yyyy-MM-dd"));
//        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
//        return now.getTime();
//    }
//
//
//    /***
//     * 增加或者减少一年
//     *
//     * @param date
//     * @param num
//     *            增加减少的年份增加为正数，减少的传负数
//     * @return
//     * @author 戴向军
//     * @date 2016年6月8日->上午11:22:27
//     */
//    public static Long changeYeary(Date date, int num) {
//        Calendar startDT = Calendar.getInstance();
//        startDT.setTime(date);
//        startDT.add(Calendar.YEAR, num);
//        return DateUtil.dateToSecond(startDT.getTime());
//    }
//
//    /**
//     * 【当前几号】
//     *
//     * @return
//     * @time:2016年1月25日 下午2:43:46
//     * @author 涂鑫
//     * @version
//     */
//    public static int getNowDay() {
//        Calendar now = Calendar.getInstance();
//        return now.get(Calendar.DAY_OF_MONTH);
//    }
//
//    /**
//     * 当天24小时中的几时
//     *
//     * @return
//     */
//    public static int getNowHour() {
//        Calendar now = Calendar.getInstance();
//        return now.get(Calendar.HOUR_OF_DAY);
//    }
//
//    /**
//     * 【增加减少天数】
//     *
//     * @param date 时间
//     * @param num  天数 (+1 -1)
//     * @return
//     * @time:2015年11月7日 下午2:25:08
//     * @author 涂鑫
//     * @version
//     */
//    public static Date addDay(Date date, int num) {
//        Calendar startDT = Calendar.getInstance();
//        startDT.setTime(date);
//        startDT.add(Calendar.DAY_OF_MONTH, num);
//        return startDT.getTime();
//    }
//
//    /**
//     * 【格式化日期】
//     *
//     * @param times
//     * @return
//     * @time:2015年12月15日 下午7:51:04
//     * @author 涂鑫
//     * @version
//     */
//    public static String formatDate(long times, String simpleDateFormat) {
//        SimpleDateFormat sdf = new SimpleDateFormat(simpleDateFormat);
//        return sdf.format(new Date(times));
//    }
//
//    /**
//     * @param date
//     * @return
//     * @description:得到一天的开始时间和结束时间
//     * @author:涂鑫
//     * @return:Map
//     */
//    public static Map<String, Long> getOneDayStartAndEndTime(Date date) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        Date start = calendar.getTime();
//        calendar.add(Calendar.DAY_OF_MONTH, 1);
//        calendar.add(Calendar.SECOND, -1);
//        Date end = calendar.getTime();
//        Map<String, Long> map = new HashMap<String, Long>();
//        map.put("start", start.getTime());
//        map.put("end", end.getTime());
//        return map;
//    }
//
//    /**
//     * @description:得到当前时间的上一个月的今天 0点0分0秒
//     * @author:聂恒
//     * @return:long
//     */
//    public static Long getDateToMonthFirst() {
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);// 获取年份
//        int month = cal.get(Calendar.MONTH);// 获取月份
//        int day = cal.get(Calendar.DATE);// 获取日
//        String t = year + "" + month + "" + day;
//        Long date = getStrDataTimes(t);
//        Map<String, Long> map = getOneDayStartAndEndTime(new Date(date));
//        return map.get("start");
//    }
//
//    /**
//     * 【判断当天是否在范围时间内】
//     *
//     * @param startDate
//     * @param endDate
//     * @return
//     * @time:2016年1月30日 下午3:33:55
//     * @author 涂鑫
//     * @version
//     */
//    public static boolean isDateRange(Date startDate, Date endDate) {
//        Date nowDate = new Date();// 现在日期
//        if (nowDate.after(startDate) && nowDate.before(endDate)) {
//            // 在开始日期之后 结束日期之前说明在范围内
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 判断指定時間是否在范围时间内
//     *
//     * @param startDate 开始时间
//     * @param endDate   结束时间
//     * @param nowDate   指定时间
//     * @return
//     */
//    public static boolean isTimeRange(Date startDate, Date endDate, Date nowDate) {
//        if (nowDate.after(startDate) && nowDate.before(endDate)) {
//            // 在开始日期之后 结束日期之前说明在范围内
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 将日期格式化 如 2016-01 格式成 2016-1
//     *
//     * @param str
//     * @return
//     */
//    public static String formatDateQueryMonth(String str) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M");
//        try {
//            return sdf.format(sdf.parse(str));
//        } catch (ParseException e) {
//            return "";
//        }
//    }
//
//    /**
//     * 将日期格式化 如 2016-01-05 格式成 2016-1-5
//     *
//     * @param str
//     * @return
//     */
//    public static String formatDateQueryDay(String str) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            return sdf.format(sdf.parse(str));
//        } catch (ParseException e) {
//            return "";
//        }
//    }
//
//    /**
//     * 获取指定日期的开始时间
//     *
//     * @param milli
//     * @return
//     */
//    public static long getStartOfTheDay(long milli) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(milli);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        return calendar.getTimeInMillis();
//    }
//
//    public static String getNowTOString() {
//        Date d = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String dateNowStr = sdf.format(d);
//        System.out.println("格式化后的日期：" + dateNowStr);
//        return dateNowStr;
//    }
//
//    /**
//     * 两个时间之间相差距离多少天
//     *
//     * @param str1 时间参数 1：
//     * @param str2 时间参数 2：
//     * @return 相差天数
//     */
//    public static long getDistanceDays(String str1, String str2) {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Date one;
//        Date two;
//        long days = 0;
//        try {
//            one = df.parse(str1);
//            two = df.parse(str2);
//            long time1 = one.getTime();
//            long time2 = two.getTime();
//            long diff;
//            if (time1 < time2) {
//                diff = time2 - time1;
//            } else {
//                diff = time1 - time2;
//            }
//            days = diff / (1000 * 60 * 60 * 24);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return days;
//    }
//
//    /**
//     * 获取当前的时间戳
//     *
//     * @return
//     */
//    public static Timestamp getNowTime() {
//        return new Timestamp(System.currentTimeMillis());
//    }
//
//    public static String[] getLast12Months() {
//
//        String[] last12Months = new String[12];
//
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1); //要先+1,才能把本月的算进去</span>
//        for (int i = 0; i < 12; i++) {
//            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1); //逐次往前推1个月
//            last12Months[11 - i] = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1);
//        }
//
//        return last12Months;
//    }
//
//    /**
//     * 根据年龄算生日
//     *
//     * @param age
//     * @return
//     */
//    public static String getBirthday(Integer age) {
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar calendar = Calendar.getInstance();
//        if (age < 0) {
//            return sdf.format(new Date()).toString();
//        }
//        calendar.setTime(new Date());
//        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - age);
//        String format = sdf.format(calendar.getTime());
//        return format;
//    }
//
//
//    /**
//     * 时间戳格式化   yyyy-MM
//     *
//     * @param time
//     * @return
//     */
//    public static String TimestampToString(Timestamp time) {
//        String tsStr = "";
//        DateFormat sdf = new SimpleDateFormat("yyyy-MM");
//        try {
//            tsStr = sdf.format(time);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return tsStr;
//    }
//
//    /**
//     * @param str1 年月日
//     * @param str2 时分   的拼接
//     * @return
//     */
//    public static Date getTimeYYmmss(String str1, String str2) {
//
//        if (BeanUtils.isNullOrEmpty(str1) || BeanUtils.isNullOrEmpty(str2)) {
//            return null;
//        }
//        String date = str1 + " " + str2;
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        try {
//            return df.parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    /**
//     * 获取近n个月 2018-03
//     *
//     * @return
//     */
//    public static List<String> getMonth12(int num) {
//
//        List<String> list = new ArrayList();
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
//
//        for (int i = 0; i < 12; i++) {
//            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1); //逐次往前推1个月
//            String s = (cal.get(Calendar.MONTH) + 1) + "";
//            if (s.length() < 2) {
//                s = "0" + s;
//            }
//            list.add(cal.get(Calendar.YEAR) + "-" + s);
//        }
//        return list;
//    }
//
//
//}
