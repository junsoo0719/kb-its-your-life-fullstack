# ✨ 요청 포워딩, 리다이렉트

## 1. 모델 1

모델 1은 기존 JSP만으로 구현한 웹 애플리케이션 구조이다.

웹 브라우저의 요청을 JSP 페이지가 직접 받아서 처리한다.

즉, JSP 페이지가 요청 처리, 비즈니스 로직 처리, 화면 출력까지 모두 담당하는 구조이다. 📌

## 2. 모델 1의 특징

모델 1 방식에서는 JSP 페이지에 여러 코드가 섞이게 된다.

- 비즈니스 로직 처리 코드
- 데이터 처리 코드
- 웹 브라우저에 결과를 출력하는 코드
- HTML 화면 코드

즉, JSP가 핵심 역할을 수행한다.

이 방식은 구조가 단순하지만, 코드가 복잡해지고 유지보수가 어려워질 수 있다. ⚠️

## 3. 모델 2

모델 2는 클라이언트의 요청 처리, 응답 처리, 비즈니스 로직 처리 부분을 모듈화한 구조이다.

요청을 처리하는 Servlet, 데이터를 처리하는 Model, 결과를 출력하는 JSP View로 역할을 나누어 웹 브라우저의 요청을 처리한다.

즉, 모델 2는 MVC 패턴을 웹 애플리케이션에 적용한 구조이다. ✅

## 4. 모델 2의 구성

모델 2는 다음 요소로 나눌 수 있다.

- Model
- View
- Controller

각 역할은 다음과 같다.

- Model → 요청에 대한 로직을 처리할 자바빈즈나 자바 클래스
- View → 요청 결과를 출력하는 JSP 페이지
- Controller → 모든 흐름을 제어하는 Servlet

즉, 모델 2에서는 Servlet이 중요한 역할을 한다. 📌

## 5. MVC

MVC는 Model, View, Controller의 약자이다.

웹 애플리케이션을 다음 세 가지 역할로 분리하는 디자인 패턴이다.

- 비즈니스 로직
- 프레젠테이션 로직
- 데이터 또는 요청 처리

즉, MVC는 하나의 JSP에 모든 코드를 섞지 않고 역할별로 나누는 구조이다. ✅

## 6. 웹 애플리케이션에서 MVC 역할

웹 애플리케이션에서는 일반적으로 애플리케이션을 다음과 같이 구분한다.

- 비즈니스 로직
- 프레젠테이션
- 요청 처리 데이터

비즈니스 로직은 애플리케이션의 데이터를 조작하는 데 사용된다.

예를 들어 다음 정보의 처리이다.

- 고객 정보
- 제품 정보
- 주문 정보

프레젠테이션은 애플리케이션이 사용자에게 어떻게 표시되는지를 의미한다.

예를 들어 다음 요소가 포함된다.

- 위치
- 폰트
- 크기
- 화면 구성

요청 처리 데이터는 비즈니스 로직과 프레젠테이션 파트를 함께 묶어 요청과 응답 흐름을 처리하는 부분이다. 📌

## 7. MVC 패턴 구성 요소

MVC 패턴은 Model, View, Controller로 구성된다.

각 요소는 서로 다른 역할을 담당한다.

- Model → 데이터와 비즈니스 로직
- View → 사용자에게 보여줄 화면
- Controller → 요청 흐름 제어

이렇게 역할을 나누면 코드의 재사용성과 유지보수성이 좋아진다. ✅

## 8. Model

Model은 애플리케이션의 데이터와 비즈니스 로직을 담는 객체이다.

예를 들어 회원, 게시글, 상품, 주문 같은 데이터를 처리하는 객체가 Model 역할을 할 수 있다.

Model은 단순 데이터만 담을 수도 있고, 데이터 처리 로직을 포함할 수도 있다.

즉, Model은 애플리케이션의 핵심 데이터와 로직을 담당한다. 📌

## 9. Controller

Controller는 Model과 View 사이에서 동작을 조정한다.

웹으로부터 받은 요청에 가장 적합한 Model을 생성하거나 호출하고, 사용자에게 응답할 적절한 View를 선택한다.

웹 애플리케이션의 모델 2 구조에서는 Servlet이 Controller 역할을 한다.

즉, Controller는 요청을 받고, 처리할 로직을 선택하고, 결과를 보여줄 View로 이동시키는 역할이다. ✅

## 10. View

View는 사용자에게 Model의 정보, 즉 데이터를 보여주는 역할을 한다.

웹 애플리케이션에서는 JSP가 View 역할을 한다.

View는 비즈니스 로직을 포함하지 않는 것이 좋다.

하나의 Model은 다양한 View에서 사용할 수 있다.

