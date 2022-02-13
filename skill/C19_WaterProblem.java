package skill;

import java.util.HashMap;
import java.util.Map.Entry;

// 给定一个数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器，请返回容器能装多少水
// 比如，arr = {3，1，2，5，2，4}，根据值画出的直方图就是容器形状，该容器可以装下5格水
// 再比如，arr = {4，5，1，3，2}，该容器可以装下2格水
public class C19_WaterProblem {

	// public static int getWater1(int[] arr) {
	// if (arr == null || arr.length < 3) {
	// return 0;
	// }
	// int value = 0;
	// for (int i = 1; i < arr.length - 1; i++) {
	// int leftMax = 0;
	// int rightMax = 0;
	// for (int l = 0; l < i; l++) {
	// leftMax = Math.max(arr[l], leftMax);
	// }
	// for (int r = i + 1; r < arr.length; r++) {
	// rightMax = Math.max(arr[r], rightMax);
	// }
	// value += Math.max(0, Math.min(leftMax, rightMax) - arr[i]);
	// }
	// return value;
	// }

	// 空间复杂度：O(N)
	// public static int getWater2(int[] arr) {
	// if (arr == null || arr.length < 3) {
	// return 0;
	// }
	// int n = arr.length - 2;
	// int[] leftMaxs = new int[n];
	// leftMaxs[0] = arr[0];
	// for (int i = 1; i < n; i++) {
	// leftMaxs[i] = Math.max(leftMaxs[i - 1], arr[i]);
	// }
	// int[] rightMaxs = new int[n];
	// rightMaxs[n - 1] = arr[n + 1];
	// for (int i = n - 2; i >= 0; i--) {
	// rightMaxs[i] = Math.max(rightMaxs[i + 1], arr[i + 2]);
	// }
	// int value = 0;
	// for (int i = 1; i <= n; i++) {
	// value += Math.max(0, Math.min(leftMaxs[i - 1], rightMaxs[i - 1]) - arr[i]);
	// }
	// return value;
	// }

	// public static int getWater3(int[] arr) {
	// if (arr == null || arr.length < 3) {
	// return 0;
	// }
	// int n = arr.length - 2;
	// int[] rightMaxs = new int[n];
	// rightMaxs[n - 1] = arr[n + 1];
	// for (int i = n - 2; i >= 0; i--) {
	// rightMaxs[i] = Math.max(rightMaxs[i + 1], arr[i + 2]);
	// }
	// int leftMax = arr[0];
	// int value = 0;
	// for (int i = 1; i <= n; i++) {
	// value += Math.max(0, Math.min(leftMax, rightMaxs[i - 1]) - arr[i]);
	// leftMax = Math.max(leftMax, arr[i]);
	// }
	// return value;
	// }

	// // 空间复杂度：O(1)
	// public static int getWater4(int[] arr) {
	// if (arr == null || arr.length < 3) {
	// return 0;
	// }
	// int value = 0;
	// int leftMax = arr[0];
	// int rightMax = arr[arr.length - 1];
	// int l = 1;
	// int r = arr.length - 2;
	// while (l <= r) {
	// if (leftMax <= rightMax) {
	// value += Math.max(0, leftMax - arr[l]);
	// leftMax = Math.max(leftMax, arr[l++]);
	// } else {
	// value += Math.max(0, rightMax - arr[r]);
	// rightMax = Math.max(rightMax, arr[r--]);
	// }
	// }
	// return value;
	// }

	// 暴力查找左右最大值
	public static int getWater1(int[] arr) {
		if (arr == null || arr.length < 3) {
			return 0;
		}

		int res = 0;
		int leftMax = arr[0];
		int rightMax = arr[arr.length - 1];
		for (int i = 1; i < arr.length - 1; i++) {
			// 左侧最大值
			leftMax = arr[0];
			for (int j = 1; j < i; j++) {
				if (arr[j] > leftMax) {
					leftMax = arr[j];
				}
			}
			// 右侧最大值
			rightMax = arr[arr.length - 1];
			for (int j = arr.length - 2; j > i; j--) {
				if (arr[j] > rightMax) {
					rightMax = arr[j];
				}
			}
			int maxLess = Math.min(leftMax, rightMax);
			if (maxLess > arr[i]) {
				res += maxLess - arr[i];
			}
		}
		return res;
	}

	// 预处理缓存
	public static int getWater2(int[] arr) {
		if (arr == null || arr.length < 3) {
			return 0;
		}

		// 对左右两侧最大值预处理
		int[] leftMaxArr = new int[arr.length];
		leftMaxArr[0] = -1;
		for (int i = 1; i < leftMaxArr.length; i++) {
			leftMaxArr[i] = Math.max(leftMaxArr[i - 1], arr[i - 1]);
		}
		int[] rightMaxArr = new int[arr.length];
		rightMaxArr[rightMaxArr.length - 1] = -1;
		for (int i = rightMaxArr.length - 2; i >= 0; i--) {
			rightMaxArr[i] = Math.max(rightMaxArr[i + 1], arr[i + 1]);
		}

		int res = 0;
		for (int i = 1; i < rightMaxArr.length - 1; i++) {
			int lessMax = Math.min(leftMaxArr[i], rightMaxArr[i]);
			if (lessMax > arr[i]) {
				res += lessMax - arr[i];
			}
		}
		return res;
	}

	public static int getWater3(int[] arr) {
		if (arr == null || arr.length < 3) {
			return 0;
		}

		// 只对右侧最大值预处理
		int[] rightMaxArr = new int[arr.length];
		rightMaxArr[rightMaxArr.length - 1] = -1;
		for (int i = rightMaxArr.length - 2; i >= 0; i--) {
			rightMaxArr[i] = Math.max(rightMaxArr[i + 1], arr[i + 1]);
		}

		int res = 0;
		int leftMax = arr[0];
		for (int i = 1; i < rightMaxArr.length - 1; i++) {
			leftMax = Math.max(leftMax, arr[i - 1]);
			int lessMax = Math.min(leftMax, rightMaxArr[i]);
			if (lessMax > arr[i]) {
				res += lessMax - arr[i];
			}
		}
		return res;
	}

	// 双指针
	public static int getWater4(int[] arr) {
		if (arr == null || arr.length < 3) {
			return 0;
		}

		int leftI = 1;
		int leftMax = arr[0];
		int rightI = arr.length - 2;
		int rightMax = arr[arr.length - 1];

		int res = 0;
		while (leftI <= rightI) {
			if (leftMax <= rightMax) {
				if (arr[leftI] < leftMax) {
					res += leftMax - arr[leftI];
				} else {
					leftMax = arr[leftI];
				}
				leftI++;
			} else {
				if (arr[rightI] < rightMax) {
					res += rightMax - arr[rightI];
				} else {
					rightMax = arr[rightI];
				}
				rightI--;
			}
		}
		return res;
	}

	// for test
	public static int[] generateRandomArray() {
		int[] arr = new int[(int) (Math.random() * 98) + 2];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 200) + 2;
		}
		return arr;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1000000; i++) {
			int[] arr = generateRandomArray();
			int r1 = getWater1(arr);
			int r2 = getWater3(arr);
			int r3 = getWater3(arr);
			int r4 = getWater4(arr);
			if (r1 != r2 || r3 != r4 || r1 != r3) {
				System.out.println("What a fucking day! fuck that! man!");
			}
		}

		HashMap<String, String> map = new HashMap<String, String>();

		for (Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " , " + entry.getValue());
		}

	}

}
