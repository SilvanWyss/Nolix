/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.list;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.objectcomposition.copier.Copyable;
import ch.nolix.baseapi.state.statemutation.Clearable;

/**
 * A {@link IArrayList} is a {@link ExtendedIterable} that can add and remove
 * elements.
 * 
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link IArrayList}.
 */
public interface IArrayList<E> extends Clearable, Copyable<IArrayList<E>>, IAppendableList<E> {
  /**
   * Adds the given element at the given oneBasedIndex
   * 
   * @param oneBasedIndex
   * @param element
   * @throws RuntimeException if the given oneBasedIndex is not positive or bigger
   *                          than the one-incremented number of the elements of
   *                          the current {@link IArrayList}
   * @throws RuntimeException if the given elements is null
   */
  void insertAtOneBasedIndex(int oneBasedIndex, E element);
}
