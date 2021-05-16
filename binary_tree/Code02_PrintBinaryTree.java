package binary_tree;

public class Code02_PrintBinaryTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		// Node head = new Node(1);
		// head.left = new Node(-222222222);
		// head.right = new Node(3);
		// head.left.left = new Node(Integer.MIN_VALUE);
		// head.right.left = new Node(55555555);
		// head.right.right = new Node(66);
		// head.left.left.right = new Node(777);
		// printTree(head);

		// head = new Node(1);
		// head.left = new Node(2);
		// head.right = new Node(3);
		// head.left.left = new Node(4);
		// head.right.left = new Node(5);
		// head.right.right = new Node(6);
		// head.left.left.right = new Node(7);
		// printTree(head);

		// head = new Node(1);
		// head.left = new Node(1);
		// head.right = new Node(1);
		// head.left.left = new Node(1);
		// head.right.left = new Node(1);
		// head.right.right = new Node(1);
		// head.left.left.right = new Node(1);
		// printTree(head);

		// Node head = new Node(5);
		// head.left = new Node(3);
		// head.right = new Node(8);
		// head.left.left = new Node(2);
		// head.left.right = new Node(4);
		// head.left.left.left = new Node(1);
		// head.right.left = new Node(7);
		// head.right.left.left = new Node(6);
		// head.right.right = new Node(10);
		// head.right.right.left = new Node(9);
		// head.right.right.right = new Node(11);
		// printTree(head);

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
		printTree(head);

	}

}
