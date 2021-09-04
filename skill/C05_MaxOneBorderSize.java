package skill;

// 题目:
// 给定一个N*N的矩阵matrix，只有0和1两种值，返回边框全是1的最大正方形的边长长度。
// 例如:
// 0 1 1 1 1
// 0 1 0 0 1
// 0 1 0 0 1
// 0 1 1 1 1
// 0 1 0 1 1 
// 其中边框全是1的最大正方形的大小为4*4，所以返回4。
public class C05_MaxOneBorderSize {

	// public static int getMaxSize(int[][] m) {
	// int[][] right = new int[m.length][m[0].length];
	// int[][] down = new int[m.length][m[0].length];
	// setBorderMap(m, right, down);
	// for (int size = Math.min(m.length, m[0].length); size != 0; size--) {
	// if (hasSizeOfBorder(size, right, down)) {
	// return size;
	// }
	// }
	// return 0;
	// }

	// private static void setBorderMap(int[][] m, int[][] right, int[][] down) {
	// int r = m.length;
	// int c = m[0].length;
	// if (m[r - 1][c - 1] == 1) {
	// right[r - 1][c - 1] = 1;
	// down[r - 1][c - 1] = 1;
	// }
	// for (int i = r - 2; i != -1; i--) {
	// if (m[i][c - 1] == 1) {
	// right[i][c - 1] = 1;
	// down[i][c - 1] = down[i + 1][c - 1] + 1;
	// }
	// }
	// for (int i = c - 2; i != -1; i--) {
	// if (m[r - 1][i] == 1) {
	// right[r - 1][i] = right[r - 1][i + 1] + 1;
	// down[r - 1][i] = 1;
	// }
	// }
	// for (int i = r - 2; i != -1; i--) {
	// for (int j = c - 2; j != -1; j--) {
	// if (m[i][j] == 1) {
	// right[i][j] = right[i][j + 1] + 1;
	// down[i][j] = down[i + 1][j] + 1;
	// }
	// }
	// }
	// }

	// private static boolean hasSizeOfBorder(int size, int[][] right, int[][] down)
	// {
	// for (int i = 0; i != right.length - size + 1; i++) {
	// for (int j = 0; j != right[0].length - size + 1; j++) {
	// if (right[i][j] >= size && down[i][j] >= size && right[i + size - 1][j] >=
	// size
	// && down[i][j + size - 1] >= size) {
	// // System.out.println("2: " + i + " " + j + " " + size);
	// return true;
	// }
	// }
	// }
	// return false;
	// }

	// 暴力枚举 => O(n4)
	public static int getMaxSize1(int[][] m) {
		if (m == null || m.length == 0 || m[0].length == 0) {
			return 0;
		}

		int res = 0;
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				if (m[i][j] == 0) {
					continue;
				}
				// NOTE: l只是表示两点的索引差，为距离差，所求的边长由一个个点表示多长，为l + 1
				for (int l = Math.min(m[0].length - j, m.length - i); l > 0; l--) {
					if (isSquare(m, i, j, l)) {
						res = Math.max(res, l);
						break;
					}
				}

			}
		}
		return res;
	}

	private static boolean isSquare(int[][] m, int x, int y, int l) {
		// 上行
		for (int i = y; i < y + l; i++) {
			if (m[x][i] != 1) {
				return false;
			}
		}
		// 右竖
		for (int i = x + 1; i < x + l; i++) {
			if (m[i][y + l - 1] != 1) {
				return false;
			}
		}
		// 下行
		for (int i = y; i < y + l - 1; i++) {
			if (m[x + l - 1][i] != 1) {
				return false;
			}
		}
		// 左竖
		for (int i = x; i < x + l - 1; i++) {
			if (m[i][y] != 1) {
				return false;
			}
		}
		return true;
	}

	// 空间换时间
	// 缓存任意点右边和下边有多少个连续的1(包括自己)
	public static int getMaxSize2(int[][] m) {
		if (m == null || m.length == 0 || m[0].length == 0) {
			return 0;
		}

		int rowLength = m.length;
		int colLength = m[0].length;

		// 初始化缓存
		int[][][] tem = setCache(m);
		int[][] rightCache = tem[0];
		int[][] downCache = tem[1];

		int res = 0;
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				int l = Math.min(rightCache[i][j], downCache[i][j]);
				while (l > 0) {
					if (isSquare2(rightCache, downCache, i, j, l)) {
						// System.out.println(i + " " + j + " " + l);
						res = Math.max(res, l);
						break;
					}
					l--;
				}
			}
		}
		return res;
	}

	private static int[][][] setCache(int[][] m) {
		int rowLength = m.length;
		int colLength = m[0].length;
		int[][] rightCache = new int[rowLength][colLength];
		int[][] downCache = new int[rowLength][colLength];
		// 任意位置右侧有多少个1
		for (int row = 0; row < rowLength; row++) {
			rightCache[row][colLength - 1] = m[row][colLength - 1] == 1 ? 1 : 0;
		}
		for (int i = 0; i < rowLength; i++) {
			for (int j = colLength - 2; j >= 0; j--) {
				rightCache[i][j] = m[i][j] == 1 ? rightCache[i][j + 1] + 1 : 0;
			}
		}
		// 任意位置下面有多少个1
		for (int col = 0; col < colLength; col++) {
			downCache[rowLength - 1][col] = m[rowLength - 1][col] == 1 ? 1 : 0;
		}
		for (int i = rowLength - 2; i >= 0; i--) {
			for (int j = 0; j < colLength; j++) {
				downCache[i][j] = m[i][j] == 1 ? downCache[i + 1][j] + 1 : 0;
			}
		}
		return new int[][][] { rightCache, downCache };
	}

	private static boolean isSquare2(int[][] right, int[][] down, int x, int y, int l) {
		if (right[x][y] < l) {
			return false;
		}
		if (down[x][y + l - 1] < l) {
			return false;
		}
		if (x + l - 1 >= right.length) {
			System.out.println(x + " " + y + " " + l);
		}

		if (right[x + l - 1][y] < l) {
			return false;
		}
		if (down[x][y] < l) {
			return false;
		}
		return true;
	}

	// for test
	public static int[][] generateRandom01Matrix(int rowSize, int colSize) {
		int[][] res = new int[rowSize][colSize];
		for (int i = 0; i != rowSize; i++) {
			for (int j = 0; j != colSize; j++) {
				res[i][j] = (int) (Math.random() * 2);
			}
		}
		return res;
	}

	// for test
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int testTime = 1000;
		for (int i = 0; i < testTime; i++) {
			int[][] matrix = generateRandom01Matrix(100, 80);
			// printMatrix(matrix);
			// int answer = getMaxSize(matrix);
			int answer1 = getMaxSize1(matrix);
			int answer2 = getMaxSize2(matrix);

			if (answer1 != answer2) {
				System.out.println(answer2);
				System.out.println("error: " + answer1);
			}
		}
	}
}
