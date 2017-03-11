package adt.bst;

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

	private int height(BSTNode<T> node) {
		if (node.isEmpty()) {
			return -1;
		} else {
			return Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight())) + 1;
		}

	}

	@Override
	public BSTNode<T> search(T element) {
		if (element != null) {
			if (this.root.getData() != null) {
				if (this.root.getData().equals(element)) {
					return this.root;
				} else {
					return search(this.root, element);

				}
			}
		}
		return new BSTNode<T>();
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node.getData() != null) {
			if (node.getData().equals(element)) {
				return node;
			}
			if ((node.getData().compareTo(element)) < 0) {
				return search((BSTNode<T>) node.getRight(), element);
			} else {
				return search((BSTNode<T>) node.getLeft(), element);
			}
		}
		return new BSTNode<T>();
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.root.getData() == null) {

				this.root.setData(element);
				this.root.setLeft(new BSTNode<T>());
				this.root.setRight(new BSTNode<T>());
				this.root.getRight().setParent(root);
				this.root.getLeft().setParent(root);
			} else if ((this.root.getData().compareTo(element)) < 0) {
				this.insert((BSTNode<T>) this.root.getRight(), element);

			} else {
				this.insert((BSTNode<T>) this.root.getLeft(), element);
			}
		}

	}

	/**
	 * Metodo criado para inserção recursiva, precisa receber o nó.
	 * 
	 * @param node
	 * @param element
	 */
	private void insert(BSTNode<T> node, T element) {
		if (node.getData() == null) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else if ((node.getData().compareTo(element)) < 0) {
			this.insert((BSTNode<T>) node.getRight(), element);

		} else {
			this.insert((BSTNode<T>) node.getLeft(), element);

		}

	}

	@Override
	public BSTNode<T> maximum() {
		if (this.root.getData() != null) {
			return maximum(this.root);
		}
		return null;

	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.getRight().getData() == null) {
			return node;
		} else {
			return maximum((BSTNode<T>) node.getRight());
		}

	}

	@Override
	public BSTNode<T> minimum() {
		if (this.root.getData() != null) {
			return minimum(this.root);
		}

		return null;
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.getLeft().getData() == null) {
			return node;
		} else {
			return minimum((BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		if (node.getData() != null) {
			if (node.getRight().getData() != null) {
				return minimum((BSTNode<T>) node.getRight());
			}

			else {
				return sucessor(node, (BSTNode<T>) node.getParent(), element);
			}
		}

		return null;
	}

	private BSTNode<T> sucessor(BSTNode<T> node, BSTNode<T> parent, T element) {
		if (parent != null && node != null && node.equals(parent.getRight())) {
			node = parent;
			parent = (BSTNode<T>) parent.getParent();
			return sucessor(node, parent, element);
		}

		return parent;

	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		if (node.getData() != null) {
			if (node.getLeft().getData() != null) {
				return maximum((BSTNode<T>) node.getLeft());
			}

			else {
				return predecessor(node, (BSTNode<T>) node.getParent(), element);
			}
		}

		return null;
	}

	private BSTNode<T> predecessor(BSTNode<T> node, BSTNode<T> parent, T element) {
		if (parent != null && node != null && node.equals(parent.getLeft())) {
			node = parent;
			parent = (BSTNode<T>) parent.getParent();
			return predecessor(node, parent, element);
		}

		return parent;

	}

	@Override
	public void remove(T element) {
		if (element == null || this.root.isEmpty()) {
			return;
		} else {
			BSTNode<T> node = search(element);

			if (node != null) {
				remove(node);

			}
		}
	}

	protected void remove(BSTNode<T> node) {

		// size Tree == 1
		if (node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			node.setData(null);
			return;
		}

		if (node.getParent() == null) {
			if (node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
				this.root = (BSTNode<T>) node.getRight();
				this.root.setParent(null);

			} else if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
				this.root = (BSTNode<T>) node.getLeft();
				this.root.setParent(null);

			} else {
				removeTwoChilds(node);
			}

		} else if (node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
			removeChildRight(node);

		} else if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			removeChildLeft(node);

		} else {
			removeTwoChilds(node);

		}

	}

	/**
	 * Remove NODE que contem apenas um nó a sua esquerda
	 * 
	 * @param node
	 */
	private void removeChildLeft(BSTNode<T> node) {
		node.getLeft().setParent(node.getParent());
		if (node.getParent().getLeft().equals(node)) {
			node.getParent().setLeft(node.getLeft());
		} else {
			node.getParent().setRight(node.getLeft());
		}

	}

	/**
	 * Remove NODE que contem apenas um nó a sua direita
	 * 
	 * @param node
	 */
	private void removeChildRight(BSTNode<T> node) {
		node.getRight().setParent(node.getParent());
		if (node.getParent().getLeft().equals(node)) {
			node.getParent().setLeft(node.getRight());
		} else {
			node.getParent().setRight(node.getRight());
		}

	}

	/**
	 * Remove NODE que contem 2 nos, a direita e a esquerda
	 * 
	 * @param node
	 */
	private void removeTwoChilds(BSTNode<T> node) {
		BSTNode<T> sucessor = sucessor(node.getData());
		T elemento = sucessor.getData();
		remove(sucessor);
		node.setData(elemento);

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
