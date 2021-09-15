package others;

// 给定一个数组arr长度为N，你可以把任意长度大于0且小于N的前缀作为左部分，剩下的作为右部分。
// 但是每种划分下都有左部分的最大值和右部分的最大值，
// 请返回左部分最大值减去右部分最大值的最大绝对值。

public class C04_MaxABSBetweenLeftAndRight {

	// public static int maxABS1(int[] arr) {
	// int res = Integer.MIN_VALUE;
	// int maxLeft = 0;
	// int maxRight = 0;
	// for (int i = 0; i != arr.length - 1; i++) {
	// maxLeft = Integer.MIN_VALUE;
	// for (int j = 0; j != i + 1; j++) {
	// maxLeft = Math.max(arr[j], maxLeft);
	// }
	// maxRight = Integer.MIN_VALUE;
	// for (int j = i + 1; j != arr.length; j++) {
	// maxRight = Math.max(arr[j], maxRight);
	// }
	// res = Math.max(Math.abs(maxLeft - maxRight), res);
	// }
	// return res;
	// }

	// public static int maxABS2(int[] arr) {
	// int[] lArr = new int[arr.length];
	// int[] rArr = new int[arr.length];
	// lArr[0] = arr[0];
	// rArr[arr.length - 1] = arr[arr.length - 1];
	// for (int i = 1; i < arr.length; i++) {
	// lArr[i] = Math.max(lArr[i - 1], arr[i]);
	// }
	// for (int i = arr.length - 2; i > -1; i--) {
	// rArr[i] = Math.max(rArr[i + 1], arr[i]);
	// }
	// int max = 0;
	// for (int i = 0; i < arr.length - 1; i++) {
	// max = Math.max(max, Math.abs(lArr[i] - rArr[i + 1]));
	// }
	// return max;
	// }

	// public static int maxABS3(int[] arr) {
	// int max = Integer.MIN_VALUE;
	// for (int i = 0; i < arr.length; i++) {
	// max = Math.max(arr[i], max);
	// }
	// return max - Math.min(arr[0], arr[arr.length - 1]);
	// }

	// 暴力解法
	public static int maxABS1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int res = Integer.MIN_VALUE;
		int leftMax;
		int rightMax;
		for (int i = 1; i < arr.length; i++) { // 当前索引值的左边界为数组左右边界
			leftMax = Integer.MIN_VALUE;
			for (int j = 0; j < i; j++) {
				leftMax = Math.max(leftMax, arr[j]);
			}

			rightMax = Integer.MIN_VALUE;
			for (int j = i; j < arr.length; j++) {
				rightMax = Math.max(rightMax, arr[j]);
			}

			res = Math.max(res, Math.abs(leftMax - rightMax));
		}
		return res;
	}

	// 预处理缓存
	public static int maxABS2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int[] leftMaxCache = new int[arr.length];
		int[] rightMaxCache = new int[arr.length];

		leftMaxCache[0] = arr[0];
		for (int i = 1; i < leftMaxCache.length; i++) {
			leftMaxCache[i] = Math.max(leftMaxCache[i - 1], arr[i]);
		}

		rightMaxCache[rightMaxCache.length - 1] = arr[arr.length - 1];
		for (int i = arr.length - 2; i >= 0; i--) {
			rightMaxCache[i] = Math.max(rightMaxCache[i + 1], arr[i]);
		}

		int res = Integer.MIN_VALUE;
		for (int i = 0; i < rightMaxCache.length; i++) {
			res = Math.max(res, Math.abs(leftMaxCache[i] - rightMaxCache[i]));
		}

		return res;
	}

	// 业务分析
	public static int maxABS3(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int leftMax = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length - 1; i++) {
			leftMax = Math.max(leftMax, arr[i]);
		}

		int rightMax = Integer.MIN_VALUE;
		for (int i = 1; i < arr.length; i++) {
			rightMax = Math.max(rightMax, arr[i]);
		}

		return Math.max(Math.abs(leftMax - arr[arr.length - 1]), Math.abs(rightMax - arr[0]));
	}

	// for test
	public static int[] generateRandomArray(int length) {
		int[] arr = new int[length];
		for (int i = 0; i != arr.length; i++) {
			arr[i] = (int) (Math.random() * 1000) - 499;
		}
		return arr;
	}

	public static void main(String[] args) {
		int[] arr = generateRandomArray(200);
		System.out.println(maxABS1(arr));
		System.out.println(maxABS2(arr));
		System.out.println(maxABS3(arr));
	}
}
