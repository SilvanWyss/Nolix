package ch.nolix.systemapi.objectdataapi.modelapi;

public interface IAbstractValue<V> extends IField {

  Class<V> getValueType();
}
