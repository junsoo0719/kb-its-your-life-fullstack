<template>
  <div>
    <ProvideGrandChild :drilling="drilling" />
  </div>
</template>

<script>
import ProvideGrandChild from './ProvideGrandChild.vue';

export default {
  components: {
    ProvideGrandChild,
  },
  props: ['drilling'],
};
</script>
