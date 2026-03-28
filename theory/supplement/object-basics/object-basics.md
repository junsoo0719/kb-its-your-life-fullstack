# 객체

## 1. 객체란

자바스크립트에서 객체는 여러 개의 값과 기능을 하나로 묶어 표현하는 데이터 구조이다.

객체는 보통 다음과 같은 형태로 만든다.

```js
const person = {
  name: '홍길동',
  age: 20,
};
```

여기서

- `name`, `age`는 속성(property)
- 객체 안에 들어 있는 함수는 메서드(method)

라고 부른다.

---

## 2. 객체 리터럴 표기

`{}`를 사용해 객체 인스턴스를 바로 만드는 방식을 **객체 리터럴 표기**라고 한다.

```js
const student = {
  name: '김철수',
  grade: 3,
};
```

이 방식은 자바스크립트에서 매우 자주 사용된다.

즉,

- 객체를 빠르게 만들 수 있고
- 바로 속성을 넣을 수 있으며
- 간단한 데이터 구조를 표현할 때 편하다

는 장점이 있다.

---

## 3. Singleton 패턴과 객체 리터럴

객체 리터럴은 한 번만 사용할 객체를 만들 때도 자주 사용된다.

예:

```js
const config = {
  appName: 'Todo App',
  version: '1.0',
};
```

이처럼 프로그램 안에서 하나의 객체만 두고 사용하는 형태는  
Singleton 패턴처럼 이해할 수도 있다.

즉, 객체 리터럴은 단일 설정 객체나 단일 관리 객체를 만들 때도 잘 어울린다.

---

## 4. 추상화

추상화는 현실에 존재하는 대상에서  
필요한 속성이나 기능만 뽑아내어 표현하는 것이다.

예를 들어 학생을 객체로 표현할 때  
현실의 모든 정보를 넣는 것이 아니라 필요한 정보만 추린다.

```js
const student = {
  name: '홍길동',
  age: 20,
  grade: 3,
};
```

즉,

- 이름
- 나이
- 학년

처럼 필요한 속성만 추출해서 객체로 표현하는 것이 추상화이다.

---

## 5. 함수를 사용한 객체 생성

객체를 하나씩 직접 만들 수도 있지만,  
함수를 이용해서 객체를 생성할 수도 있다.

예:

```js
function createStudent(name, age) {
  return {
    name,
    age,
  };
}

const s1 = createStudent('홍길동', 20);
const s2 = createStudent('김철수', 21);
```

이 방식은 필요한 객체를 개별적으로 만들어서 사용할 수 있다.

### 장점

- 객체를 여러 개 만들기 쉽다
- 상황에 맞는 데이터를 넣기 좋다
- 서로 다른 형태의 객체를 배열에 넣을 수도 있다

즉, 개별적인 객체를 만드는 것이 객체의 특성을 더 정확히 반영할 수 있다.

---

## 6. 생성자 함수

생성자 함수는 `new` 키워드를 사용해서 객체를 생성할 수 있는 함수이다.

예:

```js
function Student() {}
```

이 함수는 `new`와 함께 호출하면 객체를 생성하는 역할을 할 수 있다.

---

## 7. `new Student()`와 `Student()`의 차이

같은 함수라도 `new`를 붙였는지에 따라 동작이 완전히 달라진다.

### 7-1. `let student = new Student()`

```js
function Student() {}

let student = new Student();
```

이 경우에는 다음과 같은 일이 일어난다.

1. 비어 있는 객체 인스턴스가 새로 만들어진다.
2. `this`가 그 새 객체를 가리킨다.
3. 생성자 함수가 실행된다.
4. 특별한 반환값이 없으면 최종적으로 그 객체가 반환된다.

즉, 생성자 함수는 `new`와 함께 호출될 때 객체 생성 용도로 동작한다.

### 7-2. `let student = Student()`

```js
function Student() {}

let student = Student();
```

이 경우에는 일반 함수 호출이다.

즉,

- `this`는 생성된 객체를 가리키지 않는다
- 상황에 따라 전역 객체를 가리키거나, 엄격 모드에서는 `undefined`가 될 수 있다
- 함수에 `return`이 없으면 최종 결과는 `undefined`이다

즉, `new` 없이 호출하면 생성자 함수가 아니라 일반 함수처럼 실행된다.

---

## 8. 생성자 함수의 예

```js
function Student(name, age) {
  this.name = name;
  this.age = age;
}

const s1 = new Student('홍길동', 20);
const s2 = new Student('김철수', 21);
```

