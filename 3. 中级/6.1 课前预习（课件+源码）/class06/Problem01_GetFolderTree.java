package class06;

import java.util.TreeMap;

public class Problem01_GetFolderTree {

	public static class Node {
		public String name;
		public TreeMap<String, Node> nextMap;

		public Node(String name) {
			this.name = name;
			nextMap = new TreeMap<>();
		}
	}

	public static void print(String[] folderPaths) {
		if (folderPaths == null || folderPaths.length == 0) {
			return;
		}
		Node head = generateFolderTree(folderPaths);
		printProcess(head, 0);
	}

	public static Node generateFolderTree(String[] folderPaths) {
		Node head = new Node("");
		for (String foldPath : folderPaths) {
			String[] paths = foldPath.split("\\\\");
			Node cur = head;
			for (int i = 0; i < paths.length; i++) {
				if (!cur.nextMap.containsKey(paths[i])) {
					cur.nextMap.put(paths[i], new Node(paths[i]));
				}
				cur = cur.nextMap.get(paths[i]);
			}
		}
		return head;
	}

	public static void printProcess(Node head, int level) {
		if (level != 0) {
			System.out.println(get2nSpace(level) + head.name);
		}
		for (Node next : head.nextMap.values()) {
			printProcess(next, level + 1);
		}
	}

	public static String get2nSpace(int n) {
		String res = "";
		for (int i = 1; i < n; i++) {
			res += "  ";
		}
		return res;
	}

	public static void main(String[] args) {
		String[] arr = { "b\\cst", "d\\", "a\\d\\e", "a\\b\\c" };
		print(arr);
	}

}
