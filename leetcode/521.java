package leetcode;

// 521
class Solution521 {
  public int findLUSlength(String a, String b) {
    if (a.length() != b.length()) {
      return Math.max(a.length(), b.length());
    }
    return a.equals(b) ? -1 : a.length();
  }

  public static void main(String[] args) {
    System.out.println(new Solution521().findLUSlength("aba", "cdc"));
  }
}