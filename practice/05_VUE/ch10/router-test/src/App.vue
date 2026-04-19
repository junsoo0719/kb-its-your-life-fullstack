<template>
  <div class="container">
    <Header />
    <RouterView></RouterView>
  </div>
</template>

<script>
import Header from './components/Header.vue';

export default {
  name: 'App',
  components: { Header },
};
</script>

<style>
.container {
  text-align: center;
  margin-top: 10px;
}
</style>
