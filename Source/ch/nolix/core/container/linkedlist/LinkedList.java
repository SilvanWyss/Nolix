//package declaration
package ch.nolix.core.container.linkedlist;

//own imports
import ch.nolix.core.commontype.commontypeconstant.CharacterCatalogue;
import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.independent.independenthelper.IterableHelper;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PluralLowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerBooleanGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;

//class
/**
 * A {@link LinkedList} is a {@link Container} that can add elements at the begin or end.
 * A {@link LinkedList} is clearable.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <E> is the type of the elements of a {@link LinkedList}.
 */
public final class LinkedList<E> extends Container<E> implements ILinkedList<E> {
	
	//static method
	/**
	 * @param array
	 * @param <E2> is the type of the elements of the given array.
	 * @return a new {@link LinkedList} with the elements in the given array.
	 * @throws ArgumentIsNullException if the given array is null.
	 * @throws ArgumentIsNullException if one of the elements in the given array is null.
	 */
	public static <E2> LinkedList<E2> fromArray(final E2[] array) {
		
		final var list = new LinkedList<E2>();
		list.addAtEnd(array);
		
		return list;
	}
	
	//static method
	/**
	 * @param container
	 * @param <E2> is the type of the elements of the given container.
	 * @return a new {@link LinkedList} with the elements in the given container.
	 * @throws ArgumentIsNullException if the given container is null.
	 * @throws ArgumentIsNullException if one of the elements in the given container is null.
	 */
	public static <E2> LinkedList<E2> fromIterable(final Iterable<E2> container) {
		
		final var list = new LinkedList<E2>();
		list.addAtEnd(container);
		
		return list;
	}
	
	//static method
	/**
	 * @param element
	 * @param <E2> is the type of the given element.
	 * @return a new {@link LinkedList} with the given element.
	 * @throws ArgumentIsNullException if the given element is null.
	 */
	public static <E2> LinkedList<E2> withElement(final E2 element) {
		
		final var list = new LinkedList<E2>();
		list.addAtEnd(element);
		
		return list;
	}
	
	//static method
	/**
	 * @param firstElement
	 * @param elements
	 * @param <E2> is the type of the given elements.
	 * @return a new {@link LinkedList} with the given elements.
	 * @throws ArgumentIsNullException if the given firstElement or one of the given elements is null.
	 */
	@SuppressWarnings("unchecked")
	public static <E2> LinkedList<E2> withElements(final E2 firstElement, final E2... elements) {
		
		final var list = new LinkedList<E2>();
		list.addAtEnd(firstElement, elements);
		
		return list;
	}
	
	//attribute
	private int elementCount;
	
