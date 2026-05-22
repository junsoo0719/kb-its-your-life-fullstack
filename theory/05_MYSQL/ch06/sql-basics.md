# ✨ SQL 기본

## 1. SELECT 기본 구조

SQL에서 데이터를 조회할 때는 `SELECT ... FROM` 구문을 사용한다.

기본 형식은 다음과 같다.

    SELECT 열이름
    FROM 테이블이름;

테이블의 모든 열을 조회하고 싶을 때는 `*`를 사용한다.

    SELECT *
    FROM employees;

즉, `SELECT`는 어떤 열을 조회할지 지정하고, `FROM`은 어느 테이블에서 가져올지 지정한다. 📌

## 2. USE 구문

`USE` 구문은 사용할 데이터베이스를 지정할 때 사용한다.

    USE 데이터베이스_이름;

한 번 데이터베이스를 지정하면, 특별히 다시 `USE` 문을 사용하거나 다른 데이터베이스를 명시하지 않는 이상 이후 SQL 문은 지정된 데이터베이스에서 수행된다.

예를 들면 다음과 같다.

    USE employees;

Workbench에서는 SQL 문으로 지정하지 않고, 화면에서 직접 데이터베이스를 선택해서 사용할 수도 있다.

## 3. SELECT와 FROM

`SELECT`에는 조회하고 싶은 열 이름을 작성한다.

모든 열을 조회할 때는 다음처럼 쓴다.

    SELECT *
    FROM employees;

특정 열만 조회할 때는 열 이름을 작성한다.

    SELECT emp_no, first_name, last_name
    FROM employees;

여러 개의 열을 조회할 때는 콤마(`,`)로 구분한다.  
또한 열 이름의 순서는 출력하고 싶은 순서대로 배열할 수 있다. ✅

## 4. SQL 주석

SQL에서도 주석을 작성할 수 있다.

한 줄 주석은 `--`를 사용한다.

    -- 한 줄 주석

여러 줄 주석은 `/* ... */`를 사용한다.

    /*
      여러 줄 주석
    */

주석은 실행되지 않고 설명을 남길 때 사용한다.

## 5. 데이터베이스, 테이블, 열 확인

데이터베이스명, 테이블명, 열 이름이 확실하지 않을 때는 조회 명령을 사용할 수 있다.

데이터베이스 목록 확인은 다음과 같다.

    SHOW DATABASES;

현재 데이터베이스의 테이블 목록 확인은 다음과 같다.

    SHOW TABLES;

특정 테이블의 구조를 확인할 때는 `DESCRIBE` 또는 `DESC`를 사용한다.

    DESCRIBE employees;
    DESC employees;

즉, SQL 작성 전에 구조를 확인할 때 유용하다. 📌

## 6. 열 이름 별칭

조회 결과에서 열 이름을 다른 이름으로 보여주고 싶을 때는 별칭을 사용한다.

기본 형식은 다음과 같다.

    SELECT 열이름 AS 별칭
    FROM 테이블이름;

예를 들면 다음과 같다.

    SELECT first_name AS name
    FROM employees;

`AS`를 사용하면 출력 결과의 열 이름을 더 읽기 쉽게 바꿀 수 있다.

## 7. WHERE 절

`WHERE` 절은 조회 결과에 조건을 주어 원하는 데이터만 조회할 때 사용한다.

기본 형식은 다음과 같다.

    SELECT 필드이름
    FROM 테이블이름
    WHERE 조건식;

즉, 전체 데이터 중 조건에 맞는 행만 필터링해서 가져오는 역할을 한다.

## 8. 조건 연산자와 관계 연산자

특정 조건의 데이터만 조회할 때는 조건 연산자와 관계 연산자를 조합해서 사용한다.

대표적인 조건 연산자는 다음과 같다.

- `=`
- `<`
- `>`
- `<=`
- `>=`
- `<>`
- `!=`

대표적인 관계 연산자는 다음과 같다.

- `NOT`
- `AND`
- `OR`

예를 들면 다음과 같다.

    SELECT *
    FROM employees
    WHERE gender = 'M' AND first_name = 'Georgi';

