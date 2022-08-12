package leetcode;

class Solution482 {
  public String licenseKeyFormatting(String s, int k) {
    String res = "";
    int count = 1;
    s = s.replaceAll("-", "");
    for (int i = s.length() - 1; i >= 0; i--) {
      if (count % k == 0 && i != 0) {
        res = "-" + (char) (s.charAt(i) > 96 ? (int) (s.charAt(i)) - 32 : s.charAt(i)) + res;
      } else {
        res = (char) (s.charAt(i) > 96 ? (int) (s.charAt(i)) - 32 : s.charAt(i)) + res;

      }
      count++;
    }

    return res;
  }

  public static void main(String[] args) {
    System.out.println(new Solution482().licenseKeyFormatting("--a-a-a-a--", 2));
  }
}