이 예제에서

- `s1`, `s2`는 서로 다른 객체
- 각 객체는 자기 자신의 `name`, `age`를 가진다

즉, 생성자 함수는 같은 구조의 객체를 여러 개 만들 때 유용하다.

---

## 9. 프로토타입이 필요한 이유

생성자 함수 안에 메서드를 직접 넣으면  
객체를 만들 때마다 같은 함수가 반복해서 생성될 수 있다.

예:

```js
function Student(name) {
  this.name = name;
  this.sayHello = function () {
    console.log('hello');
  };
}
```

이 경우 `Student` 객체를 여러 개 만들면  
`sayHello` 함수도 객체마다 새로 만들어질 수 있다.

이것은 메모리 사용 측면에서 비효율적일 수 있다.

이 문제를 해결하기 위해 사용하는 것이 **프로토타입**이다.

---

## 10. 프로토타입

프로토타입은 생성자 함수로 만든 객체들이  
공통으로 참조할 수 있는 공간이다.

즉,

- 공통 메서드나 공통 멤버를
- 각 인스턴스마다 따로 만들지 않고
- 한 곳에 두고 공유하는 구조

라고 이해하면 된다.

---

## 11. `함수.prototype`

자바스크립트의 모든 함수는 `prototype` 속성을 가진다.

즉, 생성자 함수에는 다음과 같은 형태로 공통 멤버를 추가할 수 있다.

```js
function Student(name) {
  this.name = name;
}

Student.prototype.sayHello = function () {
  console.log('hello');
};
```

이렇게 하면 `sayHello`는 각 인스턴스마다 따로 생성되지 않고  
프로토타입 객체에 한 번만 정의된다.

---

## 12. 프로토타입 객체와 인스턴스

생성자 함수로 만들어진 각 인스턴스는  
자신이 직접 공통 메서드를 들고 있는 것이 아니라  
프로토타입 객체를 참조해서 사용한다.

즉,

- 개별 인스턴스는 자기 자신의 멤버를 가지고 있고
- 공통 멤버는 프로토타입 객체 쪽에서 찾는다

는 구조이다.

학습 단계에서는 이 연결을  
인스턴스가 `__proto__`를 통해 프로토타입 객체를 참조한다고 이해할 수 있다.

즉, 각 인스턴스는 자기만의 데이터도 가지고 있고,  
공통 멤버를 공유하기 위한 참조도 가지고 있다.

---

## 13. 생성자 함수 구성 원칙

생성자 함수 안에는 보통 **인스턴스마다 달라지는 데이터**를 넣는다.

즉, 데이터 파트는 생성자 함수 안에 넣는 것이 일반적이다.

```js
function Student(name, age) {
  this.name = name;
  this.age = age;
}
```

반면 여러 인스턴스가 공통으로 사용하는 멤버는  
프로토타입 객체에 넣는 것이 좋다.

```js
Student.prototype.sayHello = function () {
  console.log('hello');
};
```

즉,

- 인스턴스 멤버 → 생성자 함수 안
- 공통 멤버 → 프로토타입 객체

로 구분하면 된다.

---

## 14. 프로토타입 객체가 만들어지는 시점

`함수.prototype`은 함수가 정의될 때 준비된다.

즉, 프로토타입 객체는 생성자 함수가 호출될 때마다 새로 만들어지는 것이 아니라  
함수가 정의될 때 한 번 준비되고, 이후 생성된 인스턴스들이 이를 공유한다.

그래서 공통 기능을 한 번만 정의하고 여러 객체가 함께 사용할 수 있다.

---

## 15. `this`는 언제 결정되는가

`this`는 함수가 **호출될 때** 결정된다.

예:

```js
function test() {
  console.log(this);
}
```

이 함수의 `this`가 무엇인지는  
함수를 어디에 썼는지가 아니라 **어떻게 호출했는지**에 따라 달라진다.

즉,

- 일반 함수 호출
- 메서드 호출
- 생성자 호출
- `call`, `apply`, `bind`

등에 따라 `this`가 달라질 수 있다.

---

## 16. 프로토타입 탐색

예를 들어 다음과 같은 코드가 있다고 하자.

```js
function Student(name) {
  this.name = name;
}

Student.prototype.sayHello = function () {
  console.log('hello');
};

const s1 = new Student('홍길동');
```

이때

```js
s1.sayHello();
```

를 실행하면 자바스크립트는 다음 순서로 찾는다.

