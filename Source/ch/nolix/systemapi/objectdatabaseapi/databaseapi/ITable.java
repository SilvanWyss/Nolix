//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface ITable<

    E extends IEntity>
    extends IDatabaseObject, IIdHolder, INameHolder {

  //method declaration
  boolean containsEntityWithId(String id);

  //method declaration
  int getEntityCount();

  //method declaration
  Class<E> getEntityType();

  //method declaration
  IContainer<IColumn> getStoredColumns();

  //method declaration
  IContainer<E> getStoredEntities();

  //method declaration
  E getStoredEntityById(String id);

  //method declaration
  E getStoredEntityByIdOrNull(String id);

  //method declaration
  IDatabase getStoredParentDatabase();

  //method declaration
  ITable<E> insertEntity(E entity);

  //method declaration
  IContainer<E> technicalGetRefEntitiesInLocalData();
}
