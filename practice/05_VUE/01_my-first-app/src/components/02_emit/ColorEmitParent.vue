<template>
  <div :style="{ backgroundColor: color }">
    <h1>저의 색상을 정해주세요!!</h1>
    <ColorEmitChild @colorChange="colorChangeHandler" v-model:color="color" />
  </div>
</template>
<script>
import ColorEmitChild from './ColorEmitChild.vue';
export default {
  name: 'ColorEmitParent',
  data() {
    return {
      color: '',
    };
  },
  components: {
    ColorEmitChild,
  },
  methods: {
    colorChangeHandler(color) {
      this.color = color;
    },
  },
};
</script>
