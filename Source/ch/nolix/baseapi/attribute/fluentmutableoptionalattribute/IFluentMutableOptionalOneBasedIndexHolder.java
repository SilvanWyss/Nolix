/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.IOptionalOneBasedIndexHolder;

/**
 * A {@link IFluentMutableOptionalOneBasedIndexHolder} is a
 * {@link IOptionalOneBasedIndexHolder} whose one-based index can be set
 * programmatically and fluently and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link IFluentMutableOptionalOneBasedIndexHolder}
 */
public interface IFluentMutableOptionalOneBasedIndexHolder<H extends IFluentMutableOptionalOneBasedIndexHolder<H>>
extends IOptionalOneBasedIndexHolder {
  /**
   * Removes the one-based index of the current
   * {@link IFluentMutableOptionalOneBasedIndexHolder}.
   */
  void removeOneBasedIndex();

  /**
   * Sets the one-based index of the current
   * {@link IFluentMutableOptionalOneBasedIndexHolder}.
   * 
   * @param oneBasedIndex
   * @return the current {@link IFluentMutableOptionalOneBasedIndexHolder}
   */
  H setOneBasedIndex(int oneBasedIndex);
}
