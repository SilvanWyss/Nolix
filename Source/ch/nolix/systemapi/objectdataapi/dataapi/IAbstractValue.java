package ch.nolix.systemapi.objectdataapi.dataapi;

public interface IAbstractValue<V> extends IField {

  Class<V> getValueType();
}
