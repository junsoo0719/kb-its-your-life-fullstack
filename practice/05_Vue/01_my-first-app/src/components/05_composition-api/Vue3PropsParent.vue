<template>
  <div>
    <Vue3PropsChild :msg="msg" />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import Vue3PropsChild from './Vue3PropsChild.vue';

const msg = ref('props로 전달');
</script>
