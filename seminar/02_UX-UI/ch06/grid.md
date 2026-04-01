# Grid

## 1. 개념

Grid는 **2차원 레이아웃 시스템**이다.  
즉, 행(row)과 열(column)을 동시에 설계할 수 있는 레이아웃 방식이다.

Flexbox가 한 번에 한 방향만 중심적으로 다루는 **1차원 레이아웃**이라면, Grid는 화면 전체 구조를 한 번에 나눌 수 있는 **전체 뼈대 설계용 레이아웃**이다.

Grid의 특징은 다음과 같다.

- 행과 열을 동시에 설계할 수 있다.
- 전체 화면 구조를 직관적으로 나눌 수 있다.
- 복잡한 레이아웃을 더 적은 중첩으로 구성할 수 있다.

실무에서는 보통

- **전체 뼈대는 Grid**
- **세부 요소 정렬은 Flexbox**
  방식으로 많이 사용한다.

## 2. Flexbox와 Grid의 차이

Flexbox는 1차원 레이아웃이다.  
즉, `row` 또는 `column` 한 방향을 기준으로 요소를 정렬하는 데 강하다.

하지만 전체 화면 구조를 설계할 때는 한계가 있다.

- 한 번에 한 방향만 중심적으로 다룬다.
- 화면 전체를 행과 열 구조로 나누기 어렵다.
- 복잡한 구조에서는 중첩이 많아질 수 있다.

반면 Grid는 2차원 레이아웃이므로 다음이 가능하다.

- 가로와 세로를 동시에 설계할 수 있다.
- 전체 레이아웃 뼈대를 더 직관적으로 구성할 수 있다.
- 화면 구조를 명확하게 나누기 쉽다.

즉, **정렬 중심은 Flexbox**, **구조 설계 중심은 Grid**라고 이해하면 된다.

## 3. Grid의 기본 구조

Grid는 크게 **Grid Container**와 **Grid Item**으로 나뉜다.

### 3.1 Grid Container

`display: grid`가 적용된 부모 요소이다.  
즉, Grid 구조를 만드는 기준이 되는 영역이다.

```css
.container {
  display: grid;
}
```

### 3.2 Grid Item

Grid Container 안에 들어 있는 자식 요소이다.  
격자 안에 실제로 배치되는 요소들을 의미한다.

```html
<div class="container">
  <div class="item">1</div>
  <div class="item">2</div>
  <div class="item">3</div>
</div>
```

위 구조에서 부모는 Grid Container, 자식들은 Grid Item이다.

## 4. 비율 기반 레이아웃

Grid에서는 `fr(fraction)` 단위를 사용해 남는 공간을 비율로 나눌 수 있다.

```css
.container {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
}
```

위 코드는 3개의 열을 동일한 비율로 나누는 의미이다.

예를 들어 다음과 같이도 사용할 수 있다.

```css
.container {
  display: grid;
  grid-template-columns: 1fr 2fr 1fr;
}
```

이 경우 가운데 열이 양쪽보다 2배 넓어진다.

즉, `fr`은 **남은 공간을 비율로 나누는 단위**이다.

## 5. 반복 레이아웃

동일한 구조를 반복해서 만들 때는 `repeat()`를 사용한다.

```css
.container {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
}
```

위 코드는 아래와 같은 의미이다.

```css
grid-template-columns: 1fr 1fr 1fr;
```

즉, `repeat(반복횟수, 값)` 형태로 사용하며, 같은 패턴을 간단하게 작성할 수 있다.

## 6. minmax()

`minmax(최소값, 최대값)`는 Grid Item의 크기를 유연하게 만들기 위한 함수이다.

```css
.container {
  display: grid;
  grid-template-columns: minmax(200px, 1fr);
}
```

이 의미는 다음과 같다.

- 최소 크기는 `200px`
- 최대 크기는 `1fr`

즉, 너무 작아지지는 않지만, 공간이 있으면 유연하게 늘어날 수 있게 만든다.

이 함수는 반응형 레이아웃에서 매우 중요하다.

