package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		if (top == -1) {
			return null;
		} else {
			return array[top];
		}
	}

	@Override
	public boolean isEmpty() {
		if (top == -1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if (top == array.length-1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element.equals(null) ) {
			throw new IllegalArgumentException("Elemento nulo.");
		}else if (!isFull()) {
			top++;
			array[top] = element;
		}else {
			throw new adt.stack.StackOverflowException();
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (top == -1) {
			throw new adt.stack.StackUnderflowException();
		}else {
			top --;
			return array[top+1];
		}
	}

}
