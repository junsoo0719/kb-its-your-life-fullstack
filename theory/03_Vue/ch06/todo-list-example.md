# TodoList 예제

## 1. Bootstrap 클래스와 사용자 정의 스타일

Bootstrap에는 이미 기본 클래스로 `container` 같은 클래스가 존재한다.  
하지만 여기에 새로운 스타일을 추가해서 덮어쓸 수도 있다.

이는 CSS의 **cascading** 특성 때문이며,  
기존 스타일 위에 새 스타일이 적용될 수 있다.

즉, Bootstrap 기본 클래스를 사용하더라도  
필요하면 별도의 사용자 정의 스타일을 추가해서 모양을 바꿀 수 있다.

---

## 2. `.pointer` 클래스

`.pointer` 클래스는 보통 마우스 커서를 손가락 모양으로 바꾸기 위해 사용한다.

예:

```css
.pointer {
  cursor: pointer;
}
```

이 클래스는 클릭 가능한 요소에 자주 사용한다.

예를 들면

- 삭제 버튼
- 완료 체크 영역
- 목록 항목

등에 적용할 수 있다.

---

## 3. `.input-group`

Bootstrap의 `.input-group`은 입력 요소와 버튼 등을 하나의 묶음처럼 배치할 때 사용한다.

예:

```html
<div class="input-group">
  <input type="text" class="form-control" />
  <button class="btn btn-primary">추가</button>
</div>
```

이렇게 하면 `input`과 `button`이 붙어서 하나의 그룹처럼 보인다.

즉, TodoList에서 입력창과 추가 버튼을 자연스럽게 연결할 때 유용하다.

---

## 4. `.list-group`, `.list-group-item`

Bootstrap의 목록 관련 클래스이다.

### `.list-group`

목록 전체를 감싸는 부모 클래스이다.

### `.list-group-item`

각 항목 하나를 나타낸다.

예:

```html
<ul class="list-group">
  <li class="list-group-item">할 일 1</li>
  <li class="list-group-item">할 일 2</li>
</ul>
```

이 클래스들은 보통 폭 전체를 차지하는 형태로 보이며,  
사이드 메뉴나 목록 UI에서 많이 사용된다.

TodoList에서는 할 일 목록을 보여 줄 때 잘 어울린다.

---

## 5. TodoList에서 사용하는 데이터

TodoList 예제에서는 보통 다음과 같은 데이터를 정의한다.

### `todo`

텍스트 박스에 사용자가 현재 입력 중인 내용을 저장하는 데이터이다.

예:

```js
todo: '';
```

즉, 입력창과 연결되는 값이다.

### `todolist`

추가된 할 일 목록 전체를 저장하는 배열이다.

예:

```js
todolist: [];
```

이 배열 안의 todo 한 건은 보통 다음 구조를 가진다.

```js
{
  id: 고유값,
  todo: "할 일 내용",
  completed: false
}
```

---

## 6. todo 한 건의 구조

### `id`

각 할 일을 구분하기 위한 고유값이다.

예제에서는 보통 `timestamp` 값을 사용한다.

```js
id: new Date().getTime();
```

즉, 목록 렌더링 시 key로도 활용할 수 있다.

### `todo`

할 일의 실제 내용이다.

```js
todo: '운동하기';
```

### `completed`

완료 여부를 나타내는 값이다.

- `true` : 완료
- `false` : 미완료

예:

```js
completed: false;
```

---

## 7. 메서드 정의

TodoList에서는 보통 다음 메서드들이 필요하다.

### `addTodo()`

할 일을 새로 추가하는 메서드이다.  
즉, **Create** 역할을 한다.

### `deleteTodo()`

특정 할 일을 삭제하는 메서드이다.  
즉, **Delete** 역할을 한다.

### `toggleCompleted()`

완료 여부를 바꾸는 메서드이다.  
즉, **Update** 역할을 한다.

### `v-for`

목록을 화면에 반복 출력할 때 사용한다.  
즉, **Read** 역할을 담당한다고 볼 수 있다.

정리하면 TodoList 예제에서는 CRUD 흐름이 다음처럼 구성된다.

- Create → `addTodo()`
- Read → `v-for`
- Update → `toggleCompleted()`
- Delete → `deleteTodo()`

---

## 8. 이벤트 핸들러 필요

`addTodo()`, `deleteTodo()`, `toggleCompleted()`는  
모두 사용자 동작에 의해 실행되므로 이벤트 핸들러가 필요하다.

예:

```html
<button @click="addTodo">추가</button>
<button @click="deleteTodo(todo.id)">삭제</button>
<li @click="toggleCompleted(todo.id)">...</li>
```

즉, 메서드는 보통 클릭 이벤트와 연결된다.

---

## 9. 배열 메서드

TodoList에서는 배열을 자주 다루므로 배열 메서드가 중요하다.

### `.find()`

조건을 만족하는 첫 번째 요소를 반환한다.

```js
const item = todolist.find((item) => item.id === id);
```

즉, 값을 반환한다.

### `.findIndex()`

조건을 만족하는 첫 번째 요소의 인덱스를 반환한다.

```js
const index = todolist.findIndex((item) => item.id === id);
```

즉, 위치를 반환한다.

### 공통 특징

둘 다 **처음으로 true를 반환하는 위치에서 탐색을 멈춘다.**

즉, 끝까지 다 찾는 것이 아니라 첫 번째 일치 항목까지만 본다.

---

## 10. `.splice()`

배열 요소를 삭제하거나 교체할 때 사용한다.

기본 형식:

```js
splice(위치, 삭제개수);
```

예:

```js
this.todolist.splice(index, 1);
```

이 코드는 `index` 위치에서 항목 1개를 삭제한다.

