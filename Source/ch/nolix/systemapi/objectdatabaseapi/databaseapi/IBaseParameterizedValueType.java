//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//interface
public interface IBaseParameterizedValueType<V> extends IParameterizedPropertyType {

  //method declaration
  Class<V> getValueType();
}
