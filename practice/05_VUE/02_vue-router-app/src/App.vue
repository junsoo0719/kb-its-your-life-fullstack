<script setup>
import { RouterLink, RouterView } from 'vue-router';
import RouterHeader from './components/RouterHeader.vue';

window.localStorage.setItem('name', '이효석');
console.log(window.localStorage.getItem('name'));
localStorage.setItem('judengchi', '구치파치');
localStorage.removeItem('name');
localStorage.clear();
console.log(localStorage);
</script>

<template>
  <header>
    <nav>
      <RouterLink to="/">Home</RouterLink>
      /
      <RouterLink to="/about">About</RouterLink>
      /
      <RouterLink to="/dynamic/happy">Happy</RouterLink>
      /
      <RouterLink to="/dynamic/exciting">Exciting</RouterLink>
      /
      <RouterLink to="/dynamic-watch/happy">WatchHappy</RouterLink>
      /
      <RouterLink to="/dynamic-watch/exciting">WatchExciting</RouterLink>
      /
      <RouterLink to="/cors">Cors</RouterLink>
      /
      <RouterLink to="/cors-ex">CorsEx</RouterLink>

    </nav>
  </header>
  <RouterHeader />
  <RouterView />
</template>

<style scoped></style>
