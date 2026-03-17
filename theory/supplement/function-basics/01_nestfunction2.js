function sum(n) {
  function add(a, b) {
    return a + b;
  }
  var s = 0;
  for (var i = 0; i <= n; i++) {
    s = add(s, i);
  }
  return s;
}
console.log('1~100 = ' + sum(100));
console.log('2 + 3 = ' + add(2 + 3)); // 에러
