package leetcode;
import java.util.Stack;

// 栈 或 数组+指针
class Solution682 {
  public int calPoints(String[] operations) {

    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < operations.length; i++) {
      switch (operations[i]) {
        case "+":
          int top = stack.pop();
          int val = stack.peek() + top;
          stack.push(top);
          stack.push(val);
          break;
        case "D":
          stack.push(2 * stack.peek());
          break;
        case "C":
          stack.pop();
          break;

        default:
          stack.push(Integer.parseInt(operations[i]));
          break;
      }
    }

    int sum = 0;
    while (!stack.isEmpty()) {
      sum += stack.pop();
    }

    return sum;
  }

  public static void main(String[] args) {
    System.out.println(new Solution682().calPoints(new String[] { "5", "-2", "4", "C", "D", "9", "+", "+" }));
  }
}