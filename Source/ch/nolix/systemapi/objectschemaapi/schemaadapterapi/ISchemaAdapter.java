package ch.nolix.systemapi.objectschemaapi.schemaadapterapi;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.savecontrolapi.IResettableChangeSaver;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public interface ISchemaAdapter extends IResettableChangeSaver {

  ISchemaAdapter addTable(ITable table);

  ITable getStoredTableByName(String name);

  IContainer<ITable> getStoredTables();

  int getTableCount();
}
