<template>
  <div>
    <input type="text" @input="$emit('update:name', $event.target.value)" />
  </div>
</template>

<script>
export default {
  name: 'VmodelEmitChild',
  props: ['name'],
  emits: ['update:name'],
};
</script>
