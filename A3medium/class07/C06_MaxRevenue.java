package A3medium.class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import java.util.TreeMap;
import java.util.TreeSet;

// CC直播的运营部门组织了很多运营活动，每个活动需要花费一定的时间参与，主播每参加完一个活动即可得到一定的奖励，
// 参与活动可以从任意活动开始，但一旦开始，就需要将后续活动参加完毕(注意:最后一个活动必须参与)，
// 活动之间存在一定的依赖关系(不存在环的情况)。
// 现在给出所有的活动时间与依赖关系，以及给出有限的时间，请帮主播计算在有限的时候内，能获得的最大奖励，以及需要的最少时长。
// 如上图数据所示，给定有限时间为10天。可以获取得最大奖励为:11700，需要的时长为:9天。参加的活动为BDFH四个。
// 输入描述:
// 第一行输入数据N与D，表示有N项活动，D表示给予的时长。0<N<=1000，0<D<=10000。 
// 从第二行开始到N+1行，每行描述一个活动的信息，其中第一项表示当前活动需要花费的时间t，第二项表示可以获 得的奖励a，之后有N项数据，表示当前活动与其他活动的依赖关系，1表示有依赖，0表示无依赖。每项数据用空格分开。
// 输出描述: 输出两项数据A与T，用空格分割。A表示所获得的最大奖励，T表示所需要的时长。 输入
// 8 10
// 3 2000 0 1 1 0 0 0 0 0
// 3 4000 0 0 0 1 1 0 0 0
// 2 2500 0 0 0 1 0 0 0 0
// 1 1600 0 0 0 0 1 1 1 0
// 4 3800 0 0 0 0 0 0 0 1
// 2 2600 0 0 0 0 0 0 0 1
// 4 4000 0 0 0 0 0 0 0 1
// 3 3500 0 0 0 0 0 0 0 0
// 输出
// 11700 9
public class C06_MaxRevenue {

