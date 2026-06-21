/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.container.matrix;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of the parent {@link IMatrix} of a
 *            {@link IMatrixRow}.
 */
public interface IMatrixRow<E> extends ExtendedIterable<E> {
  /**
   * @return the one-based row index of the current {@link IMatrixRow}.
   */
  int getOneBasedRowIndex();
}
