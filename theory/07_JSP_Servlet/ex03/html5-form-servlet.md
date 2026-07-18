# ✨ HTML5 Form 태그와 서블릿

## 1. form 태그

`form` 태그는 웹 브라우저에서 사용자가 입력한 데이터를 서버로 전송할 때 사용한다.

주로 로그인, 회원가입, 검색, 게시글 작성 같은 화면에서 사용된다.

`form` 태그에서 중요한 속성은 다음과 같다.

- `action`
- `method`

즉, `form` 태그는 “어디로”, “어떤 방식으로” 데이터를 보낼지 지정하는 태그이다. 📌

## 2. action 속성

`action` 속성은 submit 버튼을 선택했을 때 웹 서버에서 처리할 컴포넌트를 지정한다.

즉, 사용자가 입력한 폼 데이터를 어느 JSP 또는 Servlet으로 보낼지 정한다.

예시는 다음과 같다.

    <form action="/login" method="post">
        <input type="text" name="name">
        <button type="submit">전송</button>
    </form>

여기서 `action="/login"`은 폼 데이터를 `/login` 경로로 전송하겠다는 의미이다.

## 3. method 속성

`method` 속성은 웹 브라우저에서 웹 서버로 요청하는 방법을 지정한다.

대표적인 방식은 다음 두 가지이다.

- GET
- POST

GET과 POST는 서버로 데이터를 보내는 방식이 다르다.

즉, `method`는 요청 파라미터를 URL에 붙여 보낼지, 요청 body에 담아 보낼지를 결정한다. ✅

## 4. GET 방식

GET 방식은 요청 파라미터 값이 웹 브라우저의 URL에 포함되어 웹 서버로 전송되는 방식이다.

예시는 다음과 같다.

    http://서버IP:포트번호/컨텍스트명/경로명/login?name=홍길동&age=20

여기서 `?` 뒤에 붙은 값들이 요청 파라미터이다.

    name=홍길동
    age=20

즉, GET 방식은 요청 데이터가 URL에 보이는 방식이다. 📌

## 5. GET 방식의 특징

GET 방식의 특징은 다음과 같다.

- 요청 파라미터가 URL에 표시된다.
- 서블릿에서는 `doGet()` 메서드가 처리한다.
- 파라미터 길이에 제한이 있다.
- URL에 데이터가 노출되므로 보안에 취약하다.
- 일반 `<a href="...">` 링크나 명시적 URL 요청은 GET 방식이다.

예시는 다음과 같다.

    <a href="/login?name=hong&age=20">요청</a>

즉, 조회나 검색처럼 데이터 노출 부담이 적은 요청에 주로 사용한다. ✅

## 6. POST 방식

POST 방식은 요청 파라미터 값이 HTTP Request의 요청 몸체 body에 포함되어 전송되는 방식이다.

GET과 달리 URL에서 파라미터 값을 직접 확인할 수 없다.

예시는 다음과 같다.

    http://서버IP:포트번호/컨텍스트명/경로명/login

실제 데이터는 URL 뒤에 붙지 않고 HTTP Request Body 안에 담긴다.

즉, POST 방식은 사용자가 입력한 데이터를 body에 담아 서버로 전송한다. 📌

## 7. POST 방식의 특징

POST 방식의 특징은 다음과 같다.

- 요청 파라미터가 HTTP Request Body에 포함된다.
- URL에서 파라미터 값을 확인할 수 없다.
- 서블릿에서는 `doPost()` 메서드가 처리한다.
- 새로고침하는 경우 사용자에게 재요청 의사를 묻는 정보창이 나타날 수 있다.
- 로그인, 회원가입, 게시글 작성처럼 데이터를 등록하거나 변경하는 작업에 많이 사용된다.

즉, POST는 서버에 데이터를 전송하고 처리하는 요청에 주로 사용한다. ✅

## 8. GET과 POST 비교

GET과 POST는 다음처럼 비교할 수 있다.

| 구분        | GET                   | POST               |
| ----------- | --------------------- | ------------------ |
| 데이터 위치 | URL                   | HTTP Request Body  |
| URL 노출    | 노출됨                | 노출되지 않음      |
| 처리 메서드 | `doGet()`             | `doPost()`         |
| 길이 제한   | 있음                  | 상대적으로 적음    |
| 보안성      | 낮음                  | GET보다 나음       |
| 주요 용도   | 조회, 검색, 링크 요청 | 등록, 수정, 로그인 |

즉, GET은 조회 중심, POST는 데이터 전송과 처리 중심으로 이해하면 된다. 📌

