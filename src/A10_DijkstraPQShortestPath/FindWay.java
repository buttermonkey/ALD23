package A10_DijkstraPQShortestPath;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class FindWay {
	protected static final int NOT_VISITED = -1;

	protected Graph graph;
	protected int[] pred;
	
	public FindWay(Graph graph) {
		this.graph = graph;
		this.pred = new int[graph.numVertices()];
	}

	/**
	 * Liefert den Weg von (from) nach (to) als Liste zurück
	 * @param from Startknoten
	 * @param to Zielknoten
	 * @return Weg von Start nach Ziel oder null
	 */
	public List<Integer> findWay(int from, int to) {
		if (from >= graph.numVertices() || to >= graph.numVertices() || from < 0 || to < 0) {
			System.err.println("One ore both vertices not part of the graph: (" + from + ", " + to + ")"
					+ " (valid vertices: 0.." + (graph.numVertices() - 1) + ")");
			return Collections.emptyList();
		}

		initPathSearch();
		if (!calculatePath(from, to)) {
			return null;
		}
		return createWay(from, to);
	}

	/**
	 * Initialisierungsfunktion
	 */
	abstract protected void initPathSearch();

	/**
	 * Berechnungsfunktion für Weg von (from) nach (to)
	 */
	abstract protected boolean calculatePath(int from, int to);
	
	/**
	 * Weg von (to) nach (from) aus Vorgängerknoten rekonstruieren
	 * @param from Startknoten
	 * @param to Zielknoten
	 * @return Weg als Liste
	 */
	protected ArrayList<Integer> createWay(int from, int to) {
		if (!this.calculatePath(from, to))
			return new ArrayList<>();

		ArrayList<Integer> way = new ArrayList<Integer>();
		int v = to;
		while (v != NOT_VISITED) {
			way.add(0, v);
			v = pred[v];
		}
		return way;
	}
}
