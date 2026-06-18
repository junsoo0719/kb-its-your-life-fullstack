# ✨ 영속, 비즈니스 계층의 CRUD 구현

## 1. 영속 계층과 비즈니스 계층

게시판 기능을 구현할 때는 보통 계층을 나누어 개발한다.

영속 계층은 데이터베이스와 직접 연결되는 계층이다.  
즉, SQL을 실행하고 데이터를 저장, 조회, 수정, 삭제하는 역할을 한다.

비즈니스 계층은 실제 서비스 로직을 처리하는 계층이다.  
영속 계층을 직접 화면에서 호출하지 않고, 서비스 계층을 통해 필요한 작업을 수행한다.

정리하면 다음과 같다.

- 영속 계층 → DB 접근, SQL 실행
- 비즈니스 계층 → 서비스 로직 처리
- CRUD → Create, Read, Update, Delete

즉, 게시판의 등록, 조회, 수정, 삭제 기능은 영속 계층과 비즈니스 계층을 나누어 구현하는 것이 좋다. 📌

## 2. VO 프로퍼티명과 테이블 컬럼명 불일치

Java의 VO 객체 프로퍼티명과 데이터베이스 테이블 컬럼명이 다를 수 있다.

예를 들면 다음과 같다.

    updateDate -> update_date

Java에서는 보통 camelCase를 사용한다.

    updateDate

반면 데이터베이스 컬럼명은 snake_case를 많이 사용한다.

    update_date

이렇게 이름이 다르면 MyBatis가 자동으로 매핑하지 못할 수 있다. ⚠️

## 3. MyBatis 설정으로 컬럼명 매핑

VO 객체의 프로퍼티명과 테이블 컬럼명이 다를 때는 MyBatis 설정 파일에서 관련 설정을 할 수 있다.

대표적으로 underscore 방식 컬럼명을 camelCase 프로퍼티명으로 자동 매핑하도록 설정할 수 있다.

예를 들어 `update_date` 컬럼을 `updateDate` 프로퍼티로 매핑하는 방식이다.

즉, DB 컬럼명과 Java 프로퍼티명이 다를 경우 MyBatis 설정을 통해 매핑 문제를 해결할 수 있다. ✅

## 4. Mapper XML

MyBatis에서는 SQL을 작성하는 방법으로 어노테이션 방식과 XML 방식이 있다.

간단한 쿼리는 `@Select()` 같은 어노테이션으로 작성할 수 있다.

하지만 쿼리가 복잡해지면 어노테이션 안에 SQL을 작성하는 것이 매우 어려워진다.

이때는 `resources` 영역에 Mapper XML 파일을 만들고, 그 안에 SQL을 작성한다. 📌

## 5. Mapper XML을 사용하는 이유

Mapper XML을 사용하면 복잡한 SQL을 더 읽기 좋게 작성할 수 있다.

예를 들어 다음과 같은 경우 XML 방식이 더 적합하다.

- 조인이 많은 쿼리
- 조건문이 포함된 동적 SQL
- 긴 INSERT, UPDATE 문
- CDATA 처리가 필요한 SQL
- 여러 줄로 작성해야 하는 쿼리

즉, 복잡한 SQL은 Mapper XML로 분리하는 것이 유지보수에 좋다.

## 6. Mapper XML 위치와 이름

Mapper XML은 Mapper 인터페이스와 연계되어야 한다.

작성 규칙은 다음과 같다.

- `resources` 영역에 XML 작성
- Mapper 인터페이스의 패키지 경로와 동일한 경로에 작성
- Mapper 인터페이스와 동일한 파일명으로 작성

예를 들어 Mapper 인터페이스가 다음 위치에 있다면

    org.example.board.mapper.BoardMapper

XML 파일은 `resources` 아래에 다음처럼 둘 수 있다.

    resources/org/example/board/mapper/BoardMapper.xml

즉, Mapper 인터페이스의 경로와 XML 경로를 맞춰야 MyBatis가 연결하기 쉽다. ✅

## 7. CDATA

Mapper XML에서 SQL을 작성할 때 `<`, `>` 같은 문자는 XML 태그로 해석될 수 있다.

이 문제를 막기 위해 `CDATA`를 사용할 수 있다.

형식은 다음과 같다.

    <![CDATA[
        SQL 문자열
    ]]>

`CDATA`는 해당 영역의 내용을 이미 컴파일한 데이터처럼 취급하여 XML 태그로 해석하지 않도록 한다.

