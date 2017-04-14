//package declaration
package ch.nolix.common.container;

//own imports
import ch.nolix.common.constants.StringManager;
import ch.nolix.common.exception.Argument;
import ch.nolix.common.exception.InvalidArgumentException;
import ch.nolix.common.exception.EmptyArgumentException;
import ch.nolix.common.exception.ErrorPredicate;
import ch.nolix.common.functional.IElementTakerComparableGetter;
import ch.nolix.common.functional.IElementTakerRunner;
import ch.nolix.common.functional.IElementTakerBooleanGetter;
import ch.nolix.common.functional.IElementTakerElementGetter;
import ch.nolix.common.helper.CharacterHelper;
import ch.nolix.common.helper.IterableHelper;
import ch.nolix.common.interfaces.Clearable;
import ch.nolix.common.zetaValidator.ZetaValidator;

//class
/**
 * A list is a container that can add elements at the begin or at the end.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 920
 * @param <E> - The type of the elements of a list.
 */
public class List<E>
implements
	Clearable,
	IContainer<E> {
	
	//attributes
	private ListNode<E> firstNode;
	private ListNode<E> lastNode;
	private int count = 0;
	
	//constructor
	/**
	 * Creates new empty list.
	 * The complexity of this method is O(1).
	 */
	public List() {}
	
	//constructor
	/**
	 * Creates new list with the given element.
	 * The complexity of this method is O(1).
	 * 
	 * @param element
	 * @throws NullArgumentException if the given element is null.
	 */
	public List(final E element) {
		addAtEnd(element);
	}
	
	//constructor
	/**
	 * Creates new list with the given elements.
	 * The complexity of this method is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @throws NullArgumentException if the given element container is null.
	 * @throws NullArgumentException if one of the given elements is null.
	 */
	@SuppressWarnings("unchecked")
	public List(final E... elements) {
		addAtEnd(elements);
	}
	
	//constructor
	/**
	 * Creates new list with the given elements.
	 * The complexity of this method is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @throws NullArgumentException if the given element container is null.
	 * @throws NullArgumentException if one of the given elements is null.
	 */
	public List(final Iterable<E> elements) {
		addAtEnd(elements);
	}
	
	//method
	/**
	 * Adds the given element at the begin of this list.
	 * The complexity of this method is O(1).
	 * 
	 * @param element
	 * @return this list.
	 * @throws NullArgumentException if the given element is null.
	 */
	public List<E> addAtBegin(E element) {
		
		//Creates new node.
		final ListNode<E> node = new ListNode<E>(element);
		
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
	 * The complexity of this method is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @return this list.
	 * @throws NullArgumentException if the given element container is null.
	 * @throws NullArgumentException if one of the given elements is null.
	 */
	@SuppressWarnings("unchecked")
	public List<E> addAtBegin(final E... elements) {
		
		//Checks if the given element container is not null.
		ZetaValidator.supposeThat(elements).thatIsNamed("element container").isNotNull();
		
		//Iterates the given elements.
		for (int i = elements.length; i >= 0; i--) {
			addAtBegin(elements[i]);
		}
		
		return this;
	}
	
	//method
	/**
	 * Adds the given elements at the begin of this list.
	 * The complexity of this method is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @return this list.
	 * @throws NullArgumentException if the given element container is null.
	 * @throws NullArgumentException if one of the given elements is null.
	 */
	public List<E> addAtBegin(final Iterable<E> elements) {
		
		//Checks if the given element container is not null.
		ZetaValidator.supposeThat(elements).thatIsNamed("element container").isNotNull();
		
		if (!IterableHelper.isEmpty(elements)) {
			
			ListNode<E> preNode = new ListNode<E>(null);
			
			ListNode<E> iterator = preNode;
			for (final E e: elements) {
				iterator.setElement(e);
				iterator.setNextNode(new ListNode<E>(null));
				iterator = iterator.getNextNode();
				count++;
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
	 * Adds the given element at the begin of this list with regarding singularity.
	 * The complexity of this method is O(n) if this list contains n elements.
	 * 
	 * @param element
	 * @return this list.
	 * @throws NullArgumentException if the given element is null.
	 * @throws InvalidArgumentException if this list contains already the given element.
	 */
	public List<E> addAtBeginRegardingSingularity(final E element) {
		
		//Checks if this list contains already the given element.
		if (contains(element)) {
			throw new InvalidArgumentException(
				new Argument(element),
				new ErrorPredicate("is already contained by the list")
			);
		}
		
		return addAtBegin(element);
	}
	
	//method
	/**
	 * Adds the given element at the end of this list.
	 * The complexity of this method is O(1).
	 * 
	 * @param element
	 * @return this list.
	 * @throws NullArgumentException if the given element is null.
	 */
	public List<E> addAtEnd(final E element) {
		
		//Creates new node.
		final ListNode<E> node = new ListNode<E>(element);
		
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
	 * The complexity of this method is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @return this list.
	 * @throws NullArgumentException if the given element container is null.
	 * @throws NullArgumentException if one of the given elements is null.
	 */
	@SuppressWarnings("unchecked")
	public List<E> addAtEnd(final E... elements) {
		
		//Checks if the given element container is not null.
		ZetaValidator.supposeThat(elements).thatIsNamed("element container").isNotNull();
		
		//Iterates the given elements.
		for (final E e: elements) {
			addAtEnd(e);
		}
		
		return this;
	}
	
	//method
	/**
	 * Adds the given elements at the end of this list.
	 * The complexity of this method is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @return this list.
	 * @throws NullArgumentException if the given element container is null.
	 * @throws NullArgumentException if one of the given elements is null.
	 */
	public List<E> addAtEnd(final Iterable<E> elements) {
		
		//Checks if the given element container is not null.
		ZetaValidator.supposeThat(elements).thatIsNamed("element container").isNotNull();
		
		elements.forEach(e -> addAtEnd(e));
		
		return this;
	}
	
	//method
	/**
	 * Adds the given element at the begin of this list with regarding singularity.
	 * The complexity of this method is O(n) if this list contains n elements.
	 * 
	 * @param element
	 * @return this list.
	 * @throws NullArgumentException if the given element is null.
	 * @throws InvalidArgumentException if this list contains already the given element.
	 */
	public List<E> addAtEndRegardingSingularity(final E element) {
		
		//Checks if this list contains already the given element.
		if (contains(element)) {
			throw new InvalidArgumentException(
				new Argument(element),
				new ErrorPredicate("is already contained by the list")
			);
		}
		
		return addAtEnd(element);
	}
		
	//method
	/**
	 * Removes all elements of this list.
	 * The complexity of this method is O(n) when this list contains n elements.
	 */
	public void clear() {
		
		if (!isEmpty()) {
			
			ListNode<E> iterator = firstNode;		
			while (iterator.hasNextNode()) {
				final ListNode<E> nextNode = iterator.getNextNode();
				iterator.removeNextNode();
				iterator = nextNode;
			}
			
			firstNode = null;
			lastNode = null;
			count = 0;
			
			System.gc();
		}
	}
	
	//method
	/**
	 * @return true if this list contains any element.
	 */
	public boolean containsAny() {
		
		//Calls the method of the desired interface this list implements.
		return IContainer.super.containsAny();
	}
	
	//method
	/**
	 * Runs the given runner on each element of this list.
	 * The complexity of this method is O(n) when this list contains n elements.
	 * 
	 * @param runner
	 * @return this list.
	 */
	public List<E> forEachAndGetList(final IElementTakerRunner<E> runner) {
		
		//Iterates this list.
		for (final E e: this) {
			runner.run(e);
		}
		
		return this;
	}
	
	//method
	/**
	 * The complexity of this method is O(n) when this list contains n elements.
	 * 
	 * @param selector
	 * @return all elements the given selector selects from this list.
	 */
	public List<E> getAll(final IElementTakerBooleanGetter<E> selector) {
		
		//Creates list.
		final List<E> elements = new List<E>();
		
		//Fills up the list with the elements the given selector selects from this list.
		for (final E e: this) {
			if (selector.getOutput(e)) {
				elements.addAtEnd(e);
			}
		}
		
		return elements;
	}
		
	//method
	/**
	 * The complexity of this method is O(n) if this list contains n elements.
	 * 
	 * @return a new list containing the elements of this list.
	 */
	public List<E> getCopy() {
		return toContainer(e -> e);
	}
	
	//method
	/**
	 * The complexity of this method is O((n-m)*m) if:
	 * -This list contains n elements.
	 * -The sequences that matches the given sequence pattern contain m elements.
	 * 
	 * @param sequencePattern
	 * @return the number of sequences from this list that match the given sequence pattern.
	 */
	public int getCount(final SequencePattern<E> sequencePattern) {
		return getSequences(sequencePattern).getSize();
	}

	//method
	/**
	 * The complexity of this method is O((n-m)*m) if:
	 * -This list contains n elements.
	 * -The sequences that matches the given sequence pattern contain m elements.
	 * 
	 * @param sequencePattern
	 * @return the ratio of the sequences from this list that match the given sequence pattern.
	 * @throws EmptyArgumentException if this list is empty.
	 */
	public double getRatio(final SequencePattern<E> sequencePattern) {
		
		//Checks if this list is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(new Argument(this));
		}
		
		return ((double)getCount(sequencePattern) / getSize());
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return the first element of this list
	 * @throws EmptyArgumentException if this list is empty.
	 */
	public E getRefFirst() {
		
		//Checks if this list is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(new Argument(this));
		}
		
		return firstNode.getElement();
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return the last element of this list.
	 * @throws EmptyArgumentException if this list is empty.
	 */
	public E getRefLast() {
		
		//Checks if this list is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(new Argument(this));
		}
		
		return lastNode.getElement();
	}
	
	//method
	/**
	 * The complexity of this method is O((n-m)*m) if:
	 * -This list contains n elements.
	 * -The sequences that matches the given sequence pattern contain m elements.
	 * 
	 * @param sequencePattern
	 * @return the sequences from this list that match the given sequence pattern.
	 */
	public List<List<E>> getSequences(final SequencePattern<E> sequencePattern) {
		return sequencePattern.getSequences(this);
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return the number of elements of this list.
	 */
	public int getSize() {
		return count;
	}
	
	//method
	/**
	 * This method uses the merge sort algorithm.
	 * The complexity of this method is O(n*log(n)) if this list contains n elements.
	 * 
	 * @param comparator
	 * @return a new list with the elements of this list sorted from smallest to biggest according to the given norm.
	 */
	public <E2> List<E> getSorted(final IElementTakerComparableGetter<E, E2> norm) {
		
		//Handles the case if this list is empty.
		if (isEmpty()) {
			return new List<E>();
		}
		
		//Handles the case if this list is not empty.
		return getSortedSubList(1, getSize(), norm);
	}
	
	//method
	/**
	 * @return true if this list is empty
	 */
	public boolean isEmpty() {
		
		//Calls the method of the desired interface of this list.
		return IContainer.super.isEmpty();
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return a new iterator of this list.
	 */
	public ListIterator<E> iterator() {
		return new ListIterator<E>(firstNode);
	}
	
	//method
	/**
	 * Removes all elements the given selector selects from this list.
	 * The complexity of this method is O(n) if this list contains n elements.
	 * 
	 * @param selector
	 * @return this list.
	 */
	public List<E> removeAll(final IElementTakerBooleanGetter<E> selector) {
		
		final List<E> list = getAll(e -> !selector.getOutput(e));
		clear();
		addAtEnd(list);
		
		return this;
	}
	
	//method
	/**
	 * Removes and returns the first element of this list.
	 * The complexity of this method is O(1).
	 * 
	 * @return the first element of this list.
	 * @throws EmptyArgumentException if this list is empty.
	 */
	public E removeAndGetRefFirst() {
		final E element = getRefFirst();
		removeFirst();
		return element;
	}
	
	//method
	/**
	 * Removes and returns the first element the given selector selects from this list.
	 * The complexity of this method is O(n) if this list contains n elements.
	 * 
	 * @param selector
	 * @return the first element the given selector selects from this list.
	 * @throws InvalidArgumentException if this list contains no element the given selector selects.
	 */
	public E removeAndGetRefFirst(final IElementTakerBooleanGetter<E> selector) {
		E element = getRefFirst(selector);
		removeFirst(selector);
		return element;
	}
	
	//method
	/**
	 * Removes and returns the last element of this list.
	 * The complexity of this method is O(1).
	 * 
	 * @return the last element of this list.
	 * @throws EmptyArgumentException if this list is empty.
	 */
	public E removeAndGetRefLast() {
		E element = getRefLast();
		removeLast();
		return element;
	}
		
	//method
	/**
	 * Removes the first element of this list.
	 * The complexity of this method is O(1).
	 * 
	 * @return this list.
	 * @throws EmptyArgumentException if this list is empty.
	 */
	public List<E> removeFirst() {
		
		//Checks if this list is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(new Argument(this));
		}
		
		//Handles the case if this list contains 1 element.
		if (containsOne()) {
			clear();
		}
		
		//Handles the case if this list contains several elements.
		else {
			firstNode = firstNode.getNextNode();
			count--;
		}
		
		return this;
	}
	
	//method
	/**
	 * Removes the first appearance of the given element from this list.
	 * The complexity of this method is O(n) if this list contains n elements.
	 * 
	 * @param element
	 * @return this list.
	 * @throws InvalidArgumentException if this list does not contain the given element.
	 */
	public List<E> removeFirst(final E element) {
		
		//Checks if this list is not empty.
		if (isEmpty()) {
			throw new InvalidArgumentException(
				new Argument(this),
				new ErrorPredicate("contains not '" + element + "'")
			);
		}
		
		if (firstNode.contains(element)) {
			return removeFirst();
		}

		ListNode<E> iterator = firstNode;
		while (iterator.hasNextNode()) {
			
			final ListNode<E> nextNode = iterator.getNextNode();
			
			if (nextNode.contains(element)) {
				removeNextNode(iterator);
				return this;
			}
			
			iterator = nextNode;
		}
		
		throw new InvalidArgumentException(
			new Argument(this),
			new ErrorPredicate("contains not '" + element + "'")
		);
	}
	
	//method
	/**
	 * Removes the first element the given selector selects from this list.
	 * The complexity of this method is O(n) if this list contains n elements.
	 * 
	 * @param selector
	 * @return this list.
	 * @throws InvalidArgumentException if this list contains no element the given selector selects.
	 */
	public List<E> removeFirst(final IElementTakerBooleanGetter<E> selector) {
		
		//Checks if this list is not empty.
		if (isEmpty()) {
			throw new InvalidArgumentException(
				new Argument(this),
				new ErrorPredicate("contains no such element")
			);
		}

		if (selector.getOutput(getRefFirst())) {
			return removeFirst();
		}

		ListNode<E> iterator = firstNode;
		while (iterator.hasNextNode()) {	
			
			final ListNode<E> nextNode = iterator.getNextNode();
			
			if (selector.getOutput(nextNode.getElement())) {
				removeNextNode(iterator);
				return this;
			}
			
			iterator = nextNode;
		}
		
		throw new InvalidArgumentException(
			new Argument(this),
			new ErrorPredicate("contains no such element")
		);
	}
	
	//method
	/**
	 * Removes the last element of this list.
	 * The complexity of this method is O(1).
	 * 
	 * @return this list.
	 * @throws EmptyArgumentException if this list is empty.
	 */
	public List<E> removeLast() {
		
		//Checks if this list is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(new Argument(this));
		}
		
		//Handles the case if this list contains 1 element.
		if (containsOne()) {
			clear();
		}
		
		//Handles the case if this list contains several elements.
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
	 * Replaces the first element the given selector selects from this list with the given element.
	 * The complexity of this method is O(n) if this list contains n elements.
	 * 
	 * @param selector
	 * @param element
	 * @return this list.
	 * @throws InvalidArgumentException if this list contains no element the given selector selects.
	 * @throws NullArgumentException if the given element is null.
	 */
	public List<E> replaceFirst(IElementTakerBooleanGetter<E> selector, E element) {
		
		//Checks if this list is not empty.
		if (isEmpty()) {
			throw new InvalidArgumentException(
				new Argument(this),
				new ErrorPredicate("contains not '" + element + "'")
			);
		}
		
		ListNode<E> iterator = firstNode;
		while (true) {
			
			if (selector.getOutput(iterator.getElement())) {
				iterator.setElement(element);
				return this;
			}
			
			if (iterator.hasNextNode()) {
				iterator = iterator.getNextNode();
				continue;
			}
			
			throw new InvalidArgumentException(
				new Argument(this),
				new ErrorPredicate("contains not '" + element + "'")
			);
		}
	}
	
	//method
	/**
	 * Reverses the elements of this list.
	 * The complexity of this method is O(n) if this list contains n elements.
	 * 
	 * @return this list.
	 */
	public List<E> reverse() {
		
		if (!isEmpty()) {
			lastNode = firstNode;
			ListNode<E> iterator = firstNode;
			while (iterator.hasNextNode()) {
				final ListNode<E> node = iterator.getNextNode();
				iterator.setNextNode(firstNode);
				firstNode = iterator;
				iterator = node;
			}
			iterator.setNextNode(firstNode);
			firstNode = iterator;
		}
		
		return this;
	}
	
	//method
	/**
	 * Sorts this list according to the given norm.
	 * This method uses the merge sort algorithm.
	 * The complexity of this method is O(n*log(n)) if this list contains n elements.
	 * 
	 * @param norm
	 * @return this list.
	 */
	public <E2> List<E> sort(IElementTakerComparableGetter<E, E2> norm) {
		
		final List<E> sorted = getSorted(norm);
		clear();
		addAtEnd(sorted);
		
		return this;
	}
	
	//method
	/**
	 * The complexity of this method is O(n) if this list contains n elements.
	 * 
	 * @param transformer
	 * @return a new list with the elements the given transformer transforms from the elements of this list.
	 */
	public <O> List<O> toContainer(IElementTakerElementGetter<E, O> transformer) {
		final List<O> list = new List<O>();
		forEach(e -> list.addAtEnd(transformer.getOutput(e)));
		return list;
	}
	
	//method
	/**
	 * The complexity of this method is O(n) if this list contains n elements.
	 * 
	 * @return a string representation of this list.
	 */
	public String toString() {
		
		String string = StringManager.EMPTY_STRING;
		
		//Iterates this list.
		boolean atBegin = true;
		for (final E e: this) {
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
	
	//method
	/**
	 * @param startIndex
	 * @param endIndex
	 * @return a new list with the elements from the given start index to the given end index sorted according to the given norm.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <E2> List<E> getSortedSubList(
		final int startIndex,
		final int endIndex,
		final IElementTakerComparableGetter<E, E2> norm
	) {
		
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
				Comparable element2Value = norm.getValue(startNode.getNextNode().getElement());
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
	 * @throws NullArgumentException if the given node is null.
	 * @throws UnexistingPropertyException if the given node has no next node.
	 */
	private void removeNextNode(ListNode<E> node) {
		
		//Checks if the given node is not null.
		ZetaValidator.supposeThat(node).thatIsNamed("node").isNotNull();
		
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
