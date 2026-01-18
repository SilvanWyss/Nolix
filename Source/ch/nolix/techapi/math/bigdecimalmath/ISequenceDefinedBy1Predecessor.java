/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.techapi.math.bigdecimalmath;

/**
 * @author Silvan Wyss
 * @param <V> is the type of the values of a
 *            {@link ISequenceDefinedBy1Predecessor}.
 */
public interface ISequenceDefinedBy1Predecessor<V> extends ISequence<V> {
  V getFirstValue();
}
