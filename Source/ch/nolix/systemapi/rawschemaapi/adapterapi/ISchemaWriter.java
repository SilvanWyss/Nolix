package ch.nolix.systemapi.rawschemaapi.adapterapi;

import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IResettableChangeSaver;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;

public interface ISchemaWriter extends IResettableChangeSaver {

  void addColumn(String tableName, ColumnDto column);

  void addTable(TableDto table);

  void deleteColumn(String tableName, String columnName);

  void deleteTable(String tableName);

  void setColumnName(String tableName, String columnName, String newColumnName);

  void setColumnContentModel(String columnId, IContentModelDto contentModelDto);

  void setTableName(String tableName, String newTableName);
}
