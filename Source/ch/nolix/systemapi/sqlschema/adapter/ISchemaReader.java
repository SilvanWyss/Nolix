/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlschema.adapter;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.systemapi.sqlschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public interface ISchemaReader extends GroupCloseable {
  boolean columnIsEmpty(String tableName, String columnName);

  int getTableCount();

  TableDto loadTable(String tableName);

  ExtendedIterable<TableDto> loadTables();

  boolean tableExist();

  boolean tableExists(String tableName);
}
