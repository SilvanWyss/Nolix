package ch.nolix.system.objectdata.model;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

public final class FieldFromTableCreator {

  private static final FieldFromColumnCreator COLUMN_FIELD_CREATOR = new FieldFromColumnCreator();

  public IContainer<AbstractField> createFieldsFromTable(final ITable<? extends IEntity> table) {
    return table.getStoredColumns().to(COLUMN_FIELD_CREATOR::createFieldFromColumnView);
  }
}
