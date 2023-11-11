package A05_Breitensuche;

import java.util.*;

public class Breitensuche extends BaseTree<Integer> {

	@Override
	protected int compare(Integer a, Integer b) {
		return a.compareTo(b);
	}

	/**
	 * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück
	 * @param start Startknoten für Teilbaum
	 * @return Liste der Knoten in Breitenfolge
	 */
	public List<Integer> getBreadthFirstOrder(Node<Integer> start) {
		List<Integer> result = new ArrayList<>();
		Queue<Node<Integer>> backlog = new LinkedList<>();
		backlog.add(start);
		while (!backlog.isEmpty()) {
			Node<Integer> next = backlog.poll();
			if (next != null) {
				result.add(next.getValue());
				backlog.add(next.getLeft());
				backlog.add(next.getRight());
			}
		}
		return result;
	}
//	public List<Integer> getBreadthFirstOrder(Node<Integer> start) {
//		List<Integer> result = new ArrayList<>();
//		int level = 1;
//		List<Integer> nodesAtLevel = getBreadthFirstOrderForLevel(start, level);
//		while (nodesAtLevel.size() > 0) {
//			result.addAll(nodesAtLevel);
//			level++;
//			nodesAtLevel = getBreadthFirstOrderForLevel(start, level);
//		}
//		return result;
//	}

	/**
	 * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück,
	 * allerdings nur jene Knoten, die in der angegebenen Ebene liegen (Start hat Ebene=1)
	 * @param start Startknoten für Teilbaum
	 * @param level Nur Knoten dieser Ebene ausgeben
	 * @return Liste aller Knoten
	 */
	public List<Integer> getBreadthFirstOrderForLevel(Node<Integer> start, int level) {
		if (start == null || level < 1)
			return Collections.emptyList();
		if (level == 1)
			return List.of(start.getValue());
		List<Integer> nodesAtLevel = new ArrayList<>();
		nodesAtLevel.addAll(getBreadthFirstOrderForLevel(start.getLeft(), level - 1));
		nodesAtLevel.addAll(getBreadthFirstOrderForLevel(start.getRight(), level - 1));
		return nodesAtLevel;
	}

}
