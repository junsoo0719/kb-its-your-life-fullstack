# ✨ MyBatis와 스프링 연동

## 1. MyBatis와 스프링 연동 개념

MyBatis는 SQL을 직접 작성하면서 Java 객체와 데이터베이스 결과를 매핑할 수 있게 도와주는 persistence framework이다.

스프링과 MyBatis를 연동하면 스프링 컨테이너가 MyBatis 관련 객체를 빈으로 관리할 수 있고, Mapper 인터페이스를 통해 SQL을 더 편하게 호출할 수 있다. 📌

즉, 스프링에서는 MyBatis를 이용해 데이터베이스 접근 코드를 구조적으로 분리하고 관리할 수 있다.

## 2. MyBatis 관련 라이브러리

MyBatis와 스프링을 연동하려면 관련 라이브러리를 추가해야 한다.

대표적으로 필요한 라이브러리는 다음과 같다.

- `spring-jdbc`
- `spring-tx`
- `mybatis`
- `mybatis-spring`

각 라이브러리는 역할이 다르므로 구분해서 이해해야 한다.

## 3. spring-jdbc와 spring-tx

`spring-jdbc`는 스프링에서 데이터베이스 처리를 지원하는 라이브러리이다.

`spring-tx`는 스프링에서 트랜잭션 처리를 지원하는 라이브러리이다.

정리하면 다음과 같다.

- `spring-jdbc` → 데이터베이스 처리 지원
- `spring-tx` → 트랜잭션 처리 지원

즉, 스프링에서 DB 작업과 트랜잭션 관리를 하려면 필요한 라이브러리이다. 📌

## 4. mybatis와 mybatis-spring

`mybatis`는 MyBatis 자체 기능을 사용하기 위한 라이브러리이다.

`mybatis-spring`은 MyBatis와 스프링을 연동하기 위한 라이브러리이다.

정리하면 다음과 같다.

- `mybatis` → MyBatis 핵심 라이브러리
- `mybatis-spring` → MyBatis와 스프링 연동용 라이브러리

즉, 스프링 프로젝트에서 MyBatis를 사용하려면 MyBatis 자체 라이브러리뿐 아니라 스프링 연동 라이브러리도 필요하다. ✅

## 5. SqlSession

`SqlSession`은 MyBatis에서 SQL 실행과 관련된 핵심 객체이다.

`SqlSession`은 다음과 같은 작업을 처리한다.

- 데이터베이스 연결 사용
- SQL 전달
- SQL 실행
- 결과 리턴
- Mapper 호출 처리

즉, `SqlSession`은 MyBatis가 실제 SQL 작업을 수행할 때 사용하는 객체이다. 📌

## 6. SqlSessionFactory

`SqlSessionFactory`는 `SqlSession` 객체를 생성하는 역할을 한다.

즉, `SqlSession`을 직접 만들기보다 `SqlSessionFactory`를 통해 필요한 `SqlSession`을 얻는다.

정리하면 다음과 같다.

- `SqlSessionFactory` → `SqlSession` 생성
- `SqlSession` → SQL 실행, 결과 처리

스프링과 연동하면 `SqlSessionFactory`도 스프링 빈으로 등록해서 관리할 수 있다.

## 7. Mapper

Mapper는 SQL과 그 SQL에 대한 처리를 지정하는 역할을 한다.

MyBatis에서 Mapper는 크게 두 가지 방식으로 작성할 수 있다.

- XML 방식
- 인터페이스 + 어노테이션 방식

XML 방식은 SQL을 XML 파일에 작성하고, 인터페이스와 연결해서 사용한다.

인터페이스 + 어노테이션 방식은 Java 인터페이스의 메서드 위에 SQL 어노테이션을 작성하는 방식이다.

즉, Mapper는 Java 코드와 SQL 사이를 연결하는 역할을 한다. ✅

## 8. Mapper 인터페이스

Mapper 인터페이스는 데이터베이스에 수행할 작업을 메서드로 정의한다.

예를 들면 다음과 같다.

    public interface MemberMapper {
        MemberVO selectMember(String id);
        List<MemberVO> selectAll();
        int insertMember(MemberVO member);
    }

이 인터페이스의 메서드와 실제 SQL이 연결되면, 개발자는 메서드를 호출하는 방식으로 SQL을 실행할 수 있다.

