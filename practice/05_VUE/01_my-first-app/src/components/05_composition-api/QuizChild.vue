<template>
  <div>
    <input type="text" v-model.number="userInput" />
    <button @click="checkAnswer">정답!</button>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue';

const userInput = ref(0);
const msg = ref('');
const submitCount = ref(0);
const props = defineProps({
  answer: {
    type: Number,
    required: true,
  },
});
const emit = defineEmits(['send-msg']);

function checkAnswer() {
  // console.log(userInput.value);
  // console.log(props.answer);
  if (props.answer > userInput.value) {
    msg.value = 'Up!';
  } else if (props.answer < userInput.value) {
    msg.value = 'Down!';
  } else {
    msg.value = '정답입니다!!!';
  }
  submitCount.value++;
  emit('send-msg', msg.value);
}

watch(submitCount, (cur, old) => {
  if (cur === 3) {
    alert(`${cur}번째 시도 입니다!`);
  }
});
</script>
