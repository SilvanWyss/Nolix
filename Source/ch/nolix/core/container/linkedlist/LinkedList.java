//package declaration
package ch.nolix.core.container.linkedlist;

//Java imports
import java.util.function.Function;
import java.util.function.Predicate;

import ch.nolix.core.commontypetool.iteratortool.GlobalIterableTool;
import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.CharacterCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PluralLowerCaseVariableCatalogue;

//class
/**
 * A {@link LinkedList} is a {@link Container} that can add elements at the
 * begin or end. A {@link LinkedList} is clearable.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <E> is the type of the elements of a {@link LinkedList}.
 */
public final class LinkedList<E> extends Container<E> implements ILinkedList<E> {

  //attribute
  private int elementCount;

  //optional attribute
  private LinkedListNode<E> firstNode;

  //optional attribute
  private LinkedListNode<E> lastNode;

  //static method
  /**
   * @param array
   * @param <E2>  is the type of the elements of the given array.
   * @return a new {@link LinkedList} with the elements in the given array.
   * @throws ArgumentIsNullException if the given array is null.
   * @throws ArgumentIsNullException if one of the elements in the given array is
   *                                 null.
   */
  public static <E2> LinkedList<E2> fromArray(final E2[] array) {

    GlobalValidator.assertThat(array).thatIsNamed(LowerCaseVariableCatalogue.ARRAY).isNotNull();

    final var list = new LinkedList<E2>();
    list.addAtEnd(array);

    return list;
  }

  //static method
  /**
   * @param container
   * @param <E2>      is the type of the elements of the given container.
   * @return a new {@link LinkedList} with the elements in the given container.
   * @throws ArgumentIsNullException if the given container is null.
   * @throws ArgumentIsNullException if one of the elements in the given container
   *                                 is null.
   */
  public static <E2> LinkedList<E2> fromIterable(final Iterable<E2> container) {

    final var list = new LinkedList<E2>();
    list.addAtEnd(container);

    return list;
  }

