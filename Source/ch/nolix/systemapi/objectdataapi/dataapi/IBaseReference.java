//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//interface
public interface IBaseReference<E extends IEntity> extends IField {

  //method declaration
  ITable<E> getReferencedTable();

  //method declaration
  String getReferencedTableName();
}
