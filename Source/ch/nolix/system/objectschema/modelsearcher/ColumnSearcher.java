/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.modelsearcher;

import ch.nolix.systemapi.midschema.fieldproperty.BaseFieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.modelsearcher.IColumnSearcher;

/**
 * @author Silvan Wyss
 */
public final class ColumnSearcher implements IColumnSearcher {
  /**
   * {@inheritDoc}
   */
  @Override
  public BaseFieldType getBaseFieldType(final IColumn column) {
    return column.getFieldType().getBaseType();
  }
}
