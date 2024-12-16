package ch.nolix.systemapi.rawschemaapi.schemaadapterapi;

import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IResettableChangeSaver;
import ch.nolix.systemapi.rawschemaapi.schemadto.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

public interface ISchemaWriter extends IResettableChangeSaver {

  void addColumn(String tableName, IColumnDto column);

  void addTable(ITableDto table);

  void deleteColumn(String tableName, String columnName);

  void deleteTable(String tableName);

  void setColumnName(String tableName, String columnName, String newColumnName);

  void setColumnParameterizedFieldType(String columnId, IContentModelDto parameterizedfieldType);

  void setTableName(String tableName, String newTableName);
}