즉, SQL 문자열 안의 `<`, `>` 같은 문자가 XML 문법으로 처리되지 않게 한다. 📌

## 8. CDATA 사용 기준

SQL 안에 `<`, `>` 같은 비교 연산자가 들어간다면 `CDATA`를 사용하는 것이 안전하다.

예를 들면 다음과 같다.

    <![CDATA[
        SELECT *
        FROM tbl_board
        WHERE no > 10
    ]]>

하지만 SQL 문자열 안에 XML 태그로 오해될 문자가 없다면 `CDATA`는 생략할 수 있다.

즉, 태그 문자가 없다면 반드시 사용할 필요는 없다.

## 9. Create 처리

CRUD 중 Create는 데이터를 새로 등록하는 작업이다.

게시판에서는 새 게시글을 등록하는 기능이 Create에 해당한다.

MyBatis에서는 `insert` 구문을 사용하여 게시글 데이터를 테이블에 저장한다.

기본 흐름은 다음과 같다.

1. BoardVO 객체에 게시글 데이터 저장
2. Mapper의 insert 메서드 호출
3. Mapper XML의 insert SQL 실행
4. 데이터베이스에 게시글 등록

## 10. PK 자동 생성 처리

게시글을 등록할 때 PK가 자동으로 정해지는 경우가 있다.

예를 들어 시퀀스나 자동 증가 값을 이용해 게시글 번호가 생성될 수 있다.

PK 처리 방식은 크게 두 가지로 나눌 수 있다.

- insert만 처리하고 생성된 PK 값을 알 필요가 없는 경우
- insert 실행 후 생성된 PK 값을 알아야 하는 경우

두 번째 경우는 추가 작업에서 방금 생성된 PK가 필요할 때이다. 📌

## 11. 생성된 PK 값이 필요한 경우

insert 후 생성된 PK 값을 알아야 하는 대표적인 경우는 후속 작업에서 해당 키를 외래 키로 사용해야 할 때이다.

예를 들어 게시글을 등록한 뒤 첨부파일을 저장한다고 하자.

첨부파일 테이블은 게시글 번호를 외래 키로 가질 수 있다.

이 경우 게시글 insert 후 생성된 게시글 번호를 알아야 첨부파일 테이블에 저장할 수 있다.

즉, insert 후 생성된 PK는 다른 테이블의 FK로 후속 작업을 할 때 중요하다. ✅

## 12. selectKey

MyBatis의 `<selectKey>`는 SQL이 실행되기 전이나 후에 별도의 PK 값을 얻기 위해 사용한다.

예를 들어 insert 전에 시퀀스 값을 먼저 조회해서 VO 객체에 넣을 수 있다.

기본적으로 다음과 같은 역할을 한다.

- PK 값 미리 조회
- 조회한 PK 값을 VO 객체 프로퍼티에 세팅
- insert SQL에서 해당 PK 값 사용

즉, `<selectKey>`는 insert와 PK 생성을 연결하는 기능이다. 📌

## 13. order 속성

`<selectKey>`의 `order` 속성은 selectKey 구문을 언제 실행할지 지정한다.

값은 다음과 같다.

- `BEFORE` → insert문 실행 전에 selectKey 구문 실행
- `AFTER` → insert문 실행 후에 selectKey 구문 실행

예를 들어 insert 전에 PK 값을 먼저 가져와야 한다면 다음처럼 설정한다.

    order="BEFORE"

필기에서는 `order='before'`로 적혀 있지만, 일반적으로 MyBatis XML에서는 대문자인 `BEFORE`, `AFTER`로 작성하는 것이 명확하다.

## 14. keyProperty

`keyProperty`는 selectKey로 얻은 PK 값을 어떤 VO 프로퍼티에 넣을지 지정한다.

예를 들어 BoardVO의 `no` 프로퍼티에 PK 값을 넣고 싶다면 다음처럼 작성한다.

    keyProperty="no"

즉, selectKey 결과가 BoardVO의 `no` 값으로 세팅된다. ✅

## 15. keyColumn

`keyColumn`은 테이블에서 PK에 해당하는 컬럼명을 지정한다.

예를 들어 테이블의 PK 컬럼명이 `no`라면 다음처럼 작성할 수 있다.

    keyColumn="no"

즉, 데이터베이스 컬럼 기준으로 어떤 값을 PK로 볼지 지정하는 속성이다.

