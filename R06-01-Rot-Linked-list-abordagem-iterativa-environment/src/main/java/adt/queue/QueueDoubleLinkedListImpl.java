package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		}else {
			list.insert(element);
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}else {
			T element = ((DoubleLinkedListImpl<T>) list).getLast().getData();
			list.removeFirst();
			return element;
		}
	}

	@Override
	public T head() {
		T element = ((DoubleLinkedListImpl<T>) list).getHead().getData();
		return element;
	}

	@Override
	public boolean isEmpty() {
		if (list.size() == 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if (list.size() == size) {
			return true;
		}else {
			return false;
		}
	}

}
