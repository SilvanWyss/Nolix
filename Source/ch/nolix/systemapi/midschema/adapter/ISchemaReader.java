/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschema.adapter;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.time.main.ITime;

/**
 * @author Silvan Wyss
 */
public interface ISchemaReader extends GroupCloseable {
  boolean columnIsEmpty(String tableName, String columnName);

  ITime getSchemaTimestamp();

  int getTableCount();

  TableDto loadTable(String tableName);

  ExtendedIterable<TableDto> loadTables();
}
