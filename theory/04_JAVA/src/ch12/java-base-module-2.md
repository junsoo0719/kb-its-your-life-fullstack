# ✨ java.base 모듈2

## 1. 문자열 관련 클래스

`java.base` 모듈에는 문자열을 다루기 위한 여러 클래스가 포함되어 있다.

대표적인 클래스는 다음과 같다.

- `String` → 문자열 저장과 기본 조작
- `StringBuilder` → 효율적인 문자열 조작
- `StringTokenizer` → 구분자로 연결된 문자열 분리

즉, 문자열을 단순히 저장할 때는 `String`, 문자열 변경 작업이 많을 때는 `StringBuilder`, 구분자를 기준으로 나눌 때는 `StringTokenizer`를 사용할 수 있다. 📌

## 2. StringBuilder

`StringBuilder`는 문자열을 자주 변경해야 할 때 `String`보다 효율적인 클래스이다.

`String`은 불변 객체이므로 문자열을 수정할 때마다 새로운 객체가 만들어진다.  
반면 `StringBuilder`는 내부 문자열을 변경하면서 작업하므로 문자열 조작이 많을 때 더 효율적이다.

자주 사용하는 메소드는 다음과 같다.

- `append()` → 문자열 뒤에 추가
- `insert()` → 특정 위치에 삽입
- `delete()` → 특정 범위 삭제
- `replace()` → 특정 범위 문자열 대체

위 4개 메소드의 리턴 타입은 `StringBuilder`이다.  
따라서 메소드 체이닝이 가능하다. ✅

예를 들면 다음과 같다.

    String result = new StringBuilder()
        .append("Hello")
        .append(" Java")
        .toString();

보통 체이닝의 마지막에는 `toString()`을 사용해서 `String` 타입으로 변환한다.

## 3. StringTokenizer

`StringTokenizer`는 구분자로 연결된 문자열을 분리할 때 사용한다.

예를 들어 한 종류의 구분자로 문자열이 나뉘어 있을 때 사용하기 좋다.

    StringTokenizer st = new StringTokenizer("A/B/C", "/");

반복해서 토큰을 꺼낼 때는 `hasMoreTokens()`를 사용한다.

    while (st.hasMoreTokens()) {
        String token = st.nextToken();
    }

`split()`도 문자열 분리에 사용할 수 있다.

    str.split("/");

즉, 구분자를 기준으로 문자열을 나누고 배열로 받고 싶다면 `split()`, 토큰을 하나씩 꺼내고 싶다면 `StringTokenizer`를 사용할 수 있다.

## 4. Wrapper 클래스

Wrapper 클래스는 기본 타입의 값을 객체로 감싸는 클래스이다.

즉, 기본 타입 값을 객체처럼 다룰 수 있게 해 준다.

대표적인 예는 다음과 같다.

- `int` → `Integer`
- `double` → `Double`
- `boolean` → `Boolean`
- `char` → `Character`

Wrapper 클래스는 다음과 같은 목적으로 사용된다.

- 기본 타입 값을 객체로 다루기 위해
- `equals()`로 내부 값 비교를 하기 위해
- 문자열을 기본 타입 값으로 변환하기 위해

대부분의 Wrapper 클래스에는 문자열을 기본 타입으로 바꾸는 정적 메소드가 있다.

    Integer.parseInt("10")
    Double.parseDouble("3.14")
    Boolean.parseBoolean("true")

즉, `parse + 기본타입명` 형태의 메소드를 자주 사용한다. 📌

## 5. Math 클래스

`Math` 클래스는 수학 계산에 필요한 정적 메소드를 제공한다.

대표적인 메소드는 다음과 같다.

- `abs()` → 절댓값
- `ceil()` → 올림
- `floor()` → 내림
- `max()` → 최댓값
- `min()` → 최솟값
- `random()` → 0.0 이상 1.0 미만의 double 난수
- `round()` → 반올림

`Math.random()`은 다음 범위의 난수를 반환한다.

    0.0 <= 값 < 1.0

즉, 0은 포함하고 1은 포함하지 않는다. ⚠️

