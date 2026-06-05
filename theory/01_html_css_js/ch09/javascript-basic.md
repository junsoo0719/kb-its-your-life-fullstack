# JavaScript 기본 문법

## 1. 표기법

- **케밥 표기법(kebab-case)**: `ax-bx-cx`
  - HTML, CSS에서 주로 사용
  - JavaScript 변수명에는 보통 사용하지 않음
  - `-`가 빼기 연산자로 해석될 수 있기 때문

- **스네이크 표기법(snake_case)**: `ax_bx_cx`
  - C, C++, Python 등에서 자주 사용

- **카멜 표기법(camelCase)**: `axBxCx`
  - Java, JavaScript에서 변수명, 함수명에 자주 사용

- **파스칼 표기법(PascalCase)**: `AxBxCx`
  - JavaScript, Java 등에서 클래스명, 생성자 함수명에 자주 사용

---

## 2. `<script>` 태그

- `<script>` 태그는 **등장 횟수와 위치에 제한이 없음**
- 하지만 유지보수를 위해 **한곳에 모아서 관리하는 것이 좋음**
- 보통:
  - `<head>` 안에 작성하거나
  - `</body>` 바로 위에 작성
- 실제로는 **외부 `.js` 파일로 분리**해서 관리하는 경우가 많음

---

## 3. 숫자와 문자열

- JavaScript의 숫자는 기본적으로 **`Number` 타입 하나로 처리됨**
- 정수끼리 나누어도 결과는 `Number` 타입으로 나옴

예:

```javascript
10 / 3; // 3.3333333333333335
```

- `0`으로 나누면:
  - 양수 / 0 → `Infinity`
  - 음수 / 0 → `-Infinity`
  - `0 / 0` → `NaN`

예:

```javascript
10 / 0 - // Infinity
  10 / 0; // -Infinity
0 / 0; // NaN
```

- 문자열은 작은따옴표 `' '` 와 큰따옴표 `" "` 모두 사용 가능
- 보통 한 가지 방식으로 통일해서 사용
- 수업에서는 **작은따옴표 사용**으로 정리해도 됨

---

## 4. 리터럴(literal)

- **실제 데이터를 코드에 직접 적는 것**을 리터럴이라고 함

예:

```javascript
10; // 숫자 리터럴
('hello'); // 문자열 리터럴
true[(1, 2, 3)]; // 불리언 리터럴 // 배열 리터럴
{
  name: 'Tom';
} // 객체 리터럴
```

---

## 5. 문자열과 숫자의 연산

### 5-1. 기본 규칙

- 연산 시 자료형이 다르면 JavaScript가 자동으로 형변환을 시도할 수 있음
- 특히 `+` 연산자는 **문자열 연결**로 동작할 수 있음
- `-`, `*`, `/`, `%`는 보통 **숫자로 변환한 뒤 계산**

예:

```javascript
'10' + 20; // '1020'
'10' - 3; // 7
'10' * 2; // 20
'10' / 2; // 5
```

### 5-2. 정리

- **`+`** : 문자열이 하나라도 있으면 문자열 연결로 처리되는 경우가 많음
- **나머지 연산자** : 숫자로 바꿔서 계산하는 경우가 많음

---

## 6. 비교 연산자

### 6-1. `==`, `!=`

- 비교 전에 **형변환이 일어날 수 있음**

예:

```javascript
'10' == 10; // true
'10' != 10; // false
```

### 6-2. `===`, `!==`

- **값과 자료형을 모두 비교**
- 실무와 학습에서는 **삼중 연산자 사용 권장**

예:

```javascript
'10' === 10; // false
'10' !== 10; // true
```

---

## 7. falsy / truthy

### 7-1. falsy 값

조건문에서 `false`처럼 해석되는 값:

- `false`
- `0`
- `''` (빈 문자열)
- `null`
- `undefined`
- `NaN`

### 7-2. truthy 값

- 위의 falsy가 아닌 대부분의 값은 truthy
- 빈 배열 `[]`도 **truthy**
- 빈 객체 `{}`도 **truthy**

> 참고: Python에서는 빈 리스트가 `False`처럼 해석되지만, JavaScript에서는 `true`처럼 해석됨

### 7-3. `!!a`

- 불리언 타입이 아닌 값을 **명시적으로 boolean 값으로 변환**할 때 사용

예:

```javascript
!!0; // false
!!1; // true
!!''; // false
!!'hello'; // true
```

---

## 8. 변수와 상수

### 8-1. 변수 선언

- 값을 할당하지 않고 선언만 하면 초기값은 `undefined`

예:

```javascript
let a;
console.log(a); // undefined
```

