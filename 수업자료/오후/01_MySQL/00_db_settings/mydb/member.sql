DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
	`member_no`	int	NOT NULL	COMMENT '회원 번호',
	`member_email`	varchar(50)	NOT NULL	COMMENT '회원 이메일(아이디)',
	`member_pw`	varchar(30)	NOT NULL	COMMENT '회원 비밀번호',
	`member_nick`	varchar(30)	NOT NULL	COMMENT '회원 닉네임',
	`member_tel`	char(11)	NULL	COMMENT '전화번호(＂-＂ 제외)',
	`member_addr`	varchar(500)	NULL	COMMENT '회원 주소',
	`enroll_dt`	datetime	NOT NULL	DEFAULT CURRENT_TIMESTAMP	COMMENT '회원　가입일',
	`secession_fi`	char(1)	NOT NULL	DEFAULT 'N'	COMMENT '탈퇴 여부(Y : 탈퇴, N : 미탈퇴)'
);

ALTER TABLE `member` ADD CONSTRAINT `PK_MEMBER` PRIMARY KEY (
	`member_no`
);