package A4high.class01;

import java.util.Arrays;

// 给定一个数组，求如果排序之后，相邻两数的最大差值。要求时间复杂度O(N)，
// 且要求不能用非基于比较的排序。

// NOTE: 使用空桶之间的差这一平凡解一定大于同一桶内的任意两值差来缩小比较范围，就不用考虑桶内的数之差了
public class C01_MaxGap {

	// public static int maxGap(int[] nums) {
	// if (nums == null || nums.length < 2) {
	// return 0;
	// }
	// int len = nums.length;
	// int min = Integer.MAX_VALUE;
	// int max = Integer.MIN_VALUE;
	// for (int i = 0; i < len; i++) {
	// min = Math.min(min, nums[i]);
	// max = Math.max(max, nums[i]);
	// }
	// if (min == max) {
	// return 0;
	// }
	// boolean[] hasNum = new boolean[len + 1];
	// int[] maxs = new int[len + 1];
	// int[] mins = new int[len + 1];
	// int bid = 0;
	// for (int i = 0; i < len; i++) {
	// bid = bucket(nums[i], len, min, max);
	// mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
	// maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
	// hasNum[bid] = true;
	// }
	// int res = 0;
	// int lastMax = maxs[0];
	// int i = 1;
	// for (; i <= len; i++) {
	// if (hasNum[i]) {
	// res = Math.max(res, mins[i] - lastMax);
	// lastMax = maxs[i];
	// }
	// }
	// return res;
	// }

	public static int maxGap(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}

		boolean[] hasNum = new boolean[arr.length + 1];
		int[] maxCache = new int[arr.length + 1];
		int[] minCache = new int[arr.length + 1];

		// 获取最值
		int min = arr[0];
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
		}
		if (max == min) {
			return 0;
		}

		// 入桶
		for (int i = 0; i < arr.length; i++) {
			// 比例关系： slice =( max - min ) / arr.length = (arr[i] - min) / index
			// TODO: 为什么不是：slice =( max - min ) /(arr.length + 1) = (arr[i] - min) / (index
			// + 1)
			int index = (int) ((float) (arr[i] - min) / (max - min) * arr.length);
			if (hasNum[index]) {
				maxCache[index] = Math.max(maxCache[index], arr[i]);
				minCache[index] = Math.min(minCache[index], arr[i]);
			} else {
				maxCache[index] = arr[i];
				minCache[index] = arr[i];
				hasNum[index] = true;
			}
		}

		int maxGap = Integer.MIN_VALUE;

		// 第一个桶和最后一个桶一定有元素
		int pre = maxCache[0];
		for (int i = 1; i < minCache.length; i++) {
			if (!hasNum[i]) {
				continue;
			}
			maxGap = Math.max(maxGap, minCache[i] - pre);
			pre = maxCache[i];
		}

		return maxGap;
	}

	public static int bucket(long num, long len, long min, long max) {
		return (int) ((num - min) * len / (max - min));
	}

	// for test
	public static int comparator(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		Arrays.sort(nums);
		int gap = Integer.MIN_VALUE;
		for (int i = 1; i < nums.length; i++) {
			gap = Math.max(nums[i] - nums[i - 1], gap);
		}
		return gap;
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
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			if (maxGap(arr1) != comparator(arr2)) {
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

}
