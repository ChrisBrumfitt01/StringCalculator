package com.esg.calculator;

import com.esg.calculator.exception.InvalidInputException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    List<Integer> negativeNumbers = new ArrayList<>();
    for(String s : arr) {
      int currentNumber = Integer.parseInt(s);
      if(currentNumber < 0) {
        negativeNumbers.add(currentNumber);
      }
      total += currentNumber;
    }

    if(!negativeNumbers.isEmpty()){
      String negativeNumbersStr = negativeNumbers.stream()
          .map(String::valueOf)
          .collect(Collectors.joining(","));
      throw new InvalidInputException(String.format("Negatives not allowed: %s", negativeNumbersStr));
    }
    return total;
  }

}
