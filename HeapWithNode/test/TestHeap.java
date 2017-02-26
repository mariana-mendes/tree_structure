import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import heap.Heap;

public class TestHeap {

	private Heap heap;

	@Before
	public void setUp() {
		this.heap = new Heap<Integer>();

	}

	@Test
	public void insert() {
		heap.insert(new Integer(8));
		heap.insert(new Integer(6));
		heap.insert(new Integer(5));

		assertEquals(new Integer(8), heap.getRoot().getData());
		assertEquals(new Integer(5), heap.getRoot().getRight().getData());
		assertEquals(new Integer(6), heap.getRoot().getLeft().getData());
		
		heap.insert(new Integer(9));
		assertEquals(new Integer(9), heap.getRoot().getData());
		assertEquals(new Integer(5), heap.getRoot().getRight().getData());
		assertEquals(new Integer(8), heap.getRoot().getLeft().getData());

		
		
	}

}