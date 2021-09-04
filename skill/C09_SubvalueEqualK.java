package skill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

// 给定一个数组arr，求差值为k的去重数字对。
public class C09_SubvalueEqualK {

	// public static List<List<Integer>> allPair(int[] arr, int k) {
	// HashSet<Integer> set = new HashSet<>();
	// for (int i = 0; i < arr.length; i++) {
	// set.add(arr[i]);
	// }
	// List<List<Integer>> res = new ArrayList<>();
	// for (Integer cur : set) {
	// if (set.contains(cur + k)) {
	// res.add(Arrays.asList(cur, cur + k));
	// }
	// }
	// return res;
	// }

	public static List<List<Integer>> allPair(int[] arr, int k) {
		List<List<Integer>> res = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			set.add(arr[i]);
		}

		for (int i : set) {
			if (set.contains(arr[i] + k)) {
				res.add(Arrays.asList(arr[i], arr[i] + k));
			}
		}

		return res;
	}
}
