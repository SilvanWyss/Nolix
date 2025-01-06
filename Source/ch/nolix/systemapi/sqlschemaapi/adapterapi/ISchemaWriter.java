package ch.nolix.systemapi.sqlschemaapi.adapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IResettableChangeSaver;
import ch.nolix.systemapi.sqlschemaapi.dto.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.dto.TableDto;

public interface ISchemaWriter extends IResettableChangeSaver {

  void addColumn(String tableName, ColumnDto column);

  void addTable(TableDto table);

  void deleteColumn(String tableName, String columnName);

  void deleteTable(String tableName);

  IContainer<String> getSqlStatements();

  void renameColumn(String tableName, String columnName, String newColumnName);

  void renameTable(String tableName, String newTableName);
}
