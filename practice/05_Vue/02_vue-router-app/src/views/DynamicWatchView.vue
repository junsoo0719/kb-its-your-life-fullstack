<script setup>
import { ref, watch } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();

const msg = ref('');
const imgSrc = ref('');

watch(
  () => route.params.mood,
  (cur, prev) => {
    if (cur === 'happy') {
      msg.value = '행복합니다';
      imgSrc.value =
        'https://biz.chosun.com/resizer/v2/ITX6BGQ5LYVWC2YIYCMOLSAB5E.jpg?auth=d6a39af5925462230a21c35f0a2c31ce133496c11df064895572b38b18aa2f2a&width=464';
    } else {
      msg.value = '씐납니다';
      imgSrc.value =
        'https://cdn.mhnse.com/news/photo/202411/350550_402920_3151.jpg';
    }
  },
  { immediate: true },
);
</script>

<template>
  <div>
    <h1>오늘의 기분은 !? {{ msg }}</h1>
    <img :src="imgSrc" :alt="msg" />
  </div>
</template>

<style lang="scss" scoped></style>
