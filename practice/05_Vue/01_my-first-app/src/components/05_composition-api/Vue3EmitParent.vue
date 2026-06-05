<template>
  <div>
    <h1>{{ msg }}</h1>
    <Vue3EmitChild @send-msg="emitHandler" />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import Vue3EmitChild from './Vue3EmitChild.vue';

const msg = ref('자식놈이 바꿀 메시지 입니다!');
const emitHandler = (input) => {
  msg.value = input;
};
</script>
