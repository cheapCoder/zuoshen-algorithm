package hash_function;

import java.util.HashMap;

public class Code02_RandomPool {

	// public static class Pool<K> {
	// private HashMap<K, Integer> keyIndexMap;
	// private HashMap<Integer, K> indexKeyMap;
	// private int size;

	// public Pool() {
	// this.keyIndexMap = new HashMap<K, Integer>();
	// this.indexKeyMap = new HashMap<Integer, K>();
	// this.size = 0;
	// }

	// public void insert(K key) {
	// if (!this.keyIndexMap.containsKey(key)) {
	// this.keyIndexMap.put(key, this.size);
	// this.indexKeyMap.put(this.size++, key);
	// }
	// }

	// public void delete(K key) {
	// if (this.keyIndexMap.containsKey(key)) {
	// int deleteIndex = this.keyIndexMap.get(key);
	// int lastIndex = --this.size;
	// K lastKey = this.indexKeyMap.get(lastIndex);
	// this.keyIndexMap.put(lastKey, deleteIndex);
	// this.indexKeyMap.put(deleteIndex, lastKey);
	// this.keyIndexMap.remove(key);
	// this.indexKeyMap.remove(lastIndex);
	// }
	// }

	// public K getRandom() {
	// if (this.size == 0) {
	// return null;
	// }
	// int randomIndex = (int) (Math.random() * this.size); // 0 ~ size -1
	// return this.indexKeyMap.get(randomIndex);
	// }

	// }

	public static class Pool<T> {
		private HashMap<Integer, T> indexMap = new HashMap<>();
		private HashMap<T, Integer> valueMap = new HashMap<>();

		public void insert(T key) {
			if (!valueMap.containsKey(key)) {
				indexMap.put(indexMap.size(), key);
				valueMap.put(key, valueMap.size());
			}
		}

		public void delete(T key) {
			if (!valueMap.containsKey(key)) {
				return;
			}
			int index = valueMap.get(key);
			T element = indexMap.get(indexMap.size() - 1);
			indexMap.put(index, element);
			indexMap.remove(indexMap.size() - 1);
			valueMap.put(element, index);
			valueMap.remove(key);
		}

		public T getRandom() {
			return indexMap.get((int) Math.random() * indexMap.size());
		}
	}

	public static void main(String[] args) {
		Pool<String> pool = new Pool<String>();
		pool.insert("zuo");
		pool.insert("cheng");
		pool.insert("yun");
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());

	}

}
