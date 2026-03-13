const country = ['korea', 'USA', 'Japan', 'China'];
console.log('before = ' + country);
country.sort(function (left, right) {
  let left2 = left.toLowerCase(); //소문자로 변환하여 대소문자 구분 없이 정렬
  let right2 = right.toLowerCase();
  if (left2 < right2) return -1;
  if (left2 > right2) return 1;
  return 0;
});
console.log('after = ' + country);
