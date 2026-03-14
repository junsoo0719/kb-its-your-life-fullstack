fs = require('fs');
path = require('path');

filepath = path.join(__dirname, 'example.txt');

const data = fs.readFileSync(filepath, 'utf-8'); // 인코딩 지정
console.log(data);
