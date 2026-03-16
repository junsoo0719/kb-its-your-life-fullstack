const fs = require('fs');

fs.readFile('example.txt', 'utf8', (err, data) => {
  if (err) {
    return console.log(err);
  }
  console.log(data);
});

console.log('코드 끝');
