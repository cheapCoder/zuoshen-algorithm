package A4high.class08;

import java.util.HashMap;

// 给定字符串str1和str2，求str1的子串中含有str2所有字符的最小子串长度
// 【举例】
// str1="abcde"，str2="ac"
// 因为"abc"包含str2所有的字符，并且在满足这一条件的str1的所有子串中，"abc"是最短的，返回3。
// str1="12345"，str2="344" 最小包含子串不存在，返回0。
// 注意不考虑顺序

// 滑动窗口+词频表
public class C02_MinWindowLength {

	public static int minLength(String str1, String str2) {
		if (str1 == null || str2 == null || str1.length() < str2.length()) {
			return 0;
		}
		char[] chas1 = str1.toCharArray();
		char[] chas2 = str2.toCharArray();
		int[] map = new int[256];
		for (int i = 0; i != chas2.length; i++) {
			map[chas2[i]]++;
		}
		int left = 0;
		int right = 0;
		int match = chas2.length;
		int minLen = Integer.MAX_VALUE;
		while (right != chas1.length) {
			map[chas1[right]]--; // 不在str2的会减成负数，用于下面判断
			if (map[chas1[right]] >= 0) {
				match--;
			}
			if (match == 0) {
				while (map[chas1[left]] < 0) {
					map[chas1[left++]]++;
				}
				minLen = Math.min(minLen, right - left + 1);
				match++;
				map[chas1[left++]]++;
			}
			right++;
		}
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}

	// hashmap方式，还是法一的答案简单
	public static int minLength2(String str1, String str2) {
		if (str1 == null || str2 == null || str1.length() < str2.length()) {
			return 0;
		}
		char[] arr1 = str1.toCharArray();
		char[] arr2 = str2.toCharArray();

		// 构建词频表
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < arr2.length; i++) {
			if (!map.containsKey(arr2[i])) {
				map.put(arr2[i], 0);
			}
			map.put(arr2[i], map.get(arr2[i]) + 1);
		}

		int res = Integer.MAX_VALUE;
		int left = -1;
		int right = 0;
		int needMatch = arr2.length;
		while (right != arr1.length) {
			// System.out.println(needMatch);
			if (map.containsKey(arr1[right])) {
				if (map.get(arr1[right]) > 0) {
					needMatch--;
				}
				map.put(arr1[right], map.get(arr1[right]) - 1);
			}
			if (needMatch == 0) {
				left++;
				while (true) {
					if (map.containsKey(arr1[left])) {
						map.put(arr1[left], map.get(arr1[left]) + 1);
						if (map.get(arr1[left]) == 1) { // 词频表表示字符i所欠的次数，为负数则表示多余的字符数，应保证每个字符都小于1
							needMatch++;
							break;
						}
					}
					left++;
				}

				res = Math.min(res, right - left + 1);
			}

			right++;
		}
		return res == Integer.MAX_VALUE ? 0 : res;
	}

	public static void main(String[] args) {
		String str1 = "adabbcq231a";
		String str2 = "acb1a";
		System.out.println(minLength(str1, str2));
		System.out.println(minLength2(str1, str2));
	}
}
