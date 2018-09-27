package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		}else if (head == -1){
			head++;
			tail++;
			array[tail] = element;
			elements++;
		}else {
			tail++;
			array[tail] = element;
			elements++;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}else {
			T temp = array[tail];
			tail--;
			elements--;
			return temp;
		}
	}

	@Override
	public T head() {
		if (head == -1) {
			return null;
		} else {
			return array[head];
		}
	}

	@Override
	public boolean isEmpty() {
		if (elements == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if (elements == array.length) {
			return true;
		} else {
			return false;
		}
	}

}
