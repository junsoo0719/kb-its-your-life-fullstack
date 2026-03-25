# TodoList 예제 리팩토링

## 1. 리팩토링과 컴포넌트 분할

TodoList 예제를 리팩토링할 때 중요한 핵심은  
UI를 적절한 컴포넌트 단위로 나누는 것이다.

Vue에서는 단순히 화면을 잘게 나누는 것보다,  
**한 번에 변경되는 데이터를 렌더링하는 UI 단위**로 컴포넌트를 분할하는 것이 중요하다.

즉, 데이터 변화의 범위를 기준으로 컴포넌트를 나누는 것이 더 자연스럽다.

---

## 2. 컴포넌트 분할 기준

컴포넌트를 나누는 기준은 다음과 같이 정리할 수 있다.

### 2-1. 변경된 데이터만 다시 렌더링

컴포넌트를 분리하면  
변경된 데이터와 관련된 UI만 다시 렌더링하기 쉬워진다.

즉, 화면 전체를 한 덩어리로 관리하는 것보다  
변화가 일어나는 부분만 독립적으로 관리하기가 더 좋다.

예를 들어 TodoList에서는

- 입력 영역
- 목록 영역
- 목록 한 건
- 버튼 영역

등을 나눠 볼 수 있다.

이렇게 하면 특정 Todo 항목 하나가 바뀌었을 때  
관련된 부분만 다시 처리하도록 구조를 잡기 쉬워진다.

---

### 2-2. 재사용성이 높을수록 컴포넌트로 분리

같은 UI를 여러 군데에서 재사용할 가능성이 높다면  
컴포넌트로 분리하는 것이 좋다.

예를 들면 다음과 같은 것들이 있다.

- 공통 버튼
- 입력 폼
- 목록 한 건
- 상태 표시 영역

즉, 재사용 가치가 높을수록 별도 컴포넌트로 빼는 것이 유리하다.

---

### 2-3. 복잡도가 높을 때 분리

기능이 많아지고 한 파일 안에 코드가 길어질수록  
컴포넌트 단위로 나누는 것이 관리에 유리하다.

컴포넌트 분리를 하면 다음 장점이 있다.

- 관리가 쉬워짐
- 가독성이 좋아짐
- 역할이 분명해짐
- 수정 범위를 줄일 수 있음

즉, 복잡도가 높아질수록 컴포넌트 분할의 효과가 커진다.

---

## 3. TodoList에서 루트 컴포넌트의 역할

TodoList 예제 리팩토링에서는  
데이터 생성, 수정, 삭제를 **루트 컴포넌트(App)** 가 담당하는 구조가 중요하다.

즉, 실제 데이터의 소유자는 루트 컴포넌트이다.

예를 들어 다음과 같은 동작은 루트가 처리한다.

- 할 일 추가
- 할 일 완료 상태 변경
- 할 일 삭제

즉,

- Create
- Update
- Delete

를 루트 컴포넌트가 맡는다.

이유는 데이터가 한곳에서 관리되어야  
상태 흐름이 명확하고 예측 가능해지기 때문이다.

---

## 4. 자식 컴포넌트의 역할

자식 컴포넌트는 보통 다음 역할을 맡는다.

- 부모가 내려준 데이터 표시
- 사용자 이벤트 발생
- 필요한 이벤트를 부모에게 전달

즉, 자식은 화면 표시와 이벤트 전달에 집중하고,  
실제 데이터 변경은 부모가 수행하도록 구조를 잡는다.

이것이 Vue 컴포넌트 설계의 기본 흐름이다.

---

## 5. props를 이용한 데이터 전달

부모는 자식에게 `props`로 데이터를 전달한다.

예:

```vue
<TodoItem :todoItem="todoItem" />
```

여기서

- `:todoItem="todoItem"`  
  부모의 데이터를 자식에게 전달하는 부분이다.

자식 컴포넌트는 이를 `props` 옵션으로 받는다.

```vue
<script>
export default {
  props: ['todoItem'],
};
</script>
```

즉,

- 부모는 값을 내려주고
- 자식은 그 값을 받아 화면에 사용한다

는 구조이다.

---

## 6. props의 의미

`props`는 부모가 자식에게 전달하는 읽기 전용 데이터이다.

즉, 자식은 `todoItem`을 받아서 출력하거나 참조할 수는 있지만  
직접 수정하면 안 된다.

이 점이 중요한 이유는  
데이터를 실제로 소유한 쪽이 부모이기 때문이다.

즉, TodoList에서 한 건의 상태를 바꾸더라도  
자식이 직접 값을 고치는 것이 아니라  
부모에게 요청하는 구조로 가야 한다.

---

## 7. 이벤트 중개

다음과 같은 코드는 이벤트를 중개하는 대표적인 예이다.

```vue
@toggle-completed="$emit('toggle-completed', $event)"
```

이 코드는 자식 컴포넌트가 받은 이벤트를  
다시 상위 부모에게 전달하는 역할을 한다.

즉, **이벤트를 중개하는 역할**이다.

예를 들어 컴포넌트 구조가 다음과 같다고 하자.

- App
- TodoList
- TodoItem

이때 `TodoItem`에서 이벤트가 발생하면  
중간의 `TodoList`가 이벤트를 받아서 다시 상위인 `App`으로 올려 보낼 수 있다.

즉, 중간 컴포넌트는

- 직접 데이터를 바꾸지 않고
- 이벤트만 위로 전달하는 중개 역할

을 할 수 있다.

---

## 8. 이벤트 전달 흐름

자식에서 부모로 이벤트를 올릴 때는 `$emit()`을 사용한다.

예:

```js
this.$emit('toggle-completed', id);
```

부모는 이를 이벤트 핸들러로 받는다.

