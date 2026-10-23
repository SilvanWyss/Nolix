/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.math.bigdecimalmath;

/**
 * @author Silvan Wyss
 * @param <V> the type of the values of a {@link ISequenceDefinedBy2Predecessor}
 */
public interface ISequenceDefinedBy2Predecessor<V> extends ISequence<V> {
  /**
   * @return the first value of the current {@link ISequenceDefinedBy2Predecessor}
   */
  V getFirstValue();

  /**
   * @return the second value of the current
   *         {@link ISequenceDefinedBy2Predecessor}
   */
  V getSecondValue();
}
