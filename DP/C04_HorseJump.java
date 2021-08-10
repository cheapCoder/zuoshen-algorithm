package DP;
// 象棋中马的跳法【题目】请同学们自行搜索或者想象一个象棋的棋盘，然后把整个棋盘放入第一象限，棋盘的最左下 角是(0,0)位置。那么整个棋盘就是横坐标上9条线、纵坐标上10条线的一个区域。给你三个 参数，x，y，k，返回如果“马”从(0,0)位置出发，必须走k步，最后落在(x,y)上的方法数 有多少种?
public class C04_HorseJump {

	public static int getWays(int x, int y, int step) {
		return process(x, y, step);
	}
	
	public static int process(int x, int y, int step) {
		if (x < 0 || x > 8 || y < 0 || y > 9) {
			return 0;
		}
		if (step == 0) {
			return (x == 0 && y == 0) ? 1 : 0;
		}
		// NOTE:(0, 0)到(x, y)的走法数相当于(x, y)到(0, 0)的走法数
		return process(x - 1, y + 2, step - 1)
				+ process(x + 1, y + 2, step - 1)
				+ process(x + 2, y + 1, step - 1)
				+ process(x + 2, y - 1, step - 1)
				+ process(x + 1, y - 2, step - 1)
				+ process(x - 1, y - 2, step - 1)
				+ process(x - 2, y - 1, step - 1)
				+ process(x - 2, y + 1, step - 1);
	}

	public static int dpWays(int x, int y, int step) {
		if (x < 0 || x > 8 || y < 0 || y > 9 || step < 0) {
			return 0;
		}
		int[][][] dp = new int[9][10][step + 1];
		dp[0][0][0] = 1;
		for (int h = 1; h <= step; h++) {
			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 10; c++) {
					dp[r][c][h] += getValue(dp, r - 1, c + 2, h - 1);
					dp[r][c][h] += getValue(dp, r + 1, c + 2, h - 1);
					dp[r][c][h] += getValue(dp, r + 2, c + 1, h - 1);
					dp[r][c][h] += getValue(dp, r + 2, c - 1, h - 1);
					dp[r][c][h] += getValue(dp, r + 1, c - 2, h - 1);
					dp[r][c][h] += getValue(dp, r - 1, c - 2, h - 1);
					dp[r][c][h] += getValue(dp, r - 2, c - 1, h - 1);
					dp[r][c][h] += getValue(dp, r - 2, c + 1, h - 1);
				}
			}
		}
		return dp[x][y][step];
	}

	public static int getValue(int[][][] dp, int row, int col, int step) {
		if (row < 0 || row > 8 || col < 0 || col > 9) {
			return 0;
		}
		return dp[row][col][step];
	}

	public static void main(String[] args) {
		int x = 7;
		int y = 7;
		int step = 10;
		System.out.println(getWays(x, y, step));
		System.out.println(dpWays(x, y, step));
	}
}
