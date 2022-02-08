package A4high.class07;

// 数组异或和的定义:把数组中所有的数异或起来得到的值
// 给定一个整型数组 arr，其中可能有正、有负、有零，求其中子数组的最大异或和
// 【举例】		
// arr = {3} 数组只有1个数，所以只有一个子数组，就是这个数组本身，最大异或和为3
// arr = {3, -28, -29, 2}
// 子数组有很多，但是{-28, -29}这个子数组的异或和为7，是所有子数组中最大的

// NOTE: 前缀树 + 二进制 + 贪心
public class C02_MaxEOR {

	public static class Node {
		public Node[] nexts = new Node[2];
	}

	public static class NumTrie {
		public Node head = new Node();

		public void add(int num) {
			Node cur = head;
			for (int move = 31; move >= 0; move--) {
				int path = ((num >> move) & 1);
				cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
				cur = cur.nexts[path];
			}

		}

		public int maxXor(int num) {
			Node cur = head;
			int res = 0;
			for (int move = 31; move >= 0; move--) {
				int path = (num >> move) & 1;
				int best = move == 31 ? path : (path ^ 1);
				best = cur.nexts[best] != null ? best : (best ^ 1);
				res |= (path ^ best) << move;
				cur = cur.nexts[best];
			}
			return res;
		}

	}

	public static int maxXorSubarray(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int eor = 0;
		NumTrie numTrie = new NumTrie();
		numTrie.add(0);
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
			max = Math.max(max, numTrie.maxXor(eor));
			numTrie.add(eor);
		}

		return max;
	}

	// 暴力O(N2)
	public static int maxXorSubarray2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int res = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			int sum = 0;
			for (int j = i; j < arr.length; j++) {
				sum ^= arr[j];
				res = Math.max(res, sum);
			}
		}

		return res;
	}

	// O(N)
	public static int maxXorSubarray3(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		TrieTree tree = new TrieTree();
		int res = Integer.MIN_VALUE;
		int sum = 0;
		tree.add(0); // 添加0表示：[0,i]要去掉多余异或和的子数组长度可为0，即[0,-1]，即表示当前值就为0-i的异或和
		for (int i = 0; i < arr.length; i++) {
			sum ^= arr[i];
			res = Math.max(res, tree.getMax(sum));
			tree.add(sum); // 异或的数组最少长度为1，这行提到max前就表示可为空数组，即异或和有一个固定值0了
		}

		return res;

	}

	private static class Head {
		public Head[] Path = new Head[2];
	}

	private static class TrieTree {
		public Head head = new Head();

		public void add(int num) {
			Head tem = head;
			for (int i = 31; i >= 0; i--) {
				if (tem.Path[(num >> i) & 1] == null) {
					tem.Path[(num >> i) & 1] = new Head();
				}
				tem = tem.Path[(num >> i) & 1];
			}

		}

		// 通过传入参数获取前缀树内保存值能取到的最大的异或和
		public int getMax(int num) {
			int res = 0;
			Head tem = head;
			for (int i = 31; i >= 0; i--) {
				int wish = i == 31 ? (num >> i) & 1 : ((num >> i) & 1) ^ 1;
				if (tem.Path[wish] != null) { // 0或1两者必有一条路，因为当初add时候就一定会选一条路
					res |= (wish << i);
					tem = tem.Path[wish];
				} else {
					res |= ((wish ^ 1) << i);
					tem = tem.Path[wish ^ 1];
				}

			}

			return num ^ res;
		}

	}

	// for test
	public static int comparator(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			int eor = 0;
			for (int j = i; j < arr.length; j++) {
				eor ^= arr[j];
				max = Math.max(max, eor);
			}
		}
		return max;
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 30;
		int maxValue = 50;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			// int[] arr = new int[] { -1, -19 };

			int res = maxXorSubarray3(arr);
			int comp = maxXorSubarray(arr);
			if (res != comp) {
				succeed = false;
				printArray(arr);
				System.out.println(res);
				System.out.println(comp);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}
}
