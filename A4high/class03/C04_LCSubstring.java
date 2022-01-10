package A4high.class03;

// 请注意区分子串和子序列的不同，给定两个字符串str1和str2，求两个字符串的最长公共子串。

//NOTE: 动态规划空间压缩的技巧: 当动态规划状态转移方程有y=f(x)中y只依赖于单一的x时，完全可以考虑将空间复杂度O(N*M)转为O(1);
public class C04_LCSubstring {

	// public static String lcst1(String str1, String str2) {
	// if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
	// return "";
	// }
	// char[] chs1 = str1.toCharArray();
	// char[] chs2 = str2.toCharArray();
	// int[][] dp = getdp(chs1, chs2);
	// int end = 0;
	// int max = 0;
	// for (int i = 0; i < chs1.length; i++) {
	// for (int j = 0; j < chs2.length; j++) {
	// if (dp[i][j] > max) {
	// end = i;
	// max = dp[i][j];
	// }
	// }
	// }
	// return str1.substring(end - max + 1, end + 1);
	// }

	// public static int[][] getdp(char[] str1, char[] str2) {
	// int[][] dp = new int[str1.length][str2.length];
	// for (int i = 0; i < str1.length; i++) {
	// if (str1[i] == str2[0]) {
	// dp[i][0] = 1;
	// }
	// }
	// for (int j = 1; j < str2.length; j++) {
	// if (str1[0] == str2[j]) {
	// dp[0][j] = 1;
	// }
	// }
	// for (int i = 1; i < str1.length; i++) {
	// for (int j = 1; j < str2.length; j++) {
	// if (str1[i] == str2[j]) {
	// dp[i][j] = dp[i - 1][j - 1] + 1;
	// }
	// }
	// }
	// return dp;
	// }

	// public static String lcst2(String str1, String str2) {
	// if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
	// return "";
	// }
	// char[] chs1 = str1.toCharArray();
	// char[] chs2 = str2.toCharArray();
	// int row = 0;
	// int col = chs2.length - 1;
	// int max = 0;
	// int end = 0;
	// while (row < chs1.length) {
	// int i = row;
	// int j = col;
	// int len = 0;
	// while (i < chs1.length && j < chs2.length) {
	// if (chs1[i] != chs2[j]) {
	// len = 0;
	// } else {
	// len++;
	// }
	// if (len > max) {
	// end = i;
	// max = len;
	// }
	// i++;
	// j++;
	// }
	// if (col > 0) {
	// col--;
	// } else {
	// row++;
	// }
	// }
	// return str1.substring(end - max + 1, end + 1);
	// }

	public static String lcst1(String str1, String str2) {
		if (str1 == null || str1.length() == 0 || str2 == null || str2.length() == 0) {
			return "";
		}
		char[] arr1 = str1.toCharArray();
		char[] arr2 = str2.toCharArray();

		// 初始化
		int[][] dpCache = new int[arr2.length][arr1.length];
		// 列
		for (int i = 0; i < dpCache[0].length; i++) {
			dpCache[0][i] = arr1[i] == arr2[0] ? 1 : 0;
		}
		// 行
		for (int i = 0; i < dpCache.length; i++) {
			dpCache[i][0] = arr1[0] == arr2[i] ? 1 : 0;
		}

		// 求值
		for (int i = 1; i < dpCache.length; i++) {
			for (int j = 1; j < dpCache[0].length; j++) {
				dpCache[i][j] = arr1[j] == arr2[i] ? dpCache[i - 1][j - 1] + 1 : 0;
			}
		}

		String res = "";
		for (int i = 0; i < dpCache.length; i++) {
			for (int j = 0; j < dpCache[0].length; j++) {
				if (dpCache[i][j] > res.length()) {
					res = str1.substring(j - dpCache[i][j] + 1, j + 1);
				}
			}
		}
		return res;
	}

	// 递归方程只是单向依赖时，可以转为O(1)的空间复杂度
	public static String lcst2(String str1, String str2) {
		if (str1 == null || str1.length() == 0 || str2 == null || str2.length() == 0) {
			return "";
		}

		char[] arr1 = str1.toCharArray();
		char[] arr2 = str2.toCharArray();

		String res = arr2[arr2.length - 1] == arr1[0] ? str2.substring(arr2.length - 1) : "";
		String maxStr = res;
		int row = 0;
		int col = arr2.length - 2;
		while (row < arr1.length) {

			int i = row;
			int j = col;
			while (i < arr1.length && j < arr2.length) {
				if (i == 0) {
					res = (arr1[0] == arr2[j] ? "" + arr1[0] : "");
				} else if (j == 0) {
					res = (arr1[i] == arr2[0] ? "" + arr2[0] : "");
				} else {
					res = (arr1[i] == arr2[j] ? res + Character.toString(arr2[j]) : "");
				}
				if (res.length() > maxStr.length()) {
					maxStr = res;
				}
				i++;
				j++;
			}

			if (col == 0) {
				row++;
			} else {
				col--;
			}
		}

		return maxStr;
	}

	public static void main(String[] args) {
		String str1 = "ABCDDDD001234567DEFG";
		String str2 = "HABCDDDDIJKL01234567MNOP";
		System.out.println(lcst1(str1, str2));
		System.out.println(lcst2(str1, str2));
	}

}