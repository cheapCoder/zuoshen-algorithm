package A4high.class04;

// 对于一个字符串, 从前开始读和从后开始读是一样的, 我们就称这个字符串是回文串。
// 例如"ABCBA","AA", "A" 是回文串, 而"ABCD", "AAB"不是回文串。
// 牛牛特别喜欢回文串, 他手中有一个字符串s, 牛牛在思考能否从字符串中移除部分(0个或多个)字符使其变为回文串，并且牛牛认为空串不是回文串。
// 牛牛发现移除的方案可能有很多种, 希望你来帮他计算一下一共有多少种移除方案可以使s变为回文串。
// 对于两种移除方案, 如果移除的字符依次构成的序列不一样就是不同的方案。
// 例如，XXY 4种 ABA 5种
// 【说明】 这是今年的原题，提供的说明和例子都很让人费解。现在根据当时题目的所有测试用例，重新解释当时的题目含义: 
// 1)"1AB23CD21"，你可以选择删除A、B、C、D，然后剩下子序列{1,2,3,2,1}，只要剩下的子序列是同一个，那么就只算1种方法，和A、B、C、D选择什么样的删除顺序没有关系。
// 2)"121A1"，其中有两个{1,2,1}的子序列，第一个{1,2,1}是由{位置0，位置1，位置2}构成，第二个{1,2,1} 是由{位置0，位置1，位置4}构成。这两个子序列被认为是不同的子序列。也就是说在本题中，认为字面值一样,但是位置不同的字符就是不同的。
// 3)其实这道题是想求，str中有多少个不同的子序列，每一种子序列只对应一种删除方法，那就是把多余的东西去掉，而和去掉的顺序有关。

// NOTE:范围尝试模型
// dp[i][j] = (1.)以i开头，以j结尾
// 				 + (2.)不以i开头，以j结尾
// 				 + (3.)以i开头，不以j结尾
// 				 + (4.)不以i开头，不以j结尾
// 				 的子串回文数相加(此处不是dp[i+1][j-1]的意思)
// 	因为是序列的问题，dp[i][j]表示i-j这个范围可用可不用，其所有回文子序列;
// 	dp[i][j-1]表示你一定不要j结尾，i位置可用可不用j-1位置也可用可不用时的子序列数
// 	因此：
// 	dp[i][j-1] = 3 + 4
// 	dp[i+1][j] = 2 + 4
// 	dp[i+1][j-1] = 4
// 	当arr[i]==arr[j]时，dp[i+1][j-1]可为{arr[i],arr[j]}=>1种,{arr[i]-(dp[i+1][j-1]种情况)-arr[j]}=>dp[i+1][j-1]种
// 	dp[i+1][j-1] = dp[i+1][j-1] + 1

public class C05_PalindromeWays {

	public static int way1(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		char[] s = str.toCharArray();
		int len = s.length;
		int[][] dp = new int[len + 1][len + 1];
		for (int i = 0; i <= len; i++) {
			dp[i][i] = 1;
		}
		for (int subLen = 2; subLen <= len; subLen++) {
			for (int l = 1; l <= len - subLen + 1; l++) {
				int r = l + subLen - 1;
				dp[l][r] += dp[l + 1][r];
				dp[l][r] += dp[l][r - 1];
				if (s[l - 1] == s[r - 1])
					dp[l][r] += 1;
				else
					dp[l][r] -= dp[l + 1][r - 1];
			}
		}
		return dp[1][len];
	}

	// public static int way2(String str) {
	// if (str == null || str.length() == 0) {
	// return 0;
	// }
	// char[] s = str.toCharArray();
	// int n = s.length;
	// int[][] dp = new int[100][100];
	// for (int i = 0; i < n; i++) {
	// dp[i][i] = 1;
	// if (i + 1 < n && s[i] == s[i + 1])
	// dp[i][i + 1] = 3;
	// else
	// dp[i][i + 1] = 2;
	// }
	// for (int p = 2; p < n; ++p) {
	// for (int i = 0, j = p; j < n; ++i, ++j)
	// if (s[i] == s[j])
	// dp[i][j] = dp[i + 1][j] + dp[i][j - 1] + 1;
	// else
	// dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
	// }
	// return dp[0][n - 1];
	// }

	public static int way3(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		char[] arr = str.toCharArray();
		// 初始化
		int[][] dpCache = new int[arr.length][arr.length];
		for (int i = 0; i < dpCache.length; i++) {
			dpCache[i][i] = 1;
		}
		for (int i = 0; i < dpCache.length - 1; i++) {
			dpCache[i][i + 1] = arr[i] == arr[i + 1] ? 3 : 2;
		}

		for (int i = dpCache.length - 3; i >= 0; i--) {
			for (int j = i + 2; j < dpCache[0].length; j++) {
				dpCache[i][j] = dpCache[i][j - 1] + dpCache[i + 1][j] - dpCache[i + 1][j - 1];
				if (arr[i] == arr[j]) {
					dpCache[i][j] += dpCache[i + 1][j - 1] + 1;
				}
			}
		}
		return dpCache[0][arr.length - 1];
	}

	// for test
	public static String getRandomStringOnlyAToD(int len) {
		int range = 'D' - 'A' + 1;
		char[] charArr = new char[(int) (Math.random() * (len + 1))];
		for (int i = 0; i != charArr.length; i++) {
			charArr[i] = (char) ((int) (Math.random() * range) + 'A');
		}
		return String.valueOf(charArr);
	}

	public static void main(String[] args) {

		int maxLen = 10;
		int testTimes = 1000000;
		String str = null;
		for (int i = 0; i != testTimes; i++) {
			str = getRandomStringOnlyAToD(maxLen);
			if (way1(str) != way3(str)) {
				System.out.print("\"" + str + "\"" + " : ");
				System.out.println(way1(str) + "|" + way3(str));
			}
		}
	}

}
