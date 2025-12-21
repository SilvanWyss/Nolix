package ch.nolix.systemapi.objectdata.model;

/**
 * @author Silvan Wyss
 * @param <V> is the type of the value of a {@link IValueField}.
 */
public interface IValueField<V> extends IBaseValueField<V> {
  V getStoredValue();

  void setValue(V value);

  void setValueFromString(String string);
}
