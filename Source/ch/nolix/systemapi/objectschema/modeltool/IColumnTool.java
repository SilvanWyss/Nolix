/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.modeltool;

import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface IColumnTool extends IDatabaseObjectExaminer {
  boolean isAValidBackReferenceColumn(IColumn column);

  boolean referencesBackGivenColumn(IColumn column, IColumn probableBackReferencedColumn);

  boolean referencesGivenTable(IColumn column, ITable table);
}
