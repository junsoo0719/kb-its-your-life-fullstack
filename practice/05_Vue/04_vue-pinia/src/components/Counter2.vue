<script setup>
import { useCounterStore } from '@/stores/counter';
import { ref } from 'vue';

const counterStore = useCounterStore();
</script>

<template>
  <div>
    <h1>Counter</h1>
    <h2>count : {{ counterStore.count }}</h2>
    <h2>count x 2 : {{ counterStore.doubleCount }}</h2>
    <button @click="counterStore.increment">+1</button>
    <button @click="counterStore.decrement">-1</button>
  </div>
</template>

<style lang="scss" scoped></style>
