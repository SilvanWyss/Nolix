/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.container.matrix;

import ch.nolix.baseapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of the parent {@link IMatrix} of a
 *            {@link IMatrixRow}.
 */
public interface IMatrixRow<E> extends IContainer<E> {
  /**
   * @return the one-based row index of the current {@link IMatrixRow}.
   */
  int getOneBasedRowIndex();
}
