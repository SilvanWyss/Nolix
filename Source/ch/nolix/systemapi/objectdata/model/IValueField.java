package ch.nolix.systemapi.objectdata.model;

public interface IValueField<V> extends IBaseValueField<V> {
  V getStoredValue();

  void setValue(V value);

  void setValueFromString(String string);
}
