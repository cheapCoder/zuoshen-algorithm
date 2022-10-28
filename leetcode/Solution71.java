package leetcode;

import java.util.LinkedList;
import java.util.Stack;

class Solution71 {
  public String simplifyPath(String path) {
    if (path == null || path.length() == 0) {
      return null;
    }
    LinkedList<String> stack = new LinkedList<>();
    String[] arr = path.split("/");

    for (int i = 0; i < arr.length; i++) {
      if (arr[i].length() == 0) {
        continue;
      }

      if (arr[i].equals("..")) {
        if (!stack.isEmpty()) {
          stack.pop();
        }
      } else if (!arr[i].equals(".")) {
        stack.push(arr[i]);
      }
    }

    if (stack.isEmpty()) {
      return "/";
    }

    String res = "";
    while (!stack.isEmpty()) {
      res += "/" + stack.pollLast();
    }

    return res;
  }

  public String simplifyPath2(String path) {

    if (path == null || path.length() == 0) {
      return null;
    }

    Stack<String> stack = new Stack<>();

    String cur = "";
    for (int i = 0; i < path.length(); i++) {
      char c = path.charAt(i);
      if (c == '/') {
        if (cur.equals("..")) {
          if (!stack.isEmpty()) {
            stack.pop();
          }
        } else if (!cur.equals("") && !cur.equals(".") && !cur.equals("/")) {
          stack.push(cur);
        }
        cur = "";
      } else {
        cur += c;
      }
    }

    if (cur.equals("..")) {
      if (!stack.isEmpty()) {
        stack.pop();
      }
    } else if (!cur.equals("") && !cur.equals(".") && !cur.equals("/")) {
      stack.push(cur);
    }

    String res = "";
    while (!stack.isEmpty()) {
      res = stack.pop() + "/" + res;
    }

    return "/" + (res.equals("") ? "" : res.substring(0, res.length() - 1));
  }

  public static void main(String[] args) {
    System.out.println(new Solution71().simplifyPath("/a/./b/../../c/"));
    System.out.println(new Solution71().simplifyPath("/home//foo/"));
    System.out.println(new Solution71().simplifyPath("/../"));
  }
}