즉, DAO 구현 코드를 직접 많이 작성하지 않아도 Mapper 인터페이스를 통해 DB 작업을 처리할 수 있다.

## 9. @MapperScan

`@MapperScan`은 Mapper 인터페이스를 검색할 패키지 목록을 지정하는 어노테이션이다.

스프링은 `@MapperScan`에 지정된 패키지에서 Mapper 인터페이스를 찾고, 해당 인터페이스를 빈으로 등록한다.

예시는 다음과 같다.

    @MapperScan("org.example.mapper")

이렇게 설정하면 해당 패키지 안의 Mapper 인터페이스를 스프링 빈으로 사용할 수 있다. 📌

## 10. Mapper 구현체 자동 생성

Mapper 인터페이스는 개발자가 직접 구현 클래스를 작성하지 않아도 된다.

MyBatis가 Mapper 인터페이스의 구현체를 동적으로 자동 생성한다.

즉, 개발자는 인터페이스와 SQL만 작성하면 되고, 실제 구현 객체는 MyBatis가 만들어 준다.

정리하면 다음과 같다.

1. 개발자가 Mapper 인터페이스 작성
2. MyBatis가 SQL과 인터페이스 메서드 연결
3. 스프링이 Mapper를 빈으로 등록
4. MyBatis가 구현체를 동적으로 생성
5. 개발자는 Mapper 빈을 주입받아 사용

이 구조 덕분에 데이터베이스 접근 코드가 훨씬 간단해진다. ✅

## 11. JDBC 처리 관련 로그

JDBC로 SQL을 실행할 때 실제로 어떤 SQL이 실행되는지 확인해야 할 때가 있다.

특히 `PreparedStatement`는 SQL에 `?`를 사용한다.

    SELECT *
    FROM member
    WHERE id = ?

이때 실제로 `?`에 어떤 값이 들어갔는지 알아야 디버깅이 쉬워진다.

## 12. log4jdbc-log4j2

`log4jdbc-log4j2`는 JDBC 실행 로그를 더 자세히 출력해 주는 라이브러리이다.

특히 `PreparedStatement`로 SQL을 처리할 때 `?`에 실제로 치환된 값을 출력해 준다.

즉, 다음처럼 파라미터가 실제 값으로 들어간 SQL을 확인할 수 있어 디버깅에 유용하다. 📌

    SELECT *
    FROM member
    WHERE id = 'user01'

이 라이브러리를 사용하면 MyBatis나 JDBC에서 실행되는 SQL을 더 쉽게 확인할 수 있다.

## 13. 로그 레벨 설정

로그가 너무 많이 출력되면 필요한 정보를 찾기 어렵다.

이때 로그 레벨을 설정해서 출력할 로그의 범위를 조절할 수 있다.

로그 레벨은 다음과 같다.

- `FATAL`
- `ERROR`
- `WARN`
- `INFO`
- `DEBUG`
- `TRACE`

위로 갈수록 심각한 로그이고, 아래로 갈수록 더 자세한 로그이다.

## 14. 로그 레벨 종류

각 로그 레벨의 의미는 다음과 같다.

- `FATAL` → 가장 크리티컬한 에러가 일어났을 때만 로깅
- `ERROR` → 일반 에러가 일어났을 때만 로깅
- `WARN` → 에러는 아니지만 주의할 필요가 있을 때 로깅
- `INFO` → 일반 정보를 나타낼 때 로깅
- `DEBUG` → 일반 정보를 상세히 나타낼 때 로깅
- `TRACE` → 경로 추적을 위해 사용

즉, 개발 중에는 `DEBUG`나 `TRACE`가 유용할 수 있고, 운영 환경에서는 보통 `INFO`나 `WARN` 이상으로 조절한다. 📌

## 15. log4jdbc 출력 로그 조절

`log4jdbc`는 다양한 JDBC 관련 로그를 출력할 수 있다.

하지만 모든 로그를 출력하면 너무 많은 메시지가 나타날 수 있다.

그래서 `log4j2.xml`에서 `<Logger>` 태그를 이용해 출력할 로그를 조절한다.

예를 들어 핵심 SQL 로그만 보고 싶다면 `jdbc.sqlonly` 로그를 설정할 수 있다.

    <Logger name="jdbc.sqlonly" level="info" />

`jdbc.sqlonly`는 실행된 SQL 중심의 로그를 출력할 때 사용한다. ✅

## 16. 테스트용 log4j2.xml 설정

