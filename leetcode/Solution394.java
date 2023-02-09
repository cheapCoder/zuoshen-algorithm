package leetcode;

import java.util.Stack;

// 1. 递归
// 2. 用栈模拟递归
class Solution394 {
  public String decodeString(String what) {
    StringBuilder sb = new StringBuilder(what);
    Stack<String> stack = new Stack<>();

    boolean isNum = sb.charAt(0) <= '9' && sb.charAt(0) >= '0';
    String cur = "";
    for (int i = 0; i < sb.length(); i++) {
      char c = sb.charAt(i);
      if (c == '[') {
        stack.push(cur);
        cur = "";
      } else if (c == ']') {
        String pop;
        while (true) {
          pop = stack.pop();

          if (pop.charAt(0) >= '0' && pop.charAt(0) <= '9') {
            break;
          } else {
            cur = pop + cur;
          }
        }
        stack.push(cur.repeat(Integer.parseInt(pop)));

        cur = "";
      } else {
        if (isNum != (c <= '9' && c >= '0')) {
          isNum = !isNum;
          if (cur.length() > 0) {
            stack.push(cur);
            cur = "";
          }
        }

        cur += c;
      }
    }
    stack.push(cur);
    

    String res = "";
    while (!stack.isEmpty()) {
      res = stack.pop() + res;
    }

    return res;
  }

  public static void main(String[] args) {
    System.out.println(new Solution394().decodeString("3[a]2[bc]"));
    System.out.println(new Solution394().decodeString("3[a2[c]]"));
    System.out.println(new Solution394().decodeString("2[abc]3[cd]ef"));
    System.out.println(new Solution394().decodeString("abc3[cd]xyz"));
  }
}