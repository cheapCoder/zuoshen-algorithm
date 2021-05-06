package class05;

import java.util.HashMap;
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

	public static int getMaxWidth(Node head) {
		if (head == null) {
			return 0;
		}
		int maxWidth = 0;
		int curWidth = 0;
		int curLevel = 0;
		HashMap<Node, Integer> levelMap = new HashMap<>();
		levelMap.put(head, 1);
		LinkedList<Node> queue = new LinkedList<>();
		queue.add(head);
		Node node = null;
		Node left = null;
		Node right = null;
		while (!queue.isEmpty()) {
			node = queue.poll();
			left = node.left;
			right = node.right;
			if (left != null) {
				levelMap.put(left, levelMap.get(node) + 1);
				queue.add(left);
			}
			if (right != null) {
				levelMap.put(right, levelMap.get(node) + 1);
				queue.add(right);
			}
			if (levelMap.get(node) > curLevel) {
				curWidth = 0;
				curLevel = levelMap.get(node);
			} else {
				curWidth++;
			}
			maxWidth = Math.max(maxWidth, curWidth);
		}
		return maxWidth;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
