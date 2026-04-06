<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const BASE_URL = '/api';
const router = useRouter();
const route = useRoute();
const todoId = route.params.id;

const todo = ref({});
const fetchTodo = async () => {
  try {
    const todoUrl = BASE_URL + `/todos/${todoId}`;
    const todoRes = await axios.get(todoUrl);
    todo.value = todoRes.data;
    console.log('TODO 데이터 목록 : ', todoRes.data);
  } catch (error) {
    console.error(error);
  }
};

const deleteTodo = async (id) => {
  try {
    const deleteUrl = BASE_URL + `/todos/${id}`;
    const deleteRes = await axios.delete(deleteUrl);
    console.log(deleteRes.data);
    router.push({ name: 'todo' });
  } catch (error) {
    console.error(error);
  }
};

fetchTodo();
</script>

<template>
  <div>
    <h1>TODO DETAIL</h1>
    <h2>id : {{ todo.id }}</h2>
    <h2>todo : {{ todo.todo }}</h2>
    <h2>desc : {{ todo.desc }}</h2>
    <h2>done : {{ todo.done }}</h2>
    <button @click="router.push({ name: 'todo' })">목록</button>
    <button @click="router.push({ name: 'todo/edit', params: { id: id } })">
      수정
    </button>
    <button @click="deleteTodo(todo.id)">삭제</button>
  </div>
</template>

<style lang="scss" scoped></style>
