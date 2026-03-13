console.log('1. async 카페에서 1번 손님 주문을 받습니다');

setTimeout(() => {
  console.log('2. 1번 손님 커피가 준비 되었습니다. (콜백 실행)');
}, 3000);

console.log('3. 2번 손님의 주문을 받습니다');
