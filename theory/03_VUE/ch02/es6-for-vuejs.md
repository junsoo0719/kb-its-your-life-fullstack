# Vue.js를 위한 ES6

## 1. let과 const

### let

`let`으로 선언한 변수는 **블록 스코프**를 가진다.

즉, 중괄호 `{}` 범위 안에서만 유효하다.

```js
if (true) {
  let a = 10;
  console.log(a); // 10
}

console.log(a); // ReferenceError
```

`let`은 `var`와 다르게 같은 블록에서 중복 선언할 수 없다.

```js
let x = 1;
let x = 2; // 에러
```

참고로 `let`은 완전히 호이스팅이 안 되는 것은 아니다.  
선언 자체는 먼저 처리되지만, **초기화 전에 접근할 수 없어서** `ReferenceError`가 발생한다.

```js
console.log(y); // ReferenceError
let y = 5;
```

이 구간을 **TDZ(Temporal Dead Zone)** 라고 한다.

---

### const

`const`는 **상수**를 선언할 때 사용하며, `let`과 같은 블록 스코프를 가진다.

```js
const PI = 3.14;
```

`const`는 재할당이 불가능하다.

```js
const num = 10;
num = 20; // 에러
```

하지만 참조형 데이터에서는 **참조 자체만 바꿀 수 없고**, 내부 데이터는 수정할 수 있다.

```js
const user = { name: '홍길동' };
user.name = '김철수'; // 가능
user = {}; // 에러
```

즉,

- `const` 변수에 다른 객체를 다시 대입하는 것은 불가
- 객체 내부의 속성값 수정은 가능

---

## 2. 기본 파라미터

기본 파라미터는 함수 호출 시 인수를 생략했을 때 사용할 기본값을 지정하는 문법이다.

```js
function func(arg1, arg2 = 10, arg3 = 20) {
  console.log(arg1, arg2, arg3);
}

func(1); // 1 10 20
```

보통 뒤쪽 매개변수부터 기본값을 지정하는 형태로 많이 사용한다.

---

## 3. 가변 파라미터

가변 파라미터는 전달되는 인수의 개수를 유동적으로 받을 때 사용한다.

```js
function func(arg1, arg2, ...argv) {
  console.log(arg1);
  console.log(arg2);
  console.log(argv);
}

func(1, 2, 3, 4, 5);
```

위 코드에서:

- `arg1` → `1`
- `arg2` → `2`
- `argv` → `[3, 4, 5]`

특징은 다음과 같다.

- 한 함수에 **1개만 사용 가능**
- **마지막 매개변수**에만 사용할 수 있다

---

## 4. 구조 분해 할당

구조 분해 할당은 배열이나 객체의 값을 꺼내서 여러 변수에 한 번에 할당하는 문법이다.

### 4-1. 배열 구조 분해 할당

배열은 **순서에 따라** 값이 할당된다.

```js
const arr = [10, 20, 30];
const [a, b, c] = arr;

console.log(a, b, c); // 10 20 30
```

### 4-2. 객체 구조 분해 할당

객체는 **속성 이름에 따라** 값이 할당된다.

```js
const p1 = { name: '홍길동', age: 20, gender: 'M' };
const { name, age, gender } = p1;

console.log(name, age, gender);
```

속성 이름을 바꿔서 받을 수도 있다.

```js
const p1 = { name: '홍길동', age: 20, gender: 'M' };
const { name: n, age: a, gender } = p1;

console.log(n, a, gender);
```

위 코드는

- `name` → `n`
- `age` → `a`

로 이름을 바꿔서 받는 예시이다.

### 4-3. import와 구조 분해 할당의 관계

다음과 같은 문법은 named import 방식이다.

```js
import { ref, reactive } from 'vue';
```

중괄호를 사용해서 필요한 이름만 꺼내 온다는 점에서  
객체 구조 분해 할당과 비슷하게 볼 수 있다.

---

## 5. this

`this`는 함수나 메서드가 호출될 때 결정되는 특별한 참조값이다.

