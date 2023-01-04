package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class NestedInteger {
  private List<NestedInteger> list;
  private Integer integer;

  public NestedInteger(List<NestedInteger> list) {
    this.list = list;
  }

  public void add(NestedInteger nestedInteger) {
    if (this.list != null) {
      this.list.add(nestedInteger);
    } else {
      this.list = new ArrayList();
      this.list.add(nestedInteger);
    }
  }

  public void setInteger(int num) {
    this.integer = num;
  }

  public NestedInteger(Integer integer) {
    this.integer = integer;
  }

  public NestedInteger() {
    this.list = new ArrayList();
  }

  public boolean isInteger() {
    return integer != null;
  }

  public Integer getInteger() {
    return integer;
  }

  public List<NestedInteger> getList() {
    return list;
  }

  public static String printNi(NestedInteger thisNi, StringBuilder sb) {
    if (thisNi.isInteger()) {
      sb.append(thisNi.integer);
      sb.append(",");
    }
    sb.append("[");
    for (NestedInteger ni : thisNi.list) {
      if (ni.isInteger()) {
        sb.append(ni.integer);
        sb.append(",");
      } else {
        printNi(ni, sb);
      }
    }
    sb.append("]");
    return sb.toString();
  }
}

public class Solution341 implements Iterator<Integer> {

  public LinkedList<Integer> list;

  public Solution341(List<NestedInteger> nestedList) {
    list = new LinkedList<>();
    this.process(nestedList);
    System.out.println(list);
  }

  private void process(List<NestedInteger> input) {
    for (int i = 0; i < input.size(); i++) {
      NestedInteger ni = input.get(i);
      if (ni.isInteger()) {
        this.list.addLast(ni.getInteger());
      } else {
        process(ni.getList());
      }
    }

  }

  @Override
  public Integer next() {
    return list.removeFirst();
  }

  @Override
  public boolean hasNext() {
    return !list.isEmpty();
  }

  public static void main(String[] args) {
    // [[1,1],2,[3,3]]
    List<NestedInteger> test = new ArrayList<>();

    // [1,1]
    List<NestedInteger> flist = new ArrayList<>();
    flist.add(new NestedInteger(1));
    flist.add(new NestedInteger(1));
    NestedInteger first = new NestedInteger(flist);

    test.add(first);

    // 2
    test.add(new NestedInteger(2));

    // [3,3]
    List<NestedInteger> tlist = new ArrayList<>();
    tlist.add(new NestedInteger(3));
    tlist.add(new NestedInteger(3));
    NestedInteger third = new NestedInteger(tlist);

    test.add(third);

    // System.out.println(NestedInteger.printNi(, new StringBuilder()));
    var instance = new Solution341(test);

    System.out.println(instance);


    int[] res = new int[instance.list.size()];
    int i = 0;
    while (instance.hasNext()) {
      res[i++] = instance.next();
    }

    System.out.println(res);

  }
}