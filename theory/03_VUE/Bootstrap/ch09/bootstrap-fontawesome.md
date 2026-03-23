# Bootstrap Fontawesome

## 1. Font Awesome이란

Font Awesome은 아이콘을 글자처럼 사용할 수 있게 해 주는 라이브러리이다.

즉, 일반 이미지 파일을 넣는 방식이 아니라  
아이콘 글꼴 또는 SVG 기반 아이콘을 클래스 형태로 불러와 사용하는 방식이다.

학습 관점에서는 **이미지가 아니라 글자처럼 다룰 수 있는 아이콘**이라고 이해하면 편하다.

예:

```html
<i class="fa-solid fa-house"></i>
```

---

## 2. 글자처럼 취급된다는 의미

Font Awesome 아이콘은 글자처럼 취급되기 때문에  
일반 텍스트에 적용하는 스타일을 비슷하게 적용할 수 있다.

예를 들면 다음과 같은 속성을 함께 사용할 수 있다.

- 색상
- 배경색
- 크기

예:

```html
<i class="fa-solid fa-house text-primary"></i>
<i class="fa-solid fa-magnifying-glass" style="font-size: 30px;"></i>
```

즉, 이미지처럼 별도로 크기를 맞추는 방식보다  
글자 스타일을 조절하듯 다룰 수 있다는 장점이 있다.

---

## 3. 부모 요소의 글자 크기 영향

아이콘은 글자처럼 취급되므로  
부모 요소의 글자 크기 영향을 받는다.

예를 들어 `h1` 태그 안에서 사용하면  
`h1`의 큰 글자 크기가 그대로 적용된다.

```html
<h1><i class="fa-solid fa-house"></i> Home</h1>
```

이 경우 아이콘도 `h1` 크기에 맞게 크게 보인다.

즉, 아이콘의 크기는 주변 텍스트 흐름과 자연스럽게 함께 바뀔 수 있다.

---

## 4. Bootstrap과 함께 사용

Bootstrap과 Font Awesome은 함께 자주 사용된다.

예:

```html
<button class="btn btn-primary">
  <i class="fa-solid fa-magnifying-glass"></i> 검색
</button>
```

이렇게 사용하면

- Bootstrap이 버튼 모양 담당
- Font Awesome이 아이콘 담당

을 하게 된다.

즉, 버튼, 네비게이션, 카드, 메뉴, 알림창 등에서 같이 조합해서 많이 사용한다.

---

## 5. 자주 쓰이는 아이콘 예시

실무나 예제에서 자주 쓰이는 아이콘은 다음과 같다.

### home

홈 화면, 메인 페이지 이동 등에 사용한다.

```html
<i class="fa-solid fa-house"></i>
```

### ok

확인, 완료, 성공 의미로 자주 사용한다.

```html
<i class="fa-solid fa-check"></i>
```

### 취소

취소, 닫기, 해제 의미로 자주 사용한다.

```html
<i class="fa-solid fa-xmark"></i>
```

### 삭제

삭제 기능에 자주 사용한다.

```html
<i class="fa-solid fa-trash"></i>
```

### 검색

검색창, 검색 버튼에 자주 사용한다.

```html
<i class="fa-solid fa-magnifying-glass"></i>
```

### 로그인

로그인 버튼이나 사용자 접근 메뉴에 사용한다.

```html
<i class="fa-solid fa-right-to-bracket"></i>
```

### 로그아웃

로그아웃 버튼이나 사용자 메뉴에 사용한다.

```html
<i class="fa-solid fa-right-from-bracket"></i>
```

### 회원가입

회원가입이나 사용자 추가 기능에 사용한다.

```html
<i class="fa-solid fa-user-plus"></i>
```

---

## 6. 사용 예시

### 버튼 안에서 사용

```html
<button class="btn btn-success"><i class="fa-solid fa-check"></i> 확인</button>
```

### 삭제 버튼

```html
<button class="btn btn-danger"><i class="fa-solid fa-trash"></i> 삭제</button>
```

### 네비게이션에서 사용

```html
<a class="navbar-brand" href="#"> <i class="fa-solid fa-house"></i> Home </a>
```

### 검색 입력 옆 버튼

```html
<button class="btn btn-outline-primary">
  <i class="fa-solid fa-magnifying-glass"></i>
</button>
```

---

## 7. 정리

- Font Awesome 아이콘은 이미지가 아니라 글자처럼 사용할 수 있는 아이콘이다.
- 글자처럼 취급되므로 색상, 배경색, 크기 같은 글자 속성을 함께 적용할 수 있다.
- 부모 요소의 글자 크기 영향을 받는다.
- `h1` 안에서 사용하면 `h1` 크기에 맞게 크게 보일 수 있다.
- Bootstrap과 함께 사용하면 버튼, 메뉴, 네비게이션 등에 아이콘을 쉽게 넣을 수 있다.
- 자주 쓰이는 아이콘으로는 home, 확인, 취소, 삭제, 검색, 로그인, 로그아웃, 회원가입 등이 있다.
