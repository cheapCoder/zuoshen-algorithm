package A3medium.class08;

// NOTE: (重要)编辑距离问题
// 给定两个字符串str1和str2，再给定三个整数ic、dc和rc，分别代表插入、删除和替换一个字符的代价，
// 返回将str1编辑成str2的最小代价。
// 【举例】
// str1="abc"，str2="adc"，ic=5，dc=3，rc=2 从"abc"编辑成"adc"，把'b'替换成'd'是代价最小的，所以返回2
// str1="abc"，str2="adc"，ic=5，dc=3，rc=100 从"abc"编辑成"adc"，先删除'b'，然后插入'd'是代价最小的，所以返回8
// str1="abc"，str2="abc"，ic=5，dc=3，rc=2 不用编辑了，本来就是一样的字符串，所以返回0
public class C05_EditCost {

	// public static int minCost1(String str1, String str2, int ic, int dc, int rc)
	// {
	// if (str1 == null || str2 == null) {
	// return 0;
	// }
	// char[] chs1 = str1.toCharArray();
	// char[] chs2 = str2.toCharArray();
	// int row = chs1.length + 1;
	// int col = chs2.length + 1;
	// int[][] dp = new int[row][col];
	// for (int i = 1; i < row; i++) {
	// dp[i][0] = dc * i;
	// }
	// for (int j = 1; j < col; j++) {
	// dp[0][j] = ic * j;
	// }
	// for (int i = 1; i < row; i++) {
	// for (int j = 1; j < col; j++) {
	// if (chs1[i - 1] == chs2[j - 1]) {
	// dp[i][j] = dp[i - 1][j - 1];
	// } else {
	// dp[i][j] = dp[i - 1][j - 1] + rc;
	// }
	// dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
	// dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
	// }
	// }
	// return dp[row - 1][col - 1];
	// }

