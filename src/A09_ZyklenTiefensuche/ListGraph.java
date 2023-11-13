package A09_ZyklenTiefensuche;
import A10_DijkstraPQShortestPath.WeightedEdge;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

public class ListGraph implements Graph {

	private ArrayList<WeightedEdge>[] graph;
	private int numVertices;
	private boolean directed;
	
	@SuppressWarnings("unchecked")
	public ListGraph(int numVertices, boolean directed) {
		graph = new ArrayList[numVertices];
		for (int i=0; i < numVertices; i++)
			graph[i] = new ArrayList<WeightedEdge>();
		this.numVertices = numVertices;
		this.directed = directed;
	}
	
	public int numVertices() {
		return numVertices;
	}
	
	public boolean isDirected() {
		return directed;
	}

	public boolean hasEdge(int u, int v) {
		WeightedEdge pv = findEdge(u, v);
		return pv != null;
	}

	public int getEdgeWeight(int u, int v) {
		WeightedEdge pv = findEdge(u, v);
		return pv.weight;
	}

	public void addEdge(int u, int v) {
		addEdge(u, v, 1);
	}
	
	public void addEdge(int u, int v, int weight) {
		graph[u].add(new WeightedEdge(u, v, weight));
		if (!directed) {
			graph[v].add(new WeightedEdge(v, u, weight));
		}
	}
	
	private WeightedEdge findEdge(int u, int v) {
		for (WeightedEdge we: graph[u]) {
			if (we.to_vertex == v) {
				return we;
			}
		}
		return null;
	}

	public void removeEdge(int u, int v) {
		removeDirectedEdge(u, v);
		if (!isDirected())
			removeDirectedEdge(v, u);
	}

	private void removeDirectedEdge(int u, int v) {
		graph[u] = graph[u].stream()
				.filter(edge -> edge.to_vertex != v)
				.collect(toCollection(ArrayList::new));
	}

	public List<WeightedEdge> getEdges(int v) {
		return graph[v];
	}
}
