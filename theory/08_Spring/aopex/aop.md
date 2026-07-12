# ✨ AOP

## 1. AOP 개념

AOP는 Aspect Oriented Programming의 약자이다.

한국어로는 관점 지향 프로그래밍이라고 한다.

AOP는 핵심 비즈니스 로직과 공통 관심사 로직을 분리해서 관리하는 프로그래밍 방식이다. 📌

예를 들어 회원가입, 게시글 등록, 상품 주문 같은 기능은 핵심 비즈니스 로직이다.  
반면 로그 기록, 권한 검사, 예외 처리, 트랜잭션 처리 같은 기능은 여러 로직에서 반복되는 공통 관심사이다.

즉, AOP는 반복되는 공통 기능을 핵심 로직에서 분리하기 위한 기법이다.

## 2. 관심사의 예

AOP에서 말하는 관심사는 핵심 기능 외에 함께 고려해야 하는 부가 기능을 의미한다.

대표적인 관심사의 예는 다음과 같다.

- 파라미터가 올바르게 들어왔는지 확인
- 이 작업을 하는 사용자가 적절한 권한을 가진 사용자인지 확인
- 이 작업에서 발생할 수 있는 예외를 어떻게 처리할지 결정
- 메서드 실행 시간을 측정
- 로그 기록
- 트랜잭션 처리

이러한 작업들은 여러 메서드에서 반복될 수 있다.

## 3. 관심사의 분리

관심사의 분리는 개발자가 핵심 비즈니스 로직에 집중할 수 있도록 부가 기능을 별도로 분리하는 것이다.

개발자가 염두에 두어야 하는 일들을 별도의 관심사로 분리하고, 핵심 로직에는 실제 비즈니스 처리만 작성하는 것을 권장한다. ✅

예를 들어 게시글 등록 메서드에 로그, 권한 검사, 예외 처리 코드가 모두 들어가면 핵심 로직이 복잡해진다.

AOP를 사용하면 기존 코드, 즉 핵심 비즈니스 로직을 수정하지 않고 원하는 기능인 관심사와 결합할 수 있다.

## 4. AOP를 사용하는 이유

AOP를 사용하는 이유는 다음과 같다.

- 중복 코드 제거
- 핵심 로직과 공통 로직 분리
- 유지보수 편의성 향상
- 기존 코드 수정 최소화
- 공통 기능의 일괄 적용

즉, AOP를 사용하면 여러 클래스와 메서드에 흩어져 있는 공통 기능을 한 곳에서 관리할 수 있다. 📌

## 5. AOP 용어

AOP에서 자주 사용하는 핵심 용어는 다음과 같다.

- Target
- Advice
- Proxy
- JoinPoint
- Pointcut

각 용어는 AOP 동작 흐름을 이해하는 데 중요하다.

## 6. Target

Target은 개발자가 작성한 핵심 비즈니스 로직을 가지는 객체이다.

즉, 실제 기능을 수행하는 원본 객체이다.

예를 들어 `BoardService`, `MemberService`처럼 비즈니스 로직을 수행하는 객체가 Target이 될 수 있다.

    public class BoardService {
        public void register() {
            System.out.println("게시글 등록");
        }
    }

여기서 `BoardService`는 핵심 로직을 가진 Target 객체로 볼 수 있다. ✅

## 7. Advice

Advice는 분리된 관심사 로직이다.

즉, Target의 메서드 실행 전, 실행 후, 예외 발생 후 등에 추가로 실행할 코드이다.

예를 들어 다음과 같은 기능이 Advice가 될 수 있다.

- 로그 출력
- 권한 검사
- 실행 시간 측정
- 예외 처리

Advice는 “언제 실행될 것인지”를 어노테이션으로 지정한다. 📌

## 8. Proxy

Proxy는 Target을 감싸는 래퍼 객체이다.

