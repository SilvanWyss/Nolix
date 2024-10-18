package ch.nolix.systemapi.objectdataapi.dataapi;

public interface IBaseValue<V> extends IField {

  Class<V> getValueType();
}
