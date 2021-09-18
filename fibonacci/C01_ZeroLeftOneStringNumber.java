package fibonacci;

// NOTE: 当一个问题除了前几项是base case能直接已知结果外，
// 后续的每一项都按照之前值的严格递推表达式来求出的问题，典例：斐波那契数列的O(logN)算法
// f(n,n-1,...n-m)=f(m,...2,1)*Math.pow(|{x x x}{x x x}{x x x}|,n-m)

// 题目：
// 字符串只由'0'和'1'两种字符构成，当字符串长度为1时，所有可能的字符串为"0"、"1";
// 当字符串长度为2时，所有可能的字符串为"00"、"01"、"10"、"11";
// 当字符串长度为3时，所有可能的字符串为"000"、"001"、"010"、"011"、"100"、 "101"、"110"、"111"...
// 如果某一个字符串中，只要是出现'0'的位置，左边就靠着'1'，这样的字符串叫作达标字符串。
// 给定一个正数N，返回所有长度为N的字符串中，达标字符串的数量。 比如，N=3，返回3，因为只有"101"、"110"、"111"达标。
// TODO:
public class C01_ZeroLeftOneStringNumber {

	public static int getNum1(int n) {
		if (n < 1) {
			return 0;
		}
		return process(1, n);
	}

	public static int process(int i, int n) {
		if (i == n - 1) {
			return 2;
		}
		if (i == n) {
			return 1;
		}
		return process(i + 1, n) + process(i + 2, n);
	}

	public static int getNum2(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		int pre = 1;
		int cur = 1;
		int tmp = 0;
		for (int i = 2; i < n + 1; i++) {
			tmp = cur;
			cur += pre;
			pre = tmp;
		}
		return cur;
	}

	public static int getNum3(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1 || n == 2) {
			return n;
		}
		int[][] base = { { 1, 1 }, { 1, 0 } };
		int[][] res = matrixPower(base, n - 2);
		return 2 * res[0][0] + res[1][0];
	}

	public static int[][] matrixPower(int[][] m, int p) {
		int[][] res = new int[m.length][m[0].length];
		for (int i = 0; i < res.length; i++) {
			res[i][i] = 1;
		}
		int[][] tmp = m;
		for (; p != 0; p >>= 1) {
			if ((p & 1) != 0) {
				res = muliMatrix(res, tmp);
			}
			tmp = muliMatrix(tmp, tmp);
		}
		return res;
	}

	// 行列式相乘
	public static int[][] muliMatrix(int[][] m1, int[][] m2) {
		int[][] res = new int[m1.length][m2[0].length];
		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m2[0].length; j++) {
				for (int k = 0; k < m2.length; k++) {
					res[i][j] += m1[i][k] * m2[k][j];
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		for (int i = 0; i != 20; i++) {
			System.out.println(getNum1(i));
			System.out.println(getNum2(i));
			System.out.println(getNum3(i));
			System.out.println("===================");
		}

	}
}
