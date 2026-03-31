<template>
  <div>
    <h1>드디어 도착한 props 의 내용은?</h1>
    <h1>Inject 로 날아온 데이터 : {{ fly }}</h1>
    <h1>Props 로 굴러온 데이터 : {{ drilling }}</h1>
  </div>
</template>

<script>
export default {
  props: ['drilling'],
  inject: ['fly'],
};
</script>
