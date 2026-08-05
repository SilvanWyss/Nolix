/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.linkedlist;

import java.util.function.Predicate;

import ch.nolix.base.commontype.iterableexaminer.IterableExaminer;
import ch.nolix.base.datastructure.arraylist.ArrayList;
import ch.nolix.base.datastructure.extendediterable.AbstractExtendedIterable;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.copyableiterator.CopyableIterator;
import ch.nolix.baseapi.datastructure.list.IArrayList;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.baseapi.foundation.marker.Marker;
import ch.nolix.baseapi.generalcatalog.textcatalog.CharacterCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PluralLowerCaseVariableNameCatalog;

/**
 * A {@link LinkedList} is a {@link AbstractExtendedIterable} that can add
 * elements at the begin or end. A {@link LinkedList} is clearable.
 * 
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link LinkedList}.
 */
public final class LinkedList<E> // NOSONAR: A LinkedList is a principal object thus it has many methods.
extends AbstractExtendedIterable<E>
implements ILinkedList<E> {
  private static final IterableExaminer ITERABLE_EXAMINER = new IterableExaminer();

  private int elementCount;

  private LinkedListNode<E> firstNode;

  private LinkedListNode<E> lastNode;

  /**
   * Creates a new empty {@link LinkedList}.
   */
  private LinkedList() {
  }

  /**
   * @param <T> the type of the elements of the created {@link LinkedList}
   * @return a new empty {@link LinkedList}.
   */
  public static <T> LinkedList<T> createEmpty() {
    return new LinkedList<>();
  }

  /**
   * @param array
   * @param <T>   the type of the elements of the given array
   * @return a new {@link LinkedList} with the elements in the given array
   * @throws RuntimeException if the given array is null
   * @throws RuntimeException if one of the elements in the given array is null
   */
  public static <T> LinkedList<T> fromArray(final T[] array) {
    Validator.assertThat(array).thatIsNamed(LowerCaseVariableNameCatalog.ARRAY).isNotNull();

    final var list = new LinkedList<T>();
    list.addAtEnd(array);

    return list;
  }

  /**
   * @param container
   * @param <T>       the type of the elements of the given container
   * @return a new {@link LinkedList} with the elements in the given container
   * @throws RuntimeException if the given container is null
   * @throws RuntimeException if one of the elements in the given container is
   *                          null.
   */
  public static <T> LinkedList<T> fromIterable(final Iterable<T> container) {
    final var list = new LinkedList<T>();
    list.addAtEnd(container);

    return list;
  }

  /**
   * @param element
   * @param <T>     the type of the given element
   * @return a new {@link LinkedList} with the given element
   * @throws RuntimeException if one of the given element is null
   */
  @SuppressWarnings("unchecked")
  public static <T> LinkedList<T> withElement(final T... element) {
    final var list = new LinkedList<T>();

    list.addAtEnd(element);

    return list;
  }

  /**
   * @param elements
   * @param <T>      the type of the given elements
   * @return a new {@link LinkedList} with the given elements
   * @throws RuntimeException if one of the given elements is null
   */
  @SuppressWarnings("unchecked")
  public static <T> LinkedList<T> withElements(final T... elements) {
    final var list = new LinkedList<T>();

    list.addAtEnd(elements);

    return list;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addAtBegin(final E element) {
    final var node = LinkedListNode.withElement(element);

    if (isEmpty()) {
      firstNode = node;
      lastNode = node;
    } else {
      node.setNextNode(firstNode);
      firstNode = node;
    }

    elementCount++;
  }

  /**
   * The time complexity of this implementation is O(n) if n elements are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public void addAtBegin(@SuppressWarnings("unchecked") E... elements) {
    if (isEmpty()) {
      addAtBeginWhenIsEmpty(elements);
    } else {
      addAtBeginWhenContainsAny(elements);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addAtBegin(final Iterable<? extends E> elements) {
    // Asserts that the given elements is not null.
    Validator.assertThat(elements).thatIsNamed(PluralLowerCaseVariableNameCatalog.ELEMENTS).isNotNull();

    // Handles the case that the given elements is not empty.
    if (ITERABLE_EXAMINER.containsAny(elements)) {
      final LinkedListNode<E> newFirstNode = LinkedListNode.withElement(elements.iterator().next());

      LinkedListNode<E> node = null;

      for (final var e : elements) {
        if (node == null) {
          node = newFirstNode;
        } else {
          final var currentNode = LinkedListNode.withElement(e);
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

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public void addAtEnd(final E element) {
    // Creates new node.
    final var node = LinkedListNode.withElement(element);

    if (isEmpty()) {
      firstNode = node;
      lastNode = node;
    } else {
      lastNode.setNextNode(node);
      lastNode = node;
    }
    elementCount++;
  }

  /**
   * Adds the given elements at the end of the current {@link LinkedList}. The
   * complexity of this implementation is O(n) if n elements are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public void addAtEnd(final Iterable<? extends E> elements) {
    elements.forEach(this::addAtEnd);
  }

  /**
   * The time complexity of this implementation is O(n) if n elements are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public <T extends E> void addAtEnd(@SuppressWarnings("unchecked") T... elements) {
    // Iterates the given elements.
    for (final var e : elements) {
      addAtEnd(e);
    }
  }

  /**
   * The complexity of this implementation is O(n) when the current
   * {@link LinkedList} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    // Handles the case that the current list contains any elements.
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

  /**
   * An object equals a list if it is a list containing exactly the same elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object object) {
    // Handles the case that the given object is a LinkedList.
    if (object instanceof final LinkedList<?> linkedList) {
      return containsExactlyInSameOrder(linkedList);
    }

    // Handles the case that the given object is not a LinkedList.
    return false;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link LinkedList} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public ILinkedList<E> getCopy() {
    // Creates a LinkedList.
    final var copy = new LinkedList<E>();

    // Iterates the current LinkedList.
    for (final var e : this) {
      copy.addAtEnd(e);
    }

    return copy;
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * @return the number of elements of the current {@link LinkedList}.
   */
  @Override
  public int getCount() {
    return elementCount;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * @param oneBasedIndex
   * @return the element at the given index
   * @throws RuntimeException                      if the given index is not
   *                                               positive
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractExtendedIterable}
   *                                               does not contain an element at
   *                                               the given index.
   */
  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {
    assertContainsAny();

    if (oneBasedIndex == getCount()) {
      return lastNode.getElement();
    }

    // Iterates the current LinkedList.
    var index = 1;
    for (final var e : this) {
      // Asserts that the current index is the given index.
      if (index == oneBasedIndex) {
        return e;
      }

      index++;
    }

    throw ArgumentIsOutOfRangeException.forArgumentAndArgumentNameAndRangeWithMinAndMax(
      oneBasedIndex,
      "1-based index",
      1,
      getCount());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return true;
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * @return a new iterator of the current {@link LinkedList}.
   */
  @Override
  public CopyableIterator<E> iterator() {
    return LinkedListIterator.withNullableFirstNode(firstNode);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link LinkedList} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public void removeAll(final Predicate<E> selector) {
    final var remainingElements = getStoredOthers(selector);

    clear();

    addAtEnd(remainingElements);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link LinkedList} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public void removeAllOccurrencesOf(final Object element) {
    removeAll(e -> e == element);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public E removeAndGetStoredFirst() {
    final var element = getStoredFirstNonNull();

    removeFirst();

    return element;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
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

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public E removeAndGetStoredLast() {
    final var element = getStoredLast();

    removeLast();

    return element;
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public void removeFirst() {
    // Enumerates the element count of the current LinkedList.
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

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public void removeFirstStrictly() {
    // Enumerates the element count of the current LinkedList.
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

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link LinkedList} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public void removeFirst(final Predicate<E> selector) {
    // Handles the case that the current LinkedList contains elements.
    if (containsAny()) {
      removeFirstWhenContainsAny(selector);
    }
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link LinkedList} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public void removeFirstOccurrenceOf(final Object element) {
    // Handles the case that the current LinkedList contains any.
    if (containsAny()) {
      removeFirstOccurrenceOfWhenContainsAny(element);
    }
  }

  /**
   * The time complexity of this implementation is O(n).
   * 
   * {@inheritDoc}
   */
  @Override
  public void removeLast() {
    // Handles the case that the current LinkedList contains elements.
    if (containsAny()) {
      removeLastWhenContainsAny();
    }
  }

  /**
   * The time complexity of this implementation is O(n).
   * 
   * {@inheritDoc}
   */
  @Override
  public void removeLastStrictly() {
    assertContainsAny();

    removeLastWhenContainsAny();
  }

  /**
   * The time complexity of this implementation is O(n).
   * 
   * {@inheritDoc}
   */
  @Override
  public void removeStrictlyFirstOccurrenceOf(Object element) {
    // Handles the case that the current LinkedList contains any.
    if (containsAny()) {
      removeStrictlyFirstOccurrenceOfWhenContainsAny(element);
    }
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link LinkedList} contains n elements.
   *
   * {@inheritDoc}
   */
  @Override
  public void replaceFirst(final Predicate<E> selector, final E element) {
    var iterator = firstNode;

    while (true) { // NOSONAR: In this case, break statements are nicer than a terminal condition.

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

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link LinkedList} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toStringWithDelimiter(CharacterCatalog.COMMA);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <T> IArrayList<T> createEmptyArrayListFromMarkerWithInitialCapacity(
    final Marker<T> marker,
    final int initialCapacity) {
    return ArrayList.withInitialCapacity(initialCapacity);
  }

  /**
   * Adds the given elements at the begin of the current {@link LinkedList} for
   * the case that the current {@link LinkedList} is not empty. The elements will
   * be added in the given order.
   * 
   * @param elements
   * @throws RuntimeException if one of the given elements is null
   */
  @SuppressWarnings("null")
  private void addAtBeginWhenContainsAny(E[] elements) {
    LinkedListNode<E> newFirstNode = null;
    LinkedListNode<E> iteratorNode = null;

    for (final var e : elements) {
      final var newNode = LinkedListNode.withElement(e);

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

  /**
   * Adds the given elements at the begin of the current {@link LinkedList} for
   * the case that the current {@link LinkedList} is empty. The elements will be
   * added in the given order.
   * 
   * @param elements
   * @throws RuntimeException if one of the given elements is null
   */
  private void addAtBeginWhenIsEmpty(E[] elements) {
    LinkedListNode<E> iteratorNode = null;

    for (final var e : elements) {
      final var newNode = LinkedListNode.withElement(e);

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

  /**
   * @throws RuntimeException if the current {@link LinkedList} is empty
   */
  private void assertContainsAny() {
    if (isEmpty()) {
      throw EmptyArgumentException.forArgument(this);
    }
  }

  /**
   * The time complexity of this implementation is O(n) if the current
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

  /**
   * Removes the first element the given selector selects from the current
   * {@link ILinkedList} for the case that the current {@link LinkedList} contains
   * any.
   * 
   * @param selector
   */
  private void removeFirstWhenContainsAny(final Predicate<E> selector) {
    if (selector.test(getStoredFirstNonNull())) {
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

  /**
   * The time complexity of this implementation is O(n).
   * 
   * Removes the last element from the current {@link LinkedList} for the case
   * that the current {@link LinkedList} contains elements.
   */
  private void removeLastWhenContainsAny() {
    // Handles the case that the current list contains 1 element.
    if (containsOne()) {
      clear();

      // Handles the case that the current list contains several elements.
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

  /**
   * Removes the next node of the given node.
   * 
   * @param node
   * @throws RuntimeException                      if the given node is null
   * @throws ArgumentDoesNotHaveAttributeException if the given node does not have
   *                                               a next node.
   */
  private void removeNextNode(final LinkedListNode<E> node) {
    // Asserts that the given node is not null.
    Validator.assertThat(node).thatIsNamed(LowerCaseVariableNameCatalog.NODE).isNotNull();

    final var nextNode = node.getNextNode();

    if (nextNode.hasNextNode()) {
      node.setNextNode(nextNode.getNextNode());
    } else {
      node.removeNextNode();
      lastNode = node;
    }

    elementCount--;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
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
