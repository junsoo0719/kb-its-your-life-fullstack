# ✨ 로그인과 로그아웃 처리

## 1. Spring Security 설정 후 POST 요청 문제

Spring Security를 설정한 뒤 POST 요청을 처리할 때 한글 문자 인코딩 문제가 발생할 수 있다.

특히 로그인, 회원가입, 게시글 작성처럼 request body에 한글 데이터가 포함되는 경우 한글이 깨지는 현상이 나타날 수 있다. 📌

이는 Spring Security Filter가 기존에 등록한 문자 인코딩 필터보다 먼저 동작하면서 발생할 수 있다.

## 2. 문자 인코딩 문제 원인

일반적으로 한글 깨짐을 방지하기 위해 `WebConfig`에서 문자 인코딩 필터를 등록한다.

하지만 Spring Security가 적용되면 Security Filter Chain이 먼저 동작할 수 있다.

이때 Security Filter에서 POST body가 먼저 resolve되면, 이후 문자 인코딩 필터가 적용되어도 이미 읽힌 body의 한글이 깨질 수 있다.

즉, 인코딩 필터가 너무 늦게 실행되는 것이 문제이다. ⚠️

## 3. 기존 WebConfig 인코딩 필터의 한계

`WebConfig`에 문자 인코딩 필터를 등록해도, Spring Security Filter보다 늦게 실행되면 POST body 인코딩 문제를 해결하지 못할 수 있다.

흐름은 다음과 같이 문제가 발생한다.

1. 클라이언트가 한글이 포함된 POST 요청 전송
2. Security Filter가 먼저 동작
3. POST body가 먼저 resolve됨
4. 이후 문자 인코딩 필터가 실행됨
5. 이미 읽힌 한글 데이터가 깨짐

즉, 문자 인코딩 필터는 POST body가 읽히기 전에 먼저 실행되어야 한다. 📌

## 4. 해결 방법

해결 방법은 Spring Security Filter Chain 안에서 문자 인코딩 필터를 적절한 위치에 등록하는 것이다.

특히 문자 인코딩 필터를 `CsrfFilter`보다 앞에 등록해야 한다.

즉, Security Filter가 POST body를 처리하기 전에 문자 인코딩을 먼저 적용해야 한다. ✅

## 5. CsrfFilter보다 앞에 등록하는 이유

`CsrfFilter`는 CSRF 토큰 검증 과정에서 요청 데이터를 확인할 수 있다.

이 과정에서 POST body가 먼저 읽히면 문자 인코딩이 적용되기 전에 body가 처리될 수 있다.

따라서 문자 인코딩 필터를 `CsrfFilter`보다 앞에 두면, 요청 body가 읽히기 전에 UTF-8 인코딩이 먼저 적용된다.

즉, 한글 깨짐을 방지하려면 인코딩 필터의 위치가 중요하다. 📌

## 6. CharacterEncodingFilter

문자 인코딩 처리를 위해 `CharacterEncodingFilter`를 사용할 수 있다.

이 필터는 request와 response의 인코딩을 UTF-8로 설정한다.

예시는 다음과 같다.

    CharacterEncodingFilter filter = new CharacterEncodingFilter();
    filter.setEncoding("UTF-8");
    filter.setForceEncoding(true);

`setForceEncoding(true)`를 설정하면 request와 response 모두 지정한 인코딩을 강제로 적용할 수 있다. ✅

## 7. Security 설정에서 필터 추가

Spring Security 설정에서 `addFilterBefore()`를 사용하면 특정 필터 앞에 원하는 필터를 등록할 수 있다.

예시는 다음과 같다.

    http.addFilterBefore(characterEncodingFilter(), CsrfFilter.class);

이 설정은 `characterEncodingFilter()`를 `CsrfFilter`보다 먼저 실행하겠다는 의미이다.

즉, CSRF 처리 전에 문자 인코딩을 먼저 적용한다. 📌

## 8. 설정 예시

개념적인 설정 예시는 다음과 같다.

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(characterEncodingFilter(), CsrfFilter.class);

        return http.build();
    }

이렇게 설정하면 Spring Security Filter Chain 안에서 문자 인코딩 필터가 먼저 동작할 수 있다. ✅

## 9. 로그인과 로그아웃 처리에서 중요한 점

로그인과 로그아웃 처리는 Spring Security Filter Chain 안에서 처리되는 경우가 많다.

따라서 로그인 요청이 POST 방식이고, username이나 기타 파라미터에 한글이 포함될 가능성이 있다면 인코딩 처리를 반드시 먼저 해야 한다.

특히 API 로그인처럼 request body를 직접 읽는 경우에도 인코딩 설정이 중요하다.

즉, 로그인/로그아웃 처리에서는 Security Filter Chain의 실행 순서를 함께 고려해야 한다. 📌

## 10. 중요 포인트 📌

- Spring Security 설정 후 POST 요청에서 한글 깨짐이 발생할 수 있다.
- 원인은 문자 인코딩 필터보다 Security Filter가 먼저 동작하기 때문이다.
- Security Filter에서 POST body가 먼저 resolve되면 한글이 깨질 수 있다.
- `WebConfig`에 등록한 문자 인코딩 필터만으로는 해결되지 않을 수 있다.
- 문자 인코딩 필터는 POST body가 읽히기 전에 실행되어야 한다.
- Spring Security Filter Chain에서 문자 인코딩 필터를 등록해야 한다.
- 문자 인코딩 필터는 `CsrfFilter`보다 앞에 등록하는 것이 중요하다.
- `CharacterEncodingFilter`를 사용해 UTF-8 인코딩을 적용할 수 있다.
- `addFilterBefore()`를 사용하면 특정 필터 앞에 필터를 추가할 수 있다.

## 정리 ✅

Spring Security 설정 후 POST 요청에서 한글이 깨지는 이유는 `WebConfig`에서 등록한 문자 인코딩 필터보다 Security Filter가 먼저 동작할 수 있기 때문이다.  
Security Filter에서 POST body가 먼저 resolve되면 이후 문자 인코딩 필터가 실행되어도 이미 읽힌 데이터의 한글이 깨질 수 있다.  
따라서 Spring Security Filter Chain 안에서 문자 인코딩 필터를 `CsrfFilter`보다 앞에 등록해야 한다.  
이때 `CharacterEncodingFilter`를 사용해 UTF-8 인코딩을 강제로 적용하고, `addFilterBefore()`를 이용해 필터 실행 순서를 조정할 수 있다.
