<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const BASE_URL = '/api';

const router = useRouter();

const todoArr = ref([]);
const isFetching = ref(true);
const isError = ref(false);

const fetchTodo = async () => {
  try {
    isFetching.value = true;
    isError.value = false;

    const todoUrl = BASE_URL + '/todos';
    const todoRes = await axios.get(todoUrl);

    todoArr.value = todoRes.data;
    console.log('TODO 데이터 목록 : ', todoRes.data);

    isFetching.value = false;
  } catch (error) {
    isError.value = true;
    console.error(error);
  }
};

fetchTodo();
</script>

<template>
  <div>
    <h1>TODOLIST PAGE</h1>
    <h2 v-if="isFetching">데이터 로딩 중</h2>
    <h2 v-else-if="isError">통신 중 에러 발생</h2>
    <div v-else>
      <ul>
        <li v-for="item in todoArr" :key="item.id">
          {{ item.id }} /
          <RouterLink :to="`/todo/detail/${item.id}`">{{
            item.todo
          }}</RouterLink>
          / {{ item.done }}
        </li>
      </ul>
      <button @click="router.push({ name: 'todo/write' })">Todo 작성</button>
    </div>
  </div>
</template>

<style lang="scss" scoped></style>
