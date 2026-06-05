const calculator = {
  name: 'MyCalculator',
  number: [1, 2, 3],

  printNumberRegular() {
    this.number.forEach(function (n) {
      console.log(this.name + ': ' + n);
    });
  },

  printNumberRegularArrow() {
    this.number.forEach((n) => {
      console.log(this.name + ': ' + n);
    });
  },
};

calculator.printNumberRegular();
calculator.printNumberRegularArrow();
