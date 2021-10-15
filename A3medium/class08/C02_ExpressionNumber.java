package A3medium.class08;

// 给定一个只由 0(假)、1(真)、&(逻辑与)、|(逻辑或)和^(异或)五种字符组成 的字符串express，再给定一个布尔值 desired。
// 返回express能有多少种组合方式，可以达到desired的结果。
// 【举例】
// 1. express="1^0|0|1"，desired=false
// 只有 1^((0|0)|1)和 1^(0|(0|1))的组合可以得到 false，返回 2。
// 2. express="1"，desired=false
// 无组合则可以得到false，返回0
public class C02_ExpressionNumber {

	// public static boolean isValid(char[] exp) {
	// if ((exp.length & 1) == 0) {
	// return false;
	// }
	// for (int i = 0; i < exp.length; i = i + 2) {
	// if ((exp[i] != '1') && (exp[i] != '0')) {
	// return false;
	// }
	// }
	// for (int i = 1; i < exp.length; i = i + 2) {
	// if ((exp[i] != '&') && (exp[i] != '|') && (exp[i] != '^')) {
	// return false;
	// }
	// }
	// return true;
	// }

	// public static int num1(String express, boolean desired) {
	// if (express == null || express.equals("")) {
	// return 0;
	// }
	// char[] exp = express.toCharArray();
	// if (!isValid(exp)) {
	// return 0;
	// }
	// return p(exp, desired, 0, exp.length - 1);
	// }

	// public static int p(char[] exp, boolean desired, int L, int R) {
	// if (L == R) {
	// if (exp[L] == '1') {
	// return desired ? 1 : 0;
	// } else {
	// return desired ? 0 : 1;
	// }
	// }
	// int res = 0;
	// if (desired) {
	// for (int i = L + 1; i < R; i += 2) {
	// switch (exp[i]) {
	// case '&':
	// res += p(exp, true, L, i - 1) * p(exp, true, i + 1, R);
	// break;
	// case '|':
	// res += p(exp, true, L, i - 1) * p(exp, false, i + 1, R);
	// res += p(exp, false, L, i - 1) * p(exp, true, i + 1, R);
	// res += p(exp, true, L, i - 1) * p(exp, true, i + 1, R);
	// break;
	// case '^':
	// res += p(exp, true, L, i - 1) * p(exp, false, i + 1, R);
	// res += p(exp, false, L, i - 1) * p(exp, true, i + 1, R);
	// break;
	// }
	// }
	// } else {
	// for (int i = L + 1; i < R; i += 2) {
	// switch (exp[i]) {
	// case '&':
	// res += p(exp, false, L, i - 1) * p(exp, true, i + 1, R);
	// res += p(exp, true, L, i - 1) * p(exp, false, i + 1, R);
	// res += p(exp, false, L, i - 1) * p(exp, false, i + 1, R);
	// break;
	// case '|':
	// res += p(exp, false, L, i - 1) * p(exp, false, i + 1, R);
	// break;
	// case '^':
	// res += p(exp, true, L, i - 1) * p(exp, true, i + 1, R);
	// res += p(exp, false, L, i - 1) * p(exp, false, i + 1, R);
	// break;
	// }
	// }
	// }
	// return res;
	// }

	// public static int num2(String express, boolean desired) {
	// if (express == null || express.equals("")) {
	// return 0;
	// }
	// char[] exp = express.toCharArray();
	// if (!isValid(exp)) {
	// return 0;
	// }
	// int[][] t = new int[exp.length][exp.length];
	// int[][] f = new int[exp.length][exp.length];
	// t[0][0] = exp[0] == '0' ? 0 : 1;
	// f[0][0] = exp[0] == '1' ? 0 : 1;
	// for (int i = 2; i < exp.length; i += 2) {
	// t[i][i] = exp[i] == '0' ? 0 : 1;
	// f[i][i] = exp[i] == '1' ? 0 : 1;
	// for (int j = i - 2; j >= 0; j -= 2) {
	// for (int k = j; k < i; k += 2) {
	// if (exp[k + 1] == '&') {
	// t[j][i] += t[j][k] * t[k + 2][i];
	// f[j][i] += (f[j][k] + t[j][k]) * f[k + 2][i] + f[j][k] * t[k + 2][i];
	// } else if (exp[k + 1] == '|') {
	// t[j][i] += (f[j][k] + t[j][k]) * t[k + 2][i] + t[j][k] * f[k + 2][i];
	// f[j][i] += f[j][k] * f[k + 2][i];
	// } else {
	// t[j][i] += f[j][k] * t[k + 2][i] + t[j][k] * f[k + 2][i];
	// f[j][i] += f[j][k] * f[k + 2][i] + t[j][k] * t[k + 2][i];
	// }
	// }
	// }
	// }
	// return desired ? t[0][t.length - 1] : f[0][f.length - 1];
	// }

	// 暴力递归
	public static int num1(String express, boolean desired) {
		char[] arr = express.toCharArray();

		if (express == null || !isValid(arr)) {
			return -1;
		}

		return process(arr, desired, 0, arr.length - 1);
	}

