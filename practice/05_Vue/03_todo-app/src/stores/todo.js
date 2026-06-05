import axios from 'axios';
import { defineStore } from 'pinia';
import { ref } from 'vue';

const BASE_URL = '/api';

export const useTodoStore = defineStore('todo', () => {
  const todoArr = ref([]);
  const isFetching = ref(false);
  const isError = ref(false);

  const fetchTodoList = async () => {
    try {
      isFetching.value = true;
      const fetchTodoListUrl = BASE_URL + '/todos';
      const fetchTodoListRes = await axios.get(fetchTodoListUrl);

      todoArr.value = fetchTodoListRes.data;
      isFetching.value = false;

      return todoArr.value;
    } catch (error) {
      isError.value = true;
      console.error(error);
    }
  };

  return { todoArr, isFetching, isError, fetchTodoList };
});
