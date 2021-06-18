import java.util.Arrays;

class Solution {
  public static int hIndex(int[] citations) {
    Arrays.sort(citations);
    int i = 0;
    while (i < citations.length && citations[i] < citations.length - i) {
      i++;
    }
    while (i + 1 < citations.length && citations[i + 1] == citations[i + 1]) {
      i++;
    }
    return i + 1;
  }

  public static void main(String[] args) {
    hIndex(new int[] { 100, 12, 23, 5, 6, 8, 4, 3, 345, 35, 2 });
    // hIndex(new int[] { 0, 0, 0, 0, 0 });
  }
}