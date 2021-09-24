package class08;

public class Problem01_ScrambleString {

	public static boolean sameTypeSameNumber(char[] str1, char[] str2) {
		if (str1.length != str2.length) {
			return false;
		}
		int[] map = new int[256];
		for (int i = 0; i < str1.length; i++) {
			map[str1[i]]++;
		}
		for (int i = 0; i < str2.length; i++) {
			if (--map[str2[i]] < 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean isScramble1(String s1, String s2) {
		if ((s1 == null && s2 != null) || (s1 != null && s2 == null)) {
			return false;
		}
		if (s1 == null && s2 == null) {
			return true;
		}
		if (s1.equals(s2)) {
			return true;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		if (!sameTypeSameNumber(str1, str2)) {
			return false;
		}
		int N = s1.length();
		return process(str1, str2, 0, 0, N);
	}

	// 返回str1[从L1开始往右长度为size的子串]和str2[从L2开始往右长度为size的子串]是否互为旋变字符串
	// 在str1中的这一段和str2中的这一段一定是等长的，所以只用一个参数size
	public static boolean process(char[] str1, char[] str2, int L1, int L2,
			int size) {
		if (size == 1) {
			return str1[L1] == str2[L2];
		}
		// 枚举每一种情况，有一个计算出互为旋变就返回true。都算不出来最后返回false
		for (int leftPart = 1; leftPart < size; leftPart++) {
			if ((process(str1, str2, L1, L2, leftPart) && process(str1, str2,
					L1 + leftPart, L2 + leftPart, size - leftPart))
					|| (process(str1, str2, L1, L2 + size - leftPart, leftPart) && process(
							str1, str2, L1 + leftPart, L2, size - leftPart))) {
				return true;
			}
		}
		return false;
	}

	public static boolean isScramble2(String s1, String s2) {
		if ((s1 == null && s2 != null) || (s1 != null && s2 == null)) {
			return false;
		}
		if (s1 == null && s2 == null) {
			return true;
		}
		if (s1.equals(s2)) {
			return true;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		if (!sameTypeSameNumber(str1, str2)) {
			return false;
		}
		int N = s1.length();
		boolean[][][] dp = new boolean[N][N][N + 1];
		for (int L1 = 0; L1 < N; L1++) {
			for (int L2 = 0; L2 < N; L2++) {
				dp[L1][L2][1] = str1[L1] == str2[L2];
			}
		}
		// 第一层for循环含义是：依次填size=2层、size=3层..size=N层，每一层都是一个二维平面
		// 第二、三层for循环含义是：在具体的一层，整个面都要填写，所以用两个for循环去填一个二维面
		// L1的取值氛围是[0,N-size]，因为从L1出发往右长度为size的子串，L1是不能从N-size+1出发的，这样往右就不够size个字符了
		// L2的取值范围同理
		// 第4层for循环完全是递归函数怎么写，这里就怎么改的
		for (int size = 2; size <= N; size++) {
			for (int L1 = 0; L1 <= N - size; L1++) {
				for (int L2 = 0; L2 <= N - size; L2++) {
					for (int leftPart = 1; leftPart < size; leftPart++) {
						if ((dp[L1][L2][leftPart] && dp[L1 + leftPart][L2
								+ leftPart][size - leftPart])
								|| (dp[L1][L2 + size - leftPart][leftPart] && dp[L1
										+ leftPart][L2][size - leftPart])) {
							dp[L1][L2][size] = true;
							break;
						}
					}
				}
			}
		}
		return dp[0][0][N];
	}

	public static void main(String[] args) {
		String test1 = "abcd";
		String test2 = "cdab";
		System.out.println(isScramble1(test1, test2));
		System.out.println(isScramble2(test1, test2));

		test1 = "abcd";
		test2 = "cadb";
		System.out.println(isScramble1(test1, test2));
		System.out.println(isScramble2(test1, test2));

		test1 = "bcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcde";
		test2 = "ebcdeebcdebebcdebcdebcdecdebcbcdcdebcddebcbdebbbcdcdebcdeebcdebcdeebcddeebccdebcdbcdebcd";
		// System.out.println(isScramble1(test1, test2));
		System.out.println(isScramble2(test1, test2));
	}

}
