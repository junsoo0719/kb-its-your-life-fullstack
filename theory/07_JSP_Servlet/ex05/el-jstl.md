# ✨ EL, JSTL

## 1. EL 개념

EL은 Expression Language의 약자이다.

JSP에서 데이터를 출력하기 위한 표현 언어이다.

기존 JSP에서는 값을 출력할 때 다음과 같이 표현식을 사용했다.

    <%= name %>

EL을 사용하면 더 간단하고 직관적으로 값을 출력할 수 있다.

    ${name}

즉, EL은 JSP에서 데이터를 더 쉽고 깔끔하게 출력하기 위한 문법이다. 📌

## 2. EL을 사용하는 이유

EL을 사용하면 JSP 페이지에서 Java 코드를 직접 작성하는 양을 줄일 수 있다.

기존 JSP 표현식은 Java 코드와 HTML 코드가 섞이기 쉬웠다.

EL은 다음과 같은 장점이 있다.

- 문법이 직관적이다.
- 사용하기 쉽다.
- JSP에서 변수나 속성 값을 간단히 출력할 수 있다.
- null 처리를 편하게 할 수 있다.
- Map, List, 배열, 자바빈 객체의 값에 접근할 수 있다.

즉, EL은 JSP 화면에서 데이터를 출력하는 코드를 더 간결하게 만들어 준다. ✅

## 3. EL 기본 문법

EL의 기본 문법은 다음과 같다.

    ${표현식}

표현식 안에는 내장 객체나 scope에 저장된 속성명을 지정할 수 있다.

예를 들어 request scope에 `name`이라는 값이 저장되어 있다면 JSP에서 다음처럼 출력할 수 있다.

    ${name}

또는 명확하게 request scope를 지정할 수도 있다.

    ${requestScope.name}

즉, EL은 scope에 저장된 데이터를 찾아 출력한다. 📌

## 4. JSP 표현식과 EL의 차이

기존 JSP 표현식은 JSP 안의 Java 변수나 Java 표현식을 출력할 때 사용한다.

    <%= name %>

반면 EL은 page, request, session, application 같은 scope에 저장된 속성 값을 출력할 때 사용한다.

    ${name}

즉, `<%= %>`는 JSP 변수나 Java 표현식 중심이고, `${}`는 scope에 저장된 속성 값 중심이다. ✅

## 5. null 처리

기존 JSP 표현식에서는 값이 null일 때 어떻게 처리할지 신경 써야 했다.

하지만 EL은 null 값을 별도로 처리하지 않아도 된다.

EL에서 값이 null이면 에러를 발생시키기보다 비어 있는 값으로 출력된다.

예를 들어 다음 값이 null이어도

    ${member.name}

화면에는 빈 값처럼 출력된다.

즉, EL은 JSP 화면 출력에서 null 처리 부담을 줄여준다. 📌

## 6. EL이 처리 가능한 데이터형

EL은 다양한 데이터형을 처리할 수 있다.

대표적으로 다음 데이터에 접근할 수 있다.

- 프리미티브 타입
- Map
- List
- 배열
- 자바빈 객체

즉, JSP에서 단순 값뿐만 아니라 객체, 컬렉션, 배열의 데이터도 쉽게 출력할 수 있다. ✅

## 7. EL 내장 객체

EL에는 미리 제공되는 내장 객체가 있다.

대표적인 EL 내장 객체는 다음과 같다.

- `pageScope`
- `requestScope`
- `sessionScope`
- `applicationScope`
- `param`
- `paramValues`
- `header`
- `headerValues`
- `cookie`
- `initParam`
- `pageContext`

이 내장 객체들을 사용하면 scope, 요청 파라미터, 헤더, 쿠키, 초기화 파라미터 등에 접근할 수 있다. 📌

## 8. Scope 관련 내장 객체

EL에서 scope에 접근할 때 사용하는 내장 객체는 다음과 같다.

- `pageScope`
- `requestScope`
- `sessionScope`
- `applicationScope`

예시는 다음과 같다.

    ${pageScope.name}
    ${requestScope.name}
    ${sessionScope.name}
    ${applicationScope.name}

각 객체는 해당 scope에 저장된 속성 값을 찾는다.

즉, 같은 이름의 속성이 여러 scope에 있을 때 명확하게 구분할 수 있다. ✅

## 9. param과 paramValues

`param`은 요청 파라미터 값을 가져올 때 사용한다.

예를 들어 URL이 다음과 같다고 하자.

    /member.jsp?name=kim

JSP에서는 다음처럼 출력할 수 있다.

    ${param.name}

`paramValues`는 같은 이름의 파라미터가 여러 개 전달될 때 사용한다.

예를 들어 체크박스처럼 여러 값이 전달되는 경우이다.

    ${paramValues.hobby[0]}

즉, `param`은 단일 요청 파라미터, `paramValues`는 여러 요청 파라미터 값을 처리할 때 사용한다. 📌

## 10. header와 headerValues

`header`는 HTTP 요청 헤더 값을 가져올 때 사용한다.

예시는 다음과 같다.

    ${header["User-Agent"]}

