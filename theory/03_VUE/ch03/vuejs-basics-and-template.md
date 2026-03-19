# Vue.js 기초와 Template

## 1. Vue 디렉티브

Vue 디렉티브(directive)는 `v-`로 시작하는 특별한 속성이다.  
즉, Vue가 해석하는 지시어라고 보면 된다.

예:

```html
v-text v-bind v-model v-show v-if v-for
```

일반 HTML 속성은 문자열로 해석되지만,  
Vue 디렉티브는 자바스크립트 표현식처럼 해석된다.

---

## 2. 일반 HTML 속성과 Vue 디렉티브의 차이

기존 HTML 속성은 보통 문자열로 처리된다.

```html
<img src="image.png" title="샘플 이미지" />
```

여기서 `src`, `title`의 값은 문자열이다.

반면 Vue 디렉티브는 자바스크립트 표현식으로 해석된다.

```html
<h2 v-text="message"></h2>
```

여기서 `message`는 단순 문자열 `"message"`가 아니라  
`data()`가 반환한 객체의 속성명으로 해석된다.

즉, 내부적으로는 Vue 인스턴스의 데이터에 연결된다.

```js
vm.message;
```

처럼 이해할 수 있다.

HTML 안에서는 `this`를 직접 쓰지 않고 그냥 `message`라고 적는다.

---

## 3. `{{ message }}` 와 `v-text`

다음 두 표현은 같은 의미로 볼 수 있다.

```html
<h2>{{ message }}</h2>
```

```html
<h2 v-text="message"></h2>
```

둘 다 `message` 값을 화면에 출력한다.

차이점은 다음 정도로 볼 수 있다.

- `{{ }}` : 템플릿 보간 표현
- `v-text` : 디렉티브 방식 출력

실무에서는 단순 텍스트 출력에 `{{ }}`가 더 자주 쓰인다.

---

## 4. `v-bind`

`v-bind`는 HTML 속성과 Vue 데이터를 연결할 때 사용한다.

기본 형식:

```html
v-bind:속성="표현식"
```

예:

```html
<img v-bind:src="imageUrl" />
```

여기서

- `속성` : HTML 속성
- `표현식` : Vue가 관리하는 데이터

즉, Vue 데이터의 값을 HTML 속성에 넣어 준다.

### 축약 표현

`v-bind:`는 `:`로 줄여서 쓸 수 있다.

```html
<img :src="imageUrl" />
```

### 특징

`v-bind`는 **단방향 바인딩**이다.

즉,

- Vue 데이터 → HTML 속성 반영
- HTML 속성 변경 → Vue 데이터 자동 반영 아님

---

## 5. `v-model`

`v-model`은 입력 요소와 데이터를 **양방향 데이터 바인딩**할 때 사용한다.

```html
<input v-model="name" />
```

이 경우 다음 두 가지가 모두 가능하다.

- 데이터 값 변경 → UI 변경
- UI 입력값 변경 → 데이터 값 변경

즉, 데이터와 입력 요소가 서로 연결된다.

---

## 6. checkbox와 `v-model`

체크박스는 사용 방식에 따라 바인딩 결과가 다르다.

### 6-1. 단일 선택

체크박스 하나를 단일 속성과 연결하면 보통 `boolean` 값으로 처리된다.

```html
<input type="checkbox" v-model="checked" />
```

- 체크됨 → `true`
- 체크 해제 → `false`

### 6-2. 다중 선택

체크박스 여러 개를 배열 속성과 연결하면  
선택된 값들이 배열에 들어간다.

```html
<input type="checkbox" value="A" v-model="items" />
<input type="checkbox" value="B" v-model="items" />
<input type="checkbox" value="C" v-model="items" />
```

이 경우 `items`는 배열이 된다.

### 6-3. `true-value`, `false-value`

체크 여부를 `true`, `false` 말고 다른 값으로 처리할 수도 있다.

```html
<input type="checkbox" v-model="status" true-value="Y" false-value="N" />
```

- 체크됨 → `"Y"`
- 체크 해제 → `"N"`

---

## 7. `v-model` 수식어

`v-model`에는 입력값 처리 방식을 조정하는 수식어가 있다.