TodoList에서 삭제 기능을 구현할 때 자주 사용된다.

---

## 11. Vue와 배열의 반응형 처리

Vue에서는 배열도 Proxy를 통해 반응형으로 처리된다.

즉, 배열에 대해 다음과 같은 수정이 일어나면

- 추가
- 삭제
- 변경

Vue가 그 변화를 감지해서 화면을 다시 렌더링할 수 있다.

예:

```js
this.todolist.push(newTodo);
this.todolist.splice(index, 1);
```

이런 코드가 실행되면 배열이 바뀌고,  
Vue가 이를 감지해 화면 목록도 자동으로 갱신한다.

이것이 Vue 반응형 시스템의 큰 장점이다.

---

## 12. `==` 와 `===`

자바스크립트 비교 연산자는 크게 두 가지를 많이 사용한다.

### `==`

형 변환을 한 뒤 비교한다.

```js
1 == '1'; // true
```

### `===`

형까지 먼저 비교하고, 형이 같을 때 값도 비교한다.

```js
1 === '1'; // false
```

TodoList에서 `id` 비교를 할 때는  
의도치 않은 형 변환을 막기 위해 보통 `===`를 사용하는 편이 안전하다.

---

## 13. `@click`과 매개변수 전달

### 기본 방식

```html
@click="toggleCompleted"
```

이처럼 함수명만 쓰면 Vue가 기본적으로 이벤트 객체를 전달할 수 있다.

즉, 핸들러는 이벤트 객체를 받을 수 있다.

### 내 정보만 전달

```html
@click="toggleCompleted(todoitem.id)"
```

이 경우에는 이벤트 객체가 아니라 `todoitem.id`가 전달된다.

즉, 내가 지정한 값만 전달된다.

### 둘 다 전달

```html
@click="toggleCompleted($event, todoitem.id)"
```

이처럼 `$event`를 명시하면 이벤트 객체와 내가 원하는 값을 함께 전달할 수 있다.

정리하면,

- 함수명만 사용 → 이벤트 객체 전달 가능
- 값만 호출문에 넣기 → 지정한 값 전달
- 둘 다 필요 → `$event`를 직접 써야 함

이다.

---

## 14. 삭제 버튼과 이벤트 버블링

Todo 항목 전체에 `@click`이 걸려 있고,  
그 안쪽에 삭제 버튼에도 `@click`이 걸려 있는 경우를 생각해 보자.

예:

```html
<li @click="toggleCompleted(todo.id)">
  {{ todo.todo }}
  <button @click="deleteTodo(todo.id)">삭제</button>
</li>
```

이 경우 삭제 버튼을 눌렀을 때

1. 삭제 버튼의 클릭 이벤트 실행
2. 부모 `li`의 클릭 이벤트도 실행

될 수 있다.

즉, 삭제만 하려 했는데 완료 처리까지 같이 발생할 수 있다.

---

## 15. `.stop` 수식어

이런 문제를 막기 위해 삭제 버튼에는 `.stop` 수식어를 붙여야 한다.

```html
<button @click.stop="deleteTodo(todo.id)">삭제</button>
```

이는 내부적으로 `event.stopPropagation()`과 비슷한 효과를 준다.

즉, 이벤트 전파를 막아서  
부모의 `@click`이 함께 실행되지 않게 한다.

삭제 버튼에 부모 클릭 핸들러가 있는지 반드시 확인해야 하는 이유가 바로 이것이다.

---

## 16. TodoList 예시 구조

간단한 구조는 다음처럼 만들 수 있다.

```html
<div class="container">
  <div class="input-group mb-3">
    <input type="text" class="form-control" v-model="todo" />
    <button class="btn btn-primary" @click="addTodo">추가</button>
  </div>

  <ul class="list-group">
    <li
      class="list-group-item pointer"
      v-for="todoitem in todolist"
      :key="todoitem.id"
      @click="toggleCompleted(todoitem.id)"
    >
      {{ todoitem.todo }}
      <button
        class="btn btn-danger btn-sm float-end"
        @click.stop="deleteTodo(todoitem.id)"
      >
        삭제
      </button>
    </li>
  </ul>
</div>
```

이 구조 안에는 다음 개념이 함께 들어 있다.

- `v-model`
- `@click`
- `v-for`
- `:key`
- Bootstrap의 `input-group`
- Bootstrap의 `list-group`
- `.stop` 수식어

---

## 17. 정리

- Bootstrap의 `container` 클래스에 추가 스타일을 덮어써서 사용할 수 있다.
- `.pointer`는 `cursor: pointer`를 주기 위한 사용자 정의 클래스이다.
- `.input-group`은 입력창과 버튼 등을 하나의 그룹처럼 묶어 준다.
- `.list-group`, `.list-group-item`은 목록 UI를 만들 때 유용하다.
- `todo`는 입력창 값, `todolist`는 할 일 목록 배열이다.
- 각 todo 항목은 `id`, `todo`, `completed` 속성을 가질 수 있다.
- `addTodo()`, `deleteTodo()`, `toggleCompleted()`는 각각 Create, Delete, Update 역할을 한다.
- 목록 출력은 `v-for`로 처리하며 Read 역할을 한다.
- `.find()`는 값을, `.findIndex()`는 인덱스를 반환한다.
- `.splice()`는 배열 요소 삭제에 자주 사용된다.
- Vue는 배열도 Proxy로 반응형 처리하므로 수정 시 화면이 자동 갱신된다.
- 비교할 때는 보통 `===`가 더 안전하다.
- 이벤트 객체와 사용자 정의 값을 함께 전달하려면 `$event`를 명시해야 한다.
- 부모에도 클릭 이벤트가 있다면 삭제 버튼에는 `@click.stop`이 필요하다.
