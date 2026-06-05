# 이벤트 처리

## 1. 인라인 이벤트

Vue에서는 `v-on` 디렉티브를 사용해 이벤트를 연결할 수 있다.

기본 형식은 다음과 같다.

```html
v-on:[이벤트이름]="표현식"
```

축약 표현도 매우 자주 사용한다.

```html
@[이벤트이름]="표현식"
```

예:

```html
<button v-on:click="count++">증가</button>
<button @click="count++">증가</button>
```

즉,

- `v-on:click="..."`
- `@click="..."`

는 같은 의미이다.

참고로 이전에 사용했던 `v-bind`의 축약 표현은 `:속성명`이었다.

---

## 2. `v-bind`와 `v-model`

Vue에서 자주 비교되는 개념이 `v-bind`와 `v-model`이다.

### `v-bind`

단방향 바인딩이다.

```html
<input :value="name" />
```

즉,

- Vue 데이터 → 화면

한 방향으로만 값이 반영된다.

### `v-model`

양방향 바인딩이다.

```html
<input v-model="name" />
```

즉,

- Vue 데이터 → 화면
- 화면 입력값 → Vue 데이터

두 방향으로 값이 연결된다.

---

## 3. 이벤트 객체 전달

이벤트 핸들러에 이벤트 객체를 직접 전달할 수 있다.

```html
<button @click="test($event)">클릭</button>
```

여기서 `$event`는 브라우저가 전달하는 이벤트 객체이다.

즉, 핸들러 함수에서 실제 이벤트 정보를 사용할 수 있다.

---

## 4. 이벤트 핸들러 메서드

이벤트 핸들러는 `methods`에 정의한 함수를 사용할 수 있다.

```html
<button @click="changeCount">클릭</button>
```

```js
methods: {
  changeCount() {
    this.count++;
  }
}
```

### 함수명만 쓰는 경우

괄호 없이 함수명만 써도 된다.

```html
<button @click="changeCount">클릭</button>
```

이 경우 Vue가 해당 메서드를 이벤트 핸들러로 연결한다.

### 호출문을 쓰는 경우

매개변수를 직접 전달해야 하면 호출문 형태를 사용한다.

```html
<button @click="changeCount(10)">클릭</button>
<button @click="changeCount($event)">클릭</button>
```

즉,

- 매개변수 전달이 필요 없으면 함수명만 사용
- 매개변수 전달이 필요하면 호출문 사용

이다.

---

## 5. methods 안에서의 `this`

`methods` 안에 정의한 함수는 Vue 인스턴스의 메서드처럼 동작한다.

그래서 반응형 데이터에 접근할 때는 `this`를 붙여야 한다.

```js
methods: {
  changeName() {
    this.name = "홍길동";
  }
}
```

`this`를 붙이지 않으면 Vue의 반응형 데이터가 아니라  
지역 변수나 전역 변수처럼 해석될 수 있다.

즉, 메서드 안에서 데이터에 접근할 때는 보통 `this`가 필요하다.

---

## 6. methods에서 화살표 함수 사용 주의

이벤트 핸들러 메서드를 `methods`에 정의할 때는 화살표 함수를 사용하지 않는 것이 좋다.

```js
methods: {
  changeName: () => {
    this.name = '홍길동';
  };
}
```

이렇게 하면 `this`가 Vue 인스턴스를 가리키지 않을 수 있다.

화살표 함수는 **자기 자신의 this를 만들지 않고 바깥 영역의 this를 사용**하기 때문이다.

그래서 `methods`에서는 일반 함수 문법을 사용하는 것이 맞다.

```js
methods: {
  changeName() {
    this.name = "홍길동";
  }
}
```

---

## 7. 수동 양방향 처리

다음과 같이 `:value`와 `@input`을 함께 사용하면  
직접 양방향 바인딩과 비슷한 효과를 만들 수 있다.

```html
<input :value="name" @input="changeName" />
```

```js
methods: {
  changeName(event) {
    this.name = event.target.value;
  }
}
```

이 방식도 가능하지만, Vue에서는 보통 `v-model`을 쓰는 것이 더 자연스럽다.

즉, 이 방식은 **가능하지만 비권장**이다.

---

## 8. 인라인 이벤트 핸들러 직접 작성

다음처럼 인라인 핸들러에서 직접 값을 바꾸는 것도 가능하다.

```html
<input @input="(e) => this.name = e.target.value" />
```

