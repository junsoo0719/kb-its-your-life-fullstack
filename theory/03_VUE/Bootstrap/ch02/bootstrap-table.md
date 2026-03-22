# Bootstrap Table

## 1. Bootstrap 테이블 기본 클래스

Bootstrap에서는 `table` 클래스를 사용해 기본 테이블 스타일을 적용한다.

예:

```html
<table class="table">
  ...
</table>
```

이 기본 클래스 위에 여러 보조 클래스를 함께 사용해서 표의 모양을 바꿀 수 있다.

---

## 2. `.table-striped`

`.table-striped`는 줄마다 배경색이 번갈아 적용되는 **얼룩말 효과**를 준다.

```html
<table class="table table-striped">
  ...
</table>
```

행을 구분해서 보기 쉽게 만들 때 자주 사용한다.

---

## 3. `.table-bordered`

`.table-bordered`는 테이블과 셀에 **테두리**를 추가한다.

```html
<table class="table table-bordered">
  ...
</table>
```

각 셀의 구분을 더 분명하게 보여 주고 싶을 때 사용한다.

---

## 4. `.table-borderless`

`.table-borderless`는 테이블의 **테두리를 제거**한다.

```html
<table class="table table-borderless">
  ...
</table>
```

좀 더 단순하고 깔끔한 형태의 표를 만들 때 사용할 수 있다.

---

## 5. `.table-hover`

`.table-hover`는 마우스를 올린 행에 **hover 효과**를 적용한다.

```html
<table class="table table-hover">
  ...
</table>
```

사용자가 어떤 행을 보고 있는지 더 쉽게 알 수 있게 해 준다.

---

## 6. `.table-dark`

`.table-dark`는 테이블 전체를 **어두운 배경 스타일**로 바꾼다.

```html
<table class="table table-dark">
  ...
</table>
```

배경이 어두워지고 글자 색도 그에 맞게 변경된다.

---

## 7. 상황별 배경색 적용

Bootstrap에서는 특정 영역에 상황별 배경색 클래스를 적용할 수 있다.

예를 들어 다음과 같은 클래스들이 있다.

- `.table-primary`
- `.table-success`
- `.table-warning`
- `.table-danger`
- `.table-info`

적용 위치에 따라 범위가 달라진다.

### 7-1. 테이블 전체에 적용

```html
<table class="table table-primary">
  ...
</table>
```

테이블 전체에 스타일이 적용된다.

### 7-2. `tbody`에 적용

```html
<tbody class="table-success">
  ...
</tbody>
```

`tbody` 영역 전체에 적용된다.

### 7-3. `td`에 적용

```html
<td class="table-warning">내용</td>
```

셀 하나에만 적용된다.

즉, 적용하는 태그 위치에 따라 스타일 범위가 달라진다.

---

## 8. `.table-sm`

`.table-sm`은 **작은 표**를 만들 때 사용한다.

```html
<table class="table table-sm">
  ...
</table>
```

기본 테이블보다 셀 안의 패딩이 줄어들어 더 촘촘한 표가 된다.

---

## 9. 반응형 테이블

작은 화면에서 표의 너비가 너무 커지면 내용이 잘릴 수 있다.  
이럴 때 `.table-responsive`를 사용한다.

```html
<div class="table-responsive">
  <table class="table">
    ...
  </table>
</div>
```

### 특징

- `.table-responsive`는 **부모 태그**에 지정한다
- 테이블이 부모보다 커지면 **가로 스크롤바**가 생성된다

즉, 화면이 좁아도 표를 깨지지 않게 볼 수 있다.

---

## 10. 정리

- `.table-striped`는 얼룩말 효과를 준다.
- `.table-bordered`는 테두리를 추가한다.
- `.table-borderless`는 테두리를 제거한다.
- `.table-hover`는 마우스를 올린 행에 hover 효과를 준다.
- `.table-dark`는 어두운 배경 스타일을 적용한다.
- 상황별 배경색은 테이블 전체, `tbody`, `td` 등에 적용할 수 있다.
- `.table-sm`은 패딩이 줄어든 작은 표를 만든다.
- `.table-responsive`는 부모 태그에 적용하며, 표가 클 경우 스크롤바를 만든다.
