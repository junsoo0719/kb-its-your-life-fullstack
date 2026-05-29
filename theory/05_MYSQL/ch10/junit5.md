# ✨ JUnit5

## 1. JUnit 개념

JUnit은 자바에서 단위 테스트를 작성하고 실행할 때 사용하는 테스트 라이브러리이다.  
단위 테스트는 프로그램의 작은 기능 단위가 의도한 대로 동작하는지 확인하는 테스트이다.

즉, JUnit은 작성한 코드가 제대로 동작하는지 자동으로 검증하기 위한 도구이다. 📌

## 2. Gradle과 JUnit5

빌드 시스템을 Gradle로 지정한 경우 JUnit5가 기본 단위 테스트 라이브러리로 설정될 수 있다.

이때 프로젝트에는 테스트 코드를 작성하기 위한 `test` 폴더가 자동으로 생성된다.

`test` 폴더는 실제 코드와 테스트 코드를 분리해서 관리하기 위한 공간이다.

즉,

- 실제 코드 → `src/main`
- 테스트 코드 → `src/test`

와 같은 구조로 나누어 관리한다. ✅

## 3. 테스트 코드 분리

테스트 코드는 실제 프로그램 코드와 분리해서 작성한다.

이렇게 분리하는 이유는 다음과 같다.

- 실제 실행 코드와 테스트 코드를 구분하기 위해
- 테스트 코드가 배포 코드에 섞이지 않도록 하기 위해
- 기능 검증 코드를 체계적으로 관리하기 위해

즉, 테스트 코드는 프로그램 품질을 확인하기 위한 별도의 코드이다.

## 4. @Test

`@Test`는 해당 메서드가 테스트 메서드임을 표시하는 어노테이션이다.

    @Test
    void testMethod() {
        ...
    }

JUnit은 `@Test`가 붙은 메서드를 테스트 케이스로 인식하고 실행한다.

즉, 테스트로 실행하고 싶은 메서드에는 반드시 `@Test`를 붙여야 한다. 📌

## 5. @DisplayName

`@DisplayName`은 테스트 이름을 보기 좋게 표시하기 위한 어노테이션이다.

    @DisplayName("테스트이름")
    @Test
    void testMethod() {
        ...
    }

테스트 실행 결과에서 해당 테스트를 구분하는 제목으로 사용된다.

즉, 테스트 메서드 이름만으로 의미가 부족할 때 설명용 이름을 붙이는 역할을 한다.

## 6. Assertions

`Assertions`는 테스트 결과를 검증하기 위한 여러 단정문을 제공하는 클래스이다.

JUnit에서는 `Assertions`의 static 메서드를 사용해 실제 결과가 기대 결과와 같은지 확인한다.

즉, 테스트는 단순히 코드를 실행하는 것이 아니라, 결과가 맞는지 단정문으로 검증해야 한다. ✅

## 7. assertEquals()

`assertEquals()`는 실제값과 기대값이 같은지 비교하는 단정문이다.

형식은 다음과 같다.

    assertEquals(실제값, 기대값);

필기 기준으로는 실제값과 기대값이 다르면 테스트가 실패하고 예외가 발생한다.

즉, 코드 실행 결과가 예상한 값과 일치하는지 확인할 때 사용한다.

예를 들면 다음과 같다.

    assertEquals(result, 10);

이 코드는 `result`가 10인지 확인한다.

## 8. 주요 어노테이션

JUnit5에서 테스트 실행 흐름을 제어할 때 사용하는 주요 어노테이션은 다음과 같다.

- `@BeforeAll`
- `@BeforeEach`
- `@Test`
- `@AfterEach`
- `@AfterAll`

이 어노테이션들은 테스트 실행 전후에 필요한 준비 작업과 정리 작업을 수행할 때 사용한다. 📌

## 9. @BeforeAll

`@BeforeAll`은 전체 테스트가 실행되기 전에 1회 호출된다.

특징은 다음과 같다.

- 전체 테스트 실행 전 1회 호출
- 클래스 레벨 설정에 사용
- static 메서드로 정의

즉, 모든 테스트가 공통으로 사용할 준비 작업을 한 번만 수행할 때 사용한다.

## 10. @BeforeEach

`@BeforeEach`는 각 테스트 케이스가 실행되기 전에 호출된다.

즉, 테스트 메서드마다 반복해서 실행된다.

예를 들어 테스트마다 새로운 객체를 만들거나, 초기 상태를 다시 세팅할 때 사용한다.

    @BeforeEach
    void setUp() {
        ...
    }