이 경우 학습 상황에 따라 `this`가 Vue 인스턴스를 가리키는 것으로 이해할 수 있지만,  
실무나 유지보수 관점에서는 코드가 지저분해지기 쉽다.

즉, 이런 방식도 **비권장**이다.

보통은 `v-model` 또는 `methods`를 사용하는 편이 더 좋다.

---

## 9. 숫자 입력과 `v-model.number`

`v-model`은 기본적으로 입력값을 문자열로 처리한다.

예:

```html
<input v-model="age" />
```

이 경우 숫자를 입력해도 실제 값은 문자열일 수 있다.

숫자로 처리하고 싶으면 `.number` 수식어를 사용한다.

```html
<input v-model.number="age" />
```

즉,

- `v-model` → 문자열
- `v-model.number` → 숫자 변환 시도

이다.

---

## 10. 이벤트 객체의 주요 공통 속성

이벤트 객체에는 여러 속성이 있다.

### `target`

가장 많이 사용하는 속성이다.  
실제로 이벤트가 발생한 요소를 가리킨다.

### `currentTarget`

현재 이벤트를 처리하고 있는 요소를 가리킨다.

즉,

- `target` : 실제 이벤트 발생 위치
- `currentTarget` : 현재 핸들러가 실행 중인 요소

이다.

---

## 11. 키보드 이벤트 관련 속성

키보드 이벤트에서는 다음과 같은 속성들을 자주 본다.

### `altKey`, `shiftKey`, `ctrlKey`

보조 키가 눌렸는지 여부를 나타낸다.

- `true`
- `false`

형태의 boolean 값이다.

### `key`

입력된 키 값을 문자열로 나타낸다.  
대소문자를 구분할 수 있다.

### `code`

키보드에서 눌린 키의 코드값을 문자열로 나타낸다.

### `keyCode`

예전 방식의 숫자 코드값이다.

### `charCode`

문자의 유니코드 관련 숫자값이다.

현재는 `key`, `code`를 더 자주 사용한다.

---

## 12. 이벤트 객체의 주요 메서드

### `preventDefault()`

이벤트의 기본 동작을 막는다.

예를 들면 다음과 같은 기본 동작을 막을 수 있다.

- 링크 이동
- 폼 제출
- 브라우저 기본 메뉴 동작

예:

```js
event.preventDefault();
```

### `stopPropagation()`

이벤트 전파를 중단한다.

즉, 부모 요소로 이벤트가 전달되지 않게 막는다.

```js
event.stopPropagation();
```

이 메서드는 capturing, bubbling과 관련이 있다.

---

## 13. `@contextmenu`

`@contextmenu`는 마우스 오른쪽 버튼 클릭과 관련된 이벤트이다.

```html
<div @contextmenu="openMenu">우클릭 영역</div>
```

보통 사용자 정의 우클릭 메뉴를 만들 때 사용한다.

---

## 14. `.prevent` 수식어

Vue에서는 `event.preventDefault()`를 직접 호출하지 않고  
수식어로 간단히 처리할 수 있다.

```html
<form @submit.prevent="submitForm">...</form>
```

이것은 내부적으로 `event.preventDefault()`를 호출한 것과 비슷한 효과를 가진다.

### 언제 쓰는가

- 항상 기본 동작을 막아야 할 때 → `.prevent`
- 상황에 따라 다르게 막아야 할 때 → `event.preventDefault()` 직접 사용

즉, 폼 처리에서 자주 사용된다.

---

## 15. 이벤트 전파

HTML 요소가 부모-자식 관계일 때  
자식에서 발생한 이벤트는 부모에도 영향을 줄 수 있다.

즉, 자식 요소에서 클릭이 일어나면 부모 쪽 이벤트 핸들러도 실행될 수 있다.

---

## 16. Capturing과 Bubbling

이벤트 전파에는 두 가지 흐름이 있다.

### Capturing

부모가 먼저 처리하는 방식이다.

### Bubbling

자식이 먼저 처리하고 부모 쪽으로 올라가는 방식이다.

기본적으로 브라우저 이벤트는 **bubbling** 방식이 더 일반적이다.

즉, 보통은

1. 자식에서 먼저 실행
2. 부모에서 나중에 실행

순서로 이해하면 된다.

---

## 17. `target`과 `currentTarget` 차이

예를 들어 바깥 요소 `outer`, 안쪽 요소 `inner`가 있다고 하자.

### outer를 직접 클릭한 경우

- `target` = `outer`
- `currentTarget` = `outer`

즉, 둘이 같다.

