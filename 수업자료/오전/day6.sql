-- =====================================================
-- MySQL 8 사용자 생성 / 접속 가능 / 권한 부여 / 권한 회수 / 삭제
-- =====================================================

-- 1. 실습용 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS sqldb;

-- 2. 기존 사용자가 있으면 삭제
DROP USER IF EXISTS 'testuser'@'localhost';

-- 3. 사용자 생성
-- 이 명령만으로 'testuser'는 localhost에서 MySQL 접속 가능
CREATE USER 'testuser'@'localhost'
IDENTIFIED BY 'Test1234!';

-- 4. 접속만 가능한 기본 상태 확인
-- USAGE는 "접속은 가능하지만 DB 작업 권한은 거의 없음"을 의미
SHOW GRANTS FOR 'testuser'@'localhost';

-- 5. 접속 가능 상태를 명시적으로 표현하고 싶을 때
-- MySQL에서는 CONNECT 권한이 따로 없으므로 USAGE를 사용
GRANT USAGE
ON *.*
TO 'testuser'@'localhost';

-- 6. 다시 권한 확인
SHOW GRANTS FOR 'testuser'@'localhost';

-- 7. sqldb 데이터베이스에 작업 권한 부여
-- SELECT : 조회
-- INSERT : 추가
-- UPDATE : 수정
GRANT SELECT, INSERT, UPDATE
ON sqldb.*
TO 'testuser'@'localhost';

-- 모든 권한
GRANT usage
ON *.*
TO 'testuser'@'localhost';

-- 8. 권한 부여 후 확인
SHOW GRANTS FOR 'testuser'@'localhost';

-- 9. UPDATE 권한만 회수
REVOKE UPDATE
ON sqldb.*
FROM 'testuser'@'localhost';

-- 10. 권한 회수 후 확인
SHOW GRANTS FOR 'testuser'@'localhost';

-- 11. SELECT, INSERT 권한도 회수
REVOKE SELECT, INSERT
ON sqldb.*
FROM 'testuser'@'localhost';

-- 12. 권한이 거의 없는 상태 확인
-- 다시 USAGE 상태만 남음
SHOW GRANTS FOR 'testuser'@'localhost';

-- 13. 사용자 삭제
DROP USER 'testuser'@'localhost';

-- 14. 삭제 확인
SELECT user, host
FROM mysql.user
WHERE user = 'testuser';


