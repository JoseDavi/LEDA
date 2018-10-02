package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	public DoubleLinkedListImpl() {
		head = new DoubleLinkedListNode<T>();
		last = new DoubleLinkedListNode<T>();
	}

	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) head;
		DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, aux.previous, aux.previous);
		if (head.isNIL()) {
			last = newNode;
		}
		head = newNode;
	}

	@Override
	public void removeFirst() {
		if (!head.isNIL()) {
			head = head.next;
			DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) head;
			aux.previous = new DoubleLinkedListNode<>();
			head = aux;
			if (head.isNIL()) {
				last = (DoubleLinkedListNode<T>) head;
			}
		}
	}

	@Override
	public void removeLast() {
		if (!last.isNIL()) {
			last = last.previous;
			if (last.isNIL()) {
				head = last;
			}
			last.next = new DoubleLinkedListNode<T>();
		}
	}
	
	public T search(T element) {
		DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) head;
		DoubleLinkedListNode<T> auxLast = last;
		while (auxHead != auxLast && (DoubleLinkedListNode<T>)auxHead.next != auxLast && auxHead.data != element && auxLast.data != element) {
			auxHead = (DoubleLinkedListNode<T>) auxHead.next;
			auxLast = auxLast.previous;
		}
		if (auxHead.data == element) {
			return auxHead.getData();
		}else if (auxLast.data == element) {
			return auxLast.getData();
		}else {
			return null;
		}
	}
	
	public void insert(T element) {
		DoubleLinkedListNode<T> aux = last;
		DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, (DoubleLinkedListNode<T>) aux.next, aux.previous);
		if (last.isNIL()) {
			head = newNode;
		}
		last = newNode;
	}
	
	public void remove(T element) {
		
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
