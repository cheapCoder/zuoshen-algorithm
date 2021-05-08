package sort;

public class Code14_NetherlandsFlag {

	// public static int[] partition(int[] arr, int l, int r, int p) {
	// int less = l - 1;
	// int more = r + 1;
	// while (l < more) {
	// if (arr[l] < p) {
	// swap(arr, ++less, l++);
	// } else if (arr[l] > p) {
	// swap(arr, --more, l);
	// } else {
	// l++;
	// }
	// }
	// return new int[] { less + 1, more - 1 };
	// }

	public static int[] partition(int[] arr, int start, int end, int val) {
		int left = start - 1;
		int right = end + 1;
		int i = start;
		while (i < right) {
			if (arr[i] > val) {
				swap(arr, i, --right);
			} else if (arr[i] == val) {
				i++;
			} else {
				swap(arr, i++, ++left);
			}
		}

		return new int[] { left + 1, right - 1 };
	}

	// for test
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// for test
	public static int[] generateArray() {
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 3);
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

	public static void main(String[] args) {
		int[] test = generateArray();

		printArray(test);
		int[] res = partition(test, 0, test.length - 1, 1);
		printArray(test);
		System.out.println(res[0]);
		System.out.println(res[1]);

	}
}
