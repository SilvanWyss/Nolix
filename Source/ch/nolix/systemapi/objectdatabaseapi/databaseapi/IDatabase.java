//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IDatabase extends IDatabaseObject {

  //method declarations
  <E extends IEntity> IContainer<E> getStoredEntitiesByType(Class<E> type);

  //method declaration
  <E extends IEntity> ITable<E> getStoredTableByEntityType(Class<E> entityClass);

  //method declaration
  ITable<IEntity> getStoredTableByName(String name);

  //method declaration
  IContainer<? extends ITable<IEntity>> getStoredTables();

  //method declaration
  ITime getSchemaTimestamp();

  //method declaration
  <E extends IEntity> IDatabase insertEntity(E entity);
}
