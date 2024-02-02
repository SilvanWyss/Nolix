//package declaration
package ch.nolix.systemapi.objectdataapi.dataadapterapi;

//own imports
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IResettableChangeSaver;
import ch.nolix.coreapi.programstructureapi.builderapi.EmptyCopyable;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//interface
public interface IDataAdapter<DA extends IDataAdapter<DA>>
extends EmptyCopyable<DA>, IResettableChangeSaver {

  //method declaration
  <E extends IEntity> ITable<E> getStoredTableByEntityType(Class<E> entityType);

  //method declaration
  <E extends IEntity> DA insert(E entity);
}