`headerValues`는 같은 이름의 헤더 값이 여러 개일 때 사용할 수 있다.

즉, 요청 헤더 정보를 JSP에서 확인할 때 사용하는 내장 객체이다.

## 11. cookie

`cookie`는 요청에 포함된 쿠키 정보를 가져올 때 사용한다.

예를 들어 `userId`라는 쿠키가 있다면 다음처럼 접근할 수 있다.

    ${cookie.userId.value}

즉, EL을 사용하면 쿠키 객체의 값도 간단히 출력할 수 있다. ✅

## 12. initParam

`initParam`은 웹 애플리케이션 초기화 파라미터에 접근할 때 사용한다.

초기화 파라미터는 보통 `web.xml` 등에 설정된 값을 의미한다.

예시는 다음과 같다.

    ${initParam.appName}

즉, 애플리케이션 설정 값을 JSP에서 출력할 때 사용할 수 있다.

## 13. pageContext

`pageContext`는 JSP의 `PageContext` 객체에 접근할 때 사용한다.

이를 통해 request, response, session, servletContext 같은 JSP 관련 객체에 접근할 수 있다.

예를 들어 context path를 얻을 때 다음처럼 사용할 수 있다.

    ${pageContext.request.contextPath}

이 표현식은 JSP에서 링크나 form action 경로를 작성할 때 자주 사용된다. 📌

## 14. EL 연산자

EL은 단순 출력뿐 아니라 여러 연산자도 사용할 수 있다.

대표적인 연산자는 다음과 같다.

- `.`
- `[]`
- `()`
- `empty`
- `+`, `-`, `*`, `/`, `%`
- `&&`, `||`, `!`
- `==`, `>`, `>=`, `<`, `<=`, `!=`

즉, EL 안에서도 객체 접근, 배열 접근, 산술 연산, 논리 연산, 비교 연산을 사용할 수 있다. ✅

## 15. . 연산자

`.` 연산자는 Map이나 자바빈 객체의 속성에 접근할 때 사용한다.

예를 들어 `member` 객체에 `name` 프로퍼티가 있다면 다음처럼 작성한다.

    ${member.name}

여기서 `name`은 다음 중 하나로 해석될 수 있다.

- Map의 key 값
- 자바빈의 프로퍼티

즉, `${member.name}`은 `member`에서 `name`이라는 값을 꺼내 출력한다. 📌

## 16. 자바빈 프로퍼티 접근

자바빈 객체가 지정된 경우 EL은 getter 메서드를 이용해 값을 가져온다.

예를 들어 다음과 같은 클래스가 있다고 하자.

    public class Member {
        private String name;

        public String getName() {
            return name;
        }
    }

JSP에서는 다음처럼 출력할 수 있다.

    ${member.name}

이때 EL은 내부적으로 `getName()`을 호출해 값을 가져온다.

즉, EL에서 말하는 프로퍼티는 필드 자체보다 getter/setter 규칙에 의해 결정된다. ✅

## 17. [] 배열 표기법

`[]` 표기법은 Map, 자바빈, List, 배열에 접근할 때 사용할 수 있다.

예시는 다음과 같다.

    ${member["name"]}

두 번째 값인 `"name"`은 다음 중 하나로 해석될 수 있다.

- Map의 key 값
- 자바빈의 프로퍼티
- List 계열의 인덱스
- 배열의 인덱스

즉, `[]` 표기법은 다양한 자료구조의 값에 접근할 수 있는 방식이다. 📌

## 18. . 표기법과 [] 표기법 비교

`.` 표기법과 `[]` 표기법은 비슷하게 사용할 수 있다.

    ${member.name}
    ${member["name"]}

두 표현식은 대부분 같은 결과를 낼 수 있다.

하지만 key 이름에 특수문자가 있거나 동적으로 key를 지정해야 하는 경우에는 `[]` 표기법이 더 유용하다.

예를 들어 다음처럼 사용할 수 있다.

    ${map["user-name"]}

즉, 일반적인 프로퍼티 접근은 `.`, 더 유연한 접근은 `[]`를 사용할 수 있다. ✅

## 19. empty 연산자

`empty`는 값이 비어 있는지 확인할 때 사용한다.

다음과 같은 경우 true가 될 수 있다.

- null
- 빈 문자열
- 비어 있는 배열
- 비어 있는 List
- 비어 있는 Map

예시는 다음과 같다.

    ${empty name}

또는 조건문과 함께 사용할 수 있다.

    ${empty member.name}

즉, `empty`는 값이 없거나 비어 있는지 확인할 때 유용하다. 📌

## 20. 산술 연산자

EL에서는 산술 연산자를 사용할 수 있다.

대표적인 산술 연산자는 다음과 같다.

- `+`
- `-`
- `*`
- `/`
- `%`

예시는 다음과 같다.

    ${10 + 20}
    ${price * count}
    ${total / 2}

즉, JSP 안에서 간단한 계산 결과를 출력할 수 있다.

## 21. 논리 연산자

EL에서는 논리 연산자도 사용할 수 있다.

