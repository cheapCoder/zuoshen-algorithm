package binary_tree;

public class Code06_IsBalancedTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// public static boolean isBalanced(Node head) {
	// return process(head).isBalanced;
	// }

	// public static class ReturnType {
	// public boolean isBalanced;
	// public int height;

	// public ReturnType(boolean isB, int hei) {
	// isBalanced = isB;
	// height = hei;
	// }
	// }

	// public static ReturnType process(Node x) {
	// if (x == null) {
	// return new ReturnType(true, 0);
	// }
	// ReturnType leftData = process(x.left);
	// ReturnType rightData = process(x.right);
	// int height = Math.max(leftData.height, rightData.height);
	// boolean isBalanced = leftData.isBalanced && rightData.isBalanced
	// && Math.abs(leftData.height - rightData.height) < 2;
	// return new ReturnType(isBalanced, height);
	// }

	private static class ReturnType {
		int height;
		boolean isBalanced;

		public ReturnType(int height, boolean isBalanced) {
			this.height = height;
			this.isBalanced = isBalanced;
		}
	}

	public static ReturnType isBalancedTree(Node head) {
		if (head == null) {
			return new ReturnType(0, true);
		}

		ReturnType leftReturn = isBalancedTree(head.left);
		ReturnType rightReturn = isBalancedTree(head.right);

		if (leftReturn.isBalanced && rightReturn.isBalanced && (Math.abs(leftReturn.height - rightReturn.height) <= 1)) {
			return new ReturnType(Math.max(leftReturn.height, rightReturn.height) + 1, true);
		}

		return new ReturnType(0, false);
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
		// head.right.right.right = new Node(11);
		System.out.println(isBalancedTree(head).isBalanced);
	}
}
