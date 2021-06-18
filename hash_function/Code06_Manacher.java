package hash_function;

public class Code06_Manacher {

	// public static char[] manacherString(String str) {
	// char[] charArr = str.toCharArray();
	// char[] res = new char[str.length() * 2 + 1];
	// int index = 0;
	// for (int i = 0; i != res.length; i++) {
	// res[i] = (i & 1) == 0 ? '#' : charArr[index++];
	// }
	// return res;
	// }

	// public static int maxLcpsLength(String str) {
	// if (str == null || str.length() == 0) {
	// return 0;
	// }
	// char[] charArr = mapString(str);
	// int[] pArr = new int[charArr.length];
	// int C = -1;
	// int R = -1;
	// int max = Integer.MIN_VALUE;
	// for (int i = 0; i != charArr.length; i++) {
	// pArr[i] = R > i ? Math.min(pArr[2 * C - i/* c - (i - c) */], R - i) : 1;
	// while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
	// if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
	// pArr[i]++;
	// else {
	// break;
	// }
	// }
	// if (i + pArr[i] > R) {
	// R = i + pArr[i];
	// C = i;
	// }
	// max = Math.max(max, pArr[i]);
	// }
	// return max - 1;
	// }

	// 法一：暴力解法
	public static int maxLcpsLength1(String str) {
	char[] arr = mapString(str);
	int count = 0;
	for (int i = 0; i < arr.length; i++) {
	int r = 1;
	while (i - r >= 0 && i + r < arr.length && arr[i - r] == arr[i + r]) {
	r++;
	}
	count = Math.max( 2 * r - 1, count);
	}
	return count / 2;
	}

	// 法二：Manacher
	public static int maxLcpsLength2(String str) {
		char[] charArr = mapString(str);
		int[] diameterArr = new int[charArr.length];

		int borderRight = -1;
		int borderCenter = -1;

		for (int i = 0; i < diameterArr.length; i++) {
			if (borderRight <= i) { // 暴力扩充
				int r = 1;
				while (i - r >= 0 && i + r < charArr.length && charArr[i - r] == charArr[i + r]) {
					r++;
				}
				diameterArr[i] = r * 2 - 1;
				borderRight = Math.max(i + r, borderRight);
				borderCenter = Math.max(i, borderCenter);
			} else {
				int borderLeft = 2 * borderCenter - borderRight;
				int left = 2 * borderCenter - i;

				if (left - diameterArr[left] > borderLeft) {
					diameterArr[i] = diameterArr[left];
				} else if (left - diameterArr[left] == borderLeft) { // 有优化的暴力扩充
					int r = borderRight - i + 1;
					while (i - r >= 0 && i + r < charArr.length && charArr[i - r] == charArr[i + r]) {
						r++;
					}
					diameterArr[i] = r * 2 - 1;
					borderRight = Math.max(i + r, borderRight);
					borderCenter = Math.max(i, borderCenter);
				} else {
					diameterArr[i] = 2 * (left - borderLeft + 1) - 1;
				}
			}

		}
		int maxDiameter = diameterArr[0];
		for (int i = 1; i < diameterArr.length; i++) {
			maxDiameter = Math.max(maxDiameter, diameterArr[i]);
		}
		return maxDiameter / 2;
	}

	private static char[] mapString(String str) {
		char[] arr = str.toCharArray();
		char[] tem = new char[arr.length * 2 + 1];
		for (int i = 0; i < tem.length; i++) {
			// if (i % 2 == 1) {
			// tem[i] = arr[i / 2];
			// } else {
			// tem[i] = '#';
			// }
			tem[i] = i % 2 == 1 ? arr[i / 2] : '#';
			// System.out.print(tem[i]);
		}
		return tem;
	}

	public static void main(String[] args) {
		String str1 = "abc1234321ab";
		System.out.println(maxLcpsLength1(str1));
		System.out.println(maxLcpsLength2(str1));
	}

}
