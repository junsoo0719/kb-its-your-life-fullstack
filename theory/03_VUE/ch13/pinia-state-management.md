# ✨ pinia를 이용한 상태 관리

## 1. Pinia 개념

Pinia는 Composition API 방식으로 Vue 애플리케이션의 중앙 집중화된 상태 관리를 제공하는 도구이다.  
여러 컴포넌트가 같은 상태를 함께 사용해야 할 때 주로 사용한다.

Pinia의 핵심은 여러 컴포넌트가 하나의 상태를 공유한다는 점이다.  
즉, 한쪽 컴포넌트에서 상태를 바꾸면 다른 컴포넌트도 같은 값을 보게 된다.

또한 Pinia는 전역 상태를 하나의 거대한 store에 몰아넣는 방식이 아니라, 기능별로 store를 여러 개 나누어 관리하는 구조를 사용한다.  
이 방식은 역할 분리가 명확하고 유지보수도 쉽다는 장점이 있다. 📌

## 2. Pinia 아키텍처

Pinia의 기본 구조는 다음과 같이 이해할 수 있다.

    Vue 컴포넌트 ↔ Pinia Store ↔ 백엔드 API

이 구조에서 store는 컴포넌트와 백엔드 사이에서 상태와 기능을 관리하는 중심 역할을 한다.

### 2-1. State

State는 여러 컴포넌트가 공유하는 데이터 저장소이다.

컴포넌트는 이 state를 바인딩해서 화면에 출력한다.  
그리고 state 값이 바뀌면 Vue의 반응성에 의해 화면도 자동으로 다시 렌더링된다.

즉, state는 "저장된 데이터"라고 이해하면 된다.

### 2-2. Actions

Actions는 실제 동작이나 기능을 담당한다.

예를 들어 데이터 추가, 삭제, 수정 같은 작업은 action 함수에서 처리한다.  
그리고 그 작업 결과를 state에 반영한다.

중요한 점은 state를 직접 수정하기보다 action을 통해 수정하도록 구조를 잡아야 한다는 것이다. ⚠️  
이렇게 해야 상태 변경 흐름이 명확해지고 관리가 쉬워진다.

### 2-3. Getters

Getters는 state를 가공한 결과값이다.

getter는 Vue의 `computed`와 비슷한 개념으로 이해하면 된다.  
즉, 원본 state를 그대로 보여주는 것이 아니라 계산된 값을 제공할 때 사용한다.

정리하면 다음 흐름이다.

- 컴포넌트는 action을 호출한다.
- action은 state를 변경한다.
- getter는 변경된 state를 가공해서 보여준다.

## 3. Store 정의

Pinia에서 store는 `defineStore()`로 정의한다.

기본 형식은 다음과 같다.

    defineStore('스토어명', 함수)

여기서 두 번째 인자로 들어가는 함수는 store를 정의하는 setup 역할을 한다.  
이 함수 안에서 다음 요소들을 작성한다.

- 반응형 상태 정의
- 계산된 상태(computed) 정의
- 상태를 변경하는 action 함수 정의
- 외부에서 사용할 항목 return

즉, store는 필요한 state, getter, action을 만들고 마지막에 외부에 공개할 것들을 객체로 반환하는 구조이다.

## 4. 컴포넌트에서 Store 사용

컴포넌트에서는 `setup()` 안에서 store를 불러와 반응성 있게 연결해서 사용한다.

    const store = useCount1Store();
    const count = computed(() => store.count);
    const increment = store.increment;

이 구조를 보면 다음과 같이 정리할 수 있다.

- `state` → 저장된 데이터
- `actions` → 데이터를 바꾸는 기능
- `getters` → 데이터를 계산해서 보여주는 값
- `store 분리` → 기능별로 관리하기 쉽게 나눔
- `컴포넌트` → store를 가져다 화면에 출력하고 action 호출

즉, 컴포넌트는 데이터를 직접 관리하는 것이 아니라 store를 통해 상태를 읽고, action을 실행하는 역할을 맡는다.

## 5. 분해 할당 시 주의점

`<template>`에서 `store.count`, `store.doubleCount`처럼 길게 쓰는 대신, `<script>`에서 분해 할당을 하고 싶을 수 있다.

예를 들어 다음과 같은 코드이다.

    const { count, doubleCount, increment } = useCounterStore();

하지만 이렇게 하면 action 함수는 정상 작동하더라도 상태 데이터는 반응성을 잃어버릴 수 있다.

그 이유는 다음과 같다.

- action 함수는 분해 할당해도 문제 없음
- 상태 데이터는 분해 할당하면 값만 복사됨
- 따라서 반응형 연결이 끊어질 수 있음

즉, 다음처럼 action만 따로 꺼내 쓰는 것은 가능하다.

    const { increment } = useCounterStore();

하지만 `count`, `doubleCount` 같은 상태 데이터나 getter는 단순 분해 할당하면 반응성을 잃을 수 있으므로 주의해야 한다. ⚠️

