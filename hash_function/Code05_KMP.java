package hash_function;

public class Code05_KMP {

	public static int getIndexOf(String s, String m) {
		if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
			return -1;
		}
		char[] str1 = s.toCharArray();
		char[] str2 = m.toCharArray();
		int i1 = 0;
		int i2 = 0;
		int[] next = getNextArray(str2);
		while (i1 < str1.length && i2 < str2.length) {
			if (str1[i1] == str2[i2]) {
				i1++;
				i2++;
			} else if (next[i2] == -1) {
				i1++;
			} else {
				i2 = next[i2];
			}
		}
		return i2 == str2.length ? i1 - i2 : -1;
	}

	// public static int[] getNextArray(char[] ms) {
	// if (ms.length == 1) {
	// return new int[] { -1 };
	// }
	// int[] next = new int[ms.length];
	// next[0] = -1;
	// next[1] = 0;
	// int i = 2;
	// int cn = 0;
	// while (i < next.length) {
	// if (ms[i - 1] == ms[cn]) {
	// next[i++] = ++cn;
	// } else if (cn > 0) {
	// cn = next[cn];
	// } else {
	// next[i++] = 0;
	// }
	// }
	// return next;
	// }

	public static int KMP(String s1, String s2) {
		if (s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
			return -1;
		}

		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		int[] next = getNextArray(c2);

		int i1 = 0, i2 = 0;
		while (i1 < c1.length && i2 < c2.length) {
			if (c1[i1] == c2[i2]) {
				i1++;
				i2++;
			} else {
				if (i2 == 0) {
					i1++;
				} else {
					i2 = next[i2];
				}
			}
		}
		return i2 == c2.length ? i1 - i2 : -1;
	}

	private static int[] getNextArray(char[] list) {
		int[] arr = new int[list.length];
		arr[0] = -1;
		arr[1] = 0;
		for (int i = 2; i < arr.length; i++) {
			if (list[i - 1] == list[arr[i - 1]]) {
				arr[i] = arr[i - 1] + 1;
			} else {
				int j = arr[i - 1];
				while (j != 0 && list[i - 1] != list[j]) {
					j = arr[j];
				}
				if (list[i - 1] != list[j]) {
					arr[i] = 0;
				} else {
					arr[i] = arr[j] + 1;
				}
			}
		}
		return arr;
	}

	public static void main(String[] args) {
		String str = "abcabcaababaccc";
		String match = "ababa";
		System.out.println(getIndexOf(str, match));
		System.out.println("-----------------");
		System.out.println(KMP(str, match));
	}

}
