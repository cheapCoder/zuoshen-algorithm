package leetcode;

class Solution388 {
  public int lengthLongestPath(String input) {
    if (input == null || input.length() == 0) {
      return 0;
    }

    String[] arr = input.split("\\n(?!\\t)");

    int res = 0;
    for (int i = 0; i < arr.length; i++) {
      int c = process(arr[i], 1);
      if (c != 0) {
        res = Math.max(res, c);
      }
    }

    return res;
  }

  private int process(String str, int level) {
    String[] arr = str.split("\\n" + "\\t".repeat(level) + "(?!\\t)");
    if (arr.length == 1)
      return arr[0].contains(".") ? arr[0].length() : 0;

    int res = 0;
    // must run
    for (int i = 1; i < arr.length; i++) {
      int c = process(arr[i], level + 1);
      if (c != 0) {
        res = Math.max(res, c);
      }
    }

    return res == 0 ? 0 : res + arr[0].length() + 1;

  }

  public static void main(String[] args) {
    System.out.println(new Solution388().lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
    System.out.println(new Solution388().lengthLongestPath(
        "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
    System.out.println(new Solution388().lengthLongestPath("a"));
    System.out.println(new Solution388().lengthLongestPath("file1.txt\nfile2.txt\nlongfile.txt"));
    System.out.println(new Solution388().lengthLongestPath("a\n\tb\n\t\tc"));
    System.out.println(new Solution388().lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));

    // System.out.println("22\n\t2123".matches(".*?\n\t[^\t].*?"));
  }
}
// dir
// subdir1
// tsubdir2
// file.ext