# 사용자별 구매 이력 조회
SELECT *
FROM buytbl b
JOIN usertbl u
ON b.userID = u.userID;

# userID가 'JYP'인 데이터만 출력
SELECT *
FROM buytbl b
         JOIN usertbl u
              ON b.userID = u.userID
where b.userID = 'JYP';

# 구매이력이 없는 사용자도 출력
select u.userID, u.name, b.prodName, u.addr, concat(u.mobile1, u.mobile2) as 연락처
from usertbl u
         left outer join buytbl b on u.userID = b.userID
order by u.userID;

# sqldb의 사용자를 모두 조회하되 전화가 없는 사람은 제외하고 출력하세요.
select * from usertbl
where mobile1 is not null
  and mobile2 is not null;

# sqldb의 사용자를 모두 조회하되 전화가 없는 사람만 출력하세요.
select * from usertbl
where mobile1 is null
   or mobile2 is null;


