package adt.bst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MyTest {

	private BSTImpl<Integer> bst;

	private BSTNode<Integer> NIL;

	@Before
	public void setUp() {
		this.bst = new BSTImpl<>();
		this.NIL = new BSTNode<>();

	}

	@Test
	public void insert() {
		assertEquals(0, bst.size());
		assertEquals(NIL.getData(), bst.maximum());
		assertEquals(-1, bst.height());

		// apenas 1 elemento
		this.bst.insert(new Integer(9));

		assertEquals(new Integer(9), bst.maximum().getData());
		assertEquals(new Integer(9), bst.minimum().getData());

		// + insercoes
		this.bst.insert(new Integer(7));
		this.bst.insert(new Integer(12));

		assertEquals(new Integer(9), bst.root.getData());
		assertEquals(new Integer(7), bst.root.getLeft().getData());
		assertEquals(new Integer(12), bst.root.getRight().getData());

		assertEquals(new Integer(12), bst.maximum().getData());
		assertEquals(new Integer(7), bst.minimum().getData());

		// search
		assertEquals(new Integer(9), bst.search(9).getData());
		assertEquals(new Integer(7), bst.search(7).getData());
		assertEquals(new Integer(12), bst.search(12).getData());
		assertEquals(NIL.getData(), bst.search(90).getData());

		// second test
		this.bst.insert(new Integer(10));
		this.bst.insert(new Integer(2));
		this.bst.insert(new Integer(20));

		assertEquals(new Integer(20), bst.maximum().getData());
		assertEquals(new Integer(2), bst.minimum().getData());

	}

	@Test
	public void sucessorAndPredecessor() {

		assertEquals(-1, bst.height());

		this.bst.insert(new Integer(9));
		this.bst.insert(new Integer(6));
		this.bst.insert(new Integer(7));
		this.bst.insert(new Integer(5));
		this.bst.insert(new Integer(8));
		this.bst.insert(new Integer(12));
		this.bst.insert(new Integer(10));
		this.bst.insert(new Integer(20));

		assertEquals(new Integer(9), bst.sucessor(8).getData());
		assertEquals(new Integer(10), bst.sucessor(9).getData());
		assertEquals(new Integer(7), bst.sucessor(6).getData());

		//teste altura
		assertEquals(2, bst.height(bst.search(new Integer(6))));
		assertEquals(1, bst.height(bst.search(new Integer(12))));
		assertEquals(3, bst.height());

		assertEquals(new Integer(7), bst.predecessor(8).getData());
		assertEquals(new Integer(8), bst.predecessor(9).getData());
		assertEquals(new Integer(5), bst.predecessor(6).getData());

	}
	


}
