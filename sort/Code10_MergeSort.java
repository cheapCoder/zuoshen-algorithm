package sort;

import java.util.Arrays;

public class Code10_MergeSort {

	// public static void mergeSort(int[] arr) {
	// if (arr == null || arr.length < 2) {
	// return;
	// }
	// mergeSort(arr, 0, arr.length - 1);
	// }

	// public static void mergeSort(int[] arr, int l, int r) {
	// if (l == r) {
	// return;
	// }
	// int mid = l + ((r - l) >> 1);
	// mergeSort(arr, l, mid);
	// mergeSort(arr, mid + 1, r);
	// merge(arr, l, mid, r);
	// }

	// public static void merge(int[] arr, int l, int m, int r) {
	// int[] help = new int[r - l + 1];
	// int i = 0;
	// int p1 = l;
	// int p2 = m + 1;
	// while (p1 <= m && p2 <= r) {
	// help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
	// }
	// while (p1 <= m) {
	// help[i++] = arr[p1++];
	// }
	// while (p2 <= r) {
	// help[i++] = arr[p2++];
	// }
	// for (i = 0; i < help.length; i++) {
	// arr[l + i] = help[i];
	// }
	// }

	public static void mergeSort(int[] arr) { // 归并排序
		if (arr == null || arr.length < 2) {
			return;
		}

		mergeSort(arr, 0, arr.length - 1);
	}

	public static void mergeSort(int[] arr, int L, int R) {
		if (L == R) {
			return;
		}
		int mid = (int) Math.floor(L + ((R - L) >> 1));
		mergeSort(arr, L, mid);
		mergeSort(arr, mid + 1, R);

		int leftPointer = L;
		int rightPointer = mid + 1;
		int[] temArr = new int[R - L + 1];
		int currentIndex = 0;
		while (leftPointer <= mid && rightPointer <= R) {
			if (arr[leftPointer] <= arr[rightPointer]) {
				temArr[currentIndex++] = arr[leftPointer++];
				// leftPointer++;
			} else {
				temArr[currentIndex++] = arr[rightPointer++];
				// rightPointer++;
			}
			// currentIndex++;
		}

		while (leftPointer <= mid) {
			temArr[currentIndex++] = arr[leftPointer++];
			// leftPointer++;
			// currentIndex++;
		}
		while (rightPointer <= R) {
			temArr[currentIndex++] = arr[rightPointer++];
			// currentIndex++;
			// rightPointer++;
		}

		for (int i = 0; i < temArr.length; i++) {
			arr[L + i] = temArr[i];
		}
	}

	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
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
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
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
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			mergeSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		mergeSort(arr);
		printArray(arr);

	}

}
