# ✨ 스프링의 특징과 의존성 주입

## 1. 스프링의 주요 특징

스프링은 자바 기반 애플리케이션을 더 쉽게 개발할 수 있도록 도와주는 프레임워크이다.

스프링의 주요 특징은 다음과 같다.

- POJO 기반의 구성
- 의존성 주입
- AOP 지원
- 편리한 MVC 구조
- WAS에 종속적이지 않은 개발 환경

즉, 스프링은 객체를 직접 생성하고 관리하는 부담을 줄이고, 애플리케이션 구조를 더 유연하게 만들 수 있도록 도와준다. 📌

## 2. POJO 기반의 구성

POJO는 Plain Old Java Object의 약자이다.

특정 프레임워크나 라이브러리에 강하게 의존하지 않는 일반 자바 객체를 의미한다.

스프링은 POJO 기반으로 애플리케이션을 구성할 수 있도록 지원한다.

즉, 스프링을 사용하더라도 핵심 비즈니스 객체는 일반 자바 클래스로 작성할 수 있다. ✅

예시는 다음과 같다.

    public class MemberService {
        public void join() {
            System.out.println("회원가입 처리");
        }
    }

이 클래스는 특정 스프링 클래스를 상속하지 않아도 된다.

## 3. 의존성 주입

의존성 주입은 Dependency Injection, 줄여서 DI라고 한다.

객체가 필요한 의존 객체를 직접 생성하지 않고, 외부에서 주입받는 방식이다.

예를 들어 서비스 객체가 DAO 객체를 필요로 할 때 직접 `new`로 생성하지 않고, 스프링 컨테이너가 필요한 객체를 넣어준다.

    public class MemberService {
        private MemberDAO memberDAO;

        public MemberService(MemberDAO memberDAO) {
            this.memberDAO = memberDAO;
        }
    }

즉, 의존성 주입을 사용하면 객체 간 결합도를 낮출 수 있다. 📌

## 4. AOP 지원

AOP는 Aspect Oriented Programming의 약자이다.

공통 관심사를 핵심 로직과 분리해서 관리하는 방식이다.

예를 들어 여러 메서드에서 반복되는 로그 출력, 트랜잭션 처리, 권한 검사 같은 기능을 따로 분리할 수 있다.

즉, AOP를 사용하면 핵심 비즈니스 로직은 깔끔하게 유지하고, 공통 기능은 별도로 관리할 수 있다. ✅

## 5. 편리한 MVC 구조

스프링은 MVC 구조를 편리하게 구현할 수 있도록 지원한다.

MVC는 다음 세 가지 역할로 나뉜다.

- Model
- View
- Controller

Controller는 요청을 받고, Model은 데이터를 담고, View는 화면을 담당한다.

즉, 스프링 MVC를 사용하면 웹 애플리케이션의 요청 처리 구조를 체계적으로 관리할 수 있다. 📌

## 6. WAS에 종속적이지 않은 개발 환경

스프링은 특정 WAS에 강하게 종속되지 않는 개발 환경을 제공한다.

WAS는 Web Application Server를 의미한다.

스프링을 사용하면 애플리케이션의 핵심 로직을 WAS와 분리해서 작성할 수 있으므로 테스트와 유지보수가 쉬워진다.

즉, 특정 서버 환경에 묶이지 않고 더 유연하게 개발할 수 있다.

## 7. 스프링 테스트 설정

스프링을 테스트할 때는 JUnit만 단독으로 사용하는 것이 아니라 스프링 테스트 확장 기능을 함께 사용할 수 있다.

대표적으로 다음 어노테이션을 사용한다.

- `@ExtendWith(SpringExtension.class)`
- `@ContextConfiguration(classes = {RootConfig.class})`
- `@Autowired`

이 어노테이션들을 사용하면 테스트 코드에서도 스프링 컨텍스트를 준비하고, 스프링 빈을 주입받아 테스트할 수 있다. ✅

## 8. @ExtendWith(SpringExtension.class)

`@ExtendWith(SpringExtension.class)`는 JUnit 테스트에서 스프링 확장 기능을 함께 사용하겠다는 의미이다.

즉, JUnit 단독 테스트가 아니라 스프링을 테스트 환경에 연결하는 설정이다.

    @ExtendWith(SpringExtension.class)
    class SampleTests {
    }

이 어노테이션을 사용하면 테스트 코드에서 스프링 컨텍스트와 관련된 기능을 사용할 수 있다. 📌

## 9. @ContextConfiguration

`@ContextConfiguration`은 테스트에서 어떤 스프링 컨텍스트를 준비해야 하는지 지정하는 어노테이션이다.

예시는 다음과 같다.

    @ContextConfiguration(classes = {RootConfig.class})

이 설정은 테스트 실행 시 `RootConfig.class`를 기반으로 스프링 컨텍스트를 생성하겠다는 의미이다.

즉, 테스트에서도 실제 스프링 설정 클래스를 이용해 빈을 등록하고 사용할 수 있다. ✅

## 10. @Autowired

`@Autowired`는 스프링 컨텍스트에 등록된 빈을 자동으로 주입받을 때 사용한다.

