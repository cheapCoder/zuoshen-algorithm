package class07;

public class Problem03_3times {

	public static int getNum(int l, int r) {
		int sum = 0;
		for (int i = l; i <= r; i++) {
			long tmp = (long) (i + 1) * (long) i / 2L;
			if (tmp % 3 == 0) {
				sum++;
			}
		}
		return sum;
	}

}
