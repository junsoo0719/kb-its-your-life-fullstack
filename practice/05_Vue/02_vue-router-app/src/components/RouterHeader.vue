<script setup>
import { ref, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const router = useRouter();
const route = useRoute();

watch(
  () => route.path,
  (newRoute) => console.log(newRoute),
);
</script>

<template>
  <header>
    <nav>
      <button @click="router.push('/')">Home</button>
      <button @click="router.push('about')">About</button>
      <button @click="router.back()">뒤로</button>
      <button @click="router.forward()">앞으로</button>
      <button @click="router.go(-2)">뒤로 2번</button>
    </nav>
  </header>
</template>

<style lang="scss" scoped></style>
