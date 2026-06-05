function sayWord(word) {
  console.log(`나는 외친다!! ${word}`);
}

function callbackLog(word, cb) {
  console.log('========== callbackLog 함수 호출 ==========');
  cb(word);
}

callbackLog('무~야~호~~~~~', sayWord);
callbackLog('러닝화', function (word) {
  console.log(`나는 사고 싶다!! ${word}`);
});
