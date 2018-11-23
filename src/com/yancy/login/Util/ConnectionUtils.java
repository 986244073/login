package com.yancy.login.Util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtils {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;
    private static Properties props = new Properties();
    //ThreadLocal:保证一个线程一个连接
    private static ThreadLocal<Connection> t1 = new ThreadLocal<>();

    /*
     * 读取db
     * */
    static {
//类加载器
        InputStream in =
                ConnectionUtils.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            props.load(in);
            driver = props.getProperty("jdbc.driver");
            driver = props.getProperty("jdbc.url");
            driver = props.getProperty("jdbc.user");
            driver = props.getProperty("jdbc.pawd");
            Class.forName(driver);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static Connection getConn() throws SQLException {
        Connection conn = t1.get();
        if (conn == null) {
            conn = DriverManager.getConnection(url, username, password);
            t1.set(conn);
        }
        return conn;
    }

    public static void closeConn() throws SQLException {
        Connection conn = t1.get();
        if (conn != null) {
            conn.close();
        }
        t1.set(null);
    }
}
