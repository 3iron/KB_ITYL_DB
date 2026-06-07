package org.edu.member.dao;

import org.edu.member.common.JDBCUtil;
import org.edu.member.vo.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl implements MemberDao {

    private Connection conn = JDBCUtil.getConnection();

    // 회원 등록
    @Override
    public int create(Member member) throws SQLException {
        String sql = "INSERT INTO members VALUES (DEFAULT, ?, ?, ?, ?, 'N', ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, member.getMemberId());
            pstmt.setString(2, member.getMemberPw());
            pstmt.setString(3, member.getMemberName());
            pstmt.setString(4, member.getMemberRole());
            pstmt.setInt(5, member.getDeptNo());

            int result = pstmt.executeUpdate();

            if (result == 1) {
                conn.commit();
            }

            return result;
        }
    }

    // 회원 목록 조회
    @Override
    public List<Member> getList() throws SQLException {
        String sql = "SELECT no, id, pw, name, role, del_yn, dept_no FROM members";

        List<Member> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Member mem = new Member();

                mem.setMemberNo(rs.getInt("no"));
                mem.setMemberId(rs.getString("id"));
                mem.setMemberPw(rs.getString("pw"));
                mem.setMemberName(rs.getString("name"));
                mem.setMemberRole(rs.getString("role"));
                mem.setDeletedYn(rs.getString("del_yn"));
                mem.setDeptNo(rs.getInt("dept_no"));

                list.add(mem);
            }
        }

        return list;
    }

    // 회원 정보 수정
    @Override
    public int update(Member mem) throws SQLException {
        String sql = "UPDATE members SET name = ?, role = ? WHERE no = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mem.getMemberName());
            pstmt.setString(2, mem.getMemberRole());
            pstmt.setInt(3, mem.getMemberNo());

            int result = pstmt.executeUpdate();

            if (result == 1) {
                conn.commit();
            }

            return result;
        }
    }

    // 회원 삭제
    @Override
    public int delete(int memberNo) throws SQLException {
        String sql = "DELETE FROM members WHERE no = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberNo);

            int result = pstmt.executeUpdate();

            if (result == 1) {
                conn.commit();
            }

            return result;
        }
    }

    // 회원 부서명 조회
    @Override
    public Member getDeptName(int memberNo) throws SQLException {
        String sql = "SELECT no, name, d.dept_no, dept_name " +
                "FROM members m " +
                "JOIN departments d ON m.dept_no = d.dept_no " +
                "WHERE no = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberNo);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Member mem = new Member();

                    mem.setMemberNo(rs.getInt("no"));
                    mem.setMemberName(rs.getString("name"));
                    mem.setDeptNo(rs.getInt("dept_no"));
                    mem.setDeptName(rs.getString("dept_name"));

                    return mem;
                }
            }
        }

        return null;
    }

    // 회원 정보 조회
    @Override
    public Member select(int memberNo) throws SQLException {
        String sql = "SELECT no, id, pw, name, role, del_yn, dept_no FROM members WHERE no = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberNo);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Member mem = new Member();

                    mem.setMemberNo(rs.getInt("no"));
                    mem.setMemberId(rs.getString("id"));
                    mem.setMemberPw(rs.getString("pw"));
                    mem.setMemberName(rs.getString("name"));
                    mem.setMemberRole(rs.getString("role"));
                    mem.setDeletedYn(rs.getString("del_yn"));
                    mem.setDeptNo(rs.getInt("dept_no"));

                    return mem;
                }
            }
        }

        return null;
    }
}