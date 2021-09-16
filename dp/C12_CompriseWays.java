package dp;

// 牛牛准备参加学校组织的春游,出发前牛牛准备往背包里装入一些零食,牛牛的背包容量为w。
// 牛牛家里一共有n袋零食,第i袋零食体积为v[i]。牛牛想知道在总体积不超过背包容量的情况下，他一共有多少种零食放法(总体积为0也算一种放法)。
public class C12_CompriseWays {

	// 请保证arr里面都是正数, w也是正数
	// public static int ways(int[] arr, int w) {
	// if (arr == null || arr.length == 0 || w < 0) {
	// return 0;
	// }
	// int[][] dp = new int[arr.length][w + 1];
	// for (int i = 0; i < arr.length; i++) {
	// dp[i][0] = 1;
	// }
	// for (int j = 1; j <= w; j++) {
	// dp[0][j] = j >= arr[0] ? 2 : 1;
	// }
	// for (int i = 1; i < arr.length; i++) {
	// for (int j = 1; j <= w; j++) {
	// dp[i][j] = dp[i - 1][j];
	// if (j - arr[i] >= 0) {
	// dp[i][j] += dp[i - 1][j - arr[i]];
	// }
	// }
	// }
	// return dp[arr.length - 1][w];
	// }

	// 暴力递归
	public static int ways(int[] arr, int w) {
		if (arr == null || arr.length == 0 || w < 0) {
			return 0;
		}

		return process(arr, 0, w);
	}

	private static int process(int[] arr, int i, int w) {
		if (w < 0) {
			return 0;
		}
		if (i == arr.length) {
			return 1;
		}
		return process(arr, i + 1, w) + process(arr, i + 1, w - arr[i]);
	}

	// 记忆化缓存
	public static int ways2(int[] arr, int w) {
		if (arr == null || arr.length == 0 || w < 0) {
			return 0;
		}
		int[][] cache = new int[arr.length][w + 1];
		for (int i = 0; i < cache.length; i++) {
			for (int j = 0; j < cache[i].length; j++) {
				cache[i][j] = -1;
			}
		}
		return process(arr, cache, 0, w);
	}

	private static int process(int[] arr, int[][] cache, int i, int w) {
		if (cache[i][w] == -1) {
			if (w < 0) {
				cache[i][w] = 0;
			} else if (i == arr.length) {
				cache[i][w] = 1;
			} else {
				cache[i][w] = process(arr, i + 1, w) + process(arr, i + 1, w - arr[i]);
			}
		}
		return cache[i][w];
	}

	// 动态规划
	public static int ways3(int[] arr, int w) {
		if (arr == null || arr.length == 0 || w < 0) {
			return 0;
		}

		int[][] cache = new int[arr.length + 1][w + 1];
		for (int i = 0; i < w + 1; i++) {
			cache[arr.length][i] = 1;
		}

		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j = 0; j < w + 1; j++) {
				cache[i][j] = cache[i + 1][j] + (j - arr[i] >= 0 ? cache[i + 1][j - arr[i]] : 0);
			}
		}

		return cache[0][w];
	}

	// 空间优化为O(N)的动态规划
	public static int ways4(int[] arr, int w) {
		if (arr == null || arr.length == 0 || w < 0) {
			return 0;
		}

		int[] cache = new int[w + 1];
		for (int i = 0; i < w + 1; i++) {
			cache[i] = 1;
		}

		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j = w; j >= 0; j--) {
				cache[j] = cache[j] + (j - arr[i] >= 0 ? cache[j - arr[i]] : 0);
			}
		}

		return cache[w];
	}

	public static void main(String[] args) {
		int[] arr = { 4, 3, 2, 9, 7 };
		int w = 8;
		System.out.println(ways(arr, w));
		System.out.println(ways2(arr, w));
		System.out.println(ways3(arr, w));
		System.out.println(ways4(arr, w));
	}

}
