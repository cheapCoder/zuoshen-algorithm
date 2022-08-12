package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class Solution68 {
  public List<String> fullJustify(String[] words, int maxWidth) {
    if (words == null || words.length == 0) {
      return null;
    }

    LinkedList<Integer> counts = new LinkedList<>();
    HashMap<Integer, Integer> map = new HashMap<>();

    // 获取分界点索引
    int len = words[0].length();
    for (int i = 1; i < words.length; i++) {
      if (len + words[i].length() + 1 > maxWidth) {
        counts.add(i - 1);
        map.put(i - 1, len);
        len = words[i].length();
      } else {
        len += words[i].length() + 1;
      }
    }

    // 根据分界生成字符串
    List<String> res = new ArrayList<>();
    int left = 0;
    int right;

    StringBuilder cache = new StringBuilder();
    while (!counts.isEmpty()) {
      right = counts.poll();
      cache.delete(0, cache.length());
      if (left == right) {
        cache.append(words[left]);
        cache.append(" ".repeat(maxWidth - cache.length()));
      } else {
        int extraLen = (maxWidth - map.get(right)) / (right - left);
        int extraIndex = (maxWidth - map.get(right)) % (right - left);

        for (int i = left; i <= right; i++) {
          cache.append(words[i]);
          if (i == right) {
            continue;
          }
          cache.append(" ".repeat(1 + extraLen + (i - left < extraIndex ? 1 : 0)));
        }

      }
      res.add(cache.toString());
      left = right + 1;
    }

    cache.delete(0, cache.length());
    for (int i = left; i < words.length; i++) {
      cache.append(words[i]);
      if (i == words.length - 1) {
        cache.append(" ".repeat(maxWidth - cache.length()));
        continue;
      }
      cache.append(" ");
    }

    res.add(cache.toString());

    return res;
  }

  public static void main(String[] args) {

    System.out.println(new Solution68()
        .fullJustify(new String[] { "What", "must", "be", "acknowledgment", "shall", "be" }, 16));

    // System.out.println("This is an".length());
    // System.out.println(" example of text".length());

  }
}