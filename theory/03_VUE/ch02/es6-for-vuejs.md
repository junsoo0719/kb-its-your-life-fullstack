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

---

### 4-1. 배열 구조 분해 할당

배열은 **순서에 따라** 값이 할당된다.

```js
const arr = [10, 20, 30];
const [a, b, c] = arr;

console.log(a, b, c); // 10 20 30
```

---

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

---

### 4-3. import와 구조 분해 할당의 관계

다음과 같은 문법은 named import 방식이다.

```js
import { ref, reactive } from 'vue';
```

중괄호를 사용해서 필요한 이름만 꺼내 온다는 점에서  
객체 구조 분해 할당과 비슷하게 볼 수 있다.

다만 문법 자체가 구조 분해 할당 그 자체는 아니고,  
**형태와 사용 방식이 유사한 import 문법**으로 이해하면 된다.

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

---

### call()

`call()`은 지정한 객체를 `this`로 연결한 뒤 함수를 **즉시 호출**한다.

```js
function hello(city) {
  console.log(this.name, city);
}

const user = { name: '홍길동' };
hello.call(user, '서울');
```

---

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

그래서 객체의 메서드를 만들 때는 보통 일반 메서드 문법을 더 많이 사용한다.

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

---

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

---

### 10-2. 객체에 속성 추가

```js
const color = 'blue';
const obj4 = { ...obj1, color };
```

기존 객체 값을 복사하면서 새 속성을 추가할 수 있다.

---

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

## 12. 정리

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
- Vue.js를 효율적으로 사용하려면 ES6 문법에 익숙해야 한다.
