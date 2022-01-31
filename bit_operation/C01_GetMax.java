package bit_operation;

// 位运算的题目
// 给定两个有符号32位整数a和b，返回a和b中较大的。
// 【要求】
// 不用做任何比较判断。
public class C01_GetMax {

	// public static int flip(int n) {
	// return n ^ 1;
	// }

	// public static int sign(int n) {
	// return flip((n >> 31) & 1);
	// }
	// c有溢出问题
	// public static int getMax1(int a, int b) {
	// int c = a - b;
	// int scA = sign(c);
	// int scB = flip(scA);
	// return a * scA + b * scB;
	// }

	// c无溢出问题,因为当c溢出时，就不看c的符号位了，单独有a, b符号位决定
	// public static int getMax2(int a, int b) {
	// int c = a - b;
	// int sa = sign(a);
	// int sb = sign(b);
	// int sc = sign(c);
	// int difSab = sa ^ sb;
	// int sameSab = flip(difSab);
	// int returnA = difSab * sa + sameSab * sc;
	// int returnB = flip(returnA);
	// return a * returnA + b * returnB;
	// }
	public static int getMax1(int a, int b) {
		int c = a - b;
		// NOTE: 不能这样得出符号位：~((c >>> 31) & 1), 取反会前31为都由0变为1
		int isPositive = (c >>> 31) ^ 1;
		// System.out.println("negative: " + isPositive);
		int isNegative = isPositive ^ 1;

		return isPositive * a + isNegative * b;
	}

	public static int getMax2(int a, int b) {
		int c = a - b;
		int aSign = ((a >> 31) & 1) ^ 1;
		// int bSign = ((b >> 31) & 1) ^ 1;
		int cSign = ((c >> 31) & 1) ^ 1;
		int abDiff = a ^ b;
		int abSame = abDiff ^ 1;
		int returnA = abDiff * cSign + abSame * aSign;
		int returnB = returnA ^ 1;

		return returnA * a + returnB * b;
	}

	public static void main(String[] args) {
		int a = -16;
		int b = 1;
		System.out.println(getMax1(a, b));
		System.out.println(getMax2(a, b));
		a = 2147483647;
		b = -2147480000;
		System.out.println(getMax1(a, b)); // wrong answer because of overflow
		System.out.println(getMax2(a, b));

	}

}
