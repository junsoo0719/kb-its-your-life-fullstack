# 배열 메서드 정리

## 1. Array.from()

- `Array.from()`은 **배열이 아닌 iterable 객체** 또는 **유사 배열 객체(array-like object)** 를 배열로 변환할 때 사용함
- 예를 들어 `NodeList`, 문자열 등을 배열로 바꿀 수 있음

예:

```javascript
const str = 'hello';
const arr = Array.from(str);

console.log(arr); // ['h', 'e', 'l', 'l', 'o']
```

예:

```javascript
const items = document.querySelectorAll('li');
const itemArray = Array.from(items);
```

---

## 2. indexOf()

- `indexOf(item, start)`는 배열에서 `item`을 찾아 **처음 발견한 인덱스**를 반환
- 찾지 못하면 `-1` 반환
- 두 번째 매개변수 `start`를 넣으면 해당 위치부터 탐색

예:

```javascript
const fruits = ['apple', 'banana', 'orange', 'banana'];

console.log(fruits.indexOf('banana')); // 1
console.log(fruits.indexOf('banana', 2)); // 3
console.log(fruits.indexOf('grape')); // -1
```

---

## 3. lastIndexOf()

- `lastIndexOf(item, start)`는 배열에서 `item`을 찾아 **뒤에서부터 탐색한 결과의 인덱스**를 반환
- 찾지 못하면 `-1` 반환
- 두 번째 매개변수가 없으면 **배열의 끝에서부터 탐색**
- 두 번째 매개변수가 있으면 **해당 인덱스부터 거꾸로 탐색**

예:

```javascript
const fruits = ['apple', 'banana', 'orange', 'banana'];

console.log(fruits.lastIndexOf('banana')); // 3
console.log(fruits.lastIndexOf('banana', 2)); // 1
```

---

## 4. push()와 pop()

### 4-1. push()

- `push(a, b, c, ...)`는 배열 **맨 끝에 요소를 추가**
- 여러 개를 한 번에 넣을 수 있음
- 반환값은 **변경된 배열의 길이(length)**

예:

```javascript
const arr = [1, 2];
arr.push(3, 4);

console.log(arr); // [1, 2, 3, 4]
```

### 4-2. pop()

- `pop()`은 배열의 **마지막 요소를 제거하고 그 값을 반환**
- 원본 배열이 바뀜

예:

```javascript
const arr = [1, 2, 3];

const result = arr.pop();

console.log(result); // 3
console.log(arr); // [1, 2]
```

정리:

- `push()` + `pop()` 조합으로 **스택(stack)** 구조를 만들 수 있음

---

## 5. shift()와 unshift()

### 5-1. shift()

- `shift()`는 배열의 **첫 번째 요소를 제거하고 그 값을 반환**
- 원본 배열이 바뀜

예:

```javascript
const arr = [1, 2, 3];

const result = arr.shift();

console.log(result); // 1
console.log(arr); // [2, 3]
```

### 5-2. unshift()

- `unshift(a, b, c, ...)`는 배열의 **맨 앞에 요소를 추가**
- 여러 개를 한 번에 넣을 수 있음
- 넣은 순서대로 앞쪽에 추가됨
- 반환값은 **변경된 배열의 길이(length)**

예:

```javascript
const arr = [3, 4];
arr.unshift(1, 2);

console.log(arr); // [1, 2, 3, 4]
```

정리:

- `push()` + `shift()` 조합으로 **큐(queue)** 구조를 만들 수 있음
- 또는 `unshift()` + `pop()` 조합으로도 큐처럼 사용할 수 있음

> 정정:
>
> - 맨앞 **삭제**는 `shift()`
> - 맨앞 **추가**는 `unshift()`
> - 맨끝 **삭제**는 `pop()`
> - 맨끝 **추가**는 `push()`

---

## 6. reverse()

- `reverse()`는 배열의 순서를 **뒤집음**
- 원본 배열을 직접 변경함

예:

```javascript
const arr = [1, 2, 3];
arr.reverse();

console.log(arr); // [3, 2, 1]
```