### `lazy`

입력 중 바로 반영하지 않고,  
엔터를 치거나 포커스가 이동했을 때 동기화한다.

```html
<input v-model.lazy="message" />
```

### `number`

입력값을 숫자로 형변환한다.

```html
<input v-model.number="age" />
```

### `trim`

앞뒤 공백을 제거한다.

```html
<input v-model.trim="name" />
```

---

## 8. 문자열을 숫자로 변환하는 함수

폼 입력값은 기본적으로 문자열로 들어오는 경우가 많다.  
이때 숫자 처리가 필요하면 다음 함수를 사용할 수 있다.

### `Number()`

문자열을 숫자로 변환한다.

```js
Number('100'); // 100
```

### `parseInt()`

정수 형태로 변환한다.

```js
parseInt('100.9'); // 100
```

### `parseFloat()`

실수 형태로 변환한다.

```js
parseFloat('100.9'); // 100.9
```

---

## 9. `v-model`과 한글 입력 처리

`v-model`은 한글 입력 시 처리 시점이 다소 다르게 느껴질 수 있다.

한글은 조합형 입력 방식이라서  
한 글자가 완전히 입력된 시점에 값이 반영되는 것처럼 보일 수 있다.

즉, 영문 입력과 달리 입력 도중의 처리 시점이 다르게 느껴질 수 있다.

---

## 10. `@input`

`@`는 이벤트 바인딩의 축약 표현이다.

```html
<input @input="changeName" />
```

이 표현은 아래와 같다.

```html
<input v-on:input="changeName" />
```

여기서

- `@` : 이벤트 핸들러 축약어
- `input` : 이벤트 이름
- `changeName` : 메서드 이름

보통 이벤트 핸들러 함수는 `methods` 속성 안에 작성한다.

```js
methods: {
  changeName(event) {
    this.name = event.target.value;
  }
}
```

---

## 11. `v-show`

`v-show`는 화면에 보여줄지 말지를 결정하는 디렉티브이다.

```html
<p v-show="visible">안녕하세요</p>
```

특징은 다음과 같다.

- HTML 요소는 생성된다
- DOM에는 존재한다
- 다만 화면에 보일지 말지만 바뀐다
- 보통 `display` 스타일이 변경되는 방식이다

즉, 요소는 남아 있지만 화면에만 숨겨질 수 있다.

조건식이 `true`이면 보이고,  
`false`이면 숨겨진다.

---

## 12. `v-if`

`v-if`는 조건에 따라 DOM 자체를 추가하거나 제거한다.

```html
<p v-if="visible">안녕하세요</p>
```

- `true` → DOM에 추가
- `false` → DOM에서 제거

즉, `v-show`와 달리 단순히 보이기만 제어하는 것이 아니라  
실제로 DOM 생성 여부를 바꾼다.

함께 사용할 수 있는 디렉티브는 다음과 같다.

- `v-if`
- `v-else-if`
- `v-else`

---

## 13. `v-show`와 `v-if` 차이

### `v-show`

- DOM은 유지
- 화면 표시 여부만 변경
- `display` 스타일이 바뀜

### `v-if`

- DOM 자체를 생성 / 제거
- 조건에 따라 요소가 실제로 사라질 수 있음

즉,

- 자주 보였다 숨겨졌다 하는 경우 → `v-show`
- 조건에 따라 아예 만들지 않을 경우 → `v-if`

로 이해하면 된다.

---

## 14. `v-for`

`v-for`는 반복적인 데이터 렌더링에 사용한다.

가장 많이 사용하는 대상은 배열이다.

기본 형식:

```html
<태그명 v-for="변수 in 배열" :key="id값">
```

예:

```html
<li v-for="contact in contacts" :key="contact.id">{{ contact.name }}</li>
```

---

## 15. 객체를 이용한 `v-for`

객체를 순회할 때는 다음과 같이 작성할 수 있다.

```html
<태그명 v-for="(val, key) in 객체" :key="key">
```

예:

```html
<li v-for="(val, key) in user" :key="key">{{ key }} : {{ val }}</li>
```

주의할 점은

- `val`이 먼저 오고
- `key`가 나중에 온다

는 것이다.

