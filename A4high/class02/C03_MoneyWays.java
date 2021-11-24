package A4high.class02;

// 现有n1+n2种面值的硬币，其中前n1种为普通币，可以取任意枚，
// 后n2种为纪念币，每种最多只能取一枚，每种硬币有一个面值，问能用多少种方法拼出m的面值?
public class C03_MoneyWays {

	public static int moneyWays(int[] arbitrary, int[] onlyone, int money) {
		if (money < 0) {
			return 0;
		}
		if ((arbitrary == null || arbitrary.length == 0) && (onlyone == null || onlyone.length == 0)) {
			return money == 0 ? 1 : 0;
		}
		int[][] dparb = getDpArb(arbitrary, money);
		int[][] dpone = getDpOne(onlyone, money);
		if (dparb == null) {
			return dpone[dpone.length - 1][money];
		}
		if (dpone == null) {
			return dparb[dparb.length - 1][money];
		}
		int res = 0;
		for (int i = 0; i <= money; i++) {
			res += dparb[dparb.length - 1][i] * dpone[dpone.length - 1][money - i];
		}
		return res;
	}

	public static int[][] getDpArb(int[] arr, int money) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		int[][] dp = new int[arr.length][money + 1];
		for (int i = 0; i < arr.length; i++) {
			dp[i][0] = 1;
		}
		for (int j = 1; arr[0] * j <= money; j++) {
			dp[0][arr[0] * j] = 1;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j <= money; j++) {
				dp[i][j] = dp[i - 1][j];
				dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
			}
		}
		return dp;
	}

	public static int[][] getDpOne(int[] arr, int money) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		int[][] dp = new int[arr.length][money + 1];
		for (int i = 0; i < arr.length; i++) {
			dp[i][0] = 1;
		}
		if (arr[0] <= money) {
			dp[0][arr[0]] = 1;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j <= money; j++) {
				dp[i][j] = dp[i - 1][j];
				dp[i][j] += j - arr[i] >= 0 ? dp[i - 1][j - arr[i]] : 0;
			}
		}
		return dp;
	}

	// 暴力法
	public static int moneyWays2(int[] arbitrary, int[] onlyOne, int target) {
		if (target < 0) {
			return 0;
		}
		if ((arbitrary == null || arbitrary.length == 0) && (onlyOne == null || onlyOne.length == 0)) {
			return target == 0 ? 1 : 0;
		}

		int res = 0;
		for (int i = 0; i <= target; i++) {
			res += arbiFunc2(arbitrary, target, 0, i) * onlyOneFunc2(onlyOne, target, 0, target - i);
		}

		return res;
	}

	private static int arbiFunc2(int[] arbitrary, int target, int i, int sum) {
		if (i == arbitrary.length) {
			return sum == target ? 1 : 0;
		}
		if (arbitrary == null || arbitrary.length == 0 || sum > target) {
			return 0;
		}

		int res = 0;
		for (int j = 0; sum + arbitrary[i] * j <= target; j++) {
			res += arbiFunc2(arbitrary, target, i + 1, sum + arbitrary[i] * j);
		}

		return res;
	}

	private static int onlyOneFunc2(int[] onlyOne, int target, int i, int sum) {
		if (i == onlyOne.length) {
			return sum == target ? 1 : 0;
		}
		if (onlyOne == null || onlyOne.length == 0 || sum > target) {
			return 0;
		}

		return onlyOneFunc2(onlyOne, target, i + 1, sum + onlyOne[i]) + onlyOneFunc2(onlyOne, target, i + 1, sum);
	}

	// TODO:
	// 动态规划
	public static int moneyWays3(int[] arbitrary, int[] onlyOne, int target) {
		if (target < 0) {
			return 0;
		}
		if ((arbitrary == null || arbitrary.length == 0) && (onlyOne == null || onlyOne.length == 0)) {
			return target == 0 ? 1 : 0;
		}

		int[][] arb = arbiFunc3(arbitrary, target);
		int[][] only = onlyOneFunc3(onlyOne, target);

		if (arb == null) {
			return only[only.length - 1][target];
		}
		if (only == null) { // 上面已经判断过全为null的情况
			return arb[arb.length - 1][target];
		}

		int res = 0;
		for (int i = 0; i <= target; i++) {
			res += arb[arb.length - 1][i] * only[only.length - 1][target - i];
		}

		return res;
	}

	private static int[][] arbiFunc3(int[] arbitrary, int target) {
		if (arbitrary == null || arbitrary.length == 0) {
			return null;
		}

		int[][] res = new int[arbitrary.length][target + 1];

		// 初始化
		// 第一列
		for (int i = 0; i < arbitrary.length; i++) {
			res[i][0] = 1;
		}
		// 第一行
		for (int i = 1; i <= target; i++) {
			res[0][i] = i % arbitrary[0] == 0 ? 1 : 0;
		}

		for (int i = 1; i < arbitrary.length; i++) {
			for (int j = 1; j <= target; j++) {
				res[i][j] = res[i - 1][j] + (j - arbitrary[i] >= 0 ? res[i][j - arbitrary[i]] : 0);
			}
		}

		return res;
	}

	private static int[][] onlyOneFunc3(int[] onlyOne, int target) {
		if (onlyOne == null || onlyOne.length == 0) {
			return null;
		}

		int[][] res = new int[onlyOne.length][target + 1];

		// 初始化
		// 第一列
		for (int i = 0; i < onlyOne.length; i++) {
			res[i][0] = 1;
		}
		// 第一行
		for (int i = 1; i <= target; i++) {
			res[0][i] = i == onlyOne[0] ? 1 : 0;
		}

		for (int i = 1; i < onlyOne.length; i++) {
			for (int j = 1; j <= target; j++) {
				res[i][j] = res[i - 1][j] + (j - onlyOne[i] >= 0 ? res[i - 1][j - onlyOne[i]] : 0);
			}
		}

		return res;
	}

	public static void main(String[] args) {
		int[] arbitrary = new int[] { 3, 2, 5 };
		int[] onlyOne = new int[] { 1, 2, 4 };

		System.out.println(moneyWays(arbitrary, onlyOne, 25));
		System.out.println(moneyWays2(arbitrary, onlyOne, 25));
		System.out.println(moneyWays3(arbitrary, onlyOne, 25));

	}
}
