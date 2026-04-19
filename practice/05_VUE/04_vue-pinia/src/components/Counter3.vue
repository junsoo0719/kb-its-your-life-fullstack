<script setup>
import { useCounterStore } from '@/stores/counter';
import { storeToRefs } from 'pinia';
import { ref } from 'vue';

const counterStore = useCounterStore();

const { count, doubleCount } = storeToRefs(counterStore);
const { increment, decrement } = counterStore;
</script>

<template>
  <div>
    <h1>Counter</h1>
    <h2>count : {{ count }}</h2>
    <h2>count x 2 : {{ doubleCount }}</h2>
    <button @click="increment">+1</button>
    <button @click="decrement">-1</button>
  </div>
</template>

<style lang="scss" scoped></style>
