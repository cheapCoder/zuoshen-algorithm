package dp;

// 题目：给定一个int数组，里面包含不同面值的货币值，每种货币有无限张，要凑出K元有多少中方法？
public class C07_CoinsWay {

  // public static int coins1(int[] arr, int aim) {
  // if (arr == null || arr.length == 0 || aim < 0) {
  // return 0;
  // }
  // return process1(arr, 0, aim);
  // }

  // public static int process1(int[] arr, int index, int aim) {
  // int res = 0;
  // if (index == arr.length) {
  // res = aim == 0 ? 1 : 0;
  // } else {
  // for (int i = 0; arr[index] * i <= aim; i++) {
  // res += process1(arr, index + 1, aim - arr[index] * i);
  // }
  // }
  // return res;
  // }

  // public static int coinsOther(int[] arr, int aim) {
  // if (arr == null || arr.length == 0 || aim < 0) {
  // return 0;
  // }
  // return processOther(arr, arr.length - 1, aim);
  // }

  // public static int processOther(int[] arr, int index, int aim) {
  // int res = 0;
  // if (index == -1) {
  // res = aim == 0 ? 1 : 0;
  // } else {
  // for (int i = 0; arr[index] * i <= aim; i++) {
  // res += processOther(arr, index - 1, aim - arr[index] * i);
  // }
  // }
  // return res;
  // }

  // public static int coins2(int[] arr, int aim) {
  // if (arr == null || arr.length == 0 || aim < 0) {
  // return 0;
  // }
  // int[][] map = new int[arr.length + 1][aim + 1];
  // return process2(arr, 0, aim, map);
  // }

  // public static int process2(int[] arr, int index, int aim, int[][] map) {
  // int res = 0;
  // if (index == arr.length) {
  // res = aim == 0 ? 1 : 0;
  // } else {
  // int mapValue = 0;
  // for (int i = 0; arr[index] * i <= aim; i++) {
  // mapValue = map[index + 1][aim - arr[index] * i];
  // if (mapValue != 0) {
  // res += mapValue == -1 ? 0 : mapValue;
  // } else {
  // res += process2(arr, index + 1, aim - arr[index] * i, map);
  // }
  // }
  // }
  // map[index][aim] = res == 0 ? -1 : res;
  // return res;
  // }

  // public static int coins3(int[] arr, int aim) {
  // if (arr == null || arr.length == 0 || aim < 0) {
  // return 0;
  // }
  // int[][] dp = new int[arr.length][aim + 1];
  // for (int i = 0; i < arr.length; i++) {
  // dp[i][0] = 1;
  // }
  // for (int j = 1; arr[0] * j <= aim; j++) {
  // dp[0][arr[0] * j] = 1;
  // }
  // int num = 0;
  // for (int i = 1; i < arr.length; i++) {
  // for (int j = 1; j <= aim; j++) {
  // num = 0;
  // for (int k = 0; j - arr[i] * k >= 0; k++) {
  // num += dp[i - 1][j - arr[i] * k];
  // }
  // dp[i][j] = num;
  // }
  // }
  // return dp[arr.length - 1][aim];
  // }

  // public static int coins4(int[] arr, int aim) {
  // if (arr == null || arr.length == 0 || aim < 0) {
  // return 0;
  // }
  // int[][] dp = new int[arr.length][aim + 1];
  // for (int i = 0; i < arr.length; i++) {
  // dp[i][0] = 1;
  // }
  // for (int j = 1; arr[0] * j <= aim; j++) {
  // dp[0][arr[0] * j] = 1;
  // }
  // for (int i = 1; i < arr.length; i++) {
  // for (int j = 1; j <= aim; j++) {
  // dp[i][j] = dp[i - 1][j];
  // dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
  // }
  // }
  // return dp[arr.length - 1][aim];
  // }

  // public static int coins5(int[] arr, int aim) {
  // if (arr == null || arr.length == 0 || aim < 0) {
  // return 0;
  // }
  // int[] dp = new int[aim + 1];
  // for (int j = 0; arr[0] * j <= aim; j++) {
  // dp[arr[0] * j] = 1;
  // }
  // for (int i = 1; i < arr.length; i++) {
  // for (int j = 1; j <= aim; j++) {
  // dp[j] += j - arr[i] >= 0 ? dp[j - arr[i]] : 0;
  // }
  // }
  // return dp[aim];
  // }

  // 暴力递归
  // NOTE:错误示例:答案错误，没有保证顺序，会有重复， 比如 1 5 10 5 和5 1 5 10 会算为两种方法
  // public static int coins1(int[] arr, int aim) {
  // if (arr == null || arr.length == 0) {
  // return 0;
  // }
  // if (aim < 0) {
  // System.out.println(0);
  // return 0;
  // }
  // if (aim == 0) {
  // return 1;
  // }
  // int res = 0;
  // for (int i = 0; i < arr.length; i++) {
  // res += coins1(arr, aim - arr[i]);
  // }
  // return res;
  // }

  // 暴力递归(从0往arr.length - 1位置选择)
  public static int coins1(int[] arr, int aim) {
    if (arr == null || arr.length == 0) {
      return 0;
    }

    return process(arr, 0, aim);
  }

