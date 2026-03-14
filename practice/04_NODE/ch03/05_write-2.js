fs = require('fs');

const data = fs.readFileSync('example.txt', 'utf-8');

if (fs.existsSync('text-1.txt')) {
  console.log('file already exists');
} else {
  fs.writeFileSync('./text-1.txt', data);
}
