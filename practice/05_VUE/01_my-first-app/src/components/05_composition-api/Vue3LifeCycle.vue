<template>
  <div>
    <h1>Tetz's favorite food list</h1>
    <button @click="fetchFoods">데이터 받아오기</button>
    <ul>
      <li v-for="(food, index) in foods" v-bind:key="index">
        {{ food.food }}
      </li>
    </ul>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';

const endPoint =
  'https://port-0-kb7-back-node-mmc3pmla14c7b8b4.sel3.cloudtype.app';

const foods = ref([]);

const fetchFoods = async () => {
  try {
    const response = await fetch(`${endPoint}/food/all`);
    foods.value = await response.json();
  } catch (e) {
    console.error(e);
  }
};

onMounted(() => {
  fetchFoods();
});
</script>
