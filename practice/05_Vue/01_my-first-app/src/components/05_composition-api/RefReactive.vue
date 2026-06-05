<template>
  <div>
    <h1>아아 꿈을 꾸었습니다. 제가 차은우가 되는 꿈을...</h1>
    <h1>Ref Data : {{ refData.name }}</h1>
    <h1>Reactive Data : {{ reactiveData.name }}</h1>
    <button @click="changeRef">Ref 데이터 변경</button>
    <button @click="changeReactive">Reactive 데이터 변경</button>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue';

const refData = ref({ name: '이효석' });
let reactiveData = reactive({ name: '이효석' });

const changeRef = () => {
  refData.value = { name: '차은우' };
};
const changeReactive = () => {
  reactiveData = { name: '차은우' };
};
</script>
