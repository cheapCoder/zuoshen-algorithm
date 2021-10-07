package A3medium.class07;

// CC里面有一个土豪很喜欢一位女直播Kiki唱歌，平时就经常给她点赞、送礼、私聊,最近CC直播平台在举行中秋之星主播唱歌比赛。
// 假设一开始该女主播的初始人气值为start，能够晋升下一轮人气需要刚好达到end，
// 土豪给主播增加人气的可以采取的方法有:
// x.点赞 花费x C币，人气+2 
// y.送礼 花费y C币，人气*2 
// z.私聊 花费z C币，人气-2 
// 其中end远大于start且end为偶数，请写一个程序帮助土豪计算一下，最少花费多少C币就能帮助该主播Kiki将人气刚好达到end，从而能够晋级下一轮?
// 输入描述:第一行输入5个数据，分别为:x y z start end，每项数据以空格分开。其中:0 < x,y,z <= 10000，0 < start,end <= 1000000 
// 输出描述:需要花费的最少C币。
// 示例1:
// 输入 3 100 1 2 6 
// 输出 6

// NOTE: 分支限界(剪枝)：递归死循环跑不完时，需要人为规定其边界限制递归条件: 
// 1. 找一个‘平凡解’作为限制条件
// 2. 从业务中发现哪些递归条件下明显不用继续递归了，手动终止递归
public class C04_Kiki {

	// public static int minCoins1(int add, int times, int del, int start, int end)
	// {
	// if (start > end) {
	// return -1;
	// }
	// return process(0, end, add, times, del, start, end * 2, ((end - start) / 2) *
	// add);
	// }

	// // 没必要定义变量limitAim
	// public static int process(int pre, int aim, int add, int times, int del, int
	// finish, int limitAim, int limitCoin) {
	// if (pre > limitCoin) {
	// return Integer.MAX_VALUE;
	// }
	// if (aim < 0) {
	// return Integer.MAX_VALUE;
	// }
	// if (aim > limitAim) {
	// return Integer.MAX_VALUE;
	// }
	// if (aim == finish) {
	// return pre;
	// }
	// int min = Integer.MAX_VALUE;
	// int p1 = process(pre + add, aim - 2, add, times, del, finish, limitAim,
	// limitCoin);
	// if (p1 != Integer.MAX_VALUE) {
	// min = p1;
	// }
	// int p2 = process(pre + del, aim + 2, add, times, del, finish, limitAim,
	// limitCoin);
	// if (p2 != Integer.MAX_VALUE) {
	// min = Math.min(min, p2);
	// }
	// if ((aim & 1) == 0) {
	// int p3 = process(pre + times, aim / 2, add, times, del, finish, limitAim,
	// limitCoin);
	// if (p3 != Integer.MAX_VALUE) {
	// min = Math.min(min, p3);
	// }
	// }
	// return min;
	// }

	public static int minCoins3(int add, int times, int del, int start, int end) {
		if (start > end) {
			return -1;
		}
		int limitCoin = ((end - start) / 2) * add;
		int limitAim = end * 2;
		int[][] dp = new int[limitCoin + 1][limitAim + 1];
		for (int pre = 0; pre <= limitCoin; pre++) {
			for (int aim = 0; aim <= limitAim; aim++) {
				if (aim == start) {
					dp[pre][aim] = pre;
				} else {
					dp[pre][aim] = Integer.MAX_VALUE;
				}
			}
		}
		for (int pre = limitCoin; pre >= 0; pre--) {
			for (int aim = 0; aim <= limitAim; aim++) {
				if (aim - 2 >= 0 && pre + add <= limitCoin) {
					dp[pre][aim] = Math.min(dp[pre][aim], dp[pre + add][aim - 2]);
				}
				if (aim + 2 <= limitAim && pre + del <= limitCoin) {
					dp[pre][aim] = Math.min(dp[pre][aim], dp[pre + del][aim + 2]);
				}
				if ((aim & 1) == 0) {
					if (aim / 2 >= 0 && pre + times <= limitCoin) {
						dp[pre][aim] = Math.min(dp[pre][aim], dp[pre + times][aim / 2]);
					}
				}
			}
		}
		return dp[0][end];
	}

	// 暴力递归，剪枝函数减少递归量
	public static int minCoins1(int x, int y, int z, int start, int end) {
		if (start < 0 || end < 0 || start > end) {
			return -1;
		}

		int baseCoin = end >= start ? (end - start) * x / 2 : (start - end) * z / 2;
		return process(x, y, z, baseCoin, start, end, start, 0);
	}

	private static int process(int x, int y, int z, int baseCoin, int start, int end, int curPoint, int curCoins) {
		if (curPoint == end) {
			return curCoins;
		}

		if (curPoint > (end * 2) || curPoint < (start / 2) || curCoins > baseCoin) {
			return -1;
		}

		int plus = process(x, y, z, baseCoin, start, end, curPoint + 2, curCoins + x);
		int multi = process(x, y, z, baseCoin, start, end, curPoint * 2, curCoins + y);
		int sub = process(x, y, z, baseCoin, start, end, curPoint - 2, curCoins + z);

		int res = Integer.MAX_VALUE;

		if (plus != -1) {
			res = Math.min(res, plus);
		}
		if (multi != -1) {
			res = Math.min(res, multi);
		}
		if (sub != -1) {
			res = Math.min(res, sub);
		}
		return res == Integer.MAX_VALUE ? -1 : res;
	}

	public static int minCoins2(int x, int y, int z, int start, int end) {
		if (start < 0 || end < 0 || start > end) {
			return -1;
		}

		int baseCoin = end >= start ? (end - start) * x / 2 : (start - end) * z / 2;

		// 初始化dp数组
		int[][] cache = new int[baseCoin + 1][2 * end + 1];
		for (int i = 0; i < cache.length; i++) {
			for (int j = 0; j < cache[0].length; j++) {
				cache[i][j] = Integer.MAX_VALUE;
			}
			cache[i][end] = i;
		}

		for (int i = cache.length - 1; i >= 0; i--) {
			for (int j = cache[0].length - 1; j >= 0; j--) {
				if (i + x < baseCoin && j + 2 <= end) {
					cache[i][j] = Math.min(cache[i][j], cache[i + x][j + 2]);
				}
				if (i + y < baseCoin && j * 2 <= end) {
					cache[i][j] = Math.min(cache[i][j], cache[i + y][j * 2]);
				}
				if (i + z < baseCoin && j - 2 >= 0) {
					cache[i][j] = Math.min(cache[i][j], cache[i + z][j - 2]);
				}
			}
		}

		return cache[0][start];
	}

	public static void main(String[] args) {
		int add = 6;
		int times = 5;
		int del = 1;
		int start = 10;
		int end = 28;
		System.out.println(minCoins1(add, times, del, start, end));
		System.out.println(minCoins2(add, times, del, start, end));
	}
}
