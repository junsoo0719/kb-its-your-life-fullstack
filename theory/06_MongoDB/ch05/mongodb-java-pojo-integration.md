# ✨ MongoDB Java POJO 연동

## 1. POJO 매핑의 개념

POJO는 Plain Old Java Object의 약자이다.  
특별한 프레임워크에 종속되지 않는 일반적인 Java 객체를 의미한다.

MongoDB Java 연동에서는 MongoDB의 도큐먼트를 Java 객체와 연결해서 사용할 수 있다.  
이렇게 MongoDB 도큐먼트와 Java 클래스를 서로 변환하는 방식을 POJO 매핑이라고 한다. 📌

즉, MongoDB의 데이터를 `Document` 객체로만 다루는 것이 아니라 Java 클래스 형태로 다룰 수 있게 만드는 방식이다.

## 2. Document 방식의 불편함

MongoDB Java Driver에서는 기본적으로 `Document` 객체를 이용해 데이터를 처리할 수 있다.

예를 들면 다음과 같은 방식이다.

    Document doc = new Document("name", "kim")
        .append("age", 20);

하지만 데이터가 많아지면 `Document` 방식은 번거로워질 수 있다.

예를 들어 필드명을 문자열로 직접 작성해야 하고, 값을 꺼낼 때도 타입을 직접 맞춰야 한다.

    String name = doc.getString("name");
    int age = doc.getInteger("age");

이 방식은 간단한 예제에서는 괜찮지만, 실제 프로젝트에서는 관리하기 어렵다. ⚠️

## 3. Document 방식의 문제점

`Document`로 실제 데이터를 처리하면 다음과 같은 문제가 생길 수 있다.

- 필드명을 문자열로 직접 작성해야 함
- 오타가 나도 컴파일 단계에서 잡기 어려움
- 데이터 타입 변환을 직접 신경 써야 함
- 코드가 길어지고 가독성이 떨어짐
- 객체 지향적인 코드 작성이 어려움

즉, `Document` 방식은 MongoDB 구조를 직접 다루는 방식이라 Java 객체 중심의 개발과는 잘 맞지 않을 수 있다.

## 4. Java POJO 클래스 사용

POJO 매핑을 사용하면 MongoDB 데이터를 Java 클래스와 연결해서 사용할 수 있다.

예를 들어 사용자 데이터를 다룬다면 다음과 같은 클래스를 만들 수 있다.

    public class User {
        private String username;
        private int age;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

이렇게 클래스를 만들어 두면 MongoDB의 도큐먼트를 `User` 객체처럼 다룰 수 있다.

즉, 데이터베이스 데이터를 Java 객체로 자연스럽게 사용할 수 있다. ✅

## 5. POJO 매핑이 필요한 이유

POJO 매핑이 필요한 이유는 Java 코드에서 데이터를 더 편하게 다루기 위해서이다.

`Document` 방식은 MongoDB의 도큐먼트 구조를 직접 조작하는 방식이고, POJO 방식은 Java 객체를 중심으로 데이터를 처리하는 방식이다.

정리하면 다음과 같다.

- `Document` 방식 → 도큐먼트 직접 조작
- POJO 방식 → Java 객체로 데이터 처리

즉, POJO 매핑을 사용하면 MongoDB 데이터를 객체 지향적으로 다룰 수 있다. 📌

## 6. POJO 매핑의 장점

POJO 매핑을 사용하면 다음과 같은 장점이 있다.

- Java 클래스 기반으로 데이터 관리 가능
- 필드명 오타를 줄일 수 있음
- getter/setter를 통해 객체처럼 사용 가능
- 코드 가독성 향상
- 유지보수 쉬움
- 도메인 모델 중심 개발 가능

즉, 실제 애플리케이션에서는 `Document`를 직접 다루는 것보다 POJO 클래스를 사용하는 방식이 더 구조적이다.

## 7. POJO와 객체 지향

POJO 매핑은 MongoDB 데이터를 Java 객체로 다룰 수 있게 해 준다.

이렇게 하면 데이터베이스에서 가져온 데이터를 단순한 key-value 묶음으로 보는 것이 아니라, 의미 있는 객체로 다룰 수 있다.

예를 들어 `User`, `Product`, `Board`, `Comment` 같은 클래스를 만들고, MongoDB 도큐먼트와 연결할 수 있다.

즉, MongoDB를 사용하더라도 Java 애플리케이션 내부에서는 객체 지향적인 구조를 유지할 수 있다. ✅

## 8. 중요 포인트 📌

- POJO는 일반적인 Java 객체를 의미한다.
- POJO 매핑은 MongoDB 도큐먼트와 Java 클래스를 연결하는 방식이다.
- `Document`로 데이터를 처리하면 필드명을 문자열로 직접 다뤄야 해서 번거롭다.
- `Document` 방식은 오타나 타입 처리 실수가 발생하기 쉽다.
- Java POJO 클래스를 사용하면 MongoDB 데이터를 객체처럼 다룰 수 있다.
- POJO 매핑은 코드 가독성과 유지보수성을 높여 준다.
- 실제 프로젝트에서는 `Document` 직접 처리보다 POJO 기반 처리가 더 구조적이다.

## 정리 ✅

MongoDB Java POJO 연동은 MongoDB의 도큐먼트를 Java 객체와 연결해서 사용하는 방식이다.  
`Document`로 실제 데이터를 직접 처리하면 필드명을 문자열로 관리해야 하고, 타입 변환이나 오타 문제 때문에 코드가 번거로워질 수 있다.  
POJO 매핑을 사용하면 MongoDB 데이터를 Java 클래스 형태로 다룰 수 있어 객체 지향적인 코드 작성이 가능해진다.  
시험 대비에서는 `Document` 방식의 불편함, POJO 매핑의 필요성, Java 객체로 데이터를 처리하는 장점을 함께 정리해 두는 것이 중요하다.
