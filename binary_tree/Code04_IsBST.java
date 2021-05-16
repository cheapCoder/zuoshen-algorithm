package binary_tree;

// import java.util.LinkedList;

public class Code04_IsBST {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// public static boolean isBST(Node head) {		// 二叉搜索树
	// if (head == null) {
	// return true;
	// }
	// LinkedList<Node> inOrderList = new LinkedList<>();
	// process(head, inOrderList);
	// int pre = Integer.MIN_VALUE;
	// for (Node cur : inOrderList) {
	// if (pre >= cur.value) {
	// return false;
	// }
	// pre = cur.value;
	// }
	// return true;
	// }

	// public static void process(Node node, LinkedList<Node> inOrderList) {
	// if (node == null) {
	// return;
	// }
	// process(node.left, inOrderList);
	// inOrderList.add(node);
	// process(node.right, inOrderList);
	// }

	// // 法一
	// public static boolean isBST(Node head) {
	// if (head == null) {
	// return true;
	// }
	// LinkedList<Node> tem = new LinkedList<>();
	// Node preNode = null;
	// inOrderTraversal(head, tem);
	// for (Node node : tem) {
	// if (preNode != null && preNode.value >= node.value) {
	// return false;
	// }
	// preNode = node;
	// }
	// return true;
	// }

	// private static void inOrderTraversal(Node head, LinkedList<Node> list) {
	// if (head == null) {
	// return;
	// }

	// inOrderTraversal(head.left, list);
	// list.add(head);
	// inOrderTraversal(head.right, list);
	// }


	// 法二
	public static boolean isBST(Node head) {
		return process(head).isBST;
	}

	private static ReturnType process(Node head) {
		if (head == null) {
			return null;
		}

		ReturnType left = process(head.left);
		ReturnType right = process(head.right);

		int max = head.value;
		int min = head.value;
		boolean isBST = true;

		if (left != null && (!left.isBST || left.max >= head.value)) {
			isBST = false;
		}
		if (right != null && (!right.isBST || right.min <= head.value)) {
			isBST = false;
		}

		if (left != null) {
			max = Math.max(left.max, max);
			min = Math.min(left.min, max);
		}
		if (right != null) {
			max = Math.max(right.max, max);
			min = Math.min(right.min, min);
		}

		return new ReturnType(max, min, isBST);
	}

	private static class ReturnType {
		int max;
		int min;
		boolean isBST;

		public ReturnType(int max, int min, boolean isBST) {
			this.max = max;
			this.min = min;
			this.isBST = isBST;
		}

	}

	public static void main(String[] args) {
		Node head = new Node(5);
		head.left = new Node(3);
		head.right = new Node(8);
		head.left.left = new Node(2);
		head.left.right = new Node(4);
		head.left.left.left = new Node(1);
		head.right.left = new Node(7);
		head.right.left.left = new Node(6);
		head.right.right = new Node(10);
		head.right.right.left = new Node(9);
		head.right.right.right = new Node(11);
		System.out.println(isBST(head));
	}

}
