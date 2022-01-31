package hash_function;

import java.util.Stack;

// 单调栈结构的原理和实现
// 在数组中想找到一个数，左边和右边比这个数小、且离这个数最近的位置。
// 如果对每一个数都想求这样的信息，能不能整体代价达到O(N)?需要使用到单调栈结构
public class C09_AllTimesMinToMax {

	public static int max1(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				int minNum = Integer.MAX_VALUE;
				int sum = 0;
				for (int k = i; k <= j; k++) {
					sum += arr[k];
					minNum = Math.min(minNum, arr[k]);
				}
				max = Math.max(max, minNum * sum);
			}
		}
		return max;
	}

	public static int max2(int[] arr) {
		int size = arr.length;
		int[] sums = new int[size];
		sums[0] = arr[0];
		for (int i = 1; i < size; i++) {
			sums[i] = sums[i - 1] + arr[i];
		}
		int max = Integer.MIN_VALUE;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < size; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
				int j = stack.pop();
				max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[j]);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int j = stack.pop();
			max = Math.max(max, (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[j]);
		}
		return max;
	}

	public static int[] gerenareRondomArray() {
		int[] arr = new int[(int) (Math.random() * 20) + 10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 101);
		}
		return arr;
	}

	public static void main(String[] args) {
		int testTimes = 2000000;
		for (int i = 0; i < testTimes; i++) {
			int[] arr = gerenareRondomArray();
			if (max1(arr) != max2(arr)) {
				System.out.println("FUCK!");
				break;
			}
		}

	}

}
