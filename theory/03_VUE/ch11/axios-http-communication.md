# axios를 이용한 HTTP 통신

## 1. REST 개념

REST(Representational State Transfer)는 서버의 정보를 자원(Resource)으로 간주하고, 각 자원에 URI를 부여한 뒤 HTTP 메서드로 자원에 대한 작업을 처리하는 방식이다.  
즉, URI와 HTTP 메서드를 이용해 객체화된 서비스에 접근하는 구조이다.

다음과 같이 대응해서 이해할 수 있다.

- 매개변수 → form
- 함수명 → URI
- 기능 → 메서드(GET, POST, PUT, DELETE ...)
- 리턴값 → response

## 2. REST 방식의 자원 처리

REST에서는 자원을 중심으로 URI를 설계하고, 수행할 작업은 HTTP 메서드로 구분한다.

예를 들어 게시판 자원을 처리하면 다음과 같이 구성할 수 있다.

### 2-1. 읽기(GET)

- `/board` → 전체 게시글 목록 조회
- `/board/{boardId}` → 특정 게시글 1개 조회
- GET 요청은 일반적으로 BODY가 없다.

### 2-2. 생성(POST)

- `/board` → 게시글 생성
- POST 요청은 BODY가 있다.
- BODY에는 보통 JSON 데이터를 담아 전송한다.
- 기본 형태는 `a=b&c=d...` 방식도 사용할 수 있다.

### 2-3. 수정(PUT)

- `/board/{boardId}` → 특정 게시글 수정
- PUT은 전체 수정에 사용된다.
- 부분 수정은 PATCH를 사용한다.
- 수정 내용은 BODY에 JSON으로 담아 전송한다.

### 2-4. 삭제(DELETE)

- `/board/{boardId}` → 특정 게시글 삭제

## 3. REST 구성 요소

REST는 다음 3가지 요소로 구성된다.

- 자원(Resource) → URI
- 행위(Verb) → HTTP METHOD
- 표현(Representation) → 일반적으로 JSON 사용

즉, REST는 자원을 URI로 표현하고, 자원에 대한 동작은 HTTP METHOD로 구분하며, 실제 데이터 표현은 보통 JSON을 사용한다.  
이처럼 URI와 METHOD 조합으로 처리되는 API를 REST API라고 한다.

REST API 서비스의 장점은 클라이언트 종류에 제한이 적다는 점이다.  
즉, 웹 브라우저뿐 아니라 일반 애플리케이션, 모바일 앱 등과도 통신할 수 있다.

## 4. HTTP 상태 코드

서버는 요청 처리 결과를 상태 코드로 응답한다.

- `200` → 요청 성공
- `201` → POST 요청 성공, 자원 생성 완료

상태 코드는 요청이 정상 처리되었는지 확인하는 기준이 된다.

## 5. axios 개념

axios는 HTTP 기반 통신을 지원하는 자바스크립트 라이브러리이다.  
클라이언트가 서버와 데이터를 주고받을 때 사용하는 대표적인 도구이다.

axios는 Promise API를 사용하므로 `async/await` 문법도 함께 사용할 수 있다.

## 6. axios와 fetch 비교

axios와 fetch는 모두 비동기 HTTP 통신에 사용되지만 차이가 있다.

### 6-1. 공통점

- 둘 다 Promise 기반으로 동작한다.
- `async/await` 사용이 가능하다.

### 6-2. 차이점

- axios는 Content-Type 정보를 이용해 JSON 데이터를 자동으로 자바스크립트 객체로 변환한다.
- fetch는 JSON 자동 변환을 지원하지 않으므로 별도의 Promise 체인 처리가 필요하다.

즉, axios는 응답 데이터를 더 편리하게 다룰 수 있다.

## 7. axios 메서드 종류

axios는 HTTP 메서드별로 함수를 제공한다.

- GET → `get()`
- POST → `post()`
- PUT → `put()`
- DELETE → `delete()`

모든 메서드는 Promise 객체를 반환한다.

## 8. 응답 객체의 data

응답 데이터 처리 방식은 fetch와 axios에서 차이가 있다.

- fetch → JSON 문자열 형태로 처리
- axios → 자바스크립트 객체 형태로 처리(역직렬화)

즉, axios는 응답 객체의 `data`를 바로 사용할 수 있다는 장점이 있다.

## 9. Promise와 async/await

axios는 비동기 통신 라이브러리이므로 작업이 끝날 때까지 기다리지 않고 Promise 객체를 반환한다.

비동기 처리 방식은 다음과 같이 이해할 수 있다.

- 함수의 마지막 인자에 콜백 함수가 있는 경우 → 작업 완료 시 콜백 함수 호출
- 콜백 함수 인자가 없는 경우 → Promise 객체 반환

axios는 두 가지 방식으로 자주 사용한다.

- Promise 방식 → `axios.get(url).then()`
- async/await 방식 → `await axios.get(url)`

## 10. axios.get() 메서드

`axios.get()`은 GET 요청을 처리할 때 사용한다.  
주로 목록 조회, 상세 조회와 같은 읽기 작업에 사용된다.

    axios.get(url).then((response) => {
      console.log(response.data);
    });

    const response = await axios.get(url);
    console.log(response.data);

## 11. axios.response 객체

axios의 응답 객체에는 다음과 같은 주요 속성이 있다.

- `data` → 수신된 응답 데이터
- `request` → 서버와 통신에 사용된 XMLHttpRequest 객체 정보
- `status` → 서버가 응답한 HTTP 상태 코드

이 중에서 가장 자주 사용하는 속성은 `data`이다.

