package A06_Tiefensuche;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import A05_Breitensuche.BaseTree;
import A05_Breitensuche.Node;

public class Tiefensuche extends BaseTree<Film> {

	@Override
	/**
	 * Sortierkriterium im Baum: Länge des Films
	 */
	protected int compare(Film a, Film b) {
		return Double.compare(a.getLänge(), b.getLänge());
	}

	/**
	 * Retourniert die Titelliste der Film-Knoten des Teilbaums in symmetrischer Folge (engl. in-order, d.h. links-Knoten-rechts)
	 * @param node Wurzelknoten des Teilbaums
	 * @return Liste der Titel in symmetrischer Reihenfolge
	 */
	public List<String> getNodesInOrder(Node<Film> node) {
		if (node == null)
			return Collections.emptyList();
		List<String> result = new ArrayList<>();
		result.addAll(getNodesInOrder(node.getLeft()));
		result.add(node.getValue().getTitel());
		result.addAll(getNodesInOrder(node.getRight()));
		return result;
	}
	
	/**
	 * Retourniert Titelliste jener Filme, deren Länge zwischen min und max liegt, in Hauptreihenfolge (engl. pre-order, d.h. Knoten-links-rechts)
	 * @param min Minimale Länge des Spielfilms
	 * @param max Maximale Länge des Spielfilms
	 * @return Liste der Filmtitel in Hauptreihenfolge
	 */
	public List<String> getMinMaxPreOrder(double min, double max) {
		return getMinMaxPreOrder(root, min, max);
	}

	private List<String> getMinMaxPreOrder(Node<Film> w, double min, double max) {
		if (w == null)
			return Collections.emptyList();
		List<String> result = new ArrayList<>();
		if (w.getValue().getLänge() >= min && w.getValue().getLänge() <= max)
			result.add(w.getValue().getTitel());
		if (min < w.getValue().getLänge())
			result.addAll(getMinMaxPreOrder(w.getLeft(), min, max));
		if (max > w.getValue().getLänge())
			result.addAll(getMinMaxPreOrder(w.getRight(), min, max));
		return result;
	}

}
