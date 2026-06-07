# ✨ 테이블

## 1. 데이터베이스 만들기

MySQL에서 테이블을 만들기 전에는 먼저 데이터베이스를 생성하고 사용할 데이터베이스를 선택해야 한다.

기본 흐름은 다음과 같다.

    DROP DATABASE tabledb;
    CREATE DATABASE tabledb;
    USE tabledb;

`DROP DATABASE`는 기존 데이터베이스를 삭제하고, `CREATE DATABASE`는 새 데이터베이스를 생성한다.  
`USE`는 이후 SQL 문을 실행할 기본 데이터베이스를 선택한다. 📌

필기에는 `USE tabeldb;`로 적혀 있지만, 앞에서 만든 데이터베이스 이름이 `tabledb`이므로 정확히는 다음처럼 작성해야 한다.

    USE tabledb;

## 2. usertbl 만들기

회원 정보를 저장할 테이블은 다음과 같이 만들 수 있다.

    DROP TABLE IF EXISTS usertbl;

    CREATE TABLE usertbl(
        userID CHAR(8) NOT NULL PRIMARY KEY,
        name VARCHAR(10) NOT NULL,
        birthYear INT NOT NULL,
        addr CHAR(2) NOT NULL,
        mobile1 CHAR(3) NULL,
        mobile2 CHAR(8) NULL,
        height SMALLINT NULL,
        mDate DATE NULL
    );

`DROP TABLE IF EXISTS`는 해당 테이블이 존재하면 삭제한다.  
테이블이 없을 때 발생할 수 있는 오류를 방지할 수 있다.

`usertbl`에서는 `userID`가 기본 키이다.  
따라서 `userID`는 중복될 수 없고 `NULL`도 허용되지 않는다. ✅

## 3. buytbl 만들기

구매 정보를 저장할 테이블은 다음과 같이 만들 수 있다.

    DROP TABLE IF EXISTS buytbl;

    CREATE TABLE buytbl(
        num INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
        userid CHAR(8) NOT NULL,
        prodName CHAR(6) NOT NULL,
        groupName CHAR(4) NULL,
        price INT NOT NULL,
        amount SMALLINT NOT NULL,
        FOREIGN KEY(userid) REFERENCES usertbl(userID)
    );

`num`은 `AUTO_INCREMENT`가 적용된 기본 키이다.  
새 데이터가 입력될 때마다 번호가 자동으로 증가한다.

`userid`는 `usertbl`의 `userID`를 참조하는 외래 키이다.  
따라서 `buytbl`에 구매 데이터를 넣으려면 해당 사용자가 `usertbl`에 먼저 존재해야 한다. 📌

## 4. 회원 테이블 데이터 입력

회원 데이터는 `INSERT INTO` 문으로 입력한다.

    INSERT INTO usertbl VALUES('LSG', '이승기', 1987, '서울', '011', '1111111', 182, '2008-8-8');
    INSERT INTO usertbl VALUES('KBS', '김범수', 1979, '경남', '011', '2222222', 173, '2012-4-4');
    INSERT INTO usertbl VALUES('KKH', '김경호', 1971, '전남', '019', '3333333', 177, '2007-7-7');

여러 행을 하나의 `INSERT` 문으로 묶어서 입력할 수도 있다.

    INSERT INTO usertbl VALUES
        ('LSG', '이승기', 1987, '서울', '011', '1111111', 182, '2008-8-8'),
        ('KBS', '김범수', 1979, '경남', '011', '2222222', 173, '2012-4-4'),
        ('KKH', '김경호', 1971, '전남', '019', '3333333', 177, '2007-7-7');

여러 행을 한 번에 입력하면 반복되는 SQL 문을 줄일 수 있다.

## 5. 구매 테이블 데이터 입력

