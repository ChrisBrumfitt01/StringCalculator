package com.esg.calculator;

public class StringCalculator {

  public int add(String numbers) {
    if (numbers.equals("")){
      return 0;
    }

    String delimiter = ",";
    if (numbers.startsWith("//")) {
      delimiter = String.valueOf(numbers.charAt(2));
      numbers = numbers.substring(4);
    }

    String[] arr = numbers.split(String.format("[%s\n]", delimiter));
    int total = 0;
    for(String s : arr) {
      total += Integer.parseInt(s);
    }
    return total;
  }

}
