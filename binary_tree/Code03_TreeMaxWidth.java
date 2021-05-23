package binary_tree;

// import java.util.HashMap;
import java.util.LinkedList;

public class Code03_TreeMaxWidth {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// public static int getMaxWidth(Node head) {
	// if (head == null) {
	// return 0;
	// }
	// int maxWidth = 0;
	// int curWidth = 0;
	// int curLevel = 0;
	// HashMap<Node, Integer> levelMap = new HashMap<>();
	// LinkedList<Node> queue = new LinkedList<>();

	// levelMap.put(head, 1);
	// queue.add(head);
	// Node node = null;
	// Node left = null;
	// Node right = null;
	// while (!queue.isEmpty()) {
	// node = queue.poll();
	// left = node.left;
	// right = node.right;
	// if (left != null) {
	// levelMap.put(left, levelMap.get(node) + 1);
	// queue.add(left);
	// }
	// if (right != null) {
	// levelMap.put(right, levelMap.get(node) + 1);
	// queue.add(right);
	// }
	// if (levelMap.get(node) > curLevel) {
	// curWidth = 1;
	// curLevel = levelMap.get(node);
	// } else {
	// curWidth++;
	// }
	// maxWidth = Math.max(maxWidth, curWidth);
	// }
	// return maxWidth;
	// }

	// // 法一：用hashmap记录每个节点的层级
	// public static int getMaxWidth(Node head) {
	// if (head == null) {
	// return 0;
	// }

	// LinkedList<Node> tem = new LinkedList<>();
	// HashMap<Node, Integer> levelMap = new HashMap<>();
	// int curLevel = 1;
	// int curLevelCount = 0;

	// tem.add(head);
	// levelMap.put(head, 1);
	// int max = Integer.MIN_VALUE;
	// while (!tem.isEmpty()) {
	// head = tem.poll();

	// if (levelMap.get(head) > curLevel) {
	// max = Math.max(max, curLevelCount);
	// curLevelCount = 1;
	// curLevel++;
	// } else {
	// curLevelCount++;
	// }

	// if (head.left != null) { // 由于curLevel可能他会变化，所以判断左右树的部分要放到后面
	// tem.add(head.left);
	// levelMap.put(head.left, curLevel + 1);
	// }
	// if (head.right != null) {
	// tem.add(head.right);
	// levelMap.put(head.right, curLevel + 1);
	// }
	// }

	// return max;
	// }

	// 法二：用变量记录每层的终止节点
	public static int getMaxWidth(Node head) {
		if (head == null) {
			return 0;
		}

		LinkedList<Node> tem = new LinkedList<>();
		Node curLevelLast = head;
		Node nextLevelLast = null;
		int max = Integer.MIN_VALUE;
		int curLevelCount = 0;
		tem.add(head);
		while (!tem.isEmpty()) {
			head = tem.poll();
			if (head.left != null) {		// 这段要放上边，不然会导致curLevel变成null
				tem.add(head.left);
				nextLevelLast = head.left;
			}
			if (head.right != null) {
				tem.add(head.right);
				nextLevelLast = head.right;
			}

			if (head == curLevelLast) {
				max = Math.max(max, ++curLevelCount);
				curLevelCount = 0;
				curLevelLast = nextLevelLast;
				nextLevelLast = null;
			} else {
				curLevelCount++;
			}

		}

		return max;
	}

	public static void main(String[] args) {
		//  Auto-generated method stub
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
		System.out.println(getMaxWidth(head));
	}

}
