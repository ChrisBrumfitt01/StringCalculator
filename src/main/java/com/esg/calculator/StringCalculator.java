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
    List<String> delimiters = new ArrayList<>();

    if(!numbers.startsWith("//")) {

      delimiters.add(",");
      return numbers.split(getRegex(delimiters));

    } else {

      String delimiter = "";
      loop:
      for (char c : numbers.substring(2).toCharArray()) {
        switch (c) {
          case '\n':
            if(!delimiter.equals("")){
              delimiters.add(delimiter);
            }
            break loop;

          case '[':
            continue;

          case ']':
            delimiters.add(delimiter);
            delimiter = "";
            continue;

          default:
            delimiter += getEscapedCharacter(c);
            break;
        }
      }

      numbers = numbers.substring(numbers.indexOf("\n")+1);
      return numbers.split(getRegex(delimiters));
    }
  }

  private String getEscapedCharacter(char c) {
    if(SPECIAL_CHARACTERS.contains(c)){
      return "\\" + c;
    }
    return Character.toString(c);
  }

  private String getRegex(List<String> delimiters) {
    delimiters.add("\n");
    return String.join("|", delimiters);
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
