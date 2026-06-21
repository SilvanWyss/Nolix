/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

/**
 * @author Silvan Wyss
 */
public final class FieldFromTableCreator {
  private FieldFromTableCreator() {
  }

  public static ExtendedIterable<AbstractField> createFieldsFromTable(final ITable<? extends IEntity> table) {
    return table.getStoredColumns().to(FieldMapper::mapColumnToField);
  }
}
