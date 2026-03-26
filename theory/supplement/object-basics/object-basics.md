## 21. closure를 이용한 private 멤버

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

## 22. Rectangle 예제에서 closure가 사용된 방식

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

## 23. 왜 인스턴스 내부 함수여야 하는가

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

## 24. `getArea()`와 프로토타입 메서드

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

## 25. closure에 담기는 값

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

## 26. 상속 흉내 내기: `Square`

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

## 27. `this.base(length, length)`의 의미

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

## 28. 왜 `Rectangle(length, length)`로 바로 호출하면 안 되는가

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

## 29. 호출 순서 주의

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

## 30. `instanceof`

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

## 31. 정리

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
