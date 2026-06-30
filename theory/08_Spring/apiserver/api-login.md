# ✨ API 로그인

## 1. API 로그인 개념

API 로그인은 JSP 화면 기반 로그인과 다르게, 클라이언트가 JSON 형식으로 로그인 정보를 전송하고 서버가 인증 결과를 JSON으로 응답하는 방식이다.

일반적인 웹 로그인은 로그인 성공 후 페이지 이동이나 세션 기반 처리를 많이 사용한다.  
반면 API 서버에서는 로그인 성공 시 JWT 토큰과 사용자 정보를 JSON으로 응답하고, 이후 요청에서 클라이언트가 토큰을 함께 전송한다. 📌

즉, API 로그인은 화면 이동보다 JSON 요청과 JSON 응답이 핵심이다.

## 2. LoginDTO

`LoginDTO`는 로그인 요청 데이터를 담는 DTO이다.

Spring Security 규약에 따라 다음 프로퍼티를 가진다.

- `username`
- `password`

예시는 다음과 같다.

    public class LoginDTO {
        private String username;
        private String password;
    }

클라이언트는 요청 body에 JSON 문자열로 로그인 정보를 전송한다.

    {
      "username": "user01",
      "password": "1234"
    }

서버에서는 이 JSON 문자열을 `LoginDTO` 객체로 역직렬화한다. ✅

## 3. Jackson을 이용한 역직렬화

API 로그인에서는 request body의 JSON 문자열을 직접 읽어 `LoginDTO` 객체로 변환해야 한다.

이때 Jackson 라이브러리를 사용한다.

Jackson은 JSON 문자열을 Java 객체로 변환하거나, Java 객체를 JSON 문자열로 변환하는 라이브러리이다.

즉, 로그인 요청에서는 다음 방향으로 동작한다.

    JSON 문자열 -> LoginDTO 객체

예를 들면 필터에서 `ObjectMapper`를 사용해 다음처럼 처리할 수 있다.

    LoginDTO loginDTO = objectMapper.readValue(
        request.getInputStream(),
        LoginDTO.class
    );

## 4. UserInfoDTO

`UserInfoDTO`는 로그인 성공 시 응답에 포함시킬 사용자 정보를 담는 DTO이다.

필기 기준으로 다음 정보를 포함한다.

- `token`
- `user`
  - `username`
  - `email`
  - `roles`

예를 들면 응답 구조는 다음과 같이 만들 수 있다.

    {
      "token": "jwt-token-value",
      "user": {
        "username": "user01",
        "email": "user01@test.com",
        "roles": ["ROLE_USER"]
      }
    }

즉, `UserInfoDTO`는 로그인 성공 후 클라이언트가 사용할 토큰과 사용자 기본 정보를 전달하기 위한 객체이다. 📌

## 5. AuthResultDTO

`AuthResultDTO`는 로그인 성공 결과를 나타내는 응답 DTO이다.

필기 기준으로 인증 token과 `UserInfoDTO`로 구성된다.

즉, 로그인 성공 응답 전체를 표현하는 객체이다.

예시는 다음과 같다.

    public class AuthResultDTO {
        private String token;
        private UserInfoDTO user;
    }

또는 `UserInfoDTO` 내부에 token을 포함시키는 구조로 설계할 수도 있다.

중요한 점은 로그인 성공 응답에 인증 토큰과 사용자 정보가 함께 포함된다는 것이다. ✅

## 6. JsonResponse

`JsonResponse`는 로그인 결과를 필터에서 직접 JSON으로 응답하기 위한 유틸리티 클래스이다.

Spring MVC Controller에서는 객체를 반환하면 Jackson이 자동으로 JSON 응답을 만들어 준다.  
하지만 필터에서는 컨트롤러를 거치지 않고 직접 `HttpServletResponse`에 응답을 써야 하는 경우가 있다.

이때 `JsonResponse` 같은 유틸리티 클래스를 만들어 JSON 응답 처리를 공통화할 수 있다. 📌

## 7. JsonResponse.send()

`send()` 메서드는 정상 응답을 JSON으로 전송할 때 사용한다.

형식은 다음과 같다.

    static <T> void send(HttpServletResponse response, T result) throws IOException

역할은 다음과 같다.

1. 응답 Content-Type을 JSON으로 설정한다.
2. Jackson으로 `result` 객체를 JSON 문자열로 직렬화한다.
3. `response` 출력 스트림 또는 writer로 직접 전송한다.

즉, 필터에서 로그인 성공 결과 객체를 JSON 응답으로 내려줄 때 사용한다. ✅

## 8. JsonResponse.sendError()

`sendError()` 메서드는 에러 응답을 JSON으로 전송할 때 사용한다.

형식은 다음과 같다.

    static void sendError(HttpServletResponse response, HttpStatus status, String message) throws IOException

