//package declaration
package ch.nolix.systemapi.sqldatabasebasicschemaapi.schemaadapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IResettableChangeSaver;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.ITableDto;

//interface
public interface ISchemaWriter extends IResettableChangeSaver {

  // method declaration
  void addColumn(String tableName, IColumnDto column);

  // method declaration
  void addTable(ITableDto table);

  // method declaration
  void deleteColumn(String tableName, String columnName);

  // method declaration
  void deleteTable(String tableName);

  // method declaration
  IContainer<String> getSqlStatements();

  // method declaration
  void renameColumn(String tableName, String columnName, String newColumnName);

  // method declaration
  void renameTable(String tableName, String newTableName);
}
