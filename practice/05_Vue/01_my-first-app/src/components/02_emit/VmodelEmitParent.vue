<template>
  <div>
    <h1>너의 이름은 {{ name }}</h1>
    <VmodelEmitChild v-model:name="name" />
  </div>
</template>

<script>
import VmodelEmitChild from './VmodelEmitChild.vue';

export default {
  name: 'VmodelEmitParent',
  components: {
    VmodelEmitChild,
  },
  data() {
    return {
      name: '',
    };
  },
  methods: {},
};
</script>
