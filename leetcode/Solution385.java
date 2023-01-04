package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class NestedInteger {
  private List<NestedInteger> list;
  private Integer integer;

  public NestedInteger(List<NestedInteger> list) {
    this.list = list;
  }

  public void add(NestedInteger nestedInteger) {
    if (this.list != null) {
      this.list.add(nestedInteger);
    } else {
      this.list = new ArrayList();
      this.list.add(nestedInteger);
    }
  }

  public void setInteger(int num) {
    this.integer = num;
  }

  public NestedInteger(Integer integer) {
    this.integer = integer;
  }

  public NestedInteger() {
    this.list = new ArrayList();
  }

  public boolean isInteger() {
    return integer != null;
  }

  public Integer getInteger() {
    return integer;
  }

  public List<NestedInteger> getList() {
    return list;
  }

  public static String printNi(NestedInteger thisNi, StringBuilder sb) {
    if (thisNi.isInteger()) {
      sb.append(thisNi.integer);
      sb.append(",");
    }
    sb.append("[");
    for (NestedInteger ni : thisNi.list) {
      if (ni.isInteger()) {
        sb.append(ni.integer);
        sb.append(",");
      } else {
        printNi(ni, sb);
      }
    }
    sb.append("]");
    return sb.toString();
  }
}

// 方式
// 1. 栈
// 2. 递归
public class Solution385 {
  public NestedInteger deserialize(String s) {
    // 1 <= s.length <= 5 * 104
    boolean isNegative = false;
    Stack<NestedInteger> stack = new Stack<>();
    // NestedInteger header = new NestedInteger();
    // NestedInteger curNode = header;
    // stack.push(header);

    int num = 0;
    char c;

    for (int i = 0; i < s.length(); i++) {
      c = s.charAt(i);
      switch (c) {
        case '[': {
          NestedInteger tem = new NestedInteger();
          stack.push(tem);
          break;
        }
        case ']':
        case ',': {
          if (i - 1 >= 0 && s.charAt(i - 1) == ']') {
            NestedInteger tem = stack.pop();
            stack.peek().add(tem);
          } else {
            stack.peek().setInteger((isNegative ? -1 : 1) * num);
          }

          isNegative = false;
          num = 0;

          break;
        }
        case '-':
          isNegative = true;
          break;
        default:
          num = num * 10 + (c - '0');
          break;
      }
    }
    if (s.length() >= 1 && s.charAt(s.length() - 1) != ']') {
      if (stack.isEmpty()) {
        stack.push(new NestedInteger());
      }
      stack.peek().setInteger((isNegative ? -1 : 1) * num);
    }

    NestedInteger res = stack.pop();
    // System.out.println(header);
    return res;
  }

  public static void main(String[] args) {
    System.out.println(NestedInteger.printNi(new Solution385().deserialize("[123,[456,[789]]]"), new StringBuilder()));
    System.out.println(NestedInteger.printNi(new Solution385().deserialize("234"), new StringBuilder()));
  }
}