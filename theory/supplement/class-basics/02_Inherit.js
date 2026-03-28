class Parent {
  constructor(name) {
    this.name = name;
  }
  print() {
    console.log('이름 : ' + this.name);
  }
}

class Child extends Parent {
  constructor(name, age) {
    super(name);
    this.age = age;
  }
  print() {
    super.print();
    console.log('나이 : ' + this.age);
  }
  static sayHello() {
    console.log('Hello~');
  }
}

class GrandChild extends Child {
  constructor(name, age, address) {
    super(name, age);
    this.address = address;
  }
  print() {
    super.print();
    console.log('주소 : ' + this.address);
  }
}
let person = new GrandChild('홍길동', 20, '서울');
person.print();
GrandChild.sayHello();
