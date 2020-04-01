package com.vinid.idjupviecs.util;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.annotation.Nullable;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public final class Strings {


    public static final String SPACE = " ";

    public static final String EMPTY = "";

    public static final String LF = "\n";

    public static final String CR = "\r";

    public static final int INDEX_NOT_FOUND = -1;

    private static final int PAD_LIMIT = 8192;

    public static String add(final String... s) {
        if (s == null || s.length == 0) return EMPTY;
        int len = s.length;
        StringBuilder r = new StringBuilder(128);
        for (int i = 0; i < len; i++) if (s[i] != null) r.append(s[i]);
        return r.toString();
    }

    public static String add(String a, String b) {
        StringBuilder r = new StringBuilder((length(a) + length(b)));
        if (a != null) {
            r.append(a);
        }
        if (b != null) {
            r.append(b);
        }
        return r.toString();
    }

    public static String add(String a, String b, String c) {
        final int n = length(a) + length(b) + length(c);
        final StringBuilder r = new StringBuilder((n)); /* capacity */
        if (a != null) {
            r.append(a);
        }
        if (b != null) {
            r.append(b);
        }
        if (c != null) {
            r.append(c);
        }
        return r.toString();
    }

    public static String add(String a, String b, String c, String d) {
        final int n = length(a) + length(b) + length(c) + length(d);
        final StringBuilder r = new StringBuilder((n)); /* capacity */
        if (a != null) {
            r.append(a);
        }
        if (b != null) {
            r.append(b);
        }
        if (c != null) {
            r.append(c);
        }
        if (d != null) {
            r.append(d);
        }
        return r.toString();
    }

    public static final String lappend(int src, int length, char padding) {
        return lappend(String.valueOf(src), length, padding);
    }

    public static final String lappend(String src, int length, char padding) {
        //
        if (src != null && src.length() >= length) {
            return src;
        }

        //
        src = src == null ? EMPTY : src;
        final StringBuilder r = new StringBuilder(length);
        for (int i = src.length(); i < length; i++) {
            r.append(padding);
        }
        r.append(src);
        return r.toString();
    }

    /**
     * isEmpty(null)      = true
     * isEmpty("")        = true
     * isEmpty(" ")       = false
     * isEmpty("bob")     = false
     * isEmpty("  bob  ") = false
     */
    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    /**
     * isNotEmpty(null)      = false
     * isNotEmpty("")        = false
     * isNotEmpty(" ")       = true
     * isNotEmpty("bob")     = true
     * isNotEmpty("  bob  ") = true
     */
    public static boolean isNotEmpty(final String cs) {
        return !isEmpty(cs);
    }

    /**
     * isAnyEmpty((String) null)    = true
     * isAnyEmpty((String[]) null)  = false
     * isAnyEmpty(null, "foo")      = true
     * isAnyEmpty("", "bar")        = true
     * isAnyEmpty("bob", "")        = true
     * isAnyEmpty("  bob  ", null)  = true
     * isAnyEmpty(" ", "bar")       = false
     * isAnyEmpty("foo", "bar")     = false
     * isAnyEmpty(new String[]{})   = false
     * isAnyEmpty(new String[]{""}) = true
     */
    public static boolean isAnyEmpty(final String... s) {
        if (s == null || Array.getLength(s) == 0) {
            return false;
        }
        for (final String cs : s) {
            if (isEmpty(cs)) {
                return true;
            }
        }
        return false;
    }

    /**
     * isNoneEmpty((String) null)    = false
     * isNoneEmpty((String[]) null)  = true
     * isNoneEmpty(null, "foo")      = false
     * isNoneEmpty("", "bar")        = false
     * isNoneEmpty("bob", "")        = false
     * isNoneEmpty("  bob  ", null)  = false
     * isNoneEmpty(new String[] {})  = true
     * isNoneEmpty(new String[]{""}) = false
     * isNoneEmpty(" ", "bar")       = true
     * isNoneEmpty("foo", "bar")     = true
     */
    public static boolean isNoneEmpty(final String... s) {
        return !isAnyEmpty(s);
    }

    /**
     * isAllEmpty(null)             = true
     * isAllEmpty(null, "")         = true
     * isAllEmpty(new String[] {})  = true
     * isAllEmpty(null, "foo")      = false
     * isAllEmpty("", "bar")        = false
     * isAllEmpty("bob", "")        = false
     * isAllEmpty("  bob  ", null)  = false
     * isAllEmpty(" ", "bar")       = false
     * isAllEmpty("foo", "bar")     = false
     */
    public static boolean isAllEmpty(final String... s) {
        if (s == null || Array.getLength(s) == 0) {
            return false;
        }
        for (final String cs : s) {
            if (isNotEmpty(cs)) {
                return false;
            }
        }
        return true;
    }

    /**
     * isBlank(null)      = true
     * isBlank("")        = true
     * isBlank(" ")       = true
     * isBlank("bob")     = false
     * isBlank("  bob  ") = false
     */
    public static boolean isBlank(final String cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * isNotBlank(null)      = false
     * isNotBlank("")        = false
     * isNotBlank(" ")       = false
     * isNotBlank("bob")     = true
     * isNotBlank("  bob  ") = true
     */
    public static boolean isNotBlank(final String cs) {
        return !isBlank(cs);
    }

    /**
     * isAnyBlank((String) null)    = true
     * isAnyBlank((String[]) null)  = false
     * isAnyBlank(null, "foo")      = true
     * isAnyBlank(null, null)       = true
     * isAnyBlank("", "bar")        = true
     * isAnyBlank("bob", "")        = true
     * isAnyBlank("  bob  ", null)  = true
     * isAnyBlank(" ", "bar")       = true
     * isAnyBlank(new String[] {})  = false
     * isAnyBlank(new String[]{""}) = true
     * isAnyBlank("foo", "bar")     = false
     */
    public static boolean isAnyBlank(final String... s) {
        if (s == null || Array.getLength(s) == 0) {
            return false;
        }
        for (final String cs : s) {
            if (isBlank(cs)) {
                return true;
            }
        }
        return false;
    }

    /**
     * isNoneBlank((String) null)    = false
     * isNoneBlank((String[]) null)  = true
     * isNoneBlank(null, "foo")      = false
     * isNoneBlank(null, null)       = false
     * isNoneBlank("", "bar")        = false
     * isNoneBlank("bob", "")        = false
     * isNoneBlank("  bob  ", null)  = false
     * isNoneBlank(" ", "bar")       = false
     * isNoneBlank(new String[] {})  = true
     * isNoneBlank(new String[]{""}) = false
     * isNoneBlank("foo", "bar")     = true
     */
    public static boolean isNoneBlank(final String... s) {
        return !isAnyBlank(s);
    }

    /**
     * isAllBlank(null)             = true
     * isAllBlank(null, "foo")      = false
     * isAllBlank(null, null)       = true
     * isAllBlank("", "bar")        = false
     * isAllBlank("bob", "")        = false
     * isAllBlank("  bob  ", null)  = false
     * isAllBlank(" ", "bar")       = false
     * isAllBlank("foo", "bar")     = false
     * isAllBlank(new String[] {})  = true
     */
    public static boolean isAllBlank(final String... s) {
        if (s == null || Array.getLength(s) == 0) {
            return false;
        }
        for (final String cs : s) {
            if (isNotBlank(cs)) {
                return false;
            }
        }
        return true;
    }

    public static String replaces(String s, String r, String x) {
        if (s == null) return null;
        Pattern p = compile(r);
        return p.matcher(s).replaceAll(x);
    }

    public static boolean isEquals(final String s1, final String s2) {
        return isEquals(s1, s2, false);
    }

    public static boolean isEquals(String s1, String s2, boolean ic) {
        if (s1 == s2) return true;
        else if (s1 == null && s2 == null) return true;
        else if (s1 == null || s2 == null) return false;
        else return ic ? s1.equalsIgnoreCase(s2) : s1.equals(s2);
    }

    /**
     * Strings.trim(null)          = null
     * Strings.trim("")            = ""
     * Strings.trim("     ")       = ""
     * Strings.trim("abc")         = "abc"
     * Strings.trim("    abc    ") = "abc"
     */
    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }

    /**
     * Strings.trimToEmpty(null)          = ""
     * Strings.trimToEmpty("")            = ""
     * Strings.trimToEmpty("     ")       = ""
     * Strings.trimToEmpty("abc")         = "abc"
     * Strings.trimToEmpty("    abc    ") = "abc"
     */
    public static String trimToEmpty(final String str) {
        return str == null ? EMPTY : str.trim();
    }

    /**
     * Strings.trimToNull(null)          = null
     * Strings.trimToNull("")            = null
     * Strings.trimToNull("     ")       = null
     * Strings.trimToNull("abc")         = "abc"
     * Strings.trimToNull("    abc    ") = "abc"
     */
    public static String trimToNull(final String str) {
        final String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    /**
     * Strings.truncate(null, 0)       = null
     * Strings.truncate(null, 2)       = null
     * Strings.truncate("", 4)         = ""
     * Strings.truncate("abcdefg", 4)  = "abcd"
     * Strings.truncate("abcdefg", 6)  = "abcdef"
     * Strings.truncate("abcdefg", 7)  = "abcdefg"
     * Strings.truncate("abcdefg", 8)  = "abcdefg"
     * Strings.truncate("abcdefg", -1) = throws an IllegalArgumentException
     */
    public static String truncate(final String str, final int maxWidth) {
        return truncate(str, 0, maxWidth);
    }

    /**
     * Strings.truncate(null, 0, 0) = null
     * Strings.truncate(null, 2, 4) = null
     * Strings.truncate("", 0, 10) = ""
     * Strings.truncate("", 2, 10) = ""
     * Strings.truncate("abcdefghij", 0, 3) = "abc"
     * Strings.truncate("abcdefghij", 5, 6) = "fghij"
     * Strings.truncate("raspberry peach", 10, 15) = "peach"
     * Strings.truncate("abcdefghijklmno", 0, 10) = "abcdefghij"
     * Strings.truncate("abcdefghijklmno", -1, 10) = throws an IllegalArgumentException
     * Strings.truncate("abcdefghijklmno", Integer.MIN_VALUE, 10) = "abcdefghij"
     * Strings.truncate("abcdefghijklmno", Integer.MIN_VALUE, Integer.MAX_VALUE) = "abcdefghijklmno"
     * Strings.truncate("abcdefghijklmno", 0, Integer.MAX_VALUE) = "abcdefghijklmno"
     * Strings.truncate("abcdefghijklmno", 1, 10) = "bcdefghijk"
     * Strings.truncate("abcdefghijklmno", 2, 10) = "cdefghijkl"
     * Strings.truncate("abcdefghijklmno", 3, 10) = "defghijklm"
     * Strings.truncate("abcdefghijklmno", 4, 10) = "efghijklmn"
     * Strings.truncate("abcdefghijklmno", 5, 10) = "fghijklmno"
     * Strings.truncate("abcdefghijklmno", 5, 5) = "fghij"
     * Strings.truncate("abcdefghijklmno", 5, 3) = "fgh"
     * Strings.truncate("abcdefghijklmno", 10, 3) = "klm"
     * Strings.truncate("abcdefghijklmno", 10, Integer.MAX_VALUE) = "klmno"
     * Strings.truncate("abcdefghijklmno", 13, 1) = "n"
     * Strings.truncate("abcdefghijklmno", 13, Integer.MAX_VALUE) = "no"
     * Strings.truncate("abcdefghijklmno", 14, 1) = "o"
     * Strings.truncate("abcdefghijklmno", 14, Integer.MAX_VALUE) = "o"
     * Strings.truncate("abcdefghijklmno", 15, 1) = ""
     * Strings.truncate("abcdefghijklmno", 15, Integer.MAX_VALUE) = ""
     * Strings.truncate("abcdefghijklmno", Integer.MAX_VALUE, Integer.MAX_VALUE) = ""
     * Strings.truncate("abcdefghij", 3, -1) = throws an IllegalArgumentException
     * Strings.truncate("abcdefghij", -2, 4) = throws an IllegalArgumentException
     */
    public static String truncate(final String str, final int offset, final int maxWidth) {
        if (offset < 0) {
            throw new IllegalArgumentException("offset cannot be negative");
        }
        if (maxWidth < 0) {
            throw new IllegalArgumentException("maxWith cannot be negative");
        }
        if (str == null) {
            return null;
        }
        if (offset > str.length()) {
            return EMPTY;
        }
        if (str.length() > maxWidth) {
            final int ix = Math.min(offset + maxWidth, str.length());
            return str.substring(offset, ix);
        }
        return str.substring(offset);
    }

    /**
     * Strings.strip(null)     = null
     * Strings.strip("")       = ""
     * Strings.strip("   ")    = ""
     * Strings.strip("abc")    = "abc"
     * Strings.strip("  abc")  = "abc"
     * Strings.strip("abc  ")  = "abc"
     * Strings.strip(" abc ")  = "abc"
     * Strings.strip(" ab c ") = "ab c"
     */
    public static String strip(final String str) {
        return strip(str, null);
    }

    /**
     * <p>Strips whitespace from the start and end of a String  returning
     * {@code null} if the String is empty ("") after the strip.</p>
     *
     * <p>This is similar to {@link #trimToNull(String)} but removes whitespace.
     * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * Strings.stripToNull(null)     = null
     * Strings.stripToNull("")       = null
     * Strings.stripToNull("   ")    = null
     * Strings.stripToNull("abc")    = "abc"
     * Strings.stripToNull("  abc")  = "abc"
     * Strings.stripToNull("abc  ")  = "abc"
     * Strings.stripToNull(" abc ")  = "abc"
     * Strings.stripToNull(" ab c ") = "ab c"
     * </pre>
     *
     * @param str the String to be stripped, may be null
     * @return the stripped String,
     * {@code null} if whitespace, empty or null String input
     * @since 2.0
     */
    public static String stripToNull(String str) {
        if (str == null) {
            return null;
        }
        str = strip(str, null);
        return str.isEmpty() ? null : str;
    }

    /**
     * Strings.stripToEmpty(null)     = ""
     * Strings.stripToEmpty("")       = ""
     * Strings.stripToEmpty("   ")    = ""
     * Strings.stripToEmpty("abc")    = "abc"
     * Strings.stripToEmpty("  abc")  = "abc"
     * Strings.stripToEmpty("abc  ")  = "abc"
     * Strings.stripToEmpty(" abc ")  = "abc"
     * Strings.stripToEmpty(" ab c ") = "ab c"
     */
    public static String stripToEmpty(final String str) {
        return str == null ? EMPTY : strip(str, null);
    }

    /**
     * Strings.strip(null, *)          = null
     * Strings.strip("", *)            = ""
     * Strings.strip("abc", null)      = "abc"
     * Strings.strip("  abc", null)    = "abc"
     * Strings.strip("abc  ", null)    = "abc"
     * Strings.strip(" abc ", null)    = "abc"
     * Strings.strip("  abcyx", "xyz") = "  abc"
     */
    public static String strip(String str, final String stripChars) {
        if (isEmpty(str)) {
            return str;
        }
        str = stripStart(str, stripChars);
        return stripEnd(str, stripChars);
    }

    /**
     * Strings.stripStart(null, *)          = null
     * Strings.stripStart("", *)            = ""
     * Strings.stripStart("abc", "")        = "abc"
     * Strings.stripStart("abc", null)      = "abc"
     * Strings.stripStart("  abc", null)    = "abc"
     * Strings.stripStart("abc  ", null)    = "abc  "
     * Strings.stripStart(" abc ", null)    = "abc "
     * Strings.stripStart("yxabc  ", "xyz") = "abc  "
     */
    public static String stripStart(final String str, final String stripChars) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        int start = 0;
        if (stripChars == null) {
            while (start != strLen && Character.isWhitespace(str.charAt(start))) {
                start++;
            }
        } else if (stripChars.isEmpty()) {
            return str;
        } else {
            while (start != strLen && stripChars.indexOf(str.charAt(start)) != INDEX_NOT_FOUND) {
                start++;
            }
        }
        return str.substring(start);
    }

    /**
     * Strings.stripEnd(null, *)          = null
     * Strings.stripEnd("", *)            = ""
     * Strings.stripEnd("abc", "")        = "abc"
     * Strings.stripEnd("abc", null)      = "abc"
     * Strings.stripEnd("  abc", null)    = "  abc"
     * Strings.stripEnd("abc  ", null)    = "abc"
     * Strings.stripEnd(" abc ", null)    = " abc"
     * Strings.stripEnd("  abcyx", "xyz") = "  abc"
     * Strings.stripEnd("120.00", ".0")   = "12"
     */
    public static String stripEnd(final String str, final String stripChars) {
        int end;
        if (str == null || (end = str.length()) == 0) {
            return str;
        }

        if (stripChars == null) {
            while (end != 0 && Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        } else if (stripChars.isEmpty()) {
            return str;
        } else {
            while (end != 0 && stripChars.indexOf(str.charAt(end - 1)) != INDEX_NOT_FOUND) {
                end--;
            }
        }
        return str.substring(0, end);
    }

    // Contains
    //-----------------------------------------------------------------------

    /**
     * StringUtils.contains(null, *)    = false
     * StringUtils.contains("", *)      = false
     * StringUtils.contains("abc", 'a') = true
     * StringUtils.contains("abc", 'z') = false
     */
    public static boolean contains(final String s, final String c) {
        if (isEmpty(s)) {
            return false;
        }
        return s.contains(c);
    }

    public static ToStringBuilder build(Object obj) {
        return new ToStringBuilder(obj, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static ToStringBuilder build(Object obj, String toString) {
        return new ToStringBuilder(obj, ToStringStyle.SHORT_PREFIX_STYLE).appendToString(toString);
    }

    public static String buildEx(final Object obj) {
        return ToStringBuilder.reflectionToString(obj, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static Short toShort(@Nullable String v) {
        if (v == null) return null;
        try {
            return Short.valueOf(v);
        } catch (Throwable ignore) {
            return null;
        }
    }

    public static Short toShort(@Nullable String v, Short value) {
        if (v == null) return value;
        try {
            return Short.valueOf(v);
        } catch (Throwable ignore) {
            return value;
        }
    }

    public static Integer toInteger(@Nullable String v) {
        if (v == null) return null;
        try {
            return Integer.valueOf(v);
        } catch (Throwable ignore) {
            return null;
        }
    }

    public static Long toLong(@Nullable String v) {
        if (v == null) return null;
        try {
            return Long.valueOf(v);
        } catch (Throwable ignore) {
            return null;
        }
    }

    public static Integer toInteger(@Nullable String v, Integer value) {
        if (v == null) return value;
        try {
            return Integer.valueOf(v);
        } catch (Throwable ignore) {
            return value;
        }
    }

    public static BigDecimal toDecimal(String v) {
        if (v == null) return null;
        try {
            return new BigDecimal((v));
        } catch (Throwable ignore) {
            return null;
        }
    }

    public static BigDecimal toDecimalStripTrailingZero(String v) {
        if (v == null) return null;
        try {
            return new BigDecimal((v)).stripTrailingZeros();
        } catch (Throwable ignore) {
            return null;
        }
    }

    public static BigDecimal toDecimal(String v, BigDecimal value) {
        if (v == null) return value;
        try {
            return new BigDecimal(v);
        } catch (Throwable ignore) {
            return value;
        }
    }

    /**
     *
     */
    public static String lower(final String s) {
        return s == null ? null : s.toLowerCase();
    }

    public static int length(String s) {
        return s == null ? 0 : s.length();
    }


    public static String nullToEmpty(String s) {
        return s == null ? EMPTY : s;
    }

    public static String nullToEmpty(BigDecimal v) {
        return v == null ? EMPTY : v.toPlainString();
    }

    public static String decimal(BigDecimal v) {
        return v == null ? null : v.toPlainString();
    }

    private static final Pattern PUNCTUATION_SYMBOLS = Pattern.compile("[\\p{P}\\p{S}]");

    public static String replaceAllPunctuationAndSymbol(String s, String replacement) {
        return PUNCTUATION_SYMBOLS.matcher(s).replaceAll(replacement);
    }
}
