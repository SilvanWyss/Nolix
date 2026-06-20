/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.OneBasedIndexHolder;

/**
 * A {@link FluentMutableOneBasedIndexHolder} is a {@link OneBasedIndexHolder}
 * whose one-based index can be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableOneBasedIndexHolder}
 */
public interface FluentMutableOneBasedIndexHolder<H extends FluentMutableOneBasedIndexHolder<H>>
extends OneBasedIndexHolder {
  /**
   * Sets the one-based index of the current
   * {@link FluentMutableOneBasedIndexHolder}.
   * 
   * @param oneBasedIndex
   * @return the current {@link FluentMutableOneBasedIndexHolder}
   */
  H setOneBasedIndex(int oneBasedIndex);
}
