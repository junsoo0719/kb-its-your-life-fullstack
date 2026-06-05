# 컴포넌트 심화

## 1. CSS 적용 범위

Vue 컴포넌트에서는 스타일 적용 범위를 어떻게 관리하느냐가 중요하다.

---

## 2. 전역 CSS

전역 CSS는 보통 `src/main.js`에서 import한 CSS 파일을 의미한다.

예:

```js
import './assets/main.css';
```

이렇게 import한 CSS는 특정 컴포넌트만이 아니라 **페이지 전체**에 적용된다.

즉, 프로젝트 전역에서 공통 스타일을 줄 때 사용한다.

---

## 3. 범위 CSS

범위 CSS는 특정 컴포넌트 안에서만 스타일이 적용되도록 제한하는 방식이다.

Vue는 컴포넌트가 렌더링하는 요소에 **추가 식별자**를 붙여서 스타일 충돌을 피한다.

즉, 다른 컴포넌트와 같은 클래스명을 써도 서로 충돌하지 않게 할 수 있다.

---

## 4. CSS 적용 순서

CSS는 **import 순서**에 따라 적용 순서가 달라진다.

즉, 나중에 불러온 스타일이 앞의 스타일을 덮어쓸 수 있다.

그래서 전역 CSS, 컴포넌트 CSS, 외부 라이브러리 CSS를 함께 쓸 때는  
import 순서를 잘 보는 것이 중요하다.

---

## 5. `<style scoped>`

Vue에서 가장 많이 쓰는 방법은 `<style scoped>`이다.

```vue
<style scoped>
.title {
  color: blue;
}
</style>
```

이렇게 하면 해당 컴포넌트가 렌더링한 요소에만 스타일이 적용된다.

즉,

- 다른 컴포넌트와 스타일 충돌을 줄일 수 있고
- 관리가 쉬워지며
- 실무에서도 많이 사용하는 방식이다

그래서 보통 **권장 방법**으로 본다.

---

## 6. CSS 모듈

Vue에서는 CSS 모듈도 사용할 수 있다.

```vue
<style module>
.title {
  color: red;
}
</style>
```

이 방식은 클래스명을 모듈처럼 관리해서  
스타일 충돌을 더 강하게 방지할 수 있다.

즉, 범위 CSS와 비슷한 목적을 가지지만  
클래스명을 별도로 참조해서 사용하는 방식이다.

---

## 7. 슬롯(slot)

슬롯은 부모 컴포넌트와 자식 컴포넌트 사이에서  
**템플릿을 전달하는 방법**이다.

일반 props는 데이터를 전달하지만,  
슬롯은 **화면 조각(template)** 을 전달한다는 점이 다르다.

즉,

- 부모: 내용을 제공
- 자식: 어디에 출력할지 결정

하는 구조이다.

---

## 8. 슬롯의 기본 개념

자식 컴포넌트 안에 `<slot>`을 두면  
부모가 넘긴 템플릿이 그 위치에 렌더링된다.

예:

```vue
<slot>Item</slot>
```

여기서 `Item`은 **기본(default) 내용**이다.

즉,

- 부모가 아무 내용도 넘기지 않으면 `Item`이 보이고
- 부모가 내용을 넘기면 그 내용이 대신 표시된다

---

## 9. 슬롯의 기본 사용법

슬롯의 기본 사용 흐름은 다음과 같다.

1. 부모 컴포넌트가 자식 컴포넌트에 템플릿을 전달한다.
2. 자식 컴포넌트는 `<slot>` 위치에 그 템플릿을 렌더링한다.

예:

### 자식 컴포넌트

```vue
<template>
  <div>
    <slot>기본 내용</slot>
  </div>
</template>
```

### 부모 컴포넌트

```vue
<MyComponent>
  <h3>부모가 전달한 내용</h3>
</MyComponent>
```

이 경우 자식의 `<slot>` 위치에  
`<h3>부모가 전달한 내용</h3>`이 들어간다.

