# ✨ Spring MVC의 Controller2

## 1. Model이라는 데이터 전달자

`Model`은 Controller에서 생성한 데이터를 JSP 같은 View로 전달할 때 사용하는 객체이다.

Controller 메서드의 매개변수로 `Model` 타입을 선언하면, 스프링 MVC가 자동으로 Model 객체를 전달해 준다.

    @GetMapping("/ex01")
    public String ex01(Model model) {
        model.addAttribute("serverTime", new java.util.Date());
        return "sample/ex01";
    }

이렇게 저장한 데이터는 request 스코프에 속성으로 저장되고, JSP로 forward될 때 함께 전달된다. 📌

## 2. Servlet 모델2 방식과 비교

Servlet에서 모델2 방식으로 데이터를 전달할 때는 다음처럼 작성했다.

    request.setAttribute("serverTime", new java.util.Date());

Spring MVC에서는 이를 `Model` 객체로 대체할 수 있다.

    model.addAttribute("serverTime", new java.util.Date());

즉, Spring MVC의 `Model`은 Servlet의 `request.setAttribute()`를 더 편리하게 사용할 수 있도록 도와주는 데이터 전달자이다. ✅

## 3. @ModelAttribute

`@ModelAttribute`는 요청 파라미터를 Model에 담아 View까지 전달할 때 사용한다.

DTO 객체로 받은 쿼리 파라미터는 자동으로 View에 전달된다.

하지만 기본 자료형이나 문자열 같은 단순 타입 쿼리 파라미터는 자동으로 View에 전달되지 않는다.

이때 `@ModelAttribute`를 사용하면 해당 값을 request 스코프에 저장할 수 있다. 📌

## 4. @ModelAttribute 사용 예시

예를 들어 다음 요청이 있다고 하자.

    http://localhost:8080/sample/ex04?name=aaa&age=11&page=9

DTO 객체가 `name`, `age`를 가진다면 `name`, `age`는 DTO에 담겨 View로 전달될 수 있다.

하지만 `page`처럼 별도의 기본 자료형 파라미터는 자동으로 View에 전달되지 않는다.

이 경우 다음처럼 작성할 수 있다.

    @GetMapping("/ex04")
    public String ex04(SampleDTO dto,
                       @ModelAttribute("page") int page) {
        return "sample/ex04";
    }

`@ModelAttribute("page")`를 사용하면 `page` 값이 request 스코프에 저장되고, JSP에서 사용할 수 있다. ✅

## 5. @ModelAttribute의 동작

`@ModelAttribute("파라미터명")`은 쿼리 파라미터 값을 request 스코프에 저장한다.

이때 지정한 파라미터명이 스코프의 key가 된다.

정리하면 다음과 같다.

- DTO 파라미터 → 자동으로 View에 전달 가능
- 기본 자료형 파라미터 → 자동 전달되지 않음
- `@ModelAttribute` 사용 → request 스코프에 저장 가능

즉, 단순 파라미터 값을 JSP에서 사용해야 한다면 `@ModelAttribute`를 사용할 수 있다. 📌

## 6. Controller 메서드의 리턴 타입

Spring MVC Controller 메서드는 다양한 리턴 타입을 사용할 수 있다.

대표적인 리턴 타입은 다음과 같다.

- `String`
- `void`
- VO, DTO 타입
- `ResponseEntity`
- `Model`
- `ModelAndView`
- `HttpHeaders`

리턴 타입에 따라 Spring MVC가 응답을 처리하는 방식이 달라진다.

## 7. String 리턴 타입

`String`을 리턴하면 JSP View의 경로 또는 이름으로 해석된다.

    @GetMapping("/ex01")
    public String ex01() {
        return "sample/ex01";
    }

이 경우 ViewResolver를 통해 실제 JSP 경로로 변환된다.

즉, `String` 리턴은 가장 일반적인 View 이름 반환 방식이다. ✅

## 8. void 리턴 타입

Controller 메서드의 리턴 타입이 `void`이면 호출한 URL과 동일한 이름의 JSP로 해석될 수 있다.

예를 들어 요청 URL이 다음과 같다면

    /sample/ex01

