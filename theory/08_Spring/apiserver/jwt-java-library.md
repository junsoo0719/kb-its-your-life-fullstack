# ✨ JWT 자바 라이브러리

## 1. JWT 자바 라이브러리 개념

JWT 자바 라이브러리는 Java 애플리케이션에서 JWT 토큰을 생성하고 검증하기 위해 사용하는 라이브러리이다.

JWT는 로그인 인증 이후 사용자 정보를 토큰 형태로 전달할 때 자주 사용된다.  
서버는 사용자의 정보를 기반으로 토큰을 생성하고, 클라이언트는 이후 요청마다 해당 토큰을 함께 전송한다. 📌

즉, JWT 라이브러리는 토큰 생성, 서명, 검증, 정보 추출 작업을 도와준다.

## 2. Secret Key 준비

JWT는 토큰이 위조되지 않았는지 확인하기 위해 서명 값을 사용한다.

이때 서명에 사용할 비밀키가 필요하다.

개발 단계에서는 임의의 긴 문자열을 직접 지정해서 사용할 수 있다.

    private String secretKey = "아주 긴 임의의 문자열 지정";

이 문자열은 그대로 사용하기보다 Base64 인코딩해서 사용할 수 있다.

    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());

즉, Secret Key는 JWT 서명 생성과 검증에 사용하는 핵심 값이다. 📌

## 3. 운영 환경에서 Secret Key 생성

운영 환경에서는 라이브러리를 이용해 키를 자동 생성할 수도 있다.

    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

이 코드는 HS256 알고리즘에 사용할 Secret Key를 생성한다.

다만 주의할 점이 있다.  
서버가 재가동되면 key 문자열이 새로 생성되므로, 기존에 발급한 토큰은 더 이상 사용할 수 없다. ⚠️

따라서 운영 환경에서는 재시작 후에도 유지되는 안전한 키 관리 방식이 필요하다.

## 4. 토큰 유효 기간

JWT에는 유효 기간을 설정할 수 있다.

유효 기간은 보통 밀리초 단위로 계산한다.

테스트용으로 5분짜리 토큰을 만들려면 다음과 같이 작성할 수 있다.

    static final long TOKEN_PERIOD = 1000L * 60L * 5L;

계산 의미는 다음과 같다.

- `1000L` → 1초
- `60L` → 1분
- `5L` → 5분

즉, 위 설정은 토큰 유효 시간을 5분으로 지정한다. ✅

## 5. Payload 정보 구성

JWT의 Payload에는 사용자와 관련된 정보를 담을 수 있다.

JWT에서는 Payload에 담는 정보를 Claim이라고 한다.

Java JWT 라이브러리에서는 `Claims` 객체를 이용해 정보를 구성할 수 있다.

    Claims claims = Jwts.claims().setSubject(userPk);

여기서 `setSubject()`는 JWT의 subject 값을 설정한다.

보통 subject에는 사용자 식별값이나 username을 넣는다. 📌

## 6. Registered Claim

Registered Claim은 JWT에서 미리 정해진 표준 Claim이다.

대표적으로 다음 값들이 있다.

- `sub` → subject, 토큰 제목 또는 사용자 식별값
- `iat` → issued at, 토큰 발급 시간
- `exp` → expiration, 토큰 만료 시간

예시는 다음과 같다.

    Date now = new Date();

    Claims claims = Jwts.claims().setSubject(userPk);
    claims.setIssuedAt(now);
    claims.setExpiration(new Date(now.getTime() + TOKEN_PERIOD));

즉, JWT에는 누가 대상인지, 언제 발급되었는지, 언제 만료되는지를 담을 수 있다. ✅

## 7. Public Claim

Public Claim은 개발자가 필요에 따라 추가하는 공개 Claim이다.

예를 들어 사용자의 권한 정보를 넣을 수 있다.

    claims.put("role", role);

이렇게 하면 JWT Payload에 `role`이라는 이름으로 권한 정보가 저장된다.

즉, public claim은 서비스에서 필요한 추가 정보를 담을 때 사용한다. 📌

## 8. JWT 토큰 생성

JWT 토큰은 `Jwts.builder()`를 이용해 생성할 수 있다.

