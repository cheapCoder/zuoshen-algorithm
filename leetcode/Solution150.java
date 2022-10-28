package leetcode;

import java.util.Stack;

class Solution150 {
  public int evalRPN(String[] tokens) {
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < tokens.length; i++) {
      if (tokens[i].equals("+")) {
        stack.push(stack.pop() + stack.pop());
      } else if (tokens[i].equals("-")) {
        Integer mul = stack.pop();
        Integer muled = stack.pop();
        stack.push(muled - mul);
      } else if (tokens[i].equals("*")) {
        stack.push(stack.pop() * stack.pop());
      } else if (tokens[i].equals("/")) {
        Integer div = stack.pop();
        Integer divided = stack.pop();
        stack.push(divided / div);
      } else {
        stack.push(Integer.parseInt(tokens[i]));
      }
    }

    return stack.pop();
  }

  public static void main(String[] args) {
    System.out.println(new Solution150().evalRPN(new String[] { "2", "1", "+", "3", "*" }));
    System.out.println(new Solution150().evalRPN(new String[] { "4", "13", "5", "/", "+" }));
    // System.out.println("22\n\t2123".matches(".*?\n\t[^\t].*?"));
  }
}
// dir
// subdir1
// tsubdir2
// file.ext