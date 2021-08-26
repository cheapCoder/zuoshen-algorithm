package dp;
// 排成一条线的纸牌博弈问题
// 【题目】
// 给定一个整型数组arr，代表数值不同的纸牌排成一条线。玩家A和玩家B依次拿走每张纸牌，规定玩家A先拿，玩家B后拿，
// 但是每个玩家每次只能拿走最左或最右的纸牌，玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。

//【举例】
// arr=[1,2,100,4]。
// 开始时，玩家A只能拿走1或4。如果玩家A拿走1，则排列变为[2,100,4]，接下来玩家B可以拿走2或4，
// 然后继续轮到玩家A。如果开始时玩家A拿走4，则排列变为[1,2,100]，接下来玩家B可以拿走1或100，
// 然后继续轮到玩家A。玩家A作为绝顶聪明的人不会先拿4，因为拿4之后，玩家B将拿走100。
// 所以玩家A会先拿1，让排列变为[2,100,4]，接下来玩家B不管怎么选，100都会被玩家A拿走。玩家A会获胜，分数为101。
// 所以返回101。arr=[1,100,2]。
//开始时，玩家A不管拿1还是2，玩家B作为绝顶聪明的人，都会把100拿走。玩家B会获胜，分数为100。所以返回Í00
public class C03_CardsInLine {

	// public static int win1(int[] arr) {
	// if (arr == null || arr.length == 0) {
	// return 0;
	// }
	// return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
	// }

	// public static int f(int[] arr, int i, int j) {
	// if (i == j) {
	// return arr[i];
	// }
	// return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
	// }

	// public static int s(int[] arr, int i, int j) {
	// if (i == j) {
	// return 0;
	// }
	// return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));
	// }

	// public static int win2(int[] arr) {
	// if (arr == null || arr.length == 0) {
	// return 0;
	// }
	// int[][] f = new int[arr.length][arr.length];
	// int[][] s = new int[arr.length][arr.length];
	// for (int j = 0; j < arr.length; j++) {
	// f[j][j] = arr[j];
	// for (int i = j - 1; i >= 0; i--) {
	// f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
	// s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
	// }
	// }
	// return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
	// }
	// 法一 暴力递归
	public static int win1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}

		return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
	}

	private static int f(int[] arr, int left, int right) {
		if (left == right) {
			return arr[left];
		}

		return Math.max(arr[left] + s(arr, left + 1, right), arr[right] + s(arr, left, right - 1));
	}

	private static int s(int[] arr, int left, int right) {
		if (left == right) {
			return 0;
		}
		// NOTE: 不用加arr[left]和arr[right], 数组后选就相当于在先选后剩下的数组中进行先选
		return Math.min(f(arr, left + 1, right), f(arr, left, right - 1));
	}

	// 法二 记忆化搜索
	public static int win2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[][] fCache = new int[arr.length][arr.length];
		int[][] sCache = new int[arr.length][arr.length];
		for (int i = 0; i < sCache.length; i++) {
			for (int j = 0; j < sCache.length; j++) {
				fCache[i][j] = -1;
				sCache[i][j] = -1;
			}
		}

		return Math.max(f2(arr, 0, arr.length - 1, fCache, sCache), s2(arr, 0, arr.length - 1, fCache, sCache));
	}

	private static int f2(int[] arr, int left, int right, int[][] fCache, int[][] sCache) {
		if (fCache[left][right] == -1) {
			if (left == right) {
				fCache[left][right] = arr[left];
			} else {

				fCache[left][right] = Math.max(arr[left] + s2(arr, left + 1, right, fCache, sCache),
						arr[right] + s2(arr, left, right - 1, fCache, sCache));
			}
		}
		return fCache[left][right];
	}

	private static int s2(int[] arr, int left, int right, int[][] fCache, int[][] sCache) {
		if (sCache[left][right] == -1) {
			if (left == right) {
				sCache[left][right] = 0;
			} else {
				sCache[left][right] = Math.min(f2(arr, left + 1, right, fCache, sCache),
						f2(arr, left, right - 1, fCache, sCache));

			}
		}
		return sCache[left][right];
	}

	// TODO:法三 严格表结构
	public static int win3(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[][] fArr = new int[arr.length][arr.length];
		int[][] sArr = new int[arr.length][arr.length];
		// base case初始化
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (i == j) {
					fArr[i][j] = arr[i];
					sArr[i][j] = 0;
				}
			}
		}

		// 任意位置的值计算
		for (int j = 1; j < arr.length; j++) {
			for (int i = j - 1; i >= 0; i--) {
				fArr[i][j] = Math.max(arr[i] + sArr[i + 1][j], arr[j] + sArr[i][j - 1]);
				sArr[i][j] = Math.min(fArr[i + 1][j], fArr[i][j - 1]);
			}
		}

		return Math.max(fArr[0][arr.length - 1], sArr[0][arr.length - 1]);
	}

	public static void main(String[] args) {
		int[] arr = { 1, 9, 1, 4 };
		System.out.println(win1(arr));
		System.out.println(win2(arr));
		System.out.println(win3(arr));
	}
}