---

## 7. sort()

- `sort()`는 배열을 정렬함
- **원본 배열을 직접 변경**
- 기본적으로는 **문자열 기준 정렬**을 수행함

예:

```javascript
const arr = ['banana', 'apple', 'orange'];
arr.sort();

console.log(arr); // ['apple', 'banana', 'orange']
```

### 7-1. 숫자 정렬 시 주의

`sort()`는 기본적으로 문자열처럼 비교하므로 숫자 배열은 원하는 결과가 안 나올 수 있음.

예:

```javascript
const numbers = [10, 2, 30, 4];
numbers.sort();

console.log(numbers); // [10, 2, 30, 4] 또는 문자열 기준 정렬 결과
```

정확한 숫자 정렬을 하려면 **비교 함수(compare function)** 를 넣어야 함.

---

### 7-2. 오름차순 정렬

```javascript
const numbers = [10, 2, 30, 4];
numbers.sort(function (left, right) {
  return left - right;
});

console.log(numbers); // [2, 4, 10, 30]
```

화살표 함수:

```javascript
numbers.sort((a, b) => a - b);
```

---

### 7-3. 내림차순 정렬

```javascript
const numbers = [10, 2, 30, 4];
numbers.sort(function (left, right) {
  return right - left;
});

console.log(numbers); // [30, 10, 4, 2]
```

화살표 함수:

```javascript
numbers.sort((a, b) => b - a);
```

---

### 7-4. 비교 함수 해석

`sort()`의 비교 함수는 반환값의 부호에 따라 순서를 결정함.

```javascript
function compare(left, right) {
  return left - right;
}
```

정리:

- 반환값이 **음수**면 `left`가 `right`보다 앞에 옴
- 반환값이 **0**이면 순서를 유지
- 반환값이 **양수**면 `right`가 `left`보다 앞에 옴

예를 들어 오름차순에서:

- `left - right < 0`이면 `left`가 더 작으므로 앞에 감
- `left - right > 0`이면 `right`가 더 작으므로 앞에 감

내림차순은 반대로 `right - left`를 사용하면 됨.

---

### 7-5. sort() 후 reverse()

오름차순 정렬 후 `reverse()`를 해서 내림차순처럼 만들 수도 있음.

```javascript
const numbers = [10, 2, 30, 4];
numbers.sort((a, b) => a - b);
numbers.reverse();

console.log(numbers); // [30, 10, 4, 2]
```

다만 보통은 처음부터 비교 함수를 써서 정렬하는 방식이 더 명확함.

---

## 8. 연산자와 숫자/문자열 처리

- `+` 연산자는 문자열이 섞이면 **문자열 연결**로 동작할 수 있음
- `%` 같은 연산자는 보통 숫자로 변환해서 계산함

예:

```javascript
console.log('10' + 2); // "102"
console.log('10' % 3); // 1
```

그래서 숫자 정렬이나 계산에서는 값의 타입을 잘 확인해야 함.

---

## 9. slice()

- `slice(start, end)`는 배열에서 **start 이상, end 미만** 구간을 잘라서 **새 배열로 반환**
- `end` 인덱스는 포함되지 않음
- 원본 배열은 바뀌지 않음

예:

```javascript
const arr = [10, 20, 30, 40, 50];
const result = arr.slice(1, 4);

console.log(result); // [20, 30, 40]
console.log(arr); // [10, 20, 30, 40, 50]
```

---

## 10. splice()

- `splice(index, howmany, item1, ..., itemX)`는 배열의 특정 위치에서
  1. `howmany`개를 삭제하고
  2. 그 자리에 새 요소들을 삽입함
- 원본 배열이 직접 변경됨
- 반환값은 **삭제된 요소들의 배열**

예:

```javascript
const arr = [10, 20, 30, 40];
const result = arr.splice(1, 2, 99, 100);

console.log(result); // [20, 30]
console.log(arr); // [10, 99, 100, 40]
```

정리:

- 중간 요소 삭제: `splice(index, 1)`
- 중간에 요소 삽입: `splice(index, 0, 새값)`

