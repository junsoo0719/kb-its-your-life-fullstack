<template>
  <div>
    할 일
    <input
      type="text"
      class="form-control"
      v-mode.trim="text"
      @keyup.enter="addTodoHandler"
      placeholder="할일을 여기에 입력!"
    />
    <span class="btn btn-primary input-group-addon" @click="addTodoHandler"
      >추가</span
    >
  </div>
</template>
<script>
export default {
  name: 'InputTodo',
  data() {
    return {
      text: '',
    };
  },
  emits: ['add-todo'],
  methods: {
    addTodoHandler() {
      if (this.text.length >= 2) {
        this.$emit('add-todo', this.text);
        this.text = '';
      }
    },
  },
};
</script>