## 6. Random 클래스

`Random` 클래스는 난수를 생성할 때 사용하는 클래스이다.

객체 생성 방식은 다음과 같다.

    Random random = new Random();

또는 seed 값을 지정할 수도 있다.

    Random random = new Random(10L);

필기 기준으로 정리하면 다음과 같다.

- `Random()` → 일반적인 난수 생성
- `Random(long seed)` → seed 기반 난수 생성

알고리즘 개발 단계에서는 동일한 난수값이 반복되면 테스트하기 좋기 때문에 seed를 지정하는 방식이 유용할 수 있다.  
실전 운영 단계에서는 상황에 따라 적절한 방식으로 난수를 생성한다.

## 7. Random 주요 메소드

`Random` 클래스의 주요 메소드는 다음과 같다.

- `nextBoolean()` → boolean 난수
- `nextDouble()` → double 난수
- `nextInt()` → int 범위 난수
- `nextInt(int n)` → 0부터 n-1 사이의 난수

예를 들어 다음 코드는 0부터 5까지의 정수를 생성한다.

    random.nextInt(6)

즉, `nextInt(n)`은 n을 포함하지 않는다는 점을 기억해야 한다. 📌

## 8. Arrays.sort()

배열을 정렬할 때는 `Arrays.sort()`를 사용할 수 있다.

    Arrays.sort(배열명);

이 메소드는 배열을 오름차순으로 정렬한다.  
정렬 알고리즘은 내부적으로 빠른 정렬 방식을 사용하므로 일반적인 상황에서 효율적이다.

즉, 배열 정렬이 필요할 때 직접 정렬 알고리즘을 만들지 않고 `Arrays.sort()`를 사용할 수 있다. ✅

## 9. 날짜 관련 클래스

자바에서 날짜와 시간을 다룰 때 여러 클래스가 사용된다.

대표적인 클래스는 다음과 같다.

- `Date` → 날짜 정보
- `Calendar` → 날짜와 요일 정보
- `LocalDateTime` → 날짜와 시간 조작, `Date`의 확장 버전처럼 사용 가능

현재 날짜 객체를 만들 때는 다음처럼 작성한다.

    new Date()

`Calendar`는 직접 `new`로 만들기보다 다음처럼 사용한다.

    Calendar.getInstance()

월 정보를 계산할 때는 주의가 필요하다.  
`Calendar`에서 월은 0부터 시작하므로 실제 월로 사용할 때는 `+1`이 필요하다. ⚠️

## 10. SimpleDateFormat

`SimpleDateFormat`은 날짜를 원하는 문자열 형식으로 변환할 때 사용한다.

예를 들면 다음과 같다.

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
    String strDate = sdf.format(new Date());

자주 사용하는 패턴 문자는 다음과 같다.

- `y` → 년
- `M` → 월
- `d` → 일
- `D` → 월 구분 없는 일
- `E` → 요일

즉, 날짜를 화면에 출력하거나 문자열로 저장할 때 많이 사용한다. 📌

## 11. DecimalFormat

`DecimalFormat`은 숫자를 원하는 형식의 문자열로 변환할 때 사용한다.

생성자를 통해 포맷을 지정한다.

    DecimalFormat df = new DecimalFormat("#,###");

즉, 숫자에 콤마를 붙이거나 소수점 자리를 조절하는 등 출력 형식을 지정할 때 사용한다.

## 12. 리플렉션

리플렉션은 `Class` 객체를 이용해 클래스 내부 정보인 메타데이터를 들여다보는 기능이다.

즉, 프로그램 실행 중에 클래스와 인터페이스의 메타 정보를 읽거나 다룰 수 있다.

리플렉션으로 확인할 수 있는 정보는 다음과 같다.

- 패키지 정보
- 클래스 이름
- 생성자
- 필드
- 메소드

즉, 코드 실행 중에 클래스 구조를 분석할 수 있는 기능이다. ✅

## 13. Class 객체 얻기

`Class` 객체를 얻는 방법은 대표적으로 세 가지가 있다.

### 13-1. 클래스 이름으로 얻기

