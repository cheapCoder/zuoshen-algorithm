package hash_function;

import java.util.LinkedList;

public class Code07_SlidingWindowMaxArray {

	// public static int[] getMaxWindow(int[] arr, int w) {
	// if (arr == null || w < 1 || arr.length < w) {
	// return null;
	// }
	// LinkedList<Integer> qmax = new LinkedList<Integer>();
	// int[] res = new int[arr.length - w + 1];
	// int index = 0;
	// for (int i = 0; i < arr.length; i++) {
	// while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
	// qmax.pollLast();
	// }
	// qmax.addLast(i);
	// if (qmax.peekFirst() == i - w) {
	// qmax.pollFirst();
	// }
	// if (i >= w - 1) {
	// res[index++] = arr[qmax.peekFirst()];
	// }
	// }
	// return res;
	// }

	public static int[] getMaxWindow(int[] arr, int w) {
		if (arr == null || w <= 0 || arr.length < w) {
			return null;
		}

		LinkedList<Integer> queue = new LinkedList<>(); // 存储索引
		int[] res = new int[arr.length + 1 - w];

		queue.addLast(0);
		int index = 1;
		while (index < w) { // 0 ~ w - 1
			if (queue.isEmpty() || arr[queue.peekLast()] > arr[index]) {
				queue.addLast(index++);
				// index++;
			} else if (arr[queue.peekLast()] <= arr[index]) {
				queue.pollLast();
			}
		}
		res[0] = arr[queue.peek()];
		// System.out.println(queue);

		for (int i = 1; i < res.length; i++) {
			// System.out.println(queue);
			// 从arr[w]开始
			while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[i - 1 + w]) {
				queue.pollLast();
			}
			queue.addLast(i - 1 + w);

			if (queue.peekFirst() == i - 1) {
				queue.pollFirst();
			}

			res[i] = arr[queue.peek()];
		}
		return res;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 4, 3, 5, 4, 3, 3, 6, 7 };
		int w = 3;
		printArray(getMaxWindow(arr, w));

	}

}