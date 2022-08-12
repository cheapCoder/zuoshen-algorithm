package util;

public class Util {
  public static <E> void print(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static <E> void print(int[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        System.out.print(arr[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static <E> void print(boolean[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static <E> void print(boolean[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        System.out.print(arr[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static <E> void print(char[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static <E> void print(char[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        System.out.print(arr[i][j] + " ");
      }
      System.out.println();
    }
  }

  private void reverse(char[] s, int left, int right) {
    if (left >= right) {
      return;
    }
    char tem;
    for (int i = left; i < left + (right - left + 1) / 2; i++) {
      tem = s[i];
      s[i] = s[right - (i - left)];
      s[right - (i - left)] = tem;
    }
  }

  public static void main(String[] args) {
    Util.print(new int[][] { { 1, 2, 3 }, { 4, 5, 6 } });
  }
}
