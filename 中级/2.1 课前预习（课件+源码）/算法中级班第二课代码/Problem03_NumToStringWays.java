package class02;

public class Problem03_NumToStringWays {

	public static int convertWays(int num) {
		if (num < 1) {
			return 0;
		}
		return process(String.valueOf(num).toCharArray(), 0);
	}

	public static int process(char[] str, int index) {
		if (index == str.length) {
			return 1;
		}
		if (str[index] == '0') {
			return 0;
		}
		int res = process(str, index + 1);
		if (index == str.length - 1) {
			return res;
		}
		if (((str[index] - '0') * 10 + str[index + 1] - '0') < 27) {
			res += process(str, index + 2);
		}
		return res;
	}

	// 还能简化
	public static int dpways(int num) {
		if (num < 1) {
			return 0;
		}
		char[] str = String.valueOf(num).toCharArray();
		int[] dp = new int[str.length + 1];
		dp[str.length] = 1;
		dp[str.length - 1] = str[str.length - 1] == '0' ? 0 : 1;
		for (int i = str.length - 2; i >= 0; i--) {
			if (str[i] == '0') {
				dp[i] = 0;
			} else {
				dp[i] = dp[i + 1]
						+ (((str[i] - '0') * 10 + str[i + 1] - '0') < 27 ? dp[i + 2]
								: 0);
			}
		}
		return dp[0];
	}

	public static void main(String[] args) {
		int test = 111143311;
		System.out.println(convertWays(test));
		System.out.println(dpways(test));
	}

}
