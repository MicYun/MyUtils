package com.run.utils;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;

import com.run.utils.config.GlobalConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by MicYun on 2018/7/9.
 */
public class TimeUtils {
    public static final int MILLIS_OF_HOUR = 60 * 60 * 1000;
    public static final int MILLIS_OF_DAY = 24 * MILLIS_OF_HOUR;
    public static final int DAYS_OF_WEEK = 7;


    public static long getCurrentTime() {
        return Calendar.getInstance().getTime().getTime();
    }

    public static long getTodayTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime().getTime();
    }

    public static int getDaysInMonth(int month, int year) {
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.MARCH:
            case Calendar.MAY:
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.OCTOBER:
            case Calendar.DECEMBER:
                return 31;
            case Calendar.APRIL:
            case Calendar.JUNE:
            case Calendar.SEPTEMBER:
            case Calendar.NOVEMBER:
                return 30;
            case Calendar.FEBRUARY:
                return isLeapYear(year) ? 29 : 28;
            default:
                throw new IllegalArgumentException("Invalid Month");
        }
    }

    public static boolean isLeapYear(int year) {
        boolean isLeapYear = false;
        if (year % 400 == 0) {
            isLeapYear = true;
        } else if (year % 4 == 0 && year % 100 != 0) {
            isLeapYear = true;
        }
        return isLeapYear;
    }

    public static boolean isSameYear(Calendar day1, Calendar day2) {
        if (day1 == null || day2 == null) {
            return false;
        }
        return day1.get(Calendar.YEAR) == day2.get(Calendar.YEAR);
    }

    public static boolean isSameMonth(Calendar day1, Calendar day2) {
        if (day1 == null || day2 == null) {
            return false;
        }
        return day1.get(Calendar.YEAR) == day2.get(Calendar.YEAR)
                && day1.get(Calendar.MONTH) == day2.get(Calendar.MONTH);
    }

    public static boolean isSameDay(Calendar day1, Calendar day2) {
        if (day1 == null || day2 == null) {
            return false;
        }
        return day1.get(Calendar.YEAR) == day2.get(Calendar.YEAR)
                && day1.get(Calendar.MONTH) == day2.get(Calendar.MONTH)
                && day1.get(Calendar.DAY_OF_MONTH) == day2.get(Calendar.DAY_OF_MONTH);
    }

    public static boolean isSameDay(Calendar day1, int year, int monthOfYear, int dayOfMonth) {
        if (day1 == null) {
            return false;
        }
        return day1.get(Calendar.YEAR) == year
                && day1.get(Calendar.MONTH) == monthOfYear
                && day1.get(Calendar.DAY_OF_MONTH) == dayOfMonth;
    }

    public static boolean isBelowHour(long timeMillis1, long timeMillis2) {
        long rest = timeMillis1 - timeMillis2;
        if (Math.abs(rest) <= MILLIS_OF_HOUR) {
            return true;
        }
        return false;
    }


    public static String getDayOfWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return getResources().getStringArray(R.array.day_of_week)[dayOfWeek - 1];
    }

    public static String getDayOfWeek2(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return getResources().getStringArray(R.array.day_of_week2)[dayOfWeek - 1];
    }

    private static String getDayOfWeek(int dayOfWeek) {
        return getResources().getStringArray(R.array.day_of_week)[dayOfWeek - 1];
    }

    public static String getDateHHMM(long time) {
        return new SimpleDateFormat(getResources().getString(R.string.date_format_hhmm), Locale.CHINA)
                .format(time);
    }

    public static String getDateMMDD(long time) {
        return new SimpleDateFormat(getResources().getString(R.string.date_format_mmdd), Locale.CHINA)
                .format(time);
    }

    public static String getDataMMDDHHMM(long time) {
        return new SimpleDateFormat(getResources().getString(R.string.date_format_mmddhhmm),
                Locale.CHINA).format(time);
    }

    public static String getDateAll(long time) {
        return new SimpleDateFormat(getResources().getString(R.string.date_format_yyyymmdd),
                Locale.CHINA).format(time);
    }

    public static String getDateYYMMDDHHMM(long time) {
        return new SimpleDateFormat(getResources().getString(R.string.date_format_yyyymmddhhmm),
                Locale.CHINA).format(time);
    }

    public static String getDateYYMMDDHHMM2(long time) {
        return new SimpleDateFormat(getResources().getString(R.string.date_format_yyyymmddhhmm2),
                Locale.CHINA).format(time);
    }

    public static String getDateM(long time) {
        return new SimpleDateFormat(getResources().getString(R.string.date_format_m), Locale.CHINA)
                .format(time);
    }

    public static String getDateYYYYM(long time) {
        return new SimpleDateFormat(getResources().getString(R.string.date_format_yyyym), Locale.CHINA)
                .format(time);
    }

    public static String getmDateYYYYMMDD2(long time) {
        return new SimpleDateFormat(getResources().getString(R.string.date_format_yyyymmdd2),
                Locale.CHINA).format(time);
    }

    public static String getDateYYYYMMDD5(long time) {
        return new SimpleDateFormat(getResources().getString(R.string.date_format_yyyymmdd5),
                Locale.CHINA).format(time);
    }

    public static String getDataYYYYMMDDSS(long time) {
        return new SimpleDateFormat(getResources().getString(R.string.date_format_yyyymmddhhmmss),
                Locale.CHINA).format(time);
    }

    public static String getDataYYYYMMDDHHMMSS(long time) {
        return new SimpleDateFormat(getResources().getString(R.string.data_format_yyyymmdd_hhmmss),
                Locale.CHINA).format(time);
    }

    public static String getDataYYYYMMDDHHMMSS2(long time) {
        return new SimpleDateFormat(getResources().getString(R.string.data_format_yyyymmdd_hhmmss2),
                Locale.CHINA).format(time);
    }

    public static String getmDateYYYYMMDD3(long time) {
        return new SimpleDateFormat(getResources().getString(R.string.date_format_yyyymmdd3),
                Locale.CHINA).format(time);
    }

    public static String getmDateYYYYMMDD4(long time) {
        return new SimpleDateFormat(getResources().getString(R.string.date_format_yyyymmdd4),
                Locale.CHINA).format(time);
    }

    public static String getDateYYYYMMDD6(long time) {
        return new SimpleDateFormat(getResources().getString(R.string.date_format_yyyymmdd6),
                Locale.CHINA).format(time);
    }

    public static String getDateMMDD2(long time) {
        return new SimpleDateFormat(getResources().getString(R.string.date_format_mmdd1),
                Locale.CHINA).format(time);
    }

    public static String getDateMMSS(long time) {
        return new SimpleDateFormat(getResources().getString(R.string.date_mm_ss),
                Locale.CHINA).format(time);
    }

    public static Calendar birthdayToCalendar(String birthday) {
        final Calendar c = Calendar.getInstance();
        if (!TextUtils.isEmpty(birthday)) {
            final String[] birthArray = birthday.split("-");
            if (birthArray.length == 3) {
                c.set(
                        Integer.valueOf(birthArray[0]),
                        Integer.valueOf(birthArray[1]) - 1,
                        Integer.valueOf(birthArray[2]));
                return c;
            }
        }
        return null;
    }

    private static Resources getResources() {
        return GlobalConfig.getAppContext().getResources();
    }

    public static long parseStringToLong(String dateStr, String pattern) {
        if (TextUtils.isEmpty(dateStr) || TextUtils.isEmpty(pattern)) {
            return 0;
        }
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        try {
            Date date = formater.parse(dateStr);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取倒计时字符串
     *
     * @param time
     * @return
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static String getCountTime(long time) {
        if (time < 0) {
            return "";
        } else {
            TimeUnit.DAYS.toSeconds(1);
            StringBuffer countTimeString = new StringBuffer();
            if (time > TimeUnit.DAYS.toMillis(1)) {
                countTimeString.append(time / TimeUnit.DAYS.toMillis(1)).append(
                        StringUtil.getString(R.string.day));
            }
            parseTime(time, TimeUnit.DAYS.toMillis(1), TimeUnit.HOURS.toMillis(1), countTimeString);
            parseTime(time, TimeUnit.HOURS.toMillis(1), TimeUnit.MINUTES.toMillis(1), countTimeString);
            parseTime(time, TimeUnit.MINUTES.toMillis(1), TimeUnit.SECONDS.toMillis(1), countTimeString);
            return countTimeString.substring(0, countTimeString.length() - 1);
        }
    }

    private static void parseTime(long time, long longer, long shorter, StringBuffer countTimeString) {
        if (time > shorter) {
            long val = (time % longer) / shorter;
            if (val < 10) {
                countTimeString.append(StringUtil.getString(R.string.zero));
            }
            countTimeString.append(val).append(StringUtil.getString(R.string.colon));
        } else {
            countTimeString.append(StringUtil.getString(R.string.zero_zero)).append(
                    StringUtil.getString(R.string.colon));
        }
    }
}
