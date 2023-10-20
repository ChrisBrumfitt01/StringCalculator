package com.esg.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

  private StringCalculator calculator = new StringCalculator();

  @Test
  public void add_emptyString_shouldReturn0(){
    int result = calculator.add("");
    assertEquals(result, 0);
  }

  @Test
  public void add_singleNumber_shouldReturnThatNumber(){
    int result = calculator.add("1");
    assertEquals(result, 1);
  }

  @Test
  public void add_twoNumbers_shouldAddTheNumbers() {
    int result = calculator.add("1,2");
    assertEquals(result, 3);
  }

}
