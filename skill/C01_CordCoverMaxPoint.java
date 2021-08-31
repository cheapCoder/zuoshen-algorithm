package skill;

// 给定一个有序数组arr，代表数轴上从左到右有n个点arr[0]、arr[1]...arr[n-1]， 
// 给定一个正数L，代表一根长度为L的绳子，求绳子最多能覆盖其中的几个点
public class C01_CordCoverMaxPoint {

	// 法一： O(Nlog(N))的方式
	// 长度为L的绳子最多覆盖几个点，请保证arr有序
	// public static int maxPoint(int[] arr, int L) {
	// int res = 1;
	// for (int i = 0; i < arr.length; i++) {
	// int nearest = nearestIndex(arr, i, arr[i] - L);
	// res = Math.max(res, i - nearest + 1);
	// }
	// return res;
	// }

	// // 在arr[0..R]范围上，找满足>=value的最左位置
	// public static int nearestIndex(int[] arr, int R, int value) {
	// int L = 0;
	// int index = R;
	// while (L < R) {
	// int mid = L + ((R - L) >> 1);
	// if (arr[mid] >= value) {
	// index = mid;
	// R = mid - 1;
	// } else {
	// L = mid + 1;
	// }
	// }
	// return index;
	// }

	// 法一：nlog(n)
	public static int maxPoint(int[] arr, int L) {
		if (arr == null || arr.length < 0 || L < 0) {
			return 0;
		}
		int res = 0;
		for (int i = 0; i < arr.length; i++) {
			res = Math.max(res, nearestIndex(arr, L, i) - i + 1);
		}
		return res;
	}

	private static int nearestIndex(int[] arr, int L, int left) {
		int wishRight = arr[left] + L;
		int right = arr.length - 1;
		while (left < right) {
			int mid = (right + left) / 2;
			if (arr[mid] > wishRight) {
				right = mid - 1; // 加一减一是避免死循环
			} else if (arr[mid] <= wishRight) {// 用等于是考虑到有多个相同的值时，这样最后的值刚好大于wishRight
				left = mid + 1;
			}
		}

		return left - 1;
	}

	// 法二：O(N)方式
	// 维持一个窗口(L、R)都往右移动
	public static int maxPoint2(int[] arr, int L) {
		if (arr == null || arr.length < 0 || L < 0) {
			return 0;
		}

		int l = 0;
		int r = 0;
		int res = 0;
		while (r < arr.length) {
			if (arr[r] - arr[l] > L) {
				res = Math.max(res, r - l);
				l++;
			} else {
				r++;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int[] arr = { 0, 13, 24, 35, 46, 57, 60, 72, 87 };
		int L = 30;

		System.out.println(maxPoint(arr, L));
		System.out.println(maxPoint2(arr, L));

	}

}
