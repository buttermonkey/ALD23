package A10_DijkstraPQShortestPath;


public class DijkstraPQShortestPath extends FindWay {
	private int[] dist;

	public DijkstraPQShortestPath(Graph graph) {
		super(graph);
	}

	/**
	 * Startentfernung initialisieren
	 * 
	 * @param from
	 *            Startknoten
	 */
	protected void initPathSearch() {
		int numv = graph.numVertices();
		dist = new int[numv];
		for (int i = 0; i < numv; i++) {
			dist[i] = Integer.MAX_VALUE; // Summen im Graph dürfen nie mehr ergeben
			pred[i] = NOT_VISITED;
		}
	}

	/**
	 * Berechnet *alle* kürzesten Wege ausgehend vom Startknoten Setzt dist[]-
	 * und pred[]-Arrays, kein Rückgabewert
	 * 
	 * @param from
	 *            Startknoten
	 */
	protected boolean calculatePath(int from, int to) {
		VertexHeap heap = new VertexHeap(graph.numVertices());
		heap.insert(new Vertex(from, 0));
		dist[from] = 0;

		while (!heap.isEmpty()) {
			Vertex v = heap.remove();
			for (WeightedEdge e : graph.getEdges(v.vertex)) {
				int currentPathDistance = v.cost + e.weight;
				if (dist[e.to_vertex] > currentPathDistance) {
					if (pred[e.to_vertex] != NOT_VISITED)
						heap.setCost(e.to_vertex, currentPathDistance);
					else
						heap.insert(new Vertex(e.to_vertex, currentPathDistance));
					dist[e.to_vertex] = currentPathDistance;
					pred[e.to_vertex] = e.from_vertex;
				}
			}
		}

		return pred[to] != NOT_VISITED;
	}
}
