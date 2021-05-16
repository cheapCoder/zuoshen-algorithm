package binary_tree;

public class Code11_isFullBinaryTree {

  public static class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
      this.value = data;
    }
  }

  private static class ReturnType {
    int height;
    int nodeCount;

    public ReturnType(int height, int nodeCount) {
      this.height = height;
      this.nodeCount = nodeCount;
    }

  }

  public static boolean isFullBinaryTree(Node head) {   // 满二叉树
    ReturnType info = process(head);
    System.out.println(info.height + "   " + info.nodeCount);
    return info.nodeCount == Math.pow(2, info.height) - 1;

  }

  private static ReturnType process(Node head) {
    if (head == null) {
      return new ReturnType(0, 0);
    }

    ReturnType left = process(head.left);
    ReturnType right = process(head.right);

    return new ReturnType(Math.max(left.height, right.height) + 1, left.nodeCount + right.nodeCount + 1);
  }

  public static void main(String[] args) {
    Node head = new Node(5);
    head.left = new Node(3);
    head.right = new Node(8);
    head.left.left = new Node(2);
    head.left.right = new Node(4);
    head.left.right.left = new Node(12);
    head.left.right.right = new Node(13);
    head.left.left.left = new Node(1);
    head.left.left.right = new Node(10);
    head.right.left = new Node(7);
    head.right.left.left = new Node(6);
    head.right.left.right = new Node(15);
    head.right.right = new Node(10);
    head.right.right.left = new Node(9);
    head.right.right.right = new Node(11);

    System.out.println(isFullBinaryTree(head));
  }
}
