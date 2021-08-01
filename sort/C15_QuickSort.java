package sort;

import java.util.Arrays;

public class C15_QuickSort {

	// public static void quickSort(int[] arr) {
	// if (arr == null || arr.length < 2) {
	// return;
	// }
	// quickSort(arr, 0, arr.length - 1);
	// }

	// public static void quickSort(int[] arr, int l, int r) {
	// if (l < r) {
	// swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
	// int[] p = partition(arr, l, r);
	// quickSort(arr, l, p[0] - 1);
	// quickSort(arr, p[1] + 1, r);
	// }
	// }

	// public static int[] partition(int[] arr, int l, int r) {
	// int less = l - 1;
	// int more = r;
	// while (l < more) {
	// if (arr[l] < arr[r]) {
	// swap(arr, ++less, l++);
	// } else if (arr[l] > arr[r]) {
	// swap(arr, --more, l);
	// } else {
	// l++;
	// }
	// }
	// swap(arr, more, r);
	// return new int[] { less + 1, more };
	// }

	public static void quickSort(int[] arr, int left, int right) {
		if (left >= right) {
			return;
		}
		if (arr == null || arr.length < 2) {
			return;
		}

		swap(arr, left + (int) Math.random() * (right - left + 1), right);
		int[] tem = partition(arr, left, right);
		quickSort(arr, left, tem[0] - 1);
		quickSort(arr, tem[1] + 1, right);
	}

	private static int[] partition(int[] arr, int left, int right) { // 返回数组，包含小于区域的边界和大于区域的边界
		int less = left - 1;
		int more = right ;
		int i = left;
		while (i < more) {
			if (arr[i] > arr[right]) {
				swap(arr, --more, i);
			} else if (arr[i] == arr[right]) {
				i++;
			} else {
				swap(arr, ++less, i++);
			}
		}
		swap(arr, more, right);
		return new int[] { less + 1, more };
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
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
			quickSort(arr1, 0, arr1.length - 1);
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
		quickSort(arr, 0, arr.length - 1);
		printArray(arr);

	}

}
