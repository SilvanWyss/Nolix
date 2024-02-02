//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//interface
public interface IBaseParameterizedReferenceType<E extends IEntity> extends IParameterizedPropertyType {

  //method declaration
  ITable<E> getStoredencedTable();
}
