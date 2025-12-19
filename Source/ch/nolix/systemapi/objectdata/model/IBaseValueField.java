package ch.nolix.systemapi.objectdata.model;

/**
 * @author Silvan Wyss
 */
public interface IBaseValueField<V> extends IField {
  Class<V> getValueType();
}
