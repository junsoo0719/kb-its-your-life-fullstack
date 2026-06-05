# Vue.js 소개

## 1. Vue.js 기본 구조

Vue는 화면(View)과 데이터를 연결해서,  
데이터가 바뀌면 화면도 함께 업데이트되도록 도와주는 자바스크립트 라이브러리이다.

기본 구조는 다음과 같다.

```html
<div id="app">
  <h2>{{ message }}</h2>
</div>

<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script>
  const model = { message: 'Hello Vue3!' };

  const vm = Vue.createApp({
    name: 'App',
    data() {
      return model;
    },
  }).mount('#app');
</script>
```

---

## 2. 각 부분의 의미

### 2-1. `div id="app"`

```html
<div id="app"></div>
```

이 영역은 Vue가 관리할 대상이다.  
즉, Vue가 화면을 연결하고 갱신할 범위가 된다.

보통 이 부분을 **View**라고 볼 수 있다.

---

### 2-2. `{{ message }}`

```html
<h2>{{ message }}</h2>
```

`{{ }}` 문법은 **머스태시 문법(Mustache Syntax)** 이라고 하며,  
Vue 안의 데이터를 화면에 출력할 때 사용한다.

이 부분은 템플릿 안에서 `message` 값을 표시하는 표현식이다.

즉, 데이터가 `"Hello Vue3!"`이면 화면에는 그 값이 그대로 출력된다.

---

### 2-3. Vue 라이브러리 불러오기

```html
<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
```

이 코드는 CDN을 통해 Vue 라이브러리를 불러오는 부분이다.

- Vue를 별도 설치하지 않고 바로 사용할 수 있다
- 학습용, 간단한 예제에서 자주 사용한다

---

### 2-4. model

```js
const model = { message: 'Hello Vue3!' };
```

`model`은 화면에 표시할 데이터를 담고 있는 객체이다.

여기서는 `message`라는 속성에 `"Hello Vue3!"` 값을 저장하고 있다.

즉, 이 객체가 화면에 보여 줄 데이터를 가지고 있다.

---

### 2-5. `Vue.createApp()`

```js
const vm = Vue.createApp({
  name: 'App',
  data() {
    return model;
  },
}).mount('#app');
```

`Vue.createApp()`은 Vue 애플리케이션을 생성하는 함수이다.

여기에 전달하는 객체를 **옵션 객체**라고 한다.  
이 객체 안에 컴포넌트 이름, 데이터, 메서드 등의 설정을 작성할 수 있다.

여기서는 다음 내용을 포함하고 있다.

- `name: "App"`  
  애플리케이션 또는 컴포넌트의 이름이다.

- `data() { return model; }`  
  Vue가 사용할 데이터를 반환하는 함수이다.

즉, `model` 객체를 Vue의 반응형 데이터로 연결하는 역할을 한다.

---

### 2-6. `mount("#app")`

```js
.mount("#app");
```

`mount()`는 Vue 앱을 실제 HTML 요소에 연결하는 함수이다.

여기서는 `id="app"`인 요소에 Vue를 연결한다.

즉, Vue가 `#app` 영역을 관리하게 된다.

---

## 3. View, Model, ViewModel 관점에서 보기

이 예제를 구조적으로 보면 다음과 같이 이해할 수 있다.

### View

```html
<div id="app">
  <h2>{{ message }}</h2>
</div>
```

사용자에게 실제로 보이는 화면 부분이다.

### Model

```js
const model = { message: 'Hello Vue3!' };
```

화면에 표시할 데이터이다.

### ViewModel

```js
const vm = Vue.createApp(...).mount("#app");
```

View와 Model을 연결하는 역할을 한다.

Vue에서는 `createApp()`으로 만든 앱 객체와  
그 안의 반응형 데이터 연결 구조를 ViewModel처럼 볼 수 있다.

---

## 4. 데이터 변경과 화면 업데이트

브라우저 개발자 도구 콘솔에서 값을 바꿔 보면 차이를 확인할 수 있다.

### 4-1. `model.message = 'xxx'`

```js
model.message = 'xxx';
```

이렇게 `model`에 직접 값을 대입하면  
단순히 객체 값만 바뀌고 화면은 다시 그려지지 않을 수 있다.

즉, Vue의 반응형 갱신 흐름을 거치지 않기 때문에  
UI 업데이트가 바로 일어나지 않을 수 있다.

---

### 4-2. `vm.message = 'xxx'`

```js
vm.message = 'xxx';
```

이렇게 `vm`을 통해 값을 변경하면  
Vue가 데이터 변화를 감지하고 화면을 다시 그리는 작업이 일어난다.

즉,

- 값 변경 감지
- 화면 재렌더링
- UI 자동 업데이트

가 이루어진다.

---

## 5. 핵심 정리

Vue의 핵심은 **데이터와 화면을 연결하는 것**이다.

이 예제에서 중요한 흐름은 다음과 같다.

1. `model`에 데이터를 만든다.
2. `data()`에서 그 데이터를 반환한다.
3. `mount("#app")`로 Vue를 화면에 연결한다.
4. 템플릿에서 `{{ message }}`로 데이터를 출력한다.
5. Vue가 데이터 변화를 감지하면 화면을 자동으로 업데이트한다.

---

## 6. 정리

- Vue는 데이터와 화면을 연결해 주는 라이브러리이다.
- `#app`은 Vue가 관리할 화면 영역이다.
- `{{ message }}`는 데이터를 화면에 출력하는 템플릿 문법이다.
- `model`은 화면에 표시할 데이터를 담고 있는 객체이다.
- `Vue.createApp()`은 Vue 애플리케이션을 생성한다.
- `data()`는 Vue가 사용할 데이터를 반환한다.
- `mount("#app")`는 Vue를 실제 HTML 요소에 연결한다.
- `vm`을 통해 데이터를 변경하면 Vue가 변화를 감지하고 화면을 다시 그린다.