  private static int process(int[] arr, int i, int aim) {
    // if (aim < 0) { // NOTE:从下面for循环可知，aim不会小于0
    // return 0;
    // }
    if (i == arr.length) {
      return aim == 0 ? 1 : 0;
    }
    int res = 0;
    for (int j = 0; j * arr[i] <= aim; j++) {
      res += process(arr, i + 1, aim - arr[i] * j);
    }
    return res;
  }

  // 暴力递归(从arr.length - 1往0位置选择)
  public static int coinsOther(int[] arr, int aim) {
    if (arr == null || arr.length == 0 || aim < 0) {
      return 0;
    }

    return processOther(arr, arr.length - 1, aim);
  }

  private static int processOther(int[] arr, int i, int aim) {
    int res = 0;
    if (i == -1) {
      res = aim == 0 ? 1 : 0;
    } else {
      for (int j = 0; j * arr[i] <= aim; j++) {
        res += processOther(arr, i - 1, aim - arr[i] * j);
      }
    }
    return res;
  }

  // 记忆化搜索
  public static int coins2(int[] arr, int aim) {
    if (arr == null || arr.length == 0) {
      return 0;
    }

    int[][] cache = new int[arr.length + 1][aim + 1];
    for (int i = 0; i < cache.length; i++) {
      for (int j = 0; j < cache[i].length; j++) {
        cache[i][j] = -1;
      }
    }
    return process2(arr, cache, 0, aim);
  }

  private static int process2(int[] arr, int[][] cache, int i, int aim) {
    if (cache[i][aim] == -1) {
      cache[i][aim] = 0;
      if (i == arr.length) {
        cache[i][aim] = aim == 0 ? 1 : 0;
      } else {
        for (int j = 0; j * arr[i] <= aim; j++) {
          cache[i][aim] += process2(arr, cache, i + 1, aim - arr[i] * j);
        }
      }
    }
    return cache[i][aim];
  }

  // 动态规划@1(最基本)
  public static int coins3(int[] arr, int aim) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int[][] cache = new int[arr.length + 1][aim + 1];
    // 初始化
    cache[arr.length][0] = 1;
    for (int i = 1; i < cache[arr.length].length; i++) {
      cache[arr.length][i] = 0;
    }

    int sumIndex = 0;
    for (int i = cache.length - 2; i >= 0; i--) {
      for (int j = 0; j < cache[i].length; j++) {
        sumIndex = j;
        while (sumIndex >= 0) {
          cache[i][j] += cache[i + 1][sumIndex];
          sumIndex -= arr[i];
        }
      }
    }
    return cache[0][aim];
  }

  // 动态规划@2(利用已计算的值所代表的意义，增加已加过的部分的重用)
  public static int coins4(int[] arr, int aim) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int[][] cache = new int[arr.length + 1][aim + 1];
    // 初始化
    cache[arr.length][0] = 1;
    for (int i = 1; i < cache[arr.length].length; i++) {
      cache[arr.length][i] = 0;
    }

    for (int i = cache.length - 2; i >= 0; i--) {
      for (int j = 0; j < cache[i].length; j++) {
        if (j - arr[i] >= 0) {
          cache[i][j] += cache[i][j - arr[i]];
        }
        cache[i][j] += cache[i + 1][j];
      }
    }
    return cache[0][aim];
  }

  // 动态规划@3(1. 利用已计算的值所代表的意义，增加已加过的部分的重用; 2.重复利用一维数组和有限变量降低空间复杂度)
  public static int coins5(int[] arr, int aim) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int[] cache = new int[aim + 1];
    // 初始化
    cache[0] = 1;
    for (int i = 1; i < cache.length; i++) {
      cache[i] = 0;
    }

    for (int i = arr.length - 1; i >= 0; i--) {
      for (int j = 0; j < cache.length; j++) {
        if (j - arr[i] >= 0) {
          cache[j] += cache[j - arr[i]];
        }
      }
    }
    return cache[aim];
  }

  public static void main(String[] args) {
    int[] coins = { 10, 5, 1, 25 };
    int aim = 2000;

    long start = 0;
    long end = 0;
    start = System.currentTimeMillis();
    System.out.println(coins1(coins, aim));
    end = System.currentTimeMillis();
    System.out.println("cost time : " + (end - start) + "(ms)");

    start = System.currentTimeMillis();
    System.out.println(coinsOther(coins, aim));
    end = System.currentTimeMillis();
    System.out.println("cost time : " + (end - start) + "(ms)");

    aim = 20000;

    start = System.currentTimeMillis();
    System.out.println(coins2(coins, aim));
    end = System.currentTimeMillis();
    System.out.println("cost time : " + (end - start) + "(ms)");

    start = System.currentTimeMillis();
    System.out.println(coins3(coins, aim));
    end = System.currentTimeMillis();
    System.out.println("cost time : " + (end - start) + "(ms)");

    start = System.currentTimeMillis();
    System.out.println(coins4(coins, aim));
    end = System.currentTimeMillis();
    System.out.println("cost time : " + (end - start) + "(ms)");

    start = System.currentTimeMillis();
    System.out.println(coins5(coins, aim));
    end = System.currentTimeMillis();
    System.out.println("cost time : " + (end - start) + "(ms)");

  }

}