---

## 10. 명명된 슬롯

자식 컴포넌트 안에 슬롯 영역이 여러 개 있을 수도 있다.  
이럴 때는 슬롯마다 이름을 붙여서 구분한다.

```vue
<slot name="header"></slot>
<slot name="default"></slot>
<slot name="footer"></slot>
```

이런 방식을 **명명된 슬롯(named slot)** 이라고 한다.

부모는 각 슬롯 이름에 맞게 내용을 전달할 수 있다.

명명된 슬롯은 보통 **화면 레이아웃 관리** 목적으로 많이 사용된다.

예를 들면

- header
- content
- footer
- sidebar

같은 구조를 만들 때 유용하다.

---

## 11. 범위 슬롯

범위 슬롯(scoped slot)은  
부모가 자식에게 템플릿을 넘기면서,  
그 템플릿 안에서 **자식의 데이터**를 사용할 수 있게 하는 방식이다.

즉,

- 템플릿은 부모가 작성
- 데이터는 자식이 제공

하는 구조이다.

이럴 때 전달된 데이터는 슬롯 템플릿 내부 범위에서만 사용할 수 있다.

---

## 12. 범위 슬롯의 흐름

범위 슬롯은 보통 다음 순서로 이해하면 된다.

1. 부모가 바인딩된 템플릿을 자식 컴포넌트의 슬롯으로 전달한다.
2. 자식은 슬롯을 통해 부모에게 속성을 전달한다.
3. 부모는 `<template>` 범위 안에서 전달받은 참조자로 데이터를 사용한다.

즉, 부모가 화면 모양을 정하되  
그 안에 들어갈 실제 데이터는 자식이 제공하는 구조이다.

---

## 13. props

props는 부모에서 자식으로 데이터를 전달하는 가장 기본적인 방법이다.

### 특징

- 부모 → 자식 전달
- 자식은 읽기 전용으로 사용
- 자식이 직접 수정하면 안 됨

즉, props는 **읽기 전용**이다.

---

## 14. props 기본값

props에 기본값을 주려면 객체 형태로 작성해야 한다.

즉, 단순 배열이 아니라  
`이름: 설정값` 구조로 정의해야 한다.

예:

```js
props: {
  title: {
    type: String,
    default: '기본 제목'
  }
}
```

기본값을 지정하지 않으면 보통 `undefined`가 된다.

---

## 15. props를 객체 하나로 전달하는 경우

props가 너무 많아지면  
하나씩 여러 개 넘기기보다 객체 하나로 묶어서 전달하는 방법이 더 편할 수 있다.

예:

```vue
<ChildComponent :todoItem="todoItem" />
```

이렇게 하면 관련된 데이터를 하나의 객체로 관리할 수 있어서  
코드가 더 단순해질 수 있다.

---

## 16. 사용자 정의 이벤트

자식이 부모에게 정보를 전달할 때는 사용자 정의 이벤트를 사용한다.

기본 형식은 다음과 같다.

```js
$emit('이벤트명', 전달값);
```

예:

```js
this.$emit('toggle-completed', id);
```

즉, 자식은 부모를 직접 수정하지 않고  
이벤트를 올려 보내는 방식으로 변경을 요청한다.

---

## 17. `$event.target`

브라우저 이벤트를 처리할 때는 `$event.target`을 자주 사용한다.

`target`은 **실제로 이벤트가 발생한 요소**를 가리킨다.

예를 들어 체크박스라면 다음처럼 활용할 수 있다.

```vue
@change="$emit('check-changed', { id, checked: $event.target.checked })"
```

이 코드는 자식 컴포넌트에서

- 어떤 항목인지 `id`
- 체크 여부 `checked`

를 묶어서 부모에게 전달하는 예이다.

부모는 이를 다음처럼 받을 수 있다.

```vue
<ChildComponent @check-changed="handlerMethod" />
```