구매 테이블에는 다음과 같이 데이터를 입력한다.

    INSERT INTO buytbl VALUES(NULL, 'KBS', '운동화', NULL, 30, 2);
    INSERT INTO buytbl VALUES(NULL, 'KBS', '노트북', '전자', 1000, 1);
    INSERT INTO buytbl VALUES(NULL, 'JYP', '모니터', '전자', 200, 1);

여기서 첫 번째 값인 `num`은 `AUTO_INCREMENT` 컬럼이므로 `NULL`을 넣으면 자동으로 번호가 생성된다.

하지만 세 번째 행은 오류가 발생한다.

    INSERT INTO buytbl VALUES(NULL, 'JYP', '모니터', '전자', 200, 1);

이유는 `JYP`라는 `userid`가 `usertbl`에 존재하지 않기 때문이다.  
`buytbl.userid`는 `usertbl.userID`를 참조하는 외래 키이므로, 기준 테이블에 없는 사용자는 구매 테이블에 입력할 수 없다. ⚠️

## 6. 제약 조건

제약 조건은 데이터의 무결성을 지키기 위한 제한 조건이다.

즉, 데이터를 입력하거나 수정할 때 특정 조건을 만족해야만 처리되도록 제한한다.

예를 들어 동일한 아이디로 다시 회원 가입을 할 수 없도록 막는 것이 제약 조건의 역할이다.

대표적인 제약 조건은 다음과 같다.

- `PRIMARY KEY`
- `FOREIGN KEY`
- `UNIQUE`
- `CHECK`
- `DEFAULT`
- `NULL` 허용 여부

제약 조건은 잘못된 데이터가 테이블에 들어오는 것을 막아 데이터의 정확성과 일관성을 유지한다. 📌

## 7. 기본 키 제약 조건

기본 키는 테이블에 존재하는 많은 행을 구분할 수 있는 식별자이다.

기본 키의 특징은 다음과 같다.

- 중복 불가
- `NULL` 불가
- 테이블당 1개만 존재
- 자동으로 클러스터형 인덱스 생성

기본 키로 지정된 컬럼은 테이블의 각 행을 구분하는 기준이 된다.  
따라서 반드시 고유한 값이어야 한다. ✅

## 8. 컬럼 레벨 기본 키 설정

기본 키는 컬럼을 선언하면서 바로 지정할 수 있다.

    CREATE TABLE usertbl(
        userID CHAR(8) NOT NULL PRIMARY KEY,
        name VARCHAR(10) NOT NULL
    );

이 방식은 하나의 컬럼을 기본 키로 지정할 때 간단하게 사용할 수 있다.

## 9. 테이블 레벨 기본 키 설정

기본 키는 테이블 레벨에서도 설정할 수 있다.

    DROP TABLE IF EXISTS usertbl2;

    CREATE TABLE usertbl2 (
        userID CHAR(8) NOT NULL,
        name VARCHAR(10) NOT NULL,
        birthYear INT NOT NULL,
        CONSTRAINT PK_userTBL_userID PRIMARY KEY(userID)
    );

`CONSTRAINT` 키워드는 제약 조건 이름을 지정할 때 사용한다.  
제약 조건 이름은 생략할 수도 있으며, 생략하면 시스템이 자동으로 이름을 부여한다.

관례적으로 제약 조건명은 다음처럼 작성할 수 있다.

    제약조건종류_테이블명_컬럼명

예를 들면 다음과 같다.

    PK_userTBL_userID

## 10. 복합 키

기본 키는 하나의 컬럼뿐 아니라 여러 컬럼을 조합해서 만들 수도 있다.

예를 들어 제품 테이블에서 제품코드만으로 식별이 어렵다면 다음처럼 조합할 수 있다.

    제품코드 + 제품일련번호

이렇게 여러 컬럼을 묶어 기본 키로 사용하는 것을 복합 키라고 한다.

복합 키는 반드시 테이블 레벨에서 설정해야 한다.  
컬럼 레벨에서는 여러 컬럼을 묶어 기본 키로 지정할 수 없다. ⚠️

