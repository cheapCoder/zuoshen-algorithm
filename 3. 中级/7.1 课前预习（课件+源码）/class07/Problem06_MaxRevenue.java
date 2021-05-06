package class07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Problem06_MaxRevenue {

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
		int[][] dependents = { 
				{ 0, 1, 1, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 1, 1, 0, 0, 0 }, 
				{ 0, 0, 0, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 1, 1, 1, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 1 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } };

		
		int[] res = maxRevenue(allTime, revenue, times, dependents);
		System.out.println(res[0] + " , " + res[1]);
	}

}
