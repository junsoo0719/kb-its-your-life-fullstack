# vue-router를 이용한 라우팅 1

## 1. 개념

Vue에서 라우팅은 URL 경로에 따라 다른 컴포넌트를 보여 주는 기능이다.

특히 SPA(Single Page Application, 단일 페이지 애플리케이션)에서는 페이지 전체를 새로 불러오는 것이 아니라, 필요한 화면만 바꿔 끼우는 방식으로 동작한다.

즉, HTML 문서를 계속 새로 요청하는 방식이 아니라 하나의 페이지 안에서 화면 전환이 이루어진다.

## 2. SPA와 라우팅

SPA는 처음 한 번 필요한 파일을 불러온 뒤, 이후에는 JavaScript를 이용해 화면을 동적으로 바꾸는 구조이다.

이 방식의 장점은 다음과 같다.

- 페이지 전환이 빠르다.
- 사용자 경험이 자연스럽다.
- 필요한 부분만 다시 렌더링할 수 있다.

하지만 SPA에서는 일반적인 `<a href="">` 방식으로 이동하면 브라우저가 새로운 페이지를 요청하게 되므로, Vue Router를 사용해 화면 전환을 처리해야 한다.

## 3. router 객체 생성과 등록

Vue Router를 사용하려면 먼저 router 객체를 생성하고 앱에 등록해야 한다.

```js
import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
  history: createWebHistory(),
  routes: [],
});

export default router;
```

그리고 `main.js`에서 등록한다.

```js
import { createApp } from 'vue';
import App from './App.vue';
import router from './router';

const app = createApp(App);

app.use(router);
app.mount('#app');
```

### 핵심 정리

- `createRouter()` : router 객체 생성
- `routes` : 라우팅 테이블
- `app.use(router)` : 앱에 router 등록

즉, router를 만들고 등록해야 Vue 애플리케이션 전체에서 라우팅 기능을 사용할 수 있다.

## 4. routes와 라우팅 테이블

`routes`는 어떤 경로에 어떤 컴포넌트를 연결할지 정리한 라우팅 테이블이다.

```js
const routes = [
  { path: '/', component: HomeView },
  { path: '/about', component: AboutView },
];
```

위 코드는 다음 의미를 가진다.

- `/` 경로로 들어오면 `HomeView` 렌더링
- `/about` 경로로 들어오면 `AboutView` 렌더링

즉, URL과 컴포넌트를 연결하는 표라고 생각하면 된다.

## 5. RouterView

`<RouterView>`는 현재 경로에 맞는 컴포넌트를 화면에 렌더링할 위치를 지정하는 컴포넌트이다.

```vue
<template>
  <div>
    <RouterView />
  </div>
</template>
```

즉, 라우터가 현재 URL을 보고 어떤 컴포넌트를 보여 줄지 결정하면, 그 컴포넌트가 `<RouterView>` 위치에 출력된다.

## 6. RouterLink

SPA에서는 일반 `<a href="">` 태그 대신 `<RouterLink>`를 사용해야 한다.

```vue
<template>
  <div>
    <RouterLink to="/">홈</RouterLink>
    <RouterLink to="/about">소개</RouterLink>
  </div>
</template>
```

### 특징

- `to` 속성에 이동할 경로를 작성한다.
- 화면에 보이는 형태는 `a` 태그와 비슷하다.
- 페이지를 새로 고치지 않고 화면만 전환한다.

SPA에서는 `<a href="#">`에서 `#` 외에 다른 경로를 직접 쓰기 어렵기 때문에, Vue Router에서는 `<RouterLink>`를 사용해 이동을 처리한다.

## 7. router 폴더와 views 폴더

보통 Vue 프로젝트에서는 라우터 설정을 `router/index.js` 파일에 작성한다.

### 7.1 router/index.js

- 라우터 설정을 모아 두는 파일
- 디렉토리명 `router`를 모듈명처럼 사용

### 7.2 views 폴더

- 페이지 단위 컴포넌트를 저장하는 폴더
- `pages` 폴더를 쓰는 경우도 있음

즉,

- `router` : 경로 설정
- `views` : 실제 화면 컴포넌트
  로 역할이 나뉜다.

## 8. 비동기 컴포넌트 로딩

라우팅할 컴포넌트는 비동기 방식으로 불러올 수 있다.

```js
const routes = [
  {
    path: '/about',
    component: () => import('../views/AboutView.vue'),
  },
];
```

이 방식은 처음부터 `AboutView`를 불러오지 않고, 사용자가 `/about` 경로에 처음 접근했을 때 import한다.

### 장점

- 초기 로딩 시간을 줄일 수 있다.
- 처음 앱 실행 속도가 더 빨라질 수 있다.

즉, 자주 사용하지 않는 페이지는 나중에 불러오게 해서 성능을 최적화하는 방식이다.

## 9. 동기 로딩과 비동기 로딩 차이

비동기로 하지 않고 아래처럼 작성할 수도 있다.

```js
import AboutView from '../views/AboutView.vue';

const routes = [
  {
    path: '/about',
    component: AboutView,
  },
];
```

