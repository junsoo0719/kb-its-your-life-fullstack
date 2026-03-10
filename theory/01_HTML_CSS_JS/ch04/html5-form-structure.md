# HTML5 입력 양식 태그와 구조화 태그

## 1. 입력 양식과 form 기본 개념

- `input` 태그에서 **`name` 속성은 서버에 데이터를 전달할 때 매우 중요**
- `value` 속성은 입력 요소의 **초기값** 또는 **서버로 전달될 값**으로 사용됨
- form을 제출하면 입력 데이터는 보통  
  `name=value` 형태로 전달됨
- 여러 개의 입력값은 `&` 기호로 연결됨

예:

```html
name=kim&age=20
```

---

## 2. form의 method와 URL 전달 방식

- `form` 태그에서 `method`를 지정하지 않으면 기본값은 **GET**
- GET 방식은 입력 정보가 **URL 뒤에 포함되어 전달됨**
- URL에서 `?` 뒤에 붙는 데이터를 **쿼리 문자열(query string)** 이라고 함

예:

```html
/search?text=hello
```

- 여러 개의 데이터는 `&`로 연결됨

예:

```html
/search?name=kim&age=20
```

---

## 3. hidden 입력

- 숨겨진 값을 서버로 전달할 때는 `hidden=hidden`이 아니라  
  **`type="hidden"`** 을 사용함

예:

```html
<input type="hidden" name="userId" value="abc123" />
```

- 화면에는 보이지 않지만, form 제출 시 서버로 전달됨

---

## 4. 버튼과 전송

### 4-1. 일반 버튼

- 일반 버튼은 보통 서버로 데이터를 전달하는 목적이 아님
- 예를 들어 `type="button"` 버튼은 클릭해도 form이 자동 제출되지 않음

예:

```html
<input type="button" value="클릭" />
```

### 4-2. 제출 버튼

- `type="submit"` 버튼은 form을 서버로 제출함
- 버튼도 `name`과 `value`를 가질 수 있음
- 단, **클릭된 submit 버튼만** 서버로 전달될 수 있음

예:

```html
<input type="submit" value="전송" />
```

- `value`는 버튼에 보이는 글자(타이틀) 역할을 함

---

## 5. URL과 쿼리 문자열

- URL의 `?` 앞부분은 기본 주소
- `?` 뒤에는 추가 정보(쿼리 문자열)를 붙일 수 있음
- GET 방식에서는 이 쿼리 문자열을 통해 데이터가 전달됨

예:

```html
/test?text=Hello
```

- 공백은 원래 URL에 그대로 사용할 수 없음
- 따라서 공백은 **URL 인코딩**되어 전달됨
- 대표적으로 공백은 `%20` 또는 `+` 로 바뀔 수 있음

예:

```text
Hello World → Hello%20World
```

---

## 6. 이미지 크기와 경로

- 이미지에서 `100x100`은 보통 가로 100px, 세로 100px 크기를 뜻함
- `img` 태그에서는 `width`, `height` 속성으로 크기를 지정할 수 있음

예:

```html
<img src="image.jpg" width="100" height="100" />
```

- `src`의 `?` 뒤에 쿼리 문자열을 붙여 추가 정보를 보낼 수도 있음

예:

```html
<img src="image.jpg?version=1" />
```

---

## 7. label 태그와 id

- `label` 태그는 입력 요소와 연결할 때 사용함
- 이때 연결 기준은 **`name`이 아니라 `id`**
- `id`는 브라우저(문서 내부)에서 요소를 식별하기 위한 값

예:

```html
<label for="user-name">이름</label>
<input type="text" id="user-name" name="name" />
```

정리:

- `id` → 브라우저 내부에서 요소 식별
- `name` → 서버에 데이터 전달할 때 사용

---

## 8. 라디오 버튼

- 라디오 버튼은 여러 개 중 **하나만 선택**하는 입력 요소
- 같은 그룹으로 묶으려면 **`name` 속성값을 같게 해야 함**
- 각 항목은 서로 다른 `value`를 가짐
- 서버에는 `name=value` 형태로 전달됨

예:

