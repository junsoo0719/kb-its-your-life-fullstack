const fs = require('fs');

const data = fs.readFileSync('./example.txt', 'utf-8');
console.log(data);

fs.readFile('./example.txt', 'utf-8', (err, data) => {
  if (err) {
    console.error(err);
    return;
  }
  console.log(data);
});
