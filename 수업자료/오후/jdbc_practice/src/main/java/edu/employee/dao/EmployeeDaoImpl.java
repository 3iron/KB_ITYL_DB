package edu.employee.dao;

import edu.common.JDBCUtil;
import edu.employee.vo.EmployeeVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public List<EmployeeVO> getDepartmentEmployees(String deptTitle) {
        List<EmployeeVO> list = new ArrayList<>();

        /* 마케팅부 직원이 존재하지 않아서 직접 추가 - 쿼리문
        *
            INSERT INTO EMPLOYEE (
                EMP_ID,
                EMP_NAME,
                EMP_NO,
                EMAIL,
                PHONE,
                DEPT_CODE,
                JOB_CODE,
                SAL_LEVEL,
                SALARY,
                BONUS,
                MANAGER_ID,
                HIRE_DATE,
                ENT_YN
            )
            VALUES (
                '223',
                '홍길동',
                '900101-1234567',
                'hong@test.com',
                '01012341234',
                'D3',
                'J7',
                'S5',
                2500000,
                0.1,
                '200',
                CURDATE(),
                'N'
            );
        * */

        String sql = """
                SELECT 
                    E.EMP_NAME,
                    D.DEPT_TITLE,
                    J.JOB_NAME,
                    E.BONUS,
                    E.ENT_YN
                FROM EMPLOYEE E
                JOIN DEPARTMENT D ON E.DEPT_CODE = D.DEPT_ID
                JOIN JOB J ON E.JOB_CODE = J.JOB_CODE
                WHERE D.DEPT_TITLE = ?
                ORDER BY E.BONUS DESC
                """;

        try (
                Connection conn = JDBCUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, deptTitle);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    EmployeeVO emp = new EmployeeVO();

                    emp.setEmpName(rs.getString("EMP_NAME"));
                    emp.setDeptTitle(rs.getString("DEPT_TITLE"));
                    emp.setJobName(rs.getString("JOB_NAME"));
                    emp.setBonus(rs.getObject("BONUS", Double.class));
                    emp.setEntYn(rs.getString("ENT_YN"));

                    list.add(emp);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<EmployeeVO> getDepartmentAvgSalary() {
        List<EmployeeVO> list = new ArrayList<>();

        String sql = """
                SELECT 
                    D.DEPT_TITLE,
                    J.JOB_NAME,
                    COUNT(*) AS EMP_COUNT,
                    ROUND(AVG(E.SALARY)) AS AVG_SALARY
                FROM EMPLOYEE E
                JOIN DEPARTMENT D ON E.DEPT_CODE = D.DEPT_ID
                JOIN JOB J ON E.JOB_CODE = J.JOB_CODE
                WHERE E.ENT_YN = 'N'
                GROUP BY D.DEPT_TITLE, J.JOB_NAME
                HAVING AVG(E.SALARY) >= 3000000
                ORDER BY AVG_SALARY DESC
                """;

        try (
                Connection conn = JDBCUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
                EmployeeVO emp = new EmployeeVO();

                emp.setDeptTitle(rs.getString("DEPT_TITLE"));
                emp.setJobName(rs.getString("JOB_NAME"));
                emp.setEmployeeCount(rs.getInt("EMP_COUNT"));
                emp.setAvgSalary(rs.getDouble("AVG_SALARY"));

                list.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<EmployeeVO> getWorkingEmployees() {
        List<EmployeeVO> list = new ArrayList<>();

        String sql = """
                SELECT 
                    D.DEPT_TITLE,
                    J.JOB_NAME,
                    E.EMP_NAME,
                    E.SALARY
                FROM EMPLOYEE E
                LEFT JOIN DEPARTMENT D ON E.DEPT_CODE = D.DEPT_ID
                JOIN JOB J ON E.JOB_CODE = J.JOB_CODE
                WHERE E.ENT_YN = 'N'
                ORDER BY J.JOB_NAME ASC
                LIMIT 10
                """;

        try (
                Connection conn = JDBCUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
                EmployeeVO emp = new EmployeeVO();

                emp.setDeptTitle(rs.getString("DEPT_TITLE"));
                emp.setJobName(rs.getString("JOB_NAME"));
                emp.setEmpName(rs.getString("EMP_NAME"));
                emp.setSalary(rs.getInt("SALARY"));

                list.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public int increaseSalary(String deptCode) {
        int result = 0;

        String sql = """
                UPDATE EMPLOYEE
                SET SALARY = SALARY * 1.1
                WHERE DEPT_CODE = ?
                """;

        try (
                Connection conn = JDBCUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, deptCode);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<EmployeeVO> getEmployeesWithoutPhone() {
        List<EmployeeVO> list = new ArrayList<>();

        String sql = """
                SELECT 
                    E.EMP_NAME,
                    E.PHONE,
                    D.DEPT_TITLE
                FROM EMPLOYEE E
                LEFT JOIN DEPARTMENT D ON E.DEPT_CODE = D.DEPT_ID
                WHERE E.PHONE IS NULL
                ORDER BY E.EMP_NAME DESC
                """;

        try (
                Connection conn = JDBCUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
                EmployeeVO emp = new EmployeeVO();

                emp.setEmpName(rs.getString("EMP_NAME"));
                emp.setPhone(rs.getString("PHONE"));
                emp.setDeptTitle(rs.getString("DEPT_TITLE"));

                list.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}