## 9. 요청 파라미터 값 추출

서블릿에서는 클라이언트가 전송한 파라미터 값을 `request` 객체의 메서드를 이용해 추출한다.

대표적인 메서드는 다음과 같다.

- `getParameter(name)`
- `getParameterValues(name)`
- `getParameterNames()`

즉, `HttpServletRequest` 객체를 통해 폼에서 전송된 데이터를 읽을 수 있다. ✅

## 10. getParameter(name)

`getParameter(name)`은 하나의 파라미터 값을 추출할 때 사용한다.

형식은 다음과 같다.

    String value = request.getParameter("name");

예를 들어 요청 URL이 다음과 같다면

    /login?name=홍길동

다음 코드로 값을 꺼낼 수 있다.

    String name = request.getParameter("name");

`name`에 해당하는 파라미터 값이 없으면 `null`을 리턴한다. 📌

## 11. getParameterValues(name)

`getParameterValues(name)`은 하나의 name에 여러 값이 전달되는 경우 사용한다.

대표적인 예는 다음과 같다.

- checkbox
- multiple select

예를 들어 체크박스에서 여러 취미가 선택되면 같은 name으로 여러 값이 전달될 수 있다.

    <input type="checkbox" name="hobby" value="movie">
    <input type="checkbox" name="hobby" value="music">
    <input type="checkbox" name="hobby" value="game">

서블릿에서는 다음처럼 배열로 받을 수 있다.

    String[] hobbies = request.getParameterValues("hobby");

즉, 하나의 name에 여러 값이 있을 때는 `getParameterValues()`를 사용한다. ✅

## 12. getParameterNames()

`getParameterNames()`는 폼 태그 내에 전달된 파라미터 name 목록을 얻고자 할 때 사용한다.

모든 name 값을 `Enumeration` 타입으로 리턴한다.

예시는 다음과 같다.

    Enumeration<String> names = request.getParameterNames();

    while (names.hasMoreElements()) {
        String name = names.nextElement();
        String value = request.getParameter(name);
    }

즉, 어떤 파라미터들이 전달되었는지 전체 목록을 확인할 때 사용할 수 있다. 📌

## 13. 서블릿의 한글 처리

서블릿에서 한글 파라미터를 처리할 때는 요청 방식에 따라 인코딩 처리가 달라질 수 있다.

특히 POST 방식에서는 request body에 포함된 한글이 깨질 수 있으므로 인코딩 설정이 필요하다.

즉, 한글 데이터를 올바르게 받으려면 요청 파라미터를 읽기 전에 인코딩을 설정해야 한다. ✅

## 14. GET 방식의 한글 처리

GET 방식은 URL을 UTF-8로 인식하기 때문에 일반적으로 별도의 한글 인코딩 지정이 필요하지 않다.

GET 요청의 파라미터는 URL에 포함되어 전달된다.

예시는 다음과 같다.

    /login?name=홍길동

현재 톰캣 환경에서는 URL 인코딩이 UTF-8로 처리되는 경우가 많아 별도 설정 없이 한글이 정상 처리될 수 있다. 📌

## 15. POST 방식의 한글 처리

POST 방식은 요청 파라미터 값이 HTTP Body에 포함되어 전달된다.

톰캣의 디폴트 문자 인코딩 방식은 ISO-8859-1일 수 있다.

따라서 HTTP Body에 있는 입력 파라미터 값에 한글이 포함된 경우 한글 인코딩 작업이 필요하다.

다음 코드를 파라미터를 읽기 전에 작성해야 한다.

    request.setCharacterEncoding("UTF-8");

즉, POST 방식에서는 `getParameter()`를 호출하기 전에 UTF-8 인코딩을 먼저 지정해야 한다. ✅

## 16. request.setCharacterEncoding()

`request.setCharacterEncoding("UTF-8")`은 request body의 문자 인코딩을 UTF-8로 지정하는 코드이다.

POST 요청에서 한글이 포함된 데이터를 받을 때 사용한다.

중요한 점은 반드시 파라미터 값을 읽기 전에 호출해야 한다는 것이다.

잘못된 순서는 다음과 같다.

    String name = request.getParameter("name");
    request.setCharacterEncoding("UTF-8");

올바른 순서는 다음과 같다.

    request.setCharacterEncoding("UTF-8");
    String name = request.getParameter("name");

즉, 이미 파라미터를 읽은 뒤에는 인코딩을 설정해도 효과가 없을 수 있다. ⚠️

## 17. HTTP 상태 코드 405

