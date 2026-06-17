# ✨ 스프링 컨텍스트 - 빈 정의

## 1. 빈의 개념

빈(Bean)은 스프링 컨텍스트가 관리하고 있는 객체를 의미한다.

즉, 단순히 `new`로 만든 객체가 아니라 스프링이 알고 있고, 필요할 때 꺼내서 사용할 수 있도록 관리하는 객체이다. 📌

스프링은 컨텍스트에 등록된 객체만 관리할 수 있다.

## 2. 스프링 컨텍스트의 개념

스프링 컨텍스트는 스프링이 관리하는 객체들을 담아두는 공간이다.

처음에는 `Parrot` 타입의 객체와 스프링 컨텍스트를 독립적으로 만들 수 있다.  
이때 스프링 컨텍스트는 비어 있는 상태이다.

이후 `Parrot` 인스턴스를 스프링 컨텍스트에 추가하면, 스프링이 해당 인스턴스를 인식하고 관리할 수 있다.

즉, 객체를 스프링 컨텍스트에 등록해야 스프링이 그 객체를 빈으로 관리할 수 있다. ✅

## 3. @Bean을 이용한 빈 등록

`@Bean` 어노테이션을 사용하면 스프링 컨텍스트에 빈을 추가할 수 있다.

스프링은 `@Bean`으로 등록된 객체를 관리 대상 객체로 인식한다.

즉, 직접 만든 객체를 스프링 컨텍스트에 등록하고 싶을 때 `@Bean`을 사용할 수 있다.

## 4. @Configuration

`@Configuration`은 스프링 설정 클래스를 정의할 때 사용하는 어노테이션이다.

스프링 컨텍스트는 이 설정 클래스를 참고해서 어떤 객체를 빈으로 등록할지 결정한다.

예시는 다음과 같다.

    @Configuration
    public class ProjectConfig {
    }

즉, `@Configuration`이 붙은 클래스는 스프링 컨텍스트를 구성하는 설정 정보 역할을 한다. 📌

## 5. 스프링 컨텍스트에 빈을 추가하는 단계

`@Bean`을 사용해서 스프링 컨텍스트에 빈을 추가하는 단계는 다음과 같다.

1. `@Configuration`으로 구성 클래스를 정의한다.
2. 컨텍스트에 추가하려는 객체 인스턴스를 반환하는 메서드를 구성 클래스에 작성한다.
3. 해당 메서드에 `@Bean` 어노테이션을 추가한다.
4. 스프링이 구성 클래스를 사용해서 컨텍스트를 생성한다.

예시는 다음과 같다.

    @Configuration
    public class ProjectConfig {

        @Bean
        public Parrot parrot() {
            Parrot p = new Parrot();
            p.setName("Koko");
            return p;
        }
    }

이렇게 하면 `parrot()` 메서드가 반환하는 `Parrot` 객체가 스프링 컨텍스트에 빈으로 등록된다. ✅

## 6. AnnotationConfigApplicationContext

자바 설정 클래스를 기반으로 스프링 컨텍스트를 만들 때는 `AnnotationConfigApplicationContext`를 사용할 수 있다.

    var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

여기서 `ProjectConfig.class`는 어떤 설정 정보를 사용해서 컨텍스트를 만들지 알려주는 역할을 한다.

즉, 스프링은 `ProjectConfig` 클래스를 읽고, 그 안에 정의된 `@Bean` 메서드를 실행해서 빈을 등록한다. 📌

## 7. 컨텍스트에서 빈 가져오기

스프링 컨텍스트에서 원하는 빈 객체를 꺼낼 때는 `getBean()`을 사용한다.

타입으로 빈을 가져오는 방법은 다음과 같다.

    Parrot p = context.getBean(Parrot.class);

이 코드는 컨텍스트 안에서 `Parrot` 타입의 빈을 찾아 가져온다.

단, 동일한 타입의 빈이 1개만 있을 때 사용할 수 있다. ⚠️

## 8. 동일 타입 빈이 여러 개일 때 문제

