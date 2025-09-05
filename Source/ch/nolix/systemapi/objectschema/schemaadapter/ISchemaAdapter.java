package ch.nolix.systemapi.objectschema.schemaadapter;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.resourcecontrol.savecontrol.IResettableChangeSaver;
import ch.nolix.systemapi.objectschema.model.ITable;

public interface ISchemaAdapter extends IResettableChangeSaver {
  ISchemaAdapter addTable(ITable table);

  ITable getStoredTableByName(String name);

  IContainer<ITable> getStoredTables();

  int getTableCount();
}
