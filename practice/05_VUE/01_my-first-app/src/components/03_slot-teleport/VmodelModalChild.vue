<template>
  <teleport to="body">
    <div class="modal-overlay" v-if="visible">
      <div class="modal">
        <button class="close-btn" @click="$emit('update:visible', false)">
          X
        </button>
        <div class="modal-content">
          <slot></slot>
        </div>
      </div>
    </div>
  </teleport>
</template>

<script>
export default {
  props: ['visible'],
  emits: ['update:visible'],
};
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal {
  background-color: white;
  padding: 20px;
  border-radius: 5px;
  position: relative;
  width: 60%;
}

.close-btn {
  position: absolute;
  top: 10px;
  right: 10px;
  background: transparent; /* 글자 안 보이게 */
  border: none;
  font-size: 20px;
  font-weight: 700; /* bold */
  cursor: pointer;
}

.modal-content {
  margin-top: 40px;
}
</style>
