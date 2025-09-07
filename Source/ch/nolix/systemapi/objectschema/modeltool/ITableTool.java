package ch.nolix.systemapi.objectschema.modeltool;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

public interface ITableTool extends IDatabaseObjectExaminer {
  int getColumnCount(ITable table);

  IContainer<? extends IColumn> getStoredBaseBackReferenceColumns(ITable table);

  IContainer<? extends IColumn> getStoredBackReferencingColumns(ITable table);

  IContainer<? extends IColumn> getStoredReferencingColumns(ITable table);

}
