package hash_function;

import java.util.LinkedList;
import java.util.Stack;

public class Code08_MonotonousStack {

	// public static int[][] getNearLessNoRepeat(int[] arr) {
	// int[][] res = new int[arr.length][2];
	// Stack<Integer> stack = new Stack<>();
	// for (int i = 0; i < arr.length; i++) {
	// while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
	// int popIndex = stack.pop();
	// int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
	// res[popIndex][0] = leftLessIndex;
	// res[popIndex][1] = i;
	// }
	// stack.push(i);
	// }
	// while (!stack.isEmpty()) {
	// int popIndex = stack.pop();
	// int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
	// res[popIndex][0] = leftLessIndex;
	// res[popIndex][1] = -1;
	// }
	// return res;
	// }

	// public static int[][] getNearLess(int[] arr) {
	// int[][] res = new int[arr.length][2];
	// Stack<List<Integer>> stack = new Stack<>();
	// for (int i = 0; i < arr.length; i++) {
	// while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
	// List<Integer> popIs = stack.pop();
	// // 取位于下面位置的列表中，最晚加入的那个
	// int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(
	// stack.peek().size() - 1);
	// for (Integer popi : popIs) {
	// res[popi][0] = leftLessIndex;
	// res[popi][1] = i;
	// }
	// }
	// if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
	// stack.peek().add(Integer.valueOf(i));
	// } else {
	// ArrayList<Integer> list = new ArrayList<>();
	// list.add(i);
	// stack.push(list);
	// }
	// }
	// while (!stack.isEmpty()) {
	// List<Integer> popIs = stack.pop();
	// // 取位于下面位置的列表中，最晚加入的那个
	// int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(
	// stack.peek().size() - 1);
	// for (Integer popi : popIs) {
	// res[popi][0] = leftLessIndex;
	// res[popi][1] = -1;
	// }
	// }
	// return res;
	// }

	// 无重复值
	public static int[][] getNearLessNoRepeat(int[] arr) {
		Stack<Integer> stack = new Stack<>();
		int[][] res = new int[arr.length][2];

		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
				int curIndex = stack.pop();
				res[curIndex][0] = stack.isEmpty() ? -1 : stack.peek();
				res[curIndex][1] = i;
			}
			stack.push(i);
		}

		while (!stack.isEmpty()) {
			int curIndex = stack.pop();
			res[curIndex][0] = stack.isEmpty() ? -1 : stack.peek();
			res[curIndex][1] = -1;
		}
		return res;
	}

	// 有重复值
	public static int[][] getNearLess(int[] arr) {
		Stack<LinkedList<Integer>> stack = new Stack<>();
		int[][] res = new int[arr.length][2];

		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek().peek()] > arr[i]) {
				LinkedList<Integer> curList = stack.pop();
				for (int j : curList) {
					res[j][0] = stack.isEmpty() ? -1 : stack.peek().peekLast();
					res[j][1] = i;
				}
			}

			if (!stack.isEmpty() && arr[stack.peek().peek()] == arr[i]) {
				stack.peek().add(i);
			} else {
				LinkedList<Integer> list = new LinkedList<>();
				list.add(i);
				stack.push(list);
			}
		}

		while (!stack.isEmpty()) {
			LinkedList<Integer> curList = stack.pop();
			for (int j : curList) {
				res[j][0] = stack.isEmpty() ? -1 : stack.peek().peekLast();
				res[j][1] = -1;
			}
		}

		return res;
	}

	// for test
	public static int[] getRandomArrayNoRepeat(int size) {
		int[] arr = new int[(int) (Math.random() * size) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		for (int i = 0; i < arr.length; i++) {
			int swapIndex = (int) (Math.random() * arr.length);
			int tmp = arr[swapIndex];
			arr[swapIndex] = arr[i];
			arr[i] = tmp;
		}
		return arr;
	}

	// for test
	public static int[] getRandomArray(int size, int max) {
		int[] arr = new int[(int) (Math.random() * size) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
		}
		return arr;
	}

	// for test
	public static int[][] rightWay(int[] arr) {
		int[][] res = new int[arr.length][2];
		for (int i = 0; i < arr.length; i++) {
			int leftLessIndex = -1;
			int rightLessIndex = -1;
			int cur = i - 1;
			while (cur >= 0) {
				if (arr[cur] < arr[i]) {
					leftLessIndex = cur;
					break;
				}
				cur--;
			}
			cur = i + 1;
			while (cur < arr.length) {
				if (arr[cur] < arr[i]) {
					rightLessIndex = cur;
					break;
				}
				cur++;
			}
			res[i][0] = leftLessIndex;
			res[i][1] = rightLessIndex;
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[][] res1, int[][] res2) {
		if (res1.length != res2.length) {
			return false;
		}
		for (int i = 0; i < res1.length; i++) {
			if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
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
	}

	public static void main(String[] args) {
		int size = 10;
		int max = 20;
		int testTimes = 2000000;
		for (int i = 0; i < testTimes; i++) {
			int[] arr1 = getRandomArrayNoRepeat(size);
			int[] arr2 = getRandomArray(size, max);
			if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
				System.out.println("Oops!");
				printArray(arr1);
				break;
			}
			if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
			System.out.println("Oops!");
			printArray(arr2);
			break;
			}
		}
	}

}
