package skill;

// 假设s和m初始化，s = "a"; m = s; 再定义两种操作，
// 第一种操作:
// m = s;
// s = s + s;
// 第二种操作: 
// s = s + m;
// 求最小的操作步骤数，可以将s拼接到长度等于n

// NOTE:纯数学分析，不要求会
// 结论：1.若n为质数，则只使用操作二的答案就是最小操作数
// 因为：除了2质数都不是偶数，操作二只调一次时，和调一次操作一效果一样；若调了两次以上，s一定是偶数，就不是质数了
// 2.
public class C17_SplitNbySM {

	// 附加题：怎么判断一个数是不是质数？
	public static boolean isPrim(int n) {
		if (n < 2) {
			return false;
		}
		int max = (int) Math.sqrt((double) n);
		for (int i = 2; i <= max; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	// 请保证n不是质数
	// 返回:
	// 0) 所有因子的和，但是因子不包括1
	// 1) 所有因子的个数，但是因子不包括1
	public static int[] divsSumAndCount(int n) {
		int sum = 0;
		int count = 0;
		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
				sum += i;
				count++;
				n /= i;
			}
		}
		return new int[] { sum, count };
	}

	public static int minOps(int n) {
		if (n < 2) {
			return 0;
		}
		if (isPrim(n)) {
			return n - 1;
		}
		int[] divSumAndCount = divsSumAndCount(n);
		return divSumAndCount[0] - divSumAndCount[1];
	}

}
