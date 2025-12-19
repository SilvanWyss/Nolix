package ch.nolix.systemapi.sqlschema.adapter;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.systemapi.sqlschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public interface ISchemaReader extends GroupCloseable {
  boolean columnIsEmpty(String tableName, String columnName);

  int getTableCount();

  TableDto loadTable(String tableName);

  IContainer<TableDto> loadTables();

  boolean tableExist();

  boolean tableExists(String tableName);
}