외부에서 Target 객체를 직접 호출하는 것이 아니라 Proxy 객체를 통해 Target 객체의 메서드를 호출한다.

Proxy는 내부에서 Target을 호출하면서, 그 전후에 Advice를 실행할 수 있다.

즉, Proxy는 Target과 Advice를 연결해 주는 중간 객체이다. ✅

## 9. JoinPoint

JoinPoint는 AOP를 적용할 수 있는 지점을 의미한다.

스프링 AOP에서는 보통 Target 객체의 메서드가 JoinPoint가 된다.

즉, Advice가 끼어들 수 있는 메서드 실행 지점이라고 이해하면 된다.

예를 들어 `BoardService.register()` 메서드가 AOP 적용 대상이라면, 이 메서드 실행 지점이 JoinPoint이다. 📌

## 10. Proxy를 통한 호출 흐름

AOP가 적용되면 외부 호출은 Target 객체에 직접 전달되지 않는다.

외부에서는 Proxy 객체를 통해 Target 객체의 JoinPoint를 호출한다.

흐름은 다음과 같다.

1. 외부에서 메서드 호출
2. Proxy 객체가 요청을 받음
3. Proxy가 Advice 실행
4. Proxy가 Target의 JoinPoint 호출
5. 필요하면 메서드 실행 후 Advice 실행
6. 결과 반환

즉, Proxy 덕분에 기존 Target 코드를 직접 수정하지 않고도 부가 기능을 추가할 수 있다. ✅

## 11. Advice의 종류

Advice는 실행 위치에 따라 여러 종류로 나뉜다.

대표적인 Advice 어노테이션은 다음과 같다.

- `@Around`
- `@Before`
- `@AfterReturning`
- `@AfterThrowing`
- `@After`

각 Advice는 Target 메서드의 어느 시점에 실행되는지가 다르다. 📌

## 12. @Before

`@Before`는 JoinPoint 호출 전에 실행된다.

즉, Target 메서드가 실행되기 전에 먼저 실행되는 Advice이다.

예를 들어 파라미터 확인, 권한 체크, 로그 기록 등에 사용할 수 있다.

    @Before("execution(* org.example.service.*.*(..))")
    public void beforeLog() {
        log.info("메서드 실행 전");
    }

## 13. @AfterReturning

`@AfterReturning`은 Target 메서드가 정상적으로 실행된 후에 실행된다.

즉, 예외 없이 메서드가 끝났을 때 실행되는 Advice이다.

반환값을 기록하거나 정상 처리 로그를 남길 때 사용할 수 있다. ✅

    @AfterReturning("execution(* org.example.service.*.*(..))")
    public void afterReturningLog() {
        log.info("메서드 정상 실행 후");
    }

## 14. @AfterThrowing

`@AfterThrowing`은 지정된 대상이 예외를 발생시킨 후에 동작한다.

즉, Target 메서드 실행 중 예외가 발생했을 때 실행되는 Advice이다.

예외 로그 기록이나 예외 상황 추적에 사용할 수 있다. 📌

    @AfterThrowing(
        pointcut = "execution(* org.example.service.*.*(..))",
        throwing = "ex"
    )
    public void afterThrowingLog(Exception ex) {
        log.error("예외 발생: " + ex.getMessage());
    }

## 15. @After

`@After`는 Target 메서드가 정상적으로 실행되거나 예외가 발생했을 때 구분 없이 실행된다.

즉, 성공과 실패 여부와 상관없이 항상 실행되는 Advice이다.

파일 닫기, 자원 정리, 공통 로그 출력 등에 사용할 수 있다.

    @After("execution(* org.example.service.*.*(..))")
    public void afterLog() {
        log.info("메서드 실행 완료");
    }

## 16. @Around

`@Around`는 메서드 실행 전과 실행 후에 모두 처리가 가능한 Advice이다.

가장 강력한 Advice이며, Target 메서드 실행 자체를 제어할 수 있다.

