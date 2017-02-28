package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			int hRight = height((BSTNode<T>) node.getRight());
			int hLeft = height((BSTNode<T>) node.getLeft());
			return hLeft - hRight;
		}
		return 0;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int factory = calculateBalance(node);
		boolean verify = verifyZigZag(node);
		if ((factory < -1 || factory > 1) && verify) {
			this.doubleRotation(node);
		} else if (factory > 1) {
			Util.leftRotation(node);
		} else if (factory < -1) {
			Util.rightRotation(node);
		} else {
			rebalanceUp(node);
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node.getParent() != null && !node.isEmpty()) {
			rebalance((BSTNode<T>) node.getParent());
		}
	}

	//"ZigZag" in the node
	public <T extends Comparable<T>> boolean verifyZigZag(BSTNode<T> node) {
		if (!node.isEmpty() && node != null) {
			return (!node.getLeft().isEmpty() && !node.getLeft().getRight().isEmpty()
					|| !node.getRight().isEmpty() && !node.getRight().getLeft().isEmpty());
		}

		return false;
	}

	// to make two rotation if the node have a 'zig-zag'
	public <T extends Comparable<T>> void doubleRotation(BSTNode<T> node) {
		if (!node.getLeft().isEmpty() && !node.getLeft().getRight().isEmpty()) {
			Util.leftRotation((BSTNode<T>) node.getLeft());
			Util.rightRotation(node);
		} else if (!node.getRight().isEmpty() && !node.getRight().getLeft().isEmpty()) {
			Util.rightRotation((BSTNode<T>) node.getRight());
			Util.leftRotation(node);
		}
	}
	
	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.root.isEmpty()) {
				this.root.setData(element);
				this.root.setLeft(new BSTNode<>());
				this.root.setRight(new BSTNode<>());
				this.root.getLeft().setParent(root);
				this.root.getRight().setParent(root);

			} else if ((this.root.getData().compareTo(element)) < 0) {
				insert(element, (BSTNode<T>) this.root.getRight());
			} else {
				insert(element, (BSTNode<T>) this.root.getLeft());
			}
		}
	}

	private void insert(T element, BSTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
			this.rebalance(node);
		} else if ((node.getData().compareTo(element)) < 0) {
			insert(element, (BSTNode<T>) node.getRight());
		} else {
			insert(element, (BSTNode<T>) node.getLeft());
		}
	}


}
