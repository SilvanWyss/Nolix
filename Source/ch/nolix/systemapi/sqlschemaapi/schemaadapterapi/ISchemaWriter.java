package ch.nolix.systemapi.sqlschemaapi.schemaadapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IResettableChangeSaver;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;

public interface ISchemaWriter extends IResettableChangeSaver {

  void addColumn(String tableName, IColumnDto column);

  void addTable(ITableDto table);

  void deleteColumn(String tableName, String columnName);

  void deleteTable(String tableName);

  IContainer<String> getSqlStatements();

  void renameColumn(String tableName, String columnName, String newColumnName);

  void renameTable(String tableName, String newTableName);
}
