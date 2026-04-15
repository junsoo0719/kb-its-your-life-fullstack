<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

defineProps({
  todoItem: { type: Object, required: true },
});

const router= useRouter();
const{deleteTodo,toggleDone}=inject('actions')

</script>

<template>
  <li
    :class="
      todoItem.done
        ? 'list-group-item list-group-item-success'
        : 'list-group-item'
    "
  >
    <span
      :class="todoItem.done ? 'todo-done pointer' : 'pointer'"
      @click="toggleDone(todoItem.id)"
    ></span>
  </li>
</template>

<style lang="scss" scoped></style>
