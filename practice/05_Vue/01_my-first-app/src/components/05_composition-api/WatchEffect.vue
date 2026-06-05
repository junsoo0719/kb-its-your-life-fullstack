<template>
  <div>
    <h1>{{ count }}</h1>
    <button @click="count++">+</button>
  </div>
</template>

<script setup>
import { ref, watchEffect } from 'vue';

const count = ref(0);

watchEffect(() => {
  console.log(`카운트가 ${count.value} 로 변경 되었습니다`);
});
</script>
