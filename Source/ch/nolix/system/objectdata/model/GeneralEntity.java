package ch.nolix.system.objectdata.model;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public final class GeneralEntity extends AbstractEntity {

  private static final FieldFromTableCreator FIELD_FROM_TABLE_EXTRACTOR = new FieldFromTableCreator();

  private final String tableName;

  private GeneralEntity(final Table<GeneralEntity> table) {
    tableName = table.getName();
  }

  public static GeneralEntity forTable(final Table<GeneralEntity> table) {
    return new GeneralEntity(table);
  }

  @Override
  public String getParentTableName() {
    return tableName;
  }

  @Override
  protected IContainer<AbstractField> findFields() {
    return FIELD_FROM_TABLE_EXTRACTOR.createFieldsFromTable(getStoredParentTable());
  }
}
