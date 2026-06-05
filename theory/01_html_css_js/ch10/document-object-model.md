# 문서 객체 모델 DOM(Document Object Model)

## 1. DOM이란?

- DOM(Document Object Model)은 HTML 문서를 **자바스크립트에서 객체 형태로 다룰 수 있게 만든 구조**
- 자바스크립트로 문서의 요소를 선택하고, 내용을 바꾸고, 스타일을 바꾸고, 속성을 제어할 수 있음

자바스크립트로 주로 하는 일:

1. 구조 조정
2. 내용 변경
3. 속성 제어
4. 스타일 조작
5. 이벤트 처리

---

## 2. 문서 객체 선택 메서드

### 2-1. querySelector()

- CSS 선택자를 사용해서 **첫 번째 요소 1개만 선택**
- 선택한 요소가 없으면 `null` 반환

예:

```javascript
document.querySelector('h1');
document.querySelector('#header');
document.querySelector('.item');
```

### 2-2. querySelectorAll()

- CSS 선택자를 사용해서 **조건에 맞는 모든 요소를 선택**
- 결과는 **NodeList** 형태로 반환
- 선택한 요소가 없으면 **비어 있는 NodeList**가 반환됨
- 즉, `length`가 `0`이므로 반복문을 돌려도 에러가 나지 않음

예:

```javascript
document.querySelectorAll('h1');
document.querySelectorAll('.item');
```

---

## 3. script 실행 시점

자바스크립트에서 문서 객체를 선택하려면, 해당 HTML 요소가 먼저 만들어져 있어야 함.

그래서 보통 아래 방법 중 하나를 사용함.

### 방법 1. script를 body 끝부분에 작성

```html
<body>
  <h1>Header</h1>
  <script>
    const h1 = document.querySelector('h1');
  </script>
</body>
```

### 방법 2. `window.onload` 사용

- 페이지의 모든 리소스(문서, 이미지 등)가 다 로드된 뒤 실행됨

```javascript
window.onload = function () {
  const h1 = document.querySelector('h1');
};
```

> 참고: DOM만 준비되면 바로 실행하고 싶을 때는 `DOMContentLoaded`를 쓰기도 함

---

## 4. 브라우저의 전역 객체

- 자바스크립트의 **전역 객체(global object)** 는 실행 환경에 따라 달라짐
- 브라우저 환경에서는 전역 객체가 **`window`**
- 따라서 브라우저에서는 전역 함수나 일부 전역 값이 `window` 아래에 연결됨

예:

```javascript
window.alert('안녕하세요');
alert('안녕하세요');
```

- 위 두 코드는 브라우저에서 같은 의미로 동작함

> 정정: 여기서 말하는 것은 `top` 객체가 아니라 **전역 객체(global object)** 개념이라고 보는 것이 정확함  
> `top`은 브라우저에서 최상위 창(window)을 가리키는 별도의 속성임

---

## 5. var, let, const와 전역 객체

전역 범위에서 선언할 때 차이가 있음.

- `var`로 선언한 전역 변수는 브라우저에서 `window`의 프로퍼티가 될 수 있음
- `let`, `const`로 선언한 전역 변수는 `window`의 프로퍼티가 아님

예:

```javascript
var a = 10;
let b = 20;
const c = 30;

console.log(window.a); // 10
console.log(window.b); // undefined
console.log(window.c); // undefined
```

정리:

- `var` → 함수 스코프 중심, 오래된 방식
- `let` → 재할당 가능한 블록 스코프 변수
- `const` → 재할당 불가능한 블록 스코프 변수

보통 modern JavaScript에서는 `let`, `const` 사용을 권장함.

---

## 6. window.onload와 콜백 함수

- `window.onload`의 `onload`는 **로드(load) 이벤트가 발생했을 때 실행할 함수**를 등록하는 것
- 이때 넣는 함수는 **직접 호출하는 함수**가 아니라, 특정 시점에 나중에 실행되는 **콜백 함수(callback)** 또는 **이벤트 핸들러**라고 볼 수 있음

예:

```javascript
window.onload = function () {
  console.log('문서 로드 완료');
};
```

