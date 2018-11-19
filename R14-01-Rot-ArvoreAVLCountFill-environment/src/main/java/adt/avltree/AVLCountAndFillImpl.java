package adt.avltree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import adt.bst.BSTNode;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {

	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	protected void rebalance(BSTNode<T> node) {
		int height = calculateBalance(node);
		if (height > 1) {
			if (calculateBalance((BSTNode<T>) node.getRight()) < 0) {
				rightRotation((BSTNode<T>) node.getRight());
				leftRotation(node);
				this.RLcounter++;
			} else {
				leftRotation(node);
				this.RRcounter++;
			}
		} else if (height < -1) {
			if (calculateBalance((BSTNode<T>) node.getLeft()) > 0) {
				leftRotation((BSTNode<T>) node.getLeft());
				rightRotation(node);
				this.LRcounter++;
			} else {
				rightRotation(node);
				this.LLcounter++;
			}
		}
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		if (array != null) {
			T[] preOrder = this.preOrder();
			List<T> temp = new ArrayList<>();
			Collections.addAll(temp, array);
			Collections.addAll(temp, preOrder);
			Collections.sort(temp);
			removeRepeated(temp);
			this.root = new BSTNode<>();
			T[] add = getArrayInsert((T[]) temp.toArray(new Comparable[temp.size()]));
			for (int i = 0; i < add.length; i++) {
				this.insert(add[i]);
			}
		}
	}
	
	private T[] getArrayInsert(T[] array) {
		T[] saida = (T[]) new Comparable[array.length];
		ArrayList<ArrayList<T>> m = new ArrayList<>();
		m.add(new ArrayList<T>(Arrays.asList(array)));
		int i = 0;
		while (i < array.length) {
			int middle = m.get(i).size() / 2;
			saida[i] = m.get(i).get(middle);
			m.add(newArrayList(m.get(i), 0, middle));
			m.add(newArrayList(m.get(i), middle + 1, m.get(i).size()));
			i += 1;
		}
		return saida;
	}
	
	private ArrayList<T> newArrayList(ArrayList<T> array, int a, int f) {
		ArrayList<T> aux = new ArrayList<>();
		for (int i = a; i < f; i++) {
			aux.add(array.get(i));
		}
		return aux;
	}
	
	private void removeRepeated(List<T> list) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size() && list.get(i).equals(list.get(j)); j++) {
				list.remove(list.get(j));
			}
		}
	}

}
