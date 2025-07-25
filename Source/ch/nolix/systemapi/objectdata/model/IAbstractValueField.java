package ch.nolix.systemapi.objectdata.model;

public interface IAbstractValueField<V> extends IField {

  Class<V> getValueType();
}
