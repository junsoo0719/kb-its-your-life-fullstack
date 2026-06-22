# ✨ 화면 처리

## 1. 화면 처리의 개념

화면 처리는 사용자가 보는 JSP 화면, 정적 파일, 공통 레이아웃, 요청 이동 방식 등을 구성하는 작업이다.

게시판 프로젝트에서는 목록, 상세, 등록, 수정, 삭제 화면을 만들면서 공통 UI를 분리하고, CSS/JS 같은 정적 파일을 연결한다.

즉, 화면 처리는 단순히 JSP를 만드는 것뿐만 아니라 정적 리소스 관리, 공통 레이아웃 분리, redirect 처리, 삭제 요청 방식까지 포함한다. 📌

## 2. resources 폴더

`resources/` 폴더는 정적 파일을 구성하는 위치이다.

대표적인 정적 파일은 다음과 같다.

- CSS 파일
- 이미지 파일
- JavaScript 파일

예시는 다음과 같다.

    resources/
    ├── css/main.css
    ├── images/background.jsp
    └── js/main.js

정적 파일은 서버에서 처리 로직을 거치는 것이 아니라 브라우저가 직접 받아서 사용하는 파일이다.

## 3. 정적 파일 종류

정적 파일의 역할은 다음과 같다.

- `css/main.css` → 화면 스타일 지정
- `images/background.jsp` → 화면에서 사용하는 이미지 파일
- `js/main.js` → 화면 동작을 담당하는 JavaScript 파일

필기에는 `background.jsp`로 적혀 있지만, 일반적으로 이미지 파일이라면 `.jpg`, `.png`, `.gif` 같은 확장자를 사용한다.  
정확한 파일명이 `background.jsp`인지, `background.jpg`인지 프로젝트 파일을 확인하는 것이 좋다. ⚠️

## 4. views/layouts 폴더

`views/layouts` 폴더는 각 페이지의 공통 부분을 독립 파일로 정의하는 위치이다.

게시판 화면에서는 여러 JSP가 공통으로 사용하는 부분이 있다.

예를 들어 다음과 같다.

- 상단 영역
- 메뉴 영역
- 하단 영역

이런 공통 부분을 각 JSP마다 반복해서 작성하면 코드 중복이 많아진다.  
따라서 공통 부분을 별도 파일로 분리하고, 각 페이지에서 include해서 사용한다. ✅

## 5. 공통 레이아웃 파일

공통 레이아웃 파일 구성 예시는 다음과 같다.

    views/layouts/
    ├── header.jsp
    ├── menu.jsp
    └── footer.jsp

각 파일의 역할은 다음과 같다.

- `header.jsp` → HTML head, CSS 링크, 상단 공통 코드
- `menu.jsp` → 내비게이션 메뉴
- `footer.jsp` → 하단 공통 영역, JS 링크

즉, 화면 전체에서 반복되는 코드를 분리해 재사용할 수 있다. 📌

## 6. include 사용

각 JSP에서는 공통 레이아웃 파일을 include해서 사용할 수 있다.

예시는 다음과 같다.

    <%@ include file="../layouts/header.jsp" %>
    <%@ include file="../layouts/menu.jsp" %>

    <!-- 페이지별 내용 -->

    <%@ include file="../layouts/footer.jsp" %>

이렇게 하면 페이지마다 공통 코드를 반복해서 작성하지 않아도 된다.

즉, include는 JSP 화면의 코드 중복을 줄이기 위한 방법이다. ✅

## 7. 정적 파일 캐싱

브라우저는 CSS, JS, 이미지 같은 정적 파일을 캐싱한다.

캐싱은 한 번 받은 파일을 브라우저가 저장해 두었다가 다음 요청 때 다시 사용하는 기능이다.

장점은 페이지 로딩 속도가 빨라진다는 것이다.

하지만 개발 중에는 문제가 될 수 있다.

예를 들어 서버에서 `main.css`를 수정했는데 브라우저가 예전 CSS 파일을 캐시에서 사용하면 변경 사항이 화면에 반영되지 않는다. ⚠️

## 8. 정적 파일 캐싱 차단

