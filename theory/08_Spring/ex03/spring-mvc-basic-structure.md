# ✨ 스프링 MVC의 기본 구조

## 1. 스프링 MVC 개념

Spring MVC는 웹 애플리케이션을 개발하기 위한 스프링의 웹 MVC 프레임워크이다.

개발자는 Servlet/JSP API의 세부 동작을 직접 신경 쓰지 않고, Controller, Service, Repository 같은 계층 구조를 이용해 웹 애플리케이션을 제작할 수 있다.

즉, Spring MVC는 내부적으로 Servlet/JSP 처리를 담당하면서 개발자가 더 구조적으로 웹 요청을 처리할 수 있게 도와준다. 📌

## 2. 개발자의 코드 영역

Spring MVC를 사용하면 개발자는 Servlet/JSP API를 직접 다루기보다 스프링이 제공하는 구조에 맞춰 코드를 작성한다.

개발자가 주로 작성하는 영역은 다음과 같다.

- Controller
- Service
- Repository 또는 Mapper
- View

즉, 요청 처리 흐름은 Spring MVC가 관리하고, 개발자는 각 계층의 역할에 맞는 코드를 작성한다. ✅

## 3. Spring MVC와 Servlet/JSP

Spring MVC는 내부적으로 Servlet/JSP 기반으로 동작한다.

하지만 개발자가 직접 `HttpServlet`을 상속하거나 복잡한 JSP 처리 흐름을 모두 직접 제어하지 않아도 된다.

스프링이 내부적으로 요청을 받고, 적절한 Controller를 찾고, View를 연결해 준다.

즉, Spring MVC는 Servlet/JSP를 더 편리하게 사용할 수 있도록 감싸는 구조라고 이해할 수 있다. 📌

## 4. 모델 2 구조

모델 2 방식은 웹 애플리케이션을 역할별로 분리하는 구조이다.

기본 흐름은 다음과 같다.

1. Request
2. FrontController가 요청을 받고 실제 Controller에 전달
3. Request 처리를 위한 로직 연동
4. Model 구성
5. 순수한 데이터 처리 로직인 Service 호출
6. 처리된 데이터를 View로 전달
7. Response

즉, 요청 처리, 비즈니스 로직, 화면 출력을 분리해서 관리하는 구조이다.

## 5. FrontController

FrontController는 모든 요청을 먼저 받아서 적절한 Controller로 전달하는 역할을 한다.

Spring MVC에서는 `DispatcherServlet`이 FrontController 역할을 한다.

즉, 클라이언트 요청이 들어오면 먼저 `DispatcherServlet`이 받고, 어떤 Controller가 처리할지 결정한다. ✅

## 6. Model

Model은 Controller에서 처리한 데이터를 View로 전달하기 위한 객체이다.

예를 들어 게시글 목록을 조회한 뒤 View에 전달할 때 Model에 데이터를 담는다.

    model.addAttribute("list", boardList);

이렇게 담긴 데이터는 JSP 같은 View에서 사용할 수 있다.

즉, Model은 Controller와 View 사이에서 데이터를 전달하는 역할을 한다. 📌

## 7. Service

Service는 순수한 데이터 처리 로직 또는 비즈니스 로직을 담당한다.

Controller는 요청을 받고 어떤 서비스를 호출할지 결정하고, 실제 핵심 처리는 Service에서 수행한다.

예를 들어 게시글 등록, 수정, 삭제 같은 작업은 Service 계층에서 처리한다.

즉, Service는 웹 요청과 직접적인 화면 처리보다는 애플리케이션의 실제 업무 로직을 담당한다. ✅

## 8. View

View는 사용자에게 보여줄 화면을 담당한다.

Controller에서 처리된 데이터는 Model에 담기고, View는 그 데이터를 이용해 화면을 렌더링한다.

JSP를 사용하는 경우 View는 `.jsp` 파일이 될 수 있다.

즉, View는 최종적으로 브라우저에 전달될 HTML 화면을 만드는 역할을 한다. 📌

## 9. Spring MVC 라이프 사이클

Spring MVC의 요청 처리 순서는 다음과 같다.

1. Filter
2. DispatcherServlet
3. HandlerMapping
4. HandlerInterceptor
5. HandlerAdapter
6. Controller
7. Service
8. Repository 또는 Mapper
9. ViewResolver
10. View
11. Response

즉, 요청은 여러 단계를 거쳐 Controller에 도달하고, 처리 결과는 View를 통해 응답으로 전달된다. ✅

## 10. Filter

Filter는 Web Application의 전역적인 로직을 담당한다.

