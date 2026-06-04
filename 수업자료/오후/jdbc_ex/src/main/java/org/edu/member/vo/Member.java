package org.edu.member.vo;

import lombok.*;

// VO(Value Object) : 값 자체를 표현하고 의미를 갖는 객체
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {

    private int memberNo;
    private String memberId;
    private String memberName;
    private String memberPw;
    private String memberRole;
}

public class Member()
