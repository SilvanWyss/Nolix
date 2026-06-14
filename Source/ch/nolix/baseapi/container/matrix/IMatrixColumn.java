/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.container.matrix;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of the parent {@link IMatrix} of a
 *            {@link IMatrixColumn}.
 */
public interface IMatrixColumn<E> extends IWellOrderContainer<E> {
  /**
   * @return the one-based column index of the current {@link IMatrixColumn}.
   */
  int getOneBasedColumnIndex();
}
