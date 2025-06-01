package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalOneBasedIndexHolder;

/**
 * A {@link IFluentMutableOptionalOneBasedIndexHolder} is a
 * {@link IOptionalOneBasedIndexHolder} whose one-based index can be set and
 * removed programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2025-06-01
 * @param <I> is the type of a
 *            {@link IFluentMutableOptionalOneBasedIndexHolder}.
 */
public interface IFluentMutableOptionalOneBasedIndexHolder<I extends IFluentMutableOptionalOneBasedIndexHolder<I>>
extends IOptionalOneBasedIndexHolder {

  /**
   * Removes the one-based index of the current
   * {@link IFluentMutableOptionalOneBasedIndexHolder}.
   * 
   * @return the current {@link IFluentMutableOptionalOneBasedIndexHolder}.
   */
  I removeOneBasedIndex();

  /**
   * Sets the one-based index of the current
   * {@link IFluentMutableOptionalOneBasedIndexHolder}.
   * 
   * @param oneBasedIndex
   * @return the current {@link IFluentMutableOptionalOneBasedIndexHolder}.
   */
  I setOneBasedIndex(int oneBasedIndex);
}
