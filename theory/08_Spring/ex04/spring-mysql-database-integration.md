# ✨ 스프링과 MySQL Database 연동

## 1. 스프링과 MySQL 연동 개념

스프링 애플리케이션에서 MySQL 데이터베이스를 사용하려면 JDBC 연결 설정이 필요하다.

즉, 스프링 프로젝트에서 MySQL에 접속할 수 있도록 다음 작업을 준비해야 한다.

- 데이터베이스 생성
- 사용자 계정 생성
- 권한 설정
- JDBC 드라이버 설정
- `DataSource` 설정
- 연결 테스트 코드 작성

스프링에서는 직접 `DriverManager`로 매번 연결하는 방식보다 `DataSource`와 Connection Pool을 사용하는 방식이 일반적이다. 📌

## 2. 데이터베이스 생성 및 계정 생성

MySQL을 사용하려면 먼저 프로젝트에서 사용할 데이터베이스를 생성해야 한다.

예시는 다음과 같다.

    CREATE DATABASE 데이터베이스명;

그리고 해당 데이터베이스에 접근할 사용자 계정을 생성한다.

    CREATE USER '사용자명'@'호스트명' IDENTIFIED BY '비밀번호';

예를 들어 로컬에서 접속할 계정을 만들 수 있다.

    CREATE USER 'spring_user'@'localhost' IDENTIFIED BY '1234';

즉, 스프링 애플리케이션에서 사용할 전용 DB 계정을 만들어 관리할 수 있다. ✅

## 3. 권한 설정

생성한 사용자에게 데이터베이스 접근 권한을 부여해야 한다.

    GRANT ALL PRIVILEGES ON 데이터베이스명.* TO '사용자명'@'호스트명';

예시는 다음과 같다.

    GRANT ALL PRIVILEGES ON spring_db.* TO 'spring_user'@'localhost';

권한 설정 후에는 다음 명령으로 권한 정보를 반영한다.

    FLUSH PRIVILEGES;

즉, 데이터베이스를 만들고 사용자에게 권한을 부여해야 스프링에서 정상적으로 접속할 수 있다. 📌

## 4. IntelliJ 설정

스프링 프로젝트에서 Lombok이나 어노테이션 기반 코드 생성을 사용할 경우 Annotation Processor 설정을 활성화해야 한다.

설정 위치는 다음과 같다.

    Settings
    → Build, Execution, Deployment
    → Compiler
    → Annotation Processors
    → Enable annotation processing

이 설정이 꺼져 있으면 Lombok의 `@Getter`, `@Setter`, `@Builder` 같은 어노테이션이 정상적으로 동작하지 않을 수 있다. ⚠️

## 5. Gradle 테스트 실행 설정

IntelliJ에서 Gradle 프로젝트를 사용할 때 테스트 실행 방식을 변경할 수 있다.

설정 위치는 다음과 같다.

    빌드, 실행, 배포
    → 빌드 도구
    → Gradle
    → 다음을 사용하여 테스트 실행
    → IntelliJ IDEA 선택

이 설정은 테스트 실행을 Gradle이 아니라 IntelliJ IDEA가 직접 수행하도록 지정하는 것이다.

테스트 실행 속도나 디버깅 편의성 때문에 IntelliJ IDEA로 설정하는 경우가 있다. 📌

## 6. 프로젝트의 JDBC 연결

스프링 프로젝트에서 MySQL과 연결하려면 JDBC 드라이버가 필요하다.

JDBC 연결에 필요한 대표 정보는 다음과 같다.

- JDBC Driver
- JDBC URL
- DB 사용자명
- DB 비밀번호

예를 들어 MySQL 연결 정보는 다음과 같은 형태이다.

    jdbc.driver=com.mysql.cj.jdbc.Driver
    jdbc.url=jdbc:mysql://localhost:3306/데이터베이스명
    jdbc.username=사용자명
    jdbc.password=비밀번호

