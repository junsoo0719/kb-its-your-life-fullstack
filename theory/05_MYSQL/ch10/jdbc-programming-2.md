# ✨ JDBC 프로그래밍2

## 1. VO 패턴

VO는 Value Object의 약자이다.  
VO 객체는 특정 테이블의 한 행을 매핑하는 클래스이다.

즉, 데이터베이스 테이블의 한 행을 Java 객체 하나로 표현하는 방식이다. 📌

테이블과 Java 클래스의 대응 관계는 다음과 같다.

- 클래스 정의 → 테이블
- 필드들 → 컬럼들
- 인스턴스 → 한 행

예를 들어 `users` 테이블이 있다면 `UserVO` 클래스를 만들고, 테이블의 각 컬럼을 클래스 필드로 정의할 수 있다.

## 2. VO 객체의 역할

VO 객체는 데이터베이스에서 조회한 한 행의 데이터를 Java 코드에서 다루기 쉽게 담는 역할을 한다.

예를 들어 테이블에 다음 컬럼이 있다고 하자.

- `id`
- `password`
- `name`
- `role`

그러면 Java에서는 다음과 같은 VO 클래스를 만들 수 있다.

    public class UserVO {
        private String id;
        private String password;
        private String name;
        private String role;
    }

이렇게 하면 데이터베이스의 한 행을 `UserVO` 객체 하나로 다룰 수 있다. ✅

## 3. VO와 DTO

테이블에 맞춰서 설계하면 VO 객체라고 부를 수 있다.  
반면 비즈니스 로직에 맞춰서 설계하면 DTO 객체라고 부를 수 있다.

정리하면 다음과 같다.

- VO → 테이블 구조에 맞춘 객체
- DTO → 비즈니스 로직 또는 데이터 전달 목적에 맞춘 객체

하지만 실제 개발에서는 테이블 구조와 비즈니스 로직 구조가 비슷한 경우가 많다.  
그래서 VO와 DTO를 혼용해서 사용하는 경우도 많다. 📌

## 4. DAO 패턴

DAO는 Data Access Object의 약자이다.  
DAO 클래스는 데이터베이스에 접근하여 실질적인 데이터베이스 연동 작업을 담당하는 클래스이다.

즉, SQL을 실행하고 데이터베이스와 직접 통신하는 역할을 DAO가 맡는다.

DAO는 테이블에 대한 CRUD 연산을 처리한다.

- Create → 데이터 삽입
- Read → 데이터 조회
- Update → 데이터 수정
- Delete → 데이터 삭제

## 5. DAO 클래스의 역할

DAO 클래스는 JDBC 코드가 서비스나 화면 코드에 섞이지 않도록 분리하는 역할을 한다.

즉, 데이터베이스 접근 코드를 DAO에 모아두면 프로그램 구조가 더 깔끔해진다.

DAO 클래스가 담당하는 작업은 다음과 같다.

- DB 연결 얻기
- SQL 작성
- `PreparedStatement` 생성
- 파라미터 설정
- SQL 실행
- `ResultSet` 처리
- VO 객체 생성 및 반환

즉, 데이터베이스 관련 작업을 한 곳에서 담당하게 만드는 패턴이다. ✅

## 6. DAO 인터페이스와 구현 클래스

DAO는 보통 인터페이스를 먼저 정의하고, 그 다음 구현 클래스를 작성하는 방식으로 설계한다.

예를 들어 사용자 테이블에 대한 DAO라면 다음처럼 구성할 수 있다.

    public interface UserDAO {
        void insert(UserVO user);
        UserVO findById(String id);
        List<UserVO> findAll();
        void update(UserVO user);
        void delete(String id);
    }

그리고 실제 JDBC 코드를 작성하는 구현 클래스를 만든다.

    public class UserDAOImpl implements UserDAO {
        ...
    }

이렇게 하면 코드가 인터페이스에 의존하게 되어 유지보수와 확장이 쉬워진다. 📌

## 7. CRUD와 DAO

DAO는 테이블에 대한 CRUD 연산을 담당한다.

