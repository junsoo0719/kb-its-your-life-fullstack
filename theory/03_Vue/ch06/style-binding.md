# 스타일 적용

## 1. 스타일 적용 순서

스타일은 여러 위치에서 동시에 적용될 수 있고, 우선순위에 따라 최종 결과가 결정된다.

예를 들어 다음과 같은 순서로 생각할 수 있다.

```html
<style>
  .test { ... }
  .over { ... }
</style>
```

```html
<div class="test over" style="..."></div>
```

일반적으로는 다음처럼 뒤에서 적용되는 스타일이 더 우선될 수 있다.

1. 요소 기본 스타일
2. `.test` 스타일
3. `.over` 스타일
4. 인라인 스타일

즉, 인라인 스타일이 가장 강하게 적용되는 경우가 많다.

---

## 2. 인라인 스타일 바인딩

Vue에서는 `v-bind:style` 또는 축약형 `:style`을 사용해 인라인 스타일을 동적으로 적용할 수 있다.

```html
<div :style="style1">내용</div>
```

즉, 스타일을 문자열로 직접 쓰는 대신 데이터와 연결해서 동적으로 바꿀 수 있다.

---

## 3. data에서 객체로 스타일 정의

스타일은 `data()` 안에서 객체 형태로 정의할 수 있다.

```html
<div :style="style1">내용</div>
```

```js
data() {
  return {
    style1: {
      backgroundColor: "aqua",
      color: "black",
    },
  };
}
```

### 주의

자바스크립트 객체에서는 CSS 속성명을 그대로 `background-color`처럼 쓰는 것이 아니라  
보통 **camelCase** 형태로 작성한다.

예:

- `background-color` → `backgroundColor`
- `font-size` → `fontSize`

---

## 4. 인라인에서 객체 직접 정의

스타일 객체를 별도로 만들지 않고 템플릿 안에서 바로 정의할 수도 있다.

```html
<div :style="{ backgroundColor: 'aqua', color: 'black' }">내용</div>
```

또는 데이터 속성을 바로 넣을 수도 있다.

```html
<div :style="{ backgroundColor, color }">내용</div>
```

즉, 객체 리터럴 형태로 스타일을 바로 바인딩할 수 있다.

---

## 5. 배열로 스타일 전달

여러 스타일 객체를 배열로 묶어서 전달할 수도 있다.

```html
<div :style="[myColor, myLayout]">내용</div>
```

예:

```js
data() {
  return {
    myColor: {
      color: "white",
      backgroundColor: "navy",
    },
    myLayout: {
      width: "200px",
      height: "100px",
    },
  };
}
```

이 경우 여러 스타일 객체가 합쳐져서 적용된다.

즉, 스타일을 역할별로 나누어 관리할 수 있다.

---

## 6. 클래스 바인딩

스타일을 직접 인라인으로 주는 대신 CSS 클래스를 바인딩할 수도 있다.

기본 형식은 다음과 같다.

```html
v-bind:class="..."
```

축약형으로는 다음처럼 쓴다.

```html
:class="..."
```

즉, Vue 데이터에 따라 CSS 클래스를 동적으로 붙이거나 뗄 수 있다.

---

## 7. 문자열로 클래스 바인딩

가장 단순한 방식은 클래스명을 문자열로 전달하는 것이다.

```html
<div :class="'test'">내용</div>
```

또는 데이터 속성을 통해 전달할 수도 있다.

```html
<div :class="className">내용</div>
```

```js
data() {
  return {
    className: "test",
  };
}
```

---

## 8. 객체로 클래스 바인딩

가장 많이 사용하는 방식은 **true / false 값을 가진 객체**를 바인딩하는 것이다.

```html
<div :class="{ active: isActive, danger: hasError }">내용</div>
```

```js
data() {
  return {
    isActive: true,
    hasError: false,
  };
}
```

위 코드는 다음처럼 동작한다.

- `active` → `true`이면 적용
- `danger` → `false`이면 적용되지 않음

