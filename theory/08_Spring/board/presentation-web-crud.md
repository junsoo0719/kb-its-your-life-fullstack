# ✨ 프레젠테이션(웹) 계층의 CRUD 구현

## 1. 프레젠테이션 계층의 역할

프레젠테이션 계층은 사용자의 웹 요청을 받아 처리하는 계층이다.

스프링 MVC에서는 주로 Controller가 이 역할을 담당한다.  
Controller는 사용자의 요청 URL과 HTTP 메서드에 따라 적절한 서비스 메서드를 호출하고, 결과를 Model에 담아 View로 전달한다. 📌

게시판 CRUD에서는 다음 요청을 처리한다.

- 목록 조회
- 새 글 등록
- 글 상세 조회
- 글 수정
- 글 삭제

## 2. 컨트롤러 테스트

컨트롤러 테스트는 실제 웹 브라우저나 웹 서버에 요청을 보내는 방식이 아니다.

대신 `MockMvc`를 사용해서 웹 요청을 보낸 것과 같은 효과를 낸다.

즉, 실제 서버를 실행하지 않고도 컨트롤러가 요청을 제대로 처리하는지 테스트할 수 있다. ✅

## 3. MockMvc

`MockMvc`는 스프링 MVC 테스트에서 사용하는 객체이다.

`MockMvc`는 웹 서버에 요청을 보낸 것과 같은 효과를 내는 메서드를 제공한다.

즉, 메서드 호출만으로 다음과 같은 웹 요청을 테스트할 수 있다.

- GET
- POST
- UPDATE
- DELETE

테스트마다 새로운 `MockMvc` 객체 생성이 필요하므로 보통 `@BeforeEach` 어노테이션이 붙은 메서드에서 설정한다.

## 4. @BeforeEach와 MockMvc 설정

`@BeforeEach`는 각 테스트 메서드가 실행되기 전에 매번 실행된다.

따라서 테스트마다 새로운 `MockMvc` 객체를 만들 때 사용할 수 있다.

예시는 다음과 같다.

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(boardController)
                .build();
    }

이렇게 하면 각 테스트 실행 전에 컨트롤러 테스트 환경이 새로 준비된다. 📌

## 5. 목록 요청

게시판 목록 요청은 GET 방식으로 처리한다.

요청 정보는 다음과 같다.

- HTTP 메서드: GET
- URL: `/board/list`
- Model 속성명: `list`
- Model 속성값: `service.getList()`로 가져온 목록
- View 이름: `board/list`

즉, 목록 요청이 들어오면 서비스에서 게시글 목록을 가져와 `list`라는 이름으로 Model에 담고, `board/list` 화면으로 이동한다.

## 6. BoardController 목록 처리

목록 처리는 컨트롤러에서 다음 흐름으로 작성할 수 있다.

    @GetMapping("/list")
    public void list(Model model) {
        model.addAttribute("list", service.getList());
    }

또는 명시적으로 View 이름을 반환할 수도 있다.

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", service.getList());
        return "board/list";
    }

즉, 컨트롤러는 서비스 결과를 Model에 담고 View로 전달한다. ✅

## 7. GET 요청 테스트

`MockMvcRequestBuilders.get()`을 사용하면 GET 요청을 만들 수 있다.

형식은 다음과 같다.

    MockMvcRequestBuilders.get(url문자열)

예시는 다음과 같다.

    mockMvc.perform(MockMvcRequestBuilders.get("/board/list"));

이 코드는 `/board/list` URL로 GET 요청을 보낸 것처럼 테스트한다.

## 8. perform()

`perform()`은 지정된 요청을 스프링 MVC가 처리하도록 실행하는 메서드이다.

    mockMvc.perform(요청빌더)

이 메서드를 호출하면 지정된 URL을 처리하는 컨트롤러 메서드가 호출된다.

`perform()`은 `ResultActions` 객체를 리턴한다.

