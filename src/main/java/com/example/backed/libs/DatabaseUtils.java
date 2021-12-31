package com.example.backed.libs;

import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class DatabaseUtils {
    /** プロパティファイルのキー名(ドライバ名) */
    public static String PROP_DRIVER_NAME = "spring.datasource.driver-class-name";
    /** プロパティファイルのキー名(コネクションURL) */
    public static String PROP_CONNECTION_URL = "spring.datasource.url";
    /** プロパティファイルのキー名(DBアクセスユーザ) */
    public static String PROP_USER = "spring.datasource.username";
    /** プロパティファイルのキー名(アクセスパスワード) */
    public static String PROP_PASSWORD = "spring.datasource.password";

    /**
     * データソースをのインスタンスを取得する
     * @return データソースのインスタンス
     */
    public static DataSource getDataSource() {
        try {
            String driverName = PropertyUtils.getProperty(PROP_DRIVER_NAME);
            String connectionUrl = PropertyUtils.getProperty(PROP_CONNECTION_URL);
            String user = PropertyUtils.getProperty(PROP_USER);
            String pass = PropertyUtils.getProperty(PROP_PASSWORD);

            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            dataSourceBuilder.driverClassName(driverName);
            dataSourceBuilder.url(connectionUrl);
            dataSourceBuilder.username(user);
            dataSourceBuilder.password(pass);
            return dataSourceBuilder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 1レコード、1カラムのデータを取得する
     * @param sql 検索するSQL
     * @param columnIndex 取得カラムのIndex
     * @param <T>
     * @return 該当カラムのValue
     */
    @SuppressWarnings("unchecked")
    public static <T> T getRowValue(String sql, int columnIndex) {
        try (Connection c = DatabaseUtils.getDataSource().getConnection();
             PreparedStatement s = c.prepareStatement(sql);
             ResultSet r = s.executeQuery()){
            r.next();
            return (T)r.getObject(columnIndex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 1レコードのデータをMap形式で取得する
     * @param sql 検索するSQL
     * @return レコード情報のMAP
     */
    public static Map<String, Object> getRowValues(String sql) {
        Map<String, Object> rows = new HashMap<>();
        try (Connection c = DatabaseUtils.getDataSource().getConnection();
             PreparedStatement s = c.prepareStatement(sql);
             ResultSet r = s.executeQuery()){
            r.next();
            int countColumns = r.getMetaData().getColumnCount();
            for (int i = 1; i < countColumns; i++) {
                rows.put(r.getMetaData().getColumnName(i), r.getObject(i));
            }
            return rows;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
