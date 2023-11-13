package A09_ZyklenTiefensuche;

import A10_DijkstraPQShortestPath.WeightedEdge;

import java.util.List;
import java.util.Stack;

public class Zyklen {

	private Graph g;
	
	public Zyklen(Graph g) {
		this.g = g;
	}
	
	/**
	 * Retourniert einen Zyklus eines Graphen, sofern einer existiert
	 * @param g zu prüfender Graph
	 * @return Anzahl der Komponenten
	 */
	public List<Integer> getCycle() {
		for (int v = 0; v < g.numVertices(); v++) {
			List<Integer> cycle = getCycle(new Stack<>(), v);
			if (cycle != null)
				return cycle;
		}
		return null;
	}

	private List<Integer> getCycle(Stack<Integer> nodesVisited, int v) {
		if (nodesVisited.contains(v)) {
			nodesVisited.push(v);
			return nodesVisited.stream()
					.dropWhile(node -> node != v)
					.toList();
		}

		int previousNode = nodesVisited.isEmpty() ? -1 : nodesVisited.peek();
		nodesVisited.push(v);
		for (WeightedEdge edge : g.getEdges(v)) {
			if (!g.isDirected() && edge.to_vertex == previousNode)
				continue;

			List<Integer> cycle = getCycle(nodesVisited, edge.to_vertex);
			if (cycle != null)
				return cycle;
		}
		nodesVisited.pop();
		return null;
	}

}