	public static int minCost2(String str1, String str2, int ic, int dc, int rc) {
		if (str1 == null || str2 == null) {
			return 0;
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		char[] longs = chs1.length >= chs2.length ? chs1 : chs2;
		char[] shorts = chs1.length < chs2.length ? chs1 : chs2;
		if (chs1.length < chs2.length) { // str2�ϳ��ͽ���ic��dc��ֵ
			int tmp = ic;
			ic = dc;
			dc = tmp;
		}
		int[] dp = new int[shorts.length + 1];
		for (int i = 1; i <= shorts.length; i++) {
			dp[i] = ic * i;
		}
		for (int i = 1; i <= longs.length; i++) {
			int pre = dp[0]; // pre��ʾ���Ͻǵ�ֵ
			dp[0] = dc * i;
			for (int j = 1; j <= shorts.length; j++) {
				int tmp = dp[j]; // dp[j]û����ǰ�ȱ�������
				if (longs[i - 1] == shorts[j - 1]) {
					dp[j] = pre;
				} else {
					dp[j] = pre + rc;
				}
				dp[j] = Math.min(dp[j], dp[j - 1] + ic);
				dp[j] = Math.min(dp[j], tmp + dc);
				pre = tmp; // pre���dp[j]û����ǰ��ֵ
			}
		}
		return dp[shorts.length];
	}

	// 法一：用暴力递归任意位置i的三种选择，并使用当所有选择都用替换时的代价作为边界排除(剪枝)多余的递归
	public static int minCost3(String str1, String str2, int ic, int dc, int rc) {
		if (str1 == null || str2 == null) {
			return 0;
		}

		char[] cArr1 = str1.toCharArray();
		char[] cArr2 = str2.toCharArray();

		// 计算代价base
		int replaceCost = Math.min(cArr1.length, cArr2.length) * rc;
		int deleteOrAddCost = cArr1.length > cArr2.length ? dc * (cArr1.length - cArr2.length)
				: ic * (cArr2.length - cArr1.length);
		int baseCost = replaceCost + deleteOrAddCost;

		return process(cArr1, cArr2, ic, dc, rc, baseCost, 0, 0, 0);
	}

	// i表示第arr1的比对当前索引，j表示第arr2当前比对的索引
	private static int process(char[] arr1, char[] arr2, int ic, int dc, int rc, int baseCost, int i, int j,
			int curCost) {
		if (curCost >= baseCost) { // 剪枝作用，这题可以没有
			return baseCost;
		}
		if (i == arr1.length && j == arr2.length) {
			return curCost;
		}
		if (i < arr1.length && j < arr2.length && arr1[i] == arr2[j]) {
			return process(arr1, arr2, ic, dc, rc, baseCost, i + 1, j + 1, curCost);
		}

		int replaceCost = Integer.MAX_VALUE;
		int deleteCost = Integer.MAX_VALUE;
		int insertCost = Integer.MAX_VALUE;

		if (i < arr1.length && j < arr2.length) {
			replaceCost = process(arr1, arr2, ic, dc, rc, baseCost, i + 1, j + 1, curCost + rc);
		}
		if (i < arr1.length) {
			deleteCost = process(arr1, arr2, ic, dc, rc, baseCost, i + 1, j, curCost + dc);
		}
		if (j < arr2.length) {
			insertCost = process(arr1, arr2, ic, dc, rc, baseCost, i, j + 1, curCost + ic);
		}

		return Math.min(replaceCost, Math.min(deleteCost, insertCost));
	}

	// way2：动态规划，空间复杂度O(N*M)
	public static int minCost4(String str1, String str2, int ic, int dc, int rc) {
		if (str1 == null || str2 == null) {
			return 0;
		}
		char[] cArr1 = str1.toCharArray();
		char[] cArr2 = str2.toCharArray();

		// 初始化基准, 行和列都包含空串情况，
		// dpCache[i][j]表示：使str1从0-i变成str2的0-j范围所需的花费
		int[][] dpCache = new int[cArr1.length + 1][cArr2.length + 1];
		for (int i = 0; i < dpCache.length; i++) {
			dpCache[i][0] = i * dc;
		}
		for (int i = 0; i < dpCache[0].length; i++) {
			dpCache[0][i] = i * ic;
		}

		// 状态转移方程
		for (int i = 1; i < dpCache.length; i++) {
			for (int j = 1; j < dpCache[0].length; j++) {
				if (cArr1[i - 1] == cArr2[j - 1]) {
					dpCache[i][j] = dpCache[i - 1][j - 1];
				} else {
					int replaceCost = dpCache[i - 1][j - 1] + rc;
					int deleteCost = dpCache[i - 1][j] + dc;
					int addCost = dpCache[i][j - 1] + ic;

					dpCache[i][j] = Math.min(replaceCost, Math.min(deleteCost, addCost));
				}
			}
		}

		return dpCache[cArr1.length][cArr2.length];
	}

	// way3：动态规划，空间复杂度O(M)
	public static int minCost5(String str1, String str2, int ic, int dc, int rc) {
		if (str1 == null || str2 == null) {
			return 0;
		}
		char[] cArr1 = str1.toCharArray();
		char[] cArr2 = str2.toCharArray();

		// 初始化dp
		int[] preCache = new int[cArr2.length + 1];
		int[] curCache = new int[cArr2.length + 1];
		for (int i = 0; i < preCache.length; i++) {
			preCache[i] = i * ic;
		}

		// 状态转移方程
		for (int i = 1; i < cArr1.length + 1; i++) {
			curCache[0] = i * dc;
			for (int j = 1; j < curCache.length; j++) {
				if (cArr1[i - 1] == cArr2[j - 1]) {
					curCache[j] = preCache[j - 1];
				} else {
					int replaceCost = preCache[j - 1] + rc;
					int deleteCost = preCache[j] + dc;
					int addCost = curCache[j - 1] + ic;

					curCache[j] = Math.min(replaceCost, Math.min(deleteCost, addCost));
				}
			}
			preCache = curCache;
			curCache = new int[cArr2.length + 1];
		}

		return preCache[cArr2.length];
	}

	public static void main(String[] args) {
		String str1 = "ab12cd3";
		String str2 = "abcdf";
		// System.out.println(minCost1(str1, str2, 5, 3, 2));
		System.out.println(minCost2(str1, str2, 5, 3, 2));
		System.out.println(minCost3(str1, str2, 5, 3, 2));
		System.out.println(minCost4(str1, str2, 5, 3, 2));
		System.out.println(minCost5(str1, str2, 5, 3, 2));
		System.out.println("------------");

		str1 = "abcdaaf";
		str2 = "ab12cdasdfe3";
		// System.out.println(minCost1(str1, str2, 3, 2, 4));
		System.out.println(minCost2(str1, str2, 3, 2, 4));
		System.out.println(minCost3(str1, str2, 3, 2, 4));
		System.out.println(minCost4(str1, str2, 3, 2, 4));
		System.out.println(minCost5(str1, str2, 3, 2, 4));
		System.out.println("------------");

		str1 = "";
		str2 = "ab12cd3";
		// System.out.println(minCost1(str1, str2, 1, 7, 5));
		System.out.println(minCost2(str1, str2, 1, 7, 5));
		System.out.println(minCost3(str1, str2, 1, 7, 5));
		System.out.println(minCost4(str1, str2, 1, 7, 5));
		System.out.println(minCost5(str1, str2, 1, 7, 5));
		System.out.println("------------");

		str1 = "abcdf";
		str2 = "";
		// System.out.println(minCost1(str1, str2, 2, 9, 8));
		System.out.println(minCost2(str1, str2, 2, 9, 8));
		System.out.println(minCost3(str1, str2, 2, 9, 8));
		System.out.println(minCost4(str1, str2, 2, 9, 8));
		System.out.println(minCost5(str1, str2, 2, 9, 8));

	}

}
