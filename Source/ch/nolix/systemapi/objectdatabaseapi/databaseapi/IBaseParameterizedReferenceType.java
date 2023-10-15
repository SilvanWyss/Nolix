//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//interface
public interface IBaseParameterizedReferenceType<

    E extends IEntity>
    extends IParameterizedPropertyType {

  // method declaration
  ITable<E> getStoredencedTable();
}