1. `s1` 인스턴스 자신에게 `sayHello`가 있는지 확인
2. 없으면 프로토타입 객체로 올라가서 찾기
3. 프로토타입에 있으면 그것을 사용

즉, 인스턴스 멤버를 먼저 찾고, 없으면 프로토타입 객체를 본다.

---

## 17. 읽을 때와 쓸 때의 차이

이 부분이 매우 중요하다.

### 17-1. 읽을 때

속성을 **읽을 때**는 현재 객체에서 먼저 찾고,  
없으면 프로토타입 객체를 따라 올라가며 찾는다.

예:

```js
console.log(s1.sayHello);
```

이 경우 `s1`에 `sayHello`가 없으면  
`Student.prototype.sayHello`를 찾아서 사용한다.

즉, **읽기에서는 프로토타입 체인을 따라 탐색**한다.

### 17-2. 쓸 때

속성에 값을 **쓸 때**는 다르게 동작한다.

예:

```js
s1.sayHello = function () {
  console.log('new hello');
};
```

이 경우 자바스크립트는 보통  
프로토타입에 있는 값을 바꾸러 올라가지 않고,  
현재 객체 `s1`에 새로운 속성을 직접 만든다.

즉,

- 읽기: 없으면 프로토타입까지 올라가서 찾음
- 쓰기: 현재 객체에 직접 속성을 생성하거나 수정함

이라는 차이가 있다.

### 결과

이렇게 되면 `s1`에 새 `sayHello`가 생기므로  
이후에는 프로토타입의 `sayHello`보다  
`s1` 자신의 `sayHello`가 먼저 사용된다.

예:

```js
function Student(name) {
  this.name = name;
}

Student.prototype.sayHello = function () {
  console.log('prototype hello');
};

const s1 = new Student('홍길동');
const s2 = new Student('김철수');

s1.sayHello = function () {
  console.log('instance hello');
};

s1.sayHello(); // instance hello
s2.sayHello(); // prototype hello
```

즉, 쓰기는 현재 객체에 새로운 속성을 만들 수 있기 때문에  
프로토타입 멤버를 가리는(shadowing) 효과가 생길 수 있다.

---

## 18. 배열과 객체

객체는 배열 안에도 자유롭게 넣을 수 있다.

예:

```js
const students = [
  { name: '홍길동', age: 20 },
  { name: '김철수', age: 21 },
];
```

심지어 서로 다른 형태의 객체도 배열에 넣을 수 있다.

```js
const arr = [{ name: '홍길동' }, { title: '제목' }, { price: 1000 }];
```

이것은 자바스크립트 객체의 유연한 특징 중 하나이다.

---

## 19. 객체 설계 관점에서 중요한 점

객체를 만들 때 중요한 것은  
현실 세계를 그대로 복사하는 것이 아니라  
필요한 데이터와 기능을 잘 나누어 표현하는 것이다.

즉,

- 어떤 속성이 필요한지
- 어떤 메서드가 필요한지
- 공통 멤버는 무엇인지
- 개별 멤버는 무엇인지

를 구분하는 것이 중요하다.

특히 생성자 함수와 프로토타입을 함께 이해하면  
객체를 더 구조적으로 설계할 수 있다.

---

## 20. closure를 이용한 private 멤버

자바스크립트에서는 함수의 지역변수를 외부에서 직접 접근할 수 없게 만들고,  
내부 함수만 그 변수에 접근하도록 구성할 수 있다.

이 방식을 이용하면 **private 멤버처럼 동작하는 구조**를 만들 수 있다.

예:

```js
function Rectangle(width, height) {
  this.getWidth = function () {
    return width;
  };
  this.getHeight = function () {
    return height;
  };
  this.setWidth = function (w) {
    width = w;
  };
  this.setHeight = function (h) {
    height = h;
  };
}
```

여기서 `width`, `height`는 객체 인스턴스의 속성이 아니라  
생성자 함수 내부의 **지역변수**이다.

즉,

- `this.width`
- `this.height`

처럼 인스턴스에 직접 저장된 것이 아니다.

---

## 21. Rectangle 예제에서 closure가 사용된 방식

위 코드에서 `getWidth`, `getHeight`, `setWidth`, `setHeight`는  
생성자 함수 내부에서 정의된 **인스턴스 멤버 함수**이다.

이 함수들은 자신이 만들어질 당시의 바깥 함수 지역변수인  
`width`, `height`를 계속 기억한다.