메서드가 `void`를 리턴할 경우 View 이름도 요청 경로를 기준으로 결정된다.

    sample/ex01

즉, 명시적으로 View 이름을 반환하지 않아도 요청 URL에 따라 View가 결정될 수 있다. 📌

## 9. VO, DTO 리턴 타입

VO 또는 DTO 객체를 리턴하면 JSON 타입의 데이터로 변환해서 브라우저로 응답할 수 있다.

이때 객체를 JSON으로 변환하기 위해 `jackson-databind` 라이브러리가 필요하다.

예시는 다음과 같다.

    @GetMapping("/data")
    @ResponseBody
    public SampleDTO data() {
        return new SampleDTO("aaa", 11);
    }

응답 결과는 JSON 형태가 될 수 있다.

    {
        "name": "aaa",
        "age": 11
    }

즉, 객체를 직접 응답하려면 JSON 변환 라이브러리가 필요하다. ✅

## 10. ResponseEntity 리턴 타입

`ResponseEntity`는 브라우저로 직접 응답을 구성할 때 사용한다.

`ResponseEntity`를 사용하면 다음 내용을 직접 설정할 수 있다.

- HTTP 상태 코드
- 응답 헤더
- 응답 바디

예시는 다음과 같다.

    return ResponseEntity
            .ok()
            .header("Custom-Header", "value")
            .body("success");

즉, 단순 View 이동이 아니라 HTTP 응답 자체를 세밀하게 제어할 때 사용한다. 📌

## 11. Model, ModelAndView 리턴 타입

`Model`은 데이터를 View로 전달할 때 사용한다.

`ModelAndView`는 Model 데이터와 View 이름을 함께 지정할 수 있는 객체이다.

예시는 다음과 같다.

    ModelAndView mav = new ModelAndView();
    mav.addObject("serverTime", new java.util.Date());
    mav.setViewName("sample/ex01");
    return mav;

즉, `ModelAndView`는 데이터와 View 정보를 하나의 객체로 묶어 반환할 때 사용한다.

## 12. HttpHeaders 리턴 타입

`HttpHeaders`는 응답에 내용 없이 HTTP 헤더 메시지만 전달할 때 사용할 수 있다.

즉, body 없이 header 정보만 응답해야 하는 경우에 사용할 수 있다.

다만 일반적인 화면 처리에서는 `String`, `Model`, `ModelAndView`, `ResponseEntity`를 더 자주 사용한다. 📌

## 13. forward 방식

`String` 타입으로 View 이름을 반환하면 기본적으로 forward 방식으로 처리된다.

예시는 다음과 같다.

    return "sample/ex01";

이 경우 서버 내부에서 JSP로 요청을 전달한다.

forward 방식은 URL이 바뀌지 않고 서버 내부에서 View로 이동한다.

즉, Controller에서 처리한 Model 데이터를 JSP로 전달할 때 주로 사용한다. ✅

## 14. redirect 방식

redirect 방식으로 처리하려면 문자열 앞에 `redirect:`를 붙인다.

    return "redirect:/sample/ex06";

이 경우 View 이름이 아니라 요청 경로를 제시하는 것이다.

redirect는 브라우저에게 해당 URL로 다시 요청하라고 지시한다.

즉, URL이 바뀌고 새로운 요청이 발생한다. 📌

## 15. RedirectAttributes

`RedirectAttributes`는 redirect 방식으로 이동할 때 파라미터를 전달하기 위해 사용한다.

Servlet에서는 다음처럼 redirect했다.

    response.sendRedirect("/sample/ex06?name=aaa&age=10");

Spring MVC에서는 `RedirectAttributes`를 사용할 수 있다.

    @GetMapping("/ex05")
    public String ex05(RedirectAttributes ra) {
        ra.addAttribute("name", "AAA");
        ra.addAttribute("age", 10);
        return "redirect:/sample/ex06";
    }

이렇게 하면 redirect URL에 쿼리 파라미터가 붙는다.

    /sample/ex06?name=AAA&age=10

즉, redirect 시 필요한 값을 안전하게 전달할 수 있다. ✅

## 16. addAttribute()

