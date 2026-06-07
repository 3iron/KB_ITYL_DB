package org.edu.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private int memberNo;
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberRole;

    private String deletedYn;

    private int deptNo;
    private String deptName;
}