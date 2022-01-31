package sort;

// 4)一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到这一个数 
// 5)一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到这两个数
public class C07_EvenTimesOddTimes {

	public static void printOddTimesNum1(int[] arr) {
		int eO = 0;
		for (int cur : arr) {
			eO ^= cur;
		}
		System.out.println(eO);
	}

	public static void printOddTimesNum2_answer(int[] arr) {
		int eO = 0, eOhasOne = 0;
		for (int curNum : arr) {
			eO ^= curNum;
		}
		int rightOne = eO & (~eO + 1);
		for (int cur : arr) {
			if ((cur & rightOne) != 0) {
				eOhasOne ^= cur;
			}
		}
		System.out.println(eOhasOne + " " + (eO ^ eOhasOne));
	}

	public static void printOddTimesNum2(int[] arr) {
		int r = 0;
		for (int val : arr) {
			r ^= val;
		}
		int rightOne = r & (~r + 1);
		int a = 0;
		for (int val : arr) {
			if ((val & rightOne) != 0) {
				a ^= val;
			}
		}

		System.out.println(a + "  " +( a ^ r));
	}

	public static void main(String[] args) {
		int a = 5;
		int b = 7;

		a = a ^ b;
		b = a ^ b;
		a = a ^ b;

		System.out.println(a);
		System.out.println(b);

		int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
		printOddTimesNum1(arr1);

		int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
		printOddTimesNum2(arr2);

	}

}
