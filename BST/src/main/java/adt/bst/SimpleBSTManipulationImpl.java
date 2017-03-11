package adt.bst;

import adt.bt.BTNode;

public class SimpleBSTManipulationImpl<T extends Comparable<T>> extends BSTImpl<T> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {

		if (tree1 == null && tree2 == null) {
			return true;
		}
		if (tree1 != null && tree2 == null) {
			return false;
		}
		if (tree1 == null && tree2 != null) {
			return false;

		}

		if (!tree1.getRoot().equals(tree2.getRoot())) {
			return false;
		} else {
			return equals((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot(), true, true);
		}
	}

	private boolean equals(BSTNode<T> btNode, BSTNode<T> btNode2, boolean right, boolean left) {

		if (btNode.isEmpty() && btNode.equals(btNode2)) {
			return true && right && left;
		} else {

			right = (btNode.getRight().equals(btNode2.getRight()));
			left = (btNode.getLeft().equals(btNode2.getLeft()));
			if (right && left) {
				right = equals((BSTNode<T>) btNode.getRight(), (BSTNode<T>) btNode2.getRight(), right, left);
				left = equals((BSTNode<T>) btNode.getLeft(), (BSTNode<T>) btNode2.getLeft(), right, left);

				return right && left;
			} else {
				return false;

			}

		}
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		if (tree1 == null && tree2 == null) {
			return true;
		}
		if (tree1 != null && tree2 == null) {
			return false;
		}
		if (tree1 == null && tree2 != null) {
			return false;

		}

		return Similar((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot(), true, true);

	}

	private boolean Similar(BSTNode<T> um, BSTNode<T> dois, boolean right, boolean left) {
		if ((dois.isEmpty() && !um.isEmpty()) || (!dois.isEmpty() && um.isEmpty()) || !difere(um, dois)) {
			return false;
		} else {

			if (um.isEmpty() && dois.isEmpty()) {
				return true;
			}
			right = difere((BSTNode<T>) dois.getRight(), (BSTNode<T>) um.getRight());
			left = difere((BSTNode<T>) dois.getLeft(), (BSTNode<T>) um.getLeft());

			if (right && left) {
				left = Similar((BSTNode<T>) um.getLeft(), (BSTNode<T>) dois.getLeft(), right, left);
				right = Similar((BSTNode<T>) um.getRight(), (BSTNode<T>) dois.getRight(), right, left);
				return right && left;

			}

			else {
				return false;
			}
		}
	}

	private boolean difere(BSTNode<T> um, BSTNode<T> dois) {
		if (um.isEmpty() && dois.isEmpty()) {
			return true;
		}

		boolean a = (!um.isEmpty() && !dois.isEmpty() || (um.isEmpty()) && dois.isEmpty());
		boolean b = (um.getLeft().isEmpty() && dois.getLeft().isEmpty()
				|| (!um.getLeft().isEmpty() && !dois.getLeft().isEmpty()));
		boolean c = (!um.getRight().isEmpty() && !dois.getRight().isEmpty()
				|| (um.getRight().isEmpty() && dois.getRight().isEmpty()));

		return a && b && c;

	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		if (tree == null || k > tree.size() || k < 0 || tree.isEmpty())
			return null;
		if (k == 1)
			return minimum((BSTNode<T>) tree.getRoot()).getData();
		return orderStatic(minimum((BSTNode<T>) tree.getRoot()), k, 1);
	}

	private T orderStatic(BSTNode<T> node,int k, int j) {
		if (node.isEmpty()) {
			return null;
		} else {
			if (k == j) {
				return node.getData();
			} else {
				return orderStatic(sucessor(node),k, ++j);

			}
		}

	}

}