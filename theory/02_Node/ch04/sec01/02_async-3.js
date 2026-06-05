const fs = require('node:fs');

fs.readdir('./', (err, files) => {
  if (err) {
    return console.error(err);
  }
  console.log(files);
});
console.log('Code is done.');