예시는 다음과 같다.

    String token = Jwts.builder()
        .setSubject(subject)
        .setIssuedAt(new Date())
        .setExpiration(new Date(new Date().getTime() + expire))
        .signWith(key)
        .compact();

각 메서드의 의미는 다음과 같다.

- `setSubject(subject)` → 토큰 대상 설정
- `setIssuedAt(new Date())` → 발급 시간 설정
- `setExpiration(...)` → 만료 시간 설정
- `signWith(key)` → Secret Key로 서명
- `compact()` → 최종 JWT 문자열 생성

즉, 필요한 Claim을 설정하고 서명한 뒤 문자열 형태의 JWT를 만든다. ✅

## 9. JWT 검증

JWT 검증은 토큰이 정상적인지 확인하는 과정이다.

검증할 때는 토큰을 만들 때 사용한 Secret Key와 같은 키를 사용해야 한다.

토큰이 유효 시간 이전이고 서명이 올바르면 정상 토큰으로 판단할 수 있다.

검증 예시는 다음과 같다.

    Jws<Claims> claims = Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(jwtToken);

`parseClaimsJws()`는 토큰을 해석하고 검증한다.  
토큰이 잘못되었거나 만료된 경우 예외가 발생할 수 있다. ⚠️

## 10. JWT 검증 시 발생 가능한 예외

JWT 검증 과정에서는 여러 예외가 발생할 수 있다.

대표적인 예외는 다음과 같다.

- `ExpiredJwtException`
- `UnsupportedJwtException`
- `MalformedJwtException`
- `SignatureException`
- `IllegalArgumentException`

각 의미는 다음과 같다.

- `ExpiredJwtException` → 유효 시간 만기
- `UnsupportedJwtException` → 지원하지 않는 JWT
- `MalformedJwtException` → 잘못된 JWT 포맷
- `SignatureException` → 서명 불일치
- `IllegalArgumentException` → 잘못된 정보 포함

즉, JWT 검증에서는 만료 여부, 형식, 서명, 입력값을 모두 확인해야 한다. 📌

## 11. JWT 정보 추출

JWT에서 사용자 정보를 추출할 수 있다.

대표적으로 subject 값을 꺼낼 수 있다.

    Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();

이 코드는 토큰을 파싱한 뒤 Payload의 subject 값을 추출한다.

보통 subject에는 username이나 사용자 PK를 저장하므로, 이를 이용해 사용자 정보를 조회할 수 있다. ✅

## 12. Claim에서 정보 추출

JWT의 Claim에서 권한 정보나 사용자 정보를 추출할 수 있다.

예시는 다음과 같다.

    Claims claims = parseClaims(accessToken);

    if (claims.get(AUTHORITIES_KEY) == null) {
        throw new RuntimeException("권한 정보가 없는 토큰입니다.");
    }

    final String username = claims.getSubject();

이 코드는 토큰에서 Claim을 꺼내고, 권한 정보가 있는지 확인한 뒤 username을 추출한다.

즉, JWT에 저장된 Claim을 이용해 인증된 사용자를 구성할 수 있다. 📌

## 13. UserDetailsService와 연동

JWT에서 username을 추출한 뒤 `UserDetailsService`를 통해 사용자 정보를 조회할 수 있다.

    final CurrentUser userDetails =
        (CurrentUser) userDetailsService.loadUserByUsername(username);

이 방식은 토큰에 들어 있는 username을 기반으로 DB 또는 저장소에서 사용자 정보를 다시 가져오는 구조이다.

즉, JWT는 사용자를 식별하는 단서를 제공하고, 실제 사용자 상세 정보는 `UserDetailsService`에서 로딩할 수 있다. ✅

## 14. JwtProcessor

`JwtProcessor`는 JWT 작업을 위한 Helper 클래스이다.

JWT 생성, 검증, 정보 추출 코드를 여러 곳에 흩어 놓지 않고 하나의 클래스로 캡슐화한다.

즉, JWT 관련 주요 작업을 한 곳에서 관리하기 위한 유틸리티 클래스이다.

## 15. JwtProcessor의 주요 메서드

`JwtProcessor`에는 보통 다음과 같은 메서드를 작성할 수 있다.

    String generateToken(String subject)

