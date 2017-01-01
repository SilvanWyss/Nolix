/*
 * file:	List.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	810
 */

//package declaration
package ch.nolix.common.container;

//own imports
import ch.nolix.common.constants.StringManager;
import ch.nolix.common.functional.IElementTakerComparableGetter;
import ch.nolix.common.functional.IElementTakerRunner;
import ch.nolix.common.functional.IElementTakerBooleanGetter;
import ch.nolix.common.functional.IElementTakerElementGetter;
import ch.nolix.common.helper.CharacterHelper;
import ch.nolix.common.helper.IterableHelper;

//class
/**
 * A list is a container that can add elements at the begin or at the end.
 */
public class List<E> implements IContainer<E> {
	
	//attributes
	private ListNode<E> firstNode;
	private ListNode<E> lastNode;
	private int count = 0;
	
	//constructor
	/**
	 * Creates new empty list.
	 * The complexity of this implementation is O(1).
	 */
	public List() {}
	
	//constructor
	/**
	 * Creates new list that contains the given elements.
	 * The complexity of this implementation is O(n) if n elements are given.
	 * 
	 * @param elements
	 */
	public List(Iterable<E> elements) {
		addAtEnd(elements);
	}
	
	//method
	/**
	 * Adds the given element at the begin of this list.
	 * The complexity of this implementation is O(1).
	 * 
	 * @param element
	 * @return this list
	 * @throws Exception if the given element is null
	 */
	public List<E> addAtBegin(E element) {
		
		ListNode<E> node = new ListNode<E>(element);
		if (isEmpty()) {
			firstNode = node;
			lastNode = node;
		}
		else {
			node.setNextNode(firstNode);
			firstNode = node;
		}
		count++;
		
		return this;
	}
	
	//method
	/**
	 * Adds the given elements at the begin of this list.
	 * The complexity of this implementation is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @return this list
	 * @throws Exception if one of the given elements is null
	 */
	@SuppressWarnings("unchecked")
	public List<E> addAtBegin(E... elements) {
		
		for (int i = elements.length; i >= 0; i--) {
			addAtEnd(elements[i]);
		}
		
		return this;
	}
	
	//method
	/**
	 * Adds the given element at the begin of this list regarding singularity.
	 * The complexity of this implementation is O(n) if this list contains n elements.
	 * 
	 * @param element
	 * @return this list
	 * @throws Exception if:
	 * -the given element is null
	 * -this list already contains the given element
	 */
	public List<E> addAtBeginRegardingSingularity(E element) {
		
		if (contains(element)) {
			throw new RuntimeException("List already contains the element '" + element.toString() + "'.");
		}
		
		return addAtBegin(element);
	}
	
	//method
	/**
	 * Adds the given element at the end of this list.
	 * The complexity of this implementation is O(1).
	 * 
	 * @param element
	 * @return this list
	 * @throws Exception if the given element is null
	 */
	public List<E> addAtEnd(E element) {
		
		ListNode<E> node = new ListNode<E>(element);
		if (isEmpty()) {
			firstNode = node;
			lastNode = node;
		}
		else {
			lastNode.setNextNode(node);
			lastNode = node;
		}
		count++;
		
		return this;
	}
	
	//method
	/**
	 * Adds the given elements at the end of this list.
	 * The complexity of this implementation is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @return this list
	 * @throws Exception if one of the given elements is null
	 */
	@SuppressWarnings("unchecked")
	public List<E> addAtEnd(E... elements) {
		
		//Iterates the given elements.
		for (E e: elements) {
			addAtEnd(e);
		}
		
		return this;
	}
	
	//method
	/**
	 * Adds the given element at the end of this list regarding singularity.
	 * The complexity of this implementation is O(n) if this list contains n elements.
	 * 
	 * @param element
	 * @return this list
	 * @throws Exception if:
	 * -the given element is null
	 * -this list already contains the given element 
	 */
	public List<E> addAtEndRegardingSingularity(E element) {
		
		if (contains(element)) {
			throw new RuntimeException("List already contains the element '" + element.toString() + "'.");
		}
		
		return addAtEnd(element);
	}
	
	//method
	/**
	 * Adds the given elements at the begin of this list.
	 * The complexity of this implementation is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @return this list
	 */
	public List<E> addAtBegin(Iterable<E> elements) {
		
		if (!IterableHelper.isEmpty(elements)) {
			
			ListNode<E> preNode = new ListNode<E>(null);
			
			ListNode<E> iterator = preNode;
			for (E e: elements) {
				count++;
				iterator.setElement(e);
				iterator.setNextNode(new ListNode<E>(null));
				iterator = iterator.getNextNode();
			}
			
			this.firstNode = preNode.getNextNode();
			
			if (lastNode == null) {
				lastNode = iterator;
			}
		}
		
		return this;
	}
	
