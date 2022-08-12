public class Solution {
  public int repeatedStringMatch(String a, String b) {
    if (b.length() == 0) {
      return 0;
    }
    if (a.length() == 0) {
      return -1;
    }

    int loop = 0;
    int leftP = 0;
    int rightP = 0;

    // int preStart = 0;
    while (rightP < b.length()) {
      if (leftP == 0) {
        loop++;
        if (loop > 1 && rightP == 0) {
          return -1;
        }
      }

      System.out.println(leftP);
      if (a.charAt(leftP) == b.charAt(rightP)) {

        // leftP++;
        leftP = leftP == a.length() - 1 ? 0 : leftP + 1;

        rightP++;
      } else {
        // if (loop <= 1) {
        // leftP = preStart + 1;
        leftP = leftP - (rightP) + 1;
        if (leftP < 0) {
          leftP = a.length() + (leftP % a.length());
          loop--;
        } else if (leftP >= a.length()) {
          return -1;
        }
        rightP = 0;
        // } else {
        // return -1;
        // }
      }

    }

    return loop;
  }

  public static void main(String[] args) {
    // System.out.println(new Solution().repeatedStringMatch("abc",
    // "abcabcabcabca"));
    // System.out.println(new Solution().repeatedStringMatch("abcd", "cdabcdab"));
    // System.out.println(new Solution().repeatedStringMatch("abc", "wxyz"));
    // System.out.println(new Solution().repeatedStringMatch("aabaa", "aaab")); // 2
    System.out.println(new Solution().repeatedStringMatch("abcd", "cdabcdacdabcda")); // 2

    // System.out.println(new Solution().repeatedStringMatch("aa", "a"));
  }
}