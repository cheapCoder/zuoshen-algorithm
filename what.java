import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public static int reachNumber(int target) {
    
    return choice(1, 0, target);
  }
  public void me (int i) {

  }
  private int me () {
    return 0;
  }
  private static int choice(int time, int pos, int target) {
    if (pos == target) {
      return time;
    }

    return Math.min(choice(time + 1, pos - time, target), choice(time + 1, pos + time, target));
  }

  public static void main(String[] args) {
    // System.out.println(reachNumber(3));
    System.out.println(Arrays.toString(new int[]{1,2,3,4}));
   List aa =  Arrays.asList(new int[]{1,2,3,4});
   ArrayList al = new ArrayList<>();
  }
}

