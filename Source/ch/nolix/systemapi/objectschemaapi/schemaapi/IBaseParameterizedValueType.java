//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

//interface
public interface IBaseParameterizedValueType<V> extends IParameterizedFieldType {

  //method declaration
  Class<V> getValueClass();
}
