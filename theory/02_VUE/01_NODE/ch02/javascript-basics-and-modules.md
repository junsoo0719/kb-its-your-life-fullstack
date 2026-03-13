# 자바스크립트 기초 문법과 모듈

## 1. 함수와 값의 활용

자바스크립트에서 함수는 값처럼 다룰 수 있다.

대표적인 활용 방식은 다음과 같다.

- **매개변수로 전달**할 수 있다.
  - 콜백 함수 전달
- **리턴값으로 받을** 수 있다.

예:

```js
function greet() {
  console.log('hello');
}

function run(callback) {
  callback();
}

run(greet);
```

---

## 2. 동기 처리와 비동기 처리

### 동기 처리

작업을 **순서대로 하나씩** 실행하는 방식이다.  
앞의 작업이 끝나야 다음 작업이 실행된다.

### 비동기 처리

시간이 오래 걸리는 작업을 시작해 두고,  
그 작업이 끝날 때까지 기다리지 않고 **다음 작업을 먼저 실행**하는 방식이다.

주로 다음과 같은 작업에서 사용된다.

- 파일 읽기/쓰기
- 네트워크 통신
- 데이터베이스 처리
- 타이머 처리

---

## 3. 콜백 함수

**콜백(callback)** 은 어떤 작업이 끝난 뒤 실행할 함수를 의미한다.

즉, **작업 완료 후 실행할 함수**를 미리 전달해 두는 방식이다.

예:

```js
function work(callback) {
  console.log('작업 수행');
  callback();
}

work(function () {
  console.log('작업 완료 후 실행');
});
```

콜백은 간단한 비동기 처리에 자주 쓰이지만,  
여러 단계가 중첩되면 코드가 복잡해질 수 있다.

---

## 4. Promise

Promise는 비동기 작업의 **성공** 또는 **실패**를 관리하기 위한 객체이다.

Promise 객체를 생성할 때는 비동기 작업을 수행할 함수를 전달한다.

기본 형식:

```js
const promise = new Promise((resolve, reject) => {
  // 비동기 작업 수행

  if (성공) {
    resolve(결과값);
  } else {
    reject(에러);
  }
});
```

### resolve

작업이 **성공했을 때 호출**하는 함수이다.  
매개변수로 작업 결과를 전달한다.

### reject

작업이 **실패했을 때 호출**하는 함수이다.  
매개변수로 에러 객체 또는 에러 정보를 전달한다.

---

## 5. Promise 객체의 메서드

### then(result)

작업이 성공했을 때 실행된다.  
`resolve()`에 전달한 값이 `then()`의 매개변수로 들어온다.

### catch(err)

작업이 실패했을 때 실행된다.  
`reject()`에 전달한 값이 `catch()`의 매개변수로 들어온다.

예:

```js
const promise = new Promise((resolve, reject) => {
  const success = true;

  if (success) {
    resolve('작업 성공');
  } else {
    reject(new Error('작업 실패'));
  }
});

promise
  .then((result) => {
    console.log(result);
  })
  .catch((err) => {
    console.log(err.message);
  });
```

---

## 6. async / await

### async

함수 앞에 `async`를 붙이면 그 함수는 **항상 Promise를 반환**한다.

```js
async function hello() {
  return 'hello';
}
```

위 함수는 실제로는 Promise 형태로 값을 반환한다.

### await

`await`는 Promise가 처리될 때까지 기다렸다가  
처리 결과를 반환받는 키워드이다.

- **async 함수 내부에서만 사용 가능**
- Promise가 끝날 때까지 기다린 뒤 결과를 꺼내 쓸 수 있다.

예:

```js
function delay() {
  return new Promise((resolve) => {
    setTimeout(() => resolve('완료'), 1000);
  });
}

async function run() {
  const result = await delay();
  console.log(result);
}

run();
```

`async/await`를 사용하면 Promise 체인을 더 읽기 쉽게 작성할 수 있다.

---

## 7. CommonJS 모듈 시스템

Node.js의 전통적인 모듈 방식은 **CommonJS** 이다.

### 7-1. module.exports

`module.exports`에는 **객체, 값, 함수 등 하나의 대상**을 할당할 수 있다.

```js
module.exports = function () {
  console.log('hello');
};
```

### 7-2. exports.xxx

`exports`의 속성으로 값을 추가하면 여러 개를 내보낼 수 있다.

```js
exports.add = (a, b) => a + b;
exports.sub = (a, b) => a - b;
```

### 7-3. exports와 module.exports의 관계

기본적으로 다음과 같은 관계로 시작한다.

```js
exports = module.exports = {};
```

즉, 처음에는 같은 객체를 가리킨다.

그래서 속성을 추가할 때는 둘 다 동작할 수 있다.

```js
exports.add = ...
module.exports.sub = ...
```

