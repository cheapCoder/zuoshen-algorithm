package skill;

// LeetCode：https://leetcode-cn.com/problems/super-washing-machines/
// 有n个打包机器从左到右一字排开，上方有一个自动装置会抓取一批放物品到每个打包机上，
// 放到每个机器上的这些物品数量有多有少，由于物品数量不相同，
// 需要工人将每个机器上的物品进行移动从而到达物品数量相等才能打包。(每轮所有位置的包都能移动一次)
// 每个物品重量太大、 每次只能搬一个物品进行移动，为了省力，只在相邻的机器上移动。
// 请计算在搬动最小轮数的前提下，使每个机器上的物品数量相等。如果不能使每个机器上的物品相同，返回-1。
// 例如[1,0,5]表示有3个机器，每个机器上分别有1、0、5个物品，经过这些轮后: 
// 第一轮:1 0<-5 => 1 1 4
// 第二轮:1<-1<-4 => 2 1 3	
// 第三轮:2 1 <- 3 => 2 2 2 
// 移动了3轮，每个机器上的物品相等，所以返回3
// 例如[2,2,3]表示有3个机器，每个机器上分别有2、2、3个物品，这些物品不管怎么移动，
// 都不能使三个机器上物品数量相等，返回-1

// NOTE:复杂的题目，考虑任意位置i的相关情况，一般是i的值什么情况，左侧什么情况，右侧什么情况，进行分类讨论，发现规律
public class C16_PackingMachine {

	// public static int MinOps(int[] arr) {
	// if (arr == null || arr.length == 0) {
	// return 0;
	// }
	// int size = arr.length;
	// int sum = 0;
	// for (int i = 0; i < size; i++) {
	// sum += arr[i];
	// }
	// if (sum % size != 0) {
	// return -1;
	// }
	// int avg = sum / size;
	// int leftSum = 0;
	// int ans = 0;
	// for (int i = 0; i < arr.length; i++) {
	// int L = i * avg - leftSum;
	// int R = (size - i - 1) * avg - (sum - leftSum - arr[i]);
	// if (L > 0 && R > 0) {
	// ans = Math.max(ans, Math.abs(L) + Math.abs(R));
	// } else {
	// ans = Math.max(ans, Math.max(Math.abs(L), Math.abs(R)));
	// }
	// leftSum += arr[i];
	// }
	// return ans;
	// }

	public static int MinOps2(int[] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		if (sum % arr.length != 0) {
			return -1;
		}

		int average = sum / arr.length;
		int preOver = 0; // 当前值之前部分多出的份数
		int postOver = (sum - arr[0]) - (average * (arr.length - 1)); // 当前值之后部分多出的份数
		int res = arr[0] - average * 1;
		for (int i = 1; i < arr.length; i++) {
			preOver += (arr[i - 1] - average);
			postOver -= (arr[i] - average);

			// if (preOver > 0 && postOver > 0) {
			// res = Math.max(res, Math.max(preOver, postOver));
			// } else if (preOver <= 0 && postOver <= 0) {
			// res = Math.max(res, -(preOver + postOver));
			// } else {
			// res = Math.max(res, Math.max(Math.abs(preOver), Math.abs(postOver)));
			// }

			// 简化后
			if (preOver <= 0 && postOver <= 0) {
				res = Math.max(res, -(preOver + postOver));
			} else {
				res = Math.max(res, Math.max(Math.abs(preOver), Math.abs(postOver)));
			}
		}

		return res;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 4, 9, 29 };
		// System.out.println(MinOps(arr));
		System.out.println(MinOps2(arr));

	}

}
