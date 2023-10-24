package com.esg.calculator;

import com.esg.calculator.exception.InvalidInputException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

  public int add(String numbers) {
    if (numbers.equals("")){
      return 0;
    }

    String[] numbersArr = generateListOfNumbers(numbers);
    return doCalculation(numbersArr);
  }

  private String[] generateListOfNumbers(String numbers) {
    String delimiter = ",";

    if (numbers.startsWith("//")) {
      delimiter = "";
      for(char c : numbers.substring(2).toCharArray()){
        if (c == '\n' || c == ']'){
          break;
        }
        if (c == '[') {
          continue;
        }
        delimiter += c;
      }
      numbers = numbers.substring(numbers.indexOf("\n")+1);
    }

    return numbers.split(String.format("%s|\n", delimiter));
  }

  private int doCalculation(String[] numbers) {
    List<Integer> negativeNumbers = new ArrayList<>();
    int total = Arrays.stream(numbers)
        .mapToInt(Integer::parseInt)
        .peek(i -> {
          if (i < 0) {
            negativeNumbers.add(i);
          }
        })
        .filter(i -> i<=1000)
        .sum();

    if(!negativeNumbers.isEmpty()){
      String negativeNumbersStr = negativeNumbers.stream()
          .map(String::valueOf)
          .collect(Collectors.joining(","));
      throw new InvalidInputException(String.format("Negatives not allowed: %s", negativeNumbersStr));
    }
    return total;
  }

}
