# CSS 프레임워크

## 1. 개념

CSS 프레임워크는 **미리 정의된 스타일과 구조를 제공하는 CSS 도구**이다.

즉, 개발자가 처음부터 모든 스타일을 직접 작성하지 않아도, 이미 준비된 클래스나 컴포넌트를 활용해서 빠르게 화면을 만들 수 있게 도와주는 도구이다.

사용하는 이유는 다음과 같다.

- 개발 속도를 높일 수 있다.
- 디자인 일관성을 유지하기 쉽다.
- 유지보수가 쉬워진다.

즉, CSS 프레임워크는 **빠르고 일관된 UI 개발**을 위한 도구라고 볼 수 있다.

## 2. CSS 프레임워크의 종류

CSS 프레임워크는 제공 방식에 따라 성격이 다르다.

### 2.1 컴포넌트 기반

완성된 버튼, 카드, 네비게이션 같은 UI를 미리 제공하는 방식이다.

대표 예시:

- Bootstrap

특징:

- 버튼, 카드, 네비게이션 등 완성된 UI 제공
- 빠르게 결과물 제작 가능
- 기본 디자인이 비슷해질 수 있음

장점:

- 이미 정해진 디자인을 가져다 쓰기 때문에 속도가 빠르다.

단점:

- 커스터마이징에 한계가 있다.
- 결과물 디자인이 비슷해질 수 있다.

### 2.2 유틸리티 기반

작은 CSS 클래스를 조합해서 UI를 만드는 방식이다.

대표 예시:

- Tailwind CSS

특징:

- 작은 클래스를 조합해서 UI 구성
- 디자인 자유도가 높음
- 처음에는 클래스가 많아 보여 학습 난이도가 있을 수 있음

즉, 완성된 컴포넌트를 가져오는 방식보다 **직접 조립하는 느낌**에 가깝다.

### 2.3 디자인 시스템 기반

디자인 규칙과 UI 컴포넌트를 함께 제공하는 방식이다.

대표 예시:

- Material UI

특징:

- 디자인 규칙 + UI 제공
- 일관성이 매우 높음
- 자유도는 상대적으로 제한됨

즉, 통일감 있는 서비스를 만들기 좋지만, 원하는 스타일을 자유롭게 바꾸는 데는 제한이 있을 수 있다.

## 3. Bootstrap

Bootstrap은 대표적인 **컴포넌트 기반 CSS 프레임워크**이다.

버튼, 카드, 폼, 네비게이션처럼 자주 사용하는 UI를 미리 만들어 두었기 때문에 빠르게 화면을 구성할 수 있다.

### 3.1 Bootstrap 연결

```html
<link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
  rel="stylesheet"
/>
```

`<link>` 태그를 사용해 미리 만들어진 CSS 파일을 불러온다.

## 4. Bootstrap 로그인 화면 실습

### 4.1 body 배경색

Bootstrap은 배경색을 클래스 형태로 제공한다.

- `bg-primary` : 주요 색상, 파란색 계열
- `bg-secondary` : 보조 색상, 회색
- `bg-success` : 성공, 초록색
- `bg-danger` : 위험/에러, 빨간색
- `bg-info` : 정보, 하늘색

즉, 클래스를 붙이는 것만으로 빠르게 색상을 적용할 수 있다.

### 4.2 바깥쪽 전체 영역

```html
<div
  class="container min-vh-100 d-flex justify-content-center align-items-center"
></div>
```

각 클래스 의미는 다음과 같다.

- `container` : 페이지 내용을 감싸는 기본 레이아웃 클래스
- `min-vh-100` : 최소 높이를 화면 전체 높이 기준으로 설정
- `d-flex` : `display: flex` 적용
- `justify-content-center` : 주축 방향 가운데 정렬
- `align-items-center` : 교차축 방향 가운데 정렬

즉, 화면 전체 높이를 기준으로 로그인 박스를 정가운데 배치하는 구조이다.

### 4.3 로그인 카드 박스

```html
<div class="card shadow p-4" style="width: 100%; max-width: 400px;"></div>
```

각 요소 의미는 다음과 같다.

- `card` : 카드 컴포넌트 클래스
- `shadow` : 그림자 적용
- `p-4` : 내부 여백 적용
- `width: 100%` : 부모 영역 안에서 최대 너비 사용
- `max-width: 400px` : 최대 400px까지만 넓어짐

즉, 작은 화면에서는 꽉 차게 쓰고, 큰 화면에서는 너무 넓어지지 않도록 제한하는 방식이다.

### 4.4 제목

```html
<h2 class="text-center mb-4">Login</h2>
```

- `text-center` : 텍스트 가운데 정렬
- `mb-4` : 아래쪽 여백 적용

### 4.5 이메일 및 비밀번호 입력 영역

```html
<div class="mb-3">
  <label class="form-label">Email</label>
  <input type="email" class="form-control" />
</div>
```