즉, 객체의

- 속성명 → 클래스명
- 속성값 → 적용 여부(true / false)

로 이해하면 된다.

이 방식이 가장 자주 쓰인다.

---

## 9. 클래스와 checkbox

checkbox는 `v-model`과 함께 자주 사용되며,  
상황에 따라 `boolean` 또는 배열과 연결할 수 있다.

### boolean과 연결

```html
<input type="checkbox" v-model="isActive" />
<div :class="{ active: isActive }">내용</div>
```

체크 여부에 따라 클래스 적용 여부를 바꿀 수 있다.

### 배열과 연결

여러 개의 체크박스를 배열에 연결할 수도 있다.

```html
<input type="checkbox" value="red" v-model="selected" />
<input type="checkbox" value="bold" v-model="selected" />
```

즉, checkbox는 스타일이나 클래스 상태를 바꾸는 UI와 연결해서 자주 사용된다.

---

## 10. computed와 동적 스타일

동적 스타일이나 클래스는 `computed`와 함께 자주 사용된다.

계산된 속성은 반응형 데이터를 바탕으로 결과를 계산하고,  
필요할 때만 다시 계산된다.

```js
computed: {
  styleObject() {
    return {
      backgroundColor: this.isActive ? "aqua" : "gray",
      color: this.hasError ? "red" : "black",
    };
  }
}
```

```html
<div :style="styleObject">내용</div>
```

---

## 11. computed의 캐시 특성

`computed`는 **캐시(cache)** 와 연결된다.

즉, 관련된 반응형 데이터가 바뀌지 않으면  
몇 번 접근해도 매번 다시 계산하지 않고 이전 결과를 재사용한다.

예를 들어:

```js
computed: {
  fullName() {
    return this.firstName + this.lastName;
  }
}
```

`firstName`, `lastName`이 바뀌지 않으면  
`fullName`을 여러 번 사용해도 한 번 계산한 결과를 재사용한다.

이 점 때문에 `computed`는 동적 스타일이나 동적 클래스 계산에 매우 적합하다.

---

## 12. 동적 스타일에 computed를 사용하는 이유

스타일 바인딩을 직접 템플릿 안에 길게 쓰면 코드가 복잡해질 수 있다.

예:

```html
<div
  :style="{ backgroundColor: isActive ? 'aqua' : 'gray', color: hasError ? 'red' : 'black' }"
>
  내용
</div>
```

이런 경우 `computed`로 분리하면 더 읽기 쉬워진다.

```js
computed: {
  styleObject() {
    return {
      backgroundColor: this.isActive ? "aqua" : "gray",
      color: this.hasError ? "red" : "black",
    };
  }
}
```

```html
<div :style="styleObject">내용</div>
```

즉,

- 템플릿이 깔끔해지고
- 재사용이 쉬워지고
- 캐시 효과도 얻을 수 있다

는 장점이 있다.

---

## 13. 정리

- 스타일은 우선순위에 따라 최종 적용 결과가 달라진다.
- 인라인 스타일은 `v-bind:style` 또는 `:style`로 동적으로 적용할 수 있다.
- 스타일 객체는 `data()` 안에서 정의할 수 있다.
- 스타일 속성명은 보통 camelCase로 작성한다.
- 스타일 객체를 템플릿 안에서 직접 정의할 수도 있다.
- 여러 스타일 객체는 배열로 묶어서 전달할 수 있다.
- 클래스 바인딩은 `v-bind:class` 또는 `:class`를 사용한다.
- 클래스는 문자열로 바인딩할 수도 있고, 객체로 바인딩할 수도 있다.
- 객체 바인딩에서는 속성명이 클래스명, 값이 적용 여부(true / false)가 된다.
- checkbox는 `v-model`과 함께 boolean 또는 배열로 연결할 수 있다.
- `computed`는 캐시를 사용하므로 동적 스타일과 클래스 계산에 적합하다.
- 반응형 데이터가 바뀔 때만 다시 계산되므로 효율적이다.