또한 객체의 속성 순서는 항상 예측 가능하다고만 보기는 어렵다.

---

## 16. 인덱스 번호 사용

### 배열에서 인덱스 사용

```html
<태그명 v-for="(contact, index) in contacts" :key="contact.id">
```

### 객체에서 인덱스 사용

```html
<태그명 v-for="(val, key, index) in regions" :key="key">
```

즉,

- 배열: `(값, 인덱스)`
- 객체: `(값, 키, 인덱스)`

형태로 사용할 수 있다.

---

## 17. 여러 요소를 묶어서 반복 렌더링

여러 요소를 함께 반복하고 싶을 때는 `<template>`를 사용할 수 있다.

```html
<template v-for="item in items" :key="item.id">
  <h3>{{ item.title }}</h3>
  <p>{{ item.description }}</p>
</template>
```

`<template>`는 반복 제어용으로만 사용되며  
실제 DOM 요소로 추가되지는 않는다.

즉, 묶음 역할만 한다.

---

## 18. `key` 속성의 중요성

배열을 렌더링할 때 데이터 변경 없이 위치만 바뀌는 경우가 있다.

이때 `key` 속성의 유무가 중요하다.

### `key`가 없으면

Vue가 각 요소를 정확히 구분하기 어려워서  
전체를 다시 렌더링하는 방식으로 동작할 수 있다.

### `key`가 있으면

각 항목을 식별할 수 있으므로  
필요한 위치 변경만 더 효율적으로 처리할 수 있다.

즉,

- 읽기 전용 출력만 하는 경우 → `key` 중요도가 낮을 수 있음
- 동적으로 추가 / 삭제 / 정렬 / 이동되는 경우 → `key` 매우 중요

---

## 19. Proxy 객체

Vue 3에서 중요한 개념 중 하나가 Proxy 객체이다.

Proxy는 객체의 속성 접근과 변경을 감시할 수 있게 해 준다.

Vue는 `data`로 지정된 객체를 반응형으로 처리하기 위해  
이 객체들을 Proxy로 감싼다.

즉,

- 데이터 변경사항을 감시하고
- 값이 바뀌면
- 다시 렌더링하도록 유도한다

이것이 Vue 반응형 시스템의 핵심이다.

---

## 20. Proxy와 배열 메서드

Vue는 배열도 Proxy를 통해 감지할 수 있다.

배열을 변경하는 메서드를 사용하면  
Vue가 그 변경을 감지해서 필요한 렌더링을 다시 수행한다.

예를 들면 다음과 같은 메서드가 해당된다.

- `push()`
- `pop()`
- `splice()`
- `sort()`
- `shift()`
- `unshift()`

특히 `key` 값이 적절히 지정되어 있으면  
변경된 부분만 더 효율적으로 다시 렌더링할 수 있다.

즉, 배열 자체가 반응형으로 관리되기 때문에  
데이터가 바뀌면 화면도 자동으로 갱신된다.

---

## 21. 정리

- Vue 디렉티브는 `v-`로 시작하는 특별한 속성이다.
- `{{ message }}`와 `v-text="message"`는 비슷한 출력 방식이다.
- `v-bind`는 Vue 데이터를 HTML 속성에 연결하는 단방향 바인딩이다.
- `v-model`은 입력 요소와 데이터를 연결하는 양방향 바인딩이다.
- checkbox는 단일 선택일 때 boolean, 다중 선택일 때 배열과 바인딩될 수 있다.
- `lazy`, `number`, `trim`은 `v-model` 수식어이다.
- `@input`은 `v-on:input`의 축약 표현이다.
- `v-show`는 DOM을 유지한 채 보이기만 제어한다.
- `v-if`는 DOM 자체를 생성하거나 제거한다.
- `v-for`는 배열이나 객체를 반복 렌더링할 때 사용한다.
- 여러 요소를 묶어 반복할 때는 `<template v-for>`를 사용할 수 있다.
- 동적 목록에서는 `key` 속성이 매우 중요하다.
- Vue는 Proxy를 이용해 데이터 변경을 감지한다.
- 배열 변경 메서드도 Vue가 감지해서 렌더링을 다시 수행할 수 있다.
