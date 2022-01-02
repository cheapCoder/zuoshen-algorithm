package A4high.class02;

// 问题：数组有正，有负，有零时怎么求和小于等于K的最长子数组长度？

// 补充问题：数组有正，有负，有零时怎么求和大于等于K的最长子数组长度呢？

public class C03_LongestLessSumSubArrayLength {

	public static int maxLengthAwesome(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[] minSums = new int[arr.length];
		int[] minSumEnds = new int[arr.length];
		minSums[arr.length - 1] = arr[arr.length - 1];
		minSumEnds[arr.length - 1] = arr.length - 1;
		for (int i = arr.length - 2; i >= 0; i--) {
			if (minSums[i + 1] < 0) {
				minSums[i] = arr[i] + minSums[i + 1];
				minSumEnds[i] = minSumEnds[i + 1];
			} else {
				minSums[i] = arr[i];
				minSumEnds[i] = i;
			}
		}
		int end = 0;
		int sum = 0;
		int res = 0;
		// i是窗口的最左的位置，end是窗口最右位置的下一个位置
		for (int i = 0; i < arr.length; i++) {
			// while循环结束之后：
			// 1) 如果以i开头的情况下，累加和<=k的最长子数组是arr[i..end-1]，看看这个子数组长度能不能更新res；
			// 2) 如果以i开头的情况下，累加和<=k的最长子数组比arr[i..end-1]短，更新还是不更新res都不会影响最终结果；
			while (end < arr.length && sum + minSums[end] <= k) {
				sum += minSums[end];
				end = minSumEnds[end] + 1;
			}
			res = Math.max(res, end - i);
			if (end > i) { // 窗口内还有数
				sum -= arr[i];
			} else { // 窗口内已经没有数了，说明从i开头的所有子数组累加和都不可能<=k
				end = i + 1;
			}
		}
		return res;
	}

	// public static int maxLength(int[] arr, int k) {
	// int[] h = new int[arr.length + 1];
	// int sum = 0;
	// h[0] = sum;
	// for (int i = 0; i != arr.length; i++) {
	// sum += arr[i];
	// h[i + 1] = Math.max(sum, h[i]);
	// }
	// sum = 0;
	// int res = 0;
	// int pre = 0;
	// int len = 0;
	// for (int i = 0; i != arr.length; i++) {
	// sum += arr[i];
	// pre = getLessIndex(h, sum - k);
	// len = pre == -1 ? 0 : i - pre + 1;
	// res = Math.max(res, len);
	// }
	// return res;
	// }

	// public static int getLessIndex(int[] arr, int num) {
	// int low = 0;
	// int high = arr.length - 1;
	// int mid = 0;
	// int res = -1;
	// while (low <= high) {
	// mid = (low + high) / 2;
	// if (arr[mid] >= num) {
	// res = mid;
	// high = mid - 1;
	// } else {
	// low = mid + 1;
	// }
	// }
	// return res;
	// }

	// 暴力法O(n2)
	public static int maxLength(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}

		int res = 0;
		for (int i = 0; i < arr.length; i++) {
			int curLen = 0;
			int sum = 0;
			for (int j = i; j < arr.length; j++) {
				sum += arr[j];
				if (sum <= k) {
					curLen = j - i + 1;
				}
			}

			res = Math.max(res, curLen);
		}

		return res;
	}

	// O(N)
	public static int maxLengthAwesome2(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}

		int[] minSumIndex = new int[arr.length];
		int[] minSum = new int[arr.length];
		minSum[arr.length - 1] = arr[arr.length - 1];
		minSumIndex[arr.length - 1] = arr.length - 1;
		for (int i = arr.length - 2; i >= 0; i--) {
			minSum[i] = minSum[i + 1] > 0 ? arr[i] : minSum[i + 1] + arr[i];
			minSumIndex[i] = minSum[i + 1] > 0 ? i : minSumIndex[i + 1];
		}

		int res = 0;
		int sum = 0;
		int curRight = 0;
		for (int left = 0; left < arr.length; left++) {
			while (curRight < arr.length && sum + minSum[curRight] <= k) {
				sum += minSum[curRight];
				curRight = minSumIndex[curRight] + 1;
			}
			res = Math.max(res, curRight - left);
			// TODO: 为什么不判断curRight有没右移过也行？
			// if (left == curRight) {
			// curRight = left + 1;
			// } else {
			sum -= arr[left];
			// }
		}
		return res;
	}

	// for test
	public static int[] generateRandomArray(int len, int maxValue) {
		int[] res = new int[len];
		for (int i = 0; i != res.length; i++) {
			res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
		}
		return res;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100000000; i++) {
			int[] arr = generateRandomArray(10, 20);
			int k = (int) (Math.random() * 20) - 5;
			int second = maxLengthAwesome(arr, k);
			// System.out.println(second);
			int first = maxLengthAwesome2(arr, k);
			// System.out.println(first);
			if (first != second) {
				System.out.println("oops!");
			} else {
				// System.out.println("equal");
			}
		}

	}

}
