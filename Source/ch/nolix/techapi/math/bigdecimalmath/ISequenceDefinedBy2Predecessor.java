package ch.nolix.techapi.math.bigdecimalmath;

/**
 * @author Silvan Wyss
 */
public interface ISequenceDefinedBy2Predecessor<V> extends ISequence<V> {
  V getFirstValue();

  V getSecondValue();
}
