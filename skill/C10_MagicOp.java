package skill;

import java.util.Arrays;
import java.util.HashSet;

// 给一个包含n个整数元素的集合a，一个包含m个整数元素的集合b。
// 定义magic操作为:
// 从一个集合中取出一个元素，放到另一个集合里，且操作过后每个集合的平均值都大大于于操作前。
// 注意以下两点:
// 1)不可以把一个集合的元素取空，这样就没有平均值了
// 2)值为x的元素从集合b取出放入集合a，但集合a中已经有值为x的元素，则a的平均值不变(因为集合元素不会重复)，b的平均值可能会改变(因为x被取出了)
// 问最多可以进行多少次magic操作?

// NOTE:难！对所有情况分类讨论
// a, b平均值不能相等
// 只能从平均值大的拿出小于自己平均值且大于另一集合平均值的值，到平均值小的集合里
public class C10_MagicOp {

	// 请保证arr1无重复值、arr2中无重复值，且arr1和arr2肯定有数字
	public static int maxOps(int[] arr1, int[] arr2) {
		double sum1 = 0;
		for (int i = 0; i < arr1.length; i++) {
			sum1 += (double) arr1[i];
		}
		double sum2 = 0;
		for (int i = 0; i < arr2.length; i++) {
			sum2 += (double) arr2[i];
		}
		if (avg(sum1, arr1.length) == avg(sum2, arr2.length)) {
			return 0;
		}
		int[] arrMore = null;
		int[] arrLess = null;
		double sumMore = 0;
		double sumLess = 0;
		if (avg(sum1, arr1.length) > avg(sum2, arr2.length)) {
			arrMore = arr1;
			sumMore = sum1;
			arrLess = arr2;
			sumLess = sum2;
		} else {
			arrMore = arr2;
			sumMore = sum2;
			arrLess = arr1;
			sumLess = sum1;
		}
		Arrays.sort(arrMore);
		HashSet<Integer> setLess = new HashSet<>();
		for (int num : arrLess) {
			setLess.add(num);
		}
		int moreSize = arrMore.length;
		int lessSize = arrLess.length;
		int ops = 0;
		for (int i = 0; i < arrMore.length; i++) {
			double cur = (double) arrMore[i];
			if (cur < avg(sumMore, moreSize) && cur > avg(sumLess, lessSize) && !setLess.contains(arrMore[i])) {
				sumMore -= cur;
				moreSize--;
				sumLess += cur;
				lessSize++;
				setLess.add(arrMore[i]);
				ops++;
			}
		}
		return ops;
	}

	private static double avg(double sum, int size) {
		return sum / (double) (size);
	}

	public static int maxOps2(int[] arr1, int[] arr2) {
		// 求和
		double sum1 = 0;
		double sum2 = 0;
		for (int i = 0; i < arr1.length; i++) {
			sum1 += arr1[i];
		}
		for (int i = 0; i < arr2.length; i++) {
			sum2 += arr2[i];
		}

		double average1 = (double) sum1 / arr1.length;
		double average2 = (double) sum2 / arr2.length;
		if (average1 == average2) {
			return 0;
		}
		int[] bigArr = average1 > average2 ? arr1 : arr2;
		int[] smallArr = average1 < average2 ? arr1 : arr2;
		double bigSum = average1 > average2 ? sum1 : sum2;
		double smallSum = average1 < average2 ? sum1 : sum2;

		// 平均值大的数组中所有满足条件的值，从小到大添加到平均值小的数组，可以保证两数组平均值总是最大差距，magic操作数最大
		Arrays.sort(bigArr);

		// 初始化hashset
		HashSet<Integer> smallSet = new HashSet<>();
		for (int i = 0; i < smallArr.length; i++) {
			smallSet.add(smallArr[i]);
		}

		int bigLen = bigArr.length;
		int smallLen = smallArr.length;
		int count = 0;
		for (int i = 0; i < bigArr.length; i++) {
			if (bigArr[i] < bigSum / bigLen && bigArr[i] > smallSum / smallLen && !smallSet.contains(bigArr[i])) {
				count++;
				smallSet.add(bigArr[i]);
				bigSum -= bigArr[i];
				bigLen--;
				smallSum += bigArr[i];
				smallLen++;
			}
		}

		return count;
	}

	// private static double getAverage(int) {
	// int sum = 0;
	// for (int i = 0; i < arr.length; i++) {
	// sum += arr[i];
	// }
	// return (double) sum / arr.length;
	// }

	public static void main(String[] args) {
		int[] arr1 = { 1, 2, 5 };
		int[] arr2 = { 2, 3, 4, 5, 6 };
		System.out.println(maxOps(arr1, arr2));
		System.out.println(maxOps2(arr1, arr2));

	}

}
