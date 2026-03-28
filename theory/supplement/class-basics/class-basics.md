# 클래스

## 1. 클래스란

클래스는 객체를 만들기 위한 설계도 같은 개념이다.

자바스크립트의 클래스 문법은 기존 생성자 함수와 프로토타입 기반 객체 모델을  
더 읽기 쉽게 표현할 수 있도록 만든 문법이다.

즉, 내부 동작은 프로토타입 기반이지만  
작성 방식은 다른 언어의 클래스 문법처럼 더 명확하게 보이도록 정리된 형태이다.

---

## 2. 생성자

클래스에서는 `constructor()` 함수로 생성자를 정의한다.

```js
class Student {
  constructor(name, age) {
    this.name = name;
    this.age = age;
  }
}
```

생성자는 객체를 만들 때 자동으로 호출된다.

예:

```js
const s1 = new Student('홍길동', 20);
```

이때 새 객체가 만들어지고,  
`constructor()` 안에서 `this`를 통해 그 객체의 인스턴스 멤버를 초기화할 수 있다.

즉,

- `this`는 새로 생성된 객체를 가리키고
- 생성자 매개변수도 받을 수 있다

는 점이 중요하다.

---

## 3. 프로토타입 메서드

클래스 블록 안에 정의하는 메서드는 기본적으로 **프로토타입 메서드**가 된다.

```js
class Student {
  constructor(name) {
    this.name = name;
  }

  sayHello() {
    console.log('hello');
  }
}
```

여기서 `sayHello()`는 각 인스턴스마다 따로 생성되는 것이 아니라  
프로토타입 객체에 저장되어 여러 인스턴스가 공유한다.

즉, 공통으로 사용하는 메서드는 프로토타입 멤버가 된다.

### 특징

- 클래스 블록 안에 정의한다
- `function` 키워드를 쓰지 않는다
- 여러 인스턴스가 공통으로 공유한다

---

## 4. getter / setter 메서드

클래스에서는 `get`, `set` 키워드를 사용해  
getter / setter 메서드를 만들 수 있다.

예:

```js
class Person {
  constructor(name) {
    this._name = name;
  }

  get name() {
    return this._name;
  }

  set name(value) {
    this._name = value;
  }
}
```

사용 예:

```js
const p = new Person('홍길동');

p.name = '김철수'; // set 호출
console.log(p.name); // get 호출
```

즉,

- `this.name = ...` → `set` 함수 호출
- `... = this.name` → `get` 함수 호출

로 이해하면 된다.

겉보기에는 일반 속성처럼 사용하지만  
실제로는 내부적으로 함수가 동작하는 방식이다.

---

## 5. 정적 메서드(static 메서드)

정적 메서드는 `static` 키워드를 사용해 정의한다.

```js
class MathUtil {
  static add(a, b) {
    return a + b;
  }
}
```

호출은 인스턴스가 아니라 클래스 이름으로 한다.

```js
console.log(MathUtil.add(10, 20));
```

즉, 정적 메서드는 객체를 생성하지 않아도 사용할 수 있다.

### 특징

- 클래스 자체에 속한 메서드이다
- 인스턴스와 무관하게 동작한다
- 일반적으로 인스턴스 데이터를 직접 다루지 않는다

필기 기준으로는 **인스턴스와 무관하게 운영되는 메서드**로 이해하면 된다.

---

## 6. 상속

클래스는 `extends` 키워드를 사용해 상속할 수 있다.

```js
class Parent {
  constructor(name) {
    this.name = name;
  }
}

class Child extends Parent {
  constructor(name, age) {
    super(name);
    this.age = age;
  }
}
```

즉,

- `Child`는 `Parent`를 상속받고
- 부모 클래스의 속성과 메서드를 사용할 수 있다

---

## 7. `super()`

자식 클래스에서 생성자를 정의할 경우  
부모 클래스의 생성자를 호출하기 위해 `super()`를 사용한다.

```js
class Child extends Parent {
  constructor(name, age) {
    super(name);
    this.age = age;
  }
}
```

중요한 점은 `super()`가 먼저 호출되어야 한다는 것이다.

필기 흐름대로 정리하면,

```js
constructor([매개변수]) {
  super([매개변수]);
}
```

형태로 부모 생성자를 먼저 호출한 뒤 나머지 초기화를 진행한다고 이해하면 된다.

즉, 상속 구조에서 부모 쪽 초기화가 먼저 이루어져야 한다.

---

## 8. 클래스에서 데이터와 메서드의 위치

클래스에서 보통 다음처럼 구분해서 이해하면 된다.

- 데이터 → 인스턴스 멤버
- 함수(메서드) → 프로토타입 멤버

예:

```js
class Student {
  constructor(name, age) {
    this.name = name; // 인스턴스 멤버
    this.age = age; // 인스턴스 멤버
  }

  sayHello() {
    // 프로토타입 멤버
    console.log('hello');
  }
}
```

즉,

- 인스턴스마다 달라지는 값은 `constructor` 안에
- 공통으로 쓰는 메서드는 클래스 블록 메서드로

정의하는 것이 일반적이다.

---

## 9. 프로토타입 객체와 공유 정보

프로토타입 객체에 있는 멤버는 여러 인스턴스가 공유할 수 있다.

예:

