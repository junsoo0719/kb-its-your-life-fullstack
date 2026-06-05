const fs = require('fs');

const rs = fs.createReadStream('./readMe.txt', 'utf-8');
const ws = fs.createWriteStream('./writeMe.txt');

rs.pipe(ws);
