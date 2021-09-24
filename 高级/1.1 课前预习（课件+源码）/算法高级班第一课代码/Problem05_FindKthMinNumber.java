package class01;

import java.util.Arrays;

public class Problem05_FindKthMinNumber {

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

	public static int getUpMedian(int[] a1, int s1, int e1, int[] a2, int s2,
			int e2) {
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

	public static int[] generateSortedArray(int len, int maxValue) {
		int[] res = new int[len];
		for (int i = 0; i != len; i++) {
			res[i] = (int) (Math.random() * (maxValue + 1));
		}
		Arrays.sort(res);
		return res;
	}

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
		System.out.println(sortedAll[kth - 1]);

	}

}
