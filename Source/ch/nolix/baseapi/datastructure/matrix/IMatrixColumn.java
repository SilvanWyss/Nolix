/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.matrix;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of the parent {@link IMatrix} of a
 *            {@link IMatrixColumn}.
 */
public interface IMatrixColumn<E> extends ExtendedIterable<E> {
  /**
   * @return the one-based column index of the current {@link IMatrixColumn}
   */
  int getOneBasedColumnIndex();
}
