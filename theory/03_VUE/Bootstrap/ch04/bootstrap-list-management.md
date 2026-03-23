# Bootstrap 목록 관리

## 1. Pagination

`pagination`은 페이지 번호 목록을 만들 때 사용하는 클래스이다.

기본 구조는 다음과 같다.

```html
<ul class="pagination">
  <li class="page-item"><a class="page-link" href="#">1</a></li>
  <li class="page-item"><a class="page-link" href="#">2</a></li>
  <li class="page-item"><a class="page-link" href="#">3</a></li>
</ul>
```

### 관련 클래스

- `.pagination` : 페이지 목록 전체
- `.page-item` : 각 페이지 항목
- `.page-link` : 실제 링크 영역

---

## 2. Pagination 상태

페이지네이션 항목에도 상태를 줄 수 있다.

- `.active` : 현재 선택된 페이지
- `.disabled` : 비활성화된 페이지

예:

```html
<ul class="pagination">
  <li class="page-item disabled"><a class="page-link" href="#">이전</a></li>
  <li class="page-item active"><a class="page-link" href="#">1</a></li>
  <li class="page-item"><a class="page-link" href="#">2</a></li>
</ul>
```

---

## 3. Pagination 크기

페이지네이션 크기도 조절할 수 있다.

- `.pagination-lg` : 큰 크기
- `.pagination-sm` : 작은 크기

예:

```html
<ul class="pagination pagination-lg">
  ...
</ul>
```

---

## 4. Pagination 정렬

페이지네이션 정렬은 flex 관련 클래스를 사용한다.

- `.justify-content-center` : 가운데 정렬
- `.justify-content-end` : 오른쪽 정렬

예:

```html
<ul class="pagination justify-content-center">
  ...
</ul>
```

---

## 5. 인라인 스타일과 Bootstrap 클래스

예를 들어 다음과 같은 인라인 스타일이 있다고 하자.

```html
style="margin: 20px 0"
```

이런 스타일은 Bootstrap 유틸리티 클래스로 바꿔 쓸 수 있다.

```html
class="my-4"
```

여기서

- `m` : margin
- `y` : 위아래 방향
- `4` : Bootstrap 간격 단계

즉, 인라인 스타일 대신 Bootstrap 클래스로 여백을 줄 수 있다.

---

## 6. Breadcrumb

Breadcrumb는 현재 위치를 경로처럼 보여 주는 UI이다.  
보통 상위 메뉴 → 하위 메뉴 형태로 수평 나열된다.

기본 구조는 다음과 같다.

```html
<nav>
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="#">Home</a></li>
    <li class="breadcrumb-item"><a href="#">Category</a></li>
    <li class="breadcrumb-item active">Current</li>
  </ol>
</nav>
```

### 관련 클래스

- `.breadcrumb` : 전체 breadcrumb 영역
- `.breadcrumb-item` : 각 경로 항목

필기에서 적은 `breadcumb`는 정확히는 `breadcrumb`이다.

---

## 7. List Group

`list-group`은 목록을 그룹 형태로 보여 줄 때 사용한다.  
사이드 메뉴에서 자주 사용된다.

기본 구조:

```html
<ul class="list-group">
  <li class="list-group-item">항목 1</li>
  <li class="list-group-item">항목 2</li>
  <li class="list-group-item">항목 3</li>
</ul>
```

### 관련 클래스

- `.list-group` : 목록 전체
- `.list-group-item` : 각 항목

---

## 8. `.list-group-item-action`

목록 항목을 클릭 가능한 형태로 만들 때 사용한다.

특히 `<a>` 태그와 함께 자주 사용한다.

```html
<div class="list-group">
  <a href="#" class="list-group-item list-group-item-action">메뉴 1</a>
  <a href="#" class="list-group-item list-group-item-action">메뉴 2</a>
</div>
```

즉, 단순 출력용 목록이 아니라  
클릭 가능한 메뉴 형태로 만들 수 있다.

---

## 9. `.list-group-flush`

