package A4high.class04;

// 给定一个字符串str，如果可以在str的任意位置添加字符，请返回在添加字符最少的情况下，让str整体都是回文字符串的一种结果。
// 【举例】
// str="ABA"。str本身就是回文串，不需要添加字符，所以返回"ABA"。
// str="AB"。可以在'A'之前添加'B'，使str整体都是回文串，故可以返回"BAB"。
// 也可以在'B'之后添加'A'，使str整体都是回文串，故也可以返回"ABA"。
// 总之，只要添加的字符数最少，返回其中一种结果即可。

// NOTE:范围上尝试模型
public class C03_PalindromeMinAdd {

	// public static String getPalindrome1(String str) {
	// 	if (str == null || str.length() < 2) {
	// 		return str;
	// 	}
	// 	char[] chas = str.toCharArray();
	// 	int[][] dp = getDP(chas);
	// 	char[] res = new char[chas.length + dp[0][chas.length - 1]];
	// 	int i = 0;
	// 	int j = chas.length - 1;
	// 	int resl = 0;
	// 	int resr = res.length - 1;
	// 	while (i <= j) {
	// 		if (chas[i] == chas[j]) {
	// 			res[resl++] = chas[i++];
	// 			res[resr--] = chas[j--];
	// 		} else if (dp[i][j - 1] < dp[i + 1][j]) {
	// 			res[resl++] = chas[j];
	// 			res[resr--] = chas[j--];
	// 		} else {
	// 			res[resl++] = chas[i];
	// 			res[resr--] = chas[i++];
	// 		}
	// 	}
	// 	return String.valueOf(res);
	// }

	// public static int[][] getDP(char[] str) {
	// 	int[][] dp = new int[str.length][str.length];
	// 	for (int j = 1; j < str.length; j++) {
	// 		dp[j - 1][j] = str[j - 1] == str[j] ? 0 : 1;
	// 		for (int i = j - 2; i > -1; i--) {
	// 			if (str[i] == str[j]) {
	// 				dp[i][j] = dp[i + 1][j - 1];
	// 			} else {
	// 				dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
	// 			}
	// 		}
	// 	}
	// 	return dp;
	// }

	public static String getPalindrome2(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}

		char[] arr = str.toCharArray();
		String[][] dpCache = new String[arr.length][arr.length];
		for (int i = 0; i < dpCache.length - 1; i++) {
			dpCache[i][i] = String.valueOf(arr[i]);
			dpCache[i][i + 1] = String.valueOf(arr[i] == arr[i + 1] ? new char[] { arr[i], arr[i + 1] }
					: new char[] { arr[i], arr[i + 1], arr[i] });
		}
		dpCache[arr.length - 1][arr.length - 1] = String.valueOf(arr[arr.length - 1]);

		for (int i = dpCache.length - 3; i >= 0; i--) {
			for (int j = i + 2; j < dpCache[0].length; j++) {
				// 左侧和下侧
				dpCache[i][j] = dpCache[i][j - 1].length() > dpCache[i + 1][j].length() ? arr[i] + dpCache[i + 1][j] + arr[i]
						: arr[j] + dpCache[i][j - 1] + arr[j];
				// 对比左下侧
				if (arr[i] == arr[j] && dpCache[i + 1][j - 1].length() + 2 < dpCache[i][j].length()) {
					dpCache[i][j] = arr[i] + dpCache[i + 1][j - 1] + arr[i];
				}
			}
		}

		return dpCache[0][arr.length - 1];
	}

	public static void main(String[] args) {
		String str = "AB1CD2EFG3H43IJK2L1MMN";
		// System.out.println(getPalindrome1(str));
		// 长度得一样,具体的结果字符串可能有多种,保证为回文字符串即可
		System.out.println(getPalindrome2(str));
	}

}
