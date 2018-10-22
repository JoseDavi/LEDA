package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (!this.isFull() && element != null) {
			int hash = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, 0);
			int p = 0;
			boolean achou = false;
			while (!achou) {
				if (this.table[hash] == null || this.table[hash].equals(deletedElement)) {
					this.table[hash] = element;
					achou =  true;
					this.elements++;
				}else {
					this.COLLISIONS++;
					p++;
					hash = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, p);;
				}
			}
		}else if (this.isFull()) {
			throw new HashtableOverflowException();
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			int hash = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, 0);
			int p = 0;
			boolean achou = false;
			while (this.table[hash] != null && p < capacity() && !achou) {
				if (this.table[hash].equals(element)) {
					this.table[hash] = deletedElement;
					achou = true;
					this.elements--;
				}else {
					p++;
					hash = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, p);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T search(T element) {
		T saida = null;
		if (element != null) {
			int hash = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, 0);
			int p = 0;
			boolean achou = false;
			while (this.table[hash] != null && p < capacity() && !achou) {
				if (this.table[hash].equals(element)) {
					saida = (T) this.table[hash];
					achou = true;
				}else {
					p++;
					hash = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, p);
				}
			}
		}
		return saida;
	}

	@Override
	public int indexOf(T element) {
		int saida = -1;
		if (element != null) {
			int hash = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, 0);
			int p = 0;
			boolean achou = false;
			while (this.table[hash] != null && p < capacity() && !achou) {
				if (this.table[hash].equals(element)) {
					saida = hash;
					achou = true;
				}else {
					p++;
					hash = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, p);
				}
			}
		}
		return saida;
	}
}
