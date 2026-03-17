function outcount() {
  var count = 0;
  setInterval(function () {
    count++;
    console.log(count + '초 지났습니다.');
  }, 1000);
}
outcount();
