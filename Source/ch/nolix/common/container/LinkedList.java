//package declaration
package ch.nolix.common.container;

import ch.nolix.common.constant.CharacterCatalogue;
import ch.nolix.common.constant.MultiVariableNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.common.functionAPI.IElementTakerComparableGetter;
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.invalidArgumentException.EmptyArgumentException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.skillAPI.Clearable;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link LinkedList} is a {@link IContainer} that can add elements at the begin or end.
 * A {@link LinkedList} is clearable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 1160
 * @param <E> The type of the elements of a {@link LinkedList}.
 */
public final class LinkedList<E> implements Clearable<LinkedList<E>>, IContainer<E> {
	
	//attribute
	private int elementCount = 0;
	
	//optional attributes
	private LinkedListNode<E> firstNode;
	private LinkedListNode<E> lastNode;
		
	//constructor
	/**
	 * Creates a new {@link LinkedList} that is empty.
	 * The complexity of this method is O(1).
	 */
	public LinkedList() {}
	
	//constructor
	/**
	 * Creates a new {@link LinkedList} with the given element.
	 * The complexity of this method is O(1).
	 * 
	 * @param element
	 * @throws ArgumentIsNullException if the given element is null.
	 */
	public LinkedList(final E element) {
		addAtEnd(element);
	}
	
	//constructor
	/**
	 * Creates a new {@link LinkedList} with the given elements.
	 * The complexity of this method is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @throws ArgumentIsNullException if the given element container is null.
	 * @throws ArgumentIsNullException if one of the given elements is null.
	 */
	@SuppressWarnings("unchecked")
	public LinkedList(final E... elements) {
		addAtEnd(elements);
	}
	
	//constructor
	/**
	 * Creates a new {@link LinkedList} with the given elements.
	 * The complexity of this method is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @throws ArgumentIsNullException if the given element container is null.
	 * @throws ArgumentIsNullException if one of the given elements is null.
	 */
	public <E2 extends E> LinkedList(final Iterable<E2> elements) {
		addAtEnd(elements);
	}
	
