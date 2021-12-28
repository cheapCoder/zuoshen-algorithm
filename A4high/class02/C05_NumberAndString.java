package A4high.class02;

// 一个 char 类型的数组 chs，其中所有的字符都不同。
// 例如，chs=['A', 'B', 'C', ... 'Z']，则字符串与整数的对应关系如下:
// A, B... Z, AA,AB...AZ,BA,BB...ZZ,AAA... ZZZ, AAAA...
// 1, 2...26,27, 28... 52,53,54...702,703...18278, 18279... 
// 例如，chs=['A', 'B', 'C']，则字符串与整数的对应关系如下: A,B,C,AA,AB...CC,AAA...CCC,AAAA...
// 1, 2,3,4,5...12,13...39,40...
// 给定一个数组 chs，实现根据对应关系完成字符串与整数相互转换的两个函数。

// NOTE: K伪进制
public class C05_NumberAndString {

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

	// NOTE:因为伪进制没有0，所有应先确保每位都至少有1再定具体值
	public static String getString2(int num) {
		if (num <= 0) {
			return "";
		}

		// 获取str的长度
		int len = 0;
		num -= 1;
		while (num > 0) {
			len++;
			num -= Math.pow(26, len);
		}
		num += Math.pow(26, len);

		System.out.println(len);

		char[] res = new char[len];
		for (int i = 0; i < len; i++) {
			res[i] = (char) ((num / (int) Math.pow(26, len - 1 - i)) + 64 + 1); // +1是之前有存的1
			num = num % ((int) (Math.pow(26, len - 1 - i)));
		}

		return String.valueOf(res);
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

	public static int getNum2(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}

		char[] arr = str.toUpperCase().toCharArray();
		int res = 0;
		for (int i = arr.length - 1; i >= 0; i--) {
			res += Math.pow(26, arr.length - 1 - i) * (arr[i] - 65 + 1);
		}
		return res;
	}

	public static void main(String[] args) {
		char[] chs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z' };
		// int len = 1;
		// String res = "";
		// for (int i = 1; i != 705; i++) {
		// res = getString(chs, i);
		// if (res.length() != len) {
		// len = res.length();
		// System.out.println("================");
		// }
		// System.out.print(res + " ");
		// if (i % chs.length == 0) {
		// System.out.println();
		// }
		// }
		// System.out.println();
		// System.out.println("========================");
		int testNum = 78128712;
		// System.out.println(getNum(chs, getString(chs, testNum)));
		// System.out.println(getNum2(getString(chs, testNum)));
		String testStr = "ZsgSsG";
		System.out.println(getNum(chs, testStr));
		System.out.println(getNum2(testStr));
		System.out.println(getString(chs, getNum(chs, testStr)));
		System.out.println(getString2(getNum2(testStr)));
	}
}
