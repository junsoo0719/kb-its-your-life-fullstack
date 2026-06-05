<template>
  <div>
    <div class="top">
      <h1>여기는 TOP 슬롯, 뭐든지 기울입니다!</h1>
      <slot name="top"> </slot>
    </div>
    <div class="bot">
      <h1>여기는 BOT 슬롯, 뭐든지 밑줄을 칩니다!</h1>
      <slot name="bot"> </slot>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.top {
  font-style: italic;
  background-color: skyblue;
}
.bot {
  text-decoration: underline;
  background-color: orange;
}
</style>