`@Around`에서는 `ProceedingJoinPoint`를 사용해 실제 Target 메서드를 실행한다. ✅

    @Around("execution(* org.example.service.*.*(..))")
    public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
        log.info("메서드 실행 전");

        Object result = pjp.proceed();

        log.info("메서드 실행 후");
        return result;
    }

## 17. ProceedingJoinPoint

`ProceedingJoinPoint`는 `@Around` Advice에서 사용하는 객체이다.

`proceed()` 메서드를 호출해야 실제 Target 메서드가 실행된다.

    Object result = pjp.proceed();

만약 `proceed()`를 호출하지 않으면 Target 메서드가 실행되지 않는다.

즉, `ProceedingJoinPoint`는 Around Advice에서 원래 메서드 실행을 제어하는 핵심 객체이다. 📌

## 18. Pointcut

Pointcut은 Advice를 어떤 JoinPoint에 결합할 것인지 결정하는 표현식이다.

즉, 어떤 메서드에 AOP를 적용할지 지정하는 조건이다.

예를 들어 특정 패키지의 모든 메서드에 Advice를 적용하거나, 특정 어노테이션이 붙은 메서드에만 Advice를 적용할 수 있다.

## 19. execution

`execution`은 메서드를 기준으로 Pointcut을 설정할 때 사용한다.

가장 많이 사용하는 Pointcut 표현식 중 하나이다.

예시는 다음과 같다.

    execution(* org.example.service.*.*(..))

의미는 다음과 같다.

- `*` → 모든 반환 타입
- `org.example.service.*` → 해당 패키지의 모든 클래스
- `*` → 모든 메서드
- `(..)` → 모든 파라미터

즉, 특정 패키지의 메서드를 기준으로 AOP를 적용할 수 있다. ✅

## 20. within

`within`은 특정 타입, 즉 클래스나 패키지를 기준으로 Pointcut을 설정할 때 사용한다.

예시는 다음과 같다.

    within(org.example.service.*)

이 표현식은 특정 패키지 안의 타입을 기준으로 Advice 적용 대상을 지정한다.

즉, 메서드 실행 자체보다는 특정 타입 범위를 기준으로 대상을 잡는다. 📌

## 21. this

`this`는 주어진 인터페이스를 구현한 객체를 대상으로 Pointcut을 지정할 때 사용한다.

즉, 프록시 객체가 특정 타입인 경우를 기준으로 AOP 적용 대상을 선택할 수 있다.

인터페이스 기반 구조에서 특정 인터페이스를 구현한 객체들에 공통 기능을 적용할 때 사용할 수 있다.

## 22. args

`args`는 특정 파라미터를 가지는 대상들만 Pointcut으로 설정할 때 사용한다.

예를 들어 특정 타입의 파라미터를 받는 메서드만 Advice 적용 대상으로 지정할 수 있다.

또한 메서드에 전달되는 파라미터 값을 추적할 때도 사용할 수 있다. ✅

## 23. @annotation

`@annotation`은 특정 어노테이션이 적용된 대상들만 Pointcut으로 설정할 때 사용한다.

예를 들어 특정 메서드에 커스텀 어노테이션을 붙이고, 해당 어노테이션이 붙은 메서드에만 Advice를 적용할 수 있다.

    @annotation(org.example.annotation.LogExecutionTime)

즉, 어노테이션 기반으로 AOP 적용 대상을 세밀하게 지정할 수 있다. 📌

## 24. args를 이용한 파라미터 추적

`args`를 이용하면 메서드에 전달되는 파라미터를 추적할 수 있다.

이 기능은 다음과 같은 상황에서 유용하다.

- 해당 메서드에 전달되는 파라미터가 무엇인지 기록하고 싶은 경우
- 예외가 발생했을 때 어떤 파라미터에 문제가 있었는지 확인하고 싶은 경우
- 특정 타입의 파라미터를 받는 메서드에만 Advice를 적용하고 싶은 경우

