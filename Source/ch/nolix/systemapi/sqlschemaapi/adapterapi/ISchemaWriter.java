package ch.nolix.systemapi.sqlschemaapi.adapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IResettableChangeSaver;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto;

public interface ISchemaWriter extends IResettableChangeSaver {

  void addAdditionalSqlStatements(IContainer<String> additionalSqlStatements);

  void addColumn(String tableName, ColumnDto column);

  void addColumns(String tableName, IContainer<ColumnDto> columns);

  void addTable(TableDto table);

  void deleteColumn(String tableName, String columnName);

  void deleteColumnIfExists(String tableName, String columnName);

  void deleteTable(String tableName);

  void renameColumn(String tableName, String columnName, String newColumnName);

  void renameColumnIfExists(String tableName, String columnName, String newColumnName);

  void renameTable(String tableName, String newTableName);
}
