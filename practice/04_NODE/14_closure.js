function makeCounter() {
  let count = 0;
  return ++count;
}

function makeCounterClosure() {
  let count = 0;
  return function () {
    return count++;
  };
}

const mc = makeCounter;
const mcc = makeCounterClosure();

console.log(mc());
console.log(mc());
console.log(mc());

console.log(mcc());
console.log(mcc());
console.log(mcc());
