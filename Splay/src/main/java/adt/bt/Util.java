package adt.bt;

import adt.bst.BSTNode;

public class Util {

	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BTNode<T> parent = node.getParent();
		BTNode<T> right = node.getRight();

		if (parent != null) {
			if (parent.getLeft().equals(node))
				parent.setLeft(right);
			else
				parent.setRight(right);
		}
		right.setParent(parent);
		node.setParent(right);

		BTNode<T> rightLeft = right.getLeft();
		node.setRight(rightLeft);
		right.setLeft(node);
		if (rightLeft != null)
			rightLeft.setParent(node);

		return (BSTNode<T>) right;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BTNode<T> parent = node.getParent();
		BTNode<T> left = node.getLeft();

		if (parent != null) {
			if (parent.getLeft().equals(node))
				parent.setLeft(left);
			else
				parent.setRight(left);
		}
		left.setParent(parent);
		node.setParent(left);

		BTNode<T> leftRight = left.getRight();
		node.setLeft(leftRight);
		left.setRight(node);
		if (leftRight != null)
			leftRight.setParent(node);

		return (BSTNode<T>) left;
	}

}