## 11. 인덱스 확인

테이블에 생성된 인덱스는 다음 명령으로 확인할 수 있다.

    SHOW INDEX FROM prodTbl;

기본 키를 생성하면 클러스터형 인덱스가 자동으로 생성된다.  
따라서 기본 키 기준으로 테이블 데이터가 정렬되어 관리된다.

## 12. 외래 키 제약 조건

외래 키는 두 테이블 사이의 관계를 선언하여 데이터 무결성을 보장하는 제약 조건이다.

외래 키 관계를 설정하면 한 테이블이 다른 테이블에 의존하게 된다.

외래 키의 기준 테이블에서 참조되는 열은 반드시 다음 중 하나여야 한다.

- `PRIMARY KEY`
- `UNIQUE`

즉, 외래 키는 기준 테이블의 고유한 값을 참조해야 한다. 📌

## 13. 외래 키 생성 방법 1

테이블을 만들 때 외래 키를 함께 지정할 수 있다.

    CREATE TABLE buyTBL (
        num INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
        userID CHAR(8) NOT NULL,
        prodName CHAR(6) NOT NULL,
        FOREIGN KEY(userID) REFERENCES userTBL(userID)
    );

이 방식은 테이블 생성 시점에 외래 키 관계를 함께 설정한다.

## 14. 외래 키 생성 방법 2

이미 생성된 테이블에 외래 키를 추가할 수도 있다.

    ALTER TABLE buyTBL
    ADD CONSTRAINT FK_userTBL_buyTBL
    FOREIGN KEY(userID)
    REFERENCES userTBL(userID);

`ALTER TABLE`은 기존 테이블의 구조나 제약 조건을 변경할 때 사용한다.

## 15. 외래 키 옵션

외래 키에는 `ON DELETE CASCADE`, `ON UPDATE CASCADE` 같은 옵션을 설정할 수 있다.

이 옵션은 기준 테이블의 데이터가 삭제되거나 변경되었을 때, 외래 키 테이블에도 자동으로 반영되도록 한다.

예를 들어 기준 테이블의 회원 아이디가 변경되면 구매 테이블의 아이디도 함께 변경되도록 할 수 있다.

다만 자동으로 연쇄 변경 또는 삭제가 발생하므로 사용할 때 주의해야 한다. ⚠️

## 16. UNIQUE 제약 조건

`UNIQUE` 제약 조건은 중복되지 않는 유일한 값을 입력해야 하는 조건이다.

기본 키와 비슷하지만 차이가 있다.

- `PRIMARY KEY` → 중복 불가, `NULL` 불가
- `UNIQUE` → 중복 불가, `NULL` 허용

`UNIQUE`는 `NULL` 값을 여러 개 허용할 수 있다.

예를 들어 이메일 주소는 중복되면 안 되므로 `UNIQUE`로 설정할 수 있다.

    CREATE TABLE userTBL (
        userID CHAR(8) NOT NULL PRIMARY KEY,
        name VARCHAR(10) NOT NULL,
        birthYear INT NOT NULL,
        email CHAR(30) NULL UNIQUE
    );

## 17. CHECK 제약 조건

`CHECK` 제약 조건은 입력되는 데이터를 점검하는 기능이다.

예를 들어 출생 연도가 특정 범위 안에 들어오도록 제한할 수 있다.

    CREATE TABLE userTBL(
        userID CHAR(8) PRIMARY KEY,
        name VARCHAR(10),
        birthYear INT CHECK (birthYear >= 1900 AND birthYear <= 2023),
        mobile1 CHAR(3) NULL,
        CONSTRAINT CK_name CHECK (name IS NOT NULL)
    );

`CHECK`는 잘못된 값이 입력되지 않도록 조건을 걸 때 사용한다. ✅

## 18. DEFAULT 정의

