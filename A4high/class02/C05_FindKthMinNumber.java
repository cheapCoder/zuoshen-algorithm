package A4high.class02;

import java.util.Arrays;

// 给定两个一维int数组A和B.
// 其中:A是长度为m、元素从小到大排好序的有序数组。B是长度为n、元素从小到大排好序的有序数组。
// 希望从A和B数组中，找出最大的第k个数字，要求:使用尽量少的时间复杂度。

// NOTE: pretty pretty pretty pretty hard
public class C05_FindKthMinNumber {

	public static int findKthNum(int[] arr1, int[] arr2, int kth) {
		if (arr1 == null || arr2 == null) {
			throw new RuntimeException("Your arr is invalid!");
		}
		if (kth < 1 || kth > arr1.length + arr2.length) {
			throw new RuntimeException("K is invalid!");
		}
		int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
		int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
		int l = longs.length;
		int s = shorts.length;
		if (kth <= s) {
			return getUpMedian(shorts, 0, kth - 1, longs, 0, kth - 1);
		}
		if (kth > l) {
			if (shorts[kth - l - 1] >= longs[l - 1]) {
				return shorts[kth - l - 1];
			}
			if (longs[kth - s - 1] >= shorts[s - 1]) {
				return longs[kth - s - 1];
			}
			return getUpMedian(shorts, kth - l, s - 1, longs, kth - s, l - 1);
		}
		if (longs[kth - s - 1] >= shorts[s - 1]) {
			return longs[kth - s - 1];
		}
		return getUpMedian(shorts, 0, s - 1, longs, kth - s, kth - 1);
	}

	public static int getUpMedian(int[] a1, int s1, int e1, int[] a2, int s2, int e2) {
		int mid1 = 0;
		int mid2 = 0;
		int offset = 0;
		while (s1 < e1) {
			mid1 = (s1 + e1) / 2;
			mid2 = (s2 + e2) / 2;
			offset = ((e1 - s1 + 1) & 1) ^ 1;
			if (a1[mid1] > a2[mid2]) {
				e1 = mid1;
				s2 = mid2 + offset;
			} else if (a1[mid1] < a2[mid2]) {
				s1 = mid1 + offset;
				e2 = mid2;
			} else {
				return a1[mid1];
			}
		}
		return Math.min(a1[s1], a2[s2]);
	}

	// 法一:双指针
	public static int findKthNum2(int[] arr1, int[] arr2, int kth) {
		if (arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0 || kth > arr1.length + arr2.length) {
			return -1;
		}
		int i1 = arr1.length - 1;
		int i2 = arr2.length - 1;
		int count = 0;
		int cur = -1;
		while (count < kth) {
			if (arr1[i1] > arr2[i2]) {
				cur = arr1[i1];
				i1--;
			} else {
				cur = arr2[i2];
				i2--;
			}
			count++;
		}
		return cur;
	}

	// 法二：
	public static int findKthNum3(int[] arr1, int[] arr2, int kth) {
		if (arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0 || kth > arr1.length + arr2.length) {
			return -1;
		}

		// 三段范围
		if (kth <= Math.min(arr1.length, arr2.length)) {
			return getUpMid3(arr1, arr2, 0, kth - 1, 0, kth - 1);
		} else if (kth > Math.max(arr1.length, arr2.length)) {
			int i1 = kth - arr2.length - 1;
			int i2 = kth - arr1.length - 1;
			if (arr1[i1] > arr2[arr2.length - 1]) {
				return arr1[i1];
			}
			if (arr2[i2] > arr1[arr1.length - 1]) {
				return arr2[i2];
			}

			return getUpMid3(arr1, arr2, i1 + 1, arr1.length - 1, i2 + 1, arr2.length - 1);
		} else {
			int[] bigArr = arr1.length >= arr2.length ? arr1 : arr2;
			int[] smallArr = arr1.length < arr2.length ? arr1 : arr2;

			int bigLeft = kth - smallArr.length - 1;
			int bigRight = kth - 1;
			if (bigArr[bigLeft] > smallArr[smallArr.length - 1]) {
				return bigArr[bigLeft];
			} else {
				return getUpMid3(bigArr, smallArr, bigLeft + 1, bigRight, 0, smallArr.length - 1);
			}
		}

	}

	// 原型算法：
	private static int getUpMid3(int[] arr1, int[] arr2, int left1, int right1, int left2, int right2) {
		if (arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0 || arr1.length != arr2.length || left1 < 0
				|| left2 < 0 || right1 >= arr1.length || right2 >= arr2.length) {
			return -1;
		}

		if (left1 == right1) {
			return Math.min(arr1[left1], arr2[left2]);
		}

		int mid = (right1 + left1) / 2;

		// 分类讨论
		if (arr1[mid] == arr2[mid]) {
			return arr1[mid];
		} else {
			int[] bigArr = arr1[mid] > arr2[mid] ? arr1 : arr2;
			int[] smallArr = arr1[mid] < arr2[mid] ? arr1 : arr2; // 不考虑等于
			if ((right1 - left1 + 1) % 2 != 0) { // 长度为奇数
				if (bigArr[mid - 1] < smallArr[mid]) {
					return smallArr[mid];
				} else {
					return getUpMid3(arr1, arr2, left1, mid - 1, mid, right2);
				}
			} else { // 长度为偶数
				return getUpMid3(arr1, arr2, left1, mid, mid + 1, right2);
			}
		}
	}

	// For test, this method is inefficient but absolutely right
	public static int[] getSortedAllArray(int[] arr1, int[] arr2) {
		if (arr1 == null || arr2 == null) {
			throw new RuntimeException("Your arr is invalid!");
		}
		int[] arrAll = new int[arr1.length + arr2.length];
		int index = 0;
		for (int i = 0; i != arr1.length; i++) {
			arrAll[index++] = arr1[i];
		}
		for (int i = 0; i != arr2.length; i++) {
			arrAll[index++] = arr2[i];
		}
		Arrays.sort(arrAll);
		return arrAll;
	}

	// For test
	public static int[] generateSortedArray(int len, int maxValue) {
		int[] res = new int[len];
		for (int i = 0; i != len; i++) {
			res[i] = (int) (Math.random() * (maxValue + 1));
		}
		Arrays.sort(res);
		return res;
	}

	// For test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int len1 = 10;
		int len2 = 23;
		int maxValue1 = 20;
		int maxValue2 = 100;
		int[] arr1 = generateSortedArray(len1, maxValue1);
		int[] arr2 = generateSortedArray(len2, maxValue2);
		printArray(arr1);
		printArray(arr2);
		int[] sortedAll = getSortedAllArray(arr1, arr2);
		printArray(sortedAll);
		int kth = 17;
		System.out.println(findKthNum(arr1, arr2, kth));
		System.out.println(findKthNum2(arr1, arr2, kth));
		System.out.println(findKthNum3(arr1, arr2, kth));
		System.out.println(sortedAll[kth - 1]);

	}

}
