package leetcode;

import java.io.IOException;

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
class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode[] arr = new ListNode[n + 1];
        int p = 0;

        ListNode guide = new ListNode();
        guide.next = head;

        ListNode node = guide;
        while (node != null) {
            arr[p] = node;
            p = (p == arr.length - 1) ? 0 : (p + 1);

            node = node.next;
        }

        if (arr[p].next == null) {
            return null;
        }

        arr[p].next = arr[p].next.next;

        return guide.next;
    }
}

class MainClass {
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

    public static void main(String[] args) throws IOException {
        ListNode head = stringToListNode("[1,2]");
        int n = Integer.parseInt("1");
        // ListNode head = stringToListNode("[1,2,3,4,5]");
        // int n = Integer.parseInt("2");

        ListNode ret = new Solution19().removeNthFromEnd(head, n);

        String out = listNodeToString(ret);

        System.out.print(out);
    }
}