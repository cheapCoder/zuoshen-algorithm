package greed;

import java.util.Arrays;

// 例子:
// 给定一个字符串类型的数组strs，找到一种拼接方式，使得把所有字符串拼起来之后形成的 字符串具有最小的字典序。

// 证明贪心策略可能是件非常腌心的事情。平时当然推荐你搞清楚所有的来龙去脉，但是笔试时用对数器的方式!
public class C02_LowestLexicography {

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
