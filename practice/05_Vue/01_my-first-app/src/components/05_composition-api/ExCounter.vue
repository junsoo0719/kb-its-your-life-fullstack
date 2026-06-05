<template>
  <div>
    <h1>Vue3 Counter</h1>
    <h1>Count : {{ count }}</h1>
    <h1>두배로! {{ double }}</h1>
    <h1>지금까지 계산한 횟수 : {{ calcCount }}</h1>
    <button @click="increment">Increment</button>
    <button @click="decrement">decrement</button>
  </div>
</template>

<script setup>
import { computed, ref } from 'vue';

const count = ref(0);
const double = computed(() => count.value * 2);
const calcCount = ref(0);

function increment() {
  count.value++;
  calcCount.value++;
}
function decrement() {
  count.value--;
  calcCount.value++;
}
</script>