대표적인 논리 연산자는 다음과 같다.

- `&&`
- `||`
- `!`

예시는 다음과 같다.

    ${age >= 20 && member}
    ${!empty name}

즉, 여러 조건을 조합하거나 부정 조건을 만들 수 있다. ✅

## 22. 비교 연산자

EL에서는 비교 연산자를 사용할 수 있다.

대표적인 비교 연산자는 다음과 같다.

- `==`
- `>`
- `>=`
- `<`
- `<=`
- `!=`

예시는 다음과 같다.

    ${age >= 20}
    ${name == "kim"}
    ${count != 0}

즉, 조건에 따라 값을 비교할 수 있다. 📌

## 23. JSTL 개념

JSTL은 JSP Standard Tag Library의 약자이다.

JSP에서 자주 사용하는 조건문, 반복문, 출력, 포맷 처리 등을 태그 형태로 제공하는 라이브러리이다.

EL이 값을 출력하거나 표현식을 작성하는 데 사용된다면, JSTL은 JSP에서 제어 흐름이나 반복 출력을 태그로 처리할 때 사용한다.

예를 들어 조건문과 반복문을 Java 코드 없이 작성할 수 있다.

    <c:if test="${not empty list}">
        데이터가 있습니다.
    </c:if>

    <c:forEach var="item" items="${list}">
        ${item}
    </c:forEach>

즉, EL과 JSTL을 함께 사용하면 JSP에서 Java 코드를 줄이고 화면 코드를 더 깔끔하게 작성할 수 있다. ✅

## 24. EL과 JSTL의 관계

EL과 JSTL은 JSP에서 함께 자주 사용된다.

EL은 데이터를 꺼내고 표현하는 역할을 한다.

JSTL은 조건문, 반복문 같은 태그 기반 제어를 담당한다.

예시는 다음과 같다.

    <c:forEach var="member" items="${memberList}">
        ${member.name}
    </c:forEach>

여기서 JSTL의 `<c:forEach>`는 반복을 담당하고, EL의 `${member.name}`은 데이터를 출력한다.

즉, JSTL은 흐름 제어, EL은 값 출력에 사용된다고 정리할 수 있다. 📌

## 25. 중요 포인트 📌

- EL은 데이터를 출력하기 위한 언어이다.
- EL은 JSP에서 변수를 출력할 때 사용한다.
- EL 기본 문법은 `${표현식}`이다.
- EL은 `<%= %>`보다 문법이 직관적이고 사용하기 쉽다.
- EL은 null 값을 별도로 처리하지 않아도 빈 값처럼 출력된다.
- EL은 프리미티브, Map, List, 배열, 자바빈 등을 처리할 수 있다.
- EL은 scope에 저장된 속성 값을 출력할 때 사용한다.
- `<%= %>`는 JSP 변수와 Java 표현식을 출력하는 방식이다.
- `pageScope`, `requestScope`, `sessionScope`, `applicationScope`는 scope 관련 내장 객체이다.
- `param`은 요청 파라미터 값을 가져올 때 사용한다.
- `paramValues`는 같은 이름의 파라미터가 여러 개일 때 사용한다.
- `header`, `headerValues`는 요청 헤더 값을 가져올 때 사용한다.
- `cookie`는 쿠키 정보에 접근할 때 사용한다.
- `initParam`은 초기화 파라미터에 접근할 때 사용한다.
- `pageContext`는 JSP의 PageContext 객체에 접근할 때 사용한다.
- `${member.name}`에서 두 번째 값은 Map의 key 또는 자바빈 프로퍼티이다.
- `${member["name"]}`은 Map, 자바빈, List, 배열에 사용할 수 있다.
- `empty`는 값이 비어 있는지 확인할 때 사용한다.
- JSTL은 JSP에서 조건문, 반복문 등을 태그로 처리하기 위한 라이브러리이다.
- EL과 JSTL을 함께 사용하면 JSP에서 Java 코드를 줄일 수 있다.

## 정리 ✅

EL은 JSP에서 데이터를 출력하기 위한 표현 언어이며, `${표현식}` 형태로 사용한다.  
기존 JSP 표현식 `<%= %>`와 달리 EL은 scope에 저장된 속성 값을 쉽게 출력할 수 있고, null 값을 별도로 처리하지 않아도 빈 값처럼 출력된다.  
EL은 프리미티브, Map, List, 배열, 자바빈 같은 다양한 데이터에 접근할 수 있으며, `.`, `[]`, `empty`, 산술 연산자, 논리 연산자, 비교 연산자 등을 사용할 수 있다.  
EL 내장 객체에는 `pageScope`, `requestScope`, `sessionScope`, `applicationScope`, `param`, `paramValues`, `header`, `cookie`, `initParam`, `pageContext` 등이 있다.  
JSTL은 JSP에서 조건문과 반복문 같은 제어 흐름을 태그로 작성하게 해 주는 라이브러리이며, EL과 함께 사용하면 JSP 안의 Java 코드를 줄이고 화면 코드를 더 깔끔하게 작성할 수 있다.
