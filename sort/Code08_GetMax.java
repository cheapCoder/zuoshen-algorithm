package sort;

public class Code08_GetMax {

	public static int getMax(int[] arr) {
		return process(arr, 0, arr.length - 1);
	}

	// public static int process_answer(int[] arr, int L, int R) {
	// 	if (L == R) {
	// 		return arr[L];
	// 	}
	// 	int mid = L + ((R - L) >> 1);
	// 	int leftMax = process(arr, L, mid);
	// 	int rightMax = process(arr, mid + 1, R);
	// 	return Math.max(leftMax, rightMax);
	// }

	public static int process(int[] arr, int L, int R) {
		if (R - L == 1) {
			return Math.max(arr[L], arr[R]);
		}

		int midIndex = (int) Math.floor(L + (R - L) >> 1);
		int leftMax = process(arr, L, midIndex);
		int rightMax = process(arr, midIndex + 1, R);
		return Math.max(leftMax, rightMax);
	}

}
