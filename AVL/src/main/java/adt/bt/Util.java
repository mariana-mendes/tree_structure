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
		if (node != null && !node.isEmpty()) {
			BSTNode<T> leftNewRoot = (BSTNode<T>) node.getRight().getLeft();
			node.getRight().setParent(node.getParent());
			node.getRight().setLeft(node);
			node.setParent(node.getRight());
			node.setRight(leftNewRoot);

			return (BSTNode<T>) node.getParent();

		}
		return null;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			BSTNode<T> rightNewRoot = (BSTNode<T>) node.getLeft().getRight();
			node.getLeft().setParent(node.getParent());
			node.getLeft().setRight(node);
			node.setParent(node.getLeft());
			node.setLeft(rightNewRoot);

			return (BSTNode<T>) node.getParent();

		}
		return null;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
	
	

}
