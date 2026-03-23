# Bootstrap Utilities

## 1. border 유틸리티

Bootstrap에서는 테두리를 쉽게 추가하거나 제거할 수 있는 유틸리티 클래스를 제공한다.

### `.border-0`

전체 경계선을 지운다.

```html
<div class="border border-0">내용</div>
```

즉, 기본 테두리가 있더라도 모두 제거된다.

---

## 2. 한쪽 경계선만 지우기

특정 방향의 경계선만 제거할 수도 있다.

- `.border-top-0`
- `.border-end-0`
- `.border-bottom-0`
- `.border-start-0`

예:

```html
<div class="border border-top-0">위쪽 경계선 제거</div>
<div class="border border-end-0">오른쪽 경계선 제거</div>
<div class="border border-bottom-0">아래쪽 경계선 제거</div>
<div class="border border-start-0">왼쪽 경계선 제거</div>
```

여기서

- `top` : 위
- `end` : 오른쪽
- `bottom` : 아래
- `start` : 왼쪽

으로 이해하면 된다.

---

## 3. 한쪽 경계선만 설정하기

특정 방향에만 테두리를 줄 수도 있다.

- `.border-top`
- `.border-end`
- `.border-bottom`
- `.border-start`

예:

```html
<div class="border-top">위쪽만 테두리</div>
<div class="border-bottom">아래쪽만 테두리</div>
```

즉, 필요한 방향에만 선을 줄 수 있다.

---

## 4. 경계선 굵기 설정

테두리 굵기도 조절할 수 있다.

- `.border-1`
- `.border-2`
- `.border-3`
- `.border-4`
- `.border-5`

예:

```html
<div class="border border-3">굵은 테두리</div>
```

숫자가 커질수록 테두리가 더 두꺼워진다.

---

## 5. 경계선 색상 설정

테두리 색상도 지정할 수 있다.

예를 들면 다음과 같은 클래스가 있다.

- `.border-primary`
- `.border-warning`
- `.border-danger`
- `.border-success`
- `.border-info`

예:

```html
<div class="border border-primary">파란 테두리</div>
<div class="border border-warning">노란 테두리</div>
```

즉, Bootstrap의 상황별 색상 체계를 그대로 사용할 수 있다.

---

## 6. 둥근 모서리

Bootstrap에서는 모서리를 둥글게 만드는 클래스도 제공한다.

기본적으로는 `.rounded`를 사용한다.

```html
<div class="border rounded">내용</div>
```

---

## 7. `.rounded-*`

`.rounded-*` 형태로 위치나 정도를 조절할 수 있다.

예를 들어 방향 관련 클래스는 다음처럼 사용할 수 있다.

- `.rounded-top`
- `.rounded-end`
- `.rounded-bottom`
- `.rounded-start`

예:

```html
<div class="border rounded-top">위쪽만 둥글게</div>
<div class="border rounded-bottom">아래쪽만 둥글게</div>
```

또한 둥근 정도를 조절하는 클래스도 사용할 수 있다.

- `.rounded-0`
- `.rounded-1`
- `.rounded-2`
- `.rounded-3`

예:

```html
<div class="border rounded-0">둥글지 않음</div>
<div class="border rounded-3">더 둥글게</div>
```

즉, `*` 위치에 따라

- 방향
- 둥근 정도

를 조절할 수 있다.

---

## 8. clearfix

`clearfix`는 float가 적용된 자식 요소 때문에 부모 높이가 무너지는 문제를 해결할 때 사용한다.

예:

```html
<div class="clearfix">
  <div style="float: left;">왼쪽</div>
  <div style="float: right;">오른쪽</div>
</div>
```

자식 요소들이 `float`로 배치되면 부모가 그 높이를 제대로 감지하지 못할 수 있는데,  
이럴 때 `clearfix`를 사용하면 부모가 자식 요소를 감싸도록 도와준다.

즉, float 해제 용도로 사용하는 클래스이다.

---

## 9. 정리

- `.border-0`은 전체 경계선을 지운다.
- `.border-top-0`, `.border-end-0`, `.border-bottom-0`, `.border-start-0`은 특정 방향의 경계선만 제거한다.
- `.border-top`, `.border-end`, `.border-bottom`, `.border-start`는 특정 방향에만 경계선을 준다.
- `.border-1` ~ `.border-5`는 경계선 굵기를 조절한다.
- `.border-primary`, `.border-warning` 같은 클래스로 경계선 색상을 지정할 수 있다.
- `.rounded`는 기본 둥근 모서리를 만든다.
- `.rounded-*`는 방향이나 둥근 정도를 조절할 때 사용한다.
- `.clearfix`는 float로 인해 부모 높이가 무너지는 문제를 해결할 때 사용한다.
