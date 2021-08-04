package dp;

import java.util.List;

public class C02_MaxHappy {
	// answer:

	// public static int maxHappy(int[][] matrix) {
	// int[][] dp = new int[matrix.length][2];
	// boolean[] visited = new boolean[matrix.length];
	// int root = 0;
	// for (int i = 0; i < matrix.length; i++) {
	// if (i == matrix[i][0]) {
	// root = i;
	// }
	// }
	// process(matrix, dp, visited, root);
	// return Math.max(dp[root][0], dp[root][1]);
	// }

	// public static void process(int[][] matrix, int[][] dp, boolean[] visited, int
	// root) {
	// visited[root] = true;
	// dp[root][1] = matrix[root][1];
	// for (int i = 0; i < matrix.length; i++) {
	// if (matrix[i][0] == root && !visited[i]) {
	// process(matrix, dp, visited, i);
	// dp[root][1] += dp[i][0];
	// dp[root][0] += Math.max(dp[i][1], dp[i][0]);
	// }
	// }
	// }

	public static class Employee {
		public int happy;
		List<Employee> subordinates;
	}

	private static class Info {
		public int comeMaxHappy;
		public int notComeMaxHappy;

		public Info(int comeMaxHappy, int notComeMaxHappy) {
			this.comeMaxHappy = comeMaxHappy;
			this.notComeMaxHappy = notComeMaxHappy;
		}
	}

	public int maxHappy(Employee boss) {
		Info info = process(boss);
		return Math.max(info.comeMaxHappy, info.notComeMaxHappy);
	}

	public Info process(Employee emp) {
		if (emp.subordinates.size() == 0) {
			return new Info(emp.happy, 0);
		}

		int comeMaxHappy = 0;
		int notComeMaxHappy = 0;

		for (Employee e : emp.subordinates) {
			Info info = process(e);
			comeMaxHappy += info.notComeMaxHappy;
			notComeMaxHappy += Math.max(info.comeMaxHappy, info.notComeMaxHappy);
		}

		return new Info(comeMaxHappy, notComeMaxHappy);
	}

	public static void main(String[] args) {
		// int[][] matrix = { { 1, 8 }, { 1, 9 }, { 1, 10 } };
		// System.out.println(maxHappy(matrix));
	}
}
