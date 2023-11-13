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

		int pivot = partition(personen, left, right);

		sort(personen, left, pivot - 1);
		sort(personen, pivot + 1, right);
	}

	private int partition(Person[] personen, int left, int right) {
		selectPivot(personen, left, right);
		int itemFromLeft = getItemFromLeft(personen, left, right, right);
		int itemFromRight = getItemFromRight(personen, left, right, right);
		while (itemFromLeft < itemFromRight) {
			swap(personen, itemFromLeft, itemFromRight);
			itemFromLeft = getItemFromLeft(personen, itemFromLeft, itemFromRight, right);
			itemFromRight = getItemFromRight(personen, itemFromLeft, itemFromRight, right);
		}
		swap(personen, itemFromLeft, right);
		return itemFromLeft;
	}

	// Select pivot and place it on the right most side
	private static void selectPivot(Person[] personen, int left, int right) {
		int pivot = (left + right) / 2;
		swap(personen, pivot, right);
	}

	private static void swap(Person[] personen, int idx1, int idx2) {
		Person temp = personen[idx1];
		personen[idx1] = personen[idx2];
		personen[idx2] = temp;
	}

	private int getItemFromLeft(Person[] personen, int left, int right, int pivot) {
		for (int i = left; i < right; i++)
			if (personen[i].compareTo(personen[pivot]) > 0)
				return i;
		return right;
	}

	private int getItemFromRight(Person[] personen, int left, int right, int pivot) {
		for (int i = right - 1; i > left; i--)
			if (personen[i].compareTo(personen[pivot]) < 0)
				return i;
		return -1;
	}
}