Filter라는 이름처럼 요청이 DispatcherServlet에 들어가기 전, Web Application 단에서 전체적인 필터링 작업을 수행한다.

주로 다음과 같은 작업에 사용된다.

- 인코딩 설정
- 인증 검사
- 요청/응답 로깅
- 공통 보안 처리

즉, Filter는 스프링 MVC 내부로 들어가기 전 단계에서 실행되는 전역 처리 로직이다. 📌

## 11. DispatcherServlet

DispatcherServlet은 들어오는 모든 Request를 우선적으로 받아 처리하는 서블릿이다.

Spring MVC에서 FrontController 역할을 담당한다.

DispatcherServlet의 주요 역할은 다음과 같다.

1. 클라이언트 요청 수신
2. HandlerMapping에게 요청을 처리할 Controller 검색 요청
3. HandlerMapping으로부터 Controller 정보 반환받기
4. 해당 Controller와 요청을 매핑
5. Controller 실행 후 ViewResolver를 통해 View 연결

즉, DispatcherServlet은 요청을 어느 Controller로 보낼지 배치하는 핵심 역할을 한다. ✅

## 12. HandlerMapping

HandlerMapping은 DispatcherServlet으로부터 Controller 검색 요청을 받는다.

그리고 요청 URL과 매핑되는 Controller 정보를 찾아 DispatcherServlet에게 반환한다.

예를 들어 `/board/list` 요청이 들어오면 해당 요청을 처리할 Controller 메서드를 찾는다.

즉, HandlerMapping은 요청 URL과 Controller를 연결하는 역할을 한다. 📌

## 13. HandlerInterceptor

HandlerInterceptor는 Request가 Controller에 매핑되기 전 앞단에서 부가적인 로직을 추가할 때 사용한다.

주로 다음과 같은 작업에 많이 사용된다.

- 세션 검사
- 쿠키 검사
- 권한 인증
- 로그인 여부 확인
- 공통 요청 처리

즉, HandlerInterceptor는 Controller 실행 전후에 공통 로직을 끼워 넣을 수 있는 구조이다. ✅

## 14. HandlerAdapter

HandlerAdapter는 DispatcherServlet이 찾은 Controller를 실제로 실행할 수 있도록 도와주는 객체이다.

DispatcherServlet은 HandlerMapping을 통해 어떤 Controller가 요청을 처리할지 알 수 있다.

하지만 Controller의 실행 방식은 다양할 수 있으므로 HandlerAdapter가 중간에서 실제 호출을 담당한다.

즉, HandlerAdapter는 DispatcherServlet과 Controller 사이에서 Controller 호출을 돕는 어댑터 역할을 한다. 📌

## 15. Controller

Controller는 Request와 매핑되는 곳이다.

요청에 대해 어떤 로직으로 처리할 것인지 결정하고, 그에 맞는 Service를 호출한다.

Controller는 Service Bean을 스프링 컨테이너로부터 주입받아 사용한다.

예시는 다음과 같다.

    @Controller
    @RequestMapping("/board")
    public class BoardController {

        private final BoardService service;

        public BoardController(BoardService service) {
            this.service = service;
        }

        @GetMapping("/list")
        public String list(Model model) {
            model.addAttribute("list", service.getList());
            return "board/list";
        }
    }

즉, Controller는 요청과 서비스 로직을 연결하는 역할을 한다. ✅

## 16. Service 계층

Service는 데이터 처리 및 가공을 위한 비즈니스 로직을 수행한다.

Request에 대한 실질적인 로직을 수행하며, Repository 또는 Mapper를 통해 DB에 접근한다.

예를 들어 게시판 서비스는 다음과 같은 기능을 제공할 수 있다.

- 게시글 목록 조회
- 게시글 상세 조회
- 게시글 등록
- 게시글 수정
- 게시글 삭제

즉, Service는 CRUD 작업을 비즈니스 로직 관점에서 처리한다. 📌

## 17. Repository

Repository는 데이터베이스에 접근하는 객체이다.

DAO, 즉 Data Access Object라고 부르기도 한다.

Service에서 직접 SQL을 작성하거나 DB 연결을 처리하지 않고, Repository를 통해 데이터베이스 CRUD 작업을 수행한다.

즉, Repository는 DB 접근 책임을 분리하기 위한 계층이다. ✅

## 18. Mapper

MyBatis를 사용하는 경우 Repository 대신 Mapper라는 이름을 많이 사용한다.

Mapper는 SQL과 Java 메서드를 연결하는 역할을 한다.

