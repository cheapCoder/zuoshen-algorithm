package util;

import java.util.Comparator;

class Com implements Comparator<String> {
  @Override
  public int compare(String a, String b) {
    return b.length() - a.length();
  }
}