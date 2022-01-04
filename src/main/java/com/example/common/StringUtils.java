package com.example.common;

import org.apache.commons.lang3.RandomStringUtils;
import com.google.common.base.CaseFormat;

/**
 * 文字列操作ユーティリティ。
 */
public final class StringUtils {

    /**
     * コンストラクタ。
     *     インスタンスを生成しないで使用するためPrivateになっています。
     */
    private StringUtils() {
    }

    public static String convertSnakeToCamel(String lowerSnake) {
        String result = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, lowerSnake);
        return result;
    }

    /**
     * 文字列が空・NULLか判定する。
     * @param val 文字列
     * @return 判定結果（空・NULLの場合True）
     */
    public static boolean isEmpty(final String val) {
        return org.apache.commons.lang3.StringUtils.isEmpty(val);
    }

    /**
     * ２つの文字列が一致するか判定する。
     * @param val1 文字列１
     * @param val2 文字テル２
     * @return 判定結果（一致する場合True）
     */
    public static boolean equals(final String val1, final String val2) {
        return org.apache.commons.lang3.StringUtils.equals(val1, val2);
    }

    /**
     * ２つの文字列が不一致か判定する。
     * @param val1 文字列１
     * @param val2 文字列２
     * @return 判定結果（不一致の場合True）
     */
    public static boolean notEquals(final String val1, final String val2) {
        return !equals(val1, val2);
    }

    /**
     * 指定した文字数のランダム文字列を生成する。
     * @param length 生成する文字列の文字数
     * @return ランダム文字列
     */
    public static String random(final int length) {
        return RandomStringUtils.random(length);
    }

    /**
     * 指定した文字が連続した文字列を生成する。
     * @param val 生成対象文字
     * @param length 文字数
     * @return 指定文字の連続文字列
     */
    public static String repeat(String val, int length) {
        return org.apache.commons.lang3.StringUtils.repeat(val, length);
    }

    /**
     * 複数文字列を指定したセパレータ連結した文字列を生成する。
     * @param separator セパレータ
     * @param objects 連結対象文字列
     * @return 連携した文字列
     */
    public static String joinWith(String separator, Object... objects) {
        return org.apache.commons.lang3.StringUtils.joinWith(separator, objects);
    }

    /**
     * 文字列がnullの場合にブランク("")に置換する
     * @param val 検査対象文字列
     * @return 置換後文字列
     */
    public static String nvl(String val) {
        return val == null ? "" : val;
    }
}
