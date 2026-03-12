const ironMan = {
  name: 'Tony Stark',
  age: 34,
  height: 174,
  weight: 65,
};

// let 빼먹으면 안됨
for (let key in ironMan) {
  // ${}를 쓰려면 양쪽이 ''이 아니라 ``이어야 함
  console.log(`key 는 ${key} 이고, 값은 ${ironMan[key]}`);
}

ironMan.name;
ironMan['name'];