`RedirectAttributes`의 `addAttribute()`는 redirect URL에 쿼리 파라미터를 추가한다.

예시는 다음과 같다.

    ra.addAttribute("name", "AAA");
    ra.addAttribute("age", 10);

결과적으로 redirect 요청 URL은 다음처럼 구성된다.

    /sample/ex06?name=AAA&age=10

즉, redirect 이후 요청에서도 해당 값을 쿼리 파라미터로 받을 수 있다. 📌

## 17. 객체 타입 응답과 jackson-databind

Controller가 객체를 직접 응답하려면 JSON 변환이 필요하다.

이때 `jackson-databind` 라이브러리가 필요하다.

Jackson은 Java 객체를 JSON 문자열로 변환한다.

즉, REST API나 Ajax 응답처럼 화면이 아니라 데이터를 직접 내려줄 때 사용한다. ✅

## 18. 파일 업로드 방법

Spring MVC에서 파일 업로드는 Servlet 3.0 기능을 이용할 수 있다.

파일 업로드를 처리하려면 multipart 설정이 필요하다.

multipart 설정에서는 업로드 파일의 저장 위치, 파일 크기 제한, 메모리 사용 기준 등을 지정할 수 있다. 📌

## 19. multipart 설정 항목

multipart 설정의 주요 항목은 다음과 같다.

- `location`
- `maxFileSize`
- `maxRequestSize`
- `fileSizeThreshold`

각 의미는 다음과 같다.

- `location` → 업로드 처리 디렉토리 경로
- `maxFileSize` → 업로드 가능한 파일 하나의 최대 크기
- `maxRequestSize` → 업로드 가능한 전체 요청 최대 크기
- `fileSizeThreshold` → 메모리 파일의 최대 크기

`fileSizeThreshold`보다 작은 파일은 실제 파일로 저장하지 않고 메모리에서만 작업할 수 있다.

## 20. maxFileSize와 maxRequestSize

`maxFileSize`는 파일 하나의 최대 크기를 의미한다.

반면 `maxRequestSize`는 한 번의 요청에서 업로드 가능한 전체 크기를 의미한다.

예를 들어 여러 파일을 업로드하는 경우, 각 파일은 `maxFileSize`를 넘으면 안 되고 전체 합계는 `maxRequestSize`를 넘으면 안 된다.

즉, 여러 파일 업로드에서는 두 설정을 구분해서 이해해야 한다. ✅

## 21. 스프링 MVC의 예외 처리

Spring MVC에서는 예외를 공통으로 처리할 수 있다.

대표적인 방식은 다음과 같다.

- `@ExceptionHandler`
- `@ControllerAdvice`
- `ResponseEntity`를 이용한 예외 메시지 구성

즉, 컨트롤러마다 반복해서 try-catch를 작성하지 않고 공통 예외 처리 클래스를 만들 수 있다. 📌

## 22. @ExceptionHandler

`@ExceptionHandler`는 특정 예외가 발생했을 때 실행할 메서드를 지정한다.

예시는 다음과 같다.

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return "error/error";
    }

이 메서드는 지정한 예외가 발생했을 때 실행된다.

즉, 컨트롤러 내부 또는 Advice 클래스에서 예외별 처리 메서드를 정의할 수 있다. ✅

## 23. @ControllerAdvice

`@ControllerAdvice`는 여러 컨트롤러에서 발생하는 예외를 공통으로 처리하기 위한 어노테이션이다.

AOP를 이용해 컨트롤러 처리 과정에 공통 예외 처리 로직을 적용한다.

주로 HTTP 상태 코드 `500 Internal Server Error`에 대응하기 위한 기법으로 사용할 수 있다.

예시는 다음과 같다.

    @ControllerAdvice
    public class CommonExceptionAdvice {

        @ExceptionHandler(Exception.class)
        public String except(Exception e) {
            return "error/error";
        }
    }

즉, 전역 예외 처리 클래스를 만들 때 사용한다. 📌

## 24. ResponseEntity를 이용한 예외 메시지 구성

REST API에서는 예외가 발생했을 때 JSP 화면보다 JSON 메시지를 응답하는 경우가 많다.

