const tetz = {
  id: 'xenosign',
  name: '이효석',
  emai: 'xenosign@naver.com',
};

// 변수 3개를 선언하면서 객체의 각 값을 각 변수에 할당
const { id, name, email } = tetz;

const fruits = ['사과', '바나나', '수박'];

const [apple, banana, watermelon] = fruits;

console.log(fruits);
// 전개 연산자
console.log(...fruits);

const newFruits = ['딸기', ...fruits];

console.log(newFruits);

const person = { name: '홍길동', age: 30 };
const job = { title: '개발자', company: 'ABC Inc' };

const profile = { ...person, ...job, location: '서울' };

console.log(profile);
