# ✨ Api Server Security 기본 설정

## 1. API 서버 보안 설정의 개념

API 서버는 화면을 반환하는 일반 웹 서버와 다르게 JSON 데이터를 주고받는 구조로 동작한다.

따라서 Spring Security 설정도 일반 웹 애플리케이션과 다르게 구성해야 한다.  
특히 form 로그인, 세션 기반 인증, CSRF 보호 방식은 API 서버 구조와 맞지 않는 경우가 많다. 📌

API 서버에서는 보통 JWT 같은 토큰 기반 인증을 사용하므로, 서버가 로그인 상태를 세션에 저장하지 않는 stateless 구조로 설정한다.

## 2. API 서버를 위한 기본 Security 설정

API 서버를 위한 기본 Spring Security 설정에서 추가할 부분은 다음과 같다.

- CORS 허용
- CSRF 기능 비활성화
- formLogin 기능 비활성화
- session 생성 모드를 stateless 모드로 설정
- AuthenticationManager 빈 등록

이 설정들은 API 서버가 브라우저, 프론트엔드 애플리케이션, 모바일 앱 등과 JSON 기반으로 통신할 수 있도록 구성하는 데 필요하다. ✅

## 3. CORS 허용

CORS는 Cross Origin Resource Sharing의 약자이다.

브라우저는 보안 정책상 현재 페이지가 로드된 origin과 다른 origin으로 요청을 보낼 때 제한을 둔다.

예를 들어 프론트엔드 개발 서버와 백엔드 API 서버가 다음처럼 다를 수 있다.

    프론트엔드: http://localhost:5173
    백엔드 API: http://localhost:8080

이 경우 origin이 다르므로 브라우저에서 CORS 문제가 발생할 수 있다.

따라서 API 서버에서는 필요한 origin의 요청을 허용하도록 CORS 설정을 추가해야 한다. 📌

## 4. CORS 설정이 필요한 이유

API 서버는 보통 프론트엔드 서버와 분리되어 동작한다.

프론트엔드에서 API 서버로 요청을 보내려면 브라우저가 해당 요청을 허용해야 한다.

CORS 설정이 없으면 서버 코드가 정상이어도 브라우저에서 요청을 차단할 수 있다.

즉, CORS 허용은 프론트엔드와 백엔드가 분리된 구조에서 필수적인 설정이다. ✅

## 5. CSRF 기능 비활성화

CSRF는 Cross Site Request Forgery의 약자이다.

CSRF 보호는 세션 기반 웹 애플리케이션에서 사용자의 인증 정보를 악용한 요청 위조를 막기 위해 사용된다.

하지만 JWT 기반 API 서버에서는 일반적으로 서버가 세션을 사용하지 않고, 요청마다 Authorization 헤더의 토큰을 검증한다.

따라서 API 서버에서는 CSRF 기능을 비활성화하는 경우가 많다. 📌

## 6. CSRF를 비활성화하는 이유

API 서버는 보통 다음과 같은 특징을 가진다.

- JSON 요청과 응답 사용
- JWT 토큰 기반 인증 사용
- 서버 세션을 사용하지 않음
- 클라이언트가 Authorization 헤더에 토큰을 담아 요청

CSRF 보호는 주로 세션 쿠키 기반 인증에서 의미가 크다.

따라서 stateless한 API 서버에서는 CSRF를 비활성화하고, 대신 토큰 검증 방식으로 인증을 처리한다. ✅

## 7. formLogin 기능 비활성화

`formLogin`은 Spring Security가 제공하는 폼 기반 로그인 기능이다.

일반 웹 애플리케이션에서는 사용자가 로그인 페이지에서 아이디와 비밀번호를 입력하고, 서버가 세션을 생성하는 방식으로 동작한다.

하지만 API 서버에서는 HTML 로그인 화면이 아니라 JSON 로그인 요청을 처리한다.

따라서 기본 formLogin 기능은 비활성화한다. 📌

## 8. formLogin을 비활성화하는 이유

API 서버에서 로그인 요청은 보통 다음과 같은 형태이다.

    POST /api/login
    Content-Type: application/json

    {
      "username": "user01",
      "password": "1234"
    }

즉, 사용자가 form 화면에서 로그인하는 것이 아니라 클라이언트가 JSON body를 전송한다.

그래서 Spring Security의 기본 로그인 페이지나 formLogin 처리 방식은 API 서버 구조와 맞지 않는다.

## 9. SessionCreationPolicy.STATELESS

API 서버에서는 session 생성 모드를 stateless로 설정한다.

stateless는 서버가 클라이언트의 로그인 상태를 세션에 저장하지 않는다는 의미이다.

Spring Security에서는 다음과 같은 방식으로 설정할 수 있다.

    sessionCreationPolicy(SessionCreationPolicy.STATELESS)

이 설정을 적용하면 서버는 인증 상태를 세션에 저장하지 않고, 요청마다 토큰을 검증하는 방식으로 동작한다. ✅

## 10. Stateless 구조의 특징

