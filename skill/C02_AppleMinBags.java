package skill;

// 小虎去附近的商店买苹果，奸诈的商贩使用了捆绑交易，只提供6个每袋和8个 每袋的包装包装不可拆分。
// 可是小虎现在只想购买恰好n个苹果，小虎想购买尽量少的袋数方便携带。
// 如果不能购买恰好n个苹果，小虎将不会购买。
// 输入一个整数n，表示小虎想购买的个苹果，返回最小使用多少袋子。如果无论如何都不能正好装下，返回-1。
public class C02_AppleMinBags {

	// public static int minBags(int apple) {
	// if (apple < 0) {
	// return -1;
	// }
	// int bag6 = -1;
	// int bag8 = apple / 8;
	// int rest = apple - 8 * bag8;
	// while (bag8 >= 0 && rest < 24) {
	// int restUse6 = minBagBase6(rest);
	// if (restUse6 != -1) {
	// bag6 = restUse6;
	// break;
	// }
	// rest = apple - 8 * (--bag8);
	// }
	// return bag6 == -1 ? -1 : bag6 + bag8;
	// }

	// public static int minBagBase6(int rest) {
	// return rest % 6 == 0 ? (rest / 6) : -1;
	// }

	// public static int minBagAwesome(int apple) {
	// if ((apple & 1) != 0) {
	// return -1;
	// }
	// if (apple < 18) {
	// return apple == 0 ? 0 : (apple == 6 || apple == 8) ? 1 : (apple == 12 ||
	// apple == 14 || apple == 16) ? 2 : -1;
	// }
	// return (apple - 18) / 8 + 3;
	// }

	// 暴力方式
	public static int minBags1(int n, int bagCount) {
		if (n < 0) {
			return -1;
		}
		if (n == 0) {
			return bagCount;
		}

		int count8 = minBags1(n - 8, bagCount + 1);
		if (count8 != -1) {
			return count8;
		}
		int count6 = minBags1(n - 6, bagCount + 1);
		if (count6 != -1) {
			return count6;
		}
		return -1;
	}

	// 法二
	public static int minBags(int n) {
		// 不能是奇数
		if ((n & 1) == 1) {
			return -1;
		}
		if (n < 0) {
			return -1;
		}

		int count8 = n / 8;
		int count6 = 0;

		while (count8 >= 0) {
			if ((n - count8 * 8) % 6 == 0) {
				count6 = (n - count8 * 8) / 6;
				break;
			}
			count8--;
		}

		if (count8 < 0) {
			return -1;
		} else {
			return count6 + count8;
		}
	}

	// 打表法：打印结果，发现规律
	public static int minBagAwesome(int n) {
		if (n == 0) {
			return 0;
		}
		if (n < 0 || (n & 1) == 1 || n == 2 || n == 4 || n == 10) {
			return -1;
		}
		if (n == 6 || n == 8) {
			return 1;
		}
		if (n == 12 || n == 14 || n == 16) {
			return 2;
		}
		// n >= 18
		return (n + 6) / 8;

	}

	public static void main(String[] args) {
		int max = Integer.MAX_VALUE;
		int testTime = 100000000;
		for (int test = 0; test < testTime; test++) {
			int apple = (int) (Math.random() * max);
			if (minBags(apple) != minBagAwesome(apple)) {
				System.out.println("error");
			}
		}

	}

}