	private static int process(char[] arr, boolean desired, int left, int right) {
		if (left == right) {
			return (desired && arr[left] == '1') || (!desired && arr[left] == '0') ? 1 : 0;
		}

		if (left > right || left >= arr.length) {
			return 0;
		}

		int sum = 0;
		for (int i = left; i <= right; i += 2) {
			if (desired) { // true
				if (i + 1 > right) {
					continue;
				}
				if (arr[i + 1] == '&') {
					sum += process(arr, true, left, i) * process(arr, true, i + 2, right);
				} else if (arr[i + 1] == '|') {
					sum += process(arr, true, left, i) * process(arr, false, i + 2, right);
					sum += process(arr, false, left, i) * process(arr, true, i + 2, right);
					sum += process(arr, true, left, i) * process(arr, true, i + 2, right);
				} else if (arr[i + 1] == '^') {
					sum += process(arr, true, left, i) * process(arr, false, i + 2, right);
					sum += process(arr, false, left, i) * process(arr, true, i + 2, right);
				} else {
					System.out.println("string type wrong!");
					return -1;
				}
			} else { // false
				if (i + 1 > right) {
					continue;
				}
				if (i + 1 < arr.length && arr[i + 1] == '&') {
					sum += process(arr, true, left, i) * process(arr, false, i + 2, right);
					sum += process(arr, false, left, i) * process(arr, true, i + 2, right);
					sum += process(arr, false, left, i) * process(arr, false, i + 2, right);
				} else if (arr[i + 1] == '|') {
					sum += process(arr, false, left, i) * process(arr, false, i + 2, right);
				} else if (arr[i + 1] == '^') {
					sum += process(arr, true, left, i) * process(arr, true, i + 2, right);
					sum += process(arr, false, left, i) * process(arr, false, i + 2, right);
				} else {
					System.out.println("string type wrong!");
					return -1;
				}
			}
		}

		return sum;
	}

	public static int num2(String express, boolean desired) {
		char[] arr = express.toCharArray();

		if (express == null || !isValid(arr)) {
			return -1;
		}

		int[][] falseCache = new int[arr.length][arr.length];
		int[][] trueCache = new int[arr.length][arr.length];

		for (int i = 0; i < trueCache.length; i++) {
			for (int j = 0; j < trueCache[0].length; j++) {
				if (i == j) {
					if (arr[i] == '0') {
						falseCache[i][j] = 1;
					} else {
						trueCache[i][j] = 1;
					}
				}
			}
		}

		for (int i = 0; i < trueCache.length; i++) {
			for (int j = 0; j < trueCache[0].length; j++) {
				System.out.print(falseCache[i][j] + " ");
			}
			System.out.println();
		}

		for (int left = trueCache.length - 1; left >= 0; left -= 2) {
			for (int right = 0; right < trueCache[0].length; right += 2) {

				for (int i = left; i <= right; i += 2) {
					if (i + 1 > right) {
						continue;
					}

					if (arr[i + 1] == '&') {
						trueCache[left][right] += trueCache[left][i] * trueCache[i + 2][right];
					} else if (arr[i + 1] == '|') {
						trueCache[left][right] += trueCache[left][i] * falseCache[i + 2][right]
								+ falseCache[left][i] * trueCache[i + 2][right] + trueCache[left][i] * trueCache[i + 2][right];
					} else if (arr[i + 1] == '^') {
						trueCache[left][right] += trueCache[left][i] * falseCache[i + 2][right]
								+ falseCache[left][i] * trueCache[i + 2][right];
					} else {
						System.out.println("string type wrong!");
						return -1;
					}

					if (arr[i + 1] == '&') {
						falseCache[left][right] += trueCache[left][i] * falseCache[i + 2][right]
								+ falseCache[left][i] * trueCache[i + 2][right] + falseCache[left][i] * falseCache[i + 2][right];
					} else if (arr[i + 1] == '|') {
						falseCache[left][right] += falseCache[left][i] * falseCache[i + 2][right];
					} else if (arr[i + 1] == '^') {
						falseCache[left][right] += trueCache[left][i] * trueCache[i + 2][right]
								+ falseCache[left][i] * falseCache[i + 2][right];
					} else {
						System.out.println("string type wrong!");
						return -1;
					}
				}
			}

		}

		if (desired) {
			return trueCache[0][arr.length - 1];
		} else {
			return falseCache[0][arr.length - 1];
		}
	}

	private static boolean isValid(char[] exp) {
		if (exp == null) {
			return false;
		}

		for (int i = 0; i < exp.length; i += 2) {
			if (exp[i] != '0' && exp[i] != '1') {
				return false;
			}
		}

		for (int i = 1; i < exp.length; i += 2) {
			if (exp[i] != '&' && exp[i] != '|' && exp[i] != '^') {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String express = "1^0&0|1&1^0&0^1|0|1&1";
		boolean desired = true;
		System.out.println(num1(express, desired));
		System.out.println(num2(express, desired));

	}

}
