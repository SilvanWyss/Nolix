package ch.nolix.system.objectdata.data;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public final class GeneralEntity extends BaseEntity {

  private static final FieldFromTableMapper FIELD_FROM_TABLE_EXTRACTOR = new FieldFromTableMapper();

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
  IContainer<AbstractField> internalLoadFields() {
    return FIELD_FROM_TABLE_EXTRACTOR.createFieldsFromTable(getStoredParentTable());
  }
}
