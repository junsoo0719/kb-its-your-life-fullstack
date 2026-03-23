# Bootstrap flex

## 1. Flex 기본 개념

Bootstrap에서는 flex 레이아웃을 쉽게 적용할 수 있도록 여러 유틸리티 클래스를 제공한다.

가장 기본이 되는 클래스는 `.d-flex`이다.

```html
<div class="d-flex">
  <div>item1</div>
  <div>item2</div>
  <div>item3</div>
</div>
```

`.d-flex`를 적용하면 부모 요소가 flex container가 되고,  
자식 요소들은 flex item으로 배치된다.

필기 기준으로는 자식 요소들이 인라인 블록처럼 가로로 나열되는 느낌으로 이해할 수 있다.

기본적으로 자식 요소의 높이와 너비는 **내용물 크기만큼** 잡히는 경우가 많다.

---

## 2. 방향 지정

flex는 배치 방향을 바꿀 수 있다.

### `.flex-row`

기본적인 가로 방향 배치이다.

```html
<div class="d-flex flex-row">...</div>
```

왼쪽에서 오른쪽으로 배치된다.

### `.flex-row-reverse`

가로 방향이지만 반대 순서로 배치된다.

```html
<div class="d-flex flex-row-reverse">...</div>
```

오른쪽에서 왼쪽 방향으로 보이게 된다.

### `.flex-column`

세로 방향으로 배치한다.

```html
<div class="d-flex flex-column">...</div>
```

위에서 아래로 쌓인다.

### `.flex-column-reverse`

세로 방향이지만 반대 순서로 배치한다.

```html
<div class="d-flex flex-column-reverse">...</div>
```

아래에서 위로 쌓이는 것처럼 보인다.

---

## 3. 정렬

flex에서는 요소 사이의 정렬도 쉽게 조절할 수 있다.

### `.justify-content-between`

남는 공간을 각 항목 사이에 배분한다.  
양 끝 항목은 양쪽 끝에 붙고, 중간 항목 사이 간격이 벌어진다.

```html
<div class="d-flex justify-content-between">...</div>
```

실무에서 많이 사용하는 정렬 방식이다.

### `.justify-content-around`

각 항목 주변에 여백을 배분한다.

```html
<div class="d-flex justify-content-around">...</div>
```

항목 사이뿐 아니라 양쪽 끝에도 여백이 들어간다.

즉,

- `between` : 항목 사이 간격 중심
- `around` : 항목 주변 전체에 여백

으로 이해하면 된다.

---

## 4. `.flex-fill`

`.flex-fill`은 flex item이 가능한 공간을 채우도록 만드는 클래스이다.

```html
<div class="d-flex">
  <div class="flex-fill">A</div>
  <div class="flex-fill">BBBB</div>
  <div class="flex-fill">CC</div>
</div>
```

남는 공간을 채우는 데 사용하지만,  
항목 내용 길이에 따라 실제 크기가 완전히 똑같지 않게 보일 수도 있다.

즉, 공간을 채우는 역할은 하지만  
항상 완전히 균등하게 나뉜다고만 보기는 어렵다.

---

## 5. `.flex-grow-1`

`.flex-grow-1`은 남는 공간을 더 차지하도록 하는 클래스이다.

```html
<div class="d-flex">
  <div>A</div>
  <div class="flex-grow-1">B</div>
  <div>C</div>
</div>
```

위 예시에서는 `B`가 남는 공간을 채우면서 더 넓어진다.

즉,

- `.flex-grow-1`이 설정된 요소는 남는 공간을 확장해서 사용
- 설정되지 않은 나머지 요소는 비교적 고정된 크기 유지

로 이해하면 된다.

---

## 6. 줄이기(shrink)

flex에서는 공간이 부족할 때 요소가 줄어들 수도 있다.  
이때 관련되는 개념이 shrink이다.

즉,

- grow : 남는 공간을 늘려서 채움
- shrink : 공간이 부족할 때 줄어듦

이라는 방향으로 이해하면 된다.

Bootstrap에서도 flex 관련 shrink 유틸리티를 조합해서 사용할 수 있다.

---

## 7. `.ms-auto`

`.ms-auto`는 왼쪽 여백을 자동으로 주는 클래스이다.  
Bootstrap 5 기준에서 `ms`는 **margin-start**를 의미한다.

```html
<div class="d-flex">
  <div>왼쪽</div>
  <div class="ms-auto">오른쪽으로 밀림</div>
</div>
```

이 클래스를 적용하면 해당 요소가 가능한 만큼 뒤로 밀려서  
오른쪽 정렬처럼 보이게 만들 수 있다.

즉, flex 환경에서 자동 마진을 이용해 위치를 조정하는 데 자주 사용한다.

---

## 8. 예시

### 가로 정렬

```html
<div class="d-flex flex-row">
  <div>1</div>
  <div>2</div>
  <div>3</div>
</div>
```

### 세로 정렬

```html
<div class="d-flex flex-column">
  <div>1</div>
  <div>2</div>
  <div>3</div>
</div>
```

### 사이 간격 벌리기

```html
<div class="d-flex justify-content-between">
  <div>left</div>
  <div>center</div>
  <div>right</div>
</div>
```

### 한 항목만 확장하기

```html
<div class="d-flex">
  <div>menu</div>
  <div class="flex-grow-1">content</div>
  <div>side</div>
</div>
```

### 자동 마진 사용

```html
<div class="d-flex">
  <div>logo</div>
  <div class="ms-auto">menu</div>
</div>
```

---

## 9. 정리

- `.d-flex`는 부모 요소를 flex container로 만든다.
- flex item의 기본 크기는 보통 내용물 크기를 기준으로 잡힌다.
- `.flex-row`, `.flex-row-reverse`는 가로 방향 배치이다.
- `.flex-column`, `.flex-column-reverse`는 세로 방향 배치이다.
- `.justify-content-between`은 요소 사이 간격을 벌리는 데 많이 사용된다.
- `.justify-content-around`는 양쪽 끝에도 여백을 포함해 배치한다.
- `.flex-fill`은 남는 공간을 채우도록 한다.
- `.flex-grow-1`은 특정 요소가 나머지 공간을 더 차지하게 만든다.
- shrink는 공간이 부족할 때 요소가 줄어드는 개념이다.
- `.ms-auto`는 자동 마진을 이용해 요소를 뒤쪽으로 밀어낸다.
