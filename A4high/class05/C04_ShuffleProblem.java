package A4high.class05;

import java.util.Arrays;

// NOTE: 完美洗牌问题
// 结论：当数组长度符合3的k次方-1时，每个独立循环的范围是0,2,8,26...3的k-1次方-1(k>=1)

// 给定一个长度为偶数的数组arr，长度记为2*N。前N个为左部分，后N个为右部分。
// arr就可以表示为{L1,L2,..,Ln,R1,R2,..,Rn}， 请将数组调整成{R1,L1,R2,L2,..,Rn,Ln}的样子。
// 要求时间复杂度O(Nlog3N)空间复杂度O(1)

// 题目：给定任意长度的无序数组,请经过一些操作使数组变成两两相邻的数字为循环的大于小于规律
// 即 1<2>3<4>5<6...

public class C04_ShuffleProblem {

	// 数组的长度为len，调整前的位置是i，返回调整之后的位置
	// 下标不从0开始，从1开始
	public static int modifyIndex1(int i, int len) {
		if (i <= len / 2) {
			return 2 * i;
		} else {
			return 2 * (i - (len / 2)) - 1;
		}
	}

	// 数组的长度为len，调整前的位置是i，返回调整之后的位置
	// 下标不从0开始，从1开始
	public static int modifyIndex2(int i, int len) {
		return (2 * i) % (len + 1);
	}

	// 主函数
	// 数组必须不为空，且长度为偶数
	public static void shuffle(int[] arr) {
		if (arr != null && arr.length != 0 && (arr.length & 1) == 0) {
			shuffle(arr, 0, arr.length - 1);
		}
	}

	// 在arr[L..R]上做完美洗牌的调整
	public static void shuffle(int[] arr, int L, int R) {
		while (R - L + 1 > 0) { // 切成一块一块的解决，每一块的长度满足(3^k)-1
			int len = R - L + 1;
			int base = 3;
			int k = 1;
			// 计算小于等于len并且是离len最近的，满足(3^k)-1的数
			// 也就是找到最大的k，满足3^k <= len+1
			while (base <= (len + 1) / 3) {
				base *= 3;
				k++;
			}
			// 当前要解决长度为base-1的块，一半就是再除2
			int half = (base - 1) / 2;
			// [L..R]的中点位置
			int mid = (L + R) / 2;
			// 要旋转的左部分为[L+half...mid], 右部分为arr[mid+1..mid+half]
			// 注意在这里，arr下标是从0开始的
			rotate(arr, L + half, mid, mid + half);
			// 旋转完成后，从L开始算起，长度为base-1的部分进行下标连续推
			cycles(arr, L, base - 1, k);
			// 解决了前base-1的部分，剩下的部分继续处理
			L = L + base - 1;
		}
	}

	// 从start位置开始，往右len的长度这一段，做下标连续推
	// 出发位置依次为1,3,9...
	public static void cycles(int[] arr, int start, int len, int k) {
		// 找到每一个出发位置trigger，一共k个
		// 每一个trigger都进行下标连续推
		// 出发位置是从1开始算的，而数组下标是从0开始算的。
		for (int i = 0, trigger = 1; i < k; i++, trigger *= 3) {
			int preValue = arr[trigger + start - 1];
			int cur = modifyIndex2(trigger, len);
			while (cur != trigger) {
				int tmp = arr[cur + start - 1];
				arr[cur + start - 1] = preValue;
				preValue = tmp;
				cur = modifyIndex2(cur, len);
			}
			arr[cur + start - 1] = preValue;
		}
	}

	// [L..M]为左部分，[M+1..R]为右部分，左右两部分互换
	public static void rotate(int[] arr, int L, int M, int R) {
		reverse(arr, L, M);
		reverse(arr, M + 1, R);
		reverse(arr, L, R);
	}

	// [L..R]做逆序调整
	public static void reverse(int[] arr, int L, int R) {
		while (L < R) {
			int tmp = arr[L];
			arr[L++] = arr[R];
			arr[R--] = tmp;
		}
	}

