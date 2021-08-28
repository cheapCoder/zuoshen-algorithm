package dp;


// Bob的生存概率
// 【题目】 给定五个参数n,m,i,j,k。
// 表示在一个N*M的区域，Bob处在(i,j)点，每次Bob等概率的向上、 下、左、右四个方向移动一步，Bob必须走K步。
// 如果走完之后，Bob还停留在这个区域上， 就算Bob存活，否则就算Bob死亡。
// 请求解Bob的生存概率，返回字符串表示分数的方式。
public class C05_BobDie {

	// public static String bob1(int N, int M, int i, int j, int K) {
	// int all = (int) Math.pow(4, K); // 所有移动方法数
	// int live = process(N, M, i, j, K);
	// int gcd = gcd(all, live);
	// return String.valueOf((live / gcd) + "/" + (all / gcd));
	// }

	// // 存活的方法数
	// public static int process(int N, int M, int row, int col, int rest) {
	// if (row < 0 || row == N || col < 0 || col == M) {
	// return 0;
	// }
	// if (rest == 0) {
	// return 1;
	// }
	// int live = process(N, M, row - 1, col, rest - 1);
	// live += process(N, M, row + 1, col, rest - 1);
	// live += process(N, M, row, col - 1, rest - 1);
	// live += process(N, M, row, col + 1, rest - 1);
	// return live;
	// }

	// public static int gcd(int m, int n) {
	// return n == 0 ? m : gcd(n, m % n);
	// }

	// public static String bob2(int N, int M, int i, int j, int K) {
	// int[][][] dp = new int[N + 2][M + 2][K + 1];
	// for (int row = 1; row <= N; row++) {
	// for (int col = 1; col <= M; col++) {
	// dp[row][col][0] = 1;
	// }
	// }
	// for (int rest = 1; rest <= K; rest++) {
	// for (int row = 1; row <= N; row++) {
	// for (int col = 1; col <= M; col++) {
	// dp[row][col][rest] = dp[row - 1][col][rest - 1];
	// dp[row][col][rest] += dp[row + 1][col][rest - 1];
	// dp[row][col][rest] += dp[row][col - 1][rest - 1];
	// dp[row][col][rest] += dp[row][col + 1][rest - 1];
	// }
	// }
	// }
	// int all = (int) Math.pow(4, K);
	// int live = dp[i + 1][j + 1][K];
	// int gcd = gcd(all, live);
	// return String.valueOf((live / gcd) + "/" + (all / gcd));
	// }

	public static String bob1(int N, int M, int i, int j, int K) {
		int all = (int) Math.pow(4, K);
		int alive = process(N, M, i, j, K);
		int gcbVal = gcd(all, alive);

		return (alive / gcbVal) + "/" + (all / gcbVal);
	}

	private static int process(int N, int M, int i, int j, int K) {
		if (i < 0 || i >= N || j < 0 || j >= M) {
			return 0;
		}
		if (K == 0) {
			return 1;
		}
		return process(N, M, i + 1, j, K - 1) + process(N, M, i - 1, j, K - 1) + process(N, M, i, j + 1, K - 1)
				+ process(N, M, i, j - 1, K - 1);
	}

	public static String bob2(int N, int M, int n, int m, int K) {
		if (K < 0) {
			return "Null";
		}
		if (K == 0) {
			return (n >= 0 && n < N && m >= 0 && m < M) ? "1/1" : "0/1";
		}

		int[][] curK = new int[N][M];
		int[][] preK = new int[N][M];
		// base case 初始化
		for (int j = 0; j < M; j++) {
			for (int i = 0; i < N; i++) {
				curK[i][j] = 1;
			}
		}

		int kTem = K;

		while (K-- > 0) {
			int[][] tem = curK;
			curK = preK;
			preK = tem;
			for (int j = 0; j < M; j++) {
				for (int i = 0; i < N; i++) {
					curK[i][j] = getValue(i + 1, j, N, M, preK) + getValue(i - 1, j, N, M, preK) + getValue(i, j + 1, N, M, preK)
							+ getValue(i, j - 1, N, M, preK);
				}
			}
		}
		// System.out.println(curK[n][m]);
		int alive = curK[n][m];
		int all = (int) Math.pow(4, kTem);
		int gcbVal = gcd(all, alive);
		// System.out.println("what" +gcbVal);
		return (alive / gcbVal) + "/" + (all / gcbVal);
	}

	private static int gcd(int i, int j) {
		return j == 0 ? i : gcd(j, i % j);
	}

	private static int getValue(int x, int y, int N, int M, int[][] preK) {
		if (x < 0 || x >= N || y < 0 || y >= M) {
			return 0;
		}
		return preK[x][y];
	}

	public static void main(String[] args) {
		int N = 10;
		int M = 10;
		int i = 3;
		int j = 2;
		int K = 5;
		System.out.println(bob1(N, M, i, j, K));
		System.out.println(bob2(N, M, i, j, K));
	}
}