필기에는 `Httpstatus`로 적혀 있지만, 정확한 타입명은 `HttpStatus`이다.

역할은 다음과 같다.

1. 응답 상태 코드를 설정한다.
2. 에러 메시지를 JSON 형태로 구성한다.
3. Jackson으로 직렬화한 뒤 response로 직접 전송한다.

즉, 로그인 실패나 인증 오류가 발생했을 때 JSON 에러 응답을 내려주는 역할을 한다. 📌

## 9. API를 통한 로그인 절차

API 로그인 절차는 일반적으로 필터를 통해 처리한다.

필기 기준 핵심 필터는 다음과 같다.

    JwtUsernamePasswordAuthenticationFilter

이 필터는 로그인 URL과 로그인 성공/실패 처리기를 등록한다.

즉, 로그인 요청이 들어오면 해당 필터가 요청 body를 읽고 인증 처리를 시작한다. ✅

## 10. JwtUsernamePasswordAuthenticationFilter

`JwtUsernamePasswordAuthenticationFilter`는 JWT 기반 API 로그인을 처리하기 위한 커스텀 필터이다.

주요 역할은 다음과 같다.

- 로그인 URL 감시
- 요청 body에서 로그인 정보 추출
- `UsernamePasswordAuthenticationToken` 생성
- 인증 매니저에게 인증 요청 전달
- 로그인 성공 시 JWT 발급
- 로그인 실패 시 JSON 에러 응답

즉, 기존 폼 로그인 방식 대신 JSON 기반 로그인 요청을 처리하도록 만든 필터이다. 📌

## 11. 로그인 URL 등록

`JwtUsernamePasswordAuthenticationFilter`는 특정 로그인 URL로 들어오는 요청을 처리하도록 설정한다.

예를 들어 다음과 같은 URL을 로그인 요청으로 사용할 수 있다.

    POST /api/auth/login

또는 프로젝트 설정에 따라 다음처럼 사용할 수도 있다.

    POST /api/login

중요한 점은 필터가 처리할 로그인 URL을 명확히 등록해야 한다는 것이다.

## 12. 로그인 성공/실패 처리기

API 로그인에서는 로그인 성공이나 실패 시 페이지로 이동하지 않는다.

대신 JSON 응답을 직접 내려준다.

따라서 필터에는 로그인 성공 처리기와 실패 처리기를 등록할 수 있다.

- 성공 처리기 → JWT 토큰 생성 후 사용자 정보와 함께 JSON 응답
- 실패 처리기 → 상태 코드와 에러 메시지를 JSON 응답

즉, API 서버에서는 redirect가 아니라 JSON 응답 중심으로 로그인 결과를 처리한다. ✅

## 13. body에서 LoginDTO 추출

API 로그인 절차의 첫 번째 단계는 요청 body에서 `LoginDTO`를 추출하는 것이다.

클라이언트는 다음처럼 JSON body를 전송한다.

    {
      "username": "user01",
      "password": "1234"
    }

필터에서는 request body를 읽고 Jackson을 이용해 `LoginDTO` 객체로 변환한다.

    LoginDTO loginDTO = objectMapper.readValue(
        request.getInputStream(),
        LoginDTO.class
    );

이렇게 변환된 `LoginDTO`에서 username과 password를 꺼내 인증 토큰을 만든다. 📌

## 14. UsernamePasswordAuthenticationToken 준비

API 로그인 절차의 두 번째 단계는 `UsernamePasswordAuthenticationToken`을 준비하는 것이다.

Spring Security에서는 아이디와 비밀번호 인증 요청을 표현할 때 `UsernamePasswordAuthenticationToken`을 사용한다.

예시는 다음과 같다.

    UsernamePasswordAuthenticationToken authToken =
        new UsernamePasswordAuthenticationToken(
            loginDTO.getUsername(),
            loginDTO.getPassword()
        );

이 객체는 아직 인증이 완료된 객체가 아니라, 인증을 요청하기 위한 객체이다.

즉, 사용자가 입력한 username과 password를 Spring Security 인증 시스템에 전달하기 위한 토큰이다. ✅

## 15. AuthenticationManager로 인증 요청

`UsernamePasswordAuthenticationToken`을 만든 뒤에는 `AuthenticationManager`에게 인증을 요청한다.

개념적으로는 다음과 같다.

    Authentication authentication =
        authenticationManager.authenticate(authToken);

`AuthenticationManager`는 내부적으로 `AuthenticationProvider`와 `UserDetailsService` 등을 이용해 사용자 정보를 확인한다.

인증에 성공하면 인증된 `Authentication` 객체가 반환된다.  
인증에 실패하면 예외가 발생하고 실패 처리기가 실행된다. 📌

## 16. 로그인 성공 시 처리

로그인에 성공하면 서버는 JWT 토큰을 생성한다.

