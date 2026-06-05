# Bootstrap 기초

## 1. 기본 영역 잡기

Bootstrap에서는 화면의 전체 구조를 잡기 위해 컨테이너 클래스를 사용한다.

### `.container`

반응형 고정 너비 컨테이너이다.  
화면 크기에 따라 너비가 자동으로 조절된다.

```html
<div class="container">내용</div>
```

### `.container-fluid`

좌우 마진 없이 전체 너비를 사용하는 컨테이너이다.  
즉, 화면 전체 영역을 가득 사용한다.

```html
<div class="container-fluid">내용</div>
```

---

## 2. 여백 관련 클래스

Bootstrap에서는 여백을 빠르게 지정할 수 있는 유틸리티 클래스를 제공한다.

- `p` : padding
- `m` : margin

기본 형식은 다음과 같다.

```html
p-n m-n
```

여기서 `n`은 보통 `1`부터 `5`까지의 숫자를 사용한다.

예:

```html
<div class="p-3">패딩</div>
<div class="m-2">마진</div>
```

### 방향 지정

특정 방향만 지정할 수도 있다.

- `pt` : `padding-top`
- `pb` : `padding-bottom`
- `ps` : `padding-left` 계열
- `pe` : `padding-right` 계열
- `mt` : `margin-top`
- `mb` : `margin-bottom`

예:

```html
<div class="pt-3">위쪽 패딩</div>
```

---

## 3. Grid 시스템

Bootstrap의 grid 시스템은 **한 행을 12개의 컬럼으로 나누어 사용하는 구조**이다.

필요에 따라 컬럼을 묶어서 사용할 수 있다.

### 행 정의

행은 `.row` 클래스로 정의한다.

```html
<div class="row">...</div>
```

### 컬럼 정의

컬럼은 `.col-*` 클래스로 정의한다.

```html
<div class="col-3"></div>
<div class="col-6"></div>
<div class="col-3"></div>
```

위 예시는 `3 + 6 + 3 = 12`로 한 행을 채운다.

---

## 4. 해상도별 컬럼 설정

Bootstrap에서는 화면 크기에 따라 컬럼 배치를 다르게 지정할 수 있다.

대표적으로 다음과 같은 클래스가 있다.

- `.col-sm-*`
- `.col-md-*`
- `.col-lg-*`

예:

```html
<div class="col-sm-3"></div>
<div class="col-sm-3"></div>
<div class="col-sm-3"></div>
<div class="col-sm-3"></div>
```

### 의미

`.col-sm-3`은 `sm` 이상 화면에서는 한 요소가 3칸을 차지한다는 뜻이다.  
즉, 한 행에 4개까지 배치할 수 있다.

하지만 `sm`보다 작은 화면에서는 가로 폭이 부족하므로  
보통 한 줄에 하나씩 세로로 쌓인다.

즉,

- `sm` 이상: 1행 4개
- `sm` 미만: 1행 1개

처럼 반응형으로 동작한다.

---

## 5. 텍스트 정렬 클래스

텍스트 정렬을 쉽게 지정할 수 있다.

- `.text-start` : 왼쪽 정렬
- `.text-center` : 가운데 정렬
- `.text-end` : 오른쪽 정렬

예:

```html
<p class="text-start">왼쪽</p>
<p class="text-center">가운데</p>
<p class="text-end">오른쪽</p>
```

---

## 6. 텍스트 꾸밈 관련 클래스

### `.text-decoration-none`

텍스트의 기본 장식을 제거할 때 사용한다.  
특히 링크의 밑줄 제거에 자주 사용된다.

예:

```html
<a href="#" class="text-decoration-none">링크</a>
```

---

## 7. 텍스트 색상 클래스

Bootstrap은 미리 정의된 텍스트 색상 클래스를 제공한다.

- `.text-primary`
- `.text-success`
- `.text-info`
- `.text-warning`
- `.text-danger`

예:

```html
<p class="text-primary">primary</p>
<p class="text-success">success</p>
<p class="text-info">info</p>
<p class="text-warning">warning</p>
<p class="text-danger">danger</p>
```

각 클래스는 의미에 맞는 색상을 빠르게 적용할 수 있게 해 준다.

---

## 8. 정리

- `.container`는 반응형 고정 너비 영역이다.
- `.container-fluid`는 화면 전체 너비를 사용하는 영역이다.
- `p`는 패딩, `m`은 마진을 의미한다.
- `pt`는 `padding-top`을 의미한다.
- Bootstrap grid는 한 행을 12개 컬럼으로 나누어 사용한다.
- `.row`는 행, `.col-*`는 컬럼을 정의한다.
- `.col-sm-*`, `.col-md-*`, `.col-lg-*`로 화면 크기별 배치를 설정할 수 있다.
- `.text-start`, `.text-center`, `.text-end`로 텍스트 정렬을 지정할 수 있다.
- `.text-decoration-none`으로 텍스트 장식을 제거할 수 있다.
- `.text-primary`, `.text-success`, `.text-info`, `.text-warning`, `.text-danger`로 텍스트 색상을 지정할 수 있다.
