package A4high.class08;

// N个加油站组成一个环形，给定两个长度都是N的非负数组oil和dis(N>1)，oil[i]代表第i个加油站存的油可以跑多少千米，
// dis[i]代表第i个加油站到环中下一个加油站相隔多少千米。
// 假设你有一辆油箱足够大的车，初始时车里没有油。如果车从第i个加油站出发，最终可以回到这个加油站，那么第i个加油站就算良好出发点，否则就不算。
// 请返回长度为N的boolean型数组res，res[i]代表第i个加油站是不是良好出发点。

// NOTE: 往两边扩展的滑动窗口
public class C04_GasStations {

	public static boolean[] stations(int[] dis, int[] oil) {
		if (dis == null || oil == null || dis.length < 2 || dis.length != oil.length) {
			return null;
		}
		int init = changeDisArrayGetInit(dis, oil);
		return init == -1 ? new boolean[dis.length] : enlargeArea(dis, init);
	}

	public static int changeDisArrayGetInit(int[] dis, int[] oil) {
		int init = -1;
		for (int i = 0; i < dis.length; i++) {
			dis[i] = oil[i] - dis[i];
			if (dis[i] >= 0) {
				init = i;
			}
		}
		Integer.toString(123).toCharArray();
		return init;
	}

	public static boolean[] enlargeArea(int[] dis, int init) {
		boolean[] res = new boolean[dis.length];
		int start = init;
		int end = nextIndex(init, dis.length);
		int need = 0;
		int rest = 0;
		do {
			// 当前来到的start已经在连通区域中，可以确定后续的开始点一定无法转完一圈
			if (start != init && start == lastIndex(end, dis.length)) {
				break;
			}
			// 当前来到的start不在连通区域中，就扩充连通区域
			if (dis[start] < need) { // 从当前start出发，无法到达initial点
				need -= dis[start];
			} else { // 如start可以到达initial点，扩充连通区域的结束点
				rest += dis[start] - need;
				need = 0;
				while (rest >= 0 && end != start) {
					rest += dis[end];
					end = nextIndex(end, dis.length);
				}
				// 如果连通区域已经覆盖整个环，当前的start是良好出发点，进入2阶段
				if (rest >= 0) {
					res[start] = true;
					connectGood(dis, lastIndex(start, dis.length), init, res);
					break;
				}
			}
			start = lastIndex(start, dis.length);
		} while (start != init);
		return res;
	}

	// 已知start的next方向上有一个良好出发点
	// start如果可以达到这个良好出发点，那么从start出发一定可以转一圈
	public static void connectGood(int[] dis, int start, int init, boolean[] res) {
		int need = 0;
		while (start != init) {
			if (dis[start] < need) {
				need -= dis[start];
			} else {
				res[start] = true;
				need = 0;
			}
			start = lastIndex(start, dis.length);
		}
	}

	public static int lastIndex(int index, int size) {
		return index == 0 ? (size - 1) : index - 1;
	}

	public static int nextIndex(int index, int size) {
		return index == size - 1 ? 0 : (index + 1);
	}

	// 1.暴力方式
	public static boolean[] process1(int[] dis, int[] oil) {
		if (dis == null || dis.length == 0 || oil == null || oil.length == 0 || dis.length != oil.length) {
			return null;
		}

		int[] merge = new int[dis.length];
		for (int i = 0; i < merge.length; i++) {
			merge[i] = oil[i] - dis[i];
		}

		boolean[] res = new boolean[dis.length];

		for (int i = 0; i < merge.length; i++) {
			if (merge[i] < 0) {
				res[i] = false;
				continue;
			}
			int j = i;
			int sum = 0;
			do {
				sum += merge[j];
				if (sum < 0) {
					break;
				}
				j = j == merge.length - 1 ? 0 : j + 1;
			} while (i != j);
			res[i] = i == j ? true : false;
		}
		return res;
	}

	// 2.两头扩展的滑动窗口
	public static boolean[] process2(int[] dis, int[] oil) {
		if (dis == null || dis.length == 0 || oil == null || oil.length == 0 || dis.length != oil.length) {
			return null;
		}

		int[] merge = new int[dis.length];
		for (int i = 0; i < merge.length; i++) {
			merge[i] = oil[i] - dis[i];
		}

		boolean[] res = new boolean[dis.length];

		int start = 0;
		while (start < merge.length && merge[start] < 0) {
			res[start++] = false;
		}
		int end = start;
		


		return res;
	}

	// for test
	public static boolean[] test(int[] dis, int[] oil) {
		if (dis == null || oil == null || dis.length < 2 || dis.length != oil.length) {
			return null;
		}
		boolean[] res = new boolean[dis.length];
		for (int i = 0; i < dis.length; i++) {
			dis[i] = oil[i] - dis[i];
		}
		for (int i = 0; i < dis.length; i++) {
			res[i] = canWalkThrough(dis, i);
		}
		return res;
	}

	// for test
	public static boolean canWalkThrough(int[] arr, int index) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[index];
			if (sum < 0) {
				return false;
			}
			index = nextIndex(index, arr.length);
		}
		return true;
	}

	// for test
	public static void printArray(int[] dis, int[] oil) {
		for (int i = 0; i < dis.length; i++) {
			System.out.print(oil[i] - dis[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void printBooleanArray(boolean[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		System.out.println();
	}

	// for test
	public static int[] generateArray(int size, int max) {
		int[] res = new int[size];
		for (int i = 0; i < res.length; i++) {
			res[i] = (int) (Math.random() * max);
		}
		return res;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		int[] res = new int[arr.length];
		for (int i = 0; i < res.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(boolean[] res1, boolean[] res2) {
		for (int i = 0; i < res1.length; i++) {
			if (res1[i] != res2[i]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int max = 20;
		for (int i = 0; i < 5000000; i++) {
			int size = (int) (Math.random() * 20) + 2;
			int[] dis = generateArray(size, max);
			int[] oil = generateArray(size, max);
			int[] dis1 = copyArray(dis);
			int[] oil1 = copyArray(oil);
			int[] dis2 = copyArray(dis);
			int[] oil2 = copyArray(oil);
			// boolean[] res1 = stations(dis1, oil1);
			boolean[] res1 = process1(dis1, oil1);
			boolean[] res2 = test(dis2, oil2);
			if (!isEqual(res1, res2)) {
				printArray(dis, oil);
				printBooleanArray(res1);
				printBooleanArray(res2);
				System.out.println("what a fucking day!");
				break;
			}
		}
	}

}