`DEFAULT`는 값을 생략했을 때 자동으로 입력되는 기본값을 정의하는 방법이다.

    CREATE TABLE userTBL(
        userID CHAR(8) NOT NULL PRIMARY KEY,
        name VARCHAR(10) NOT NULL,
        birthYear INT NOT NULL DEFAULT -1,
        addr CHAR(2) NOT NULL DEFAULT '서울',
        mobile1 CHAR(3) NULL,
        mobile2 CHAR(8) NULL,
        height SMALLINT NULL DEFAULT 170,
        mDate DATE NULL
    );

필기에는 `mobi1e2`처럼 보이는 부분이 있는데, 문맥상 `mobile2`가 맞다.

`DEFAULT`가 설정된 열에는 다음처럼 `DEFAULT` 키워드를 사용해서 값을 입력할 수 있다.

    INSERT INTO usertbl VALUES (
        'LHL', '이혜리', DEFAULT, DEFAULT, '011', '1234567',
        DEFAULT, '2023.12.12'
    );

또한 열 이름이 명시되지 않은 경우, `DEFAULT`로 설정된 값이 자동으로 입력될 수 있다.

    INSERT INTO usertbl(userID, name) VALUES('KAY', '김아영');

## 19. NULL 값 허용

`NULL`은 아무 값도 없다는 의미이다.

공백 문자열 `''`이나 숫자 `0`과는 다르다.

컬럼에 `NULL`을 허용하려면 `NULL`, 허용하지 않으려면 `NOT NULL`을 사용한다.

    name VARCHAR(10) NOT NULL
    mobile1 CHAR(3) NULL

기본 키가 설정된 열은 `NOT NULL`을 생략해도 자동으로 `NOT NULL`이 적용된다. 📌

## 20. 테이블 삭제

테이블을 삭제할 때는 `DROP TABLE` 문을 사용한다.

    DROP TABLE 테이블이름;

외래 키 제약 조건의 기준 테이블은 바로 삭제할 수 없다.  
먼저 외래 키가 생성된 외래 키 테이블을 삭제해야 한다.

예를 들어 `buytbl`이 `usertbl`을 참조하고 있다면, `usertbl`을 먼저 삭제할 수 없다.

삭제 순서는 다음과 같다.

1. 외래 키 테이블 삭제
2. 기준 테이블 삭제

동시에 여러 테이블을 삭제하는 것도 가능하다.

## 21. 테이블 수정 ALTER TABLE

`ALTER TABLE`은 이미 만들어진 테이블에 무엇인가를 추가, 변경, 수정, 삭제할 때 사용한다.

즉, 테이블 구조를 바꾸는 명령이다.

대표적인 작업은 다음과 같다.

- 열 추가
- 열 삭제
- 열 이름 변경
- 데이터 형식 변경
- 제약 조건 추가
- 제약 조건 삭제

## 22. 열 추가

열을 추가할 때는 `ADD`를 사용한다.

기본적으로 새 열은 가장 뒤에 추가된다.

    ALTER TABLE usertbl
    ADD homepage VARCHAR(30)
    DEFAULT 'http://www.hanbit.co.kr'
    NULL;

열 순서를 지정하려면 `FIRST` 또는 `AFTER 열이름`을 사용할 수 있다.

즉, 열을 맨 앞에 넣거나 특정 열 뒤에 넣을 수 있다.

## 23. 열 삭제

열을 삭제할 때는 `DROP COLUMN`을 사용한다.

    ALTER TABLE usertbl
    DROP COLUMN mobile1;

단, 제약 조건이 걸린 열을 삭제할 경우에는 먼저 제약 조건을 삭제한 후 열을 삭제해야 한다. ⚠️

## 24. 열 이름 및 데이터 형식 변경

열 이름이나 데이터 형식을 변경할 때는 `CHANGE COLUMN`을 사용한다.

    ALTER TABLE usertbl
    CHANGE COLUMN name uName VARCHAR(20) NULL;

