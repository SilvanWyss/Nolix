package ch.nolix.systemapi.objectdataapi.modelapi;

public interface IAbstractValueField<V> extends IField {

  Class<V> getValueType();
}