즉, 자식은 이벤트와 데이터를 함께 부모에 전달할 수 있다.

---

## 18. `v-if` / `v-else` 주의사항

`v-if`와 `v-else`는 반드시 **연속해서 배치**되어야 한다.

즉, 두 요소 사이에 다른 요소가 끼어 있으면 안 된다.

예를 들어 이런 식은 올바르지 않다.

```html
<div v-if="ok">A</div>
<p>중간 요소</p>
<div v-else>B</div>
```

이 경우 `v-else`는 앞의 `v-if`와 연결되지 않는다.

즉, `v-if`와 `v-else`는 바로 이어져 있어야 한다.

---

## 19. 배열 원소 찾기

배열 원소를 찾아서 **수정**할 때는 `find()`가 더 적합한 경우가 많다.

```js
const todo = todolist.find((item) => item.id === id);
```

이 경우 실제 원소를 바로 가져올 수 있다.

반면 `splice()` 등을 사용해  
원소를 제거하거나 추가하려면 `findIndex()`가 더 유용하다.

```js
const index = todolist.findIndex((item) => item.id === id);
this.todolist.splice(index, 1);
```

즉,

- 원소 자체를 다룰 때 → `find()`
- 위치를 찾아 삭제 / 삽입할 때 → `findIndex()`

로 이해하면 된다.

---

## 20. Font Awesome 관련 클래스

Font Awesome 아이콘 클래스는 보통 다음처럼 구성된다.

- `fa` : Font Awesome 기본 계열
- `r` : regular
- `s` : solid

예를 들면 다음과 같은 조합이 자주 나온다.

```html
<i class="fas fa-home"></i> <i class="far fa-user"></i>
```

즉, 아이콘 종류와 스타일 계열을 함께 조합해서 사용한다.

---

## 21. Font Awesome import

외부 CDN을 이용해서 Font Awesome을 불러올 수 있다.

```html
<style>
  @import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css');
</style>
```

이렇게 하면 아이콘 관련 CSS를 프로젝트에서 사용할 수 있다.

---

## 22. 중요한 개념 정리

이번 단원에서 중요한 개념은 다음과 같다.

### 컴포넌트

Vue 애플리케이션을 구성하는 기본 단위이다.

### 단일 컴포넌트의 `style scoped`

컴포넌트 범위 안에서만 스타일을 적용해 충돌을 줄이는 중요한 방법이다.

### 슬롯

부모가 자식에게 템플릿을 전달하는 핵심 개념이다.  
특히 매우 중요하다.

### 사용자 정의 `v-model`

사용자 정의 컴포넌트에서도 `v-model`처럼 동작하도록 설계할 수 있다.

### provide / inject

깊은 컴포넌트 계층에서 props를 계속 전달하지 않고  
상위에서 하위로 값을 공유할 때 사용할 수 있다.

### teleport

컴포넌트의 템플릿 일부를 현재 위치가 아닌  
다른 DOM 위치에 렌더링할 때 사용한다.

---

## 23. 정리

- 전역 CSS는 `main.js`에서 import한 스타일이며 페이지 전체에 적용된다.
- 범위 CSS는 컴포넌트 렌더링 요소에 식별자를 추가해 스타일 충돌을 피한다.
- CSS 적용 순서는 import 순서의 영향을 받는다.
- `<style scoped>`는 해당 컴포넌트에만 스타일을 적용하는 권장 방식이다.
- CSS 모듈도 스타일 충돌을 피하는 방법 중 하나이다.
- 슬롯은 부모가 자식에게 템플릿을 전달하는 방식이다.
- 기본 슬롯은 `<slot>기본내용</slot>` 형태로 사용할 수 있다.
- 명명된 슬롯은 여러 슬롯 영역을 구분할 때 사용한다.
- 범위 슬롯은 부모 템플릿 안에서 자식의 데이터를 사용할 수 있게 한다.
- props는 부모에서 자식으로 전달되는 읽기 전용 데이터이다.
- props 기본값은 객체 형태로 정의해야 한다.
- props가 많으면 객체 하나로 묶어 전달하는 것이 편할 수 있다.
- 자식은 `$emit()`으로 부모에게 사용자 정의 이벤트를 전달한다.
- `$event.target`은 실제 이벤트가 발생한 요소를 가리킨다.
- `v-if`와 `v-else`는 연속해서 배치해야 한다.
- 배열 원소 수정에는 `find()`, 삭제나 삽입에는 `findIndex()`가 유리하다.
- 중요한 개념으로는 컴포넌트, scoped style, 슬롯, 사용자 정의 `v-model`, provide/inject, teleport가 있다.

