package A4high.class07;

// 判定一个由[a-z]字符构成的字符串和一个包含'.'和'*'通配符的字符串是否匹配。通配符'.'匹配任意单一字符,'*'匹配任意多个字符包括0个字符。字符串长度不会超过100，字符串不为空。

// 输入描述:
// 字符串str和包含通配符的字符串pattern。字符串长度为[0,100]输出描述: true表示匹配，false表示不匹配

// NOTE: 暴力递归改写动态规划时，若base case不全，则根据题意手动不全所需的base case，从而写出一般情况的状态转移方程
public class C01_RegularExpressionMatch {

	public static boolean isValid(char[] s, char[] e) {
		for (int i = 0; i < s.length; i++) {
			if (s[i] == '*' || s[i] == '.') {
				return false;
			}
		}
		for (int i = 0; i < e.length; i++) {
			if (e[i] == '*' && (i == 0 || e[i - 1] == '*')) {
				return false;
			}
		}
		return true;
	}

	public static boolean isMatch(String str, String exp) {
		if (str == null || exp == null) {
			return false;
		}
		char[] s = str.toCharArray();
		char[] e = exp.toCharArray();
		return isValid(s, e) ? process(s, e, 0, 0) : false;
	}

	public static boolean process(char[] s, char[] e, int si, int ei) {
		if (ei == e.length) {
			return si == s.length;
		}
		if (ei + 1 == e.length || e[ei + 1] != '*') {
			return si != s.length && (e[ei] == s[si] || e[ei] == '.') && process(s, e, si + 1, ei + 1);
		}
		while (si != s.length && (e[ei] == s[si] || e[ei] == '.')) {
			if (process(s, e, si, ei + 2)) {
				return true;
			}
			si++;
		}
		return process(s, e, si, ei + 2);
	}

	public static boolean isMatchDP(String str, String exp) {
		if (str == null || exp == null) {
			return false;
		}
		char[] s = str.toCharArray();
		char[] e = exp.toCharArray();
		if (!isValid(s, e)) {
			return false;
		}
		boolean[][] dp = initDPMap(s, e);
		for (int i = s.length - 1; i > -1; i--) {
			for (int j = e.length - 2; j > -1; j--) {
				if (e[j + 1] != '*') {
					dp[i][j] = (s[i] == e[j] || e[j] == '.') && dp[i + 1][j + 1];
				} else {
					int si = i;
					while (si != s.length && (s[si] == e[j] || e[j] == '.')) {
						if (dp[si][j + 2]) {
							dp[i][j] = true;
							break;
						}
						si++;
					}
					if (dp[i][j] != true) {
						dp[i][j] = dp[si][j + 2];
					}
				}
			}
		}

		printArray(dp);
		return dp[0][0];
	}

	public static boolean[][] initDPMap(char[] s, char[] e) {
		int slen = s.length;
		int elen = e.length;
		boolean[][] dp = new boolean[slen + 1][elen + 1];
		dp[slen][elen] = true;
		for (int j = elen - 2; j > -1; j = j - 2) {
			if (e[j] != '*' && e[j + 1] == '*') {
				dp[slen][j] = true;
			} else {
				break;
			}
		}
		if (slen > 0 && elen > 0) {
			if ((e[elen - 1] == '.' || s[slen - 1] == e[elen - 1])) {
				dp[slen - 1][elen - 1] = true;
			}
		}
		return dp;
	}

	public static boolean isMatch2(String str, String exp) {
		if (str == null || str.length() == 0 || exp == null || exp.length() == 0) {
			return false;
		}
		char[] strArr = str.toCharArray();
		char[] expArr = exp.toCharArray();
		if (!isValid2(strArr, expArr)) {
			return false;
		}
		return process2(strArr, expArr, 0, 0);
	}

	private static boolean process2(char[] str, char[] exp, int strI, int expI) {
		if (strI == str.length || expI == exp.length) {
			return strI == str.length && expI == exp.length ? true : false;
		}

		if (expI + 1 < exp.length && exp[expI + 1] == '*') {
			if (exp[expI] == '.') {
				for (int i = strI; i <= str.length; i++) {
					if (process2(str, exp, i, expI + 2)) {
						return true;
					}
				}
				return false;
			} else {
				while (strI < str.length && str[strI] == exp[expI]) {
					if (process2(str, exp, strI, expI)) {
						return true;
					}
					strI++;
				}
				return false;
			}
		} else {
			return exp[expI] == '.' || exp[expI] == str[strI] ? process2(str, exp, strI + 1, expI + 1) : false;
		}
	}

	private static boolean isValid2(char[] str, char[] exp) {
		if (str == null || exp == null) {
			return false;
		}
		for (int i = 0; i < str.length; i++) {
			if (str[i] < 'a' || str[i] > 'z') {
				return false;
			}
		}
		for (int i = 0; i < exp.length; i++) {
			if ((exp[i] < 'a' || exp[i] > 'z') && exp[i] != '.' && exp[i] != '*') {
				return false;
			}
			if ((exp[i] == '*') && (i == 0 || ((exp[i - 1] > 'z' || exp[i - 1] < 'a') && exp[i - 1] != '.'))) {
				return false;
			}
		}

		return true;
	}

	public static boolean isMatch3(String str, String exp) {
		if (str == null || str.length() == 0 || exp == null || exp.length() == 0) {
			return false;
		}
		char[] strArr = str.toCharArray();
		char[] expArr = exp.toCharArray();
		if (!isValid2(strArr, expArr)) {
			return false;
		}

		int row = strArr.length + 1;
		int col = expArr.length + 1;

		// 初始化
		boolean[][] dpCache = new boolean[row][col]; // 默认值就是false了，只用改true的
		dpCache[row - 1][col - 1] = true;

		// 从递归的process方法中可看出，有expI+2的，所以base case至少要最后两列，少的倒数第二列需要手动补齐
		dpCache[row - 1][col - 2] = expArr[col - 2] == '.' || expArr[col - 2] == strArr[row - 2] ? true : false;

		for (int i = row - 2; i >= 0; i--) {
			for (int j = col - 3; j >= 0; j--) {

				if (j + 1 < expArr.length && expArr[j + 1] == '*') {
					if (expArr[j] == '.') {
						for (int k = i; k <= strArr.length; k++) {
							if (dpCache[k][j + 2]) {
								dpCache[i][j] = true;
								break;
							}
						}
					} else {
						int k = i;
						while (k < strArr.length && strArr[k] == expArr[j]) {
							if (dpCache[k][j]) {
								dpCache[i][j] = true;
							}
							k++;
						}
					}
				} else {
					dpCache[i][j] = expArr[j] == '.' || expArr[j] == strArr[i] ? dpCache[i + 1][j + 1] : false;
				}
			}
		}
		printArray(dpCache);
		return dpCache[0][0];
	}

	// for test
	public static void printArray(boolean[][] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		System.out.println("-------");
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		String str = "abcccdefg";
		String exp = "ab.*d.*e.*";
		System.out.println(isMatch3(str, exp));
		System.out.println(isMatchDP(str, exp));

	}

}
