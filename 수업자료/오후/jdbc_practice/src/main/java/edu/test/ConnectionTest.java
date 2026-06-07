package edu.test;

import edu.common.JDBCUtil;

import java.sql.Connection;

public class ConnectionTest {

    public static void main(String[] args) {

        try (Connection conn = JDBCUtil.getConnection()) {

            if (conn != null) {
                System.out.println("DB 연결 성공!");
                System.out.println(conn);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}