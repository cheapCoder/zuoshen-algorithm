package A4high.class04;

import java.util.Arrays;

// 给定一个数组arr，长度为N且每个值都是正数，代表N个人的体重。再给定一个正数 limit，代表一艘船的载重。
// 以下是坐船规则，1)每艘船最多只能做两人;2)乘客的体重和不能超过limit。
// 返回如果同时让这N个人过河最少需要几条船。

// 贪心
public class C01_MinBoat {

	public static int minBoat(int[] arr, int weight) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		Arrays.sort(arr);
		int lessR = -1;
		for (int i = arr.length - 1; i >= 0; i--) {
			if (arr[i] <= (weight / 2)) {
				lessR = i;
				break;
			}
		}
		if (lessR == -1) {
			return arr.length;
		}
		int lessIndex = lessR;
		int moreIndex = lessR + 1;
		int lessUnused = 0;
		while (lessIndex >= 0) {
			int solved = 0;
			while (moreIndex < arr.length
					&& arr[lessIndex] + arr[moreIndex] <= weight) {
				moreIndex++;
				solved++;
			}
			if (solved == 0) {
				lessUnused++;
				lessIndex--;
			} else {
				lessIndex = Math.max(-1, lessIndex - solved);
			}
		}
		int lessAll = lessR + 1;
		int lessUsed = lessAll - lessUnused;
		int moreUnsolved = arr.length - lessR - 1 - lessUsed;
		return lessUsed + ((lessUnused + 1) >> 1) + moreUnsolved;
	}

	// 从中间分界位置开始双指针

	// 从两端位置开始双指针
	public static int minBoat2(int[] arr, int limit) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		Arrays.sort(arr);
		if (arr[arr.length - 1] > limit) {
			System.out.println("超重！");
			return Integer.MAX_VALUE;
		}

		float res = 0;
		int half = limit / 2;
		int border = 0;
		// 获取大于limit/2的第一个数的索引
		if (arr[0] > half) {
			border = 0;
		} else if (arr[arr.length - 1] <= half) {
			border = arr.length;
		} else {
			// 二分查找
			int leftBorder = 0;
			int rightBorder = arr.length - 1;
			while (leftBorder <= rightBorder) {
				int mid = (leftBorder + rightBorder) / 2;
				if (arr[mid] > half && (mid == 0 || arr[mid - 1] <= half)) {
					border = mid;
					break;
				} else if (arr[mid] <= half) {
					leftBorder = mid + 1;
				} else {
					rightBorder = mid - 1;
				}
			}
		}

		int left = border - 1;
		int right = border;
		while (left >= 0 && right < arr.length) {
			// 不超重，双指针往两边扩展
			if (arr[left] + arr[right] <= limit) {
				res += 1;
				left--;
				right++;
			} else {
				// 超重，只右指针扩展
				res += 0.5;
				left--;
			}
		}
		// 结清最后没计算的人, 左侧可能的剩余人/2+右侧可能剩余的人
		res += (left + 1) / 2.0 + (arr.length - right);
		return (int) Math.ceil(res);
	}

	public static int[] generator() {
		int[] arr = new int[(int) (Math.random() * 6) + 8];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 50 + 2);
		}
		return arr;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 2, 2, 2, 4, 4, 4, 4, 5 };
		int limit = 5;
		for (int i = 0; i < 1000000; i++) {
			arr = generator();
			limit = (int) (Math.random() * 55 + 52); // 保证limit大于arr最大值
			// Arrays.sort(arr);
			int ans1 = minBoat(arr, limit);
			int ans2 = minBoat2(arr, limit);
			if (ans1 != ans2) {
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println("------");
			}
		}

	}

}
