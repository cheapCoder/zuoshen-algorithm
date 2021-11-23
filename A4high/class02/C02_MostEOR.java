package A4high.class02;

import java.util.HashMap;

// 给出n个数字 a_1,...,a_n，问最多有多少不重叠的非空区间，使得每个区间内数字的xor(异或运算)都等于0。

// NOTE: 假设答案法
public class C02_MostEOR {

	// public static int mostEOR(int[] arr) {
	// int ans = 0;
	// int xor = 0;
	// int[] mosts = new int[arr.length];
	// HashMap<Integer, Integer> map = new HashMap<>();
	// map.put(0, -1);
	// for (int i = 0; i < arr.length; i++) {
	// xor ^= arr[i];
	// if (map.containsKey(xor)) {
	// int pre = map.get(xor);
	// mosts[i] = pre == -1 ? 1 : (mosts[pre] + 1);
	// }
	// if (i > 0) {
	// mosts[i] = Math.max(mosts[i - 1], mosts[i]);
	// }
	// map.put(xor, i);
	// ans = Math.max(ans, mosts[i]);
	// }
	// return ans;
	// }

	public static int mostEOR(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}

		// 记录曾经为key值的最近索引
		HashMap<Integer, Integer> xorMap = new HashMap<>();
		xorMap.put(0, -1);
		xorMap.put(arr[0], 0);

		// 记录从0-i中xor为0的区间有多少个
		int[] res = new int[arr.length];
		res[0] = arr[0] == 0 ? 1 : 0;

		int xor = arr[0];
		for (int i = 1; i < arr.length; i++) {
			xor ^= arr[i];

			// 分类讨论：
			// 1.当i位置不在最后一个使xor为0的区间内时
			int situation1 = res[i - 1];

			// 2.当i位置在最后一个使xor为0的区间内时
			int situation2;
			if (xorMap.containsKey(xor)) {
				situation2 = xorMap.get(xor) == -1 ? 1 : res[xorMap.get(xor)] + 1;
			} else {
				situation2 = 0;
			}

			res[i] = Math.max(situation1, situation2);
			xorMap.put(xor, i);
		}

		return res[arr.length - 1];
	}

	// for test
	public static int comparator(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[] eors = new int[arr.length];
		int eor = 0;
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
			eors[i] = eor;
		}
		int[] mosts = new int[arr.length];
		mosts[0] = arr[0] == 0 ? 1 : 0;
		for (int i = 1; i < arr.length; i++) {
			mosts[i] = eors[i] == 0 ? 1 : 0;
			for (int j = 0; j < i; j++) {
				if ((eors[i] ^ eors[j]) == 0) {
					mosts[i] = Math.max(mosts[i], mosts[j] + 1);
				}
			}
			mosts[i] = Math.max(mosts[i], mosts[i - 1]);
		}
		return mosts[mosts.length - 1];
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random());
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
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 300;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int res = mostEOR(arr);
			int comp = comparator(arr);
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
