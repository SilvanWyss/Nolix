package ch.nolix.techapi.math.bigdecimalmath;

/**
 * @author Silvan Wyss
 */
public interface ISequenceDefinedBy1Predecessor<V> extends ISequence<V> {
  V getFirstValue();
}
