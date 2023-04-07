package leetcode;

import java.io.IOException;

// Definition for a Node.
class Node {
  public int val;
  public Node prev;
  public Node next;
  public Node child;

  public Node(int data) {
    this.val = data;
  }
};

class Solution_430 {
  public Node flatten(Node head) {
    if (head == null) {
      return null;
    }

    // 守卫确保第一个node没有child
    Node guide = new Node(-1);
    guide.next = head;
    internalFlatten(guide);

    return guide.next;
  }

  private Node internalFlatten(Node node) {
    do {
      node = node.next;

      System.out.println(node);
      if (node.child != null) {
        Node head = new Node(-1);
        head.next = node.child;
        Node end = internalFlatten(head);
        if (node.next != null) {
          end.next = node.next;
          node.next.prev = end;
        }
        node.next = node.child;
        node.child.prev = node;
        node.child = null;
        node = end;
      }
    } while (node.next != null);

    return node;
  }
}

public class Solution430 {
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

  public static Node stringToNode(String input) {
    // Generate array from the input
    int[] nodeValues = stringToIntegerArray(input);

    // Now convert that list into linked list
    Node dummyRoot = new Node(0);
    Node ptr = dummyRoot;
    for (int item : nodeValues) {
      ptr.next = new Node(item);
      ptr = ptr.next;
    }
    return dummyRoot.next;
  }

  public static String NodeToString(Node node) {
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

  public static void main(String[] args) throws IOException {
    // Node head =
    // stringToNode("[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]");

    // 1
    // Node head = new Node(1);
    // head.next = new Node(2);
    // head.next.prev = head;
    // head.next.next = new Node(3);
    // head.next.next.prev = head.next;
    // head.next.next.next = new Node(4);
    // head.next.next.next.prev = head.next.next;
    // head.next.next.next.next = new Node(5);
    // head.next.next.next.next.prev = head.next.next.next;
    // head.next.next.next.next.next = new Node(6);
    // head.next.next.next.next.next.prev = head.next.next.next.next;

    // Node level2 = new Node(7);
    // level2.next = new Node(8);
    // level2.next.prev = level2;
    // level2.next.next = new Node(9);
    // level2.next.next.prev = level2.next;
    // level2.next.next.next = new Node(10);
    // level2.next.next.next.prev = level2.next.next;

    // Node level3 = new Node(11);
    // level3.next = new Node(12);
    // level3.next.prev = level3;

    // level2.next.child = level3;
    // head.next.next.child = level2;

    // // test2
    // Node head = new Node(1);
    // head.child = new Node(3);
    // head.next = new Node(2);

    // test 3
    Node head = new Node(1);
    head.child = new Node(2);
    head.child.child = new Node(3);

    Node ret = new Solution_430().flatten(head);

    String out = NodeToString(ret);

    System.out.print(out);
  }
}