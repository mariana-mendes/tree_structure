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

		// teste altura
		assertEquals(2, bst.height(bst.search(new Integer(6))));
		assertEquals(1, bst.height(bst.search(new Integer(12))));
		assertEquals(3, bst.height());

		assertEquals(new Integer(7), bst.predecessor(8).getData());
		assertEquals(new Integer(8), bst.predecessor(9).getData());
		assertEquals(new Integer(5), bst.predecessor(6).getData());

	}

	@Test
	public void max() {
		this.bst.insert(new Integer(18));
		this.bst.insert(new Integer(36));
		this.bst.insert(new Integer(9));
		this.bst.insert(new Integer(6));
		this.bst.insert(new Integer(12));
		this.bst.insert(new Integer(10));
		this.bst.insert(new Integer(1));
		this.bst.insert(new Integer(8));

		assertEquals(bst.betweenTwo(1, 10), new Integer(12));
		assertEquals(bst.betweenTwo(10, 1), new Integer(12));

		assertEquals(7, bst.sumOfKSmallest(2));
		assertEquals(34, bst.sumOfKSmallest(5));
		assertEquals(1, bst.sumOfKSmallest(1));
		assertEquals(15, bst.sumOfKSmallest(3));
		assertEquals(24, bst.sumOfKSmallest(4));

	}

	@Test
	public void find() {
		this.bst.insert(new Integer(9));
		this.bst.insert(new Integer(4));
		this.bst.insert(new Integer(3));
		this.bst.insert(new Integer(17));
		this.bst.insert(new Integer(22));
		this.bst.insert(new Integer(20));
		this.bst.insert(new Integer(6));
		assertEquals(new Integer(20), bst.findMinimum(19));
		assertEquals(new Integer(3), bst.findMinimum(-5));
		assertEquals(new Integer(22), bst.findMinimum(30));
		assertEquals(new Integer(9), bst.findMinimum(9));
	}

	@Test
	public void orders() {
		
		this.bst.insert(new Integer(9));
		this.bst.insert(new Integer(4));
		this.bst.insert(new Integer(3));
		this.bst.insert(new Integer(17));
		this.bst.insert(new Integer(22));
		this.bst.insert(new Integer(20));
		this.bst.insert(new Integer(6));
		
		
		bst.walkByLevels(bst.root);
	}
	
	


}
