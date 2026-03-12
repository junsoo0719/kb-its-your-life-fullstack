const add = function (a, b) {
  return a + b;
};

const add2 = (a, b) => {
  return a + b;
};

// 함수에 return만 존재하고 return 뒤에 값이 오는 경우는 이와 같이 축약 가능
const add3 = (a, b) => a + b;

const toUpperCase = function (str) {
  return str.toUpperCase();
};

const toUpperCase2 = (str) => {
  return str.toUpperCase();
};

const toUpperCase3 = (str) => str.toUpperCase();

const sumArray = function (arr) {
  let sum = 0;
  for (let i = 0; i < arr.length; i++) {
    sum += arr[i];
  }
  return sum;
};

const sumArray2 = (arr) => {
  let sum = 0;
  for (let i = 0; i < arr.length; i++) {
    sum += arr[i];
  }
  return sum;
};
