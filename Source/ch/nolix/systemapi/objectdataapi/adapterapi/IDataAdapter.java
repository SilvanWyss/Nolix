package ch.nolix.systemapi.objectdataapi.adapterapi;

import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IResettableChangeSaver;
import ch.nolix.coreapi.structurecontrolapi.copierapi.EmptyCopyable;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

public interface IDataAdapter extends EmptyCopyable<IDataAdapter>, IResettableChangeSaver {

  String getDatabaseName();

  <E extends IEntity> ITable<E> getStoredTableByEntityType(Class<E> entityType);

  <E extends IEntity> IDataAdapter insertEntity(E entity, @SuppressWarnings("unchecked") E... entities);
}
