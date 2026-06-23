# ✨ Rest Controller

## 1. Rest Controller 개념

`Rest Controller`는 화면(View)을 반환하는 컨트롤러가 아니라, 데이터를 응답으로 반환하는 컨트롤러이다.

일반적인 웹 컨트롤러는 JSP 같은 View 이름을 반환하지만, REST API용 컨트롤러는 객체를 반환하고 이 객체가 JSON 문자열로 변환되어 클라이언트에게 전달된다.

즉, REST API 서버에서는 Controller가 HTML 화면이 아니라 JSON 데이터를 응답한다. 📌

## 2. API 서버에서 CUD 처리

API 서버에서 CUD는 다음 작업을 의미한다.

- Create → 생성
- Update → 수정
- Delete → 삭제

API 서버에서는 CUD 작업 후 처리한 객체를 리턴하는 경우가 많다.

예를 들면 다음과 같다.

- 생성된 객체
- 업데이트된 객체
- 삭제된 객체

즉, 단순히 성공 여부만 응답하는 것이 아니라, 실제 처리된 데이터를 응답으로 돌려줄 수 있다. ✅

## 3. BoardService 수정

CUD 작업 후 처리한 객체를 리턴하려면 `BoardService`의 메서드도 수정이 필요할 수 있다.

예를 들어 기존에는 등록 메서드가 `void`였을 수 있다.

    void create(BoardDTO board);

하지만 API 서버에서는 생성된 객체를 응답해야 할 수 있으므로 다음처럼 변경할 수 있다.

    BoardDTO create(BoardDTO board);

수정과 삭제도 마찬가지로 처리된 객체를 리턴하도록 설계할 수 있다.

즉, REST API에서는 서비스 계층이 컨트롤러 응답에 필요한 객체를 반환하도록 구성할 수 있다. 📌

## 4. @RestController

`@RestController`는 REST API용 컨트롤러를 만들 때 사용하는 어노테이션이다.

`@RestController`는 모든 메서드에 `@ResponseBody`를 자동으로 추가한 것과 같다.

즉, 메서드가 반환하는 값은 View 이름이 아니라 HTTP 응답 body로 처리된다.

    @RestController
    @RequestMapping("/api/board")
    public class BoardApiController {
    }

## 5. @RestController의 특징

`@RestController`의 주요 특징은 다음과 같다.

- 모든 메서드에 `@ResponseBody` 자동 적용
- 응답 헤더의 `Content-Type`을 `application/json` 타입으로 설정
- 메서드가 객체를 리턴하면 자동으로 JSON 문자열로 변환
- JSON 변환은 Jackson 라이브러리가 담당

즉, Java 객체를 리턴하면 클라이언트는 JSON 형태의 응답을 받는다. ✅

## 6. Jackson 라이브러리

Jackson은 Java 객체를 JSON 문자열로 변환하거나, JSON 문자열을 Java 객체로 변환하는 라이브러리이다.

`@RestController`에서 객체를 리턴하면 Jackson이 자동으로 객체를 JSON으로 변환한다.

예를 들어 컨트롤러가 다음 객체를 리턴한다고 하자.

    BoardDTO board = new BoardDTO();
    board.setTitle("제목");
    board.setWriter("user01");

응답은 다음과 같은 JSON 형태가 될 수 있다.

    {
        "title": "제목",
        "writer": "user01"
    }

즉, REST API에서 객체와 JSON 사이의 변환을 Jackson이 담당한다. 📌

## 7. @RequestBody

요청의 body가 `application/json` 인코딩인 경우, 컨트롤러 메서드의 매개변수 앞에 `@RequestBody`를 사용한다.

예를 들어 클라이언트가 JSON 데이터를 전송하면 다음처럼 받을 수 있다.

    @PostMapping
    public BoardDTO create(@RequestBody BoardDTO board) {
        return service.create(board);
    }

`@RequestBody`는 요청 body에 담긴 JSON 문자열을 Java 객체로 변환해 준다.

