package adt.splaytree;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import adt.bst.BSTNode;
import adt.splaytree.SplayTree;
import adt.splaytree.SplayTreeImpl;

public class StudentSplayTreeTest {

	private SplayTree<Integer> splay;
	private BSTNode<Integer> NIL = new BSTNode<Integer>();

	@Before
	public void setUp() {
		splay = new SplayTreeImpl<>();
	}
	
	 @Test
	 public void testInit() {
	 assertTrue(splay.isEmpty());
	 assertEquals(0, splay.size());
	 assertEquals(-1, splay.height());
	 assertEquals(NIL, splay.getRoot());
	 }
	
	
	
	 @Test
	 public void testInsert() {
	 splay.insert(5);
	 assertEquals(1, splay.size());
	 assertEquals(0, splay.height());
	 assertArrayEquals(new Integer[] { 5 }, splay.preOrder());
	
	 assertFalse(splay.isEmpty());
	 assertEquals(new Integer(5), splay.getRoot().getData());
	
	 splay.insert(-15);
	 assertEquals(2, splay.size());
	 assertEquals(1, splay.height());
	 assertArrayEquals(new Integer[] { -15, 5 }, splay.preOrder());
	
	 splay.insert(99);
	 assertEquals(3, splay.size());
	 assertEquals(2, splay.height());
	 assertArrayEquals(new Integer[] { 99, 5, -15 }, splay.preOrder());
	 }
	
	 @Test
	 public void testRemove() {
	 splay.insert(5);
	 splay.insert(120);
	 splay.insert(1);
	 splay.insert(-100);
	
	 splay.remove(1);
	 assertEquals(3, splay.size());
	 assertArrayEquals(new Integer[] { -100, 5, 120 }, splay.preOrder());
	 splay.search(3);
	 assertArrayEquals(new Integer[] { 5, -100, 120 }, splay.preOrder());
	
	 splay.remove(-100);
	 assertEquals(2, splay.size());
	 assertArrayEquals(new Integer[] { 5, 120 }, splay.preOrder());
	 splay.search(120);
	 assertArrayEquals(new Integer[] { 120, 5 }, splay.preOrder());
	
	 splay.remove(5);
	 splay.remove(120);
	 assertEquals(NIL, splay.getRoot());
	 assertTrue(splay.isEmpty());
	 }

	@Test
	public void teste() {

		splay.insert(7);

		assertEquals(new Integer(7), splay.getRoot().getData());

		splay.insert(10);

		assertEquals(new Integer(10), splay.getRoot().getData());
		assertEquals(new Integer(7), splay.getRoot().getLeft().getData());

		splay.insert(15);

		assertEquals(new Integer(15), splay.getRoot().getData());
		assertEquals(new Integer(10), splay.getRoot().getLeft().getData());
		assertEquals(new Integer(7), splay.getRoot().getLeft().getLeft().getData());
		
		splay.insert(5);
		
		assertEquals(new Integer(5), splay.getRoot().getData());
		assertEquals(new Integer(15), splay.getRoot().getRight().getData());
		assertEquals(new Integer(7), splay.getRoot().getRight().getLeft().getData());
		assertEquals(new Integer(10), splay.getRoot().getRight().getLeft().getRight().getData());
		

	}
}
