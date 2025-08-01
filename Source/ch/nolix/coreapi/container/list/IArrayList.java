package ch.nolix.coreapi.container.list;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.objectcreation.copier.Copyable;
import ch.nolix.coreapi.state.statemutation.Clearable;

/**
 * A {@link IArrayList} is a {@link IContainer} that can add and remove
 * elements.
 * 
 * @author Silvan Wyss
 * @version 2024-01-30
 * @param <E> is the type of the elements of a {@link IArrayList}.
 */
public interface IArrayList<E> extends Clearable, Copyable<IArrayList<E>>, IContainer<E> {

  /**
   * Adds the given element at the end of the current {@link IArrayList}.
   * 
   * @param element
   * @throws RuntimeException if the given elements is null.
   */
  void addAtEnd(E element);

  /**
   * Adds the given given elements at the end of the current {@link IArrayList}.
   * The elements will be added in the given order.
   * 
   * @param element
   * @param elements
   * @throws RuntimeException if the given element is null.
   * @throws RuntimeException if the given elements is null.
   * @throws RuntimeException if one of the given elements is null.
   */
  void addAtEnd(E element, @SuppressWarnings("unchecked") E... elements);

  /**
   * Adds the given elements at the end of the current {@link IArrayList}. The
   * elements will be added in the given order.
   * 
   * @param elements
   * @throws RuntimeException if the given elements is null.
   * @throws RuntimeException if one of the given elements is null.
   */
  void addAtEnd(E[] elements);

  /**
   * Adds the given elements at the end of the current {@link IArrayList}. The
   * elements will be added in the given order.
   * 
   * @param elements
   * @throws RuntimeException if the given elements is null.
   * @throws RuntimeException if one of the given elements is null.
   */
  void addAtEnd(Iterable<? extends E> elements);
}
