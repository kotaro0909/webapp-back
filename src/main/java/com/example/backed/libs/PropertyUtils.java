package com.example.backed.libs;

import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {

    public static String APPLICATION_PROPERTIES = "/application.properties";

    /** アクティブプロファイル */
    public enum ActiveProfile {dev, st, prod}

    /** アクティブプロファイルのキー名 */
    private final static String PROP_ACTIVE_PROFILE = "spring.profiles.active";

    /** プロパティのインスタンス */
    private static Properties p = null;

    /**
     * Staticメソッド<br>
     *     プロパティをセットアップする.
     */
    static {
        p = new Properties();
        try {
            PropertyUtils u = new PropertyUtils();
            InputStream rs = u.getClass().getResourceAsStream(APPLICATION_PROPERTIES);
            p.load(rs);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * キーに一致するプロパティを取得する
     * @param key キー
     * @return Value
     */
    public static String getProperty(String key) {
        return p.getProperty(key);
    }

    /**
     *  プロパティを追加する
     * @param key キー
     * @param value 値
     */
    public static void setProperty(String key, String value) {
        p.setProperty(key, value);
    }

    /**
     * アクティブプロファイルを取得する
     * @return アクティブロファイル
     */
    public static ActiveProfile getActiveProfile() {
        String active = p.getProperty(PROP_ACTIVE_PROFILE);
        for (ActiveProfile a : ActiveProfile.values()) {
            if(StringUtils.equals(a.toString(), active)) {
                return a;
            }
        }
        throw new RuntimeException("Active profile is not collect (you need select one in dev,st,prod. Check key=[" + PROP_ACTIVE_PROFILE + "] on application.properties");
    }
}