컨텍스트에 `Parrot` 타입 인스턴스가 3개 등록되어 있다면, 스프링은 어떤 빈을 반환해야 할지 결정할 수 없다.

예를 들어 다음과 같은 상황이다.

    @Bean
    public Parrot parrot1() {
        return new Parrot();
    }

    @Bean
    public Parrot parrot2() {
        return new Parrot();
    }

    @Bean
    public Parrot parrot3() {
        return new Parrot();
    }

이 상태에서 다음처럼 타입만으로 조회하면 예외가 발생할 수 있다.

    context.getBean(Parrot.class);

스프링 입장에서는 `Parrot` 타입 빈이 여러 개라 어떤 빈을 선택해야 하는지 알 수 없기 때문이다.

## 9. 빈의 기본 이름

`@Bean`으로 등록한 빈은 기본적으로 메서드명이 빈 이름이 된다.

예를 들어 다음 메서드가 있다면

    @Bean
    public Parrot parrot() {
        return new Parrot();
    }

기본 빈 이름은 `parrot`이 된다.

즉, `@Bean` 메서드 이름이 빈을 구분하는 기본 이름으로 사용된다. 📌

## 10. 빈 이름 직접 지정

`@Bean`에 이름을 직접 지정할 수도 있다.

방법은 다음과 같다.

    @Bean(name = "miki")

    @Bean(value = "miki")

    @Bean("miki")

세 방식 모두 빈 이름을 `"miki"`로 지정한다.

즉, 메서드명 대신 원하는 이름으로 빈을 등록하고 싶을 때 사용할 수 있다.

## 11. 이름으로 빈 가져오기

동일한 타입의 빈이 여러 개 있을 때는 빈 이름으로 선택해야 한다.

형식은 다음과 같다.

    context.getBean(빈이름, 타입.class);

예를 들면 다음과 같다.

    Parrot p = context.getBean("miki", Parrot.class);

여기서 `"miki"`는 빈 이름이고, `Parrot.class`는 리턴 타입으로 사용할 클래스이다.

즉, 같은 타입의 빈이 여러 개 있을 때는 이름을 함께 지정해서 정확히 가져와야 한다. ✅

## 12. 스테레오타입 어노테이션

스프링 컨텍스트에 빈을 추가하는 또 다른 방법은 스테레오타입 어노테이션을 사용하는 것이다.

대표적으로 `@Component`를 사용할 수 있다.

`@Component`는 스프링이 컨텍스트에 인스턴스를 추가할 클래스를 표시하는 어노테이션이다.

예시는 다음과 같다.

    @Component
    public class Parrot {
    }

즉, 이 클래스의 객체를 스프링이 자동으로 생성하고 컨텍스트에 등록할 수 있다.

## 13. @ComponentScan

`@Component`만 붙인다고 바로 빈으로 등록되는 것은 아니다.

스프링에게 `@Component`가 붙은 클래스를 어디에서 찾을지 알려줘야 한다.

이때 사용하는 어노테이션이 `@ComponentScan`이다.

    @Configuration
    @ComponentScan(basePackages = "org.example")
    public class ProjectConfig {
    }

`@ComponentScan`은 지정한 패키지에서 `@Component`가 붙은 클래스를 찾아 빈으로 등록한다. 📌

## 14. @Component의 기본 빈 이름

`@Component`로 등록된 빈의 기본 이름은 클래스명의 camelCase 형태이다.

예를 들어 클래스 이름이 `Parrot`이면 기본 빈 이름은 다음과 같다.

    parrot

즉, 클래스명의 첫 글자를 소문자로 바꾼 이름이 기본 빈 이름으로 사용된다.

## 15. @Bean과 @Component의 차이

`@Bean`과 `@Component`는 모두 빈을 등록할 수 있지만 사용 방식이 다르다.

`@Bean`은 설정 클래스 안에서 메서드가 반환하는 객체를 빈으로 등록한다.

    @Bean
    public Parrot parrot() {
        return new Parrot();
    }

`@Component`는 클래스 자체에 붙여서 스프링이 직접 객체를 생성하고 빈으로 등록하게 한다.

    @Component
    public class Parrot {
    }

