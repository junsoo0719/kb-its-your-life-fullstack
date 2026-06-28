# ✨ JWT의 이해

## 1. JWT 개념

JWT는 JSON Web Token의 약자이다.

JSON 포맷을 이용하여 사용자에 대한 속성을 저장하는 Claim 기반의 Web Token이다.

즉, JWT는 사용자의 인증 정보나 권한 정보를 JSON 형태로 담고, 이를 토큰 문자열로 만들어 클라이언트와 서버가 주고받을 수 있게 하는 방식이다. 📌

## 2. JWT의 구조

JWT는 세 부분으로 구성된다.

    Header.Payload.Signature

각 구성 요소는 `.`으로 구분된다.

JWT의 세 가지 구성 요소는 다음과 같다.

- Header
- Payload
- Signature

각 부분은 Base64로 인코딩되어 표현된다.

즉, JWT는 헤더, 페이로드, 서명을 각각 인코딩한 뒤 `.`으로 연결한 문자열이다. ✅

## 3. Header

Header는 JWT의 타입과 서명에 사용할 알고리즘 정보를 담는다.

예시는 다음과 같다.

    {
      "alg": "HS256",
      "typ": "JWT"
    }

Header는 주로 다음 두 값으로 구성된다.

- `alg`
- `typ`

## 4. alg

`alg`는 해싱 알고리즘을 의미한다.

이 알고리즘은 JWT의 Signature를 생성하거나 토큰을 검증할 때 사용된다.

예를 들어 다음 값은 HS256 알고리즘을 사용한다는 뜻이다.

    "alg": "HS256"

즉, `alg`는 토큰 서명과 검증에 사용할 알고리즘을 지정한다. 📌

## 5. typ

`typ`는 토큰의 타입을 의미한다.

JWT에서는 보통 다음과 같이 작성한다.

    "typ": "JWT"

즉, 이 토큰이 JWT 형식의 토큰임을 나타낸다.

## 6. Payload

Payload는 토큰에서 사용할 정보 조각들을 담는 부분이다.

Payload에 담기는 정보를 Claim이라고 한다.

Claim은 key-value 형태로 구성된다.

예를 들면 다음과 같다.

    {
      "sub": "user01@test.com",
      "role": "ROLE_USER"
    }

즉, Payload는 JWT가 담고 있는 실제 정보 영역이다. ✅

## 7. Claim

Claim은 토큰에 담기는 정보의 조각이다.

JWT의 Claim은 크게 세 종류로 나눌 수 있다.

- 등록된 클레임
- 공개 클레임
- 비공개 클레임

각 Claim은 key-value 형태로 작성된다.

즉, Claim은 JWT Payload 안에 들어가는 사용자 정보 또는 토큰 정보를 의미한다. 📌

## 8. 등록된 클레임

등록된 클레임은 Registered Claim이라고 한다.

토큰 정보를 표현하기 위해 이미 정해진 종류의 데이터이다.

모두 선택적으로 작성할 수 있지만, 사용할 것을 권장한다.

대표적인 등록된 클레임은 다음과 같다.

- `iss`
- `sub`
- `aud`
- `exp`
- `nbf`
- `iat`
- `jti`

## 9. iss

`iss`는 issuer의 약자이다.

토큰 발급자를 의미한다.

즉, 이 JWT를 누가 발급했는지 나타낸다.

    "iss": "api-server"

## 10. sub

`sub`는 subject의 약자이다.

토큰 제목 또는 토큰의 주체를 의미한다.

보통 unique한 값을 사용하며, 사용자 이메일이나 username을 많이 사용한다.

    "sub": "user01@test.com"

즉, `sub`는 이 토큰이 어떤 사용자를 나타내는지 식별하는 값으로 사용할 수 있다. ✅

## 11. aud

`aud`는 audience의 약자이다.

토큰 대상자를 의미한다.

즉, 이 토큰을 사용할 대상 시스템이나 사용자를 나타낼 수 있다.

    "aud": "client-app"

## 12. exp

`exp`는 expiration의 약자이다.

토큰 만료 시간을 의미한다.

`exp`는 NumericDate 형식으로 되어 있어야 한다.

예시는 다음과 같다.

    "exp": 1480849147370

즉, `exp` 시간이 지나면 해당 토큰은 만료된 토큰으로 처리된다. 📌

## 13. nbf

`nbf`는 not before의 약자이다.

토큰 활성 날짜를 의미한다.

즉, 해당 시간 이전에는 토큰이 유효하지 않다.

예를 들어 `nbf`가 특정 시간으로 설정되어 있으면, 그 시간 이후부터 토큰을 사용할 수 있다.

## 14. iat

