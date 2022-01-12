package A4high.class04;

import java.util.Arrays;
import java.util.LinkedList;

// 给定一个数组arr，长度为N且每个值都是正数，代表N个人的体重。再给定一个正数 limit，代表一艘船的载重。
// 以下是坐船规则，1)每艘船最多只能做两人;2)乘客的体重和不能超过limit。
// 返回如果同时让这N个人过河最少需要几条船。

// 贪心
public class C01_MinBoat {

	// 请保证arr有序
	public static int minBoat(int[] arr, int weight) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
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
		int left = 0;
		int right = arr.length - 1;
	while (left < right) {
		
	}
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
		for (int i = 0; i < 10000; i++) {
			arr = generator();
			limit = (int) (Math.random() * 55 + 50);
			int ans1 = minBoat(arr, limit);
			int ans2 = minBoat2(arr, limit);
			// System.out.println(ans1);
		}

	}

}
