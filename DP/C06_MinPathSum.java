package DP;

// 矩阵的最小路径和
// 【题目】
// 给定一个矩阵 m，从左上角开始每次只能向右或者向下走，最后到达右下角的位置，
// 路径上所有的数字累加起来就是路径和，返回所有的路径中最小的路径和。
// 【举例】
// 如果给定的 m 如下: 1359 8134 5061 8840
// 路径 1，3，1，0，6，1，0 是所有路径中路径和最小的，所以返回12
// NOTE:矩阵不一定是正方形
public class C06_MinPathSum {

	// public static int minPathSum1(int[][] m) {
	// if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
	// return 0;
	// }
	// int row = m.length;
	// int col = m[0].length;
	// int[][] dp = new int[row][col];
	// dp[0][0] = m[0][0];
	// for (int i = 1; i < row; i++) {
	// dp[i][0] = dp[i - 1][0] + m[i][0];
	// }
	// for (int j = 1; j < col; j++) {
	// dp[0][j] = dp[0][j - 1] + m[0][j];
	// }
	// for (int i = 1; i < row; i++) {
	// for (int j = 1; j < col; j++) {
	// dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
	// }
	// }
	// return dp[row - 1][col - 1];
	// }

	// // 节省了空间
	// public static int minPathSum3(int[][] m) {
	// if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
	// return 0;
	// }
	// int more = Math.max(m.length, m[0].length);
	// int less = Math.min(m.length, m[0].length);
	// boolean rowmore = more == m.length;
	// int[] arr = new int[less];
	// arr[0] = m[0][0];
	// for (int i = 1; i < less; i++) {
	// arr[i] = arr[i - 1] + (rowmore ? m[0][i] : m[i][0]);
	// }
	// for (int i = 1; i < more; i++) {
	// arr[0] = arr[0] + (rowmore ? m[i][0] : m[0][i]);
	// for (int j = 1; j < less; j++) {
	// arr[j] = Math.min(arr[j - 1], arr[j]) + (rowmore ? m[i][j] : m[j][i]);
	// }
	// }
	// return arr[less - 1];
	// }

	// 暴力递归
	public static int minPathSum1(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		return process(m, 0, 0);
	}

	private static int process(int[][] arr, int i, int j) {
		if (i == arr.length - 1 && j == arr[0].length - 1) {
			return arr[i][j];
		}
		if (i == arr.length - 1) {
			return arr[i][j] + process(arr, i, j + 1);
		}
		if (j == arr[0].length - 1) {
			return arr[i][j] + process(arr, i + 1, j);
		}
		return arr[i][j] + Math.min(process(arr, i + 1, j), process(arr, i, j + 1));
	}

	// 动态规划
	public static int minPathSum2(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}

		int rowLen = m.length;
		int colLen = m[0].length;
		int[][] cache = new int[rowLen][colLen];
		cache[rowLen - 1][colLen - 1] = m[rowLen - 1][colLen - 1];
		// 最后一行
		for (int lastRow = colLen - 2; lastRow >= 0; lastRow--) {
			cache[rowLen - 1][lastRow] = m[rowLen - 1][lastRow] + cache[rowLen - 1][lastRow + 1];
		}
		// 最后一列
		for (int lasCol = rowLen - 2; lasCol >= 0; lasCol--) {
			cache[lasCol][colLen - 1] = m[lasCol][colLen - 1] + cache[lasCol + 1][colLen - 1];
			// System.out.print(cache[lasCol][rowLen - 1] + " ");
		}
		// 其他任意位置
		for (int i = rowLen - 2; i >= 0; i--) {
			for (int j = colLen - 2; j >= 0; j--) {
				cache[i][j] = m[i][j] + Math.min(cache[i + 1][j], cache[i][j + 1]);
			}
		}
		return cache[0][0];
	}

	// O(N)空间复杂度的动态规划
	// 每次只缓存一行，任意格子的右侧元素就是cache[j + 1], 下侧元素就是还未被覆盖的cache[j]
	public static int minPathSum3(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}

		int rowLen = m.length;
		int colLen = m[0].length;
		int[] cache = new int[colLen];
		cache[colLen - 1] = m[rowLen - 1][colLen - 1];
		// 最后一行
		for (int lastRow = colLen - 2; lastRow >= 0; lastRow--) {
			cache[lastRow] = m[rowLen - 1][lastRow] + cache[lastRow + 1];
		}
		// 其他任意位置
		for (int i = rowLen - 2; i >= 0; i--) {
			cache[colLen - 1] = cache[colLen - 1] + m[i][colLen - 1];
			for (int j = colLen - 2; j >= 0; j--) {
				cache[j] = m[i][j] + Math.min(cache[j], cache[j + 1]);
			}
		}
		return cache[0];
	}

	// for test
	public static int[][] generateRandomMatrix(int rowSize, int colSize) {
		if (rowSize < 0 || colSize < 0) {
			return null;
		}
		int[][] result = new int[rowSize][colSize];
		for (int i = 0; i != result.length; i++) {
			for (int j = 0; j != result[0].length; j++) {
				result[i][j] = (int) (Math.random() * 10);
			}
		}
		return result;
	}

	// for test
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
		int[][] m = generateRandomMatrix(3, 4);
		// int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 }, { 8, 8, 0, 5 }
		// };
		printMatrix(m);
		System.out.println(minPathSum1(m));
		System.out.println(minPathSum2(m));
		System.out.println(minPathSum3(m));
		}
	}
}
