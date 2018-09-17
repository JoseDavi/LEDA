package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			
			int pivot = 1;
			
			while (pivot+1 <= rightIndex) {
				if (pivot < leftIndex) {
					pivot += 2;
				}
				if (array[pivot].compareTo(array[pivot+1]) > 0) {
					Util.swap(array, pivot, pivot+1);
					pivot -= 1;
				}else {
					pivot +=1;
				}
			}
		}
	}
}