이 정보들을 설정 파일에 분리해 두면 코드에서 직접 값을 하드코딩하지 않아도 된다. ✅

## 7. JDBC 테스트 코드

DB 연결 설정이 끝나면 테스트 코드를 통해 실제로 연결되는지 확인해야 한다.

테스트의 목적은 다음과 같다.

- JDBC 드라이버가 정상적으로 로드되는지 확인
- DB URL이 올바른지 확인
- 사용자명과 비밀번호가 맞는지 확인
- MySQL 서버에 정상 접속되는지 확인

즉, 본격적인 DAO나 Repository 코드를 작성하기 전에 연결 테스트를 먼저 진행하는 것이 좋다. 📌

## 8. DataSource

`DataSource`는 데이터베이스 연결을 제공하는 객체이다.

기존 JDBC에서는 `DriverManager.getConnection()`을 통해 직접 연결을 얻었다.  
하지만 스프링에서는 보통 `DataSource`를 빈으로 등록하고, 필요한 곳에서 주입받아 사용한다.

즉, `DataSource`는 DB 연결을 관리하고 제공하는 표준 인터페이스라고 볼 수 있다.

## 9. Connection Pool

Connection Pool은 데이터베이스 연결 객체를 미리 여러 개 만들어 두고 재사용하는 방식이다.

DB 연결은 생성 비용이 크기 때문에 요청마다 새로 연결을 만들면 성능이 떨어질 수 있다.

Connection Pool을 사용하면 다음과 같은 장점이 있다.

- DB 연결 재사용 가능
- 연결 생성 비용 감소
- 성능 향상
- 연결 수 관리 가능

즉, 실무에서는 직접 연결을 만들기보다 Connection Pool을 이용하는 방식이 일반적이다. ✅

## 10. HikariCP

HikariCP는 Java에서 많이 사용하는 고성능 Connection Pool 라이브러리이다.

스프링 프로젝트에서 MySQL과 연동할 때 `DataSource` 구현체로 HikariCP를 사용할 수 있다.

HikariCP를 사용하면 스프링이 데이터베이스 연결을 효율적으로 관리할 수 있다.

즉, HikariCP는 `DataSource`를 통해 DB 연결 풀을 제공하는 라이브러리이다. 📌

## 11. 라이브러리 추가와 DataSource 설정

스프링에서 MySQL과 HikariCP를 사용하려면 관련 라이브러리를 추가해야 한다.

대표적으로 필요한 라이브러리는 다음과 같다.

- MySQL JDBC Driver
- HikariCP
- Spring JDBC 또는 관련 스프링 라이브러리

이후 설정 클래스에서 `DataSource`를 빈으로 등록할 수 있다.

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();

        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        return new HikariDataSource(config);
    }

이렇게 등록한 `DataSource`는 스프링 컨텍스트에서 관리되는 빈이 된다.

## 12. .properties에서 값 읽어오기

DB 연결 정보는 코드에 직접 작성하기보다 `.properties` 파일에 분리하는 것이 좋다.

예를 들어 `application.properties` 파일에 다음처럼 작성할 수 있다.

    jdbc.driver=com.mysql.cj.jdbc.Driver
    jdbc.url=jdbc:mysql://localhost:3306/spring_db
    jdbc.username=spring_user
    jdbc.password=1234

이렇게 하면 설정 값을 외부 파일에서 관리할 수 있어 유지보수가 쉬워진다. ✅

## 13. @PropertySource

`@PropertySource`는 사용할 `.properties` 파일의 경로를 지정하는 클래스 레벨 어노테이션이다.

기본 형식은 다음과 같다.

    @PropertySource({"properties 경로 문자열"})

예시는 다음과 같다.

    @PropertySource({"classpath:/application.properties"})

