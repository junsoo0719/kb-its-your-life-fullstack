# vue-router를 이용한 라우팅 2

## 1. 개념

vue-router에서 라우팅은 단순히 정적인 경로만 연결하는 것이 아니라, **동적인 값**, **이름 기반 이동**, **중첩 구조**까지 함께 다룰 수 있다.

이번 내용의 핵심은 다음과 같다.

- 동적 라우트
- `params` 사용
- 명명된 라우트
- `router.push()`
- 중첩 라우팅

즉, 단순한 페이지 이동을 넘어서 **실제 서비스 구조에 맞는 라우팅 방법**을 배우는 단계이다.

## 2. 동적 라우트(Dynamic Route)

동적 라우트는 **일정한 패턴의 URI 경로를 하나의 라우트에 연결하는 방법**이다.

예를 들어 회원 상세보기, 수정, 삭제 같은 기능은 항상 특정 대상 1개가 정해져야 한다.  
이럴 때 URL에 식별자(id)를 함께 넣는다.

```js
{
  path: '/members/:id',
  component: MemberDetailView
}
```

위 코드에서 `:id`는 동적으로 바뀌는 값이다.

예를 들면 다음과 같은 URL이 가능하다.

- `/members/1`
- `/members/10`
- `/members/25`

즉, 하나의 라우트 설정으로 여러 상세 페이지를 처리할 수 있다.

## 3. params 사용

동적 라우트의 값은 `params`로 꺼낸다.

Composition API에서는 `useRoute()`를 사용한다.

```js
import { useRoute } from 'vue-router';

const currentRoute = useRoute();
const id = currentRoute.params.id;
```

여기서 중요한 점은 다음과 같다.

- `currentRoute.params.id`를 매우 자주 사용한다.
- `params`의 모든 값은 URL에서 온 정보이다.
- 따라서 **항상 문자열(String)** 이다.

즉, 숫자처럼 보여도 실제 타입은 문자열이다.

## 4. params 값 형 변환

URL에서 받은 `params`는 문자열이므로, 숫자로 사용해야 할 경우 형 변환이 필요하다.

```js
const currentRoute = useRoute();
const id = parseInt(currentRoute.params.id, 10);
```

### 설명

- `parseInt()`는 문자열을 정수로 바꾸는 함수이다.
- 두 번째 인자 `10`은 10진수라는 의미이다.
- 보통 생략 가능하지만 명시적으로 써 주는 것이 안전하다.

즉, `params.id`를 숫자 계산이나 비교에 사용할 때는 형 변환이 필요하다.

## 5. RouterLink에서 동적 경로 사용

목록 화면에서 각 항목의 상세 화면으로 이동할 때는 `router-link`에 동적 경로를 연결한다.

```vue
<router-link :to="'/members/' + m.id">상세보기</router-link>
```

위 방식은 매우 중요하다.

템플릿 문자열을 사용할 수도 있다.

```vue
<router-link :to="`/members/${m.id}`">상세보기</router-link>
```

두 방식 모두 같은 의미이다.

즉,

- 문자열 더하기 방식
- 템플릿 문자열 방식

둘 다 가능하지만, 가독성은 템플릿 문자열이 더 좋을 때가 많다.

## 6. 외부 사이트 이동

외부 사이트로 이동하는 경우에는 `router-link`가 아니라 **반드시 `a` 태그**를 사용해야 한다.

```html
<a href="https://www.google.com">구글로 이동</a>
```

이유는 `router-link`는 Vue 애플리케이션 내부 라우팅 전용이기 때문이다.

즉,

- 내부 페이지 이동 → `router-link`
- 외부 사이트 이동 → `a`

로 구분해야 한다.

## 7. @ 별칭

Vue 프로젝트에서는 `@`를 `src` 디렉토리의 별칭으로 많이 사용한다.

예를 들면 다음과 같다.

```js
import HomeView from '@/views/HomeView.vue';
```

이 의미는 다음과 같다.

```js
import HomeView from '../src/views/HomeView.vue';
```

즉, `@`는 `src`를 가리키는 별칭이라서 경로를 더 짧고 보기 쉽게 만들어 준다.

## 8. Bootstrap 복습

라우팅과 함께 목록 화면을 만들 때 Bootstrap grid를 자주 같이 사용한다.

```html
<div class="row">
  <div class="col-6 col-sm-4 col-md-3 col-lg-2">카드</div>
</div>
```

### 의미

- `row` : grid의 행 역할
- `col-6` : 기본 2칸 배치
- `col-sm-4` : sm 구간에서 3칸 배치
- `col-md-3` : md 구간에서 4칸 배치
- `col-lg-2` : lg 구간에서 6칸 배치

즉, 화면 크기에 따라 카드 개수를 다르게 보여 줄 수 있다.

## 9. 명명된 라우트(Named Route)

명명된 라우트는 `path` 대신 `name`을 기준으로 이동하는 방식이다.

```js
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
  },
  {
    path: '/videos',
    name: 'videos',
    component: VideoListView,
  },
];
```

### 왜 많이 쓰는가

이 방식은 매우 자주 쓰이며 권장된다.

이유는 URL은 바뀔 수 있지만, 이름은 상대적으로 안정적으로 유지할 수 있기 때문이다.

즉,

- 예전 방식: `to="/"`
- 권장 방식: `:to="{ name: 'home' }"`

URL이 나중에 바뀌어도 `name`만 유지되면 사용하는 쪽 코드를 많이 수정하지 않아도 된다.

## 10. 객체 방식의 to 사용

명명된 라우트를 사용할 때는 `to`에 객체를 넣는다.

```vue
<router-link :to="{ name: 'home' }">홈</router-link>
```

