# ✨ [보충] - 표준 API의 함수적 인터페이스

## 1. 함수적 인터페이스의 구분

자바 표준 API의 함수적 인터페이스는 인터페이스에 선언된 추상 메소드의 형태에 따라 구분할 수 있다.

구분 기준은 다음 두 가지이다.

- 매개값이 있는지
- 리턴값이 있는지

즉, 함수적 인터페이스는 "입력값을 받는가?"와 "결과값을 돌려주는가?"를 기준으로 나누어 이해하면 쉽다. 📌

## 2. 대표 함수적 인터페이스 종류

표준 API에서 자주 사용하는 함수적 인터페이스는 다음과 같다.

- `Runnable`
- `Consumer`
- `Supplier`
- `Predicate`
- `Function`
- `Operator`

각 인터페이스는 추상 메소드의 형태가 다르기 때문에 사용하는 목적도 다르다.

## 3. Runnable

`Runnable`은 매개변수도 없고 리턴값도 없는 함수적 인터페이스이다.

특징은 다음과 같다.

- 매개변수 없음
- 리턴값 없음

즉, 단순히 어떤 작업을 실행하기 위한 목적으로 사용된다.

## 4. Consumer

`Consumer`는 매개변수는 있지만 리턴값은 없는 함수적 인터페이스이다.

즉, 값을 받아서 소비하는 역할을 한다.

대표적인 형태는 다음과 같다.

    Consumer<T>

추상 메소드는 다음과 같다.

    void accept(T t)

매개변수가 2개인 경우에는 `BiConsumer<T, U>`를 사용한다.

    BiConsumer<T, U>

추상 메소드는 다음과 같다.

    void accept(T t, U u)

정리하면 다음과 같다.

- `Consumer<T>` → 객체 1개를 받아 소비
- `BiConsumer<T, U>` → 객체 2개를 받아 소비

즉, Consumer는 전달받은 값을 출력하거나 저장하거나 처리할 때 사용할 수 있다. ✅

## 5. Supplier

`Supplier`는 매개변수는 없고 리턴값은 있는 함수적 인터페이스이다.

즉, 외부에서 값을 받지 않고 어떤 객체를 만들어서 제공하는 역할을 한다.

대표적인 형태는 다음과 같다.

    Supplier<T>

추상 메소드는 다음과 같다.

    T get()

여기서 제네릭 `T`는 리턴 타입을 의미한다.

즉, Supplier는 값을 공급하는 역할이다. 📌

## 6. Predicate

`Predicate`는 매개변수를 받고 `boolean` 타입 값을 리턴하는 함수적 인터페이스이다.

즉, 전달받은 객체가 조건을 만족하는지 검사할 때 사용한다.

대표적인 형태는 다음과 같다.

    Predicate<T>

추상 메소드는 다음과 같다.

    boolean test(T t)

매개변수가 2개인 경우에는 `BiPredicate<T, U>`를 사용한다.

    BiPredicate<T, U>

추상 메소드는 다음과 같다.

    boolean test(T t, U u)

정리하면 다음과 같다.

- `Predicate<T>` → 객체 1개를 검사
- `BiPredicate<T, U>` → 객체 2개를 검사
- 리턴 타입은 항상 `boolean`

즉, Predicate는 조건 판단에 사용된다.

## 7. Function

`Function`은 매개변수도 있고 리턴값도 있는 함수적 인터페이스이다.

특히 매개변수와 리턴값의 타입이 다를 수 있다.  
즉, 입력받은 객체를 다른 형태의 결과로 변환하거나 매핑할 때 사용한다.

대표적인 형태는 다음과 같다.

    Function<T, R>

추상 메소드는 다음과 같다.

    R apply(T t)

여기서 의미는 다음과 같다.

- `T` → 매개변수 타입
- `R` → 리턴 타입

매개변수가 2개인 경우에는 `BiFunction<T, U, R>`을 사용한다.

    BiFunction<T, U, R>

추상 메소드는 다음과 같다.

    R apply(T t, U u)

여기서 마지막 제네릭 `R`이 리턴 타입이다. 📌

## 8. Function의 활용

Function은 객체를 다른 객체로 변환할 때 자주 사용한다.

예를 들어 다음과 같은 상황에서 많이 쓰인다.

- `List<Member>` → `List<String>`
- Member 객체에서 이름만 추출
- 객체를 DTO로 변환
- 입력값을 계산 결과로 변환

즉, Function은 "입력 타입을 결과 타입으로 매핑하는 역할"을 한다.

