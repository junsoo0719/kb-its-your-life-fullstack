<template>
  <div>
    <ProvideChild :drilling="drilling" />
  </div>
</template>

<script>
import ProvideChild from './ProvideChild.vue';

export default {
  components: {
    ProvideChild,
  },
  data() {
    return {
      drilling: '으아아아아아아아아 어디까지 내려가는 거에요~~~~~~~~~~~~~~~~~',
    };
  },
  provide() {
    return {
      fly:'훗 한번에 갔슈'
    }
  },
};
</script>
