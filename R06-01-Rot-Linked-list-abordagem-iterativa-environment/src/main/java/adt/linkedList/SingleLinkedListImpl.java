package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		if (head.isNIL()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> aux = head;
		while (!aux.isNIL()) {
			size++;
			aux = aux.next;
		}
		return size;
	}

	@Override
	public T search(T element) {
		T result = null;
		SingleLinkedListNode<T> aux = head;
		while (!aux.isNIL() && result == null) {
			if (aux.data.equals(element)) {
				result = aux.data;
			}
			aux = aux.next;
		}
		return result;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> aux = head;
		if (head.isNIL()) {
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, head);
			head = newNode;
		} else {
			while (!aux.next.isNIL()) {
				aux = aux.next;
			}
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, aux.next);
			aux.next = newNode;
		}
	}

	@Override
	public void remove(T element) {
		SingleLinkedListNode<T> previus = head;
		SingleLinkedListNode<T> aux = head.next;
		if (!head.isNIL()) {
			if (head.getData().equals(element)) {
				head = head.next;
			} else {
				while (!aux.isNIL() && !(aux.getData().equals(element))) {
					previus = aux;
					aux = aux.next;
				}
				if (!aux.isNIL()) {
					previus.setNext(aux.next);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[size()];
		SingleLinkedListNode<T> aux = head;
		int cont  = 0;
		while (!aux.isNIL()) {
			result[cont] = aux.getData();
			aux = aux.next;
			cont++;
		}
		return result;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
