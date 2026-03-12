function leftPad(str, len, ch) {
  str = String(str);
  if (str.length >= len) {
    return str;
  }
  if (ch === undefined) {
    ch = ' ';
  } else if (ch === 0) {
    ch = '0';
  }
  let result = '';
  for (let i = 0; i < len - str.length; i++) {
    result += ch;
  }
  return result + str;
}

// function leftPad(str, len, ch) {
//   str = String(str);
//   len = len - str.length;

//   if (len < 1) return str;

//   //ch에 값이 전달되지 않은 경우 빈 공백
//   if (!ch && ch !== 0) ch = ' ';
//   else if (ch === 0) ch = '0';

//   for (let i = 0; i < len; i++) {
//     str = ch + str;
//   }
//   return str;
// }

console.log(leftPad('abc', 5, '*'));
console.log(leftPad('123', 2, '0'));
console.log(leftPad('7', 4, 0));
console.log(leftPad('hi', 5));
