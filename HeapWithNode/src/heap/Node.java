package heap;

/**
 * The node of a binary tree. Its internal data has type T, which does not need
 * to be comparable.
 */
public class Node<T> {
	protected T data;
	protected Node<T> left;
	protected Node<T> right;
	protected Node<T> parent;

	public Node(T data, Node<T> left, Node<T> right, Node<T> parent) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	public Node() {
	}

	public boolean isEmpty() {
		return this.data == null;
	}

	public boolean isLeaf() {
		return this.data != null && this.left.isEmpty() && this.right.isEmpty();
	}
	
	@Override
	public String toString() {
		String resp = "NIL";
		if (!isEmpty()) {
			resp = data.toString();
		}
		return resp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		boolean resp = false;
		if (obj instanceof Node) {
			if (!this.isEmpty() && !((Node<T>) obj).isEmpty()) {
				resp = this.data.equals(((Node<T>) obj).data);
			} else {
				resp = this.isEmpty() && ((Node<T>) obj).isEmpty();
			}

		}
		return resp;
	}

    public void printTree() {
        if (right != null && !right.isEmpty()) {
            getRight().printTree(true, "");
        }
        System.out.println(this.data + "");
        if (left != null && !left.isEmpty()) {
            getLeft().printTree(false, "");
        }
    }
 
    public void printTree(boolean isRight, String indent) {
        if (right != null && !right.isEmpty()) {
            getRight().printTree(true, indent + (isRight ? "       " : " |      "));
        }
        System.out.print(indent);
        if (isRight) {
            System.out.print(" /");
        } else {
            System.out.print(" \\");
        }
        System.out.print("----- ");
        System.out.println(this.data + "");
        if (left != null && !left.isEmpty()) {
            getLeft().printTree(false, indent + (isRight ? " |      " : "        "));
        }
    }
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}
}
