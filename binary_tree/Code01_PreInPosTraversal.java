package binary_tree;

import java.util.Stack;

public class Code01_PreInPosTraversal {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// public static void preOrderRecur(Node head) {
	// if (head == null) {
	// return;
	// }
	// System.out.print(head.value + " ");
	// preOrderRecur(head.left);
	// preOrderRecur(head.right);
	// }

	// public static void inOrderRecur(Node head) {
	// if (head == null) {
	// return;
	// }
	// inOrderRecur(head.left);
	// System.out.print(head.value + " ");
	// inOrderRecur(head.right);
	// }

	// public static void posOrderRecur(Node head) {
	// if (head == null) {
	// return;
	// }
	// posOrderRecur(head.left);
	// posOrderRecur(head.right);
	// System.out.print(head.value + " ");
	// }

	// public static void preOrderUnRecur(Node head) {
	// System.out.print("pre-order: ");
	// if (head != null) {
	// Stack<Node> stack = new Stack<Node>();
	// stack.add(head);
	// while (!stack.isEmpty()) {
	// head = stack.pop();
	// System.out.print(head.value + " ");
	// if (head.right != null) {
	// stack.push(head.right);
	// }
	// if (head.left != null) {
	// stack.push(head.left);
	// }
	// }
	// }
	// System.out.println();
	// }

	// public static void inOrderUnRecur(Node head) {
	// System.out.print("in-order: ");
	// if (head != null) {
	// Stack<Node> stack = new Stack<Node>();
	// while (!stack.isEmpty() || head != null) {
	// if (head != null) {
	// stack.push(head);
	// head = head.left;
	// } else {
	// head = stack.pop();
	// System.out.print(head.value + " ");
	// head = head.right;
	// }
	// }
	// }
	// System.out.println();
	// }

	// public static void posOrderUnRecur1(Node head) {
	// System.out.print("pos-order: ");
	// if (head != null) {
	// Stack<Node> s1 = new Stack<Node>();
	// Stack<Node> s2 = new Stack<Node>();
	// s1.push(head);
	// while (!s1.isEmpty()) {
	// head = s1.pop();
	// s2.push(head);
	// if (head.left != null) {
	// s1.push(head.left);
	// }
	// if (head.right != null) {
	// s1.push(head.right);
	// }
	// }
	// while (!s2.isEmpty()) {
	// System.out.print(s2.pop().value + " ");
	// }
	// }
	// System.out.println();
	// }

	// TODO:不明白
	public static void posOrderUnRecur2_(Node h) {
		System.out.print("pos-order: ");
		if (h != null) {
			Stack<Node> stack = new Stack<Node>();
			stack.push(h);
			Node c = null;
			while (!stack.isEmpty()) {
				c = stack.peek();
				if (c.left != null && h != c.left && h != c.right) {
					stack.push(c.left);
				} else if (c.right != null && h != c.right) {
					stack.push(c.right);
				} else {
					System.out.print(stack.pop().value + " ");
					h = c;
				}
			}
		}
		System.out.println();
	}

	public static void preOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		System.out.print(head.value + " ");
		preOrderRecur(head.left);
		preOrderRecur(head.right);
	}

	public static void inOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		inOrderRecur(head.left);
		System.out.print(head.value + " ");
		inOrderRecur(head.right);
	}

	public static void postOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		postOrderRecur(head.left);
		postOrderRecur(head.right);
		System.out.print(head.value + " ");
	}

	// 前序遍历
	public static void preOrderUnRecur(Node head) {
		if (head == null) {
			return;
		}

		Stack<Node> tem = new Stack<>();
		tem.add(head);
		while (!tem.isEmpty()) {
			head = tem.pop();
			System.out.print(head.value + " ");
			if (head.right != null) {
				tem.add(head.right);
			}
			;
			if (head.left != null) {
				tem.add(head.left);
			}
		}
		System.out.println();
	}

	private static void inOrderUnRecur(Node head) {
		// if (head == null) {
		// return;
		// }

		Stack<Node> tem = new Stack<>();
		// tem.add(head);
		while (!tem.isEmpty() || head != null) {
			if (head != null) {
				tem.push(head);
				head = head.left;
			} else {
				head = tem.pop();
				System.out.print(head.value + " ");
				head = head.right;
			}
		}
		System.out.println();
	}

	private static void posOrderUnRecur1(Node head) {
		if (head == null) {
			return;
		}

		Stack<Node> tem = new Stack<>();
		Stack<Node> tem2 = new Stack<>();

		tem.add(head);
		while (!tem.isEmpty()) {
			head = tem.pop();
			// System.out.print(head.value + " ");
			tem2.push(head);
			if (head.left != null) {
				tem.push(head.left);
			}
			if (head.right != null) {
				tem.push(head.right);
			}

		}

		while (!tem2.isEmpty()) {
			System.out.print(tem2.pop().value + " ");
		}
		System.out.println();
	}

	// TODO:
	private static void posOrderUnRecur2(Node head) {

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

		// recursive
		System.out.println("==============recursive==============");
		System.out.print("pre-order: ");
		preOrderRecur(head);
		System.out.println();
		System.out.print("in-order: ");
		inOrderRecur(head);
		System.out.println();
		System.out.print("pos-order: ");
		postOrderRecur(head);
		System.out.println();

		// unrecursive
		System.out.println("============unrecursive=============");
		preOrderUnRecur(head);
		inOrderUnRecur(head);
		posOrderUnRecur1(head);
		posOrderUnRecur2(head);

	}

}
