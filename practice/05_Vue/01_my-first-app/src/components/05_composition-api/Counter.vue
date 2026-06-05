<template>
  <div>
    <h1>Vue3 Counter</h1>
    <h1>Count : {{ count }}</h1>
    <h1>두배로! {{ double }}</h1>
    <button @click="increment">+</button>
  </div>
</template>

<script setup>
import { computed, ref } from 'vue';

const count = ref(0);
const double = computed(() => count.value * 2);

function increment() {
  count.value++;
}
</script>
