package org.edu.member.dao;

import org.edu.member.common.JDBCUtil;
import org.edu.member.vo.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PmjMemberDaoImpl implements MemberDao{
    // JDBCUtil을 통해 Connection 객체 가져오기
    Connection con = JDBCUtil.getConnection();

    // 회원 등록
    @Override
    public int create(Member m) throws SQLException {
        String sql = "INSERT INTO members VALUES (NULL, ?, ?, ?, ?, DEFAULT, ?)";

        try (PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, m.getMemberId());
            pstm.setString(2, m.getMemberPw());
            pstm.setString(3, m.getMemberName());
            pstm.setString(4, m.getMemberRole());
            pstm.setInt(5, m.getDeptNo());

            int result = pstm.executeUpdate();

            if (result > 0) {
                con.commit();
            } else {
                con.rollback();
            }

            return result;
        }
    }

    // 회원 정보 수정
    @Override
    public int update(Member m) throws SQLException {
        String sql = "UPDATE members SET name = ?, role = ? WHERE no = ?";

        try (PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, m.getMemberName());
            pstm.setString(2, m.getMemberRole());
            pstm.setInt(3, m.getMemberNo());

            int result = pstm.executeUpdate();

            if (result > 0) {
                con.commit();
            } else {
                con.rollback();
            }

            return result;
        }
    }

    // 회원 정보 삭제
    @Override
    public int delete(int memberNo) throws SQLException {
        String sql = "DELETE FROM members WHERE no = ?";

        try (PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, memberNo);

            int result = pstm.executeUpdate();

            if (result > 0) {
                con.commit();
            } else {
                con.rollback();
            }

            return result;
        }
    }

    // 회원 정보 조회
    @Override
    public Member select(int no) throws SQLException {
        String sql = "SELECT no, id, password, name, role, deleted_yn, dept_no FROM members WHERE no = ?";

        try (PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, no);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    Member m = new Member();

                    m.setMemberNo(rs.getInt("no"));
                    m.setMemberId(rs.getString("id"));
                    m.setMemberPw(rs.getString("password"));
                    m.setMemberName(rs.getString("name"));
                    m.setMemberRole(rs.getString("role"));
                    m.setDeletedYn(rs.getString("deleted_yn"));
                    m.setDeptNo(rs.getInt("dept_no"));

                    return m;
                }
            }
        }

        return null;
    }

    // 회원 목록 조회
    @Override
    public List<Member> getList() throws SQLException {
        String sql = "SELECT no, id, password, name, role, deleted_yn, dept_no FROM members";

        List<Member> list = new ArrayList<>();

        try (PreparedStatement pstm = con.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Member m = new Member();

                m.setMemberNo(rs.getInt("no"));
                m.setMemberId(rs.getString("id"));
                m.setMemberPw(rs.getString("password"));
                m.setMemberName(rs.getString("name"));
                m.setMemberRole(rs.getString("role"));
                m.setDeletedYn(rs.getString("deleted_yn"));
                m.setDeptNo(rs.getInt("dept_no"));

                list.add(m);
            }
        }

        return list;
    }

    // 회원 부서명 조회
    @Override
    public Member getDeptName(int no) throws SQLException {
        String sql = "SELECT m.no, m.name, d.dept_no, d.dept_name " +
                "FROM members m " +
                "JOIN departments d ON m.dept_no = d.dept_no " +
                "WHERE m.no = ?";

        try (PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, no);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    Member m = new Member();

                    m.setMemberNo(rs.getInt("no"));
                    m.setMemberName(rs.getString("name"));
                    m.setDeptNo(rs.getInt("dept_no"));
                    m.setDeptName(rs.getString("dept_name"));

                    return m;
                }
            }
        }

        return null;
    }
}
