package skill;

// 给定一个元素为非负整数的二维数组matrix，每行和每列都是从小到大有序的。 
// 再给定一个非负整数aim，请判断aim是否在matrix中。

public class C15_FindNumInSortedMatrix {

	// public static boolean isContains(int[][] matrix, int K) {
	// int row = 0;
	// int col = matrix[0].length - 1;
	// while (row < matrix.length && col > -1) {
	// if (matrix[row][col] == K) {
	// return true;
	// } else if (matrix[row][col] > K) {
	// col--;
	// } else {
	// row++;
	// }
	// }
	// return false;
	// }

	// TODO:
	public static boolean isContains(int[][] matrix, int K) {
		// if (matrix == null) {
		// return false;
		// }

		// int row = 0;
		// int col = 0;
		// while () {

		// }
		return false;
	}

	// TODO:尝试二维二分法
	public static boolean isContains2(int[][] matrix, int K) {
		if (matrix == null) {
			return false;
		}

		// int left = 0;
		// int right = matrix[0].length - 1;
		// int rowMid = 0;
		// while (left < right) {
		// rowMid = (right + left) / 2;
		// if (matrix[0][rowMid] > K) {
		// right = rowMid - 1;
		// } else if (matrix[0][rowMid + 1] < K) {
		// left = rowMid + 1;
		// } else if (matrix[0][rowMid] < K && matrix[0][rowMid + 1] > K) {
		// break;
		// }
		// }
		// if (left >= right) {
		// rowMid = right;
		// }
		// // System.out.println(1);
		// int top = 0;
		// int bottom = matrix.length - 1;
		// int colMid = 0;
		// while (top < bottom) {
		// colMid = (bottom + top) / 2;
		// if (matrix[0][rowMid] <= K && (rowMid + 1 >= matrix[0].length ||
		// matrix[0][rowMid + 1] >= K)) {
		// break;
		// } else if (matrix[rowMid][colMid] > K) {
		// bottom = colMid - 1;
		// } else if (matrix[rowMid][colMid] < K) {
		// top = colMid + 1;
		// }
		// }
		// if (top >= bottom) {
		// colMid = bottom;
		// System.out.println(colMid);
		// }

		// left = 0;
		// right = rowMid;
		// while (left <= right) {
		// rowMid = (right + left) / 2;
		// if (matrix[rowMid][colMid] == K) {
		// return true;
		// } else if (matrix[rowMid][colMid] > K) {
		// right = rowMid - 1;
		// } else {
		// left = rowMid + 1;
		// }
		// }

		return false;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 0, 1, 2, 3, 4, 5, 6 }, // 0
				{ 10, 12, 13, 15, 16, 17, 18 }, // 1
				{ 23, 24, 25, 26, 27, 28, 29 }, // 2
				{ 44, 45, 46, 47, 48, 49, 50 }, // 3
				{ 65, 66, 67, 68, 69, 70, 71 }, // 4
				{ 96, 97, 98, 99, 100, 111, 122 }, // 5
				{ 166, 176, 186, 187, 190, 195, 200 }, // 6
				{ 233, 243, 321, 341, 356, 370, 380 } // 7
		};
		int K = 233;
		System.out.println(isContains(matrix, K));
		System.out.println(isContains2(matrix, K));
	}

}