- `mb-3` : 아래 여백 적용
- `form-label` : 폼의 label 스타일 적용
- `form-control` : 입력창 스타일 적용

즉, Bootstrap은 폼 요소도 일관된 디자인으로 바로 사용할 수 있다.

### 4.6 로그인 버튼

```html
<button class="btn btn-primary w-100">로그인</button>
```

- `btn` : 버튼 기본 클래스
- `btn-primary` : primary 스타일 버튼
- `w-100` : 너비 100%

즉, 버튼을 손쉽게 강조하고 전체 폭으로 늘릴 수 있다.

## 5. Tailwind CSS

Tailwind CSS는 대표적인 **유틸리티 기반 프레임워크**이다.

미리 완성된 버튼이나 카드보다, 작은 클래스들을 조합해서 원하는 UI를 직접 만든다.

### 5.1 Tailwind 연결

```html
<script src="https://cdn.tailwindcss.com"></script>
```

Tailwind CDN 방식은 `<script>` 태그를 사용한다.

## 6. `<link>`와 `<script>`의 차이

Bootstrap은 보통 `<link>`로 연결하고, Tailwind CDN은 `<script>`로 연결한다.

### 6.1 기본 차이

- `<link>` : 외부 CSS 파일을 직접 불러온다.
- `<script>` : 외부 JavaScript 파일을 불러와 실행한다.

즉, **태그 자체의 용량 차이**가 핵심이 아니라, **불러오는 리소스의 종류와 처리 방식의 차이**가 핵심이다.

### 6.2 용량 관점에서의 차이

Bootstrap CDN 방식은 미리 완성된 CSS 파일을 바로 다운로드해서 적용한다.  
즉, 브라우저는 준비된 스타일 묶음을 그대로 받는다.

반면 Tailwind CDN 방식은 JavaScript를 먼저 불러온 뒤, 페이지 안의 클래스를 보고 필요한 스타일을 브라우저에서 생성하는 방식이다.  
즉, CSS 파일을 바로 받는 것이 아니라 **Tailwind 동작용 스크립트 + 브라우저 내 생성 과정**이 포함된다.

그래서 학습용 CDN 기준으로 보면:

- Bootstrap `<link>` : 미리 만들어진 CSS를 바로 적용
- Tailwind `<script>` : JS를 실행해서 필요한 스타일 생성

즉, 브라우저 처리 부담은 Tailwind CDN 방식이 더 있을 수 있다.

### 6.3 중요한 정리

여기서 꼭 알아야 할 점은 **Tailwind 자체가 무조건 더 무겁다**는 뜻은 아니라는 것이다.

실무에서는 Tailwind를 CDN `<script>` 방식이 아니라 빌드 과정으로 사용해서, 실제로 필요한 클래스만 모아 최종 CSS를 만든다.  
이 경우에는 오히려 불필요한 스타일이 줄어들어 결과 파일이 더 효율적일 수 있다.

즉,

- **학습용 CDN 기준**: Bootstrap은 CSS 파일 직접 로드, Tailwind는 JS 실행 기반
- **실무 빌드 기준**: Tailwind는 필요한 스타일만 추려서 더 효율적으로 만들 수도 있음

## 7. Tailwind 로그인 화면 실습

### 7.1 body 배경색

Tailwind는 숫자 단위로 색상 강도를 나눈다.

- `50` : 거의 흰색
- `100` : 매우 밝음
- `200~400` : 밝은 영역
- `500` : 기본 색
- `600~700` : 더 진해짐
- `800~900` : 매우 어두움
- `950` : 거의 검정

즉, 같은 색상이라도 밝기 단계별로 세밀하게 조절할 수 있다.

### 7.2 바깥쪽 전체 영역

```html
<div class="min-h-screen flex items-center justify-center"></div>
```

각 클래스 의미는 다음과 같다.

- `min-h-screen` : 최소 높이를 화면 전체 높이로 설정
- `flex` : `display: flex`
- `items-center` : 세로 방향 가운데 정렬
- `justify-center` : 가로 방향 가운데 정렬

즉, Bootstrap과 비슷하게 화면 중앙 배치 구조를 만들 수 있다.

### 7.3 로그인 카드 박스

```html
<div class="w-full max-w-sm bg-white shadow-lg rounded-lg p-6"></div>
```

- `w-full` : 너비 100%
- `max-w-sm` : 최대 너비를 sm 크기로 제한
- `bg-white` : 배경 흰색
- `shadow-lg` : 큰 그림자 적용
- `rounded-lg` : 모서리 둥글게
- `p-6` : 내부 여백 적용

즉, Tailwind는 카드 하나도 여러 유틸리티 클래스를 조합해 직접 만든다.

### 7.4 제목

```html
<h2 class="text-2xl font-bold text-center mb-6">Login</h2>
```

