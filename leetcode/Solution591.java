package leetcode;

import java.util.Stack;

enum NODE_TYPE {
  START,
  END,
  ORIGIN,
}

class Node {
  public NODE_TYPE type;
  public int start;
  public int end;

  public Node(int start, int end, NODE_TYPE type) {
    this.start = start;
    this.end = end;
    this.type = type;
  }

}

public class Solution591 {

  public boolean isValid(String code) {
    boolean hasRoot = false;
    Stack<Node> stack = new Stack<>();
    Node node = null;

    int i = 0;
    while (i < code.length()) {
      if (code.charAt(i) == '<') {
        node = getNext(code, i);
        if (node == null) {
          return false;
        }

        if (stack.isEmpty()) {
          if (node.type != NODE_TYPE.START) {
            return false;
          }
          // need single root
          if (hasRoot == true) {
            return false;
          }

          // need start with tag
          if (node.start != 0) {
            return false;
          }
          hasRoot = true;
        }

        if (node.type == NODE_TYPE.START) {
          stack.push(node);
        } else if (node.type == NODE_TYPE.END) {
          Node top = stack.peek();
          if (code.substring(top.start + 1, top.end).equals(code.substring(node.start + 2, node.end))) {
            stack.pop();
          } else {
            return false;
          }
        }
        i = node.end + 1;
      } else {
        i++;
      }

    }

    if (node != null && (node.type != NODE_TYPE.END || node.end != code.length() - 1)) {
      return false;
    }
    return stack.isEmpty() && hasRoot;
  }

  private Node getNext(String str, int start) {
    if (str.startsWith("<![CDATA[", start)) {
      // cdata tag
      int index = str.indexOf("]]>", start + 9);
      if (index == -1) {
        return null;
      } else {
        return new Node(start, index + 2, NODE_TYPE.ORIGIN);
      }
    } else if (str.startsWith("</", start)) {
      // end tag
      int index = str.indexOf(">", start + 2);
      if (index == -1) {
        return null;
      } else {
        return isTagName(str, start + 2, index - 1) ? new Node(start, index, NODE_TYPE.END) : null;
      }
    } else {
      // start tag
      int index = str.indexOf(">", start + 1);
      if (index == -1) {
        return null;
      } else {
        return isTagName(str, start + 1, index - 1) ? new Node(start, index, NODE_TYPE.START) : null;
      }
    }
  }

  private boolean isTagName(String str, int start, int end) {
    if (end - start + 1 < 1 || end - start + 1 > 9) {
      return false;
    }
    for (int i = start; i <= end; i++) {
      if (str.charAt(i) > 'Z' || str.charAt(i) < 'A') {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    // // true
    // System.out.println(new Solution591().isValid("<DIV>This is the first line
    // <![CDATA[<div>]]></DIV>"));
    // // true
    // System.out.println(new Solution591().isValid("<DIV>>> ![cdata[]]
    // <![CDATA[<div>]>]]>]]>>]</DIV>"));
    // // false
    // System.out.println(
    // new Solution591()
    // .isValid("<DIV> unmatched tags with invalid tag name </1234567890> and
    // <CDATA[[]]> </DIV>"));
    // // true
    // System.out.println(
    // new Solution591().isValid("<DIV>>>
    // ![cdata[]]<![CDATA[<div>]>]]>]]>>]</DIV>"));
    // // false
    // System.out.println(new Solution591().isValid("<![CDATA[wahaha]]]>"));
    // false
    // System.out.println(new
    // Solution591().isValid("<TRUe><![CDATA[123123]]]]><![CDATA[>123123]]></TRUe>"));
    // false
    // System.out.println(new Solution591().isValid("123456"));
    // false
    System.out.println(new Solution591().isValid("<A></A>>"));
    System.out.println(new Solution591().isValid("!!!<A>123</A>"));

    // System.out.println('V'> 'Z' || 'V' < 'A');
  }

}
