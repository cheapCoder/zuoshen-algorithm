package A4high.class02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

// 问题：天际线问题(https://leetcode-cn.com/problems/the-skyline-problem/)
// 给定一个N×3的矩阵matrix，对于每一个长度为3的小数组arr，都表示一个大楼的三个数据。
// arr[0]表示大楼的左边界，arr[1]表示大楼的右边界，arr[2]表示大楼的高度(一定大于0)。
// 每座大楼的地基都在X轴上，大楼之间可能会有重叠，请返回整体的轮廓线数组。

public class C01_BuildingOutline {

	// // 描述高度变化的对象
	// public static class Node {
	// public int x; // x轴上的值
	// public boolean isAdd;// true为加入，false为删除
	// public int h; // 高度

	// public Node(int x, boolean isAdd, int h) {
	// this.x = x;
	// this.isAdd = isAdd;
	// this.h = h;
	// }
	// }

	// // 排序的比较策略
	// // 1，第一个维度的x值从小到大。
	// // 2，如果第一个维度的值相等，看第二个维度的值，“加入”排在前，“删除”排在后
	// // 3，如果两个对象第一维度和第二个维度的值都相等，则认为两个对象相等，谁在前都行。
	// public static class NodeComparator implements Comparator<Node> {
	// @Override
	// public int compare(Node o1, Node o2) {
	// if (o1.x != o2.x) {
	// return o1.x - o2.x;
	// }
	// if (o1.isAdd != o2.isAdd) {
	// return o1.isAdd ? -1 : 1;
	// }
	// return 0;
	// }
	// }

	// // 全部流程的主方法
	// public static List<List<Integer>> buildingOutline(int[][] matrix) {
	// Node[] nodes = new Node[matrix.length * 2];
	// // 每一个大楼轮廓数组，产生两个描述高度变化的对象
	// for (int i = 0; i < matrix.length; i++) {
	// nodes[i * 2] = new Node(matrix[i][0], true, matrix[i][2]);
	// nodes[i * 2 + 1] = new Node(matrix[i][1], false, matrix[i][2]);
	// }

	// // 把描述高度变化的对象数组，按照规定的排序策略排序
	// Arrays.sort(nodes, new NodeComparator());

	// // TreeMap就是java中的红黑树结构，直接当作有序表来使用
	// TreeMap<Integer, Integer> mapHeightTimes = new TreeMap<>();
	// TreeMap<Integer, Integer> mapXvalueHeight = new TreeMap<>();
	// for (int i = 0; i < nodes.length; i++) {
	// if (nodes[i].isAdd) { // 如果当前是加入操作
	// if (!mapHeightTimes.containsKey(nodes[i].h)) { // 没有出现的高度直接新加记录
	// mapHeightTimes.put(nodes[i].h, 1);
	// } else { // 之前出现的高度，次数加1即可
	// mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) + 1);
	// }
	// } else { // 如果当前是删除操作
	// if (mapHeightTimes.get(nodes[i].h) == 1) { // 如果当前的高度出现次数为1，直接删除记录
	// mapHeightTimes.remove(nodes[i].h);
	// } else { // 如果当前的高度出现次数大于1，次数减1即可
	// mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) - 1);
	// }
	// }

	// // 根据mapHeightTimes中的最大高度，设置mapXvalueHeight表
	// if (mapHeightTimes.isEmpty()) { // 如果mapHeightTimes为空，说明最大高度为0
	// mapXvalueHeight.put(nodes[i].x, 0);
	// } else { // 如果mapHeightTimes不为空，通过mapHeightTimes.lastKey()取得最大高度
	// mapXvalueHeight.put(nodes[i].x, mapHeightTimes.lastKey());
	// }
	// }
	// // res为结果数组，每一个List<Integer>代表一个轮廓线，有开始位置，结束位置，高度，一共三个信息
	// List<List<Integer>> res = new ArrayList<>();
	// // 一个新轮廓线的开始位置
	// int start = 0;
	// // 之前的最大高度
	// int preHeight = 0;
	// // 根据mapXvalueHeight生成res数组
	// for (Entry<Integer, Integer> entry : mapXvalueHeight.entrySet()) {
	// // 当前位置
	// int curX = entry.getKey();
	// // 当前最大高度
	// int curMaxHeight = entry.getValue();
	// if (preHeight != curMaxHeight) { // 之前最大高度和当前最大高度不一样时
	// if (preHeight != 0) {
	// res.add(new ArrayList<>(Arrays.asList(start, curX, preHeight)));
	// }
	// start = curX;
	// preHeight = curMaxHeight;
	// }
	// }
	// return res;
	// }

