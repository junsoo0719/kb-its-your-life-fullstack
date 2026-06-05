<script setup>
import Counter1 from './components/Counter1.vue';
import Counter2 from './components/Counter2.vue';
import Counter3 from './components/Counter3.vue';
</script>

<template>
  <!-- 서로 다른 컴포넌트이지만 같은 pinia store의 값을 사용함 -->
  <Counter1 />
  <Counter2 />
  <Counter3 />
</template>

<style scoped></style>
