# ✨ JDBC 프로그래밍

## 1. JDBC 개념

JDBC는 Java Database Connectivity의 약자이다.  
자바 프로그램에서 데이터베이스와 연결해 데이터를 입출력할 수 있도록 지원하는 기술이다.

JDBC는 DBMS 종류와 상관없이 동일한 방식으로 사용할 수 있는 클래스와 인터페이스로 구성되어 있다.  
즉, MySQL, Oracle 등 DBMS가 달라도 자바 코드에서는 JDBC 표준 API를 이용해 비슷한 방식으로 데이터베이스를 다룰 수 있다. 📌

## 2. JDBC 개발 절차

JDBC를 이용한 데이터베이스 처리 흐름은 보통 다음 순서로 진행된다.

1. JDBC 드라이버 로드
2. 데이터베이스 연결
3. `Statement` 생성
4. SQL문 전송
5. 결과 받기
6. 연결 해제

즉, 먼저 데이터베이스에 연결한 뒤 SQL을 실행하고, 결과를 받은 후 사용한 자원을 닫는 구조이다. ✅

## 3. JDBC 드라이버 로드

데이터베이스에 연결하려면 먼저 JDBC 드라이버를 로드해야 한다.

MySQL 드라이버 로드는 다음과 같이 작성한다.

    Class.forName("com.mysql.cj.jdbc.Driver");

이 코드는 MySQL JDBC 드라이버 클래스가 있는지 확인하고 로드하는 역할을 한다.

만약 해당 드라이버 클래스가 없으면 `ClassNotFoundException`이 발생한다. ⚠️

## 4. Connection 객체

`Connection` 객체는 데이터베이스와 연결된 세션을 나타낸다.

즉, 자바 프로그램과 데이터베이스 사이의 연결 통로라고 볼 수 있다.

연결은 `DriverManager.getConnection()` 메소드를 사용해서 얻는다.

    Connection conn = DriverManager.getConnection("연결 문자열", "사용자", "비밀번호");

이 객체를 통해 SQL 실행 객체인 `Statement`나 `PreparedStatement`를 만들 수 있다.

## 5. 연결 문자열

MySQL 연결 문자열의 기본 형식은 다음과 같다.

    jdbc:mysql://[host]:[port]/[db이름]

예를 들면 다음과 같다.

    String url = "jdbc:mysql://127.0.0.1:3306/jdbc_ex";
    Connection conn = DriverManager.getConnection(url, "jdbc_ex", "jdbc_ex");

이 코드는 로컬 MySQL 서버의 `jdbc_ex` 데이터베이스에 `jdbc_ex` 사용자로 접속하는 예시이다.

## 6. JDBCUtil 모듈화

데이터베이스 연결과 닫기 작업은 JDBC를 사용할 때 반복적으로 필요하다.

따라서 매번 같은 코드를 작성하기보다 `common.JDBCUtil` 같은 유틸리티 클래스로 모듈화할 수 있다.

즉, 연결 생성과 자원 해제를 공통 기능으로 분리하면 코드 중복을 줄이고 관리하기 쉬워진다. 📌

## 7. Statement

`Statement`는 SQL 문을 실행하는 클래스이다.  
`Connection` 객체를 통해 생성한다.

    Statement stmt = conn.createStatement();

`Statement`는 SQL 문자열을 그대로 전달해서 실행할 때 사용한다.

즉, 단순한 SQL 실행에는 `Statement`를 사용할 수 있다.

## 8. Statement의 SQL 실행 메소드

`Statement`에서 자주 사용하는 SQL 실행 메소드는 다음과 같다.

    ResultSet executeQuery(SQL문)
    int executeUpdate(SQL문)

각 메소드의 의미는 다음과 같다.

- `executeQuery()` → `SELECT`문 실행, 결과로 `ResultSet` 반환
- `executeUpdate()` → `INSERT`, `UPDATE`, `DELETE`문 실행, 반영된 행 수 반환

즉, 조회는 `executeQuery()`, 데이터 변경은 `executeUpdate()`를 사용한다. ✅

## 9. ResultSet

`ResultSet`은 `SELECT`문의 실행 결과를 담는 객체이다.

조회된 행들을 순서대로 읽으면서 컬럼 값을 추출할 수 있다.

컬럼 값은 다음 형태로 가져온다.

    getXxxx(컬럼명)

여기서 `Xxxx`는 추출하고자 하는 데이터 타입명이다.

대표적인 메소드는 다음과 같다.

- `getString()`
- `getInt()`
- `getLong()`
- `getDouble()`

예를 들면 다음과 같다.

    String name = rs.getString("name");
    int age = rs.getInt("age");

## 10. PreparedStatement

`PreparedStatement`는 SQL문에 값을 넣을 때 파라미터화해서 처리하는 클래스이다.

즉, SQL 문자열 안에 직접 값을 붙이는 대신 `?`를 사용해 나중에 값을 설정한다.

예시는 다음과 같다.

    String sql = "INSERT INTO users(id, password, name, role) VALUES (?, ?, ?, ?)";

`PreparedStatement`는 `Connection` 객체를 통해 생성한다.

    PreparedStatement pstmt = conn.prepareStatement(sql);

## 11. PreparedStatement 파라미터 설정

`PreparedStatement`의 `?` 자리에 값을 넣을 때는 `setXxxx()` 메소드를 사용한다.

기본 형식은 다음과 같다.

    pstmt.setXxxx(파라미터번호, 값);

대표적인 메소드는 다음과 같다.