이 구문은 `name` 컬럼명을 `uName`으로 바꾸고, 데이터 형식도 `VARCHAR(20)`으로 변경한다.

정보 손실이 없으면 변경이 허용될 수 있지만, 정보 손실 가능성이 있으면 변경이 불허될 수 있다.

예를 들어 긴 문자열을 짧은 문자열 타입으로 줄이면 기존 데이터가 잘릴 수 있으므로 주의해야 한다. 📌

## 25. 제약 조건 추가 및 삭제

제약 조건도 `ALTER TABLE`로 추가하거나 삭제할 수 있다.

기본 키를 삭제하려면 다음처럼 작성할 수 있다.

    ALTER TABLE usertbl
    DROP PRIMARY KEY;

하지만 오류가 발생할 수 있다.

예를 들어 `usertbl`의 기본 키인 `userID` 열이 `buytbl`에 외래 키로 연결되어 있다면, 기본 키를 바로 삭제할 수 없다.

이 경우 먼저 외래 키 제약 조건을 제거한 후 기본 키를 제거해야 한다. ⚠️

## 26. 중요 포인트 📌

- 데이터베이스는 `CREATE DATABASE`로 생성하고 `USE`로 선택한다.
- 테이블은 `CREATE TABLE`로 생성한다.
- `DROP TABLE IF EXISTS`는 테이블이 있을 때만 삭제한다.
- `AUTO_INCREMENT`는 숫자 값을 자동 증가시킨다.
- 외래 키가 참조하는 값은 기준 테이블에 먼저 존재해야 한다.
- 제약 조건은 데이터 무결성을 지키기 위한 제한 조건이다.
- 기본 키는 중복과 `NULL`을 허용하지 않는다.
- 기본 키는 테이블당 1개만 존재한다.
- 기본 키를 만들면 클러스터형 인덱스가 자동 생성된다.
- 복합 키는 반드시 테이블 레벨에서 설정한다.
- 외래 키는 두 테이블 사이의 관계를 선언한다.
- 외래 키가 참조하는 기준 열은 `PRIMARY KEY` 또는 `UNIQUE`여야 한다.
- `UNIQUE`는 중복을 허용하지 않지만 `NULL`은 허용한다.
- `CHECK`는 입력값 조건을 검사한다.
- `DEFAULT`는 값을 생략했을 때 들어갈 기본값을 지정한다.
- `NULL`은 공백이나 0이 아니라 값이 없다는 의미이다.
- 외래 키의 기준 테이블은 참조 중이면 바로 삭제할 수 없다.
- `ALTER TABLE`은 테이블 구조 변경에 사용한다.
- 제약 조건이 걸린 열은 제약 조건을 먼저 삭제한 뒤 삭제해야 한다.

## 정리 ✅

테이블 단원에서는 데이터베이스 생성, 테이블 생성, 데이터 입력, 제약 조건, 테이블 수정과 삭제가 핵심이다.  
`CREATE TABLE`을 사용해 테이블을 만들고, `PRIMARY KEY`, `FOREIGN KEY`, `UNIQUE`, `CHECK`, `DEFAULT`, `NULL` 허용 여부를 통해 데이터 무결성을 관리한다.  
기본 키는 행을 식별하는 기준이며 중복과 `NULL`을 허용하지 않고, 외래 키는 두 테이블 사이의 관계를 설정해 기준 테이블에 없는 값이 입력되지 않도록 한다.  
또한 `ALTER TABLE`을 사용하면 열을 추가, 삭제, 변경하거나 제약 조건을 추가·삭제할 수 있다.  
시험 대비에서는 기본 키와 외래 키의 차이, 복합 키 설정 방식, `UNIQUE`와 `PRIMARY KEY` 차이, `CHECK`, `DEFAULT`, `NULL`, 외래 키가 있는 테이블 삭제 순서를 함께 정리해 두는 것이 중요하다.