- `text-2xl` : 큰 글자 크기
- `font-bold` : 굵은 글자
- `text-center` : 가운데 정렬
- `mb-6` : 아래 여백

### 7.5 이메일 및 비밀번호 입력 영역

```html
<label class="block">Email</label>
<input class="w-full px-3 py-2 border rounded-md" type="email" />
```

- `block` : label을 block 요소로 변경
- `px-3` : 좌우 padding
- `py-2` : 상하 padding

즉, 필요한 모양을 직접 조합해서 입력창을 만든다.

### 7.6 로그인 버튼

```html
<button class="w-full bg-blue-500 text-white rounded-md py-2">로그인</button>
```

- `text-white` : 글자색 흰색
- `rounded-md` : 모서리 둥글게

즉, Tailwind는 버튼도 조합식으로 구성하는 방식이다.

## 8. Bootstrap / Tailwind / Material UI 비교

세 도구는 UI를 만드는 방식이 다르다.

### 8.1 Bootstrap / Tailwind

- 구조: HTML + CSS
- 방식: 클래스 조합
- 자유도: 높음
- 난이도: 상대적으로 낮음

### 8.2 Material UI

- 구조: React 컴포넌트
- 방식: 컴포넌트 사용
- 자유도: 규칙 기반
- 난이도: 상대적으로 높음

즉, Bootstrap과 Tailwind는 주로 HTML에 클래스를 붙여 스타일링하는 방식이고, Material UI는 React 환경에서 컴포넌트 자체를 가져다 쓰는 방식이다.

## 9. 예제

### 9.1 Bootstrap 예제

```html
<link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
  rel="stylesheet"
/>

<div
  class="container min-vh-100 d-flex justify-content-center align-items-center"
>
  <div class="card shadow p-4" style="width: 100%; max-width: 400px;">
    <h2 class="text-center mb-4">Login</h2>

    <div class="mb-3">
      <label class="form-label">Email</label>
      <input type="email" class="form-control" />
    </div>

    <div class="mb-3">
      <label class="form-label">Password</label>
      <input type="password" class="form-control" />
    </div>

    <button class="btn btn-primary w-100">로그인</button>
  </div>
</div>
```

### 9.2 Tailwind 예제

```html
<script src="https://cdn.tailwindcss.com"></script>

<div class="min-h-screen flex items-center justify-center bg-slate-100">
  <div class="w-full max-w-sm bg-white shadow-lg rounded-lg p-6">
    <h2 class="text-2xl font-bold text-center mb-6">Login</h2>

    <label class="block mb-2">Email</label>
    <input class="w-full px-3 py-2 border rounded-md mb-4" type="email" />

    <label class="block mb-2">Password</label>
    <input class="w-full px-3 py-2 border rounded-md mb-4" type="password" />

    <button class="w-full bg-blue-500 text-white rounded-md py-2">
      로그인
    </button>
  </div>
</div>
```

## 10. 중요 포인트

- CSS 프레임워크는 미리 정의된 스타일과 구조를 제공하는 도구이다.
- 사용하는 이유는 개발 속도 향상, 일관된 디자인 유지, 유지보수 편의성 때문이다.
- Bootstrap은 컴포넌트 기반 프레임워크이다.
- Tailwind는 유틸리티 기반 프레임워크이다.
- Material UI는 디자인 시스템 기반 프레임워크이다.
- Bootstrap은 빠르지만 커스터마이징 한계가 있고 디자인이 비슷해질 수 있다.
- Tailwind는 자유도가 높지만 초기 학습 난이도가 있다.
- Material UI는 일관성은 높지만 자유도가 제한될 수 있다.
- Bootstrap은 보통 `<link>`로 CSS 파일을 직접 불러온다.
- Tailwind CDN은 `<script>`로 JS를 불러와 스타일을 생성한다.
- 용량 차이는 태그 차이보다 리소스 처리 방식의 차이로 이해해야 한다.

## 정리

CSS 프레임워크는 이미 준비된 스타일과 구조를 활용해서 UI를 더 빠르고 일관되게 만들 수 있게 해 주는 도구이다.  
대표적으로 Bootstrap, Tailwind, Material UI가 있으며, 각각 사용하는 방식과 자유도가 다르다.

Bootstrap은 완성된 UI를 빠르게 가져다 쓰는 데 강하고, Tailwind는 작은 클래스를 조합해 자유롭게 디자인하는 데 강하다.  
Material UI는 React 환경에서 일관된 디자인 시스템을 적용하기 좋다.

또한 Bootstrap은 보통 `<link>`로 미리 만들어진 CSS를 불러오고, Tailwind CDN은 `<script>`로 JS를 실행해 스타일을 생성한다.  
따라서 차이는 태그 이름이 아니라 **불러오는 리소스 종류와 처리 방식**의 차이로 이해해야 한다.
