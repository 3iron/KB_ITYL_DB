/*
 *** 인텔리제이를 이용하여 MySQL 연결하여 사용하기 ***

 1. 데이터 소스 생성
    방법 1) 메인 메뉴에서 `File` | `New` | `Data Source`를 선택하고 `MySQL`을 선택
    방법 2) Database 도구 창에서  `New` 버튼을 클릭하고 `Data Source` 선택 후 `MySQL`을 선택

 2. 드라이버 설정
    - 연결 설정 영역 하단에 `Download missing driver files` 링크가 있는지 확인
    - 있으면 클릭하여 다운로드 수행

 3. 연결 세부 정보 지정
    - `General(일반)` 탭에서 알맞은 정보 입력
    - `Host`: 서버 주소 (예: localhost 또는 127.0.0.1)
    - : MySQL 포트 (기본값: 3306) `Port`
    - `Authentication`: 인증 방식 선택 (일반적으로 `User & Password`)
    - 및 : 사용자 자격 증명 입력
        `User` : root
        `Password` : root 비밀번호
    - `Database`: 연결할 데이터베이스 이름

 4. URL 확인 후 연결 테스트
    - URL : jdbc:mysql://localhost:3306   형태
    - 연결 테스트 클릭 -> 체크 표시 확인

 5. 'Schemas(스키마)' 탭에서 사용하려는 스키마 모두 체크

 6. 확인 클릭

 *** SQL 실행은 CTRL + Enter ***

 - 커서가 올라간 SQL만 실행하고 싶은 경우
    인텔리제이 설정 > 데이터베이스 > 쿼리 실행 > 실행
    > 구문 내 캐럿이 실행될 때 > "최소 하위 쿼리 또는 구문" 선택
*/

-- 현재 서버에 존재하는 데이터베이스 확인
show databases;

-- ----------------------------------
-- sqldb 데이터베이스로 전환
use sqldb;

-- usertbl에서 김경호 보다 큰 사람들의 이름과 키 조회

-- 1. 김경호의 키 조회
SELECT name, height
FROM usertbl
WHERE name = '김경호'; -- 177

-- 2. 키가 177 보다 큰 사람들의 이름과 키 조회
SELECT name, height FROM usertbl
WHERE height > 177;

-- 1 + 2 조합하여 서브쿼리 만들기
SELECT name, height FROM usertbl
WHERE height > (SELECT height
                FROM usertbl
                WHERE name = '김경호');



select * from employees where emp_no = 110022;