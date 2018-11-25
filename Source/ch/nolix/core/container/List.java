//package declaration
package ch.nolix.core.container;

import ch.nolix.core.argument.Argument;
import ch.nolix.core.argument.ErrorPredicate;
//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.MultiVariableNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.functionAPI.IElementTaker;
import ch.nolix.core.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.core.functionAPI.IElementTakerComparableGetter;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.EmptyStateException;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A {@link List} is a {@link IContainer} that can add elements at the begin or end.
 * A {@link List} is clearable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 1010
 * @param <E> The type of the elements of a {@link List}.
 */
public final class List<E> implements Clearable<List<E>>, IContainer<E> {
	
	//attribute
	private int elementCount = 0;
	
	//optional attributes
	private ListNode<E> firstNode;
	private ListNode<E> lastNode;
		
	//constructor
	/**
	 * Creates a new {@link List} that is empty.
	 * The complexity of this method is O(1).
	 */
	public List() {}
	
	//constructor
	/**
	 * Creates a new {@link List} with the given element.
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
	 * Creates a new {@link List} with the given elements.
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
	 * Creates a new {@link List} with the given elements.
	 * The complexity of this method is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @throws NullArgumentException if the given element container is null.
	 * @throws NullArgumentException if one of the given elements is null.
	 */
	public <E2 extends E> List(final Iterable<E2> elements) {
		addAtEnd(elements);
	}
	
	//method
	/**
	 * Adds the given element at the begin of the current {@link List}.
	 * The complexity of this method is O(1).
	 * 
	 * @param element
	 * @return the current {@link List}.
	 * @throws NullArgumentException if the given element is null.
	 */
	public List<E> addAtBegin(E element) {
		
		//Creates new node.
		final var node = new ListNode<E>(element);
		
		if (isEmpty()) {
			firstNode = node;
			lastNode = node;
		}
		else {
			node.setNextNode(firstNode);
			firstNode = node;
		}
		elementCount++;
		
		return this;
	}
	
	//method
	/**
	 * Adds the given elements at the begin of the current {@link List}.
	 * The complexity of this method is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @return the current {@link List}.
	 * @throws NullArgumentException if the given elements is null.
	 * @throws NullArgumentException if one of the given elements is null.
	 */
	@SuppressWarnings("unchecked")
	public List<E> addAtBegin(final E... elements) {
		
		//Checks if the given elements is not null.
		Validator.suppose(elements).thatIsNamed(MultiVariableNameCatalogue.ELEMENTS).isInstance();
		
		//Iterates the given elements.
		for (var i = elements.length; i >= 0; i--) {
			addAtBegin(elements[i]);
		}
		
		return this;
	}
	
