# Flexbox

## 1. 개념

Flexbox는 **Flexible Box Layout**의 줄임말로, 행(Row) 또는 열(Column)을 기준으로 요소를 정렬하고 배치하는 **1차원 레이아웃 방식**이다.

즉, 한 번에 한 축을 기준으로 요소를 배치하는 데 적합하며, 복잡한 레이아웃보다 **정렬과 공간 분배**에 강한 구조이다.

Flexbox의 주요 특징은 다음과 같다.

- 흐름을 유지하며 요소를 배치할 수 있다.
- 남는 공간을 자동으로 분배할 수 있다.
- 구조가 단순하고 일관적이다.

## 2. Flex container 기본 속성

Flexbox를 사용하려면 먼저 부모 요소를 Flex container로 만들어야 한다.

```css
.container {
  display: flex;
}
```

`display: flex`를 적용하면 자식 요소들은 Flex item이 되고, 이후 방향, 줄바꿈, 정렬, 크기 비율 등을 설정할 수 있다.

## 3. 축의 개념

Flexbox를 이해할 때 가장 중요한 개념은 **주축(main axis)**과 **교차축(cross axis)**이다.

- 주축: 아이템이 정렬되고 쌓이는 방향
- 교차축: 주축과 수직인 방향

예를 들어,

- 가로 정렬이면 주축은 가로
- 세로 정렬이면 주축은 세로

즉, 어떤 속성이 어떤 축을 기준으로 동작하는지 이해해야 Flexbox를 정확히 사용할 수 있다.

## 4. flex-direction

`flex-direction`은 아이템이 배치되는 방향을 정하는 속성이다.  
즉, **주축을 결정하는 속성**이다.

```css
.container {
  display: flex;
  flex-direction: row;
}
```

값은 다음과 같다.

- `row`: 왼쪽에서 오른쪽으로 가로 배치
- `column`: 위에서 아래로 세로 배치
- `row-reverse`: 가로 반대 방향 배치
- `column-reverse`: 세로 반대 방향 배치

세로 정렬(`column`)을 사용할 때는 아이템이 기본적으로 가로로 늘어날 수 있으므로, 필요한 경우 `width`를 직접 지정해야 한다.

## 5. flex-wrap

`flex-wrap`은 아이템을 한 줄에 유지할지, 여러 줄로 나눌지를 정하는 속성이다.

```css
.container {
  display: flex;
  flex-wrap: wrap;
}
```

값은 다음과 같다.

- `nowrap`: 한 줄 유지(기본값)
- `wrap`: 여러 줄 허용
- `wrap-reverse`: 여러 줄 허용 + 반대 방향 정렬

아이템 개수가 많거나 화면 크기가 줄어들 때 줄바꿈이 필요하면 `wrap`을 사용한다.

## 6. justify-content

`justify-content`는 **주축 방향**으로 요소를 정렬하는 속성이다.

```css
.container {
  display: flex;
  justify-content: center;
}
```

주요 값은 다음과 같다.

- `flex-start`: 시작점 정렬
- `center`: 가운데 정렬
- `flex-end`: 끝점 정렬
- `space-between`: 양쪽 끝 정렬, 사이 공간 균등 분배
- `space-around`: 각 요소 주변에 공간 분배
- `space-evenly`: 모든 간격을 동일하게 분배

즉, 가로 배치일 때는 가로 정렬, 세로 배치일 때는 세로 정렬 역할을 한다.

## 7. align-items

`align-items`는 **교차축 방향**으로 요소를 정렬하는 속성이다.

```css
.container {
  display: flex;
  align-items: center;
}
```

값은 다음과 같다.

- `flex-start`
- `flex-end`
- `center`
- `baseline`
- `stretch`

특히 `center`는 자주 사용하는 값으로, 교차축 기준 중앙 정렬을 만든다.  
가로 배치 기준에서는 흔히 **수직 중앙 정렬**에 사용된다.

## 8. 속성별 기준 축 정리

Flexbox에서 자주 쓰는 속성을 축 기준으로 정리하면 다음과 같다.

- `flex-direction` → 방향 설정, 주축 결정
- `flex-wrap` → 줄바꿈 설정, 주축 기준
- `justify-content` → 주축 정렬
- `align-items` → 교차축 정렬

즉, Flexbox는 속성 이름만 외우는 것이 아니라 **어느 축을 기준으로 동작하는지** 함께 이해해야 한다.

## 9. align-self

`align-self`는 특정 Flex item 하나만 개별적으로 정렬할 때 사용하는 속성이다.

```css
.item {
  align-self: flex-end;
}
```

