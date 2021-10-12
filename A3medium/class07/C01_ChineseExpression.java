package A3medium.class07;

// 把一个数字用中文表示出来。数字范围为0~亿位。 
// 为了方便输出，使用字母替换相应的中文，万W 千Q 百B 十S 零L。
// 使用数字取代中文数字注:对于 11 应该表示为 一十一(1S1)，而不是十一(S1)

// 下方实现并未按这些约定

// 输入描述:
// 数字 0(包含)到 99999(包含)。
// 输出描述:
// 用字母替换相应的中文，万W 千Q 百B 十S 零L
// 示例1:
// 输入:
// 12001
// 输出:
// 1W2QL1
public class C01_ChineseExpression {

	// public static String num1To9(int num) {
	// if (num < 1 || num > 9) {
	// return "";
	// }
	// String[] names = { "一", "二", "三", "四", "五", "六", "七", "八", "九" };
	// return names[num - 1];
	// }

	// public static String num1To99(int num, boolean hasBai) {
	// if (num < 1 || num > 99) {
	// return "";
	// }
	// if (num < 10) {
	// return num1To9(num);
	// }
	// int shi = num / 10;
	// if (shi == 1 && (!hasBai)) {
	// return "十" + num1To9(num % 10);
	// } else {
	// return num1To9(shi) + "十" + num1To9(num % 10);
	// }
	// }

	// public static String num1To999(int num) {
	// if (num < 1 || num > 999) {
	// return "";
	// }
	// if (num < 100) {
	// return num1To99(num, false);
	// }
	// String res = num1To9(num / 100) + "百";
	// int rest = num % 100;
	// if (rest == 0) {
	// return res;
	// } else if (rest >= 10) {
	// res += num1To99(rest, true);
	// } else {
	// res += "零" + num1To9(rest);
	// }
	// return res;
	// }

	// public static String num1To9999(int num) {
	// if (num < 1 || num > 9999) {
	// return "";
	// }
	// if (num < 1000) {
	// return num1To999(num);
	// }
	// String res = num1To9(num / 1000) + "千";
	// int rest = num % 1000;
	// if (rest == 0) {
	// return res;
	// } else if (rest >= 100) {
	// res += num1To999(rest);
	// } else {
	// res += "零" + num1To99(rest, false);
	// }
	// return res;
	// }

	// public static String num1To99999999(int num) {
	// if (num < 1 || num > 99999999) {
	// return "";
	// }
	// int wan = num / 10000;
	// int rest = num % 10000;
	// if (wan == 0) {
	// return num1To9999(rest);
	// }
	// String res = num1To9999(wan) + "万";
	// if (rest == 0) {
	// return res;
	// } else {
	// if (rest < 1000) {
	// return res + "零" + num1To999(rest);
	// } else {
	// return res + num1To9999(rest);
	// }
	// }
	// }

	// public static String getNumChiExp(int num) {
	// if (num == 0) {
	// return "零";
	// }
	// String res = num < 0 ? "负" : "";
	// int yi = Math.abs(num / 100000000);
	// int rest = Math.abs((num % 100000000));
	// if (yi == 0) {
	// return res + num1To99999999(rest);
	// }
	// res += num1To9999(yi) + "亿";
	// if (rest == 0) {
	// return res;
	// } else {
	// if (rest < 10000000) {
	// return res + "零" + num1To99999999(rest);
	// } else {
	// return res + num1To99999999(rest);
	// }
	// }
	// }

	// TODO: 优化
	public static String getNumChiExp(int num) {
		String res = num < 0 ? "负" : "";
		num = Math.abs(num);

		// while (true) {
		// if (num == 0) {
		// break;
		// } else
		if (num < 10) { // 个位
			res += num1To9(num, true);
		} else if (num < 100) { // 十位
			res += numTen(num, false);
		} else if (num < 1000) { // 百位
			res += numHundred(num);
		} else if (num < 10000) { // 千位
			res += numThousand(num);
		} else if (num < 100000000) { // 万位
			res += numWan(num);
		} else if (num >= 100000000) { // 亿位
			res += numToYi(num);
		}
		// }
		return res;
	}

	private static String numToYi(int num) {
		if (num < 100000000) {
			throw new RuntimeException("参数不合法！");
		}

		String s = "";

		// while (num >= 100000000) {
		int dig = num / 100000000;
		int remain = num % 100000000;
		if (dig > 10000) {
			s += numWan(dig);
		} else if (dig >= 1000) {
			s += numThousand(dig);
		} else if (dig >= 100) {
			s += numHundred(dig);
		} else if (dig >= 10) {
			s += numTen(dig, false);
		} else {
			s += num1To9(dig, false);
		}

		if (remain >= 10000) {
			s += "亿" + (remain > 10000000? "" : "零") + numWan(remain);
		} else if (remain >= 1000) {
			s += "亿零" + numThousand(remain);
		} else if (remain >= 100) {
			s += "亿零" + numHundred(remain);
		} else if (remain >= 10) {
			s += "亿零" + numTen(remain, true);
		} else if (remain == 0) {
			s += "亿";
		} else {
			s += "亿零" + num1To9(remain, false);
		}

		// num = dig;
		// }

		return s;
	}