테스트 환경에서는 불필요한 로그를 줄이기 위해 특정 로그 레벨을 `warn`으로 설정할 수 있다.

대표적으로 다음 로그들이 있다.

- `jdbc.audit`
- `jdbc.connection`
- `jdbc.resultset`

이 로그들은 너무 자세한 정보를 출력할 수 있으므로, 핵심 SQL만 확인하고 싶다면 `warn`으로 낮추는 것이 좋다.

예시는 다음과 같다.

    <Logger name="jdbc.audit" level="warn" />
    <Logger name="jdbc.connection" level="warn" />
    <Logger name="jdbc.resultset" level="warn" />

이렇게 설정하면 불필요한 로그는 줄이고 필요한 SQL 로그만 확인할 수 있다. 📌

## 17. 전체 연동 흐름

MyBatis와 스프링 연동 흐름은 다음과 같이 정리할 수 있다.

1. MyBatis 관련 라이브러리를 추가한다.
2. `DataSource`를 설정한다.
3. `SqlSessionFactory`를 설정한다.
4. Mapper 인터페이스를 작성한다.
5. `@MapperScan`으로 Mapper 패키지를 지정한다.
6. 스프링이 Mapper 인터페이스를 빈으로 등록한다.
7. MyBatis가 Mapper 구현체를 동적으로 생성한다.
8. 필요한 곳에서 Mapper 빈을 주입받아 SQL을 실행한다.
9. log4jdbc와 로그 레벨 설정으로 SQL 실행 로그를 확인한다.

즉, 스프링은 객체 관리와 의존성 주입을 담당하고, MyBatis는 SQL 실행과 결과 매핑을 담당한다. ✅

## 18. 중요 포인트 📌

- MyBatis와 스프링을 연동하려면 관련 라이브러리가 필요하다.
- `spring-jdbc`는 스프링의 데이터베이스 처리를 지원한다.
- `spring-tx`는 스프링의 트랜잭션 처리를 지원한다.
- `mybatis`는 MyBatis 핵심 라이브러리이다.
- `mybatis-spring`은 MyBatis와 스프링 연동용 라이브러리이다.
- `SqlSession`은 Connection 생성, SQL 전달, 결과 리턴 등을 처리한다.
- `SqlSessionFactory`는 `SqlSession` 객체를 생성한다.
- Mapper는 SQL과 그 처리를 지정하는 역할을 한다.
- Mapper는 XML 또는 인터페이스 + 어노테이션 방식으로 작성할 수 있다.
- `@MapperScan`은 Mapper 인터페이스를 검색할 패키지를 지정한다.
- `@MapperScan`으로 찾은 Mapper 인터페이스는 스프링 빈으로 등록된다.
- MyBatis는 Mapper 인터페이스의 구현체를 동적으로 자동 생성한다.
- `log4jdbc-log4j2`는 `PreparedStatement`의 `?`에 치환된 실제 값을 출력해 준다.
- 로그 레벨을 설정하면 너무 많은 로그 출력을 조절할 수 있다.
- 핵심 SQL 로그만 보고 싶다면 `jdbc.sqlonly`를 `info`로 설정할 수 있다.
- `jdbc.audit`, `jdbc.connection`, `jdbc.resultset` 등은 테스트 환경에서 `warn`으로 조절할 수 있다.

## 정리 ✅

MyBatis와 스프링 연동에서는 라이브러리 추가, `SqlSessionFactory`, `SqlSession`, Mapper, `@MapperScan`, SQL 로그 설정이 핵심이다.  
`spring-jdbc`와 `spring-tx`는 스프링의 DB 처리와 트랜잭션 처리를 담당하고, `mybatis`와 `mybatis-spring`은 MyBatis 기능과 스프링 연동을 담당한다.  
`SqlSessionFactory`는 `SqlSession`을 생성하고, `SqlSession`은 SQL 실행과 결과 반환을 처리한다.  
Mapper는 SQL과 Java 메서드를 연결하는 역할을 하며, `@MapperScan`을 통해 Mapper 인터페이스를 찾아 스프링 빈으로 등록하고 구현체를 동적으로 생성한다.  
또한 `log4jdbc-log4j2`를 사용하면 `PreparedStatement`의 `?`에 들어간 실제 값을 확인할 수 있고, 로그 레벨 설정으로 필요한 로그만 출력할 수 있다.
