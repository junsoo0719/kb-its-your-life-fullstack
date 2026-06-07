# ✨ JDBC 프로그래밍 - Travel

## 1. 프로젝트 개요

이번 프로젝트는 관광지 정보를 담고 있는 `travel.csv` 파일을 데이터베이스로 임포트하는 실습이다.

즉, CSV 파일에 저장된 관광지 데이터를 Java에서 읽고, JDBC를 이용해 MySQL 데이터베이스에 저장하는 구조이다. 📌

전체 흐름은 다음과 같다.

1. `travel.csv` 파일 준비
2. Java에서 CSV 파일 읽기
3. CSV 데이터를 Java 객체로 변환
4. JDBC를 이용해 데이터베이스에 저장
5. 관광지 정보와 이미지 정보를 조인해서 조회

## 2. opencsv

`opencsv`는 Java에서 CSV 파일을 다룰 수 있도록 도와주는 라이브러리이다.

CSV 파일은 콤마(`,`)를 기준으로 데이터를 구분하는 텍스트 파일이다.  
직접 문자열을 나누어 처리할 수도 있지만, CSV 파일에는 따옴표, 줄바꿈, 특수문자 등이 포함될 수 있기 때문에 라이브러리를 사용하는 것이 안전하다.

즉, `opencsv`는 CSV 파일을 더 편리하고 안정적으로 읽기 위해 사용한다. ✅

## 3. CSVReader 클래스

`CSVReader`는 CSV 파일을 한 줄씩 읽을 때 사용하는 클래스이다.

생성자에는 CSV 파일을 읽을 수 있는 `Reader` 객체를 전달한다.

    new CSVReader(new FileReader("travel.csv"))

즉, `FileReader`가 파일을 문자 단위로 읽고, `CSVReader`가 CSV 형식에 맞게 한 줄씩 처리한다.

## 4. readNext()

`CSVReader`의 `readNext()` 메서드는 CSV 파일에서 한 줄을 읽고, 콤마를 기준으로 나누어 `String[]` 배열로 리턴한다.

    String[] line = csvReader.readNext();

예를 들어 CSV 한 줄이 다음과 같다면

    서울타워,서울,관광지

`readNext()`는 다음과 같은 배열로 반환한다.

    ["서울타워", "서울", "관광지"]

즉, 한 행의 각 컬럼 값을 문자열 배열로 처리할 수 있다. 📌

## 5. CsvToBeanBuilder 클래스

`CsvToBeanBuilder<T>`는 CSV 파일의 한 라인을 읽어 지정한 `T` 타입의 인스턴스로 변환해 주는 클래스이다.

즉, CSV 데이터를 단순한 문자열 배열이 아니라 Java 객체로 변환할 수 있다.

기본 사용 형식은 다음과 같다.

    List<T> t = new CsvToBeanBuilder<T>(new FileReader("csv 파일 경로"))
            .withType(T.class)
            .build()
            .parse();

이 코드는 CSV 파일을 읽고, 각 행을 `T` 타입 객체로 변환한 뒤 `List<T>`로 반환한다.

## 6. CSV 헤더와 필드 매핑

`CsvToBeanBuilder`를 사용할 때는 CSV 파일의 첫 번째 줄에 있는 헤더명과 Java 클래스의 필드명이 같아야 한다.

예를 들어 CSV 헤더가 다음과 같다면

    title,address,phone

Java 클래스도 다음처럼 필드를 정의해야 한다.

    private String title;
    private String address;
    private String phone;

즉, CSV 헤더명과 클래스 필드명이 일치해야 자동 매핑이 가능하다. ⚠️

## 7. Foreign Key와 Cascade

외래 키를 설정할 때는 보통 `CASCADE` 옵션을 함께 설정하는 경우가 많다.

`CASCADE`는 기준 테이블의 데이터가 변경되거나 삭제될 때, 이를 참조하는 테이블에도 자동으로 반영되도록 하는 옵션이다.

예를 들어 관광지 정보가 삭제되면 해당 관광지에 연결된 이미지 정보도 함께 삭제되도록 설정할 수 있다.

즉, 관련 데이터를 함께 관리해야 할 때 `ON DELETE CASCADE`, `ON UPDATE CASCADE` 같은 옵션을 사용할 수 있다. 📌

## 8. @Builder

`@Builder`는 Lombok에서 제공하는 어노테이션이다.

`@Builder`를 사용하면 `builder()`라는 static 메서드가 자동으로 추가된다.

    TravelVO travel = TravelVO.builder()
            .title("관광지명")
            .address("주소")
            .build();

이 방식은 생성자에 값을 순서대로 넣는 방식보다 읽기 쉽고, 필요한 값만 선택적으로 설정할 수 있다. ✅

## 9. @Builder와 @AllArgsConstructor 관계

`@Builder`는 내부적으로 모든 필드를 매개변수로 받는 생성자와 관련이 있다.

즉, builder에서 체이닝으로 설정한 파라미터들이 최종적으로 전체 매개변수를 받는 생성자에 전달된다.

따라서 `builder()`를 사용하려면 매개변수를 모두 가지는 생성자가 존재해야 한다.

    @AllArgsConstructor
    @Builder
    public class TravelVO {
        ...
    }

디폴트 생성자만 있으면 `builder()` 사용에 문제가 생길 수 있다. ⚠️

## 10. TravelVO와 TravelImageVO

`TravelVO`와 `TravelImageVO`는 각각 관광지 정보와 관광지 이미지 정보를 표현하는 Java 객체이다.

