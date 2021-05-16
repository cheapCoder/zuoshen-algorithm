package binary_tree;

public class Code10_PaperFolding {

	// public static void printAllFolds(int N) {
	// printProcess(1, N, true);
	// System.out.println();
	// }

	public static void printProcess(int i, int N, boolean down) {
	if (i > N) {
	return;
	}
	printProcess(i + 1, N, true);
	System.out.print(down ? "down " : "up " + " ");
	printProcess(i + 1, N, false);
	}

	public static void printAllFolds2(int n) {

		print(n, "down");
		System.out.println();
	}

	private static void print(int n, String s) {
		if (n == 0) {
			return;
		}
		print(n - 1, "down");
		System.out.print(s + " ");
		print(n - 1, "up");

	}

	public static void main(String[] args) {
		int N = 4;
		// printAllFolds(N);
		printAllFolds2(N);
	}

}
