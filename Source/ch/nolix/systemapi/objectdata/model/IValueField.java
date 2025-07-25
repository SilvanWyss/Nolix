package ch.nolix.systemapi.objectdata.model;

public interface IValueField<V> extends IAbstractValueField<V> {

  V getStoredValue();

  void setValue(V value);

  void setValueFromString(String string);
}
