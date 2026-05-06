# ✨ 예외 처리

## 1. 에러와 예외

자바에서 오류 상황은 크게 에러와 예외로 나눌 수 있다.

- 에러(Error) → 복구할 수 없는 심각한 상황
- 예외(Exception) → 복구가 가능한 상황

즉, 에러는 프로그램이 정상적으로 대응하기 어려운 문제이고, 예외는 잘못된 사용이나 코딩으로 인해 발생하지만 처리해서 복구할 수 있는 문제이다. 📌

## 2. 일반 예외와 런타임 예외

예외는 크게 일반 예외와 런타임 예외로 나눌 수 있다.

### 2-1. 일반 예외

일반 예외는 예외 처리를 강제하는 예외이다.  
즉, 반드시 `try-catch`를 사용하거나 `throws`로 예외를 떠넘겨야 한다.

대표적인 예시는 다음과 같다.

- `ClassNotFoundException`
- `InterruptedException`

이런 예외는 컴파일 단계에서 예외 처리 여부를 검사한다.

### 2-2. 런타임 예외

런타임 예외는 예외 처리가 필수는 아니다.  
실행 중에 발생할 수 있으며, 잘못된 코드 작성이나 값 사용으로 인해 자주 발생한다.

대표적인 예시는 다음과 같다.

- `NullPointerException`
- `ArrayIndexOutOfBoundsException`
- `NumberFormatException`

런타임 예외는 예외 처리를 강제하지 않지만, 발생하면 프로그램이 비정상 종료될 수 있으므로 주의해야 한다. ⚠️

## 3. 예외 클래스 계층 구조

자바 예외의 최상위 타입은 `Throwable`이다.

`Throwable` 아래에는 크게 다음 두 타입이 있다.

- `Exception`
- `Error`

`Exception`은 처리 가능한 예외 상황을 나타내고, `Error`는 복구하기 어려운 심각한 문제를 나타낸다.

정리하면 다음과 같다.

- `Throwable`
  - `Exception`
    - 일반 예외
    - `RuntimeException`
  - `Error`

`RuntimeException` 아래에 `NullPointerException`, `ArrayIndexOutOfBoundsException`, `NumberFormatException` 같은 실행 예외들이 포함된다.

## 4. 예외 처리의 의미

예외 처리는 문제가 발생했을 때 프로그램을 바로 종료하지 않고, 복구 작업을 수행하도록 하는 것이다.

여기서 복구란 특정 시점 이전의 정상 상태로 되돌리거나, 오류 상황에 맞는 대체 처리를 수행하는 것을 의미한다.

즉, 예외 처리는 프로그램의 안정성을 높이기 위한 중요한 문법이다. ✅

## 5. try-catch-finally 구조

예외가 발생할 가능성이 있는 코드는 `try` 블록 안에 작성한다.

    try {
        // 예외 발생 가능 코드
    } catch (Exception e) {
        // 예외 처리 코드
    } finally {
        // 항상 실행되는 코드
    }

구조의 의미는 다음과 같다.

- `try` → 예외를 추적할 코드 구간
- `catch` → 예외 발생 시 복구 작업 수행
- `finally` → 예외 발생 여부와 상관없이 항상 실행

즉, `try` 안에서 예외가 발생하면 그 이후 코드는 실행되지 않고 바로 `catch`로 이동한다.

## 6. finally

`finally`는 예외 발생 여부와 관계없이 항상 실행되는 블록이다.

주로 다음과 같은 클린업 작업에 사용된다.

- 파일 닫기
- 네트워크 연결 닫기
- DB 연결 해제
- 사용한 리소스 정리

`finally`는 선택 사항이지만, 리소스를 정리해야 하는 경우에는 매우 중요하다. 📌

## 7. 예외 정보 출력

예외 객체를 이용하면 예외에 대한 정보를 확인할 수 있다.

대표적인 메소드는 다음과 같다.

    e.getMessage()
    e.toString()
    e.printStackTrace()

각 메소드의 차이는 다음과 같다.

- `getMessage()` → 예외 메시지만 출력
- `toString()` → 예외 클래스명과 메시지 출력
- `printStackTrace()` → 예외 발생 경로인 콜스택까지 출력

디버깅할 때는 `printStackTrace()`가 가장 많은 정보를 제공한다. ✅

## 8. Class와 Class.forName()

`Class`는 메서드 영역을 조작하는 역할을 하는 클래스이다.  
클래스를 로드해서 파일에 있는 클래스를 메서드 영역에 올리는 작업과 관련이 있다.

