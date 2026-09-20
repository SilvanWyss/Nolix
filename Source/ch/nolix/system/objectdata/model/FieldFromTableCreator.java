/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.model.ITable;

/**
 * @author Silvan Wyss
 */
public final class FieldFromTableCreator {
  private FieldFromTableCreator() {
  }

  public static ExtendedIterable<AbstractField> createFieldsFromTable(final ITable<? extends Entity> table) {
    return table.getStoredColumns().to(FieldMapper::mapColumnToField);
  }
}
