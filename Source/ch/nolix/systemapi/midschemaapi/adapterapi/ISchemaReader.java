package ch.nolix.systemapi.midschemaapi.adapterapi;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.resourcecontrol.resourceclosing.GroupCloseable;
import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface ISchemaReader extends GroupCloseable {

  boolean columnIsEmpty(String tableName, String columnName);

  ITime getSchemaTimestamp();

  int getTableCount();

  TableDto loadTable(String tableName);

  IContainer<TableDto> loadTables();
}
