function executeAsyncTask(callback) {
  console.log('콜백을 받는 함수 시작');
  setTimeout(() => {
    console.log('setTimeout 끝!');
    callback('콜백으로 비동기 구현 성공!');
  }, 2000);
}

console.log('콜백 함수 전달 전');

executeAsyncTask(function (data) {
  console.log(data);
});
