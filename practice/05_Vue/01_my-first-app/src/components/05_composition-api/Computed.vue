<template>
  <div>
    <h1>{{ count }}</h1>
    <button @click="increment">+</button>
  </div>
</template>

<script setup>
import { computed, ref } from 'vue';

const count = ref(1);
const double = computed(() => count.value * 2);

function increment() {
  count.value++;
}

console.log('🚀 ~ double:', double);
console.log('🚀 ~ double.value:', double.value);
</script>
