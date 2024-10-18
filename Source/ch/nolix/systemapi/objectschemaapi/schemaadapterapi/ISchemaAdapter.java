package ch.nolix.systemapi.objectschemaapi.schemaadapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IResettableChangeSaver;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public interface ISchemaAdapter extends IResettableChangeSaver {

  ISchemaAdapter addTable(ITable table);

  ITable getStoredTableByName(String name);

  IContainer<ITable> getStoredTables();

  int getTableCount();
}
