package leetcode;

import java.util.ArrayList;
import java.util.Stack;

/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

public class Solution92 {

    // 法1：用了O(n)的空间复杂度
    public ListNode reverseBetween1(ListNode head, int left, int right) {
        ListNode guide = new ListNode();
        guide.next = head;

        ListNode cur = guide;
        Stack<ListNode> cache = new Stack<>();
        int i = 0;
        ListNode pre = null;
        ListNode next = null;
        while (i != left) {
            if (i == left - 1) {
                pre = cur;
            }
            cur = cur.next;
            i++;
        }
        while (i <= right) {
            cache.push(cur);
            cur = cur.next;
            i++;
        }
        next = cur;

        while (!cache.isEmpty()) {
            pre.next = cache.pop();
            pre = pre.next;
        }
        pre.next = next;
        return guide.next;
    }

    // 法2：用O(1)的空间复杂度
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode guide = new ListNode();
        guide.next = head;

        ListNode cur = guide;
        int i = 0;
        ListNode pre = null;
        ListNode next = null;
        while (i != left) {
            if (i == left - 1) {
                pre = cur;
            }
            cur = cur.next;
            i++;
        }
        while (i <= right) {
            cur = cur.next;
            i++;
        }
        next = cur;

        while (!cache.isEmpty()) {
            pre.next = cache.pop();
            pre = pre.next;
        }
        pre.next = next;
        return guide.next;
    }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) {
        ListNode head = stringToListNode("[1,2,3,4,5]");
        int left = Integer.parseInt("2");
        int right = Integer.parseInt("4");

        ListNode ret = new Solution92().reverseBetween(head, left, right);

        String out = listNodeToString(ret);

        System.out.print(out);
    }
}