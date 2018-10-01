<<<<<<< HEAD
package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		double fator = 1.25;
		int gap = (int) (array.length/fator);
		int i = leftIndex;
		
		while (gap >= 1.0) {
			if ((gap + i) <= rightIndex) {
				if (array[i].compareTo(array[gap]) > 0) {
					Util.swap(array, i, gap);
					i += 1;
				}
			}else {
				gap = (int) (gap/fator);
			}
		}
		
		for (int j = leftIndex; j < rightIndex; j++) {
			if (array[j].compareTo(array[j+1]) > 0) {
				Util.swap(array, j, j+1);
			}
		}
	}
}
=======
package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		double fator = 1.25;
		int gap = (int) (array.length/fator);
		int i = leftIndex;
		
		while (gap > 1) {
			if (array[i].compareTo(array[gap+i]) > 0) {
				Util.swap(array, i, gap+i);
			}
			i += 1;
			if ((gap + i) > rightIndex) {	
				gap = (int) (gap/fator);
				i = leftIndex;
			}
		}
		
		for (int j = leftIndex; j < rightIndex; j++) {
			if (array[j].compareTo(array[j+1]) > 0) {
				Util.swap(array, j, j+1);
			}
		}
	}
}
>>>>>>> cc0403564f1fa433ef4be69f591e87f5ddb1aff0
