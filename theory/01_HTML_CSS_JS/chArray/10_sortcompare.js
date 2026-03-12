const score = [82, 96, 54, 76, 92, 99, 69, 88];
console.log('before = ' + score);
function compare(left, right) {
  return right - left;
}
// score.sort(compare);
score.sort((a, b) => b - a);
console.log('after = ' + score);
