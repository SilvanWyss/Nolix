package ch.nolix.system.objectdata.model;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

public final class FieldFromTableMapper {

  private static final FieldFromColumnMapper FIELD_FROM_COLUMN_MAPPER = new FieldFromColumnMapper();

  public IContainer<AbstractField> createFieldsFromTable(final ITable<? extends IEntity> table) {
    return table.getStoredColumns().to(FIELD_FROM_COLUMN_MAPPER::createFieldFromColumn);
  }
}
