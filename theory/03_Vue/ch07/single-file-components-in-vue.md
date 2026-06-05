# 단일 파일 컴포넌트를 이용한 Vue 애플리케이션 개발

## 1. 단일 파일 컴포넌트

단일 파일 컴포넌트(SFC, Single File Component)는  
컴포넌트 하나를 `.vue` 파일 하나에 작성하는 방식이다.

즉, 하나의 컴포넌트를 파일 단위로 관리할 수 있다.

예를 들어 다음처럼 구성할 수 있다.

```vue
<template>
  <div>{{ message }}</div>
</template>

<script>
export default {
  data() {
    return {
      message: 'Hello Vue',
    };
  },
};
</script>

<style>
div {
  color: blue;
}
</style>
```

이 방식의 핵심은 **컴포넌트 단위로 관심사를 분리**하는 데 있다.

즉,

- template: 화면
- script: 로직
- style: 스타일

을 한 파일 안에서 함께 관리할 수 있다.

---

## 2. 번들링

번들링은 여러 개의 모듈 파일을 묶어서  
하나 또는 몇 개의 모듈 파일로 만드는 과정이다.

즉, 개발 중에는 파일이 여러 개로 나뉘어 있어도  
실행하거나 배포할 때는 묶어서 관리할 수 있다.

Vue 프로젝트에서는 `.vue` 파일, JS 모듈, CSS 등을  
빌드 도구가 묶어 주는 방식으로 동작한다.

---

## 3. `@` 경로

Vue 프로젝트에서는 `@`를 `./src` 경로처럼 사용하는 경우가 많다.

예:

```js
import MyComponent from '@/components/MyComponent.vue';
```

이 표현은 보통 다음과 비슷한 의미이다.

```js
import MyComponent from './src/components/MyComponent.vue';
```

즉, `@`는 `src` 폴더를 가리키는 별칭(alias)처럼 사용된다.

---

## 4. `export default`

Vue 단일 파일 컴포넌트에서는 `export default`가  
기존의 `createApp()` 안에 넣던 옵션 객체 역할을 대신한다.

예:

```vue
<script>
export default {
  name: 'App',
  data() {
    return {
      message: 'Hello',
    };
  },
};
</script>
```

즉, 컴포넌트 파일 안에서는 `createApp()`을 직접 쓰기보다  
`export default`로 컴포넌트의 설정 객체를 내보내는 방식으로 작성한다.

---

## 5. 컴포넌트 등록

Vue에서는 컴포넌트를 등록해서 사용할 수 있다.

등록 방식은 크게 두 가지이다.

- 전역 컴포넌트
- 지역 컴포넌트

---

## 6. 전역 컴포넌트

전역 컴포넌트는 한 번 등록하면 여러 곳에서 바로 사용할 수 있다.

기본 형식은 다음과 같다.

```js
createApp(App).component('컴포넌트이름', 컴포넌트);
```

예:

```js
import { createApp } from 'vue';
import App from './App.vue';
import MyButton from './components/MyButton.vue';

createApp(App).component('MyButton', MyButton).mount('#app');
```

### 특징

- 한 번 등록하면 여러 컴포넌트에서 사용할 수 있다
- 자주 쓰는 공통 컴포넌트에 적합하다
- 사용하려면 먼저 `import`가 필요하다

즉, 여러 군데에서 자주 사용하는 컴포넌트일 때 유용하다.

---

## 7. 지역 컴포넌트

지역 컴포넌트는 사용하는 컴포넌트 안에서 직접 등록해서 사용한다.

예:

```vue
<script>
import MyButton from './components/MyButton.vue';

export default {
  components: {
    MyButton,
  },
};
</script>
```

### 특징

- 필요한 곳에서만 등록해서 사용한다
- 다른 컴포넌트에는 자동으로 보이지 않는다
- 보통 1~2번 정도 사용하는 컴포넌트에 적합하다

실무에서는 보통 **지역 컴포넌트 방식**을 더 많이 사용한다.

즉, 필요한 곳에서만 명확하게 연결하는 방식이 더 일반적이다.

---

## 8. 컴포넌트 간 정보 전달

컴포넌트는 서로 데이터를 주고받아야 할 때가 많다.

기본 방향은 다음과 같다.

- 부모 → 자식 : 속성(Props)
- 자식 → 부모 : 이벤트(Event)

즉, 부모는 데이터를 내려 주고,  
자식은 이벤트를 올려 보내는 구조로 많이 사용한다.

---

## 9. Props를 이용한 정보 전달

부모 컴포넌트가 자식 컴포넌트에 데이터를 전달할 때는  
속성(props)을 사용한다.

### 자식 컴포넌트

자식은 `props` 옵션으로 받을 속성을 정의한다.

```vue
<script>
export default {
  props: ['title'],
};
</script>
```

### 부모 컴포넌트

부모는 `v-bind`를 사용해 자식에게 값을 전달한다.