즉, 이것이 바로 **closure**가 사용된 방식이다.

### 핵심 흐름

1. `Rectangle(width, height)`가 호출된다.
2. `width`, `height`라는 지역변수가 생성된다.
3. 내부 함수인 `getWidth`, `getHeight`, `setWidth`, `setHeight`가 만들어진다.
4. 이 내부 함수들은 바깥 함수의 지역변수 `width`, `height`를 기억한다.
5. 생성자 함수 실행이 끝난 뒤에도 내부 함수들은 계속 그 값에 접근할 수 있다.

즉, 외부에서는 `width`, `height`를 직접 볼 수 없고,  
오직 내부 함수들을 통해서만 접근할 수 있다.

이 점 때문에 private 멤버처럼 사용할 수 있다.

---

## 22. 왜 인스턴스 내부 함수여야 하는가

private 멤버를 closure로 운용하려면  
그 지역변수에 접근하는 함수가 **생성자 함수 내부에서 함께 정의되어야 한다.**

즉, 다음처럼 외부에 따로 정의하면 안 된다.

```js
function getWidth() {
  return width; // 접근 불가
}
```

이렇게 하면 `width`가 어느 생성자 함수의 지역변수인지 알 수 없기 때문에  
closure가 성립하지 않는다.

반드시 다음처럼 생성자 함수 안에서 정의해야 한다.

```js
function Rectangle(width, height) {
  this.getWidth = function () {
    return width;
  };
}
```

즉,

- 바깥 함수의 지역변수
- 그 안에서 정의된 내부 함수

이 조합이 있어야 closure를 통해 private 멤버처럼 다룰 수 있다.

---

## 23. `getArea()`와 프로토타입 메서드

면적을 구하는 함수는 공통 기능이므로 프로토타입에 두는 것이 좋다.

```js
Rectangle.prototype.getArea = function () {
  return this.getWidth() * this.getHeight();
};
```

여기서 중요한 점은  
`getArea()`가 직접 `width`, `height`를 읽지 않는다는 것이다.

왜냐하면 `width`, `height`는 지역변수이므로  
프로토타입 메서드에서는 직접 접근할 수 없기 때문이다.

그래서 다음과 같이 접근자 메서드를 이용해야 한다.

- `this.getWidth()`
- `this.getHeight()`

즉,

- 데이터는 closure로 보호
- 공통 기능은 prototype에 정의
- prototype 메서드는 getter를 통해 private 데이터 접근

구조로 이해하면 된다.

---

## 24. closure에 담기는 값

closure에 담기는 것은 단순히 `let` 변수만이 아니다.

다음과 같은 것도 closure의 대상이 된다.

- 함수 매개변수
- `let`으로 선언한 지역변수
- `const`로 선언한 지역변수
- 함수 내부의 지역 상태

즉, `Rectangle(width, height)`에서

- `width`
- `height`

둘 다 함수의 지역 범위에 있으므로 closure로 유지될 수 있다.

---

## 25. 상속 흉내 내기: `Square`

다음과 같이 `Rectangle`을 이용해 `Square`를 만들 수 있다.

```js
function Square(length) {
  this.base = Rectangle; // 부모 생성자 함수 참조
  this.base(length, length); // 부모 생성자 함수 호출
}
```

이 방식은 생성자 함수 시절 자바스크립트에서  
상속 비슷한 구조를 만들 때 쓰던 방법 중 하나이다.

---

## 26. `this.base(length, length)`의 의미

`this.base = Rectangle`로 먼저 부모 생성자 함수를 현재 객체에 연결한 뒤,

```js
this.base(length, length);
```

를 호출하면 `Rectangle`이 **현재 Square 인스턴스를 기준으로 실행**된다.

즉,

- `new Square(length)`로 인해 먼저 Square 인스턴스가 만들어지고
- 그 인스턴스가 `this`가 되고
- 그 상태에서 `Rectangle(length, length)`를 현재 객체 기준으로 실행하는 효과가 난다

그래서 `Rectangle` 안에서 정의한 인스턴스 멤버들이  
Square 인스턴스에 들어오게 된다.

즉, 부모 생성자 함수의 인스턴스 멤버를  
자식 인스턴스가 물려받는 효과를 만든다.

---

## 27. 왜 `Rectangle(length, length)`로 바로 호출하면 안 되는가

다음처럼 그냥 호출하면 문제가 생긴다.

```js
Rectangle(length, length);
```

