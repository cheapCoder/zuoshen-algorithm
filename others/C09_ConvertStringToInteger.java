package others;

// 给定一个字符串，如果该字符串符合人们日常书写一个整数的形式，返回int类型的这个数;
// 如果不符合或者越界返回-1或者报错。
public class C09_ConvertStringToInteger {
	// public static int convert(String str) {
	// if (str == null || str.equals("")) {
	// return 0; // can not convert
	// }
	// char[] chas = str.toCharArray();
	// if (!isValid(chas)) {
	// return 0; // can not convert
	// }
	// boolean posi = chas[0] == '-' ? false : true;
	// int minq = Integer.MIN_VALUE / 10;
	// int minr = Integer.MIN_VALUE % 10;
	// int res = 0;
	// int cur = 0;
	// for (int i = posi ? 0 : 1; i < chas.length; i++) {
	// cur = '0' - chas[i];
	// if ((res < minq) || (res == minq && cur < minr)) {
	// return 0; // can not convert
	// }
	// res = res * 10 + cur;
	// }
	// if (posi && res == Integer.MIN_VALUE) {
	// return 0; // can not convert
	// }
	// return posi ? -res : res;
	// }

	// public static boolean isValid(char[] chas) {
	// if (chas[0] != '-' && (chas[0] < '0' || chas[0] > '9')) {
	// return false;
	// }
	// if (chas[0] == '-' && (chas.length == 1 || chas[1] == '0')) {
	// return false;
	// }
	// if (chas[0] == '0' && chas.length > 1) {
	// return false;
	// }
	// for (int i = 1; i < chas.length; i++) {
	// if (chas[i] < '0' || chas[i] > '9') {
	// return false;
	// }
	// }
	// return true;
	// }

	public static int convert(String str) throws Exception {
		if (str == null || str.length() == 0 || !isValid(str)) {
			throw new Exception("not a number");
		}

		boolean isPos = str.charAt(0) != '-';

		str = isPos ? str : str.substring(1);
		char[] arr = str.toCharArray();

		int maxYu = Integer.MIN_VALUE % 10;
		int maxDiv = Integer.MIN_VALUE / 10;
		int res = '0' - arr[0];
		for (int i = 1; i < arr.length; i++) {
			int cur = '0' - arr[i];
			if (res < maxDiv || (res == maxDiv && cur < maxYu)) {
				throw new Exception("out of range!");
			}
			res = res * 10 + cur;
		}

		if (isPos && res == -2147483648) {
			throw new Exception("out of range!");
		}
		return isPos ? -res : res;

	}

	private static boolean isValid(String str) {

		if (str.charAt(0) != '-' && (str.charAt(0) > '9' || str.charAt(0) < '0')) {
			return false;
		}
		if (str.charAt(0) == '0' && str.length() > 1) {
			return false;
		}
		if (str.charAt(0) == '-' && str.charAt(1) == '0') {
			return false;
		}
		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i) > '9' || str.charAt(i) < '0') {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		try {
			String test1 = "2147483647"; // max in java
			System.out.println(convert(test1));
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			String test2 = "-2147483648"; // min in java
			System.out.println(convert(test2));
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			String test3 = "2147483648"; // overflow
			System.out.println(convert(test3));
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			String test4 = "-2147483649"; // overflow
			System.out.println(convert(test4));
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			String test5 = "-123";
			System.out.println(convert(test5));
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			String test5 = "-1s23";
			System.out.println(convert(test5));
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			String test5 = "-0123";
			System.out.println(convert(test5));
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			String test5 = "+0123";
			System.out.println(convert(test5));
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			String test5 = "02312";
			System.out.println(convert(test5));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
