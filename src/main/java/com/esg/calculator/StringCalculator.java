package com.esg.calculator;

public class StringCalculator {

  public int add(String numbers) {
    if(numbers.equals("")) {
      return 0;
    }

    if(numbers.length() == 1) {
      return Integer.parseInt(numbers);
    }

    return Integer.parseInt(String.valueOf(numbers.charAt(0))) +
        Integer.parseInt(String.valueOf(numbers.charAt(2)));
  }

}
