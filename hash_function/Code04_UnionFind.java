package hash_function;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code04_UnionFind {

	public static class Element<V> {
		public V value;

		public Element(V value) {
			this.value = value;
		}

	}

	public static class UnionFindSet<V> {
		public HashMap<V, Element<V>> elementMap;
		public HashMap<Element<V>, Element<V>> fatherMap;
		public HashMap<Element<V>, Integer> rankMap;

		public UnionFindSet(List<V> list) {
			elementMap = new HashMap<>();
			fatherMap = new HashMap<>();
			rankMap = new HashMap<>();
			for (V value : list) {
				Element<V> element = new Element<V>(value);
				elementMap.put(value, element);
				fatherMap.put(element, element);
				rankMap.put(element, 1);
			}
		}

		private Element<V> findHead(Element<V> element) {
			Stack<Element<V>> path = new Stack<>();
			while (element != fatherMap.get(element)) {
				path.push(element);
				element = fatherMap.get(element);
			}
			while (!path.isEmpty()) {
				fatherMap.put(path.pop(), element);
			}
			return element;
		}

		public boolean isSameSet(V a, V b) {
			if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
				return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
			}
			return false;
		}

		public void union(V a, V b) {
			if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
				Element<V> aF = findHead(elementMap.get(a));
				Element<V> bF = findHead(elementMap.get(b));
				if (aF != bF) {
					Element<V> big = rankMap.get(aF) >= rankMap.get(bF) ? aF : bF;
					Element<V> small = big == aF ? bF : aF;
					fatherMap.put(small, big);
					rankMap.put(big, rankMap.get(aF) + rankMap.get(bF));
					rankMap.remove(small);
				}
			}
		}

	}

	// public class UnionFindSet {

	// private class Element<T> {
	// private T value;
	// public Element<T> previous;

	// public Element(T value) {
	// this.value = value;
	// }

	// }

	// private Element<Integer> getHead(Element<Integer> element) {
	// Stack<Element<Integer>> sons = new Stack<>();

	// while (element.previous != element) {
	// sons.push(element);
	// element = element.previous;
	// }
	// while (!sons.isEmpty()) {
	// sons.pop().previous = element;
	// }
	// return element;
	// }

	// private boolean isSameSet(Element<Integer> e1, Element<Integer> e2) {
	// return getHead(e1) == getHead(e2);
	// }

	// private void union() {

	// }

	// }
}