```vue
<ChildComponent :title="message" />
```

즉,

- 자식: 받을 속성 정의
- 부모: `v-bind`로 값 전달

구조이다.

---

## 10. Props의 중요한 특징

Props는 **읽기 전용**이다.

즉, 자식 컴포넌트는 부모로부터 전달받은 속성값을 직접 바꾸면 안 된다.

### 이유

데이터를 실제로 소유하고 있는 쪽은 부모이기 때문이다.

즉, 데이터 수정은 그 데이터를 가진 컴포넌트가 해야 한다.

### 중요한 점

부모에서 속성값이 바뀌면  
자식 컴포넌트는 자동으로 다시 렌더링된다.

즉, props는 부모 상태를 자식에게 반영하는 통로라고 이해하면 된다.

---

## 11. 자식에서 props 수정 시 문제

자식 컴포넌트에서 props를 직접 수정하려고 하면 에러가 나거나 경고가 발생할 수 있다.

예:

```js
this.title = '새 제목';
```

이런 방식은 허용되지 않는다.

즉,

- 부모가 가진 데이터는 부모가 수정
- 자식은 전달받아 사용만 함

원칙을 지켜야 한다.

---

## 12. 사용자 정의 이벤트를 이용한 정보 전달

자식이 부모에게 정보를 전달할 때는 이벤트를 사용한다.

즉, 자식은 부모를 직접 수정하는 대신  
"이런 일이 일어났다"는 신호를 이벤트로 보낸다.

이때 사용하는 것이 `$emit()`이다.

기본 형식:

```js
this.$emit('이벤트명', 값1, 값2, ...)
```

---

## 13. 자식 컴포넌트에서 `$emit()`

예:

```js
this.$emit('event-name', eventArgs1, eventArgs2);
```

의미는 다음과 같다.

- `'event-name'` : 부모가 들을 이벤트 이름
- 뒤의 값들 : 부모에게 전달할 데이터

즉, 자식은 이벤트와 함께 필요한 정보를 부모로 올려 보낼 수 있다.

---

## 14. 부모 컴포넌트에서 이벤트 수신

부모는 `v-on` 또는 `@` 축약 표현으로 자식의 이벤트를 받는다.

예:

```vue
<ChildComponent @event-name="handlerMethod" />
```

즉, 자식이 `$emit('event-name', ...)`을 실행하면  
부모의 `handlerMethod`가 호출된다.

---

## 15. 부모-자식 정보 전달 흐름 정리

### 부모 → 자식

Props 사용

```vue
<ChildComponent :title="message" />
```

### 자식 → 부모

이벤트 사용

```js
this.$emit('event-name', value);
```

```vue
<ChildComponent @event-name="handlerMethod" />
```

즉,

- 부모는 props로 값 전달
- 자식은 emit으로 이벤트 전달

구조이다.

---

## 16. 예시

### 자식 컴포넌트

```vue
<template>
  <button @click="sendMessage">전달</button>
</template>

<script>
export default {
  props: ['msg'],
  methods: {
    sendMessage() {
      this.$emit('send-msg', this.msg);
    },
  },
};
</script>
```

### 부모 컴포넌트

```vue
<template>
  <ChildComponent :msg="message" @send-msg="receiveMessage" />
</template>

<script>
import ChildComponent from './ChildComponent.vue';

export default {
  components: {
    ChildComponent,
  },
  data() {
    return {
      message: 'Hello',
    };
  },
  methods: {
    receiveMessage(value) {
      console.log(value);
    },
  },
};
</script>
```

이 예제에서

- 부모는 `message`를 자식에게 props로 전달
- 자식은 버튼 클릭 시 `$emit()`으로 부모에게 전달
- 부모는 이벤트를 받아 처리

하게 된다.

---

## 17. 정리

- 단일 파일 컴포넌트는 컴포넌트 하나를 `.vue` 파일 하나에 작성하는 방식이다.
- template, script, style을 한 파일 안에서 관리할 수 있다.
- 번들링은 여러 모듈을 하나 또는 몇 개의 파일로 묶는 과정이다.
- `@`는 보통 `src` 경로를 가리키는 별칭으로 사용된다.
- `export default`는 컴포넌트의 설정 객체를 내보내는 역할을 한다.
- 전역 컴포넌트는 한 번 등록하면 여러 곳에서 사용할 수 있다.
- 지역 컴포넌트는 사용하는 컴포넌트 안에서 직접 등록한다.
- 부모에서 자식으로는 props로 데이터를 전달한다.
- 자식에서 부모로는 이벤트를 사용해 정보를 전달한다.
- props는 읽기 전용이며 자식이 직접 수정하면 안 된다.
- 부모가 props 값을 바꾸면 자식은 자동으로 다시 렌더링된다.
- 자식은 `$emit()`으로 사용자 정의 이벤트를 발생시킬 수 있다.
- 부모는 `v-on` 또는 `@`로 자식의 이벤트를 수신한다.