Stateless 구조에서는 서버가 사용자의 상태를 저장하지 않는다.

대신 클라이언트가 매 요청마다 인증 정보를 함께 전송한다.

예를 들어 JWT 기반 인증에서는 클라이언트가 다음과 같은 헤더를 보낸다.

    Authorization: Bearer JWT토큰

서버는 요청을 받을 때마다 토큰을 검증하고, 유효한 토큰이면 인증된 사용자로 처리한다.

즉, stateless API 서버에서는 세션이 아니라 토큰이 인증 상태를 증명한다. 📌

## 11. AuthenticationManager 빈 등록

`AuthenticationManager`는 Spring Security에서 인증을 담당하는 핵심 객체이다.

로그인 요청이 들어왔을 때 username과 password를 검증하려면 `AuthenticationManager`가 필요하다.

API 로그인 필터나 인증 처리 로직에서 직접 `AuthenticationManager`를 사용해야 하는 경우, 이를 스프링 빈으로 등록해야 한다. ✅

## 12. AuthenticationManager가 필요한 이유

JWT 기반 API 로그인에서는 커스텀 필터가 JSON body에서 로그인 정보를 읽고 `UsernamePasswordAuthenticationToken`을 만든다.

그 다음 인증 처리를 위해 `AuthenticationManager`에 인증 요청을 전달한다.

흐름은 다음과 같다.

1. JSON body에서 username, password 추출
2. `UsernamePasswordAuthenticationToken` 생성
3. `AuthenticationManager`로 인증 요청
4. 인증 성공 시 JWT 발급
5. 인증 실패 시 에러 응답

즉, API 로그인에서도 실제 인증 처리는 Spring Security의 `AuthenticationManager`를 통해 수행된다. 📌

## 13. 기본 설정 예시

API 서버 보안 설정은 개념적으로 다음과 같이 구성할 수 있다.

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
            .csrf().disable()
            .formLogin().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

이 코드는 다음 설정을 포함한다.

- CORS 허용
- CSRF 비활성화
- formLogin 비활성화
- 세션 생성 정책 stateless 설정

실제 프로젝트에서는 여기에 JWT 필터, 인증/인가 URL 설정 등이 추가된다. ✅

## 14. API 서버 보안 설정 흐름

API 서버 보안 설정의 전체 흐름은 다음과 같다.

1. 프론트엔드 요청 허용을 위해 CORS 설정
2. 세션 기반 CSRF 보호가 필요하지 않으므로 CSRF 비활성화
3. HTML form 로그인 방식을 사용하지 않으므로 formLogin 비활성화
4. 서버 세션을 사용하지 않기 위해 stateless 설정
5. JSON 로그인 인증을 처리하기 위해 AuthenticationManager 빈 등록
6. JWT 발급 및 검증 필터 추가

즉, API 서버는 세션 기반 웹 보안 설정을 줄이고, 토큰 기반 인증 구조에 맞게 보안 설정을 구성한다. 📌

## 15. 중요 포인트 📌

- API 서버는 일반 웹 서버와 다르게 JSON 기반 요청과 응답을 처리한다.
- API 서버에서는 JWT 같은 토큰 기반 인증을 많이 사용한다.
- 프론트엔드와 백엔드 origin이 다르면 CORS 설정이 필요하다.
- CORS는 Cross Origin Resource Sharing의 약자이다.
- CSRF는 세션 기반 인증에서 주로 중요한 보안 기능이다.
- JWT 기반 stateless API 서버에서는 CSRF를 비활성화하는 경우가 많다.
- API 서버는 HTML 로그인 페이지를 사용하지 않으므로 formLogin을 비활성화한다.
- session 생성 모드는 stateless로 설정한다.
- Stateless 구조에서는 서버가 로그인 상태를 세션에 저장하지 않는다.
- 클라이언트는 매 요청마다 JWT 토큰을 함께 전송한다.
- 서버는 요청마다 토큰을 검증한다.
- AuthenticationManager는 인증을 담당하는 핵심 객체이다.
- API 로그인 필터에서 AuthenticationManager를 사용하려면 빈 등록이 필요하다.

## 정리 ✅

Api Server Security 기본 설정에서는 CORS 허용, CSRF 비활성화, formLogin 비활성화, session stateless 설정, AuthenticationManager 빈 등록이 핵심이다.  
API 서버는 보통 프론트엔드와 분리되어 동작하므로 CORS 설정이 필요하고, JWT 기반 인증을 사용하면 서버 세션을 사용하지 않기 때문에 CSRF와 formLogin 기능은 비활성화하는 경우가 많다.  
또한 세션 생성 모드를 `SessionCreationPolicy.STATELESS`로 설정하여 서버가 로그인 상태를 저장하지 않도록 한다.  
로그인 요청에서는 JSON body에서 username과 password를 추출한 뒤 `AuthenticationManager`를 통해 인증을 수행하므로, API 로그인 구조에서는 `AuthenticationManager`를 빈으로 등록하는 설정이 필요하다.