예를 들어 같은 게시글 목록 데이터를 다음처럼 여러 방식으로 보여줄 수 있다.

- HTML 화면
- 모바일 화면
- JSON 응답

즉, View는 데이터를 사용자에게 표현하는 역할에 집중한다. 📌

## 11. 요청 포워딩의 필요성

요청 포워딩은 요청 처리 흐름을 다른 Servlet이나 JSP로 전달하기 위해 필요하다.

요청 포워딩이 필요한 이유는 다음과 같다.

- 요청 처리 작업의 모듈화
- 모듈의 재사용성 증가
- 유지보수 편의성 향상
- MVC 모델 2 구조 구현
- 요청 처리와 응답 처리 분리

즉, 요청 포워딩을 사용하면 Controller와 View를 역할별로 분리할 수 있다. ✅

## 12. 모델 2에서 요청과 응답 처리

모델 2 구조에서는 요청 처리와 응답 처리를 나누어 담당한다.

- 요청 처리 → FrontController
- 응답 처리 → JSP View

FrontController는 요청을 받고 필요한 로직을 수행한 뒤, 결과 데이터를 JSP로 전달한다.

JSP는 전달받은 데이터를 화면에 출력한다.

즉, Servlet은 처리 흐름을 제어하고, JSP는 화면 표현에 집중한다. 📌

## 13. 요청 포워딩 방법

요청 포워딩 방법은 크게 두 가지로 볼 수 있다.

- `RequestDispatcher` 클래스를 이용한 forward 방법
- `HttpServletResponse` 클래스를 이용한 redirect 방법

두 방식 모두 다른 페이지로 이동하는 것처럼 보일 수 있지만, 내부 동작은 다르다.

## 14. RequestDispatcher를 이용한 forward

forward는 `RequestDispatcher`를 이용해 처리할 수 있다.

예시는 다음과 같다.

    RequestDispatcher dis = req.getRequestDispatcher(target);
    dis.forward(req, res);

여기서 `target`은 이동할 Servlet 또는 JSP 경로이다.

forward는 서버 내부에서 요청을 다른 자원으로 전달한다. ✅

## 15. forward의 특징

forward는 클라이언트가 내부 이동 과정을 알 수 없다.

브라우저 입장에서는 처음 요청한 URL만 보인다.

즉, 사용자는 요청이 Servlet에서 처리된 것인지, JSP로 전달되어 처리된 것인지 알 수 없다.

forward는 서버 내부에서 이동하므로 기존 request와 response 객체가 유지된다. 📌

## 16. forward와 request scope

forward는 동일한 `HttpServletRequest` 객체를 사용한다.

따라서 request scope에 저장한 데이터를 target JSP나 Servlet에서 사용할 수 있다.

예시는 다음과 같다.

    req.setAttribute("message", "forward 데이터");
    RequestDispatcher dis = req.getRequestDispatcher("/result.jsp");
    dis.forward(req, res);

이 경우 `result.jsp`에서 request에 저장된 `message` 값을 사용할 수 있다. ✅

## 17. HttpServletResponse를 이용한 redirect

redirect는 `HttpServletResponse`의 `sendRedirect()` 메서드를 이용한다.

예시는 다음과 같다.

    res.sendRedirect(target);

여기서 `target`은 이동할 페이지 또는 요청 URL이다.

redirect는 첫 번째 요청에 대해 응답을 보낸 뒤, 브라우저가 다시 새로운 요청을 보내도록 한다. 📌

## 18. redirect의 특징

redirect는 브라우저가 새로운 요청을 다시 보내는 방식이다.

즉, 첫 번째 요청과 redirect 이후 요청은 서로 다른 요청이다.

따라서 동일한 `HttpServletRequest` 객체가 아니라 새로운 request가 사용된다.

결과적으로 request scope가 달라진다. ✅

## 19. forward와 redirect의 요청 횟수 차이

forward와 redirect의 가장 큰 차이는 요청 횟수이다.

forward는 요청이 1번 간 것이다.

redirect는 요청이 2번 간 것이다.

정리하면 다음과 같다.

- forward → 클라이언트 요청 1번, 서버 내부 이동
- redirect → 클라이언트 요청 1번 후 서버 응답, 브라우저가 다시 요청 1번

즉, redirect는 브라우저가 새 요청을 보내기 때문에 URL도 변경된다. 📌

## 20. forward와 redirect 비교

forward와 redirect는 다음처럼 비교할 수 있다.