	//method
	/**
	 * Adds the given elements at the begin of the current {@link List}.
	 * The complexity of this method is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @return the current {@link List}.
	 * @throws NullArgumentException if the given element container is null.
	 * @throws NullArgumentException if one of the given elements is null.
	 */
	public <E2 extends E> List<E> addAtBegin(final Iterable<E2> elements) {
		
		//Checks if the given elements is not null.
		Validator.suppose(elements).thatIsNamed("element container").isInstance();
		
		//Handles the case that the given elements is not empty.
		if (new ReadContainer<E>(elements).isEmpty()) {
			
			final var preNode = new ListNode<E>(null);
			
			var iterator = preNode;
			for (final E e: elements) {
				iterator.setElement(e);
				iterator.setNextNode(new ListNode<E>(null));
				iterator = iterator.getNextNode();
				elementCount++;
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
	 * Adds the given element at the begin of the current {@link List} with regarding singularity.
	 * The complexity of this method is O(n) if the current {@link List} contains n elements.
	 * 
	 * @param element
	 * @return the current {@link List}.
	 * @throws NullArgumentException if the given element is null.
	 * @throws InvalidArgumentException if the current {@link List} contains already the given element.
	 */
	public List<E> addAtBeginRegardingSingularity(final E element) {
		
		//Checks if the current {@link List} contains already the given element.
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
	 * Adds the given element at the end of the current {@link List}.
	 * The complexity of this method is O(1).
	 * 
	 * @param element
	 * @return the current {@link List}.
	 * @throws NullArgumentException if the given element is null.
	 */
	public List<E> addAtEnd(final E element) {
		
		//Creates new node.
		final var node = new ListNode<E>(element);
		
		if (isEmpty()) {
			firstNode = node;
			lastNode = node;
		}
		else {
			lastNode.setNextNode(node);
			lastNode = node;
		}
		elementCount++;
		
		return this;
	}
	
	//method
	/**
	 * Adds the given elements at the end of the current {@link List}.
	 * The complexity of this method is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @return the current {@link List}.
	 * @throws NullArgumentException if the given element container is null.
	 * @throws NullArgumentException if one of the given elements is null.
	 */
	@SuppressWarnings("unchecked")
	public List<E> addAtEnd(final E... elements) {
		
		//Checks if the given element container is not null.
		Validator.suppose(elements).thatIsNamed("element container").isInstance();
		
		//Iterates the given elements.
		for (final E e: elements) {
			addAtEnd(e);
		}
		
		return this;
	}
	
	//method
	/**
	 * Adds the given elements at the end of the current {@link List}.
	 * The complexity of this method is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @return the current {@link List}.
	 * @throws NullArgumentException if the given elements is null.
	 * @throws NullArgumentException if one of the given elements is null.
	 */
	public <E2 extends E> List<E> addAtEnd(final Iterable<E2> elements) {
		
		//Checks if the given elements is not null.
		Validator.suppose(elements).thatIsNamed(MultiVariableNameCatalogue.ELEMENTS).isInstance();
		
		elements.forEach(e -> addAtEnd(e));
		
		return this;
	}
	
	//method
	/**
	 * Adds the elements the given extractor extracts from the given elements
	 * at the end of the current {@link List}.
	 * The complexity of this method is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @param extractor
	 * @return the current {@link List}.
	 */
	public <E2> List<E> addAtEnd(
		final Iterable<E2> elements,
		IElementTakerElementGetter<E2, E> extractor
	) {
		elements.forEach(e -> addAtEnd(extractor.getOutput(e)));
		
		return this;
	}
	
	//method
	/**
	 * Adds the given element at the begin of the current {@link List} with regarding singularity.
	 * The complexity of this method is O(n) if the current {@link List} contains n elements.
	 * 
	 * @param element
	 * @return the current {@link List}.
	 * @throws NullArgumentException if the given element is null.
	 * @throws InvalidArgumentException if the current {@link List} contains already the given element.
	 */
	public List<E> addAtEndRegardingSingularity(final E element) {
		
		//Checks if the current {@link List} contains already the given element.
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
	 * Removes all elements of the current {@link List}.
	 * The complexity of this method is O(n) when the current {@link List} contains n elements.
	 * 
	 * @return the current {@link List}.
	 */
	public List<E> clear() {
		
		//Handles the case that the current list contains any elements.
		if (containsAny()) {
			
			var iterator = firstNode;		
			while (iterator.hasNextNode()) {
				final var nextNode = iterator.getNextNode();
				iterator.removeNextNode();
				iterator = nextNode;
			}
			
			firstNode = null;
			lastNode = null;
			elementCount = 0;
			
			System.gc();
		}
		
		return this;
	}
	
	//method
	/**
	 * @return true if the current {@link List} contains any element.
	 */
	public boolean containsAny() {
		
		//Calls the default method of the required interface.
		return IContainer.super.containsAny();
	}
	
	//method
	/**
	 * An object equals a list if it is a list containing exactly the same elements.
	 * 
	 * @return true if the given object equals the current {@link List}.
	 */
	public boolean equals(Object object) {
		
		//Handles the case that the given object is no list.
		if (!(object instanceof List<?>)) {
			return false;
		}
		
		//Handles the case that the given object is a list.
		
			final var list = (List<?>)object;
			
			if (getSize() != list.getSize()) {
				return false;
			}
			
			return containsAll(list);
	}
	
	//method
	/**
	 * Runs the given runner on each element of the current {@link List}.
	 * The complexity of this method is O(n) when the current {@link List} contains n elements.
	 * 
	 * @param runner
	 * @return the current {@link List}.
	 */
	public List<E> forEachAndGetList(final IElementTaker<E> runner) {
		
		//Iterates the current {@link List}.
		for (final var e: this) {
			runner.run(e);
		}
		
		return this;
	}
		
	//method
	/**
	 * The complexity of this method is O(n) if the current {@link List} contains n elements.
	 * 
	 * @return a new list with the elements of the current {@link List}.
	 */
	public List<E> getCopy() {
		return to(e -> e);
	}
	
	//method
	/**
	 * The complexity of this method is O(n^2) if the current {@link List} contains n elements.
	 * 
	 * @param norm
	 * @return a new list of groups of the elements of the current {@link List}
	 * whereas the value of the given norm is equal for all elements of a group.
	 */
	public <E2> List<List<E>> getGroups(final IElementTakerElementGetter<E, E2> norm) {
		
		final List<List<E>> groups = new List<List<E>>();
		
		//Iterates the current list.
		for (final E e : this) {
			
			final E2 categoryRepresentator = norm.getOutput(e);
			
			final List<E> group
			= groups.getRefFirstOrNull(
				l -> l.contains(e2 -> norm.getOutput(e).equals(categoryRepresentator))
			);
			
			if (group == null) {
				groups.addAtEnd(new List<E>(e));
			}
			else {
				group.addAtEnd(e);
			}
		}
		
		return groups;
	}

	//method
	/**
	 * The complexity of this method is O((n-m)*m) if:
	 * -This list contains n elements.
	 * -The sequences that matches the given sequence pattern contain m elements.
	 * 
	 * @param sequencePattern
	 * @return the ratio of the sequences from the current {@link List} that match the given sequence pattern.
	 * @throws EmptyStateException if the current {@link List} is empty.
	 */
	public double getRatio(final SequencePattern<E> sequencePattern) {
		
		//Checks if the current list is not empty.
		if (isEmpty()) {
			throw new EmptyStateException(this);
		}
		
		return ((double)getSequenceCount(sequencePattern) / getSize());
	}
		
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return the last element of the current {@link List}.
	 * @throws EmptyStateException if the current {@link List} is empty.
	 */
	public E getRefLast() {
		
		//Checks if the current list is not empty.
		if (isEmpty()) {
			throw new EmptyStateException(this);
		}
		
		return lastNode.getElement();
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return the last element of the current {@link List} or null.
	 */
	public E getRefLastOrNull() {
		
		//Handles the case that the current list is empty.
		if (isEmpty()) {
			return null;
		}
		
		//Handles the case that the current list is not empty.
		return getRefLast();
	}
	
	//method
	/**
	 * The complexity of this method is O((n-m)*m) if:
	 * -This list contains n elements.
	 * -The sequences that matches the given sequence pattern contain m elements.
	 * 
	 * @param sequencePattern
	 * @return the number of sequences from the current {@link List} that match the given sequence pattern.
	 */
	public int getSequenceCount(final SequencePattern<E> sequencePattern) {
		return getSequences(sequencePattern).getSize();
	}
	
	//method
	/**
	 * The complexity of this method is O((n-m)*m) if:
	 * -This list contains n elements.
	 * -The sequences that matches the given sequence pattern contain m elements.
	 * 
	 * @param sequencePattern
	 * @return the sequences from the current {@link List} that match the given sequence pattern.
	 */
	public List<List<E>> getSequences(final SequencePattern<E> sequencePattern) {
		return sequencePattern.getSequences(this);
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return the number of elements of the current {@link List}.
	 */
	public int getSize() {
		return elementCount;
	}
	
	//method
	/**
	 * @return true if the current {@link List} is empty
	 */
	public boolean isEmpty() {
		
		//Calls the method of the desired interface of the current list.
		return IContainer.super.isEmpty();
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return a new iterator of the current {@link List}.
	 */
	public ListIterator<E> iterator() {
		return new ListIterator<E>(firstNode);
	}
	
	//method
	/**
	 * The complexity of this method is O(n) if the current {@link List} contains n elements.
	 * 
	 * @param sequencePattern
	 * @return true if the current {@link List} matches the given sequence pattern.
	 */
	public boolean matches(final SequencePattern<E> sequencePattern) {
		return sequencePattern.matches(this);
	}
	
	//method
	/**
	 * Orders the elements of the current {@link List} according to the given norm.
	 * This method uses the merge sort algorithm.
	 * The complexity of this method is O(n*log(n)) if the current {@link List} contains n elements.
	 * 
	 * @param norm
	 * @return the current {@link List}.
	 */
	public <E2> List<E> order(final IElementTakerComparableGetter<E, E2> norm) {
		
		//Handles the case that the current list contains any elements.
		if (containsAny()) {
			final var orderedList = getOrderedSubList(1, getSize(), norm);
			clear();
			addAtEnd(orderedList);
		}
		
		return this;
	}
	
	//method
	/**
	 * Removes all elements the given selector selects from the current {@link IList}.
	 * The complexity of this method is O(n) if the current {@link List} contains n elements.
	 * 
	 * @param selector
	 * @return the current {@link List}.
	 */
	public List<E> removeAll(final IElementTakerBooleanGetter<E> selector) {
		
		final List<E> list = getRefSelected(e -> !selector.getOutput(e));
		clear();
		addAtEnd(list);
		
		return this;
	}
	
	//method
	/**
	 * Removes and returns the first element of the current {@link List}.
	 * The complexity of this method is O(1).
	 * 
	 * @return the first element of the current {@link List}.
	 * @throws EmptyArgumentException if the current {@link List} is empty.
	 */
	public E removeAndGetRefFirst() {
		final E element = getRefFirst();
		removeFirst();
		return element;
	}
	
	//method
	/**
	 * Removes and returns the first element the given selector selects from the current {@link List}.
	 * The complexity of this method is O(n) if the current {@link List} contains n elements.
	 * 
	 * @param selector
	 * @return the first element the given selector selects from the current {@link List}.
	 * @throws InvalidArgumentException if the current {@link List} contains no element the given selector selects.
	 */
	public E removeAndGetRefFirst(final IElementTakerBooleanGetter<E> selector) {
		E element = getRefFirst(selector);
		removeFirst(selector);
		return element;
	}
	
	//method
	/**
	 * Removes and returns the last element of the current {@link List}.
	 * The complexity of this method is O(1).
	 * 
	 * @return the last element of the current {@link List}.
	 * @throws EmptyArgumentException if the current {@link List} is empty.
	 */
	public E removeAndGetRefLast() {
		E element = getRefLast();
		removeLast();
		return element;
	}
		
	//method
	/**
	 * Removes the first element of the current {@link List}.
	 * The complexity of this method is O(1).
	 * 
	 * @return the current {@link List}.
	 * @throws EmptyStateException if the current {@link List} is empty.
	 */
	public List<E> removeFirst() {
		
		//Checks if the current list is not empty.
		if (isEmpty()) {
			throw new EmptyStateException(this);
		}
		
		//Handles the case that the current list contains 1 element.
		if (containsOne()) {
			clear();
		}
		
		//Handles the case that the current list contains several elements.
		else {
			firstNode = firstNode.getNextNode();
			elementCount--;
		}
		
		return this;
	}
	
	//method
	/**
	 * Removes the first element the given selector selects from {@link IList.
	 * The complexity of this method is O(n) if the current {@link List} contains n elements.
	 * 
	 * @param selector
	 * @return the current {@link List}.
	 * @throws InvalidArgumentException if the current {@link List} contains no element the given selector selects.
	 */
	public List<E> removeFirst(final IElementTakerBooleanGetter<E> selector) {
		
		//Checks if the current list is not empty.
		if (isEmpty()) {
			throw new InvalidArgumentException(
				new Argument(this),
				new ErrorPredicate("contains no such element")
			);
		}

		if (selector.getOutput(getRefFirst())) {
			return removeFirst();
		}

		var iterator = firstNode;
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
	 * Removes the first appearance of all of the given elements from the current {@link List}.
	 * 
	 * The complexity of this method is O(m * n) if:
	 * -m elements are given.
	 * -This list contains n elements.
	 * 
	 * @param elements
	 * @return the current {@link List}.
	 * @throws InvalidArgumentException if the current {@link List} does not contain one of the given elements.
	 */
	public <E2> List<E> removeFirst(final Iterable<E2> elements) {
		
		elements.forEach(e -> removeFirst(e));
		
		return this;
	}
	
	//method
	/**
	 * Removes the first appearance of the given element from the current {@link IList.
	 * The complexity of this method is O(n) if the current {@link List} contains n elements.
	 * 
	 * @param element
	 * @return the current {@link List}.
	 * @throws InvalidArgumentException if the current {@link List} does not contain the given element.
	 */
	public List<E> removeFirst(final Object element) {
		
		//Checks if the current list is not empty.
		if (isEmpty()) {
			throw new InvalidArgumentException(
				new Argument(this),
				new ErrorPredicate("contains not '" + element + "'")
			);
		}
		
		if (firstNode.contains(element)) {
			return removeFirst();
		}
		
		var iterator = firstNode;
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
			new ErrorPredicate("does not contain '" + element + "'")
		);
	}
		
	//method
	/**
	 * Removes the last element of the current {@link List}.
	 * The complexity of this method is O(1).
	 * 
	 * @return the current {@link List}.
	 * @throws EmptyStateException if the current {@link List} is empty.
	 */
	public List<E> removeLast() {
		
		//Checks if the current list is not empty.
		if (isEmpty()) {
			throw new EmptyStateException(this);
		}
		
		//Handles the case that the current list contains 1 element.
		if (containsOne()) {
			clear();
		}
		
		//Handles the case that the current list contains several elements.
		else {
			
			var iterator = firstNode;
			
			while (iterator.getNextNode() != lastNode) {
				iterator = iterator.getNextNode();
			}
			
			iterator.removeNextNode();
			lastNode = iterator;
			elementCount--;
		}
		
		return this;
	}
	
	//method
	/**
	 * Removes the given element from the current {@link List}.
	 * 
	 * @param element
	 * @return the current {@link List}.
	 * @throws InvalidStateException if the current {@link List} does not contain the given element once.
	 */
	public List<E> removeRegardingSingularity(final E element) {
		
		//Enumerates the element count of the given element.
		switch (getCount(element)) {
			case 0:
				throw new InvalidStateException(this, "does not contain the given elemen");
			case 1:
				return removeFirst(element);
			default:
				throw new InvalidStateException(this, "contains the given element several times");
		}
	}
	
	//method
	/**
	 * Replaces the first element the given selector selects from the current {@link List} with the given element.
	 * The complexity of this method is O(n) if the current {@link List} contains n elements.
	 * 
	 * @param selector
	 * @param element
	 * @return the current {@link List}.
	 * @throws InvalidArgumentException if the current {@link List} contains no element the given selector selects.
	 * @throws NullArgumentException if the given element is null.
	 */
	public List<E> replaceFirst(IElementTakerBooleanGetter<E> selector, E element) {
		
		//Checks if the current list is not empty.
		if (isEmpty()) {
			throw new InvalidArgumentException(
				new Argument(this),
				new ErrorPredicate("contains not '" + element + "'")
			);
		}
		
		var iterator = firstNode;
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
	 * Reverses the elements of the current {@link List}.
	 * The complexity of this method is O(n) if the current {@link List} contains n elements.
	 * 
	 * @return the current {@link List}.
	 */
	public List<E> reverse() {
		
		//Handles the case that the current list contains any elements.
		if (containsAny()) {
			lastNode = firstNode;
			var iterator = firstNode;
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
	 * The complexity of this method is O(n) if the current {@link List} contains n elements.
	 * 
	 * @return a string representation of the current {@link List}.
	 */
	public String toString() {
		return toString(CharacterCatalogue.COMMA);
	}
	
	//method
	/**
	 * @param extractor
	 * @return a new {@link ListUsingMediator} with the current {@link List} and the given extractor.
	 * @throws NullArgumentException if the given extractor is null.
	 */
	public ListUsingMediator<E> using(final IElementTakerElementGetter<Object, E> extractor) {
		return new ListUsingMediator<E>(this, extractor);
	}
	
	//method
	/**
	 * @param startIndex
	 * @param endIndex
	 * @return a new {@link List} with the elements from the given start index to the given end index ordered according to the given norm.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <E2> List<E> getOrderedSubList(
		final int startIndex,
		final int endIndex,
		final IElementTakerComparableGetter<E, E2> norm
	) {
		
		//Searches for the start node.
		var startNode = firstNode;
		for (var i = 1; i < startIndex; i++) {
			startNode = startNode.getNextNode();
		}
		
		//Calculates the length of the sub list.
		final var length = (endIndex - startIndex) + 1;	
		
		//Handles the case when the sub list contains 1 element.
		if (length == 1) {
			return new List<E>(startNode.getElement());
		}
		
		//Handles the case when the sub list contains 2 elements.
		if (length == 2) {
			
			final List<E> list = new List<E>();

			final Comparable element1Value = norm.getValue(startNode.getElement());
			final Comparable element2Value = norm.getValue(startNode.getNextNode().getElement());
			if (element1Value.compareTo(element2Value) > 0) {
				list.addAtEnd(startNode.getNextNode().getElement());
				list.addAtEnd(startNode.getElement());					
			}
			else {
				list.addAtEnd(startNode.getElement());
				list.addAtEnd(startNode.getNextNode().getElement());
			}
			
			return list;
		}
		
		//Handles the case when the sub list contains more than 2 elements.
			final var list = new List<E>();
			final var middleIndex = startIndex + length / 2;
			final var subList1 = getOrderedSubList(startIndex, middleIndex, norm);
			final var subList2 = getOrderedSubList(middleIndex + 1, endIndex, norm);	
			for (var i = 1; i <= length; i++) {
				
				if (subList1.isEmpty()) {					
					list.addAtEnd(subList2.getRefFirst());
					subList2.removeFirst();
				}
				else if (subList2.isEmpty()) {
					list.addAtEnd(subList1.getRefFirst());
					subList1.removeFirst();
					
				}
				else {
					final Comparable value1 = norm.getValue(subList1.getRefFirst());
				 	final Comparable value2 = norm.getValue(subList2.getRefFirst());
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
	private void removeNextNode(final ListNode<E> node) {
		
		//Checks if the given node is not null.
		Validator.suppose(node).thatIsNamed(VariableNameCatalogue.NODE).isInstance();
		
		final var nextNode = node.getNextNode();
		
		if (nextNode.hasNextNode()) {
			node.setNextNode(nextNode.getNextNode());
		}
		else {
			node.removeNextNode();
			lastNode = node;
		}
		
		elementCount--;
	}
}
