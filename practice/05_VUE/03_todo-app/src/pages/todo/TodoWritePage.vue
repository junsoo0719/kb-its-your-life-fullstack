<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const BASE_URL = '/api';
const router = useRouter();

const todo = ref('');
const desc = ref('');

const addTodo = async () => {
  try {
    const addTodoUrl = BASE_URL + '/todos';
    const newTodo = {
      todo: todo.value,
      desc: desc.value,
      done: false,
    };

    const addTodoRes = await axios.post(addTodoUrl, newTodo);

    return router.push({ name: 'todo' });
  } catch (error) {
    console.error(error);
  }
};
</script>

<template>
  <div>
    <h1>TODO WRITE</h1>
    <h2>todo : <input type="text" v-model.trim="todo" /></h2>
    <h2>desc : <input type="text" v-model.trim="desc" /></h2>
    <button @click="addTodo">작성</button>
    <button @click="router.push({ name: 'todo' })">취소</button>
  </div>
</template>

<style lang="scss" scoped></style>