```js
class Student {
  sayHello() {
    console.log('hello');
  }
}

const s1 = new Student();
const s2 = new Student();
```

이 경우 `sayHello()`는 `s1`, `s2`가 각각 따로 갖는 것이 아니라  
공통의 프로토타입 메서드를 공유한다.

즉, 프로토타입 객체에 있으면 **공유 정보**가 된다.

---

## 10. 프로토타입 멤버에 데이터 배정

프로토타입 멤버에는 메서드뿐 아니라 데이터도 둘 수 있다.

예를 들어 개념적으로는 다음처럼 생각할 수 있다.

```js
function Student() {}
Student.prototype.school = 'KB';
```

또는 클래스 관점에서도  
프로토타입에 있는 데이터는 여러 인스턴스가 공통으로 참조하는 정보처럼 동작할 수 있다.

즉, **프로토타입 멤버로 데이터 배정 자체는 가능**하다.

---

## 11. 프로토타입 데이터는 읽을 때 괜찮은 이유

프로토타입에 있는 데이터를 **읽기만 할 때는** 큰 문제가 없다.

예:

```js
function Student() {}
Student.prototype.school = 'KB';

const s1 = new Student();
const s2 = new Student();

console.log(s1.school); // KB
console.log(s2.school); // KB
```

이 경우 인스턴스 자신에게 `school`이 없으면  
프로토타입 쪽으로 올라가서 `school` 값을 읽는다.

즉, 여러 인스턴스가 공통 기본값처럼 읽어 쓰기에는 편할 수 있다.

---

## 12. 프로토타입 데이터는 쓸 때 문제가 되는 이유

문제는 **쓰기**이다.

예:

```js
function Student() {}
Student.prototype.school = 'KB';

const s1 = new Student();
const s2 = new Student();

s1.school = 'OpenAI';
```

이 경우 많은 사람들이 프로토타입의 `school` 값이 바뀐다고 생각할 수 있지만,  
실제로는 보통 `s1` 객체 자신에게 `school` 속성이 새로 생긴다.

즉,

- 읽을 때는 프로토타입까지 올라가서 찾음
- 쓸 때는 현재 인스턴스에 새 속성을 만듦

이라는 차이가 있다.

그래서 결과는 다음처럼 된다.

```js
console.log(s1.school); // OpenAI
console.log(s2.school); // KB
```

즉, `s1`은 자기 자신의 `school`을 가지게 되고  
프로토타입의 공통값을 가려 버리게 된다.

이 때문에 프로토타입에 데이터를 두는 것은 읽기용 기본값 정도로는 괜찮을 수 있지만,  
수정이 자주 일어나는 데이터에는 적합하지 않은 경우가 많다.

---

## 13. 왜 메서드는 프로토타입에 두고 데이터는 인스턴스에 두는가

이 문제 때문에 보통은 다음 원칙으로 정리한다.

- 공통 기능(메서드) → 프로토타입에 둔다
- 개별 상태(데이터) → 인스턴스에 둔다

메서드는 공유해도 문제가 적고 메모리 효율도 좋다.  
반면 데이터는 각 객체마다 달라질 가능성이 크기 때문에 인스턴스에 두는 것이 안전하다.

즉, 클래스 설계에서 가장 기본적인 원칙 중 하나는  
**데이터는 인스턴스에, 메서드는 프로토타입에** 두는 것이다.

---

## 14. 예시

```js
class Rectangle {
  constructor(width, height) {
    this.width = width;
    this.height = height;
  }

  getArea() {
    return this.width * this.height;
  }

  static info() {
    console.log('사각형 클래스');
  }
}

class Square extends Rectangle {
  constructor(length) {
    super(length, length);
  }
}

const r1 = new Rectangle(10, 20);
console.log(r1.getArea()); // 200

const s1 = new Square(5);
console.log(s1.getArea()); // 25

Rectangle.info(); // 사각형 클래스
```

이 예제에서

- `width`, `height` → 인스턴스 멤버
- `getArea()` → 프로토타입 메서드
- `info()` → 정적 메서드
- `Square` → `Rectangle` 상속

관계를 볼 수 있다.

---

## 15. 정리

- 클래스는 객체를 만들기 위한 설계도 개념이다.
- 생성자는 `constructor()`로 정의한다.
- `constructor()` 안에서 `this`를 사용해 인스턴스 멤버를 초기화한다.
- 클래스 블록 안의 일반 메서드는 프로토타입 메서드가 된다.
- 메서드는 `function` 키워드 없이 정의한다.
- getter / setter는 `get`, `set` 키워드로 정의한다.
- 속성처럼 보이지만 내부적으로 함수가 호출된다.
- 정적 메서드는 `static` 키워드로 정의하며 클래스 이름으로 호출한다.
- 상속은 `extends`를 사용한다.
- 자식 클래스 생성자에서는 `super()`로 부모 생성자를 호출한다.
- 데이터는 인스턴스 멤버로 두고, 메서드는 프로토타입 멤버로 두는 것이 일반적이다.
- 프로토타입 객체에 있는 정보는 여러 인스턴스가 공유할 수 있다.
- 프로토타입 데이터는 읽기는 괜찮지만, 쓰기는 현재 인스턴스에 새 속성이 생겨 문제가 될 수 있다.
- 그래서 변경 가능한 데이터는 보통 인스턴스에 두는 것이 안전하다.
