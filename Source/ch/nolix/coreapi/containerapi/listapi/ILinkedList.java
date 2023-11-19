//package declaration
package ch.nolix.coreapi.containerapi.listapi;

import java.util.function.Predicate;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.methodapi.mutationapi.Clearable;
import ch.nolix.coreapi.methodapi.skillapi.Copyable;

//interface
/**
 * A {@link ILinkedList} is a {@link IContainer} where elements can be added or
 * removed.
 * 
 * @author Silvan Wyss
 * @date 2022-07-04
 * @param <E> is the type of the elements of a {@link ILinkedList}.
 */
public interface ILinkedList<E> extends Clearable, Copyable<ILinkedList<E>>, IContainer<E> {

  //method declaration
  /**
   * Adds the given element at the begin of the current {@link ILinkedList}.
   * 
   * @param element
   * @throws RuntimeException if the given elements is null.
   */
  void addAtBegin(E element);

  //method declaration
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

  //method declaration
  /**
   * Adds the given elements at the begin of the current {@link ILinkedList}. The
   * elements will be added in the given order.
   * 
   * @param elements
   * @throws RuntimeException if one of the given elements is null.
   */
  void addAtBegin(E[] elements);

  //method declaration
  /**
   * Adds the given elements at the begin of the current {@link ILinkedList}. The
   * elements will be added in the given order.
   * 
   * @param elements
   * @throws RuntimeException if one of the given elements is null.
   */
  void addAtBegin(Iterable<? extends E> elements);

  //method declaration
  /**
   * Adds the given element at the end of the current {@link ILinkedList}.
   * 
   * @param element
   * @throws RuntimeException if the given elements is null.
   */
  void addAtEnd(E element);

  //method declaration
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

  //method declaration
  /**
   * Adds the given elements at the end of the current {@link ILinkedList}. The
   * elements will be added in the given order.
   * 
   * @param elements
   * @throws RuntimeException if one of the given elements is null.
   */
  void addAtEnd(E[] elements);

  //method declaration
  /**
   * Adds the given elements at the end of the current {@link ILinkedList}. The
   * elements will be added in the given order.
   * 
   * @param elements
   * @throws RuntimeException if one of the given elements is null.
   */
  void addAtEnd(Iterable<? extends E> elements);

  //method declaration
  /**
   * Removes all elements from the current {@link ILinkedList} the given selector
   * selects.
   * 
   * @param selector
   */
  void removeAll(Predicate<E> selector);

  //method declaration
  /**
   * Removes and returns the first element of the current {@link ILinkedList}.
   * 
   * @return the first element of the current {@link ILinkedList}.
   * @throws RuntimeException if the current {@link ILinkedList} is empty.
   */
  E removeAndGetRefFirst();

  //method declaration
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
  E removeAndGetRefFirst(Predicate<E> selector);

  //method declaration
  /**
   * Removes and returns the last element of the current {@link ILinkedList}.
   * 
   * @return the last element of the current {@link ILinkedList}.
   * @throws RuntimeException if the current {@link ILinkedList} is empty.
   */
  E removeAndGetRefLast();

  //method declaration
  /**
   * Removes the first element from the current {@link ILinkedList}.
   */
  void removeFirst();

  //method declaration
  /**
   * Removes the first element the given selector selects from the current
   * {@link ILinkedList}
   * 
   * @param selector
   */
  void removeFirst(Predicate<E> selector);

  //method declaration
  /**
   * Removes the first occurrence of the given element from the current
   * {@link ILinkedList}.
   * 
   * @param element
   */
  void removeFirstOccurrenceOf(Object element);

  //method declaration
  /**
   * Removes the last element from the current {@link ILinkedList}.
   */
  void removeLast();

  //method declaration
  /**
   * Removes the first occurrence of the given element from the current
   * {@link ILinkedList}.
   * 
   * @param element
   * @throws RuntimeException if the current {@link ILinkedList} does not contain
   *                          the given element.
   */
  void removeStrictlyFirstOccurrenceOf(Object element);

  //method declaration
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
