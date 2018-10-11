package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		if (data == null) {
			return true;
		} else {	
			return false;
		}
	}

	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		} else {
			return 1 + next.size();
		}
	}

	@Override
	public T search(T element) {
		if (isEmpty()) {
			return null;
		} else {
			if (data.equals(element)) {
				return data;
			} else {
				return next.search(element);
			}
		}
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			data = element;
			next = new RecursiveSingleLinkedListImpl<>();
		} else {
			next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (data.equals(element)) {
				data = next.getData();
				next = next.getNext();
			} else {
				next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[size()];
		toArrayRecursive(result, this, 0);
		return result;
	}

	private void toArrayRecursive(T[] result, RecursiveSingleLinkedListImpl<T> node, int i) {
		if (!node.isEmpty()){
			result[i] = node.getData();
			toArrayRecursive(result,node.next,i+1);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
