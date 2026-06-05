<template>
  <div>
    <button @click="showModal">모달 열기</button>
    <div class="content">
      <h1>원래 부모 요소의 컨텐츠 입니다!!!</h1>
      <h1>원래 부모 요소의 컨텐츠 입니다!!!</h1>
      <h1>원래 부모 요소의 컨텐츠 입니다!!!</h1>
      <h1>원래 부모 요소의 컨텐츠 입니다!!!</h1>
      <h1>원래 부모 요소의 컨텐츠 입니다!!!</h1>
      <h1>원래 부모 요소의 컨텐츠 입니다!!!</h1>
      <h1>원래 부모 요소의 컨텐츠 입니다!!!</h1>
      <h1>원래 부모 요소의 컨텐츠 입니다!!!</h1>
      <h1>원래 부모 요소의 컨텐츠 입니다!!!</h1>
      <h1>원래 부모 요소의 컨텐츠 입니다!!!</h1>
      <h1>원래 부모 요소의 컨텐츠 입니다!!!</h1>
      <h1>원래 부모 요소의 컨텐츠 입니다!!!</h1>
      <h1>원래 부모 요소의 컨텐츠 입니다!!!</h1>
      <h1>원래 부모 요소의 컨텐츠 입니다!!!</h1>
      <h1>원래 부모 요소의 컨텐츠 입니다!!!</h1>
      <h1>원래 부모 요소의 컨텐츠 입니다!!!</h1>
      <h1>원래 부모 요소의 컨텐츠 입니다!!!</h1>
      <h1>원래 부모 요소의 컨텐츠 입니다!!!</h1>
      <h1>원래 부모 요소의 컨텐츠 입니다!!!</h1>
      <h1>원래 부모 요소의 컨텐츠 입니다!!!</h1>
    </div>
    <ModalChild :visible="isModalVisible" @close="hideModal">
      <h2>이 모달을 당장 닫지 않으면!?</h2>
      <p>설렐 겁니다!</p>
      <img
        class="image"
        src="https://encrypted-tbn0.gstatic.com/licensed-image?q=tbn:ANd9GcQrk0i8zr26BNSU_q6HodebyCpZkEIf6VB14ilipceGPYrtRG9qhvDscZWj71VuT5-eVBY9igsT8BppkNz6NYmuwPpOrapa_7fklZkhvsXmxRk3uZZgq7DrqpfOhG3mIJdoNEMHIis11Ks&s=19"
      />
    </ModalChild>
  </div>
</template>

<script>
import ModalChild from './ModalChild.vue';

export default {
  components: {
    ModalChild,
  },
  data() {
    return {
      isModalVisible: false,
    };
  },
  methods: {
    showModal() {
      this.isModalVisible = true;
    },
    hideModal() {
      this.isModalVisible = false;
    },
  },
};
</script>

<style scoped>
.img {
  align-items: center;
}
</style>
