/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.container.list;

/**
 * A {@link IAppendableList} is a list that can add elements at its end.
 * 
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link IAppendableList}
 */
public interface IAppendableList<E> {
  /**
   * Adds the given element at the end of the current {@link IAppendableList}.
   * 
   * @param element
   * @throws RuntimeException if the given elements is null
   */
  void addAtEnd(E element);

  /**
   * Adds the given elements at the end of the current {@link IAppendableList}.
   * The elements will be added in the given order.
   * 
   * @param elements
   * @throws RuntimeException if the given elements is null
   * @throws RuntimeException if one of the given elements is null
   */
  void addAtEnd(Iterable<? extends E> elements);

  /**
   * Adds the given elements at the end of the current {@link IAppendableList}.
   * The elements will be added in the given order.
   * 
   * @param elements
   * @param <T>      the type of the given elements
   * @throws RuntimeException if the given elements is null
   * @throws RuntimeException if one of the given elements is null
   */
  <T extends E> void addAtEnd(@SuppressWarnings("unchecked") T... elements);
}