하지만 `exports = 다른값`처럼 **새 값을 직접 대입하면 연결이 끊길 수 있으므로**  
실무에서는 내보내는 기준을 `module.exports`로 이해하는 것이 안전하다.

---

## 8. require() 모듈 탐색 방식

`require('')`는 모듈을 불러오는 함수이다.

### 8-1. 이름만 있는 경우

예:

```js
require('fs');
require('express');
```

이 경우 Node.js는 다음 순서로 모듈을 찾는다.

1. **내장 모듈** 찾기
2. 현재 폴더의 `node_modules` 찾기
3. 상위 폴더의 `node_modules` 찾기
4. 루트 폴더까지 반복
5. 전역 모듈 위치 확인

### 8-2. 경로가 포함된 경우

예:

```js
require('./math');
require('../util/test');
```

이 경우에는 **현재 파일 기준의 경로**를 따라 탐색한다.

- `./` : 현재 디렉토리
- `../` : 상위 디렉토리

---

## 9. CommonJS에서 제공되는 전역 정보

### \_\_dirname

현재 모듈이 위치한 **디렉토리 경로**

### \_\_filename

현재 모듈의 **파일 전체 경로**

예:

```js
console.log(__dirname);
console.log(__filename);
```

---

## 10. ES 모듈 시스템

ES 모듈은 자바스크립트 표준 모듈 시스템이다.

### 10-1. export

변수, 함수, 클래스 등을 내보낼 수 있다.

```js
export const pi = 3.14;
export function add(a, b) {
  return a + b;
}
```

### 10-2. export default

기본 내보내기이다.

- 한 모듈에서 **1개만 사용 가능**
- 가져올 때 이름을 자유롭게 정할 수 있다
- 중괄호 없이 import 한다

```js
export default function () {
  console.log('default export');
}
```

가져오기:

```js
import myFunc from './a.js';
```

### 10-3. 여러 개를 묶어서 export

여러 대상을 한 번에 내보낼 수도 있다.

```js
const a = 10;
const b = 20;

export { a, b };
```

---

## 11. ES 모듈 import 방식

### 11-1. named import

`export`로 내보낸 이름을 가져올 때 사용한다.

- **중괄호 `{}` 필요**
- export한 이름과 같아야 한다
- `as`를 사용해 이름을 바꿀 수 있다

```js
import { a, b } from './a.js';
import { a as num1, b as num2 } from './a.js';
```

### 11-2. default import

`export default`로 내보낸 값을 가져올 때 사용한다.

- **중괄호 없음**
- 이름은 자유롭게 정할 수 있다

```js
import value from './a.js';
```

### 11-3. namespace import

모듈 전체를 하나의 객체처럼 묶어서 가져온다.

- 이름 충돌을 피하기 좋다

```js
import * as myModule from './a.js';
```

사용 예:

```js
console.log(myModule.a);
console.log(myModule.b);
```

---

## 12. export와 export default를 같이 사용하는 경우

한 파일 안에서 `export`와 `export default`를 함께 사용할 수 있다.

예: `a.js`

```js
export const t1 = 10;
export const t2 = 20;
export const t5 = 50;

const x = 'default value';
export default x;
```

가져오는 방법:

### t1만 가져올 때

```js
import { t1 } from './a.js';
```

### t1, t5를 가져올 때

```js
import { t1, t5 } from './a.js';
```

### default 값을 가져올 때

```js
import n from './a.js';
```

### named export와 default export를 같이 가져올 때

```js
import n, { t1, t5 } from './a.js';
```

---

## 13. Node.js 모듈 캐시

Node.js는 한 번 불러온 모듈을 **캐시(cache)** 한다.

예:

```js
// a.js
console.log('hello');
```

이 파일을 여러 번 import 또는 require 하더라도,  
실제로 모듈 본문이 실행되는 횟수는 **처음 한 번**이다.

즉,

- 같은 모듈을 여러 번 불러와도
- 매번 새로 실행되지 않고
- 캐시된 결과를 재사용한다

이 특징은 성능에는 도움이 되지만,  
모듈 내부 상태를 변경하는 코드가 있다면 결과에 영향을 줄 수 있다.

---

## 14. 정리

- 함수는 값처럼 전달하거나 반환할 수 있다.
- 비동기 처리는 오래 걸리는 작업을 기다리지 않고 다음 작업을 수행한다.
- 콜백은 작업 완료 후 실행할 함수이다.
- Promise는 비동기 작업의 성공과 실패를 체계적으로 처리한다.
- `async/await`는 Promise를 더 읽기 쉽게 다룰 수 있게 해 준다.
- CommonJS에서는 `module.exports`, `exports`, `require()`를 사용한다.
- ES 모듈에서는 `export`, `export default`, `import`를 사용한다.
- Node.js는 모듈을 한 번만 실행하고 이후에는 캐시를 사용한다.