즉, 요청 실행 후 결과 검증을 이어서 수행할 수 있다. 📌

## 9. ResultActions와 andReturn()

`ResultActions`는 요청 처리 결과에 대해 검증하거나 결과를 반환받을 수 있는 객체이다.

`andReturn()`을 호출하면 컨트롤러의 처리 결과를 `MvcResult`로 받을 수 있다.

    MvcResult result = mockMvc.perform(get("/board/list"))
            .andReturn();

즉, 테스트 실행 결과를 직접 꺼내서 ModelAndView, 상태 코드 등을 확인할 수 있다.

## 10. MvcResult

`MvcResult`는 컨트롤러의 처리 결과 정보를 가진 객체이다.

포함하는 정보는 다음과 같다.

- Model 정보
- View 정보
- 상태 코드
- 요청 및 응답 정보

`getModelAndView()`를 호출하면 `ModelAndView` 객체를 얻을 수 있다.

    ModelAndView mav = result.getModelAndView();

즉, 컨트롤러가 어떤 View를 반환했고 Model에 어떤 값을 담았는지 확인할 수 있다. ✅

## 11. MockMvc 주요 메서드

MockMvc 테스트에서 자주 사용하는 메서드는 다음과 같다.

    standaloneSetup()
    perform()
    andExpect()
    andReturn()

검증에 자주 사용하는 표현은 다음과 같다.

    andExpect(status().isOk())
    andExpect(content().string("expected"))
    andExpect(jsonPath("$.property").value("expected"))
    andExpect(view().name("expectedView"))
    andExpect(model().attribute("attributeName", "expectedValue"))
    andExpect(redirectedUrl("expectedUrl"))

각 메서드는 응답 상태, 응답 내용, JSON 값, View 이름, Model 속성, redirect URL 등을 검증할 때 사용한다. 📌

## 12. 새 글 등록 화면 요청

새 글 등록 화면은 GET 요청으로 처리한다.

요청 정보는 다음과 같다.

- HTTP 메서드: GET
- URL: `/board/create`
- View 이름: `board/create`

즉, 사용자가 새 글 작성 화면에 접근하면 `board/create` 화면을 보여준다.

예시는 다음과 같다.

    @GetMapping("/create")
    public String create() {
        return "board/create";
    }

## 13. 새 글 등록 처리

새 글 등록 처리는 POST 요청으로 처리한다.

요청 정보는 다음과 같다.

- HTTP 메서드: POST
- URL: `/board/create`
- 파라미터: `BoardDTO`
- 처리: `service.create()`로 실제 등록
- View 이름: `redirect:/board/list`

즉, 사용자가 작성한 게시글 데이터를 `BoardDTO`로 받고, 서비스에서 등록한 뒤 목록 화면으로 redirect한다. ✅

예시는 다음과 같다.

    @PostMapping("/create")
    public String create(BoardDTO board) {
        service.create(board);
        return "redirect:/board/list";
    }

## 14. MockMvc로 POST 요청 테스트

POST 요청 테스트는 `MockMvcRequestBuilders.post()`를 사용한다.

형식은 다음과 같다.

    MockMvcRequestBuilders.post(url문자열)
        .param(키1, 값1)
        .param(키2, 값2)

예시는 다음과 같다.

    mockMvc.perform(post("/board/create")
            .param("title", "테스트 제목")
            .param("content", "테스트 내용")
            .param("writer", "user01"))
            .andExpect(redirectedUrl("/board/list"));

여기서 `.param()`은 form 요소 값을 전달하는 역할을 한다. 📌

## 15. 글 상세 보기

글 상세 보기는 GET 요청으로 처리한다.

요청 정보는 다음과 같다.

- HTTP 메서드: GET
- URL: `/board/get`
- 파라미터: `no`
- Model 속성명: `board`
- Model 속성값: `service.get()`으로 가져온 `BoardDTO`
- View 이름: `board/get`