예:

```javascript
const arr = [1, 2, 4];
arr.splice(2, 0, 3);

console.log(arr); // [1, 2, 3, 4]
```

---

## 11. concat()

- `concat(a, b, c, ...)`는 배열이나 값을 이어 붙여서 **새 배열을 반환**
- 원본 배열은 바뀌지 않음

예:

```javascript
const a = [1, 2];
const b = [3, 4];
const result = a.concat(b);

console.log(result); // [1, 2, 3, 4]
console.log(a); // [1, 2]
```

---

## 12. join()

- `join(delimiter)`는 배열의 요소들을 하나의 문자열로 합침
- 구분자를 지정할 수 있음
- 구분자를 생략하면 기본값은 `,`

예:

```javascript
const arr = ['apple', 'banana', 'orange'];

console.log(arr.join()); // "apple,banana,orange"
console.log(arr.join(' / ')); // "apple / banana / orange"
```

---

## 13. forEach()

- `forEach()`는 배열의 각 요소를 하나씩 순회하면서 작업할 때 사용
- **반환값이 없음**
- 원본 배열의 각 요소에 대해 주어진 함수를 실행함

기본 형태:

```javascript
array.forEach(function (value, index, array) {
  // 작업
});
```

예:

```javascript
const score = [80, 90, 100];
let sum = 0;

score.forEach(function (value) {
  sum += value;
});

console.log(sum); // 270
```

정리:

- 매개변수는 원래 `값`, `인덱스`, `전체 배열`
- 각 원소 값만 필요하면 매개변수 1개만 써도 됨

---

## 14. map()

- `map()`은 기존 배열의 각 요소를 가공해서 **새로운 배열**을 만듦
- **원소 개수는 유지**
- 데이터만 변형됨
- 반환값이 필요함

기본 형태:

```javascript
array.map(function (value, index, array) {
  return 가공한값;
});
```

예:

```javascript
const score = [80, 90, 100];

const score2 = score.map(function (value) {
  return value * 2;
});

console.log(score2); // [160, 180, 200]
console.log(score); // [80, 90, 100]
```

정리:

- 원본 배열은 유지
- 새로운 배열 생성
- 각 요소를 변형할 때 사용

---

## 15. filter()

- `filter()`는 조건을 만족하는 요소만 골라서 **새로운 배열**을 만듦
- 기존 데이터를 그대로 유지하면서, **원소 개수만 달라질 수 있음**
- 콜백 함수가 `true`를 반환한 요소만 남음

기본 형태:

```javascript
array.filter(function (value, index, array) {
  return 조건식;
});
```

예:

```javascript
const score = [70, 80, 90, 60, 100];

const score2 = score.filter(function (value) {
  return value >= 80;
});

console.log(score2); // [80, 90, 100]
console.log(score); // [70, 80, 90, 60, 100]
```

정리:

- 데이터 값 자체는 그대로 유지
- 조건에 맞는 요소만 추림
- 새 배열 반환

---

## 핵심 요약

- `Array.from()`은 iterable 또는 유사 배열 객체를 배열로 바꿀 때 사용
- `indexOf()`는 앞에서부터 찾고, `lastIndexOf()`는 뒤에서부터 찾음
- `push()` / `pop()`은 배열 끝에서 작업하며 스택 구현 가능
- `shift()` / `unshift()`는 배열 앞에서 작업함
- `reverse()`는 배열 순서를 뒤집고 원본을 바꿈
- `sort()`는 기본적으로 문자열 기준 정렬이며, 숫자 정렬은 비교 함수가 필요함
- `slice()`는 원본을 바꾸지 않고 일부를 잘라 새 배열 반환
- `splice()`는 원본을 바꾸면서 삭제/삽입 가능
- `concat()`은 배열을 이어 붙여 새 배열 반환
- `join()`은 배열 요소를 문자열로 합침
- `forEach()`는 순회용, 반환값 없음
- `map()`은 각 요소를 가공한 새 배열 반환
- `filter()`는 조건에 맞는 요소만 모아 새 배열 반환