### 8-2. 상수

- 상수는 `const`로 선언
- 상수 이름을 **대문자 + 스네이크 표기법**으로 쓰는 것은 관례
- 필수 규칙은 아니지만 많이 사용함

예:

```javascript
const MAX_SIZE = 10;
```

---

## 9. 논리 연산자

- `&&`, `||`는 단순히 `true` 또는 `false`만 반환하지 않음
- **판별이 끝나는 시점의 실제 값**을 반환함

### 9-1. `&&`

- 왼쪽부터 확인
- falsy를 만나면 그 값을 반환
- 끝까지 모두 truthy면 마지막 값을 반환

예:

```javascript
10 && 20 && 30; // 30
0 && 20 && 30; // 0
```

### 9-2. `||`

- 왼쪽부터 확인
- truthy를 만나면 그 값을 반환
- 끝까지 모두 falsy면 마지막 값을 반환

예:

```javascript
10 || 20 || 30; // 10
0 || 20 || 30; // 20
```

### 9-3. 정리

- 다른 언어처럼 항상 `true`, `false`만 나오는 것이 아님
- **값 자체가 반환될 수 있음**

---

## 10. 배열

### 10-1. 배열의 특징

- 배열 안에 배열을 넣을 수 있음
- JavaScript는 변수 타입이 고정되어 있지 않음
- 따라서 배열 원소의 타입도 고정되지 않음

예:

```javascript
let arr = [1, 'hello', true, [10, 20]];
```

### 10-2. TypeScript

- JavaScript는 타입이 자유로운 대신 실수가 생기기 쉬움
- **타입을 더 엄격하게 관리하기 위해 TypeScript를 사용**하기도 함

### 10-3. `length`

- JavaScript 배열은 `length`를 변경할 수 있음

예:

```javascript
let arr = [1, 2, 3, 4];
arr.length = 2;

console.log(arr); // [1, 2]
```

- 다른 언어에서는 배열 길이가 고정인 경우가 많지만, JavaScript 배열은 비교적 유연함

---

## 11. `for...in` / `for...of`

### 11-1. `for...in`

- **키(key)** 를 순회
- 배열에서는 인덱스가 전달됨
- 객체에서는 속성명이 전달됨

예:

```javascript
let arr = ['a', 'b', 'c'];

for (let i in arr) {
  console.log(i);
}
// 0
// 1
// 2
```

### 11-2. `for...of`

- **값(value)** 을 순회
- 뒤에는 **iterable** 이 와야 함

예:

```javascript
let arr = ['a', 'b', 'c'];

for (let v of arr) {
  console.log(v);
}
// a
// b
// c
```

### 11-3. iterable

`for...of`를 사용할 수 있는 대표적인 iterable:

- 배열(Array)
- 문자열(String)
- Map
- Set 등

### 11-4. 주의

- **일반 객체 `{}` 는 기본적으로 iterable이 아님**
- 따라서 일반 객체에는 `for...of`를 바로 사용할 수 없음

예:

```javascript
let obj = { name: 'Tom', age: 20 };

// 가능
for (let key in obj) {
  console.log(key);
}

// 불가능
// for (let value of obj) { ... }
```

---

## 12. 배열의 `undefined`와 빈 칸(hole)

- 배열에서 `undefined`가 들어 있는 것과
- 아예 값이 없는 **빈 칸(hole)** 은 다름

예:

```javascript
let a = [undefined];
let b = [,];
```

- 둘은 비슷해 보여도 반복문이나 배열 메서드에서 다르게 동작할 수 있음
- 따라서  
  **"undefined의 원소는 반복문에서 제외된다"**  
  라고 단정하면 부정확함

### 정리

- `undefined`는 **값이 있는 상태**
- 빈 칸(hole)은 **값 자체가 없는 상태**

---

## 13. 함수

### 13-1. 반환 타입

- JavaScript 함수는 반환 타입을 따로 지정하지 않음
- `return`을 생략할 수 있음
- `return`이 없으면 `undefined`가 반환됨

예:

```javascript
function f() {}

console.log(f()); // undefined
```

### 13-2. 매개변수 개수

- 함수 호출 시 매개변수 개수가 정확히 일치하지 않아도 됨

예:

```javascript
function square(x) {
  return x * x;
}

square(3); // 9
square(); // NaN
```

- `square()`처럼 호출하면 `x`는 `undefined`
- `undefined * undefined` 결과는 `NaN`

---

## 14. 매개변수 기본값

### 14-1. 예전 방식

예전에는 다음과 같이 쓰기도 했음:

```javascript
function f(x) {
  x = x || 10;
  console.log(x);
}
```

