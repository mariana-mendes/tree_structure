package adt.splaytree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements SplayTree<T> {

	private void splay(BSTNode<T> node) {
		if (node.getParent() == null)
			return;

		if (node.getParent().getParent() == null) {
			if (leftChild(node)) {
				rightRotation((BSTNode<T>) node.getParent());
			} else {
				leftRotation((BSTNode<T>) node.getParent());
			}

			// ZIG_ZIG
		} else if (inLeft(node)) {
			rightRotation((BSTNode<T>) node.getParent().getParent());
			rightRotation((BSTNode<T>) node.getParent());
		} else if (inRight(node)) {
			leftRotation((BSTNode<T>) node.getParent().getParent());
			leftRotation((BSTNode<T>) node.getParent());

			// ZIG_ZAG
		} else if (zigZagLeft(node)) {
			leftRotation((BSTNode<T>) node.getParent());
		} else {
			rightRotation((BSTNode<T>) node.getParent());
		}

		splay(node);

	}

	public void insert(T element) {
		if (element != null) {
			super.insert(element);
			this.splay(super.search(element));
		}
	}

	public BSTNode<T> search(T element) {
		BSTNode<T> searching = super.search(element);
		if (searching.isEmpty()) {
			this.splay((BSTNode<T>) searching.getParent());
		} else {
			this.splay(searching);
		}

		return searching;
	}

	public void remove(T element) {
		BSTNode<T> node = super.search(element);
		if (node.isEmpty()) {
			splay((BSTNode<T>) node.getParent());
		} else {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			super.remove(node);
			this.splay(parent);
		}

	}

	private void rightRotation(BSTNode<T> node) {
		BSTNode<T> no = Util.rightRotation(node);
		if (no.getParent() == null) {
			this.root = no;
		}
	}

	private void leftRotation(BSTNode<T> node) {
		BSTNode<T> no = Util.leftRotation(node);
		if (no.getParent() == null) {
			this.root = no;
		}
	}

	private boolean leftChild(BSTNode<T> node) {
		return node.getParent().getLeft().equals(node);

	}

	private boolean inLeft(BSTNode<T> node) {
		return node.getParent().getLeft().equals(node)
				&& node.getParent().getParent().getLeft().equals(node.getParent());
	}

	private boolean inRight(BSTNode<T> node) {
		return node.getParent().getRight().equals(node)
				&& node.getParent().getParent().getRight().equals(node.getParent());
	}

	private boolean zigZagLeft(BSTNode<T> node) {
		return node.getParent().getRight().equals(node)
				&& node.getParent().getParent().getLeft().equals(node.getParent());

	}
}
