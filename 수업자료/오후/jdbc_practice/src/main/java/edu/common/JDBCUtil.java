package edu.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {

    private static final Properties prop = new Properties();

    static {
        try {
            InputStream is = JDBCUtil.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties");

            if (is == null) {
                throw new RuntimeException("application.properties 파일을 찾을 수 없습니다.");
            }

            prop.load(is);

            Class.forName(prop.getProperty("driver"));

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("JDBCUtil 초기화 실패", e);
        }
    }

    // DB 연결 생성
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                prop.getProperty("url"),
                prop.getProperty("username"),
                prop.getProperty("password")
        );
    }

    // SELECT용 close
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // INSERT, UPDATE, DELETE용 close
    public static void close(Connection conn, Statement stmt) {
        close(conn, stmt, null);
    }
}