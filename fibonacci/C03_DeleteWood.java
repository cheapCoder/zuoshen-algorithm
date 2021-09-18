package fibonacci;

// 在迷迷糊糊的大草原上，小红捡到了n根木棍，第i根木棍的长度为i，
// 小红现在很开心。想选出其中的三根木棍组成美丽的三角形。
// 但是小明想捉弄小红，想去掉一些木棍，使得小红任意选三根木棍都不能组成三角形。
// 请问小明最少去掉多少根木棍呢？

public class C03_DeleteWood {

	// public static int minDelete(int m) {
	// if (m < 4) {
	// return 0;
	// }
	// int k_2 = 2;
	// int k_1 = 3;
	// int num = 3;
	// while (k_2 + k_1 <= m) {
	// num++;
	// k_1 += k_2;
	// k_2 = k_1 - k_2;
	// }
	// return m - num;
	// }

	public static int minDelete2(int n) {
		if (n < 0) {
			return -1;
		}
		if (n < 4) { // 1 2 3 不够
			return 0;
		}

		int n1 = 1;
		int n2 = 2;
		int count = 2;
		while (n2 + n1 <= n) {
			n2 += n1;
			n1 = n2 - n1; // 优化, 不用tem缓存
			count++;
		}
		return n - count;
	}

	public static void main(String[] args) {
		int test = 231;
		// System.out.println(minDelete(test));
		System.out.println(minDelete2(test));
	}
}
