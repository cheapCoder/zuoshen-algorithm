package leetcode;

import java.util.LinkedList;

// 减法不满足结合律
class Solution227 {
  public int calculate(String s) {
    int num = 0;
    char cur;
    LinkedList<Integer> numStack = new LinkedList<>();
    LinkedList<Character> acStack = new LinkedList<>();

    for (int i = 0; i < s.length(); i++) {
      cur = s.charAt(i);
      switch (cur) {
        case '+':
        case '-':
          acStack.addLast(cur);
          numStack.addLast(num);
          num = 0;
          break;
        case '/':
        case '*':
          var conf = getNextInt(s, i + 1);
          num = (cur == '/' ? num / conf[0] : num * conf[0]);
          i = conf[1] - 1;
          break;
        case ' ':
          break;
        default:
          num = num * 10 + (cur - '0');
          break;
      }
    }
    numStack.addLast(num);
    while (!acStack.isEmpty()) {
      int first = numStack.removeFirst();
      int last = numStack.removeFirst();
      switch (acStack.removeFirst()) {
        case '+':
          numStack.addFirst(first + last);
          break;
        case '-':
          numStack.addFirst(first - last);
          break;
        default:
          break;
      }
    }
    return numStack.pop();
  }

  private int[] getNextInt(String str, int i) {

    // ignore left whitespace
    while (str.charAt(i) == ' ') {
      i++;
    }

    int res = 0;
    int j = i;
    char c;
    for (; j < str.length(); j++) {
      c = str.charAt(j);
      if (c < '0' || c > '9') {
        break;
      }
      res = res * 10 + (c - '0');
    }

    return new int[] { res, j };
  }

  public static void main(String[] args) {
    // System.out.println(new Solution227().calculate(" 3+5 / 2 "));
    System.out.println(new Solution227().calculate(" 123 + 13 / 3 * 2 + 24 / 4 + 2 - 12 - 12"));
  }
}
// dir
// subdir1
// tsubdir2
// file.ext