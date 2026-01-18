package ch.nolix.system.objectdata.model;

import ch.nolix.coreapi.container.base.IContainer;

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
  IContainer<AbstractField> findFields() {
    return FieldFromTableCreator.createFieldsFromTable(getStoredParentTable());
  }
}