개발 중에 CSS 파일을 수정해도 반영되지 않는 현상이 발생하면 브라우저 캐시를 끄거나 비워야 한다.

대표적인 방법은 다음과 같다.

- 브라우저 개발자 도구에서 Disable cache 사용
- 강력 새로고침 사용
- 정적 파일 URL에 버전 파라미터 추가
- 서버 설정에서 캐시 제어

예를 들어 CSS 링크에 버전 값을 붙이면 브라우저가 새 파일로 인식할 수 있다.

    <link rel="stylesheet" href="/resources/css/main.css?v=1">

즉, 개발 중에는 캐시 때문에 변경 사항이 바로 보이지 않을 수 있다는 점을 기억해야 한다. 📌

## 9. Tomcat 한글 로그 처리

Tomcat 로그에서 한글이 깨지는 경우 인코딩 설정을 추가할 수 있다.

필기 기준 설정은 다음과 같다.

    -Dfile.encoding=UTF-8

이 옵션은 JVM 실행 시 파일 인코딩을 UTF-8로 지정하는 설정이다.

즉, Tomcat 실행 환경에서 한글 로그가 깨질 때 UTF-8 인코딩을 지정해 해결할 수 있다. ✅

## 10. HomeController

`HomeController`는 기본 홈페이지 요청을 처리하는 컨트롤러이다.

개발 편의를 위해 홈페이지를 임시로 게시판 목록 페이지로 redirect할 수 있다.

예시는 다음과 같다.

    @GetMapping("/")
    public String home() {
        return "redirect:/board/list";
    }

이렇게 하면 기본 주소로 접속했을 때 게시판 목록 페이지로 이동한다.

즉, 개발 중에는 메인 페이지를 따로 만들지 않고 게시판 목록으로 바로 이동하게 할 수 있다. 📌

## 11. 재전송 redirect 처리

redirect는 서버가 브라우저에게 다른 URL로 다시 요청하라고 응답하는 방식이다.

예를 들어 다음과 같다.

    return "redirect:/board/list";

이 경우 서버는 브라우저에게 `/board/list`로 다시 요청하라고 알려준다.

브라우저는 내부적으로 연속 작업으로 처리하므로 사용자는 보통 이 과정을 직접 인지하지 못한다.

## 12. redirect 상태 코드

redirect는 보통 3xx 상태 코드를 사용한다.

필기에는 상태코드가 `20x`로 적혀 있지만, redirect는 일반 성공 응답인 `2xx`가 아니라 `3xx` 계열 상태 코드로 처리된다. ⚠️

대표적으로 다음 상태 코드가 있다.

- `302 Found`
- `303 See Other`
- `307 Temporary Redirect`
- `308 Permanent Redirect`

즉, redirect는 서버가 요청 처리를 끝낸 뒤 브라우저에게 다른 URL로 이동하도록 지시하는 응답이다.

## 13. 삭제 처리의 문제점

삭제 버튼을 다음처럼 `<a>` 태그로 만들 수 있다.

    <a href="delete?no=${board.no}" class="btn btn-primary">
        <i class="fas fa-trash-alt"></i> 삭제
    </a>

하지만 `<a>` 태그를 클릭하면 GET 요청이 전송된다.

즉, 위 코드는 다음과 같은 요청을 보낸다.

    GET /board/delete?no=글번호

문제는 컨트롤러에서 삭제 처리를 `@PostMapping`으로 설정했다면 GET 요청으로는 삭제가 실행되지 않는다는 것이다. 📌

## 14. 삭제는 POST 요청으로 처리

삭제는 데이터를 변경하는 작업이다.

따라서 단순 링크인 GET 요청보다 POST 요청으로 처리하는 것이 적절하다.

컨트롤러가 다음처럼 되어 있다면

    @PostMapping("/delete")
    public String delete(@RequestParam Long no) {
        service.delete(no);
        return "redirect:/board/list";
    }

화면에서도 POST 요청을 보내야 한다.

즉, `<a>` 태그가 아니라 form을 이용해야 한다. ✅

## 15. 숨겨진 form을 이용한 삭제

삭제 버튼은 화면에서는 버튼처럼 보이게 만들고, 실제 요청은 숨겨진 form으로 POST 전송할 수 있다.

