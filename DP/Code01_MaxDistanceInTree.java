package DP;

public class Code01_MaxDistanceInTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static class ReturnType {
		public int maxDistance;
		public int h;

		public ReturnType(int m, int h) {
			this.maxDistance = m;
			this.h = h;
		}
	}

	// 法一：暴力递归
	private static int maxDistance(Node head1) {
		if (head1 == null) {
			return 0;
		}

		return process(head1).maxDistance;
	}

	private static ReturnType process(Node head) {
		if (head == null) {
			return new ReturnType(0, 0);
		}
		ReturnType left = process(head.left);
		ReturnType right = process(head.right);

		// 经过父节点
		int maxDisOverFa = left.h + right.h + 1;
		// 不经过父节点
		int maxDisNotOverFa = Math.max(left.maxDistance, right.maxDistance);

		return new ReturnType(Math.max(maxDisOverFa, maxDisNotOverFa), Math.max(left.h, right.h) + 1);
	}


	// NOTE: answer

	// public static int maxDistance(Node head) {
	// int[] record = new int[1];
	// return posOrder(head, record);
	// }

	// public static ReturnType process(Node head) {
	// 	if (head == null) {
	// 		return new ReturnType(0, 0);
	// 	}
	// 	ReturnType leftReturnType = process(head.left);
	// 	ReturnType rightReturnType = process(head.right);
	// 	int includeHeadDistance = leftReturnType.h + 1 + rightReturnType.h;
	// 	int p1 = leftReturnType.maxDistance;
	// 	int p2 = rightReturnType.maxDistance;
	// 	int resultDistance = Math.max(Math.max(p1, p2), includeHeadDistance);
	// 	int hitSelf = Math.max(leftReturnType.h, rightReturnType.h) + 1;
	// 	return new ReturnType(resultDistance, hitSelf);
	// }

	// 法二： 通过一个对象记住当前最大距离
	// public static int posOrder(Node head, int[] record) {
	// if (head == null) {
	// record[0] = 0;
	// return 0;
	// }
	// int lMax = posOrder(head.left, record);
	// int maxFromLeft = record[0];
	// int rMax = posOrder(head.right, record);
	// int maxFromRight = record[0];
	// int curNodeMax = maxFromLeft + maxFromRight + 1;
	// record[0] = Math.max(maxFromLeft, maxFromRight) + 1;
	// return Math.max(Math.max(lMax, rMax), curNodeMax);
	// }

	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.left = new Node(2);
		head1.right = new Node(3);
		head1.left.left = new Node(4);
		head1.left.right = new Node(5);
		head1.right.left = new Node(6);
		head1.right.right = new Node(7);
		head1.left.left.left = new Node(8);
		head1.right.left.right = new Node(9);
		System.out.println(maxDistance(head1));

		Node head2 = new Node(1);
		head2.left = new Node(2);
		head2.right = new Node(3);
		head2.right.left = new Node(4);
		head2.right.right = new Node(5);
		head2.right.left.left = new Node(6);
		head2.right.right.right = new Node(7);
		head2.right.left.left.left = new Node(8);
		head2.right.right.right.right = new Node(9);
		System.out.println(maxDistance(head2));

	}

}