이때 `ResponseEntity`를 사용하면 상태 코드와 메시지를 함께 구성할 수 있다.

예시는 다음과 같다.

    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("서버 오류가 발생했습니다.");

즉, API 예외 처리에서는 `ResponseEntity`로 응답 상태와 body를 직접 만들 수 있다. ✅

## 25. 404 에러 페이지

404 에러는 요청 URL에 해당하는 컨트롤러나 리소스를 찾지 못했을 때 발생한다.

하지만 404 에러는 기본적으로 서버에서 Exception을 발생시키지 않을 수 있다.

따라서 일반적인 `@ExceptionHandler`만으로는 처리되지 않을 수 있다. ⚠️

## 26. NoHandlerFoundException

404 에러를 예외로 처리하려면 `NoHandlerFoundException`을 사용할 수 있다.

`NoHandlerFoundException`은 요청을 처리할 Handler를 찾지 못했을 때 발생하는 예외이다.

다만 404 에러를 이 예외로 처리하려면 별도의 설정이 필요하다.

즉, 404 에러 페이지를 공통 예외 처리 방식으로 다루려면 설정을 통해 `NoHandlerFoundException`이 발생하도록 만들어야 한다. 📌

## 27. 중요 포인트 📌

- `Model`은 Controller에서 생성한 데이터를 JSP로 전달하는 데이터 전달자이다.
- `Model` 데이터는 request 스코프에 속성으로 저장되고 JSP로 forward된다.
- Servlet의 `request.setAttribute()`를 Spring MVC에서는 `model.addAttribute()`로 대체할 수 있다.
- DTO 쿼리 파라미터는 자동으로 View에 전달될 수 있다.
- 기본 자료형 쿼리 파라미터는 자동으로 View에 전달되지 않는다.
- `@ModelAttribute`를 사용하면 쿼리 파라미터를 request 스코프에 저장할 수 있다.
- `String` 리턴은 JSP View 이름으로 해석된다.
- `void` 리턴은 호출 URL과 동일한 이름의 JSP로 해석될 수 있다.
- VO, DTO 리턴은 JSON 응답으로 변환될 수 있다.
- 객체를 JSON으로 응답하려면 `jackson-databind` 라이브러리가 필요하다.
- `ResponseEntity`는 HTTP 헤더와 body를 직접 구성할 때 사용한다.
- `redirect:`를 붙이면 redirect 방식으로 처리된다.
- `RedirectAttributes`는 redirect 시 파라미터를 전달할 때 사용한다.
- 파일 업로드는 Servlet 3.0 multipart 기능을 이용할 수 있다.
- multipart 설정에는 `location`, `maxFileSize`, `maxRequestSize`, `fileSizeThreshold`가 있다.
- 예외 처리는 `@ExceptionHandler`, `@ControllerAdvice`, `ResponseEntity`를 이용할 수 있다.
- 404 에러는 기본적으로 Exception을 발생시키지 않을 수 있다.
- 404를 예외로 처리하려면 `NoHandlerFoundException` 관련 설정이 필요하다.

## 정리 ✅

Spring MVC의 Controller2에서는 Model 데이터 전달, `@ModelAttribute`, Controller 리턴 타입, redirect 처리, 파일 업로드, 예외 처리가 핵심이다.  
`Model`은 Controller에서 생성한 데이터를 JSP로 전달하는 객체이며, Servlet의 `request.setAttribute()`를 대체할 수 있다.  
DTO 객체는 자동으로 View에 전달될 수 있지만, 기본 자료형 쿼리 파라미터는 자동 전달되지 않으므로 `@ModelAttribute`를 사용해 request 스코프에 저장할 수 있다.  
Controller 메서드는 `String`, `void`, VO/DTO, `ResponseEntity`, `ModelAndView`, `HttpHeaders` 등 다양한 타입을 리턴할 수 있고, `redirect:`를 사용하면 redirect 방식으로 처리된다.  
또한 파일 업로드는 Servlet 3.0 multipart 설정을 이용하며, 예외 처리는 `@ExceptionHandler`, `@ControllerAdvice`, `ResponseEntity`를 통해 공통으로 구성할 수 있다.