예시는 다음과 같다.

    <form id="deleteForm" action="/board/delete" method="post" style="display:none;">
        <input type="hidden" name="no" value="${board.no}">
    </form>

    <button type="button" id="deleteBtn" class="btn btn-primary">
        <i class="fas fa-trash-alt"></i> 삭제
    </button>

이렇게 하면 삭제할 글 번호를 hidden input으로 전달할 수 있다.

## 16. JavaScript로 삭제 확인 처리

삭제 전에는 정말 삭제할지 사용자에게 확인해야 한다.

사용자가 “예”를 선택했을 때만 실제 POST 요청을 전송한다.

예시는 다음과 같다.

    <script>
        document.querySelector("#deleteBtn").addEventListener("click", function () {
            if (confirm("정말 삭제하시겠습니까?")) {
                document.querySelector("#deleteForm").submit();
            }
        });
    </script>

이 코드는 삭제 버튼을 클릭했을 때 확인 창을 띄우고, 사용자가 확인하면 숨겨진 form을 제출한다.

즉, 삭제 전 확인과 POST 요청 전송은 JavaScript로 처리할 수 있다. ✅

## 17. 삭제 처리 흐름

삭제 처리 흐름은 다음과 같다.

1. 사용자가 삭제 버튼 클릭
2. JavaScript에서 confirm 창 표시
3. 사용자가 취소하면 아무 작업도 하지 않음
4. 사용자가 확인하면 hidden form submit
5. POST `/board/delete` 요청 전송
6. 컨트롤러에서 `service.delete()` 호출
7. 삭제 후 `/board/list`로 redirect

즉, 삭제는 사용자 확인 후 POST 요청으로 안전하게 처리해야 한다. 📌

## 18. 중요 포인트 📌

- `resources/` 폴더에는 CSS, 이미지, JS 같은 정적 파일을 둔다.
- `views/layouts`에는 공통 JSP 조각 파일을 둔다.
- `header.jsp`, `menu.jsp`, `footer.jsp`를 include해서 코드 중복을 줄일 수 있다.
- 브라우저는 정적 파일을 캐싱하므로 CSS 변경 사항이 바로 반영되지 않을 수 있다.
- 개발 중에는 캐시를 끄거나 강력 새로고침을 사용할 수 있다.
- Tomcat 한글 로그가 깨질 때 `-Dfile.encoding=UTF-8` 설정을 사용할 수 있다.
- HomeController에서 개발 편의를 위해 홈페이지를 게시판 목록으로 redirect할 수 있다.
- redirect는 브라우저가 다른 URL로 다시 요청하게 만드는 방식이다.
- redirect 상태 코드는 일반적으로 3xx 계열이다.
- `<a>` 태그 요청은 GET 요청이다.
- 삭제 컨트롤러가 `@PostMapping`이면 GET 링크로는 삭제 요청을 보낼 수 없다.
- 삭제는 숨겨진 form을 이용해 POST 요청으로 처리할 수 있다.
- 삭제 전 확인은 JavaScript `confirm()`으로 처리할 수 있다.

## 정리 ✅

화면 처리에서는 정적 파일 구성, 공통 레이아웃 분리, 캐시 문제, redirect 처리, 삭제 요청 방식이 핵심이다.  
`resources/`에는 CSS, 이미지, JS 같은 정적 파일을 두고, `views/layouts`에는 `header.jsp`, `menu.jsp`, `footer.jsp`처럼 각 페이지에서 공통으로 사용하는 JSP를 분리한다.  
브라우저는 정적 파일을 캐싱하므로 개발 중 CSS 수정이 바로 반영되지 않을 수 있고, Tomcat 한글 로그 처리를 위해 `-Dfile.encoding=UTF-8` 설정을 사용할 수 있다.  
`HomeController`는 개발 편의를 위해 기본 페이지를 게시판 목록으로 redirect할 수 있으며, redirect는 일반적으로 3xx 상태 코드로 처리된다.  
삭제 기능은 `<a>` 태그로 만들면 GET 요청이 되므로, `@PostMapping` 컨트롤러와 맞추기 위해 숨겨진 form과 JavaScript를 이용해 POST 요청으로 전송해야 한다.