### 차이점

- 동기 로딩: 처음 앱이 시작될 때 함께 로드됨
- 비동기 로딩: 해당 요청이 처음 들어왔을 때 로드됨

사용자가 느끼는 기능상의 차이는 거의 없을 수 있다.  
하지만 성능 측면에서는 비동기 로딩이 더 유리하다.

즉, 화면 동작은 비슷하지만 초기 로딩 속도 최적화 측면에서 비동기 방식이 더 좋다.

## 10. 컴포넌트에서 router 객체 접근

컴포넌트 안에서는 router 객체에 접근할 수 있다.

### 10.1 Options API

```js
this.$router;
```

### 10.2 Composition API

```js
import { useRouter } from 'vue-router';

const router = useRouter();
```

`router` 객체는 라우터 관련 동작을 처리할 때 사용한다.

예를 들면

- 페이지 이동
- 라우터 정보 확인
- 프로그래밍 방식의 네비게이션
  등을 처리할 수 있다.

## 11. 현재 매칭된 라우트 정보 접근

현재 URL에 매칭된 라우트 정보는 `route` 객체로 확인할 수 있다.

### 11.1 Options API

```js
this.$route;
```

또는

```js
this.$router.currentRoute;
```

### 11.2 Composition API

```js
import { useRoute } from 'vue-router';

const currentRoute = useRoute();
```

즉,

- `router` : 라우터 동작 제어
- `route` : 현재 경로 정보 확인
  이라고 구분하면 된다.

## 12. currentRoute 객체의 주요 속성

현재 라우트 객체에는 여러 정보가 들어 있다.

### 12.1 params

URI 경로에 동적으로 전달된 파라미터 정보이다.

예를 들어 다음과 같은 경로가 있다면

```js
{ path: '/user/:id', component: UserView }
```

`/user/10`으로 접근했을 때 `id` 값은 params로 전달된다.

```js
this.$route.params.id;
```

또는 Composition API에서는

```js
currentRoute.params.id;
```

### 12.2 query

쿼리스트링 정보를 객체 형태로 제공한다.

예를 들어

```text
?a=1&b=2
```

와 같은 요청이 들어오면 `query`는 다음과 비슷한 형태가 된다.

```js
{ a: 1, b: 2 }
```

즉,

- `params` : 경로에 포함된 동적 값
- `query` : `?key=value` 형태의 추가 정보
  라고 이해하면 된다.

## 13. 예제

```js
import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/',
    component: () => import('../views/HomeView.vue'),
  },
  {
    path: '/about',
    component: () => import('../views/AboutView.vue'),
  },
  {
    path: '/user/:id',
    component: () => import('../views/UserView.vue'),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
```

```vue
<template>
  <nav>
    <RouterLink to="/">홈</RouterLink>
    <RouterLink to="/about">소개</RouterLink>
    <RouterLink to="/user/10?name=tom">사용자</RouterLink>
  </nav>

  <RouterView />
</template>
```

설명:

- `createRouter()`로 router 객체를 만든다.
- `routes`에 경로와 컴포넌트를 연결한다.
- `<RouterLink>`로 화면 이동 링크를 만든다.
- `<RouterView>`에 현재 경로의 컴포넌트가 렌더링된다.
- `/user/10?name=tom`처럼 접근하면 `params.id`와 `query.name`을 확인할 수 있다.

## 14. 중요 포인트

- SPA는 하나의 페이지 안에서 화면만 바뀌는 구조이다.
- Vue Router는 SPA에서 경로별 화면 전환을 처리한다.
- `createRouter()`로 router 객체를 생성한다.
- `routes`는 경로와 컴포넌트를 연결하는 라우팅 테이블이다.
- `app.use(router)`로 앱에 라우터를 등록한다.
- `<RouterView>`는 현재 경로 컴포넌트를 렌더링하는 위치이다.
- `<RouterLink>`는 SPA 방식의 화면 이동 링크이다.
- 비동기 import는 초기 로딩 시간을 줄이는 데 유리하다.
- `this.$router` / `useRouter()`는 라우터 객체 접근
- `this.$route` / `useRoute()`는 현재 경로 정보 접근
- `params`는 동적 경로 파라미터, `query`는 쿼리스트링 정보이다.

## 정리

Vue Router는 SPA에서 URL 경로에 따라 다른 컴포넌트를 보여 주기 위한 라우팅 도구이다.

핵심 구성은 `createRouter()`, `routes`, `app.use(router)`, `<RouterLink>`, `<RouterView>`이다.  
이들을 이용하면 페이지를 새로 고치지 않고도 자연스럽게 화면을 전환할 수 있다.

또한 컴포넌트 안에서는 `router` 객체와 `route` 객체를 구분해서 사용해야 한다.  
`router`는 이동과 제어를 담당하고, `route`는 현재 경로 정보를 확인하는 데 사용된다.

실무에서는 비동기 컴포넌트 로딩을 함께 사용해 초기 로딩 시간을 줄이는 방식도 중요하다.
