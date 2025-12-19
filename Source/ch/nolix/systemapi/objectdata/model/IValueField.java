package ch.nolix.systemapi.objectdata.model;

/**
 * @author Silvan Wyss
 */
public interface IValueField<V> extends IBaseValueField<V> {
  V getStoredValue();

  void setValue(V value);

  void setValueFromString(String string);
}
