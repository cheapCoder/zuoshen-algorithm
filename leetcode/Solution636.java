package leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Solution636 {
  public int[] exclusiveTime(int n, List<String> logs) {
    int[] res = new int[n];

    Stack<int[]> stack = new Stack<>();

    stack.push(new int[] { Integer.parseInt(logs.get(0).split(":")[0]), 0 });

    for (int i = 0; i < logs.size(); i++) {
      String[] cur = logs.get(i).split(":");

      if ("start".equals(cur[1])) {
        int[] top = stack.peek();
        res[top[0]] += Integer.parseInt(cur[2]) - top[1];
        // top[1] = Integer.parseInt(cur[2]);
        stack.push(new int[] { Integer.parseInt(cur[0]), Integer.parseInt(cur[2]) });
      } else {
        int[] top = stack.pop();
        stack.peek()[1] = Integer.parseInt(cur[2]) + 1;
        res[top[0]] += Integer.parseInt(cur[2]) - top[1] + 1;
      }

    }

    return res;
  }

  public static void main(String[] args) {
    System.out
        .println(new Solution636().exclusiveTime(2, Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6")));
  }

}
