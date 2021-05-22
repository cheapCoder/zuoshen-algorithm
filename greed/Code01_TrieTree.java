package greed;

public class Code01_TrieTree {

	// public static class TrieNode {
	// public int path;
	// public int end;
	// public TrieNode[] nexts;

	// public TrieNode() {
	// path = 0;
	// end = 0;
	// nexts = new TrieNode[26];
	// }
	// }

	// public static class Trie {
	// private TrieNode root;

	// public Trie() {
	// root = new TrieNode();
	// }

	// public void insert(String word) { // 未考虑大小写
	// if (word == null) {
	// return;
	// }
	// char[] chs = word.toCharArray();
	// TrieNode node = root;
	// int index = 0;
	// for (int i = 0; i < chs.length; i++) {
	// index = chs[i] - 'a';
	// if (node.nexts[index] == null) {
	// node.nexts[index] = new TrieNode();
	// }
	// node.path++;
	// }
	// node.end++;
	// }

	// public void delete(String word) {
	// if (search(word) != 0) {
	// char[] chs = word.toCharArray();
	// TrieNode node = root;
	// int index = 0;
	// for (int i = 0; i < chs.length; i++) {
	// index = chs[i] - 'a';
	// if (--node.nexts[index].path == 0) {
	// node.nexts[index] = null;
	// return;
	// }
	// node = node.nexts[index];
	// }
	// node.end--;
	// }
	// }

	// public int search(String word) {
	// if (word == null) {
	// return 0;
	// }
	// char[] chs = word.toCharArray();
	// TrieNode node = root;
	// int index = 0;
	// for (int i = 0; i < chs.length; i++) {
	// index = chs[i] - 'a';
	// if (node.nexts[index] == null) {
	// return 0;
	// }
	// node = node.nexts[index];
	// }
	// return node.end;
	// }

	// public int prefixNumber(String pre) {
	// if (pre == null) {
	// return 0;
	// }
	// char[] chs = pre.toCharArray();
	// TrieNode node = root;
	// int index = 0;
	// for (int i = 0; i < chs.length; i++) {
	// index = chs[i] - 'a';
	// if (node.nexts[index] == null) {
	// return 0;
	// }
	// node = node.nexts[index];
	// }
	// return node.path;
	// }
	// }

	private static class TrieNode {
		public int path = 0;
		public int end = 0;
		public TrieNode[] nexts = new TrieNode[26];
	}

	public static class Trie {
		private TrieNode root = new TrieNode();

		public void insert(String word) {
			if (word == null) {
				return;
			}

			TrieNode cur = root;
			char[] arr = word.toLowerCase().toCharArray();
			int i = 0;

			cur.path++;
			while (i < arr.length) {
				if (cur.nexts[arr[i] - 'a'] == null) {
					cur.nexts[arr[i] - 'a'] = new TrieNode();
				}
				cur = cur.nexts[arr[i++] - 'a'];
				cur.path++;
			}
			cur.end++;
		}

		public void delete(String word) {
			if (search(word) == 0) {
				return;
			}

			TrieNode cur = root;
			char[] arr = word.toLowerCase().toCharArray();
			int i = 0;

			while (i < arr.length) {
				if (cur.nexts[arr[i] - 'a'] == null) {
					return;
				}

				if (--cur.nexts[arr[i] - 'a'].path == 0) {
					cur.nexts[arr[i] - 'a'] = null;
					return;
				}
				cur = cur.nexts[arr[i++] - 'a'];

			}
			cur.end--;
		}

		// 返回Word加入过几次
		public int search(String word) {
			if (word == null) {
				return 0;
			}

			TrieNode cur = root;
			char[] arr = word.toLowerCase().toCharArray();
			int i = 0;

			while (i < arr.length) {
				cur = cur.nexts[arr[i++] - 'a'];
				if (cur == null) {
					return 0;
				}
			}

			return cur.end;
		}

		public int prefixNumber(String pre) {
			if (pre == null) {
				return 0;
			}

			TrieNode cur = root;
			char[] arr = pre.toLowerCase().toCharArray();
			int i = 0;

			while (i < arr.length) {
				cur = cur.nexts[arr[i++] - 'a'];
				if (cur == null) {
					return 0;
				}
			}

			return cur.path;
		}

	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		System.out.println(trie.search("zuo"));
		trie.insert("zuo");
		System.out.println(trie.search("zuo"));
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.insert("zuo");
		trie.insert("zuo");
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.insert("zuoa");
		trie.insert("zuoac");
		trie.insert("zuoab");
		trie.insert("zuoad");
		trie.delete("zuoa");
		System.out.println(trie.search("zuoa"));
		System.out.println(trie.prefixNumber("zuo")); // 3
	}

}