### inner를 클릭한 경우

이벤트는 먼저 `inner`에서 시작하고, bubbling으로 `outer`까지 올라갈 수 있다.

이때:

- `target` = 실제 클릭된 `inner`
- `currentTarget` = 현재 처리 중인 요소

즉,

- `inner` 핸들러 실행 시 → `currentTarget`은 `inner`
- `outer` 핸들러 실행 시 → `currentTarget`은 `outer`

하지만 `target`은 계속 `inner` 그대로이다.

정리하면,

- `target`은 바뀌지 않음
- `currentTarget`은 현재 처리 중인 요소에 따라 바뀜

이다.

---

## 18. 버블링 차단

부모로 이벤트가 올라가지 않게 하려면 `stopPropagation()`을 사용한다.

```js
event.stopPropagation();
```

이렇게 하면 bubbling이 중단된다.

Vue에서는 수식어와 함께 활용하기도 한다.

---

## 19. `.once` 수식어

`.once`는 이벤트를 한 번만 실행하고, 이후에는 이벤트 연결을 해제한다.

```html
<button @click.once="test">한 번만 클릭</button>
```

즉, 첫 번째 실행 후 더 이상 같은 핸들러가 실행되지 않는다.

---

## 20. 키보드 이벤트 수식어

Vue는 키보드 이벤트 처리도 수식어로 간단하게 쓸 수 있다.

### `.enter`

가장 많이 사용하는 수식어이다.

```html
<input @keyup.enter="submit" />
```

엔터 키를 눌렀을 때만 핸들러가 실행된다.

### 조합 키 사용

```html
<textarea @keyup.ctrl.enter="submit"></textarea>
```

Ctrl + Enter를 동시에 눌렀을 때 실행된다.

```html
<input @keyup.ctrl.c="copy" />
```

Ctrl + C 조합도 표현할 수 있다.

---

## 21. `.exact` 수식어

`.exact`는 지정한 조합과 **정확히 일치할 때만** 이벤트를 실행한다.

예:

```html
<button @click.exact="clickOnly">일반 클릭</button>
<button @click.ctrl.exact="clickCtrl">Ctrl + 클릭</button>
<button @click.ctrl.alt.exact="clickCtrlAlt">Ctrl + Alt + 클릭</button>
```

즉,

- `@click.exact` : 다른 보조 키 없이 클릭만
- `@click.ctrl.exact` : Ctrl만 함께 눌린 경우
- `@click.ctrl.alt.exact` : Ctrl + Alt 조합이 정확히 맞는 경우

이다.

---

## 22. 인라인 이벤트 방식 주의

인라인 이벤트 안에 복잡한 로직이나 스타일 처리 코드를 직접 넣는 방식은 권장되지 않는다.

예를 들어 다음과 같은 방식은 가능하지만 가독성이 떨어질 수 있다.

```html
<input @input="(e) => this.name = e.target.value" />
```

Vue에서는

- 단순한 표현식은 인라인으로
- 복잡한 처리는 `methods`로

분리하는 것이 더 좋다.

즉, 인라인 스타일이나 인라인 로직은 과하게 쓰지 않는 편이 좋다.

---

## 23. 정리

- Vue 이벤트는 `v-on` 또는 `@` 축약 표현으로 연결한다.
- `v-bind`는 단방향, `v-model`은 양방향 바인딩이다.
- 이벤트 객체는 `$event`로 전달할 수 있다.
- 이벤트 핸들러는 보통 `methods`에 정의한다.
- 매개변수가 필요 없으면 함수명만 쓰고, 필요하면 호출문을 쓴다.
- `methods` 안에서는 반응형 데이터에 접근할 때 `this`가 필요하다.
- `methods`에서는 화살표 함수를 사용하지 않는 것이 좋다.
- `:value`와 `@input`으로 수동 양방향 처리도 가능하지만 보통 `v-model`이 더 적절하다.
- `v-model.number`를 쓰지 않으면 입력값이 문자열로 처리될 수 있다.
- `target`은 실제 이벤트 발생 요소, `currentTarget`은 현재 처리 중인 요소이다.
- `preventDefault()`는 기본 동작을 막고, `stopPropagation()`은 이벤트 전파를 막는다.
- `.prevent`, `.once`, `.enter`, `.exact` 같은 수식어를 사용하면 이벤트 처리를 더 간단히 작성할 수 있다.
- bubbling은 자식에서 부모 방향으로 이벤트가 전달되는 기본 흐름이다.