---

## 24. 동적 컴포넌트

동적 컴포넌트는 화면의 **같은 위치에 여러 컴포넌트 중 하나를 바꿔 가며 표시**해야 할 때 사용한다.

이때 `<component>` 요소를 사용한다.

```vue
<component :is="currentTab"></component>
```

여기서 `is` 속성에 바인딩한 `currentTab` 값이  
등록된 컴포넌트 이름과 같으면 `<component>` 대신 해당 컴포넌트가 렌더링된다.

즉,

- 현재 탭 값이 `Tab1`이면 `Tab1` 컴포넌트 렌더링
- 현재 탭 값이 `Tab2`이면 `Tab2` 컴포넌트 렌더링

처럼 동작한다.

여기서 말하는 **등록된 컴포넌트 이름**은  
`components` 옵션에 등록한 이름을 의미한다.

예:

```vue
<script>
import Tab1 from './Tab1.vue';
import Tab2 from './Tab2.vue';

export default {
  components: {
    Tab1,
    Tab2,
  },
  data() {
    return {
      currentTab: 'Tab1',
    };
  },
};
</script>
```

---

## 25. 특정 클래스 활성화

탭 메뉴처럼 현재 선택된 항목만 특별한 스타일을 주고 싶을 때는  
다음과 같이 클래스 바인딩을 사용할 수 있다.

```vue
:class="{ active: tab.id === currentTab }"
```

의미는 다음과 같다.

- `tab.id === currentTab` 이면 `active` 클래스 적용
- 아니면 적용하지 않음

즉, 현재 선택된 탭에만 활성화 스타일을 줄 수 있다.

---

## 26. `keep-alive`

동적 컴포넌트를 전환할 때는  
기본적으로 이전 컴포넌트가 제거되고 새 컴포넌트가 다시 생성될 수 있다.

이때 상태를 유지하고 싶으면 `keep-alive`를 사용한다.

```vue
<keep-alive>
  <component :is="currentTab"></component>
</keep-alive>
```

`keep-alive`는 컴포넌트를 **캐시**해서  
다시 돌아왔을 때 이전 상태를 유지할 수 있게 해 준다.

예를 들면

- 입력 중이던 값 유지
- 스크롤 위치 유지
- 내부 상태 유지

같은 효과를 기대할 수 있다.

즉, 동적 컴포넌트를 전환할 때 상태를 보존하고 싶을 때 유용하다.

---

## 27. 사용자 정의 `v-model`

Vue에서는 사용자 정의 컴포넌트에도 `v-model`을 적용할 수 있다.

부모 컴포넌트에서 다음과 같이 사용한다.

```vue
<ChildComponent v-model:message="parentMessage" />
```

이 의미는  
자식 컴포넌트의 `message` 속성과  
부모 컴포넌트의 `parentMessage`를 연결하겠다는 뜻이다.

즉,

- 부모 → 자식 : `message` props 전달
- 자식 → 부모 : `update:message` 이벤트 전달

구조가 자동으로 연결된다.

---

## 28. 사용자 정의 `v-model`의 자식 컴포넌트 작성

자식 컴포넌트에서는 먼저 props를 정의해야 한다.

```vue
<script>
export default {
  props: {
    message: String,
  },
};
</script>
```

