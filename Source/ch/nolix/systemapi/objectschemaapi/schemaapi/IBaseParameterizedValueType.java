package ch.nolix.systemapi.objectschemaapi.schemaapi;

public interface IBaseParameterizedValueType<V> extends IParameterizedFieldType {

  Class<V> getValueClass();
}
