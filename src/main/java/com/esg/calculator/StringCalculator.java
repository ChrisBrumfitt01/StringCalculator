package com.esg.calculator;

import com.esg.calculator.exception.InvalidInputException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

  private List<Character> SPECIAL_CHARACTERS = List.of('.', '[', ']', '{', '}', '(', ')', '<', '>', '*',
      '+', '-', '=', '!', '?', '^', '$', '|');

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
        delimiter += getEscapedCharacter(c);
      }
      numbers = numbers.substring(numbers.indexOf("\n")+1);
    }

    return numbers.split(getRegex(delimiter));
  }

  private String getEscapedCharacter(char c) {
    if(SPECIAL_CHARACTERS.contains(c)){
      return "\\" + c;
    }
    return Character.toString(c);
  }

  private String getRegex(String delimiter) {
    return String.format("%s|\n", delimiter);
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
