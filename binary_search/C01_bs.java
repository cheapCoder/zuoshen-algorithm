package binary_search;

// TODO:
public class C01_BS {

  // // 第一个 >=5 的元素
  // public static int firstGE5(int[] arr, int n) {

  // }

  // // 最后一个 <5 的元素
  // public static int lastL5(int[] arr, int n) {

  // }

  // // 第一个 >5 的元素
  // public static int firstG5(int[] arr, int n) {

  // }

  // // 最后一个 <=5 的元素
  // public static int lastLE5(int[] arr, int n) {

  // }

  // 循环二分
  public static int binary_search(int[] arr, int target) {
    if (arr == null || arr.length == 0) {
      return -1;
    }

    int left = 0;
    int right = arr.length - 1;
    int mid;
    while (left <= right) {
      mid = left + (right - left) / 2; // 为了不溢出
      if (arr[mid] == target) {
        return mid;
      } else if (arr[mid] > target) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return -1;
  }

  // 递归二分
  public static int binary_search2(int[] arr, int target) {
    if (arr == null || arr.length == 0) {
      return -1;
    }
    return process(arr, target, 0, arr.length - 1);
  }

  private static int process(int[] arr, int target, int left, int right) {
    int mid = left + (right - left) / 2;
    if (left > right) {
      return -1;
    }
    if (arr[mid] == target) {
      return mid;
    } else if (arr[mid] > target) {
      return process(arr, target, left, mid - 1);
    } else {
      return process(arr, target, mid + 1, right);
    }
  }

  public static void main(String[] args) {
    int[] arr = new int[] { 1, 2, 3, 5, 5, 5, 8, 9 };
    System.out.println(binary_search(arr, 5));
    System.out.println(binary_search2(arr, 5));
  }
}
