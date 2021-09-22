package A3medium.class08;

// 在一个字符串中找到没有重复字符子串中最长的长度。
// 例如:
// abcabcbb没有重复字符的最长子串是abc，长度为3 bbbbb，答案是b，长度为1
// pwwkew，答案是wke，长度是3
// 要求:答案必须是子串，"pwke" 是一个子字符序列但不是一个子字符串。

// NOTE: 看到子串和子数组的问题，就想想以任意一个位置i结尾时的情况
public class C04_LongestNoRepeatSubstring {

	public static int maxUnique(String str) {
		if (str == null || str.equals("")) {
			return 0;
		}
		char[] chas = str.toCharArray();
		int[] map = new int[256];
		for (int i = 0; i < 256; i++) {
			map[i] = -1;
		}
		int len = 0;
		int pre = -1;
		int cur = 0;
		for (int i = 0; i != chas.length; i++) {
			pre = Math.max(pre, map[chas[i]]);
			cur = i - pre;
			len = Math.max(len, cur);
			map[chas[i]] = i;
		}
		return len;
	}

	// for test
	public static String getRandomString(int len) {
		char[] str = new char[len];
		int base = 'a';
		int range = 'z' - 'a' + 1;
		for (int i = 0; i != len; i++) {
			str[i] = (char) ((int) (Math.random() * range) + base);
		}
		return String.valueOf(str);
	}

	// for test
	public static String maxUniqueString(String str) {
		if (str == null || str.equals("")) {
			return str;
		}
		char[] chas = str.toCharArray();
		int[] map = new int[256];
		for (int i = 0; i < 256; i++) {
			map[i] = -1;
		}
		int len = -1;
		int pre = -1;
		int cur = 0;
		int end = -1;
		for (int i = 0; i != chas.length; i++) {
			pre = Math.max(pre, map[chas[i]]);
			cur = i - pre;
			if (cur > len) {
				len = cur;
				end = i;
			}
			map[chas[i]] = i;
		}
		return str.substring(end - len + 1, end + 1);
	}

	public static void main(String[] args) {
		String str = getRandomString(20);
		System.out.println(str);
		System.out.println(maxUnique(str));
		System.out.println(maxUniqueString(str));
	}
}
