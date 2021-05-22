package force_recursion;

public class Code02_CardsInLine {

	// public static int win1(int[] arr) {
	// if (arr == null || arr.length == 0) {
	// return 0;
	// }
	// return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
	// }

	// public static int f(int[] arr, int i, int j) {
	// if (i == j) {
	// return arr[i];
	// }
	// return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
	// }

	// public static int s(int[] arr, int i, int j) {
	// if (i == j) {
	// return 0;
	// }
	// return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));
	// }

	// public static int win2(int[] arr) {
	// if (arr == null || arr.length == 0) {
	// return 0;
	// }
	// int[][] f = new int[arr.length][arr.length];
	// int[][] s = new int[arr.length][arr.length];
	// for (int j = 0; j < arr.length; j++) {
	// f[j][j] = arr[j];
	// for (int i = j - 1; i >= 0; i--) {
	// f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
	// s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
	// }
	// }
	// return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
	// }
	
	// TODO:不明白
	public static int win1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
	}

	private static int f(int[] arr, int left, int right) {
		if (left == right) {
			return arr[left];
		}

		return Math.max(arr[left] + s(arr, left + 1, right), arr[right] + s(arr, left, right - 1));
	}

	private static int s(int[] arr, int left, int right) {
		if (left == right) {
			return 0;
		}
		return Math.min(f(arr, left + 1, right), f(arr, left, right - 1));
	}

	public static int win2(int[] arr) {
		return 0;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 9, 1 };
		System.out.println(win1(arr));
		// System.out.println(win2(arr));

	}

}
