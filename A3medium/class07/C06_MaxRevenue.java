package A3medium.class07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;

// CC直播的运营部门组织了很多运营活动，每个活动需要花费一定的时间参与，主播每参加完一个活动即可得到一定的奖励，
// 参与活动可以从任意活动开始，但一旦开始，就需要将后续活动参加完毕(注意:最后一个活动必须参与)，
// 活动之间存在一定的依赖关系(不存在环的情况)。
// 现在给出所有的活动时间与依赖关系，以及给出有限的时间，请帮主播计算在有限的时候内，能获得的最大奖励，以及需要的最少时长。
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

	public static void main(String[] args) {
		int allTime = 10;
		int[] revenue = { 2000, 4000, 2500, 1600, 3800, 2600, 4000, 3500 };
		int[] times = { 3, 3, 2, 1, 4, 2, 4, 3 };
		int[][] dependents = { { 0, 1, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 1, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 1, 1, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } };

		int[] res = maxRevenue(allTime, revenue, times, dependents);
		System.out.println(res[0] + " , " + res[1]);
	}

}
