/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.modeltool;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface ITableTool extends IDatabaseObjectExaminer {
  int getColumnCount(ITable table);

  IContainer<? extends IColumn> getStoredBaseBackReferenceColumns(ITable table);

  IContainer<? extends IColumn> getStoredBackReferencingColumns(ITable table);

  IContainer<? extends IColumn> getStoredReferencingColumns(ITable table);

}
