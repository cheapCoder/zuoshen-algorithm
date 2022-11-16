package leetcode;

import java.util.LinkedList;

// 减法不满足结合律
class Solution224 {
  public int calculate(String s) {
    char[] arr = s.toCharArray();

    return process(arr, 0)[0];
  }

  private int[] process(char[] arr, int i) {
    LinkedList<Integer> stack = new LinkedList<>();
    int num = 0;
    char preAction = '+';

    while (true) {
      if (i == arr.length || arr[i] == ')') {

        switch (preAction) {
          case '+':
            stack.addLast(num);
            break;
          case '-':
            stack.addLast(-num);
            break;
          case '/':
            stack.addLast(stack.removeLast() / num);
            break;
          case '*':
            stack.addLast(stack.removeLast() * num);
            break;
        }

        // stack.addLast(num);
        break;
      } else if (arr[i] == ' ') {
      } else if (Character.isDigit(arr[i])) {
        num = num * 10 + arr[i] - '0';
      } else if (arr[i] == '(') {
        int[] next = process(arr, i + 1);
        num = next[0];
        i = next[1] - 1;
      } else {
        switch (preAction) {
          case '+':
            stack.addLast(num);
            break;
          case '-':
            stack.addLast(-num);
            break;
          case '/':
            stack.addLast(stack.removeLast() / num);
            break;
          case '*':
            stack.addLast(stack.removeLast() * num);
            break;
        }
        num = 0;
        preAction = arr[i];
      }

      i++;
    }

    return new int[] { sumFromStack(stack), i + 1 };
  }

  private int sumFromStack(LinkedList<Integer> stack) {
    int res = 0;
    while (!stack.isEmpty()) {
      res += stack.removeFirst();
    }
    return res;
  }

  // private int[] getNextInt(char[] arr, int start) {
  // int num = 0;
  // while (arr[start] == ' ') {
  // start++;
  // }
  // if (arr[start] == '(') {
  // return process(arr, start + 1);
  // }
  // while (start < arr.length) {
  // if (arr[start] < '0' || arr[start] > '9') {
  // break;
  // }
  // num = num * 10 + arr[start++] - '0';
  // }

  // return new int[] { num, start };
  // }

  public static void main(String[] args) {
    // System.out.println(new Solution227().calculate(" 3+5 / 2 "));
    // System.out.println(new Solution224().calculate(" 2-1 + 2 "));
    System.out.println(new Solution224().calculate("(1+(4+5+2)-3)+(6+8)"));
    // 23
  }
}
// dir
// subdir1
// tsubdir2
// file.ext