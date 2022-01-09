package A4high.class03;

// 给定一个二维数组matrix，每个单元都是一个整数，有正有负。
// 最开始的时候小Q操纵一条长度为0的蛇,蛇从矩阵最左侧任选一个单元格进入地图，
// 蛇每次只能够到达当前位置的右上相邻，右侧相邻和右下相邻的单元格。 
// 蛇到达一个单元格后，自身的长度会瞬间加上该单元格的数值，任何情况下长度为负则游戏结束。
// 小Q可以在游戏开始的时候把地图中的某一个节点的值变为其相反数
// (注:最多只能改变一个节点)。问在小Q游戏过程中，他的蛇最长长度可以到多少?
// 比如:
// 1 -4  10
// 3 -2 -1
// 2 -1  0
// 0  5 -2
// 最优路径为从最左侧的3开始，3 -> -4(利用能力变成4) -> 10。所以返回17。
public class C02_SnakeGame {

	public static int walk1(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int res = Integer.MIN_VALUE;
		for (int i = 0; i < matrix.length; i++) {
			int[] ans = process(matrix, i, 0);
			res = Math.max(res, Math.max(ans[0], ans[1]));
		}
		return res;
	}

	// 从(i,j)出发一直走到最右侧的旅程中
	// 0) 在没有使用过能力的情况下，返回路径最大和
	// 1) 在使用过能力的情况下，返回路径最大和
	public static int[] process(int[][] m, int i, int j) {
		if (j == m[0].length - 1) {
			return new int[] { m[i][j], -m[i][j] };
		}
		int[] restAns = process(m, i, j + 1);
		int restUnuse = restAns[0];
		int restUse = restAns[1];
		if (i - 1 >= 0) {
			restAns = process(m, i - 1, j + 1);
			restUnuse = Math.max(restUnuse, restAns[0]);
			restUse = Math.max(restUse, restAns[1]);
		}
		if (i + 1 < m.length) {
			restAns = process(m, i + 1, j + 1);
			restUnuse = Math.max(restUnuse, restAns[0]);
			restUse = Math.max(restUse, restAns[1]);
		}
		int no = m[i][j] + restUnuse;
		int yes = Math.max(m[i][j] + restUse, -m[i][j] + restUnuse);
		return new int[] { no, yes };
	}

	public static int walk2(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int[][][] dp = new int[matrix.length][matrix[0].length][2];
		for (int i = 0; i < dp.length; i++) {
			dp[i][matrix[0].length - 1][0] = matrix[i][matrix[0].length - 1]; // 答案是考虑蛇从右侧进入
			dp[i][matrix[0].length - 1][1] = -matrix[i][matrix[0].length - 1];
		}
		for (int j = matrix[0].length - 2; j >= 0; j--) {
			for (int i = 0; i < matrix.length; i++) {
				int restUnuse = dp[i][j + 1][0];
				int restUse = dp[i][j + 1][1];
				if (i - 1 >= 0) {
					restUnuse = Math.max(restUnuse, dp[i - 1][j + 1][0]);
					restUse = Math.max(restUse, dp[i - 1][j + 1][1]);
				}
				if (i + 1 < matrix.length) {
					restUnuse = Math.max(restUnuse, dp[i + 1][j + 1][0]);
					restUse = Math.max(restUse, dp[i + 1][j + 1][0]);
				}
				dp[i][j][0] = matrix[i][j] + restUnuse; // 未考虑rest值都<0时，就停止的情况，不和题意
				dp[i][j][1] = Math.max(matrix[i][j] + restUse, -matrix[i][j] + restUnuse);
			}
		}

		int res = Integer.MIN_VALUE;
		for (int i = 0; i < matrix.length; i++) {
			res = Math.max(res, Math.max(dp[i][0][0], dp[i][0][1]));
		}
		return res;
	}

