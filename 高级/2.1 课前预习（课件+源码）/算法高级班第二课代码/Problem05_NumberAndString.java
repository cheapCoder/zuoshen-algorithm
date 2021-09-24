package class02;

public class Problem05_NumberAndString {

	public static String getString(char[] chs, int n) {
		if (chs == null || chs.length == 0 || n < 1) {
			return "";
		}
		int cur = 1;
		int base = chs.length;
		int len = 0;
		while (n >= cur) {
			len++;
			n -= cur;
			cur *= base;
		}
		char[] res = new char[len];
		int index = 0;
		int nCur = 0;
		do {
			cur /= base;
			nCur = n / cur;
			res[index++] = getKthCharAtChs(chs, nCur + 1);
			n %= cur;
		} while (index != res.length);
		return String.valueOf(res);
	}

	public static char getKthCharAtChs(char[] chs, int k) {
		if (k < 1 || k > chs.length) {
			return 0;
		}
		return chs[k - 1];
	}

	public static int getNum(char[] chs, String str) {
		if (chs == null || chs.length == 0) {
			return 0;
		}
		char[] strc = str.toCharArray();
		int base = chs.length;
		int cur = 1;
		int res = 0;
		for (int i = strc.length - 1; i != -1; i--) {
			res += getNthFromChar(chs, strc[i]) * cur;
			cur *= base;
		}
		return res;
	}

	public static int getNthFromChar(char[] chs, char ch) {
		int res = -1;
		for (int i = 0; i != chs.length; i++) {
			if (chs[i] == ch) {
				res = i + 1;
				break;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		char[] chs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z' };
		int len = 1;
		String res = "";
		for (int i = 1; i != 705; i++) {
			res = getString(chs, i);
			if (res.length() != len) {
				len = res.length();
				System.out.println("================");
			}
			System.out.print(res + " ");
			if (i % chs.length == 0) {
				System.out.println();
			}
		}
		System.out.println();
		System.out.println("========================");
		int testNum = 78128712;
		System.out.println(getNum(chs, getString(chs, testNum)));
		String testStr = "BZZA";
		System.out.println(getString(chs, getNum(chs, testStr)));

	}
}
