package bit_operation;

// 给定两个有符号32位整数a和b，不能使用算术运算符，分别实现a和b的加、减、乘、除运 算(不考虑溢出)
public class C03_AddMinusMultiDivideByBit {
	public static int negNum(int n) {
		return add(~n, 1);
	}

	public static boolean isNeg(int n) {
		return n < 0;
	}
	// public static int add(int a, int b) {
	// int sum = a;
	// while (b != 0) {
	// sum = a ^ b;
	// b = (a & b) << 1;
	// a = sum;
	// }
	// return sum;
	// }

	// public static int minus(int a, int b) {
	// return add(a, negNum(b));
	// }

	// public static int multi(int a, int b) {
	// int res = 0;
	// while (b != 0) {
	// if ((b & 1) != 0) {
	// res = add(res, a);
	// }
	// a <<= 1;
	// b >>>= 1;
	// }
	// return res;
	// }

	// public static int div(int a, int b) {
	// int x = isNeg(a) ? negNum(a) : a;
	// int y = isNeg(b) ? negNum(b) : b;
	// int res = 0;
	// for (int i = 31; i > -1; i = minus(i, 1)) {
	// if ((x >> i) >= y) {
	// res |= (1 << i);
	// x = minus(x, y << i);
	// }
	// }
	// return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
	// }

	// public static int divide(int a, int b) {
	// if (b == 0) {
	// throw new RuntimeException("divisor is 0");
	// }
	// if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
	// return 1;
	// } else if (b == Integer.MIN_VALUE) {
	// return 0;
	// } else if (a == Integer.MIN_VALUE) {
	// int res = div(add(a, 1), b);
	// return add(res, div(minus(a, multi(res, b)), b));
	// } else {
	// return div(a, b);
	// }
	// }

	public static int add(int a, int b) {
		int sum = a ^ b;
		int jinWei = (a & b) << 1;
		int tem = 0;
		while (jinWei != 0) {
			tem = sum;
			sum = jinWei ^ sum;
			jinWei = (tem & jinWei) << 1;
		}
		return sum;
	}

	public static int minus(int a, int b) {
		return add(a, add(~b, 1));
	}

	// TODO:为什么不用考虑正负号影响
	public static int multi(int a, int b) {
		int mostRight = 0;
		int mul = 0;
		while (b != 0) {
			mostRight = b & 1;
			if (mostRight == 1) {
				mul += a;
			}
			a = a << 1;
			b = b >>> 1;
		}
		return mul;
	}

	// TODO: 除法不对
	public static int divide(int a, int b) throws RuntimeException {
		if (b == 0) {
			throw new RuntimeException("divided is 0");
		}
		// int x = a < 0 ? minus(a, -1) : a;
		// int y = b < 0 ? minus(b, -1) : b;

		// int divVal = 0;
		// for (int i = 31; i >= 0; i = minus(i, 1)) {
		// if (x >= (y << i)) {
		// // divVal = add(divVal, 1 << i);
		// divVal |= 1 << i;
		// x = minus(x, y << i);
		// }
		// }
		// return (a < 0) ^ (b < 0) ? minus(divVal, -1) : divVal;
		return 0;
	}

	public static void main(String[] args) {
		int a = (int) (Math.random() * 100000) - 50000;
		int b = (int) (Math.random() * 100000) - 50000;
		System.out.println("a = " + a + ", b = " + b);
		System.out.println(add(a, b));
		System.out.println(a + b);
		System.out.println("=========");
		System.out.println(minus(a, b));
		System.out.println(a - b);
		System.out.println("=========");
		System.out.println(multi(a, b));
		System.out.println(a * b);
		System.out.println("=========");
		System.out.println(divide(a, b));
		System.out.println(a / b);
		System.out.println("=========");

		a = Integer.MIN_VALUE;
		b = 32;
		System.out.println(divide(a, b));
		System.out.println(a / b);

	}

}
