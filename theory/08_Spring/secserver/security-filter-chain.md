# ✨ Security Filter Chain

## 1. Security Filter Chain 개념

Security Filter Chain은 스프링 시큐리티에서 HTTP 요청을 처리할 때 거쳐 가는 보안 필터들의 흐름이다.

클라이언트가 요청을 보내면 요청은 컨트롤러에 바로 도달하지 않고, 여러 보안 필터를 순서대로 거치면서 인증, 인가, 세션 처리, 예외 처리 등을 수행한다. 📌

즉, Security Filter Chain은 웹 요청에 보안 처리를 적용하는 필터들의 체인이다.

## 2. SecurityContext

`SecurityContext`는 현재 요청 또는 현재 사용자에 대한 인증 정보를 담는 객체이다.

주로 내부에 `Authentication` 객체를 가진다.

`Authentication` 객체에는 다음 정보가 포함될 수 있다.

- 인증된 사용자 정보
- 인증 여부
- 권한 정보
- 인증 방식

즉, SecurityContext는 “현재 사용자가 누구인지, 인증되었는지, 어떤 권한을 가지는지”를 저장하는 공간이다. ✅

## 3. SecurityContextHolder

`SecurityContextHolder`는 현재 실행 흐름에서 `SecurityContext`를 보관하는 역할을 한다.

스프링 시큐리티는 요청 처리 중 필요한 순간마다 `SecurityContextHolder`에서 인증 정보를 꺼내 사용한다.

즉, 현재 요청의 인증 상태를 전역처럼 접근할 수 있게 관리하는 저장소라고 이해할 수 있다. 📌

## 4. SecurityContextPersistenceFilter

`SecurityContextPersistenceFilter`는 request가 발생하면 `SecurityContext` 객체의 생성, 저장, 조회를 담당하는 필터이다.

요청이 시작되면 새로운 `SecurityContext`를 생성하여 `SecurityContextHolder`에 저장할 수 있다.

또한 인증이 완료된 후에는 세션에 `SecurityContext`를 저장하고, 다음 요청에서는 세션에서 다시 꺼내 인증 상태를 유지한다.

즉, 요청마다 인증 정보를 이어서 사용할 수 있도록 연결해 주는 필터이다. ✅

## 5. 익명 사용자의 SecurityContext 처리

로그인하지 않은 익명 사용자의 경우 `AnonymousAuthenticationFilter`에서 `AnonymousAuthenticationToken` 객체를 생성한다.

그리고 이 객체를 `SecurityContext`에 저장한다.

즉, 로그인하지 않은 사용자도 스프링 시큐리티 내부에서는 완전히 `null` 사용자로 두는 것이 아니라 익명 사용자 인증 객체로 관리할 수 있다. 📌

## 6. 인증 시 SecurityContext 처리

사용자가 로그인하면 `UsernamePasswordAuthenticationFilter`가 인증 요청을 처리한다.

인증에 성공하면 `UsernamePasswordAuthentication` 객체를 생성하고, 이를 `Authentication` 객체와 함께 `SecurityContext`에 저장한다.

이후 인증이 완료되면 세션에 `SecurityContext`를 저장하고 응답한다.

즉, 로그인 성공 후에는 인증 정보가 세션에 보관되어 다음 요청에서도 로그인 상태를 유지할 수 있다. ✅

## 7. 인증 후 SecurityContext 처리

인증이 끝난 뒤 다음 요청이 들어오면 스프링 시큐리티는 세션에서 `SecurityContext`를 꺼낸다.

그리고 이를 `SecurityContextHolder`에 저장한다.

만약 `SecurityContext` 안에 `Authentication` 객체가 있으면 인증이 유지된다.

즉, 매 요청마다 세션에서 인증 정보를 복원하여 로그인 상태를 유지한다. 📌

## 8. LogoutFilter

`LogoutFilter`는 사용자의 로그아웃을 처리하는 필터이다.

설정된 로그아웃 URL로 들어오는 요청을 감시하고, 해당 요청이 들어오면 사용자를 로그아웃 처리한다.

로그아웃 시에는 보통 다음 작업이 수행될 수 있다.

- 세션 무효화
- SecurityContext 제거
- 인증 정보 삭제
- 로그아웃 성공 후 이동 처리

즉, 로그아웃 요청을 감지하고 인증 상태를 제거하는 필터이다. ✅

## 9. UsernamePasswordAuthenticationFilter

`UsernamePasswordAuthenticationFilter`는 설정된 로그인 URL로 오는 요청을 감시하며 사용자 인증을 처리하는 필터이다.

일반적인 아이디/비밀번호 기반 로그인 요청을 처리한다.

인증이 실패하면 `AuthenticationFailureHandler`를 실행한다.

즉, 로그인 요청을 받아 인증 성공 또는 실패 처리를 담당하는 필터이다. 📌

## 10. AuthenticationFailureHandler

`AuthenticationFailureHandler`는 인증 실패 시 실행되는 처리 객체이다.

예를 들어 로그인 실패 시 다음 작업을 할 수 있다.

