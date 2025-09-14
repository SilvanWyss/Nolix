package ch.nolix.systemapi.objectdata.modelexaminer;

import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdata.model.IColumn;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

/**
 * @author Silvan Wyss
 * @version 2025-09-14
 */
public interface IColumnExaminer extends IDatabaseObjectExaminer {
  /**
   * @param column
   * @param referenceableTable
   * @return true if the given column contains the given referenceableTable, false
   *         otherwise.
   */
  boolean containsReferenceableTable(IColumn column, ITable<IEntity> referenceableTable);
}
