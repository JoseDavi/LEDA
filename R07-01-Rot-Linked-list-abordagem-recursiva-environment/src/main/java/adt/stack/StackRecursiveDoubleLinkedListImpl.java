package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		}else {
			top.insert(element);
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException();
		}else {
			T element = top();
			top.removeLast();
			return element;
		}
	}

	@Override
	public T top() {
		if (isEmpty()) {
			return null;
		}else {
			T[] array = top.toArray();
			T element = array[-1];
			return element;
		}
	}

	@Override
	public boolean isEmpty() {
		if (top.size() == 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if (top.size() == size) {
			return true;
		}else {
			return false;
		}
	}

}
