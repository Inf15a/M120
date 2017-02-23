package ch.lu.bbzw.calculator.utils;

public class NumeralSystemConverterForStrings {
  // nobody should make a object from that class
  private NumeralSystemConverterForStrings() {

  }

  public static String txtToBinary(String intToParse) {
    return Integer.toBinaryString(Integer.parseInt(intToParse));
  }

  public static String txtToDecimal(String intToParse) {
    return String.valueOf(Integer.parseInt(intToParse, 2));
  }
}
