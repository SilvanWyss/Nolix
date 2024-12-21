package ch.nolix.system.objectdata.data;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

public final class FieldFromTableMapper {

  private static final FieldFromColumnMapper FIELD_FROM_COLUMN_MAPPER = new FieldFromColumnMapper();

  public IContainer<AbstractField> createFieldsFromTable(final ITable<? extends IEntity> table) {
    return table.getStoredColumns().to(FIELD_FROM_COLUMN_MAPPER::createFieldFromColumn);
  }
}
