# vue-router를 이용한 라우팅 3

## 1. 개념

내비게이션 가드(Navigation Guard)는 라우터를 통해 페이지가 이동할 때, 이동을 허용할지 막을지 또는 다른 경로로 보낼지를 결정하는 기능이다.

즉, 사용자가 특정 페이지로 이동하기 전에 검사하거나, 이동이 끝난 뒤 추가 작업을 처리할 수 있다.

주로 다음과 같은 상황에서 사용한다.

- 로그인하지 않은 사용자의 접근 제한
- 특정 페이지 진입 전 권한 검사
- 페이지 이동 후 로그 기록
- 이동 중 데이터 확인

## 2. 전역 수준 내비게이션 가드

전역 수준 내비게이션 가드는 **모든 경로 이동에 대해 공통으로 실행되는 가드**이다.

라우터 객체를 이용해서 등록한다.

### 2.1 beforeEach()

`beforeEach()`는 내비게이션이 일어나기 전에 실행된다.

```js
router.beforeEach((to, from) => {
  // 이동 전 실행
});
```

이 함수 안에서 반환값에 따라 동작이 달라진다.

- 정상 진행: 아무것도 반환하지 않거나 `true` 반환
- 이동 취소: `false` 반환
- 리다이렉트: 다른 경로 문자열 또는 route 객체 반환

예시:

```js
router.beforeEach((to, from) => {
  if (to.path === '/admin') {
    return '/';
  }
});
```

또는

```js
router.beforeEach((to, from) => {
  if (to.path === '/admin') {
    return { path: '/' };
  }
});
```

또는

```js
router.beforeEach((to, from) => {
  if (to.path === '/admin') {
    return { name: 'member-detail', params: { id: 2 } };
  }
});
```

### 2.2 afterEach()

`afterEach()`는 내비게이션이 완료된 후에 실행된다.

```js
router.afterEach((to, from) => {
  // 이동 후 실행
});
```

이 가드는 주로 다음과 같은 작업에 사용한다.

- 로그 기록
- 화면 추적
- 이동 완료 후 후처리

즉,

- `beforeEach()`는 이동 전 검사
- `afterEach()`는 이동 후 처리
  라고 이해하면 된다.

## 3. 라우트 수준의 내비게이션 가드

라우트 수준의 내비게이션 가드는 **특정 라우트 하나에만 적용되는 가드**이다.

각 라우트 설정 안에 `beforeEnter`를 작성한다.

```js
const routes = [
  {
    path: '/admin',
    component: AdminView,
    beforeEnter: (to, from) => {
      // 특정 라우트 진입 전 실행
    },
  },
];
```

### 3.1 특징

- 각 라우트 단위로 설정한다.
- 특정 페이지에만 개별 조건을 걸 수 있다.
- `to`, `from` 객체를 사용할 수 있다.

`to`, `from` 객체에는 다음과 같은 정보가 들어 있다.

- `path`
- `name`
- `query`
- `params`

즉, 현재 어디로 가는지와 어디서 왔는지를 확인해서 조건을 제어할 수 있다.

## 4. Composition API에서의 내비게이션 가드

Options API와 Composition API는 내비게이션 가드 사용 방식이 다르다.

| Options API         | Composition API            |
| ------------------- | -------------------------- |
| `beforeRouteEnter`  | `setup()` 내부 코드로 대체 |
| `beforeRouteUpdate` | `onBeforeRouteUpdate`      |
| `beforeRouteLeave`  | `onBeforeRouteLeave`       |

즉, Composition API에서는 기존 옵션 메서드 대신 전용 함수나 `setup()` 내부 코드를 사용한다.

### 4.1 onBeforeRouteUpdate

현재 컴포넌트가 재사용되는 상태에서 라우트 정보가 바뀔 때 실행된다.

```js
import { onBeforeRouteUpdate } from 'vue-router';

onBeforeRouteUpdate((to, from) => {
  // 같은 컴포넌트에서 경로만 바뀔 때 처리
});
```

예를 들어 `/members/1`에서 `/members/2`로 바뀌는 경우처럼, 컴포넌트는 그대로인데 파라미터만 바뀌는 상황에서 유용하다.

### 4.2 onBeforeRouteLeave

현재 페이지를 떠나기 전에 실행된다.

```js
import { onBeforeRouteLeave } from 'vue-router';

onBeforeRouteLeave((to, from) => {
  // 페이지 이탈 전 처리
});
```

예를 들어 작성 중인 폼을 저장하지 않았을 때 경고를 띄우는 데 사용할 수 있다.

## 5. RouterView

`<RouterView />`는 각 경로에 맞는 컴포넌트를 렌더링할 위치를 지정하는 컴포넌트이다.

```vue
<template>
  <RouterView />
</template>
```

즉, 라우터가 현재 URL에 맞는 컴포넌트를 결정하면, 그 컴포넌트가 `<RouterView />` 위치에 출력된다.

## 6. RouterLink

`<RouterLink>`는 내부 페이지 이동을 위한 링크 생성 컴포넌트이다.

```vue
<RouterLink to="/members">회원목록</RouterLink>
```

화면에 보이는 형태는 `a` 태그와 비슷하지만, SPA 방식으로 페이지 전체를 새로 불러오지 않고 화면만 전환한다.

즉, Vue 애플리케이션 내부 이동은 `RouterLink`를 사용하는 것이 기본이다.

## 7. 라우터 객체 사용

