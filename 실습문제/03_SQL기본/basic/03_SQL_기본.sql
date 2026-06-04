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


# 1. titles 테이블의 데이터 출력
select * from employees.titles;

# 2. employees 테이블에서 first_name 컬럼만 출력하기
select first_name from employees.employees;

# 3. employees 테이블에서 first_name 컬럼, last_name 컬럼, gender 컬럼 출력하기
select first_name, last_name, gender from employees.employees;

# 4.
select last_name, gender, hire_date from employees.employees;

select *
from sqldb.usertbl
where name = '김경호';

select *
from sqldb.usertbl
where birthYear >= 1970 && height >= 182;

select *
from sqldb.usertbl
where height >= 180 && height <= 183;

select *
from sqldb.usertbl
where addr = '경남' or addr = '전남' or addr='경북';

select *
from sqldb.usertbl
where name like '김%';

select name, height
from sqldb.usertbl
where height  > (select height from sqldb.usertbl where name = '김경호');

# usertbl을 mDate의 오름차순으로 정렬하여 출력
select *
from sqldb.usertbl
order by mDate asc;

# usertbl을 mDate의 내림차순으로 정렬하여 출력
select *
from sqldb.usertbl
order by mDate desc;

# usertbl을 height의 내림차순으로 정렬하고,
# 동률인 경우 name의 내림차순으로 정렬하여 출력하세요.
select *
from sqldb.usertbl
order by height desc, name desc;

# usertbl의 주소지를 중복없이 오름 차순으로 출력하세요
select distinct addr
from usertbl
order by addr asc;

# --- World 데이터베이스 ---

# 국가 코드가 'KOR'인 도시를 찾아 인구수를 역순으로 표시하세요.
select Name, Population
from world.city
where CountryCode = 'KOR'
order by Population desc;

# city 테이블에서 국가코드와 인구수를 출력하라
# 정렬은 국가코드별로 오름차순으로,
# 동일한 코드(국가) 안에서는 인구 수의 역순으로 표시.
select CountryCode, Population
from world.city
order by CountryCode asc, Population desc;

# city 테이블에서
# 국가코드가 'KOR'인 도시의 수를 표시.
select count(*)
from world.city
where CountryCode = 'KOR';

# city 테이블에서
# 국가코드가 'KOR', 'CHN', 'JPN'인 도시를 찾으세요.
select *
from world.city
where CountryCode = 'KOR'
    OR CountryCode = 'CHN'
    OR CountryCode = 'JPN' ;

# 국가코드가 'KOR'이면서
# 인구가 100만 이상인 도시를 찾으세요
SELECT *
FROM world.city
where CountryCode = 'KOR'
  AND Population >= 1000000;

# 국가코드가 'KOR'인 도시 중
# 인구수가 많은 순서로 상위 10개만 표시
select *
from world.city
where CountryCode = 'KOR'
ORDER BY Population
limit 10;

# city 테이블에서 국가코드가 'KOR'이고
# 인구가 100만 이상 500만 이하인 도시
select *
from world.city
where CountryCode = 'KOR'
AND Population >= 1000000
  AND Population <= 5000000;

