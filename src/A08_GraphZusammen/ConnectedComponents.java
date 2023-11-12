package A08_GraphZusammen;

import A10_DijkstraPQShortestPath.WeightedEdge;

import java.util.List;

public class ConnectedComponents {
	
	/**
	 * Retourniert die Anzahl der zusammenh�ngenden Komponenten eines Graphen
	 * @param g zu pr�fender Graph
	 * @return Anzahl der Komponenten
	 */
	public int getNumberOfComponents(Graph g) {
		int[] nodeLabels = new int[g.numVertices()];
		int components=0;
		
		for (int v = 0; v < nodeLabels.length; v++) {
			if (nodeLabels[v] == 0) {
				components += 1;
				labelComponent(g, nodeLabels, v, components);
			}
		}
		
		return components;
	}

	private void labelComponent(Graph g, int[] nodeLabels, int v, int component) {
		if (nodeLabels[v] != 0)
			return;
		nodeLabels[v] = component;
		g.getEdges(v).forEach(edge -> labelComponent(g, nodeLabels, edge.to_vertex, component));
	}

}
