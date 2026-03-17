function outer() {
  var value = 1234;
  function inner() {
    console.log('value = ' + value);
  }
  inner();
}
outer();
