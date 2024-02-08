//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//interface
public interface IBaseValue<V> extends IProperty {

  //method declaration
  Class<V> getValueType();
}