즉, 조건식을 조합해서 필요한 데이터만 조회할 수 있다. ✅

## 9. BETWEEN ... AND

`BETWEEN ... AND`는 특정 범위 안에 있는 값을 조회할 때 사용한다.

    SELECT *
    FROM employees
    WHERE emp_no BETWEEN 10001 AND 10010;

이 구문은 지정한 시작값과 끝값을 포함한다.

즉, 연속적인 범위 조건을 작성할 때 유용하다.

## 10. IN()

`IN()`은 여러 개의 이산적인 값 중 하나에 해당하는 데이터를 조회할 때 사용한다.

    SELECT *
    FROM employees
    WHERE emp_no IN (10001, 10005, 10010);

즉, 연속 범위가 아니라 특정 값 목록 중 하나와 일치하는지 검사할 때 사용한다.

## 11. LIKE

`LIKE`는 문자열 내용 검색에 사용한다.

와일드카드 문자는 다음과 같다.

- `%` → 무엇이든 허용, 0글자 이상
- `_` → 한 글자와 매치

예를 들면 다음과 같다.

    SELECT *
    FROM employees
    WHERE first_name LIKE 'A%';

이 구문은 `first_name`이 A로 시작하는 데이터를 조회한다.

한 글자만 대체하고 싶을 때는 `_`를 사용한다.

    SELECT *
    FROM employees
    WHERE first_name LIKE 'A_';

즉, 문자열 패턴 검색에는 `LIKE`를 사용한다. 📌

## 12. 서브쿼리

서브쿼리는 쿼리문 안에 또 다른 쿼리문이 들어 있는 구조이다.

예를 들면 다음과 같다.

    SELECT *
    FROM employees
    WHERE emp_no IN (
        SELECT emp_no
        FROM salaries
    );

즉, 안쪽 쿼리의 결과를 바깥쪽 쿼리에서 조건으로 사용하는 방식이다.

## 13. ANY, SOME, ALL

서브쿼리 결과가 여러 개일 때 `ANY`, `SOME`, `ALL`을 사용할 수 있다.

### 13-1. ANY

`ANY`는 서브쿼리의 여러 결과 중 한 가지만 만족해도 조건이 참이 된다.

    WHERE salary > ANY (서브쿼리)

또한 `= ANY(서브쿼리)`는 `IN(서브쿼리)`와 동일한 의미로 사용할 수 있다.

### 13-2. SOME

`SOME`은 `ANY`와 같은 의미로 사용된다.

즉, 여러 결과 중 일부 조건만 만족해도 된다.

### 13-3. ALL

`ALL`은 서브쿼리의 여러 결과를 모두 만족해야 조건이 참이 된다.

즉, `ANY`보다 더 강한 조건이라고 볼 수 있다. ⚠️

## 14. ORDER BY 절

`ORDER BY` 절은 조회 결과의 출력 순서를 조절하는 구문이다.

결과 데이터 자체를 변경하는 것은 아니고, 출력되는 순서만 바꾼다.

기본 오름차순 정렬은 다음과 같다.

    ORDER BY 컬럼명 ASC;

`ASC`는 기본값이므로 생략할 수 있다.

내림차순 정렬은 다음과 같다.

    ORDER BY 컬럼명 DESC;

정렬은 편리하지만 속도가 느려질 수 있다. 📌

## 15. 인덱스와 정렬 속도

기본키(PK)에 대해서는 인덱스가 자동 생성된다.  
인덱스는 정렬된 구조를 가지므로 PK 기준 정렬은 비교적 빠르다.

반면 다른 컬럼은 인덱스를 명시적으로 만들지 않으면 정렬 속도가 느릴 수 있다.

정리하면 다음과 같다.

- PK 컬럼 → 인덱스 자동 생성, 정렬 빠름
- 일반 컬럼 → 인덱스 없으면 정렬 느릴 수 있음

즉, 정렬 성능은 인덱스 여부와 관련이 깊다.

## 16. DISTINCT

