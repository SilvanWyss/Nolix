package ch.nolix.systemapi.objectdataapi.datatoolapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.modelexaminerapi.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdataapi.modelapi.IColumn;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

public interface ITableTool extends IDatabaseObjectExaminer {

  <E extends IEntity> IContainer<IColumn> getColumsThatReferenceGivenTable(ITable<E> table);

  IContainer<String> getLocallyDeletedEntities(final ITable<?> table);
}
