package A3medium.class06;

import java.util.HashMap;

// 已知一棵二叉树中没有重复节点，并且给定了这棵树的中序遍历数组和先序遍历数组，返回后序遍历数组。
// 比如给定:
// int[] pre = { 1, 2, 4, 5, 3, 6, 7 };
// int[] in = { 4, 2, 5, 1, 6, 3, 7 }; 
// 返回: {4,5,2,6,7,3,1}
public class C04_PreAndInArrayToPosArray {

	// // 时间复杂度 O(N)
	// public static int[] getPosArray(int[] pre, int[] in) {
	// if (pre == null || in == null) {
	// return null;
	// }
	// int len = pre.length;
	// int[] pos = new int[len];
	// HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	// for (int i = 0; i < len; i++) {
	// map.put(in[i], i);
	// }
	// setPos(pre, 0, len - 1, in, 0, len - 1, pos, len - 1, map);
	// return pos;
	// }

	// public static int setPos(int[] p, int pi, int pj, int[] n, int ni, int nj,
	// int[] s, int si,
	// HashMap<Integer, Integer> map) {
	// if (pi > pj) {
	// return si;
	// }
	// s[si--] = p[pi];
	// // 遍历获取in数组内子树头节点位置，改为了用hashmap预处理缓存
	// int i = map.get(p[pi]);
	// si = setPos(p, pj - nj + i + 1, pj, n, i + 1, nj, s, si, map);
	// return setPos(p, pi + 1, pi + i - ni, n, ni, i - 1, s, si, map);
	// }

	public static int[] getPosArray(int[] pre, int[] in) {
		if (pre == null || in == null || pre.length != in.length) {
			return null;
		}
		// 缓存中序遍历数组的位置索引
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			map.put(in[i], i);
		}

		int[] pos = new int[pre.length];
		process(pre, in, pos, map, 0, pre.length - 1, 0, in.length - 1);
		return pos;
	}

	public static void process(int[] pre, int[] in, int[] pos, HashMap<Integer, Integer> map, int preS, int preE, int inS,
			int inE) {
		if (preS > preE || inS > inE) {
			return;
		}

		int headIndex = map.get(pre[preS]);
//	{ 1, 2, 4, 5, 3, 6, 7 };
//	{ 4, 2, 5, 1, 6, 3, 7 };
//  { 4  5  2  6  7  3  1 }
	 // 0  1  2  3  4  5  6
		// 更改当前子树的头节点
		// TODO:位置对应关系不对
		pos[inE] = pre[preS];
	
		// 左侧递归更改值
		process(pre, in, pos, map, preS + 1, preS + (headIndex - inS), inS, headIndex - 1); // preE为preS + 1 +( headIndex - inS) - 1
		// 右侧递归更改值
		process(pre, in, pos, map, preS + (headIndex - inS) + 1, preE, headIndex + 1, inE);
	}

	// for test
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
