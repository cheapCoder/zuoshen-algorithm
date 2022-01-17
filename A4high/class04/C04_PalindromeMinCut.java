package A4high.class04;

// 给定一个字符串str，返回把str全部切成回文子串的最小分割数。 
// 【举例】
// str="ABA"。
// 不需要切割，str本身就是回文串，所以返回0。
// str="ACDCDCDAD"。 最少需要切2次变成3个回文子串，比如"A"、"CDCDC"和"DAD"，所以返回2。

// NOTE:从左往右尝试模型，验证是否为回文串时是范围上尝试模型(valid函数)
public class C04_PalindromeMinCut {

	public static int minCut(String str) {
		if (str == null || str.equals("")) {
			return 0;
		}
		char[] chas = str.toCharArray();
		int len = chas.length;
		int[] dp = new int[len + 1];
		dp[len] = -1;
		boolean[][] p = new boolean[len][len];
		for (int i = len - 1; i >= 0; i--) {
			dp[i] = Integer.MAX_VALUE;
			for (int j = i; j < len; j++) {
				if (chas[i] == chas[j] && (j - i < 2 || p[i + 1][j - 1])) {
					p[i][j] = true;
					dp[i] = Math.min(dp[i], dp[j + 1] + 1);
				}
			}
		}
		return dp[0];
	}

	// 暴力递归
	// 问题等价于求最少有多少个回文字符串
	public static int minCut2(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}

		char[] arr = str.toCharArray();
		boolean[][] palindromeArr = getPalindrome1(arr);

		return process(arr, palindromeArr, 0, 0, 0) - 1;
	}

	private static int process(char[] arr, boolean[][] PDArr, int left, int right, int count) {
		if (right == arr.length) {
			return left == arr.length ? count : Integer.MAX_VALUE;
		}

		if (PDArr[left][right]) {
			return Math.min(process(arr, PDArr, right + 1, right + 1, count + 1),
					process(arr, PDArr, left, right + 1, count));
		} else {
			return process(arr, PDArr, left, right + 1, count);
		}
	}

	private static boolean[][] getPalindrome1(char[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		// 判断是否为回文的缓存数组
		boolean[][] palindromeArr = new boolean[arr.length][arr.length];
		for (int i = 0; i < palindromeArr.length; i++) {
			palindromeArr[i][i] = true;
		}
		for (int i = 0; i < palindromeArr.length - 1; i++) {
			palindromeArr[i][i + 1] = arr[i] == arr[i + 1] ? true : false;
		}
		for (int i = arr.length - 3; i >= 0; i--) {
			for (int j = i + 2; j < palindromeArr.length; j++) {
				palindromeArr[i][j] = arr[i] == arr[j] ? palindromeArr[i + 1][j - 1] : false;
			}
		}

		return palindromeArr;
	}

	// 动态规划
	public static int minCut3(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}

		char[] arr = str.toCharArray();
		boolean[][] palindromeArr = getPalindrome1(arr);

		int[] res = new int[arr.length]; // 0-i位置最少有多少个回文
		res[0] = 1;
		for (int j = 1; j < res.length; j++) {
			res[j] = res[j - 1] + 1;
			for (int i = j; i >= 0; i--) {
				if (palindromeArr[i][j]) {
					res[j] = Math.min(res[j], (i == 0 ? 0 : res[i - 1]) + 1);
				}
			}
		}

		return res[res.length - 1] - 1;
	}

	// for test
	public static String getRandomStringOnlyAToD(int len) {
		int range = 'D' - 'A' + 1;
		char[] charArr = new char[(int) (Math.random() * (len + 1))];
		for (int i = 0; i != charArr.length; i++) {
			charArr[i] = (char) ((int) (Math.random() * range) + 'A');
		}
		return String.valueOf(charArr);
	}

	public static void main(String[] args) {
		int maxLen = 10;
		int testTimes = 1000000;
		String str = null;
		for (int i = 0; i != testTimes; i++) {
			str = getRandomStringOnlyAToD(maxLen);
			if (minCut(str) != minCut3(str)) {
				System.out.print("\"" + str + "\"" + " : ");
				System.out.println(minCut(str) + "|" + minCut3(str));
			}
		}
	}
}
