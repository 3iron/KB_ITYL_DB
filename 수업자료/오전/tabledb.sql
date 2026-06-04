-- =====================================================
-- 1. DATABASE 생성
-- =====================================================

DROP DATABASE IF EXISTS tableDB;
CREATE DATABASE tableDB;
USE tableDB;

-- =====================================================
-- 2. usertbl 생성
-- =====================================================

DROP TABLE IF EXISTS usertbl;

CREATE TABLE usertbl (
    userID CHAR(8) NOT NULL PRIMARY KEY,
    name VARCHAR(10) NOT NULL,
    birthYear INT NOT NULL,
    addr CHAR(2) NOT NULL,
    mobile1 CHAR(3) NULL,
    mobile2 CHAR(8) NULL,
    height SMALLINT NULL,
    mDate DATE NULL
);

-- =====================================================
-- 3. buytbl 생성 (외래키 포함)
-- =====================================================

DROP TABLE IF EXISTS buytbl;

CREATE TABLE buytbl (
    num INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    userID CHAR(8) NOT NULL,
    prodName CHAR(6) NOT NULL,
    groupName CHAR(4) NULL,
    price INT NOT NULL,
    amount SMALLINT NOT NULL,

    CONSTRAINT FK_buytbl_usertbl
    FOREIGN KEY(userID)
    REFERENCES usertbl(userID)
);

-- =====================================================
-- 4. 데이터 입력
-- =====================================================

INSERT INTO usertbl VALUES
('LSG', '이승기', 1987, '서울', '011', '1111111', 182, '2008-08-08'),
('KBS', '김범수', 1979, '경남', '011', '2222222', 173, '2012-04-04'),
('KKH', '김경호', 1971, '전남', '019', '3333333', 177, '2007-07-07');

INSERT INTO buytbl(userID, prodName, groupName, price, amount) VALUES
('KBS', '운동화', NULL, 30, 2),
('KBS', '노트북', '전자', 1000, 1),
('LSG', '모니터', '전자', 200, 1);

-- =====================================================
-- 5. 조회
-- =====================================================

SELECT * FROM usertbl;
SELECT * FROM buytbl;

-- JOIN 조회
SELECT
    u.userID,
    u.name,
    b.prodName,
    b.price,
    b.amount
FROM usertbl u
INNER JOIN buytbl b
ON u.userID = b.userID;

-- =====================================================
-- 6. UNIQUE 제약조건
-- =====================================================


ALTER TABLE usertbl
ADD email VARCHAR(30) UNIQUE;

-- 테스트
UPDATE usertbl
SET email = 'aaa@test.com'
WHERE userID = 'LSG';

select * from usertbl;

-- error
UPDATE usertbl
SET email = 'aaa@test.com'
WHERE userID = 'KBS';

-- =====================================================
-- 7. CHECK 제약조건
-- =====================================================

ALTER TABLE usertbl
ADD CONSTRAINT CK_height
CHECK(height >= 100);

select * from usertbl;

-- error
INSERT INTO usertbl
(userID, name, birthYear, height, addr)
VALUES
('APP', '이순신', 1982, 50, '서울');

-- ok
INSERT INTO usertbl
(userID, name, birthYear, height, addr)
VALUES
('APP', '이순신', 1982, 150, '서울');

select * from usertbl;

-- =====================================================
-- 8. DEFAULT 설정
-- =====================================================

ALTER TABLE usertbl
ADD point INT DEFAULT 0;

-- DEFAULT 자동 입력 확인
INSERT INTO usertbl
(userID, name, birthYear, addr)
VALUES
('WB', '원빈', 1982, '서울');

SELECT * FROM usertbl;

-- =====================================================
-- 9. ALTER TABLE 실습
-- =====================================================

-- 컬럼 추가
ALTER TABLE usertbl
ADD homepage VARCHAR(30)
DEFAULT 'http://www.naver.com';

-- 컬럼 수정
ALTER TABLE usertbl
MODIFY homepage VARCHAR(50);

desc usertbl;

-- 컬럼 이름 변경
ALTER TABLE usertbl
CHANGE COLUMN name uName VARCHAR(20) NULL;

desc usertbl;

-- 컬럼 삭제
ALTER TABLE usertbl
DROP COLUMN mobile1;

desc usertbl;

-- =====================================================
-- 10. 복합 PRIMARY KEY
-- =====================================================

DROP TABLE IF EXISTS prodtbl;

CREATE TABLE prodtbl (
    prodCode CHAR(3) NOT NULL,
    prodID CHAR(4) NOT NULL,
    prodDate DATETIME NOT NULL,
    prodCur CHAR(10) NULL,

    CONSTRAINT PK_prodtbl
    PRIMARY KEY(prodCode, prodID)
);

SHOW INDEX FROM prodtbl;

-- =====================================================
-- 11. RENAME 실습
-- =====================================================

RENAME TABLE prodtbl TO producttbl;

desc producttbl;

ALTER TABLE producttbl
RENAME COLUMN prodCur TO status;

-- =====================================================
-- 12. TRUNCATE 실습
-- =====================================================

TRUNCATE TABLE producttbl;

-- =====================================================
-- 13. DROP 실습
-- 외래키 테이블 먼저 삭제
-- =====================================================

DROP TABLE IF EXISTS buytbl;
DROP TABLE IF EXISTS usertbl;
DROP TABLE IF EXISTS producttbl;

-- =====================================================
-- 14. DATABASE 삭제
-- =====================================================

DROP DATABASE IF EXISTS tableDB;




