package linked_list;

public class Code05_SmallerEqualBigger {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	// public static Node listPartition1(Node head, int pivot) {
	// if (head == null) {
	// return head;
	// }
	// Node cur = head;
	// int i = 0;
	// while (cur != null) {
	// i++;
	// cur = cur.next;
	// }
	// Node[] nodeArr = new Node[i];
	// i = 0;
	// cur = head;
	// for (i = 0; i != nodeArr.length; i++) {
	// nodeArr[i] = cur;
	// cur = cur.next;
	// }
	// arrPartition(nodeArr, pivot);
	// for (i = 1; i != nodeArr.length; i++) {
	// nodeArr[i - 1].next = nodeArr[i];
	// }
	// nodeArr[i - 1].next = null;
	// return nodeArr[0];
	// }

	// public static void arrPartition(Node[] nodeArr, int pivot) {
	// int small = -1;
	// int big = nodeArr.length;
	// int index = 0;
	// while (index != big) {
	// if (nodeArr[index].value < pivot) {
	// swap(nodeArr, ++small, index++);
	// } else if (nodeArr[index].value == pivot) {
	// index++;
	// } else {
	// swap(nodeArr, --big, index);
	// }
	// }
	// }

	// public static void swap(Node[] nodeArr, int a, int b) {
	// Node tmp = nodeArr[a];
	// nodeArr[a] = nodeArr[b];
	// nodeArr[b] = tmp;
	// }

	// public static Node listPartition2(Node head, int pivot) {
	// Node sH = null; // small head
	// Node sT = null; // small tail
	// Node eH = null; // equal head
	// Node eT = null; // equal tail
	// Node bH = null; // big head
	// Node bT = null; // big tail
	// Node next = null; // save next node
	// // every node distributed to three lists
	// while (head != null) {
	// next = head.next;
	// head.next = null;
	// if (head.value < pivot) {
	// if (sH == null) {
	// sH = head;
	// sT = head;
	// } else {
	// sT.next = head;
	// sT = head;
	// }
	// } else if (head.value == pivot) {
	// if (eH == null) {
	// eH = head;
	// eT = head;
	// } else {
	// eT.next = head;
	// eT = head;
	// }
	// } else {
	// if (bH == null) {
	// bH = head;
	// bT = head;
	// } else {
	// bT.next = head;
	// bT = head;
	// }
	// }
	// head = next;
	// }
	// // small and equal reconnect
	// if (sT != null) {
	// sT.next = eH;
	// eT = eT == null ? sT : eT;
	// }
	// // all reconnect
	// if (eT != null) {
	// eT.next = bH;
	// }
	// return sH != null ? sH : eH != null ? eH : bH;
	// }

	// 方法一：利用数组
	public static Node listPartition1(Node head, int pivot) {
		if (head == null) {
			return head;
		}

		Node pointer = head;
		int length = 0;
		while (pointer != null) {
			pointer = pointer.next;
			length++;
		}

		Node[] arr = new Node[length];
		int i = 0;
		pointer = head;
		while (pointer != null) {
			arr[i++] = pointer;
			pointer = pointer.next;
		}

		i = 0;
		arrPartition(arr, pivot);
		for (; i < arr.length - 1; i++) {
			arr[i].next = arr[i + 1];
		}
		arr[i].next = null;

		return arr[0];
	}

	private static void arrPartition(Node[] arr, int pivot) {
		if (arr == null || arr.length < 2) {
			return;
		}

		int left = -1;
		int right = arr.length;
		int i = 0;
		while (i < right) {
			if (arr[i].value < pivot) {
				swap(arr, i++, ++left);
			} else if (arr[i].value > pivot) {
				swap(arr, i, --right);
			} else {
				i++;
			}
		}
	}

	private static void swap(Node[] arr, int i, int j) {
		if (i == j) {
			return;
		}
		Node tem = arr[i];
		arr[i] = arr[j];
		arr[j] = tem;
	}

	// 方法二：使用6个指针表示3个区域的边界
	public static Node listPartition2(Node head, int pivot) {
		Node SH = null, ST = null, EH = null, ET = null, BH = null, BT = null;
		Node next;
		while (head != null) {
			next = head.next;
			head.next = null;
			if (head.value < pivot) {
				if (SH == null && ST == null) {
					SH = ST = head;
				} else {
					ST.next = head;
					ST = head;
				}
			} else if (head.value == pivot) {
				if (EH == null && ET == null) {
					EH = ET = head;
				} else {
					ET.next = head;
					ET = head;
				}
			} else {
				if (BH == null && BT == null) {
					BH = BT = head;
				} else {
					BT.next = head;
					BT = head;
				}
			}
			head = next;
		}

		// // small and equal reconnect
		// if (ST != null) { // TODO: 不明白
		// ST.next = EH;
		// ET = ET == null ? ST : ET;
		// }
		// // all reconnect
		// if (ET != null) {
		// ET.next = BH;
		// }
		// return SH != null ? SH : EH != null ? EH : BH;

		if (SH != null) {
			ST.next = EH != null ? EH : BH;
		}
		if (EH != null) {
			ET.next = BH;
		}
		return SH != null ? SH : (EH != null ? EH : BH);
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
		Node head1 = new Node(7);
		head1.next = new Node(9);
		head1.next.next = new Node(1);
		head1.next.next.next = new Node(8);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(2);
		head1.next.next.next.next.next.next = new Node(5);
		printLinkedList(head1);
		// head1 = listPartition1(head1, 5);
		head1 = listPartition2(head1, 5);
		printLinkedList(head1);

	}

}
