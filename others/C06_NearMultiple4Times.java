package others;

// 给定一个数组arr，如果通过调整可以做到arr中任意两个相邻的数字相乘是4的倍数，返回true;如果不能返回false
public class C06_NearMultiple4Times {

	// public static boolean nearMultiple4Times(int[] arr) {
	// int fourTimes = 0; // 是4的倍数的数有多少个
	// int evenExpFourTimes = 0; // 是偶数但不是4的倍数的数有多少个
	// int odd = 0; // 奇数有多少个
	// for (int i = 0; i < arr.length; i++) {
	// if ((arr[i] & 1) != 0) {
	// odd++;
	// } else {
	// if (arr[i] % 4 == 0) {
	// fourTimes++;
	// } else {
	// evenExpFourTimes++;
	// }
	// }
	// }
	// return evenExpFourTimes == 0 ? (fourTimes + 1 >= odd) : (evenExpFourTimes >=
	// odd);
	// }

	public static boolean nearMultiple4Times(int[] arr) {
		if (arr == null || arr.length == 0) {
			return false;
		}

		int fourTimes = 0;
		int twoTimes = 0;
		int otherTimes = 0;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 4 == 0) {
				fourTimes++;
			} else if (arr[i] % 2 == 0) { // 用arr[i] & 1 == 0更快
				twoTimes++;
			} else {
				otherTimes++;
			}
		}

		if (twoTimes == 1 || twoTimes == 0) {
			return fourTimes >= otherTimes + twoTimes - 1;
		} else {
			return fourTimes >= otherTimes;
		}
	}

}
