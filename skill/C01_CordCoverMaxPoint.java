package skill;

// 给定一个有序数组arr，代表数轴上从左到右有n个点arr[0]、arr[1]...arr[n-1]， 给定一个正数L，代表一根长度为L的绳子，求绳子最多能覆盖其中的几个点。
public class C01_CordCoverMaxPoint {

	//法一： O(Nlog(N))的方式
	// 长度为L的绳子最多覆盖几个点，请保证arr有序
	public static int maxPoint(int[] arr, int L) {
		int res = 1;
		for (int i = 0; i < arr.length; i++) {
			int nearest = nearestIndex(arr, i, arr[i] - L);
			res = Math.max(res, i - nearest + 1);
		}
		return res;
	}

	// 在arr[0..R]范围上，找满足>=value的最左位置
	public static int nearestIndex(int[] arr, int R, int value) {
		int L = 0;
		int index = R;
		while (L < R) {
			int mid = L + ((R - L) >> 1);
			if (arr[mid] >= value) {
				index = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}
		return index;
	}

	//TODO 法二：O(N)方式


	public static void main(String[] args) {
		int[] arr = { 0, 13, 24, 35, 46, 57, 60, 72, 87 };
		int L = 6;

		System.out.println(maxPoint(arr, L));

	}

}
