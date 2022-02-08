package A4high.class07;

// 汉诺塔游戏的要求把所有的圆盘从左边都移到右边的柱子上，给定一个整型数组arr，其中只含有1、2和3，代表所有圆盘目前的状态，
// 1代表左柱，2代表中柱，3代表右柱，arr[i]的值代表第i+1个圆盘的位置。 
// 比如，arr=[3,3,2,1]，代表第1个圆盘在右柱上、第2个圆盘在右柱上、第3个圆盘在中柱上、第4个圆盘在左柱上
// 如果arr代表的状态是最优移动轨迹过程中出现的状态，返回arr这种状态是最优移动轨迹中的第几个状态;
// 如果arr代表的状态不是最优移动轨迹过程中出现的状态，则返回-1。
public class C04_HanoiProblem {

	// public static int step1(int[] arr) {
	// if (arr == null || arr.length == 0) {
	// return -1;
	// }
	// return process(arr, arr.length - 1, 1, 2, 3);
	// }

	// public static int process(int[] arr, int i, int from, int mid, int to) {
	// if (i == -1) {
	// return 0;
	// }
	// if (arr[i] != from && arr[i] != to) {
	// return -1;
	// }
	// if (arr[i] == from) {
	// return process(arr, i - 1, from, to, mid);
	// } else {
	// int rest = process(arr, i - 1, mid, from, to);
	// if (rest == -1) {
	// return -1;
	// }
	// return (1 << i) + rest;
	// }
	// }

	public static int step2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int from = 1;
		int mid = 2;
		int to = 3;
		int i = arr.length - 1;
		int res = 0;
		int tmp = 0;
		while (i >= 0) {
			if (arr[i] != from && arr[i] != to) {
				return -1;
			}
			if (arr[i] == to) {
				res += 1 << i;
				tmp = from;
				from = mid;
			} else {
				tmp = to;
				to = mid;
			}
			mid = tmp;
			i--;
		}
		return res;
	}

	public static int step1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}


	}

	

	// for test
	public static int[] getRandomArray(int size, int max) {
		int[] arr = new int[(int) (Math.random() * size)];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * max);
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println();
	}

	public static void main(String[] args) {

		int[] arr = { 3, 3, 2, 1 };
		System.out.println(step1(arr));
		System.out.println(step2(arr));

		int size = 10;
		int max = 10;
		int testTimes = 3000000;
		for (int i = 0; i < testTimes; i++) {
			arr = getRandomArray(size, max);
			if (step1(arr) != step2(arr)) {
				printArray(arr);
				System.out.println(step1(arr));
				System.out.println(step2(arr));
				break;
			}
		}
	}
}
