fs = require('fs');
path = require('path');

filepath = path.join(__dirname, 'example.txt');

const data = fs.readFileSync(filepath, 'utf8');

if (fs.existsSync('text-1.txt')) {
  // text-1.txt 파일이 있다면
  console.log('file already exist');
} else {
  // text-1.txt 파일이 없다면
  fs.writeFileSync('./text-1.txt', data);
}
