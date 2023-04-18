package com.github.markboydcode.questions;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Calculates for any four digit number the number of iterations it takes to arrive at Kaprekar's constant of 6174 by
 * the following steps:
 *
 * 1) Take any four digit number (whose digits are not all identical).
 * 2) Rearrange the string of digits to form the largest and smallest 4-digit numbers possible.
 * 3) Take these two numbers and subtract the smaller number from the larger.
 * 4) Use the number you obtain and repeat the above process until you arrive at Kaprekar's constant of 6174.
 * 5) Return the count of the number of iterations it took to arrive at the constant.
 *
 * @author boydmr
 */
public class KaprekarsConstant {
  static final String TOO_LOW = "the value is less than or equal to zero";
  static final String TOO_HIGH = "the value is greater than 9999";
  static final String NO_VARIANCE = "the value contains digits that are all identical";

  private static final int THE_CONSTANT = 6174;

  int iterations = 0;

  public static void main(String args[]) {
      new KaprekarsConstant().calculateIterations(1004);
  }

  /**
   *
   * @param fourDigitNumber the number to be tested
   * @return the number of iterations to arrive at Kaprekar's constant.
   * @throws IllegalArgumentException with one of three messages: "the value is greater than 9999",
   *  "the value is less than or equal to zero", or "the value contains digits that are all identical".
   */
  public int calculateIterations(int fourDigitNumber) {
    if (fourDigitNumber <= 0) {
      throw new IllegalArgumentException(TOO_LOW);
    } else if (fourDigitNumber > 9999) {
      throw new IllegalArgumentException(TOO_HIGH);
    }

    if (THE_CONSTANT == fourDigitNumber) {
        return iterations; //we reached the magical number, no interations anymore
    }

    // your implementation here
    String[] smallest = String.valueOf(fourDigitNumber).split("(?!^)");
    orderMin(smallest);

    String smallestString = String.join("", smallest);

    String biggestString = String.valueOf(fourDigitNumber);
    if (String.valueOf(fourDigitNumber).length() < 4) {
        biggestString = "0" + biggestString;
    }
    String[] biggest = biggestString.split("(?!^)");
    orderMax(biggest);

    String biggestJoinedString = String.join("", biggest);

    iterations++;
    Integer reducedNumber = reduce(Integer.valueOf(biggestJoinedString), Integer.valueOf(smallestString));
    calculateIterations(reducedNumber);

    return iterations;
  }

  private void orderMin(String[] arrayToSort) {

    Arrays.sort(arrayToSort, Comparator.comparing(Integer::valueOf));

  }

  private void orderMax(String[] arrayToSort) {

    Arrays.sort(arrayToSort, (o1, o2) -> Integer.valueOf(o2).compareTo(Integer.valueOf(o1)));

  }

  private Integer reduce(Integer biggest, Integer smaller) {

    return biggest - smaller;

  }

  public void resetIterations() {
    iterations = 0;
  }

}
