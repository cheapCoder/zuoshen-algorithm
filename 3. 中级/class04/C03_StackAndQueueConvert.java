//package class04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 如何仅用队列结构实现栈结构? 
// 如何仅用栈结构实现队列结构?
public class C03_StackAndQueueConvert {

	// public static class TwoStacksQueue {
	// private Stack<Integer> stackPush;
	// private Stack<Integer> stackPop;

	// public TwoStacksQueue() {
	// stackPush = new Stack<Integer>();
	// stackPop = new Stack<Integer>();
	// }

	// public void push(int pushInt) {
	// stackPush.push(pushInt);
	// }

	// public int poll() {
	// if (stackPop.empty() && stackPush.empty()) {
	// throw new RuntimeException("Queue is empty!");
	// } else if (stackPop.empty()) {
	// while (!stackPush.empty()) {
	// stackPop.push(stackPush.pop());
	// }
	// }
	// return stackPop.pop();
	// }

	// public int peek() {
	// if (stackPop.empty() && stackPush.empty()) {
	// throw new RuntimeException("Queue is empty!");
	// } else if (stackPop.empty()) {
	// while (!stackPush.empty()) {
	// stackPop.push(stackPush.pop());
	// }
	// }
	// return stackPop.peek();
	// }
	// }

	// public static class TwoQueuesStack {
	// private Queue<Integer> queue;
	// private Queue<Integer> help;

	// public TwoQueuesStack() {
	// queue = new LinkedList<Integer>();
	// help = new LinkedList<Integer>();
	// }

	// public void push(int pushInt) {
	// queue.add(pushInt);
	// }

	// public int peek() {
	// if (queue.isEmpty()) {
	// throw new RuntimeException("Stack is empty!");
	// }
	// while (queue.size() != 1) {
	// help.add(queue.poll());
	// }
	// int res = queue.poll();
	// help.add(res);
	// swap();
	// return res;
	// }

	// public int pop() {
	// if (queue.isEmpty()) {
	// throw new RuntimeException("Stack is empty!");
	// }
	// while (queue.size() > 1) {
	// help.add(queue.poll());
	// }
	// int res = queue.poll();
	// swap();
	// return res;
	// }

	// private void swap() {
	// Queue<Integer> tmp = help;
	// help = queue;
	// queue = tmp;
	// }

	// }

	public class StackToQueue<T> {
		Stack<T> pushStack = new Stack<>();
		Stack<T> popStack = new Stack<>();

		public void push(T val) {
			pushStack.push(val);
		}

		public T pop() {
			if (popStack.isEmpty()) {
				while (!pushStack.isEmpty()) {
					popStack.push(pushStack.pop());
				}
			}
			return popStack.pop();
		}

		public T peek() {
			if (popStack.isEmpty()) {
				while (!pushStack.isEmpty()) {
					popStack.push(pushStack.pop());
				}
			}
			return popStack.peek();
		}
	}

	public class QueueToStack<T> {
		Queue<T> pushQueue = new LinkedList<>();
		Queue<T> pollQueue = new LinkedList<>();

		public void add(T val) {
			pushQueue.add(val);
		}

		public T poll() {
			// 不用考虑开始为空队列的情况，原生poll已经处理
			while (pushQueue.size() > 1) {
				pollQueue.add(pushQueue.poll());
			}
			T tem = pushQueue.poll();

			// 交换两队列
			Queue<T> cache = pollQueue;
			pollQueue = pushQueue;
			pushQueue = cache;

			return tem;
		}

		public T peek() {
			// 不用考虑开始为空队列的情况，原生poll已经处理
			while (pushQueue.size() > 1) {
				pollQueue.add(pushQueue.poll());
			}
			T tem = pushQueue.poll();
			pollQueue.add(tem);
			
			// 交换两队列
			Queue<T> cache = pollQueue;
			pollQueue = pushQueue;
			pushQueue = cache;

			return tem;
		}
	}
}
