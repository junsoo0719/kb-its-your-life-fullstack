# Bootstrap Form

## 1. Form 기본 클래스

Bootstrap에서는 폼 요소를 더 깔끔하게 꾸미기 위해 여러 전용 클래스를 제공한다.

대표적으로 입력 요소에 많이 사용하는 클래스는 `.form-control`이다.

---

## 2. `.form-control`

`.form-control`은 입력 요소를 Bootstrap 스타일에 맞게 꾸며 주는 클래스이다.

```html
<input type="text" class="form-control" />
```

이 클래스를 적용하면 기본적으로 입력 요소의 `width`가 `100%`로 설정된다.

즉, 부모 요소의 가로 너비를 기준으로 꽉 차게 배치된다.

주로 다음 요소들에 많이 사용한다.

- `<input>`
- `<textarea>`
- `<select>`

예:

```html
<input type="text" class="form-control" />
<textarea class="form-control"></textarea>
<select class="form-control">
  <option>선택</option>
</select>
```

---

## 3. 입력 요소 크기 설정

입력 요소 크기를 조절할 수도 있다.

### `.form-control-lg`

큰 크기의 입력 요소를 만든다.

```html
<input type="text" class="form-control form-control-lg" />
```

### `.form-control-sm`

작은 크기의 입력 요소를 만든다.

```html
<input type="text" class="form-control form-control-sm" />
```

즉,

- `.form-control-lg` : 큰 입력창
- `.form-control-sm` : 작은 입력창

이다.

---

## 4. 체크박스와 라디오 버튼

체크박스나 라디오 버튼처럼 선택형 입력 요소에는 `form-check` 계열 클래스를 사용한다.

---

## 5. `.form-check`

`.form-check`는 체크박스나 라디오 버튼을 감싸는 부모 영역이다.

```html
<div class="form-check">...</div>
```

이 클래스는 선택형 입력 요소를 Bootstrap 형식에 맞게 정렬하고 배치할 때 사용한다.

---

## 6. `.form-check-input`

`.form-check-input`은 체크박스나 라디오 버튼 자체에 적용하는 클래스이다.

```html
<div class="form-check">
  <input class="form-check-input" type="checkbox" />
</div>
```

또는

```html
<div class="form-check">
  <input class="form-check-input" type="radio" name="group" />
</div>
```

즉,

- `.form-check` : 선택 요소를 감싸는 부모
- `.form-check-input` : 실제 체크박스 / 라디오 버튼

으로 이해하면 된다.

---

## 7. 기본 구조 예시

### 체크박스 예시

```html
<div class="form-check">
  <input class="form-check-input" type="checkbox" id="check1" />
  <label class="form-check-label" for="check1"> 동의합니다 </label>
</div>
```

### 라디오 버튼 예시

```html
<div class="form-check">
  <input class="form-check-input" type="radio" name="gender" id="male" />
  <label class="form-check-label" for="male"> 남성 </label>
</div>
<div class="form-check">
  <input class="form-check-input" type="radio" name="gender" id="female" />
  <label class="form-check-label" for="female"> 여성 </label>
</div>
```

여기서 `label`에는 보통 `.form-check-label`을 함께 사용한다.

---

## 8. 정리

- `.form-control`은 Bootstrap 스타일의 입력 요소 클래스이다.
- `.form-control`은 기본적으로 `width: 100%` 성격을 가진다.
- `.form-control-lg`는 큰 입력창, `.form-control-sm`은 작은 입력창을 만든다.
- `.form-check`는 체크박스나 라디오 버튼을 감싸는 부모 클래스이다.
- `.form-check-input`은 실제 체크박스나 라디오 버튼에 적용하는 클래스이다.
- 체크박스와 라디오 버튼은 보통 `.form-check`와 `.form-check-input`을 함께 사용한다.