	// 请保证只有唯一的最后节点
	public static int[] maxRevenue(int allTime, int[] revenue, int[] times, int[][] dependents) {
		int size = revenue.length;
		HashMap<Integer, ArrayList<Integer>> parents = new HashMap<>();
		for (int i = 0; i < size; i++) {
			parents.put(i, new ArrayList<>());
		}
		int end = -1;
		for (int i = 0; i < dependents.length; i++) {
			boolean allZero = true;
			for (int j = 0; j < dependents[0].length; j++) {
				if (dependents[i][j] != 0) {
					parents.get(j).add(i);
					allZero = false;
				}
			}
			if (allZero) {
				end = i;
			}
		}
		HashMap<Integer, TreeMap<Integer, Integer>> nodeCostRevenueMap = new HashMap<>();
		for (int i = 0; i < size; i++) {
			nodeCostRevenueMap.put(i, new TreeMap<>());
		}
		nodeCostRevenueMap.get(end).put(times[end], revenue[end]);
		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(end);
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int last : parents.get(cur)) {
				for (Entry<Integer, Integer> entry : nodeCostRevenueMap.get(cur).entrySet()) {
					int lastCost = entry.getKey() + times[last];
					int lastRevenue = entry.getValue() + revenue[last];
					TreeMap<Integer, Integer> lastMap = nodeCostRevenueMap.get(last);
					if (lastMap.floorKey(lastCost) == null || lastMap.get(lastMap.floorKey(lastCost)) < lastRevenue) {
						lastMap.put(lastCost, lastRevenue);
					}
				}
				queue.add(last);
			}
		}
		TreeMap<Integer, Integer> allMap = new TreeMap<>();
		for (TreeMap<Integer, Integer> curMap : nodeCostRevenueMap.values()) {
			for (Entry<Integer, Integer> entry : curMap.entrySet()) {
				int cost = entry.getKey();
				int reven = entry.getValue();
				if (allMap.floorKey(cost) == null || allMap.get(allMap.floorKey(cost)) < reven) {
					allMap.put(cost, reven);
				}
			}
		}
		return new int[] { allMap.floorKey(allTime), allMap.get(allMap.floorKey(allTime)) };
	}

	// 法一：暴力解法
	public static int[] maxRevenue2(int allTime, int[] revenue, int[] times, int[][] dependents) {
		if (allTime < 0 || revenue == null || times == null || dependents == null) {
			return new int[] { -1, -1 };
		}

		int[] res = process(allTime - times[times.length - 1], revenue, times, dependents, times.length - 1,
				revenue[times.length - 1]);
		return new int[] { allTime - res[0], res[1] };
	}

	private static int[] process(int remainTime, int[] revenue, int[] times, int[][] dependents, int i, int rewards) {
		if (remainTime < 0) {
			return new int[] { 0, 0 };
		}
		if (i < -1) {
			System.out.println(i);
			return new int[] { remainTime, rewards };
		}

		int[] max = new int[] { remainTime, rewards };
		for (int j = 0; j < i; j++) {
			if (dependents[j][i] == 0 || remainTime - times[j] < 0) {
				continue;
			}

			int[] res = process(remainTime - times[j], revenue, times, dependents, j, rewards + revenue[j]);
			if (max[1] < res[1]) {
				max[1] = res[1];
				max[0] = res[0];
			}
		}
		return max;
	}

	// 法二：
	private static class Node {
		public int time;
		public int revenue;

		public Node(int time, int revenue) {
			this.time = time;
			this.revenue = revenue;
		}
	}

	private static class MyList {
		public ArrayList<Node> list = new ArrayList<>();

		public void add(Node n) {
			list.add(n);
		}
	}

	public static int[] maxRevenue3(int allTime, int[] revenue, int[] times, int[][] dependents) {
		if (allTime < 0 || revenue == null || times == null || dependents == null) {
			return new int[] { -1, -1 };
		}

		if (times[times.length - 1] > allTime) {
			return new int[] { 0, 0 };
		}

		// HashMap<Integer, ArrayList<Node>> map = new HashMap<>(); //
		// 元素索引--该元素的所有路线treeMap表
		MyList[] arr = new MyList[times.length];
		arr[arr.length - 1] = new MyList();
		arr[arr.length - 1].add(new Node(times[times.length - 1], revenue[revenue.length - 1]));
		// map.put(times.length - 1, new ArrayList<>());
		// map.get(times.length - 1).add(new Node(times[times.length - 1],
		// revenue[revenue.length - 1]));

		for (int i = dependents.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (dependents[j][i] == 0) { // 筛选出能到i位置的有哪些位置
					continue;
				}

				if (arr[j] == null) {
					arr[j] = new MyList();
				}

				for (Node n : arr[i].list) {
					if (n.time + times[j] <= allTime) {
						arr[j].add(new Node(n.time + times[j], n.revenue + revenue[j]));
					}
				}
			}
		}

		// 通过treeSet排序
		TreeSet<Node> treeSet = new TreeSet<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o2.revenue - o1.revenue;
			}
		});

		for (int i = 0; i < arr.length; i++) {
			for (Node n : arr[i].list) {
				treeSet.add(n);
			}
		}
		Node n = treeSet.first();
		if (n == null) {
			return new int[] { -1, -1 };
		}
		return new int[] { n.time, n.revenue };
	}

	public static void main(String[] args) {
		int allTime = 10;
		int[] revenue = { 2000, 4000, 2500, 1600, 3800, 2600, 4000, 3500 };
		int[] times = { 3, 3, 2, 1, 4, 2, 4, 3 };
		int[][] dependents = { { 0, 1, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 1, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 1, 1, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } };

		int[] res = maxRevenue(allTime, revenue, times, dependents);
		System.out.println(res[0] + " , " + res[1]);
		int[] res2 = maxRevenue2(allTime, revenue, times, dependents);
		System.out.println(res2[0] + " , " + res2[1]);
		int[] res3 = maxRevenue3(allTime, revenue, times, dependents);
		System.out.println(res3[0] + " , " + res3[1]);

	}
}
