const http = require('http');

const server = http.createServer((req, res) => {
  if (req.url === '/home') {
    res.end('HOME');
  } else if (req.url === '/about') {
    res.end('ABOUT');
  } else {
    res.end('NOT FOUND');
  }
});

server.listen(3000, () => {
  console.log('http://localhost:3000 서버 실행중');
});
