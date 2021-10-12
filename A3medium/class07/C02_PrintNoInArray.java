package A3medium.class07;

// 给定一个整数数组A，长度为n，有 1 <= A[i] <= n，且对于[1,n]的整数，其中部分整数会重复出现而部分不会出现。
// 实现算法找到[1,n]中所有未出现在A中的整数。
// 提示:尝试实现O(n)的时间复杂度和O(1)的空间复杂度(返回值不计入空间复 杂度)。
// 输入描述:
// 一行数字，全部为整数，空格分隔
// A0 A1 A2 A3...
// 输出描述:
// 一行数字，全部为整数，空格分隔R0 R1 R2 R3... 示例1:
// 输入
// 1343
// 输出
// 2
public class C02_PrintNoInArray {

	// // 请保证arr[0..N-1]上的数字都在[1～n]之间
	// public static void printNumberNoInArray(int[] arr) {
	// if (arr == null || arr.length == 0) {
	// return;
	// }
	// for (int i = 0; i < arr.length; i++) {
	// modify(arr[i], arr);
	// }
	// for (int i = 0; i < arr.length; i++) {
	// if (arr[i] != i + 1) {
	// System.out.println(i + 1);
	// }
	// }
	// }

	// public static void modify(int value, int[] arr) {
	// while (arr[value - 1] != value) {
	// int tmp = arr[value - 1];
	// arr[value - 1] = value;
	// value = tmp;
	// }
	// }

	// 法一
	public static void printNumberNoInArray(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != i + 1) {
				modify(arr, i);
			}
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != i + 1) {
				System.out.println(i + 1);
			}
		}
	}

	private static void modify(int[] arr, int i) {
		int cache;
		while (arr[i] != arr[arr[i] - 1]) {
			// tem = arr[arr[i] - 1];
			cache = arr[arr[i] - 1];
			arr[arr[i] - 1] = arr[i];
			arr[i] = cache;
		}
	}

	// 法二
	// NOTE: 可以通过改为负数，来标记对应索引的数存在
	public static void printNumberNoInArray2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 0 && arr[arr[i] - 1] > 0) {
				arr[arr[i] - 1] = -arr[arr[i] - 1];
			}
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 0) {
				System.out.println(i + 1);
			}
		}

		// 恢复数据
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < 0) {
				arr[i] = -arr[i];
			}
		}
	}

	public static void main(String[] args) {
		int[] test = { 3, 2, 3, 5, 6, 1, 6 };
		printNumberNoInArray2(test);
		System.out.println("---");
		printNumberNoInArray(test);
	}

}
