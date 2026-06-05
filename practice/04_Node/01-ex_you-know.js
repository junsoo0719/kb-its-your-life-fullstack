// youKnow() 함수를 완성해 주세요
function youKnow(name, cb) {
  console.log(`You know ~ `);
  cb(name);
}
function sayMyName(name) {
  console.log(`엄.... 제 이름은 ${name} 입니다`);
}

youKnow('이효석', sayMyName);
youKnow('송준수', (name) => {
  console.log(`저는 ${name} 이고, 취미는 헬스를 합니다!`);
});
