package macro_scheduling;

// 用螺旋的方式打印矩阵，比如如下的矩阵:
// 0 1 2  3
// 4 5 6  7
// 8 9 10 11
// 打印顺序为:0 1 2 3 7 11 10 9 8 4 5 6

public class C03_PrintMatrixSpiralOrder {

	// public static void spiralOrderPrint(int[][] matrix) {
	// int tR = 0;
	// int tC = 0;
	// int dR = matrix.length - 1;
	// int dC = matrix[0].length - 1;
	// while (tR <= dR && tC <= dC) { // 正方形,只用满足一个就行
	// printEdge(matrix, tR++, tC++, dR--, dC--);
	// }
	// }

	// public static void printEdge(int[][] m, int tR, int tC, int dR, int dC) {
	// if (tR == dR) {
	// for (int i = tC; i <= dC; i++) {
	// System.out.print(m[tR][i] + " ");
	// }
	// } else if (tC == dC) {
	// for (int i = tR; i <= dR; i++) {
	// System.out.print(m[i][tC] + " ");
	// }
	// } else {
	// int curC = tC;
	// int curR = tR;
	// while (curC != dC) {
	// System.out.print(m[tR][curC] + " ");
	// curC++;
	// }
	// while (curR != dR) {
	// System.out.print(m[curR][dC] + " ");
	// curR++;
	// }
	// while (curC != tC) {
	// System.out.print(m[dR][curC] + " ");
	// curC--;
	// }
	// while (curR != tR) {
	// System.out.print(m[curR][tC] + " ");
	// curR--;
	// }
	// }
	// }

	public static void spiralOrderPrint(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}

		int a = 0;
		int b = 0;
		int x = matrix[0].length - 1;
		int y = matrix.length - 1;

		while (a <= x && b <= y) {
			printEdge(matrix, a++, b++, x--, y--);
		}
	}

	public static void printEdge(int[][] matrix, int a, int b, int x, int y) {
		for (int i = b; i <= y; i++) {
			System.out.print(matrix[a][i] + " ");
		}
		for (int i = a + 1; i <= x; i++) {
			System.out.print(matrix[i][y] + " ");
		}
		for (int i = y - 1; i >= b; i--) {
			System.out.print(matrix[x][i] + " ");
		}
		for (int i = x - 1; i > a; i--) {
			System.out.print(matrix[i][b] + " ");
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		spiralOrderPrint(matrix);

	}

}
