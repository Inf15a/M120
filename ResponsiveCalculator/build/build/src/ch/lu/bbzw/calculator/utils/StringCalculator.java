package ch.lu.bbzw.calculator.utils;

public class StringCalculator {
  public String divide(String dividend, String divisor, boolean toInteger) {
    double output = 0;
    try {
      output = Double.parseDouble(dividend) / Double.parseDouble(divisor);
    } catch (NumberFormatException e) {
      // will return 0
    }
    if (toInteger) {
      return String.valueOf((int) output);
    }
    return String.valueOf(output);
  }

  public String multiply(String factor1, String factor2, boolean toInteger) {
    double output = 0;
    try {
      output = Double.parseDouble(factor1) * Double.parseDouble(factor2);
    } catch (NumberFormatException e) {
      // will return 0
    }
    if (toInteger) {
      return String.valueOf((int) output);
    }
    return String.valueOf(output);
  }

  public String subtract(String minuend, String subtrahend, boolean toInteger) {
    double output = 0;
    try {
      output = Double.parseDouble(minuend) - Double.parseDouble(subtrahend);
    } catch (NumberFormatException e) {
      // will return 0
    }
    if (toInteger) {
      return String.valueOf((int) output);
    }
    return String.valueOf(output);
  }

  public String summate(String summand1, String summand2, boolean toInteger) {
    double output = 0;
    try {
      output = Double.parseDouble(summand1) + Double.parseDouble(summand2);
    } catch (NumberFormatException e) {
      // will return 0
    }
    if (toInteger) {
      int outputInt = (int) output;
      return String.valueOf(outputInt);
    }
    return String.valueOf(output);
  }

}
