/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.modelexaminer;

import ch.nolix.systemapi.midschema.fieldproperty.BaseFieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.modelexaminer.IColumnExaminer;

/**
 * @author Silvan Wyss
 */
public final class ColumnExaminer implements IColumnExaminer {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isBaseBackReferenceColumn(final IColumn column) {
    return //
    column != null &&
    column.getFieldType().getBaseType() == BaseFieldType.BASE_BACK_REFERENCE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isBaseReferenceColumn(final IColumn column) {
    return //
    column != null
    && column.getFieldType().getBaseType() == BaseFieldType.BASE_REFERENCE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isBaseValueColumn(final IColumn column) {
    return //
    column != null
    && column.getFieldType().getBaseType() == BaseFieldType.BASE_VALUE_FIELD;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isOpenAndEmptyAndNotBackReferenced(final IColumn column) {
    return //
    column != null
    && column.isOpen()
    && column.isEmpty()
    && !column.isBackReferenced();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isValidBaseBackReferenceColumn(final IColumn column) {
    final var fieldType = column.getFieldType();
    final var baseType = fieldType.getBaseType();

    if (baseType == BaseFieldType.BASE_BACK_REFERENCE) {
      final var table = column.getStoredParentTable();
      final var backReferenceableColumns = column.getStoredBackReferenceableColumns();

      for (final var c : backReferenceableColumns) {
        if (!isBaseReferenceColumn(c) || !c.referencesTable(table)) {
          return false;
        }
      }

      return true;
    }

    return false;
  }
}
