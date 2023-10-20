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
  public void add_twoNumbers_shouldAdd1And2AndReturn3() {
    int result = calculator.add("1,2");
    assertEquals(result, 3);
  }

  @Test
  public void add_twoNumbers_shouldAdd3And4AndReturn7() {
    int result = calculator.add("3,4");
    assertEquals(result, 7);
  }

  @Test
  public void add_threeNumbers_shouldAddAndReturnResult() {
    int result = calculator.add("1,2,3");
    assertEquals(result, 6);
  }

  @Test
  public void add_fourNumbers_shouldAddAndReturnResult() {
    int result = calculator.add("1,2,3,4");
    assertEquals(result, 10);
  }

}
