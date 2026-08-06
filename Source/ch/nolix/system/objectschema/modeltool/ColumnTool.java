/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.modeltool;

import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.midschema.fieldproperty.BaseFieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modeltool.IColumnTool;

/**
 * @author Silvan Wyss
 */
public final class ColumnTool extends DatabaseObjectExaminer implements IColumnTool {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isABackReferenceColumn(final IColumn column) {
    return //
    column != null &&
    column.getFieldType().getBaseType() == BaseFieldType.BASE_BACK_REFERENCE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isAReferenceColumn(final IColumn column) {
    return //
    column != null &&
    column.getFieldType().getBaseType() == BaseFieldType.BASE_REFERENCE;
  }

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
        if (!isAReferenceColumn(c) || !referencesGivenTable(c, table)) {
          return false;
        }
      }

      return true;
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean referencesBackGivenColumn(final IColumn column, final IColumn probableBackReferencedColumn) {
    return column.referencesBackColumn(probableBackReferencedColumn);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean referencesGivenTable(final IColumn column, final ITable table) {
    return column.referencesTable(table);
  }
}