## 11. @AfterEach

`@AfterEach`는 각 테스트 케이스가 실행된 후 호출된다.

즉, 테스트 메서드가 끝날 때마다 반복해서 실행된다.

테스트 후 정리 작업이나 상태 초기화가 필요할 때 사용한다.

    @AfterEach
    void tearDown() {
        ...
    }

## 12. @AfterAll

`@AfterAll`은 전체 테스트가 모두 실행된 후 1회 호출된다.

특징은 다음과 같다.

- 전체 테스트 실행 후 1회 호출
- 클래스 레벨 정리에 사용
- static 메서드로 정의

즉, 모든 테스트가 끝난 뒤 한 번만 정리해야 하는 작업에 사용한다. ✅

## 13. JUnit5 실행 흐름

JUnit5의 테스트 실행 흐름은 다음과 같다.

1. `@BeforeAll`
   - 클래스 레벨 설정
   - 전체 테스트 실행 전 1회 실행

2. `@BeforeEach`
   - 메서드 레벨 설정
   - 각 테스트 실행 전마다 실행

3. `@Test`
   - 테스트 실행

4. `@AfterEach`
   - 메서드 레벨 정리
   - 각 테스트 실행 후마다 실행

5. 테스트 개수만큼 `@BeforeEach → @Test → @AfterEach` 반복

6. `@AfterAll`
   - 클래스 레벨 정리
   - 전체 테스트 실행 후 1회 실행

즉, 전체 준비와 정리는 한 번만 수행하고, 각 테스트마다 개별 준비와 정리를 반복한다. 📌

## 14. JUnit5 예시

간단한 테스트 예시는 다음과 같다.

    import org.junit.jupiter.api.*;

    import static org.junit.jupiter.api.Assertions.assertEquals;

    class CalculatorTest {

        @BeforeAll
        static void beforeAll() {
            System.out.println("전체 테스트 시작");
        }

        @BeforeEach
        void beforeEach() {
            System.out.println("각 테스트 시작 전");
        }

        @DisplayName("덧셈 테스트")
        @Test
        void addTest() {
            int result = 3 + 7;
            assertEquals(result, 10);
        }

        @AfterEach
        void afterEach() {
            System.out.println("각 테스트 종료 후");
        }

        @AfterAll
        static void afterAll() {
            System.out.println("전체 테스트 종료");
        }
    }

이 예시는 테스트 실행 전후 흐름과 `assertEquals()` 사용 방식을 함께 보여준다.

## 15. 중요 포인트 📌

- JUnit은 자바 단위 테스트 라이브러리이다.
- Gradle 프로젝트에서는 JUnit5가 기본 단위 테스트 라이브러리로 설정될 수 있다.
- `test` 폴더는 실제 코드와 테스트 코드를 분리해서 관리하는 공간이다.
- `@Test`는 테스트 메서드를 지정한다.
- `@DisplayName`은 테스트 구분 제목을 지정한다.
- `Assertions`는 여러 단정문을 static 메서드로 제공한다.
- `assertEquals()`는 실제값과 기대값을 비교한다.
- 값이 다르면 테스트 실패가 발생한다.
- `@BeforeAll`은 전체 테스트 실행 전 1회 호출된다.
- `@BeforeAll`은 static 메서드로 정의한다.
- `@BeforeEach`는 각 테스트 실행 전 호출된다.
- `@AfterEach`는 각 테스트 실행 후 호출된다.
- `@AfterAll`은 전체 테스트 실행 후 1회 호출된다.
- `@AfterAll`은 static 메서드로 정의한다.
- 실행 흐름은 `BeforeAll → BeforeEach → Test → AfterEach → AfterAll` 순서이다.

## 정리 ✅

JUnit5는 자바에서 단위 테스트를 작성하고 실행하기 위한 대표적인 테스트 라이브러리이다.  
Gradle 기반 프로젝트에서는 테스트 폴더가 자동으로 생성되어 실제 코드와 테스트 코드를 분리해서 관리할 수 있다.  
테스트 메서드는 `@Test`로 지정하고, `@DisplayName`으로 테스트 이름을 보기 좋게 표시할 수 있다.  
`Assertions`의 `assertEquals()` 같은 단정문을 사용해 실제 결과와 기대 결과를 비교하며, 값이 다르면 테스트가 실패한다.  
또한 `@BeforeAll`, `@BeforeEach`, `@AfterEach`, `@AfterAll`을 이용해 테스트 실행 전후의 준비와 정리 작업을 체계적으로 처리할 수 있다.
