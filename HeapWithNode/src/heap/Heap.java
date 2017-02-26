package heap;

public class Heap<T extends Comparable<T>> {

	private Node root;

	public Heap() {
		this.root = new Node<T>();
	}

	public void insert(T element) {
		if (this.root.isEmpty()) {
			this.root.setData(element);
			this.root.setLeft(new Node<T>());
			this.root.setRight(new Node<T>());
			this.root.getLeft().setParent(root);
			this.root.getRight().setParent(root);
		} else {
			this.insert(root, element);

		}

	}

	private void insert(Node<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new Node<T>());
			node.setRight(new Node<T>());
			node.getRight().setParent(node);
			node.getLeft().setParent(node);
			balance(node);

		} else {
			int heightRight = this.height(node.getRight());
			int heightLeft = this.height(node.getLeft());

			if (heightRight < heightLeft) {
				insert(node.getRight(), element);

			} else {
				insert(node.getLeft(), element);
			}
		}

	}

	private void balance(Node<T> node) {
		while (node.getParent() != null && !node.isEmpty()) {
			if (!node.getParent().isEmpty()) {

				if ((node.getData().compareTo(node.getParent().getData())) > 0) {
					T change = node.getData();
					node.setData(node.getParent().getData());
					node.getParent().setData(change);
				}
				node = node.getParent();
			}
		}
	}

	public void heapify(Node<T> node) {
		if (!node.isEmpty()) {
			if (!node.getRight().isEmpty() && (node.getData().compareTo(node.getRight().getData())) < 0) {
				T change = node.getData();
				node.setData(node.getRight().getData());
				node.getRight().setData(change);
				heapify(node.getRight());

			} else if (!node.getLeft().isEmpty() && (node.getData().compareTo(node.getLeft().getData())) < 0) {
				T change = node.getData();
				node.setData(node.getLeft().getData());
				node.getLeft().setData(change);
				heapify(node.getLeft());

			}
		}

	}

	public int height() {
		return height(root);

	}

	private int height(Node node) {
		if (!node.isEmpty()) {
			return Math.max(this.height((Node<T>) node.getLeft()), this.height((Node<T>) node.getRight())) + 1;
		}
		return -1;

	}

	public Node<T> getRoot() {
		return this.root;
	}
}
