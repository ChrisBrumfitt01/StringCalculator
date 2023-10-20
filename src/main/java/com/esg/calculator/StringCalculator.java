package com.esg.calculator;

public class StringCalculator {

  public int add(String numbers) {
    if(numbers.equals("")){
      return 0;
    }

    String[] arr = numbers.split(",");
    int total = 0;
    for(String s : arr) {
      total += Integer.parseInt(s);
    }
    return total;
  }

}
