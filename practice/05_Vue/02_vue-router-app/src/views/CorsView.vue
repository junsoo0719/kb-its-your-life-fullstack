<script setup>
import axios from 'axios';

const CORS_URL =
  'https://port-0-kb7-back-node-mmc3pmla14c7b8b4.sel3.cloudtype.app';
const PROXY_URL = '/api';

async function checkCorsOn() {
  try {
    const corsUrl = CORS_URL + '/cors/on';
    const corsRes = await fetch(corsUrl);
    const data = await corsRes.json();
    console.log('CORS 응답 결과 : ', data);
  } catch (e) {
    console.log('CORS 통신 에러 발생 : ', e);
  }
}

async function checkCorsOff() {
  try {
    const corsUrl = PROXY_URL + '/cors/off';
    const corsRes = await axios.get(corsUrl);
    const data = corsRes.data;

    // const corsRes = await fetch(corsUrl);
    // const data = await corsRes.json();
    console.log('CORS 응답 결과 : ', data);
  } catch (e) {
    console.log('CORS 통신 에러 발생 : ', e);
  }
}
</script>

<template>
  <div>
    <h1>CORS PAGE</h1>
    <button @click="checkCorsOn">Cors 설정 On</button>
    <button @click="checkCorsOff">Cors 설정 Off</button>
  </div>
</template>

<style scoped></style>
