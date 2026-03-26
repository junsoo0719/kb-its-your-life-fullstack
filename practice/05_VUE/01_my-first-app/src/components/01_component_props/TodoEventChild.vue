<template>
  <div>
    <button @click="parentEvent">자식 버튼</button>
  </div>
</template>

<script>
export default {
  name: 'TodoEventChild',
  props: ['parentEvent'],
};
</script>
