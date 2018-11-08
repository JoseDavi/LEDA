package adt.bst;

import java.util.ArrayList;
import java.util.List;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(root);
	}

	private int height(BSTNode<T> root) {
		if (root.isEmpty()) {
			return -1;
		}else {
			return Math.max(height((BSTNode<T>) root.getLeft()), height((BSTNode<T>) root.getRight())) + 1;
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, root);
	}

	private BSTNode<T> search(T element, BTNode<T> node) {
		if (node.isEmpty() || element.compareTo(node.getData()) == 0) {
			return (BSTNode<T>) node;
		}else if (element.compareTo(node.getData()) > 0) {
			return search(element, node.getRight());
		}else {
			return search(element, node.getLeft());
		}
	}

	@Override
	public void insert(T element) {
		insert(element, root, new BSTNode<T>());
	}

	private void insert(T element, BSTNode<T> node, BSTNode<T> parent) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<>());
			node.setRight(new BSTNode<>());
			node.setParent(parent);
		}else if (element.compareTo(node.getData()) > 0) {
			insert(element, (BSTNode<T>) node.getRight(), node);
		}else if (element.compareTo(node.getData()) < 0) {
			insert(element, (BSTNode<T>) node.getLeft(), node);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> maximo = node;
		if (maximo == null) {
			return null;
		}
		while (!maximo.getRight().isEmpty()) {
			maximo = (BSTNode<T>) maximo.getRight();
		}
		return maximo;
	}

	@Override
	public BSTNode<T> minimum() {
		return minimun(root);
	}

	private BSTNode<T> minimun(BSTNode<T> node) {
		BSTNode<T> minimo = node;
		if (minimo.isEmpty()) {
			return null;
		}
		while (!minimo.getLeft().isEmpty()) {
			minimo = (BSTNode<T>) minimo.getLeft();
		}
		return minimo;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		if (node.isEmpty()) {
			return null;
		}else if (node.getRight() != null) {
			return minimun(node);
		}
		
		while (!node.isEmpty() && node.getParent().getRight() == node) {
			node = (BSTNode<T>) node.getParent();
			
		}
		return (BSTNode<T>) node.getParent();

	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		if (node.isEmpty()) {
			return null;
		}else if (node.getLeft() != null) {
			return maximum(node);
		}
		
		while (!node.isEmpty() && node.getParent().getLeft() == node) {
			node = (BSTNode<T>) node.getParent();
			
		}
		if (node == this.root) {
			return null;
		}
		
		return (BSTNode<T>) node.getParent();
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] preOrder() {
		List<T> saida = new ArrayList<>(size());
		preOrder(saida, root);
		return saida.toArray((T[]) new Comparable[size()]);
	}

	private void preOrder(List<T> saida, BSTNode<T> root) {
		if (!root.isEmpty()) {
			saida.add(root.getData());
			preOrder(saida, (BSTNode<T>) root.getLeft());
			preOrder(saida, (BSTNode<T>) root.getRight());
		}
		
	}

	@Override
	public T[] order() {
		List<T> saida = new ArrayList<>(size());
		order(saida, root);
		return saida.toArray((T[]) new Comparable[size()]);
	}

	private void order(List<T> saida, BSTNode<T> root2) {
		if (!root.isEmpty()) {
			preOrder(saida, (BSTNode<T>) root.getLeft());
			saida.add(root.getData());
			preOrder(saida, (BSTNode<T>) root.getRight());
		}
		
	}

	@Override
	public T[] postOrder() {
		List<T> saida = new ArrayList<>(size());
		posOrder(saida, root);
		return saida.toArray((T[]) new Comparable[size()]);
	}

	private void posOrder(List<T> saida, BSTNode<T> root2) {
		if (!root.isEmpty()) {
			preOrder(saida, (BSTNode<T>) root.getLeft());
			preOrder(saida, (BSTNode<T>) root.getRight());
			saida.add(root.getData());
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