보통 **현재 호출 중인 메서드를 가지고 있는 객체**와 연결된다.

```js
const person = {
  name: '홍길동',
  info() {
    console.log(this.name);
  },
};

person.info(); // 홍길동
```

메서드를 가진 객체가 없이 일반 함수로 호출하면, 상황에 따라 전역 객체 또는 `undefined`가 연결될 수 있다.  
특히 엄격 모드에서는 `undefined`가 된다.

---

## 6. this를 변경하는 메서드

함수나 메서드를 호출할 때 `this`를 바꿀 수 있다.

### bind()

`bind()`는 지정한 객체를 `this`로 고정한 **새 함수**를 반환한다.

```js
function hello() {
  console.log(this.name);
}

const user = { name: '홍길동' };
const newFunc = hello.bind(user);

newFunc(); // 홍길동
```

### call()

`call()`은 지정한 객체를 `this`로 연결한 뒤 함수를 **즉시 호출**한다.

```js
function hello(city) {
  console.log(this.name, city);
}

const user = { name: '홍길동' };
hello.call(user, '서울');
```

### apply()

`apply()`도 `call()`과 같지만, 인수를 배열 형태로 전달한다.

```js
function hello(city, age) {
  console.log(this.name, city, age);
}

const user = { name: '홍길동' };
hello.apply(user, ['서울', 20]);
```

---

## 7. 화살표 함수와 this

화살표 함수는 자신의 `this`를 따로 만들지 않는다.  
대신 **함수를 정의한 바깥 영역의 this를 그대로 사용**한다.

```js
const obj = {
  name: '홍길동',
  normal() {
    console.log(this.name);
  },
  arrow: () => {
    console.log(this.name);
  },
};

obj.normal(); // 홍길동
obj.arrow(); // 바깥 this 기준
```

즉, 화살표 함수의 `this`는 호출 방식보다  
**정의된 위치의 this**에 의해 결정된다.

---

## 8. ES6 객체 리터럴

변수명과 객체 속성명이 같으면 한 번만 적을 수 있다.

```js
const name = '홍길동';
const age = 20;

const obj = { name, age };
```

위 코드는 아래와 같은 의미이다.

```js
const obj = {
  name: name,
  age: age,
};
```

또한 메서드를 정의할 때 `function` 키워드를 생략할 수 있다.

```js
const person = {
  name: '홍길동',
  hello() {
    console.log('hello');
  },
};
```

이 문법은 객체 구조 분해 할당과 반대 방향의 개념처럼 볼 수 있다.

- 구조 분해 할당: 객체에서 값을 꺼내 변수에 넣음
- 객체 리터럴 축약: 변수 값을 객체 속성으로 넣음

---

## 9. 템플릿 리터럴

템플릿 리터럴은 백틱 `` ` ` `` 으로 감싼 문자열이다.

`${표현식}` 문법을 사용해 값을 문자열 안에 쉽게 삽입할 수 있다.

```js
const name = '홍길동';
const age = 20;

console.log(`${name}의 나이는 ${age}세입니다.`);
```

또한 여러 줄 문자열을 편하게 작성할 수 있다.

```js
const text = `
안녕하세요
반갑습니다
`;
```

특징은 다음과 같다.

- 문자열 중간에 값 삽입 가능
- 여러 줄 작성 가능
- 문자열 조합이 더 간결함

---

## 10. 전개 연산자

전개 연산자 `...`는 객체나 배열의 값을 풀어서 전달할 때 사용한다.

```js
const arr = [1, 2, 3];
console.log(...arr); // 1 2 3
```

가변 파라미터와 모양은 같지만 위치와 역할이 다르다.

- 대입문이나 값 펼치기에서 쓰이면 **전개 연산자**
- 함수 매개변수 자리에서 쓰이면 **가변 파라미터(rest parameter)**

### 10-1. 객체 복사

```js
const obj1 = { name: '홍길동', age: 20 };
const obj2 = obj1;
```

위 코드는 참조를 복사한 것이므로 `obj1`과 `obj2`가 같은 객체를 가리킨다.

