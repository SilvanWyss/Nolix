package ch.nolix.systemapi.objectdataapi.datatoolapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

public interface ITableTool extends IDatabaseObjectTool {

  boolean allNewAndEditedMandatoryFieldsAreSet(ITable<?> table);

  boolean canInsertEntity(ITable<?> table);

  boolean canInsertGivenEntity(ITable<?> table, IEntity entity);

  boolean containsEntityWithGivenIdInLocalData(ITable<?> table, String id);

  <E extends IEntity> IContainer<IColumn> getColumsThatReferenceGivenTable(ITable<E> table);

  IContainer<String> getLocallyDeletedEntities(final ITable<?> table);

  boolean hasChanges(ITable<?> table);

  boolean hasInsertedGivenEntityInLocalData(ITable<?> table, IEntity entity);
}
