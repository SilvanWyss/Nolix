package ch.nolix.systemapi.objectdataapi.dataapi;

public interface IBaseParameterizedValueType<V> extends IParameterizedFieldType {

  Class<V> getValueType();
}