`list-group-flush`는 바깥쪽 테두리와 둥근 모서리를 제거해서  
더 단순한 형태의 목록을 만든다.

```html
<ul class="list-group list-group-flush">
  <li class="list-group-item">항목 1</li>
  <li class="list-group-item">항목 2</li>
</ul>
```

---

## 10. Card

`card`는 Bootstrap에서 매우 자주 사용하는 요소이다.  
정보를 하나의 박스처럼 묶어서 보여 줄 때 사용한다.

```html
<div class="card">...</div>
```

`card`는 부모 클래스이며,  
안쪽에 여러 자식 클래스를 조합해서 사용할 수 있다.

---

## 11. Card의 주요 자식 클래스

다음 요소들은 자주 사용되지만 항상 필수는 아니다.

- `.card-header`
- `.card-body`
- `.card-footer`

예:

```html
<div class="card">
  <div class="card-header">헤더</div>
  <div class="card-body">본문</div>
  <div class="card-footer">푸터</div>
</div>
```

---

## 12. `.card-body` 안에서 자주 쓰는 클래스

`card-body` 안에는 카드 내용을 구성하는 요소를 넣는다.

### `.card-title`

카드 제목

```html
<h5 class="card-title">제목</h5>
```

### `.card-text`

카드 본문

```html
<p class="card-text">본문 내용</p>
```

### `.card-link`

카드 안의 링크

```html
<a href="#" class="card-link">더보기</a>
```

예:

```html
<div class="card">
  <div class="card-body">
    <h5 class="card-title">카드 제목</h5>
    <p class="card-text">카드 본문 내용</p>
    <a href="#" class="card-link">상세보기</a>
  </div>
</div>
```

---

## 13. 카드 이미지

카드에 이미지를 넣을 때는 위치에 따라 클래스를 다르게 사용한다.

### `.card-img-top`

이미지를 카드 위쪽에 배치한다.

```html
<img src="image.jpg" class="card-img-top" alt="이미지" />
```

### `.card-img-bottom`

이미지를 카드 아래쪽에 배치한다.

```html
<img src="image.jpg" class="card-img-bottom" alt="이미지" />
```

---

## 14. `.card-img-overlay`

`card-img-overlay`는 이미지 위에 내용을 겹쳐서 표시할 때 사용한다.

```html
<div class="card text-bg-dark">
  <img src="image.jpg" class="card-img" alt="이미지" />
  <div class="card-img-overlay">
    <h5 class="card-title">제목</h5>
    <p class="card-text">설명</p>
  </div>
</div>
```

즉, 카드 이미지 위에 텍스트를 올려서 겹쳐 보이게 할 수 있다.

---

## 15. 정리

- `.pagination`, `.page-item`, `.page-link`로 페이지 번호 목록을 만든다.
- `.active`, `.disabled`로 pagination 상태를 표현할 수 있다.
- `.pagination-lg`, `.pagination-sm`으로 크기를 조절할 수 있다.
- `.justify-content-center`, `.justify-content-end`로 pagination 정렬을 할 수 있다.
- 인라인 스타일 대신 Bootstrap 여백 클래스인 `.my-4` 같은 유틸리티 클래스를 사용할 수 있다.
- `.breadcrumb`, `.breadcrumb-item`은 현재 위치를 경로 형태로 보여 준다.
- `.list-group`, `.list-group-item`은 목록 그룹을 만든다.
- `.list-group-item-action`은 클릭 가능한 목록을 만들 때 사용한다.
- `.list-group-flush`는 더 단순한 목록 스타일을 만든다.
- `.card`는 정보를 박스 형태로 보여 줄 때 많이 사용한다.
- `.card-header`, `.card-body`, `.card-footer`로 카드 구조를 나눌 수 있다.
- `.card-title`, `.card-text`, `.card-link`는 카드 본문 구성에 자주 사용된다.
- `.card-img-top`, `.card-img-bottom`은 카드 이미지 위치를 정한다.
- `.card-img-overlay`는 이미지 위에 내용을 겹쳐서 표시할 때 사용한다.
