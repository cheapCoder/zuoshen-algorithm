package A3medium.class06;

// 给定一个整型矩阵，返回子矩阵的最大累计和
public class C07_SubMatrixMaxSum {

	// public static int maxSum(int[][] m) {
	// if (m == null || m.length == 0 || m[0].length == 0) {
	// return 0;
	// }
	// int max = Integer.MIN_VALUE;
	// int cur = 0;
	// int[] s = null;
	// for (int i = 0; i != m.length; i++) {
	// s = new int[m[0].length];
	// for (int j = i; j != m.length; j++) {
	// cur = 0;
	// for (int k = 0; k != s.length; k++) {
	// s[k] += m[j][k];
	// cur += s[k];
	// max = Math.max(max, cur);
	// cur = cur < 0 ? 0 : cur;
	// }
	// }
	// }
	// return max;
	// }

	public static int maxSum2(int[][] m) {
		if (m == null || m.length == 0 || m[0].length == 0) {
			return 0;
		}
		int maxSum = Integer.MIN_VALUE;
		int curSum = 0;
		int[] cache = new int[m[0].length];

		for (int i = 0; i < m.length; i++) {
			for (int j = i; j < m.length; j++) {
				curSum = 0;
				for (int k = 0; k < m[0].length; k++) {
					cache[k] += m[j][k];
					curSum += cache[k];
					maxSum = Math.max(maxSum, curSum);
					if (curSum <= 0) {
						curSum = 0;
					}
				}

			}

			for (int j = 0; j < cache.length; j++) {
				cache[j] = 0;
			}
		}

		return maxSum;
	}

	public static void main(String[] args) {
		int[][] matrix = { { -90, 48, 78 }, { 64, -40, 64 }, { -81, -7, 66 } };
		// System.out.println(maxSum(matrix));
		System.out.println(maxSum2(matrix));

	}

}