---

## 7. id와 class로 요소 선택하기

### 7-1. id 선택

HTML:

```html
<h1 id="header">제목</h1>
```

자바스크립트:

```javascript
document.getElementById('header');
document.querySelector('#header');
```

정리:

- `getElementById('header')`
- `querySelector('#header')`

둘 다 1개 요소를 선택할 수 있음.  
단, `querySelector()`는 CSS 선택자를 그대로 쓸 수 있어서 더 자주 사용됨.

### 7-2. class 선택

HTML:

```html
<li class="item">사과</li>
<li class="item">바나나</li>
```

자바스크립트:

```javascript
document.getElementsByClassName('item');
document.querySelectorAll('.item');
```

> 정정: `document.getElementByClass()`는 없는 메서드이고, 정확한 이름은  
> **`document.getElementsByClassName()`** 임

정리:

- `getElementsByClassName('item')` → 여러 요소 반환
- `querySelectorAll('.item')` → 여러 요소 반환

실무/학습에서는 `querySelector`, `querySelectorAll`이 CSS 선택자를 그대로 쓸 수 있어서 더 편한 편임.

---

## 8. 여러 개를 선택했을 때: NodeList

`querySelectorAll()`의 결과는 **NodeList**다.

NodeList 특징:

- 배열과 비슷한 구조
- `length` 속성 있음
- 인덱스로 접근 가능
- 반복문 사용 가능

예:

```javascript
const items = document.querySelectorAll('.item');

console.log(items.length);
console.log(items[0]);
```

반복 예:

```javascript
for (let i = 0; i < items.length; i++) {
  console.log(items[i]);
}
```

또는:

```javascript
for (const item of items) {
  console.log(item);
}
```

> NodeList는 배열과 비슷하지만 **배열 그 자체는 아님**  
> 필요하면 `Array.from()`으로 배열로 바꿔서 `map()`, `filter()` 등을 사용할 수 있음

예:

```javascript
const items = document.querySelectorAll('.item');
const itemArray = Array.from(items);
```

---

## 9. textContent와 innerHTML

### 9-1. textContent

- 요소 내부의 **순수 텍스트**를 다룸
- 태그를 문자열로 넣어도 **그대로 글자로 처리됨**

예:

```javascript
element.textContent = '<h1>Hello</h1>';
```

결과:

```html
&lt;h1&gt;Hello&lt;/h1&gt;
```

### 9-2. innerHTML

- 요소 내부의 **HTML 코드 자체를 해석해서 넣음**
- 태그를 실제 HTML 요소로 반영할 수 있음

예:

```javascript
element.innerHTML = '<h1>Hello</h1>';
```

결과:

```html
<h1>Hello</h1>
```

### 차이점 정리

- `textContent` → 텍스트만 처리
- `innerHTML` → HTML 태그까지 해석

> `innerHTML`은 문자열을 HTML로 해석하므로, 외부 입력을 그대로 넣으면 보안 문제가 생길 수 있음  
> 특히 악성 스크립트가 섞일 수 있으므로 주의해야 함

---

## 10. 스타일 조작

자바스크립트에서는 CSS 속성 이름을 그대로 쓰지 않고, **camelCase** 형식으로 바꿔서 사용하는 경우가 많음.

예:

- CSS: `background-color`
- JS: `backgroundColor`

예시:

```javascript
const header = document.querySelector('h1');
header.style.color = 'orange';
header.style.backgroundColor = 'red';
```

---

## 11. 사용자 지정 속성 data-\*

HTML에서는 `data-*` 형식으로 사용자 지정 속성을 만들 수 있음.

예:

```html
<body data-role="main" data-user-name="kim"></body>
```

### 11-1. getAttribute(), setAttribute()로 접근

```javascript
document.body.getAttribute('data-role');
document.body.setAttribute('data-role', 'admin');
```

이 방식에서는 반드시 **`data-`를 포함한 전체 이름**을 사용해야 함.

### 11-2. dataset으로 접근

```javascript
document.body.dataset.role;
document.body.dataset.userName;
```

정리:

- `data-role` → `dataset.role`
- `data-user-name` → `dataset.userName`

즉, `dataset`에서는:

- 앞의 `data-`는 빠지고
- 하이픈(`-`) 뒤 단어는 camelCase로 바뀜

---

## 12. setInterval()과 setTimeout()

### 12-1. setInterval()

- 일정 시간마다 **반복 실행**

예:

```javascript
setInterval(function () {
  console.log('1초마다 실행');
}, 1000);
```

- `1000ms = 1초`

### 12-2. setTimeout()

- 일정 시간이 지난 뒤 **한 번만 실행**

예:

```javascript
setTimeout(function () {
  console.log('1초 후 한 번 실행');
}, 1000);
```

---

## 13. 타이머 함수 안의 this

일반 함수(function)를 타이머 콜백으로 넣으면 브라우저 환경에서 `this`가 `window`처럼 동작하는 경우가 있음.

예:

```javascript
setTimeout(function () {
  console.log(this);
}, 1000);
```

다만 `this`는 상황에 따라 헷갈리기 쉬우므로, 타이머 콜백 안에서는 `this`에 의존하지 않는 습관이 더 안전함.

특히 화살표 함수는 `this`를 자기 자신이 만들지 않고 바깥 스코프의 `this`를 따라감.

---

## 14. 이벤트 처리 방식

### 14-1. 인라인 이벤트 방식

HTML 안에 직접 이벤트를 작성하는 방식

```html
<button onclick="alert('클릭')">버튼</button>
```

이 방식은 간단하지만 HTML과 자바스크립트가 섞이므로 보통 권장되지 않음.

### 14-2. 자바스크립트에서 이벤트 등록

보통은 자바스크립트에서 이벤트를 등록하는 방식을 더 권장함.

예:

```javascript
button.onclick = function (event) {
  console.log('클릭');
};
```

또는 더 권장되는 방식:

```javascript
button.addEventListener('click', function (event) {
  console.log('클릭');
});
```

> 정리: 인라인 이벤트 모델보다 **`addEventListener()` 방식이 더 권장됨**

---

## 15. 기본 이벤트와 기본 동작 막기

일부 태그는 원래부터 기본 동작이 있음.

예:

- `<a>` → 링크 이동
- `<form>`의 submit → 폼 전송
- `<input type="submit">` → 폼 제출

이때 이벤트 핸들러가 먼저 실행되고, **막지 않으면** 그 다음 기본 동작이 수행됨.

예:

```javascript
link.addEventListener('click', function (event) {
  event.preventDefault();
});
```

### 기본 동작 막기

가장 권장되는 방식:

```javascript
event.preventDefault();
```

예:

```javascript
const link = document.querySelector('a');

link.addEventListener('click', function (event) {
  event.preventDefault();
  console.log('링크 이동 막음');
});
```

> 정정: `return false`는 일부 상황(특히 인라인 이벤트나 오래된 방식)에서는 동작할 수 있지만,  
> 현대 자바스크립트에서는 **`event.preventDefault()`를 사용하는 것이 더 정확하고 권장되는 방법**임

---

## 핵심 요약

- DOM은 HTML 문서를 자바스크립트에서 객체처럼 다루기 위한 구조
- `querySelector()`는 첫 번째 요소 1개 반환
- `querySelectorAll()`은 여러 요소를 NodeList로 반환
- 요소가 아직 만들어지기 전에는 선택할 수 없으므로 script 위치나 로드 시점을 주의해야 함
- 브라우저의 전역 객체는 `window`
- 전역 `var`는 `window`에 연결될 수 있지만, `let`과 `const`는 아님
- `textContent`는 텍스트만, `innerHTML`은 HTML까지 해석
- CSS의 `background-color`는 JS에서 `backgroundColor`
- `data-*` 속성은 `getAttribute()` 또는 `dataset`으로 접근 가능
- `setInterval()`은 반복 실행, `setTimeout()`은 한 번 실행
- 이벤트는 인라인 방식보다 `addEventListener()` 방식 권장
- 기본 동작을 막을 때는 `return false`보다 `event.preventDefault()`가 더 적절함