여기서 중요한 점은 **객체이므로 `:` 바인딩이 필요하다**는 것이다.

잘못된 예:

```vue
<router-link to="{ name: 'home' }">홈</router-link>
```

올바른 예:

```vue
<router-link :to="{ name: 'home' }">홈</router-link>
```

즉, 객체를 전달할 때는 반드시 `v-bind` 문법을 사용해야 한다.

## 11. router.push()

`router.push()`는 JavaScript 코드 안에서 페이지를 이동할 때 사용한다.

```js
router.push({ name: 'videos' });
```

이 방식은 **페이지 이동(stack)** 이 일어난다.  
즉, 브라우저 방문 기록에 쌓이므로 뒤로 가기가 가능하다.

주로 다음과 같은 상황에서 사용한다.

- 버튼 클릭 후 이동
- 로그인 완료 후 이동
- 조건 처리 후 자동 이동

Composition API에서는 보통 이렇게 사용한다.

```js
import { useRouter } from 'vue-router';

const router = useRouter();

const movePage = () => {
  router.push({ name: 'videos' });
};
```

## 12. 동적 라우트와 명명된 라우트 함께 사용

동적 라우트도 이름 기반으로 이동할 수 있다.

```js
const routes = [
  {
    path: '/members/:id',
    name: 'member-detail',
    component: MemberDetailView,
  },
];
```

이동할 때는 다음처럼 작성할 수 있다.

```vue
<router-link :to="{ name: 'member-detail', params: { id: m.id } }">
  상세보기
</router-link>
```

이 방식이 더 좋은 이유는 URL 문자열을 직접 이어 붙이지 않아도 되기 때문이다.

즉, 경로 구조가 바뀌더라도 `name` 기반으로 관리하면 수정이 쉬워진다.

## 13. 중첩 라우팅

중첩 라우팅은 부모 경로 아래에 자식 경로를 두는 방식이다.

즉, 큰 화면 틀은 유지하면서 내부 일부 영역만 바꿔 보여 줄 때 사용한다.

예를 들면

- 마이페이지 안의 주문내역 / 회원정보 / 설정
- 관리자 페이지 안의 대시보드 / 회원관리 / 게시글관리

같은 구조에 적합하다.

기본 형태는 다음과 같다.

```js
const routes = [
  {
    path: '/mypage',
    component: MyPageView,
    children: [
      {
        path: 'profile',
        component: ProfileView,
      },
      {
        path: 'orders',
        component: OrdersView,
      },
    ],
  },
];
```

부모 컴포넌트 안에는 자식 화면이 렌더링될 위치가 필요하다.

```vue
<template>
  <div>
    <h1>마이페이지</h1>
    <RouterView />
  </div>
</template>
```

즉, 중첩 라우팅은 공통 레이아웃을 유지하면서 내부 내용만 바꾸는 데 유용하다.

## 14. 예제

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

export default router;
```

```vue
<script setup>
import { useRoute, useRouter } from 'vue-router';

const currentRoute = useRoute();
const router = useRouter();

const id = parseInt(currentRoute.params.id, 10);

const moveVideos = () => {
  router.push({ name: 'members' });
};
</script>

<template>
  <div>
    <router-link :to="{ name: 'home' }">홈</router-link>
    <router-link :to="{ name: 'member-detail', params: { id: 10 } }">
      상세보기
    </router-link>

    <button @click="moveVideos">목록으로 이동</button>
  </div>
</template>
```

설명:

- `:id`로 동적 라우트 생성
- `useRoute()`로 현재 경로 정보 확인
- `params.id`는 문자열이므로 `parseInt()` 사용
- `router.push()`로 코드에서 페이지 이동
- `name` 기반 이동으로 URL 변경에 유연하게 대응

## 15. 중요 포인트

- 동적 라우트는 일정 패턴의 URI를 하나의 라우트로 처리하는 방식이다.
- `:id` 형태로 경로 파라미터를 작성한다.
- `currentRoute.params.id`는 매우 자주 사용한다.
- `params` 값은 모두 URL 정보이므로 문자열이다.
- 숫자로 사용할 때는 `parseInt(currentRoute.params.id, 10)`으로 형 변환한다.
- 내부 이동은 `router-link`, 외부 이동은 `a` 태그를 사용한다.
- `@`는 `src` 디렉토리 별칭이다.
- 명명된 라우트는 `name` 기준 이동 방식이며 많이 사용하고 권장된다.
- `:to="{ name: 'home' }"`처럼 객체를 쓸 때는 바인딩이 필요하다.
- `router.push()`는 코드에서 페이지 이동할 때 사용한다.
- 중첩 라우팅은 공통 레이아웃 안에서 내부 화면만 바꾸는 구조에 적합하다.

## 정리

vue-router의 고급 라우팅에서는 단순한 정적 경로 연결을 넘어서, 동적 라우트와 명명된 라우트, 중첩 라우팅까지 함께 사용하게 된다.

동적 라우트는 `:id` 같은 패턴을 통해 상세보기, 수정, 삭제처럼 특정 대상 1개를 지정하는 데 사용한다.  
이때 `params` 값은 모두 문자열이므로 숫자로 사용하려면 형 변환이 필요하다.

또한 실무에서는 URL 문자열보다 `name` 기반 이동을 많이 사용한다.  
이 방식은 URL 구조가 바뀌어도 수정 범위를 줄일 수 있기 때문이다.

마지막으로 `router.push()`는 코드에서 직접 페이지를 이동할 때, 중첩 라우팅은 공통 레이아웃을 유지하면서 내부 화면만 바꿔야 할 때 유용하다.
