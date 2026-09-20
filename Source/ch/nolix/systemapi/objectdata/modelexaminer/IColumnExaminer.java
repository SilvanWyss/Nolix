/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.modelexaminer;

import ch.nolix.systemapi.database.databaseobjectexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdata.model.IColumn;
import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface IColumnExaminer extends IDatabaseObjectExaminer<IColumn> {
  /**
   * @param column
   * @param referenceableTable
   * @return true if the given column contains the given referenceableTable, false
   *         otherwise
   */
  boolean containsReferenceableTable(IColumn column, ITable<Entity> referenceableTable);
}