이 방식은 `x`가 `undefined`일 때는 잘 동작함.

하지만 문제점이 있음:

```javascript
f(0);
```

- `0`은 falsy이므로 원래 값 `0` 대신 `10`이 들어갈 수 있음

즉, `0`, `''`, `false`도 기본값으로 바뀔 수 있어서 주의해야 함

### 14-2. 권장 방식

- 요즘은 **기본 매개변수 문법**을 사용

```javascript
function f(x = 10) {
  console.log(x);
}
```

### 14-3. `??` 사용

필요하면 nullish 병합 연산자도 사용 가능:

```javascript
x = x ?? 10;
```

- `??`는 `null` 또는 `undefined`일 때만 기본값을 사용
- `0`, `''`, `false`는 유지됨

---

## 15. 함수도 값이다

- JavaScript에서는 함수를 변수에 할당할 수 있음
- 함수의 반환값으로 함수를 돌려줄 수도 있음

예:

```javascript
let hello = function () {
  console.log('hi');
};

function outer() {
  return function () {
    console.log('inner');
  };
}
```

---

## 16. 배열과 객체

### 16-1. 배열

- **데이터의 순서가 중요할 때** 사용
- 키는 인덱스(0, 1, 2, ...)

예:

```javascript
let colors = ['red', 'blue', 'green'];
```

### 16-2. 객체

- **순서보다 데이터의 의미가 중요할 때** 사용
- 키는 이름
- 중괄호 `{}` 사용
- `키: 값` 형태로 작성
- 이렇게 직접 만드는 문법을 **객체 리터럴**이라고 함

예:

```javascript
let product = {
  name: 'pen',
  price: 1000,
};
```

---

## 17. 객체 접근

객체의 값에 접근하는 방법은 두 가지가 있음.

### 17-1. 점 표기법

```javascript
product.name;
```

### 17-2. 대괄호 표기법

```javascript
product['name'];
```

### 17-3. 변수로 키를 사용할 때

키 이름을 변수로 가지고 있다면 **대괄호 표기법만 가능**

예:

```javascript
let a = 'name';

product[a]; // 가능
product.a; // 'a'라는 이름의 속성을 찾음
```

즉,

- `product[a]` → 변수 `a`의 값이 `'name'`이면 `product['name']`
- `product.a` → 문자 그대로 `a`라는 속성을 찾음

---

## 18. 객체와 함수

- 함수도 객체의 값으로 넣을 수 있음

예:

```javascript
let person = {
  name: 'Tom',
  hello: function () {
    console.log('hi');
  },
};
```

이런 객체 안의 함수를 보통 **메서드(method)** 라고 부름

---

## 19. 객체와 반복

- 일반 객체는 기본적으로 iterable은 아니지만
- `for...in`으로 속성명을 순회할 수 있음

예:

```javascript
let product = {
  name: 'pen',
  price: 1000,
};

for (let key in product) {
  console.log(key, product[key]);
}
```

- 객체의 키, 값, 키-값 쌍이 필요하면 다음 메서드를 사용할 수도 있음:
  - `Object.keys(obj)`
  - `Object.values(obj)`
  - `Object.entries(obj)`

---

## 20. property(속성)

- 객체에 들어 있는 요소 하나하나를 보통 **속성(property)** 이라고 함

예:

```javascript
let user = {
  name: 'Tom',
  age: 20,
};
```

여기서:

- `name` → 속성(property)
- `age` → 속성(property)

> 참고: HTML에서는 `attribute`라는 말을 많이 쓰지만,  
> JavaScript 객체 문맥에서는 보통 `property`라고 하는 편이 더 정확함

---

## 핵심 요약

- JavaScript 변수명/함수명은 보통 **camelCase**
- 클래스명/생성자 함수명은 보통 **PascalCase**
- `<script>` 태그는 여러 번 사용 가능하지만 모아서 관리하는 것이 좋음
- JavaScript 숫자는 기본적으로 `Number` 타입
- `+`는 문자열 연결로 동작할 수 있음
- 비교는 `===`, `!==` 사용 권장
- falsy: `false`, `0`, `''`, `null`, `undefined`, `NaN`
- `[]`, `{}` 는 truthy
- 논리 연산자 `&&`, `||`는 `true/false`만이 아니라 **실제 값**을 반환
- 배열은 순서 중심, 객체는 이름/의미 중심
- `for...in`은 키, `for...of`는 값
- 일반 객체 `{}` 는 기본적으로 iterable이 아님
- 함수는 값처럼 다룰 수 있음
- 객체의 함수는 메서드라고 부름
- 객체의 요소는 보통 property(속성)라고 부름
