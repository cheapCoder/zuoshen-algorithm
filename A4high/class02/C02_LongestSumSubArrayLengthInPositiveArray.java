package A4high.class02;

import java.util.HashMap;

// 问题：给定一个数组arr，该数组无序，但每个值均为正数，再给定一个正数k。
// 求arr的所有子数组中所有元素相加和为k的最长子数组长度。
// 例如，arr=[1,2,1,1,1]，k=3。累加和为3的最长子数组为[1,1,1]，所以结果返回3。
// 要求:时间复杂度O(N)，额外空间复杂度O(1)

// NOTE: 预处理出前缀和
// 补充问题：数组有正，有负，有零时怎么求和为K的最长子数组长度

// NOTE: 滑动窗口
public class C02_LongestSumSubArrayLengthInPositiveArray {

	public static int getMaxLength(int[] arr, int k) {
		if (arr == null || arr.length == 0 || k <= 0) {
			return 0;
		}
		int left = 0;
		int right = 0;
		int sum = arr[0];
		int len = 0;

		while (right < arr.length) {
			if (sum == k) {
				len = Math.max(len, right - left + 1);
				sum -= arr[left++];
			} else if (sum < k) {
				right++;
				if (right == arr.length) {
					break;
				}
				sum += arr[right];
			} else {
				sum -= arr[left++];
			}
		}
		return len;
	}

	// 滑动窗口
	public static int getMaxLength2(int[] arr, int k) {
		if (arr == null || arr.length == 0 || k <= 0) {
			return 0;
		}
		int left = 0;
		int right = 0;
		int curSum = 0;
		int maxLen = 0;
		while (right < arr.length || curSum >= k) { // 注意还需 curSum >= k已确保right到最后的索引也能把其子数组全部校验到
			if (curSum == k) {
				maxLen = Math.max(maxLen, right - left);
				curSum -= arr[left];
				left++;
			} else if (curSum < k) {
				curSum += arr[right];
				right++;
			} else {
				curSum -= arr[left];
				left++;
			}
		}

		return maxLen;
	}

	// 补充问题：数组有正，有负，有零时怎么求和为K的最长子数组长度
	public static int getMaxLengthInAnyRange(int[] arr, int k) {
		if (arr == null || arr.length == 0 || k <= 0) {
			return 0;
		}

		HashMap<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		int maxLen = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (map.containsKey(sum - k)) {
				maxLen = Math.max(maxLen, i - map.get(sum - k));
			}

			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}

		return maxLen;
	}

	// for test
	public static int[] generatePositiveArray(int size) {
		int[] result = new int[size];
		for (int i = 0; i != size; i++) {
			result[i] = (int) (Math.random() * 10) + 1;
		}
		return result;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int len = 20;
		int k = 15;
		for (int i = 0; i < 10000; i++) {
			int[] arr = generatePositiveArray(len);
			// printArray(arr);
			// int one = getMaxLength(arr, k);
			int two = getMaxLength2(arr, k);
			// if (one != two) {
			// System.out.println("error");
			printArray(arr);
			// System.out.println(one);
			System.out.println(two);
			// }
		}

		// System.out.println(getMaxLength2(new int[] { 2, 3, 1, 9 }, 15));
		;
	}

}