반면 전개 연산자를 쓰면 값만 복사한 새 객체를 만들 수 있다.

```js
const obj3 = { ...obj1 };
```

이 경우 `obj1`과 `obj3`는 서로 다른 객체이다.

즉,

- `let obj2 = obj1` → 참조 복사
- `let obj3 = { ...obj1 }` → 얕은 복사, 새 객체 생성

### 10-2. 객체에 속성 추가

```js
const color = 'blue';
const obj4 = { ...obj1, color };
```

기존 객체 값을 복사하면서 새 속성을 추가할 수 있다.

### 10-3. 배열에 값 펼치기

```js
const arr1 = [1, 2, 3];
const arr2 = ['hello', ...arr1, 'world'];

console.log(arr2); // ["hello", 1, 2, 3, "world"]
```

전개 연산자를 사용하면 배열의 요소가 하나씩 펼쳐져 들어간다.

만약 `...` 없이 넣으면 배열이 통째로 들어가서 중첩 배열이 될 수 있다.

```js
const arr3 = ['hello', arr1, 'world'];
// ["hello", [1, 2, 3], "world"]
```

---

## 11. Vue.js에서 ES6가 중요한 이유

Vue를 사용할 때 ES6 문법은 거의 필수처럼 함께 사용된다.

특히 다음 문법이 자주 쓰인다.

- `let`, `const`
- 구조 분해 할당
- 화살표 함수
- 템플릿 리터럴
- 전개 연산자
- 객체 리터럴 축약 표현
- `import`, `export`

Vue 컴포넌트, props, emits, reactive 데이터 처리, 배열 및 객체 복사 등에서  
ES6 문법이 매우 자주 등장한다.

---

## 12. 속성과 프로퍼티

자바스크립트와 Vue를 이해할 때 **값 자체를 다루는 속성**과  
**함수로 관리되는 속성**을 구분해서 볼 필요가 있다.

### 12-1. 일반 속성

일반 속성은 값을 직접 저장하고, 그 값에 바로 접근하는 형태이다.

```js
const obj = { message: 'hello' };
obj.message = 'hi';
```

이처럼 대입 연산자를 사용해서 값을 바로 읽고 쓸 수 있다.

### 12-2. get, set으로 관리되는 프로퍼티

프로퍼티는 내부적으로 `get`, `set` 함수로 동작하도록 만들 수 있다.

```js
const obj = {
  _message: 'hello',
  get message() {
    return this._message;
  },
  set message(value) {
    this._message = value;
  },
};

obj.message = 'hi';
console.log(obj.message);
```

겉으로 보기에는 일반 속성과 사용법이 비슷하다.

```js
obj.message = 'hi';
```

하지만 내부적으로는

- 값을 읽을 때 `get`
- 값을 바꿀 때 `set`

이 동작한다.

즉, **사용법은 대입 연산자처럼 보이지만 내부 동작은 함수 호출 기반**이라고 볼 수 있다.

### 12-3. get, set 이름

접근자 프로퍼티를 만들 때는 함수 이름이 임의로 정해지는 것이 아니라  
`get 속성명`, `set 속성명` 형태로 고정된다.

예:

```js
get message() { ... }
set message(value) { ... }
```

---

## 13. Vue와 Proxy

Vue 3의 중요한 특징 중 하나는 **Proxy 기반 반응형 처리**이다.

### 13-1. Proxy란?

Proxy는 객체의 속성 접근을 가로채서  
읽기와 쓰기 동작을 중간에서 감시하거나 제어할 수 있게 해 주는 기능이다.

즉, getter / setter와 비슷한 역할을 더 일반적인 객체 레벨에서 처리할 수 있게 해 준다.

```js
const target = { message: 'hello' };

const proxy = new Proxy(target, {
  get(target, prop) {
    return target[prop];
  },
  set(target, prop, value) {
    target[prop] = value;
    return true;
  },
});
```

### 13-2. Proxy의 큰 특징

Proxy의 가장 큰 특징은 **투명성**이다.