테스트 코드에서는 필요한 객체를 직접 생성하지 않고 `@Autowired`로 주입받아 테스트할 수 있다.

    @Autowired
    private MemberService memberService;

단위 테스트에서는 `@Autowired` 사용이 권장될 수 있다.

이유는 테스트 대상 객체가 스프링 컨테이너에서 정상적으로 생성되고 주입되는지 함께 확인할 수 있기 때문이다. 📌

## 11. @Log4j2

`@Log4j2`는 Lombok을 이용해 로그를 기록하는 Logger 변수를 자동으로 생성하는 어노테이션이다.

직접 Logger 객체를 선언하지 않아도 로그를 사용할 수 있다.

    @Log4j2
    public class SampleTests {

        @Test
        void testLog() {
            log.info("테스트 로그 출력");
        }
    }

즉, `@Log4j2`를 사용하면 `log` 변수를 바로 사용할 수 있다. ✅

## 12. 로그 설정 파일

로그 설정 파일은 실행 환경에 따라 위치가 다를 수 있다.

운영 또는 일반 실행을 위한 로그 설정 파일은 다음 위치에 둔다.

    src/main/resources/log4j2.xml

테스트를 위한 로그 설정 파일은 다음 위치에 둔다.

    src/test/resources/log4j2.xml

즉, main과 test 환경의 로그 설정을 분리해서 관리할 수 있다. 📌

## 13. 테스트용 로그 설정

테스트 실행 시에는 `src/test/resources/log4j2.xml` 파일이 있으면 해당 설정이 적용된다.

만약 테스트용 로그 설정 파일이 없다면 운영을 위한 로그 설정인 `src/main/resources/log4j2.xml`이 적용된다.

정리하면 다음과 같다.

- 테스트용 설정 있음 → `src/test/resources/log4j2.xml` 적용
- 테스트용 설정 없음 → `src/main/resources/log4j2.xml` 적용

즉, 테스트 환경에서 로그 출력 방식을 따로 조절하고 싶다면 test resources에 별도 설정 파일을 두면 된다. ✅

## 14. 스프링 테스트 흐름

스프링 테스트의 기본 흐름은 다음과 같다.

1. `@ExtendWith(SpringExtension.class)`로 스프링 테스트 확장 사용
2. `@ContextConfiguration`으로 사용할 설정 클래스 지정
3. 스프링 컨텍스트 생성
4. 설정 클래스에 등록된 빈 생성
5. `@Autowired`로 테스트 클래스에 빈 주입
6. 테스트 메서드에서 주입받은 빈 사용
7. `@Log4j2`로 테스트 로그 출력

즉, 테스트에서도 실제 스프링 컨테이너 기반으로 객체를 주입받아 확인할 수 있다. 📌

## 15. 중요 포인트 📌

- 스프링은 POJO 기반의 구성을 지원한다.
- 스프링은 의존성 주입을 통해 객체 간 결합도를 낮춘다.
- 스프링은 AOP를 통해 공통 관심사를 분리할 수 있다.
- 스프링은 편리한 MVC 구조를 제공한다.
- 스프링은 WAS에 종속적이지 않은 개발 환경을 제공한다.
- `@ExtendWith(SpringExtension.class)`는 JUnit 테스트에서 스프링 확장을 함께 사용한다는 의미이다.
- `@ContextConfiguration`은 테스트에 사용할 스프링 설정 클래스를 지정한다.
- `@Autowired`는 스프링 컨텍스트의 빈을 자동 주입한다.
- 단위 테스트에서는 `@Autowired`를 사용해 스프링 빈 주입을 확인할 수 있다.
- `@Log4j2`는 Lombok을 이용해 Logger 변수를 생성한다.
- 운영 로그 설정은 `src/main/resources/log4j2.xml`에 둔다.
- 테스트 로그 설정은 `src/test/resources/log4j2.xml`에 둔다.
- 테스트용 로그 설정이 없으면 운영 로그 설정이 적용된다.

## 정리 ✅

스프링의 주요 특징은 POJO 기반 구성, 의존성 주입, AOP 지원, 편리한 MVC 구조, WAS에 종속적이지 않은 개발 환경이다.  
POJO 기반 구성 덕분에 일반 자바 객체 중심으로 개발할 수 있고, 의존성 주입을 통해 객체가 필요한 의존 객체를 직접 생성하지 않고 외부에서 주입받을 수 있다.  
스프링 테스트에서는 `@ExtendWith(SpringExtension.class)`로 스프링 확장을 사용하고, `@ContextConfiguration`으로 어떤 설정 클래스를 기반으로 컨텍스트를 만들지 지정한다.  
또한 `@Autowired`를 이용해 테스트 코드에서도 스프링 빈을 주입받을 수 있으며, `@Log4j2`를 사용하면 Lombok이 Logger 변수를 자동으로 생성해 로그를 편리하게 출력할 수 있다.  
로그 설정은 운영용 `src/main/resources/log4j2.xml`과 테스트용 `src/test/resources/log4j2.xml`로 분리해서 관리할 수 있다.
