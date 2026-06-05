# Composition API

## 1. 개념

Composition API는 Vue에서 상태, 계산 로직, 감시 로직, 생명주기 로직을 함수 중심으로 작성하는 방식이다.

기존 Options API에서 사용하던 `data`, `methods`, `computed` 같은 옵션 대신 `setup()` 안에서 필요한 기능을 직접 구성한다.

`setup()` 메서드는 컴포넌트 초기화 작업을 정의하는 곳이며, `beforeCreate`, `created` 단계에서 호출된다.

```js
import { ref } from 'vue';

export default {
  setup() {
    const x = ref(10);
    const y = ref(20);

    return { x, y };
  },
};
```

위 코드에서

- `ref(10)`의 `10`은 초기값이다.
- `x`, `y`는 반응형 데이터이다.
- `return { x, y }`로 반환해야 템플릿에서 사용할 수 있다.
- 값 재할당이 아닌 반응성 유지를 위해 `let`보다 `const` 사용을 권장한다.

## 2. setup() 메서드

`setup()`은 Composition API의 시작점이다.

### 2.1 역할

- 상태 데이터 선언
- 계산된 속성 선언
- 감시 로직 작성
- 생명주기 함수 등록
- 템플릿에서 사용할 값과 함수 반환

### 2.2 매개변수

`setup()`은 두 개의 매개변수를 받을 수 있다.

```js
setup(props, context) {
  // props : 부모 컴포넌트가 전달한 속성
  // context : 컴포넌트 컨텍스트
}
```

#### props

부모 컴포넌트로부터 전달받는 속성이다.

#### context

컴포넌트 컨텍스트로, 기존 Options API에서의 `this`와 비슷한 역할을 한다.
이 객체를 통해 `emit()` 같은 기능에 접근할 수 있다.

```js
setup(props, context) {
  context.emit('add-todo', todo);
}
```

## 3. ref()를 이용한 상태 데이터

`ref()`는 기본 데이터 타입의 반응형 데이터를 만들 때 사용한다.

즉, `Number`, `String`, `Boolean` 같은 primitive type을 반응형으로 다룰 때 적합하다.

```js
import { ref } from 'vue';

export default {
  setup() {
    const x = ref(10);
    const name = ref('Vue');
    const done = ref(false);

    return { x, name, done };
  },
};
```

### 3.1 특징

- `data` 옵션에 해당하는 역할을 한다.
- `ref()`의 반환값은 단순 값이 아니라 객체이다.
- 스크립트에서는 반드시 `.value`로 접근해야 한다.

```js
const x = ref(10);

x.value = 20; // 정상
x = 20; // 반응성 상실, 잘못된 사용
```

### 3.2 객체도 ref()로 생성 가능

객체도 `ref()`로 만들 수 있지만, 접근 시 `.value`가 필요하다.

```js
const user = ref({ name: 'Tom', age: 20 });

console.log(user.value.name);
console.log(user.value.age);
```

시험에서는 `ref()`를 사용하면 **스크립트에서는 `.value`가 필요하다**는 점이 중요하다.

## 4. reactive()를 이용한 상태 데이터

`reactive()`는 객체, 배열과 같은 참조형 데이터를 반응형으로 만들 때 사용한다.

```js
import { reactive } from 'vue';

export default {
  setup() {
    const state = reactive({
      x: 10,
      y: 20,
      todos: [],
    });

    return { state };
  },
};
```

### 4.1 특징

- 참조형 데이터에 대한 반응성을 생성한다.
- 객체의 멤버를 직접 접근할 수 있다.
- `ref()`와 달리 보통 `.value` 없이 접근한다.

```js
state.x = 30;
state.todos.push({ id: 1, text: '공부하기' });
```

### 4.2 ref()와 reactive() 차이

- `ref()` : 기본형 데이터 처리에 적합
- `reactive()` : 객체, 배열 같은 참조형 데이터 처리에 적합

즉,

- 숫자 하나, 문자열 하나 → `ref()`
- 여러 속성을 가진 객체 → `reactive()`

## 5. computed()

`computed()`는 계산된 속성을 만들 때 사용한다.
기존 Options API의 `computed` 옵션과 같은 역할이다.

```js
import { ref, computed } from 'vue';

export default {
  setup() {
    const x = ref(10);
    const y = ref(20);

    const result = computed(() => {
      return x.value + y.value;
    });

    return { x, y, result };
  },
};
```

### 5.1 특징

- 어떤 값을 계산해서 새 값을 만든다.
- 의존하는 반응형 데이터가 바뀌면 자동으로 다시 계산된다.
- 캐시를 사용하므로 같은 결과를 반복 계산하지 않는다.

즉, `computed()`는 **값을 만들어 내는 용도**이며, **성능 최적화에 유리하다**.

## 6. watch()

`watch()`는 특정 반응형 데이터의 변화를 감시할 때 사용한다.

```js
import { ref, watch } from 'vue';

export default {
  setup() {
    const x = ref(10);

    watch(x, (current, old) => {
      console.log('현재값:', current);
      console.log('이전값:', old);
    });

    return { x };
  },
};
```

### 6.1 특징

- 감시 대상 데이터가 변경되면 핸들러 함수가 실행된다.
- 첫 번째 인자: 감시할 대상
- 두 번째 인자: 처리 함수
- `current`, `old`는 `ref 객체`가 아니라 실제 값이다.

즉, `watch(x, ...)`에서 `current`, `old`는 `x.value`에 해당하는 값이다.

### 6.2 computed와 차이

