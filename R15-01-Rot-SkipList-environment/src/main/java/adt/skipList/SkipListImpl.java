package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve conectar
	 * todos os forward. Senao o ROOT eh inicializado com level=1 e o metodo deve
	 * conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	@Override
	public void insert(int key, T newValue, int height) {
		SkipListNode[] caminho = pesquisa(key);
		SkipListNode temp = caminho[0].forward[0];

		if (temp.getKey() == key) {
			temp.setValue(newValue);
		} else {
			SkipListNode newNode = new SkipListNode(key, height, newValue);

			for (int i = 0; i < height; i++) {
				newNode.forward[i] = caminho[i].forward[i];
				caminho[i].forward[i] = newNode;
			}
		}
	}

	private SkipListNode[] pesquisa(int key) {
		SkipListNode[] update = new SkipListNode[maxHeight];
		SkipListNode<T> temp = root;
		for (int i = maxHeight - 1; i >= 0; i--) {
			while (temp.forward[i].getKey() < key) {
				temp = temp.forward[i];
			}
			update[i] = temp;
		}
		return update;
	}

	@Override
	public void remove(int key) {
		SkipListNode[] caminho = pesquisa(key);
		SkipListNode temp = caminho[0].forward[0];

		if (temp.getKey() == key) {
			for (int i = 0; i < maxHeight; i++) {
				if (caminho[i].forward[i] != temp) {
					break;
				}
				caminho[i].forward[i] = temp.forward[i];

			}
		}
	}

	@Override
	public int height() {
		int h = 0;
		SkipListNode<T> temp = root;
		for (int i = maxHeight; i >= 0; i--) {
			if (!temp.forward[i].equals(NIL)) {
				h = temp.forward[i].height();
				break;
			}
		}

		return h;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> temp = root;
		for (int i = maxHeight - 1; i >= 0; i--) {
			while (temp.forward[i].getKey() < key) {
				temp = temp.forward[i];
			}
		}
		temp = temp.forward[0];
		if (temp.getKey() == key) {
			return temp;
		} else {
			return null;
		}
	}

	@Override
	public int size() {
		int size = 0;
		SkipListNode<T> temp = root;
		while (!temp.forward[0].equals(NIL)) {
			size++;
			temp = temp.forward[0];
		}

		return size;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		int tamanho = size() + 2;
		SkipListNode[] array = new SkipListNode[tamanho];
		SkipListNode<T> temp = root;
		for (int i = 0; i < tamanho; i++) {
			array[i] = temp;
			temp = temp.forward[0];
		}
		return array;
	}

}