```html
<input type="radio" name="gender" value="w" /> 여자
<input type="radio" name="gender" value="m" /> 남자
```

전달 예:

```text
gender=w
```

---

## 9. select, option, multiple

### 9-1. option

- `option` 태그 하나하나가 각각 선택 항목(item)이 됨

예:

```html
<select name="fruit">
  <option value="apple">사과</option>
  <option value="banana">바나나</option>
</select>
```

### 9-2. value 생략

- `option`에서 `value`를 생략하면  
  **화면에 보이는 글자(text)가 값으로 사용됨**

예:

```html
<option>사과</option>
```

전달값:

```text
사과
```

### 9-3. multiple

- `multiple` 속성을 사용하면 **다중 선택 가능**
- 보통 리스트 박스 형태로 표시됨
- 여러 항목을 선택할 때는 보통 `Ctrl`(또는 Mac에서는 `Cmd`) 키를 함께 사용
- `multiple`이 없으면 기본적으로 **한 개만 선택 가능한 드롭다운/선택 박스**가 됨

예:

```html
<select name="fruit" multiple>
  <option value="apple">사과</option>
  <option value="banana">바나나</option>
  <option value="orange">오렌지</option>
</select>
```

> 정정: 기본 select를 라디오 버튼 역할이라고 표현하는 것은 정확하지 않음  
> 기본 select는 **단일 선택 가능한 선택 박스(dropdown)** 라고 보는 것이 맞음

---

## 10. textarea와 공백

- 일반 HTML에서는 연속된 공백이 하나로 처리되는 경우가 많음
- 하지만 `textarea` 안의 내용은 **사용자가 입력한 줄바꿈과 공백이 값으로 유지됨**
- 또한 HTML 코드에서 `textarea` 시작 태그와 종료 태그 사이에 적은 들여쓰기/줄바꿈도 실제 값에 포함될 수 있으므로 주의해야 함

예:

```html
<textarea>
안녕하세요
  반갑습니다
</textarea>
```

- 위처럼 작성하면 줄바꿈이나 앞 공백이 그대로 포함될 수 있음

---

## 11. div 태그

- `div` 태그는 특별한 의미가 없는 **영역 묶기용 태그**
- 요소를 그룹으로 묶거나
- 부모-자식 구조를 만들거나
- CSS 적용 범위를 나누기 위해 많이 사용함

예:

```html
<div class="box">
  <p>내용</p>
</div>
```

---

## 12. 구조화 태그(semantic tags)

- HTML5에서는 의미가 있는 구조화 태그 사용을 권장함
- 이런 태그를 **시맨틱 태그(semantic tag)** 라고 함
- 의미가 드러나기 때문에 구조 파악이 쉽고, CSS 적용이나 유지보수에도 유리함

### 주요 구조화 태그

- `nav`
  - 메뉴 영역
  - 내비게이션 링크를 묶을 때 사용

- `aside`
  - 본문 옆의 부가 정보 영역
  - 측면 메뉴, 광고, 관련 링크 등에 사용

- `section`
  - 문서의 큰 주제별 구역
  - 본문의 구획을 나눌 때 사용

- `article`
  - 독립적인 글 한 덩어리
  - 게시글, 뉴스 기사, 글감 하나하나에 사용

예:

```html
<nav>메뉴</nav>
<section>
  <article>글 1</article>
  <article>글 2</article>
</section>
<aside>사이드 메뉴</aside>
```

---

## 핵심 요약

- `name`은 서버 전송용, `id`는 브라우저 식별용
- `value`는 초기값 또는 서버로 전달될 값
- form에서 `method`를 생략하면 기본은 GET
- GET 방식은 URL 뒤 쿼리 문자열로 데이터 전달
- 숨겨진 값 전송은 `type="hidden"` 사용
- 라디오 버튼은 같은 `name`으로 묶어야 한 그룹
- `option`의 `value`를 생략하면 화면의 글자가 값이 됨
- `multiple`이 있으면 select에서 다중 선택 가능
- `textarea`는 줄바꿈과 공백 처리에 주의
- `div`는 의미 없는 영역 묶기용 태그
- `nav`, `aside`, `section`, `article` 같은 시맨틱 태그 사용 권장