  //static method
  /**
   * @param element
   * @param elements
   * @param <E2>     is the type of the given elements.
   * @return a new {@link LinkedList} with the given elements.
   * @throws ArgumentIsNullException if the given element or one of the given
   *                                 elements is null.
   */
  @SuppressWarnings("unchecked")
  public static <E2> LinkedList<E2> withElement(final E2 element, final E2... elements) {

    final var list = new LinkedList<E2>();
    list.addAtEnd(element, elements);

    return list;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public void addAtBegin(final E element) {

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
  public void addAtBegin( //NOSONAR: final keyword is required for SaveVarargs annotation.
    final E element,
    @SuppressWarnings("unchecked") final E... elements) {

    addAtBegin(elements);

    addAtBegin(element);
  }

  //method
  /**
   * The complexity of this implementation is O(n) if n elements are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public void addAtBegin(E[] elements) {
    if (isEmpty()) {
      addAtBeginWhenIsEmpty(elements);
    } else {
      addAtBeginWhenContainsAny(elements);
    }
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public void addAtBegin(final Iterable<? extends E> elements) {

    //Asserts that the given elements is not null.
    GlobalValidator.assertThat(elements).thatIsNamed(PluralLowerCaseVariableCatalogue.ELEMENTS).isNotNull();

    //Handles the case that the given elements is not empty.
    if (GlobalIterableTool.containsAny(elements)) {

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
    final E element,
    final E... elements) {

    addAtEnd(element);

    //Iterates the given elements.
    for (final E e : elements) {
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
    for (final E e : elements) {
      addAtEnd(e);
    }
  }

  //method
  /**
   * Adds the given elements at the end of the current {@link LinkedList}. The
   * complexity of this implementation is O(n) if n elements are given.
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
   * Removes all elements of the current {@link LinkedList}. The complexity of
   * this implementation is O(n) when the current {@link LinkedList} contains n
   * elements.
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
   * The complexity of this implementation is O(n) if the current
   * {@link LinkedList} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public ILinkedList<E> getCopy() {

    //Creates a LinkedList.
    final var copy = new LinkedList<E>();

    //Iterates the current LinkedList
    for (final var e : this) {
      copy.addAtEnd(e);
    }

    return copy;
  }

  //method
  /**
   * The complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * @param p1BasedIndex
   * @return the element at the given index.
   * @throws NonPositiveArgumentException          if the given index is not
   *                                               positive.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link Container} does not
   *                                               contain an element at the given
   *                                               index.
   */
  @Override
  public E getStoredAt1BasedIndex(final int p1BasedIndex) {

    //Iterates the current LinkedList.
    var i = 1;
    for (final var e : this) {

      //Asserts that the current index is the given index.
      if (i == p1BasedIndex) {
        return e;
      }

      i++;
    }

    throw ArgumentIsOutOfRangeException.forArgumentNameAndArgumentAndRangeWithMinAndMax(
      "1-based index",
      p1BasedIndex,
      1,
      getCount());
  }

  //method
  /**
   * The complexity of this implementation is O(1).
   * 
   * @return the last element of the current {@link LinkedList}.
   * @throws EmptyArgumentException if the current {@link LinkedList} is empty.
   */
  @Override
  public E getStoredLast() {

    assertContainsAny();

    return lastNode.getElement();
  }

  //method
  /**
   * The complexity of this implementation is O(1).
   * 
   * @return the number of elements of the current {@link LinkedList}.
   */
  @Override
  public int getCount() {
    return elementCount;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return true;
  }

  //method
  /**
   * The complexity of this implementation is O(1).
   * 
   * @return a new iterator of the current {@link LinkedList}.
   */
  @Override
  public CopyableIterator<E> iterator() {
    return LinkedListIterator.withNullableFirstNode(firstNode);
  }

  //method
  /**
   * The complexity of this implementation is O(n) if the current
   * {@link LinkedList} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public void removeAll(final Predicate<E> selector) {

    final var remainingElements = getStoredOther(selector);

    clear();

    addAtEnd(remainingElements);
  }

  //method
  /**
   * The complexity of this implementation is O(n) if the current
   * {@link LinkedList} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public void removeAllOccurrencesOf(final Object element) {
    removeAll(e -> e == element);
  }

  //method
  /**
   * The complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public E removeAndGetStoredFirst() {

    final var element = getStoredFirst();

    removeFirst();

    return element;
  }

  //method
  /**
   * The complexity of this implementation is O(n) if the current
   * {@link LinkedList} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public E removeAndGetStoredFirst(final Predicate<E> selector) {

    final var element = getStoredFirst(selector);

    removeFirst(selector);

    return element;
  }

  //method
  /**
   * The complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public E removeAndGetStoredLast() {

    final var element = getStoredLast();

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
    switch (getCount()) {
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
   * The complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public void removeFirstStrictly() {

    //Enumerates the element count of the current LinkedList.
    switch (getCount()) {
      case 0:
        throw EmptyArgumentException.forArgument(this);
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
   * The complexity of this implementation is O(n) if the current
   * {@link LinkedList} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public void removeFirst(final Predicate<E> selector) {

    //Handles the case that the current LinkedList contains elements.
    if (containsAny()) {
      removeFirstWhenContainsAny(selector);
    }
  }

  //method
  /**
   * The complexity of this implementation is O(n) if the current
   * {@link LinkedList} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public void removeFirstOccurrenceOf(final Object element) {

    //Handles the case that the current LinkedList contains any.
    if (containsAny()) {
      removeFirstOccurrenceOfWhenContainsAny(element);
    }
  }

  //method
  /**
   * The complexity of this implementation is O(n).
   * 
   * {@inheritDoc}
   */
  @Override
  public void removeLast() {

    //Handles the case that the current LinkedList contains elements.
    if (containsAny()) {
      removeLastWhenContainsAny();
    }
  }

  //method
  /**
   * The complexity of this implementation is O(n).
   * 
   * {@inheritDoc}
   */
  @Override
  public void removeLastStrictly() {

    assertContainsAny();

    removeLastWhenContainsAny();
  }

  //method
  /**
   * The complexity of this implementation is O(n).
   * 
   * {@inheritDoc}
   */
  @Override
  public void removeStrictlyFirstOccurrenceOf(Object element) {

    //Handles the case that the current LinkedList contains any.
    if (containsAny()) {
      removeStrictlyFirstOccurrenceOfWhenContainsAny(element);
    }
  }

  //method
  /**
   * The complexity of this implementation is O(n) if the current
   * {@link LinkedList} contains n elements.
   *
   * {@inheritDoc}
   */
  @Override
  public void replaceFirst(final Predicate<E> selector, final E element) {

    var iterator = firstNode;

    while (true) { //NOSONAR: In this case, break statements are nicer than a terminal condition.

      if (selector.test(iterator.getElement())) {
        iterator.setElement(element);
        break;
      }

      if (iterator.hasNextNode()) {
        iterator = iterator.getNextNode();
      } else {
        break;
      }
    }
  }

  //method
  /**
   * This implementation uses the merge sort algorithm. The complexity of this
   * implementation is O(n*log(n)) if the current {@link Container} contains n
   * elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public <C extends Comparable<C>> IContainer<E> toOrderedList(final Function<E, C> norm) {
    return getOrderedSubList(1, getCount(), norm);
  }

  //method
  /**
   * The complexity of this implementation is O(n) if the current
   * {@link LinkedList} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalogue.COMMA);
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
   * Adds the given elements at the begin of the current {@link LinkedList} for
   * the case that the current {@link LinkedList} is not empty. The elements will
   * be added in the given order.
   * 
   * @param elements
   * @throws RuntimeException if one of the given elements is null.
   */
  private void addAtBeginWhenContainsAny(E[] elements) {

    LinkedListNode<E> newFirstNode = null;
    LinkedListNode<E> iteratorNode = null;

    for (final var e : elements) {

      final var newNode = new LinkedListNode<>(e);

      if (iteratorNode == null) {
        newFirstNode = newNode;
      } else {
        iteratorNode.setNextNode(newNode);
      }

      iteratorNode = newNode;
    }

    if (newFirstNode != null) {
      iteratorNode.setNextNode(firstNode);
      firstNode = newFirstNode;
    }

    elementCount += elements.length;
  }

  //method
  //method
  /**
   * Adds the given elements at the begin of the current {@link LinkedList} for
   * the case that the current {@link LinkedList} is empty. The elements will be
   * added in the given order.
   * 
   * @param elements
   * @throws RuntimeException if one of the given elements is null.
   */
  private void addAtBeginWhenIsEmpty(E[] elements) {

    LinkedListNode<E> iteratorNode = null;

    for (final var e : elements) {

      final var newNode = new LinkedListNode<>(e);

      if (iteratorNode == null) {
        firstNode = newNode;
      } else {
        iteratorNode.setNextNode(newNode);
      }

      iteratorNode = newNode;
    }

    lastNode = iteratorNode;
    elementCount += elements.length;
  }

  //method
  /**
   * @throws EmptyArgumentException if the current {@link LinkedList} is empty.
   */
  private void assertContainsAny() {
    if (isEmpty()) {
      throw EmptyArgumentException.forArgument(this);
    }
  }

  //method
  /**
   * @param startIndex
   * @param endIndex
   * @param norm
   * @param <C>        is the type of the {@link Comparable}s the given norm
   *                   returns.
   * @return a new {@link LinkedList} with the elements from the given start index
   *         to the given end index ordered according to the given norm.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  private <C extends Comparable<C>> LinkedList<E> getOrderedSubList(
    final int startIndex,
    final int endIndex,
    final Function<E, C> norm) {

    //Searches for the start node.
    var startNode = firstNode;
    for (var i = 1; i < startIndex; i++) {
      startNode = startNode.getNextNode();
    }

    //Calculates the length of the sub list.
    final var length = (endIndex - startIndex) + 1;

    //Handles the case when the sub list contains 1 element.
    if (length == 1) {
      return LinkedList.withElement(startNode.getElement());
    }

    //Handles the case when the sub list contains 2 elements.
    if (length == 2) {

      final var list = new LinkedList<E>();

      final Comparable element1Value = norm.apply(startNode.getElement());
      final Comparable element2Value = norm.apply(startNode.getNextNode().getElement());
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
        list.addAtEnd(subList2.getStoredFirst());
        subList2.removeFirst();
      } else if (subList2.isEmpty()) {
        list.addAtEnd(subList1.getStoredFirst());
        subList1.removeFirst();

      } else {
        final Comparable value1 = norm.apply(subList1.getStoredFirst());
        final Comparable value2 = norm.apply(subList2.getStoredFirst());
        if (value1.compareTo(value2) > 0) {
          list.addAtEnd(subList2.getStoredFirst());
          subList2.removeFirst();
        } else {
          list.addAtEnd(subList1.getStoredFirst());
          subList1.removeFirst();
        }
      }
    }
    return list;
  }

  //method
  /**
   * The complexity of this implementation is O(n) if the current
   * {@link LinkedList} contains n elements.
   * 
   * Removes the first occurrence of the given element from the current
   * {@link ILinkedList} for the case that the current {@link ILinkedList}
   * contains elements.
   * 
   * @param element
   */
  private void removeFirstOccurrenceOfWhenContainsAny(final Object element) {
    if (firstNode.contains(element)) {
      removeFirst();
    } else {

      var iterator = firstNode;
      while (iterator.hasNextNode()) {

        final var nextNode = iterator.getNextNode();

        if (nextNode.contains(element)) {
          removeNextNode(iterator);
          break;
        }

        iterator = nextNode;
      }
    }
  }

  //method
  /**
   * Removes the first element the given selector selects from the current
   * {@link ILinkedList} for the case that the current {@link LinkedList} contains
   * any.
   * 
   * @param selector
   */
  private void removeFirstWhenContainsAny(final Predicate<E> selector) {
    if (selector.test(getStoredFirst())) {
      removeFirst();
    } else {
      var iterator = firstNode;
      while (iterator.hasNextNode()) {

        final LinkedListNode<E> nextNode = iterator.getNextNode();

        if (selector.test(nextNode.getElement())) {
          removeNextNode(iterator);
          break;
        }

        iterator = nextNode;
      }
    }
  }

  //method
  /**
   * The complexity of this implementation is O(n).
   * 
   * Removes the last element from the current {@link LinkedList} for the case
   * that the current {@link LinkedList} contains elements.
   */
  private void removeLastWhenContainsAny() {

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
   * Removes the next node of the given node.
   * 
   * @param node
   * @throws ArgumentIsNullException               if the given node is null.
   * @throws ArgumentDoesNotHaveAttributeException if the given node does not have
   *                                               a next node.
   */
  private void removeNextNode(final LinkedListNode<E> node) {

    //Asserts that the given node is not null.
    GlobalValidator.assertThat(node).thatIsNamed(LowerCaseVariableCatalogue.NODE).isNotNull();

    final var nextNode = node.getNextNode();

    if (nextNode.hasNextNode()) {
      node.setNextNode(nextNode.getNextNode());
    } else {
      node.removeNextNode();
      lastNode = node;
    }

    elementCount--;
  }

  //method
  /**
   * The complexity of this implementation is O(n) if the current
   * {@link LinkedList} contains n elements.
   * 
   * Removes the first occurrence of the given element from the current
   * {@link ILinkedList} for the case that the current {@link ILinkedList}
   * contains elements.
   * 
   * @param element
   * @throws ArgumentDoesNotContainElementException if the current
   *                                                {@link LinkedList} does not
   *                                                contain the given element.
   */
  private void removeStrictlyFirstOccurrenceOfWhenContainsAny(final Object element) {
    if (firstNode.contains(element)) {
      removeFirst();
    } else {

      var iterator = firstNode;
      while (iterator.hasNextNode()) {

        final var nextNode = iterator.getNextNode();

        if (nextNode.contains(element)) {
          removeNextNode(iterator);
          return;
        }

        iterator = nextNode;
      }

      throw ArgumentDoesNotContainElementException.forArgumentAndElement(this, element);
    }
  }
}
