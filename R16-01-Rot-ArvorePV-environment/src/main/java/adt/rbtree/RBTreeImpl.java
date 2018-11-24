package adt.rbtree;

import java.util.ArrayList;
import java.util.List;

import adt.bst.BSTImpl;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
		implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		int size = 0;
		if (!this.root.isEmpty()) {
			RBNode<T> node = (RBNode<T>) this.root;
			while (!node.isEmpty()) {
				if (node.getColour() == Colour.BLACK) {
					size += 1;
				}
				node = (RBNode<T>) node.getRight();
			}
		}
		return size;
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour()
				&& verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return verifyChildrenRedNodes((RBNode<T>) this.root);
	}

	private boolean verifyChildrenRedNodes(RBNode<T> node) {
		boolean saida = true;
		if (!node.isEmpty()) {
			if (node.getColour() == Colour.RED) {
				RBNode<T> left = (RBNode<T>) node.getLeft();
				RBNode<T> right = (RBNode<T>) node.getRight();
				if (left.getColour() == Colour.RED || right.getColour() == Colour.RED) {
					saida = false;
				}
			}
			saida = verifyChildrenRedNodes((RBNode<T>) node.getLeft()) && verifyChildrenRedNodes((RBNode<T>) node.getRight());
		}
		return saida;
	}

	/**
	 * Verifies the black-height property from the root.
	 */
	private boolean verifyBlackHeight() {
		int left = verifyBlackHeight((RBNode<T>) this.root.getLeft(), 0);
		int right = verifyBlackHeight((RBNode<T>) this.root.getLeft(), 0);
		if (left == right) {
			return true;
		}else {
			return false;
		}
	}

	private int verifyBlackHeight(RBNode<T> node, int i) {
		if (node != null && node.isEmpty()) {
			if (node.getColour() == Colour.BLACK) {
				i += 1;
			}
			return Math.max(verifyBlackHeight((RBNode<T>) node.getLeft(), i),
							verifyBlackHeight((RBNode<T>) node.getRight(), i));
		}
		return i + 1;
	}

	@Override
	public void insert(T value) {
		RBNode<T> node = this.insert((RBNode<T>) this.root, value, new RBNode<>());
		node.setColour(Colour.RED);
		this.fixUpCase1(node);
	}

	private RBNode<T> insert(RBNode<T> node, T value, RBNode parent) {
		if (node.isEmpty()) {
			node.setData(value);
			node.setLeft(new RBNode<T>());
			node.setRight(new RBNode<T>());
			node.setParent(parent);
			return node;
		} else if (value.compareTo(node.getData()) < 0) {
			return this.insert((RBNode<T>) node.getLeft(), value, node);
		} else if (value.compareTo(node.getData()) > 0) {
			return this.insert((RBNode<T>) node.getRight(), value, node);
		}
		return null;
	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		List<RBNode<T>> list = new ArrayList<>();
		this.rbPreOrder((RBNode<T>) this.root, list);
		RBNode<T>[] array = new RBNode[list.size()];
		return list.toArray(array);
	}

	private void rbPreOrder(RBNode<T> node, List<RBNode<T>> list) {
		if (!node.isEmpty()) {
			list.add(node);
			rbPreOrder((RBNode<T>) node.getLeft(), list);
			rbPreOrder((RBNode<T>) node.getRight(), list);
		}
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (this.root.equals(node)) {
			node.setColour(Colour.BLACK);
		} else {
			this.fixUpCase2(node);
		}
	}

	protected void fixUpCase2(RBNode<T> node) {
		if (((RBNode<T>) node.getParent()).getColour() == Colour.BLACK) {
			// OK
		} else {
			this.fixUpCase3(node);
		}
	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grand = (RBNode<T>) parent.getParent();
		RBNode<T> uncle = null;
		if (grand.getLeft().equals(parent)) {
			uncle = (RBNode<T>) grand.getRight();
		} else {
			uncle = (RBNode<T>) grand.getLeft();
		}
		if (uncle.getColour() == Colour.RED) {
			parent.setColour(Colour.BLACK);
			uncle.setColour(Colour.BLACK);
			grand.setColour(Colour.RED);
			this.fixUpCase1(grand);
		} else {
			this.fixUpCase4(node);
		}
	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> next = node;
		if (isRightChild(node) && isLeftChild((RBNode<T>) node.getParent())) {
			node.getParent().setLeft(Util.leftRotation(node));
			next = (RBNode<T>) node.getLeft();
		} else {
			node.getParent().setRight(Util.rightRotation(node));
			next = (RBNode<T>) node.getRight();
		}
		this.fixUpCase5(next);
	}

	private boolean isLeftChild(RBNode<T> node) {
		RBNode<T> parent = null;
		if (node.getParent() != null && !node.getParent().isEmpty()) {
			parent = (RBNode<T>) node.getParent();
		}
		return parent.getLeft().equals(node);
	}

	private boolean isRightChild(RBNode<T> node) {
		RBNode<T> parent = null;
		if (node.getParent() != null && !node.getParent().isEmpty()) {
			parent = (RBNode<T>) node.getParent();
		}
		return parent.getRight().equals(node);
	}

	protected void fixUpCase5(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grand = (RBNode<T>) parent.getParent();
		parent.setColour(Colour.BLACK);
		grand.setColour(Colour.RED);
		if (this.isLeftChild(node)) {
			grand.setLeft(Util.rightRotation(grand));
		} else {
			grand.setRight(Util.leftRotation(grand));
		}
	}
}