예를 들어 `UserDAO`라면 다음과 같은 메서드를 가질 수 있다.

- `insert()` → 사용자 추가
- `findById()` → 사용자 한 명 조회
- `findAll()` → 사용자 목록 조회
- `update()` → 사용자 정보 수정
- `delete()` → 사용자 삭제

즉, DAO는 테이블별 데이터 접근 기능을 메서드로 제공하는 구조이다.

## 8. SQLException

`SQLException`은 JDBC에서 발생하는 대표적인 최상위 예외 객체이다.

JDBC 작업 중 발생할 수 있는 예외는 대부분 `SQLException` 계열로 처리된다.

예를 들어 다음과 같은 상황에서 발생할 수 있다.

- SQL 문법 오류
- DB 연결 실패
- 테이블 또는 컬럼명 오류
- 제약 조건 위반
- 데이터 타입 불일치

`SQLException`은 반드시 처리해야 하는 checked exception이다. ⚠️

## 9. SQLException 처리

`SQLException`은 반드시 `try-catch`로 처리하거나 `throws`로 호출한 쪽에 넘겨야 한다.

예를 들면 다음과 같다.

    try {
        // JDBC 코드
    } catch (SQLException e) {
        e.printStackTrace();
    }

또는 다음처럼 메서드에서 떠넘길 수 있다.

    public void insert(UserVO user) throws SQLException {
        ...
    }

실제 프로젝트에서는 단순히 `printStackTrace()`만 하기보다 로그를 남기거나 사용자에게 적절한 메시지를 전달하는 방식으로 처리하는 것이 좋다. 📌

## 10. VO, DAO, JDBC의 관계

JDBC 프로그래밍에서 VO와 DAO는 함께 사용된다.

흐름은 다음과 같다.

1. DAO가 SQL을 실행한다.
2. 조회 결과를 `ResultSet`으로 받는다.
3. `ResultSet`의 각 행을 VO 객체로 변환한다.
4. Java 코드에서는 VO 객체를 사용한다.

즉, DAO는 데이터베이스 접근을 담당하고, VO는 데이터 한 행을 담는 역할을 한다. ✅

## 11. 중요 포인트 📌

- VO는 Value Object의 약자이다.
- VO 객체는 특정 테이블의 한 행을 매핑하는 클래스이다.
- 클래스 정의는 테이블, 필드는 컬럼, 인스턴스는 한 행에 대응된다.
- 테이블에 맞춰 설계하면 VO 객체라고 볼 수 있다.
- 비즈니스 로직에 맞춰 설계하면 DTO 객체라고 볼 수 있다.
- 실제 개발에서는 VO와 DTO를 혼용해서 쓰는 경우도 많다.
- DAO는 Data Access Object의 약자이다.
- DAO는 데이터베이스에 접근하여 실제 DB 연동 작업을 담당한다.
- DAO는 테이블에 대한 CRUD 연산을 처리한다.
- DAO는 보통 인터페이스를 정의한 뒤 구현 클래스를 작성한다.
- `SQLException`은 JDBC에서 발생하는 대표적인 최상위 예외 객체이다.
- `SQLException`은 반드시 처리해야 한다.

## 정리 ✅

JDBC 프로그래밍2에서는 VO 패턴과 DAO 패턴이 핵심이다.  
VO 객체는 특정 테이블의 한 행을 Java 객체로 매핑하는 클래스이며, 클래스는 테이블, 필드는 컬럼, 인스턴스는 한 행에 대응된다.  
DAO 클래스는 데이터베이스에 접근해 실제 CRUD 작업을 처리하는 클래스이며, SQL 실행과 `ResultSet` 처리, VO 객체 변환을 담당한다.  
실제 개발에서는 DAO 인터페이스를 먼저 정의하고 구현 클래스를 작성하면 구조를 더 명확하게 관리할 수 있다.  
또한 JDBC 작업 중 발생하는 `SQLException`은 checked exception이므로 반드시 처리해야 한다.
git add theory/05_MySQL/ch10/jdbc-programming-2.md
git commit -m "2026-06-01 ch10 이론: JDBC 프로그래밍2 정리"