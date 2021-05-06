package class08;

import java.util.ArrayList;

public class Code03_PrintAllPermutations {

	public static ArrayList<String> Permutation(String str) {
		ArrayList<String> res = new ArrayList<>();
		if (str == null || str.length() == 0) {
			return res;
		}
		char[] chs = str.toCharArray();
		process(chs, 0, res);
		res.sort(null);
		return res;
	}

	public static void process(char[] chs, int i, ArrayList<String> res) {
		if (i == chs.length) {
			res.add(String.valueOf(chs));
		}
		boolean[] visit = new boolean[26];
		for (int j = i; j < chs.length; j++) {
			if (!visit[chs[j] - 'a']) {
				visit[chs[j] - 'a'] = true;
				swap(chs, i, j);
				process(chs, i + 1, res);
				swap(chs, i, j);
			}
		}
	}

	public static void swap(char[] chs, int i, int j) {
		char tmp = chs[i];
		chs[i] = chs[j];
		chs[j] = tmp;
	}

}