이 어노테이션을 설정 클래스에 붙이면 해당 properties 파일의 값을 스프링에서 읽을 수 있다.

    @Configuration
    @PropertySource({"classpath:/application.properties"})
    public class ProjectConfig {
    }

즉, 설정 클래스에서 외부 설정 파일을 사용할 수 있게 해 주는 어노테이션이다. 📌

## 14. @Value

`@Value`는 `.properties` 파일에 있는 값을 필드에 주입할 때 사용하는 필드 레벨 어노테이션이다.

기본 형식은 다음과 같다.

    @Value("${키:기본값}")

기본값은 생략할 수 있다.

    @Value("${jdbc.driver}")
    private String driver;

이 코드는 `application.properties` 파일에서 `jdbc.driver` 값을 읽어 `driver` 필드에 주입한다.

## 15. @Value 기본값

`@Value`에서는 키에 해당하는 값이 없을 경우 사용할 기본값을 지정할 수 있다.

    @Value("${jdbc.driver:com.mysql.cj.jdbc.Driver}")
    private String driver;

위 코드에서 `jdbc.driver` 값이 properties 파일에 없으면 `com.mysql.cj.jdbc.Driver`가 기본값으로 사용된다.

즉, 기본값을 지정하면 설정 누락으로 인한 오류를 줄일 수 있다. 📌

## 16. 전체 연결 흐름

스프링과 MySQL 연동 흐름은 다음과 같다.

1. MySQL 데이터베이스 생성
2. 사용자 계정 생성
3. 권한 부여
4. Gradle에 JDBC Driver와 HikariCP 라이브러리 추가
5. `application.properties`에 DB 접속 정보 작성
6. `@PropertySource`로 properties 파일 등록
7. `@Value`로 설정 값 주입
8. HikariCP 기반 `DataSource` 빈 등록
9. 테스트 코드로 연결 확인

즉, DB 준비부터 스프링 설정, 연결 테스트까지 순서대로 진행해야 한다. ✅

## 17. 중요 포인트 📌

- 스프링에서 MySQL을 사용하려면 데이터베이스 생성, 계정 생성, 권한 설정이 필요하다.
- IntelliJ에서 Annotation Processor를 활성화해야 Lombok 같은 어노테이션 처리가 정상 동작할 수 있다.
- Gradle 테스트 실행 방식을 IntelliJ IDEA로 설정할 수 있다.
- JDBC 연결에는 드라이버, URL, 사용자명, 비밀번호가 필요하다.
- `DataSource`는 데이터베이스 연결을 제공하는 객체이다.
- Connection Pool은 DB 연결을 미리 만들어 두고 재사용하는 방식이다.
- HikariCP는 대표적인 Connection Pool 라이브러리이다.
- DB 접속 정보는 `.properties` 파일로 분리해서 관리할 수 있다.
- `@PropertySource`는 사용할 properties 파일 경로를 지정한다.
- `@Value`는 properties 값을 필드에 주입한다.
- `@Value("${키:기본값}")`에서 기본값은 생략 가능하다.
- JDBC 연결 설정 후에는 테스트 코드로 연결 여부를 확인해야 한다.

## 정리 ✅

스프링과 MySQL Database 연동에서는 데이터베이스 생성, 사용자 계정 생성, 권한 설정, JDBC 연결 설정, `DataSource` 등록이 핵심이다.  
스프링에서는 직접 매번 DB 연결을 생성하기보다 HikariCP 같은 Connection Pool을 이용해 `DataSource`로 연결을 관리하는 방식이 일반적이다.  
DB 접속 정보는 `.properties` 파일에 분리하고, 설정 클래스에서 `@PropertySource`로 파일을 읽은 뒤 `@Value`를 이용해 필요한 값을 필드에 주입할 수 있다.  
또한 Lombok 등을 사용할 경우 Annotation Processor를 활성화해야 하며, DB 설정 후에는 반드시 JDBC 테스트 코드로 연결이 정상적으로 되는지 확인해야 한다.