클래스를 알고 있을 때 사용한다.

    Class clazz = 클래스이름.class;

static 멤버처럼 클래스 자체를 기준으로 접근한다.

### 13-2. 문자열 이름으로 얻기

패키지명을 포함한 클래스 이름을 문자열로 전달한다.

    Class clazz = Class.forName("패키지...클래스이름");

이 방식은 클래스 이름을 문자열로 받아 동적으로 로딩할 때 사용한다.  
단, 예외 처리가 필요할 수 있다.

### 13-3. 객체에서 얻기

이미 생성된 객체가 있을 때는 다음처럼 얻는다.

    Class clazz = 객체참조변수.getClass();

즉, 실제 객체가 어떤 클래스인지 확인할 때 사용할 수 있다.

## 14. Class 객체의 기본 정보 메소드

`Class` 객체로 클래스의 기본 정보를 얻을 수 있다.

대표적인 메소드는 다음과 같다.

- `getPackage()` → 패키지 정보
- `getSimpleName()` → 패키지를 제외한 클래스 이름
- `getName()` → 패키지를 포함한 전체 이름

필기에는 `getSimplaName()`으로 적혀 있지만, 정확한 메소드명은 `getSimpleName()`이다. ⚠️

## 15. 멤버 정보 얻기

리플렉션을 사용하면 생성자, 필드, 메소드 정보를 배열 형태로 얻을 수 있다.

생성자 정보는 다음 메소드로 얻는다.

    Constructor[] constructors = clazz.getDeclaredConstructors();

필드 정보는 다음 메소드로 얻는다.

    Field[] fields = clazz.getDeclaredFields();

메소드 정보는 다음 메소드로 얻는다.

    Method[] methods = clazz.getDeclaredMethods();

여기서 `getDeclared...()`는 해당 클래스에 선언된 멤버를 기준으로 가져온다.

반면 다음 메소드들도 있다.

- `getConstructors()`
- `getFields()`
- `getMethods()`

이 메소드들은 public 멤버와 상속받은 멤버까지 포함하는 방식으로 이해하면 된다.

## 16. 리소스 경로 얻기

`Class` 객체는 클래스 파일(`.class`) 경로 정보를 기준으로 상대 경로에 있는 리소스 파일 정보를 얻을 수 있다.

여기서 리소스란 `.java` 파일을 제외한 파일을 의미한다.

예를 들면 다음과 같은 파일들이 리소스에 해당한다.

- 이미지
- XML
- Property 파일

대표적인 메소드는 다음과 같다.

    URL url = clazz.getResource("파일명");
    InputStream is = clazz.getResourceAsStream("파일명");

각 메소드의 의미는 다음과 같다.

- `getResource(String name)` → 리소스 파일의 URL 반환
- `getResourceAsStream(String name)` → 리소스 파일의 InputStream 반환

즉, 클래스 기준 상대 경로로 파일을 읽어올 때 사용할 수 있다. 📌

## 17. 어노테이션

어노테이션은 `@` 기호를 사용해서 코드에 부가 정보를 붙이는 문법이다.

어노테이션은 다음과 같은 상황에서 사용된다.

1. 컴파일 시 사용 정보 전달
   - 예: `@Override`

2. 빌드 툴이 코드를 자동 생성할 때 사용 정보 전달
   - 예: Lombok 관련 어노테이션

3. 실행 시 특정 기능을 처리할 때 사용 정보 전달
   - 예: Spring에서 사용하는 어노테이션들

즉, 어노테이션은 코드에 의미 있는 메타데이터를 붙이는 기능이다. ✅

## 18. 어노테이션 선언

어노테이션은 `@interface` 뒤에 이름을 작성해서 선언한다.

    public @interface AnnotationName {
        String value();
    }

사용할 때는 다음처럼 작성한다.

    @AnnotationName(value = "값")

어노테이션 내부에서는 인터페이스 규칙에 따라 추상 메소드처럼 속성을 정의한다.  
이때 메소드명이 곧 속성명이 된다.

만약 기본 속성만 지정하는 경우에는 속성명을 생략하고 값만 작성할 수 있다.

    @AnnotationName("값")

