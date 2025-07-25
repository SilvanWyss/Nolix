package ch.nolix.systemapi.sqlschemaapi.adapterapi;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.resourcecontrol.resourceclosing.GroupCloseable;
import ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto;

public interface ISchemaReader extends GroupCloseable {

  boolean columnIsEmpty(String tableName, String columnName);

  int getTableCount();

  TableDto loadTable(String tableName);

  IContainer<TableDto> loadTables();

  boolean tableExist();

  boolean tableExists(String tableName);
}