	//method
	/**
	 * Adds the given element at the begin of the current {@link LinkedList}.
	 * The complexity of this method is O(1).
	 * 
	 * @param element
	 * @return the current {@link LinkedList}.
	 * @throws ArgumentIsNullException if the given element is null.
	 */
	public LinkedList<E> addAtBegin(E element) {
		
		//Creates new node.
		final var node = new LinkedListNode<E>(element);
		
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
	 * Adds the given elements at the begin of the current {@link LinkedList}.
	 * The complexity of this method is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @return the current {@link LinkedList}.
	 * @throws ArgumentIsNullException if the given elements is null.
	 * @throws ArgumentIsNullException if one of the given elements is null.
	 */
	@SuppressWarnings("unchecked")
	public LinkedList<E> addAtBegin(final E... elements) {
		
		//Asserts that the given elements is not null.
		Validator.assertThat(elements).thatIsNamed(MultiVariableNameCatalogue.ELEMENTS).isNotNull();
		
		//Iterates the given elements.
		for (final var e : elements) {
			addAtEnd(e);
		}
		
		return this;
	}
	
	//method
	/**
	 * Adds the given elements at the begin of the current {@link LinkedList}.
	 * The complexity of this method is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @return the current {@link LinkedList}.
	 * @throws ArgumentIsNullException if the given element container is null.
	 * @throws ArgumentIsNullException if one of the given elements is null.
	 */
	public <E2 extends E> LinkedList<E> addAtBegin(final Iterable<E2> elements) {
		
		//Asserts that the given elements is not null.
		Validator.assertThat(elements).thatIsNamed("element container").isNotNull();
		
		//Handles the case that the given elements is not empty.
		if (new ReadContainer<E>(elements).isEmpty()) {
			
			final var preNode = new LinkedListNode<E>(null);
			
			var iterator = preNode;
			for (final E e: elements) {
				iterator.setElement(e);
				iterator.setNextNode(new LinkedListNode<E>(null));
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
	 * Adds the given element at the begin of the current {@link LinkedList}
	 * if the current {@link LinkedList} does not contain it.
	 * 
	 * The complexity of this method is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @param element
	 * @return the current {@link LinkedList}.
	 * @throws ArgumentIsNullException if the given element is null.
	 */
	public LinkedList<E> addAtBeginIfNotContained(final E element) {
		
		//Handles the case that the current list does not contain the given element.
		if (!contains(element)) {
			addAtBegin(element);
		}
		
		return this;
	}
	
	//method
	/**
	 * Adds all of the given elements, the current {@link LinkedList} does not contain,
	 * at the begin of the current {@link LinkedList}.
	 * 
	 * The complexity of this method is O(m*n) if:
	 * -The current {@link LinkedList} contains m elements.
	 * -n elements are given.
	 * 
	 * @param elements
	 * @return the current {@link LinkedList}.
	 * @throws ArgumentIsNullException if one of the given elements is null.
	 */
	@SuppressWarnings("unchecked")
	public LinkedList<E> addAtBeginIfNotContained(final E... elements) {
		
		//Iterates the given elements.
		for (final var e : elements) {
			addAtBeginIfNotContained(e);
		}
		
		return this;
	}
	
	//method
	/**
	 * Adds the given element at the begin of the current {@link LinkedList} with regarding singularity.
	 * The complexity of this method is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @param element
	 * @return the current {@link LinkedList}.
	 * @throws ArgumentIsNullException if the given element is null.
	 * @throws InvalidArgumentException if the current {@link LinkedList} contains already the given element.
	 */
	public LinkedList<E> addAtBeginRegardingSingularity(final E element) {
		
		//Asserts that the current {@link List} contains already the given element.
		if (contains(element)) {
			throw
			new InvalidArgumentException(
				element,
				"is already contained in the current list"
			);
		}
		
		return addAtBegin(element);
	}
	
	//method
	/**
	 * Adds the given element at the end of the current {@link LinkedList}.
	 * The complexity of this method is O(1).
	 * 
	 * @param element
	 * @return the current {@link LinkedList}.
	 * @throws ArgumentIsNullException if the given element is null.
	 */
	public LinkedList<E> addAtEnd(final E element) {
		
		//Creates new node.
		final var node = new LinkedListNode<E>(element);
		
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
	 * Adds the given elements at the end of the current {@link LinkedList}.
	 * The complexity of this method is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @return the current {@link LinkedList}.
	 * @throws ArgumentIsNullException if the given element container is null.
	 * @throws ArgumentIsNullException if one of the given elements is null.
	 */
	@SuppressWarnings("unchecked")
	public LinkedList<E> addAtEnd(final E... elements) {
		
		//Asserts that the given element container is not null.
		Validator.assertThat(elements).thatIsNamed("element container").isNotNull();
		
		//Iterates the given elements.
		for (final E e: elements) {
			addAtEnd(e);
		}
		
		return this;
	}
	
	//method
	/**
	 * Adds the given elements at the end of the current {@link LinkedList}.
	 * The complexity of this method is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @return the current {@link LinkedList}.
	 * @throws ArgumentIsNullException if the given elements is null.
	 * @throws ArgumentIsNullException if one of the given elements is null.
	 */
	public <E2 extends E> LinkedList<E> addAtEnd(final Iterable<E2> elements) {
		
		//Asserts that the given elements is not null.
		Validator.assertThat(elements).thatIsNamed(MultiVariableNameCatalogue.ELEMENTS).isNotNull();
		
		elements.forEach(e -> addAtEnd(e));
		
		return this;
	}
	
	//method
	/**
	 * Adds the elements the given extractor extracts from the given elements
	 * at the end of the current {@link LinkedList}.
	 * The complexity of this method is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @param extractor
	 * @return the current {@link LinkedList}.
	 */
	public <E2> LinkedList<E> addAtEnd(
		final Iterable<E2> elements,
		IElementTakerElementGetter<E2, E> extractor
	) {
		elements.forEach(e -> addAtEnd(extractor.getOutput(e)));
		
		return this;
	}
	
	//method
	/**
	 * Adds the given element at the end of the current {@link LinkedList}
	 * if the current {@link LinkedList} does not contain it.
	 * 
	 * The complexity of this method is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @param element
	 * @return the current {@link LinkedList}.
	 * @throws ArgumentIsNullException if the given element is null.
	 */
	public LinkedList<E> addAtEndIfNotContained(final E element) {
		
		//Handles the case that the current list does not contain the given element.
		if (!contains(element)) {
			addAtEnd(element);
		}
		
		return this;
	}
	
	//method
	/**
	 * Adds all of the given elements, the current {@link LinkedList} does not contain,
	 * at the end of the current {@link LinkedList}.
	 * 
	 * The complexity of this method is O(m*n) if:
	 * -The current {@link LinkedList} contains m elements.
	 * -n elements are given.
	 * 
	 * @param elements
	 * @return the current {@link LinkedList}.
	 * @throws ArgumentIsNullException if one of the given elements is null.
	 */
	@SuppressWarnings("unchecked")
	public LinkedList<E> addAtEndIfNotContained(final E... elements) {
		
		//Iterates the given elements.
		for (final var e : elements) {
			addAtEndIfNotContained(e);
		}
		
		return this;
	}
	
	//method
	/**
	 * Adds the given element at the begin of the current {@link LinkedList} with regarding singularity.
	 * The complexity of this method is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @param element
	 * @return the current {@link LinkedList}.
	 * @throws ArgumentIsNullException if the given element is null.
	 * @throws InvalidArgumentException if the current {@link LinkedList} contains already the given element.
	 */
	public LinkedList<E> addAtEndRegardingSingularity(final E element) {
		
		//Asserts that the current {@link List} contains already the given element.
		if (contains(element)) {
			throw
			new InvalidArgumentException(
				element,
				"is already contained in the current list"
			);
		}
		
		return addAtEnd(element);
	}
		
	//method
	/**
	 * Removes all elements of the current {@link LinkedList}.
	 * The complexity of this method is O(n) when the current {@link LinkedList} contains n elements.
	 * 
	 * @return the current {@link LinkedList}.
	 */
	@Override
	public LinkedList<E> clear() {
		
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
		}
		
		return this;
	}
	
	//method
	/**
	 * @return true if the current {@link LinkedList} contains any element.
	 */
	@Override
	public boolean containsAny() {
		
		//Calls the default method of the required interface.
		return IContainer.super.containsAny();
	}
	
	//method
	/**
	 * An object equals a list if it is a list containing exactly the same elements.
	 * 
	 * @return true if the given object equals the current {@link LinkedList}.
	 */
	@Override
	public boolean equals(Object object) {
		
		//Handles the case that the given object is not a list.
		if (!(object instanceof LinkedList<?>)) {
			return false;
		}
		
		//Handles the case that the given object is a list.
		
			final var list = (LinkedList<?>)object;
			
			if (getSize() != list.getSize()) {
				return false;
			}
			
			return containsAll(list);
	}
	
	//method
	/**
	 * Runs the given runner on each element of the current {@link LinkedList}.
	 * The complexity of this method is O(n) when the current {@link LinkedList} contains n elements.
	 * 
	 * @param runner
	 * @return the current {@link LinkedList}.
	 */
	public LinkedList<E> forEachAndGetList(final IElementTaker<E> runner) {
		
		//Iterates the current {@link List}.
		for (final var e: this) {
			runner.run(e);
		}
		
		return this;
	}
		
	//method
	/**
	 * The complexity of this method is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @return a new list with the elements of the current {@link LinkedList}.
	 */
	public LinkedList<E> getCopy() {
		return to(e -> e);
	}
	
	//method
	/**
	 * The complexity of this method is O(n^2) if the current {@link LinkedList} contains n elements.
	 * 
	 * @param norm
	 * @return a new list of groups of the elements of the current {@link LinkedList}
	 * whereas the value of the given norm is equal for all elements of a group.
	 */
	public <E2> LinkedList<LinkedList<E>> getGroups(final IElementTakerElementGetter<E, E2> norm) {
		
		final LinkedList<LinkedList<E>> groups = new LinkedList<>();
		
		//Iterates the current list.
		for (final E e : this) {
			
			final E2 categoryRepresentator = norm.getOutput(e);
			
			final LinkedList<E> group
			= groups.getRefFirstOrNull(
				l -> l.contains(e2 -> norm.getOutput(e).equals(categoryRepresentator))
			);
			
			if (group == null) {
				groups.addAtEnd(new LinkedList<E>(e));
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
	 * @return the ratio of the sequences from the current {@link LinkedList} that match the given sequence pattern.
	 * @throws EmptyArgumentException if the current {@link LinkedList} is empty.
	 */
	public double getRatio(final SequencePattern<E> sequencePattern) {
		
		//Asserts that the current list is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		return ((double)getSequenceCount(sequencePattern) / getSize());
	}
		
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return the last element of the current {@link LinkedList}.
	 * @throws EmptyArgumentException if the current {@link LinkedList} is empty.
	 */
	public E getRefLast() {
		
		//Asserts that the current list is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		return lastNode.getElement();
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return the last element of the current {@link LinkedList} or null.
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
	 * The complexity of this method is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @return the second last element of the current {@link LinkedList}.
	 * @throws InvalidArgumentException if the current {@link LinkedList} contains less than 2 elements.
	 */
	public E getRefSecondLast() {
		
		//Asserts that the current List contains more than 1 element.
		if (getSize() < 2) {
			throw new InvalidArgumentException(this, "contains less than 2 elements");
		}
		
		return getRefAt(getSize() - 1);
	}
	
	//method
	/**
	 * The complexity of this method is O((n-m)*m) if:
	 * -This list contains n elements.
	 * -The sequences that matches the given sequence pattern contain m elements.
	 * 
	 * @param sequencePattern
	 * @return the number of sequences from the current {@link LinkedList} that match the given sequence pattern.
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
	 * @return the sequences from the current {@link LinkedList} that match the given sequence pattern.
	 */
	public LinkedList<LinkedList<E>> getSequences(final SequencePattern<E> sequencePattern) {
		return sequencePattern.getSequences(this);
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return the number of elements of the current {@link LinkedList}.
	 */
	@Override
	public int getSize() {
		return elementCount;
	}
	
	//method
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	//method
	/**
	 * @return true if the current {@link LinkedList} is empty
	 */
	@Override
	public boolean isEmpty() {
		
		//Calls the method of the desired interface of the current list.
		return IContainer.super.isEmpty();
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return a new iterator of the current {@link LinkedList}.
	 */
	@Override
	public LinkedListIterator<E> iterator() {
		return new LinkedListIterator<>(firstNode);
	}
	
	//method
	/**
	 * The complexity of this method is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @param sequencePattern
	 * @return true if the current {@link LinkedList} matches the given sequence pattern.
	 */
	public boolean matches(final SequencePattern<E> sequencePattern) {
		return sequencePattern.matches(this);
	}
	
	//method
	/**
	 * Orders the elements of the current {@link LinkedList} according to the given norm.
	 * This method uses the merge sort algorithm.
	 * The complexity of this method is O(n*log(n)) if the current {@link LinkedList} contains n elements.
	 * 
	 * @param norm
	 * @return the current {@link LinkedList}.
	 */
	public <E2> LinkedList<E> order(final IElementTakerComparableGetter<E, E2> norm) {
		
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
	 * Removes all elements from the current {@link LinkedList}
	 * and adds the given elements at the end of the current {@link LinkedList}.
	 * 
	 * The complexity of this method is O(m + n) if:
	 * -The current {@link LinkedList} contains m elements.
	 * -There are given n elements.
	 * 
	 * @param elements
	 * @throws ArgumentIsNullException if the given elements is null.
	 * @throws ArgumentIsNullException if one of the given elements is null.
	 */
	public void refill(final Iterable<E> elements) {
		
		//TODO: Improve the performance of this implementation.
		clear();
		addAtEnd(elements);
	}
	
	//method
	/**
	 * Removes all elements the given selector selects from the current {@link LinkedList}.
	 * The complexity of this method is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @param selector
	 * @return the current {@link LinkedList}.
	 */
	public LinkedList<E> removeAll(final IElementTakerBooleanGetter<E> selector) {
		
		final LinkedList<E> list = getRefSelected(e -> !selector.getOutput(e));
		clear();
		addAtEnd(list);
		
		return this;
	}
	
	//method
	/**
	 * Removes and returns the first element of the current {@link LinkedList}.
	 * The complexity of this method is O(1).
	 * 
	 * @return the first element of the current {@link LinkedList}.
	 * @throws EmptyArgumentException if the current {@link LinkedList} is empty.
	 */
	public E removeAndGetRefFirst() {
		final E element = getRefFirst();
		removeFirst();
		return element;
	}
	
	//method
	/**
	 * Removes and returns the first element the given selector selects from the current {@link LinkedList}.
	 * The complexity of this method is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @param selector
	 * @return the first element the given selector selects from the current {@link LinkedList}.
	 * @throws InvalidArgumentException if the current {@link LinkedList} does not contain an element the given selector selects.
	 */
	public E removeAndGetRefFirst(final IElementTakerBooleanGetter<E> selector) {
		E element = getRefFirst(selector);
		removeFirst(selector);
		return element;
	}
	
	//method
	/**
	 * Removes and returns the last element of the current {@link LinkedList}.
	 * The complexity of this method is O(1).
	 * 
	 * @return the last element of the current {@link LinkedList}.
	 * @throws EmptyArgumentException if the current {@link LinkedList} is empty.
	 */
	public E removeAndGetRefLast() {
		E element = getRefLast();
		removeLast();
		return element;
	}
		
	//method
	/**
	 * Removes the first element of the current {@link LinkedList}.
	 * The complexity of this method is O(1).
	 * 
	 * @return the current {@link LinkedList}.
	 * @throws EmptyArgumentException if the current {@link LinkedList} is empty.
	 */
	public LinkedList<E> removeFirst() {
		
		//Asserts that the current list is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
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
	 * The complexity of this method is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @param selector
	 * @return the current {@link LinkedList}.
	 * @throws InvalidArgumentException if the current {@link LinkedList} does not contain an element the given selector selects.
	 */
	public LinkedList<E> removeFirst(final IElementTakerBooleanGetter<E> selector) {
		
		//Asserts that the current list is not empty.
		if (isEmpty()) {
			throw
			new InvalidArgumentException(
				this,
				"does not contain such an element"
			);
		}

		if (selector.getOutput(getRefFirst())) {
			return removeFirst();
		}

		var iterator = firstNode;
		while (iterator.hasNextNode()) {
			
			final LinkedListNode<E> nextNode = iterator.getNextNode();
			
			if (selector.getOutput(nextNode.getElement())) {
				removeNextNode(iterator);
				return this;
			}
			
			iterator = nextNode;
		}
		
		throw
		new InvalidArgumentException(
			this,
			"does not contain such an element"
		);
	}
	
	//method
	/**
	 * Removes the first appearance of all of the given elements from the current {@link LinkedList}.
	 * 
	 * The complexity of this method is O(m * n) if:
	 * -There are given m elements.
	 * -This list contains n elements.
	 * 
	 * @param elements
	 * @return the current {@link LinkedList}.
	 * @throws InvalidArgumentException if the current {@link LinkedList} does not contain one of the given elements.
	 */
	public <E2> LinkedList<E> removeFirst(final Iterable<E2> elements) {
		
		elements.forEach(e -> removeFirst(e));
		
		return this;
	}
	
	//method
	/**
	 * Removes the first appearance of the given element from the current {@link IList.
	 * The complexity of this method is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @param element
	 * @return the current {@link LinkedList}.
	 * @throws InvalidArgumentException if the current {@link LinkedList} does not contain the given element.
	 */
	public LinkedList<E> removeFirst(final Object element) {
		
		//Asserts that the current list is not empty.
		if (isEmpty()) {
			throw
			new InvalidArgumentException(
				this,
				"does not contain the element '" + element + "'"
			);
		}
		
		if (firstNode.contains(element)) {
			return removeFirst();
		}
		
		var iterator = firstNode;
		while (iterator.hasNextNode()) {
			
			final LinkedListNode<E> nextNode = iterator.getNextNode();
			
			if (nextNode.contains(element)) {
				removeNextNode(iterator);
				return this;
			}
			
			iterator = nextNode;
		}
		
		throw
		new InvalidArgumentException(
			this,
			"does not contain the element '" + element + "'"
		);
	}
		
	//method
	/**
	 * Removes the last element of the current {@link LinkedList}.
	 * The complexity of this method is O(1).
	 * 
	 * @return the current {@link LinkedList}.
	 * @throws EmptyArgumentException if the current {@link LinkedList} is empty.
	 */
	public LinkedList<E> removeLast() {
		
		//Asserts that the current list is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
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
	 * Removes the given element from the current {@link LinkedList}.
	 * 
	 * @param element
	 * @return the current {@link LinkedList}.
	 * @throws InvalidArgumentException if the current {@link LinkedList} does not contain the given element once.
	 */
	public LinkedList<E> removeRegardingSingularity(final E element) {
		
		//Enumerates the element count of the given element.
		switch (getCount(element)) {
			case 0:
				throw new InvalidArgumentException(this, "does not contain the given elemen");
			case 1:
				return removeFirst(element);
			default:
				throw new InvalidArgumentException(this, "contains the given element several times");
		}
	}
	
	//method
	/**
	 * Replaces the first element the given selector selects from the current {@link LinkedList} with the given element.
	 * The complexity of this method is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @param selector
	 * @param element
	 * @return the current {@link LinkedList}.
	 * @throws InvalidArgumentException if the current {@link LinkedList} does not contain an element the given selector selects.
	 * @throws ArgumentIsNullException if the given element is null.
	 */
	public LinkedList<E> replaceFirst(IElementTakerBooleanGetter<E> selector, E element) {
		
		//Asserts that the current list is not empty.
		if (isEmpty()) {
			throw
			new InvalidArgumentException(
				this,
				"does not contain the element '" + element + "'"
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
			
			throw
			new InvalidArgumentException(
				this,
				"does not contain the element '" + element + "'"
			);
		}
	}
	
	//method
	/**
	 * Reverses the elements of the current {@link LinkedList}.
	 * The complexity of this method is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @return the current {@link LinkedList}.
	 */
	public LinkedList<E> reverse() {
		
		//Handles the case that the current list contains any elements.
		if (containsAny()) {
			lastNode = firstNode;
			var iterator = firstNode;
			while (iterator.hasNextNode()) {
				final LinkedListNode<E> node = iterator.getNextNode();
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
	 * The complexity of this method is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @return a string representation of the current {@link LinkedList}.
	 */
	@Override
	public String toString() {
		return toString(CharacterCatalogue.COMMA);
	}
	
	//method
	/**
	 * @param extractor
	 * @return a new {@link ListUsingMediator} with the current {@link LinkedList} and the given extractor.
	 * @throws ArgumentIsNullException if the given extractor is null.
	 */
	public ListUsingMediator<E> using(final IElementTakerElementGetter<Object, E> extractor) {
		return new ListUsingMediator<>(this, extractor);
	}
	
	//method
	/**
	 * @param startIndex
	 * @param endIndex
	 * @return a new {@link LinkedList} with the elements from the given start index to the given end index ordered according to the given norm.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <E2> LinkedList<E> getOrderedSubList(
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
			return new LinkedList<>(startNode.getElement());
		}
		
		//Handles the case when the sub list contains 2 elements.
		if (length == 2) {
			
			final var list = new LinkedList<E>();

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
			final var list = new LinkedList<E>();
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
	 * @throws ArgumentIsNullException if the given node is null.
	 * @throws UnexistingPropertyException if the given node does not have a next node.
	 */
	private void removeNextNode(final LinkedListNode<E> node) {
		
		//Asserts that the given node is not null.
		Validator.assertThat(node).thatIsNamed(VariableNameCatalogue.NODE).isNotNull();
		
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
