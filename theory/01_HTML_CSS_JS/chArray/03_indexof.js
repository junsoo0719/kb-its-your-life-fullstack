const ar = ['태연', '유리', '윤아', '써니', '수영', '유리', '서현', '효연'];
console.log('ar = ' + ar);
let sunny = ar.indexOf('써니');
console.log('써니는 ' + sunny + '번째에 있다. ');
let yuri = ar.indexOf('유리');
console.log('유리는 ' + yuri + '번째에 있다. ');
yuri = ar.lastIndexOf('유리');
console.log('유리는 뒤에서 ' + yuri + '번째에 있다. ');
let suji = ar.indexOf('수지');
if (suji == -1) {
  console.log('수지는 소녀시대가 아니다.');
}
