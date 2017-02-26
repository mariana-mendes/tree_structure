package adt.heap;

import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Essa comparação não é feita diretamente com os elementos armazenados,
 * mas sim usando um comparator. Dessa forma, dependendo do comparator, a heap
 * pode funcionar como uma max-heap ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] resp = (T[]) new Comparable[index + 1];
		for (int i = 0; i <= index; i++) {
			resp[i] = this.heap[i];
		}
		return resp;
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int position) {
		if (position > -1 && position < index) {

			if (left(position) < index) {
				if (biggerThan(position)){
					int swap = indexBiggest(left(position), right(position));
					Util.swap(heap, swap, position);
					heapify(swap);
				}
			}else if (left(position) == index) {
				if ((comparator.compare(this.heap[position], this.heap[left(position)]) < 0)) {
					Util.swap(heap, position, left(position));
				}
			}
		}
	}

	private boolean biggerThan(int i) {
		return ((comparator.compare(this.heap[i], this.heap[left(i)])) < 0
				|| (comparator.compare(this.heap[i], this.heap[right(i)])) < 0);

	}

	private int indexBiggest(int i, int j) {
		if (this.comparator.compare((this.heap[i]), this.heap[j]) < 0) {
			return j;
		}
		return i;

	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		if (element != null) {
			this.heap[++index] = element;

			int i = index;

			while (i != 0 && (this.comparator.compare(element, heap[this.parent(i)])) > 0) {
				Util.swap(heap, i, this.parent(i));
				i = this.parent(i);

			}
		}

	}

	// Precisamos aplicar o heapify apenas na metade do array, pois todos os
	// elementos A[n/2 + i]; i = 1,2,3.. sao folhas, sendo assim nao podem
	// 'descer' mais na arvores
	@Override
	public void buildHeap(T[] array) {
		if (array != null && array.length > 0) {
			this.heap = array;
			this.index = array.length - 1;
		}

		for (int i =(array.length / 2) ;  i >= 0; i--) {
			heapify(i);
		}
	}

	@Override
	public T extractRootElement() {
		if (!this.isEmpty()) {
			T remove = this.heap[0];
			Util.swap(heap, 0, index);
			index--;
			heapify(0);
			return remove;
		}
		return null;
	}

	@Override
	public T rootElement() {
		if (!this.isEmpty()) {
			return this.heap[0];
		}
		return null;
	}

	@Override
	public T[] heapsort(T[] array) {

		Comparator<T> save = this.getComparator();

		this.comparator = new Comparator<T>() {

			@Override
			public int compare(T o1, T o2) {
				return o1.compareTo(o2);
			}

		};

		this.buildHeap(array);
		T[] sorted = (T[]) new Comparable[heap.length];

		for (T t : array) {
			sorted[index] = this.extractRootElement();
		}

		this.comparator = save;
		return sorted;
	}

	@Override
	public int size() {
		return this.index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
