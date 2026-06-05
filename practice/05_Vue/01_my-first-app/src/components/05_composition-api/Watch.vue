<template>
  <div>
    <h1>{{ count }}</h1>
    <button @click="count++">+</button>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue';

const count = ref(0);

watch(count, (cur, old) => {
  console.log(`카운트가 ${old} 에서 ${cur} 로 변경 되었습니다`);
});
</script>
