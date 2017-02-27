package adt.bst;

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
		return height(this.root);
	}

	public int height(BSTNode<T> node) {
		if (node.isEmpty())
			return -1;

		return Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight())) + 1;
	}

	@Override
	public BSTNode<T> search(T element) {
		if (element != null && !this.isEmpty()) {
			if (this.root.getData().equals(element)) {
				return this.root;

			} else if ((this.root.getData().compareTo(element)) < 0) {
				return search(element, (BSTNode<T>) this.root.getRight());
			} else {
				return search(element, (BSTNode<T>) this.root.getLeft());
			}

		}
		return new BSTNode<T>();
	}

	private BSTNode<T> search(T element, BSTNode<T> next) {
		if (!next.isEmpty()) {
			if (next.getData().equals(element)) {
				return next;
			} else if ((next.getData().compareTo(element)) < 0) {
				return search(element, (BSTNode<T>) next.getRight());
			} else {
				return search(element, (BSTNode<T>) next.getLeft());
			}
		}
		return new BSTNode<T>();

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
		} else if ((node.getData().compareTo(element)) < 0) {
			insert(element, (BSTNode<T>) node.getRight());
		} else {
			insert(element, (BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (this.isEmpty())
			return null;
		return maximum(this.root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.isEmpty())
			return (BSTNode<T>) node.getParent();
		return maximum((BSTNode<T>) node.getRight());
	}

	@Override
	public BSTNode<T> minimum() {
		if (this.isEmpty())
			return null;
		return minimum(this.root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.isEmpty())
			return (BSTNode<T>) node.getParent();
		return minimum((BSTNode<T>) node.getLeft());
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		if (element != null && !this.isEmpty()) {
			BSTNode<T> node = search(element);
			if (!node.getRight().isEmpty()) {
				return minimum((BSTNode<T>) node.getRight());

			} else {

				BSTNode<T> parent = (BSTNode<T>) node.getParent();
				while (parent != null && parent.getRight().equals(node)) {
					node = parent;
					parent = (BSTNode<T>) parent.getParent();
				}

				return parent;

			}
		}
		return null;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		if (element != null && !this.isEmpty()) {
			BSTNode<T> node = search(element);
			if (!node.getLeft().isEmpty()) {
				return maximum((BSTNode<T>) node.getLeft());

			} else {

				BSTNode<T> parent = (BSTNode<T>) node.getParent();
				while (parent != null && parent.getLeft().equals(node)) {
					node = parent;
					parent = (BSTNode<T>) parent.getParent();
				}

				return parent;

			}
		}
		return null;
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty()) {
			BSTNode<T> removing = search(element);

			if (!removing.isEmpty()) {

				if (removing.isLeaf()) {
					removing.setData(null);
				} else if (removing.getParent() == null) {
					removeRoot();
				} else if (!removing.getRight().isEmpty() && removing.getLeft().isEmpty()) {
					oneChild(removing, "right");

				} else if (removing.getRight().isEmpty() && !removing.getLeft().isEmpty()) {
					oneChild(removing, "left");

				} else if (!removing.getRight().isEmpty() && !removing.getLeft().isEmpty()) {
					twoChildren(removing);

				}

			}
		}
	}

	private void oneChild(BSTNode<T> toRemove, String position) {
		if (position.equals("right")) {
			toRemove.getRight().setParent(toRemove.getParent());

			if (toRemove.getParent().getLeft().equals(toRemove)) {
				toRemove.getParent().setLeft(toRemove.getRight());
			} else {
				toRemove.getParent().setRight(toRemove.getRight());
			}

		} else {
			toRemove.getLeft().setParent(toRemove.getParent());
			if (toRemove.getParent().getLeft().equals(toRemove)) {
				toRemove.getParent().setLeft(toRemove.getLeft());
			} else {
				toRemove.getParent().setRight(toRemove.getLeft());
			}
		}

	}

	private void twoChildren(BSTNode<T> removing) {
		BSTNode<T> sucessor = sucessor(removing.getData());
		T dataSucessor = sucessor.getData();
		remove(dataSucessor);
		removing.setData(dataSucessor);

	}

	private void removeRoot() {
		if (!this.root.getLeft().isEmpty() && this.root.getRight().isEmpty()) {
			this.root.getLeft().setParent(null);
			this.root = (BSTNode<T>) this.root.getLeft();

		} else if (this.root.getLeft().isEmpty() && !this.root.getRight().isEmpty()) {
			BSTNode<T> sucessor = sucessor(root.getData());
			T dataSucessor = sucessor.getData();
			remove(sucessor.getData());
			this.root.setData(dataSucessor);
		} else {
			twoChildren(root);
		}

	}

	@Override
 	public T[] preOrder() {
 		T[] array = (T[]) new Comparable[this.size()];
 		preOrder(this.root, array, 0);
 		return array;
 	}
 
 	private int preOrder(BSTNode<T> node, T[] array, int i) {
 		if (node.getData() != null) {
 
 			array[i++] = node.getData();
 			i = preOrder((BSTNode<T>) node.getLeft(), array, i);
 			i = preOrder((BSTNode<T>) node.getRight(), array, i);
 
 		}
 		return i;
 	}
 
 	@Override
 	public T[] order() {
 		T[] array = (T[]) new Comparable[this.size()];
 		order(this.root, array, 0);
 		return array;
 	}
 
 	private int order(BSTNode<T> node, T[] array, int i) {
 		if (node.getData() != null) {
 			i = order((BSTNode<T>) node.getLeft(), array, i);
 			array[i++] = node.getData();
 			i = order((BSTNode<T>) node.getRight(), array, i);
 
 		}
 		return i;
 
 	}
 
 	@Override
 	public T[] postOrder() {
 		T[] array = (T[]) new Comparable[this.size()];
 		postOrder(this.root, array, 0);
 		return array;
 	}
 
 	private int postOrder(BSTNode<T> node, T[] array, int i) {
 		if (node.getData() != null) {
 			i = postOrder((BSTNode<T>) node.getLeft(), array, i);
 			i = postOrder((BSTNode<T>) node.getRight(), array, i);
 			array[i++] = node.getData();
 
 		}
 		return i;
 
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
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