여기서 `String`은 문자열 타입을 의미하는 생성자 함수이다.  
비슷하게 다음과 같은 것들도 사용할 수 있다.

- `String`
- `Number`
- `Boolean`
- `Array`
- `Object`

즉, props 타입 지정에는 생성자 함수명을 사용한다.

---

## 29. 사용자 정의 `v-model`의 템플릿 예시

자식 컴포넌트는 다음처럼 작성할 수 있다.

```vue
<template>
  <input
    type="text"
    :value="message"
    @input="$emit('update:message', $event.target.value)"
  />
</template>
```

이 예제의 의미는 다음과 같다.

- `:value="message"` → 부모가 전달한 값을 보여 줌
- `@input="$emit('update:message', ...)"` → 입력값이 바뀌면 부모에게 다시 전달

즉, `v-model:message`가 양방향처럼 동작하게 만든다.

여기서 중요한 점은 이벤트 이름이 반드시

```text
update:속성명
```

형식이어야 한다는 것이다.

즉, 속성명이 `message`이면 이벤트명은 `update:message`가 되어야 한다.

---

## 30. props 전달의 한계

props는 부모에서 자식으로 데이터를 전달하는 기본 방법이지만,  
컴포넌트 계층이 깊어질수록 문제가 생길 수 있다.

예를 들어 상위 컴포넌트의 데이터를  
중간 컴포넌트들을 거쳐서 계속 아래로 전달해야 할 수 있다.

이런 상황을 흔히 **props drilling**처럼 이해할 수 있다.

즉,

- 실제로는 중간 컴포넌트가 직접 쓰지 않는 값인데도
- 아래 자식에게 넘겨 주기 위해 계속 전달해야 하는 문제

가 생길 수 있다.

---

## 31. provide / inject

이 문제를 완화하기 위해 사용할 수 있는 기능이 `provide`와 `inject`이다.

### provide

상위 컴포넌트가 공용 데이터를 제공하는 기능이다.

### inject

하위 컴포넌트 트리 안의 어느 컴포넌트든  
필요한 데이터를 주입받아 사용할 수 있는 기능이다.

즉,

- 상위가 제공
- 하위가 필요할 때 직접 주입받아 사용

하는 구조이다.

---

## 32. 부모 컴포넌트에서 `provide`

부모 컴포넌트에서는 `provide()`를 사용해 데이터를 제공할 수 있다.

```js
import { computed } from 'vue';

export default {
  provide() {
    return {
      icons: { ... },
      doneCount: computed(() => {
        return this.songs.filter((s) => s.done === true).length;
      }),
    };
  }
};
```

여기서

- `icons` : 일반 속성
- `doneCount` : 계산된 속성

이다.

또한 `this.songs`는 반응형 데이터이다.

---

## 33. `computed(() => ...)` 에서 화살표 함수가 필요한 이유

`provide()` 안에서 `computed`를 사용할 때는  
보통 다음처럼 화살표 함수를 사용한다.

```js
computed(() => {
  return this.songs.filter((s) => s.done === true).length;
});
```

이유는 `this` 때문이다.

화살표 함수는 바깥 함수의 `this`를 그대로 사용하므로  
여기서는 `provide()`의 `this`, 즉 컴포넌트 인스턴스를 참조할 수 있다.

즉, `this.songs` 같은 반응형 데이터에 접근하기 위해  
화살표 함수를 쓰는 것이 중요하다.

---

## 34. 자식 컴포넌트에서 `inject`

자식 컴포넌트에서는 `inject`를 사용해 필요한 데이터를 받을 수 있다.

```js
export default {
  inject: ['icons', 'doneCount'],
};
```

이렇게 하면 자식 안에서 다음처럼 사용할 수 있다.

- `this.icons`
- `this.doneCount`

템플릿에서는 `this` 없이도 사용할 수 있다.

예:

```vue
<span>{{ doneCount }}</span>
<i :class="icons.checked"></i>
```

