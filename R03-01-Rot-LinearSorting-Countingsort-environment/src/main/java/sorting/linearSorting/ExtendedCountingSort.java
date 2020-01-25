package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (rightIndex - leftIndex <= 0) {
			return;
		}
		int min = array[0];
		int max = array[0];
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
			}
			if (array[i] > max) {
				max = array[i];
			}
		}
		
		int[] aux =  new int[max-min+1];
		Integer[] resposta = new Integer[array.length];
		
		for (int i = 0; i < array.length; i++) {
			aux[array[i]-min] += 1;
		}
	
		for (int i = 1; i < aux.length; i++) {
			aux[i] = aux[i-1] + aux[i];
		}
		
		for (int i = array.length-1; i >= 0; i--) {
			aux[array[i]-min] -= 1;
			resposta[aux[array[i]-min]] = array[i];
		}
	
		System.arraycopy(resposta, leftIndex, array, leftIndex, resposta.length);
		
	}
}
