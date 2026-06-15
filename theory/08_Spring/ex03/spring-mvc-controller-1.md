# ✨ Spring MVC의 Controller1

## 1. @RequestMapping

`@RequestMapping`은 특정 URL 요청을 컨트롤러의 메서드와 연결할 때 사용하는 어노테이션이다.

기본적으로 HTTP 메서드와 상관없이 경로가 일치하면 요청을 처리할 수 있다.

    @RequestMapping("/basic")
    public void basic() {
    }

이 경우 `/basic` 경로로 들어오는 요청을 처리한다.

즉, `@RequestMapping`은 URL과 컨트롤러 메서드를 매핑하는 기본 어노테이션이다. 📌

## 2. 공통 URL 설정

`@RequestMapping`은 클래스 레벨에서도 사용할 수 있다.

클래스에 붙이면 해당 컨트롤러의 공통 URL 경로를 설정한다.

    @Controller
    @RequestMapping("/sample")
    public class SampleController {
    }

이렇게 설정하면 해당 컨트롤러 안의 메서드들은 기본적으로 `/sample` 경로 아래에서 동작한다.

예를 들어 메서드에 `/basic`이 붙으면 최종 URL은 다음과 같다.

    /sample/basic

즉, 클래스 레벨 `@RequestMapping`은 공통 URL prefix 역할을 한다. ✅

## 3. void 리턴과 View 이름

컨트롤러 메서드의 리턴 타입이 `void`인 경우, 요청 URL을 기준으로 View 이름이 자동으로 결정될 수 있다.

예를 들어 요청 URL이 다음과 같다면

    /sample/basic

컨트롤러 메서드가 `void`를 리턴할 때 View 이름은 요청 경로를 기준으로 결정된다.

    /sample/basic.jsp

즉, 명시적으로 View 이름을 리턴하지 않아도 요청 URL에 의해 View가 선택될 수 있다. 📌

## 4. HTTP method 설정

`@RequestMapping`은 수용할 HTTP method를 지정할 수 있다.

대표적인 HTTP method는 다음과 같다.

- `GET`
- `POST`
- `PUT`
- `DELETE`

예시는 다음과 같다.

    @RequestMapping(
        value = "/basic",
        method = {RequestMethod.GET, RequestMethod.POST}
    )
    public void basic() {
    }

이 코드는 `/basic` 요청 중 GET과 POST 요청을 모두 처리한다.

즉, 하나의 메서드를 여러 HTTP method에 매핑해야 할 때 사용할 수 있다. ✅

## 5. 메서드별 Mapping 어노테이션

일반적으로는 하나의 HTTP method에 하나의 메서드를 매핑하는 경우가 많다.

이때는 메서드별 Mapping 어노테이션을 사용한다.

대표적인 어노테이션은 다음과 같다.

- `@GetMapping`
- `@PostMapping`
- `@PutMapping`
- `@DeleteMapping`

이 어노테이션들은 특정 HTTP method에만 국한되어 동작한다.

## 6. @GetMapping

`@GetMapping`은 GET 요청을 처리할 때 사용한다.

    @GetMapping("/list")
    public String list() {
        return "list";
    }

GET 요청은 주로 조회 화면이나 데이터 조회에 사용한다.

즉, 게시글 목록 조회, 상세 조회 같은 요청에 자주 사용된다. 📌

## 7. @PostMapping

`@PostMapping`은 POST 요청을 처리할 때 사용한다.

    @PostMapping("/create")
    public String create() {
        return "redirect:/list";
    }

POST 요청은 주로 데이터 등록이나 form 전송에 사용한다.

즉, 게시글 등록, 회원가입, 로그인 처리 등에 자주 사용된다. ✅

## 8. @PutMapping과 @DeleteMapping

`@PutMapping`은 PUT 요청을 처리할 때 사용한다.

PUT은 주로 전체 수정 요청에 사용된다.

    @PutMapping("/board")
    public void update() {
    }

`@DeleteMapping`은 DELETE 요청을 처리할 때 사용한다.

DELETE는 주로 삭제 요청에 사용된다.

    @DeleteMapping("/board")
    public void delete() {
    }

즉, REST API에서는 HTTP method를 이용해 작업의 의미를 구분한다. 📌

## 9. 요청의 쿼리 파라미터 수집

기존 Servlet에서는 요청 파라미터를 받을 때 다음처럼 작성했다.

    request.getParameter("name");

