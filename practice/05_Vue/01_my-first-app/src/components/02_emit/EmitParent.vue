<template>
  <div>
    <h1>너의 이름은 {{ name }}</h1>
    <EmitChild @nameChanged="nameChangedHandler" />
  </div>
</template>

<script>
import EmitChild from './EmitChild.vue';

export default {
  name: 'EmitParent',
  components: {
    EmitChild,
  },
  data() {
    return {
      name: '',
    };
  },
  methods: {
    nameChangedHandler(name) {
      this.name = name;
    },
  },
};
</script>
