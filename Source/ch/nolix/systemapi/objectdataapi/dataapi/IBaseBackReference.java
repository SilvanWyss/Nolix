//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//interface
public interface IBaseBackReference<E extends IEntity> extends IProperty {

  //method declaration
  String getBackReferencedPropertyName();

  //method declaration
  String getBackReferencedTableName();

  //method declaration
  ITable<E> getStoredBackReferencedTable();
}