subject, 즉 username에 대한 JWT 토큰을 생성한다.

    String getUsername(String token)

JWT 토큰에서 username을 추출한다.

    boolean validateToken(String token)

JWT 토큰의 유효성을 검증한다.

즉, JwtProcessor는 토큰 생성, 사용자명 추출, 유효성 검증을 담당한다. ✅

## 16. JWT 처리 흐름

JWT 처리 흐름은 다음과 같이 정리할 수 있다.

1. 로그인 성공
2. 사용자 식별값 또는 username을 subject로 설정
3. issuedAt, expiration, role 등 Claim 구성
4. Secret Key로 서명
5. JWT 문자열 생성
6. 클라이언트에 JWT 전달
7. 이후 요청에서 클라이언트가 JWT 전송
8. 서버가 JWT 검증
9. subject 또는 Claim에서 사용자 정보 추출
10. 필요한 경우 UserDetailsService로 사용자 상세 정보 조회

즉, JWT는 로그인 이후 사용자를 식별하고 요청을 인증하는 데 사용된다. 📌

## 17. Secret Key 관리 주의점

Secret Key는 JWT 보안의 핵심이다.

Secret Key가 노출되면 공격자가 유효한 토큰을 위조할 수 있다.

따라서 다음 사항을 주의해야 한다.

- 너무 짧은 문자열 사용 금지
- 코드에 하드코딩하는 방식은 운영 환경에서 지양
- 외부 설정 또는 보안 저장소 사용
- 서버 재시작 후에도 동일한 키 유지 필요
- 키가 변경되면 기존 토큰은 검증 실패 가능

즉, Secret Key는 안전하게 관리해야 한다. ⚠️

## 18. 중요 포인트 📌

- JWT 자바 라이브러리는 토큰 생성, 검증, 정보 추출을 지원한다.
- Secret Key는 JWT 서명 생성과 검증에 사용된다.
- 개발 시에는 임의의 긴 문자열을 Secret Key로 사용할 수 있다.
- Secret Key 문자열은 Base64 인코딩해서 사용할 수 있다.
- 운영 시 `Keys.secretKeyFor(SignatureAlgorithm.HS256)`로 키를 생성할 수 있다.
- 자동 생성 키는 서버 재가동 시 변경되므로 기존 토큰이 무효화될 수 있다.
- JWT 유효 기간은 밀리초 단위로 설정할 수 있다.
- `Claims` 객체로 Payload 정보를 구성한다.
- `sub`, `iat`, `exp`는 registered claim이다.
- `role` 같은 값은 public claim으로 추가할 수 있다.
- `Jwts.builder()`로 JWT 토큰을 생성한다.
- `signWith(key)`로 토큰에 서명한다.
- `compact()`로 JWT 문자열을 만든다.
- `parseClaimsJws()`로 JWT를 검증하고 Claim을 파싱한다.
- 만료된 토큰은 `ExpiredJwtException`이 발생할 수 있다.
- 서명이 다르면 `SignatureException`이 발생할 수 있다.
- `getSubject()`로 username 또는 사용자 식별값을 추출할 수 있다.
- `JwtProcessor`는 JWT 관련 작업을 캡슐화하는 Helper 클래스이다.

## 정리 ✅

JWT 자바 라이브러리는 Java 애플리케이션에서 JWT 토큰을 생성하고 검증하며, 토큰 안의 사용자 정보를 추출하는 데 사용된다.  
JWT 생성 시에는 Secret Key를 준비하고, subject, issuedAt, expiration, role 같은 Claim을 구성한 뒤 `signWith(key)`로 서명하고 `compact()`로 문자열 토큰을 만든다.  
검증 시에는 같은 Secret Key를 사용해 `parseClaimsJws()`로 토큰을 해석하며, 토큰 만료, 잘못된 포맷, 서명 불일치 등의 경우 예외가 발생할 수 있다.  
토큰에서 subject를 추출하면 username을 얻을 수 있고, 이를 기반으로 `UserDetailsService`를 통해 사용자 상세 정보를 조회할 수 있다.  
실제 프로젝트에서는 `JwtProcessor` 같은 Helper 클래스로 토큰 생성, username 추출, 유효성 검증 기능을 캡슐화해 관리하는 것이 좋다.