흐름은 다음과 같다.

1. 인증된 사용자 정보 획득
2. username 또는 사용자 PK를 subject로 사용
3. JWT 토큰 생성
4. 사용자 정보 DTO 구성
5. `AuthResultDTO` 또는 `UserInfoDTO` 구성
6. `JsonResponse.send()`로 JSON 응답 전송

즉, 로그인 성공 시 클라이언트는 JWT 토큰과 사용자 정보를 받는다. ✅

## 17. 로그인 실패 시 처리

로그인에 실패하면 JSON 형태의 에러 응답을 반환한다.

예를 들어 아이디나 비밀번호가 잘못된 경우 다음과 같은 응답을 줄 수 있다.

    {
      "message": "아이디 또는 비밀번호가 올바르지 않습니다."
    }

이때 `JsonResponse.sendError()`를 사용해 상태 코드와 메시지를 응답할 수 있다.

예를 들면 다음과 같다.

    JsonResponse.sendError(
        response,
        HttpStatus.UNAUTHORIZED,
        "로그인 실패"
    );

즉, API 로그인 실패는 로그인 페이지 이동이 아니라 401 상태 코드와 JSON 메시지로 처리하는 것이 일반적이다. 📌

## 18. API 로그인 전체 흐름

API 로그인 전체 흐름은 다음과 같다.

1. 클라이언트가 로그인 URL로 POST 요청 전송
2. 요청 body에 username, password JSON 포함
3. `JwtUsernamePasswordAuthenticationFilter`가 요청 처리
4. Jackson으로 JSON body를 `LoginDTO`로 역직렬화
5. `UsernamePasswordAuthenticationToken` 생성
6. `AuthenticationManager`에 인증 요청
7. 인증 성공 시 JWT 토큰 생성
8. `UserInfoDTO`, `AuthResultDTO` 구성
9. `JsonResponse.send()`로 성공 JSON 응답
10. 인증 실패 시 `JsonResponse.sendError()`로 에러 JSON 응답

즉, API 로그인은 필터에서 요청을 가로채고, Spring Security 인증 시스템을 거친 뒤 JSON 응답을 직접 작성하는 방식이다. ✅

## 19. 중요 포인트 📌

- API 로그인은 JSON 요청과 JSON 응답을 기반으로 한다.
- `LoginDTO`는 Spring Security 규약에 따라 `username`, `password` 프로퍼티를 가진다.
- Request body의 JSON 문자열은 Jackson을 이용해 `LoginDTO`로 역직렬화한다.
- `UserInfoDTO`는 로그인 성공 시 응답할 사용자 정보를 담는다.
- `UserInfoDTO`에는 token, username, email, roles 등을 포함할 수 있다.
- `AuthResultDTO`는 로그인 성공 결과를 나타내는 응답 객체이다.
- `JsonResponse`는 필터에서 직접 JSON 응답을 보내기 위한 유틸리티 클래스이다.
- `JsonResponse.send()`는 정상 결과 객체를 JSON으로 직렬화해 응답한다.
- `JsonResponse.sendError()`는 상태 코드와 에러 메시지를 JSON으로 응답한다.
- `JwtUsernamePasswordAuthenticationFilter`는 API 로그인 요청을 처리하는 커스텀 필터이다.
- 로그인 URL과 로그인 성공/실패 처리기를 필터에 등록한다.
- 로그인 절차에서는 body에서 `LoginDTO`를 추출한다.
- 로그인 정보로 `UsernamePasswordAuthenticationToken`을 생성한다.
- 인증 성공 시 JWT 토큰과 사용자 정보를 응답한다.
- 인증 실패 시 JSON 에러 응답을 반환한다.

## 정리 ✅

API 로그인은 사용자가 JSON body로 username과 password를 전송하면, 서버가 이를 `LoginDTO`로 역직렬화하고 Spring Security 인증 절차를 수행한 뒤 JWT 토큰과 사용자 정보를 JSON으로 응답하는 방식이다.  
`LoginDTO`는 Spring Security 규약에 맞게 `username`, `password` 프로퍼티를 가지며, Jackson을 이용해 request body의 JSON 문자열에서 객체로 변환된다.  
로그인 성공 시에는 token과 사용자 정보(username, email, roles)를 담은 `UserInfoDTO` 또는 `AuthResultDTO`를 구성하고, 필터에서 `JsonResponse.send()`를 통해 직접 JSON 응답을 전송한다.  
로그인 실패 시에는 `JsonResponse.sendError()`를 이용해 상태 코드와 에러 메시지를 반환한다.  
핵심 흐름은 `JwtUsernamePasswordAuthenticationFilter`가 로그인 URL 요청을 감시하고, body에서 `LoginDTO`를 추출한 뒤 `UsernamePasswordAuthenticationToken`을 생성해 인증 처리를 시작하는 것이다.
