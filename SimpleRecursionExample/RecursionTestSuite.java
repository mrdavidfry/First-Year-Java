public class RecursionTestSuite {

  public static void main(String[] args) {
    System.out.println("Running tests...");

    gcdTests();
    isPrimeTests();
    //isPrimeTests2();
    sumSquareDigitsTests();
    //sumSquareDigitsTests2();
    isHappyTests();

    System.out.println("...tests complete.");
  }

  public static void gcdTests() {
    checkGCD(12, 16, 4);
    checkGCD(1, 16, 1);
    checkGCD(7, 15, 1);
    checkGCD(2147483647, 2147483646, 1); //Test with upper limit for ints
  }

  public static void isPrimeTests() {
    checkIsPrime(9, false);
    checkIsPrime(2, true);
    checkIsPrime(7, true);
    checkIsPrime(231, false);
    checkIsPrime(2147483646, false);
    //checkIsPrime(2147483647, true); Stack Overflow error occurs at max!
  }

  public static void isPrimeTests2() {
    checkIsPrime2(9, false);
    checkIsPrime2(2, true);
    checkIsPrime2(7, true);
    checkIsPrime2(231, false);
    checkIsPrime2(2147483647, true);
  }

  public static void sumSquareDigitsTests() {
    checkSumSquareDigits(10, 1);
    checkSumSquareDigits(103, 10);
    checkSumSquareDigits(0, 0);
    checkSumSquareDigits(12001, 6);
    checkSumSquareDigits(2147483647, 260);
  }

  public static void sumSquareDigitsTests2() {
    checkSumSquareDigits2(10, 1);
    checkSumSquareDigits2(103, 10);
    checkSumSquareDigits2(0, 0);
    checkSumSquareDigits2(12001, 6);
    checkSumSquareDigits2(2147483647, 260);
  }

  public static void isHappyTests() {
    checkIsHappy(397, true);
    checkIsHappy(1, true);
    checkIsHappy(0, false);
    checkIsHappy(7, true);
    checkIsHappy(123, false);
    checkIsHappy(2147483647, false);
  }


  private static void checkGCD(int x, int y, int expected) {
    int actual = RecursionLibrary.greatestCommonDivisor(x, y);
    if (actual != expected) {
      System.out.println("greatestCommonDivisor(" + x + ", " + y + "); expected: "
          + expected + ", got: " + actual);
    }
  }


  private static void checkIsPrime(int value, boolean expected) {
    boolean actual = RecursionLibrary.isPrime(value);
    if (actual != expected) {
      System.out.println("isPrime(" + value + "); expected: " + expected
          + ", got: " + actual);
    }
  }

  private static void checkIsPrime2(int value, boolean expected) {
    boolean actual = RecursionLibrary.isPrime2(value);
    if (actual != expected) {
      System.out.println("isPrime2(" + value + "); expected: " + expected
              + ", got: " + actual);
    }
  }

  private static void checkSumSquareDigits(int n, int expected) {
    int actual = RecursionLibrary.sumSquareDigits(n);
    if (actual != expected) {
      System.out.println("sumSquareDigits(" + n + "); expected: "
          + expected + ", got: " + actual);
    }
  }

  private static void checkSumSquareDigits2(int n, int expected) {
    int actual = RecursionLibrary.sumSquareDigits2(n);
    if (actual != expected) {
      System.out.println("sumSquareDigits2(" + n + "); expected: "
              + expected + ", got: " + actual);
    }
  }

  private static void checkIsHappy(int n, boolean expected) {
    boolean actual = RecursionLibrary.isHappy(n);
    if (actual != expected) {
      System.out.println("isHappy(" + n + "); expected: " + expected
          + ", got: " + actual);
    }
  }

}
