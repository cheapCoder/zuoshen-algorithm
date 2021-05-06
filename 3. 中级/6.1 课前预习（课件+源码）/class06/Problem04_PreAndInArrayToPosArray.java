package class06;

import java.util.HashMap;

public class Problem04_PreAndInArrayToPosArray {

	public static int[] getPosArray(int[] pre, int[] in) {
		if (pre == null || in == null) {
			return null;
		}
		int len = pre.length;
		int[] pos = new int[len];
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < len; i++) {
			map.put(in[i], i);
		}
		setPos(pre, 0, len - 1, in, 0, len - 1, pos, len - 1, map);
		return pos;
	}

	public static int setPos(int[] p, int pi, int pj, int[] n, int ni, int nj, int[] s, int si,
			HashMap<Integer, Integer> map) {
		if (pi > pj) {
			return si;
		}
		s[si--] = p[pi];
		int i = map.get(p[pi]);
		si = setPos(p, pj - nj + i + 1, pj, n, i + 1, nj, s, si, map);
		return setPos(p, pi + 1, pi + i - ni, n, ni, i - 1, s, si, map);
	}

	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] pre = { 1, 2, 4, 5, 3, 6, 7 };
		int[] in = { 4, 2, 5, 1, 6, 3, 7 };
		int[] pos = getPosArray(pre, in);
		printArray(pos);

	}
}
