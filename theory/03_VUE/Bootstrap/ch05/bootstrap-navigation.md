# Bootstrap 네비게이션

## 1. 네비게이션과 Navbar

Bootstrap에서 네비게이션은 메뉴 영역을 만들 때 사용한다.  
대표적으로 `navbar` 클래스를 이용해 메뉴 바를 구성한다.

```html
<nav class="navbar">...</nav>
```

`navbar`는 메뉴 파트를 만들고, 반응형 네비게이션을 구성할 때 자주 사용된다.

---

## 2. `.navbar-expand-sm`

`.navbar-expand-sm`은 **언제 햄버거 버튼 형태로 바뀔지**를 결정하는 클래스이다.

```html
<nav class="navbar navbar-expand-sm">...</nav>
```

의미는 다음과 같다.

- `sm` 이상 화면에서는 메뉴가 펼쳐진 형태
- `sm` 미만 화면에서는 접힌 형태(햄버거 버튼)

즉, 반응형 전환 시점을 지정하는 클래스이다.

---

## 3. 메뉴 그룹과 메뉴 항목

### `.navbar-nav`

`.navbar-nav`는 메뉴 목록 전체를 감싸는 메뉴 그룹이다.

```html
<ul class="navbar-nav">
  ...
</ul>
```

### `.nav-item`

`.nav-item`은 메뉴 하나를 의미한다.

```html
<li class="nav-item">
  <a class="nav-link" href="#">메뉴</a>
</li>
```

즉,

- `.navbar-nav` : 메뉴 그룹
- `.nav-item` : 메뉴 하나

로 이해하면 된다.

---

## 4. 네비게이션 정렬

메뉴를 가운데 정렬하고 싶을 때는 flex 정렬 클래스를 함께 사용할 수 있다.

```html
<ul class="navbar-nav justify-content-center">
  ...
</ul>
```

### `.justify-content-center`

- 메뉴를 가운데 정렬한다.

필요에 따라 flex 관련 정렬 클래스를 함께 사용할 수 있다.

---

## 5. 배경색과 Navbar 색상

배경색과 글자색 계열은 함께 조합해서 많이 사용한다.

- `.bg-*`
- `.navbar-*`

예:

```html
<nav class="navbar navbar-dark bg-dark">...</nav>
```

또는

```html
<nav class="navbar navbar-light bg-light">...</nav>
```

여기서 `*` 위치에는 보통 다음 값들이 올 수 있다.

- `light`
- `dark`
- `primary`

즉,

- `.bg-*` : 배경색
- `.navbar-*` : navbar 내부 글자색 계열

을 맞춰서 사용하면 된다.

---

## 6. Dropdown

Dropdown은 서브메뉴를 만들 때 사용한다.

### `.dropdown`

서브메뉴 전체를 감싸는 부모 요소이다.

```html
<li class="nav-item dropdown">...</li>
```

### `.dropdown-menu`

서브메뉴 목록 그룹이다.

### `.dropdown-item`

서브메뉴의 각 항목이다.

예:

```html
<li class="nav-item dropdown">
  <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown"
    >메뉴</a
  >
  <ul class="dropdown-menu">
    <li><a class="dropdown-item" href="#">서브메뉴1</a></li>
    <li><a class="dropdown-item" href="#">서브메뉴2</a></li>
  </ul>
</li>
```

즉,

- `.dropdown` : 서브메뉴 부모
- `.dropdown-menu` : 서브메뉴 그룹
- `.dropdown-item` : 서브메뉴 하나

이다.

---

## 7. 햄버거 버튼

작은 화면에서 메뉴를 접었다가 펼칠 때 햄버거 버튼을 사용한다.

### `.navbar-toggler`

햄버거 버튼을 만드는 클래스이다.

```html
<button class="navbar-toggler" type="button">...</button>
```

---

## 8. `data-bs-toggle`

`data-bs-toggle`은 Bootstrap의 기본 동작을 지정하는 속성이다.

네비게이션에서는 보통 `collapse` 값을 사용한다.

```html
data-bs-toggle="collapse"
```