## 7. repeat()와 minmax()의 결합

실무에서는 `repeat()`와 `minmax()`를 함께 사용하는 경우가 많다.

```css
.container {
  display: grid;
  grid-template-columns: repeat(4, minmax(200px, 1fr));
}
```

위 코드는

- 4개의 열을 만들되
- 각 열은 최소 `200px`
- 공간이 남으면 `1fr` 비율로 늘어나는 구조이다.

또는 다음과 같이 많이 사용한다.

```css
.container {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
}
```

이 방식은 화면 크기에 따라 자동으로 열 개수를 조절하면서도, 각 카드의 최소 크기를 유지해 준다.

즉, 반응형 카드 레이아웃을 만들 때 매우 유용하다.

## 8. minmax 없이 repeat만 쓸 때의 문제점

다음 코드처럼 `minmax()` 없이 고정 크기만 사용하면 한계가 생긴다.

```css
.container {
  display: grid;
  grid-template-columns: repeat(auto-fit, 200px);
}
```

이 경우 각 열은 무조건 `200px`로 고정된다.

### 8.1 화면이 클 때

- 카드 크기가 늘어나지 않는다.
- 오른쪽에 애매한 빈 공간이 생긴다.

### 8.2 화면이 좁을 때

- 줄바꿈이 자연스럽지 않다.
- 어느 순간 갑자기 떨어지는 느낌이 난다.

즉, 고정 크기만 사용하면 화면 크기에 유연하게 대응하지 못한다.

그래서 `repeat()`와 `minmax()`를 함께 써야 한다.

## 9. 예제

```html
<div class="container">
  <div class="item">카드 1</div>
  <div class="item">카드 2</div>
  <div class="item">카드 3</div>
  <div class="item">카드 4</div>
</div>
```

```css
.container {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
}

.item {
  border: 1px solid #000;
  padding: 20px;
}
```

위 예제는 다음 의미를 가진다.

- 부모를 Grid Container로 만든다.
- 자식 요소들을 카드처럼 격자에 배치한다.
- 각 열은 최소 `200px`를 유지한다.
- 공간이 남으면 각 카드가 자연스럽게 늘어난다.
- 화면이 좁아지면 자동으로 줄바꿈된다.

즉, 반응형 카드 레이아웃의 기본 형태이다.

## 10. 중요 포인트

- Flexbox는 1차원, Grid는 2차원 레이아웃이다.
- 전체 화면 뼈대를 설계할 때는 Grid가 더 적합하다.
- 세부 요소 정렬은 Flexbox가 더 적합하다.
- `display: grid`를 부모에 적용하면 Grid Container가 된다.
- Grid Item은 Grid 안에 배치되는 자식 요소이다.
- `fr`은 남는 공간을 비율로 나누는 단위이다.
- `repeat()`는 반복 구조를 간단하게 작성할 때 사용한다.
- `minmax()`는 최소값과 최대값 범위를 정해 유연한 크기를 만든다.
- 반응형 레이아웃에서는 `repeat()`와 `minmax()`를 함께 사용하는 것이 중요하다.
- `repeat(auto-fit, minmax(200px, 1fr))` 형태는 실무에서 자주 사용된다.

## 정리

Grid는 행과 열을 동시에 설계할 수 있는 2차원 레이아웃 시스템이다.  
따라서 전체 화면 구조나 복잡한 레이아웃의 뼈대를 잡을 때 매우 유용하다.

Flexbox가 한 방향 정렬에 강하다면, Grid는 전체 구조 설계에 강하다.  
그래서 실무에서는 전체 뼈대는 Grid, 내부 정렬은 Flexbox로 나누어 사용하는 경우가 많다.

또한 Grid에서는 `fr`, `repeat()`, `minmax()`를 함께 이해해야 한다.  
특히 반응형 레이아웃에서는 `repeat(auto-fit, minmax(200px, 1fr))`처럼 최소 크기를 보장하면서도 유연하게 늘어나는 구조가 중요하다.