대표적인 메소드가 `Class.forName()`이다.

    Class.forName("패키지명.클래스명");

이 메소드는 클래스를 동적으로 로드할 때 사용한다.  
그리고 `ClassNotFoundException`이 발생할 수 있으므로 예외 처리가 반드시 필요하다. ⚠️

## 9. 다중 catch

하나의 `try` 블록 뒤에 여러 개의 `catch`를 작성할 수 있다.

    try {
        // 예외 발생 가능 코드
    } catch (NumberFormatException e) {
        // NumberFormatException 처리
    } catch (Exception e) {
        // 그 외 Exception 처리
    }

다중 catch에서 `catch` 하나는 하나의 예외 타입을 처리한다.

내부적으로는 예외 객체에 대해 `instanceof` 검사가 이루어진다고 이해할 수 있다.

## 10. 다중 catch 작성 순서

다중 catch에서는 부모 예외 타입을 뒤에 배치해야 한다.

이유는 부모 타입에 대해서는 자식 예외 객체도 항상 `true`가 되기 때문이다.  
즉, 업캐스팅 때문에 자식 예외도 부모 타입으로 잡힐 수 있다.

만약 `catch(Exception e)`를 먼저 작성하면 대부분의 예외가 여기서 처리되어 뒤쪽 catch가 실행될 수 없다. ⚠️

따라서 보통 다음 순서로 작성한다.

- 자식 예외 먼저
- 부모 예외 나중

이 구조는 다중 if문에서 마지막 else 역할과 비슷하게 이해할 수 있다.

## 11. 여러 예외를 동일하게 처리하기

여러 예외를 같은 방식으로 처리하고 싶다면 `|` 연산자를 사용할 수 있다.

    catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
        // 동일한 예외 처리
    }

즉, 여러 예외 타입에 대해 같은 처리 코드를 작성할 때 중복을 줄일 수 있다.

## 12. 리소스 자동 닫기

파일을 다루는 작업은 보통 다음 흐름으로 이루어진다.

1. open
2. 읽기 또는 쓰기
3. close

문제는 읽기나 쓰기 중 예외가 발생하면 `close`가 실행되지 않을 수 있다는 점이다.

이를 해결하기 위해 자바에서는 리소스 자동 닫기 구문을 제공한다.

    try (FileInputStream fis = new FileInputStream("file.txt")) {
        // 파일 읽기
    }

이 구조를 사용하면 `try` 블록이 끝날 때 자동으로 리소스가 닫힌다. ✅

## 13. AutoCloseable 인터페이스

try-with-resources에서 자동으로 닫히려면 해당 객체가 `AutoCloseable` 인터페이스를 구현해야 한다.

기본적인 자바 라이브러리의 파일, 스트림 관련 클래스들은 대부분 `AutoCloseable`을 구현하고 있다.

또한 `try()` 괄호 안에서 세미콜론(`;`)으로 구분해 여러 리소스를 동시에 처리할 수 있다.

    try (
        FileInputStream fis = new FileInputStream("file.txt");
        FileOutputStream fos = new FileOutputStream("copy.txt")
    ) {
        // 작업
    }

즉, 리소스가 여러 개여도 자동으로 닫을 수 있다.

## 14. 예외 떠넘기기

메소드 내부에서 예외가 발생할 수 있을 때, 메소드 안에서 직접 처리하지 않고 호출한 곳으로 넘길 수 있다.

이때 `throws` 키워드를 사용한다.

    void method() throws Exception {
        // 예외 발생 가능 코드
    }

즉, `throws`는 "이 메소드를 호출한 쪽에서 예외를 처리하라"는 의미이다.

## 15. 예외를 떠넘기는 이유

예외를 항상 발생한 위치에서 처리하는 것이 좋은 것은 아니다.

예를 들어 `method2`의 결과를 받은 뒤 `method1`에서 전체 흐름을 보고 처리해야 하는 경우라면, 중간의 `method2`에서 예외를 처리하지 않고 `method1`로 넘기는 것이 더 적절하다.

즉,

- 예외 발생 위치에서 바로 처리
- 상위 호출자에게 넘겨서 처리

중에서 상황에 맞게 선택해야 한다.

## 16. main까지 예외가 올라가는 경우

런타임 예외는 `try-catch`가 필수가 아니므로 예외가 자동으로 호출자에게 전달될 수 있다.

