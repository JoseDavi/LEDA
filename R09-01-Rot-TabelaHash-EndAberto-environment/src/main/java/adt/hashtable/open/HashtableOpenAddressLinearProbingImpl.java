package adt.hashtable.open;

import java.util.Queue;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

   public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
      super(size);
      hashFunction = new HashFunctionLinearProbing<T>(size, method);
      this.initiateInternalTable(size);
   }

   @Override
   public void insert(T element) {
      if (!this.isFull() && element != null) {
         int hash = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, 0);
         int p = 0;
         boolean achou = false;
         while (!achou) {
            if (this.table[hash] == null || this.table[hash].equals(deletedElement)) {
               this.table[hash] = element;
               achou = true;
               this.elements++;
            } else {
               this.COLLISIONS++;
               p++;
               hash = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, p);
               ;
            }
         }
      } else if (this.isFull()) {
         throw new HashtableOverflowException();
      }
   }

   @Override
   public void remove(T element) {
      if (!isEmpty() && element != null) {
         int hash = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, 0);
         int p = 0;
         boolean achou = false;
         while (this.table[hash] != null && p < capacity() && !achou) {
            if (this.table[hash].equals(element)) {
               this.table[hash] = deletedElement;
               achou = true;
               this.elements--;
            } else {
               p++;
               hash = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, p);
            }
         }
      }
   }

   @SuppressWarnings("unchecked")
   @Override
   public T search(T element) {
      T saida = null;
      if (element != null) {
         int hash = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, 0);
         int p = 0;
         boolean achou = false;
         while (this.table[hash] != null && p < capacity() && !achou) {
            if (this.table[hash].equals(element)) {
               saida = (T) this.table[hash];
               achou = true;
            } else {
               p++;
               hash = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, p);
            }
         }
      }
      return saida;
   }

   @Override
   public int indexOf(T element) {
      int saida = -1;
      if (element != null) {
         int hash = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, 0);
         int p = 0;
         boolean achou = false;
         while (this.table[hash] != null && p < capacity() && !achou) {
            if (this.table[hash].equals(element)) {
               saida = hash;
               achou = true;
            } else {
               p++;
               hash = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, p);
            }
         }
      }
      return saida;
   }

}
