package hash_function;

public class Code03_Islands {

	// public static int countIslands(int[][] m) {
	// if (m == null || m[0] == null) {
	// return 0;
	// }
	// int N = m.length;
	// int M = m[0].length;
	// int res = 0;
	// for (int i = 0; i < N; i++) {
	// for (int j = 0; j < M; j++) {
	// if (m[i][j] == 1) {
	// res++;
	// infect(m, i, j, N, M);
	// }
	// }
	// }
	// return res;
	// }

	// public static void infect(int[][] m, int i, int j, int N, int M) {
	// if (i < 0 || i >= N || j < 0 || j >= M || m[i][j] != 1) {
	// return;
	// }
	// m[i][j] = 2;
	// infect(m, i + 1, j, N, M);
	// infect(m, i - 1, j, N, M);
	// infect(m, i, j + 1, N, M);
	// infect(m, i, j - 1, N, M);
	// }

	public static int countIslands(int[][] arr) {
		int count = 0;

		for (int col = 0; col < arr.length; col++) {
			for (int row = 0; row < arr[col].length; row++) {
				if (arr[col][row] == 1) {
					infect(arr, row, col);
					count++;
				}
			}
		}
		return count;
	}

	private static void infect(int[][] arr, int row, int col) {
		if ((col < 0 || col >= arr.length) || (row < 0 || row >= arr[col].length) || arr[col][row] != 1) {
			return;
		}
		arr[col][row] = 2;
		infect(arr, row, col - 1);
		infect(arr, row, col + 1);
		infect(arr, row - 1, col);
		infect(arr, row + 1, col);
	}

	public static void main(String[] args) {
		int[][] m1 = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 0, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
				{ 0, 1, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
		System.out.println(countIslands(m1));

		int[][] m2 = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 1, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
				{ 0, 1, 1, 0, 0, 0, 1, 1, 0 }, { 0, 0, 0, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
		System.out.println(countIslands(m2));

	}

}