즉, 특정 글 번호를 전달받아 해당 게시글 정보를 조회하고, `board`라는 이름으로 Model에 담는다.

## 16. 상세 조회 컨트롤러 처리

상세 조회는 다음과 같이 작성할 수 있다.

    @GetMapping("/get")
    public String get(@RequestParam("no") Long no, Model model) {
        model.addAttribute("board", service.get(no));
        return "board/get";
    }

이 코드는 요청 파라미터 `no`를 받아 해당 게시글을 조회한다.

즉, `/board/get?no=1` 요청이 들어오면 1번 게시글 정보를 조회한다. ✅

## 17. GET 요청의 쿼리 파라미터 테스트

GET 요청에서 쿼리 파라미터를 전달할 때도 `.param()`을 사용한다.

    MockMvcRequestBuilders.get(url문자열)
        .param(키1, 값1)
        .param(키2, 값2)

예시는 다음과 같다.

    mockMvc.perform(get("/board/get")
            .param("no", "1"))
            .andExpect(view().name("board/get"))
            .andExpect(model().attributeExists("board"));

이 코드는 `/board/get?no=1` 요청을 테스트하는 것과 같다. 📌

## 18. 수정 화면 요청

글 수정 화면은 GET 요청으로 처리한다.

요청 정보는 다음과 같다.

- HTTP 메서드: GET
- URL: `/board/update`
- 처리: `BoardController`의 `get()`에서 처리 가능

수정 화면은 기존 글 정보를 보여준 뒤 수정할 수 있어야 하므로 상세 조회와 비슷하게 처리한다.

즉, 글 번호로 기존 게시글 정보를 조회하고 수정 화면에 전달한다.

## 19. 글 수정 처리

글 수정 처리는 POST 요청으로 처리한다.

요청 정보는 다음과 같다.

- HTTP 메서드: POST
- URL: `/board/update`
- 파라미터: `BoardDTO`
- 처리: `service.update()`로 실제 수정
- View 이름: `redirect:/board/list`

예시는 다음과 같다.

    @PostMapping("/update")
    public String update(BoardDTO board) {
        service.update(board);
        return "redirect:/board/list";
    }

즉, 수정된 데이터를 `BoardDTO`로 받아 서비스에서 수정한 뒤 목록으로 이동한다. ✅

## 20. RequestParam과 ModelAttribute

`@RequestParam`은 요청 파라미터를 메서드의 개별 매개변수로 받을 때 사용한다.

하지만 `@RequestParam`으로 받은 값은 자동으로 Request 스코프에 담기지 않는다.

즉, 화면에서 다시 사용하려면 직접 Model에 추가하거나 `@ModelAttribute`를 사용해야 한다.

반면 DTO 객체는 자동으로 Request 스코프에 담길 수 있다.

다만 성공 후 `redirect`가 발생하면 기존 Request 스코프는 사라진다. ⚠️

## 21. redirect와 Request 스코프

redirect는 새로운 요청을 다시 보내는 방식이다.

따라서 기존 요청에서 사용하던 Request 스코프 데이터는 유지되지 않는다.

즉, 다음처럼 redirect하면

    return "redirect:/board/list";

기존 요청에 담긴 Model 데이터는 사라진다.

redirect 후에도 데이터를 전달해야 한다면 redirect attributes 같은 별도 방법이 필요하다. 📌

## 22. 삭제 처리

삭제는 보통 GET 요청 없이 POST 요청으로 처리한다.

요청 정보는 다음과 같다.

- HTTP 메서드: POST
- URL: `/board/delete`
- 파라미터: `no`
- 처리: `service.delete()`로 실제 삭제
- View 이름: `redirect:/board/list`

예시는 다음과 같다.

    @PostMapping("/delete")
    public String delete(@RequestParam("no") Long no) {
        service.delete(no);
        return "redirect:/board/list";
    }

삭제는 데이터 변경 작업이므로 GET보다 POST로 처리하는 것이 적절하다. ✅

