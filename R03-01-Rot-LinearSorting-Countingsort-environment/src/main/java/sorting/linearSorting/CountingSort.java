package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

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

      int[] aux = new int[max - min + 1];
      Integer[] resposta = new Integer[array.length];

      for (int i = 0; i < array.length; i++) {
         aux[array[i] - min] += 1;
      }

      for (int i = 1; i < aux.length; i++) {
         aux[i] = aux[i - 1] + aux[i];
      }

      for (int i = array.length - 1; i >= 0; i--) {
         aux[array[i] - min] -= 1;
         resposta[aux[array[i] - min]] = array[i];
      }

      System.arraycopy(resposta, leftIndex, array, leftIndex, resposta.length);

   }

}
