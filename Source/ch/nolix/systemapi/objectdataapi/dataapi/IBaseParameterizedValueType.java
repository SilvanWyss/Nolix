//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//interface
public interface IBaseParameterizedValueType<V> extends IParameterizedPropertyType {

  //method declaration
  Class<V> getValueType();
}
