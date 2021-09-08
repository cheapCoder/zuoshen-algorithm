package macro_scheduling;

// 用zigzag的方式打印矩阵，比如如下的矩阵 0123
// 4567
// 8 91011
// 打印顺序为:0 1 4 8 5 2 3 6 9 10 7 11
public class C02_ZigZagPrintMatrix {

	// public static void printMatrixZigZag(int[][] matrix) {
	// int tR = 0;
	// int tC = 0;
	// int dR = 0;
	// int dC = 0;
	// int endR = matrix.length - 1;
	// int endC = matrix[0].length - 1;
	// boolean fromUp = false;
	// while (tR != endR + 1) {
	// printLevel(matrix, tR, tC, dR, dC, fromUp);
	// tR = tC == endC ? tR + 1 : tR;
	// tC = tC == endC ? tC : tC + 1;
	// dC = dR == endR ? dC + 1 : dC;
	// dR = dR == endR ? dR : dR + 1;
	// fromUp = !fromUp;
	// }
	// System.out.println();
	// }

	// public static void printLevel(int[][] m, int tR, int tC, int dR, int dC,
	// boolean f) {
	// if (f) {
	// while (tR != dR + 1) {
	// System.out.print(m[tR++][tC--] + " ");
	// }
	// } else {
	// while (dR != tR - 1) {
	// System.out.print(m[dR--][dC++] + " ");
	// }
	// }
	// }

	public static void printMatrixZigZag(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}

		int a = 0;
		int b = 0;
		int x = 0;
		int y = 0;
		boolean isPositive = false; // 是否为正斜线

		while (b < matrix[0].length) {

			printSlash(matrix, a, b, x, y, isPositive);

			isPositive = !isPositive;
			if (a != matrix.length - 1) {
				a++;
			} else {
				b++;
			}

			if (y != matrix[0].length - 1) {
				y++;
			} else {
				x++;
			}
		}
		System.out.println();
	}

	private static void printSlash(int[][] matrix, int a, int b, int x, int y, boolean isPositive) {
		if (isPositive) {
			while (b <= y) {
				System.out.print(matrix[a--][b++] + " ");
			}
		} else {
			while (y >= b) {
				System.out.print(matrix[x++][y--] + " ");
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		printMatrixZigZag(matrix);

	}

}
