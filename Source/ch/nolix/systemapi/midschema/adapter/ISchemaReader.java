package ch.nolix.systemapi.midschema.adapter;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.time.moment.ITime;

public interface ISchemaReader extends GroupCloseable {
  boolean columnIsEmpty(String tableName, String columnName);

  ITime getSchemaTimestamp();

  int getTableCount();

  TableDto loadTable(String tableName);

  IContainer<TableDto> loadTables();
}
