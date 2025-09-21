package ch.nolix.system.objectdata.model;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

public final class FieldFromTableCreator {
  private FieldFromTableCreator() {
  }

  public static IContainer<AbstractField> createFieldsFromTable(final ITable<? extends IEntity> table) {
    return table.getStoredColumns().to(FieldMapper::mapColumnToField);
  }
}
