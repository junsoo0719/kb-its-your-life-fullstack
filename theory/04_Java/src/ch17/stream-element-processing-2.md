# ✨ 스트림 요소 처리2

## 1. 스트림 인터페이스

스트림은 컬렉션이나 배열뿐만 아니라 숫자 범위, 난수 등 다양한 데이터 소스로부터 얻을 수 있다.

대표적인 스트림 생성 방식은 다음과 같다.

    Stream<T> Arrays.stream(T[])
    IntStream IntStream.range(int, int)
    IntStream IntStream.rangeClosed(int, int)
    IntStream Random.ints()

즉, 스트림은 객체 배열뿐 아니라 정수 범위나 랜덤 값처럼 반복 가능한 데이터 흐름을 만들 때도 사용할 수 있다. 📌

## 2. 컬렉션으로부터 스트림 얻기

컬렉션에서는 `stream()` 메소드를 사용해서 스트림을 얻을 수 있다.

    list.stream()

이 코드는 `list`의 요소를 순차적으로 처리할 수 있는 스트림을 만든다.

병렬 처리가 필요한 경우에는 `parallelStream()`을 사용할 수 있다.

    set.parallelStream()

`parallelStream()`은 여러 스레드를 활용해서 요소를 병렬로 처리할 수 있다.  
다만 병렬 처리가 항상 더 빠른 것은 아니므로 데이터 양과 작업 특성을 고려해야 한다. ⚠️

## 3. 배열로부터 스트림 얻기

배열에서는 `Arrays.stream()`을 사용해서 스트림을 얻을 수 있다.

    Arrays.stream(arr)

예를 들어 문자열 배열을 스트림으로 바꾸면 다음과 같다.

    String[] arr = {"A", "B", "C"};
    Stream<String> stream = Arrays.stream(arr);

즉, 배열도 컬렉션처럼 스트림으로 변환해서 같은 방식으로 처리할 수 있다.

## 4. 숫자 범위로부터 스트림 얻기

숫자 범위를 기준으로 스트림을 만들 때는 `IntStream.range()` 또는 `IntStream.rangeClosed()`를 사용한다.

    IntStream.range(1, 100)

`range(1, 100)`은 1부터 99까지의 정수 스트림을 만든다.  
즉, 끝 값은 포함하지 않는다.

반면 `rangeClosed()`는 끝 값까지 포함한다.

    IntStream.rangeClosed(1, 100)

이 코드는 1부터 100까지의 정수 스트림을 만든다.

즉, 일반적인 `for`문에서 1부터 100까지 반복하는 것과 비슷하게 사용할 수 있다. ✅

## 5. LongStream

정수 범위 스트림은 `int`뿐만 아니라 `long` 타입도 사용할 수 있다.

즉, 큰 범위의 숫자 흐름이 필요하다면 `LongStream` 계열을 사용할 수 있다.

예를 들면 다음과 같은 방식이다.

    LongStream.rangeClosed(1L, 100L)

즉, 숫자 범위 스트림은 타입에 따라 `IntStream`, `LongStream` 등으로 나뉜다.

## 6. Random으로부터 스트림 얻기

`Random` 클래스도 스트림을 만들 수 있다.

대표적으로 다음 메소드를 사용할 수 있다.

    Random.ints()

이 메소드는 int 난수 스트림을 만든다.

즉, 랜덤한 숫자를 여러 개 생성해서 스트림 방식으로 처리할 수 있다.

## 7. 필터링

필터링은 스트림의 중간 처리 중 하나이다.  
전체 요소 중에서 필요한 요소만 남기거나, 중복을 제거할 때 사용한다.

대표적인 필터링 메소드는 다음과 같다.

- `distinct()`
- `filter()`

필터링은 원본 데이터를 바로 바꾸는 것이 아니라, 조건에 맞는 요소만 지나가도록 중간 스트림을 만든다. 📌

## 8. distinct()

`distinct()`는 중복 요소를 제거하는 메소드이다.

    stream.distinct()

즉, 스트림 안에서 같은 값이 여러 번 등장할 경우 하나만 남기고 중복을 제거한다.

객체의 중복 판단은 객체의 `equals()`와 `hashCode()` 기준과 연결될 수 있다.  
따라서 사용자 정의 객체를 중복 제거하려면 이 두 메소드의 재정의가 중요할 수 있다. ⚠️

## 9. filter()

`filter()`는 조건에 맞는 요소만 남기는 메소드이다.

    stream.filter(item -> 조건식)

`filter()`의 매개값으로는 `Predicate`가 사용된다.  
`Predicate`는 매개변수를 받아서 `boolean`을 리턴하는 함수형 인터페이스이다.

즉, `filter()`는 `Predicate`가 `true`를 리턴하는 요소만 통과시킨다.

예를 들면 다음과 같다.

    stream.filter(item -> item.length() > 3)

이 코드는 문자열 길이가 3보다 큰 요소만 남긴다.

## 10. Predicate와 filter의 관계

`Predicate`는 조건 검사를 담당하는 함수형 인터페이스이다.

형태는 다음과 같다.

    boolean test(T t)

`filter()`는 내부적으로 이 `Predicate`의 결과를 보고 요소를 남길지 제거할지 결정한다.

즉,

- `true` 반환 → 요소 유지
- `false` 반환 → 요소 제외

로 이해하면 된다. ✅

## 11. 중요 포인트 📌

- 스트림은 컬렉션, 배열, 숫자 범위, 난수 등에서 얻을 수 있다.
- 컬렉션은 `stream()`으로 스트림을 얻는다.
- 병렬 스트림은 `parallelStream()`으로 얻는다.
- 배열은 `Arrays.stream()`으로 스트림을 얻는다.
- `IntStream.range(a, b)`는 a부터 b 전까지 포함한다.
- `IntStream.rangeClosed(a, b)`는 a부터 b까지 포함한다.
- `LongStream`을 이용하면 long 타입 범위 스트림도 만들 수 있다.
- `Random.ints()`는 int 난수 스트림을 만든다.
- `distinct()`는 중복을 제거한다.
- `filter()`는 조건에 맞는 요소만 남긴다.
- `filter()`의 조건은 `Predicate`로 작성된다.
- `Predicate`는 boolean 값을 리턴하는 함수형 인터페이스이다.

## 정리 ✅

스트림은 컬렉션이나 배열뿐만 아니라 숫자 범위와 난수에서도 얻을 수 있다.  
컬렉션에서는 `stream()`과 `parallelStream()`을 사용하고, 배열에서는 `Arrays.stream()`을 사용한다.  
숫자 범위는 `IntStream.range()`와 `IntStream.rangeClosed()`로 만들 수 있으며, 두 메소드는 끝 값을 포함하는지 여부가 다르다.  
필터링에서는 `distinct()`로 중복을 제거하고, `filter()`로 조건에 맞는 요소만 남길 수 있다.  
시험 대비에서는 스트림 생성 방법, `range`와 `rangeClosed` 차이, `distinct()`와 `filter()`의 역할, `Predicate`의 의미를 함께 정리해 두는 것이 중요하다.
