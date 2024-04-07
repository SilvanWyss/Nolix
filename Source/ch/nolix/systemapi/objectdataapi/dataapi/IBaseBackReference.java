//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//interface
public interface IBaseBackReference<E extends IEntity> extends IField {

  //method declaration
  String getBackReferencedFieldName();

  //method declaration
  String getBackReferencedTableName();

  //method declaration
  ITable<E> getStoredBackReferencedTable();
}
