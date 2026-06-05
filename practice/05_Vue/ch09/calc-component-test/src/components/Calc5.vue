<template>
  <div>
    x : <input type="text" v-model.number="x" />
    <br />
    결과 : {{ result }}
  </div>
</template>
<script>
import { ref, watch } from 'vue';
export default {
  name: 'Calc5',
  setup() {
    const x = ref(0); // 0으로 초기화
    const result = ref(0); // 0으로 초기화
    watch(x, (cur, old) => {
      console.log(`${old} -> ${cur}`);
      result.value = 2 * cur;
    });
    return { x, result };
  },
};
</script>