즉, 제공된 데이터는 자식의 자기 속성처럼 사용할 수 있다.

---

## 35. Teleport

`Teleport`는 컴포넌트 템플릿의 일부를  
현재 컴포넌트 위치가 아니라 **다른 DOM 위치로 보내서 렌더링**하는 기능이다.

즉, 논리적으로는 자식 컴포넌트 안에 있지만  
실제 렌더링 위치는 다른 곳이 된다.

예를 들면 모달 창 같은 UI에서 자주 사용한다.

---

## 36. Modal과 Teleport

모달(modal)은 화면 위에 떠서 사용자 입력을 받는 팝업형 UI이다.

모달은 보통 화면의 최상위 쪽에서 렌더링되는 것이 관리하기 편하다.

이때 `Teleport`를 사용하면  
컴포넌트 안에서 모달을 작성하더라도 실제 DOM은 `body` 같은 바깥쪽에 렌더링할 수 있다.

예:

```vue
<Teleport to="body">
  <div class="modal">
    모달 내용
  </div>
</Teleport>
```

즉,

- 작성 위치와
- 실제 렌더링 위치를 분리할 수 있다

는 점이 중요하다.

---

## 37. 지금까지의 일반 컴포넌트 로딩 방식

지금까지의 일반적인 컴포넌트 방식은  
`index.html`이 로드되고, 관련 `.js` 파일과 컴포넌트 코드가 함께 로드되는 구조이다.

즉, 당장 화면에 보이지 않는 컴포넌트라도  
초기에 미리 로딩될 수 있다.

이 방식은 단순하지만  
프로젝트가 커질수록 초기 로딩 부담이 커질 수 있다.

---

## 38. 비동기 컴포넌트

비동기 컴포넌트는 **실제로 필요한 시점에 관련 파일을 로딩**하는 방식이다.

즉, 처음부터 전부 불러오는 것이 아니라  
필요할 때 로딩해서 성능을 개선할 수 있다.

이를 위해 `defineAsyncComponent`를 사용한다.

```js
import { defineAsyncComponent } from 'vue';
```

예:

```js
const AsyncComponent = defineAsyncComponent(
  () => import('./AsyncComponent.vue'),
);
```

이 코드는 `AsyncComponent`가 실제로 사용되는 시점에  
`./AsyncComponent.vue`를 동적으로 로드한다.

즉,

- 초기 로딩 부담 감소
- 큰 컴포넌트 분리 가능
- 성능 최적화에 도움

이라는 장점이 있다.

---

## 39. 정리

- 동적 컴포넌트는 같은 위치에 여러 컴포넌트를 바꿔 가며 렌더링할 때 사용한다.
- `<component :is="...">`에서 `is` 값은 등록된 컴포넌트 이름과 연결된다.
- 클래스 바인딩으로 현재 활성 탭 스타일을 줄 수 있다.
- `keep-alive`는 동적 컴포넌트 상태를 캐시해 유지할 수 있게 해 준다.
- 사용자 정의 `v-model`은 `props + update:속성명 이벤트` 구조로 동작한다.
- props 전달이 깊어지면 계층 구조를 따라 계속 전달해야 하는 문제가 생길 수 있다.
- `provide`는 상위 컴포넌트가 공용 데이터를 제공하는 기능이다.
- `inject`는 하위 컴포넌트가 필요한 데이터를 직접 주입받아 사용하는 기능이다.
- `computed(() => ...)`는 `this`를 올바르게 사용하기 위해 화살표 함수가 중요하다.
- Teleport는 템플릿 일부를 다른 DOM 위치에 렌더링하는 기능이다.
- 모달은 Teleport와 함께 자주 사용된다.
- 비동기 컴포넌트는 실제 사용 시점에 관련 파일을 로딩한다.
- `defineAsyncComponent`를 사용하면 동적 import 기반 비동기 컴포넌트를 만들 수 있다.
