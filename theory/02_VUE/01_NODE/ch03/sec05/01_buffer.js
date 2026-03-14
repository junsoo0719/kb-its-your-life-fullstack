const fs = require('fs');
path = require('path');

filepath = path.join(__dirname, 'example.txt');

fs.readFile(filepath, (err, data) => {
  if (err) return console.log(err);
  console.log(data); // 이진 데이터 표시
  console.log('\n');
  console.log(data.toString()); // 문자열로 변환해서 표시
});
