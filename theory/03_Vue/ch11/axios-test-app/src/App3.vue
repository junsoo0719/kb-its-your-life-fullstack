<script setup>
import axios from 'axios';

const requestAPI = async () => {
  const url = '/api/todos';
  const response = await axios.get(url);
  console.log('#응답객체 : ', response);
};

requestAPI();
</script>

<template>
  <div>
    <h2>콘솔을 확인합니다.</h2>
  </div>
</template>

<style scoped></style>
