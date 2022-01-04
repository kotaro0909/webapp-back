package com.example.backed.libs;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;

public class DateUtils {

    public static String PROP_SQL_SYSDATE = "example.date-utils.get-now.sql-value";
    public static String PROP_TEST_DATE_VALUE = "example.date-utils.get-now.test-value";

    private DateUtils() {}

    /**
     * 対象文字列が日付の形式か判定する
     * @param value 判定対象文字列
     * @return 日付の形式の場合 ture
     */
    public static boolean isDate(String value) {
        try {
            DateUtils.toDate(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * システム日時を取得する
     * @return システム日時
     */
    public static Date getNow() {
        //プロファイルが「dev」かつプロパティファイルに固定の日時がセットされている場合
        if (AppProfile.getInstance().getProfile() == ActiveProfile.DEV) {
            String testValue = AppProfile.getInstance().getEnv(PROP_TEST_DATE_VALUE);
            if (testValue != null) {
                //UTのassert用に、プロパティファイルから固定値を取得する
                return DateUtils.toDate(testValue);
            }
        }
        //DB接続がある場合
        try {
            String sql = AppProfile.getInstance().getEnv(PROP_SQL_SYSDATE);
            if (sql != null) {
                //DBサーバの日時を取得するSQLがセットされている場合
                Date d = DatabaseUtils.getRowValue(sql, 1);
                return d;
            }
        } finally {
        }
        //上記のいずれにも該当しない場合はJavaのAPIから端末日時を取得する
        return new Date();
    }

    /**
     * toDateでサポートする文字列形式
     */
    public static final String[] SUPPORT_FORMAT = {
            "yyyy/MM/dd HH:mm:ss.SSS"
            , "yyyy/MM/dd HH:mm:ss"
            , "yyyy/M/d H:m:s"
            , "yyyy-MM-dd HH:mm:ss"
            , "yyyy-M-d H:m:s"
            , "yyyy/MM/dd"
            , "yyyy/M/d"
            , "yyyy-MM-dd"
            , "yyyy-M-d"
    };

    /**
     * 日付文字列をDate型に変換する.<br>
     *     サポートする文字列の形式は以下の種類です。
     * <li>yyyy/MM/dd HH:mm:ss</li>
     * <li>yyyy/M/d H:m:s</li>
     * <li>yyyy-MM-dd HH:mm:ss</li>
     * <li>yyyy-M-d H:m:s</li>
     * <li>yyyy/MM/dd</li>
     * <li>yyyy/M/d</li>
     * "yyyy-MM-dd</li>
     * "yyyy-M-d</li>
     * @param o 日付データ
     * @return 変換したDateインスタンス
     */
    public static <T> Date toDate(final Object o) {
        try {
            if (o instanceof Long) {
                return new Date((Long) o);
            }
            if (o instanceof Date) {
                return (Date) o;
            }
            String sDate = o.toString();
            return org.apache.commons.lang3.time.DateUtils.parseDate(sDate, SUPPORT_FORMAT);
        } catch (Exception e) {
            StringBuffer buf = new StringBuffer("Text need match pattern in below");
            Stream<String> plist = Arrays.stream(SUPPORT_FORMAT);
            plist.forEach(x -> buf.append(System.lineSeparator() + " - " + x));
            throw new IllegalArgumentException(buf.toString() , e);
        }
    }

    /**
     * ２つの日付の一致を判定する
     * @param val1 日付1
     * @param val2 日付2
     * @return 一致する場合True
     */
    public static boolean same(final Date val1, final Date val2) {
        return org.apache.commons.lang3.time.DateUtils.isSameDay(val1, val2);
    }
}
