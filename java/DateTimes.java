package com.vinid.up.util;

import org.apache.logging.log4j.util.Strings;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormat;

import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

public final class DateTimes {
    //
    public static final long DAY_IN_MILLIS = 86400000L;

    private static final ISOChronology CHRONOLOGY = ISOChronology.getInstance();

    public static interface DateTimeProvider {
        long now();
    }

    private static final AtomicReference<DateTimeProvider> PROVIDER = new AtomicReference<DateTimeProvider>();

    public static final DateTimeProvider getProvider() {
        return PROVIDER.get();
    }

    static {
        PROVIDER.set(new DateTimeProvider() {
            @Override
            public long now() {
                return System.currentTimeMillis();
            }
        });
    }

    public static Date currentTime() {
        return new Date(now());
    }

    public static Date currentDate() {
        return date(now());
    }

    public static final Date date(final long millis) {
        final int offset = getTimeZoneOffset(millis);
        long t = millis - ((t = (millis + offset) % DAY_IN_MILLIS) < 0 ? DAY_IN_MILLIS + t : t);
        t = t + (offset - getTimeZoneOffset(t));
        return new Date(t);
    }

    public static int getTimeZoneOffset(long millis) {
        return TimeZone.getDefault().getOffset(millis);
    }

    public static long now() {
        return getProvider().now();
    }

    public static long toMillis(long nanos) {
        return MILLISECONDS.convert(nanos, NANOSECONDS);
    }

    public static long currentTimeMillis() {
        return getProvider().now();
    }

    public static String format(String pattern, long millis) {
        return DateTimeFormat.forPattern(pattern).print(millis);
    }

    public static String format(String pattern, Date date, String defaultValue) {
        if (date == null) return defaultValue;
        return format(pattern, date.getTime());
    }

    public static long parse(final String pattern, String date) {
        return DateTimeFormat.forPattern(pattern).parseMillis(date);
    }

    public static Date parseDate(final String pattern, String date) {
        return Strings.isEmpty(date) ? null : date(parse(pattern, date));
    }

    public static long addDays(long millis, int delta) {
        return CHRONOLOGY.days().add(millis, delta);
    }

    public static long addHours(long millis, int delta) {
        return CHRONOLOGY.hours().add(millis, delta);
    }

    public static long addYears(long millis, int delta) {
        return CHRONOLOGY.years().add(millis, delta);
    }

    public static long addMonths(long millis, int delta) {
        return CHRONOLOGY.months().add(millis, delta);
    }

    public static long addMinutes(long millis, int delta) {
        return CHRONOLOGY.minutes().add(millis, delta);
    }

    public static long addSeconds(long millis, int delta) {
        return CHRONOLOGY.seconds().add(millis, delta);
    }

    public static long addDays(long millis, int delta, DateTimeZone timezone) {
        return ISOChronology.getInstance(timezone).days().add(millis, delta);
    }

    public static long addHours(long millis, int delta, DateTimeZone timezone) {
        return ISOChronology.getInstance(timezone).hours().add(millis, delta);
    }

    public static long addYears(long millis, int delta, DateTimeZone timezone) {
        return ISOChronology.getInstance(timezone).years().add(millis, delta);
    }

    public static long addMonths(long millis, int delta, DateTimeZone timezone) {
        return ISOChronology.getInstance(timezone).months().add(millis, delta);
    }

    public static long addMinutes(long millis, int delta, DateTimeZone timezone) {
        return ISOChronology.getInstance(timezone).minutes().add(millis, delta);
    }

    public static long addSeconds(long millis, int delta, DateTimeZone timezone) {
        return ISOChronology.getInstance(timezone).seconds().add(millis, delta);
    }

    private static boolean isValid(final long millis) {
        return millis > 0L;
    }

    public static boolean isValid(String date, String pattern) {
        return date != null && isValid(parseQuietly(date, pattern));
    }

    private static long parseQuietly(final String date, final String pattern) {
        if (Strings.isEmpty(date)) return 0L;
        try {
            return parse(pattern, date);
        } catch (Throwable t) {
            return 0L;
        }
    }

    public static Long toEpochSecond(Date value) {
        if (value == null) return null;
        return value.toInstant().getEpochSecond();
    }

    public static Long toEpochSecond(String pattern, String value) {
        if (value == null || pattern == null) return null;
        return toEpochSecond(new Date(parse(pattern, value)));
    }

    public static Date toDate(Long epochoSecond) {
        return epochoSecond == null ? null : Date.from(Instant.ofEpochSecond(epochoSecond));
    }

    public static Date toDateMillis(Long millis) {
        return millis == null ? null : Date.from(Instant.ofEpochMilli(millis));
    }

    public static final int getDayOfWeek(final long millis) {
        return CHRONOLOGY.dayOfWeek().get(millis) % 7 + 1;
    }


    public static String convert(String d, String patternIn, String patternOut) {
        if (Strings.isEmpty(d)) return null;
        return DateTimes.format(patternOut, DateTimes.parse(patternIn, d));
    }

}
