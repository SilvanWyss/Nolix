package ch.nolix.coreapi.container.list;

import java.util.function.Predicate;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.objectcreation.copier.Copyable;
import ch.nolix.coreapi.state.statemutation.Clearable;

/**
 * A {@link ILinkedList} is a {@link IContainer} that can add and remove
 * elements.
 * 
 * @author Silvan Wyss
 * @version 2022-07-04
 * @param <E> is the type of the elements of a {@link ILinkedList}.
 */
public interface ILinkedList<E> extends Clearable, Copyable<ILinkedList<E>>, IContainer<E> {

  /**
   * Adds the given element at the begin of the current {@link ILinkedList}.
   * 
   * @param element
   * @throws RuntimeException if the given elements is null.
   */
  void addAtBegin(E element);

  /**
   * Adds the given element and the given elements at the begin of the current
   * {@link ILinkedList}. The elements will be added in the given order.
   * 
   * @param element
   * @param elements
   * @throws RuntimeException if the given element is null.
   * @throws RuntimeException if one of the given elements is null.
   */
  void addAtBegin(E element, @SuppressWarnings("unchecked") E... elements);

  /**
   * Adds the given elements at the begin of the current {@link ILinkedList}. The
   * elements will be added in the given order.
   * 
   * @param elements
   * @throws RuntimeException if one of the given elements is null.
   */
  void addAtBegin(E[] elements);

  /**
   * Adds the given elements at the begin of the current {@link ILinkedList}. The
   * elements will be added in the given order.
   * 
   * @param elements
   * @throws RuntimeException if one of the given elements is null.
   */
  void addAtBegin(Iterable<? extends E> elements);

  /**
   * Adds the given element at the end of the current {@link ILinkedList}.
   * 
   * @param element
   * @throws RuntimeException if the given elements is null.
   */
  void addAtEnd(E element);

  /**
   * Adds the given element and the given elements at the end of the current
   * {@link ILinkedList}. The elements will be added in the given order.
   * 
   * @param element
   * @param elements
   * @throws RuntimeException if the given element is null.
   * @throws RuntimeException if one of the given elements is null.
   */
  void addAtEnd(E element, @SuppressWarnings("unchecked") E... elements);

  /**
   * Adds the given elements at the end of the current {@link ILinkedList}. The
   * elements will be added in the given order.
   * 
   * @param elements
   * @throws RuntimeException if one of the given elements is null.
   */
  void addAtEnd(E[] elements);

  /**
   * Adds the given elements at the end of the current {@link ILinkedList}. The
   * elements will be added in the given order.
   * 
   * @param elements
   * @throws RuntimeException if one of the given elements is null.
   */
  void addAtEnd(Iterable<? extends E> elements);

  /**
   * Removes all elements from the current {@link ILinkedList} the given selector
   * selects.
   * 
   * @param selector
   */
  void removeAll(Predicate<E> selector);

  /**
   * Removes all occurrences of the given element from the current
   * {@link ILinkedList}.
   * 
   * @param element
   */
  void removeAllOccurrencesOf(Object element);

  /**
   * Removes and returns the first element of the current {@link ILinkedList}.
   * 
   * @return the first element of the current {@link ILinkedList}.
   * @throws RuntimeException if the current {@link ILinkedList} is empty.
   */
  E removeAndGetStoredFirst();

  /**
   * Removes and returns the first element the given selector selects from the
   * current {@link ILinkedList}.
   * 
   * @param selector
   * @return the first element the given selector selects from the current
   *         {@link ILinkedList}.
   * @throws RuntimeException if the current {@link ILinkedList} does not contain
   *                          an element the given selector selects.
   */
  E removeAndGetStoredFirst(Predicate<E> selector);

  /**
   * Removes and returns the last element of the current {@link ILinkedList}.
   * 
   * @return the last element of the current {@link ILinkedList}.
   * @throws RuntimeException if the current {@link ILinkedList} is empty.
   */
  E removeAndGetStoredLast();

  /**
   * Removes the first element from the current {@link ILinkedList}.
   */
  void removeFirst();

  /**
   * Removes the first element from the current {@link ILinkedList}.
   * 
   * @throws RuntimeException if the current {@link ILinkedList} is empty.
   */
  void removeFirstStrictly();

  /**
   * Removes the first element the given selector selects from the current
   * {@link ILinkedList}
   * 
   * @param selector
   */
  void removeFirst(Predicate<E> selector);

  /**
   * Removes the first occurrence of the given element from the current
   * {@link ILinkedList}.
   * 
   * @param element
   */
  void removeFirstOccurrenceOf(Object element);

  /**
   * Removes the last element from the current {@link ILinkedList}.
   */
  void removeLast();

  /**
   * Removes the last element from the current {@link ILinkedList}.
   * 
   * @throws RuntimeException if the current {@link ILinkedList} is empty.
   */
  void removeLastStrictly();

  /**
   * Removes the first occurrence of the given element from the current
   * {@link ILinkedList}.
   * 
   * @param element
   * @throws RuntimeException if the current {@link ILinkedList} does not contain
   *                          the given element.
   */
  void removeStrictlyFirstOccurrenceOf(Object element);

  /**
   * Replaces the first element the given selector selects from the current
   * {@link ILinkedList} with the given element.
   * 
   * @param selector
   * @param element
   * @throws RuntimeException if the given element is null.
   */
  void replaceFirst(Predicate<E> selector, E element);
}
