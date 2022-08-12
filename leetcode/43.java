package leetcode;

class Solution43 {
  public String multiply(String num1, String num2) {
    boolean isZero = true;
    for (int i = 0; i < num1.length(); i++) {
      if (num1.charAt(i) != '0') {
        isZero = false;
      }
    }
    if (isZero) {
      return "0";
    }
    isZero = true;
    for (int i = 0; i < num2.length(); i++) {
      if (num2.charAt(i) != '0') {
        isZero = false;
      }
    }
    if (isZero) {
      return "0";
    }

    String res = "0";
    for (int i = num2.length() - 1; i >= 0; i--) {
      String cur = mutStrings(num1, num2.charAt(i) - '0') + "0".repeat(num2.length() - i - 1);
      res = addStrings(res, cur);
    }

    return res;
  }

  public String mutStrings(String a, int b) {
    if (b == 0) {
      return "0";
    }

    char[] arr = new char[a.length()];
    int extra = 0;

    for (int i = arr.length - 1; i >= 0; i--) {
      int cha = (a.charAt(i) - '0') * b + extra;
      extra = cha / 10;
      arr[i] = (char) ((cha % 10) + '0');
    }

    return (extra > 0 ? extra : "") + String.valueOf(arr);
  }

  public String addStrings(String a, String b) {

    if (a.length() > b.length()) {
      b = "0".repeat(a.length() - b.length()) + b;
    } else {
      a = "0".repeat(b.length() - a.length()) + a;
    }

    char[] arr = new char[a.length()];
    boolean isAdd = false;

    for (int i = arr.length - 1; i >= 0; i--) {
      int cha = a.charAt(i) - '0' + b.charAt(i) - '0' + (isAdd ? 1 : 0);
      isAdd = cha > 9 ? true : false;
      arr[i] = (char) ((cha % 10) + '0');
    }

    return isAdd ? "1" + String.valueOf(arr) : String.valueOf(arr);
  }

  public static void main(String[] args) {
    System.out.println(new Solution43().multiply("0", "52"));
  }
}