- `computed()` : 값을 계산해서 반환
- `watch()` : 값이 바뀌었을 때 별도의 작업 수행

또한 `watch()`는 캐시를 사용하지 않는다.

## 7. watchEffect()

`watchEffect()`는 함수 내부에서 사용한 반응형 데이터를 자동으로 추적해서 실행한다.

```js
import { ref, watchEffect } from 'vue';

export default {
  setup() {
    const x = ref(10);
    const y = ref(20);

    watchEffect(() => {
      console.log(x.value + y.value);
    });

    return { x, y };
  },
};
```

### 7.1 특징

- 함수 내부에서 사용한 반응형 데이터가 변경되면 다시 실행된다.
- 감시 대상을 직접 지정하지 않아도 된다.
- 이전 값(old value)을 제공하지 않는다.
- 주로 페이지 이동, 데이터 로드, 부수 효과 처리에 많이 사용한다.

### 7.2 watch와 비교

#### watch

- 감시 대상을 명시적으로 지정한다.
- 이전 값(old value)을 사용할 수 있다.

#### watchEffect

- 함수 내부에서 사용한 반응형 데이터를 자동 추적한다.
- 이전 값은 제공하지 않는다.

### 7.3 공통점

둘 다 반응형 데이터가 바뀌면 실행되는 감시 함수이다.

## 8. Composition API의 생명주기

Composition API에서는 생명주기 메서드 이름이 함수 형태로 바뀐다.

기존 Options API의 `beforeCreate()`, `created()`는 `setup()`이 대신한다.

나머지 생명주기 메서드는 앞에 `on`이 붙은 함수 형태를 사용한다.

예시:

- `mounted` → `onMounted`
- `beforeMount` → `onBeforeMount`
- `updated` → `onUpdated`
- `unmounted` → `onUnmounted`

```js
import { onMounted } from 'vue';

export default {
  setup() {
    onMounted(() => {
      console.log('마운트 완료');
    });
  },
};
```

### 8.1 특징

- `beforeCreate`, `created` 기능은 `setup()`으로 대체된다.
- 나머지는 `on + 생명주기이름` 형태의 함수로 작성한다.
- 부모가 mount되기 이전의 과정에서 자식 컴포넌트의 `setup()`이 먼저 실행될 수 있다.

## 9. `<script setup>`

Vue에서는 일반적인 `setup()` 함수보다 `<script setup>` 문법을 권장한다.

```vue
<script setup>
import { ref, computed } from 'vue';

const x = ref(10);
const y = ref(20);

const result = computed(() => x.value + y.value);
</script>

<template>
  <div>{{ result }}</div>
</template>
```

### 9.1 장점

- 상용구 코드가 줄어들어 더 간결하다.
- 런타임 성능이 더 좋다.
- IDE의 타입 추론 성능이 더 좋다.
- TypeScript와 함께 사용하기 좋다.

### 9.2 특징

- 최상위에서 선언한 변수와 함수는 자동으로 템플릿에서 사용할 수 있다.
- 별도로 `return`하지 않아도 된다.
- 지역 컴포넌트 등록 시 `components` 옵션이 필요 없고 `import`만 하면 된다.

## 10. props와 emit 처리 방식

`<script setup>`에서는 `props`와 `emit`도 전용 함수로 처리한다.

### 10.1 props

```vue
<script setup>
const props = defineProps({
  todoItem: {
    type: Object,
    required: true,
  },
});
</script>
```

### 10.2 emit

```vue
<script setup>
const emit = defineEmits(['delete-todo', 'toggle-completed']);

const removeTodo = (id) => {
  emit('delete-todo', id);
};
</script>
```

기존 방식은 다음과 같았다.

```js
setup(props, context) {
  context.emit('add-todo', todo);
}
```

`<script setup>`에서는 `defineEmits()`로 받은 `emit` 함수를 직접 사용한다.

즉,

- 기존: `context.emit(...)`
- 변경: `emit(...)`

## 11. 중요 포인트

- Composition API의 중심은 `setup()`이다.
- `beforeCreate`, `created`는 `setup()`이 대신한다.
- `ref()`는 기본형 반응성 데이터 생성에 사용한다.
- `ref()`는 스크립트에서 `.value`로 접근해야 한다.
- `reactive()`는 객체, 배열 같은 참조형 반응성 데이터 생성에 사용한다.
- `computed()`는 계산된 값을 만들며 캐시를 사용한다.
- `watch()`는 특정 대상을 감시하고 이전 값도 알 수 있다.
- `watchEffect()`는 내부에서 사용한 반응형 데이터를 자동 추적한다.
- `<script setup>`은 코드가 더 짧고 사용이 편리하다.
- `defineProps()`, `defineEmits()`로 props와 이벤트를 처리한다.

## 정리

Composition API는 상태와 로직을 함수 중심으로 구성하는 방식이다.

핵심은 `setup()`이며, 여기서 상태 데이터(`ref`, `reactive`), 계산된 속성(`computed`), 감시 로직(`watch`, `watchEffect`), 생명주기 함수(`onMounted` 등)를 함께 작성한다.

`ref()`는 기본형 데이터, `reactive()`는 참조형 데이터에 적합하다는 점을 구분해야 한다.
또한 `computed()`는 계산용, `watch()`는 변화 감시용이라는 차이도 중요하다.

실무와 학습에서는 `<script setup>` 문법이 많이 사용되며, `defineProps()`, `defineEmits()`와 함께 더 간결하게 컴포넌트를 작성할 수 있다.
