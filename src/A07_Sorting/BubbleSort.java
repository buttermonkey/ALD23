package A07_Sorting;


public class BubbleSort implements PersonenSort {

	/**
	 * Sortier-Funktion
	 */
	public void sort(Person[] personen) {
		for (int upperBound = personen.length - 1; upperBound > 0; upperBound--)
			for (int i = 0; i < upperBound; i++)
				if (personen[i].compareTo(personen[i+1]) > 0) {
					Person temp = personen[i];
					personen[i] = personen[i+1];
					personen[i+1] = temp;
				}
	}
	
}
