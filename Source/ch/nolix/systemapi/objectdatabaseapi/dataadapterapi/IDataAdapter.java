//package declaration
package ch.nolix.systemapi.objectdatabaseapi.dataadapterapi;

//own imports
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IResettableChangeSaver;
import ch.nolix.coreapi.programstructureapi.factoryapi.EmptyCopyable;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

//interface
public interface IDataAdapter<DA extends IDataAdapter<DA>>
extends EmptyCopyable<DA>, IResettableChangeSaver {

  //method declaration
  <E extends IEntity> ITable<E> getStoredTableByEntityType(Class<E> entityType);

  //method declaration
  <E extends IEntity> DA insert(E entity);
}