## 6. 데이터를 짧게 쓰는 올바른 방법

데이터를 짧게 쓰고 싶다면 store 자체를 유지한 상태에서 `computed`를 이용해 다시 연결하는 방식이 안전하다.

    const store = useCounterStore();
    const doubleCount = computed(() => store.doubleCount);

이렇게 하면 `<template>`에서는 다음처럼 사용할 수 있다.

- `store.count`
- `doubleCount`

즉, store를 기준으로 반응성을 유지하면서 필요한 값만 짧게 꺼내 쓰는 방식이다.

## 7. 읽기 전용으로 데이터 노출하기

다음과 같은 예제를 보자.

    const doneCount = computed(() => {
      return state.todoList.filter((todoItem) => todoItem.done === true).length;
    })

    const todoList = computed(() => state.todoList);

    return { todoList, doneCount, addTodo, deleteTodo, toggleDone };

여기서 중요한 점은 데이터를 그냥 노출하지 않고 `computed`로 감싸서 읽기 전용처럼 노출한다는 것이다.

왜 이렇게 해야 할까?

- 외부 컴포넌트가 state를 직접 수정하지 못하게 하기 위해
- 상태 변경은 action을 통해서만 일어나게 만들기 위해
- 데이터 흐름을 명확하게 유지하기 위해

즉, store 바깥으로 데이터를 내보낼 때는 가능한 한 읽기 전용 형태로 노출하는 것이 좋다. 📌

## 8. App.vue에서 store 사용 예시

App.vue에서는 다음과 같이 store를 사용할 수 있다.

    const todoListStore = useTodoListStore();
    const { todoList, addTodo, deleteTodo, toggleDone } = todoListStore;
    const doneCount = computed(() => todoListStore.doneCount);

여기서 필기 내용상 핵심은 다음과 같다.

- `todoList`는 참조로 사용
- `doneCount`처럼 기본 타입 값은 `computed`로 다시 연결해서 사용

즉, getter나 계산된 결과를 안전하게 사용하려면 `computed(() => store.값)` 형태로 다시 감싸는 방식이 중요하다.

## 9. Pinia 상태의 한계

Pinia의 상태는 메모리에 저장되므로 브라우저를 새로고침하면 초기화된다.

즉, 새로고침 후에도 유지되어야 하는 데이터라면 localStorage, sessionStorage, 쿠키, 또는 백엔드 저장과 함께 사용해야 한다.

이 점은 시험이나 실습에서 자주 나오는 중요한 포인트이다. ⚠️

## 10. Pinia로 자주 관리하는 정보

Pinia로 관리하는 대표적인 정보는 로그인 상태이다.

예를 들어 다음과 같은 정보들을 전역 상태로 관리하기 좋다.

- 로그인 여부
- 사용자 정보
- 인증 토큰
- 장바구니 상태
- 공통 UI 상태

특히 로그인 정보는 여러 페이지와 여러 컴포넌트에서 함께 사용되므로 Pinia로 관리하기에 매우 적합하다.

## 11. 중요 포인트 📌

- Pinia는 Vue 애플리케이션의 중앙 집중화된 상태 관리 도구이다.
- 여러 컴포넌트가 같은 상태를 함께 사용할 수 있다.
- Pinia는 하나의 거대한 store보다 기능별 store 여러 개로 나누는 구조를 사용한다.
- `state`는 저장된 데이터, `actions`는 상태를 바꾸는 기능, `getters`는 계산된 값이다.
- 상태 변경은 직접 하지 말고 action을 통해 처리하는 것이 좋다.
- getter는 `computed`와 비슷한 개념이다.
- 상태 데이터는 단순 분해 할당하면 반응성을 잃을 수 있다.
- action 함수는 분해 할당해도 비교적 안전하다.
- store에서 데이터를 외부로 노출할 때는 읽기 전용 형태로 내보내는 것이 좋다.
- Pinia 상태는 새로고침하면 초기화된다.
- 로그인 정보는 Pinia로 관리하는 대표적인 예시이다.

## 정리 ✅

Pinia는 Vue에서 여러 컴포넌트가 공통으로 사용하는 상태를 효율적으로 관리하기 위한 중앙 집중화 상태 관리 도구이다.  
state는 데이터를 저장하고, action은 상태를 변경하며, getter는 상태를 가공한 값을 제공한다.  
Pinia는 기능별로 store를 분리해서 관리하므로 구조가 깔끔하고 유지보수가 쉽다.  
컴포넌트에서는 store를 불러와 데이터를 화면에 출력하고 action을 호출하며, 상태 데이터는 반응성을 유지하는 방식으로 연결해야 한다.  
특히 상태를 직접 수정하지 않고 action을 통해 바꾸는 구조, 데이터를 읽기 전용으로 노출하는 방식, 분해 할당 시 반응성이 깨질 수 있다는 점, 새로고침 시 상태가 초기화된다는 점은 꼭 기억해야 한다.