예외가 계속 처리되지 않으면 `main` 메소드까지 올라갈 수 있다.  
그리고 `main`에서도 처리되지 않으면 프로그램은 강제 종료된다. ⚠️

학습할 때는 다음처럼 작성해서 main에서도 예외 처리를 생략할 수 있다.

    public static void main(String[] args) throws Exception {
        ...
    }

하지만 실제 프로그램에서는 무조건 떠넘기기보다 적절한 위치에서 처리하는 것이 좋다.

## 17. 사용자 정의 예외

사용자 정의 예외는 직접 예외 클래스를 만들어 사용하는 방식이다.

상속 대상은 보통 다음 둘 중 하나이다.

- `Exception`
- `RuntimeException`

차이는 다음과 같다.

- `Exception` 상속 → 예외 처리 필수
- `RuntimeException` 상속 → 예외 처리 선택

즉, 예외 처리를 강제하고 싶으면 `Exception`, 선택 사항으로 두고 싶으면 `RuntimeException`을 상속한다.

## 18. 사용자 정의 예외 생성자

사용자 정의 예외에서는 기본 생성자보다 예외 메시지를 입력받는 생성자를 자주 사용한다.

형식은 다음과 같다.

    public XXXException(String message) {
        super(message);
    }

여기서 `super(message)`는 부모 예외 클래스에 예외 메시지를 전달하는 역할을 한다.

이렇게 하면 나중에 `getMessage()`로 해당 메시지를 확인할 수 있다. 📌

## 19. 예외 발생시키기

예외를 직접 발생시키려면 `throw` 키워드를 사용한다.

예외를 직접 처리하는 경우는 다음과 같다.

    try {
        throw new Exception("예외메시지");
    } catch (Exception e) {
        String message = e.getMessage();
    }

이 경우 `throw`로 예외를 발생시키고, 바로 `catch`에서 처리한다.

## 20. 예외 던지기

예외를 직접 처리하지 않고 호출한 곳으로 넘기려면 `throws`를 사용한다.

    void method() throws Exception {
        throw new Exception("예외메시지");
    }

정리하면 다음과 같다.

- `throw` → 예외를 실제로 발생시킴
- `throws` → 발생한 예외를 호출한 곳으로 떠넘김

두 키워드는 이름이 비슷하지만 역할이 다르므로 반드시 구분해야 한다. ⚠️

## 21. 중요 포인트 📌

- 에러는 복구할 수 없는 상황이고, 예외는 복구 가능한 상황이다.
- 일반 예외는 예외 처리가 필수이고, 런타임 예외는 필수가 아니다.
- 예외의 최상위 타입은 `Throwable`이다.
- `Exception`과 `Error`는 `Throwable`을 상속한다.
- `try`는 예외 발생 가능 구간, `catch`는 예외 처리 구간, `finally`는 항상 실행되는 구간이다.
- 예외가 발생하면 try 블록의 남은 코드는 실행되지 않고 catch로 이동한다.
- `printStackTrace()`는 콜스택까지 출력한다.
- `Class.forName()`은 예외 처리가 필요하다.
- 다중 catch에서는 자식 예외를 먼저, 부모 예외를 나중에 작성해야 한다.
- 여러 예외를 동일 처리할 때는 `|`를 사용할 수 있다.
- 리소스 자동 닫기는 try-with-resources를 사용한다.
- 자동 닫기 대상은 `AutoCloseable`을 구현해야 한다.
- `throws`는 예외를 호출한 곳으로 떠넘긴다.
- `throw`는 예외를 직접 발생시킨다.
- 사용자 정의 예외는 `Exception` 또는 `RuntimeException`을 상속해서 만든다.

## 정리 ✅

예외 처리는 프로그램 실행 중 발생할 수 있는 문제를 처리하고 복구하기 위한 문법이다.  
일반 예외는 반드시 처리해야 하고, 런타임 예외는 처리 필수는 아니지만 발생 시 프로그램이 종료될 수 있으므로 주의해야 한다.  
`try-catch-finally`를 이용해 예외 발생 구간과 처리 구간, 클린업 구간을 나눌 수 있으며, 리소스 자동 닫기는 try-with-resources를 사용한다.  
또한 `throw`는 예외를 직접 발생시키는 키워드이고, `throws`는 예외를 호출한 곳으로 떠넘기는 키워드이다.  
시험 대비에서는 예외 계층 구조, 일반 예외와 런타임 예외 차이, 다중 catch 순서, try-with-resources, 사용자 정의 예외, `throw`와 `throws`의 차이를 꼭 정리해야 한다.
