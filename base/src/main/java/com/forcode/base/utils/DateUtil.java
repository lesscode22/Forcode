package com.forcode.base.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

/**
 * LocalDate: 日期
 * LocalTime: 时间
 * LocalDateTime: 日期时间
 * YearMonth: 年月
 * MonthDay: 月日, 可以用来检查每年的固定的日期
 * Period: 计算两日期间的月数与天数
 * Duration: 计算时间间隔
 * Instant: 时间戳信息
 * DateTimeFormatter: 自定义格式化
 */
public class DateUtil {

    /**
     * 默认格式
     */
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";
    private static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String today() {
        return today(DEFAULT_DATE_PATTERN);
    }

    public static String today(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern, Locale.CHINA));
    }

    public static long currentSecond() {
        return Instant.now().getEpochSecond();
    }

    /**
     * 默认转为 yyyy-MM-dd 格式
     * @param temporal 可接收入参 LocalDate、LocalDateTime, 其他须自定义转换格式
     * @return String
     */
    public static String convertToString(TemporalAccessor temporal) {
        return convertToString(temporal, DEFAULT_DATE_PATTERN);
    }

    public static String convertToString(TemporalAccessor temporal, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.CHINA);
        return formatter.format(temporal);
    }

    public static LocalDate convertToDate(String text) {
        return convertToDate(text, DEFAULT_DATE_PATTERN);
    }

    public static LocalDate convertToDate(String text, String pattern) {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern(pattern, Locale.CHINA));
    }

    public static LocalTime convertToTime(String text) {
        return convertToTime(text, DEFAULT_TIME_PATTERN);
    }

    public static LocalTime convertToTime(String text, String pattern) {
        return LocalTime.parse(text, DateTimeFormatter.ofPattern(pattern, Locale.CHINA));
    }

    public static LocalDateTime convertToDateTime(String text) {
        return convertToDateTime(text, DEFAULT_DATETIME_PATTERN);
    }

    public static LocalDateTime convertToDateTime(String text, String pattern) {
        return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(pattern, Locale.CHINA));
    }

    public static LocalDateTime startOfDay() {
        return startOfDay(LocalDateTime.now());
    }

    public static LocalDateTime startOfDay(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MIN);
    }

    public static LocalDateTime endOfDay() {
        return endOfDay(LocalDateTime.now());
    }

    public static LocalDateTime endOfDay(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MAX);
    }

    public static LocalDateTime startOfWeek() {
        TemporalAdjuster weekAdjuster =
                TemporalAdjusters.ofDateAdjuster(localDate ->
                        localDate.minusDays(
                                localDate.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue()));
        return LocalDateTime.now().with(weekAdjuster);
    }

    public static LocalDateTime endOfWeek() {
        TemporalAdjuster weekAdjuster =
                TemporalAdjusters.ofDateAdjuster(localDate ->
                        localDate.plusDays(
                                DayOfWeek.SUNDAY.getValue() - localDate.getDayOfWeek().getValue()));
        return LocalDateTime.now().with(weekAdjuster);
    }

    public static LocalDateTime startOfMonth() {
        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.of(now.getYear(), now.getMonth(), 1);
        return LocalDateTime.of(date, LocalTime.MIN);
    }

    public static LocalDateTime endOfMonth() {
        return LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth());
    }
}