HTTP 상태 코드 405는 Method Not Allowed를 의미한다.

즉, 요청한 URL은 존재하지만 해당 HTTP method를 처리할 수 없을 때 발생한다.

예를 들어 form에서 POST 방식으로 요청했는데 서블릿에 `doPost()`가 구현되어 있지 않으면 405 에러가 발생할 수 있다.

    <form action="/login" method="post">

이 경우 서블릿에는 다음 메서드가 필요하다.

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // POST 요청 처리
    }

즉, 요청 방식과 서블릿의 처리 메서드가 맞아야 한다. 📌

## 18. 405 에러가 발생하는 대표 상황

405 에러가 발생하는 대표적인 상황은 다음과 같다.

- GET 요청을 보냈는데 `doGet()`이 없음
- POST 요청을 보냈는데 `doPost()`가 없음
- form의 method와 서블릿 처리 메서드가 맞지 않음
- 서버가 해당 HTTP method를 허용하지 않음

따라서 405 에러가 발생하면 먼저 요청 방식과 서블릿 메서드를 확인해야 한다. ✅

## 19. 전체 흐름 예시

HTML form과 서블릿 처리 흐름은 다음과 같다.

1. 사용자가 HTML form에 값 입력
2. submit 버튼 클릭
3. form의 `action` 경로로 요청 전송
4. form의 `method`에 따라 GET 또는 POST 요청 발생
5. 서블릿의 `doGet()` 또는 `doPost()` 실행
6. `request.getParameter()`로 파라미터 값 추출
7. 필요한 로직 처리
8. 응답 반환

즉, form 태그와 서블릿은 요청 경로와 요청 방식이 서로 맞아야 정상적으로 동작한다. 📌

## 20. 중요 포인트 📌

- `form` 태그는 사용자가 입력한 데이터를 서버로 전송할 때 사용한다.
- `action` 속성은 submit 시 웹 서버에서 처리할 JSP 또는 Servlet 경로를 지정한다.
- `method` 속성은 웹 브라우저에서 웹 서버로 요청하는 방법을 지정한다.
- GET 방식은 요청 파라미터가 URL에 표시된다.
- GET 요청은 서블릿의 `doGet()` 메서드가 처리한다.
- GET 방식은 파라미터 길이에 제한이 있고 보안에 취약하다.
- 일반 링크와 명시적 URL 요청은 GET 방식이다.
- POST 방식은 요청 파라미터가 HTTP Request Body에 포함된다.
- POST 요청은 서블릿의 `doPost()` 메서드가 처리한다.
- POST 요청 새로고침 시 재요청 확인창이 나타날 수 있다.
- `getParameter(name)`은 하나의 파라미터 값을 추출한다.
- 해당 name의 파라미터가 없으면 `null`을 리턴한다.
- `getParameterValues(name)`은 하나의 name에 여러 값이 있을 때 배열로 리턴한다.
- `getParameterNames()`는 모든 파라미터 name 목록을 `Enumeration`으로 리턴한다.
- GET 방식은 URL을 UTF-8로 인식하므로 보통 별도 한글 인코딩 지정이 필요 없다.
- POST 방식은 body에 한글이 포함되면 `request.setCharacterEncoding("UTF-8")`이 필요하다.
- `request.setCharacterEncoding("UTF-8")`은 `getParameter()` 호출 전에 실행해야 한다.
- HTTP 405는 요청 method를 처리할 수 없을 때 발생한다.

## 정리 ✅

HTML5 Form 태그는 사용자가 입력한 데이터를 서버로 전송하기 위한 태그이며, `action` 속성으로 처리할 JSP 또는 Servlet 경로를 지정하고 `method` 속성으로 GET 또는 POST 요청 방식을 지정한다.  
GET 방식은 요청 파라미터가 URL에 표시되고 `doGet()`에서 처리되며, 링크 요청이나 조회 요청에 많이 사용된다.  
POST 방식은 요청 파라미터가 HTTP Request Body에 포함되고 `doPost()`에서 처리되며, 로그인이나 등록 요청처럼 데이터를 서버로 전송하는 작업에 많이 사용된다.  
서블릿에서는 `request.getParameter()`, `getParameterValues()`, `getParameterNames()`를 이용해 요청 파라미터 값을 추출할 수 있다.  
POST 요청에서 한글이 포함된 경우 `getParameter()` 호출 전에 `request.setCharacterEncoding("UTF-8")`을 실행해야 하며, 요청 방식과 서블릿 처리 메서드가 맞지 않으면 HTTP 405 에러가 발생할 수 있다.
