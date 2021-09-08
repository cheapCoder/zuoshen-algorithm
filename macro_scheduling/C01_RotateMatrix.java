package macro_scheduling;

// 给定一个正方形矩阵，只用有限几个变量，实现矩阵中每个位置的数顺时针转动 90度，比如如下的矩阵
// 0 1  2 3 
// 4 5  6 7 
// 8 9 10 11
// 12 13 14 15 
// 矩阵应该被调整为: 
// 12 8  4 0
// 13 9  5 1
// 14 10 6 2
// 15 11 7 3
// NOTE: 宏观调度
public class C01_RotateMatrix {

	// public static void rotate(int[][] matrix) {
	// int tR = 0;
	// int tC = 0;
	// int dR = matrix.length - 1;
	// int dC = matrix[0].length - 1;
	// while (tR < dR) {
	// rotateEdge(matrix, tR++, tC++, dR--, dC--);
	// }
	// }

	// public static void rotateEdge(int[][] m, int tR, int tC, int dR, int dC) {
	// int times = dC - tC;
	// int tmp = 0;
	// for (int i = 0; i != times; i++) {
	// tmp = m[tR][tC + i];
	// m[tR][tC + i] = m[dR - i][tC];
	// m[dR - i][tC] = m[dR][dC - i];
	// m[dR][dC - i] = m[tR + i][dC];
	// m[tR + i][dC] = tmp;
	// }
	// }

	public static void rotate(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}

		int a = 0;
		int b = 0;
		int x = matrix[0].length - 1;
		int y = matrix.length - 1;

		while (a <= x && b <= y) {
			rotateEdge(matrix, a++, b++, x--, y--);
		}
	}

	private static void rotateEdge(int[][] matrix, int a, int b, int x, int y) {
		int tem;
		for (int i = b; i < y; i++) {
			tem = matrix[a][b + i];
			matrix[a][b + i] = matrix[x - i][b];
			matrix[x - i][b] = matrix[x][y - i];
			matrix[x][y - i] = matrix[a + i][y];
			matrix[a + i][y] = tem;
		}
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
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		printMatrix(matrix);
		rotate(matrix);
		System.out.println("=========");
		printMatrix(matrix);

	}
}
