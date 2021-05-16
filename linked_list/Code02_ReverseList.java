package linked_list;

public class Code02_ReverseList {

	// public static class Node {
	// public int value;
	// public Node next;

	// public Node(int data) {
	// this.value = data;
	// }
	// }

	// public static Node reverseList(Node head) {
	// Node pre = null;
	// Node next = null;
	// while (head != null) {
	// next = head.next;
	// head.next = pre;
	// pre = head;
	// head = next;
	// }
	// return pre;
	// }

	// public static class DoubleNode {
	// public int value;
	// public DoubleNode last;
	// public DoubleNode next;

	// public DoubleNode(int data) {
	// this.value = data;
	// }
	// }

	// public static DoubleNode reverseList(DoubleNode head) {
	// DoubleNode pre = null;
	// DoubleNode next = null;
	// while (head != null) {
	// next = head.next;
	// head.next = pre;
	// head.last = next;
	// pre = head;
	// head = next;
	// }
	// return pre;
	// }

	// public static void printLinkedList(Node head) {
	// System.out.print("Linked List: ");
	// while (head != null) {
	// System.out.print(head.value + " ");
	// head = head.next;
	// }
	// System.out.println();
	// }

	// public static void printDoubleLinkedList(DoubleNode head) {
	// System.out.print("Double Linked List: ");
	// DoubleNode end = null;
	// while (head != null) {
	// System.out.print(head.value + " ");
	// end = head;
	// head = head.next;
	// }
	// System.out.print("| ");
	// while (end != null) {
	// System.out.print(end.value + " ");
	// end = end.last;
	// }
	// System.out.println();
	// }

	private static class Node {
		public int val;
		public Node next;

		public Node(int val) {
			this.val = val;
		}
	}

	private static class DoubleNode {
		public int val;
		public DoubleNode next;
		public DoubleNode last;

		public DoubleNode(int val) {
			this.val = val;
		}
	}

	public static Node reverseList(Node head) {
		if (head == null) {
			return head;
		}

		Node pre = null;
		Node post = head.next;
		while (head.next != null) {
			head.next = pre;
			pre = head;
			head = post;
			post = post.next;
		}
		head.next = pre;
		return head;
	}

	public static DoubleNode reverseList(DoubleNode head) {
		if (head == null) {
			return head;
		}
		DoubleNode pre = null;
		DoubleNode post = head.next;
		while (head.next != null) {
			head.last = head.next;
			head.next = pre;
			pre = head;
			head = post;
			post = post.next;
		}
		head.next = pre;
		head.last = null;
		return head;
	}

	public static void printLinkedList(Node head) {
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
		System.out.println();
	}

	public static void printDoubleLinkedList(DoubleNode head) {
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		printLinkedList(head1);
		head1 = reverseList(head1);
		printLinkedList(head1);

		DoubleNode head2 = new DoubleNode(1);
		head2.next = new DoubleNode(2);
		head2.next.last = head2;
		head2.next.next = new DoubleNode(3);
		head2.next.next.last = head2.next;
		head2.next.next.next = new DoubleNode(4);
		head2.next.next.next.last = head2.next.next;
		printDoubleLinkedList(head2);
		printDoubleLinkedList(reverseList(head2));
	}

}
