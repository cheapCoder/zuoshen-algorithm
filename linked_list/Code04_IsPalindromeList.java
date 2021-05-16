package linked_list;

import java.util.Stack;

public class Code04_IsPalindromeList { // PalindromeList:回文链表

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	// // need n extra space
	// public static boolean isPalindrome1(Node head) {
	// Stack<Node> stack = new Stack<Node>();
	// Node cur = head;
	// while (cur != null) {
	// stack.push(cur);
	// cur = cur.next;
	// }
	// while (head != null) {
	// if (head.value != stack.pop().value) {
	// return false;
	// }
	// head = head.next;
	// }
	// return true;
	// }

	// // need n/2 extra space
	// public static boolean isPalindrome2(Node head) {
	// if (head == null || head.next == null) {
	// return true;
	// }
	// Node right = head.next;
	// Node cur = head;
	// while (cur.next != null && cur.next.next != null) {
	// right = right.next;
	// cur = cur.next.next;
	// }
	// Stack<Node> stack = new Stack<Node>();
	// while (right != null) {
	// stack.push(right);
	// right = right.next;
	// }
	// while (!stack.isEmpty()) {
	// if (head.value != stack.pop().value) {
	// return false;
	// }
	// head = head.next;
	// }
	// return true;
	// }

	// // need O(1) extra space
	// public static boolean isPalindrome3(Node head) {
	// if (head == null || head.next == null) {
	// return true;
	// }
	// Node n1 = head;
	// Node n2 = head;
	// while (n2.next != null && n2.next.next != null) { // find mid node
	// n1 = n1.next; // n1 -> mid
	// n2 = n2.next.next; // n2 -> end
	// }
	// n2 = n1.next; // n2 -> right part first node
	// n1.next = null; // mid.next -> null
	// Node n3 = null;
	// while (n2 != null) { // right part convert
	// n3 = n2.next; // n3 -> save next node
	// n2.next = n1; // next of right node convert
	// n1 = n2; // n1 move
	// n2 = n3; // n2 move
	// }
	// n3 = n1; // n3 -> save last node
	// n2 = head;// n2 -> left first node
	// boolean res = true;
	// while (n1 != null && n2 != null) { // check palindrome
	// if (n1.value != n2.value) {
	// res = false;
	// break;
	// }
	// n1 = n1.next; // left to mid
	// n2 = n2.next; // right to mid
	// }
	// n1 = n3.next;
	// n3.next = null;
	// while (n1 != null) { // recover list
	// n2 = n1.next;
	// n1.next = n3;
	// n3 = n1;
	// n1 = n2;
	// }
	// return res;
	// }

	public static boolean isPalindrome1(Node head) {
		Node pointer = head;
		Stack<Integer> stack = new Stack<>();
		while (pointer != null) { // 复制到栈中
			stack.push(pointer.value);
			pointer = pointer.next;
		}

		boolean flag = true;
		pointer = head;
		while (pointer != null) {
			if (pointer.value != stack.pop()) {
				flag = false;
				break;
			}
			pointer = pointer.next;
		}

		return flag;
	}

	private static boolean isPalindrome2(Node head) {
		if (head == null || head.next == null) {
			return true;
		}
		Node quickPointer = head;
		Node slowPointer = head;
		boolean flag = true;
		Stack<Integer> stack = new Stack<>();

		while (quickPointer != null && quickPointer.next != null) {
			quickPointer = quickPointer.next.next;
			slowPointer = slowPointer.next;
		}
		Node mid = slowPointer;
		while (slowPointer != null) {
			stack.push(slowPointer.value);
			slowPointer = slowPointer.next;
		}

		while (head != mid) {
			if (head.value != stack.pop()) {
				flag = false;
				break;
			}
			head = head.next;
		}

		return flag;
	}

	private static boolean isPalindrome3(Node head) {
		if (head == null || head.next == null) {
			return true;
		}

		Node quickPointer = head;
		Node slowPointer = head;
		boolean flag = true;

		while (quickPointer.next != null && quickPointer.next.next != null) {
			quickPointer = quickPointer.next.next;
			slowPointer = slowPointer.next;
		}

		Node rightPart = slowPointer.next;
		slowPointer.next = null;
		Node pre = null;
		Node post = rightPart.next;
		while (rightPart != null) {
			post = rightPart.next;
			rightPart.next = pre;
			pre = rightPart;
			rightPart = post;
			
		}

		Node tem = pre;
		while (tem != null && head != null) {
			if (tem.value != head.value) {
				flag = false;
				break;
			}
			tem = tem.next;
			head = head.next;
		}

		rightPart = pre; // rightPart就是新的头结点
		pre = null;
		post = rightPart.next;
		while (rightPart != null) {
			post = rightPart.next;
			rightPart.next = pre;
			pre = rightPart;
			rightPart = post;
		}

		slowPointer.next = pre;

		return flag;
	}

	public static void printLinkedList(Node node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {

		Node head = null;
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(2);
		head.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

	}

}
