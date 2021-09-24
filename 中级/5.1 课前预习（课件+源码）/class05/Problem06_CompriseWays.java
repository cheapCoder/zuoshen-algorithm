package class05;

public class Problem06_CompriseWays {

	// 请保证arr里面都是正数, w也是正数
	public static int ways(int[] arr, int w) {
		if (arr == null || arr.length == 0 || w < 0) {
			return 0;
		}
		int[][] dp = new int[arr.length][w + 1];
		for (int i = 0; i < arr.length; i++) {
			dp[i][0] = 1;
		}
		for (int j = 1; j <= w; j++) {
			dp[0][j] = j >= arr[0] ? 2 : 1;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j <= w; j++) {
				dp[i][j] = dp[i - 1][j];
				if (j - arr[i] >= 0) {
					dp[i][j] += dp[i - 1][j - arr[i]];
				}
			}
		}
		return dp[arr.length - 1][w];
	}

	public static void main(String[] args) {
		int[] arr = { 4, 3, 2, 9 };
		int w = 8;
		System.out.println(ways(arr, w));
	}

}