생성자는 경우에 따라 다음처럼 볼 수 있다.

- 매개변수가 없는 생성 → `Supplier`
- 매개변수가 있는 생성 → `Function`

즉, 객체 생성 방식에 따라 Supplier처럼 볼 수도 있고 Function처럼 볼 수도 있다.

## 9. Operator

`Operator`는 `Function`의 특수한 형태이다.

`Function`은 입력 타입과 리턴 타입이 다를 수 있지만, `Operator`는 매개변수와 리턴값이 같은 성격을 가진다.

즉, 같은 타입의 값을 받아 같은 타입의 결과를 돌려줄 때 사용한다. ✅

## 10. UnaryOperator

`UnaryOperator<T>`는 `Function<T, R>`의 하위 인터페이스이다.

특징은 다음과 같다.

- 매개변수 1개
- 리턴값 1개
- 매개변수와 리턴 타입이 같음

즉, `T`를 받아서 `T`를 리턴한다.

    UnaryOperator<T>

의미는 다음과 같다.

    T 연산 후 T 리턴

## 11. BinaryOperator

`BinaryOperator<T>`는 `BiFunction<T, U, R>`의 하위 인터페이스이다.

특징은 다음과 같다.

- 매개변수 2개
- 리턴값 1개
- 매개변수와 리턴 타입이 같은 성격

즉, 같은 타입의 값 2개를 받아 같은 타입의 결과를 리턴할 때 사용한다.

    BinaryOperator<T>

의미는 다음과 같다.

    T, T 연산 후 T 리턴

## 12. 함수적 인터페이스별 메소드명

함수적 인터페이스는 각각 호출하는 메소드명이 정해져 있다.

정리하면 다음과 같다.

- `Runnable` → `run()`
- `Consumer` → `accept()`
- `Supplier` → `get()`
- `Predicate` → `test()`
- `Function` → `apply()`
- `Operator` → `apply()`

이 메소드명은 람다식과 함께 사용할 때 매우 중요하다.  
시험이나 코드 작성 시 어떤 인터페이스가 어떤 메소드를 가지는지 구분할 수 있어야 한다. 📌

## 13. 전체 비교

표준 함수적 인터페이스를 매개변수와 리턴값 기준으로 정리하면 다음과 같다.

| 인터페이스 | 매개변수 | 리턴값  | 주요 메소드 | 역할 |
| ---------- | -------- | ------- | ----------- | ---- |
| Runnable   | X        | X       | run()       | 실행 |
| Consumer   | O        | X       | accept()    | 소비 |
| Supplier   | X        | O       | get()       | 공급 |
| Predicate  | O        | boolean | test()      | 검사 |
| Function   | O        | O       | apply()     | 변환 |
| Operator   | O        | O       | apply()     | 연산 |

즉, 함수적 인터페이스는 메소드의 입력과 출력 형태를 기준으로 구분하면 훨씬 쉽게 외울 수 있다. ✅

## 14. 중요 포인트 📌

- 함수적 인터페이스는 추상 메소드의 매개변수와 리턴값 유무에 따라 구분한다.
- `Runnable`은 매개변수와 리턴값이 모두 없다.
- `Consumer`는 값을 받아 소비하고 리턴값은 없다.
- `Supplier`는 매개변수 없이 값을 공급한다.
- `Predicate`는 값을 검사하고 `boolean`을 리턴한다.
- `Function`은 값을 받아 다른 타입의 결과로 변환한다.
- `Operator`는 `Function`의 특수한 형태이며 입력과 출력 타입의 성격이 같다.
- `BiConsumer`, `BiPredicate`, `BiFunction`은 매개변수가 2개인 형태이다.
- `UnaryOperator`는 매개변수 1개, `BinaryOperator`는 매개변수 2개를 사용한다.
- 각 함수적 인터페이스의 메소드명을 반드시 기억해야 한다.

## 정리 ✅

표준 API의 함수적 인터페이스는 람다식과 함께 자주 사용되는 핵심 개념이다.  
각 인터페이스는 추상 메소드의 매개변수와 리턴값 유무에 따라 역할이 나뉜다.  
`Consumer`는 값을 소비하고, `Supplier`는 값을 공급하며, `Predicate`는 조건을 검사하고, `Function`은 값을 변환한다.  
`Operator`는 Function의 특수한 형태로, 매개변수와 리턴값이 같은 성격을 가진다.  
시험 대비에서는 각 인터페이스의 역할, 제네릭 의미, 메소드명인 `accept`, `get`, `test`, `apply`를 반드시 구분해서 정리해야 한다.
