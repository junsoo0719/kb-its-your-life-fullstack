// 삽입만 하기
const ar1 = [0, 1, 2, 3, 4, 5, 6, 7];
ar1.splice(2, 0, 10, 11, 12);
console.log('ar1 = ' + ar1);
// 삭제만 하기
const ar2 = [0, 1, 2, 3, 4, 5, 6, 7];
ar2.splice(2, 3);
console.log('ar2 = ' + ar2);
// 삭제 후 삽입 하기
const ar3 = [0, 1, 2, 3, 4, 5, 6, 7];
ar3.splice(2, 3, 10, 11);
console.log('ar3 = ' + ar3);