## 12. 요청의 헤더 값 지정하기

axios는 요청 시 설정 객체(config)를 이용해 헤더 값, timeout, params 등을 지정할 수 있다.

    axios.get(url, {
      timeout: 2000,
      headers: {
        Authorization: 'Bearer xxxxxxx'
      }
    });

또한 `headers` 외에도 `params`를 추가해서 쿼리 파라미터를 함께 보낼 수 있다.

### 12-1. timeout

- 요청 제한 시간을 설정한다.
- 지정된 시간 안에 응답이 없으면 에러가 발생한다.

### 12-2. Authorization

- 인증 토큰을 헤더에 담아 서버로 전송할 때 사용한다.

### 12-3. params

- URL 뒤에 붙는 쿼리 문자열을 설정할 때 사용한다.

## 13. axios.post() 메서드

`axios.post()`는 서버에 새로운 데이터를 생성할 때 사용한다.

기본 형식은 다음과 같다.

    axios.post(url, data, config);

- `url` → 요청 주소
- `data` → BODY에 담아 보낼 내용
- `config` → 헤더, timeout 등 추가 설정

axios는 직렬화를 자동으로 처리하므로 자바스크립트 객체를 `data`로 전달하면 자동으로 JSON 문자열로 변환된다.  
JSON이 아니라 URL 인코딩 방식으로 보내려면 `config`에서 헤더 항목을 따로 지정해야 한다.

## 14. 기타 axios 함수

axios는 다음과 같은 함수들을 제공한다.

    axios.get(url, config)
    axios.post(url, data, config)
    axios.put(url, data, config)
    axios.delete(url, config)

이때 `post`, `put`은 BODY가 있는 요청이므로 `data`를 함께 전달한다.

## 15. axios 기본 설정 변경

axios는 config 값을 매번 전달하지 않으면 기본값을 사용한다.  
반복적으로 사용하는 설정은 기본값으로 미리 지정할 수 있다.

    axios.defaults.baseURL = '/api/todos';
    axios.defaults.headers.common['Authorization'] = JWT;
    axios.defaults.timeout = 2000;

### 15-1. baseURL

- 공통으로 사용할 기본 주소를 설정한다.

### 15-2. headers.common

- 여러 요청에서 공통으로 사용할 헤더를 설정한다.

### 15-3. timeout

- 모든 요청에 공통으로 적용할 제한 시간을 설정한다.

## 16. replace(/^\/api/, '') 의미

다음 코드는 문자열에서 `/api`로 시작하는 부분을 제거하는 의미이다.

    replace(/^\/api/, '')

구성 요소는 다음과 같다.

- `/ /` → 정규표현식 리터럴
- `^` → 문자열의 시작
- `\/api` → `/api` 문자열
- `''` → 빈 문자열로 치환

즉, 문자열이 `/api`로 시작하면 그 부분을 제거한다.

## 17. origin 의미

다음 코드가 있다고 가정한다.

    const url = '/api/todos/1';

이처럼 `/api`로 시작하는 상대 경로를 사용하는 것은 frontend 서버와 backend 서버가 동일한 출처(origin)를 기준으로 동작한다는 의미이다.  
즉, 현재 접속한 서버 기준으로 `/api/todos/1`에 요청을 보내는 구조이다.

## 18. 에러 처리

axios를 `async/await`와 함께 사용할 때는 `try/catch`로 에러를 처리한다.

    async function getData() {
      try {
        const response = await axios.get('/api/todos/1');
        console.log(response.data);
      } catch (error) {
        console.error(error);
      }
    }

비동기 통신에서는 네트워크 오류, 인증 오류, 서버 오류 등이 발생할 수 있으므로 반드시 에러 처리가 필요하다.

## 19. 중요 포인트

- REST는 자원을 URI로 표현하고 작업은 HTTP METHOD로 구분한다.
- REST API는 URI와 METHOD 조합으로 자원을 처리하는 방식이다.
- axios는 HTTP 기반 통신을 지원하는 자바스크립트 라이브러리이다.
- axios는 JSON 응답을 자동으로 자바스크립트 객체로 변환한다.
- GET, POST, PUT, DELETE 메서드를 함수 형태로 제공한다.
- 응답 객체의 `data`, `request`, `status`를 자주 확인한다.
- 요청 설정으로 `timeout`, `headers`, `params`를 지정할 수 있다.
- 반복되는 설정은 `axios.defaults`로 공통 처리할 수 있다.
- 비동기 처리에는 Promise 방식과 `async/await` 방식을 사용할 수 있다.
- 에러 처리는 `try/catch`로 처리한다.

## 정리

axios는 REST 방식의 API와 통신할 때 자주 사용하는 자바스크립트 라이브러리이다.  
REST는 자원을 URI로 표현하고, HTTP METHOD를 이용해 조회·생성·수정·삭제를 처리하는 구조이다.  
axios는 Promise 기반으로 동작하므로 `then()` 방식과 `async/await` 방식 모두 사용할 수 있으며, JSON 응답을 자동으로 자바스크립트 객체로 변환해 주기 때문에 fetch보다 사용이 편리하다.  
또한 `get`, `post`, `put`, `delete` 메서드를 제공하고, `headers`, `timeout`, `params`, `defaults` 등을 통해 요청을 세부적으로 제어할 수 있다.  
시험 대비에서는 REST의 구성 요소, HTTP 상태 코드, axios 메서드 사용법, 응답 객체의 주요 속성, 비동기 처리 방식, 에러 처리 방법까지 함께 정리해 두는 것이 중요하다.
