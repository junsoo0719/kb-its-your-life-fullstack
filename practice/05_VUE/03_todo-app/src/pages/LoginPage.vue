<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const BASE_URL = '/api';

const userId = ref('');
const userPassword = ref('');
const router = useRouter();

const login = async () => {
  try {
    const loginUrl = BASE_URL + '/users';
    const loginRes = await axios.get(loginUrl);
    console.log('axios 로그인 통신 결과 : ', loginRes);

    const userArr = loginRes.data;

    const findUser = userArr.find(function (item, index) {
      return userId.value === item.id;
    });

    if (findUser === undefined)
      return alert('해당 ID 를 가진 사용자가 존재하지 않습니다');

    if (findUser.password !== userPassword.value)
      return alert('비밀번호가 일치하지 않습니다');

    localStorage.setItem('auth', 'true');
    alert('로그인 성공');

    return router.push({ name: 'todo' });
  } catch (error) {
    console.error(error);
  }
};

const logout = () => {
  localStorage.removeItem('auth');
  return alert('로그아웃 완료!');
};
</script>

<template>
  <div>
    <h1>LOGIN PAGE</h1>
    아이디 : <input type="text" v-model.trim="userId" />
    <br />
    패스워드 : <input type="password" v-model.trim="userPassword" />
    <br />
    <button @click="login">로그인</button> /
    <button @click="logout">로그 아웃</button>
  </div>
</template>

<style lang="scss" scoped></style>
