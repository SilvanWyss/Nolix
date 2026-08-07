/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.schemaadapter;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.resourcecontrol.savecontrol.IResettableChangeSaver;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface SchemaAdapter extends IResettableChangeSaver {
  SchemaAdapter addTable(ITable table);

  boolean databaseIsEmpty();

  ITable getStoredTableByName(String name);

  ExtendedIterable<? extends ITable> getStoredTables();

  int getTableCount();
}
