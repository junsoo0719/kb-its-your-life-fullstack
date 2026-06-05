const obj = {
  result: 0,
};

// obj.add = function (x, y) {
//   function inner() {
//     this.result = x + y;
//   }
//   inner(); // add안에서 호출된 것 x, 글로벌에서 호출된 것 o
// };

obj.add = function (x, y) {
  const inner = () => {
    this.result = x + y; // 화살표 함수는 this가 obj를 참조함 => 화살표함수 쓰는 게 안전
  };
  inner();
};

obj.add(3, 4);
console.log(obj);
// console.log(result); // 화살표 함수를 사용하면 global에 result 존재하지 않기 때문
