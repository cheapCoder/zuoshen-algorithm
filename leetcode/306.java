package leetcode;

class Solution306 {
  public boolean isAdditiveNumber(String num) {
    // 至少三个数
    if (num.length() < 3) {
      return false;
    }

    String left;
    String right;

    for (int i = 0; i < num.length() / 2; i++) {
      left = num.substring(0, i + 1);
      if (left.charAt(0) == '0' && left.length() > 1) {
        break;
      }
      for (int j = i + 1; j < num.length() - 1; j++) {
        if (i + 1 > num.length() - j - 1 || j - i > num.length() - j - 1) {
          break;
        }
        right = num.substring(i + 1, j + 1);
        if (right.charAt(0) == '0' && right.length() > 1) {
          break;
        }
        if (innerIsAdditive(left, right, num.substring(j + 1))) {
          return true;
        }
      }
    }

    return false;
  }

  private boolean innerIsAdditive(String pre, String cur, String last) {
    while (last.length() != 0) {
      String tem = addStrings(pre, cur);
      if (!last.startsWith(tem)) {
        return false;
      }
      pre = cur;
      cur = tem;
      last = last.substring(tem.length());
    }

    return true;
  }

  private String addStrings(String a, String b) {

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

}