## 16. resultType

`resultType`은 selectKey 실행 결과의 타입을 지정한다.

VO 객체의 프로퍼티 타입과 맞춰 작성해야 한다.

예를 들어 `no` 프로퍼티가 `Long` 타입이라면 다음처럼 작성할 수 있다.

    resultType="long"

또는 상황에 따라 `java.lang.Long`처럼 명시할 수 있다.

즉, selectKey로 조회한 값이 어떤 Java 타입으로 변환될지 지정한다. 📌

## 17. statementType

`statementType`은 SQL 실행 방식을 지정한다.

선택 가능한 값은 다음과 같다.

- `STATEMENT`
- `PREPARED`
- `CALLABLE`

기본값은 `PREPARED`이다.

각 의미는 다음과 같다.

- `STATEMENT` → 일반 Statement 방식
- `PREPARED` → PreparedStatement 방식
- `CALLABLE` → CallableStatement 방식, 주로 프로시저 호출

보통은 기본값인 `PREPARED`를 사용한다. ✅

## 18. selectKey 예시

게시글 등록 전에 PK 값을 가져오는 예시는 다음과 같다.

    <insert id="insert" parameterType="BoardVO">
        <selectKey keyProperty="no"
                   keyColumn="no"
                   resultType="long"
                   order="BEFORE">
            SELECT seq_board.NEXTVAL FROM dual
        </selectKey>

        INSERT INTO tbl_board (no, title, content, writer)
        VALUES (#{no}, #{title}, #{content}, #{writer})
    </insert>

이 예시는 insert 전에 시퀀스 값을 조회해서 `BoardVO.no`에 저장한 뒤, insert문에서 `#{no}`로 사용하는 구조이다.

MySQL에서 `AUTO_INCREMENT`를 사용하는 경우에는 방식이 달라질 수 있으므로 사용하는 DB의 PK 생성 방식에 맞게 설정해야 한다. ⚠️

## 19. 중요 포인트 📌

- 영속 계층은 데이터베이스 접근과 SQL 실행을 담당한다.
- 비즈니스 계층은 서비스 로직을 담당한다.
- VO 프로퍼티명과 테이블 컬럼명이 다르면 MyBatis 설정이 필요할 수 있다.
- `updateDate`와 `update_date`처럼 camelCase와 snake_case가 불일치할 수 있다.
- 복잡한 쿼리는 Mapper XML로 작성하는 것이 좋다.
- Mapper XML은 Mapper 인터페이스의 패키지 경로와 동일하게 작성하는 것이 좋다.
- `CDATA`는 XML에서 `<`, `>` 같은 문자가 태그로 해석되는 것을 막는다.
- 태그로 오해될 문자가 없다면 `CDATA`는 생략 가능하다.
- Create는 insert 처리이다.
- insert 후 생성된 PK 값을 알아야 하는 경우가 있다.
- 생성된 PK는 첨부파일 같은 후속 테이블에서 FK로 사용할 수 있다.
- `<selectKey>`는 insert 전후에 별도의 PK 값을 얻기 위해 사용한다.
- `keyProperty`는 PK 값을 세팅할 VO 프로퍼티명이다.
- `keyColumn`은 테이블의 PK 컬럼명이다.
- `resultType`은 selectKey 결과 타입이다.
- `order`는 selectKey 실행 시점을 지정한다.
- `statementType`의 기본값은 `PREPARED`이다.

## 정리 ✅

영속, 비즈니스 계층의 CRUD 구현에서는 MyBatis Mapper XML과 insert 시 PK 처리 방식이 중요하다.  
VO 객체의 프로퍼티명과 테이블 컬럼명이 `updateDate`, `update_date`처럼 다를 수 있으므로 MyBatis 설정을 통해 매핑 문제를 해결해야 한다.  
복잡한 SQL은 `@Select()` 같은 어노테이션보다 Mapper XML로 작성하는 것이 유지보수에 좋고, XML 안에서 `<`, `>` 문자가 문제될 때는 `CDATA`를 사용할 수 있다.  
Create 처리에서는 insert만 필요한 경우와 insert 후 생성된 PK가 필요한 경우를 구분해야 한다.  
특히 첨부파일처럼 게시글 번호를 FK로 사용하는 후속 작업이 있다면 `<selectKey>`를 이용해 insert 전후에 PK 값을 얻고 VO 객체에 세팅하는 방식이 필요하다.
