/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.modeltool;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface ITableTool extends IDatabaseObjectExaminer {
  int getColumnCount(ITable table);

  IWellOrderContainer<? extends IColumn> getStoredBaseBackReferenceColumns(ITable table);

  IWellOrderContainer<? extends IColumn> getStoredBackReferencingColumns(ITable table);

  IWellOrderContainer<? extends IColumn> getStoredReferencingColumns(ITable table);

}
