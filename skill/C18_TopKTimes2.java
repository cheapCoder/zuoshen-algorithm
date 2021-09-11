package skill;

import java.util.HashMap;
// import java.util.Comparator;
// import java.util.HashMap;
// import java.util.Map.Entry;
// import java.util.PriorityQueue;

// 实现一个结构，能随时添加str并实时返回词频最大的K个值
public class C18_TopKTimes2 {

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