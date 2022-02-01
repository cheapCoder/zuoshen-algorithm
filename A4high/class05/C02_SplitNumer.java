package A4high.class05;

// (1) 给定一个正数1，裂开的方法有一种，(1)
// (2) 给定一个正数2，裂开的方法有两种，(1和1)、(2)
// (3) 给定一个正数3，裂开的方法有三种，(1、1、1)、(1、2)、(3)
// (4) 给定一个正数4，裂开的方法有五种，(1、1、1、1)、(1、1、2)、(1、3)、(2、2)、 (4)
// 给定一个正数n，求裂开的方法数。 

// 动态规划优化状态依赖的技巧
public class C02_SplitNumer {

	public static int ways1(int n) {
		if (n < 1) {
			return 0;
		}
		return process(1, n);
	}

	public static int process(int pre, int rest) {
		if (rest == 0) {
			return 1;
		}
		if (pre > rest) {
			return 0;
		}
		int ways = 0;
		for (int i = pre; i <= rest; i++) {
			ways += process(i, rest - i);
		}
		return ways;
	}

	public static int ways2(int n) {
		if (n < 1) {
			return 0;
		}
		int[][] dp = new int[n + 1][n + 1];
		for (int pre = 1; pre < dp.length; pre++) {
			dp[pre][0] = 1;
		}
		for (int pre = n; pre > 0; pre--) {
			for (int rest = pre; rest <= n; rest++) {
				for (int i = pre; i <= rest; i++) {
					dp[pre][rest] += dp[i][rest - i];
				}
			}
		}
		return dp[1][n];
	}

	public static int ways3(int n) {
		if (n < 1) {
			return 0;
		}
		int[][] dp = new int[n + 1][n + 1];
		for (int pre = 1; pre < dp.length; pre++) {
			dp[pre][0] = 1;
		}
		for (int pre = 1; pre < dp.length; pre++) {
			dp[pre][pre] = 1;
		}
		for (int pre = n - 1; pre > 0; pre--) {
			for (int rest = pre + 1; rest <= n; rest++) {
				dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
			}
		}
		return dp[1][n];
	}

	// 暴力递归
	public static int ways4(int n) {
		if (n <= 0) {
			return 0;
		}
		return process6(1, n);
	}

	private static int process6(int min, int remain) {
		// 注意要优先判断返回1的条件
		if (remain == 0) {
			return 1;
		}
		if (min > remain) {
			return 0;
		}

		int res = 0;
		for (int i = min; i <= remain; i++) {
			res += process6(i, remain - i);
		}

		return res;
	}

	// 斜率优化
	public static int ways5(int n) {
		if (n <= 0) {
			return 0;
		}

		int[][] dpCache = new int[n + 1][n + 1]; // 第一行不用; 行为min，列为remain
		for (int i = 0; i < dpCache.length; i++) {
			dpCache[i][0] = 1;
			dpCache[i][i] = 1;
		}

		// 一般动态规划
		// for (int i = dpCache.length - 1; i >= 1; i--) {
		// for (int j = i; j < dpCache[0].length; j++) {
		// for (int k = i; k <= j; k++) {
		// dpCache[i][j] += dpCache[k][j - k];
		// }
		// }
		// }

		// 斜率优化
		for (int i = dpCache.length - 2; i >= 1; i--) {
			for (int j = i + 1; j < dpCache[0].length; j++) {
				// 斜率优化
				dpCache[i][j] = dpCache[i][j - i] + dpCache[i + 1][j];
			}
		}

		return dpCache[1][n];
	}

	public static void main(String[] args) {
		int n = 2;
		for (int i = 0; i < 100000; i++) {
			n = (int) (Math.random() * 50) + 4;
			int ans = ways3(n);
			int my = ways5(n);

			if (ans != my) {
				System.out.println(n + ": " + ans);
				System.out.println(n + ": " + my);
				System.out.println("-----");
			}
		}
		System.out.println(ways5(4));
	}

}
