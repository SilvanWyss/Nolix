/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.schemaadapter;

import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.resourcecontrol.savecontrol.IResettableChangeSaver;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface ISchemaAdapter extends IResettableChangeSaver {
  ISchemaAdapter addTable(ITable table);

  boolean databaseIsEmpty();

  ITable getStoredTableByName(String name);

  IContainer<ITable> getStoredTables();

  int getTableCount();
}
