package force_recursion;

import java.util.Stack;

public class Code4_ReverseStackUsingRecursive {

	// public static void reverse(Stack<Integer> stack) {
	// if (stack.isEmpty()) {
	// return;
	// }
	// int i = getAndRemoveLastElement(stack);
	// reverse(stack);
	// stack.push(i);
	// }

	// public static int getAndRemoveLastElement(Stack<Integer> stack) {
	// int result = stack.pop();
	// if (stack.isEmpty()) {
	// return result;
	// } else {
	// int last = getAndRemoveLastElement(stack);
	// stack.push(result);
	// return last;
	// }
	// }

	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}

		int tem = getAndRemoveLastElement(stack);
		reverse(stack);
		stack.push(tem);
	}

	private static int getAndRemoveLastElement(Stack<Integer> stack) {
		if (stack.size() <= 1) {
			return stack.pop();
		}
		int tem1 = stack.pop();
		int tem2 = getAndRemoveLastElement(stack);
		stack.push(tem1);
		return tem2;
	}

	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}

	}

}
