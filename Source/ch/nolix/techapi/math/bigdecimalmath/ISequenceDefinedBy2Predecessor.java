package ch.nolix.techapi.math.bigdecimalmath;

public interface ISequenceDefinedBy2Predecessor<V> extends ISequence<V> {
  V getFirstValue();

  V getSecondValue();
}
