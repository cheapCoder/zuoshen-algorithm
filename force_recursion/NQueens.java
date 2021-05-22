package force_recursion;

public class NQueens {

	// public static int num1(int n) {
	// if (n < 1) {
	// return 0;
	// }
	// int[] record = new int[n];
	// return process1(0, record, n);
	// }

	// public static int process1(int i, int[] record, int n) {
	// if (i == n) {
	// return 1;
	// }
	// int res = 0;
	// for (int j = 0; j < n; j++) {
	// if (isValid(record, i, j)) {
	// record[i] = j;
	// res += process1(i + 1, record, n);
	// }
	// }
	// return res;
	// }

	// public static boolean isValid(int[] record, int i, int j) {
	// for (int k = 0; k < i; k++) {
	// if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
	// return false;
	// }
	// }
	// return true;
	// }

	// public static int num2(int n) {
	// if (n < 1 || n > 32) {
	// return 0;
	// }
	// int upperLim = n == 32 ? -1 : (1 << n) - 1;
	// return process2(upperLim, 0, 0, 0);
	// }

	// public static int process2(int upperLim, int colLim, int leftDiaLim,
	// int rightDiaLim) {
	// if (colLim == upperLim) {
	// return 1;
	// }
	// int pos = 0;
	// int mostRightOne = 0;
	// pos = upperLim & (~(colLim | leftDiaLim | rightDiaLim));
	// int res = 0;
	// while (pos != 0) {
	// mostRightOne = pos & (~pos + 1);
	// pos = pos - mostRightOne;
	// res += process2(upperLim, colLim | mostRightOne,
	// (leftDiaLim | mostRightOne) << 1,
	// (rightDiaLim | mostRightOne) >>> 1);
	// }
	// return res;
	// }

	// 法一
	public static int num1(int n) {
		if (n <= 0) {
			return 0;
		}
		int[] record = new int[n]; // 索引i表示i行，record[i]表示第几列
		for (int i = 0; i < record.length; i++) {
			record[i] = -1;
		}
		return process(n, 0, record);
	}

	private static int process(int n, int i, int[] record) {
		if (i == n) { // i从0开始
			return 1;
		}
		int res = 0;
		for (int j = 0; j < n; j++) {
			if (isValid(record, i, j)) {
				record[i] = j;
				res += process(n, i + 1, record);
				record[i] = -1;
			}
		}
		return res;
	}

	private static boolean isValid(int[] record, int row, int col) {
		for (int i = 0; i < row; i++) {
			if (record[i] == col || Math.abs(record[i] - col) == Math.abs(i - row)) {
				return false;
			}
		}
		return true;
	}

	// 法二
	public static int num2(int n) {
		if (n > 32 || n < 1) {
			return 0;
		}
		int range = (1 << n) - 1;
		return process2(range, 0, 0, 0);
	}

	private static int process2(int range, int leftRestrict, int colRestrict, int rightRestrict) {
		if (range == colRestrict) {
			return 1;
		}
		int res = 0;
		int restrict = ~(colRestrict | leftRestrict | rightRestrict);
		int mostRightOne = range & (restrict & (~restrict + 1));

		while (mostRightOne != 0) {
			res += process2(range, 
					range & (leftRestrict | mostRightOne) << 1,
					range & (colRestrict | mostRightOne),
					range & (rightRestrict | mostRightOne) >>> 1
					);
			restrict -= mostRightOne;
			mostRightOne = range & (restrict & (~restrict + 1));
		}
		return res;
	}
	public static void main(String[] args) {
		int n = 14;

		long start = System.currentTimeMillis();
		System.out.println(num2(n));
		long end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + "ms");

		start = System.currentTimeMillis();
		System.out.println(num1(n));
		end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + "ms");

	}
}
