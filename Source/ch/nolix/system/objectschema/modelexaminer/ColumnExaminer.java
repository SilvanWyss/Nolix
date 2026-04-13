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
  public boolean isBaseReferenceColumn(final IColumn column) {
    return //
    column != null
    && column.getFieldType().getBaseType() == BaseFieldType.BASE_REFERENCE;
  }
}