예를 들어 다음과 같이 작성할 수 있다.

    public interface BoardMapper {
        List<BoardVO> getList();
        BoardVO get(Long no);
        int insert(BoardVO board);
        int update(BoardVO board);
        int delete(Long no);
    }

즉, Mapper는 Service가 DB 작업을 요청할 수 있도록 SQL 실행 메서드를 제공한다. 📌

## 19. ViewResolver

ViewResolver는 Controller에서 리턴한 View 이름을 실제 View 파일로 변환하는 역할을 한다.

Controller가 다음과 같이 View 이름을 반환했다고 하자.

    return "board/list";

ViewResolver는 이 이름을 실제 JSP 경로로 변환한다.

예를 들어 prefix와 suffix 설정이 있다면 다음처럼 해석될 수 있다.

    /WEB-INF/views/board/list.jsp

즉, ViewResolver는 논리적인 View 이름을 실제 View 파일로 연결한다. ✅

## 20. Response

ViewResolver를 통해 찾은 View는 Model 데이터를 이용해 화면을 렌더링한다.

렌더링된 View 화면은 최종적으로 브라우저로 전송된다.

즉, 사용자는 Controller가 처리한 결과를 HTML 화면으로 응답받게 된다. 📌

## 21. 전체 처리 흐름 예시

게시판 목록 요청을 예로 들면 Spring MVC 흐름은 다음과 같다.

1. 사용자가 `/board/list` 요청
2. Filter에서 전역 처리 수행
3. DispatcherServlet이 요청 수신
4. HandlerMapping이 `/board/list`를 처리할 Controller 검색
5. HandlerInterceptor가 사전 로직 수행
6. HandlerAdapter가 Controller 메서드 호출
7. Controller가 Service 호출
8. Service가 Repository 또는 Mapper 호출
9. Repository 또는 Mapper가 DB에서 게시글 목록 조회
10. Service가 결과 반환
11. Controller가 Model에 목록 저장
12. Controller가 View 이름 반환
13. ViewResolver가 실제 JSP 경로 결정
14. JSP가 Model 데이터를 이용해 화면 렌더링
15. 브라우저로 Response 전송

즉, Spring MVC는 요청부터 응답까지 여러 구성 요소가 역할을 나누어 처리한다. ✅

## 22. 중요 포인트 📌

- Spring MVC는 내부적으로 Servlet/JSP를 처리한다.
- 개발자는 Servlet/JSP API에 직접 신경 쓰지 않고 웹 애플리케이션을 제작할 수 있다.
- 모델 2 구조에서는 FrontController가 요청을 받고 실제 Controller에 전달한다.
- Spring MVC의 FrontController는 DispatcherServlet이다.
- Filter는 DispatcherServlet에 들어가기 전 Web Application 단에서 실행된다.
- DispatcherServlet은 모든 Request를 우선적으로 받아 처리한다.
- DispatcherServlet은 HandlerMapping에게 요청을 처리할 Controller 검색을 요청한다.
- HandlerMapping은 요청과 매핑되는 Controller 정보를 반환한다.
- HandlerInterceptor는 Controller 매핑 전후에 부가 로직을 추가한다.
- HandlerInterceptor는 세션, 쿠키, 권한 인증 로직에 자주 사용된다.
- Controller는 Request와 매핑되는 곳이다.
- Controller는 어떤 Service로 처리할지 결정하고 Service를 호출한다.
- Service는 데이터 처리 및 비즈니스 로직을 수행한다.
- Repository 또는 DAO는 DB에 접근하는 객체이다.
- MyBatis를 사용하는 경우 Mapper가 DB 접근 역할을 수행한다.
- ViewResolver는 Controller가 리턴한 View 이름을 실제 View로 연결한다.
- 렌더링된 View 화면은 브라우저로 전송된다.

## 정리 ✅

스프링 MVC의 기본 구조는 요청을 받아 처리하고 응답 화면을 반환하는 전체 흐름을 계층별로 나누어 관리하는 구조이다.  
개발자는 Servlet/JSP API를 직접 세부적으로 다루지 않고 Controller, Service, Repository 또는 Mapper, View를 작성해 웹 애플리케이션을 제작할 수 있다.  
요청은 Filter를 거쳐 DispatcherServlet으로 들어오고, DispatcherServlet은 HandlerMapping을 통해 요청에 맞는 Controller를 찾는다.  
Controller는 Service를 호출하고, Service는 Repository 또는 Mapper를 통해 DB의 CRUD 작업을 처리한다.  
마지막으로 Controller가 반환한 View 이름은 ViewResolver를 통해 실제 View로 연결되며, 렌더링된 화면이 브라우저에 Response로 전송된다.
