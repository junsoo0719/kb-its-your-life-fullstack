<template>
  <div>
    <h1>{{ msg }}</h1>
    <QuizChild :answer="answer" @send-msg="emitHandler" />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import QuizChild from './QuizChild.vue';

const msg = ref('랜덤 숫자를 맞춰 주세요');
const answer = ref(parseInt(Math.random() * 21));

function emitHandler(input) {
  msg.value = input;
}
</script>
