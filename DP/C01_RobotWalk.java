package DP;

// 机器人达到指定位置方法数

//【题目】假设有排成一行的N个位置，记为1~N，N一定大于或等于2。开始时机器人在其中的M位置上(M一定是1~N中的一个)，机器人可以往左走或者往右走，如果机器人来到1位置，那么下一步只能往右来到2位置;如果机器人来到N位置，那么下一步只能往左来到N-1位置。规定机器人必须走K步，最终能来到P位置(P也一定是1~N中的一个)的方法有多少种。给定四个参数N、M、K、P，返回方法数。

// 【举例】N=5,M=2,K=3,P=3上面的参数代表所有位置为 1 2 3 4 5。机器人最开始在2位置上，必须经过3步，最后到达3位置。走的方法只有如下3种:1)从2到1，从1到2，从2到32)从2到3，从3到2，从2到33)从2到3，从3到4，从4到3所以返回方法数3。N=3,M=1,K=3,P=3上面的参数代表所有位置为 1 2 3。机器人最开始在1位置上，必须经过3步，最后到达3位置。怎么走也不可能，所以返回方法数 0。

public class C01_RobotWalk {

	// public static int ways1(int N, int M, int K, int P) {
	// // NOTE:参数无效直接返回0, 题目有保证有效，不用写
	// if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
	// return 0;
	// }
	// // 总共N个位置，从M点出发，还剩K步，返回最终能达到P的方法数
	// return walk(N, M, K, P);
	// }

	// // N : 位置为1 ~ N，固定参数
	// // cur : 当前在cur位置，可变参数
	// // rest : 还剩res步没有走，可变参数
	// // P : 最终目标位置是P，固定参数
	// // 该函数的含义：只能在1~N这些位置上移动，当前在cur位置，走完rest步之后，停在P位置的方法数作为返回值返回
	// public static int walk(int N, int cur, int rest, int P) {
	// // 如果没有剩余步数了，当前的cur位置就是最后的位置
	// // 如果最后的位置停在P上，那么之前做的移动是有效的
	// // 如果最后的位置没在P上，那么之前做的移动是无效的
	// if (rest == 0) {
	// return cur == P ? 1 : 0;
	// }
	// // 如果还有rest步要走，而当前的cur位置在1位置上，那么当前这步只能从1走向2
	// // 后续的过程就是，来到2位置上，还剩rest-1步要走
	// if (cur == 1) {
	// return walk(N, 2, rest - 1, P);
	// }
	// // 如果还有rest步要走，而当前的cur位置在N位置上，那么当前这步只能从N走向N-1
	// // 后续的过程就是，来到N-1位置上，还剩rest-1步要走
	// if (cur == N) {
	// return walk(N, N - 1, rest - 1, P);
	// }
	// // 如果还有rest步要走，而当前的cur位置在中间位置上，那么当前这步可以走向左，也可以走向右
	// // 走向左之后，后续的过程就是，来到cur-1位置上，还剩rest-1步要走
	// // 走向右之后，后续的过程就是，来到cur+1位置上，还剩rest-1步要走
	// // 走向左、走向右是截然不同的方法，所以总方法数要都算上
	// return walk(N, cur + 1, rest - 1, P) + walk(N, cur - 1, rest - 1, P);
	// }

	// public static int ways2(int N, int M, int K, int P) {
	// // 参数无效直接返回0
	// if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
	// return 0;
	// }
	// int[][] dp = new int[K + 1][N + 1];
	// dp[0][P] = 1;
	// for (int i = 1; i <= K; i++) {
	// for (int j = 1; j <= N; j++) {
	// if (j == 1) {
	// dp[i][j] = dp[i - 1][2];
	// } else if (j == N) {
	// dp[i][j] = dp[i - 1][N - 1];
	// } else {
	// dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
	// }
	// }
	// }
	// return dp[K][M];
	// }

	// public static int ways4(int N, int M, int K, int P) {
	// // 参数无效直接返回0
	// if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
	// return 0;
	// }
	// int[] dp = new int[N + 1];
	// dp[P] = 1;
	// for (int i = 1; i <= K; i++) {
	// int leftUp = dp[1];// 左上角的值
	// for (int j = 1; j <= N; j++) {
	// int tmp = dp[j];
	// if (j == 1) {
	// dp[j] = dp[j + 1];
	// } else if (j == N) {
	// dp[j] = leftUp;
	// } else {
	// dp[j] = leftUp + dp[j + 1];
	// }
	// leftUp = tmp;
	// }
	// }
	// return dp[M];
	// }

	// 暴力递归
	public static int ways1(int N, int pos, int rest, int target) {
		if (rest == 0) {
			return pos == target ? 1 : 0;
		}
		if (pos == 1) {
			return ways1(N, 2, rest - 1, target);
		}
		if (pos == N) {
			return ways1(N, N - 1, rest - 1, target);
		}

		return ways1(N, pos - 1, rest - 1, target) + ways1(N, pos + 1, rest - 1, target);
	}

	// 记忆化搜索
	public static int ways2(int N, int M, int K, int P) {
		int[][] cache = new int[N + 1][K + 1];
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < K + 1; j++) {
				cache[i][j] = -1;
			}
		}
		// 边界条件题目有保证不越界
		return process(N, M, K, P, cache);
	}

	private static int process(int N, int pos, int rest, int target, int[][] cache) {
		if (cache[pos][rest] != -1) {
			return cache[pos][rest];
		}

		if (rest == 0) {
			cache[pos][rest] = pos == target ? 1 : 0;
		} else if (pos == 1) {
			cache[pos][rest] = process(N, 2, rest - 1, target, cache);
		} else if (pos == N) {
			cache[pos][rest] = process(N, N - 1, rest - 1, target, cache);
		} else {
			cache[pos][rest] = process(N, pos - 1, rest - 1, target, cache) + process(N, pos + 1, rest - 1, target, cache);
		}
		return cache[pos][rest];
	}

	// 严格表结构@1
	public static int ways3(int N, int M, int K, int P) {
		int[][] cache = new int[K + 1][N + 1];
		cache[0][P] = 1;
		for (int i = 1; i < cache.length; i++) {
			for (int j = 1; j < cache[i].length; j++) {
				if (j == 1) {
					cache[i][j] = cache[i - 1][j + 1];
				} else if (j == cache[i].length - 1) {
					cache[i][j] = cache[i - 1][j - 1];
				} else {
					cache[i][j] = cache[i - 1][j - 1] + cache[i - 1][j + 1];
				}
			}
		}
		return cache[K][M];
	}

	// 严格表结构@2
	// NOTE:每个各自只与上行的左侧格子和右侧格子有关，可以只缓存上一行的值和上一个左侧格子的值(因为算到当前格子时上行左侧的格子值已经被覆盖了)
	public static int ways4(int N, int M, int K, int P) {
		int[] cache = new int[N + 1];
		int leftUpCache;
		cache[P] = 1;

		for (int i = 1; i < K + 1; i++) {
			leftUpCache = cache[1];
			for (int j = 1; j < cache.length; j++) {
				int tem = cache[j];
				if (j == 1) {
					cache[j] = cache[j + 1];
				} else if (j == cache.length - 1) {
					cache[j] = leftUpCache;
				} else {
					cache[j] = leftUpCache + cache[j + 1];
				}
				leftUpCache = tem;
			}
		}

		return cache[M];
	}

	public static void main(String[] args) {
		System.out.println(ways1(7, 4, 9, 5));
		System.out.println(ways2(7, 4, 9, 5));
		System.out.println(ways3(7, 4, 9, 5));
		System.out.println(ways4(7, 4, 9, 5));
	}
}