즉, `args`는 파라미터 기반으로 AOP 적용 대상을 지정하거나 파라미터 값을 추적할 때 사용한다. ✅

## 25. AOP 적용 예시 흐름

AOP 적용 흐름은 다음과 같이 정리할 수 있다.

1. 개발자가 핵심 비즈니스 로직을 가진 Target 객체 작성
2. 공통 관심사 로직을 Advice로 분리
3. Pointcut으로 Advice를 적용할 JoinPoint 지정
4. 스프링이 Target을 감싸는 Proxy 객체 생성
5. 외부 호출은 Proxy를 통해 들어옴
6. Proxy가 Advice를 실행하고 Target 메서드 호출
7. 필요하면 메서드 실행 후 또는 예외 발생 후 Advice 실행

즉, AOP는 Proxy를 이용해 핵심 로직과 관심사 로직을 결합한다. 📌

## 26. 중요 포인트 📌

- AOP는 Aspect Oriented Programming의 약자이다.
- AOP는 관점 지향 프로그래밍이다.
- AOP는 핵심 비즈니스 로직과 공통 관심사를 분리한다.
- 관심사의 예로 파라미터 검증, 권한 확인, 예외 처리, 로그 기록 등이 있다.
- AOP를 사용하면 기존 핵심 비즈니스 로직을 수정하지 않고 관심사 기능을 결합할 수 있다.
- Target은 핵심 비즈니스 로직을 가지는 객체이다.
- Advice는 분리된 관심사 로직이다.
- Proxy는 Target을 감싸는 래퍼 객체이다.
- JoinPoint는 AOP를 적용할 Target 객체의 메서드이다.
- 외부 호출은 Proxy 객체를 통해 Target 객체의 JoinPoint를 호출한다.
- `@Before`는 JoinPoint 호출 전에 실행된다.
- `@AfterReturning`은 정상 실행 후에 실행된다.
- `@AfterThrowing`은 예외 발생 후에 실행된다.
- `@After`는 정상 실행 또는 예외 발생 여부와 상관없이 실행된다.
- `@Around`는 메서드 실행 전과 실행 후에 모두 처리가 가능하다.
- `ProceedingJoinPoint`는 `@Around`에서 실제 메서드 실행을 제어한다.
- Pointcut은 Advice를 어떤 JoinPoint에 결합할 것인지 결정하는 표현식이다.
- `execution`은 메서드를 기준으로 Pointcut을 설정한다.
- `within`은 특정 타입을 기준으로 Pointcut을 설정한다.
- `this`는 주어진 인터페이스를 구현한 객체를 대상으로 Pointcut을 지정한다.
- `args`는 특정 파라미터를 가지는 대상만 Pointcut으로 설정한다.
- `@annotation`은 특정 어노테이션이 적용된 대상만 Pointcut으로 설정한다.

## 정리 ✅

AOP는 관점 지향 프로그래밍으로, 핵심 비즈니스 로직과 공통 관심사 로직을 분리하기 위한 기법이다.  
파라미터 검증, 권한 확인, 예외 처리, 로그 기록처럼 여러 곳에서 반복되는 기능을 Advice로 분리하고, Pointcut을 통해 어떤 JoinPoint에 적용할지 결정한다.  
Target은 핵심 비즈니스 로직을 가진 객체이고, Proxy는 Target을 감싸서 외부 호출을 대신 받으며 Advice와 Target 메서드 실행을 연결한다.  
Advice는 실행 시점에 따라 `@Before`, `@AfterReturning`, `@AfterThrowing`, `@After`, `@Around`로 나뉘며, 특히 `@Around`는 `ProceedingJoinPoint`를 통해 메서드 실행 전후를 모두 제어할 수 있다.  
AOP를 사용하면 기존 코드를 직접 수정하지 않고도 공통 기능을 여러 대상에 일괄적으로 적용할 수 있다.
