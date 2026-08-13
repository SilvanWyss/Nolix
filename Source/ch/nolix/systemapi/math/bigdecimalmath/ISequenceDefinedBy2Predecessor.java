/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.math.bigdecimalmath;

/**
 * @author Silvan Wyss
 * @param <V> the type of the values of a {@link ISequenceDefinedBy2Predecessor}
 */
public interface ISequenceDefinedBy2Predecessor<V> extends ISequence<V> {
  V getFirstValue();

  V getSecondValue();
}