| 구분          | forward                | redirect                       |
| ------------- | ---------------------- | ------------------------------ |
| 이동 주체     | 서버 내부              | 브라우저                       |
| 요청 횟수     | 1번                    | 2번                            |
| URL 변경      | 변경되지 않음          | 변경됨                         |
| request 객체  | 유지됨                 | 새로 생성됨                    |
| request scope | 유지됨                 | 유지되지 않음                  |
| 데이터 전달   | request attribute 가능 | query string, session 등 사용  |
| 주요 용도     | 처리 결과를 JSP로 전달 | 작업 후 목록/다른 URL로 재요청 |

즉, 데이터를 request scope로 전달해야 한다면 forward가 적합하고, 새 요청으로 이동해야 한다면 redirect가 적합하다. ✅

## 21. 언제 forward를 사용하는가

forward는 서버 내부에서 View로 이동할 때 많이 사용한다.

예를 들어 Servlet에서 게시글 목록을 조회한 뒤 JSP로 전달하는 경우이다.

    req.setAttribute("list", boardList);
    req.getRequestDispatcher("/WEB-INF/views/board/list.jsp")
       .forward(req, res);

이 경우 request scope가 유지되므로 JSP에서 `list` 데이터를 사용할 수 있다. 📌

## 22. 언제 redirect를 사용하는가

redirect는 클라이언트가 새로운 URL로 다시 요청해야 할 때 사용한다.

예를 들어 게시글 등록 후 목록 페이지로 이동할 때 사용할 수 있다.

    res.sendRedirect("/board/list");

등록 처리 후 redirect를 사용하면 새로고침 시 같은 등록 요청이 반복되는 문제를 줄일 수 있다.

즉, 데이터 변경 작업 후에는 redirect를 사용하는 경우가 많다. ✅

## 23. PRG 패턴

PRG는 Post-Redirect-Get의 약자이다.

POST 요청으로 데이터를 처리한 뒤, redirect를 통해 GET 요청으로 결과 페이지를 다시 요청하는 패턴이다.

예를 들어 게시글 등록 흐름은 다음과 같다.

1. POST `/board/register`
2. 게시글 등록 처리
3. redirect `/board/list`
4. GET `/board/list`

이렇게 하면 사용자가 새로고침해도 POST 등록 요청이 반복되지 않는다. 📌

## 24. 중요 포인트 📌

- 모델 1은 JSP가 요청 처리와 화면 출력을 모두 담당하는 구조이다.
- 모델 1은 JSP에 비즈니스 로직과 출력 코드가 섞이기 쉽다.
- 모델 2는 요청 처리, 응답 처리, 비즈니스 로직을 모듈화한 구조이다.
- 모델 2는 Model, View, Controller로 역할을 나눈다.
- MVC는 Model, View, Controller의 약자이다.
- Model은 애플리케이션의 데이터와 비즈니스 로직을 담는다.
- Controller는 Model과 View 사이의 동작을 조정한다.
- 모델 2에서 Servlet은 Controller 역할을 한다.
- View는 사용자에게 Model의 정보를 보여준다.
- 웹 애플리케이션에서 JSP는 View 역할을 한다.
- 요청 포워딩은 요청 처리 작업의 모듈화와 재사용성 향상에 필요하다.
- 요청 처리는 FrontController가 담당하고 응답 처리는 JSP가 담당한다.
- forward는 `RequestDispatcher`를 이용한다.
- `dis.forward(req, res)`로 서버 내부 이동을 수행한다.
- forward는 클라이언트가 내부 이동을 알 수 없다.
- forward는 요청이 1번이다.
- redirect는 `HttpServletResponse`의 `sendRedirect()`를 이용한다.
- redirect는 첫 번째 요청에 응답한 뒤 브라우저가 다시 요청한다.
- redirect는 요청이 2번이다.
- redirect는 새로운 request가 사용되므로 request scope가 다르다.

## 정리 ✅

모델 1은 JSP가 요청 처리와 화면 출력을 모두 담당하는 구조이고, 모델 2는 요청 처리, 응답 처리, 비즈니스 로직을 MVC 구조로 분리한 방식이다.  
MVC에서 Model은 데이터와 비즈니스 로직을 담당하고, Controller는 요청 흐름을 제어하며, View는 사용자에게 데이터를 화면으로 보여준다.  
요청 포워딩은 요청 처리와 응답 처리를 분리하고 모듈의 재사용성과 유지보수성을 높이기 위해 사용된다.  
forward는 `RequestDispatcher`를 이용해 서버 내부에서 이동하는 방식이며, 요청이 1번만 발생하고 request scope가 유지된다.  
redirect는 `HttpServletResponse.sendRedirect()`를 이용해 브라우저가 새로운 요청을 보내도록 하는 방식이며, 요청이 2번 발생하고 새로운 request가 사용되므로 request scope가 유지되지 않는다.
