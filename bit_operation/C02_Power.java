package bit_operation;
// 判断一个32位正数是不是2的幂、4的幂
public class C02_Power {

	// public static boolean is2Power(int n) {
	// return (n & (n - 1)) == 0;
	// }

	// public static boolean is4Power(int n) {
	// return (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
	// }

	public static boolean is2Power(int n) {
		return ((~n + 1) & n) == n;
	}

	public static boolean is4Power(int n) {
		return ((~n + 1) & n) == n && (n &0x55555555) != 0 ;
	}

	public static void main(String[] args) {
		System.out.println(is2Power(2));
		System.out.println(is2Power(4));
		System.out.println(is2Power(8192));
		System.out.println(is2Power(16));
		System.out.println(is2Power(32));
		System.out.println(is2Power(64));
		System.out.println("-----------------------");
		System.out.println(is4Power(2));
		System.out.println(is4Power(4));
		System.out.println(is4Power(8192));
		System.out.println(is4Power(16));
		System.out.println(is4Power(32));
		System.out.println(is4Power(64));
	}
}
