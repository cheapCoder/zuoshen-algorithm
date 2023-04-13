package leetcode;

/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */

//  * Definition for singly-linked list.
class ListNode {
  int val;
  ListNode next;

  ListNode() {
  }

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}

class Solution {
  public ListNode rotateRight(ListNode head, int k) {
    if (head == null) {
      return head;
    }

    ListNode node = head;
    // 将尾节点连到头节点
    int count = 0;
    while (node != null) {
      count++;
      node = node.next;

    }

    k = k % count;

    if (k == 0) {
      return head;
    }
    node = head;
    ListNode[] cache = new ListNode[k + 1];
    int i = 0;
    while (node != null) {
      cache[i] = node;
      i = i == cache.length - 1 ? 0 : i + 1;
      if (node.next == null) {
        node.next = head;
        node = null;
      } else {
        node = node.next;
      }
    }
    head = cache[i].next;
    cache[i].next = null;

    return head;
  }
}

public class Solution61 {
  public static int[] stringToIntegerArray(String input) {
    input = input.trim();
    input = input.substring(1, input.length() - 1);
    if (input.length() == 0) {
      return new int[0];
    }

    String[] parts = input.split(",");
    int[] output = new int[parts.length];
    for (int index = 0; index < parts.length; index++) {
      String part = parts[index].trim();
      output[index] = Integer.parseInt(part);
    }
    return output;
  }

  public static ListNode stringToListNode(String input) {
    // Generate array from the input
    int[] nodeValues = stringToIntegerArray(input);

    // Now convert that list into linked list
    ListNode dummyRoot = new ListNode(0);
    ListNode ptr = dummyRoot;
    for (int item : nodeValues) {
      ptr.next = new ListNode(item);
      ptr = ptr.next;
    }
    return dummyRoot.next;
  }

  public static String listNodeToString(ListNode node) {
    if (node == null) {
      return "[]";
    }

    String result = "";
    while (node != null) {
      result += Integer.toString(node.val) + ", ";
      node = node.next;
    }
    return "[" + result.substring(0, result.length() - 2) + "]";
  }

  public static void main(String[] args) {
    ListNode head = stringToListNode("[1,2,3,4,5]");
    int k = 2;

    ListNode ret = new Solution().rotateRight(head, k);

    String out = listNodeToString(ret);

    System.out.print(out);
  }

}