`iat`는 issued at의 약자이다.

토큰 발급 시간을 의미한다.

토큰이 언제 발급되었는지를 나타내며, 발급 이후 경과 시간을 확인할 때 사용할 수 있다.

    "iat": 1719630000

## 15. jti

`jti`는 JWT ID를 의미한다.

JWT 토큰의 식별자 역할을 한다.

중복 방지를 위해 사용할 수 있으며, 일회용 토큰이나 Access Token 관리 등에 사용할 수 있다.

즉, `jti`는 특정 JWT를 구분하기 위한 고유 ID이다. 📌

## 16. 공개 클레임

공개 클레임은 Public Claim이라고 한다.

사용자 정의 클레임이며, 공개용 정보를 위해 사용한다.

충돌을 방지하기 위해 URI 형태의 key를 사용할 수 있다.

예시는 다음과 같다.

    {
      "https://suddiyo.tistory.com": true
    }

즉, 공개 클레임은 외부와 공유해도 되는 사용자 정의 정보를 담을 때 사용한다.

## 17. 비공개 클레임

비공개 클레임은 Private Claim이라고 한다.

서버와 클라이언트 사이에 임의로 지정한 정보를 저장할 때 사용한다.

예시는 다음과 같다.

    {
      "access_token": "access"
    }

즉, 비공개 클레임은 특정 서비스 내부에서만 사용하는 약속된 정보를 담는 Claim이다. ✅

## 18. Signature

Signature는 JWT의 서명 영역이다.

토큰을 인코딩하거나 유효성을 검증할 때 사용하는 고유한 암호화 코드이다.

Signature는 다음 정보를 기반으로 생성된다.

- Header
- Payload
- Secret Key

즉, Signature는 해당 토큰이 변조되지 않았음을 확인하기 위한 메커니즘이다. 📌

## 19. Signature 생성 과정

Signature 생성 과정은 다음과 같다.

1. Header와 Payload 값을 각각 Base64로 인코딩한다.
2. 인코딩한 값을 비밀 키를 이용해 Header에서 정의한 알고리즘으로 해싱한다.
3. 해싱한 값을 다시 Base64로 인코딩하여 Signature를 생성한다.

즉, Header와 Payload가 조금이라도 바뀌면 Signature 검증이 실패한다.

이 때문에 JWT는 토큰 변조 여부를 확인할 수 있다. ✅

## 20. JWT 로그인 흐름

JWT 기반 로그인 흐름은 다음과 같다.

1. 클라이언트가 서버로 ID/PW를 보내 로그인 요청을 한다.
2. 서버는 검증 과정을 거쳐 해당 유저가 존재하는지 확인한다.
3. 유저가 존재하면 Access Token과 Refresh Token을 발급한다.
4. 클라이언트는 이후 API 요청 시 요청 헤더에 Access Token을 포함한다.
5. 서버는 Access Token을 검증하고 요청을 처리한다.

즉, JWT 로그인에서는 로그인 성공 후 토큰을 발급하고, 이후 요청은 토큰으로 인증한다. 📌

## 21. Access Token

Access Token은 인증된 사용자가 특정 리소스에 접근할 때 사용하는 토큰이다.

클라이언트는 Access Token을 사용하여 인증된 사용자의 신원을 확인받고, 서비스 또는 리소스에 접근한다.

Access Token은 유효 기간이 지나면 만료된다.

즉, Access Token은 API 요청 시 인증을 위해 사용하는 핵심 토큰이다. ✅

## 22. Access Token 만료

Access Token은 보안을 위해 보통 짧은 유효 기간을 가진다.

유효 기간이 지나면 해당 토큰은 expired 상태가 된다.

만료된 Access Token으로 API를 요청하면 서버는 요청을 거부할 수 있다.

이 경우 새로운 Access Token을 얻기 위해 Refresh Token을 사용한다. 📌

## 23. Refresh Token

Refresh Token은 Access Token을 갱신하기 위해 사용하는 토큰이다.

일반적으로 Access Token과 함께 발급된다.

Access Token이 만료되면 클라이언트는 Refresh Token을 사용하여 새로운 Access Token을 발급받는다.

즉, Refresh Token은 사용자가 매번 다시 로그인하지 않아도 인증 상태를 유지할 수 있도록 도와준다. ✅

## 24. Refresh Token의 특징

Refresh Token은 보안상의 이유로 Access Token보다 긴 유효 기간을 가진다.

Access Token은 자주 API 요청에 사용되므로 탈취 위험을 줄이기 위해 짧게 가져가는 경우가 많다.

반면 Refresh Token은 Access Token을 재발급하기 위한 용도이므로 더 긴 유효 기간을 가진다.

