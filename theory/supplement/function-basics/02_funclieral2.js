console.log('2 + 3 = ' + add1(2, 3));
console.log('4 + 5 = ' + add2(4, 5)); // 에러
function add1(a, b) {
  return a + b;
}
var add2 = function (a, b) {
  return a + b;
};
