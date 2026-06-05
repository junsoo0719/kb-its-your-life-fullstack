# Bootstrap 기본 요소

## 1. 이미지

Bootstrap에서는 이미지를 꾸미기 위한 여러 클래스를 제공한다.

### `.rounded`

이미지 모서리를 둥글게 만든다.

```html
<img src="image.jpg" class="rounded" alt="이미지" />
```

### `.rounded-circle`

이미지를 원형으로 만든다.

```html
<img src="image.jpg" class="rounded-circle" alt="이미지" />
```

### `.img-thumbnail`

이미지에 테두리와 여백을 주어 썸네일처럼 보이게 만든다.

```html
<img src="image.jpg" class="img-thumbnail" alt="이미지" />
```

### 이미지 크기와 부모 요소

이미지의 부모 요소가 이미지보다 크면 보통 이미지 원래 크기가 유지된다.  
반대로 부모 요소가 이미지보다 작아지면 부모 영역에 맞게 조정될 수 있다.

### 반응형 이미지

반응형 이미지는 `.img-fluid` 클래스를 사용한다.

```html
<img src="image.jpg" class="img-fluid" alt="반응형 이미지" />
```

이 클래스를 사용하면 화면 크기에 따라 이미지 크기가 유동적으로 변한다.

---

## 2. Jumbotron

Jumbotron은 중요한 내용을 크게 보여 주는 영역이다.  
보통 제목, 설명, 강조 메시지 등을 눈에 띄게 배치할 때 사용한다.

예전 Bootstrap 예제에서 자주 사용되며,  
인트로 영역이나 대표 메시지 영역처럼 이해하면 된다.

---

## 3. Alert

`.alert`는 메시지를 출력하는 영역이다.

성공, 경고, 오류 등 상황에 따라 색상을 다르게 적용할 수 있다.

```html
<div class="alert alert-danger">오류가 발생했습니다.</div>
```

Vue와 함께 사용할 때는 조건부 렌더링과 자주 함께 사용한다.

```html
<div v-if="error" class="alert alert-danger">오류가 발생했습니다.</div>
```

### `.alert-link`

alert 영역 안에 링크를 강조해서 표시할 수 있다.

```html
<div class="alert alert-warning">
  자세한 내용은 <a href="#" class="alert-link">여기</a>를 확인하세요.
</div>
```

### `.alert-dismissible`

닫기 버튼이 있는 alert를 만들 때 사용한다.

```html
<div class="alert alert-success alert-dismissible">저장되었습니다.</div>
```

### `.fade`, `.show`

alert가 사라질 때 애니메이션 효과를 줄 수 있다.

- `.fade` : 사라지는 효과 준비
- `.show` : 실제 표시 상태

즉, 닫힐 때 부드럽게 사라지는 효과를 줄 수 있다.

---

## 4. Button

버튼에는 `.btn` 클래스를 사용한다.

```html
<button class="btn btn-primary">버튼</button>
```

`.btn`는 다음 태그들에 적용할 수 있다.

- `<button>`
- `<input>`
- `<a>`

---

## 5. 버튼 색상

### `.btn-*`

상황에 따라 버튼 색상을 다르게 지정할 수 있다.

예:

- `.btn-primary`
- `.btn-success`
- `.btn-warning`
- `.btn-danger`
- `.btn-info`

```html
<button class="btn btn-success">확인</button>
```

### `.btn-link`

버튼을 링크처럼 보이게 만든다.

```html
<button class="btn btn-link">링크 버튼</button>
```

### `.btn-outline-*`

배경 없이 테두리 중심의 버튼 스타일이다.  
보통 마우스를 올렸을 때 hover 효과가 더 눈에 띈다.

```html
<button class="btn btn-outline-primary">버튼</button>
```

---

## 6. 버튼 크기

버튼 크기를 조절할 수 있다.

- `.btn-lg` : 큰 버튼
- `.btn-sm` : 작은 버튼

```html
<button class="btn btn-primary btn-lg">큰 버튼</button>
<button class="btn btn-primary btn-sm">작은 버튼</button>
```

---

## 7. 블록 레벨 버튼

`.btn-block`은 버튼을 한 줄 전체 너비로 사용하는 스타일이다.

```html
<button class="btn btn-primary btn-block">전체 너비 버튼</button>
```

즉, 버튼이 가로폭을 크게 차지하게 된다.

---

## 8. 버튼 상태

### `.active`

활성화된 버튼처럼 보이게 한다.

### `.disabled`

비활성화된 버튼처럼 보이게 한다.

```html
<button class="btn btn-primary active">활성</button>
<button class="btn btn-primary disabled">비활성</button>
```

---

## 9. Spinner

Spinner는 로딩 중임을 보여 주는 요소이다.

```html
<div class="spinner-border"></div>
```

주로

- 데이터 로딩 중
- 서버 요청 대기 중
- 처리 중 상태 표시

에 사용한다.

---

## 10. `.btn-group`

`.btn-group`은 여러 버튼을 하나의 그룹처럼 묶어 보여 준다.

```html
<div class="btn-group">
  <button class="btn btn-primary">왼쪽</button>
  <button class="btn btn-primary">가운데</button>
  <button class="btn btn-primary">오른쪽</button>
</div>
```

사이드 메뉴나 버튼 묶음 UI에서 자주 사용한다.

---

## 11. Badge

`.badge`는 작은 태그나 상태 표시를 만들 때 사용한다.

```html
<span class="badge bg-primary">new</span>
```

주로

- 새 글 표시
- 숫자 알림
- 상태 태그

등에 사용한다.

---

## 12. `.rounded-pill`

`.rounded-pill`은 양 끝을 크게 둥글게 만들어 알약 모양처럼 보이게 한다.

```html
<span class="badge rounded-pill bg-success">완료</span>
```

badge나 버튼과 함께 자주 사용된다.

---

## 13. 정리

- `.rounded`, `.rounded-circle`, `.img-thumbnail`로 이미지 모양을 바꿀 수 있다.
- 반응형 이미지는 `.img-fluid`를 사용한다.
- Jumbotron은 중요한 내용을 크게 보여 주는 영역이다.
- `.alert`는 메시지 출력 영역이며 상황별 색상을 줄 수 있다.
- `.alert-link`는 alert 내부 링크를 강조한다.
- `.alert-dismissible`는 닫기 기능이 있는 alert에 사용한다.
- `.fade`, `.show`는 사라질 때 애니메이션 효과를 줄 수 있다.
- `.btn`는 버튼 모양을 만들며 여러 태그에 적용할 수 있다.
- `.btn-*`, `.btn-link`, `.btn-outline-*`로 버튼 스타일을 지정할 수 있다.
- `.btn-lg`, `.btn-sm`으로 버튼 크기를 조절할 수 있다.
- `.btn-block`은 전체 너비 버튼에 사용한다.
- `.active`, `.disabled`로 버튼 상태를 표현할 수 있다.
- Spinner는 로딩 상태를 나타낸다.
- `.btn-group`은 버튼들을 그룹으로 묶는다.
- `.badge`는 태그나 상태 표시용으로 사용한다.
- `.rounded-pill`은 둥근 알약 모양 효과를 준다.