	// 暴力递归
	public static int walk3(int[][] matrix) {
		if (matrix == null || matrix[0] == null || matrix[0].length == 0 || matrix.length == 0) {
			return 0;
		}

		int res = Integer.MIN_VALUE;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				int noUseMax = process2(matrix, i, j, false);
				int useMax = process2(matrix, i, j, true);
				int max = Math.max(noUseMax, useMax);
				res = Math.max(res, max);
			}
		}

		return res;

	}

	private static int process2(int[][] matrix, int row, int col, boolean useOpposite) {
		if (col == 0) {
			return useOpposite ? -matrix[row][col] : matrix[row][col];
		}

		int res;
		int leftNoUse = process2(matrix, row, col - 1, false);
		if (row - 1 >= 0) {
			leftNoUse = Math.max(leftNoUse, process2(matrix, row - 1, col - 1, false));
		}
		if (row + 1 < matrix.length) {
			leftNoUse = Math.max(leftNoUse, process2(matrix, row + 1, col - 1, false));
		}
		res = leftNoUse >= 0 ? leftNoUse + matrix[row][col] : Integer.MIN_VALUE;

		if (useOpposite) {
			int leftUse = process2(matrix, row, col - 1, true);
			if (row - 1 >= 0) {
				leftUse = Math.max(leftUse, process2(matrix, row - 1, col - 1, true));
			}
			if (row + 1 < matrix.length) {
				leftUse = Math.max(leftUse, process2(matrix, row + 1, col - 1, true));
			}

			if (leftNoUse >= 0) {
				res = Math.max(res, leftNoUse - matrix[row][col]);
			}
			if (leftUse >= 0) {
				res = Math.max(res, leftUse + matrix[row][col]);
			}
		}

		return res;
	}

	// 记忆化搜索，利用缓存

	// 动态规划
	public static int walk4(int[][] matrix) {
		if (matrix == null || matrix[0] == null || matrix[0].length == 0 || matrix.length == 0) {
			return 0;
		}

		// dp数组初始化
		int[][] noOppositeCache = new int[matrix.length][matrix[0].length];
		int[][] oppositeCache = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			noOppositeCache[i][0] = matrix[i][0];
			oppositeCache[i][0] = -matrix[i][0];
		}

		// 填值
		for (int col = 1; col < matrix[0].length; col++) {
			for (int row = 0; row < matrix.length; row++) {
				// 初始化为左侧的值
				int leftNoUse = noOppositeCache[row][col - 1];
				int leftUse = oppositeCache[row][col - 1];

				if (row - 1 >= 0) {
					leftNoUse = Math.max(leftNoUse, noOppositeCache[row - 1][col - 1]);
					leftUse = Math.max(leftUse, oppositeCache[row - 1][col - 1]);
				}

				if (row + 1 < matrix.length) {
					leftNoUse = Math.max(leftNoUse, noOppositeCache[row + 1][col - 1]);
					leftUse = Math.max(leftUse, oppositeCache[row + 1][col - 1]);
				}

				// 当左侧三个位置的值都小于0时，不应该能到当前位置了，所以需要判断
				// 未使用能力时
				noOppositeCache[row][col] = leftNoUse >= 0 ? leftNoUse + matrix[row][col] : Integer.MIN_VALUE;
				// 使用能力时
				oppositeCache[row][col] = Integer.MIN_VALUE;
				if (leftUse >= 0) {
					oppositeCache[row][col] = Math.max(leftUse + matrix[row][col], oppositeCache[row][col]);
				}
				if (leftNoUse >= 0) {
					oppositeCache[row][col] = Math.max(leftNoUse - matrix[row][col], oppositeCache[row][col]);
				}

			}
		}

		int res = Integer.MIN_VALUE;
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				res = Math.max(res, oppositeCache[row][col]);
				res = Math.max(res, noOppositeCache[row][col]);
			}
		}

		return res > 0 ? res : 0;
	}

	// for test
	public static int[][] generate() {
		int row = (int) Math.ceil(Math.random() * 6 + 1);
		int col = (int) Math.ceil(Math.random() * 5 + 1);

		int[][] res = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				res[i][j] = (int) Math.floor((Math.random() >= 0.5 ? -1 : 1) * Math.random() * 10);
			}
		}

		return res;
	}

	public static void main(String[] args) {
		// int[][] matrix = { { 1, -4, 10 }, { 3, -2, -1 }, { 2, -1, 0 }, { 0, 5, -2 }
		// };
		// int[][] matrix = { { -9, 7, -2 }, { 1, -3, -1 } };
		int[][] matrix = { { -6, -2 }, { -9, -9 } };

		int testTime = 1000000;
		for (int i = 0; i < testTime; i++) {
			matrix = generate();
			int three = walk3(matrix);
			int four = walk4(matrix);
			if (three != four) {
				System.out.println(three);
				System.out.println(four);
				for (int row = 0; row < matrix.length; row++) {
					for (int col = 0; col < matrix[0].length; col++) {
						System.out.print(matrix[row][col] + " ");
					}
					System.out.println();
				}
				System.out.println("not pass");
			}
		}
	}

}