	//optional attributes
	private LinkedListNode<E> firstNode;
	private LinkedListNode<E> lastNode;
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addAtBegin(E element) {
		
		//Creates new node.
		final var node = new LinkedListNode<>(element);
		
		if (isEmpty()) {
			firstNode = node;
			lastNode = node;
		} else {
			node.setNextNode(firstNode);
			firstNode = node;
		}
		elementCount++;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SafeVarargs
	public final void addAtBegin( //NOSONAR: final keyword is required for SaveVarargs annotation.
		final E firstElement,
		final E... elements
	) { 
		
		addAtBegin(firstElement);
		
		//Iterates the given elements.
		for (final E e: elements) {
			addAtBegin(e);
		}
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if n elements are given.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void addAtBegin(E[] elements) {
		
		//Iterates the given elements.
		for (final E e: elements) {
			addAtBegin(e);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addAtBegin(final Iterable<? extends E> elements) {
		
		//Asserts that the given elements is not null.
		GlobalValidator.assertThat(elements).thatIsNamed(PluralLowerCaseCatalogue.ELEMENTS).isNotNull();
		
		//Handles the case that the given elements is not empty.
		if (!IterableHelper.isEmpty(elements)) {
			
			final LinkedListNode<E> newFirstNode = new LinkedListNode<>(elements.iterator().next());
			
			LinkedListNode<E> node = null;
			
			for (final var e : elements) {
				
				if (node == null) {
					node = newFirstNode;
				} else {
					final var currentNode = new LinkedListNode<>(e);
					node.setNextNode(currentNode);
					node = currentNode;
				}
				
				elementCount++;
			}
			
			if (node != null && firstNode != null) {
				node.setNextNode(firstNode);
			}
			
			this.firstNode = newFirstNode;
			
			if (lastNode == null) {
				lastNode = node;
			}
		}
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void addAtEnd(final E element) {
		
		//Creates new node.
		final var node = new LinkedListNode<>(element);
		
		if (isEmpty()) {
			firstNode = node;
			lastNode = node;
		} else {
			lastNode.setNextNode(node);
			lastNode = node;
		}
		elementCount++;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if n elements are given.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	@SafeVarargs
	public final void addAtEnd( //NOSONAR: final keyword is required for SaveVarargs annotation.
		final E firstElement,
		final E... elements
	) { 
		
		addAtEnd(firstElement);
		
		//Iterates the given elements.
		for (final E e: elements) {
			addAtEnd(e);
		}
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if n elements are given.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void addAtEnd(E[] elements) {
		
		//Iterates the given elements.
		for (final E e: elements) {
			addAtEnd(e);
		}
	}
	
	//method
	/**
	 * Adds the given elements at the end of the current {@link LinkedList}.
	 * The complexity of this implementation is O(n) if n elements are given.
	 * 
	 * @param elements
	 * @throws ArgumentIsNullException if one of the given elements is null.
	 */
	@Override
	public void addAtEnd(final Iterable<? extends E> elements) {
		elements.forEach(this::addAtEnd);
	}
	
	//method
	/**
	 * Removes all elements of the current {@link LinkedList}.
	 * The complexity of this implementation is O(n) when the current {@link LinkedList} contains n elements.
	 */
	@Override
	public void clear() {
		
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
	}
	
	//method
	/**
	 * An object equals a list if it is a list containing exactly the same elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object object) {
		
		//Handles the case that the given object is a LinkedList.
		if (object instanceof LinkedList<?> linkedList) {
			return containsExactlyInSameOrder(linkedList);
		}
		
		//Handles the case that the given object is not a LinkedList.
		return false;
	}
			
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @return a new {@link IContainer} with the elements of the current {@link LinkedList}.
	 */
	public IContainer<E> getCopy() {
		return to(e -> e);
	}
		
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param p1BasedIndex
	 * @return the element at the given index.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link Container} does not contain an element at the given index.
	 */
	@Override
	public E getRefAt1BasedIndex(final int p1BasedIndex) {
		
		//Iterates the current LinkedList.
		var i = 1;
		for (final var e : this) {
			
			//Asserts that the current index is the given index.
			if (i == p1BasedIndex) {
				return e;
			}
			
			i++;
		}
		
		throw
		ArgumentIsOutOfRangeException.forArgumentNameAndArgumentAndRangeWithMinAndMax(
			"1-based index",
			p1BasedIndex,
			1,
			getElementCount()
		);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @return the last element of the current {@link LinkedList}.
	 * @throws EmptyArgumentException if the current {@link LinkedList} is empty.
	 */
	@Override
	public E getRefLast() {
		
		//Asserts that the current list is not empty.
		if (isEmpty()) {
			throw EmptyArgumentException.forArgument(this);
		}
		
		return lastNode.getElement();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link LinkedList} contains n elements.
	 *  
	 * {@inheritDoc}
	 */
	@Override
	public ILinkedList<E> getReversedList() {
		
		//Handles the case that the current LinkedList is empty.
		if (isEmpty()) {
			return new LinkedList<>();
		}
		
		//Handles the case that the current LinkedList contains elements.
		return getReversedListWhenContainsElements();
	}
	
	//method
	/**
	 * The complexity of this implementation is O((n-m)*m) if:
	 * -This list contains n elements.
	 * -The sequences that matches the given sequence pattern contain m elements.
	 * 
	 * @param sequencePattern
	 * @return the number of sequences from the current {@link LinkedList} that match the given sequence pattern.
	 */
	public int getSequenceCount(final SequencePattern<E> sequencePattern) {
		return getSequences(sequencePattern).getElementCount();
	}
	
	//method
	/**
	 * The complexity of this implementation is O((n-m)*m) if:
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
	 * The complexity of this implementation is O(1).
	 * 
	 * @return the number of elements of the current {@link LinkedList}.
	 */
	@Override
	public int getElementCount() {
		return elementCount;
	}
	
	//method
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @return a new iterator of the current {@link LinkedList}.
	 */
	@Override
	public LinkedListIterator<E> iterator() {
		return LinkedListIterator.withFirstNodeOrNull(firstNode);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @param sequencePattern
	 * @return true if the current {@link LinkedList} matches the given sequence pattern.
	 */
	public boolean matches(final SequencePattern<E> sequencePattern) {
		return sequencePattern.matches(this);
	}
		
	//method
	/**
	 * Removes all elements the given selector selects from the current {@link LinkedList}.
	 * The complexity of this implementation is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @param selector
	 */
	public void removeAll(final IElementTakerBooleanGetter<E> selector) {
		final var list = getRefSelected(e -> !selector.getOutput(e));
		clear();
		addAtEnd(list);
	}
	
	//method
	/**
	 * Removes and returns the first element of the current {@link LinkedList}.
	 * The complexity of this implementation is O(1).
	 * 
	 * @return the first element of the current {@link LinkedList}.
	 * @throws EmptyArgumentException if the current {@link LinkedList} is empty.
	 */
	public E removeAndGetRefFirst() {
		final var element = getRefFirst();
		removeFirst();
		return element;
	}
	
	//method
	/**
	 * Removes and returns the first element the given selector selects from the current {@link LinkedList}.
	 * The complexity of this implementation is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @param selector
	 * @return the first element the given selector selects from the current {@link LinkedList}.
	 * @throws InvalidArgumentException if the current {@link LinkedList} does not contain an element the given selector selects.
	 */
	public E removeAndGetRefFirst(final IElementTakerBooleanGetter<E> selector) {
		final var element = getRefFirst(selector);
		removeFirst(selector);
		return element;
	}
	
	//method
	/**
	 * Removes and returns the last element of the current {@link LinkedList}.
	 * The complexity of this implementation is O(1).
	 * 
	 * @return the last element of the current {@link LinkedList}.
	 * @throws EmptyArgumentException if the current {@link LinkedList} is empty.
	 */
	public E removeAndGetRefLast() {
		final var element = getRefLast();
		removeLast();
		return element;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void removeFirst() {
		
		//Enumerates the element count of the current LinkedList.
		switch (getElementCount()) {
			case 0:
				break;
			case 1:
				clear();
				break;
			default:
				firstNode = firstNode.getNextNode();
				elementCount--;
		}
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void removeFirst(final IElementTakerBooleanGetter<E> selector) {
		
		//Handles the case that the current LinkedList contains any.
		if (containsAny()) {
			removeFirstWhenContainsAny(selector);
		}
	}
	
	//method
	/**
	 * Removes the first appearance of the given element from the current {@link LinkedList}.
	 * The complexity of this implementation is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @param element
	 * @throws InvalidArgumentException if the current {@link LinkedList} does not contain the given element.
	 */
	public void removeFirst(final Object element) {
		
		//Asserts that the current list is not empty.
		if (isEmpty()) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not contain the element '" + element + "'");
		}
		
		if (firstNode.contains(element)) {
			removeFirst();
			return;
		}
		
		var iterator = firstNode;
		while (iterator.hasNextNode()) {
			
			final LinkedListNode<E> nextNode = iterator.getNextNode();
			
			if (nextNode.contains(element)) {
				removeNextNode(iterator);
				return;
			}
			
			iterator = nextNode;
		}
		
		throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not contain the element '" + element + "'");
	}
		
	//method
	/**
	 * Removes the last element of the current {@link LinkedList}.
	 * The complexity of this implementation is O(1).
	 * 
	 * @throws EmptyArgumentException if the current {@link LinkedList} is empty.
	 */
	public void removeLast() {
		
		//Asserts that the current list is not empty.
		if (isEmpty()) {
			throw EmptyArgumentException.forArgument(this);
		}
		
		//Handles the case that the current list contains 1 element.
		if (containsOne()) {
			clear();
			
		//Handles the case that the current list contains several elements.
		} else {
			
			var iterator = firstNode;
			
			while (iterator.getNextNode() != lastNode) {
				iterator = iterator.getNextNode();
			}
			
			iterator.removeNextNode();
			lastNode = iterator;
			elementCount--;
		}
	}
		
	//method
	/**
	 * Replaces the first element the given selector selects from the current {@link LinkedList} with the given element.
	 * The complexity of this implementation is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @param selector
	 * @param element
	 * @throws InvalidArgumentException if the current {@link LinkedList} does not contain an element the given selector selects.
	 * @throws ArgumentIsNullException if the given element is null.
	 */
	public void replaceFirst(IElementTakerBooleanGetter<E> selector, E element) {
		
		//Asserts that the current list is not empty.
		if (isEmpty()) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(
				this,
				"does not contain the element '" + element + "'"
			);
		}
		
		var iterator = firstNode;
		while (true) {
			
			if (selector.getOutput(iterator.getElement())) {
				iterator.setElement(element);
				return;
			}
			
			if (iterator.hasNextNode()) {
				iterator = iterator.getNextNode();
				continue;
			}
			
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not contain the element '" + element + "'");
		}
	}
	
	//method
	/**
	 * This implementation uses the merge sort algorithm.
	 * The complexity of this implementation is O(n*log(n)) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public <C extends Comparable<C>> IContainer<E> toOrderedList(final IElementTakerElementGetter<E, C> norm) {
		return getOrderedSubList(1, getElementCount(), norm);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return toStringWithSeparator(CharacterCatalogue.COMMA);
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
	 * {@inheritDoc}
	 */
	@Override
	protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
		return new LinkedList<>();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IContainer<E> getSubContainerFromStartIndexToEndIndex(
		final int p1BasedStartIndex,
		final int p1BasedEndIndex
	) {
		return new SubContainer<>(this, p1BasedStartIndex, p1BasedEndIndex);
	}
	
	//method
	/**
	 * @param startIndex
	 * @param endIndex
	 * @param norm
	 * @param <C> is the type of the {@link Comparable}s the given norm returns.
	 * @return a new {@link LinkedList} with
	 * the elements from the given start index to the given end index ordered according to the given norm.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <C extends Comparable<C>> LinkedList<E> getOrderedSubList(
		final int startIndex,
		final int endIndex,
		final IElementTakerElementGetter<E, C> norm
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
			return LinkedList.withElements(startNode.getElement());
		}
		
		//Handles the case when the sub list contains 2 elements.
		if (length == 2) {
			
			final var list = new LinkedList<E>();

			final Comparable element1Value = norm.getOutput(startNode.getElement());
			final Comparable element2Value = norm.getOutput(startNode.getNextNode().getElement());
			if (element1Value.compareTo(element2Value) > 0) {
				list.addAtEnd(startNode.getNextNode().getElement());
				list.addAtEnd(startNode.getElement());
			} else {
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
			} else if (subList2.isEmpty()) {
				list.addAtEnd(subList1.getRefFirst());
				subList1.removeFirst();
				
			} else {
				final Comparable value1 = norm.getOutput(subList1.getRefFirst());
				final Comparable value2 = norm.getOutput(subList2.getRefFirst());
				if (value1.compareTo(value2) > 0) {
					list.addAtEnd(subList2.getRefFirst());
					subList2.removeFirst();
				} else {
					list.addAtEnd(subList1.getRefFirst());
					subList1.removeFirst();
				}
			}
		}
		return list;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link LinkedList} contains n elements.
	 * 
	 * @return a new {@link LinkedList} with
	 * the elements of the current {@link LinkedList} in the reversed order for the case that
	 * the current {@link LinkedList} contains elements.
	 */
	private ILinkedList<E> getReversedListWhenContainsElements() {
		
		//Iterates the current LinkedList.
		LinkedListNode<E> lFirstNode = null;
		LinkedListNode<E> lLastNode = null;
		for (final var e : this) {
			final var node = new LinkedListNode<>(e);
			if (lFirstNode == null) {
				lFirstNode = node;
				lLastNode = node;
			} else {
				node.setNextNode(lFirstNode);
				lFirstNode = node;
			}
		}
		
		//Creates a reversed IMutableList.
		final var reversedList = new LinkedList<E>();
		reversedList.elementCount = getElementCount();
		reversedList.firstNode = lFirstNode;
		reversedList.lastNode = lLastNode;
		
		return reversedList;
	}
	
	//method
	/**
	 * Removes the first element the given selector selects from the current {@link ILinkedList} for the case that
	 * the current {@link LinkedList} contains any.
	 * 
	 * @param selector
	 */
	private void removeFirstWhenContainsAny(final IElementTakerBooleanGetter<E> selector) {
		if (selector.getOutput(getRefFirst())) {
			removeFirst();
		} else {
			var iterator = firstNode;
			while (iterator.hasNextNode()) {
				
				final LinkedListNode<E> nextNode = iterator.getNextNode();
				
				if (selector.getOutput(nextNode.getElement())) {
					removeNextNode(iterator);
					break;
				}
				
				iterator = nextNode;
			}
		}
	}
	
	//method
	/**
	 * Removes the next node of the given node.
	 * 
	 * @param node
	 * @throws ArgumentIsNullException if the given node is null.
	 * @throws ArgumentDoesNotHaveAttributeException if the given node does not have a next node.
	 */
	private void removeNextNode(final LinkedListNode<E> node) {
		
		//Asserts that the given node is not null.
		GlobalValidator.assertThat(node).thatIsNamed(LowerCaseCatalogue.NODE).isNotNull();
		
		final var nextNode = node.getNextNode();
		
		if (nextNode.hasNextNode()) {
			node.setNextNode(nextNode.getNextNode());
		} else {
			node.removeNextNode();
			lastNode = node;
		}
		
		elementCount--;
	}
}
