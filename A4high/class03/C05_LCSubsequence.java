package A4high.class03;

// 请注意区分子串和子序列的不同，给定两个字符串str1和str2，求两个字符串的最长公共子序列

public class C05_LCSubsequence {

	public static String lcse(String str1, String str2) {
		if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
			return "";
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		int[][] dp = getdp(chs1, chs2);
		int m = chs1.length - 1;
		int n = chs2.length - 1;
		char[] res = new char[dp[m][n]];
		int index = res.length - 1;
		while (index >= 0) {
			if (n > 0 && dp[m][n] == dp[m][n - 1]) {
				n--;
			} else if (m > 0 && dp[m][n] == dp[m - 1][n]) {
				m--;
			} else {
				res[index--] = chs1[m];
				m--;
				n--;
			}
		}
		return String.valueOf(res);
	}

	public static int[][] getdp(char[] str1, char[] str2) {
		int[][] dp = new int[str1.length][str2.length];
		dp[0][0] = str1[0] == str2[0] ? 1 : 0;
		for (int i = 1; i < str1.length; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
		}
		for (int j = 1; j < str2.length; j++) {
			dp[0][j] = Math.max(dp[0][j - 1], str1[0] == str2[j] ? 1 : 0);
		}
		for (int i = 1; i < str1.length; i++) {
			for (int j = 1; j < str2.length; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (str1[i] == str2[j]) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
				}
			}
		}
		return dp;
	}

	public static String lcse2(String str1, String str2) {
		if (str1 == null || str1.length() == 0 || str2 == null || str2.length() == 0) {
			return "";
		}
		char[] arr1 = str1.toCharArray();
		char[] arr2 = str2.toCharArray();

		// 初始化
		int[][] dpCache = new int[arr1.length][arr2.length];
		dpCache[0][0] = arr1[0] == arr2[0] ? 1 : 0;
		for (int i = 1; i < dpCache.length; i++) {
			dpCache[i][0] = arr1[i] == arr2[0] ? 1 : dpCache[i - 1][0];
		}
		for (int i = 1; i < dpCache[0].length; i++) {
			dpCache[0][i] = arr1[0] == arr2[i] ? 1 : dpCache[0][i - 1];
		}

		// 赋值
		for (int i = 1; i < dpCache.length; i++) {
			for (int j = 1; j < dpCache[0].length; j++) {
				dpCache[i][j] = Math.max(dpCache[i - 1][j], dpCache[i][j - 1]);
				if (arr1[i] == arr2[j] && dpCache[i][j] < dpCache[i - 1][j - 1] + 1) {
					dpCache[i][j] = dpCache[i - 1][j - 1] + 1;
				}
			}
		}

		// 查找最长子序列的位置
		int max = Integer.MIN_VALUE;
		int[] pos = new int[2];
		for (int i = 0; i < dpCache.length; i++) {
			for (int j = 0; j < dpCache[0].length; j++) {
				if (max < dpCache[i][j]) {
					max = dpCache[i][j];
					pos[0] = i;
					pos[1] = j;
				}
			}
		}

		// 根据最长的位置，倒推整个字符串，只有从左上角+1得到的结果才要+res
		String res = "";
		while (pos[0] >= 0 && pos[1] >= 0) {
			if (pos[0] == 0) { // 到第一行
				res = (arr1[0] == arr2[pos[1]] ? arr1[0] : "") + res;
				pos[1]--;
			} else if (pos[1] == 0) { // 到第一列
				res = (arr1[pos[0]] == arr2[0] ? arr2[0] : "") + res;
				pos[0]--;
			} else {
				if (arr1[pos[0]] == arr2[pos[1]]) {// 值为左上侧+1
					res = arr1[pos[0]] + res;
					max--;
					pos[0]--;
					pos[1]--;
				} else {
					if (dpCache[pos[0] - 1][pos[1]] > dpCache[pos[0]][pos[1] - 1]) { // 上侧大于左侧
						pos[0]--;
					} else {
						pos[1]--;
					}
				}
			}
		}

		return res;
	}

	public static void main(String[] args) {
		String str1 = "A1BC2D3EFGH45I6JK7LXMN";
		String str2 = "A12OPQ3RST4U5V6W7XYZ";
		System.out.println(lcse(str1, str2));
		// System.out.println(lcse2(str1, str2));

	}
}