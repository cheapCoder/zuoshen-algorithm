package skill;

import java.util.HashMap;
// import java.util.Comparator;
// import java.util.HashMap;
// import java.util.Map.Entry;
// import java.util.PriorityQueue;

// 设计并实现TopKRecord结构，可以不断地向其中加入字符串，并且可以根据字符串出现的情况随时打印加入次数最多的前k个字符串。
// 具体为:
// 1)k在TopKRecord实例生成时指定，并且不再变化(k是构造TopKRecord的参数)。
// 2)含有 add(String str)方法，即向TopKRecord中加入字符串。
// 3)含有 printTopK()方法，即打印加入次数最多的前k个字符串，打印有哪些字符串和对应的次数即可，不要求严格按排名顺序打印。
// 4)如果在出现次数最多的前k个字符串中，最后一名的字符串有多个，
// 比如出现次数最多的前3个字符串具体排名为:A 100次 B 90次 C 80次 D 80次 E 80次，其他任何字符串出现次数都不超过80次那么只需要打印3个，打印ABC、ABD、ABE都可以。也就是说可以随意抛弃最后一名，只要求打印k个 
// 要求:
// 1)在任何时候，add 方法的时间复杂度不超过 O(logk)
// 2)在任何时候，printTopK方法的时间复杂度不超过O(k)。

public class C18_TopKTimes2 {

	// public static class Node {
	// public String str;
	// public int times;

	// public Node(String s, int t) {
	// str = s;
	// times = t;
	// }
	// }

	// public static class TopKRecord {
	// private Node[] heap;
	// private int index;
	// private HashMap<String, Node> strNodeMap;
	// private HashMap<Node, Integer> nodeIndexMap;

	// public TopKRecord(int size) {
	// heap = new Node[size];
	// index = 0;
	// strNodeMap = new HashMap<String, Node>();
	// nodeIndexMap = new HashMap<Node, Integer>();
	// }

	// public void add(String str) {
	// Node curNode = null;
	// int preIndex = -1;
	// if (!strNodeMap.containsKey(str)) {
	// curNode = new Node(str, 1);
	// strNodeMap.put(str, curNode);
	// nodeIndexMap.put(curNode, -1);
	// } else {
	// curNode = strNodeMap.get(str);
	// curNode.times++;
	// preIndex = nodeIndexMap.get(curNode);
	// }
	// if (preIndex == -1) {
	// if (index == heap.length) {
	// if (heap[0].times < curNode.times) {
	// nodeIndexMap.put(heap[0], -1);
	// nodeIndexMap.put(curNode, 0);
	// heap[0] = curNode;
	// heapify(0, index);
	// }
	// } else {
	// nodeIndexMap.put(curNode, index);
	// heap[index] = curNode;
	// heapInsert(index++);
	// }
	// } else {
	// heapify(preIndex, index);
	// }
	// }

	// public void printTopK() {
	// System.out.println("TOP: ");
	// for (int i = 0; i != heap.length; i++) {
	// if (heap[i] == null) {
	// break;
	// }
	// System.out.print("Str: " + heap[i].str);
	// System.out.println(" Times: " + heap[i].times);
	// }
	// }

	// private void heapInsert(int index) {
	// while (index != 0) {
	// int parent = (index - 1) / 2;
	// if (heap[index].times < heap[parent].times) {
	// swap(parent, index);
	// index = parent;
	// } else {
	// break;
	// }
	// }
	// }

	// private void heapify(int index, int heapSize) {
	// int l = index * 2 + 1;
	// int r = index * 2 + 2;
	// int smallest = index;
	// while (l < heapSize) {
	// if (heap[l].times < heap[index].times) {
	// smallest = l;
	// }
	// if (r < heapSize && heap[r].times < heap[smallest].times) {
	// smallest = r;
	// }
	// if (smallest != index) {
	// swap(smallest, index);
	// } else {
	// break;
	// }
	// index = smallest;
	// l = index * 2 + 1;
	// r = index * 2 + 2;
	// }
	// }

	// private void swap(int index1, int index2) {
	// nodeIndexMap.put(heap[index1], index2);
	// nodeIndexMap.put(heap[index2], index1);
	// Node tmp = heap[index1];
	// heap[index1] = heap[index2];
	// heap[index2] = tmp;
	// }

	// }

	// NOTE: 视频位置：P21-1h:31m:34s
	public static class RealTimeQueue {
		HashMap<String, Integer> freqMap = new HashMap<>(); // 词频
		HashMap<String, Integer> posMap = new HashMap<>(); // 小根堆的位置索引
		String[] heap;
		int heapSize = 0;

		public RealTimeQueue(int topK) {
			heap = new String[topK];
		}

		public void add(String str) {
			if (freqMap.containsKey(str)) {
				freqMap.put(str, freqMap.get(str) + 1);
			} else {
				freqMap.put(str, 1);
			}

			if (posMap.containsKey(str)) {
				heapify(posMap.get(str));
			} else {
				if (heapSize < heap.length) {
					posMap.put(str, heapSize);
					heap[heapSize++] = str;
					if (str.equals("9")) {
						// System.out.println("somthing");
					}
					heapInsert(heapSize - 1);
				} else {
					if (getFreq(0) < freqMap.get(str)) {
						posMap.remove(heap[0]);
						heap[0] = str;
						posMap.put(str, 0);
						heapify(0);
					}
				}
			}

		}

		public void printTopK() {
			for (int i = 0; i < heapSize; i++) {
				System.out.print(heap[i] + " ");
			}
			System.out.println();
		}

		private void heapify(int i) {
			int moreI = i;
			while (true) {

				moreI = (2 * i + 1 < heapSize && getFreq(i) > getFreq(2 * i + 1)) ? 2 * i + 1 : i; // 比较左子节点
				moreI = (2 * i + 2 < heapSize && getFreq(i) > getFreq(2 * i + 2)) ? 2 * i + 2 : i; // 比较右子节点

				if (moreI == i) {
					break;
				}

				swap(i, moreI);
				i = moreI;
			}
		}

		private void heapInsert(int i) {
			// System.out.println(getFreq(i));
			// System.out.println(getFreq((i - 1) / 2));
			while ((i - 1) / 2 >= 0 && getFreq(i) < getFreq((i - 1) / 2)) {
				swap(i, (i - 1) / 2);
				i = (i - 1) / 2;
			}
		}

		private int getFreq(int i) {
			if (!freqMap.containsKey(heap[i])) {
				return -1;
			}
			// System.out.println(heap[i]);
			return freqMap.get(heap[i]);
		}

		private void swap(int i, int j) {
			int index = posMap.get(heap[i]);
			posMap.put(heap[i], posMap.get(heap[j]));
			posMap.put(heap[j], index);

			String tem = heap[i];
			heap[i] = heap[j];
			heap[j] = tem;

		}
	}

	// for test
	public static String[] generateRandomArray(int len, int max) {
		String[] res = new String[len];
		for (int i = 0; i != len; i++) {
			res[i] = String.valueOf((int) (Math.random() * (max + 1)));
		}
		return res;
	}

	// for test
	public static void printArray(String[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		String[] arr = generateRandomArray(50, 10);
		// String[] arr2 = new String[] { "6", "6", "6", "8", "9", "7", "1", "7", "3",
		// "5", "0", "10", "0", "3", "6", "8",
		// "10", "10", "10", "1" };
		int topK = 3;
		printArray(arr);
		System.out.println("---------------------");
		RealTimeQueue rtQueue = new RealTimeQueue(topK);

		for (int i = 0; i < arr.length; i++) {
			rtQueue.add(arr[i]);
			rtQueue.printTopK();
		}

	}
}