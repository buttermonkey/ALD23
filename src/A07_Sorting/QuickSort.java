package A07_Sorting;


public class QuickSort implements PersonenSort {

	/**
	 * Sortier-Funktion
	 */
	public void sort(Person[] personen) {
		sort(personen, 0, personen.length - 1);
	}

	private void sort(Person[] personen, int left, int right) {
		// Nothing to do
		if (left >= right)
			return;

		// If there are only two values to consider, we can take a huge shortcut
		if (right - left == 1) {
			if (personen[left].compareTo(personen[right]) > 0)
				swap(personen, left, right);
			return;
		}

		int itemFromLeft = getItemFromLeft(personen, left, right);
		int itemFromRight = getItemFromRight(personen, left, right);
		while (itemFromLeft < itemFromRight) {
			swap(personen, itemFromLeft, itemFromRight);
			itemFromLeft = getItemFromLeft(personen, left, right);
			itemFromRight = getItemFromRight(personen, left, right);
		}
		swap(personen, itemFromLeft, right);

		sort(personen, left, itemFromLeft - 1);
		sort(personen, itemFromLeft + 1, right);
	}

	private static void swap(Person[] personen, int idx1, int idx2) {
		Person temp = personen[idx1];
		personen[idx1] = personen[idx2];
		personen[idx2] = temp;
	}

	// The pivot is the element at the right most side
	// The pivot is the element at the right most side
	private int getItemFromLeft(Person[] personen, int left, int right) {
		for (int i = left; i < right; i++)
			if (personen[i].compareTo(personen[right]) >= 0)
				return i;
		return right;
	}

	private int getItemFromRight(Person[] personen, int left, int right) {
		for (int i = right - 1; i >= left; i--)
			if (personen[i].compareTo(personen[right]) < 0)
				return i;
		return -1;
	}
}