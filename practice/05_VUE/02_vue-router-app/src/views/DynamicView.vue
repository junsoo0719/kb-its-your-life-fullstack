<script setup>
import { computed, ref } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const mood = computed(() => route.params.mood);
const msg = computed(() =>
  mood.value === 'happy' ? '행복합니다' : '씐납니다',
);
const imgSrc = computed(() =>
  mood.value === 'happy'
    ? 'https://biz.chosun.com/resizer/v2/ITX6BGQ5LYVWC2YIYCMOLSAB5E.jpg?auth=d6a39af5925462230a21c35f0a2c31ce133496c11df064895572b38b18aa2f2a&width=464'
    : 'https://cdn.mhnse.com/news/photo/202411/350550_402920_3151.jpg',
);
</script>

<template>
  <div>
    <h1>오늘의 기분은 !? {{ msg }}</h1>
    <img :src="imgSrc" :alt="msg" />
  </div>
</template>

<style lang="scss" scoped></style>
