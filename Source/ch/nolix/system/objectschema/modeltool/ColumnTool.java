/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.modeltool;

import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.system.objectschema.modelexaminer.ColumnExaminer;
import ch.nolix.systemapi.midschema.fieldproperty.BaseFieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.modeltool.IColumnTool;

/**
 * @author Silvan Wyss
 */
public final class ColumnTool extends DatabaseObjectExaminer implements IColumnTool {
  private static final ColumnExaminer COLUMN_EXAMINER = new ColumnExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isAValidBackReferenceColumn(IColumn column) {
    final var fieldType = column.getFieldType();
    final var baseType = fieldType.getBaseType();

    if (baseType == BaseFieldType.BASE_BACK_REFERENCE) {
      final var table = column.getStoredParentTable();
      final var backReferenceableColumns = column.getStoredBackReferenceableColumns();

      for (final var c : backReferenceableColumns) {
        if (!COLUMN_EXAMINER.isBaseReferenceColumn(c) || !c.referencesTable(table)) {
          return false;
        }
      }

      return true;
    }

    return false;
  }
}
