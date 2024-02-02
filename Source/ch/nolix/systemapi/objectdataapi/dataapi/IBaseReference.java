//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//interface
public interface IBaseReference<E extends IEntity> extends IProperty {

  //method declaration
  ITable<E> getReferencedTable();

  //method declaration
  String getReferencedTableName();
}
