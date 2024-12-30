package ch.nolix.systemapi.objectschemaapi.schematoolapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.modelexaminerapi.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public interface ITableTool extends IDatabaseObjectExaminer {

  int getColumnCount(ITable table);

  IContainer<IColumn> getStoredBackReferenceColumns(ITable table);

  IContainer<IColumn> getStoredBackReferencingColumns(ITable table);

  IContainer<IColumn> getStoredReferencingColumns(ITable table);

}
