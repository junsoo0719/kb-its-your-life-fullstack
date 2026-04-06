<script setup>
import axios from 'axios';
import { ref } from 'vue';

const PROXY_URL = '/ex-api';

const data = ref([]);

const fetchData = async () => {
  try {
    const url = PROXY_URL + '/cors/ex';
    const res = await axios.get(url);
    data.value = res.data;
    console.log(res.data);
  } catch (e) {
    console.error(e);
  }
};

fetchData();
</script>

<template>
  <div>
    <h1>CORS EX</h1>
    <ul>
      <li v-for="item in data" :key="item">{{ item }}</li>
    </ul>
  </div>
</template>

<style lang="scss" scoped></style>