정리하면 다음과 같다.

- `@Bean` → 개발자가 직접 객체 생성 로직 작성
- `@Component` → 스프링이 클래스 스캔 후 객체 생성

## 16. PostConstruct

`@PostConstruct`는 인스턴스 생성 후 실행할 후처리 메서드를 지정할 때 사용한다.

`@Component`는 생성자 이후에 별도의 초기화 작업을 직접 끼워 넣기 어렵기 때문에, `@PostConstruct`를 사용해서 생성 후 처리 코드를 작성할 수 있다.

예시는 다음과 같다.

    @Component
    public class Parrot {

        private String name;

        @PostConstruct
        public void init() {
            this.name = "Koko";
        }
    }

이 메서드는 빈 인스턴스가 생성된 후 자동으로 실행된다. ✅

## 17. @PostConstruct 사용 이유

`@Bean` 방식은 메서드 안에서 객체를 생성하고 값을 설정한 뒤 반환할 수 있다.

    @Bean
    public Parrot parrot() {
        Parrot p = new Parrot();
        p.setName("Koko");
        return p;
    }

하지만 `@Component` 방식은 스프링이 객체를 직접 생성하므로, 생성 후 초기화 작업이 필요할 수 있다.

이때 `@PostConstruct`를 사용하면 스프링이 객체를 만든 뒤 실행할 초기화 메서드를 지정할 수 있다. 📌

## 18. javax.annotation-api

`@PostConstruct`는 `javax.annotation-api`에서 정의된 어노테이션이다.

따라서 환경에 따라 해당 의존성이 필요할 수 있다.

즉, `@PostConstruct`를 사용하려면 프로젝트 설정에서 관련 API가 포함되어 있는지 확인해야 한다.

## 19. 중요 포인트 📌

- 빈은 스프링 컨텍스트가 관리하는 객체이다.
- 스프링은 컨텍스트에 등록된 객체만 관리할 수 있다.
- `@Bean`은 메서드가 반환하는 객체를 스프링 빈으로 등록한다.
- `@Configuration`은 스프링 설정 클래스를 정의한다.
- `AnnotationConfigApplicationContext`는 자바 설정 클래스를 기반으로 컨텍스트를 생성한다.
- `context.getBean(Parrot.class)`는 타입으로 빈을 가져온다.
- 같은 타입의 빈이 여러 개 있으면 타입만으로 조회할 수 없다.
- `@Bean` 메서드명이 기본 빈 이름이 된다.
- `@Bean(name = "miki")`, `@Bean(value = "miki")`, `@Bean("miki")`로 빈 이름을 지정할 수 있다.
- 이름으로 빈을 가져올 때는 `context.getBean("miki", Parrot.class)`를 사용한다.
- `@Component`는 스프링이 인스턴스를 생성해 컨텍스트에 추가할 클래스를 표시한다.
- `@ComponentScan`은 컴포넌트를 어디에서 찾을지 스프링에 알려준다.
- `@Component`의 기본 빈 이름은 클래스명의 camelCase 형태이다.
- `@PostConstruct`는 인스턴스 생성 후 실행할 후처리 메서드를 지정한다.

## 정리 ✅

스프링 컨텍스트는 스프링이 관리하는 빈 객체들을 담고 있는 공간이다.  
객체를 스프링이 관리하게 하려면 `@Bean` 또는 `@Component`를 이용해 컨텍스트에 등록해야 한다.  
`@Bean`은 `@Configuration` 클래스 안의 메서드가 반환하는 객체를 빈으로 등록하고, `@Component`는 클래스에 직접 표시한 뒤 `@ComponentScan`을 통해 자동으로 탐색되어 빈으로 등록된다.  
컨텍스트에서 빈을 꺼낼 때는 `getBean()`을 사용하며, 동일한 타입의 빈이 여러 개 있으면 빈 이름을 함께 지정해야 한다.  
또한 `@PostConstruct`를 사용하면 빈 인스턴스가 생성된 후 실행할 초기화 메서드를 지정할 수 있다.
