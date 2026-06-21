/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public final class GeneralEntity extends AbstractEntity {
  private final String tableName;

  private GeneralEntity(final Table<GeneralEntity> table) {
    tableName = table.getName();
  }

  public static GeneralEntity forTable(final Table<GeneralEntity> table) {
    return new GeneralEntity(table);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getParentTableName() {
    return tableName;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  ExtendedIterable<AbstractField> findFields() {
    return FieldFromTableCreator.createFieldsFromTable(getStoredParentTable());
  }
}
