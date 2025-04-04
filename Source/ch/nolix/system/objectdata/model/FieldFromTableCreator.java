package ch.nolix.system.objectdata.model;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

public final class FieldFromTableCreator {

  private FieldFromTableCreator() {
  }

  public static IContainer<AbstractField> createFieldsFromTable(final ITable<? extends IEntity> table) {
    return table.getStoredColumns().to(FieldFromColumnCreator::createFieldFromAndWithColumnView);
  }
}
