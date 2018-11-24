public class RecursionLibrary {

  public static int greatestCommonDivisor(int x, int y) {
    assert (x >= 0 && y >= 0) :
        "greatestCommonDivisor only takes non-negative integers!";

    int gcd;
    if (y != 0) {
      gcd = (greatestCommonDivisor(y, x % y));
    } else {
      gcd = x;
    }
    return gcd;
  }

  public static boolean isPrime(int n) {
    assert (n >= 0) :
        "isPrime only takes a non-negative integer!";
    int rootN = (int) Math.ceil(Math.sqrt(n));
    boolean prime = true;
    if (n == 2) {
      prime = true;
    } else {
      prime = (checkPrimeHelper(n, rootN));
    }
    return prime;
  }

  public static boolean checkPrimeHelper(int n, int curDivisor) {
    boolean checkPrime;
    if (curDivisor == 1) {
      checkPrime = true;
    } else if (n % curDivisor == 0) {
      checkPrime = false;
    } else {
      checkPrime = checkPrimeHelper(n, curDivisor - 1);
    }
    return checkPrime;
  }

  //Extension Method:
  public static boolean isPrime2(int n) {
    assert (n >= 0) :
        "isPrime2 only takes a non-negative integer!";

    boolean prime = false;
    double rootN = Math.sqrt(n);
    for (int i = 2; i <= rootN; i++) {
      if (n % i == 0) {
        prime = false;
        break;
      }
    }
    return prime;
  }

  public static int sumSquareDigits(int n) {
    assert (n >= 0) :
        "sumSquareDigits only takes a non-negative integer!";

    int sum = 0;
    if (n > 0) {
      sum += Math.pow(n % 10, 2) + sumSquareDigits(n / 10);
    }
    return sum;
  }

  //Extension Method:
  public static int sumSquareDigits2(int n) {
    assert (n >= 0) :
        "sumSquareDigits2 only takes a non-negative integer!";

    int sum = 0;
    while (n > 0) {
      sum += Math.pow(n % 10, 2);
      n /= 10;
    }
    return sum;
  }


  public static boolean isHappy(int n) {
    assert (n >= 0) :
        "isHappy only takes a non-negative integer!";

    return isHappyHelper(sumSquareDigits(n), n, 0, 1);
  }

  public static boolean isHappyHelper(int n, int r, int t, int nt) {
    boolean happy = false;
    if (n == 1) {
      happy = true;
    } else if (n == r) {
      happy = false;
    } else if (t > 0) {
      happy = isHappyHelper(sumSquareDigits(n), r, (t - 1), nt);
    } else if (t == 0) {
      int nextN = sumSquareDigits(n);
      happy = isHappyHelper(nextN, n, nt, (nt + 1));
    }
    return happy;
  }

  //Extension Method:
  public static void printHappyNumbers(int n) {
    assert (n >= 0) :
        "printHappyNumbers only takes a non-negative integer!";

    int happyNumCount = 0;
    int curNumCounter = 1;
    while (happyNumCount != n) {
      if (isHappy(curNumCounter)) {
        System.out.println(curNumCounter);
        happyNumCount++;
      }
      curNumCounter++;
    }
  }

}
