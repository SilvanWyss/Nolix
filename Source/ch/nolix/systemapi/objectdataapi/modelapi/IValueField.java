package ch.nolix.systemapi.objectdataapi.modelapi;

public interface IValueField<V> extends IAbstractValueField<V> {

  V getStoredValue();

  void setValue(V value);

  void setValueFromString(String string);
}
