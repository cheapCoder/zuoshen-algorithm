package greed;

import java.util.Arrays;

public class Code02_LowestLexicography {

	// public static class MyComparator implements Comparator<String> {
	// @Override
	// public int compare(String a, String b) {
	// return (a + b).compareTo(b + a);
	// }
	// }

	// public static String lowestString(String[] strs) {
	// if (strs == null || strs.length == 0) {
	// return "";
	// }
	// Arrays.sort(strs, new MyComparator());
	// String res = "";
	// for (int i = 0; i < strs.length; i++) {
	// res += strs[i];
	// }
	// return res;
	// }

	public static String lowestString(String[] strs) {
		if (strs == null || strs.length < 1) {
			return "";
		}
		Arrays.sort(strs, (String o1, String o2) -> {
			return (o1 + o2).compareTo(o2 + o1);
		});

		String res = "";
		for (int i = 0; i < strs.length; i++) {
			res += strs[i];
		}
		return res;
	}

	public static void main(String[] args) {
		String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
		System.out.println(lowestString(strs1));

		String[] strs2 = { "ba", "b" };
		System.out.println(lowestString(strs2));

	}

}
