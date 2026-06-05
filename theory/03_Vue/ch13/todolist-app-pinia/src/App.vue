<template>
  <div class="container">
    <Header />
    <router-view />
    <Loading v-if="isLoading" />
  </div>
</template>

<script setup>
import { computed } from 'vue';
import { useTodoListStore } from './stores/todoList';
import Header from './components/Header.vue';
import Loading from './components/Loading.vue';

const todoListStore = useTodoListStore();
const isLoading = computed(() => todoListStore.isLoading);
const fetchTodoList = todoListStore.fetchTodoList;
fetchTodoList();
</script>
