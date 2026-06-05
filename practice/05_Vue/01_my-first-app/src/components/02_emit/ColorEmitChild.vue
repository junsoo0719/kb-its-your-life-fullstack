<template>
  <div>
    <input
      type="text"
      @input.trim="$emit('update:color', $event.target.value)"
    />
    <button @click="$emit('colorChange', { color })">색상 전달</button>
  </div>
</template>

<script>
export default {
  name: 'ColorEmitChild',
  props: ['color'],
  emits: ['colorChange', 'upadate:color'],
};
</script>
