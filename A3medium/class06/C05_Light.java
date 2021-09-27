package A3medium.class06;

// 小Q正在给一条长度为n的道路设计路灯安置方案。
// 为了让问题更简单,小Q把道路视为n个方格,需要照亮的地方用'.'表示, 不需要照亮的障碍物格子用'X'表示。
// 小Q现在要在道路上设置一些路灯, 对于安置在 pos位置的路灯, 这盏路灯可以照亮pos - 1, pos, pos + 1这三个位置。 
// 小Q希望能安置尽量少的路灯照亮所有'.'区域, 希望你能帮他计算一下最少需要多少盏路灯。
// 输入描述:
// 输入的第一行包含一个正整数t(1 <= t <= 1000), 表示测试用例数 接下来每两行一个测试数据,
//  第一行一个正整数n(1 <= n <= 1000),表示道路的长度。第二行一个字符串s表示道路的构造,只包含'.'和'X'。
// 输出描述:
// 对于每个测试用例, 输出一个正整数表示最少需要多少盏路灯。

// NOTE: 动态规划 | 技巧
public class C05_Light {

	// public static int minLight1(String s) {
	// if (s == null || s.length() == 0) {
	// return 0;
	// }
	// char[] str = s.toCharArray();
	// char[] help = new char[str.length + 2];
	// help[0] = 'X';
	// help[str.length] = 'X';
	// for (int i = 0; i < str.length; i++) {
	// help[i + 1] = str[i];
	// }
	// return process(help, 1, true);
	// }

	// 当前来到了i位置
	// 函数潜台词：help[0..i-2]上都已经点亮了
	// pre表示i-1位置是否点亮
	// 返回如果把所有位置都点亮，help[i..最后]需要几盏灯
	// process(help, i, true)：表示help[0..i-2]上都已经点亮了，i-1位置也点亮的情况下，help[i..最后]需要几盏灯
	// process(help, i, false)：表示help[0..i-2]上都已经点亮了，但是i-1位置没亮的情况下，help[i..最后]需要几盏灯
	// public static int process(char[] help, int i, boolean pre) {
	// if (i == help.length) {
	// return 0;
	// }
	// if (i == help.length - 1) {
	// return pre ? 0 : Integer.MAX_VALUE;
	// }
	// int ans = Integer.MAX_VALUE;
	// int restLight = 0;
	// if (pre) {
	// if (help[i] == 'X') {
	// restLight = process(help, i + 1, true);
	// if (restLight != Integer.MAX_VALUE) {
	// ans = Math.min(ans, restLight);
	// }
	// } else {
	// restLight = process(help, i + 1, false);
	// if (restLight != Integer.MAX_VALUE) {
	// ans = Math.min(ans, restLight);
	// }
	// restLight = process(help, i + 2, true);
	// if (restLight != Integer.MAX_VALUE) {
	// ans = Math.min(ans, restLight + 1);
	// }
	// }
	// } else {
	// restLight = process(help, i + 2, true);
	// if (restLight != Integer.MAX_VALUE) {
	// ans = Math.min(ans, restLight + 1);
	// }
	// }
	// return ans;
	// }

	// public static int minLight2(String s) {
	// if (s == null || s.length() == 0) {
	// return 0;
	// }
	// char[] str = s.toCharArray();
	// char[] help = new char[str.length + 2];
	// help[0] = 'X';
	// help[str.length] = 'X';
	// for (int i = 0; i < str.length; i++) {
	// help[i + 1] = str[i];
	// }
	// int[][] dp = new int[help.length + 1][2];
	// dp[help.length][0] = 0;
	// dp[help.length][1] = 0;
	// dp[help.length - 1][0] = Integer.MAX_VALUE;
	// dp[help.length - 1][1] = 0;
	// for (int i = help.length - 2; i >= 1; i--) {
	// dp[i][0] = Integer.MAX_VALUE;
	// dp[i][1] = Integer.MAX_VALUE;
	// int restLight = 0;
	// if (help[i] == 'X') {
	// restLight = dp[i + 1][1];
	// if (restLight != Integer.MAX_VALUE) {
	// dp[i][1] = Math.min(dp[i][1], restLight);
	// }
	// } else {
	// restLight = dp[i + 1][0];
	// if (restLight != Integer.MAX_VALUE) {
	// dp[i][1] = Math.min(dp[i][1], restLight);
	// }
	// restLight = dp[i + 2][1];
	// if (restLight != Integer.MAX_VALUE) {
	// dp[i][1] = Math.min(dp[i][1], restLight + 1);
	// }
	// }
	// restLight = dp[i + 2][1];
	// if (restLight != Integer.MAX_VALUE) {
	// dp[i][0] = Math.min(dp[i][0], restLight + 1);
	// }
	// }
	// return dp[1][1];
	// }

	// 暴力递归
	public static int minLight1(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		return process(s.toCharArray(), 0, 0);
	}

	public static int process(char[] arr, int i, int count) {
		if (i >= arr.length) {
			return count;
		}
		if (arr[i] == '.') {
			count++;
			if (i + 1 < arr.length && arr[i + 1] == '.') {
				i += 3;
			} else {
				i += 2;
			}
		} else {
			i++;
		}
		return process(arr, i, count);
	}

	// 记忆化缓存
	public static int minLight2(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		int[] cache = new int[s.length()];
		return process2(s.toCharArray(), cache, 0, 0);
	}

	public static int process2(char[] arr, int[] cache, int i, int count) {
		if (i >= arr.length) {
			cache[i] = count;
		} else {
			if (arr[i] == '.') {
				count++;
				if (i + 1 < arr.length && arr[i + 1] == '.') {
					i += 3;
				} else {
					i += 2;
				}
			} else {
				i++;
			}
			cache[i] = process(arr, i, count);
		}
		return cache[i];
	}

	// 动态规划
	public static int minLight3(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int[] cache = new int[s.length() + 1];
		cache[0] = s.charAt(0) == '.' ? 1 : 0;
		for (int i = 0; i < cache.length; i++) {
			cache[i] = 0;
		}

		for (int i = 0; i < cache.length;) {
			if (s.charAt(i) == '.') {
				cache[i] = i == 0 ? 1 : cache[i - 1] + 1;
				if (i + 1 < s.length() && s.charAt(i + 1) == '.') {
					cache[i + 1] = cache[i];
					cache[i + 2] = cache[i];
					i += 3;
				} else {
					cache[i + 1] = cache[i];
					i += 2;
				}
			} else {
				cache[i] = i == 0 ? 0 : cache[i - 1];
				i++;
			}
		}
		return cache[s.length()];
	}

	// NOTE:结果和左神的不一样，左神的写法错了
	// 贪心
	// 假设任意位置i之前都已经照亮，分析讨论i位置的情况
	public static int minLight4(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] arr = s.toCharArray();
		int res = 0;
		int i = 0;
		while (i < arr.length) {
			if (arr[i] == '.') {
				res++;
				if (i + 1 < arr.length && arr[i + 1] == '.') {
					i += 3;
				} else {
					i += 2;
				}
			} else {
				i++;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		String test = "...X.X.X..XX.XX.X.X.X.X.XX.XXX.X.XXX.XX.";
		System.out.println(minLight1(test));
		System.out.println(minLight2(test));
		System.out.println(minLight3(test));
		System.out.println(minLight4(test));
	}
}
