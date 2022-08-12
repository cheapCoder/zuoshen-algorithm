package leetcode;

class Solution {
  public String convert(String s, int numRows) {
    if (s.length() <= 1 || numRows == 1) {
      return s;
    }
    StringBuilder res = new StringBuilder();
    int loop = 2 * numRows - 2;
    for (int i = 0; i < numRows; i++) {

      for (int j = 0; j < s.length(); j++) {
        if ((j + i) % loop == 0 || j % loop == i) {
          res.append(s.charAt(j));
        }
      }
    }

    return res.toString();

  }

}
