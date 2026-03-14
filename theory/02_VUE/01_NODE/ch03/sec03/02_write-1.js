fs = require('fs');
path = require('path');

filepath = path.join(__dirname, 'example.txt');

const data = fs.readFileSync(filepath, 'utf8');
fs.writeFileSync('./text-1.txt', data);
