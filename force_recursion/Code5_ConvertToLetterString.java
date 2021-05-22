package force_recursion;

public class Code5_ConvertToLetterString {

	// public static int number(String str) {
	// if (str == null || str.length() == 0) {
	// return 0;
	// }
	// return process(str.toCharArray(), 0);
	// }

	// public static int process(char[] chs, int i) {
	// if (i == chs.length) {
	// return 1;
	// }
	// if (chs[i] == '0') {
	// return 0;
	// }
	// if (chs[i] == '1') {
	// int res = process(chs, i + 1);
	// if (i + 1 < chs.length) {
	// res += process(chs, i + 2);
	// }
	// return res;
	// }
	// if (chs[i] == '2') {
	// int res = process(chs, i + 1);
	// if (i + 1 < chs.length && (chs[i + 1] >= '0' && chs[i + 1] <= '6')) {
	// res += process(chs, i + 2);
	// }
	// return res;
	// }
	// return process(chs, i + 1);
	// }

	public static int number(String string) {
		if (string == null || string.length() == 0) {
			return 0;
		}

		return process(string.toCharArray(), 0);

	}

	private static int process(char[] arr, int i) {
		if (i >= arr.length - 1) {
			return 1;
		}

		if (arr[i] == '0') {
			return 0;
		} else if (arr[i] == '1') {
			return process(arr, i + 1) + (i < arr.length -1 ? process(arr, i + 2) : 0);
		} else if (arr[i] == '2') {
			if (arr[i + 1] >= '0' && arr[i + 1] <= '6') {
				return process(arr, i + 1) + (i < arr.length -1 ? process(arr, i + 2) : 0);
			} else {
				return process(arr, i + 1);
			}
		} else {
			return process(arr, i + 1);
		}
	}

	public static void main(String[] args) {
		System.out.println(number("11111"));
	}

}
