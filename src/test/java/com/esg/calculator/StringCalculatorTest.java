package com.esg.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.esg.calculator.exception.InvalidInputException;
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

  @Test
  public void add_newLine_shouldAddWhenUsingNewLineOrCommaAsDelimiter() {
    int result = calculator.add("1\n2,3");
    assertEquals(result, 6);
  }

  @Test
  public void add_customDelimiter_shouldAddNumbersWhenCustomDelimiterIsProvided() {
    int result = calculator.add("//;\n1;2");
    assertEquals(result, 3);
  }

  @Test
  public void add_customDelimiter_shouldAddNumbersWhenExclamationDelimiterIsProvided() {
    int result = calculator.add("//!\n1!2!3");
    assertEquals(result, 6);
  }

  @Test
  public void add_withNegativeNumber_shouldThrowException() {
    Throwable thrown = assertThrows(InvalidInputException.class, () -> calculator.add("-1,2"));
    assertEquals("Negatives not allowed: -1", thrown.getMessage());
  }

  @Test
  public void add_withNegativeNumbers_shouldThrowExceptionWithExpectedMessage() {
    Throwable thrown = assertThrows(InvalidInputException.class, () -> calculator.add("2,-4,3,-5"));
    assertEquals("Negatives not allowed: -4,-5", thrown.getMessage());
  }

  @Test
  public void add_numbersGreaterThan1000_shouldBeIgnored() {
    int result = calculator.add("1001,2");
    assertEquals(result, 2);
  }

  @Test
  public void add_numbersNotGreaterThan1000_shouldBeIncluded() {
    int result = calculator.add("1000,2");
    assertEquals(result, 1002);
  }
}
