package adt.bst;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

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
		if (element != null && root != null) {
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

	protected BSTNode<T> search(T element, BSTNode<T> next) {
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
		if (this.root.isEmpty())
			return null;
		return minimum(this.root);
	}

	protected BSTNode<T> minimum(BSTNode<T> node) {
		if (node.isEmpty())
			return (BSTNode<T>) node.getParent();
		return minimum((BSTNode<T>) node.getLeft());
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		if (element != null) {
			BSTNode<T> node = search(element);
			if (!node.isEmpty() && node != null && !node.getRight().isEmpty()) {
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

	public BSTNode<T> sucessor(BSTNode<T> node) {
		if (!node.isEmpty() && node != null && !node.getRight().isEmpty()) {
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
		if (element != null) {
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

	public void removeLeaf(BSTNode<T> node) {
		node.setData(null);

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

	// UMAS MANIPULAÇÃO AQUI MT DOIDA COM BST, ESPERO Q SEJA BST NA PROVA

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

	public T orderStatistic(BST<T> tree, int k) {
		if (tree == null || k > tree.size() || k < 0 || tree.isEmpty())
			return null;
		if (k == 1)
			return minimum((BSTNode<T>) tree.getRoot()).getData();
		return orderStatic(minimum((BSTNode<T>) tree.getRoot()), k, 1);
	}

	private T orderStatic(BSTNode<T> node, int k, int j) {
		if (node.isEmpty()) {
			return null;
		} else {
			if (k == j) {
				return node.getData();
			} else {
				return orderStatic(sucessor(node), k, ++j);

			}
		}

	}

	/**
	 * This method returns the maximum elements between two nodes
	 */

	public T betweenTwo(T to, T from) {

		if (from.compareTo(to) < 0) {
			T aux = to;
			to = from;
			from = aux;
		}

		BSTNode<T> um = search(to);
		BSTNode<T> dois = search(from);

		if (um == null || um.isEmpty() || dois == null || dois.isEmpty()) {
			return null;
		} else {
			while ((um.getParent().getData().compareTo(from)) < 0) {
				um = (BSTNode<T>) um.getParent();
			}

			if (um.getData().equals(from)) {
				return um.getData();
			} else {
				return maximum(um).getData();
			}
		}

	}

	/**
	 * this method return the sum of the k-smallest elements
	 */

	public int sumOfKSmallest(int k) {

		return sumOfKSmallest(this.minimum(), k, (int) this.minimum().getData());

	}

	private int sumOfKSmallest(BSTNode<T> min, int k, int soma) {
		if (k == 1) {
			return soma;
		} else {
			return sumOfKSmallest(sucessor(min), k - 1, soma + (Integer) sucessor(min).getData());
		}

	}

	/**
	 * This method returns minimum absolute difference
	 */

	public T findMinimum(T element) {
		if (element != null) {
			if (!search(element).isEmpty()) {
				return element;
			} else {
				return find(getRoot(), 1, 1000, element);
			}
		}
		return null;
	}

	private T find(BSTNode<T> node, int k, int menor, T element) {

		if (k <= this.size()) {
			int s = (int) orderStatistic(this, k);
			T s2 = orderStatistic(this, k);

			if (Math.abs(s - (int) element) < menor) {
				node =  search(s2);
				menor = Math.abs(s - (int) element);
			}

			return find(node, ++k, menor, element);
			

		}
		return node.getData();

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

	
	//OUTRO JEITO DOS ORDERS
	
	public T[] order2(){
		T[] array = (T[]) new Comparable[this.size()];
		ArrayList<T> list = new ArrayList<T>();
		order2(list, this.root);
		list.toArray(array);
		return array;
	}

	private void order2(ArrayList<T> list, BSTNode<T> root2) {
		if(root2.isEmpty()) return;
		
		order2(list,(BSTNode<T>) root2.getLeft());
		list.add(root2.getData());
		order2(list, (BSTNode<T>) root2.getRight());

	}
		
	public T[] preOrder2(){
		T[] array = (T[]) new Comparable[this.size()];
		ArrayList<T> list = new ArrayList<T>();
		list.toArray(array);
		return array;
	
	}
	private void preOrder2(ArrayList<T> list, BSTNode<T> node){
		if(node.isEmpty()) return;
		
		list.add(node.getData());
		preOrder2(list, (BSTNode<T>) node.getLeft());
		preOrder2(list, (BSTNode<T>) node.getRight());
		
	}
	
	public T[] postOrder2(){
		T[] array = (T[]) new Comparable[this.size()];
		ArrayList<T> list = new ArrayList<T>();
		list.toArray(array);
		return array;
	
	}
	private void postOrder2(ArrayList<T> list, BSTNode<T> node){
		if(node.isEmpty()) return;
		
		
		postOrder2(list, (BSTNode<T>) node.getLeft());
		postOrder2(list, (BSTNode<T>) node.getRight());
		list.add(node.getData());
		
	}
	
	public void walkByLevels(BSTNode<T> node){
		if(node == null) return;
		
		Deque<BSTNode<T>> deque = new ArrayDeque<>();
		
		deque.add(node);
		
		while(!deque.isEmpty()){
			
			BSTNode<T> atual = deque.removeFirst();
			System.out.print(" " +atual.getData());
		
			if(!atual.getLeft().isEmpty()){
				deque.add((BSTNode<T>) atual.getLeft());
			}
			
			if(!atual.getRight().isEmpty()){
				deque.add((BSTNode<T>) atual.getRight());
			}
		}
		
	}
	
	
	
	
	
}