	public static class Position {
		public int x;
		public int y;
		boolean isAdd;

		public Position(int x, int y, boolean isAdd) {
			this.x = x;
			this.y = y;
			this.isAdd = isAdd;
		}

	}

	public static List<List<Integer>> skyline(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) {
			return null;
		}

		// 预处理位置信息
		Position[] pos = preProcess(matrix);
		for (int i = 0; i < pos.length; i++) {
			System.out.println(pos[i].x + " | " + pos[i].y + " | " + pos[i].isAdd);
		}

		ArrayList<List<Integer>> res = new ArrayList<>();
		TreeMap<Integer, Integer> temRes = new TreeMap<>();
		TreeMap<Integer, Integer> beforeMostHeight = new TreeMap<>();
		int preHeight = -1;

		for (int i = 0; i < pos.length; i++) {
			// 维护高度频率map
			if (pos[i].isAdd) {
				if (!beforeMostHeight.containsKey(pos[i].y)) {
					beforeMostHeight.put(pos[i].y, 0);
				}
				beforeMostHeight.put(pos[i].y, beforeMostHeight.get(pos[i].y) + 1);
			} else {
				// 由于preProcess排序中上升的总是比下降的小，所有不用判断是否存在
				beforeMostHeight.put(pos[i].y, beforeMostHeight.get(pos[i].y) - 1);
				if (beforeMostHeight.get(pos[i].y) == 0) {
					beforeMostHeight.remove(pos[i].y);
				}
			}

			// 计算分界点
			int mapPreHeight = 0;
			if (!beforeMostHeight.isEmpty()) {
				mapPreHeight = beforeMostHeight.lastKey();
			}
			if (preHeight != mapPreHeight) {
				temRes.put(pos[i].x, mapPreHeight); // 存在x一样高度不一样时，取最后一个值
				preHeight = mapPreHeight;
			}
		}

		temRes.keySet().forEach(key -> {
			res.add(Arrays.asList(key, temRes.get(key)));
		});

		return res;
	}

	private static class MyComparator implements Comparator<Position> {

		@Override
		public int compare(Position o1, Position o2) {
			if (o1.x != o2.x) {
				return o1.x - o2.x;
			}
			if (o1.y != o2.y) {
				return o2.y - o1.y;
			}
			if (o1.isAdd != o2.isAdd) {
				return o1.isAdd ? -1 : 1;
			}

			return 0;
		}

	}

	private static Position[] preProcess(int[][] matrix) {
		Position[] res = new Position[matrix.length * 2];
		for (int i = 0; i < matrix.length; i++) {
			res[2 * i] = new Position(matrix[i][0], matrix[i][2], true);
			res[2 * i + 1] = new Position(matrix[i][1], matrix[i][2], false);
		}

		Arrays.sort(res, new MyComparator());

		return res;
	}

	public static void main(String[] args) {
		// int[][] test = new int[][] { { 2, 9, 10 }, { 3, 7, 15 }, { 5, 12, 12 }, { 15,
		// 20, 10 }, { 19, 24, 8 } };

		int[][] test = new int[][] { { 1, 2, 1 }, { 1, 2, 2 }, { 1, 2, 3 } };
		System.out.println(skyline(test));
		// System.out.println(buildingOutline(test));
	}
}
