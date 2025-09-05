package ch.nolix.systemapi.objectdata.model;

public interface IBaseValueField<V> extends IField {
  Class<V> getValueType();
}