즉, JSON 요청 데이터를 DTO 객체로 받을 때 사용한다. ✅

## 8. REST API 매핑 어노테이션

REST API에서는 HTTP 메서드에 따라 요청을 구분한다.

대표적인 매핑 어노테이션은 다음과 같다.

- `@GetMapping(url)`
- `@PostMapping(url)`
- `@PutMapping(url)`
- `@DeleteMapping(url)`

각 의미는 다음과 같다.

- `GET` → 조회
- `POST` → 생성
- `PUT` → 수정
- `DELETE` → 삭제

즉, 같은 URL이라도 HTTP 메서드에 따라 다른 작업을 수행할 수 있다. 📌

## 9. 게시판 REST API 예시

게시판 API는 다음처럼 구성할 수 있다.

    @RestController
    @RequestMapping("/api/board")
    public class BoardApiController {

        @GetMapping("/{no}")
        public BoardDTO get(@PathVariable Long no) {
            return service.get(no);
        }

        @PostMapping
        public BoardDTO create(@RequestBody BoardDTO board) {
            return service.create(board);
        }

        @PutMapping("/{no}")
        public BoardDTO update(@PathVariable Long no,
                               @RequestBody BoardDTO board) {
            board.setNo(no);
            return service.update(board);
        }

        @DeleteMapping("/{no}")
        public BoardDTO delete(@PathVariable Long no) {
            return service.delete(no);
        }
    }

이 구조는 HTTP 메서드와 URL을 이용해 CRUD를 표현한다.

## 10. ResponseEntity<T>

`Rest Controller`가 객체를 바로 리턴하면 JSON 응답은 쉽게 만들 수 있다.

하지만 상태 코드나 응답 헤더를 세밀하게 설정하기는 어렵다.

이때 `ResponseEntity<T>`를 사용한다.

`ResponseEntity<T>`를 사용하면 다음을 직접 설정할 수 있다.

- 상태 코드
- 응답 헤더
- body

즉, REST API 응답을 더 정확하게 제어할 수 있다. ✅

## 11. HttpEntity 구조

`ResponseEntity<T>`는 `HttpEntity<T>`를 상속하는 클래스이다.

기본 구조는 다음과 같다.

    public class HttpEntity<T> {
        private final HttpHeaders headers;

        @Nullable
        private final T body;
    }

`HttpEntity`는 HTTP 요청 또는 응답에서 header와 body를 표현하는 기본 클래스이다.

`RequestEntity<T>`와 `ResponseEntity<T>`는 이를 상속한다.

    public class RequestEntity<T> extends HttpEntity<T>
    public class ResponseEntity<T> extends HttpEntity<T>

즉, `ResponseEntity`는 HTTP 응답 정보를 담는 객체이다. 📌

## 12. ResponseEntity 생성자

`ResponseEntity<T>`는 생성자를 이용해 만들 수 있다.

대표적인 생성자는 다음과 같다.

    public ResponseEntity(HttpStatus status) {
        this(null, null, status);
    }

    public ResponseEntity(@Nullable T body, HttpStatus status) {
        this(body, null, status);
    }

    public ResponseEntity(@Nullable T body,
                          @Nullable MultiValueMap<String, String> headers,
                          HttpStatus status) {
        super(body, headers);
        Assert.notNull(status, "HttpStatus must not be null");
        this.status = status;
    }

생성자를 사용하면 상태 코드, body, header를 직접 넣을 수 있다.

하지만 일반적으로는 생성자 방식보다 빌더 패턴을 더 많이 사용한다. 📌

## 13. ResponseEntity 생성자 패턴

생성자 패턴은 다음처럼 사용할 수 있다.

    return new ResponseEntity(body, headers, HttpStatus.valueOf(200));

하지만 이 방식은 코드가 길고 가독성이 떨어질 수 있다.

따라서 Spring에서는 `ResponseEntity.ok()`, `ResponseEntity.status()` 같은 빌더 메서드를 사용하는 방식이 권장된다. ⚠️

