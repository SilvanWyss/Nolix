package ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.IOneBasedIndexHolder;

/**
 * A {@link IFluentMutableOneBasedIndexHolder} is a {@link IOneBasedIndexHolder}
 * whose one-based index can be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2025-06-01
 * @param <I> is the type of a {@link IFluentMutableOneBasedIndexHolder}.
 */
public interface IFluentMutableOneBasedIndexHolder<I extends IFluentMutableOneBasedIndexHolder<I>>
extends IOneBasedIndexHolder {
  /**
   * Sets the one-based index of the current
   * {@link IFluentMutableOneBasedIndexHolder}.
   * 
   * @param oneBasedIndex
   * @return the current {@link IFluentMutableOneBasedIndexHolder}.
   */
  I setOneBasedIndex(int oneBasedIndex);
}