이것은 일반 함수 호출이므로  
`this`가 Square 인스턴스를 가리키지 않는다.

즉,

- 일반 함수 호출로 처리되고
- `this`가 top level 객체를 가리키거나
- 엄격 모드에서는 `undefined`가 될 수 있다

그래서 원하는 인스턴스 상속 효과가 나지 않는다.

반드시 현재 인스턴스를 기준으로 호출되도록  
`this.base = Rectangle`처럼 연결한 다음 호출해야 한다.

---

## 28. 호출 순서 주의

부모 생성자 함수를 이용해 인스턴스 멤버를 물려받으려면  
현재 객체의 `this`가 먼저 정해진 상태여야 한다.

즉, `new Square()`로 만들어진 인스턴스를 기준으로  
부모 생성자 함수를 호출해야 한다.

그 뒤에 공통 메서드 같은 나머지 부분은  
`prototype`을 통해 상속 구조를 연결하면 된다.

즉, 전체 구조는 보통 다음처럼 이해할 수 있다.

1. 부모 생성자 호출 → 인스턴스 멤버 상속
2. prototype 연결 → 공통 메서드 상속

---

## 29. `instanceof`

`instanceof`는 어떤 객체가 특정 타입인지 검사할 때 사용한다.

```js
객체 instanceof 생성자함수;
```

예:

```js
s1 instanceof Student;
```

이 식은 `s1`이 `Student` 생성자 함수로부터 만들어졌는지 검사한다.

즉, 내부적으로는  
해당 객체가 **그 생성자의 prototype을 상속했는지**를 확인하는 방식으로 이해할 수 있다.

### 예

```js
function Student() {}

const s1 = new Student();

console.log(s1 instanceof Student); // true
```

즉,

- 객체 `O`가 생성자 `T`로부터 만들어졌거나
- 객체 `O`가 `T.prototype`을 상속하면

`O instanceof T`는 `true`가 된다.

---

## 30. 정리

- `{}`를 사용해 객체를 바로 만드는 방식을 객체 리터럴 표기라고 한다.
- 객체 리터럴은 자바스크립트에서 매우 자주 사용된다.
- 추상화는 현실 객체의 필요한 속성만 뽑아 표현하는 것이다.
- 함수를 이용해 객체를 생성할 수도 있다.
- 생성자 함수는 `new` 키워드와 함께 객체를 생성하는 함수이다.
- `new Student()`는 새 객체를 만들고 `this`를 그 객체에 연결한다.
- `Student()`처럼 `new` 없이 호출하면 일반 함수처럼 동작한다.
- 프로토타입은 공통 멤버를 공유하기 위한 공간이다.
- 공통 메서드를 프로토타입에 두면 메모리를 더 효율적으로 사용할 수 있다.
- 인스턴스마다 다른 데이터는 생성자 함수 안에 둔다.
- 공통으로 쓰는 멤버는 `함수.prototype.멤버` 형태로 정의한다.
- 속성을 읽을 때는 인스턴스에서 먼저 찾고, 없으면 프로토타입으로 올라간다.
- 속성에 값을 쓸 때는 현재 객체에 직접 속성을 만들거나 수정한다.
- 이 때문에 인스턴스가 프로토타입 멤버를 가릴 수도 있다.
- 객체와 프로토타입은 자바스크립트에서 매우 중요한 핵심 개념이다.
- 생성자 함수 내부 지역변수를 내부 함수가 기억하면 closure가 된다.
- `Rectangle` 예제에서 `width`, `height`는 인스턴스 속성이 아니라 지역변수이다.
- `getWidth`, `getHeight`, `setWidth`, `setHeight`는 closure를 이용해 private 멤버처럼 동작한다.
- private 멤버를 closure로 다루려면 접근 함수도 생성자 내부에서 정의되어야 한다.
- 공통 기능은 `prototype`에 두는 것이 메모리 효율상 유리하다.
- prototype 메서드는 private 지역변수에 직접 접근할 수 없으므로 getter를 통해 접근해야 한다.
- 함수 매개변수와 지역변수도 closure에 포함될 수 있다.
- 부모 생성자 함수를 현재 인스턴스 기준으로 호출하면 인스턴스 멤버를 상속하는 효과를 낼 수 있다.
- `Rectangle(length, length)`처럼 일반 호출하면 원하는 상속 효과가 나지 않는다.
- `instanceof`는 객체가 특정 생성자의 prototype 체인에 연결되어 있는지 검사한다.