정리하면 다음과 같다.

- Access Token → 리소스 접근용, 비교적 짧은 유효 기간
- Refresh Token → Access Token 갱신용, 비교적 긴 유효 기간

## 25. 토큰 갱신 Refresh

토큰 갱신 흐름은 다음과 같다.

1. 클라이언트가 Access Token으로 API 요청을 보낸다.
2. Access Token의 유효 시간이 만료되어 요청이 실패한다.
3. 클라이언트가 Refresh Token을 이용하여 Access Token 재발급을 요청한다.
4. 서버가 Refresh Token을 검증하고 새로운 Access Token을 발급한다.
5. 클라이언트는 재발급받은 Access Token을 요청 헤더에 포함하여 API를 다시 요청한다.

즉, Refresh 과정은 만료된 Access Token을 새 Access Token으로 교체하는 과정이다. 📌

## 26. axios 인터셉터와 자동 갱신

Access Token이 만료되었을 때 사용자가 직접 다시 로그인하게 만들면 사용성이 떨어진다.

따라서 토큰 갱신 과정은 사용자가 모르게 자동으로 처리되는 것이 좋다.

프론트엔드에서는 axios 인터셉터를 이용해 이 과정을 처리할 수 있다.

예를 들어 응답에서 Access Token 만료 에러를 받으면, 인터셉터가 Refresh Token으로 새 Access Token을 요청하고 기존 API 요청을 다시 보내는 방식이다.

즉, 토큰 갱신은 axios 인터셉터에서 자동 처리하는 구조가 적합하다. ✅

## 27. JWT 사용 시 주의점

JWT는 Payload가 Base64로 인코딩되어 있을 뿐 암호화된 것은 아니다.

따라서 민감한 정보는 Payload에 직접 넣지 않는 것이 좋다.

예를 들어 비밀번호, 주민등록번호, 카드번호 같은 정보는 JWT에 담으면 안 된다.

또한 토큰이 탈취되면 만료 전까지 악용될 수 있으므로 Access Token의 유효 기간을 적절히 짧게 설정해야 한다. ⚠️

## 28. 중요 포인트 📌

- JWT는 JSON 포맷을 이용해 사용자 속성을 저장하는 Claim 기반 Web Token이다.
- JWT는 `Header.Payload.Signature` 구조로 구성된다.
- 각 구성 요소는 Base64로 인코딩되고 `.`으로 구분된다.
- Header에는 `alg`, `typ` 정보가 들어간다.
- `alg`는 서명과 검증에 사용할 해싱 알고리즘이다.
- `typ`는 토큰 타입이다.
- Payload에는 Claim이 담긴다.
- Claim은 key-value 형태이다.
- Claim은 Registered, Public, Private Claim으로 나뉜다.
- Registered Claim에는 `iss`, `sub`, `aud`, `exp`, `nbf`, `iat`, `jti`가 있다.
- `sub`는 주로 사용자 이메일이나 username 같은 unique한 값을 사용한다.
- `exp`는 토큰 만료 시간이다.
- Signature는 토큰 변조 여부를 확인하기 위한 서명이다.
- Signature는 Header, Payload, Secret Key를 기반으로 생성된다.
- 로그인 성공 시 Access Token과 Refresh Token을 발급할 수 있다.
- Access Token은 리소스 접근에 사용된다.
- Refresh Token은 Access Token 재발급에 사용된다.
- Access Token이 만료되면 Refresh Token으로 새 Access Token을 발급받는다.
- 토큰 갱신 과정은 사용자가 모르게 자동으로 처리되는 것이 좋다.
- axios 인터셉터에서 토큰 갱신 처리를 할 수 있다.

## 정리 ✅

JWT는 JSON 포맷을 이용해 사용자 정보를 Claim 형태로 저장하는 Web Token이다.  
JWT는 Header, Payload, Signature 세 부분으로 구성되며, 각 부분은 Base64로 인코딩되고 `.`으로 구분된다.  
Header에는 서명 알고리즘과 토큰 타입이 들어가고, Payload에는 사용자 정보나 토큰 정보를 나타내는 Claim이 들어간다.  
Signature는 Header와 Payload, Secret Key를 기반으로 생성되며 토큰이 변조되지 않았음을 확인하는 데 사용된다.  
JWT 기반 로그인에서는 서버가 로그인 성공 시 Access Token과 Refresh Token을 발급하고, 클라이언트는 Access Token을 요청 헤더에 포함해 API를 호출한다.  
Access Token이 만료되면 Refresh Token으로 새로운 Access Token을 발급받으며, 이 과정은 axios 인터셉터 등을 이용해 사용자가 모르게 자동 처리하는 것이 좋다.
