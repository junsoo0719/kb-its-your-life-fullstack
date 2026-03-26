<template>
  <div class="todo">
    <h1>오늘의 할 일</h1>
    <TodoComponentChild />
    <hr />
    <TodoPropsChild todo="Vue 공부하기" />
    <TodoPropsChild todo="배열 함수 연습하기" />
    <TodoComponentChild />
    <TodoEventChild :parentEvent="sendingEvent" />
  </div>
</template>

<script>
import TodoComponentChild from './TodoComponentChild.vue';
import TodoEventChild from './TodoEventChild.vue';
import TodoPropsChild from './TodoPropsChild.vue';

export default {
  name: 'TodoParent',
  components: {
    TodoComponentChild,
    TodoPropsChild,
    TodoEventChild,
  },
  methods: {
    sendingEvent() {
      alert('부모 컴포넌트 님이 말씀 하셨다~ 비상!!!!');
    },
  },
};
</script>

<style scoped></style>
