package skill;

// 题目：
// 1. 给定一个函数f，可以1~5的数字等概率返回一个。请加工出1~7的数字等概率返回一个的函数。
// 2. 给定一个函数f，可以a~b的数字等概率返回一个。请加工出c~d的数字等概率返回一个的函数。
// 3. 给定一个函数f，以p概率返回0，以1-p概率返回1。请加工出等概率返回0和1的函数

public class C06_Rand5ToRand7 {

	// util
	public static int rand1To5() {
		return (int) (Math.random() * 5) + 1;
	}

	// util
	public static int rand01p() {
		// you can change p to what you like, but it must be (0,1)
		double p = 0.83;
		return Math.random() < p ? 0 : 1;
	}

	// util
	public static int randAB(int a, int b) {
		if (a > b) {
			return -1;
		}
		return (int) (Math.random() * (b - a + 1) + a);
	}
	// public static int rand1To7() {
	// int num = 0;
	// do {
	// num = (rand1To5() - 1) * 5 + rand1To5() - 1;
	// } while (num > 20);
	// return num % 7 + 1;
	// }

	// public static int rand01() {
	// int num;
	// do {
	// num = rand01p();
	// } while (num == rand01p());
	// return num;
	// }

	// public static int rand0To3() {
	// return rand01() * 2 + rand01();
	// }

	// public static int rand1To6() {
	// int num = 0;
	// do {
	// num = rand0To3() * 4 + rand0To3();
	// } while (num > 11);
	// return num % 6 + 1;
	// }

	// public static int rand1ToM(int m) {
	// return (int) (Math.random() * m) + 1;
	// }

	// public static int rand1ToN(int n, int m) {
	// int[] nMSys = getMSysNum(n - 1, m);
	// int[] randNum = getRanMSysNumLessN(nMSys, m);
	// return getNumFromMSysNum(randNum, m) + 1;
	// }

	// public static int[] getMSysNum(int value, int m) {
	// int[] res = new int[32];
	// int index = res.length - 1;
	// while (value != 0) {
	// res[index--] = value % m;
	// value = value / m;
	// }
	// return res;
	// }

	// public static int[] getRanMSysNumLessN(int[] nMSys, int m) {
	// int[] res = new int[nMSys.length];
	// int start = 0;
	// while (nMSys[start] == 0) {
	// start++;
	// }
	// int index = start;
	// boolean lastEqual = true;
	// while (index != nMSys.length) {
	// res[index] = rand1ToM(m) - 1;
	// if (lastEqual) {
	// if (res[index] > nMSys[index]) {
	// index = start;
	// lastEqual = true;
	// continue;
	// } else {
	// lastEqual = res[index] == nMSys[index];
	// }
	// }
	// index++;
	// }
	// return res;
	// }

	// 题目1
	public static int rand1To7() {
		int num;
		do {
			num = (randTo01() << 2) + (randTo01() << 1) + randTo01();
		} while (num == 7);

		return num + 1;
	}

	private static int randTo01() {
		int res;
		do {
			res = rand1To5();
		} while (res == 3);
		return res > 3 ? 1 : 0;
	}

	// 题目2
	public static int randPTo01() {
		int num;
		do {
			num = (rand01p() << 1) + rand01p();
		} while (num == 0 && num == 3);
		// num为1或者2
		return num == 1 ? 0 : 1;
	}

	// 题目3
	public static int randABToCD(int a, int b, int c, int d) {
		if (c > d) {
			return -1;
		}

		int dis = d - c + 1;
		int digit = 0;
		while (Math.pow((double) 2, (double) digit) < dis) {
			digit++;
		}
		// System.out.println("digit: " + digit);
		int res = 0;
		do {
			res = 0;
			int count = digit;
			while (count >= 0) {
				res += (randABTo01(c, d) << count--);
			}

		} while (res > d - c);

		return res + c;
	}

	private static int randABTo01(int a, int b) {
		if (a > b) {
			return -1;
		}

		int res;
		if ((b - a + 1) % 2 == 0) {
			res = randAB(a, b);
		} else {
			do {
				res = randAB(a, b);
			} while (res == b);
		}
		return res % 2 == 0 ? 0 : 1;
	}

	// for test
	public static int getNumFromMSysNum(int[] mSysNum, int m) {
		int res = 0;
		for (int i = 0; i != mSysNum.length; i++) {
			res = res * m + mSysNum[i];
		}
		return res;
	}

	// for test
	public static void printCountArray(int[] countArr) {
		for (int i = 0; i != countArr.length; i++) {
			if (countArr[i] != 0) {
				System.out.println(i + " appears " + countArr[i] + " times");
			}
		}
	}

	public static void main(String[] args) {
		int testTimes = 1000000;
		int[] countArr1 = new int[8];
		for (int i = 0; i != testTimes; i++) {
			countArr1[rand1To7()]++;
		}
		printCountArray(countArr1);

		System.out.println("=====================");

		int[] countArr2 = new int[2];
		for (int i = 0; i != testTimes; i++) {
			countArr2[randPTo01()]++;
		}
		printCountArray(countArr2);

		System.out.println("=====================");

		int m = 3;
		int n = 17;
		int[] countArr3 = new int[n + 1];
		for (int i = 0; i != 2000000; i++) {
			countArr3[randAB(m, n)]++;
		}
		printCountArray(countArr3);

		System.out.println("=====================");

		int x = 5;
		int y = 30;
		int[] countArr4 = new int[2];
		for (int i = 0; i != 2000000; i++) {
			countArr4[randABTo01(x, y)]++;
		}
		printCountArray(countArr4);

		System.out.println("=====================");

		int a = 5;
		int b = 30;
		int c = 7;
		int d = 28;
		int[] countArr5 = new int[d + 1];
		for (int i = 0; i != 2000000; i++) {
			countArr5[randABToCD(a, b, c, d)]++;
		}

		printCountArray(countArr5);

		System.out.println("=====================");
	}

}