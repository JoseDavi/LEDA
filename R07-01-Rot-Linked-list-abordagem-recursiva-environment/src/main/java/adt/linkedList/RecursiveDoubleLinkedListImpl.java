package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		if (isEmpty()) {
			data = element;
			RecursiveDoubleLinkedListImpl<T> nil = new RecursiveDoubleLinkedListImpl<>();
			this.next = nil;
			nil.previous = this;
		} else {
			RecursiveDoubleLinkedListImpl<T> aux = new RecursiveDoubleLinkedListImpl<>(this.data, this.next, this);
			this.data = element;
			this.next = aux;
			this.previous = new RecursiveDoubleLinkedListImpl<>();
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			this.data = this.next.data;
			((DoubleLinkedList<T>) this.next).removeFirst();
		}
	}

	@Override
	public void removeLast() {
		if (isEmpty()) {
			if (previous != null) {
				previous.setData(null);
				previous.setNext(null);
				if (previous.getPrevious().isEmpty()) {
					previous.setPrevious(null);
				}
			}
		} else {
			((DoubleLinkedList<T>) next).removeLast();
		}
	}

	@Override
	public void remove(T element) {
		if (this.isEmpty()) {

		} else {
			if (this.data.equals(element)) {
				if (this.previous.isEmpty() && this.next.isEmpty()) {
					this.previous = null;
					this.next = null;
					this.data = null;
				} else {
					this.data = ((RecursiveDoubleLinkedListImpl<T>) this.next).data;
					this.next = ((RecursiveDoubleLinkedListImpl<T>) this.next).next;
					if (this.next != null) {
						((RecursiveDoubleLinkedListImpl<T>) this.next).previous = this;
					}
				}
			} else {
				this.next.remove(element);
			}
		}
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			data = element;
			RecursiveDoubleLinkedListImpl<T> nil = new RecursiveDoubleLinkedListImpl<>();
			next = nil;
			nil.previous = this;
			;
			if (previous == null) {
				previous = nil;

			}
		} else {
			next.insert(element);
		}
	}

	@Override
	public T search(T element) {
		T saida = null;
		if (this.isEmpty()) {

		} else {
			if (this.data.equals(element)) {
				saida = this.data;
			} else {
				saida = this.next.search(element);
			}
		}
		return saida;
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
