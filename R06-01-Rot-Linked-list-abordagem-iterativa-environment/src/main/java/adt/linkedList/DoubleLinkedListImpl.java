package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		this.head = new DoubleLinkedListNode<T>();
		this.last = new DoubleLinkedListNode<T>();
	}

	public void insert(T element) {
		DoubleLinkedListNode<T> aux = new DoubleLinkedListNode<T>(element,new DoubleLinkedListNode<T>(),last);
		last.setNext(aux);
		if (last.isNIL()) {
			head = aux;
		}
		last = aux;
	}

	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>();
		newHead.setData(element);
		newHead.setNext(head);
		newHead.setPrevious(new DoubleLinkedListNode<>());
		((DoubleLinkedListNode<T>) head).setPrevious(newHead);
		if (head.isNIL()) {
			last = newHead;
		}
		head = newHead;
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
		while (auxHead != auxLast && (DoubleLinkedListNode<T>) auxHead.next != auxLast && auxHead.data != element
				&& auxLast.data != element) {
			auxHead = (DoubleLinkedListNode<T>) auxHead.next;
			auxLast = auxLast.previous;
		}
		if (auxHead.data == element) {
			return auxHead.getData();
		} else if (auxLast.data == element) {
			return auxLast.getData();
		} else {
			return null;
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
