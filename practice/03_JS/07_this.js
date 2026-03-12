const sayHelloNormal = function () {
  console.log(`name : ${this.name}`);
  console.log('this : ', this);
};

const sayHelloArrow = () => {
  console.log(`name : ${this.name}`);
  console.log('this : ', this);
};

const tetz = {
  name: '이효석',
  sayHelloNormal,
  sayHelloArrow,
};

tetz.sayHelloNormal();
tetz.sayHelloArrow();
