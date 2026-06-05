fs = require('fs');

if (fs.existsSync('./test')) {
  // 삭제할 디렉토리가 있다면
  fs.rmdir('./test', (err) => {
    if (err) return console.error(err);
    console.log('folder deleted');
  });
} else {
  // 삭제할 디렉토리가 없다면
  console.log('folder does not exist');
}
