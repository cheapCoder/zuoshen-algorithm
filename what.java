class Solution {
  public static int reachNumber(int target) {
    
    return choice(1, 0, target);
  }

  private static int choice(int time, int pos, int target) {
    if (pos == target) {
      return time;
    }

    return Math.min(choice(time + 1, pos - time, target), choice(time + 1, pos + time, target));
  }

  public static void main(String[] args) {
    System.out.println(reachNumber(3));
  }
}