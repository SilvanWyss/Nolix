package ch.nolix.systemapi.sqlschema.adapter;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.resourcecontrol.savecontrol.IResettableChangeSaver;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;

public interface ISchemaWriter extends IResettableChangeSaver {
  void addAdditionalSqlStatements(IContainer<String> additionalSqlStatements);

  void addColumn(String tableName, ColumnDto column);

  void addColumns(String tableName, IContainer<ColumnDto> columns);

  void addTable(TableDto table);

  void addTables(IContainer<TableDto> tables);

  void addTables(TableDto table, TableDto... tables);

  void deleteColumn(String tableName, String columnName);

  void deleteColumnIfExists(String tableName, String columnName);

  void deleteTable(String tableName);

  void renameColumn(String tableName, String columnName, String newColumnName);

  void renameColumnIfExists(String tableName, String columnName, String newColumnName);

  void renameTable(String tableName, String newTableName);
}
