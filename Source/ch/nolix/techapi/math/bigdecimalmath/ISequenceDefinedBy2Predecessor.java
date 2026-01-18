/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.techapi.math.bigdecimalmath;

/**
 * @author Silvan Wyss
 * @param <V> is the type of the values of a
 *            {@link ISequenceDefinedBy2Predecessor}
 */
public interface ISequenceDefinedBy2Predecessor<V> extends ISequence<V> {
  V getFirstValue();

  V getSecondValue();
}