## 19. 어노테이션 적용 대상

어노테이션을 어디에 붙일 수 있는지는 `@Target`으로 지정한다.

복수 지정도 가능하며, 이때 `{}` 배열 표기를 사용한다.

    @Target({ElementType.TYPE, ElementType.METHOD})

대표적인 `ElementType` 열거 상수는 다음과 같다.

- `TYPE` → 클래스, 인터페이스 등
- `ANNOTATION_TYPE` → 어노테이션 타입
- `FIELD` → 필드
- `CONSTRUCTOR` → 생성자
- `METHOD` → 메소드
- `LOCAL_VARIABLE` → 지역 변수

즉, 어노테이션을 클래스 앞에 붙일지, 메소드 앞에 붙일지 등을 지정하는 설정이다.

## 20. 어노테이션 유지 범위

어노테이션이 어느 시점까지 유지될지는 `@Retention`으로 지정한다.

즉, 어노테이션 정보가 다음 중 어디까지 살아 있을지 정한다.

- 소스 레벨
- 클래스 로딩 시점
- 런타임 시점

런타임 시점까지 유지되는 어노테이션은 리플렉션을 통해 실행 중에 읽고 활용할 수 있다. 📌

## 21. 중요 포인트 📌

- `String`은 문자열 저장과 조작에 사용한다.
- `StringBuilder`는 문자열 변경 작업이 많을 때 효율적이다.
- `StringBuilder`의 `append`, `insert`, `delete`, `replace`는 메소드 체이닝이 가능하다.
- `StringTokenizer`는 구분자로 문자열을 분리할 때 사용한다.
- Wrapper 클래스는 기본 타입 값을 객체로 포장한다.
- Wrapper 클래스는 문자열을 기본 타입으로 변환할 때도 사용한다.
- `Math` 클래스는 수학 계산용 정적 메소드를 제공한다.
- `Random.nextInt(n)`은 0부터 n-1 사이의 난수를 반환한다.
- `Arrays.sort()`는 배열을 정렬한다.
- `Date`, `Calendar`, `LocalDateTime`은 날짜와 시간 처리에 사용한다.
- `Calendar`의 월은 0부터 시작하므로 실제 월 계산 시 +1이 필요하다.
- `SimpleDateFormat`은 날짜를 문자열로 변환할 때 사용한다.
- 리플렉션은 `Class` 객체로 클래스 메타데이터를 확인하는 기능이다.
- `Class` 객체는 `.class`, `Class.forName()`, `getClass()`로 얻을 수 있다.
- `getDeclared...()` 계열은 해당 클래스에 선언된 멤버 정보를 가져온다.
- `getResource()`와 `getResourceAsStream()`은 리소스 파일 정보를 얻을 때 사용한다.
- 어노테이션은 코드에 메타데이터를 붙이는 문법이다.
- `@Target`은 어노테이션 적용 대상을 지정한다.
- `@Retention`은 어노테이션 유지 범위를 지정한다.

## 정리 ✅

`java.base 모듈2`에서는 문자열 처리, Wrapper 클래스, Math와 Random, 배열 정렬, 날짜 처리, 리플렉션, 어노테이션을 다룬다.  
`StringBuilder`는 문자열 변경 작업이 많을 때 효율적이고, `StringTokenizer`는 구분자를 기준으로 문자열을 나눌 때 사용한다.  
Wrapper 클래스는 기본 타입을 객체로 감싸고 문자열을 기본 타입으로 변환할 때도 사용된다.  
또한 `Class` 객체를 이용한 리플렉션은 클래스의 메타데이터를 읽는 기능이며, 어노테이션은 컴파일·빌드·실행 시점에 필요한 정보를 코드에 전달하는 메타데이터 문법이다.  
시험 대비에서는 `StringBuilder` 메소드 체이닝, Wrapper의 parse 메소드, Random 범위, 날짜 포맷, 리플렉션의 Class 객체 획득 방법, 어노테이션의 `@Target`과 `@Retention`을 함께 정리해 두는 것이 중요하다.
