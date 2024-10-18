package ch.nolix.systemapi.objectdataapi.dataadapterapi;

import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IResettableChangeSaver;
import ch.nolix.coreapi.programstructureapi.builderapi.EmptyCopyable;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

public interface IDataAdapter extends EmptyCopyable<IDataAdapter>, IResettableChangeSaver {

  <E extends IEntity> ITable<E> getStoredTableByEntityType(Class<E> entityType);

  <E extends IEntity> IDataAdapter insertEntity(E entity, @SuppressWarnings("unchecked") E... entities);
}
