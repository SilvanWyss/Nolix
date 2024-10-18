package ch.nolix.systemapi.objectdataapi.dataapi;

public interface IValue<V> extends IBaseValue<V> {

  V getStoredValue();

  void setValue(V value);

  void setValueFromString(String string);
}