- 로그인 페이지로 다시 이동
- 실패 메시지 전달
- 실패 횟수 기록
- 에러 응답 반환

즉, `UsernamePasswordAuthenticationFilter`에서 인증이 실패했을 때 후속 처리를 담당한다.

## 11. DefaultLoginPageGenerationFilter

`DefaultLoginPageGenerationFilter`는 사용자가 별도의 로그인 페이지를 구현하지 않은 경우 기본 로그인 페이지를 생성하는 필터이다.

스프링 시큐리티를 적용했는데 커스텀 로그인 페이지를 설정하지 않으면 기본 로그인 페이지가 제공될 수 있다.

즉, 로그인 화면을 직접 만들지 않아도 기본 로그인 페이지가 동작하도록 도와준다. ✅

## 12. BasicAuthenticationFilter

`BasicAuthenticationFilter`는 HTTP 요청의 BASIC 인증 헤더를 처리하는 필터이다.

요청 헤더에 포함된 BASIC 인증 정보를 읽고 인증 결과를 `SecurityContextHolder`에 저장한다.

즉, HTTP Basic 인증 방식을 사용할 때 인증 정보를 처리하는 필터이다. 📌

## 13. RememberMeAuthenticationFilter

`RememberMeAuthenticationFilter`는 Remember-Me 인증을 처리하는 필터이다.

먼저 `SecurityContext`에 인증 객체가 있는지 확인한다.

인증 객체가 없고, `RememberMeServices`를 구현한 객체의 요청이 있을 경우 Remember-Me 인증 토큰을 만들어 컨텍스트에 주입한다.

즉, 세션이 없어도 Remember-Me 쿠키 등을 이용해 사용자를 다시 인증할 수 있게 한다. ✅

## 14. AnonymousAuthenticationFilter

`AnonymousAuthenticationFilter`는 `SecurityContextHolder`에 인증 객체가 있는지 확인한다.

만약 인증 객체가 없으면 필요한 경우 익명 사용자용 `Authentication` 객체를 주입한다.

즉, 로그인하지 않은 사용자도 익명 사용자로 처리할 수 있게 해 주는 필터이다. 📌

## 15. SessionManagementFilter

`SessionManagementFilter`는 요청이 시작된 이후 인증된 사용자인지 확인하고, 세션 관련 처리를 수행하는 필터이다.

인증된 사용자일 경우 `SessionAuthenticationStrategy`를 호출한다.

이를 통해 다음과 같은 세션 관련 활동을 수행할 수 있다.

- 세션 고정 보호 메커니즘 활성화
- 여러 동시 로그인 확인
- 세션 정책 적용

즉, 인증 이후 세션 보안과 세션 관리를 담당하는 필터이다. ✅

## 16. 세션 고정 보호

세션 고정 공격은 공격자가 미리 만든 세션 ID를 사용자가 로그인하도록 유도한 뒤, 그 세션을 이용해 인증 상태를 탈취하는 공격 방식이다.

스프링 시큐리티는 인증 이후 세션 ID를 변경하는 방식으로 세션 고정 공격을 방지할 수 있다.

이 처리는 `SessionManagementFilter`와 `SessionAuthenticationStrategy`와 관련된다. 📌

## 17. ExceptionTranslationFilter

`ExceptionTranslationFilter`는 필터 체인 내에서 발생하는 보안 예외를 처리하는 필터이다.

주로 다음 예외를 처리한다.

- `AccessDeniedException`
- `AuthenticationException`

즉, 인증되지 않은 사용자가 접근하거나 권한이 부족한 사용자가 접근했을 때 적절한 응답이나 페이지 이동을 처리한다. ✅

## 18. AuthenticationException

`AuthenticationException`은 인증과 관련된 예외이다.

예를 들어 로그인하지 않은 사용자가 인증이 필요한 페이지에 접근하면 발생할 수 있다.

이 경우 보통 로그인 페이지로 이동하거나 401 응답을 반환한다.

즉, “인증이 필요하다”는 상황을 나타내는 예외이다. 📌

## 19. AccessDeniedException

`AccessDeniedException`은 권한 부족과 관련된 예외이다.

예를 들어 로그인은 했지만 관리자 권한이 없는 사용자가 관리자 페이지에 접근하면 발생할 수 있다.

이 경우 보통 403 응답을 반환한다.

즉, “인증은 되었지만 권한이 부족하다”는 상황을 나타내는 예외이다. ⚠️

## 20. FilterSecurityInterceptor

`FilterSecurityInterceptor`는 HTTP 리소스의 보안 처리를 수행하는 필터이다.

요청한 URL에 대해 접근 권한이 있는지 최종적으로 확인한다.

예를 들어 `/admin/**` 요청은 관리자 권한이 있어야 한다는 규칙이 있다면, 이 필터가 현재 사용자의 권한과 비교하여 접근 가능 여부를 판단한다.

즉, URL 기반 인가 처리를 담당하는 핵심 필터이다. ✅

## 21. 보안 에러

스프링 시큐리티에서 자주 만나는 보안 에러는 다음 두 가지이다.

- `401 Unauthorized`
- `403 Forbidden`

두 에러는 비슷해 보이지만 의미가 다르다.

