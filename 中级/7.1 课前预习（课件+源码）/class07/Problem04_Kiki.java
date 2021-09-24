package class07;

public class Problem04_Kiki {

	public static int minCcoins1(int add, int times, int del, int start, int end) {
		if (start > end) {
			return -1;
		}
		return process(0, end, add, times, del, start, end * 2, ((end - start) / 2) * add);
	}

	public static int process(int pre, int aim, int add, int times, int del, int finish, int limitAim, int limitCoin) {
		if (pre > limitCoin) {
			return Integer.MAX_VALUE;
		}
		if (aim < 0) {
			return Integer.MAX_VALUE;
		}
		if (aim > limitAim) {
			return Integer.MAX_VALUE;
		}
		if (aim == finish) {
			return pre;
		}
		int min = Integer.MAX_VALUE;
		int p1 = process(pre + add, aim - 2, add, times, del, finish, limitAim, limitCoin);
		if (p1 != Integer.MAX_VALUE) {
			min = p1;
		}
		int p2 = process(pre + del, aim + 2, add, times, del, finish, limitAim, limitCoin);
		if (p2 != Integer.MAX_VALUE) {
			min = Math.min(min, p2);
		}
		if ((aim & 1) == 0) {
			int p3 = process(pre + times, aim / 2, add, times, del, finish, limitAim, limitCoin);
			if (p3 != Integer.MAX_VALUE) {
				min = Math.min(min, p3);
			}
		}
		return min;
	}

	public static int minCcoins2(int add, int times, int del, int start, int end) {
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

	public static void main(String[] args) {
		int add = 6;
		int times = 5;
		int del = 1;
		int start = 10;
		int end = 30;
		System.out.println(minCcoins1(add, times, del, start, end));
		System.out.println(minCcoins2(add, times, del, start, end));
	}

}