```vue
<TodoItem @toggle-completed="toggleCompleted" />
```

중간 컴포넌트가 있으면 다시 다음처럼 넘길 수도 있다.

```vue
<TodoItem @toggle-completed="$emit('toggle-completed', $event)" />
```

즉, 이벤트는 아래에서 위로 전달되며  
필요하면 중간 컴포넌트가 그대로 중계할 수 있다.

---

## 9. TodoList 리팩토링 구조 예시

TodoList를 리팩토링하면 보통 다음처럼 나눌 수 있다.

- `App.vue`
- `TodoInput.vue`
- `TodoList.vue`
- `TodoItem.vue`

### `App.vue`

- 전체 데이터 관리
- 추가, 수정, 삭제 처리
- 루트 컴포넌트 역할

### `TodoInput.vue`

- 입력창과 추가 버튼 UI
- 입력 이벤트를 부모에 전달

### `TodoList.vue`

- 목록 전체를 렌더링
- 여러 TodoItem을 반복 출력

### `TodoItem.vue`

- Todo 한 건 출력
- 완료 토글, 삭제 버튼 이벤트 발생

이렇게 나누면 각 컴포넌트의 역할이 훨씬 분명해진다.

---

## 10. 루트 컴포넌트가 데이터 소유자인 이유

루트에서 데이터를 관리하면 다음 장점이 있다.

### 10-1. 상태 흐름이 단순해짐

데이터가 여기저기 흩어지지 않고  
한 곳에서 생성, 수정, 삭제된다.

### 10-2. 자식 컴포넌트가 단순해짐

자식은 화면 출력과 이벤트 전달에 집중할 수 있다.

### 10-3. 유지보수가 쉬워짐

문제가 생겼을 때 데이터 흐름을 추적하기 쉬워진다.

즉, TodoList처럼 상태를 관리하는 예제에서는  
루트 중심 데이터 관리가 매우 중요하다.

---

## 11. 프로젝트 구조 복습

리팩토링 예제와 함께 다시 기억해야 할 내용이 있다.

### 11-1. 프로젝트 구조

Vue 프로젝트에서는 파일 구조가 중요하다.  
라이브러리나 앱의 진입 구조가 바뀌면 보통 `main.js`를 수정하게 된다.

즉, `main.js`는 앱 시작점 역할을 한다.

---

### 11-2. props

가장 중요한 개념 중 하나이다.

- 부모 → 자식 데이터 전달
- 읽기 전용
- 자식은 직접 수정 불가

즉, Vue 컴포넌트 간 데이터 전달의 기본이다.

---

### 11-3. emit

이벤트 처리에서 가장 중요한 개념이다.

- 자식 → 부모 정보 전달
- `$emit()` 사용
- 부모는 `@이벤트명`으로 수신

즉, 자식이 부모에게 직접 데이터를 바꾸는 대신  
이벤트를 통해 변경 요청을 올리는 구조이다.

---

## 12. 예시 코드 흐름

### App.vue

```vue
<template>
  <TodoList
    :todolist="todolist"
    @toggle-completed="toggleCompleted"
    @delete-todo="deleteTodo"
  />
</template>

<script>
import TodoList from './components/TodoList.vue';

export default {
  components: {
    TodoList,
  },
  data() {
    return {
      todolist: [],
    };
  },
  methods: {
    toggleCompleted(id) {
      // 완료 상태 변경
    },
    deleteTodo(id) {
      // 삭제 처리
    },
  },
};
</script>
```

### TodoList.vue

```vue
<template>
  <TodoItem
    v-for="todoItem in todolist"
    :key="todoItem.id"
    :todoItem="todoItem"
    @toggle-completed="$emit('toggle-completed', $event)"
    @delete-todo="$emit('delete-todo', $event)"
  />
</template>

<script>
import TodoItem from './TodoItem.vue';

export default {
  props: ['todolist'],
  components: {
    TodoItem,
  },
};
</script>
```

### TodoItem.vue

```vue
<template>
  <li>
    <span @click="$emit('toggle-completed', todoItem.id)">
      {{ todoItem.todo }}
    </span>
    <button @click="$emit('delete-todo', todoItem.id)">삭제</button>
  </li>
</template>

<script>
export default {
  props: ['todoItem'],
};
</script>
```

이 구조에서

- 데이터는 App이 소유
- TodoList는 목록 출력과 이벤트 중개
- TodoItem은 개별 항목 출력과 이벤트 발생

을 맡는다.

---

## 13. 정리

- 단일 컴포넌트가 너무 커지면 역할에 따라 분리하는 것이 좋다.
- 컴포넌트는 한 번에 변경되는 데이터를 렌더링하는 UI 단위로 나누는 것이 중요하다.
- 변경된 데이터만 다시 렌더링할 수 있도록 구조를 잡는 것이 유리하다.
- 재사용성이 높을수록 컴포넌트로 분리하는 것이 좋다.
- 복잡도가 높을수록 컴포넌트 분리의 효과가 크다.
- TodoList 예제에서는 데이터 생성, 수정, 삭제를 루트 컴포넌트(App)가 담당한다.
- 자식 컴포넌트는 props로 데이터를 받고, 화면 출력과 이벤트 전달에 집중한다.
- `:todoItem="todoItem"`은 부모가 자식에게 데이터를 props로 전달하는 예이다.
- `@toggle-completed="$emit('toggle-completed', $event)"`는 이벤트를 상위 부모로 중개하는 방식이다.
- `props`는 부모에서 자식으로 데이터를 전달하는 가장 중요한 개념이다.
- `emit`은 자식에서 부모로 이벤트를 전달하는 핵심 개념이다.
- 프로젝트 구조가 바뀌면 보통 `main.js`도 함께 수정될 수 있다.
