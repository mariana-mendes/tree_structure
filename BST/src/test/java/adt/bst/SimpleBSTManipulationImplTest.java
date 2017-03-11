package adt.bst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SimpleBSTManipulationImplTest {

	SimpleBSTManipulation<Integer> manipulador;

	@Before
	public void preCarrega() {
		manipulador = new SimpleBSTManipulationImpl<>();
	}

	@Test
	public void testSimilarBasics() {
		BST<Integer> arvere1 = null;
		BST<Integer> arvere2 = null;

		assertTrue(manipulador.isSimilar(arvere1, arvere2));

		arvere1 = new BSTImpl<Integer>();
		assertFalse(manipulador.isSimilar(arvere1, arvere2));

		arvere2 = new BSTImpl<Integer>();
		assertTrue(manipulador.isSimilar(arvere1, arvere2));

		arvere1.insert(1);
		assertFalse(manipulador.isSimilar(arvere1, arvere2));
		arvere2.insert(1);
		assertTrue(manipulador.isSimilar(arvere1, arvere2));

		arvere1.insert(9);
		arvere2.insert(10);
		assertTrue(manipulador.isSimilar(arvere1, arvere2));

		arvere1.insert(-2);
		arvere1.insert(-4);
		arvere1.insert(-6);
		assertFalse(manipulador.isSimilar(arvere1, arvere2));

		arvere2.insert(-1);
		arvere2.insert(-3);
		arvere2.insert(-5);
		assertTrue(manipulador.isSimilar(arvere1, arvere2));

		arvere1.insert(2);
		arvere2.insert(3);
		assertTrue(manipulador.isSimilar(arvere1, arvere2));

		arvere1.insert(-1);
		arvere2.insert(0);
		assertTrue(manipulador.isSimilar(arvere1, arvere2));

		arvere1 = null;
		assertFalse(manipulador.isSimilar(arvere1, arvere2));
		assertFalse(manipulador.isSimilar(arvere2, arvere1));
		arvere1 = new BSTImpl<Integer>();
		assertFalse(manipulador.isSimilar(arvere2, arvere1));

		arvere1.insert(1);
		arvere1.insert(9);
		arvere1.insert(-2);
		arvere1.insert(-4);
		arvere1.insert(-6);
		arvere1.insert(2);
		arvere1.insert(-1);
		assertTrue(manipulador.isSimilar(arvere1, arvere2));

		arvere1.remove(-2);
		assertFalse(manipulador.isSimilar(arvere1, arvere2));
		arvere2.remove(-1);
		assertTrue(manipulador.isSimilar(arvere1, arvere2));
	}

	@Test
	public void testSimilarHarder() {
		// lenny face
		BST<Integer> arvere1 = null;
		BST<Integer> arvere2 = null;
		assertTrue(manipulador.isSimilar(arvere1, arvere2));

		arvere1 = new BSTImpl<Integer>();
		assertFalse(manipulador.isSimilar(arvere1, arvere2));

		arvere2 = new BSTImpl<Integer>();
		assertTrue(manipulador.isSimilar(arvere1, arvere2));

		arvere1.insert(100);
		arvere1.insert(50);
		arvere1.insert(25);
		arvere1.insert(75);
		assertFalse(manipulador.isSimilar(arvere1, arvere2));

		arvere2.insert(101);
		arvere2.insert(51);
		arvere2.insert(26);
		arvere2.insert(76);
		assertTrue(manipulador.isSimilar(arvere1, arvere2));

		arvere1.insert(60);
		arvere1.insert(90);
		arvere1.insert(85);
		arvere1.insert(99);
		arvere1.insert(150);
		arvere1.insert(125);
		assertFalse(manipulador.isSimilar(arvere1, arvere2));

		arvere2.insert(61);
		arvere2.insert(91);
		arvere2.insert(86);
		arvere2.insert(100);
		arvere2.insert(151);
		arvere2.insert(126);
		assertTrue(manipulador.isSimilar(arvere1, arvere2));

		arvere1.insert(101);
		arvere1.insert(135);
		arvere1.insert(130);
		arvere1.insert(140);
		arvere1.insert(180);
		assertFalse(manipulador.isSimilar(arvere1, arvere2));

		arvere2.insert(102);
		arvere2.insert(136);
		arvere2.insert(131);
		arvere2.insert(141);
		arvere2.insert(181);
		assertTrue(manipulador.isSimilar(arvere1, arvere2));

	}

	@Test
	public void testEqualBasics() {
		BST<Integer> arvere1 = null;
		BST<Integer> arvere2 = null;
		assertTrue(manipulador.equals(arvere1, arvere2));

		arvere1 = new BSTImpl<Integer>();
		assertFalse(manipulador.equals(arvere1, arvere2));

		arvere2 = new BSTImpl<Integer>();
		assertTrue(manipulador.equals(arvere1, arvere2));

		arvere1.insert(1);
		assertFalse(manipulador.equals(arvere1, arvere2));
		arvere2.insert(1);
		assertTrue(manipulador.equals(arvere1, arvere2));

		arvere1.insert(5);
		arvere2.insert(-5);
		assertFalse(manipulador.equals(arvere1, arvere2));

		arvere1.insert(-5);
		arvere2.insert(5);
		assertTrue(manipulador.equals(arvere1, arvere2));

		arvere1.insert(12);
		arvere1.insert(14);
		arvere2.insert(-3);
		arvere2.insert(12);
		assertFalse(manipulador.equals(arvere1, arvere2));

		arvere1.insert(-3);
		arvere2.insert(14);
		assertTrue(manipulador.equals(arvere1, arvere2));

		arvere1.insert(13);
		assertFalse(manipulador.equals(arvere1, arvere2));
		arvere2.insert(13);
		assertTrue(manipulador.equals(arvere1, arvere2));

		arvere1.remove(1);
		arvere2.remove(1);
		assertTrue(manipulador.equals(arvere1, arvere2));

		arvere1.remove(12);
		assertFalse(manipulador.equals(arvere1, arvere2));
		arvere2.remove(13);
		assertFalse(manipulador.equals(arvere1, arvere2));
		arvere1.remove(13);
		assertFalse(manipulador.equals(arvere1, arvere2));
		arvere2.remove(12);
		assertTrue(manipulador.equals(arvere1, arvere2));

		while (!arvere1.isEmpty())
			arvere1.remove(arvere1.getRoot().getData());
		while (!arvere2.isEmpty())
			arvere2.remove(arvere2.getRoot().getData());
		assertTrue(manipulador.equals(arvere1, arvere2));
	}

	@Test
	public void testEqualHarder() {
		BST<Integer> arvere1 = null;
		BST<Integer> arvere2 = null;
		assertTrue(manipulador.equals(arvere1, arvere2));

		arvere1 = new BSTImpl<Integer>();
		assertFalse(manipulador.equals(arvere1, arvere2));

		arvere2 = new BSTImpl<Integer>();
		assertTrue(manipulador.equals(arvere1, arvere2));

		arvere1.insert(101);
		arvere1.insert(51);
		arvere1.insert(26);
		arvere1.insert(76);
		arvere1.insert(61);
		arvere1.insert(91);
		arvere1.insert(86);
		assertFalse(manipulador.equals(arvere1, arvere2));

		arvere2.insert(101);
		arvere2.insert(51);
		arvere2.insert(26);
		arvere2.insert(76);
		arvere2.insert(61);
		arvere2.insert(91);
		arvere2.insert(86);
		assertTrue(manipulador.equals(arvere1, arvere2));

		arvere1.insert(100);
		arvere1.insert(151);
		arvere1.insert(126);
		arvere1.insert(181);
		arvere1.insert(102);
		arvere1.insert(136);
		arvere1.insert(131);
		arvere1.insert(141);
		assertFalse(manipulador.equals(arvere1, arvere2));

		arvere2.insert(100);
		arvere2.insert(151);
		arvere2.insert(126);
		arvere2.insert(181);
		arvere2.insert(102);
		arvere2.insert(136);
		arvere2.insert(131);
		arvere2.insert(141);
		assertTrue(manipulador.equals(arvere1, arvere2));

		arvere1.remove(125);
		arvere2.remove(125);
		assertTrue(manipulador.equals(arvere1, arvere2));
	}

	@Test
	public void testOrderStatistic() {
		BST<Integer> arvere1 = null;
		// assertNull(manipulador.orderStatistic(arvere1, 1));
		//
		arvere1 = new BSTImpl<Integer>();
		// assertNull(manipulador.orderStatistic(arvere1, 1));
		// assertNull(manipulador.orderStatistic(arvere1, 2));
		// assertNull(manipulador.orderStatistic(arvere1, -1));
		//
		 arvere1.insert(101);
		// assertNotNull(manipulador.orderStatistic(arvere1, 1));
		//
		// assertTrue(manipulador.orderStatistic(arvere1, 1).equals(101));
		// assertNull(manipulador.orderStatistic(arvere1, 2));
		// assertNull(manipulador.orderStatistic(arvere1, -1));

		arvere1.insert(51);
		arvere1.insert(26);
		assertNotNull(manipulador.orderStatistic(arvere1, 1));
		assertTrue(manipulador.orderStatistic(arvere1, 1).equals(26));
 		assertNotNull(manipulador.orderStatistic(arvere1, 2));
		assertTrue(manipulador.orderStatistic(arvere1, 2).equals(51));
		assertNotNull(manipulador.orderStatistic(arvere1, 3));
		assertTrue(manipulador.orderStatistic(arvere1, 3).equals(101));
		assertNull(manipulador.orderStatistic(arvere1, 4));
		assertNull(manipulador.orderStatistic(arvere1, -1));

		arvere1.insert(76);
		arvere1.insert(61);
		arvere1.insert(91);
		arvere1.insert(86);
		arvere1.insert(100);
		arvere1.insert(151);
		arvere1.insert(126);
		assertNotNull(manipulador.orderStatistic(arvere1, 1));
		assertTrue(manipulador.orderStatistic(arvere1, 1).equals(26));
		assertNotNull(manipulador.orderStatistic(arvere1, 5));
		assertTrue(manipulador.orderStatistic(arvere1, 5).equals(86));
		assertNotNull(manipulador.orderStatistic(arvere1, 6));
		assertTrue(manipulador.orderStatistic(arvere1, 6).equals(91));
		assertNotNull(manipulador.orderStatistic(arvere1, 8));
		assertTrue(manipulador.orderStatistic(arvere1, 8).equals(101));
		assertNull(manipulador.orderStatistic(arvere1, 14));
		assertNull(manipulador.orderStatistic(arvere1, -1));

		arvere1.insert(181);
		arvere1.insert(102);
		arvere1.insert(136);
		arvere1.insert(131);
		arvere1.insert(141);
		assertNotNull(manipulador.orderStatistic(arvere1, 1));
		assertTrue(manipulador.orderStatistic(arvere1, 1).equals(26));
		assertNotNull(manipulador.orderStatistic(arvere1, 5));
		assertTrue(manipulador.orderStatistic(arvere1, 5).equals(86));
		assertNotNull(manipulador.orderStatistic(arvere1, 6));
		assertTrue(manipulador.orderStatistic(arvere1, 6).equals(91));
		assertNotNull(manipulador.orderStatistic(arvere1, 8));
		assertTrue(manipulador.orderStatistic(arvere1, 8).equals(101));
		assertNotNull(manipulador.orderStatistic(arvere1, 11));
		assertTrue(manipulador.orderStatistic(arvere1, 11).equals(131));
		assertNotNull(manipulador.orderStatistic(arvere1, 11));
		assertTrue(manipulador.orderStatistic(arvere1, 13).equals(141));
		assertNull(manipulador.orderStatistic(arvere1, 18));
		assertNull(manipulador.orderStatistic(arvere1, -1));
	}
}