const x = 'global';

function outer() {
  const x = 'outer';

  function inner() {
    const x = 'inner';
    console.log(x);
  }

  function inner2() {
    console.log(x);
  }

  inner();
  inner2();
}

outer();