스크립트에서 프로그래밍 방식으로 페이지를 이동할 때는 라우터 객체를 사용한다.

Composition API에서는 `useRouter()`를 사용한다.

```js
import { useRouter } from 'vue-router';

const router = useRouter();
```

이후 `push()`를 사용해서 이동할 수 있다.

```js
router.push('/members');
```

또는 route 객체를 사용할 수도 있다.

```js
router.push({ name: 'member-detail', params: { id: 1 } });
```

즉,

- 문자열 경로 이동 가능
- 객체 기반 이동 가능
  이다.

## 8. 라우트 정보 추출

현재 라우트 정보를 확인할 때는 `useRoute()`를 사용한다.

```js
import { useRoute } from 'vue-router';

const currentRoute = useRoute();
```

이 객체를 통해 현재 요청의 경로와 파라미터를 확인할 수 있다.

### 8.1 fullPath

`currentRoute.fullPath`는 전체 요청 URI를 의미한다.

```js
console.log(currentRoute.fullPath);
```

예를 들어 `/members/2?page=1` 같은 전체 경로를 확인할 수 있다.

### 8.2 params

`currentRoute.params`는 동적 파라미터를 모아 둔 객체이다.

예를 들어 라우트가 다음과 같다면

```js
{
  path: '/members/:id';
}
```

`/members/10` 요청 시

```js
currentRoute.params.id;
```

처럼 사용할 수 있다.

즉, `/members/:파라미터명` 구조로 전달된 값들을 수집하는 객체이다.

### 8.3 query

`currentRoute.query`는 쿼리 파라미터를 모아 둔 객체이다.

예를 들어

```text
/members/10?page=1&keyword=vue
```

라면

```js
currentRoute.query.page;
currentRoute.query.keyword;
```

처럼 사용할 수 있다.

즉, `?key=value` 형태의 추가 정보를 추출할 때 사용한다.

## 9. 예제

```js
import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import MembersView from '@/views/MembersView.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
  },
  {
    path: '/members',
    name: 'members',
    component: MembersView,
    beforeEnter: (to, from) => {
      return true;
    },
  },
  {
    path: '/members/:id',
    name: 'member-detail',
    component: () => import('@/views/MemberDetailView.vue'),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from) => {
  if (to.path === '/forbidden') {
    return false;
  }
});

router.afterEach((to, from) => {
  console.log('이동 완료');
});

export default router;
```

```vue
<script setup>
import {
  useRouter,
  useRoute,
  onBeforeRouteLeave,
  onBeforeRouteUpdate,
} from 'vue-router';

const router = useRouter();
const currentRoute = useRoute();

const movePage = () => {
  router.push({ name: 'members' });
};

onBeforeRouteUpdate((to, from) => {
  console.log('라우트 업데이트');
});

onBeforeRouteLeave((to, from) => {
  console.log('현재 페이지 떠남');
});
</script>

<template>
  <div>
    <RouterLink to="/">홈</RouterLink>
    <RouterLink to="/members">회원목록</RouterLink>
    <button @click="movePage">이동</button>
    <RouterView />
  </div>
</template>
```

설명:

- `beforeEach()`는 모든 라우트 이동 전에 실행된다.
- `afterEach()`는 이동 완료 후 실행된다.
- `beforeEnter`는 특정 라우트에만 적용된다.
- `useRouter()`는 이동 제어에 사용한다.
- `useRoute()`는 현재 경로 정보 추출에 사용한다.
- `RouterLink`는 내부 이동용 링크이다.
- `RouterView`는 현재 경로 컴포넌트 출력 위치이다.

## 10. 중요 포인트

- 내비게이션 가드는 페이지 이동을 제어하는 기능이다.
- 전역 가드는 모든 라우트에 적용된다.
- `beforeEach()`는 이동 전, `afterEach()`는 이동 후 실행된다.
- `beforeEach()`에서 `true` 또는 반환 없음은 정상 이동이다.
- `false`를 반환하면 이동이 취소된다.
- 문자열 또는 route 객체를 반환하면 리다이렉트된다.
- 라우트 수준 가드는 `beforeEnter`로 설정한다.
- Composition API에서는 `onBeforeRouteUpdate`, `onBeforeRouteLeave`를 사용한다.
- `RouterView`는 현재 경로 컴포넌트를 렌더링하는 위치이다.
- `RouterLink`는 내부 링크 생성 컴포넌트이다.
- `useRouter()`는 페이지 이동 제어용이다.
- `useRoute()`는 현재 라우트 정보 추출용이다.
- `fullPath`, `params`, `query`는 현재 경로 정보를 확인할 때 중요하다.

## 정리

내비게이션 가드는 라우터 이동을 제어하는 기능으로, 특정 페이지 진입 전 검사, 이동 취소, 리다이렉트, 이동 후 처리 등을 담당한다.

전역 수준에서는 `beforeEach()`와 `afterEach()`를 사용하고, 특정 라우트에만 적용할 때는 `beforeEnter`를 사용한다.  
또한 Composition API에서는 `onBeforeRouteUpdate`, `onBeforeRouteLeave` 같은 방식으로 라우트 변경과 이탈을 제어할 수 있다.

Vue Router를 사용할 때는 `<RouterView />`, `<RouterLink>`, `useRouter()`, `useRoute()`의 역할도 함께 이해해야 한다.  
즉, 화면 출력 위치, 내부 링크 생성, 페이지 이동 제어, 현재 라우트 정보 추출을 각각 구분해서 사용할 수 있어야 한다.