Spring MVC에서는 컨트롤러 메서드의 매개변수에 필요한 값을 선언하면 HandlerAdapter가 요청 파라미터를 자동으로 수집해 전달한다.

즉, `request.getParameter()`를 직접 호출하는 코드를 줄일 수 있다. ✅

## 10. @RequestParam

`@RequestParam`은 요청 파라미터를 컨트롤러 메서드의 매개변수에 매핑할 때 사용한다.

예시는 다음과 같다.

    @GetMapping("/ex01")
    public String ex01(@RequestParam("name") String name) {
        return "ex01";
    }

이 코드는 요청 파라미터 `name` 값을 메서드의 `name` 변수에 넣어준다.

요청 URL 예시는 다음과 같다.

    /ex01?name=kim

즉, `@RequestParam("파라미터명")`은 특정 쿼리 파라미터를 직접 받을 때 사용한다. 📌

## 11. DTO 객체 사용

요청 파라미터가 여러 개라면 DTO 객체를 사용할 수 있다.

예를 들어 요청 파라미터가 다음과 같다고 하자.

    /sample/ex01?name=kim&age=20

DTO는 다음처럼 작성할 수 있다.

    public class SampleDTO {
        private String name;
        private int age;

        public SampleDTO() {
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

컨트롤러에서는 다음처럼 받을 수 있다.

    @GetMapping("/ex01")
    public String ex01(SampleDTO dto) {
        return "ex01";
    }

즉, Spring MVC는 요청 파라미터를 DTO 객체의 프로퍼티에 자동으로 바인딩할 수 있다. ✅

## 12. HandlerAdapter의 DTO 생성

Controller의 파라미터 수집은 HandlerAdapter가 처리한다.

DTO 객체를 매개변수로 선언하면 HandlerAdapter는 먼저 DTO의 디폴트 생성자를 이용해 객체를 생성한다.

따라서 DTO 객체에는 기본 생성자가 있어야 한다.

    public SampleDTO() {
    }

기본 생성자가 없으면 Spring MVC가 DTO 객체를 생성하기 어려울 수 있다. ⚠️

## 13. setter 메서드와 프로퍼티명

HandlerAdapter는 DTO의 setter 메서드를 보고 프로퍼티명을 유추한다.

예를 들어 다음 메서드가 있다면

    setName()

프로퍼티명은 다음과 같이 해석된다.

    name

또 다음 메서드는

    setAge()

다음 프로퍼티로 해석된다.

    age

즉, setter 메서드 이름을 기준으로 요청 파라미터와 DTO 필드를 연결한다. 📌

## 14. 요청 파라미터와 DTO 프로퍼티 매핑

HandlerAdapter는 DTO 프로퍼티와 동일한 이름의 요청 파라미터를 추출한다.

예를 들어 DTO에 `name`, `age` 프로퍼티가 있다면 내부적으로 다음과 비슷하게 처리된다.

    request.getParameter("name")
    request.getParameter("age")

해당 파라미터가 존재하면 setter를 이용해 DTO의 프로퍼티 값을 설정한다.

그 후 완성된 DTO 객체가 컨트롤러 요청 처리 메서드의 인자로 전달된다. ✅

## 15. DTO 바인딩 흐름

DTO 객체 바인딩 흐름은 다음과 같다.

1. 요청이 들어온다.
2. HandlerAdapter가 컨트롤러 메서드의 매개변수를 확인한다.
3. DTO 타입 매개변수가 있으면 기본 생성자로 DTO 객체를 생성한다.
4. DTO의 setter 메서드에서 프로퍼티명을 유추한다.
5. 요청 파라미터에서 같은 이름의 값을 찾는다.
6. 값이 있으면 setter를 호출해 DTO에 값을 넣는다.
7. 완성된 DTO를 컨트롤러 메서드 인자로 전달한다.

즉, Spring MVC는 요청 파라미터를 객체로 자동 변환해 주는 기능을 제공한다. 📌

## 16. 리스트와 배열 처리

동일한 이름의 파라미터가 여러 개 전달되는 경우가 있다.

예를 들어 다음과 같은 요청이다.

    /sample/ex02?ids=1&ids=2&ids=3

이 경우 배열이나 `ArrayList`로 받을 수 있다.

배열 예시는 다음과 같다.

    @GetMapping("/ex02")
    public String ex02(String[] ids) {
        return "ex02";
    }

리스트 예시는 다음과 같다.

    @GetMapping("/ex02")
    public String ex02(@RequestParam("ids") ArrayList<String> ids) {
        return "ex02";
    }

즉, 같은 이름의 파라미터가 여러 개라면 배열이나 리스트로 수집할 수 있다. ✅

## 17. 객체 리스트 처리

객체 리스트도 쿼리 파라미터로 받을 수 있다.

다만 단순 값 리스트보다 구조가 복잡하기 때문에 파라미터 이름 규칙을 맞춰야 한다.

예를 들어 여러 객체의 값을 전달하려면 다음처럼 인덱스를 활용할 수 있다.

    list[0].name=kim
    list[0].age=20
    list[1].name=lee
    list[1].age=30

이런 구조는 DTO 내부에 리스트 프로퍼티를 두고 바인딩할 때 사용할 수 있다.

즉, 객체 리스트를 받을 때는 파라미터 이름과 DTO 구조가 정확히 맞아야 한다. 📌

## 18. @DateTimeFormat

요청 파라미터는 기본적으로 문자열로 전달된다.

그런데 DTO의 필드 타입이 `Date`라면 문자열을 날짜 타입으로 변환해야 한다.

이때 `@DateTimeFormat`을 사용한다.

커스텀 바인딩이 가장 많이 사용되는 타입 중 하나가 Date이다.

## 19. Date 타입 필드 바인딩

DTO 객체의 Date 타입 필드에 `@DateTimeFormat`을 지정하면 요청 파라미터 문자열을 날짜 타입으로 변환할 수 있다.

예시는 다음과 같다.

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date regDate;

요청 파라미터가 다음과 같이 들어오면

    /sample/ex03?regDate=2026-06-16

Spring MVC는 문자열 `"2026-06-16"`을 `Date` 타입으로 변환해 DTO에 넣어준다.

즉, 날짜 형식이 있는 요청 파라미터는 `@DateTimeFormat`으로 패턴을 지정해야 한다. ✅

## 20. 중요 포인트 📌

- `@RequestMapping`은 URL과 컨트롤러 메서드를 매핑한다.
- `@RequestMapping`은 클래스 레벨에서 공통 URL을 설정할 수 있다.
- 메서드 리턴 타입이 `void`이면 요청 URL에 의해 View 이름이 설정될 수 있다.
- `@RequestMapping`은 수용할 HTTP method를 지정할 수 있다.
- GET, POST를 함께 매핑할 때 `method = {RequestMethod.GET, RequestMethod.POST}`를 사용할 수 있다.
- 한 가지 HTTP method에만 매핑할 때는 `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`을 사용한다.
- Spring MVC에서는 `request.getParameter()` 대신 메서드 파라미터로 요청 값을 받을 수 있다.
- `@RequestParam`은 특정 요청 파라미터를 직접 받을 때 사용한다.
- DTO 객체를 사용하면 여러 파라미터를 객체로 한 번에 받을 수 있다.
- HandlerAdapter가 DTO 객체를 기본 생성자로 생성한다.
- DTO는 반드시 기본 생성자가 있어야 한다.
- HandlerAdapter는 setter 메서드에서 프로퍼티명을 유추한다.
- 요청 파라미터 이름과 DTO 프로퍼티명이 같으면 자동으로 값이 주입된다.
- 동일한 이름의 파라미터가 여러 개면 배열이나 리스트로 받을 수 있다.
- Date 타입 필드는 `@DateTimeFormat`으로 날짜 패턴을 지정할 수 있다.

## 정리 ✅

Spring MVC의 Controller에서는 `@RequestMapping`과 메서드별 Mapping 어노테이션을 이용해 URL과 HTTP method를 컨트롤러 메서드에 연결한다.  
`@RequestMapping`은 클래스 레벨에서 공통 URL을 설정하거나, GET과 POST처럼 여러 HTTP method를 동시에 매핑할 때 사용할 수 있다.  
일반적으로는 `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`처럼 하나의 HTTP method에 특화된 어노테이션을 많이 사용한다.  
요청 파라미터는 `@RequestParam`으로 직접 받을 수도 있고, DTO 객체를 사용하면 HandlerAdapter가 기본 생성자와 setter를 이용해 요청 파라미터를 자동으로 바인딩한다.  
동일한 이름의 파라미터는 배열이나 리스트로 받을 수 있으며, Date 타입처럼 문자열에서 타입 변환이 필요한 경우에는 `@DateTimeFormat`으로 날짜 패턴을 지정해야 한다.
