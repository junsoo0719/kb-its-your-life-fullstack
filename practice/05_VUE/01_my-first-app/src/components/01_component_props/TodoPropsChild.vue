<template>
  <div>
    <h2>{{ todo }}</h2>
    <hr />
  </div>
</template>

<script>
export default {
  name: 'TodoPropsChild',
  props: ['todo'],
};
</script>

<style scoped></style>
