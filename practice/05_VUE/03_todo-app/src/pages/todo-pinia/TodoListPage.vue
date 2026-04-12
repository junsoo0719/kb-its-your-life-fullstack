<script setup>
import { useTodoStore } from '@/stores/todo';
import axios from 'axios';
import { storeToRefs } from 'pinia';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const BASE_URL = '/api';

const router = useRouter();
const todoStore = useTodoStore();
const { todoArr, isFetching, isError } = storeToRefs(todoStore);
const { fetchTodoList } = todoStore;

const initTodoList = async () => {
  try {
    await fetchTodoList();
  } catch (error) {
    console.error(error);
  }
};

initTodoList();
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
