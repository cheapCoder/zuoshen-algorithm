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

	// 暴力O(n2)
	public static boolean isContains(int[][] matrix, int K) {
		if (matrix == null) {
			return false;
		}

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == K) {
					return true;
				}
			}
		}
		return false;
	}

	// O(n)复杂度
	public static boolean isContains2(int[][] matrix, int K) {
		if (matrix == null || matrix[0][0] > K) {
			return false;
		}

		int row = 0;
		// 为什么列从最后一列开始，因为这样整个查找过程都是col--; 从0开始的话，一开始的col++，后面可能有要col--，不够固定
		int col = matrix[0].length - 1;
		while (row < matrix.length && col >= 0) {
			if (matrix[row][col] == K) {
				return true;
			} else if (matrix[row][col] > K) {
				col--;
			} else if (matrix[row][col] < K) {
				row++;
			}
		}
		return false;
	}

	// TODO:尝试"二维"二分法
	public static boolean isContains3(int[][] matrix, int K) {
		if (matrix == null) {
			return false;
		}

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
		System.out.println(isContains3(matrix, K));
	}

}
