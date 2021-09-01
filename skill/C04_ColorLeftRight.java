package skill;

// 题目：
// 牛牛有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。
// 牛牛现在可以选择任意一个正方形然后用这两种颜色的任意一种进行染色,这个正方形的颜色将会被覆盖。
// 牛牛的目标是在完成染色之后,每个红色R都比每个绿色G距离最左侧近。
// 牛牛想知道他最少需要涂染几个正方形。
// 如样例所示: s = RGRGR 我们涂染之后变成RRRGG满足要求了,涂染的个数为2,没有比这个更好的涂染方案。
public class C04_ColorLeftRight {

	// 暴力法：一次讨论左侧R有0,1,2...到arr.length个R时,result为多少，取最小值

	// RGRGR -> RRRGG
	// public static int minPaint2(String s) {
	// if (s == null || s.length() < 2) {
	// return 0;
	// }
	// char[] chs = s.toCharArray();
	// int[] right = new int[chs.length];
	// right[chs.length - 1] = chs[chs.length - 1] == 'R' ? 1 : 0;
	// for (int i = chs.length - 2; i >= 0; i--) {
	// right[i] = right[i + 1] + (chs[i] == 'R' ? 1 : 0);
	// }
	// int res = right[0];
	// int left = 0;
	// for (int i = 0; i < chs.length - 1; i++) {

	// left += chs[i] == 'G' ? 1 : 0;
	// res = Math.min(res, left + right[i + 1]);
	// }
	// res = Math.min(res, left + (chs[chs.length - 1] == 'G' ? 1 : 0));
	// return res;
	// }

	public static int minPaint1(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int res = Integer.MAX_VALUE;
		char[] arr = s.toCharArray();
		// System.out.println(arr[1]);
		for (int i = 0; i <= arr.length; i++) { // i表示第一个G块
			int cur = 0;
			for (int j = 0; j < arr.length; j++) {
				if (arr[j] != (j >= i ? 'G' : 'R')) {
					cur++;
				}
			}
			res = Math.min(res, cur);
		}
		return res;
	}

	public static int minPaint2(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		char[] arr = s.toCharArray();

		int[] leftCache = new int[arr.length + 1]; // 记录i索引之前有多少R
		int[] rightCache = new int[arr.length]; // 记录i索引以及之后有多少G
		leftCache[0] = 0;
		leftCache[1] = arr[0] == 'R' ? 1 : 0;
		for (int i = 2; i < leftCache.length; i++) {
			leftCache[i] = arr[i - 1] == 'R' ? leftCache[i - 1] + 1 : leftCache[i - 1];
		}
		rightCache[rightCache.length - 1] = arr[rightCache.length - 1] == 'G' ? 1 : 0;
		for (int i = rightCache.length - 2; i >= 0; i--) {
			rightCache[i] = arr[i] == 'G' ? rightCache[i + 1] + 1 : rightCache[i + 1];
		}

		int res = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) { // i表示第一个G块
			res = Math.min(res, (i - leftCache[i]) + (arr.length - i - rightCache[i]));
		}
		return res;
	}

	public static void main(String[] args) {
		String test = "RGGGGGGGRRGRRRGGRRRGGGGR";
		System.out.println(minPaint1(test));
		System.out.println(minPaint2(test));
	}

}
