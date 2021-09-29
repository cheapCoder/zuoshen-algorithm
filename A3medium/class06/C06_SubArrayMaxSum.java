package A3medium.class06;

// 为了保证招聘信息的质量问题，公司为每个职位设计了打分系统，打分可以为正数，也可以为负数，
// 正数表示用户认可帖子质量，负数表示用户不认可帖子质量。
// 打分的分数根据评价用户的等级大小不定，比如可以为 -1分，10分，30分，-10分等。
// 假设数组A记录了一条帖子所有打分记录，现在需要找出帖子曾经得到过最高的分数是多少。
// 其中，最高分的定义为: 用户所有打分记录中，连续打分数据之和的最大值即认为是帖子曾经获得的最高分。
// 例 如:帖子10001010近期的打 分记录为[1,1,-1,-10,11,4,-6,9,20,-10,-2],
// 那么该条帖子曾经到达过的最高分数为 11+4+(-6)+9+20=38。
// 请实现一段代码，输入为帖子近期的打分记录，输出为当前帖子 得到的最高分数。

// 子数组最大和问题
// NOTE: 假设答案法
// 较大子数组之间的分界是：子数组和小于等于0时，因为当和小于等于0时完全可以不要这部分负担
public class C06_SubArrayMaxSum {
	// public static int maxSum(int[] arr) {
	// if (arr == null || arr.length == 0) {
	// return 0;
	// }
	// int max = Integer.MIN_VALUE;
	// int cur = 0;
	// for (int i = 0; i != arr.length; i++) {
	// cur += arr[i];
	// max = Math.max(max, cur);
	// cur = cur < 0 ? 0 : cur;
	// }
	// return max;
	// }

	public static int maxSum(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}

		int maxSum = Integer.MIN_VALUE;
		int curSum = 0;
		for (int i = 0; i < arr.length; i++) {
			curSum += arr[i];
			maxSum = Math.max(curSum, maxSum);
			if (curSum <= 0) {
				curSum = 0;
			}
		}
		return maxSum;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr1 = { -2, -3, -5, 40, -10, -10, 100, 1 };
		System.out.println(maxSum(arr1));

		int[] arr2 = { -2, -3, -5, 0, 1, 2, -1 };
		System.out.println(maxSum(arr2));

		int[] arr3 = { -2, -3, -5, -1 };
		System.out.println(maxSum(arr3));

	}

}