	public static void wiggleSort(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		// 假设这个排序是额外空间复杂度O(1)的，当然系统提供的排序并不是，你可以自己实现一个堆排序
		Arrays.sort(arr);
		if ((arr.length & 1) == 1) {
			shuffle(arr, 1, arr.length - 1);
		} else {
			shuffle(arr, 0, arr.length - 1);
			for (int i = 0; i < arr.length; i += 2) {
				int tmp = arr[i];
				arr[i] = arr[i + 1];
				arr[i + 1] = tmp;
			}
		}
	}

	public static void wiggleSort2(int[] arr) {
		if (arr == null || arr.length == 0 || arr.length % 2 != 0) {
			return;
		}

		Arrays.sort(arr);
		if ((arr.length & 1) == 1) { // 是奇数
			shuffle2(arr, 1, arr.length - 1);
		} else {
			shuffle(arr);
			reverse(arr, 0, arr.length - 1);
		}
	}

	public static void shuffle2(int[] arr) {
		if (arr == null || arr.length == 0 || arr.length % 2 != 0) {
			return;
		}

		// arr = new int[] { 1, 47, 70, 32, 86, 46, 54, 25, 40, 70 };
		shuffle2(arr, 0, arr.length - 1);
		// printArray(arr);
	}

	public static void shuffle2(int[] arr, int start, int end) {
		if (start >= end) {
			return;
		}

		// System.out.println(start);
		// System.out.println(end);

		// 计算k的最大值，范围是0,2,8,26...3的k-1次方-1(k>=1)
		int k = -1;
		while (Math.pow(3, k + 1) - 1 <= end - start + 1) {
			k++;
		}
		// System.out.println(k);
		// System.out.println("-----");
		int maxCanComputeSubArrLength = (int) Math.pow(3, k) - 1;

		// 分部分翻转的几个数学关系:
		// left-right为当前数组长的一半((end - start + 1) / 2)
		// border-right为可套用推论的最长子数组长度(maxCanComputeSubArrLength / 2)
		// left-border则为剩下长度
		partReverse(arr, (start + end + 1) / 2 - ((end - start + 1) / 2 - maxCanComputeSubArrLength / 2),
				(start + end + 1) / 2 + maxCanComputeSubArrLength / 2 - 1, (start + end) / 2);
		// System.out.println("reverse");
		// printArray(arr);

		for (int i = 1; i <= k; i++) {
			loopReplace(arr, (int) Math.pow(3, i - 1) - 1 + start, start, start + maxCanComputeSubArrLength - 1);
			// printArray(arr);

		}

		shuffle2(arr, maxCanComputeSubArrLength + start, end);
	}

	// 将数组的左部分迁移到右部分
	// border处于左部分内
	private static void partReverse(int[] arr, int start, int end, int border) {
		if (border < start || end < border) {
			return;
		}

		reverseBetween(arr, start, border);
		reverseBetween(arr, border + 1, end);
		reverseBetween(arr, start, end);
	}

	private static void reverseBetween(int[] arr, int start, int end) {
		if (start >= end) {
			return;
		}
		int tem;
		for (int delta = 0; start + delta < end - delta; delta++) {
			tem = arr[start + delta];
			arr[start + delta] = arr[end - delta];
			arr[end - delta] = tem;
		}
	}

	// 46 1 54 47 25 70 40 32
	private static void loopReplace(int[] arr, int i, int start, int end) {
		int inputI = i;
		int cur = arr[i];
		int pre;
		int nextI;
		do {
			nextI = getIndex(end - start + 1, i - start) + start;
			pre = cur;
			cur = arr[nextI];
			arr[nextI] = pre;
			i = nextI;
		} while (i != inputI);
	}

	private static int getIndex(int length, int i) {
		if (i < length / 2) {
			return 2 * i + 1;
		} else {
			return 2 * i - length;
		}
	}

	// for test
	public static boolean isValidWiggle(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			if ((i & 1) == 1 && arr[i] < arr[i - 1]) {
				return false;
			}
			if ((i & 1) == 0 && arr[i] > arr[i - 1]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println();
	}

	// for test
	public static int[] generateArray() {
		int len = (int) (Math.random() * 10) * 2;
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * 100);
		}
		return arr;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5000000; i++) {
			int[] arr = generateArray();
			// printArray(arr);
			wiggleSort2(arr);
			if (!isValidWiggle(arr)) {
				System.out.println("ooops!");
				printArray(arr);
				break;
			}
		}
	}

}