- `setString()`
- `setInt()`
- `setLong()`
- `setDouble()`

예시는 다음과 같다.

    pstmt.setString(1, id);
    pstmt.setString(2, password);
    pstmt.setString(3, name);
    pstmt.setString(4, role);

파라미터 번호는 1부터 시작한다는 점을 기억해야 한다. ⚠️

## 12. PreparedStatement 실행

파라미터 설정이 끝나면 SQL을 실행한다.

`INSERT`, `UPDATE`, `DELETE`는 다음처럼 실행한다.

    int count = pstmt.executeUpdate();

`count`에는 영향을 받은 행 수가 저장된다.

조회문인 `SELECT`는 다음처럼 실행한다.

    ResultSet rs = pstmt.executeQuery();

즉, `PreparedStatement`에서도 조회는 `executeQuery()`, 변경은 `executeUpdate()`를 사용한다.

## 13. Statement와 PreparedStatement 차이

`Statement`와 `PreparedStatement`는 모두 SQL 실행에 사용되지만 차이가 있다.

- `Statement` → SQL문을 문자열 그대로 실행
- `PreparedStatement` → SQL문을 미리 준비하고 `?`에 값을 바인딩

`PreparedStatement`는 값이 들어가는 SQL을 처리할 때 더 안전하고 관리하기 좋다.  
특히 사용자 입력값을 SQL에 넣을 때는 `PreparedStatement`를 사용하는 것이 좋다. 📌

## 14. try-with-resources

JDBC에서는 `Connection`, `Statement`, `PreparedStatement`, `ResultSet` 같은 자원을 사용한 뒤 반드시 닫아야 한다.

이때 try-with-resources를 사용하면 자동으로 닫을 수 있다.

중간에 파라미터 세팅이 없는 경우는 다음과 같이 작성할 수 있다.

    try (
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
    ) {
        ...
    }

이 구조에서는 `try` 블록이 끝날 때 `stmt`와 `rs`가 자동으로 닫힌다. ✅

## 15. PreparedStatement와 try-with-resources

중간에 파라미터 세팅이 있는 경우에는 `PreparedStatement`를 먼저 생성하고, 값을 설정한 뒤 `ResultSet`을 별도의 try-with-resources로 처리할 수 있다.

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, userid);

        try (ResultSet rs = stmt.executeQuery()) {
            ...
        }
    }

이 방식은 파라미터 설정이 필요한 SQL에서 안전하게 자원을 닫을 수 있다.

즉, JDBC에서는 자원 해제를 항상 고려해야 한다. ⚠️

## 16. JDBC 작업 흐름 예시

조회 작업 흐름은 다음과 같이 정리할 수 있다.

    Class.forName("com.mysql.cj.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1:3306/jdbc_ex";

    try (
        Connection conn = DriverManager.getConnection(url, "jdbc_ex", "jdbc_ex");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");
    ) {
        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
        }
    }

흐름은 다음과 같다.

1. 드라이버 로드
2. DB 연결
3. SQL 실행 객체 생성
4. SQL 실행
5. 결과 처리
6. 자동 닫기

## 17. 중요 포인트 📌

- JDBC는 Java Database Connectivity의 약자이다.
- JDBC는 자바에서 데이터베이스 입출력을 지원한다.
- JDBC는 DBMS 종류와 상관없이 동일한 방식으로 사용할 수 있는 API를 제공한다.
- JDBC 개발 절차는 드라이버 로드, DB 연결, Statement 생성, SQL 전송, 결과 처리, 연결 해제 순서이다.
- MySQL 드라이버는 `Class.forName("com.mysql.cj.jdbc.Driver")`로 로드한다.
- 드라이버가 없으면 `ClassNotFoundException`이 발생한다.
- `Connection`은 데이터베이스 연결 세션을 나타낸다.
- 연결 문자열은 `jdbc:mysql://host:port/db이름` 형식이다.
- `Statement`는 SQL문 실행 클래스이다.
- `executeQuery()`는 `SELECT`문 실행에 사용하고 `ResultSet`을 반환한다.
- `executeUpdate()`는 `INSERT`, `UPDATE`, `DELETE`문 실행에 사용하고 반영 행 수를 반환한다.
- `ResultSet`에서는 `getString()`, `getInt()` 등으로 컬럼 값을 추출한다.
- `PreparedStatement`는 SQL문을 파라미터화해서 처리한다.
- `PreparedStatement`의 파라미터 번호는 1부터 시작한다.
- JDBC 자원은 사용 후 반드시 닫아야 하며 try-with-resources를 사용하는 것이 좋다.

## 정리 ✅

JDBC는 자바 프로그램에서 데이터베이스와 연결해 데이터를 조회, 삽입, 수정, 삭제할 수 있게 해 주는 표준 API이다.  
JDBC 개발은 드라이버 로드, 데이터베이스 연결, SQL 실행 객체 생성, SQL문 전송, 결과 처리, 연결 해제 순서로 진행된다.  
`Connection`은 DB 연결 세션을 나타내고, `Statement`와 `PreparedStatement`는 SQL 실행에 사용된다.  
조회문은 `executeQuery()`로 실행해 `ResultSet`을 받고, 삽입·수정·삭제문은 `executeUpdate()`로 실행해 반영된 행 수를 받는다.  
특히 값이 들어가는 SQL은 `PreparedStatement`의 `?` 파라미터를 사용하면 안전하고 관리하기 좋으며, JDBC 자원은 try-with-resources로 자동 닫는 방식이 권장된다.