	//method
	/**
	 * Adds the the given elements at the end of this list.
	 * The complexity of this implementation is O(n) when n elements are given.
	 * 
	 * @param elements
	 * @return this list
	 */
	public List<E> addAtEnd(Iterable<E> elements) {
		
		elements.forEach(e -> addAtEnd(e));
		
		return this;
	}
	
	//method
	/**
	 * Removes all elements of this list.
	 * The complexity of this implementation is O(n) when this list contains n elements.
	 */
	public List<E> clear() {
		
		if (!isEmpty()) {
			
			ListNode<E> iterator = firstNode;		
			while (iterator.hasNextNode()) {
				ListNode<E> nextNode;
				nextNode = iterator.getNextNode();
				iterator.removeNextNode();
				iterator = nextNode;
			}
			
			firstNode = null;
			lastNode = null;
			count = 0;
			
			System.gc();
		}
		
		return this;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @return true if this list contains any element
	 */
	public boolean containsAny() {
		return (firstNode != null);
	}
	
	public List<E> foreach(IElementTakerRunner<E> runner) {
		
		for (E e: this) {
			runner.run(e);
		}
		
		return this;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) when this list contains n elements.
	 * 
	 * @param selector
	 * @return all elements of this list the given selector selects
	 */
	public List<E> getAll(IElementTakerBooleanGetter<E> selector) {
		
		List<E> selectedElements = new List<E>();
		
		for (E t: this) {
			if (selector.getOutput(t)) {
				selectedElements.addAtEnd(t);
			}
		}
		
		return selectedElements;
	}
		
	//method
	/**
	 * Returns and removes the first element of this list.
	 * 
	 * @return the first element of this list
	 * @throws Exception if this list is empty
	 */
	public E getAndRemoveFirst() {
		E element = getRefFirst();
		removeFirst();
		return element;
	}
	
	//default method
	/**
	 * Returns and removes the first element the given selector selects from this list.
	 * 
	 * @param selector
	 * @return the first element the given selector selects
	 * @throws Exception if this list contains no element the given selector selects
	 */
	public E getAndRemoveFirst(IElementTakerBooleanGetter<E> selector) {
		E element = getRefFirst(selector);
		removeFirst(selector);
		return element;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if this list contains n elements.
	 * 
	 * @return a new list containing the elements of this list
	 */
	public List<E> getCopy() {
		return toContainer(e -> e);
	}
	
	//method
	/**
	 * @param sequencePattern
	 * @return the number of sequences from this list that match the given sequence pattern
	 */
	public int getCount(final SequencePattern<E> sequencePattern) {
		return getSequences(sequencePattern).getSize();
	}

	//method
	/**
	 * @param sequencePattern
	 * @return  the ratio of the sequences from this list that match the given sequence pattern
	 * @throws Exception if this list is empty
	 */
	public double getRatio(final SequencePattern<E> sequencePattern) {
		
		if (isEmpty()) {
			throw new RuntimeException("Container is empty.");
		}
		
		return ((double)getCount(sequencePattern) / getSize());
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @return the first element of this list
	 * @throws Exception if this list is empty
	 */
	public E getRefFirst() {
		
		for (E e: this) {
			return e;
		}
		
		throw new RuntimeException("List is empty.");
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @return the last element of this list
	 * @throws Exception if this container is empty
	 */
	public E getRefLast() {
		
		if (isEmpty()) {
			throw new RuntimeException("List is empty.");
		}
		
		return lastNode.getElement();
	}
	
	//method
	/**
	 * @param sequencePattern
	 * @return the sequences from this list that match the given sequence pattern
	 */
	public List<List<E>> getSequences(final SequencePattern<E> sequencePattern) {
		return sequencePattern.getSequences(this);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @return the number of elements of this list
	 */
	public int getSize() {
		return count;
	}
	
	//method
	/**
	 * This implementation uses the merge sort algorithm.
	 * The complexity of this implementation is O(n*log(n)) when this list contains n elements.
	 * 
	 * @param comparator
	 * @return a new list containing the elements of this list sorted from smallest to biggest according to the given norm
	 */
	public <E2> List<E> getSorted(IElementTakerComparableGetter<E, E2> norm) {
		
		if (isEmpty()) {
			return new List<E>();
		}
		
		return getSortedSubList(1, getSize(), norm);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @return true if this list is empty
	 */
	public boolean isEmpty() {
		return (firstNode == null);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @return an iterator for this list
	 */
	public ListIterator<E> iterator() {
		return new ListIterator<E>(firstNode);
	}
	
	//default method
	/**
	 * @param sequencePattern
	 * @return true if this list matches the given sequence pattern
	 */
	public boolean matches(final SequencePattern<E> sequencePattern) {
		return sequencePattern.matches(this);
	}
	
	//method
	/**
	 * Removes all elements the given selector selects from this list.
	 * The complexity of this implementation is O(n) when this list contains n elements.
	 * 
	 * @param selector
	 * @return this list
	 */
	public List<E> removeAll(IElementTakerBooleanGetter<E> selector) {
		
		List<E> temp = getAll(e -> !selector.getOutput(e));
		clear();
		addAtEnd(temp);
		
		return this;
	}
	
	//method
	/**
	 * Removes the first element of this list.
	 * The complexity of this implementation is O(1).
	 * 
	 * @return this list
	 * @throws Exception if this list is empty
	 */
	public List<E> removeFirst() {
		
		if (isEmpty()) {
			throw new RuntimeException("List is empty.");
		}
		
		if (containsOne()) {
			clear();
		}
		else {
			firstNode = firstNode.getNextNode();
			count--;
		}
		
		return this;
	}
	
	//method
	/**
	 * Removes the first appearance of the given element from this itemizable object.
	 * The complexity of this implementation is O(n) when this itemizable object contains n elements.
	 * 
	 * @param element
	 * @throws Exception if this list does not contain the given element
	 */
	public void removeFirst(E element) {
		
		if (isEmpty()) {
			throw new RuntimeException("List contains no such element");
		}
		
		if (firstNode.containsElement(element)) {
			removeFirst();
		}
		
		else if (lastNode.containsElement(element)) {
			removeLast();
		}
		
		else {
			ListNode<E> iterator = firstNode;
			while (iterator.hasNextNode()) {
				try {
					ListNode<E> nextNode = iterator.getNextNode();
					if (nextNode.containsElement(element)) {
						removeNextNode(iterator);
						return;
					}
					iterator = nextNode;
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			throw new RuntimeException("List contains nu such element");
		}
	}
	
	//method
	/**
	 * Removes the first element the given selector selects from this list.
	 * The complexity of this implementation is O(n) when this list contains n elements.
	 * 
	 * @param selector
	 * @return this list
	 */
	public List<E> removeFirst(IElementTakerBooleanGetter<E> selector) {
		
		if (isEmpty()) {
			return this;
		}

		if (selector.getOutput(getRefFirst())) {
			return removeFirst();
		}

		ListNode<E> iterator = firstNode;
		while (iterator.hasNextNode()) {
			
			ListNode<E> nextNode = iterator.getNextNode();

			if (selector.getOutput(nextNode.getElement())) {
				removeNextNode(iterator);
				return this;
			}
			
			iterator = nextNode;
		}
		
		return this;
	}
	
	//method
	/**
	 * Removes the last element of this list.
	 * The complexity of this implementation is O(1).
	 * 
	 * @return this list
	 * @throws Exception if this list is empty
	 */
	public List<E> removeLast() {
		
		if (isEmpty()) {
			throw new RuntimeException("List is empty.");
		}
		
		if (containsOne()) {
			clear();
		}
		else {
			ListNode<E> iterator = firstNode;
			while (iterator.getNextNode() != lastNode) {
				iterator = iterator.getNextNode();
			}
			iterator.removeNextNode();
			lastNode = iterator;
			count--;
		}
		
		return this;
	}
	
	//method
	/**
	 * Replaces the first element the given selector function selects from this list with the given element.
	 * 
	 * @param selector
	 * @param element
	 */
	public void replaceFirst(IElementTakerBooleanGetter<E> selector, E element) {
		
		if (isEmpty()) {
			return;
		}
		
		ListNode<E> iterator = firstNode;
		while (true) {
			if (selector.getOutput(iterator.getElement())) {
				iterator.setElement(element);
				break;
			}
			if (iterator.hasNextNode()) {
				try {
					iterator = iterator.getNextNode();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
				break;
			}			
		}
	}
	
	//method
	/**
	 * Reverses the order of the elements of this list.
	 * The complexity of this implementation is O(n) when this list contains n elements.
	 */
	public void reverse() {
		if (!isEmpty()) {
			lastNode = firstNode;
			ListNode<E> iterator = firstNode;
			while (iterator.hasNextNode()) {
				try {
					ListNode<E> tempNode = iterator.getNextNode();
					iterator.setNextNode(firstNode);
					firstNode = iterator;
					iterator = tempNode;
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			iterator.setNextNode(firstNode);
			firstNode = iterator;
		}
	}
	
	//method
	/**
	 * Sorts this list according to the given norm.
	 * This implementation uses the merge sort algorithm.
	 * The complexity of this implementation is O(n*log(n)) when this list contains n elements.
	 * 
	 * @param norm
	 */
	public <E2> void sort(IElementTakerComparableGetter<E, E2> norm) {
		List<E> sorted = getSorted(norm);
		clear();
		addAtEnd(sorted);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) when this list contains n elements.
	 * 
	 * @param transformer
	 * @return a list containing the elements the given transformer transforms from the elements of this list
	 */
	public <O> List<O> toContainer(IElementTakerElementGetter<E, O> transformer) {
		
		List<O> list = new List<O>();
		
		forEach(e -> list.addAtEnd(transformer.getOutput(e)));
		
		return list;
	}
	
	public <O> List<O> toContainerFromForEach(IElementTakerElementGetter<E, Iterable<O>> x) {
		
		final List<O> list = new List<O>();
		
		forEach(e -> list.addAtEnd(x.getOutput(e)));
		
		return list;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) when this list contains n elements.
	 * 
	 * @return a string representation of this list
	 */
	public String toString() {
		
		String string = StringManager.EMPTY_STRING;
		boolean atBegin = true;
		
		for (E e: this) {
			
			if (atBegin) {
				atBegin = false;
			}
			else {
				string += CharacterHelper.COMMA;
			}
			
			string += e.toString();
		}
		
		return string;
	}
	
	public List<E> _createEmptyContainer() {
		return new List<E>();
	}
	
	public List<IContainer<E>> _createEmptyContainerOfContainers() {
		return new List<IContainer<E>>();
	}
	
	//method
	/**
	 * @param startIndex
	 * @param endIndex
	 * @return a new list containing the elements of the sub list with the given start index and end index sorted according to the given norm
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <E2> List<E> getSortedSubList(int startIndex, int endIndex, IElementTakerComparableGetter<E, E2> norm) {
		
		//Searches for the start node.
		ListNode<E> startNode = firstNode;
		for (int i = 1; i < startIndex; i++) {
			try {
				startNode = startNode.getNextNode();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Calculates the length of the sub list.
		int length = (endIndex - startIndex) + 1;	
		
		//Handles the case when the sub list contains 1 element.
		if (length == 1) {
			List<E> list = new List<E>();
			list.addAtEnd(startNode.getElement());
			return list;
		}
		
		//Handles the case when the sub list contains 2 elements.
		if (length == 2) {
			List<E> list = new List<E>();
			try {
				Comparable element1Value = norm.getValue(startNode.getElement());
				Comparable element2Value = norm.getValue(startNode.getElementOfNextNode());
				if (element1Value.compareTo(element2Value) > 0) {
					list.addAtEnd(startNode.getNextNode().getElement());
					list.addAtEnd(startNode.getElement());					
				}
				else {
					list.addAtEnd(startNode.getElement());
					list.addAtEnd(startNode.getNextNode().getElement());
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}			
			return list;
		}
		
		//Handles the case when the sub list contains more than 2 elements.
		List<E> list = new List<E>();
		int middleIndex = startIndex + length / 2;
		List<E> subList1 = getSortedSubList(startIndex, middleIndex, norm);
		List<E> subList2 = getSortedSubList(middleIndex + 1, endIndex, norm);	
		for (int i = 1; i <= length; i++) {
			try {			
				if (subList1.isEmpty()) {					
					list.addAtEnd(subList2.getRefFirst());
					subList2.removeFirst();
				}
				else if (subList2.isEmpty()) {
					list.addAtEnd(subList1.getRefFirst());
					subList1.removeFirst();
					
				}
				else {
					Comparable value1 = norm.getValue(subList1.getRefFirst());
					Comparable value2 = norm.getValue(subList2.getRefFirst());
					if (value1.compareTo(value2) > 0) {
						list.addAtEnd(subList2.getRefFirst());
						subList2.removeFirst();
					}
					else {					
						list.addAtEnd(subList1.getRefFirst());
						subList1.removeFirst();
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return list;
	}
	
	//method
	/**
	 * Removes the next node of the given node.
	 * 
	 * @param node
	 * @throws Exception if the given node has no next node
	 */
	private void removeNextNode(ListNode<E> node) {
		
		final ListNode<E> nextNode = node.getNextNode();
		
		if (nextNode.hasNextNode()) {
			node.setNextNode(nextNode.getNextNode());
		}
		else {
			node.removeNextNode();
			lastNode = node;
		}
		
		count--;
	}
}
