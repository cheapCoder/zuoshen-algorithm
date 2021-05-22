package force_recursion;

import java.util.ArrayList;
import java.util.List;

public class Code7_PrintAllSubsquences {

	// public static void printAllSubsequence(String str) {
	// char[] chs = str.toCharArray();
	// process(chs, 0);
	// }

	// public static void process(char[] chs, int i) {
	// if (i == chs.length) {
	// System.out.println(String.valueOf(chs));
	// return;
	// }
	// process(chs, i + 1);
	// char tmp = chs[i];
	// chs[i] = 0;
	// process(chs, i + 1);
	// chs[i] = tmp;
	// }

	// public static void function(String str) {
	// char[] chs = str.toCharArray();
	// process(chs, 0, new ArrayList<Character>());
	// }

	// public static void process(char[] chs, int i, List<Character> res) {
	// if(i == chs.length) {
	// printList(res);
	// }
	// List<Character> resKeep = copyList(res);
	// resKeep.add(chs[i]);
	// process(chs, i+1, resKeep);
	// List<Character> resNoInclude = copyList(res);
	// process(chs, i+1, resNoInclude);
	// }

	// public static void printList(List<Character> res) {
	// }

	// public static List<Character> copyList(List<Character> list){
	// return null;
	// }

	// 法一
	public static void printAllSubsequence(String str) {
		if (str == null) {
			return;
		}

		process(str.toCharArray(), 0, new ArrayList<Character>());
	}

	public static void process(char[] arr, int i, ArrayList<Character> container) {
		if (i >= arr.length) {
			printList(container);
			return;
		}
		container.add(arr[i]);
		process(arr, i + 1, container);
		container.remove((Object) arr[i]);
		process(arr, i + 1, container);
	}

	// 法二
	public static void printAllSubsequence2(String str) {
		if (str == null) {
			return;
		}
		process2(str.toCharArray(), 0);
	}

	public static void process2(char[] arr, int i) {
		if (i >= arr.length) {
			System.out.println(String.valueOf(arr));
			return;
		}
		char tem = arr[i];
		process2(arr, i + 1);
		arr[i] = 0;
		process2(arr, i + 1);
		arr[i] = tem;
	}

	private static void printList(List<Character> res) {
		for (Character character : res) {
			System.out.print(character);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		String test = "abc";
		printAllSubsequence2(test);

	}

}