	private static String numWan(int num) {
		if (num >= 100000000 || num < 10000) {
			throw new RuntimeException("参数不合法！");
		}

		int dig = num / 10000;
		int remain = num % 10000;
		String s = "";

		if (dig >= 1000) {
			s += numThousand(dig);
		} else if (dig >= 100) {
			s += numHundred(dig);
		} else if (dig >= 10) {
			s += numTen(dig, false);
		} else {
			s += num1To9(dig, false);
		}

		if (remain >= 1000) {
			s += "万" + numThousand(remain);
		} else if (remain >= 100) {
			s += "万零" + numHundred(remain);
		} else if (remain >= 10) {
			s += "万零" + numTen(remain, true);
		} else if (remain == 0) {
			s += "万";
		} else {
			s += "万零" + num1To9(remain, false);
		}

		return s;
	}

	private static String numThousand(int num) {
		if (num >= 10000 || num < 1000) {
			throw new RuntimeException("参数不合法！");
		}

		int dig = num / 1000;
		int remain = num % 1000;
		if (remain >= 100) {
			return num1To9(dig, false) + "千" + numHundred(remain);
		} else if (remain >= 10) {
			return num1To9(dig, false) + "千零" + numTen(remain, true);
		} else if (remain == 0) {
			return num1To9(dig, false) + "千";
		} else {
			return num1To9(dig, false) + "千零" + num1To9(remain, false);
		}
	}

	private static String numHundred(int num) {
		if (num >= 1000 || num < 100) {
			throw new RuntimeException("参数不合法！");
		}

		int dig = num / 100;
		int remain = num % 100;

		if (remain >= 10) {
			return num1To9(dig, false) + "百" + numTen(num % 100, true);
		} else if (remain == 0) {
			return num1To9(dig, false) + "百";
		} else {
			return num1To9(dig, false) + "百零" + num1To9(remain, false);
		}
	}

	private static String numTen(int num, boolean hasHundred) {
		if (num > 99) {
			throw new RuntimeException("参数不合法！");
		}

		boolean noYi = num >= 10 && num < 20 && !hasHundred;
		if (num < 10) {
			return (num == 0 ? "" : "零") + num1To9(num % 10, false);
		} else {
			return (noYi ? "" : num1To9(num / 10, false)) + "十" + num1To9(num % 10, false);
		}
	}

	private static String num1To9(int num, boolean needIfZero) {
		if (num < 0 || num > 9) {
			throw new RuntimeException("参数不合法！");
		}
		return new String[] { needIfZero ? "零" : "", "一", "二", "三", "四", "五", "六", "七", "八", "九" }[num];
	}

	// for test
	public static int generateRandomNum() {
		boolean isNeg = Math.random() > 0.5 ? false : true;
		int value = (int) (Math.random() * Integer.MIN_VALUE);
		return isNeg ? value : -value;
	}

	public static void main(String[] args) {
		try {
			System.out.println(getNumChiExp(0));
			System.out.println(getNumChiExp(10));
			System.out.println(getNumChiExp(15));
			System.out.println(getNumChiExp(55));
			System.out.println(getNumChiExp(110));
			System.out.println(getNumChiExp(125));
			System.out.println(getNumChiExp(100));
			System.out.println(getNumChiExp(709));
			System.out.println(getNumChiExp(1234));
			System.out.println(getNumChiExp(1000));
			System.out.println(getNumChiExp(1008));
			System.out.println(getNumChiExp(1010));
			System.out.println(getNumChiExp(10000));
			System.out.println(getNumChiExp(10010));
			System.out.println(getNumChiExp(12310));
			System.out.println(getNumChiExp(12316));
			System.out.println(getNumChiExp(1900000000));
			System.out.println(getNumChiExp(1000000010));
			System.out.println(getNumChiExp(805545971));

			System.out.println(Integer.MAX_VALUE);
			System.out.println(getNumChiExp(Integer.MAX_VALUE));

			// System.out.println(Integer.MIN_VALUE);
			// System.out.println(getNumChiExp(Integer.MIN_VALUE));

			int testTime = 20;
			for (int i = 0; i < testTime; i++) {
			int num = generateRandomNum();
			System.out.println(num);
			System.out.println(getNumChiExp(num));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
