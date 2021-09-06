package skill;

// 将给定的数转换为字符串，原则如下:1对应 a，2对应b，.....26对应z，
// 例如12258可以转换为"abbeh", "aveh", "abyh", "lbeh" and "lyh"，个数为5，
// 编写一个函数，返回可以转换的不同字符串的个数。
public class C13_NumToStringWays {

	// public static int convertWays(int num) {
	// if (num < 1) {
	// return 0;
	// }
	// return process(String.valueOf(num).toCharArray(), 0);
	// }

	// public static int process(char[] str, int index) {
	// if (index == str.length) {
	// return 1;
	// }
	// if (str[index] == '0') {
	// return 0;
	// }
	// int res = process(str, index + 1);
	// if (index == str.length - 1) {
	// return res;
	// }
	// if (((str[index] - '0') * 10 + str[index + 1] - '0') < 27) {
	// res += process(str, index + 2);
	// }
	// return res;
	// }

	// // 动态规划
	// public static int dpways(int num) {
	// if (num < 1) {
	// return 0;
	// }
	// char[] str = String.valueOf(num).toCharArray();
	// int[] dp = new int[str.length + 1];
	// dp[str.length] = 1;
	// dp[str.length - 1] = str[str.length - 1] == '0' ? 0 : 1;
	// for (int i = str.length - 2; i >= 0; i--) {
	// if (str[i] == '0') {
	// dp[i] = 0;
	// } else {
	// dp[i] = dp[i + 1] + (((str[i] - '0') * 10 + str[i + 1] - '0') < 27 ? dp[i +
	// 2] : 0);
	// }
	// }
	// return dp[0];
	// }

	public static int convertWays(int num) {
		if (num <= 0) {
			return 0;
		}

		return process(Integer.toString(num).toCharArray(), 0);
	}

	private static int process(char[] arr, int i) {

		if (i == arr.length) {
			return 1;
		}

		if (i > arr.length || arr[i] == '0') {
			return 0;
		}

		int res = process(arr, i + 1);
		if (arr[i] == '1' || (arr[i] == '2' && arr[i + 1] < '7')) {
			res += process(arr, i + 2);
		}
		return res;
	}

	public static int dpways(int num) {
		String str = String.valueOf(num);
		int[] cache = new int[str.length() + 1];

		cache[cache.length - 1] = 1;
		// cache[str.length() - 1] = 1; // 不算这项，下面的i + 1, i + 2会越界
		for (int i = cache.length - 2; i >= 0; i--) {
			cache[i] = cache[i + 1];
			if (i < cache.length - 2 && (str.charAt(i) == '1' || ( str.charAt(i) == '2' && str.charAt(i + 1) < '7'))) {
				cache[i] += cache[i + 2];
			}
		}
		return cache[0];
	}

	public static void main(String[] args) {
		int test = 1121143311;
		System.out.println(convertWays(test));
		System.out.println(dpways(test));
	}

}
