//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//interface
public interface IBaseBackReference<E extends IEntity> extends IProperty {

  //method declaration
  String getBackReferencedPropertyName();

  //method declaration
  ITable<E> getBackReferencedTable();

  //method declaration
  String getBackReferencedTableName();
}
