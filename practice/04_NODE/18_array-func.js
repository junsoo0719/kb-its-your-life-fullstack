const classes = [
  { id: 1, name: '강채연' },
  { id: 2, name: '강태규' },
  { id: 3, name: '권유현' },
  { id: 4, name: '김건우' },
  { id: 5, name: '김기선' },
  { id: 6, name: '김민철' },
  { id: 7, name: '김수현' },
  { id: 8, name: '김현태' },
  { id: 9, name: '송준수' },
  { id: 10, name: '송태권' },
  { id: 11, name: '양승환' },
  { id: 12, name: '오진호' },
  { id: 13, name: '이대주' },
  { id: 14, name: '이민호' },
  { id: 15, name: '이아영' },
  { id: 16, name: '이지민' },
  { id: 17, name: '이지은' },
  { id: 18, name: '이채연' },
  { id: 19, name: '장지연' },
  { id: 20, name: '최규진' },
  { id: 21, name: '최보윤' },
  { id: 22, name: '홍상우' },
  { id: 23, name: '황지원' },
];

// 1. filter : 특정 조건에 맞는 배열을 새롭게 만들어서 리턴, 원본 배열 변화 없음
const oddNum = classes.filter(function (student, index) {
  // console.log(student, index);
  return student.id % 2 === 1;
});
// console.log(oddNum);
// console.log(classes);

const kimFamily = classes.filter((student) => student.name.startsWith('김'));
// console.log(kimFamily);

// 2. map : 각각 배열 값을 변경 하거나, 특정 작업을 수행할 때
// 원본 배열 변화 없음. 단, 작업한 결과물은 리턴
const addCondition = classes.map(function (student) {
  return { ...student, condition: 'good' };
});
// console.log(addCondition);
// console.log(classes);

const namelist = classes.map((student) => student.name);
// console.log(namelist);

// 3. forEach : 각각 배열을 순회하면서 작업 진행, 원본 X, 리턴 X
const addCondition2 = classes.forEach((student) => {
  // console.log(student);
  return { ...student, condition: 'good' };
});
// console.log(addCondition2);

// 4. find : 특정 요소 하나만 찾기, 리턴 O
const classLeader = classes.find((student) => student.name === '오진호');
// console.log(classLeader);

// 5. findIndex : 특정 요소 하나의 인덱스를 리턴
const LDJ = classes.findIndex((student) => student.name === '이대주');
// console.log(LDJ, classes[LDJ]);

// 6. sort : 정렬, 원본에 변화 있음
const randNumbers = [5, 9, 1, -4, 400, 6, 33, 77, 999];
// console.log('정렬 전 : ', randNumbers);

randNumbers.sort((a, b) => b - a);
// console.log('정렬 후 : ', randNumbers);

classes.sort((a, b) => b.id - a.id);
console.log(classes);

// 7. reduce : acc 라는 누산기를 제공, 작업 수행 중 누적되는 결과를 남길 수 있음
// 리턴 O, 원본 변화 X
const howManyStudentIncludeTae = classes.reduce((acc, student, index) => {
  if (student.name.includes('태')) {
    acc += 1;
  }
  return acc;
}, 0);
console.log(howManyStudentIncludeTae);
