package main.java.adt.splaytree;

import main.java.adt.bst.BSTImpl;
import main.java.adt.bst.BSTNode;
import main.java.adt.bt.Util;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements SplayTree<T> {

	private void splay(BSTNode<T> node) {

		if (node == null || node.getParent() == null)
			return;

		if (node != null && !node.isEmpty()) {
			if (node.getParent().getParent() == null) {

				if (isLeftChild(node)) {
					this.rightRotation((BSTNode<T>) node.getParent());
				} else {
					this.leftRotation((BSTNode<T>) node.getParent());
				}

			} else if (leftLeft(node)) {

				this.rightRotation((BSTNode<T>) node.getParent().getParent());
				this.rightRotation((BSTNode<T>) node.getParent());

			} else if (rightRight(node)) {
				this.leftRotation((BSTNode<T>) node.getParent().getParent());
				this.leftRotation((BSTNode<T>) node.getParent());

			} else if (zigZag(node)) {
				this.leftRotation((BSTNode<T>) node.getParent());
				this.rightRotation((BSTNode<T>) node.getParent());

			} else if (zagZig(node)) {
				this.rightRotation((BSTNode<T>) node.getParent());
				this.leftRotation((BSTNode<T>) node.getParent());
			}

		}

		this.splay(node);
	}

	public void insert(T element) {
		if (element != null) {
			super.insert(element);
			BSTNode<T> s = super.search(element);
			this.splay(s);
		}

	}

	public BSTNode<T> search(T element) {
		BSTNode<T> s = super.search(element);
		if (s.isEmpty()) {
			splay((BSTNode<T>) s.getParent());

		} else {
			splay(s);
		}

		return this.root;
	}

	public void remove(T element) {
		BSTNode<T> s = super.search(element);
		BSTNode<T> parent = (BSTNode<T>) s.getParent();

		if (!s.isEmpty()) {
			super.remove(element);
		}

		this.splay(parent);
	}

	// AUXILIARES
	private void leftRotation(BSTNode<T> node) {
		BSTNode<T> r = Util.leftRotation(node);
		if (r.getParent() == null) {
			this.root = r;
		}
	}

	private void rightRotation(BSTNode<T> node) {
		BSTNode<T> r = Util.rightRotation(node);
		if (r.getParent() == null) {
			this.root = r;
		}
	}

	private boolean isLeftChild(BSTNode<T> node) {
		return (node.getParent().getLeft().equals(node));
	}

	private boolean leftLeft(BSTNode<T> node) {
		return (node.getParent().getParent().getLeft().equals(node.getParent())
				&& node.getParent().getLeft().equals(node));
	}

	private boolean rightRight(BSTNode<T> node) {
		return (node.getParent().getParent().getRight().equals(node.getParent())
				&& node.getParent().getRight().equals(node));
	}

	private boolean zigZag(BSTNode<T> node) {
		return node.getParent().getRight().equals(node)
				&& node.getParent().getParent().getLeft().equals(node.getParent());
	}

	private boolean zagZig(BSTNode<T> node) {
		return node.getParent().getLeft().equals(node)
				&& node.getParent().getParent().getRight().equals(node.getParent());
	}
}
