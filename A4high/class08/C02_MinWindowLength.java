package A4high.class08;

// 给定字符串str1和str2，求str1的子串中含有str2所有字符的最小子串长度
// 【举例】
// str1="abcde"，str2="ac"
// 因为"abc"包含 str2 所有的字符，并且在满足这一条件的str1的所有子串中，"abc"是最短的，返回3。
// str1="12345"，str2="344" 最小包含子串不存在，返回0。
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
			map[chas1[right]]--;
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

	public static void main(String[] args) {
		String str1 = "adabbca";
		String str2 = "acb";
		System.out.println(minLength(str1, str2));

	}

}