`DISTINCT`는 중복된 값을 하나만 남길 때 사용한다.

    SELECT DISTINCT gender
    FROM employees;

주로 하나의 값에 대해 중복을 제거할 때 많이 사용한다.

즉, 같은 값이 여러 번 있어도 결과에는 한 번만 출력된다. ✅

## 17. LIMIT

`LIMIT`은 출력 개수를 제한할 때 사용한다.

상위 N개만 출력하려면 다음처럼 작성한다.

    LIMIT N;

예를 들면 다음과 같다.

    SELECT *
    FROM employees
    LIMIT 10;

이 구문은 상위 10개 행만 조회한다.

`LIMIT`을 사용하면 MySQL이 처리해야 할 데이터 양을 줄일 수 있어 부담을 많이 줄일 수 있다. 📌

## 18. LIMIT과 페이지네이션

페이지네이션을 구현할 때는 `LIMIT 시작, 개수` 형식을 사용할 수 있다.

    LIMIT 시작, 개수;

또는 다음 형식도 가능하다.

    LIMIT 개수 OFFSET 시작;

예를 들면 다음과 같다.

    LIMIT 10 OFFSET 20;

이 구문은 앞의 20개를 건너뛰고 10개를 가져온다는 의미이다.

주의할 점은 `LIMIT`에 수식을 넣을 수 없다는 것이다. ⚠️

## 19. 테이블 복사 CREATE TABLE ... SELECT

기존 테이블의 조회 결과를 이용해 새 테이블을 만들 수 있다.

기본 형식은 다음과 같다.

    CREATE TABLE 새테이블명
    SELECT ...
    FROM 기존테이블명;

일부 열만 복사할 수도 있다.

    CREATE TABLE buytbl3
    SELECT userID, prodName
    FROM buytbl;

조회는 다음처럼 한다.

    SELECT *
    FROM buytbl3;

단, PK나 FK 같은 제약 조건은 복사되지 않는다. ⚠️

## 20. 복사 시 컬럼명 변경

새 테이블을 만들 때 컬럼명을 바꾸고 싶다면 `SELECT`에서 별칭을 사용하면 된다.

예를 들면 다음과 같다.

    CREATE TABLE buytbl4
    SELECT userID, prodName AS product
    FROM buytbl;

또는 `AS`를 생략하고 작성할 수도 있다.

    CREATE TABLE buytbl4
    SELECT userID, prodName product
    FROM buytbl;

즉, 조회 결과의 별칭이 새 테이블의 컬럼명으로 사용될 수 있다.

## 21. GROUP BY

`GROUP BY`는 특정 컬럼을 기준으로 데이터를 그룹화할 때 사용한다.

주로 집계 함수와 함께 사용한다.

    SELECT userID, COUNT(*)
    FROM buytbl
    GROUP BY userID;

즉, 같은 값을 가진 행들을 하나의 그룹으로 묶고, 각 그룹에 대해 합계나 개수 같은 집계 결과를 계산한다.

## 22. 집계 함수

집계 함수는 여러 행의 값을 하나의 결과로 계산하는 함수이다.

대표적인 집계 함수는 다음과 같다.

- `AVG()` → 평균
- `MIN()` → 최솟값
- `MAX()` → 최댓값
- `COUNT()` → 개수
- `COUNT(DISTINCT)` → 중복 제거 후 개수
- `STDEV()` → 표준편차
- `VAR_SAMP()` → 표본 분산

집계 함수는 `GROUP BY`와 함께 많이 사용한다. 📌

## 23. GROUP BY에서 SELECT 가능한 컬럼

`GROUP BY`를 사용할 때는 `SELECT` 절에 작성할 수 있는 컬럼이 제한된다.

기본적으로 다음만 가능하다.

- `GROUP BY`에 참여한 컬럼
- 집계 함수의 결과
- 집계 표현식

즉, `GROUP BY`에 쓰이지 않은 일반 컬럼을 `SELECT` 절에 제시하면 에러가 발생할 수 있다. ⚠️

예를 들어 그룹 기준이 `userID`라면 `userID`와 집계 함수 결과만 조회하는 것이 안전하다.