두 클래스 모두 primary key에 해당하는 `no` 필드를 가진다.

이때 `no`는 primitive 타입인 `int`가 아니라 Wrapper 클래스인 `Integer`로 선언하는 것이 좋다.

    private Integer no;

이유는 데이터베이스 컬럼이 `NULL` 값을 가질 수 있는 경우 primitive 타입으로는 매핑이 어렵기 때문이다.

## 11. primitive 타입과 Wrapper 클래스

Java의 primitive 타입은 `null` 값을 가질 수 없다.

예를 들어 `int`는 값이 없을 때도 기본값 `0`을 가진다.

반면 Wrapper 클래스인 `Integer`는 객체 타입이므로 `null`을 가질 수 있다.

정리하면 다음과 같다.

- `int` → `null` 불가
- `Integer` → `null` 가능

데이터베이스 컬럼이 `NULL`일 수 있다면 Wrapper 클래스를 사용하는 것이 안전하다. 📌

## 12. 관광지 정보 하나 조회하기

관광지 정보 하나를 조회할 때는 해당 관광지에 연결된 이미지 정보도 함께 가져올 수 있다.

이때 `JOIN`을 사용한다.

관광지에는 이미지가 없을 수도 있으므로 `INNER JOIN`이 아니라 `LEFT OUTER JOIN`을 사용한다.

    SELECT t.*,
           ti.no AS tino,
           ti.filename,
           ti.travel_no
    FROM tbl_travel t
    LEFT OUTER JOIN tbl_travel_image ti
        ON t.no = ti.travel_no
    WHERE t.no = 1;

이 쿼리는 `tbl_travel`의 관광지 정보와 `tbl_travel_image`의 이미지 정보를 함께 조회한다.

## 13. LEFT OUTER JOIN을 사용하는 이유

이미지가 없는 관광지도 조회되어야 한다면 `LEFT OUTER JOIN`을 사용해야 한다.

`INNER JOIN`을 사용하면 이미지가 없는 관광지는 결과에서 제외된다.

반면 `LEFT OUTER JOIN`은 왼쪽 테이블인 `tbl_travel`의 데이터는 모두 유지하고, 오른쪽 테이블인 `tbl_travel_image`에 일치하는 데이터가 있으면 함께 가져온다.

즉,

- 관광지 정보는 반드시 조회
- 이미지 정보는 있으면 함께 조회
- 이미지가 없으면 이미지 컬럼은 `NULL`

로 처리된다. ✅

## 14. 컬럼 별칭 사용

조인 쿼리에서는 서로 다른 테이블에 같은 이름의 컬럼이 있을 수 있다.

예를 들어 `tbl_travel`과 `tbl_travel_image` 모두 `no` 컬럼을 가질 수 있다.

이때 이미지 테이블의 `no`를 구분하기 위해 별칭을 사용한다.

    ti.no AS tino

즉, 조회 결과에서 이미지 번호를 `tino`라는 이름으로 구분해서 사용할 수 있다. 📌

## 15. 중요 포인트 📌

- 프로젝트 목표는 `travel.csv` 파일의 관광지 정보를 데이터베이스로 임포트하는 것이다.
- `opencsv`는 Java에서 CSV 파일을 다루는 라이브러리이다.
- `CSVReader`는 CSV 파일을 한 줄씩 읽을 때 사용한다.
- `readNext()`는 한 줄을 읽고 콤마 기준으로 나누어 `String[]`로 반환한다.
- `CsvToBeanBuilder<T>`는 CSV 한 줄을 지정한 Java 객체 타입으로 변환한다.
- CSV 헤더명과 Java 클래스 필드명이 같아야 자동 매핑이 가능하다.
- 외래 키 설정에는 `CASCADE` 옵션을 함께 사용하는 경우가 많다.
- `@Builder`는 `builder()` static 메서드를 생성한다.
- `@Builder`는 전체 매개변수를 받는 생성자와 관련이 있다.
- `builder()`를 사용하려면 모든 매개변수를 가지는 생성자가 필요하다.
- DB 컬럼이 `NULL`일 수 있으면 primitive 타입보다 Wrapper 클래스를 사용하는 것이 안전하다.
- 관광지와 이미지 정보를 함께 조회할 때는 `JOIN`을 사용한다.
- 이미지가 없을 수도 있으므로 `LEFT OUTER JOIN`을 사용한다.
- 조인 결과에서 컬럼명이 겹치면 `AS`로 별칭을 지정한다.

## 정리 ✅

JDBC 프로그래밍 Travel 실습은 `travel.csv` 파일의 관광지 정보를 Java에서 읽고 데이터베이스로 저장하는 프로젝트이다.  
CSV 파일 처리는 `opencsv` 라이브러리를 사용하며, `CSVReader`는 한 줄씩 읽어 문자열 배열로 반환하고, `CsvToBeanBuilder<T>`는 CSV 데이터를 Java 객체로 변환한다.  
CSV 헤더명과 Java 클래스 필드명이 같아야 자동 매핑이 가능하며, 객체 생성에는 Lombok의 `@Builder`를 활용할 수 있다.  
이때 `@Builder`는 전체 매개변수를 받는 생성자와 관련이 있으므로 `@AllArgsConstructor`와 함께 이해해야 한다.  
또한 DB 컬럼이 `NULL`일 수 있는 경우 primitive 타입 대신 Wrapper 클래스를 사용하는 것이 안전하고, 관광지 정보와 이미지 정보를 함께 조회할 때는 이미지가 없을 수도 있으므로 `LEFT OUTER JOIN`을 사용하는 것이 중요하다.