## 14. ResponseEntity 빌더 패턴

`ResponseEntity`는 빌더 패턴으로 생성하는 것이 권장된다.

대표적인 사용 방식은 다음과 같다.

    return ResponseEntity.ok().build();

이 코드는 200 상태 코드만 구성한다.

    return ResponseEntity.ok(body);

이 코드는 200 상태 코드와 body를 함께 구성한다.

가장 많이 사용하는 방식이다. ✅

## 15. ResponseEntity 헤더와 body 구성

상태 코드, 헤더, body를 함께 구성할 수도 있다.

    return ResponseEntity.ok()
            .headers(headers)
            .body(body);

이 방식은 200 상태 코드와 함께 응답 헤더와 body를 설정한다.

즉, 파일 다운로드나 인증 관련 헤더처럼 별도 헤더가 필요한 경우 사용할 수 있다.

## 16. ResponseEntity status()

200이 아닌 다른 상태 코드를 응답해야 할 때는 `status()`를 사용한다.

    return ResponseEntity.status(상태코드).build();

상태 코드와 body를 함께 구성하려면 다음처럼 작성한다.

    return ResponseEntity.status(상태코드).body(body);

상태 코드, 헤더, body를 모두 구성하려면 다음처럼 작성한다.

    return ResponseEntity.status(상태코드)
            .headers(headers)
            .body(body);

필기에는 `ResponseEntiry`로 적혀 있지만, 정확한 클래스명은 `ResponseEntity`이다. ⚠️

## 17. POST 요청과 JSON BODY

REST API에서 게시글을 생성할 때는 POST 요청을 사용할 수 있다.

요청 예시는 다음과 같다.

    POST http://localhost:8080/api/board
    Content-Type: application/json

    {
      "title": "테스트 제목xxx",
      "content": "테스트 내용xxx",
      "writer": "user010"
    }

이 요청은 JSON 형식의 게시글 데이터를 서버에 전송한다.

컨트롤러에서는 `@RequestBody BoardDTO board` 형태로 받을 수 있다. 📌

## 18. IntelliJ HTTP 파일

IntelliJ에서는 `.http` 파일을 만들어 HTTP 요청을 직접 테스트할 수 있다.

예를 들어 프로젝트 루트 경로에 `board.http` 파일을 만들고 다음처럼 작성할 수 있다.

    ###
    POST http://localhost:8080/api/board/
    Content-Type: application/json

    {
      "title": "테스트 제목xxx",
      "content": "테스트 내용xxx",
      "writer": "user010"
    }

`###`는 여러 요청을 구분할 때 사용한다.

즉, 별도의 Postman 없이도 IntelliJ에서 API 요청을 테스트할 수 있다. ✅

## 19. POST 응답 코드

POST 요청으로 자원을 생성한 경우 응답 코드를 `200 OK` 대신 `201 Created`로 주는 경우도 많다.

`200 OK`는 요청이 성공했다는 의미이다.

`201 Created`는 요청이 성공했고 새로운 자원이 생성되었다는 의미이다.

따라서 생성 API에서는 다음처럼 응답할 수 있다.

    return ResponseEntity.status(HttpStatus.CREATED).body(createdBoard);

즉, 생성 작업에는 201 상태 코드를 사용하는 것이 REST API 의미에 더 잘 맞을 수 있다. 📌

## 20. @RestControllerAdvice

`@RestControllerAdvice`는 REST Controller에서 발생한 예외를 공통으로 처리할 때 사용한다.

AOP 기능을 이용해 `@RestController` 처리 과정에서 발생한 예외를 핸들링한다.

즉, 각 컨트롤러 메서드마다 try-catch를 작성하지 않고, 예외 처리 코드를 한 곳에 모을 수 있다. ✅

## 21. @ExceptionHandler

`@ExceptionHandler`는 특정 예외가 발생했을 때 실행할 메서드를 지정한다.

