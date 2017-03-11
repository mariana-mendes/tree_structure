package adt.bst;
 
public interface SimpleBSTManipulation<T extends Comparable<T>> {
 
	/**
	 * Diz se uma BST tree1 eh igual a outra BST tree2. As duas BSTs devem ter 
	 * exatamente os mesmos nós e os mesmos formatos. Este metodo DEVE ser implementado
	 * usando recursão. Voce não pode colocar elementos em arrays e depois comparar os arrays. 
	 */
	public boolean equals(BST<T> tree1, BST<T> tree2);
 
	/**
	 * Diz se uma BST tree1 eh similar a outra BST tree2. Duas BSTs sao similares 
	 * se elas possuem o mesmo formato (topologia). O conteudo de cada no é irrelevante.
	 * Este metodo DEVE ser implementado usando recursao. 
	 * Voce não pode colocar elementos em arrays e depois comparar os arrays.
	 */
	public boolean isSimilar(BST<T> tree1, BST<T> tree2);
 
	/**
	 * A K-esima estatistica de ordem de um BST eh o k-esimo menor elemento que esta 
	 * na BST. Este metodo usa a BST para calcular a k-esima estatistica de ordem informada 
	 * no parametro k (variando de 1 ate N). Por exemplo, k = 1 pede para calcular a 1a estatistica de ordem, que eh o 
	 * elemento minimo da BST. k = n peda para calcular a ultima estatistica de ordem que eh
	 * o elemento maximo da BST e assim por diante. Considere o seguinte para implementar
	 * o metodo:
	 *  - Uso OBRIGATORIO de recursao 
	 *  - NÃO pode produzir array e depois selecionar elemento especifico do array
	 *  - retornar null e a k-esima estatistica de ordem nao esta presente na BST.
	 * @param k
	 * @return
	 */
	public T orderStatistic(BST<T> tree, int k);
	
	
	
}

