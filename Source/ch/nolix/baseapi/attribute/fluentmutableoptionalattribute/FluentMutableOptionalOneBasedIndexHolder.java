/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalOneBasedIndexHolder;

/**
 * A {@link FluentMutableOptionalOneBasedIndexHolder} is a
 * {@link OptionalOneBasedIndexHolder} whose one-based index can be set
 * programmatically and fluently and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableOptionalOneBasedIndexHolder}
 */
public interface FluentMutableOptionalOneBasedIndexHolder<H extends FluentMutableOptionalOneBasedIndexHolder<H>>
extends OptionalOneBasedIndexHolder {
  /**
   * Removes the one-based index of the current
   * {@link FluentMutableOptionalOneBasedIndexHolder}.
   */
  void removeOneBasedIndex();

  /**
   * Sets the one-based index of the current
   * {@link FluentMutableOptionalOneBasedIndexHolder}.
   * 
   * @param oneBasedIndex
   * @return the current {@link FluentMutableOptionalOneBasedIndexHolder}
   */
  H setOneBasedIndex(int oneBasedIndex);
}