기본 구조는 다음과 같다.

    @RestControllerAdvice
    public class ApiExceptionAdvice {

        @ExceptionHandler({처리할예외클래스.class})
        public ResponseEntity<T> handler(HttpServletRequest request, 예외클래스 e) {
            // ResponseEntity 구성 후 리턴
        }
    }

이 메서드는 지정한 예외가 발생했을 때 호출된다.

그리고 `ResponseEntity`를 이용해 에러 상태 코드와 에러 메시지를 응답할 수 있다. 📌

## 22. REST API 예외 처리 예시

예외 처리 예시는 다음과 같다.

    @RestControllerAdvice
    public class ApiExceptionAdvice {

        @ExceptionHandler({IllegalArgumentException.class})
        public ResponseEntity<String> handleBadRequest(
                HttpServletRequest request,
                IllegalArgumentException e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

이 코드는 `IllegalArgumentException`이 발생하면 400 Bad Request와 에러 메시지를 응답한다.

즉, API 서버에서는 예외도 JSON이나 문자열 body와 상태 코드로 명확히 응답해야 한다. ✅

## 23. REST API 전체 흐름

게시판 REST API 처리 흐름은 다음과 같다.

1. 클라이언트가 HTTP 요청 전송
2. `@RestController`가 요청 수신
3. JSON body가 있으면 `@RequestBody`로 DTO 변환
4. Controller가 Service 호출
5. Service가 CUD 작업 후 처리된 객체 반환
6. Controller가 객체 또는 `ResponseEntity` 반환
7. Jackson이 객체를 JSON으로 변환
8. 응답 상태 코드, 헤더, body가 클라이언트로 전달
9. 예외 발생 시 `@RestControllerAdvice`에서 공통 처리

즉, REST API는 URL, HTTP 메서드, JSON body, 상태 코드를 함께 사용해 데이터를 주고받는다. 📌

## 24. 중요 포인트 📌

- REST API 서버에서는 CUD 처리 후 생성, 수정, 삭제된 객체를 리턴할 수 있다.
- `@RestController`는 모든 메서드에 `@ResponseBody`를 자동으로 추가한다.
- `@RestController`는 객체 반환 시 JSON 응답을 만든다.
- 객체를 JSON으로 변환하는 작업은 Jackson 라이브러리가 담당한다.
- 요청 body가 JSON이면 매개변수 앞에 `@RequestBody`를 사용한다.
- REST API 매핑에는 `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`을 사용한다.
- `ResponseEntity<T>`를 사용하면 상태 코드, 응답 헤더, body를 설정할 수 있다.
- `ResponseEntity.ok().build()`는 200 상태 코드만 구성한다.
- `ResponseEntity.ok(body)`는 200 상태 코드와 body를 구성한다.
- 200이 아닌 상태 코드는 `ResponseEntity.status(상태코드)`로 구성한다.
- POST 생성 요청에는 201 Created를 응답하는 경우도 많다.
- IntelliJ에서는 `.http` 파일로 API 요청을 테스트할 수 있다.
- `@RestControllerAdvice`는 REST Controller 예외를 공통 처리한다.
- `@ExceptionHandler`는 처리할 예외 클래스를 지정한다.

## 정리 ✅

Rest Controller는 JSP 화면이 아니라 JSON 데이터를 응답하는 API 서버용 컨트롤러이다.  
`@RestController`를 사용하면 모든 메서드에 `@ResponseBody`가 자동 적용되고, 객체를 리턴하면 Jackson 라이브러리가 JSON 문자열로 변환한다.  
JSON 요청 body를 Java 객체로 받을 때는 `@RequestBody`를 사용하며, REST API에서는 `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`으로 HTTP 메서드별 처리를 구분한다.  
응답 상태 코드와 헤더, body를 세밀하게 제어하려면 `ResponseEntity<T>`를 사용하고, 생성 성공 시에는 `200 OK` 대신 `201 Created`를 사용할 수 있다.  
또한 `@RestControllerAdvice`와 `@ExceptionHandler`를 사용하면 REST Controller에서 발생하는 예외를 공통으로 처리할 수 있다.
