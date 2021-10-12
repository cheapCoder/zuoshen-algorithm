package A3medium.class07;

// 求完全二叉树节点的个数
public class C05_CompleteTreeNodeNumber {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// 时间复杂度优化
	// public static int nodeNum(Node head) {
	// if (head == null) {
	// return 0;
	// }
	// return bs(head, 1, mostLeftLevel(head, 1));
	// }

	// public static int bs(Node node, int l, int h) {
	// if (l == h) {
	// return 1;
	// }
	// if (mostLeftLevel(node.right, l + 1) == h) {
	// return (1 << (h - l)) + bs(node.right, l + 1, h);
	// } else {
	// return (1 << (h - l - 1)) + bs(node.left, l + 1, h);
	// }
	// }

	// public static int mostLeftLevel(Node node, int level) {
	// while (node != null) {
	// level++;
	// node = node.left;
	// }
	// return level - 1;
	// }

	// 时间复杂度O(n)
	public static int nodeNum(Node head) {
		if (head == null) {
			return 0;
		}
		return nodeNum(head.left) + 1 + nodeNum(head.right);
	}

	// 时间复杂度优化
	public static int nodeNum2(Node head) {
		if (head == null) {
			return 0;
		}

		return process(head, getMostLeftHeight(head));
	}

	private static int process(Node head, int height) {
		if (head == null) {
			return 0;
		}

		int count;
		if (getMostLeftHeight(head.right) == height - 1) { // 左子树为满二叉树
			count = 1 + (int) Math.pow(2, height - 1) - 1 + process(head.right, height - 1);
		} else { // 右子树为满二叉树
			count = 1 + (int) Math.pow(2, height - 2) - 1 + process(head.left, height - 1);
		}
		return count;
	}

	private static int getMostLeftHeight(Node head) {
		int height = 0;
		while (head != null) {
			height++;
			head = head.left;
		}
		return height;
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);
		head.left.left.left = new Node(8);
		System.out.println(nodeNum(head));
		System.out.println(nodeNum2(head));
	}

}
