<template>
  <div>
    <input type="text" v-model.trim="userInput" />
    <button @click="emit('send-msg', userInput)">부모님께 전송</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const userInput = ref('');
const emit = defineEmits(['send-msg']);
</script>
