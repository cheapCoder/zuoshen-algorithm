package A4high.class01;

// 某公司招聘，有n个人入围，HR在黑板上依次写下m个正整数A1、A2、......、Am，然后让这n个人围成一个圈，并按照顺时针顺序为他们编号0、1、2、......、n-1。
// 录取规则是: 第一轮从0号的人开始，取用黑板上的第1个数字，也就是A1 
// 黑板上的数字按次序循环取用，即如果某轮用了第m个，则下一轮需要用第1个;
// 如果某轮用到第k个，则 下轮需要用第k+1个(k<m) 每一轮按照黑板上的次序取用到一个数字Ax，
// 淘汰掉从当前轮到的人开始按照顺时针顺序数到的第Ax个人，
// 下一轮开始时轮到的人即为被淘汰掉的人的顺时针顺序下一个人被淘汰的人直接回家，所以不会被后续轮次计数时数到
// 经过n-1轮后，剩下的最后1人被录取 所以最后被录取的人的编号与(n，m，A1，A2，......，Am)相关。
// 输入描述:
// 第一行是一个正整数N，表示有N组参数 从第二行开始，每行有若干个正整数，依次存放n、m、A1、......、Am，一共有N行，也就是上面的N组参数。
// 输出描述:
// 输出有N行，每行对应相应的那组参数确定的录取之人的编号示例1:
// 输入
// 1
// 4231
// 输出
// 1
// NOTE: 题意中distance是动态的，解答中只考虑的固定值的解法

// NOTE: 原型：约瑟夫环
// 数形结合，从y = x % i的数学函数图像启发
public class C04_JosephusProblem {

	// public static class Node {
	// public int value;
	// public Node next;

	// public Node(int data) {
	// this.value = data;
	// }
	// }

	// 时间复杂度：O(M * N)
	// public static Node josephusKill1(Node head, int m) {
	// if (head == null || head.next == head || m < 1) {
	// return head;
	// }
	// Node last = head;
	// while (last.next != head) {
	// last = last.next;
	// }
	// int count = 0;
	// while (head != last) {
	// if (++count == m) {
	// last.next = head.next;
	// count = 0;
	// } else {
	// last = last.next;
	// }
	// head = last.next;
	// }
	// return head;
	// }

	// 时间复杂度：O(N)
	// public static Node josephusKill2(Node head, int m) {
	// if (head == null || head.next == head || m < 1) {
	// return head;
	// }
	// Node cur = head.next;
	// int tmp = 1; // tmp -> list size
	// while (cur != head) {
	// tmp++;
	// cur = cur.next;
	// }
	// tmp = getLive(tmp, m); // tmp -> service node position
	// while (--tmp != 0) {
	// head = head.next;
	// }
	// head.next = head;
	// return head;
	// }

	// public static int getLive(int i, int m) {
	// if (i == 1) {
	// return 1;
	// }
	// return (getLive(i - 1, m) + m - 1) % i + 1;
	// }

	private static class Node {
		int value;
		Node next;

		public Node(int v) {
			this.value = v;
		}
	}

	// 法一：时间复杂度：O(distance * N)
	public static Node josephusKill1(Node head, int distance) {
		if (head == null) {
			return null;
		}

		while (head.next != head) {
			// distance-2是为了因为distance从1开始计数，并为了让指针最后听到被删节点的前一个，画图更好理解
			for (int i = 0; i < distance - 2; i++) {
				head = head.next;
			}
			head.next = head.next.next;
			head = head.next;
		}

		return head;
	}

	// 时间复杂度：O(N)
	public static Node josephusKill2(Node head, int distance) {
		if (head == null) {
			return null;
		}

		// 计算长度
		int len = 1;
		Node node = head.next;
		while (node != head) {
			len++;
			node = node.next;
		}

		int num = process(len, distance);
		while (num != 1) {
			head = head.next;
			num--;
		}
		head.next = head;

		return head;
	}

	private static int process(int len, int distance) {
		if (len == 1) {
			return 1;
		}
		return (process(len - 1, distance) + distance - 1) % len + 1;
	}

	// for test
	public static void printCircularList(Node head) {
		if (head == null) {
			return;
		}
		System.out.print("Circular List: " + head.value + " ");
		Node cur = head.next;
		while (cur != head) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println("-> " + head.value);
	}

	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);
		head1.next.next.next.next.next.next.next = new Node(8);
		head1.next.next.next.next.next.next.next.next = head1;
		printCircularList(head1);
		head1 = josephusKill1(head1, 3);
		printCircularList(head1);

		Node head2 = new Node(1);
		head2.next = new Node(2);
		head2.next.next = new Node(3);
		head2.next.next.next = new Node(4);
		head2.next.next.next.next = new Node(5);
		head2.next.next.next.next.next = new Node(6);
		head2.next.next.next.next.next.next = new Node(7);
		head2.next.next.next.next.next.next.next = new Node(8);
		head2.next.next.next.next.next.next.next.next = head2;
		printCircularList(head2);
		head2 = josephusKill2(head2, 3);
		printCircularList(head2);

	}

}
