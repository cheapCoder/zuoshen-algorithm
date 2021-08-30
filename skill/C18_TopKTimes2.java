package skill;

// import java.util.Comparator;
// import java.util.HashMap;
// import java.util.Map.Entry;
// import java.util.PriorityQueue;

// 实现一个结构，能随时添加str并实时返回词频最大的K个值
public class C18_TopKTimes2 {

	// TODO:P21-1h:31m:34s

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
		String[] arr1 = { "A", "B", "A", "C", "A", "C", "B", "B", "K" };

		String[] arr2 = generateRandomArray(50, 10);
		int topK = 3;
		printArray(arr2);

	}
}