## 22. 401 에러

`401 Unauthorized`는 로그인 없이 접근한 경우 발생한다.

즉, 인증이 필요한 리소스에 인증되지 않은 사용자가 접근했을 때 발생한다.

예를 들어 로그인하지 않은 사용자가 `/board/create` 같은 보호된 페이지에 접근하는 경우이다.

정리하면 다음과 같다.

- 원인: 인증되지 않음
- 의미: 로그인 필요
- 예시: 로그인 없이 접근

## 23. 403 에러

`403 Forbidden`은 권한이 부족한 경우 발생한다.

즉, 사용자가 로그인은 했지만 해당 리소스에 접근할 권한이 없을 때 발생한다.

예를 들어 일반 사용자가 관리자 페이지에 접근하는 경우이다.

정리하면 다음과 같다.

- 원인: 권한 부족
- 의미: 접근 금지
- 예시: 일반 사용자가 관리자 기능 접근

## 24. Security Filter Chain 전체 흐름

Security Filter Chain의 흐름은 다음과 같이 이해할 수 있다.

1. 요청이 들어온다.
2. `SecurityContextPersistenceFilter`가 SecurityContext를 준비한다.
3. 로그인 요청이면 `UsernamePasswordAuthenticationFilter`가 인증을 처리한다.
4. 로그아웃 요청이면 `LogoutFilter`가 로그아웃을 처리한다.
5. BASIC 인증이면 `BasicAuthenticationFilter`가 인증 헤더를 처리한다.
6. Remember-Me가 있으면 `RememberMeAuthenticationFilter`가 인증을 복원한다.
7. 인증 객체가 없으면 `AnonymousAuthenticationFilter`가 익명 인증 객체를 넣을 수 있다.
8. 인증된 사용자라면 `SessionManagementFilter`가 세션 관련 처리를 수행한다.
9. 요청 처리 중 예외가 발생하면 `ExceptionTranslationFilter`가 보안 예외를 처리한다.
10. `FilterSecurityInterceptor`가 최종적으로 HTTP 리소스 접근 권한을 검사한다.

즉, 필터들은 요청을 단계별로 검사하며 인증과 인가를 처리한다. 📌

## 25. 중요 포인트 📌

- Security Filter Chain은 HTTP 요청에 보안 처리를 적용하는 필터들의 흐름이다.
- `SecurityContext`는 현재 사용자의 인증 정보를 담는다.
- `SecurityContextHolder`는 현재 요청에서 사용할 SecurityContext를 저장한다.
- `SecurityContextPersistenceFilter`는 SecurityContext의 생성, 저장, 조회를 담당한다.
- 인증 후에는 Session에 SecurityContext를 저장할 수 있다.
- 다음 요청에서는 Session에서 SecurityContext를 꺼내 인증을 유지한다.
- `LogoutFilter`는 설정된 로그아웃 URL 요청을 감시하고 로그아웃을 처리한다.
- `UsernamePasswordAuthenticationFilter`는 로그인 URL 요청을 감시하고 사용자 인증을 처리한다.
- 인증 실패 시 `AuthenticationFailureHandler`가 실행된다.
- `DefaultLoginPageGenerationFilter`는 기본 로그인 페이지를 처리한다.
- `BasicAuthenticationFilter`는 HTTP BASIC 인증 헤더를 처리한다.
- `RememberMeAuthenticationFilter`는 Remember-Me 인증 토큰을 컨텍스트에 주입할 수 있다.
- `AnonymousAuthenticationFilter`는 인증 객체가 없을 때 익명 인증 객체를 주입한다.
- `SessionManagementFilter`는 세션 고정 보호와 동시 로그인 확인 같은 세션 관련 처리를 수행한다.
- `ExceptionTranslationFilter`는 `AccessDeniedException`, `AuthenticationException`을 처리한다.
- `FilterSecurityInterceptor`는 HTTP 리소스의 보안 처리를 수행한다.
- 401 에러는 로그인 없이 접근한 경우 발생한다.
- 403 에러는 권한이 부족한 경우 발생한다.

## 정리 ✅

Security Filter Chain은 스프링 시큐리티가 HTTP 요청에 인증과 인가를 적용하기 위해 사용하는 필터들의 흐름이다.  
요청이 들어오면 `SecurityContextPersistenceFilter`가 `SecurityContext`를 준비하고, 로그인 요청은 `UsernamePasswordAuthenticationFilter`, 로그아웃 요청은 `LogoutFilter`가 처리한다.  
인증 정보가 없으면 `AnonymousAuthenticationFilter`가 익명 인증 객체를 넣을 수 있고, Remember-Me 요청이 있으면 `RememberMeAuthenticationFilter`가 인증을 복원할 수 있다.  
인증된 사용자는 `SessionManagementFilter`를 통해 세션 관련 처리가 이루어지며, 보안 예외는 `ExceptionTranslationFilter`가 처리한다.  
마지막으로 `FilterSecurityInterceptor`가 HTTP 리소스 접근 권한을 검사하고, 로그인하지 않은 접근은 401, 권한 부족은 403으로 구분한다.
