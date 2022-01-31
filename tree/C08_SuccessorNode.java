package tree;

// 在二叉树中找到一个节点的后继节点
// 【题目】 现在有一种新的二叉树节点类型如下:
// public class Node {
// 	public int value;
// 	public Node left;
// 	public Node right;
// 	public Node parent;

// 	public Node(int val) {
// 		value = val;
// 	}
// }
// 该结构比普通二叉树节点结构多了一个指向父节点的parent指针。假设有一棵Node类型的节点组成的二叉树，树中每个节点的parent指针都正确地指向自己的父节点，头节点的parent指向null。
// 只给一个在二叉树中的某个节点node，请实现返回node的后继节点的函数。
// 在二叉树的中序遍历的序列中，node的下一个节点叫作node的后继节点。
public class C08_SuccessorNode {

	public static class Node {
		public int value;
		public Node left;
		public Node right;
		public Node parent;

		public Node(int data) {
			this.value = data;
		}
	}

	// public static Node getSuccessorNode(Node node) {
	// if (node == null) {
	// return node;
	// }
	// if (node.right != null) {
	// return getLeftMost(node.right);
	// } else {
	// Node parent = node.parent;
	// while (parent != null && parent.left != node) {
	// node = parent;
	// parent = node.parent;
	// }
	// return parent;
	// }
	// }

	// public static Node getLeftMost(Node node) {
	// if (node == null) {
	// return node;
	// }
	// while (node.left != null) {
	// node = node.left;
	// }
	// return node;
	// }

	// // 法一：用变量记住遍历的上一个节点
	// public static Node preNode = null;
	// public static boolean canChange = true;
	// public static Node successor = null;

	// private static Node getSuccessorNode(Node o) {
	// Node head = o;
	// while (head.parent != null) {
	// head = head.parent;
	// }
	// inOrderTraversal(head, o);
	// Node tem = successor; // 记得恢复属性为默认值
	// successor = null;
	// preNode = null;
	// canChange = true;
	// return tem;

	// }

	// public static void inOrderTraversal(Node head, Node o) {
	// if (head == null) {
	// return;
	// }

	// inOrderTraversal(head.left, o);
	// if (canChange && preNode == o) {
	// successor = head;
	// canChange = false;
	// } else {
	// preNode = head;
	// }
	// inOrderTraversal(head.right, o);
	// }

	// 法二：分类讨论
	private static Node getSuccessorNode(Node n) {
		if (n == null) {
			return null;
		}
		if (n.right != null) { // 情况一：有右子树
			Node tem = n.right;
			while (tem.left != null) {
				tem = tem.left;
			}
			return tem;
		} else { // 情况二：没有右子树
			while (n.parent != null && n.parent.left != n) {
				n = n.parent;
			}
			return n.parent;
		}
	}

	public static void main(String[] args) {
		Node head = new Node(6);
		head.parent = null;
		head.left = new Node(3);
		head.left.parent = head;
		head.left.left = new Node(1);
		head.left.left.parent = head.left;
		head.left.left.right = new Node(2);
		head.left.left.right.parent = head.left.left;
		head.left.right = new Node(4);
		head.left.right.parent = head.left;
		head.left.right.right = new Node(5);
		head.left.right.right.parent = head.left.right;
		head.right = new Node(9);
		head.right.parent = head;
		head.right.left = new Node(8);
		head.right.left.parent = head.right;
		head.right.left.left = new Node(7);
		head.right.left.left.parent = head.right.left;
		head.right.right = new Node(10);
		head.right.right.parent = head.right;

		Node test = head.left.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left.left.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left.right.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right.left.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right.right; // 10's next is null
		System.out.println(test.value + " next: " + getSuccessorNode(test));
	}

}
