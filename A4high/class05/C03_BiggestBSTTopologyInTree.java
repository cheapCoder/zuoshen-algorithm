package A4high.class05;

import java.util.HashMap;
import java.util.Map;

// 给定一棵二叉树的头节点head，已知所有节点的值都不一样，
// 返回其中最大的且符合搜索二叉树条件的最大拓扑结构的大小。 
// 拓扑结构:不是子树，只要能连起来的结构都算。
class C03_BiggestBSTTopologyInTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int bstTopoSize1(Node head) {
		if (head == null) {
			return 0;
		}
		int max = maxTopo(head, head);
		// System.out.println(head.value + " ||||| " + max);

		max = Math.max(bstTopoSize1(head.left), max);
		max = Math.max(bstTopoSize1(head.right), max);
		return max;
	}

	public static int maxTopo(Node h, Node n) {
		if (h != null && n != null && isBSTNode(h, n, n.value)) {
			// System.out.println(h.value + " " + n.value + " " + (maxTopo(h, n.left) +
			// maxTopo(h, n.right) + 1));
			// System.out.println("------");
			return maxTopo(h, n.left) + maxTopo(h, n.right) + 1;
		}
		return 0;
	}

	public static boolean isBSTNode(Node h, Node n, int value) {
		if (h == null) {
			return false;
		}
		if (h == n) {
			return true;
		}
		return isBSTNode(h.value > value ? h.left : h.right, n, value);
	}

	public static class Record {
		public int l;
		public int r;

		public Record(int left, int right) {
			this.l = left;
			this.r = right;
		}
	}

	public static int bstTopoSize2(Node head) {
		Map<Node, Record> map = new HashMap<Node, Record>();
		return posOrder(head, map);
	}

	public static int posOrder(Node h, Map<Node, Record> map) {
		if (h == null) {
			return 0;
		}
		int ls = posOrder(h.left, map);
		int rs = posOrder(h.right, map);
		modifyMap(h.left, h.value, map, true);
		modifyMap(h.right, h.value, map, false);
		Record lr = map.get(h.left);
		Record rr = map.get(h.right);
		int lbst = lr == null ? 0 : lr.l + lr.r + 1;
		int rbst = rr == null ? 0 : rr.l + rr.r + 1;
		map.put(h, new Record(lbst, rbst));
		return Math.max(lbst + rbst + 1, Math.max(ls, rs));
	}

	public static int modifyMap(Node n, int v, Map<Node, Record> m, boolean s) {
		if (n == null || (!m.containsKey(n))) {
			return 0;
		}
		Record r = m.get(n);
		if ((s && n.value > v) || ((!s) && n.value < v)) {
			m.remove(n);
			return r.l + r.r + 1;
		} else {
			int minus = modifyMap(s ? n.right : n.left, v, m, s);
			if (s) {
				r.r = r.r - minus;
			} else {
				r.l = r.l - minus;
			}
			m.put(n, r);
			return minus;
		}
	}

	// 暴力方法O(N2)
	public static int bstTopoSize3(Node head) {
		if (head == null) {
			return 0;
		}

		int[] res = new int[] { 0 };
		getMaxCount(head, head, res);
		// System.out.println(head.value + " ||||| " + res[0]);
		res[0] = Math.max(res[0], bstTopoSize3(head.left));
		res[0] = Math.max(res[0], bstTopoSize3(head.right));
		return res[0];
	}

	// 获取root为顶点时的最大二叉搜索树能有多大
	private static void getMaxCount(Node root, Node cur, int[] res) {
		if (cur == null || root == null) {
			return;
		}
		if (!canBSTFindNode(root, cur)) {
			return;
		}
		res[0]++;
		// System.out.println(root.value + " " + cur.value + " " + res[0]);
		// System.out.println("------");
		getMaxCount(root, cur.left, res);
		getMaxCount(root, cur.right, res);
	}

	// 以二叉搜索树性质在node下查找target，能找到就为true
	private static boolean canBSTFindNode(Node node, Node target) {
		if (node == null || target == null) {
			return false;
		}
		while (node != null) {
			if (target.value == node.value) {
				return true;
			} else if (target.value > node.value) {
				node = node.right;
			} else {
				node = node.left;
			}
		}
		return false;
	}

	// O(N)解法
	public static int bstTopoSize4(Node head) {
		if (head == null) {
			return 0;
		}

		// int[]索引0表示left，1表示right
		HashMap<Node, Integer> map = new HashMap<>();
		process(head, map);
		return map.get(head);
	}

	private static void process(Node node, HashMap<Node, Integer> map) {
		if (node == null) {
			return;
		}
		if (!map.containsKey(node)) {
			map.put(node, 0);
		}

		// 获取node左边的贡献值
		process(node.left, map);
		int left = node.left == null || node.value < node.left.value ? 0
				: map.get(node.left) - getLeftDecrease(node, map) + 1;
		// System.out.println("left: " + left);

		// 获取node右边的贡献值
		process(node.right, map);
		int right = node.right == null || node.value > node.right.value ? 0
				: map.get(node.left) - getRightDecrease(node, map) + 1;
		// System.out.println("right: " + right);
		map.put(node, left + right + 1);
	}

	private static int getLeftDecrease(Node cur, HashMap<Node, Integer> map) {
		// if (cur.left == null) {
		// return 0;
		// }
		int big = cur.value;
		Node pre;
		cur = cur.left;
		while (cur.right != null && cur.right.value < big) {
			cur = cur.right;
		}
		System.out.println("left: " + big + ": " + (cur.right == null ? 0 : map.get(cur)));
		return cur.right == null ? 0 : map.get(cur);
	}

	private static int getRightDecrease(Node cur, HashMap<Node, Integer> map) {
		// if (cur.right == null) {
		// return 0;
		// }
		int small = cur.value;
		cur = cur.right;
		while (cur.left != null && cur.left.value > small) {
			cur = cur.left;
		}
		System.out.println("right: " + small + ": " + (cur.left == null ? 0 : map.get(cur)));
		return cur.left == null ? 0 : map.get(cur);
	}

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		Node head = new Node(6);
		head.left = new Node(1);
		head.left.left = new Node(0);
		head.left.right = new Node(3);
		head.right = new Node(12);
		head.right.left = new Node(10);
		head.right.left.left = new Node(4);
		head.right.left.left.left = new Node(2);
		head.right.left.left.right = new Node(5);
		head.right.left.right = new Node(14);
		head.right.left.right.left = new Node(11);
		head.right.left.right.right = new Node(15);
		head.right.right = new Node(13);
		head.right.right.left = new Node(20);
		head.right.right.right = new Node(16);
		printTree(head);

		System.out.println(bstTopoSize1(head));
		System.out.println(bstTopoSize2(head));
		System.out.println(bstTopoSize3(head));
		System.out.println(bstTopoSize4(head));

	}

}
