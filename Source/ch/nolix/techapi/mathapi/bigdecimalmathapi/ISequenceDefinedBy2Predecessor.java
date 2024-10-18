package ch.nolix.techapi.mathapi.bigdecimalmathapi;

public interface ISequenceDefinedBy2Predecessor<V> extends ISequence<V> {

  V getFirstValue();

  V getSecondValue();
}