즉, 사용하는 입장에서는 일반 객체처럼 접근하지만  
내부에서는 Proxy가 중간에서 동작을 감지하고 제어한다.

```js
proxy.message = 'hi';
console.log(proxy.message);
```

겉으로는 평범한 객체처럼 보이지만,  
실제로는 `get`, `set` 동작이 중간에서 가로채진다.

### 13-3. Vue와 Proxy

Vue 3에서는 `data()`에서 반환한 객체를 반응형으로 관리하기 위해  
Proxy로 감싸서 처리한다.

```js
let vm = Vue.createApp({
  name: 'App',
  data() {
    return model;
  },
}).mount('#app');
```

이때 `data()`가 반환한 모델 데이터 객체는  
Vue에 의해 반응형 객체처럼 관리된다.

즉, 값을 읽거나 바꿀 때 Vue가 그 동작을 감지할 수 있게 된다.

### 13-4. `vm.message = "xxx"`의 의미

```js
vm.message = 'xxx';
```

이 코드는 겉보기에는 단순한 대입처럼 보이지만,  
Vue 내부에서는 `set`에 해당하는 반응형 처리 흐름이 동작한다.

즉,

1. 값 변경 감지
2. 관련된 화면 다시 계산
3. 렌더링 갱신

과정이 이어질 수 있다.

그래서 Vue는 데이터가 바뀌면 화면을 자동으로 다시 그릴 수 있다.

---

## 14. 배열의 원본 변경 메서드와 Vue 반응형 처리

배열에는 **원본 배열 자체를 변경하는 메서드**가 있다.

대표적인 메서드는 다음과 같다.

- `sort()`
- `splice()`
- `push()`
- `pop()`
- `shift()`
- `unshift()`

예:

```js
const arr = [3, 1, 2];
arr.sort();

console.log(arr); // [1, 2, 3]
```

이런 메서드는 **배열의 참조를 바꾸지 않고 내부 값만 변경**한다.

즉,

- 배열 자체는 같은 객체
- 내부 요소만 바뀜

일반 자바스크립트에서는 이런 변화를 직접 추적해야 할 수 있지만,  
Vue에서는 Proxy가 이러한 변경을 감지할 수 있다.

그래서 배열에 대해 다음과 같은 메서드를 사용해도

```js
vm.items.push('new item');
vm.items.splice(0, 1);
vm.items.sort();
```

Vue가 변화를 감지하고 렌더링을 다시 수행할 수 있다.

이 점이 Vue 반응형 시스템의 큰 장점 중 하나이다.

---

## 15. 정리

- `let`과 `const`는 블록 스코프를 가진다.
- `const`는 재할당이 불가능하지만 참조형 내부 데이터 수정은 가능하다.
- 기본 파라미터는 인수가 생략되었을 때 사용할 기본값을 지정한다.
- 가변 파라미터는 여러 개의 인수를 배열 형태로 받을 수 있다.
- 구조 분해 할당은 배열과 객체의 값을 쉽게 꺼내 쓸 수 있게 해 준다.
- `this`는 호출 방식에 따라 달라질 수 있다.
- `bind()`, `call()`, `apply()`로 `this`를 직접 지정할 수 있다.
- 화살표 함수는 바깥 영역의 `this`를 그대로 사용한다.
- 객체 리터럴 축약 문법으로 코드를 더 간단히 작성할 수 있다.
- 템플릿 리터럴은 문자열 조합과 여러 줄 문자열 작성에 유용하다.
- 전개 연산자는 객체와 배열을 복사하거나 펼칠 때 자주 사용된다.
- Vue는 `get`, `set`처럼 동작하는 반응형 처리 구조를 활용한다.
- Vue 3는 Proxy를 이용해 객체와 배열의 변화를 감지한다.
- 배열의 원본 변경 메서드도 Vue가 감지하여 화면을 다시 렌더링할 수 있다.
- Vue.js를 효율적으로 사용하려면 ES6 문법과 반응형 처리 개념에 익숙해야 한다.