## 23. 삭제 테스트

삭제 테스트는 POST 요청으로 작성할 수 있다.

    mockMvc.perform(post("/board/delete")
            .param("no", "1"))
            .andExpect(redirectedUrl("/board/list"));

이 테스트는 1번 글 삭제 요청을 보낸 뒤 목록 페이지로 redirect되는지 확인한다.

## 24. 게시판 CRUD URL 정리

게시판 CRUD 요청을 정리하면 다음과 같다.

| 기능      | HTTP 메서드 | URL             | 처리                           |
| --------- | ----------- | --------------- | ------------------------------ |
| 목록      | GET         | `/board/list`   | 목록 조회                      |
| 등록 화면 | GET         | `/board/create` | 등록 화면 이동                 |
| 등록 처리 | POST        | `/board/create` | 등록 후 목록 redirect          |
| 상세 조회 | GET         | `/board/get`    | 글 번호로 조회                 |
| 수정 화면 | GET         | `/board/update` | 기존 글 조회 후 수정 화면 이동 |
| 수정 처리 | POST        | `/board/update` | 수정 후 목록 redirect          |
| 삭제 처리 | POST        | `/board/delete` | 삭제 후 목록 redirect          |

이 구조는 게시판 CRUD 구현의 기본 흐름이다. 📌

## 25. 중요 포인트 📌

- 컨트롤러 테스트는 실제 웹 요청을 보내는 것이 아니라 `MockMvc`로 테스트한다.
- `MockMvc`는 웹 서버에 요청을 보낸 것과 같은 효과를 제공한다.
- 테스트마다 새로운 `MockMvc` 객체를 만들기 위해 `@BeforeEach`를 사용할 수 있다.
- 목록 요청은 GET `/board/list`이다.
- 목록 요청은 Model에 `list`라는 이름으로 목록 데이터를 담는다.
- `MockMvcRequestBuilders.get()`으로 GET 요청을 만들 수 있다.
- `perform()`은 요청을 실행하고 스프링 MVC가 처리하게 한다.
- `andExpect()`는 응답 상태, View 이름, Model 속성 등을 검증한다.
- 새 글 등록 화면은 GET `/board/create`이다.
- 새 글 등록 처리는 POST `/board/create`이다.
- POST 요청 테스트에서는 `.param()`으로 form 값을 전달한다.
- 상세 조회는 GET `/board/get`이며 `no` 파라미터를 사용한다.
- 수정 화면은 GET `/board/update`이며 상세 조회와 유사하다.
- 수정 처리는 POST `/board/update`이다.
- `@RequestParam`은 자동으로 Request 스코프에 담기지 않는다.
- DTO 객체는 자동으로 Request 스코프에 담길 수 있다.
- redirect가 발생하면 기존 Request 스코프는 사라진다.
- 삭제 처리는 POST `/board/delete`로 처리한다.

## 정리 ✅

프레젠테이션 계층의 CRUD 구현에서는 BoardController가 게시판 요청을 받아 Service를 호출하고, 결과를 Model에 담아 View로 전달하는 흐름이 핵심이다.  
컨트롤러 테스트는 실제 웹 서버 요청이 아니라 `MockMvc`를 사용해 GET, POST 요청을 메서드 호출로 수행한다.  
목록 조회는 `/board/list`, 등록은 GET/POST `/board/create`, 상세 조회는 `/board/get`, 수정은 GET/POST `/board/update`, 삭제는 POST `/board/delete`로 처리한다.  
`MockMvc`에서는 `perform()`으로 요청을 실행하고 `andExpect()`로 상태 코드, View 이름, Model 속성, redirect URL 등을 검증할 수 있다.  
또한 `@RequestParam`은 자동으로 Request 스코프에 담기지 않고, DTO 객체는 자동으로 담길 수 있지만 redirect 시 Request 스코프가 사라진다는 점을 기억해야 한다.
