package force_recursion;

import java.util.ArrayList;

public class Code03_PrintAllPermutations {

	// public static ArrayList<String> Permutation(String str) {
	// ArrayList<String> res = new ArrayList<>();
	// if (str == null || str.length() == 0) {
	// return res;
	// }
	// char[] chs = str.toCharArray();
	// process(chs, 0, res);
	// res.sort(null);
	// return res;
	// }

	// public static void process(char[] chs, int i, ArrayList<String> res) {
	// if (i == chs.length) {
	// res.add(String.valueOf(chs));
	// }
	// boolean[] visit = new boolean[26];
	// for (int j = i; j < chs.length; j++) {
	// if (!visit[chs[j] - 'a']) {
	// visit[chs[j] - 'a'] = true;
	// swap(chs, i, j);
	// process(chs, i + 1, res);
	// swap(chs, i, j);
	// }
	// }
	// }

	// public static void swap(char[] chs, int i, int j) {
	// char tmp = chs[i];
	// chs[i] = chs[j];
	// chs[j] = tmp;
	// }

	public static ArrayList<String> Permutation(String str) {
		ArrayList<String> container = new ArrayList<>();
		if (str == null || str.length() == 0) {
			return container;
		}

		char[] arr = str.toCharArray();
		process(arr, 0, container);
		return container;
	}

	private static void process(char[] arr, int level, ArrayList<String> container) {
		if (level == arr.length - 1) {
			container.add(arr.toString());
		}
		char tem = arr[level];
		for (int i = level + 1; i < arr.length; i++) {
			if (arr[i] != tem) {
				swap(arr, i, level);
				process(arr, level + 1, container);
				swap(arr, i, level);
			}
		}
	}

	private static void swap(char[] arr, int i, int j) {
		char tem = arr[i];
		arr[i] = arr[j];
		arr[j] = tem;
	}

}
