package test.java.adt.splaytree;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import main.java.adt.bst.BSTNode;
import main.java.adt.splaytree.SplayTree;
import main.java.adt.splaytree.SplayTreeImpl;

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
	public void testSearchInsertEOQ() {
		splay.insert(10);
		splay.insert(18);
		splay.insert(2);
		splay.insert(13);
		splay.insert(19);

		assertTrue(splay.getRoot().getData().equals(19));
		splay.search(19);
		assertTrue(splay.getRoot().getData().equals(19));
		assertEquals(splay.height(), 4);

		splay.search(10);
		assertTrue(splay.getRoot().getData().equals(10));
		assertEquals(splay.height(), 2);

		splay.insert(28);
		assertTrue(splay.getRoot().getData().equals(28));
		assertEquals(splay.height(), 4);

		splay.insert(27);
		assertTrue(splay.getRoot().getData().equals(27));
		assertEquals(splay.height(), 4);

		splay.insert(15);
		assertEquals(splay.height(), 3);
		assertTrue(splay.getRoot().getData().equals(15));

		splay.insert(22);
		assertEquals(splay.height(), 3);

		splay.search(18);
		assertEquals(splay.height(), 3);
		assertTrue(splay.getRoot().getData().equals(18));

		splay.search(13);
		assertEquals(splay.height(), 4);
		assertTrue(splay.getRoot().getData().equals(13));

		splay.search(2);
		assertEquals(splay.height(), 6);
		assertTrue(splay.getRoot().getData().equals(2));

		splay.search(19);
		assertEquals(splay.height(), 4);
		assertTrue(splay.getRoot().getData().equals(19));

		// só p dar emoção
		splay.search(29);
		assertTrue(splay.getRoot().getData().equals(28));
		assertEquals(splay.height(), 5);

		splay.search(23);
		assertTrue(splay.getRoot().getData().equals(22));
		assertEquals(splay.height(), 5);

		splay.search(9);
		assertTrue(splay.getRoot().getData().equals(10));
		assertEquals(splay.height(), 4);
//		assertEquaIs(splay.size(), 4);

		splay.search(21);
		assertTrue(splay.getRoot().getData().equals(22));
		assertEquals(splay.height(), 5);

		// vamo ver se a arvore emociona
		splay.search(null);
		assertTrue(splay.getRoot().getData().equals(22));
		assertEquals(splay.height(), 5);
	}

	/**
	 * @author Eric
	 */
	@Test
	public void testRemoveRXRX() {
		synchronized (this) {
			for (Integer e : new Integer[] { 10, 29, 1, 32, 89, 75, 44, 20, 33, -29, 12, 69, 42 })
				splay.insert(e);
			System.err.println("BOOOOOOOOOOORA");
		}

		assertEquals(splay.height(), 5);
		// A raiz deve ser a resposta para tudo
		assertTrue(splay.getRoot().getData().equals(42));
		assertEquals(splay.size(), 13);

		splay.remove(12);
		assertEquals(splay.size(), 12);
		assertEquals(splay.height(), 4);
		assertTrue(splay.getRoot().getData().equals(33));

		splay.remove(10);
		assertEquals(splay.size(), 11);
		assertEquals(splay.height(), 5);
		assertTrue(splay.getRoot().getData().equals(1));

		// mentira
		splay.remove(667);
		assertEquals(splay.size(), 11);
		assertEquals(splay.height(), 6);
		assertTrue(splay.getRoot().getData().equals(89));

		splay.remove(31);
		assertEquals(splay.size(), 11);
		assertEquals(splay.height(), 6);
		assertTrue(splay.getRoot().getData().equals(29));

		// agora é vdd
		splay.remove(33);
		assertEquals(splay.size(), 10);
		assertEquals(splay.height(), 4);
		assertTrue(splay.getRoot().getData().equals(89));
		
		// ta massa, ficou no topo dms ja
		splay.remove(89);
		assertEquals(splay.size(), 9);
		assertEquals(splay.height(), 3);
		assertTrue(splay.getRoot().getData().equals(42));
		
		//vamo ver se vai emocionar
		splay.remove(45);
		assertEquals(splay.height(), 4);
		assertTrue(splay.getRoot().getData().equals(44));

		splay.remove(null);
		assertEquals(splay.height(), 4);
		assertTrue(splay.getRoot().getData().equals(44));
		
		splay.remove(42);
		assertEquals(splay.height(), 3);
		assertTrue(splay.getRoot().getData().equals(44));
	}
	
	/**
	 * @author Thaynan
	 */
	@Test
	public void meusTests() {

		splay.insert(22);
		splay.insert(13);
		splay.insert(5);
		splay.insert(1);

		assertEquals(4, splay.size());
		assertEquals(3, splay.height());
		assertArrayEquals(new Integer[] { 1, 5, 13, 22 }, splay.preOrder());

		splay.insert(0);
		splay.insert(3);

		assertEquals(6, splay.size());
		assertEquals(3, splay.height());
		assertArrayEquals(new Integer[] { 3, 0, 1, 5, 13, 22 }, splay.preOrder());

		splay.insert(18);
		splay.insert(100);

		assertEquals(8, splay.size());
		assertEquals(6, splay.height());
		assertArrayEquals(new Integer[] { 100, 22, 18, 5, 3, 0, 1, 13 }, splay.preOrder());

		splay.search(5);

		assertEquals(8, splay.size());
		assertEquals(3, splay.height());
		assertArrayEquals(new Integer[] { 5, 3, 0, 1, 100, 18, 13, 22 }, splay.preOrder());

		splay.search(9);

		assertEquals(8, splay.size());
		assertEquals(4, splay.height());
		assertArrayEquals(new Integer[] { 13, 5, 3, 0, 1, 18, 100, 22 }, splay.preOrder());

		splay.search(3);

		assertEquals(8, splay.size());
		assertEquals(5, splay.height());
		assertArrayEquals(new Integer[] { 3, 0, 1, 5, 13, 18, 100, 22 }, splay.preOrder());
	}

	/**
	 * @author Thaynan
	 */
	@Test
	public void meusTestRemove() {

		splay.insert(22);
		splay.insert(13);
		splay.insert(5);
		splay.insert(1);
		splay.insert(9);
		splay.insert(35);
		splay.insert(10);

		assertEquals(7, splay.size());
		assertEquals(3, splay.height());
		assertArrayEquals(new Integer[] { 10, 9, 1, 5, 35, 13, 22 }, splay.preOrder());

		splay.remove(12);

		assertEquals(7, splay.size());
		assertEquals(4, splay.height());
		assertArrayEquals(new Integer[] { 13, 10, 9, 1, 5, 35, 22 }, splay.preOrder());

		splay.remove(5);

		assertEquals(6, splay.size());
		assertEquals(3, splay.height());
		assertArrayEquals(new Integer[] { 1, 13, 9, 10, 35, 22 }, splay.preOrder());

		splay.remove(1);

		assertEquals(5, splay.size());
		assertEquals(2, splay.height());
//		assertArrayEquals(new Integer[] { 13, 9, 10, 35, 22 }, splay.preOrder());

		splay.remove(13);

		assertEquals(4, splay.size());
		assertEquals(2, splay.height());
		assertArrayEquals(new Integer[] { 22, 9, 10, 35 }, splay.preOrder());

		splay.remove(22);

		assertEquals(3, splay.size());
		assertEquals(2, splay.height());
		assertArrayEquals(new Integer[] { 35, 9, 10 }, splay.preOrder());

		splay.remove(9);

		assertEquals(2, splay.size());
		assertEquals(1, splay.height());
		assertArrayEquals(new Integer[] { 35, 10 }, splay.preOrder());

		splay.remove(9);

		assertEquals(2, splay.size());
		assertEquals(1, splay.height());
		assertArrayEquals(new Integer[] { 10, 35 }, splay.preOrder());

		splay.remove(10);

		assertEquals(1, splay.size());
		assertEquals(0, splay.height());
		assertArrayEquals(new Integer[] { 35 }, splay.preOrder());

		splay.remove(35);

		assertEquals(0, splay.size());
		assertEquals(-1, splay.height());
		assertArrayEquals(new Integer[] {}, splay.preOrder());

		splay.remove(35);

		assertEquals(0, splay.size());
		assertEquals(-1, splay.height());
		assertArrayEquals(new Integer[] {}, splay.preOrder());
}
}
