const score = [82, 96, 54, 76, 9, 100, 69, 88];
console.log('before = ' + score);
score.sort(function (left, right) {
  return left - right;
});
console.log('after = ' + score);