의미는 접고 펼치는 기능을 사용하겠다는 뜻이다.

---

## 9. `data-bs-target`

`data-bs-target`은 햄버거 버튼을 눌렀을 때  
**펼치거나 숨길 대상**을 지정하는 속성이다.

보통 `id`로 연결한다.

```html
data-bs-target="#menu"
```

즉, 버튼이 눌리면 `id="menu"`인 요소가 접히거나 펼쳐진다.

---

## 10. 접히는 메뉴 영역

햄버거 버튼과 연결되는 메뉴 목록 영역은 보통 다음처럼 작성한다.

```html
<div class="collapse navbar-collapse" id="menu">
  <ul class="navbar-nav">
    ...
  </ul>
</div>
```

여기서 중요한 부분은 다음과 같다.

- `.collapse` : 접고 펼치는 기능
- `.navbar-collapse` : navbar 전용 접힘 영역
- 내부에 `.navbar-nav` : 메뉴 목록

즉, 접히는 부모 영역 안에 메뉴 목록을 넣는 구조이다.

---

## 11. `.d-flex`

`.d-flex`는 `display: flex`를 적용하는 클래스이다.

```html
<div class="d-flex">
  <div>항목1</div>
  <div>항목2</div>
</div>
```

이 클래스를 사용하면 자식 요소들이 flex 배치된다.  
가로 배치가 필요할 때 많이 사용한다.

필기 기준으로는 자식 요소가 인라인 블록처럼 가로로 나열되는 느낌으로 이해하면 된다.

---

## 12. 위치 고정 클래스

Bootstrap은 네비게이션이나 특정 요소를 화면에 고정하는 클래스도 제공한다.

### `.fixed-top`

화면 맨 위에 고정된다.

```html
<nav class="navbar fixed-top">...</nav>
```

스크롤을 내려도 계속 위에 고정되어 있다.

### `.fixed-bottom`

화면 맨 아래에 고정된다.

```html
<nav class="navbar fixed-bottom">...</nav>
```

스크롤해도 계속 아래에 고정된다.

### `.sticky-top`

원래 문서 흐름에 있다가, 스크롤해서 위쪽에 닿으면 그때 고정된다.

```html
<div class="sticky-top">...</div>
```

즉,

- 처음부터 고정되는 것은 아님
- 원래 자리에 있다가 위에 닿으면 고정됨

---

## 13. 기본 구조 예시

Bootstrap 네비게이션의 기본 예시는 다음과 같이 볼 수 있다.

```html
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Brand</a>

    <button
      class="navbar-toggler"
      type="button"
      data-bs-toggle="collapse"
      data-bs-target="#menu"
    >
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="menu">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="#">메뉴1</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">메뉴2</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
```

이 구조를 기준으로

- 메뉴 추가
- dropdown 추가
- 정렬 조정
- 고정 위치 설정

등을 확장해 갈 수 있다.

---

## 14. 정리

- `navbar`는 네비게이션 메뉴 영역을 만든다.
- `.navbar-expand-sm`은 반응형 전환 시점을 정한다.
- `.navbar-nav`는 메뉴 그룹, `.nav-item`은 메뉴 하나이다.
- `.justify-content-center`로 메뉴를 가운데 정렬할 수 있다.
- `.bg-*`, `.navbar-*`를 조합해 배경색과 글자색을 맞출 수 있다.
- `.dropdown`, `.dropdown-menu`, `.dropdown-item`으로 서브메뉴를 구성할 수 있다.
- `.navbar-toggler`는 햄버거 버튼이다.
- `data-bs-toggle="collapse"`는 접고 펼치는 기능을 지정한다.
- `data-bs-target`은 펼치고 숨길 대상의 `id`를 연결한다.
- 접히는 메뉴 영역은 보통 `.collapse navbar-collapse`를 사용한다.
- `.d-flex`는 flex 배치를 적용한다.
- `.fixed-top`, `.fixed-bottom`은 화면에 고정한다.
- `.sticky-top`은 원래 자리에 있다가 위에 닿으면 고정된다.