값은 다음과 같다.

- `flex-start`
- `flex-end`
- `center`
- `baseline`
- `stretch`

`align-items`가 전체 아이템에 적용되는 속성이라면, `align-self`는 **개별 아이템만 따로 위치를 조정**하는 속성이다.

## 10. flex

`flex`는 Flex item의 크기와 비율을 결정하는 속성이다.  
보통 다음 세 가지 값을 함께 다룬다.

- `flex-grow`
- `flex-basis`
- `flex-shrink`

즉, `flex`는 아이템이 공간을 얼마나 차지하고, 얼마나 줄어들고, 초기 크기를 어떻게 가질지를 정하는 핵심 속성이다.

## 11. flex-grow

`flex-grow`는 **남는 공간을 비율로 나누는 속성**이다.

```css
.item1 {
  flex-grow: 1;
}

.item2 {
  flex-grow: 2;
}
```

기본값은 `0`이다.  
숫자가 클수록 남는 공간을 더 많이 차지한다.

즉,

- `1 : 1`이면 동일 비율
- `1 : 2`이면 두 번째 요소가 더 많이 차지한다

## 12. flex-basis

`flex-basis`는 Flex item의 **초기 크기(기준 크기)**를 설정하는 속성이다.

```css
.item {
  flex-basis: 100px;
}
```

이 값은 전체 크기 계산의 출발점이 된다.

실무에서 전체 영역을 깔끔하게 동일 비율로 나누고 싶을 때는 보통 `flex-basis: 0`으로 설정한다.  
그래야 초기 크기의 영향 없이 비율 계산이 깔끔하게 이루어진다.

## 13. flex-shrink

`flex-shrink`는 공간이 부족할 때 아이템이 얼마나 줄어들지를 정하는 속성이다.

```css
.item {
  flex-shrink: 1;
}
```

기본값은 `1`이다.  
즉, 공간이 부족하면 기본적으로 줄어들 수 있다는 뜻이다.

## 14. 실무에서 많이 사용하는 flex: 1

실무에서는 `flex: 1`을 매우 자주 사용한다.

```css
.item {
  flex: 1;
}
```

이 값은 다음 의미를 가진다.

```css
flex-grow: 1;
flex-shrink: 1;
flex-basis: 0;
```

즉,

- 남는 공간은 동일 비율로 나누고
- 부족한 공간은 같이 줄어들며
- 초기 크기는 0부터 계산한다

그래서 여러 요소를 **동일한 크기로 균등 분할**할 때 매우 유용하다.

## 15. 예제

```html
<div class="container">
  <div class="item">1</div>
  <div class="item">2</div>
  <div class="item">3</div>
</div>
```

```css
.container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 10px;
}

.item {
  flex: 1;
  padding: 20px;
  border: 1px solid #000;
  text-align: center;
}
```

위 예제는 다음 의미를 가진다.

- 부모 요소를 Flex container로 만든다.
- 자식 요소들을 가로 방향으로 배치한다.
- 요소 사이를 균등하게 배치한다.
- 교차축 기준 가운데 정렬한다.
- 각 아이템은 `flex: 1`로 동일한 너비를 가진다.

## 16. 중요 포인트

- Flexbox는 행 또는 열 기준의 1차원 레이아웃 방식이다.
- `display: flex`를 부모에 적용해야 시작된다.
- `flex-direction`은 주축을 결정한다.
- `justify-content`는 주축 정렬이다.
- `align-items`는 교차축 정렬이다.
- `align-self`는 개별 아이템 정렬이다.
- `flex-grow`는 남는 공간 분배 비율이다.
- `flex-basis`는 초기 크기이다.
- `flex-shrink`는 부족한 공간에서 줄어드는 비율이다.
- `flex: 1`은 실무에서 매우 자주 쓰이며 동일 크기 분할에 유용하다.

## 정리

Flexbox는 요소를 한 축 기준으로 정렬하고 배치하는 1차원 레이아웃 방식이다.  
부모 요소에 `display: flex`를 적용하면 자식 요소들을 손쉽게 정렬하고, 공간을 자동으로 분배할 수 있다.

핵심은 **주축과 교차축의 개념**을 이해하는 것이다.  
`flex-direction`은 주축을 정하고, `justify-content`는 주축 정렬, `align-items`는 교차축 정렬을 담당한다.

또한 `flex-grow`, `flex-basis`, `flex-shrink`를 통해 아이템 크기와 비율을 세밀하게 조정할 수 있으며, 실무에서는 `flex: 1`을 자주 사용해 요소를 균등하게 나눈다.