## 24. HAVING

`HAVING`은 `GROUP BY`로 그룹화한 결과에 조건을 줄 때 사용한다.

즉, 일반 행에 조건을 주는 것은 `WHERE`, 그룹 결과에 조건을 주는 것은 `HAVING`이다.

예를 들면 다음과 같다.

    SELECT userID, SUM(price)
    FROM buytbl
    GROUP BY userID
    HAVING SUM(price) > 1000;

즉, 집계 결과를 기준으로 필터링할 때는 `HAVING`을 사용한다. ✅

## 25. GROUP BY와 ORDER BY 별칭 주의

`GROUP BY` 결과를 보기 좋게 출력하기 위해 별칭을 사용할 수 있다.  
하지만 별칭을 `ORDER BY`에 사용할 때는 DBMS나 작성 방식에 따라 주의가 필요하다.

필기 기준으로는 alias를 `ORDER BY`절에 쓰면 에러가 날 수 있다고 정리한다.

따라서 헷갈릴 때는 별칭 대신 원래 컬럼명이나 집계 표현식을 사용하는 것이 안전하다.

## 26. 중요 포인트 📌

- 데이터 조회는 `SELECT ... FROM` 구문을 사용한다.
- `USE 데이터베이스명;`으로 기본 데이터베이스를 지정한다.
- `SELECT *`는 모든 열을 조회한다.
- 여러 열은 콤마로 구분하고, 출력 순서대로 작성할 수 있다.
- 한 줄 주석은 `--`, 여러 줄 주석은 `/* ... */`를 사용한다.
- 데이터베이스 목록은 `SHOW DATABASES;`로 확인한다.
- 테이블 목록은 `SHOW TABLES;`로 확인한다.
- 테이블 구조는 `DESCRIBE` 또는 `DESC`로 확인한다.
- 열 별칭은 `AS`를 사용한다.
- `WHERE`는 원하는 데이터만 조회하기 위한 조건절이다.
- `BETWEEN ... AND`는 범위 조건에 사용한다.
- `IN()`은 여러 값 중 하나인지 검사할 때 사용한다.
- `LIKE`는 문자열 패턴 검색에 사용한다.
- `%`는 0글자 이상, `_`는 한 글자와 매치된다.
- 서브쿼리는 쿼리 안에 들어가는 쿼리이다.
- `ANY`와 `SOME`은 여러 결과 중 하나만 만족해도 된다.
- `ALL`은 여러 결과를 모두 만족해야 한다.
- `ORDER BY`는 출력 순서를 조절한다.
- `DISTINCT`는 중복 값을 제거한다.
- `LIMIT`은 출력 개수를 제한하고 페이지네이션에 사용된다.
- `CREATE TABLE ... SELECT`로 테이블을 복사할 수 있다.
- 테이블 복사 시 PK, FK 같은 제약 조건은 복사되지 않는다.
- `GROUP BY`는 집계 함수와 함께 사용한다.
- `HAVING`은 그룹화된 결과에 조건을 줄 때 사용한다.

## 정리 ✅

SQL 기본에서는 조회문의 핵심인 `SELECT ... FROM`부터 조건, 정렬, 중복 제거, 출력 개수 제한, 그룹화까지 정리해야 한다.  
`USE`로 기본 데이터베이스를 지정하고, `SHOW`, `DESC` 명령으로 데이터베이스와 테이블 구조를 확인할 수 있다.  
`WHERE`절은 원하는 데이터만 조회할 때 사용하며, `BETWEEN`, `IN`, `LIKE`, 서브쿼리, `ANY`, `ALL` 같은 조건 표현을 함께 사용할 수 있다.  
또한 `ORDER BY`는 출력 순서를 조절하고, `DISTINCT`는 중복 제거, `LIMIT`은 출력 개수 제한과 페이지네이션에 사용된다.  
`GROUP BY`는 집계 함수와 함께 그룹별 결과를 만들 때 사용하며, 그룹 결과 조건은 `HAVING`으로 처리한다.
